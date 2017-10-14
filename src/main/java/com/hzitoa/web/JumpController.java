package com.hzitoa.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hzitoa.entity.EmployeeInfo;
import com.hzitoa.entity.TbAuthority;
import com.hzitoa.entity.TbRole;
import com.hzitoa.service.IEmployeeInfoService;
import com.hzitoa.service.ITbAuthorityService;
import com.hzitoa.service.ITbDictService;
import com.hzitoa.service.ITbRoleService;
import com.hzitoa.vo.MenusVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 吕游
 * @company 合众艾特
 * @create 2017-09-22 10:04
 * @description
 */
@Controller
public class JumpController {

    @Autowired
    private ITbRoleService iTbRoleService;

    @Autowired
    private ITbAuthorityService iTbAuthorityService;

    @Autowired
    private IEmployeeInfoService iEmployeeInfoService;

    @RequestMapping("/index")
    public String toIndex(HttpSession session,Model model){
        EmployeeInfo employeeInfo = (EmployeeInfo) session.getAttribute("employeeInfo");
//        EmployeeInfo employeeInfo = iEmployeeInfoService.selectById(2);
        List<MenusVo> menusVoList = new ArrayList<>();
        String[] role_names = employeeInfo.getRoleName().split(",");
        List<String> roleNamesList = Arrays.asList(role_names);
        List<String> finalAutnIds = new ArrayList<>();//去重后最终的authid
        for(String roleName : roleNamesList){
            TbRole role = iTbRoleService.selectOne(new EntityWrapper<TbRole>()
                    .where("role_name='" + roleName + "'"));
            String[] auth_ids = role.getResourceIds().split(",");
            List<String> authIdsList = Arrays.asList(auth_ids);//当前角色的所有权限authid
            for(String id : authIdsList){
                if(finalAutnIds.size() != 0){
                    //authid去重
                    for(String str : finalAutnIds){
                        if(!str.equals(id)){
                            finalAutnIds.add(id);
                            break;
                        }
                    }
                }else{
                    finalAutnIds.add(id);
                }
            }
        }
        for(String finalId : finalAutnIds){
            TbAuthority authority = new TbAuthority();
            MenusVo menusVo = new MenusVo();
            authority = iTbAuthorityService.selectOne(new EntityWrapper<TbAuthority>()
                    .where("auth_id=" + Integer.valueOf(finalId))
                    .and("isMenu = 1"));
            if(authority.getPid() == null){
                BeanUtils.copyProperties(authority, menusVo);
                List<TbAuthority> subAuthorityList = iTbAuthorityService.selectList(new EntityWrapper<TbAuthority>()
                        .where("pid=" + authority.getAuthId()));
                menusVo.setSubAuthorityList(subAuthorityList);
                menusVoList.add(menusVo);
            }
        }
        model.addAttribute("menuList",menusVoList);
        return "index";
    }

    @RequestMapping("/home")
    public String toHome(){
        return "home";
    }

    @RequestMapping("/login")
    public String toLogin(){ return "login"; }

    @RequestMapping("/employeeInfo/login")
    public String toLogin2(){ return "login"; }

}
