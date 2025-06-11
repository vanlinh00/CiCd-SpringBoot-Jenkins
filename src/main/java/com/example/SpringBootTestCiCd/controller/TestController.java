package com.example.SpringBootTestCiCd.controller;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/home")
public class TestController {

    @GetMapping("/test")
    public ResponseEntity<?> register() {

        return ResponseEntity.ok( " tôi biểu diễn CI CD này - Chỉ cần push code lên tự deploy lại app");
    }

}
