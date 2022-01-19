package hello.thymeleaf.basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basic")
public class BasicController {
    @GetMapping("text-basic")
    public String textBasic(Model model){
        model.addAttribute("data", "Hello-<b>Spring!</b>");
        // 기본적으로 이스케이프 문자로 제공함, 태그로 렌더링하지 않는다. -> 기본값으로 쓰는게 맞다.
        // Unescape = use utext rather than text
        return "basic/text-basic";
    }

    @GetMapping("text-unescaped")
    public String textUnescaped(Model model){
        model.addAttribute("data", "Hello-<b>Spring!</b>");
        // 기본적으로 이스케이프 문자로 제공함, 태그로 렌더링하지 않는다.
        // Unescape = use utext rather than text
        return "basic/text-unescaped";
    }
}
