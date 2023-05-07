package com.spring.mvc.member.dto;

import com.spring.mvc.member.entity.Member;

import javax.validation.constraints.NotBlank;

public class MemberPostRequestDTO {
    @NotBlank
    private String id;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotBlank
    private String phoneNum;

    public Member toEntity() {
        return Member.builder()
                .id(this.id)
                .password(this.password)
                .name(this.name)
                .phone_num(this.phoneNum)
                .build();
    }
}
