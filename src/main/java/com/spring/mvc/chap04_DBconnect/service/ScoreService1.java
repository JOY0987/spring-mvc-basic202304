package com.spring.mvc.chap04_DBconnect.service;//package com.spring.mvc.chap04.service;

import com.spring.mvc.chap04_DBconnect.dto.ScoreListResponseDTO1;
import com.spring.mvc.chap04_DBconnect.dto.ScoreRequestDTO1;
import com.spring.mvc.chap04_DBconnect.entity.Score1;
import com.spring.mvc.chap04_DBconnect.repository.ScoreRepositoty1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// 컨트롤러와 레파지토리 사이 비즈니스 로직 처리
// ex) 트랜잭션 처리, 예외처리, dto 변환처리
@RequiredArgsConstructor
@Service
public class ScoreService1 {

    private final ScoreRepositoty1 scoreRepositoty;

    // 목록조회 중간처리
    public List<ScoreListResponseDTO1> getList(String sort) {

        // ScoreList 에서 원하는 정보만 추출하고 이름을 마스킹해서
        // 다시 DTO 리스트로 변환해줘야 한다.

        return scoreRepositoty.findAll(sort)
                .stream()
                .map(ScoreListResponseDTO1::new)
                .collect(Collectors.toList())
                ;
    }

    // 등록 중간 처리
    public boolean insertScore(ScoreRequestDTO1 dto) {
        // dto(ScoreDTO) 를 entity(Score) 로 변환해야 함.
        // save 명령
        return scoreRepositoty.save(new Score1(dto));
    }

    // 삭제 중간 처리
    public boolean delete(int stuNum) {
        return scoreRepositoty.deleteByStuNum(stuNum);
    }

    // 상세조회, 수정화면조회 중간 처리
    public Score1 retrieve(int stuNum) {
        // 만약에 스코어 전체 데이터 말고
        // 몇개만 추리고 전후 처리해서 달라고 한다면, 여기서 변환해야함...
        return scoreRepositoty.findByStuNum(stuNum);
    }

    // 성적 수정 내역 DB 업데이트
    public boolean update(Score1 score) {
        return scoreRepositoty.update(score);
    }
}
