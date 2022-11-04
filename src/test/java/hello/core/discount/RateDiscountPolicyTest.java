package hello.core.discount;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    MemberService memberService ;
    OrderService orderService;

    DiscountPolicy discountPolicy;
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
        discountPolicy= appConfig.discountPolicy();
    }
    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다. ")
    void vip_o(){
        Member member = new Member(1L, "memberV", Grade.VIP);

        int discount = discountPolicy.discount(member, 10000);

        Assertions.assertThat(discount).isEqualTo(1000);

    }

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다. ")
    void vip_X() {
        Member member = new Member(1L, "memberV", Grade.VIP);

        int discount = discountPolicy.discount(member, 10000);

        Assertions.assertThat(discount).isEqualTo(1000);

    }
}