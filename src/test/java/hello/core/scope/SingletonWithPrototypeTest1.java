package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletonWithPrototypeTest1 {
    @Test
    public void prototypeFind() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();

        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();

        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);


        ac.close();


    }

    @Test
    public void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(PrototypeBean.class,ClientBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();

        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();

        Assertions.assertThat(count2).isEqualTo(1);

        ac.close();


    }





    @Scope("singleton") // 스코프는 싱글톤이 기본값이다.
    static class ClientBean {

        @Autowired  private  ObjectProvider<PrototypeBean> prototypeBeanObjectProvider;
        // 지정한 빈을 컨테이너에서 대신 찾아주는 DL 서비스를 제공 , 스프링 의존
        @Autowired Provider<PrototypeBean> provider;
        // 자바 표준


        public int logic() {
           // PrototypeBean prototypeBean = prototypeBeanObjectProvider.getObject();
            PrototypeBean prototypeBean = provider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }


        @PostConstruct
        public void init() {
            System.out.println("ClientBean.init "+this);
        }
        @PreDestroy
        public void destroy() {
            System.out.println("ClientBean.destroy"+this);
        }

    }


    @Scope("prototype") // 스코프는 싱글톤이 기본값이다.
    static class PrototypeBean {

        private int count =0;

        public int getCount() {
            return count;
        }

        public void addCount() {
            this.count++;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init "+this);
        }

        //
        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy " + this);
        }

    }
}
