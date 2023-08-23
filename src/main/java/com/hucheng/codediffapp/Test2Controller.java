package com.hucheng.codediffapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Test2Controller {

    @GetMapping("test2/hello")
    @ResponseBody
    public String hello(String name) {
        if (name.startsWith("hucheng")) {
            return "hello!!!  hucheng";
        } else {
            return "sorry!!!  hucheng";
        }
    }

}
