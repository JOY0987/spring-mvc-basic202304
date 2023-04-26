package com.spring.mvc.chap04_DBconnect.repository;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap04.repository.ScoreRepositoryImpl;
import com.spring.mvc.chap04.repository.ScoreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class ScoreRepositoryImplDBTest {

    ScoreRepository repositoty = new ScoreRepositoryImpl();

    @Test
    @DisplayName("전체 학생의 성적 정보를 가져와야 한다.")
    void findAll() {
        List<Score> scoreList = repositoty.findAll();
        System.out.println("scoreList = " + scoreList);
    }

    @Test
    @DisplayName("학생의 성적 정보를 저장해야한다.")
    void save() {
        Score stu1 = new Score(new ScoreRequestDTO("뽀로로", 100, 50, 70));
        Score stu2 = new Score(new ScoreRequestDTO("춘식이", 33, 56, 12));
        Score stu3 = new Score(new ScoreRequestDTO("대길이", 88, 12, 0));
        repositoty.save(stu1);
        repositoty.save(stu2);
        repositoty.save(stu3);
    }

    @Test
    @DisplayName("학생의 성적 정보를 완전히 지워야한다.")
    void deleteByStuNum() {
        repositoty.deleteByStuNum(3);
    }

    @Test
    @DisplayName("학생 한 명의 성적 정보를 가져와야 한다.")
    void findByStuNum() {
        Score score = repositoty.findByStuNum(3);
        System.out.println("score = " + score);
    }
}