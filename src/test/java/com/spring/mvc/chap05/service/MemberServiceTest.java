package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.request.LoginRequestDTO;
import com.spring.mvc.chap05.dto.request.SignUpRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.spring.mvc.chap05.service.LoginResult.SUCCESS;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("SignUpDTO 를 전달하면 회원가입에 성공해야 한다.")
    void joinTest() {
        SignUpRequestDTO dto = new SignUpRequestDTO();
        dto.setAccount("apeach");
        dto.setPassword("apeach123");
        dto.setName("루피");
        dto.setEmail("aaa@ddd.com");

        // when
        memberService.join(dto);

    }

    @Test
    @DisplayName("계정명이 abc123 인 회원의 로그인 시도 결과 검증을 상황별로 수행해야 한다.")
    void loginTest() {
        // given
        LoginRequestDTO dto = new LoginRequestDTO();
        dto.setAccount("abc123");
        dto.setPassword("abc123!");

        // when
        LoginResult result = memberService.authenticate(dto);

        // then
        assertEquals(SUCCESS, result);
    }

}