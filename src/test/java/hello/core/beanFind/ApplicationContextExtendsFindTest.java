package hello.core.beanFind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestAppConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시 자식이 둘 이상 있으면 중복이 발생한다")
    void findBeanByParentTypeDuplicate() {

        assertThrows(NoUniqueBeanDefinitionException.class,()->ac.getBean(DiscountPolicy.class));

    }

    @Test
    @DisplayName("부모 타입으로 조회시, 빈 이름을 지정하면 된다.")
    void findBeanByName() {

        assertThat(ac.getBean("RateDiscountPolicy", DiscountPolicy.class)).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllByParentType() {

        Map<String, DiscountPolicy> beans = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beans.size()).isEqualTo(2);

        for (String key : beans.keySet()) {
            System.out.println("Key : " + key + " Value : " + beans.get(key));

        }

    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBySubType() {
        assertThat( ac.getBean(RateDiscountPolicy.class)).isInstanceOf(RateDiscountPolicy.class);


    }



    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - Object")
    void findAllByObjectType() {

        Map<String, Object> beans = ac.getBeansOfType(Object.class);


        for (String key : beans.keySet()) {
            System.out.println("Key : " + key + " Value" + beans.get(key));

        }


    }
        @Configuration
    static class TestAppConfig {


        @Bean
        DiscountPolicy RateDiscountPolicy(){
           return new RateDiscountPolicy();

        }


        @Bean
        DiscountPolicy FixDiscountPolicy(){
            return new FixDiscountPolicy();

        }


    }

}

