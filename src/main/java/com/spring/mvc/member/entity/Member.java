package com.spring.mvc.member.entity;

import com.spring.mvc.member.repository.MemberMapper;
import lombok.*;

// 데이터베이스와 상호작용
@Getter @Setter
@ToString @EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Member implements MemberMapper {
    private final String id;
    private final String password;
    private final String name;
    private final String phone_num;
}
