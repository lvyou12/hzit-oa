package com.hzitoa.web;

import com.hzitoa.service.ITbAuthorityService;
import com.hzitoa.vo.AceTreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Meiyang
 * @since 2017-09-22
 */
@Controller
@RequestMapping("/auth")
public class TbAuthorityController {

    @Autowired
    private ITbAuthorityService iTbAuthorityService;

    @RequestMapping(value = "/getAuth",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,AceTreeVo> getAuth(Integer roleId){
        List<AceTreeVo> list = iTbAuthorityService.getAuthTree(roleId);
        Map<String,AceTreeVo> map = new HashMap<>();
        for (AceTreeVo treeVo : list){
            map.put(treeVo.getName(),treeVo);
        }
        return  map;
    }
}
