package Yunsik.Core.discount;

import Yunsik.Core.member.Grade;
import Yunsik.Core.member.Member;

public class FixedDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) { // enum은 == 쓰는게 맞다.
            return discountFixAmount;
        } else return 0;
    }
}
