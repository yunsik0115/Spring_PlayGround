package Yunsik.Core.beanfind;

import Yunsik.Core.discount.DiscountPolicy;
import Yunsik.Core.discount.FixedDiscountPolicy;
import Yunsik.Core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면 중복 오류가 발생한다")
    void findBeanByParentTypeDuplicate(){
        // DiscountPolicy bean = ac.getBean(DiscountPolicy.class); // No Unique Bean Definition
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }
    // 부모타입으로 조회할 때 어디까지 조회하나? -- 이걸 알아야한다 == 자식까지 전부 조회한다

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면 이름을 지정해서 조회하자")
    void findBeanByParentTypeBeanName(){
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        // Type은 DiscountPolicy여도 구현 객체는 RateDiscountPolicy가 나온다
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회") // 좋지는 않음
    void findBeanBySubType(){
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class); // 구체적인 타입으로 딱 지정
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기") // 좋지는 않음
    void findAllBeanByParentType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
            // 이건 실무에선 쓰면 안됨 프로그램 로직에서 끝내야
        }
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - Object 타입으로")
    void findAllBeanByObjectType(){
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
    }


    @Configuration
    static class TestConfig{
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            // public RateDiscountPolicy rateDiscountPolicy로 해도 되는데?
            // 역할과 구현을 항상 쪼갠다! 코드 가독성을 위해(이 클래스가 어떤 역할인지?)
            // 의존관계 파악에도 명확하게 도움이 된다!
            return new RateDiscountPolicy();
        }
        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixedDiscountPolicy();
        }
    }
}

// 실무에서 쓸 일은 거의 없다
// Spring Container가 알아서 주입해준다.
// 기본기능이고 가끔 순수한 자바 애플리케이션에서 스프링 컨테이너를 형성해야할 때 쓴다!
