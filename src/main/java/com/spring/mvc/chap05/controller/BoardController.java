package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.response.BoardDetailResponseDTO;
import com.spring.mvc.chap05.dto.response.BoardListResponseDTO;
import com.spring.mvc.chap05.dto.request.BoardWriteRequestDTO;
import com.spring.mvc.chap05.dto.page.PageMaker;
import com.spring.mvc.chap05.dto.page.Search;
import com.spring.mvc.chap05.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private final BoardService boardService;

    // 1. 게시물 목록 조회
    @GetMapping("/list")
    public String list(Search page,
                       Model model,
                       HttpServletRequest request
    ) {
        boolean flag = false;

        // 쿠키를 확인 (모든 쿠키들을 배열로 얻는 방법)
//        Cookie[] cookies = request.getCookies();
//        for (Cookie c : cookies) {
//            if (c.getName().equals("login")) {
//                flag = true;
//                break;
//            }
//        }

        // 세션을 확인
        Object login = request.getSession().getAttribute("login");
        if (login != null) flag = true;

        // 로그인 쿠키(or 세션) 없으면 나가세여
        if (!flag) return "redirect:/members/sign-in";

        log.info("/board/list : GET");
        log.info("page : {}", page);
        List<BoardListResponseDTO> boardList
                = boardService.getList(page);

        // 페이징 알고리즘 작동
        PageMaker maker = new PageMaker(page, boardService.getCount(page));

        model.addAttribute("boardList", boardList);
        model.addAttribute("maker", maker);
        model.addAttribute("s", page);

        return "chap05/list";
    }

    // 2. 새 글 쓰기
    // 새 글 작성하는 창 띄우기
    @GetMapping("/write")
    public String write() {
        return "chap05/write";
    }

    // 게시 버튼을 누르면 글을 추가하고 목록으로 돌아가기
    @PostMapping("/write")
    public String writeSuccess(BoardWriteRequestDTO dto) {
        boardService.register(dto);
        return "redirect:/board/list";
    }

    // 3. 글 삭제하기
    @GetMapping("/delete")
    public String delete(int bno) {
        boardService.delete(bno);
        return "redirect:/board/list";
    }

    // 4. 상세정보 조회 요청
    @GetMapping("/detail")
    public String detail(int bno, @ModelAttribute("s") Search search, Model model) {
        model.addAttribute("b", boardService.getDetail(bno));
//        model.addAttribute("s", search); // 아노테이션으로 처리
        return "chap05/detail";
    }

    // 5. 글 수정 기능 만들기
    // 수정할 수 있는 창으로 접속
    @GetMapping("/modify")
    public String modify(int no, Model model) {
        BoardDetailResponseDTO board = boardService.getDetail(no);
        model.addAttribute("b", board);
        return "chap05/modify";
    }

    @PostMapping("/modify")
    public String modify(BoardWriteRequestDTO dto, int bno) {
        // 수정중인 게시글 찾기
        // 수정한 내용으로 변경
//        board.modify(dto);
        boardService.modify(dto.getTitle(), dto.getContent(), bno);
        return "redirect:/board/detail?bno=" + bno;
    }

    // 6. 좋아요
    @GetMapping("/like")
    public String like(int no) {
        boardService.likeUp(no);
        return "redirect:/board/detail?bno=" + no;
    }

    // TODO : 댓글기능 만들어보기..도ㅣ면...
}
