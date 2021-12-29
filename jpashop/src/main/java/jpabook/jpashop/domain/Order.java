package jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
//테이블명이 ORDER 가 아니라 ORDERS 인 것은 데이터베이스가 order by 때문에 예약어로 잡고 있는 경우가 많다. 그래서 관례상 ORDERS 를 많이 사용한다.
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 생성메서드 방식을 제외한 경우 생성을 제한한다.
public class Order {

    @Id @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // Order와 Member는 다대일 관계
    @JoinColumn(name = "member_id") // 맵핑을 무엇으로 할 것인가 Foriegn Key?
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY) // Foreign key를 어디에 둘 것인가? -> Access를 많이 하는 곳에 둔다.
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; // Hibernate가 알아서 지원해 줌

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // Order CANCEL 두개  상황

    //==연관관계 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //== 생성 메서드 ==//
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems){
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    //== 비즈니스 로직==//
    /**
     * 주문취소
     */

    public void cancel(){
        if(delivery.getDeliveryStatus() == DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가합니다");
        }
        this.setStatus(OrderStatus.CANCEL); // 원래는 변경하면 update 쿼리 날려야함. (더티체킹으로 해결) --- 더티체킹
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    //==조회 로직==//

    /**
     *
     * 전체 주문 가격 조회
     */
    public int getTotalPrice(){
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }
}
