package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScoreMapperTest {

    @Autowired
    ScoreMapper scoreMapper;

    @Test
    @DisplayName("마이바티스로 성적 정보 저장에 성공해야한다.")
    void save() {
        ScoreRequestDTO dto = new ScoreRequestDTO().builder()
                .name("자바")
                .kor(30)
                .eng(40)
                .math(100)
                .build();
        boolean flag = scoreMapper.save(new Score(dto));
        assertTrue(flag);
    }

    @Test
    @DisplayName("마이바티스로 전체 성적 정보 조회에 성공해야한다.")
    void findAll() {
        List<Score> score = scoreMapper.findAll("num");
        score.forEach(System.out::println);
        assertEquals(4, score.size());
    }

    @Test
    @DisplayName("마이바티스로 하나의 성적 정보 조회에 성공해야한다.")
    void findOne() {
        Score score = scoreMapper.findByStuNum(13);
        System.out.println("score = " + score);
        assertEquals("자바", score.getStuName());
        assertEquals(100, score.getMath());
        assertNotNull(score);
    }

    @Test
    @DisplayName("마이바티스로 성적 정보 삭제에 성공해야한다.")
    void remove() {
        boolean flag = scoreMapper.deleteByStuNum(13);
        assertTrue(flag);
    }
}