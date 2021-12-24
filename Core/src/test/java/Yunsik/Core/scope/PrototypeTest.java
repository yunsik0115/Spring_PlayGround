package Yunsik.Core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypebean1 : ");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypebean2 : ");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        ac.close(); // Prototype이여서 @PreDestroy 메서드 호출 X 관리 대상이 아님(생성과 동시에)
    }

    @Scope("prototype") // Component가 없다?
    // 위에서 new Anootation..에서 등록하면 자동으로 등록
    static class PrototypeBean{
        @PostConstruct
        public void init(){
            System.out.println("singleton bean init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("singleton bean destroy");
        }
    }
}
