package Yunsik.Core.beanfind;

import Yunsik.Core.AppConfig;
import Yunsik.Core.member.MemberService;
import Yunsik.Core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        // 이름, 클래스 형태 로 조회
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class); // 검증
    }
     // 인터페이스로 조회, 인터페이스의 구현체가 대상이 된다.
    @Test
    @DisplayName("빈 이름없이 타입으로만만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        // 이름, 클래스 형태 로 조회
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class); // 검증
    }

    @Test
    @DisplayName("빈 이름없이 구체타입으로만만 조회")
    void findBeanByName2() {
        MemberServiceImpl memberService = ac.getBean("memberService",MemberServiceImpl.class);
        // 이름, 클래스 형태 로 조회
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class); // 검증
    }
    // 항상 역할과 구현은 구분해야하는데 구현에 의존하면 안됨, 위 코드는 좋은 코드는 아님
    // 근데 상황이 항상 이상적으로 돌아가지는 않으니 참고만 해두자

    @Test
    @DisplayName("빈 이름으로 조회 X")
    void findBeanByNameX(){
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxxx", MemberService.class));
    }
}
