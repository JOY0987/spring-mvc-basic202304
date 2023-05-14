package com.spring.mvc.chap05.dto.response;

import lombok.*;

// 서버가 로그인한 정보를 클라이언트에 보낸다. (민감한 정보 X, 필요한 정보만!)
@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginUserResponseDTO {

    private String account;
    private String nickName;
    private String email;
}
