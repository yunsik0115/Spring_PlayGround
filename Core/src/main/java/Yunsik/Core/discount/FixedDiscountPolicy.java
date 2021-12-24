package Yunsik.Core.discount;

import Yunsik.Core.member.Grade;
import Yunsik.Core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("fixDiscountPolicy")
public class FixedDiscountPolicy implements DiscountPolicy {

    private final int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) { // enum은 == 쓰는게 맞다.
            return discountFixAmount;
        } else return 0;
    }
}
