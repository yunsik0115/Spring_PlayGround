package hello.SpringMVC.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
//@Controller의 경우 view 반환
@RestController // return하는 경우 문자 그대로 HTTPMESSAGEBODY에 반환. -> API개발에서 요긴 함.
public class LogTestController {
    // @Slf4j를 통해 아래 Logger 의존성 자동 주입
    //private final Logger log = LoggerFactory.getLogger(getClass());
    // getClass() : 현재 참조하고 있는 클래스를 확인할 수 있는 클래스임.

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        // soutp는 쓰지 말자...

        // 로그별로 레벨 지정 가능
        log.trace("trace log={}", name);
        log.debug("debug log={}", name); // 개발서버에서 보는 정보 디버그 할때
        log.info("info log={}", name); // 운영 시스템에서 봐야할 비즈니스에 관한 중요한 경보
        log.warn("info log={}", name); //  경고
        log.error("info log={}", name); // 에러
        // TRACE > DEBUG(개발) > INFO(운영) > WARN > ERROR
        // 모든 로그 보고 싶은 경우. application.properties에 logging.level.(하위경로) = trace 설정시 전체 로깅 가능


        return "ok";

    }
}
