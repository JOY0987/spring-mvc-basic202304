package com.spring.mvc.chap04.dto;

import lombok.*;

// DTO 는 Setter, NoArgsConstructor 필수
@Setter @Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ScoreRequestDTO {

    // 필드명은 input 태그 내 name 과 똑같게 설정
    private String name; // 학생 이름
    private int kor, eng, math; // 국, 영, 수 점수

    // 학번을 modify 만들 때 추가했는데, 이렇게 필드를 추가해야하는 경우에는
    // DTO 를 재활용하는 게 아니라 하나 더 만들어야 한다.
}
