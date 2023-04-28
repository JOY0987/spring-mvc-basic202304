package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.BoardDetailResponseDTO;
import com.spring.mvc.chap05.dto.BoardListResponseDTO;
import com.spring.mvc.chap05.dto.BoardWriteRequestDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.repository.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class BoardService {

//    private final BoardRepository boardRepository;
    private final BoardMapper boardRepository;


    // 중간처리 기능 자유롭게 사용
    // 목록 중간처리
    public List<BoardListResponseDTO> getList() {
        return boardRepository.findAll()
                .stream()
                .map(BoardListResponseDTO::new)
                .collect(toList());
    }

    // 글 등록 중간 처리
    public boolean register(BoardWriteRequestDTO dto) {
        return boardRepository.save(new Board(dto));
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
}
