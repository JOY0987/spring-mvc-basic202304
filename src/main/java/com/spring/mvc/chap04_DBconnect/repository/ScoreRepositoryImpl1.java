package com.spring.mvc.chap04_DBconnect.repository;

import com.spring.mvc.chap04_DBconnect.entity.Grade1;
import com.spring.mvc.chap04_DBconnect.entity.Score1;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

@Repository // 스프링 빈 등록 : 객체의 생성의 제어권을 스프링에게 위임 (@Repository: 저장소 빈에 사용)
public class ScoreRepositoryImpl1 implements ScoreRepositoty1 { // Impl : 구현체가 한개일때 제목에 사용

    private String url = "jdbc:mariadb://localhost:3306/spring";
    private String username = "root";
    private String password = "1234";

    public ScoreRepositoryImpl1() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Score1> findAll() {
        List<Score1> scoreList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            conn.setAutoCommit(false);
            String sql = "SELECT * FROM score";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int stuNum = rs.getInt("stu_num");
                String stuName = rs.getString("stu_name");
                int kor = rs.getInt("kor");
                int eng = rs.getInt("eng");
                int math = rs.getInt("math");
                int total = rs.getInt("total");
                double average = rs.getDouble("average");
                Grade1 grade = Grade1.valueOf(rs.getString("grade")); // 읽어올 때는 Grade 로 형변환

                Score1 score = new Score1(stuName, kor, eng, math, stuNum, total, average, grade);
                scoreList.add(score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scoreList;
    }

    @Override
    public List<Score1> findAll(String sort) {

        Comparator<Score1> compator = comparing(Score1::getStuNum);
        switch (sort) {
            case "num":
                compator = comparing(Score1::getStuNum);
                break;
            case "name":
                compator = comparing(Score1::getName);
                break;
            case "average":
                compator = comparing(Score1::getAverage).reversed();
                break;
            default:
        }

        List<Score1> scoreList = findAll();

        return scoreList.stream()
                .sorted(compator)
                .collect(toList())
                ;
    }

    @Override
    public boolean save(Score1 score) {

       try (Connection conn = DriverManager.getConnection(url, username, password)) {

           conn.setAutoCommit(false);
           String sql = "INSERT INTO score (stu_num, stu_name, kor, eng, math, total, average, grade) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
           PreparedStatement pstmt = conn.prepareStatement(sql);

           pstmt.setInt(1, score.getStuNum());
           pstmt.setString(2, score.getName());
           pstmt.setInt(3, score.getKor());
           pstmt.setInt(4, score.getEng());
           pstmt.setInt(5, score.getMath());
           pstmt.setInt(6, score.getTotal());
           pstmt.setDouble(7, score.getAverage());
           pstmt.setString(8, String.valueOf(score.getGrade())); // 저장할 때는 String(CHAR)으로 형변환

           int result = pstmt.executeUpdate();

           if (result == 1) conn.commit();
           else conn.rollback();

       } catch (SQLException e) {
           e.printStackTrace();
       }
        return false;
    }

    @Override
    public boolean deleteByStuNum(int stuNum) {

        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            conn.setAutoCommit(false);
            String sql = "DELETE FROM score WHERE stu_num=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, stuNum);

            int result = pstmt.executeUpdate();

            if (result == 1) conn.commit();
            else conn.rollback();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Score1 findByStuNum(int stuNum) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            conn.setAutoCommit(false);
            String sql = "SELECT * FROM score WHERE stu_num=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, stuNum);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
//                stuNum = rs.getInt("stu_num");
                String stuName = rs.getString("stu_name");
                int kor = rs.getInt("kor");
                int eng = rs.getInt("eng");
                int math = rs.getInt("math");
                int total = rs.getInt("total");
                double average = rs.getDouble("average");
                Grade1 grade = Grade1.valueOf(rs.getString("grade")); // 읽어올 때는 Grade 로 형변환

                Score1 score = new Score1(stuName, kor, eng, math, stuNum, total, average, grade);
                return score;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Score1 score) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            conn.setAutoCommit(false);
            String sql = "UPDATE score SET kor=?, eng=?, math=?, total=?, average=?, grade=? WHERE stu_num=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, score.getKor());
            pstmt.setInt(2, score.getEng());
            pstmt.setInt(3, score.getMath());
            pstmt.setInt(4, score.getTotal());
            pstmt.setDouble(5, score.getAverage());
            pstmt.setString(6, String.valueOf(score.getGrade())); // 저장할 때는 String(CHAR)으로 형변환
            pstmt.setInt(7, score.getStuNum());

            int result = pstmt.executeUpdate();

            if (result == 1) conn.commit();
            else conn.rollback();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

}
