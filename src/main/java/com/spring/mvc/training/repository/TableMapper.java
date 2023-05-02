package com.spring.mvc.training.repository;

import com.spring.mvc.training.entity.Table;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TableMapper {
    List<Table> findAll();
    // 게시글 상세 조회
    Table findOne(int boardNo);
    // 게시글 등록
    boolean save(Table table);
    // 게시글 삭제
    boolean deleteByNo(int boardNo);
}
