package com.spring.mvc.chap05.api;

import com.spring.mvc.chap05.dto.response.ReplyListResponseDTO;
import com.spring.mvc.chap05.dto.response.ReplyModifyResponseDTO;
import com.spring.mvc.chap05.dto.request.ReplyPostRequestDTO;
import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

import static org.springframework.http.ResponseEntity.*;

@RestController // ResponseEntity<?> => Header, 선택코드 조작을 위해 걍 붙여..
@RequiredArgsConstructor
@RequestMapping("/api/v1/replies") // 버전이 바뀌면 v 숫자를 올려주기
@Slf4j
// 클라이언트의 접근을 어떤 app 에서만 허용할 것인가?
@CrossOrigin(origins = "http://127.0.0.1:5500") // 5500 포트에서 보낸 요청은 허용해줄게 ㅋ (여러개 작성 가능)
public class ReplyController {

    private final ReplyService replyService;

    // 댓글 목록 조회 요청
    // URL : /api/v1/replies/3/page/1
    //      => 3번 게시물의 댓글 1페이지를 주세요!
    @GetMapping("/{boardNo}/page/{pageNo}")
    public ResponseEntity<?> list(
            @PathVariable long boardNo,
            @PathVariable int pageNo
    ) {
        log.info("/api/v1/replies/{}/page/{} : GET!!", boardNo, pageNo);

        Page page = new Page();
        page.setPageNo(pageNo);
        page.setAmount(10);
        ReplyListResponseDTO replyList = replyService.getList(boardNo, page);

        return ok().body(replyList);
    }

    // 댓글 등록 요청
    @PostMapping
    public ResponseEntity<?> create(
            // 요청 메세지 바디에 JSON 으로 보내주세요! (form 태그로 주면 웹에서 보내는 것만 저장 가능하므로)
           @Validated @RequestBody ReplyPostRequestDTO dto,
            HttpSession session
            , BindingResult result // 검증 결과를 가진 객체
    ) {
        // 입력값 검증에 걸리면 4xx 상태코드 리턴
        if (result.hasErrors()) { // ReplyPostRequestDTO 가 @Validated 에 걸린 경우
            return ResponseEntity
                    .badRequest()
                    .body(result.toString()); // 에러 이유
        }

        log.info("/api/v1/replies : POST! ");
        log.info("param : {} ", dto); // dto 가 잘 들어오는지 확인하는 게 좋다 (원래 debug 사용해야)
        
        // 서비스에 비즈니스 로직 처리 위임
        try {
            // 댓글 등록이 끝나면 갱신된 댓글 리스트 전달
            ReplyListResponseDTO responseDTO = replyService.register(dto, session);
            // 성공시 클라이언트에 응답하기
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            // 문제 발생 상황을 클라이언트에게 전달 (500)
            log.warn("500 Status code response!! caused by : {}", e.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
    }

    // 댓글 삭제 요청
    @DeleteMapping("/{replyNo}")
    public ResponseEntity<?> remove(
//            @PathVariable("replyNo") Long rno // 매핑되어 들어오는 값과 이름이 다르면 () 안에 표시해줘야함
            @PathVariable Long replyNo
    ) {
        if (replyNo == null) {
            return ResponseEntity.badRequest().body("댓글 번호를 보내주세요!");
        }

        log.info("/api/v1/replies/{} DELETE!", replyNo);

        try {
            ReplyListResponseDTO responseDTO = replyService.delete(replyNo);
            return ResponseEntity
                    .ok()
                    .body(responseDTO);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }

    }

    // 댓글 수정 요청
//    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT}) // PUT, PATCH 아무거나 들어와도 수정처리
    @PutMapping
    public ResponseEntity<?> modify(
            @Validated @RequestBody ReplyModifyResponseDTO dto
            , BindingResult result
    ) {
        log.info("dto: {}", dto);
        if (result.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(result.toString());
        }

        log.info("/api/v1/replies : PUT! ");
        log.info("param : {} ", dto);

        try {
            // 수정 이후 댓글 리스트
            ReplyListResponseDTO responseDTO = replyService.modify(dto);
            return ResponseEntity
                    .ok()
                    .body(responseDTO);

        } catch (SQLException e) {
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
    }

}
