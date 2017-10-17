package com.hzitoa.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hzitoa.entity.*;
import com.hzitoa.mapper.*;
import com.hzitoa.service.IEmployeeInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hzitoa.utils.DateUtils;
import com.hzitoa.vo.BootstrapTable;
import com.hzitoa.vo.EmployeeInfoVo;
import com.hzitoa.vo.LayuiVo;
import com.hzitoa.vo.MenusVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Meiyang
 * @since 2017-09-22
 */
@Service
public class EmployeeInfoServiceImpl extends ServiceImpl<EmployeeInfoMapper, EmployeeInfo> implements IEmployeeInfoService {

    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;

    @Autowired
    private TbDictMapper tbDictMapper;

    @Autowired
    private DepartmentInfoMapper departmentInfoMapper;

    @Autowired
    private TbRoleMapper tbRoleMapper;

    @Autowired
    private TbAuthorityMapper tbAuthorityMapper;

    /**
     * 登录查询
     * @param map
     * @return
     */
    @Override
    public List<EmployeeInfo> loginSelect(Map<String, Object> map) {
        return employeeInfoMapper.loginSelect(map);
    }

    @Override
    public LayuiVo<EmployeeInfoVo> ajaxData(Page<EmployeeInfo> page, Wrapper<EmployeeInfo> wrapper) {
        Page<EmployeeInfo> resultPage = this.selectPage(page, wrapper);
        LayuiVo<EmployeeInfoVo> layuiVo = new LayuiVo<>();
        //        bootstrapTable.setTotal(resultPage.getTotal());//获取总记录数
       List<EmployeeInfo> employeeInfoList = page.getRecords();
        List<EmployeeInfoVo> employeeInfoVoList = getEmployeeInfoVos(employeeInfoList);
        layuiVo.setCode(0);
        layuiVo.setMsg("");
        layuiVo.setCount(employeeInfoVoList.size());//获取总记录数
        layuiVo.setData(employeeInfoVoList);
        return layuiVo;
    }

    private List<EmployeeInfoVo> getEmployeeInfoVos(List<EmployeeInfo> employeeInfoList){
        List<EmployeeInfoVo> employeeInfoVoList = new ArrayList<>();
        EmployeeInfoVo employeeInfoVo = null;
        TbDict tbDict =  null;
        DepartmentInfo departmentInfo = null;
        for(EmployeeInfo info : employeeInfoList){
            employeeInfoVo = new EmployeeInfoVo();
            BeanUtils.copyProperties(info,employeeInfoVo);
            //部门
            if(info.getDeptId()!=null){
                departmentInfo = departmentInfoMapper.selectById(info.getDeptId());
                employeeInfoVo.setDeptName(departmentInfo.getDeptName());
            }
            //公司
            if(info.getCompanyId() != null){
                tbDict = tbDictMapper.selectById(info.getCompanyId());
                employeeInfoVo.setCompanyName(tbDict.getDictName());
            }
            //创建时间
            if(info.getCreateTime() != null){
               employeeInfoVo.setCreateTime(DateUtils.format(info.getCreateTime(),DateUtils.FORMAT_LONG));
            }
            //更新时间
            if(info.getUpdateTime() != null){
                employeeInfoVo.setUpdateTime(DateUtils.format(info.getUpdateTime(),DateUtils.FORMAT_LONG));
            }
            employeeInfoVoList.add(employeeInfoVo);
        }
        return  employeeInfoVoList;
    }

    /**
     * 当前用户拥有角色的所有菜单权限资源id
     * @param employeeInfo
     * @return
     */
    @Override
    public List<String> getMenusResource(EmployeeInfo employeeInfo) {
        List<String> allResource = getEmployeeResource(employeeInfo);
        List<String> menusResource = new ArrayList<>();
        for(String str : allResource){
            TbAuthority authority = new TbAuthority();
            authority.setAuthId(Integer.valueOf(str));
            authority = tbAuthorityMapper.selectOne(authority);
            if(authority.getIsMenu() == 1){
                menusResource.add(str);
            }
        }
        return menusResource;
    }

    /**
     * 当前用户拥有角色的所有按钮权限资源permission
     * @param employeeInfo
     * @return
     */
    @Override
    public List<String> getButtonsResource(EmployeeInfo employeeInfo) {
        List<String> allResource = getEmployeeResource(employeeInfo);
        List<String> buttonsResource = new ArrayList<>();
        for(String str : allResource){
            TbAuthority authority = new TbAuthority();
            authority.setAuthId(Integer.valueOf(str));
            authority = tbAuthorityMapper.selectOne(authority);
            if(authority.getIsMenu() == 0){
                buttonsResource.add(authority.getPermission());
            }
        }
        return buttonsResource;
    }

    /**
     * 当前用户拥有角色的所有权限资源id
     * @param employeeInfo
     * @return
     */
    private List<String> getEmployeeResource(EmployeeInfo employeeInfo) {
        String names = employeeInfo.getRoleName();
        List<String> roleNamesList = new ArrayList<>();
        if(names.contains(",")){
            String[] role_names = names.split(",");
            roleNamesList = Arrays.asList(role_names);
        }else {
            roleNamesList.add(names);
        }
        List<String> finalAutnIds = new ArrayList<>();//去重后最终的authid
        for(String roleName : roleNamesList){
            TbRole role = new TbRole();
            role.setRoleName(roleName);
            role = tbRoleMapper.selectOne(role);
            String ids = role.getResourceIds();
            List<String> authIdsList = new ArrayList<>();//当前角色的所有权限authid
            if(ids != null){
                if(ids.contains(",")){
                    String[] auth_ids = role.getResourceIds().split(",");
                    authIdsList = Arrays.asList(auth_ids);
                }else{
                    authIdsList.add(ids);
                }
            }else{
                continue;
            }
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
        return finalAutnIds;
    }
}
