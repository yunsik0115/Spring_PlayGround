package hello.SpringMVC.basic.request;

import hello.SpringMVC.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody // 뷰 조회 X Body에 return된 메세지 담김.
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int memberAge) {
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody // 뷰 조회 X Body에 return된 메세지 담김.
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody // 뷰 조회 X Body에 return된 메세지 담김.
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) { // 단 매개변수와 요청 파라메터 이름이 동일해아 사용 가능. (String, int Integer등 단순 타입의 경우 @RequestParam 생략 가능)
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody // 뷰 조회 X Body에 return된 메세지 담김.
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,// HTTP에서 required true의 경우 -> 없으면 오류 남. (양방향 스펙 결정시 정한다 Bad Request = 400)
            // int -> NULL 불가, required false 사용하려면 Integer를 사용해야 함(객체는 nullable, int는 not nullable)
            // 파라미터 이름만 있고 값이 빈 경우 "" , 빈 문자로 취급하기 때문에 OK 반환
            @RequestParam(required = false) Integer age) { // 단 매개변수와 요청 파라메터 이름이 동일해아 사용 가능. (String, int Integer등 단순 타입의 경우 @RequestParam 생략 가능)
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody // 뷰 조회 X Body에 return된 메세지 담김.
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age)
    // 이 경우는 빈 문자여도 defaultValue의 값이 들어감.
    {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody // 뷰 조회 X Body에 return된 메세지 담김.
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String,Object> paramMap) { // 단 매개변수와 요청 파라메터 이름이 동일해아 사용 가능. (String, int Integer등 단순 타입의 경우 @RequestParam 생략 가능)
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        // 파라미터의 값이 1개가 확실한 경우 Map
        // 파라미터의 값이 2개 이상인 경우 MultiValueMap을 사용하자.

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }


}
