package Hello.Servlet.web.frontcontroller.v1;

import Hello.Servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import Hello.Servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import Hello.Servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*") // v1 경로를 포함한 모든 하위 경로를 받아들인다.
public class FrontControllerServletV1 extends HttpServlet {

    private final Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1(){
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();// 8080:/ ~~ 이 부분을 그대로 받을 수 있다
        ControllerV1 controller = controllerMap.get(requestURI);
        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(request, response);
    }
}
