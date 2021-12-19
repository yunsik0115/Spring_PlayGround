package spring_introduction.spring_introduction_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_introduction.spring_introduction_project.domain.Member;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    @Override
    Optional<Member> findByName(String name);
}
// 데이터 JPA가 구현체 알아서 만들어서 등록해줌.