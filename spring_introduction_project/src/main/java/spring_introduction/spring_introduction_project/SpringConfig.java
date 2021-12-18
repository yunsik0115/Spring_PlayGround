package spring_introduction.spring_introduction_project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring_introduction.spring_introduction_project.repository.MemberRepository;
import spring_introduction.spring_introduction_project.repository.MemoryMemberRepository;
import spring_introduction.spring_introduction_project.service.MemberService;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
