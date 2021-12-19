package Yunsik.Core.order;

import Yunsik.Core.discount.DiscountPolicy;
import Yunsik.Core.discount.FixedDiscountPolicy;
import Yunsik.Core.member.Member;
import Yunsik.Core.member.MemberRepository;
import Yunsik.Core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{ // 주문 생성 요청시

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixedDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); // 회원정보 조회
        int discountPrice = discountPolicy.discount(member, itemPrice); // 확장성에 따라 member 자체를 넘겼다 프로젝트에 따라서는 Grade만 넘겨도 된다.
        // 좋은 설계 : OrderService 입장에서 할인에 대한 내용을 모름, DiscountPolicy가 모든 책임 진다. - 단일 책임 원칙을 잘 지켰다 -
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
