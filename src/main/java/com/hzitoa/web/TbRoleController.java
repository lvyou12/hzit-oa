package com.hzitoa.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hzitoa.entity.TbRole;
import com.hzitoa.service.ITbRoleService;
import com.hzitoa.service.impl.TbRoleServiceImpl;
import com.hzitoa.vo.StatusVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
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
@RequestMapping("/role")
public class TbRoleController {
    @Autowired
    private ITbRoleService iTbRoleService;

    @RequestMapping(value = "/roleList")
    public String toTbRoleList(){
        return "role/roleList";
    }

    @RequestMapping(value = "/addRole",method = RequestMethod.GET)
    public String toAddRole(){
        return "role/addRole";
    }

    @RequestMapping(value = "/checkRole",method = RequestMethod.POST)
    @ResponseBody
    public StatusVO checkRole(String roleName){
        System.out.println(roleName);
        StatusVO statusVO = new StatusVO();
        Map<String,Object> map = new HashMap<>();
        map.put("role_name",roleName);
        List<TbRole> list = iTbRoleService.selectByMap(map);
        System.out.println(list);
        if(list.size() != 0){
            statusVO.setCode(300);
            statusVO.setMsg("角色已存在!");
            return  statusVO;
        }
        statusVO.setCode(200);
        statusVO.setMsg("角色可以录入!");
        return  statusVO;
    }

    @RequestMapping(value = "/addRole",method = RequestMethod.POST)
    @ResponseBody
    public StatusVO toAddRole(TbRole role){
        StatusVO statusVO = new StatusVO();
        role.setCreateTime(new Date());
        boolean result = iTbRoleService.insert(role);
        if(result){
            statusVO.setCode(200);
            statusVO.setMsg("角色添加成功!");
        }else {
            statusVO.setCode(300);
            statusVO.setMsg("角色添加失败,请稍后再试!");
        }
        return statusVO;
    }
}
