package Hello.Servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@ServletComponentScan // 서블릿 자동 등록
@SpringBootApplication
public class ServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);
	}
	/*
	@Bean
	InternalResourceViewResolver internalResourceViewResolver(){
		return new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
	}
	스프링 부트가 자동 등록하는 뷰 리졸버 대표적 두 경우
	1. 빈 이름으로 뷰를 찾아서 반환한다. - BeanNameViewResolver
	2. JSP를 처리할 수 있는 뷰를 반환한다 - InternalResourceViewResolver (내부에서 자원이 이동, servlet to jsp -> jsp forward)
	This is what we wrote in application.properties
	*/

}
