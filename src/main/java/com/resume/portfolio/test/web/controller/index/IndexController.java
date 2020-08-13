package com.resume.portfolio.test.web.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {

        return "index";
    }

    @GetMapping("/board/save")
    public String boardSave() {

        return "/board/board-save";
    }
}
