package Hello.Servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        // MessgaeBody의 내용을 InputStream으로 얻을 수 있다. Stream을 bytecode로 바꾸려면 Spring의 StreamUtils를 이용할 수 있다.
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        // Byte를 문자로 변환할때는 항상 인코딩을 명시해야한다.
        System.out.println("messageBody = " + messageBody);
        response.getWriter().write("ok");
        // TEST에는 POSTMAN을 이용한다. RAW TYPE의 TEXT를 이용한다.
        // HTTP 메세지 데이터가 그대로 나온다.
    }
}
