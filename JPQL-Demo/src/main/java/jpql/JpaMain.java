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


            for(int i = 0; i<100; i++){
                Member member = new Member();
                member.setUsername("Mason");
                member.setAge(i);
                em.persist(member);

            }


            List<Member> resultList = em.createQuery("select m from Member m order by m.age desc", Member.class)
                    .setFirstResult(1) // offset limit
                    .setMaxResults(10)
                    .getResultList();

            System.out.println("size :" + resultList.size());
            for (Member member1 : resultList) {
                System.out.println("member1 = " + member1);
                
            }

            /*List<Member> result1 = em.createQuery("select m from Member m", Member.class).getResultList();
            // Result -> 과연 영속성 컨텍스트에 의해 관리가 되는가? -> 리스트에 있는 모든 내역들 다 관리 대상으로 들어감

            List<Member> result2 = em.createQuery("select m.team from Member m", Member.class).getResultList();
            // SQL은 Join으로 들어감 --> 이런 경우에는 join m.team t 해주는게 맞다. (성능 튜닝, SQL 인식을 위함 m.team으로 하면 예측이 잘 안됨)

            List<Address> result3 = em.createQuery("select o.address from Order o", Address.class).getResultList();
            // Order안에 있는 Address를 쿼리하는 것 임베디드 타입 프로젝션 한계 : address로 시작 불가 무조건 o.address로 해줘야 함. 엔티티로부터 시작해야 함.


            List result4 = em.createQuery("select distinct m.username, m.age from Member m").getResultList();
            // vs
            List<Object[]> result5 = em.createQuery("select distinct m.username, m.age from Member m").getResultList();
            // 타입 2개인데 어떻게 가져오지? 1) Query 타입 조회 2) Object 타입으로 조회 3) new 명령어로 조회

            Object o = result4.get(0);
            Object[] resultlist = (Object[]) o;
            System.out.println("resultlist = " + resultlist[0]);
            System.out.println("resultlist = " + resultlist[1]);

            new  명령어로 조회 多
            1) 단순 값을 DTO로 바로 조회, 패키지명을 포함한 전체 클래스명 입력, 순서와 타입이 일치하는 생성자의 필요

            List<MemberDTO> resultList1 = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m").getResultList();
            MemberDTO memberDTO = resultList1.get(0);
            System.out.println("memberDTO = " + memberDTO.getUsername());
            System.out.println("memberDTO = " + memberDTO.getAge()); */




           /*TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
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
            Member singleResult1 = query3.getSingleResult();*/

            tx.commit();
        } catch (Exception e){
            tx.rollback();;
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
