package com.spring.mvc.chap04.dto;

import com.spring.mvc.chap04.entity.Grade;
import com.spring.mvc.chap04.entity.Score;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor // final 만 골라서 초기화
@Getter @Setter @EqualsAndHashCode
public class ScoreListResponseDTO {
    
    // 고정된 상태로 클라이언트에 전달해야하므로 final
    private final int stuNum;
    private final String maskingName; // 첫글자 빼고 * 처리
    private final double average;
    private final Grade grade;

    public ScoreListResponseDTO(Score s) {
        this.stuNum = s.getStuNum();
        this.maskingName = makeMaskingName(s.getStuName());
        this.average = s.getAverage();
        this.grade = s.getGrade();
    }

    // 첫글자만 빼고 다 * 처리하기
    private String makeMaskingName(String originalName) {
        String maskingName = String.valueOf(originalName.charAt(0));
        for (int i = 1; i < originalName.length(); i++) {
            maskingName += "*";
        }
        return maskingName;
    }
}
