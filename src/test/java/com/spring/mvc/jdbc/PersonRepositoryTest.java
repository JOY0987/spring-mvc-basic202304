package com.spring.mvc.jdbc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    PersonRepository repository;

    @Test
    @DisplayName("사람의 이름과 나이 정보를 DB person table 에 잘 삽입해야 한다.")
    void saveTest() {
        // given
        Person p = new Person();
        p.setPersonName("케로로");
        p.setPersonAge(100);
        // when
        repository.save(p);
        // then
    }

    @Test
    @DisplayName("사람의 이름과 나이 정보를 DB person table 에 잘 수정해야 한다.")
    void updateTest() {
        // given
        Person p = new Person();
        p.setPersonName("수정");
        p.setPersonAge(99);
        p.setId(1L);
        // when
        repository.update(p);
        // then
    }

    @Test
    @DisplayName("사람의 이름과 나이 정보를 DB person table 에 잘 수정해야 한다.")
    void removeTest() {
        // given
        long id = 2L;
        // when
        repository.remove(id);
        // then
    }
}