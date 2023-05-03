package com.spring.mvc.chap05.controller;

import com.spring.mvc.jdbc.Person;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// 수업용 Controller

//@Controller
//@ResponseBody
@RestController // 리턴된 객체를 자동으로 환경에 맞는 JSON 배열로 변환한다.
@RequestMapping("/rests")
public class RestApiController {

    @GetMapping("/hello")
    public String hello() {
        return "안녕하세요!";
    }

    @GetMapping("/foods")
    public List<String> foods() {
//        String[] foodList = {"탕수육", "족발", "마라탕"};
        List<String> foodList = List.of("탕수육", "족발", "마라탕");
        return foodList;
    }

    @GetMapping("/person")
    public Person person() {
        Person p = new Person(1L, "루피", 3);
        return p;
    }
    // {"id":1,"personName":"루피","personAge":3}

    @GetMapping("/person-list")
    public ResponseEntity<?> personList() {
        Person p = new Person(1L, "루피", 3);
        Person p2 = new Person(2L, "딸긔겅듀", 4);
        Person p3 = new Person(3L, "뽀로로", 5);
        List<Person> personList = List.of(p, p2, p3);

        return ResponseEntity.ok().body(personList);
    }

    // 파라미터 값을 보내지 않았을 때,
    // 클라이언트 잘못인데 500 서버 에러라고 뜬다.
    // 이런 경우, 에러를 따로 설정해줘야 한다.
    @GetMapping("/bmi")
    public ResponseEntity<?> bmi(
            @RequestParam(required = false) Double cm,
            @RequestParam(required = false) Double kg) {

        if (cm == null || kg == null) {
            return ResponseEntity.badRequest().body("키랑 몸무게 보내세요"); // 400 에러로 설정
        }

        double bmi = kg / (cm / 100) * (cm / 100);

        HttpHeaders headers = new HttpHeaders();
        headers.add("fruits", "melon");
        headers.add("hobby", "soccer");
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(bmi);
    }
}
