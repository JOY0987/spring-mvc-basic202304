package com.spring.mvc.chap04.controller;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap04.repository.ScoreRepositoryImpl;
import com.spring.mvc.chap04.repository.ScoreRepositoty;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/*
    # 요청 URL
    1. 학생 성적 정보 등록화면을 보여주고 성적 정보 목록 조회 처리
       - /score/list : GET
    2. 성적 정보 등록 처리 요청
       - /score/register : POST
    3. 성적 정보 삭제 요청
       - /score/remove : POST
    4. 성적 정보 상세 조회 요청
       - /score/detail : GET
 */
@Controller
@RequestMapping("/score")
//@AllArgsConstructor : 모든 필드를 초기화하는 생성자 // 자동 @Autowired
@RequiredArgsConstructor // : final 필드만 초기화하는 생성자
public class ScoreController {

    // 저장소에 의존해야 데이터를 받아서 클라이언트에게 응답할 수 있음
    private final ScoreRepositoty repository;

    // 만약에 클래스의 생성자가 단 1개라면
    // 스프링이 자동으로 @Autowired 를 써줌
//    @Autowired
//    public ScoreController(ScoreRepositoryImpl repository) {
//        this.repository = repository;
//    }

    // 1. 성적 등록 화면 띄우기 + 정보 목록 조회
    @GetMapping("/list")
    public String list(Model model) {
        System.out.println("/score/list : GET!");
        List<Score> scoreList = repository.findAll();
        model.addAttribute("sList", scoreList);
        return "chap04/score-list";
    }

    // 2. 성적 정보 등록 처리 요청
    @PostMapping("/register")
    public String register(ScoreRequestDTO dto) {

        // 입력 데이터 (쿼리스트링) 읽기
        System.out.println("/score/register : POST!" + dto);

        // dto(ScoreDTO) 를 entity(Score) 로 변환해야 함.
        Score score = new Score(dto);

        // save 명령
        repository.save(score);

        /*
            등록 요청에서 JSP 뷰 포워딩을 하면
            갱신된 목록을 다시 한번 저장소에서 불러와
            모델에 담는 추가적인 코드가 필요하지만

            리다이렉트를 통해서 위에서 만든 /score/list : GET
            요청을 자동으로 다시 보낼 수 있다면 번거로운 코드가 줄어들 수 있겠다!
         */
        return "redirect:/score/list"; // redirect:요청 URL 작성 (JSP 파일 XXX)
    }

    // 3. 성적 정보 삭제 요청
    @PostMapping("/remove")
    public String remove() {
        System.out.println("/score/remove : POST!");
        return "";
    }
    
    // 4. 성적 정보 상세 조회 요청
    @GetMapping("/detail")
    public String detail() {
        System.out.println("/score/detail : GET!");
        return "";
    }
}
