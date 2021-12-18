package spring_introduction.spring_introduction_project.repository;

import org.springframework.stereotype.Repository;
import spring_introduction.spring_introduction_project.domain.Member;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    // 저장 공간 생성
    // 동시성 문제를 해결하기 위해 실무에서는 ConcurrentHashMap, AtomicLong 사용을 고려함

    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    public void clearStore() {
        store.clear();
    }
    // Test 코드 작동시 한번 돌고 나면 Store 된 내용을 저장소에서 초기화 해줘야 함.

}
