package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.response.BoardDetailResponseDTO;
import com.spring.mvc.chap05.dto.response.BoardListResponseDTO;
import com.spring.mvc.chap05.dto.request.BoardWriteRequestDTO;
import com.spring.mvc.chap05.dto.page.Search;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.repository.BoardMapper;
import com.spring.mvc.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class BoardService {

//    private final BoardRepository boardRepository;
    private final BoardMapper boardRepository;


    // 중간처리 기능 자유롭게 사용
    // 목록 중간처리
    public List<BoardListResponseDTO> getList(Search page) {
        return boardRepository.findAll(page)
                .stream()
                .map(BoardListResponseDTO::new)
                .collect(toList());
    }

    // 글 등록 중간 처리
    public boolean register(BoardWriteRequestDTO dto, HttpSession session) {
        Board board = new Board(dto);
        board.setAccount(LoginUtil.getCurrentLoginMemberAccount(session)); // 로그인한 사람의 계정 정보 추가
        return boardRepository.save(board);
    }

    // 글 삭제 중간 처리
    public boolean delete(int boardNo) {
        return boardRepository.deleteByNo(boardNo);
    }

    // 글 상세조회 중간 처리
    public BoardDetailResponseDTO getDetail(int bno) {
        Board board = boardRepository.findOne(bno);
        // 조회수 상승 처리
//        board.setViewCount(board.getViewCount() + 1);
        boardRepository.upViewCount(bno);
        return new BoardDetailResponseDTO(board);
    }

    public Board findOne(int bno) {
        return boardRepository.findOne(bno);
    }

    // 좋아요 클릭 중간 처리
    public boolean likeUp(int bno) {
        return boardRepository.likeUP(bno);
    }

    public boolean modify(String title, String content, int bno) {
        return boardRepository.modify(title, content, bno);
    }

    public int getCount(Search search) {
        return boardRepository.count(search);
    }
}
