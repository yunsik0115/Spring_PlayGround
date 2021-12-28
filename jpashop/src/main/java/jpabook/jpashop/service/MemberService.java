package jpabook.jpashop.service;

import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)// JPA의 모든 데이터 변경이나 로직들은 Transactional 내부에서 무조건 실행되어야 함.
//@AllArgsConstructor
@RequiredArgsConstructor // final로 된 필드들만 DI 해준다.(LOMBOK)
public class MemberService {

    private final MemberRepository memberRepository; // field injection 권장 X

    /*
    @Autowired // 런타임에서 누군가 이걸 바꿀 수 있다. 바꿀 일이 없는데
    public void setMemberRepository(MemberRepository memberRepository){ // setter injection 권장 X
        this.memberRepository = memberRepository;
    }*/

    // 생성자 Injection 권장 TestCase에서도 사용 용이함. => AllArgsConstructor

    /*
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    */

    // 회원 가입
    @Transactional(readOnly = false)
    public Long join(Member member){

        // 중복 회원 검증
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member){
        List<Member> findMembers = memberRepository.findByName(member.getName());
        // Validation Logic 문제 -> WAS 여러개 뜨는데, 동시에 DB에 Insert 요청시 답이 없다
        // --> MultiThread 사용을 고려해서 데이터베이스에 unique 제약조건을 걸자
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }

    }

    @Transactional
    // 회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    @Transactional
    // 회원 단 건 조회
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }


}
