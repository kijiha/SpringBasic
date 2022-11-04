package hello.core.member.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefuleServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefuleService statefuleService1 = ac.getBean(StatefuleService.class);
        StatefuleService statefuleService2 = ac.getBean(StatefuleService.class);

        // Thread A ;: 1000원 주문
        statefuleService1.order("name1",10000);
        // Thread B : B 사용자 20000주문
        statefuleService2.order("name2",20000);

        // Thread A : 사용자의 금액
        int price = statefuleService1.getPrice();
        System.out.println("price = "+price);


        Assertions.assertThat(statefuleService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig{
        @Bean
        public StatefuleService statefuleService() {
                return new StatefuleService();
        }

    }


}