package com.example.testproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController //처음에 추가
public class HelloContorller {

//    @RequestMapping(value = "/hello", method = RequestMethod.GET) //고전방식
    @GetMapping("/hello") //method 앞에 선언
    public String hello() {
        return "Hello world";

    }

}
