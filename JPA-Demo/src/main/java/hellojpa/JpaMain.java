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

        /*try{
            // 비영속 상태
            Member member = new Member();
            member.setId(101L);
            member.setName("Hello JPA");

            System.out.println("== Before ==");
            // persist와 동시에 영속상태로 변경 (영속성 컨텍스트에 의해 관리)
            em.persist(member);

            System.out.println("== After =="); // SQL이 나가는가? persist만으로는 나가지 않는다!

            Member findMember = em.find(Member.class, 101L);
            // 조회용 SQL이 나가는지 관찰 (Select Query 안나감, 1차 캐시에 저장됨)
            System.out.println("findMember = " + findMember.getId());
            System.out.println("findMember = " + findMember.getName());
            // 1차 캐시에 저장된 내용을 조회하기 때문에 조회 쿼리가 날아가지 않는다.
            // JPA는 첫 조회하는 시점에 1차 캐시에 해당 내용이 저장된다 -> 2회째 조회에는 SQL 쿼리가 날아가지 않는다.

            Member findMember1 = em.find(Member.class, 101L);
            Member findMember2 = em.find(Member.class, 101L);

            System.out.println(findMember1 == findMember2);
            // JPA는 자바 컬렉션(주소가 같은 것) 처럼, 영속 엔티티의 동일성을 보장해준다.
            // 1차 캐시로 반복가능한 읽기 등급의 트랜잭션 격리 수준을 데이터베이스가 아닌 애플리케이션 차원에서 제공 == 같은 트랜잭션 내에서 (==) 비교하면 True가 나온다

            tx.commit(); // 이 순간 모든 SQL쿼리가 날아감 (더티체킹 제외), 그 전까지는 모든 persist마다 JPA에 계속 내용이 쌓인다.
        } catch(Exception e){
            tx.rollback();
        } finally {
            em.close();
        }*/


        /*try{
            // 영속
            Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "A");

            em.persist(member1);
            em.persist(member2);
            // 쿼리와 엔티티가 쌓인다 여기서 버퍼링이라는 기능을 사용할 수 있다.
            // persist 때마다 쿼리 날리면 최적화를 잘 못한다(할 겨를이 없다), JDBC Batch (persistence.xml 참고)

            System.out.println("================================");


            tx.commit(); // 이때 쿼리가 DB로 전송

        } catch(Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        */

        // 변경감지
        /*try{
            // 영속
            Member member = em.find(Member.class, 150L);
            member.setName("AAA");

            em.detach(member); // 영속성 컨텍스트에서 더 이상 관리하지 않는다. 더티체킹 범위에서 빠짐(준영속 상태로 변경)
            // detach(특정 엔티티), clear() - 영속성 컨텍스트 전체 관리해제, close - 영속성 컨텍스트를 닫아버린다(종료)

            // em.persist(member); 이미 JPA상에 영속상태로 등록이 되어있기 때문에 필요 없다. 쓰면 안된다(아무 이득이 없다)
            // 알아서 찾아와서 Data를 변경한다.
            em.flush(); // 커밋 전에 강제로 플러시를 통해 SQL 쿼리 전송 가능, 1차 캐시는 그대로 유지(쓰기 지연 SQL 저장소만 영향)
            System.out.println("===============");


            tx.commit();

        } catch(Exception e){
            tx.rollback();
        } finally {
            em.close();
        }*/

        try{
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");

            /* 객체 지향성 JPA 적용 전
            member.setTeamId(team.getId()); // ID를 어떻게 얻지? (PK값을 얻고 영속상태가 됨)
            // 근데 이건 좀 객체지향스럽지 않다.

            em.persist(member);

            Member findMember = em.find(Member.class, member.getId());
            Long findTeamId = findMember.getTeamId();
            Team findTeam = em.find(Team.class, findTeamId); // JPA에 이런식으로 계--속 물어봐야함. 연관관계가 없기 때문
            // --> 객체지향스럽지 않다! 협력관계를 만들 수가 없다.... */


            // JPA 객체 지향 적용 후
            member.setTeam(team); // 알아서 JPA에서 PK값 꺼내서 Foreign키 값으로 적용함
            em.persist(member);

            // flush와 clear를 통해 영속성 컨텍스트 초기화해서 SQL문도 직접 확인할 수 있다는 것을 기억

            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam();
            System.out.println("findTeam = " + findTeam.getName());

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
