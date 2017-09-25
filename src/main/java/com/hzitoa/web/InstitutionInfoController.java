package com.hzitoa.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Meiyang
 * @since 2017-09-22
 */
@Controller
public class InstitutionInfoController {

    @RequestMapping("/institutionInfo/form-news")
    public String toInstitutionList(){
        return "/institutionInfo/form-news";
    }
}
