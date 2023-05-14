package com.spring.mvc.chap05.dto.response;

import com.spring.mvc.chap05.entity.Board;
import lombok.*;

@Getter @Setter
@RequiredArgsConstructor
@EqualsAndHashCode
public class BoardDetailResponseDTO {

    private final int boardNo;
    private final String title; // 5자 이상 줄임
    private final String content; // 30자 이상 줄임
    private final String date; // 날짜패턴 yyyy-MM-dd HH:mm
    private final int likeCount; // 좋아요 개수
    private final String writer; // 작성자 (추가)

    public BoardDetailResponseDTO(Board board) {
        this.boardNo = board.getBoardNo();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.date = BoardListResponseDTO.makePrettierDateString(board.getRegDateTime());
        this.likeCount = board.getLikeCount();
        this.writer = board.getWriter();
    }
}
