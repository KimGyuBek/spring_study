package com.example.testproject.controller;

import com.example.testproject.dto.MemberDTO;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/get-api") //공통되는 url을 넣어주기위해서 사용.
public class GetController {

    //http://localhost:8181/api/v1/get-api/hello
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String getHello() {
        return "Hello World";
    }

   @GetMapping(value = "/name")
    public String getName() {
        return "땅땅이";
   }

   @GetMapping(value = "/variable2/{variable}")
    public String getVariable1(@PathVariable String variable) {
        return variable;
   }

//   @GetMapping(value = "/variable2/{variable}")
//    public String getVariable2(@PathVariable("variable") String var) {
//        return var;
//   }
//    @GetMapping(value = "/variable2/{variable}")
//    public String getVariable2(@PathVariable("variable") String var) {
//        return var;
//    }
//

    //http://localhost:8181/api/v1/get-api/request1?
    //name=ddangddang&
    //email=ddangddang@naver.com&
    //organization=zzaltoon
    @GetMapping(value = "/request1")
    public String getRequestParam1(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String organization ) {
        return name + " " + email + " " + organization;
    }


    //http://localhost:8181/api/v1/get-api/request2key1=value1&key2=value2
    @GetMapping(value = "/request2")
    public String getRequestParam2(
            @RequestParam Map<String, String> param ) {
        StringBuilder sb = new StringBuilder();

        param.entrySet().forEach(map -> {
            sb.append(map.getKey() + " " + map.getValue() + "\n");
        });

        return sb.toString();
    }


//    //http://localhost:8181/api/v1/get-api/request3?name=ddangddang&email=ddangddang@naver.com&organization=zzaltoon
    @GetMapping(value = "/request3")
    public String getRequest3(MemberDTO memberDTO) {
        return memberDTO.toString();
    }

}
