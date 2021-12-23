package Yunsik.Core.order;

import Yunsik.Core.AppConfig;
import Yunsik.Core.member.Grade;
import Yunsik.Core.member.Member;
import Yunsik.Core.member.MemberService;
import Yunsik.Core.member.MemberServiceImpl;
import Yunsik.Core.order.Order;
import Yunsik.Core.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);

        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
