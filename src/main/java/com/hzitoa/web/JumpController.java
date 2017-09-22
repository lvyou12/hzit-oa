package com.hzitoa.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 吕游
 * @company 合众艾特
 * @create 2017-09-22 10:04
 * @description
 */
@Controller
public class JumpController {

    @RequestMapping("/sign-in")
    public String toLogin(){
        return "sign-in";
    }

    @RequestMapping("/index")
    public String toIndex(){
        return "index";
    }
}
