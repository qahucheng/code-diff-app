package com.hucheng.codediffapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @GetMapping("test1/hello1")
    @ResponseBody
    public String hello1(String name) {
        return "hello1 : " + name;
    }
}
