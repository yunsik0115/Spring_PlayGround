package spring_introduction.spring_introduction_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//@Controller // 컨트롤러는 위에 어노테이션이 꼭 필요하다.
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello"; // 템플릿에 있는 hello.html을 통해 렌더링한다 viewResolver
        // ViewName 매핑 resources:templates/ + {viewname} + .html 식
        // spring-boot-devtools 라이브러리를 추가하면 .html 파일 컴파일만 해주면
        // 서버 재시작 없이 view 변경이 가능함
    }

    @GetMapping("hello-mvc") // GET 방식으로 Parameter 인수 필요함 (name)
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-api")
    @ResponseBody // HTTP Body에 그대로 입력 객체 반환시 Json 방식으로 출력
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
