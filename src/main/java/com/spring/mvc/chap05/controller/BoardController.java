package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.BoardDetailResponseDTO;
import com.spring.mvc.chap05.dto.BoardListResponseDTO;
import com.spring.mvc.chap05.dto.BoardWriteRequestDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    // 1. 게시물 목록 조회
    @GetMapping("/list")
    public String list(Model model) {
        List<BoardListResponseDTO> boardList = boardService.getList();
        model.addAttribute("boardList", boardList);
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
    public String detail(int bno, Model model) {
        BoardDetailResponseDTO board = boardService.detail(bno);
        model.addAttribute("b", board);
        return "chap05/detail";
    }

    // 5. 글 수정 기능 만들기
    // 수정할 수 있는 창으로 접속
    @GetMapping("/modify")
    public String modify(int no, Model model) {
        BoardDetailResponseDTO board = boardService.detail(no);
        model.addAttribute("b", board);
        return "chap05/modify";
    }

    @PostMapping("/modify")
    public String modify(BoardWriteRequestDTO dto, int bno) {
        // 수정중인 게시글 찾기
        Board board = boardService.findOne(bno);
        // 수정한 내용으로 변경
        board.modify(dto);
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
