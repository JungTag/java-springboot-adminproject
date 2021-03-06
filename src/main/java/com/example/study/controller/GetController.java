package com.example.study.controller;

import com.example.study.model.SearchParam;
import com.example.study.model.network.Header;
import jdk.javadoc.internal.doclets.formats.html.markup.Head;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // localhost:8080/api
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path="/getMethod") // localhost:8080/api/getMethod
    public String getRequest() {
        return "Hi getMethod";
    }

    @GetMapping("/getParameter") // localhost:8080/api/getParameter?id=1234&password=abcd
    public String getParameter(@RequestParam String id, @RequestParam(name = "password") String pwd) {
        System.out.println("id : " + id);
        System.out.println("pwd : " + pwd);

        return id + pwd;
    }

    // localhost:8080/api/getMultiParameter?account=abcd&email=study@gmail.com&page=1
    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam) {
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        return searchParam; // jackson 라이브러리를 통해 JSON 형태로 반환
    }

    @GetMapping("/header")
    public Header getHeader() {
        // {"resultCode: "OK", "description" : "OK"}
        return Header.builder().resultCode("OK").description("OK").build();
    }
}
