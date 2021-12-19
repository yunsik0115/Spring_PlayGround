package Yunsik.Core.discount;

import Yunsik.Core.member.Grade;
import Yunsik.Core.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP)
        {
            return price * discountPercent / 100; // 불안하다.... 할인과 관련된 부분은 개발하기 엄청 어렵다.
        }
        else{
            return 0;
        }
    }

}