package com.ohgiraffers.section01.aop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor   // 모든 매개변수를 받는 생성자
public class MemberDTO {

    private long id;
    private String name;
}
