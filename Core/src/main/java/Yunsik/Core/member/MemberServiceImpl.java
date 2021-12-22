package Yunsik.Core.member;

public class MemberServiceImpl implements MemberService{

    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 인터페이스 의존 + 구현체 의존 (추상화 + 구체화) 좋지않다... DIP 위반
    // 이런거는 전부 AppConfig에 때려 넣자


    private final MemberRepository memberRepository;

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
