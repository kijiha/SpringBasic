package hello.core.autowired;


import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {
    @Test
    void AutowiredOption() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean {
        /*
        @Autowired(required=false) : 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출 안됨
        org.springframework.lang.@Nullable : 자동 주입할 대상이 없으면 null이 입력된다.
        Optional<> : 자동 주입할 대상이 없으면 Optional.empty 가 입력된다.
    */

        @Autowired(required = false)
        public void noBean1(Member member) {
            System.out.println("member1 = " + member);
        }

        @Autowired
        public void noBean2(@Nullable Member member) {
            System.out.println("member2 = " + member);
        }

        @Autowired
        public void noBean3(Optional<Member>  member) {
            System.out.println("member3 = " + member);
        }
    }

}
