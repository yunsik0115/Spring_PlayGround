package Hello.Servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Controller
@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewPath);
        requestDispatcher.forward(request,response); // Controller에서 View로 이동할 때 사용 Servlet에서 JSP 호출
        /*
        다른 서블릿이나 jsp로 이동할 수 있는 기능, 서버 내부에서 다시 호출이 발생함.
        Client에 왔다갔다 하는게 아니다! (redirect가 아님) / redirect -> 클라이언트가 알 수 있고 호출이 두번 일어남
        클라이언트 -> 서버 호출, 서버 안에서 응답이 오고 간 결과를 Client에 보낸 것이다. (한번에!)
        WEB-INF안에 JSP가 있는 경우 외부에서 직접 호출은 불가능하다, 컨트롤러만을 통해 JSP 호출을 기대할때 사용한다.
         */
    }
}
