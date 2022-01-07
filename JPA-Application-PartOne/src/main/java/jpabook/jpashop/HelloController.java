package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        // Model -> Spring UI에서 model에 실어서 데이터를 넘길 수 있다.
        // data라는 key의 값을 hello로 넘긴다.
        model.addAttribute("data", "Hello!!");
        return "hello";
    }
}
