package spring_introduction.spring_introduction_project.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import spring_introduction.spring_introduction_project.domain.Member;
import spring_introduction.spring_introduction_project.repository.MemberRepository;
import spring_introduction.spring_introduction_project.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 컨테이너와 테스트를 함께 실행한다
@Transactional // 커밋을 해야 DB에 반영됨 TEST가 끝나고 Rollback 처리
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    /* @AfterEach
    public void afterEach(){memberRepository.clearStore();} 무쓸모 -> Transactional 때문 */

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