package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

        /* try{
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
            // --> 객체지향스럽지 않다! 협력관계를 만들 수가 없다....


            // JPA 객체 지향 적용 후
            member.setTeam(team);
            // 알아서 JPA에서 PK값 꺼내서 Foreign키 값으로 적용함
            em.persist(member);

            // flush와 clear를 통해 영속성 컨텍스트 초기화해서 SQL문도 직접 확인할 수 있다는 것을 기억
            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers(); // 반대 방향으로도 객체 그래프 탐색 가능(가능하면 단방향이 좋지만...)
            for (Member m : members) {
                System.out.println("members = " + m.getUsername());
            }


            tx.commit();
        } catch(Exception e){
            tx.rollback();
        } finally {
            em.close();
        } */

        try{
            /*Member member = new Member();
            member.setUsername("member1");
            em.persist(member);

            Team team = new Team();
            team.setName("teamA");
            team.getMembers().add(member); // team 테이블에 업데이트가 안되는데...? Member Table에 있는데? -> 그러면 Member를 업데이트 쳐줘야함.
            // 외래키 업데이트
            // 객체와 테이블 차이로 반대편 테이블의 외래키를 관리해야하는 특이한 구조. + JoinColumn 안하면 JoinTable 방식으로 중간에 테이블 하나 추가함.
            em.persist(team);*/

            /*Movie movie = new Movie();
            movie.setDirector("aaaa");
            movie.setActor("bbbb");
            movie.setName("바람과함께사라지다");
            movie.setPrice(10000);

            em.persist(movie);
            em.flush();
            em.clear();

            Movie findMovie = em.find(Movie.class, movie.getId()); // Join으로 가져온다
            System.out.println("findMovie = " + findMovie);
            tx.commit(); */

            /*Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            em.persist(member2);


            em.flush();
            em.clear(); // 영속성 컨텍스트 내용 없어짐


            //Member m1 = em.find(Member.class, member1.getId());
            Member m2 = em.getReference(Member.class, member2.getId());
            //System.out.println("m1 == m2 : " + (m1.getClass()==m2.getClass()));
            System.out.println("isloaded? = " + emf.getPersistenceUnitUtil().isLoaded(m2)); // proxy 초기화 됐는지 확인
            m2.getUsername(); // proxy 강제 초기화 방법 이건 무식한 방법
            Hibernate.initialize(m2); // proxy 강제 초기화(좀 더 엘레강스한 방법, JPA 스펙상에는 없다. 무식한 방법을 쓰면 됨)
            System.out.println("isloaded? = " + emf.getPersistenceUnitUtil().isLoaded(m2));
            // Type 비교시 Proxy로 넘어올지, 실제로 넘어올지 모른다 (Class) instanceof를 통해 비교해야 옳다.
            // 엔티티가 영속성 컨텍스트에 올라가 있는 상태에서 getReference로 호출할 경우, proxy클래스가 올라오지 않는다.

            //Member findMember = em.getReference(Member.class, member.getId());

            /*select query가 나가지 않는다?
            System.out.println("findMember = " + findMember.getClass()); // proxy class -> 가짜 클래스 findMember = class hellojpa.Member$HibernateProxy$2P7Xa975
            DB 조회를 미루는 가짜 엔티티 객체 조회
            System.out.println("findMember.getId() = " + findMember.getId()); // Reference를 찾을 때 이미 getId를 (파라미터로) 사용했기 때문에 쿼리가 나가지 않는다.
            System.out.println("findMember.getId() = " + findMember.getUsername()); // 근데 이때는 나간다? username은 DB에 있다. Reference->가짜를 가져옴.
               */

            /*Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setTeam(team);
            em.persist(member1);



            em.flush();
            em.clear();

            Member m = em.find(Member.class, member1.getId());
            System.out.println("=======================================");
            System.out.println("m = " + m.getTeam().getClass());
            System.out.println("=======================================");

            System.out.println("=======================================");
            m.getTeam().getName();
            System.out.println("=======================================");*/

            /*Parent parent = new Parent();
            Child child1 = new Child();
            Child child2 = new Child();

            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);
            //em.persist(child1);
            // em.persist(child2); // em.persist 3번 호출해줘야 한다.
            // Parent 중심으로 개발중인데 Child 알아서 persist 해주면 안될까...? 이 때 쓰는게 바로 cascade이다!
            // Parent를 perist할때 Collection안에 있는 친구들을 모두 Persist 날려주겠다! CASCADE.ALL

            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());
            //findParent.getChildList().remove(0);

            // Orphan Removal에 의해 List에서 사라진 Child Entity에 대한 Delete Query가 자동으로 생성 전송됨.
            // 참조된 엔티티는 다른곳에서 참조하지 않는 고아 객체로 보고 삭제한다.
            // 참조하는 곳이 한곳일때 사용하며 특정 엔티티가 개인 소유할 때 사용한다
            // 개념적으로 부모 제거시 자식은 고아가 된다, 활성화 하면 부모를 제거할 때 자식은 자동으로 제거됨.
            // CascadeType.REMOVE 처럼 동작하게 됨.
            em.remove(findParent); // orphan remove에 의해 List에 있는 child들 모두 사라짐 */

            List<Member> result = em.createQuery("select m From Member as M where m.username like '%kim%'", Member.class).getResultList();
            // JPQL Query To Object(Not Table)


            // 동적 쿼리 대신 Criteria
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);

            Root<Member> m = query.from(Member.class);
            CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("username"),"kim"));
            List<Member> resultList = em.createQuery(cq).getResultList();
            // 장점 --> 자바 코드로 쳐서 오타나면 컴파일 오류를 내보냄, 동적쿼리를 짜기에 JPQL보다 훨씬 편리함.

            String username = "asdfasdf";
            if( username != null){
                cq = cq.where(cb.equal(m.get("username"),"kim"));
            }
            List<Member> resultList2 = em.createQuery(cq).getResultList();  // SQL 어떻게 돌아가는지 모호하고 복잡해서 실용성이 없다 (유지보수가 어렵다)
            // QueryDSL 사용을 권장한다.

            String sql = "select MEMBER_ID, city, street, zipcode from MEMBER";
            List resultList3 = em.createNativeQuery(sql, Member.class).getResultList();
            // Native Query를 날릴 수 있다 (JDBCTemplate를 사용할 수도 있다)
            // flush는 commit할때와 query(Native 포함) 날아갈때 동작함. JDBC, SpringTemplate 사용 직전에 따로 flush를 해줘야 한다.


            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally{
            em.close();
        }

        // emf를 통해 닫는다.
        emf.close();
    }
}
