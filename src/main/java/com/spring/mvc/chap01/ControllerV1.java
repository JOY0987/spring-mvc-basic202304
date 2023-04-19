package com.spring.mvc.chap01;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

// 어떤 요청들을 처리할지 공통 URL 을 설계
@RequestMapping("/spring/*")
// 이 클래스의 객체를 스프링이 생성해서 관리하도록 빈을 등록
@Controller // @Component 와 같은 개념
public class ControllerV1 {
    
    // 세부 요청들은 메서드를 통해 처리
    @RequestMapping("/hello") // http://localhost:8181/spring/hello
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
            String stu, // 1. 파라미터의 이름과 똑같고 2. 디폴트값 설정 필요 없다면 @RequestParam 생략 가능
            @RequestParam("major") String mj, // + 지역변수와 변수명이 겹치는 경우 어떤 파라미터로 받는지 () 안에 작성
            @RequestParam(defaultValue = "1") int grade //  + grade 값을 안보낼 수도 있는 경우 defaultValue 설정해두기
    ) {

        String major = "전공";

        System.out.println("stu = " + stu);
        System.out.println("major = " + mj);
        System.out.println("grade = " + grade);
        return "";
    }


    // == 3. 
}
