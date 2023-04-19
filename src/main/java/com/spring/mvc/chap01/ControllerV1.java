package com.spring.mvc.chap01;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

// 어떤 요청들을 처리할지 공통 URL 을 설계
@RequestMapping("/spring/*")
// 이 클래스의 객체를 스프링이 생성해서 관리하도록 빈을 등록
@Controller // @Component 와 같은 개념
public class ControllerV1 {
    // 세부 요청들은 메서드를 통해 처리
    @RequestMapping("/hello") // http://localhost:8181/spring/hello 요청이 들어올 시 실행
    public String hello() {
        System.out.println("\n======== 헬로 요청이 들어옴! ========\n");
        // 어떤 JSP 를 열어줄지 경로를 작성!
//        return "/WEB-INF/views/hello.jsp";
        return "hello";
    }

    // Q. /spring/food 요청이 오면 food.jsp 를 열어보세요
    @RequestMapping("/food") // 클래스에서 /spring 걸어놨으므로 /food 만 작성
    public String food() { // 메서드 이름은 중요하지 않음!
//        return "/WEB-INF/views/chap01/food.jsp";
        return "chap01/food";
    }

    // ================ 요청 파라미터 읽기 (Query String parameter) ===================
    // == 1. HttpServletRequest 사용하기
    // ==> ex ) /spring/person?name=kim&age=30

    @RequestMapping("/person")
    public String person(HttpServletRequest request) {
        String name = request.getParameter("name");
        String age = request.getParameter("age");

        System.out.println("name = " + name);
        System.out.println("age = " + age);

        return "";
    }
    
    
    // == 2. @RequestParam 사용하기
    // ==> ex ) /spring/major?stu=kim&major=business&grade=3
    // 방법 1보다 편리하다~
    @RequestMapping("/major")
    public String major(
            String stu, // 1. 쿼리 스트링의 이름과 똑같고 2. 디폴트값 설정 필요 없다면 @RequestParam 생략 가능
            @RequestParam("major") String mj, // + 지역변수와 변수명이 겹치는 경우 어떤 쿼리스트링명으로 받는지 () 안에 작성
            @RequestParam(defaultValue = "1") int grade //  + grade 값을 안보낼 수도 있는 경우 defaultValue 설정해두기
    ) {

        String major = "전공";

        System.out.println("stu = " + stu);
        System.out.println("major = " + mj);
        System.out.println("grade = " + grade);
        return "";
    }


    // == 3. 커맨드 객체 이용하기
    // == 쿼리 스트링의 양이 너무 많을 경우 또는 연관성이 있을 경우
    // ==> ex ) /spring/order?oNum=20230419007-P&goods=구두&amount=3&price=50000...
    @RequestMapping("/order")
    public String order(@ModelAttribute OrderRequestDTO dto) {
        System.out.println("dto = " + dto);
        return "";
    }


    // == 4. URL 에 경로로 붙어있는 데이터 읽기
    // ==> /spring/member/hong/107
    //     hong 이라는 유저의 107번 게시글을 읽고싶음!
    @RequestMapping("/member/{userName}/{bNo}")
    public String member(
            @PathVariable String userName // / 뒤에 있는 데이터에는 @PathVariable 필수!
            , @PathVariable long bNo
    ) {
        System.out.println("userName = " + userName);
        System.out.println("bNo = " + bNo);
        return "";
    }


    // 음식 선택 요청 처리
    // + POST 요청이 아니라면 받지 않도록 설정 => value, method 설정하기
//    @RequestMapping(value = "/food-select", method = RequestMethod.POST)
    @PostMapping("/food-select") // 위와 같은 기능!
    public String foodSelect(
            String foodName
            , String category
    ) {
        System.out.println("foodName = " + foodName);
        System.out.println("category = " + category);
        return "";
    }
    // GET 으로 보낼 경우 페이지 내 405 + .HttpRequestMethodNotSupportedException: Request method 'GET' not supported] 에러



}
