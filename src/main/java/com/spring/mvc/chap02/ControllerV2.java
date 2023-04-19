package com.spring.mvc.chap02;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

// 응답 데이터 처리
@RequestMapping("/model/*")
@Controller
public class ControllerV2 {

    // /model/hobbies   : GET
    // hobies.jsp 파일로 이름 정보와 취미 목록 정보를 보내주고싶을 때
    
    // == 1. Model 객체를 활용
    @GetMapping("/hobbies")
    public String hobbies(Model model) {

        String name = "멍멍이";
        List<String> hobbies = List.of("산책", "밥먹기", "낮잠자기");
        
        // jsp 로 보낼 데이터를 모델에 담는다
        model.addAttribute("n", name);
        model.addAttribute("hList", hobbies);

//        /WEB-INF/views/chap02/hobbies.jsp 에 전달
        return "chap02/hobbies";
    }
    
    
    // == 2. ModelAndView 사용하기
    // /model/hobbies2  :   GET
    // hobbies.jsp 를 포워딩
    // 첫번째가 더 중요하지만, 이 방법도 알아둬야한다. 비동기 서버에 사용하는 경우가 있음.

    // ModelAndView 는 리턴 타입을 ModelAndView 로 설정
    @GetMapping("/hobbies2")
    public ModelAndView hobbies2() {
        
        // jsp 로 보내야 할 데이터
        String name = "짹짹이";
        List<String> hList = List.of("전깃줄에서 졸기", "조잘대기");
        
        // 포워딩할 뷰의 이름
        String viewName = "chap02/hobbies";

        // ModelAndView 객체에 위 데이터 담기
        ModelAndView mv = new ModelAndView();
        mv.setViewName(viewName);
        mv.addObject("n", name);
        mv.addObject("hList", hList);

        return mv;
    }
    
}


