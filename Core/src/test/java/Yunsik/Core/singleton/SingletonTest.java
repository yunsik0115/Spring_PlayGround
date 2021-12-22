package Yunsik.Core.singleton;

import Yunsik.Core.AppConfig;
import Yunsik.Core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI Container")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        //1. 조회 호출시마다 객체 생성
        MemberService memberService1 = appConfig.memberService();
        //2. 조회 호출시마다 객체 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른것을 알 수 있다. -> 계속 객체를 생성하는 것은 효율적이지 않다
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
        /*
         객체는 총 4개가 생성됨
         MemberServiceImpl에 더해 MemoryMemberRepository 객체까지 총 4개가 생성된다.
         순수한 DI 컨테이너는 Appconfig를 요청할때마다 객체를 계속 생성한다
         고객 트래픽 초당 100 -> 초당 100개의 객체 생성되고 소멸됨
         해결 방안은 해당 객체는 딱 하나만 생성하고 공유하도록 설계하는것이 필요한데 이 때 싱글톤 패턴을 이용한다.
         */

    }

    @Test
    @DisplayName("싱글톤 패턴을 사용한 객체 사용") // 스프링 컨테이너를 쓰면 전부 다 싱글톤으로 생성해서 관리해줌
    void singletonServiceTest(){
        // new singletonService(); // private 생성자로 막아 컴파일 오류남
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);
        // 같은 인스턴스가 호출된다.

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
        // equal과 issameas 차이?
        // same -> 자바 == 비교
        // equal -> java의 equals 메서드와 같다고 생각하자

    }
}
