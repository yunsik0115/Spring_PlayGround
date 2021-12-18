package spring_introduction.spring_introduction_project.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spring_introduction.spring_introduction_project.domain.Member;
import spring_introduction.spring_introduction_project.repository.MemoryMemberRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;
    // 다른 테스트와 다른 객체...? 객체를 2개를 쓸 이유가 없는데?

    @BeforeEach
    public void beforeEach(){
        // TEST 독립적으로 실행시 마다 각각을 생성해준다.
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        // DI Dependency Injection Method
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void join() {
        // Given 이런 상황에서
        Member member = new Member();
        member.setName("hello");

        // When 이걸 실행했을 때
        Long saveId = memberService.join(member);

        // Then 결과가 어떤 것이 나오는가
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

        // TEST - 정상플로우도 중요하지만 예외 플로우가 더 중요함.

   }

   @Test
   public void 중복_회원_예외() {
        // Given
       Member member1 = new Member();
       member1.setName("spring");

       Member member2 = new Member();
       member2.setName("spring");

        // When
       memberService.join(member1);
       IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
       assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

       /*try {
           memberService.join(member2);
           fail("Exception Must be Occurred");
       }
       catch(IllegalStateException e){
           assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
       }*/

        // Then
   }

}