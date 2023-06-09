package com.spring.mvc.chap05.entity;

import lombok.*;

import java.time.LocalDateTime;

/*
    create table tbl_reply (
        reply_no INT(10) auto_increment,
        reply_text VARCHAR(1000) not null,
        reply_writer VARCHAR(100) not null,
        reply_date DATETIME default current_timestamp,
        board_no INT(10),
        constraint pk_reply primary key (reply_no),
        constraint fk_reply
        foreign key (board_no)
        references tbl_board (board_no)
        on delete cascade
    );
 */
@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {
    // entity 클래스의 필드명은 데이터베이스의 컬럼명과 동일하게!!
    // 역할 : 데이터베이스와 상호작용
    private long replyNo;
    private String replyText;
    private String replyWriter;
    private LocalDateTime replyDate;
    private long boardNo;
    private String account;
    private String profileImage;
}
