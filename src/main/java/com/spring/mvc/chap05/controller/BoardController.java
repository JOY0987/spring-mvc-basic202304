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
import org.springframework.web.bind.annotation.RequestParam;

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

    // 2. 글 클릭시 게시글 상세 조회
//    @GetMapping()
//    public String detail(BoardListResponseDTO dto, Model model) {
//        System.out.println("card 상세보기 : GET 요청");
//
//        return "chap05/detail";
//    }

    // 3. 새 글 쓰기
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

    // 4. 글 삭제하기
    @GetMapping("/delete")
    public String delete(int bno) {
        boardService.delete(bno);
        return "redirect:/board/list";
    }

    // 5. 상세정보 조회 요청
    @GetMapping("/detail")
    public String detail(int bno, Model model) {
        BoardDetailResponseDTO board = boardService.detail(bno);
        model.addAttribute("b", board);
        return "chap05/detail";
    }
}
