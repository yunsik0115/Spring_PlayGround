package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {
    //JPA 사용 -> Entity Manager 필요

    @PersistenceContext // spring boot가 entitymanager를 주입해준다.
    private EntityManager em;

    public long save(Member member){
        em.persist(member);
        return member.getId();
        // Command와 Query를 분리하라
        // 이 메소드는 Command에 해당. 조회가 사실 필요 없음
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }
}
