package com.spring.mvc.chap05.dto.response;

import com.spring.mvc.chap05.entity.Reply;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString @EqualsAndHashCode
@Builder
public class ReplyModifyResponseDTO {
    @NotNull
    @Min(0) @Max(Long.MAX_VALUE) // 반드시 정수 입력하도록 체크

    private Long boardNo; // 원본 글 번호
    @NotNull
    @Min(0) @Max(Long.MAX_VALUE)

    private Long replyNo; // 댓글 번호
    @NotBlank
    private String text; // 수정한 댓글 내용

    // dto 를 entity 로 바꿔서 리턴하는 메서드
    public Reply toEntity() {
        return Reply.builder()
                .boardNo(this.boardNo)
                .replyNo(this.replyNo)
                .replyText(this.text)
                .build();
    }
}
