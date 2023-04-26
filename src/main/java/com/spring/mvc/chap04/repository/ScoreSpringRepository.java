package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Score;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Repository("spring")
@RequiredArgsConstructor
public class ScoreSpringRepository implements ScoreRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Score> findAll() {
        return jdbcTemplate.query("SELECT * FROM score", (rs, rowNum) -> new Score(rs));
    }

    @Override
    public List<Score> findAll(String sort) {
        Comparator<Score> compator = comparing(Score::getStuNum);
        switch (sort) {
            case "num":
                compator = comparing(Score::getStuNum);
                break;
            case "name":
                compator = comparing(Score::getName);
                break;
            case "average":
                compator = comparing(Score::getAverage).reversed();
                break;
            default:
        }
        List<Score> scoreList = findAll();
        return scoreList.stream()
                .sorted(compator)
                .collect(Collectors.toList());
    }

    @Override
    public boolean save(Score score) {
        String sql = "INSERT INTO score (stu_num, stu_name, kor, eng, math, total, average, grade) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        int result = jdbcTemplate.update(
                sql
                , score.getStuNum()
                , score.getName()
                , score.getKor()
                , score.getEng()
                , score.getMath()
                , score.getTotal()
                , score.getAverage()
                , String.valueOf(score.getGrade()));
        return result == 1;
    }

    @Override
    public boolean deleteByStuNum(int stuNum) {
        String sql = "DELETE FROM score WHERE stu_num=?";
        int result = jdbcTemplate.update(sql, stuNum);
        return result == 1;
    }

    @Override
    public Score findByStuNum(int stuNum) {
        String sql = "SELECT * FROM score WHERE stu_num=?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Score(rs), stuNum);
    }

    @Override
    public boolean update(Score score) {
        String sql = "UPDATE score SET stu_name=?, kor=?, eng=?, math=?, total=?, average=?, grade=? WHERE stu_num=?";
        int result = jdbcTemplate.update(sql, score.getName(), score.getKor(), score.getEng(), score.getMath(),
                score.getTotal(), score.getAverage(), String.valueOf(score.getGrade()), score.getStuNum());
        return result == 1;
    }
}
