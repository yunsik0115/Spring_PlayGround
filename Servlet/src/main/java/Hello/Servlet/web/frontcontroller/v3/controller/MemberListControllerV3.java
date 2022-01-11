package Hello.Servlet.web.frontcontroller.v3.controller;

import Hello.Servlet.domain.member.Member;
import Hello.Servlet.domain.member.MemberRepository;
import Hello.Servlet.web.frontcontroller.ModelView;
import Hello.Servlet.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();
        ModelView mv = new ModelView("members");
        mv.getModel().put("members",members);
        return mv;
    }
}
