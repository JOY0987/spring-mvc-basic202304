package com.spring.mvc.training.dto;

import lombok.*;

@Setter @Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class TableWriteRequestDTO {
    private final String title;
    private final String content;
}
