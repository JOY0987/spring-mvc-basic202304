package com.spring.mvc.training.controller;

import com.spring.mvc.training.service.TableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/table")
@Slf4j
public class TableController {

    private final TableService tableService;
    @GetMapping("/table")
    public String list(Model model) {
        log.info("/table/list : GET");
//        log.info("page : {}", page);

    }

}
