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

    @RequestMapping("/index")
    public String toIndex(){
        return "index";
    }

    @RequestMapping("/login")
    public String toLogin(){ return "login"; }

    @RequestMapping("/employeeInfo/login")
    public String toLogin2(){ return "login"; }



}
