package com.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 홈에 들어오면 index.jsp 를 불러오긴
    @GetMapping("/")
    public String home() {
        return "index";
    }
}
