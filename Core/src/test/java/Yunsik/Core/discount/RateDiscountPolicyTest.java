package Yunsik.Core.discount;

import Yunsik.Core.member.Grade;
import Yunsik.Core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test // 성공 테스트
    @DisplayName("VIP는 10%할인이 적용되어야 합니다.")
    void vip_o() {
        //given
        Member member = new Member(1L, "MemberA", Grade.VIP);
        // when
        int discount = discountPolicy.discount(member, 10000);
        // then
        assertThat(discount).isEqualTo(1000);
    }

    @Test // 실패테스트
    @DisplayName("VIP가 아닌 경우 할인이 적용되면 안됩니다!")
    void vip_x() {
        //given
        Member member = new Member(1L, "MemberB", Grade.BASIC);
        // when
        int discount = discountPolicy.discount(member, 10000);
        // then
        assertThat(discount).isEqualTo(0);
    }
}