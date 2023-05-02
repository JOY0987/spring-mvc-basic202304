package com.spring.mvc.training.repository;

import com.spring.mvc.training.entity.BoardEx;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardExMapper {
    // 게시판 한 페이지 전체 보기
    List<BoardEx> findAll();

    // 게시글 상세 조회
    BoardEx findOne(int boardNo);

    // 게시글 등록
    boolean save(BoardEx board);

    // 게시글 삭제
    boolean deleteByNo(int boardNo);

    // 총 게시글 개수 조회
//    int count(Search search);
}
