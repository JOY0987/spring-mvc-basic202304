package com.spring.mvc.chap04_DBconnect.entity;

import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap04_DBconnect.dto.ScoreRequestDTO1;
import lombok.*;

import static com.spring.mvc.chap04_DBconnect.entity.Grade1.*;

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Score1 {
    
    private String name; // 학생 이름
    private int kor, eng, math; // 국, 영, 수 점수
    private int stuNum; // 학번
    private int total; // 총점
    private double average; // 평균
    private Grade1 grade; // 학점

    public Score1(ScoreRequestDTO1 dto) {
        this.name = dto.getName();
        changeScore(dto); // 학점 계산
    }

    private void calcGrade() {
        if (average >= 90) this.grade = A;
        else if (average >= 80) this.grade = B;
        else if (average >= 70) this.grade = C;
        else if (average >= 60) this.grade = D;
        else this.grade = F;
    }

    private void calcTotalAndAvg() {
        this.total = kor + eng + math;
        this.average = total / 3.0;
    }

    // modify 를 위한 캡슐화 - 순서를 정해두고, 메서드 하나로 코드를 줄인다.
    public void changeScore(ScoreRequestDTO1 dto) {
        this.kor = dto.getKor();
        this.eng = dto.getEng();
        this.math = dto.getMath();
        calcTotalAndAvg(); // 총점, 평균 계산
        calcGrade(); // 등급 계산
    }
}
