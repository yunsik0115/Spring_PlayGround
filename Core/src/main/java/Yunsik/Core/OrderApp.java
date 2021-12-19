package Yunsik.Core;

import Yunsik.Core.member.Grade;
import Yunsik.Core.member.Member;
import Yunsik.Core.member.MemberService;
import Yunsik.Core.member.MemberServiceImpl;
import Yunsik.Core.order.Order;
import Yunsik.Core.order.OrderService;
import Yunsik.Core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class OrderApp {
    public static void main(String[] args) {
        // AppConfig appConfig = new AppConfig(); // DI, OCP 준수를 위한 의존관계 재설정
        // MemberService memberService = appConfig.memberService();
        // OrderService orderService = appConfig.orderService();
        // 이전
        // MemberService memberService = new MemberService();
        // OrderService orderService = new OrderService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "MemberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "ItemA", 10000);
        System.out.println("order = " + order);
        System.out.println("order.calculatePrice = " + order.calculatePrice());
    }
}
