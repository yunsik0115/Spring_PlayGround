package spring_introduction.spring_introduction_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spring_introduction.spring_introduction_project.domain.Member;
import spring_introduction.spring_introduction_project.service.MemberService;

import java.util.List;

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

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";

    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
