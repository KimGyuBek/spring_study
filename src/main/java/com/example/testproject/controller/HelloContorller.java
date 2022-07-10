package com.example.testproject.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController //처음에 추가
public class HelloContorller {

    private final static Logger LOGGER = Logger.getGlobal();

//    @RequestMapping(value = "/hello", method = RequestMethod.GET) //고전방식
   @PostMapping(value = "exception")
   public void exceptionTest() throws Exception {
       throw new Exception();
   }

//   @ExceptionHandler(value = Exception.class)
//   public ResponseEntity<Map<String, String>> ExceptionHandler(Exception e) {
//       HttpHeaders responseHeaders = new HttpHeaders();
//
//       HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
//
//       LOGGER.info("Controller 내 ExceptionHandler 호출");
//
//       Map<String, String> map = new HashMap<>();
//       map.put("error type", httpStatus.getReasonPhrase());
//       map.put("code", "400");
//       map.put("message", "error occured!");
//
//       return new ResponseEntity<>(map, responseHeaders, httpStatus);
//
//
//   }
    @GetMapping("/hello") //method 앞에 선언
    public String hello() {
        return "Hello world";

    }

}
