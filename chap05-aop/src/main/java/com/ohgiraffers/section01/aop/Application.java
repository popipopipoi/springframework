package com.ohgiraffers.section01.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext("com.ohgiraffers.section01.aop");

        MemberService memberService = context.getBean("memberService", MemberService.class);
        // 따로 이름을 정하지 않았을경우 클래스명을 작성한다. 대신 앞에 소문자로 바꾸야한다.

        System.out.println("======== selectMembers ========");
        System.out.println(memberService.selectMembers());
        System.out.println("======== selectMember ========");
        System.out.println(memberService.selectMember(3L));

    }
}
