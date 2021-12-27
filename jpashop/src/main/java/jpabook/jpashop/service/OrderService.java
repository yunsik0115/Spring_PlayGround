package jpabook.jpashop.service;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */

    @Transactional
    public Long order(Long memberId, Long itemId, int count){
        // Entity 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order); // CASCADE.ALL -> Order를 Persist하면 컬렉션에 와있는 OrderItems도 Persist 날려줌
        // Delivery의 경우도 order persist시 같이 persist를 날린다.
        // CASCADE의 범위? -> 보통 명확하게 설명하긴 애매하나, Order같은 경우 Order가 Delivery와 OrderItem을 관리한다, 요 정도에서만 쓴다.
        // 라이프사이클에서 동일하게 관리하는 경우, 다른 곳에서 참조 불가능한 private인 경우 사용한다.

        return order.getId();
    }

    /**
     * 취소
     */
    public void cancelOrder(Long orderId){
        // 주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        // 주문 취소
        order.cancel();
    }



    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAllByString(orderSearch);
    }

}
