package com.spring.mvc.chap04.entity;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.spring.mvc.chap04.entity.Grade.*;

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Score {
    // 필드 이름과 DB 이름 맞추기
    private String stuName; // 학생 이름
    private int kor, eng, math; // 국, 영, 수 점수
    private int stuNum; // 학번
    private int total; // 총점
    private double average; // 평균
    private Grade grade; // 학점

    // Repository 에서 반복되는 과정을 생성자에서 한번에 처리합니다.
    public Score(ResultSet rs) throws SQLException {
        this.stuNum = rs.getInt("stu_num");
        this.stuName = rs.getString("stu_name");
        this.kor = rs.getInt("kor");
        this.eng = rs.getInt("eng");
        this.math = rs.getInt("math");
        this.total = rs.getInt("total");
        this.average = rs.getDouble("average");
        this.grade = Grade.valueOf(rs.getString("grade"));
    }
    public Score(ScoreRequestDTO dto) {
        this.stuName = dto.getName();
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
    public void changeScore(ScoreRequestDTO dto) {
        this.kor = dto.getKor();
        this.eng = dto.getEng();
        this.math = dto.getMath();
        calcTotalAndAvg(); // 총점, 평균 계산
        calcGrade(); // 등급 계산
    }
}
