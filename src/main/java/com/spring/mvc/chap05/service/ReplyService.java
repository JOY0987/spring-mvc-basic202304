package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.response.LoginUserResponseDTO;
import com.spring.mvc.chap05.dto.response.ReplyDetailResponseDTO;
import com.spring.mvc.chap05.dto.response.ReplyListResponseDTO;
import com.spring.mvc.chap05.dto.response.ReplyModifyResponseDTO;
import com.spring.mvc.chap05.dto.request.ReplyPostRequestDTO;
import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.dto.page.PageMaker;
import com.spring.mvc.chap05.entity.Reply;
import com.spring.mvc.chap05.repository.ReplyMapper;
import com.spring.mvc.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyService {

    private final ReplyMapper replyMapper;

    // 댓글 목록 조회 서비스
    public ReplyListResponseDTO getList(long boardNo, Page page) {
        List<ReplyDetailResponseDTO> replies = replyMapper.findAll(boardNo, page)
                .stream()
                .map(reply -> new ReplyDetailResponseDTO(reply))
                .collect(Collectors.toList());
        // Entity 리스트를 DTO 리스트로 변경

        int count = replyMapper.count(boardNo);

        return ReplyListResponseDTO.builder()
                .count(count)
                .pageInfo(new PageMaker(page, count))
                .replies(replies)
                .build();
    }

    // 댓글 등록 서비스
    // 이렇게 코딩하는 습관을 들이장
    //                  매개변수에 final 을 붙이면 매개변수를 받은 후 수정이 불가능하며 안전하게 지킬 수 있다.
    public ReplyListResponseDTO register(final ReplyPostRequestDTO dto, HttpSession session)
        throws SQLException
    {
        log.debug("register service execute!!"); // 항상 debug 로그를 띄우자
        // dto 를 entity 로 변환
        Reply reply = dto.toEntity();

        // 세션에서 댓글 작성자 데이터 가져오기
        LoginUserResponseDTO member = (LoginUserResponseDTO) session.getAttribute(LoginUtil.LOGIN_KEY);
        reply.setAccount(member.getAccount());
        reply.setReplyWriter(member.getNickName()); // 클라이언트의 데이터는 조작이 가능하기 때문에 보안상 세션에서 다시 받아와야함

        boolean flag = replyMapper.save(reply);

        // 예외 처리
        if (!flag) {
            log.warn("reply registered fail!");
            throw new SQLException("댓글 저장 실패!");
        }
        return getList(dto.getBno(), new Page(1, 10));
    }

    // 댓글 삭제 서비스
    @Transactional // 트랜잭션 처리 (하나라도 실패하면 롤백, 성공하면 커밋)
    public ReplyListResponseDTO delete(final long replyNo)
        throws Exception {
        long boardNo = replyMapper.findOne(replyNo).getBoardNo();
        replyMapper. deleteOne(replyNo);

        return getList(
                boardNo
                , new Page(1, 10)
        );
    }

    // 댓글 수정 요청
    @PutMapping("/{replyNo}")
    public ReplyListResponseDTO modify(
            ReplyModifyResponseDTO dto // DTO 새로 만드세요 검증 넣으세요
    ) throws SQLException {

        replyMapper.modify(dto.toEntity());
        return getList(
                dto.getBoardNo()
                , new Page(1, 10)
        );
    }
}
