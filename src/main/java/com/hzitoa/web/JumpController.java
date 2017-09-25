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

<<<<<<< HEAD
    @RequestMapping("/login")
    public String toLogin(){ return "login"; }

=======
>>>>>>> 9ed659677adde981d88851ac85246788c3786c78

}
