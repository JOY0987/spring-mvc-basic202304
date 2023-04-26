package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScoreSpringRepositoryTest {

    @Qualifier("spring")
    @Autowired
    ScoreRepository repository;

    @Test
    void saveScore() {
        Score score = new Score(new ScoreRequestDTO("뇽뇽", 60, 70, 80));
        repository.save(score);
    }

    @Test
    void update() {
        Score score = new Score("수정햇어요", 90, 90, 90);
        repository.update(score);
    }
}