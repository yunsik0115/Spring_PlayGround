package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello-jpql");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            // 별칭 alias가 필수다. select m from Member m (이 m이 alias에 해당함)
            // ANSI SQL 표준 지원
            // GROUP BY ORDER HAVING 모두 일반 SQL과 동일
            // TYPEQUERY - 반환 타입이 명확할때, Query - 그렇지 않을때

            Member member = new Member();
            member.setUsername("Mason");
            member.setAge(10);
            em.persist(member);

            TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
            // 내가 2번 파라미터에 타입 정보를 명시했기 대문에 TypedQuery로 날아간다.
            Query query1 = em.createQuery("select m.username, m.age from Member m"); // 타입이 명확하지 않다
            TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class); // 타입이 명확하다

            // 결과조회
            // 1) 결과가 컬렉션인 경우 .getResultList() 사용
            List<String> resultList = query2.getResultList();
            for (String s : resultList) {
                // 이런식으로 ...
            }
            // 2) 하나인 경우
            String singleResult = query2.getSingleResult();

            // 안나오거나 많이 나올 경우? -> getResultList는 그냥 빈 리스트를 반환
            // getSingleResult의 경우 결과가 없어도 1개보다 많아도 둘다 Exception이 터진다. => 별루.. Spring Data JPA에서는 추상화로 제공된다. 결과 없으면 null이나 optional 반환

            // Parameter Binding for name
            // Position은 쓰지 말자
            TypedQuery<Member> query3 = em.createQuery("select m from Member m where m.username =:username", Member.class);
            query3.setParameter("username", "member1");
            List<Member> resultList1 = query3.getResultList();
            Member singleResult1 = query3.getSingleResult();

            tx.commit();
        } catch (Exception e){
            tx.rollback();;
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
