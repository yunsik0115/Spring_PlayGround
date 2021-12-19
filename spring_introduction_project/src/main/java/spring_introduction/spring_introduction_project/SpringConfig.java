package spring_introduction.spring_introduction_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring_introduction.spring_introduction_project.aop.TimeTraceAop;
import spring_introduction.spring_introduction_project.repository.*;
import spring_introduction.spring_introduction_project.service.MemberService;

@Configuration
public class SpringConfig {

    //Datasource Injection
    //private DataSource dataSource;

    //private EntityManager em;

    private final MemberRepository memberRepository;

    @Autowired // SpringDataJpaMemberRepository가 구현체 생성.
    public SpringConfig(MemberRepository memberRepository){
        this.memberRepository = memberRepository; // Injection
    }

    /*@Autowired
    public SpringConfig(DataSource dataSource, EntityManager em){
        this.dataSource = dataSource;
        this.em = em;
    }*/

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }


    /*@Bean
    public MemberRepository memberRepository() {

    }*/

    /*
    * 지금은 SpringConfig에서 dataSource를 받아서 JdbcTemplateMemberRepository에 직접 주입하고 있습니다.
    * 이렇게 직접 주입해도 되고, 말씀하신 것 처럼 직접 주입 받지 않고, @Autowired로 주입 받아도 됩니다. 여러가지 방법이 가능합니다.
    * 그런데 여기서 JdbcTempalteMemberRepository를 @Bean을 사용해서 수동으로 스프링 빈으로 등록하는데,
    * dataSource가 생성자의 필수 파라미터 이기 때문에 SpringConfig에서는 dataSource를 꼭 받아와서 넣어주어야 합니다. */
}
