package com.spring.mvc.member.dto;

import lombok.*;

@Getter @Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class MemberListRequestDTO {
    private final String id;
    private final String password;
    private final String name;
    private final String phone_num;
}
