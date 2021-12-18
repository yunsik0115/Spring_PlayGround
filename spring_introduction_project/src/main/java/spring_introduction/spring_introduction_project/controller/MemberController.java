package spring_introduction.spring_introduction_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import spring_introduction.spring_introduction_project.service.MemberService;

@Controller
public class MemberController {

    // private final MemberService memberService = new MemberService(); 다른 컨트롤러들이 MemberService를 가져다 쓸 수 있다
    // 근데 이렇게 하면 여러개 인스턴스가 필요한데 굳이 그럴필요없다... -> 스프링 컨테이너

    private final MemberService memberService;

    @Autowired // required a bean of type ... not found
    // spring 컨테이너에서 memberservice를 가져온다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
