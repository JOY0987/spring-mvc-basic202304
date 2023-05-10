package com.spring.mvc.chap05.dto;

import lombok.*;

@Setter @Getter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class LoginRequestDTO {
    // input 의 name 에 맞춰서 필드명 설정

    private String account;
    private String password;
    private boolean autoLogin;

}
