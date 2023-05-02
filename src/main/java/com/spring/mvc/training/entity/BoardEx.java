package com.spring.mvc.training.entity;

import lombok.*;

import java.time.LocalDateTime;

@Setter @Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardEx {
    private int boardNo;
    private String title;
    private String content;
    private int viewCount;
    private LocalDateTime regDateTime;

//    // 생성자
//    public Table(int boardNo, String title, String content) {
//        this.boardNo = boardNo;
//        this.title = title;
//        this.content = content;
//        this.regDateTime = LocalDateTime.now();
//    }


}
