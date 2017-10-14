package com.hzitoa.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hzitoa.entity.TbRole;
import com.hzitoa.service.ITbRoleService;
import com.hzitoa.service.impl.TbRoleServiceImpl;
import com.hzitoa.vo.LayuiVo;
import com.hzitoa.vo.StatusVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    /**
     * 角色信息添加
     * @param role
     * @return
     */
    @RequestMapping(value = "/addRole",method = RequestMethod.POST)
    @ResponseBody
    public StatusVO toAddRole(TbRole role){
        StatusVO statusVO = new StatusVO();
        role.setCreateTime(new Date());
        boolean result = iTbRoleService.insertOne(role);
        if(result){
            result = iTbRoleService.insert(role);
            if(result){
                statusVO.setCode(200);
                statusVO.setMsg("角色添加成功!");
            }else {
                statusVO.setCode(300);
                statusVO.setMsg("角色添加失败,请稍后再试!");
            }
        }else {
            statusVO.setCode(500);
            statusVO.setMsg("角色已存在!");
        }
        return statusVO;
    }

    /**
     * 页面请求分页数据
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/roleAjaxData")
    @ResponseBody
    public LayuiVo<TbRole> roleList(int page,int limit){
        LayuiVo<TbRole> layuiVo = new LayuiVo<>();
        Page<TbRole> pageRole = new Page<>(page,limit,"createBy");
        pageRole.setAsc(false);
        pageRole = iTbRoleService.selectPage(pageRole);
        layuiVo.setData(pageRole.getRecords());
        layuiVo.setCode(0);
        layuiVo.setMsg("");
        layuiVo.setCount(iTbRoleService.selectCount(new EntityWrapper<TbRole>()));
        return layuiVo;
    }

    @RequestMapping(value = "/editRole",method = RequestMethod.GET)
    public String roleEdit(TbRole role,Model model){
        role = iTbRoleService.selectById(role.getRoleId());
        model.addAttribute("role",role);
        return "/role/editRole";
    }

    @RequestMapping(value = "/editRole",method = RequestMethod.POST)
    @ResponseBody
    public StatusVO editRole(TbRole role){
        System.out.println(role);
        iTbRoleService.editRole();
        TbRole tbRole = iTbRoleService.selectById(role.getRoleId());
        TbRole role1 = iTbRoleService.selectOne(new EntityWrapper<TbRole>().where("role_name="+role.getRoleName()));
        if(role1 == null){

        }
        return null;
    }
}
