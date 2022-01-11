package Hello.Servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Front Controller는 Interface에 의존하면서 일관성 있는 작업 수행가능.
// 각 컨트롤러는 본 인터페이스를 구현, 프론트 컨트롤러는 인터페이스를 호출하여 구현과 관계없이 로직의 일관성 유지 가능.
public interface ControllerV1 {

    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    // 이걸 갖고 인터페이스를 모두 구현한다!

}
