package Yunsik.Core.order;

import Yunsik.Core.discount.DiscountPolicy;
import Yunsik.Core.member.Member;
import Yunsik.Core.member.MemberRepository;
import Yunsik.Core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{ // 주문 생성 요청시

    private final MemberRepository memberRepository; // = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy; // 인터페이스에만 의존하도록 설계를 변경함. 이전의 문제는 하단 주석을 참고
    // 근데 이렇게만 작성하면 NULLPOINTEXCEPTION 어떻게 DIP를 지킬 수 있을까?
    // 누군가 클라이언트인 OrderServiceImpl에 DiscountPolicy의 구현 객체를 대신 생성하고 주입해주어야 한다


    // 1)private final DiscountPolicy discountPolicy = new FixedDiscountPolicy();
    // 2)private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // 훌륭하긴 한데...
    // 할인정책 변경을 위해서는 클라이언트인 OrderServiceImpl의 소스코드의 변경이 필요하다
    // 역할과 구현을 충실하게 분리 - O
    // 다형성을 활용하고 인터페이스와 구현 객체 분리 - O
    // OCP, DIP 준수 -> ?
    /*
    클래스 의존관계를 보면 추상(인터페이스)뿐만 아니라 구체(구현)클래스에도 의존하고 있다.
    추상 인터페이스 의존 = DiscountPolicy
    구체 클래스 = FixDiscountPolicy, RateDiscountPolicy ==> DIP 위반에 해당함 (항상 추상화에 의존해야한다) -- 망했다!

    As a Result, FixDiscountPolicy를 RateDiscountPolicy로 변경하는 순간 OrderServiceImpl의 소스코드 또한 변경을 해야한다. (의존성)
    OCP 위반에 해당한다.

    지금 코드 변경시 클라이언트 코드에 영향을 주기 때문에, OCP를 위반하는 것이 된다.
    */

    /*
        해결 방법
        클라이언트 코드 MemberServiceImpl은 DiscountPolicy의 Interface와 구체 클래스 모두 의존하고 있다. 따라서 의존 관계 변경이 필요하다.
     */

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    // 어떤 Policy가 들어올지 신경 쓰지 않는다.

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); // 회원정보 조회
        int discountPrice = discountPolicy.discount(member, itemPrice); // 확장성에 따라 member 자체를 넘겼다 프로젝트에 따라서는 Grade만 넘겨도 된다.
        // 좋은 설계 : OrderService 입장에서 할인에 대한 내용을 모름, DiscountPolicy가 모든 책임 진다. - 단일 책임 원칙을 잘 지켰다 -
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
