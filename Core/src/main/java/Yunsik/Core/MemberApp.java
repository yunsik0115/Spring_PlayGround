package Yunsik.Core;

import Yunsik.Core.member.Grade;
import Yunsik.Core.member.Member;
import Yunsik.Core.member.MemberService;
import Yunsik.Core.member.MemberServiceImpl;

import java.util.Arrays;

public class MemberApp {

    public static void main(String[] args) { // TEST by 순수 자바 코드
        MemberService memberService = new MemberServiceImpl();
        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA);

        Member findMember = memberService.findMember(1L);
        System.out.println("newmember = " + memberA);
        System.out.println("findMember = " + findMember.getName());

    }
}
