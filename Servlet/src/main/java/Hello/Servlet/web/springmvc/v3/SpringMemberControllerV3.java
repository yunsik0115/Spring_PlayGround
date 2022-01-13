package Hello.Servlet.web.springmvc.v3;

import Hello.Servlet.domain.member.Member;
import Hello.Servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping(value = "/new-form", method = RequestMethod.GET)
    public String newForm(){
        return "new-form";
    }


    @RequestMapping(method = RequestMethod.GET) // -> @GetMapping
    public String members(Model model)
    {
        List<Member> members = memberRepository.findAll();

        //ModelAndView mv = new ModelAndView("members");
        //mv.addObject("members", members);
        model.addAttribute("members", members);
        return "members";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST) // -> @PostMapping
    public String save(@RequestParam("username") String username, @RequestParam("age") int age, Model model) {
        Member member = new Member(username,age);
        memberRepository.save(member);

        //ModelAndView mv = new ModelAndView("save-result");
        //mv.getModel().put("member", member);
        //mv.addObject("member", member);
        model.addAttribute("member", member);
        return "save-result";
    }
}
