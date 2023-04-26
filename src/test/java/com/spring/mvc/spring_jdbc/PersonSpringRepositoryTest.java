package com.spring.mvc.spring_jdbc;

import com.spring.mvc.jdbc.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PersonSpringRepositoryTest {

    @Autowired
    PersonSpringRepository repository;

    @Test
    void savePersonTest() {
        // given
        Person p = new Person();
        p.setPersonName("김스프링");
        p.setPersonAge(2);
        // when
        repository.savePerson(p);
    }

    @Test
    void removePersonTest() {
        // given
        long id = 4L;
        // when
        repository.removePerson(id);
    }

    @Test
    void modify() {
        // given
        Person p = new Person(1L, "수정정뎡", 20);
        repository.modify(p);
    }

    @Test
    void findAllTest() {
        List<Person> people = repository.findAll();
        for (Person p : people) {
            System.out.println("p = " + p);
        }
    }

    @Test
    void findOne() {
        Person p = repository.findOne(3L);
        System.out.println("p = " + p);

//        assertEquals("춘식이", p.getPersonName());
    }
}