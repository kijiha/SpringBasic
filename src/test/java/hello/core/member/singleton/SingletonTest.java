package hello.core.member.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        MemberService m1 = appConfig.memberService();

        MemberService m2 = appConfig.memberService();

        // 참조값이 다른것을 확인
        System.out.println("MemberService 1 = " + m1);
        System.out.println("MemberService 2 = " + m2);

        // m1 != m2
        assertThat(m1).isNotSameAs(m2);
    }


    @Test
    @DisplayName("스프링  컨테이너")
    void springContainer() {
        // AppConfig appConfig = new AppConfig();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean("memberService",MemberService.class);

        MemberService memberService2 = ac.getBean("memberService",MemberService.class);
        ;

        // 참조값이 다른것을 확인
        System.out.println("MemberService 1 = " + memberService1);
        System.out.println("MemberService 2 = " + memberService2);

        // m1 != m2
        assertThat(memberService1).isSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance() ;
        SingletonService singletonService2 = SingletonService.getInstance() ;

        assertThat(singletonService1).isSameAs(singletonService2);
    }

}
