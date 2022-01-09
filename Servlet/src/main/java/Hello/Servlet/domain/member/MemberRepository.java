package Hello.Servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 동시성 문제가 고려되어있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    //Singleton Pattern : Tomcat 띄울때만 쓰고 안쓸거임.
    private static final MemberRepository instance = new MemberRepository();

    // getInstance()를 통한 조회
    public static MemberRepository getInstance(){
        return instance;
    }
    // Singleton 만들때는 private으로 생성자를 막아야 함.
    private MemberRepository(){
    }

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
        // 밖에서 ArrayList를 변경해도, store의 Value는 건들고 싶지 않다!
        // store의 member를 가져와서 변경하면 store는 변경됨.
    }

    public void clearStore(){
        store.clear();
    }

}
