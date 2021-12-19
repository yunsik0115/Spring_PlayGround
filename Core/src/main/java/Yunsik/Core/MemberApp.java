package Yunsik.Core;

import Yunsik.Core.member.Grade;
import Yunsik.Core.member.Member;
import Yunsik.Core.member.MemberService;
import Yunsik.Core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class MemberApp {

    public static void main(String[] args) { // TEST by 순수 자바 코드
        // AppConfig appConfig = new AppConfig(); // DI, OCP 준수를 위해 의존관계를 재설정했다.
        // MemberService memberService = appConfig.memberService();
        // 이전
        // MemberService memberService = new MemberService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // Appconfig의 환경 설정 정보를 갖고 Spring이 AppConfig 안에 있는 내용들을 Spring Container에 다 집어넣고 관리해준다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);// 메서드 이름으로 등록된다, 뒤에껀 타입이다

        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA);

        Member findMember = memberService.findMember(1L);
        System.out.println("newmember = " + memberA);
        System.out.println("findMember = " + findMember.getName());

    }
}
