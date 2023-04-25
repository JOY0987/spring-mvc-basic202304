package com.spring.mvc.jdbc;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository {

    private String url = "jdbc:mariadb://localhost:3306/spring";
    private String username = "root";
    private String password = "1234";

    public PersonRepository() {
        // 1. 드라이버 클래스를 로딩 (mariadb 커넥터 로딩)
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 사람정보 저장 기능
    public void save(Person person) {

        Connection conn = null;

        try {
            // DB 연결
            // Connection : db 연결 정보를 가지며(성공 또는 실패),
            // SQL 을 작성할 수 있는 statement 객체를 받을 수 있음!
            conn = DriverManager.getConnection(url, username, password);
            // 트랜잭션 시작
            conn.setAutoCommit(false); // 오토커밋 비활성화

            // SQL 을 실행해주는 객체 얻기
            String sql = "INSERT INTO person (person_name, person_age) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // ?값 세팅하기
            pstmt.setString(1, person.getPersonName());
            pstmt.setInt(2, person.getPersonAge());

            // sql 실행하기
            // INSERT, UPDATE, DELETE : executeUpdate()
            // SELECT : executeQuery()

            // executeUpdate() 는 성공한 쿼리의 수를 알려줌
            int result = pstmt.executeUpdate();

            if (result == 1) conn.commit();
            else conn.rollback();

        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 정보 수정
    public void update(Person person) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            conn.setAutoCommit(false); // 오토커밋 비활성화

            String sql = "UPDATE person SET person_name=?, person_age=? WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, person.getPersonName());
            pstmt.setInt(2, person.getPersonAge());
            pstmt.setLong(3, person.getId());

            int result = pstmt.executeUpdate();

            if (result == 1) conn.commit();
            else conn.rollback();

        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    // 정보 삭제
    public void remove(Long id) {
        Connection conn = null;
        // 정보 수정
        try {
            conn = DriverManager.getConnection(url, username, password);
            conn.setAutoCommit(false); // 오토커밋 비활성화

            String sql = "DELETE FROM person WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setLong(1, id);

            int result = pstmt.executeUpdate();

            if (result == 1) conn.commit();
            else conn.rollback();

        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 사람 정보 전체 조회
    public List<Person> findAll() {
        List<Person> people = new ArrayList<>();
        // try - with - resource : close 자동화 (AutoClosable)
        try (Connection conn
                     = DriverManager.getConnection(url, username, password)) {

            conn.setAutoCommit(false);

            String sql= "SELECT * FROM person";
            PreparedStatement pstmt = conn.prepareStatement(sql);

//            pstmt.executeUpdate(); -> DML
            ResultSet rs = pstmt.executeQuery();
            
            // 포인터 커서로 첫번째 행부터 next 호출 시마다 매 다음 행을 지목 : rs.next()
            while (rs.next()) {
                // 위치한 커서에서 데이터 추출
                long id = rs.getLong("id"); // n번째 커서의 아이디 읽기
                String name = rs.getString("person_name"); // n번째 커서의 이름 읽기
                int age = rs.getInt("person_age"); // n번째 커서의 나이 읽기

                Person p = new Person(id, name, age);
                people.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return people;
    }

    // 사람 정보 개별 조회
    public Person findOne(Long id) {
        // try - with - resource : close 자동화 (AutoClosable)
        try (Connection conn
                     = DriverManager.getConnection(url, username, password)) {

            conn.setAutoCommit(false);

            String sql= "SELECT * FROM person WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setLong(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                long pid = rs.getLong("id");
                String name = rs.getString("person_name");
                int age = rs.getInt("person_age");

                Person p = new Person(pid, name, age);
                return p;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
