package spring_introduction.spring_introduction_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 컨트롤러는 위에 어노테이션이 꼭 필요하다.
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello"; // 템플릿에 있는 hello.html을 통해 렌더링한다 viewResolver
        // ViewName 매핑 resources:templates/ + {viewname} + .html 식
        // spring-boot-devtools 라이브러리를 추가하면 .html 파일 컴파일만 해주면
        // 서버 재시작 없이 view 변경이 가능함
    }
}
