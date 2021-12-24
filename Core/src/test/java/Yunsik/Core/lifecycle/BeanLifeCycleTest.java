package Yunsik.Core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        // ConfigurableApplicationContext - ac.close를 사용하기 위함.
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close(); // ApplicationContext를 닫는다.
    }

    @Configuration
    static class LifeCycleConfig{
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}

/*
생성자 호출, url = null
Connect : null
Call: null Message = 초기화 연결 메세지

NULL? URL 어디갔나?

--> 실패!

객체를 생성 -> 필요한 값을 Setter로 주입(객체 생성 후 주입)
객체를 생성하는 단계에는 url이 없고, 객체를 생성한 다음에 외부에서 수정자 주입을 통해서 setUrl() 이 호출되어야 url이 존재하게 된다.

InitializingBean, DisposableBean 사용 시
생성자 호출, url = null
Connect : http://hello-spring.dev
Call: http://hello-spring.dev Message = 초기화 연결 메세지
19:33:00.027 [main] DEBUG org.springframework.context.annotation.AnnotationConfigApplicationContext - Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@53ce1329, started on Fri Dec 24 19:32:59 KST 2021
끝나면서 안전하게 disconnect 호출함.
close : http://hello-spring.dev

인터페이스를 사용하는 초기화, 종료 방법은 스프링 초창기에 나온 방법들이고, 지금은 다음의 더
나은 방법들이 있어서 거의 사용하지 않는다.

 */
