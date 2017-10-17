package com.hzitoa.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hzitoa.entity.EmployeeInfo;
import com.baomidou.mybatisplus.service.IService;
import com.hzitoa.vo.BootstrapTable;
import com.hzitoa.vo.EmployeeInfoVo;
import com.hzitoa.vo.LayuiVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Meiyang
 * @since 2017-09-22
 */
public interface IEmployeeInfoService extends IService<EmployeeInfo> {

    /**
     * 登录查询
     * @param map
     * @return
     */
    List<EmployeeInfo> loginSelect(Map<String,Object> map);

    LayuiVo<EmployeeInfoVo> ajaxData(Page<EmployeeInfo> page, Wrapper<EmployeeInfo> wrapper);

    /**
     * 当前用户拥有角色的所有菜单权限资源
     * @param employeeInfo
     * @return
     */
    List<String> getMenusResource(EmployeeInfo employeeInfo);

    /**
     * 当前用户拥有角色的所有按钮权限资源
     * @param employeeInfo
     * @return
     */
    List<String> getButtonsResource(EmployeeInfo employeeInfo);

	
}
