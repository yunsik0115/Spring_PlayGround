package Yunsik.Core;

import Yunsik.Core.member.Grade;
import Yunsik.Core.member.Member;
import Yunsik.Core.member.MemberService;
import Yunsik.Core.member.MemberServiceImpl;
import Yunsik.Core.order.Order;
import Yunsik.Core.order.OrderService;
import Yunsik.Core.order.OrderServiceImpl;

import java.util.Arrays;

public class OrderApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "MemberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "ItemA", 10000);
        System.out.println("order = " + order);
        System.out.println("order.calculatePrice = " + order.calculatePrice());
    }
}
