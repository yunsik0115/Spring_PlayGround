package Hello.Servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/springmvc/old-controller") // Spring Bean의 이름 지정
/**
 * Handler Mapping 우선순위,
 * 1) Annotation 기반의 Controller인 @RequestMapping에서 사용
 * 2) 스프링 빈의 이름으로 핸들러를 찾는다. (현재 클래스에서는 이 방법을 이용한다)
 *
 * HandlerAdapter
 * 1) Annotation 기반의 Controller인 @RequestMapping에서 사용
 * 2) HttpRequestHandler 처리
 * 3) Controller 인터페이스(애노테이션X, 과거에 사용)처리
 */
public class OldController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return new ModelAndView("new-form");
    }
}
