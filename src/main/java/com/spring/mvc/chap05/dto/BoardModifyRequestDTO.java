package com.spring.mvc.chap05.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@RequiredArgsConstructor
public class BoardModifyRequestDTO {

    private final String title;
    private final String content;
    private final LocalDateTime regDatetime;
}
