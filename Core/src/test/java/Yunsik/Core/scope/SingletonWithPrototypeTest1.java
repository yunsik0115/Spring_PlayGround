package Yunsik.Core.scope;

import ch.qos.logback.core.net.server.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUserPrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean1.logic();
        assertThat(count2).isEqualTo(1);

    }

    @Scope("singleton")
    static class ClientBean{

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;
        // 생성자 주입으로 바꿔주는것이 권장됨. (DIP)

        public int logic(){
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            // Getobject 호출 해야 그 때 Bean을 찾아준다.
            /*
            Prototype.init Yunsik.Core.scope.SingletonWithPrototypeTest1$PrototypeBean@6fe1b4fb
            Prototype.init Yunsik.Core.scope.SingletonWithPrototypeTest1$PrototypeBean@1c32386d
             */
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
        // private final PrototypeBean prototypeBean;

        /*@Autowired
        private ApplicationContext ac;

        public int logic(){
            PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
            // 호출할 때마다 계속 새로운 Prototype Bean을 생성할 것을 요청
            // 직접 의존관계를 찾는 것 -> Dependency Lookup(조회.탐색)
            // 컨택스트 전체 주입하면 스프링 컨테이너에 종속적인 코드가 되고, 단위 테스트도 어려워진다
            // DL의 기능만 제공하는 뭔가 없을까? --> ObjectFactory, ObjectProvider
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }

        @Autowired
        public ClientBean(PrototypeBean prototypeBean){
            this.prototypeBean = prototypeBean;
        }

        public int logic(){
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }*/
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;
        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("Prototype.init "+ this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("Prototype.destroy");
        }
    }
}
