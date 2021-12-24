package Yunsik.Core.discount;

import Yunsik.Core.member.Grade;
import Yunsik.Core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("mainDiscountPolicy")
public class RateDiscountPolicy implements DiscountPolicy{

    private final int discountPercent = 10;

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
