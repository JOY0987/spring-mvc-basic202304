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
    public List<Score> findAll(String sort) { // sort 를 쿼리 ? 로 처리할 수 없음
        String sql = "SELECT * FROM score ";
        switch (sort) {
            case "num":
                sql += "ORDER BY stu_num";
                break;
            case "name":
                sql += "ORDER BY stu_name";
                break;
            case "average":
                sql += "ORDER BY average DESC"; // 평균은 높은 순으로 출력해야함
                break;
            default:
        }
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Score(rs));
    }

    @Override
    public boolean save(Score score) {
        String sql = "INSERT INTO score (stu_name, kor, eng, math, total, average, grade) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql
                , score.getName()
                , score.getKor()
                , score.getEng()
                , score.getMath()
                , score.getTotal()
                , score.getAverage()
                , String.valueOf(score.getGrade())) == 1;
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
        return jdbcTemplate.update(sql, score.getName(), score.getKor(), score.getEng(), score.getMath(),
                score.getTotal(), score.getAverage(), String.valueOf(score.getGrade()), score.getStuNum()) == 1;
    }
}
