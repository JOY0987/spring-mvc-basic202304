package com.spring.mvc.chap01;

import lombok.*;

// DTO (Data Transfer Object)
// 클라이언트의 요청 데이터를 서버에서 받을 때
// 또는 서버의 응답 데이터를 클라이언트로 보낼 때 사용하는 객체
@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {
    // 주문 관련 필드를 설정
    // 필드명이 클라이언트가 보낸 쿼리스트링 이름과 같아야 함!!
    // 세터와 기본 생성자가 있어야 정상 작동함.
    private String oNum; // 주문번호
    private String goods; // 상품명
    private int amount; // 주문수량
    private int price; // 상품가격
}
