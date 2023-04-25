package com.spring.mvc.chap04_DBconnect.repository;

import com.spring.mvc.chap04_DBconnect.entity.Score1;

import java.util.List;

// 역할 명세 (추상화):
// 성적 정보를 잘 저장하고 추가하고 삭제하고 수정해야 한다.
// 그래서 어디에 저장하는 건데?
// 어디에서 조회하니? 어디에서 삭제하니?
public interface ScoreRepositoty1 {
    
    // 성적 정보 전체 목록 조회 명세화
    List<Score1> findAll(); // 일반 목록조회
    default List<Score1> findAll(String sort) {
        return null;
    };  // 정렬 목록조회
        // => 인터페이스 구현 이후 추가하는 메서드이므로, 오버라이딩이 강제되지 않도록 합니다. (default + {})
    

    // 성적 정보 등록
//    boolean save(Score score);
    boolean save(Score1 score);

    // 성적 정보 한개 삭제
//    boolean deleteByStuNum(int stuNum);
    boolean deleteByStuNum(int stuNum);

    // 성적 정보 개별 조회
    Score1 findByStuNum(int stuNum);

    // 성적 정보 수정, DB 업데이트
    boolean update(Score1 score);
}
