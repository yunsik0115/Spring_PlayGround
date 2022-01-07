package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional // 기본적으로 Commit 안하고 Rollback 해버림
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    @Rollback(false) // Rollback하지 않는다 (Insert 쿼리를 쏜다 영속성 컨텍스트 플러싱)
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long savedId = memberService.join(member);

        // then
        em.flush(); // 영속성 컨텍스트에 Flush하라고 수동으로 명령 (수동이 우선)  기본적인 Transactional에서는 rollback default.
        assertEquals(member, memberRepository.findOne(savedId));
        // Persist만 한다고 해서 Insert쿼리 날아가지 않음
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception{
       //given
        Member member1 = new Member();
        Member member2 = new Member();

        member1.setName("kim");
        member2.setName("kim");
        //when

        memberService.join(member1);
        memberService.join(member2);
        // then
        // fail("예외가 발생해야 한다."); // 코드가 돌다가 fail에 오면 안된다.

        /*try{
            memberService.join(member2);
        } catch(IllegalStateException e){
            return ;
        }
         어노테이션이 알아서 해쥼! ><*/
        fail("예외가 발생해야 한다.");
    }
}
