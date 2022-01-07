package Hello.Servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello") // 서블릿 이름, url 매핑
public class HelloServlet extends HttpServlet {
    // servlet을 사용하려면 httpservlet을 상속받아야한다.

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // HTTP 호출이 들어오면 WAS가 request response 객체를 만들어서 서블릿에 던져준다.
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String username = request.getParameter("username"); // 쿼리 파라미터
        System.out.println("username = " + username);
        // Request 관련 코드 끝

        // Response 관련 메세지 시작
        // HTTP 응답 헤더
        response.setContentType("text/plain"); // 단순 문자를 보내겠다
        response.setCharacterEncoding("utf-8"); // 요즘 시대에는 EUC-KR은 사용하지 않는다.
        // content-length 등 나머지 정보들은 WAS가 알아서 해준다.

        // HTTP 응답 바디
        response.getWriter().write("hello " + username);

        /*application.properties에 다음과 같이 작성
        logging.level.org.apache.coyote.http11=debug
        # 서버 재시작시 HTTP 관련한 모든 정보가 나옴.
        # 실 가동 서버에서 사용시 성능 저하 이슈가 있기 때문에 유지보수, 개발단계에서만 사용
         */

        // HttpServlet... -> 인터페이스 , 구현체는 WAS에 존재
    } // servlet이 호출되면 service가 호출된다.
}
