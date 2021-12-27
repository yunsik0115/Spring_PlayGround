package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository // Spring Bean으로 등록
@RequiredArgsConstructor
public class MemberRepository {

    //@PersistenceContext
    private final EntityManager em;

    /*
    @PersistenceUnit
    private EntityManagerFactory emf;
     */

    public void save(Member member){
        em.persist(member); // 영속성 컨텍스트에 멤버 객체가 올라감, 그 때 ID가 생성됨.
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class) // -> 엔티티 객체 대상 쿼리, SQL은 테이블 대상 쿼리
              .getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
