package com.spring.mvc.training.entity;

import com.spring.mvc.training.dto.TableWriteRequestDTO;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Table {
    private int tableNo;
    private String title;
    private String content;
    private int viewCount;
    private LocalDateTime regDateTime;

    // 생성자
    public Table(int tableNo, String title, String content) {
        this.tableNo = tableNo;
        this.title = title;
        this.content = content;
        this.regDateTime = LocalDateTime.now();
    }

    // 글 작성
    public Table(TableWriteRequestDTO dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.regDateTime = LocalDateTime.now();
    }

}
