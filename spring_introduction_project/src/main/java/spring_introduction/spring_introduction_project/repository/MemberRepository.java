package spring_introduction.spring_introduction_project.repository;

import spring_introduction.spring_introduction_project.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    // 없는 경우 NULL을 반환하는 대신 Optional을 감싸서 반환하는 방식 선호함.
    // Java 8에 들어간 기능
    List<Member> findAll();
}
