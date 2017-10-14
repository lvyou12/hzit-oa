package com.hzitoa.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hzitoa.entity.TbRole;
import com.hzitoa.service.ITbRoleService;
import com.hzitoa.vo.LayuiEntity;
import com.hzitoa.vo.LayuiVo;
import com.hzitoa.vo.StatusVO;
import com.hzitoa.vo.TbRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

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
     * 数据分页展示
     * @param layuiEntity
     * @return
     */
    @RequestMapping(value = "/roleAjaxData")
    @ResponseBody
    public LayuiVo<TbRoleVo> roleList(LayuiEntity layuiEntity){
        LayuiVo<TbRoleVo> layuiVo = new LayuiVo<>();
        Page<TbRole> pageRole = new Page<>(layuiEntity.getPage(),layuiEntity.getLimit(),"createBy");
        pageRole.setAsc(false);
        Wrapper<TbRole> wrapper = new EntityWrapper<>();
        layuiVo = iTbRoleService.ajaxData(pageRole,wrapper);
        return layuiVo;
    }

    @RequestMapping(value = "/editRole",method = RequestMethod.GET)
    public String roleEdit(TbRole role,Model model){
        role = iTbRoleService.selectById(role.getRoleId());
        model.addAttribute("role",role);
        return "/role/editRole";
    }

    /**
     * 修改角色信息
     * @param role
     * @return
     */
    @RequestMapping(value = "/editRole",method = RequestMethod.POST)
    @ResponseBody
    public StatusVO editRole(TbRole role){
        StatusVO statusVO = new StatusVO();
        TbRole tbRole = iTbRoleService.selectById(role.getRoleId());
        tbRole.setRoleName(role.getRoleName());
        tbRole.setAvailable(role.getAvailable());
        tbRole.setUpdateBy(role.getUpdateBy());
        tbRole.setUpdateTime(new Date());
        boolean result = iTbRoleService.updateById(tbRole);
        if(result){
            statusVO.setCode(200);
            statusVO.setMsg("修改成功!");
        }else {
            statusVO.setCode(300);
            statusVO.setMsg("修改失败!");
        }
        return statusVO;
    }

    /**
     * 禁用角色
     * @param roleIds
     * @return
     */
    @RequestMapping(value = "/disableRole",method = RequestMethod.GET)
    @ResponseBody
    public StatusVO disableRole(Integer type,HttpSession session,Integer ... roleIds){
        boolean result = false;
        StatusVO statusVO = new StatusVO();
        result = iTbRoleService.updateRoles(type,session,roleIds);
        if(result){
            statusVO.setCode(200);
            statusVO.setMsg("操作成功!");
        }else {
            statusVO.setCode(300);
            statusVO.setMsg("操作失败,请稍后再试!");
        }
        return statusVO;
    }

    @RequestMapping(value = "/deleteRole",method = RequestMethod.GET)
    @ResponseBody
    public StatusVO deleteRole(Integer ... roleIds){
        boolean result = false;
        StatusVO statusVO = new StatusVO();
        result = iTbRoleService.deleteRoles(roleIds);
        if(result){
            statusVO.setCode(200);
            statusVO.setMsg("删除成功!");
        }else {
            statusVO.setCode(300);
            statusVO.setMsg("删除失败,请稍后再试!");
        }
        return statusVO;
    }
}
