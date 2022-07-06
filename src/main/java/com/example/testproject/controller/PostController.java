package com.example.testproject.controller;

import com.example.testproject.dto.MemberDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/post-api")
public class PostController {

    @PostMapping(value = "/default")
    public String postMethod() {
        return "Hello World";
    }

    //http://localhost:8181/api/v1/post-api/member
    @PostMapping(value = "/member")
    public String postMember(@RequestBody Map<String, Object> postData) {
        StringBuilder sb = new StringBuilder();

        postData.entrySet().forEach(map -> {
            sb.append(map.getKey() + " " + map.getValue() + "\n");
        });

        return sb.toString();
    }


    //http://localhost:8181/api/v1/post-api/member2
    @PostMapping(value = "/member2")
    public String member2(@RequestBody MemberDTO memberDTO) {
        return memberDTO.toString();
    }

}
