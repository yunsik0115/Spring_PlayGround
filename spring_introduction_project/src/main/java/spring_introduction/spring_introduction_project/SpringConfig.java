package spring_introduction.spring_introduction_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring_introduction.spring_introduction_project.repository.JdbcMemberRepository;
import spring_introduction.spring_introduction_project.repository.MemberRepository;
import spring_introduction.spring_introduction_project.repository.MemoryMemberRepository;
import spring_introduction.spring_introduction_project.service.MemberService;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    //Datasource Injection
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JdbcMemberRepository(dataSource);
    }
}
