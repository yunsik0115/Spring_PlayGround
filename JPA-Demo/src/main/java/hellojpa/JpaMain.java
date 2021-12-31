package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // JPA 작동 방식 실습
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // persistence.xml의 persistence unit name
        // emf는 Application 로딩 시점에 딱 하나 존재해야 함.
        // 행위 당 Transaction 단위의 경우 DB의 커넥션 얻어서 쿼리를 날리고 종료되는 때 마다 각각의 EntityManager를 생성해줘야함.

        EntityManager em = emf.createEntityManager();
        // entitymanager 꺼내고

        EntityTransaction tx = em.getTransaction();
        tx.begin(); // DB Transaction 시작

        // 정석 코드
       /*try {
            // code 작성 후
            Member member = new Member();
            member.setId(1L);
            member.setName("helloA");
            em.persist(member);
            // JPA에서는 Transaction이라는 단위가 엄청 중요함
            // 모든 데이터 변경은 Transaction 내부에서 일어나야 함.

           Member findMember = em.find(Member.class, 1L);
           // 또는
           List<Member> findMembers = em.createQuery("select m from Member as m", Member.class)
                   .setFirstResult(1)
                   .setMaxResults(10) // 1번부터 10개 가져와
                   .getResultList();
           // 테이블이 아니라 엔티티(객체) 대상으로 쿼리를 찾는다.

           for (Member findMember1 : findMembers) {
               System.out.println("findMember1 = " + findMember1);
           }

           System.out.println("findMember.id = " + findMember.getId());
           System.out.println("findMember.name = " + findMember.getName());
           tx.commit();

           // findMember.setName("Another One"); // 이 경우 업데이트 쿼리 필요 X 더티체킹

        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }*/

        try{
            // 비영속 상태
            Member member = new Member();
            member.setId(100L);
            member.setName("Hello JPA");

            System.out.println("== Before ==");
            // persist와 동시에 영속상태로 변경 (영속성 컨텍스트에 의해 관리)
            em.persist(member);
            System.out.println("== After =="); // SQL이 나가는가? persist만으로는 나가지 않는다!


            tx.commit();
        } catch(Exception e){
            tx.rollback();
        } finally {
            em.close();
        }



        // emf를 통해 닫는다.
        emf.close();
    }
}
