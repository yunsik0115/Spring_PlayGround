package Yunsik.Core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan // @Component Annotaion이 붙은 클래스를 찾아서 자동으로 스프링 빈 등록
        (
                excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
                // AppConfig 관한 Class의 등록을 방지한다. (AppConfig.java 및 Test Config와 같은 User-Defined 수동 등록 코드 중복 방지)
                // 예제 코드를 삭제하지 않기 위해 제외함.
        )
public class AutoAppConfig {

}
