package hello.SpringMVC.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {
    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ServletInputStream inputStream = request.getInputStream(); // Stream은 바이트 코드 어떤 인코딩일지 지정해줘야 함.
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("message body ={}", messageBody);

        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException{
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("message body ={}", messageBody);
        responseWriter.write("ok");
    }

    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(RequestEntity<String> httpEntity) throws IOException{
        // String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); => HttpEntity<String> httpEntity
        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);

        return new ResponseEntity<>("ok", HttpStatus.CREATED);
    }

    @PostMapping("/request-body-string-v4")
    public HttpEntity<String> requestBodyStringV4(RequestEntity<String> httpEntity) throws IOException{
        // String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); => HttpEntity<String> httpEntity
        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);

        return new ResponseEntity<>("ok", HttpStatus.CREATED);
    }


}
