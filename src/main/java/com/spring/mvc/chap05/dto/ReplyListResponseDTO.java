package com.spring.mvc.chap05.dto;

import com.spring.mvc.chap05.dto.page.PageMaker;
import lombok.*;

import java.util.List;

@Setter @Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyListResponseDTO {
// DTO 의 필드값은 클라이언트에게 전달하는 JSON 키값과 연동된다.

    private int count; // 총 댓글 수
    private PageMaker pageInfo; // 페이지 정보
    private List<ReplyDetailResponseDTO> replies; // 댓글 리스트
}
