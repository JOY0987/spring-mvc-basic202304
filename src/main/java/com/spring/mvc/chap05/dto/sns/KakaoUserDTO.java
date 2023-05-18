package com.spring.mvc.chap05.dto.sns;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class KakaoUserDTO {
    // 키값과 필드 맞추기
    private long id;

    @JsonProperty("connected_at")
    private LocalDateTime connectedAt;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Setter
    @Getter
    @ToString
    public static class KakaoAccount {
        private String email;
        private Profile profile;

        @Setter
        @Getter
        @ToString
        public static class Profile {
            private String nickname;
            @JsonProperty("profile_image_url")
            private String profileImageUrl;
        }
    }
}
