package Hello.Servlet.web.frontcontroller.v3;

import Hello.Servlet.web.frontcontroller.ModelView;
import Hello.Servlet.web.frontcontroller.MyView;
import Hello.Servlet.web.frontcontroller.v2.ControllerV2;
import Hello.Servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import Hello.Servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import Hello.Servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import Hello.Servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import Hello.Servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import Hello.Servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*") // v1 경로를 포함한 모든 하위 경로를 받아들인다.
public class FrontControllerServletV3 extends HttpServlet {

    private final Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3(){
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();// 8080:/ ~~ 이 부분을 그대로 받을 수 있다
        ControllerV3 controller = controllerMap.get(requestURI);
        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName); // View 논리이름으로 myView 반환.

        view.render(mv.getModel(), request, response);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
