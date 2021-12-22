package Yunsik.Core.singleton;

import Yunsik.Core.AppConfig;
import Yunsik.Core.member.MemberRepository;
import Yunsik.Core.member.MemberServiceImpl;
import Yunsik.Core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberservice -> memberRepository1 = " + memberRepository1);
        System.out.println("orderservice -> memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);
        // 결과 = 같은 인스턴스가 조회된다.

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);

    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass()); // class type를 확인할 수 있다.

        // bean = class Yunsik.Core.AppConfig$$EnhancerBySpringCGLIB$$e83003c4
        /*
        그런데 예상과는 다르게 클래스 명에 xxxCGLIB가 붙으면서 상당히 복잡해진 것을 볼 수 있다.
        이것은 내가 만든 클래스가 아니라 스프링이 CGLIB라는 바이트코드 조작 라이브러리를 사용해서 AppConfig 클래스를 상속받은 임의의 다른 클래스를 만들고,
        그 다른 클래스를 스프링 빈으로 등록한 것이다!
        @Bean
           public MemberRepository memberRepository() {

            if (memoryMemberRepository가 이미 스프링 컨테이너에 등록되어 있으면?) {
                return 스프링 컨테이너에서 찾아서 반환;
            } else { //스프링 컨테이너에 없으면 기존 로직을 호출해서 MemoryMemberRepository를 생성하고 스프링 컨테이너에 등록
                    return 반환
            }
        }
        @Bean이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고, 스프링 빈이 없으면 생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어진다.
        덕분에 싱글톤이 보장되는 것이다.
        AppConfig@CGLIB는 AppConfig의 자식 타입이므로, AppConfig 타입으로 조회 할 수 있다
         */
    }
}
