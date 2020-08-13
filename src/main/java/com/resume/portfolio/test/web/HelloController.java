package com.resume.portfolio.test.web;

import com.resume.portfolio.test.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping()
    public String hello1() {

        return "hello";
    }

    @GetMapping("/hello")
    public String hello() {

        return "///hello";
    }
    @GetMapping("/dto")
    public HelloResponseDto helloResponseDto(@RequestParam("name") String name,
                                             @RequestParam("amount") int amount) {

        return new HelloResponseDto(name, amount);
    }
}
