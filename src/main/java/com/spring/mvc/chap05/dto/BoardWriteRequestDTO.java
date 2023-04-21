package com.spring.mvc.chap05.dto;

import lombok.*;

@Setter @Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class BoardWriteRequestDTO {

    private final String title;
    private final String content;
}
