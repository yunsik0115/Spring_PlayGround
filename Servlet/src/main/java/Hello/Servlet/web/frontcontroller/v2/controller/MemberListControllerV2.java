package Hello.Servlet.web.frontcontroller.v2.controller;

import Hello.Servlet.domain.member.Member;
import Hello.Servlet.domain.member.MemberRepository;
import Hello.Servlet.web.frontcontroller.MyView;
import Hello.Servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MemberListControllerV2 implements ControllerV2 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();
        request.setAttribute("members",members); // Model에 데이터 주입
        return new MyView("/WEB-INF/views/members.jsp");
    }
}
