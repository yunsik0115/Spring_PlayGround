package Yunsik.Core;

import Yunsik.Core.discount.DiscountPolicy;
import Yunsik.Core.discount.FixedDiscountPolicy;
import Yunsik.Core.discount.RateDiscountPolicy;
import Yunsik.Core.member.MemberService;
import Yunsik.Core.member.MemberServiceImpl;
import Yunsik.Core.member.MemoryMemberRepository;
import Yunsik.Core.order.OrderService;
import Yunsik.Core.order.OrderServiceImpl;


public class AppConfig { // 이를 통해 구성 영역인 Appconfig만 바뀌고 나머지 사용 영역에는 영향이 없게 된다.
    // 생성된 객체 인스턴스의 참조를 생성자를 통해서 주입해준다.
    public MemberService memberService(){
        return new MemberServiceImpl(MemberRepository()); // 역할이 잘 드러나도록 Ctrl + Alt + M
    }

    private MemoryMemberRepository MemberRepository() {
        return new MemoryMemberRepository(); // 저장소 변경시 해당 코드만 변경하면 된다.
    }

    public OrderService orderService(){
        return new OrderServiceImpl(MemberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        //return new FixedDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
