package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Score;

import java.util.List;

// 역할 명세 (추상화):
// 성적 정보를 잘 저장하고 추가하고 삭제하고 수정해야 한다.
// 그래서 어디에 저장하는 건데?
// 어디에서 조회하니? 어디에서 삭제하니?
public interface ScoreRepository {
    
    // 성적 정보 전체 목록 조회 명세화
    List<Score> findAll(); // 일반 목록조회
    List<Score> findAll(String sort);  // 정렬 목록조회
        // => 인터페이스 구현 이후 추가하는 메서드이므로, 오버라이딩이 강제되지 않도록 합니다. (default + {})
    

    // 성적 정보 등록
    boolean save(Score score);

    // 성적 정보 한개 삭제
    boolean deleteByStuNum(int stuNum);

    // 성적 정보 개별 조회
    Score findByStuNum(int stuNum);

    default boolean update(Score score) {
        return false;
    }
}
