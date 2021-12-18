package spring_introduction.spring_introduction_project.service;

import spring_introduction.spring_introduction_project.domain.Member;
import spring_introduction.spring_introduction_project.repository.MemberRepository;
import spring_introduction.spring_introduction_project.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    // ctrl shift t -> Test Code Generation
    // private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 동일 repository(test에서도) 사용하기 위해 다음과 같이 코드 작성

    private final MemberRepository memberRepository; // final -> 전체 코드에서 한번만 할당
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    } // 생성자

    //회원가입
    public Long join(Member member){

        // 회원 가입시 같은 이름이 있는 경우? (중복?)

        // Optional<Member> result = memberRepository.findByName(member.getName()); 보다는
        validateDuplicateMember(member);
        // ctrl alt shift m : extract method
        /* result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });*/
        // Optional 내에 Member 객체가 있기 때문에 이런 방식으로 해결 가능 과거에는 try null ...
        // 꺼내고 싶으면 get으로 꺼낼수있지만 권장 X / Orelseget도 많이 씀


        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });
    }

    // 전체 회원 조회

    public List<Member> findMembers() {
        return memberRepository.findAll(); // List 멤버형
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }




}
