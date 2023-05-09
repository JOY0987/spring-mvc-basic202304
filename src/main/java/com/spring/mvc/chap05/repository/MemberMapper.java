package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    // SQL 을 떠올리면서 작성하기! (? 에 들어가는 정보를 매개변수로 설정)

    // 회원 가입
    boolean save(Member member);

    // 회원 정보 조회
    Member findMember(String account);

    // 중복체크(account, email) 기능
    int isDuplicate(
            @Param("type") String type
            , @Param("keyword") String keyword
            );

}
