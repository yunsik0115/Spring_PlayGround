package Hello.Servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller // OR @RequestMapping @Controller를 클래스 레벨에 동시에 작성시 동일하게 인식
public class SpringMemberFormControllerV1 {
    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process(){
        System.out.println("SpringMemberFormControllerV1.process");
        return new ModelAndView("new-form"); // 논리경로, view-resolver
        // application.properties에 등록된 prefix suffix에 의해 결정.

        //@Controller를 통해, RequestHandlerMapping 정보로 등록하고 @Component 에노테이션 등록으로 Bean 등록 대상이 된다.
    }
}
