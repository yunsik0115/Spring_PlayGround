package Yunsik.Core;

import Yunsik.Core.discount.DiscountPolicy;
import Yunsik.Core.discount.FixedDiscountPolicy;
import Yunsik.Core.discount.RateDiscountPolicy;
import Yunsik.Core.member.MemberRepository;
import Yunsik.Core.member.MemberService;
import Yunsik.Core.member.MemberServiceImpl;
import Yunsik.Core.member.MemoryMemberRepository;
import Yunsik.Core.order.OrderService;
import Yunsik.Core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Spring을 이용한다
// @Configuration이 없으면? -> 싱글톤 보장이 되지 않는다.
public class AppConfig { // 이를 통해 구성 영역인 Appconfig만 바뀌고 나머지 사용 영역에는 영향이 없게 된다.
    // 생성된 객체 인스턴스의 참조를 생성자를 통해서 주입해준다.

    /*
    다음 코드는 XML에서 아래 두 memberService와 MemberRepository를 작성하는 것과 같다.
    <bean id="memberService" class="Yunsik.Core.member.MemberServiceImpl">
        <constructor-arg name="memberRepository" ref="memberRepository"></constructor-arg>
    </bean>
    <bean id="memberRepository" class="Yunsik.Core.member.MemoryMemberRepository"></bean>
     */

    @Bean // 팩토리 메서드에 의한 추가
    public MemberService memberService(){
        System.out.println("Call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
        // 역할이 잘 드러나도록 Ctrl + Alt + M
        // @Bean memberService -> new MemberRepository 호출
        // @Bean orderService -> new MemberRepository 호출 -> 싱글톤 깨져야 정상 아닐까? TEST 코드를 돌려보자
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("Call AppConfig.memberRepository");
        return new MemoryMemberRepository(); // 저장소 변경시 해당 코드만 변경하면 된다.
    }

    @Bean
    public OrderService orderService(){
        System.out.println("Call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        //return new FixedDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
