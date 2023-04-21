package com.spring.mvc.chap04.service;

import com.spring.mvc.chap04.dto.ScoreListResponseDTO;
import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap04.repository.ScoreRepositoty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// 컨트롤러와 레파지토리 사이 비즈니스 로직 처리
// ex) 트랜잭션 처리, 예외처리, dto 변환처리
@RequiredArgsConstructor
@Service
public class ScoreService {

    private final ScoreRepositoty scoreRepositoty;
    
    // 목록조회 중간처리
    /*
        컨트롤러는 데이터베이스를 통해 성적 정보 리스트를 가져오길 원한다.
        그런데 데이터베이스는 성적 정보를 전부 모아서 준다.
        컨트롤러는 정보를 일부만 받았으면 좋겠다.
     */
    public List<ScoreListResponseDTO> getList(String sort) {

        // ScoreList 에서 원하는 정보만 추출하고 이름을 마스킹해서
        // 다시 DTO 리스트로 변환해줘야 한다.

        return scoreRepositoty.findAll(sort)
                    .stream()
                    .map(ScoreListResponseDTO::new)
                    .collect(Collectors.toList());
    }

    // 등록 중간 처리
    /*
        컨트롤러는 나에게 DTO를 줬지만
        레파지토리는 ScoreEntity를 달라고 한다.
        내가 변환해야겠네...
     */
    public boolean insertScore(ScoreRequestDTO dto) {
        // dto(ScoreDTO) 를 entity(Score) 로 변환해야 함.
        // save 명령
        return scoreRepositoty.save(new Score(dto));
    }

    // 삭제 중간 처리
    public boolean delete(int stuNum) {
        return scoreRepositoty.deleteByStuNum(stuNum);
    }

    // 상세조회, 수정화면조회 중간 처리
    public Score retrieve(int stuNum) {
        // 만약에 스코어 전체 데이터 말고
        // 몇개만 추리고 전후 처리해서 달라고 한다면, 여기서 변환해야함...
        return scoreRepositoty.findByStuNum(stuNum);
    }
}