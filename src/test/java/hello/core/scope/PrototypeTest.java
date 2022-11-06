package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {
    @Test
    public void PrototypeTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(prototypeBean.class);

        prototypeBean bean1 = ac.getBean(prototypeBean.class);
        prototypeBean bean2 = ac.getBean(prototypeBean.class);
        System.out.println("prototypeBean1 = " + bean1);
        System.out.println("prototypeBean2 = " + bean2);

        Assertions.assertThat(bean1).isNotSameAs(bean2);

        ac.close();


    }


    @Scope("prototype") // 스코프는 싱글톤이 기본값이다.
    static class prototypeBean {

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        //
        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }

    }
}
