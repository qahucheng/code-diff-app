package com.hucheng.codediffapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Test2Controller {

    @GetMapping("test2/hello1")
    @ResponseBody
    public String hello(String name) {
        if (name.startsWith("hucheng1995")) {
            return "hello!!!  hucheng1995";
        } else {
            return "sorry!!!  hucheng1995";
        }
    }

    @GetMapping("test2/hello2")
    @ResponseBody
    public String hello2(String name) {
        if (name.startsWith("chenghu")){
            return "thanks!!!  hucheng";
        }
        if (name.startsWith("hucheng1995")) {
            return "hello!!!  hucheng1995";
        } 
        return "default!!!  hucheng1995";
        
    }

        @GetMapping("test2/hello3")
    @ResponseBody
    public String hello3(String name) {
        if (name.startsWith("chenghu")){
            return "thanks1.1!!!  hucheng";
        }
        if (name.startsWith("hucheng1995")) {
            return "hello!!!  hucheng1995";
        } 
        return "default1.1!!!  hucheng1995";
        
    }


}
