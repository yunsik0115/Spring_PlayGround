package Yunsik.Core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 인터페이스 의존 + 구현체 의존 (추상화 + 구체화) 좋지않다... DIP 위반
    // 이런거는 전부 AppConfig에 때려 넣자


    private final MemberRepository memberRepository;



    @Autowired // @Component로 Spring Bean에 등록만 함, 근데 그러면 의존관계 주입은 어떻게?
                // => 자동 의존관계 주입이 필요함, @AutoWired 애노테이션 사용
    // ac.getBean(MemberRepository.class)가 들어간다고 생각하자
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public MemberRepository getMemberRepository(){
        // for test
        return memberRepository;
    }
}
