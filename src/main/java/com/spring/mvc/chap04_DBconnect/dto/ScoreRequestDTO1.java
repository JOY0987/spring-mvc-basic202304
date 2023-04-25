package com.spring.mvc.chap04_DBconnect.dto;

import lombok.*;

// DTO 는 Setter, NoArgsConstructor 필수
@Setter @Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ScoreRequestDTO1 {

    // 필드명은 input 태그 내 name 과 똑같게 설정
    private String name; // 학생 이름
    private int kor, eng, math; // 국, 영, 수 점수

}
