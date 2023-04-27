package com.spring.mvc.etc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LogServiceTest {

    @Autowired
    LogService service;

    @Test
    void ttt() {
        service.showLog();
    }
}