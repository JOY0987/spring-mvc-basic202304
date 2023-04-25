package com.spring.mvc.jdbc;

// Entity : 이 클래스는 데이터베이스 테이블과의 연동을 위한 객체
// DB table 의 컬럼과 1:1로 매칭되는 필드를 적어주세요.

import lombok.*;

// DB 랑 데이터를 주고받으면 Entity, 브라우저랑 데이터를 주고받으면 DTO 라고 부릅니다.
@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private long id;
    private String personName;
    private int personAge;
}
