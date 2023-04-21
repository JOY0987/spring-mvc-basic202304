package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.BoardListResponseDTO;
import com.spring.mvc.chap05.dto.BoardWriteRequestDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;


    // 중간처리 기능 자유롭게 사용
    // 목록 중간처리
    public List<BoardListResponseDTO> getList() {
        return boardRepository.findAll()
                .stream()
                .map(BoardListResponseDTO::new)
                .collect(toList());
    }

    public void register(BoardWriteRequestDTO dto) {
        boardRepository.save(new Board(dto));
    }

    public boolean delete(int boardNo) {
        return boardRepository.deleteByNo(boardNo);
    }
}
