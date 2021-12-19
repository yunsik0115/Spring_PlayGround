package Yunsik.Core;

import Yunsik.Core.discount.FixedDiscountPolicy;
import Yunsik.Core.member.MemberService;
import Yunsik.Core.member.MemberServiceImpl;
import Yunsik.Core.member.MemoryMemberRepository;
import Yunsik.Core.order.OrderService;
import Yunsik.Core.order.OrderServiceImpl;


public class AppConfig {
    // 생성된 객체 인스턴스의 참조를 생성자를 통해서 주입해준다.
    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService(){
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixedDiscountPolicy());
    }
}
