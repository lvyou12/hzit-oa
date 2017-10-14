package com.hzitoa.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hzitoa.entity.EmployeeInfo;
import com.hzitoa.entity.TbRole;
import com.hzitoa.mapper.TbRoleMapper;
import com.hzitoa.service.ITbRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hzitoa.utils.DateUtils;
import com.hzitoa.vo.LayuiVo;
import com.hzitoa.vo.TbRoleVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Meiyang
 * @since 2017-09-22
 */
@Service
@Transactional
public class TbRoleServiceImpl extends ServiceImpl<TbRoleMapper, TbRole> implements ITbRoleService {

    @Autowired
    private TbRoleMapper tbRoleMapper;

    @Override
    public LayuiVo<TbRoleVo> ajaxData(Page<TbRole> pageRole, Wrapper<TbRole> wrapper) {
        LayuiVo<TbRoleVo> layuiVo = new LayuiVo<>();
        List<TbRoleVo> list = null;
        List<TbRole> roleList = tbRoleMapper.selectPage(new RowBounds(pageRole.getOffset(),pageRole.getLimit()),wrapper);
        if(roleList.size() != 0){
            list = modifyTbRole(roleList);
        }
        layuiVo.setData(list);
        layuiVo.setCode(0);
        layuiVo.setMsg("");
        layuiVo.setCount(tbRoleMapper.selectCount(new EntityWrapper<TbRole>()));
        return layuiVo;
    }

    private List<TbRoleVo> modifyTbRole(List<TbRole> roleList){
        List<TbRoleVo> voList = new ArrayList<>();
        for(TbRole role : roleList){
            TbRoleVo vo = new TbRoleVo();
            BeanUtils.copyProperties(role,vo);
            if(role.getCreateTime() != null){
                vo.setCreateTime(DateUtils.format(role.getCreateTime(),DateUtils.FORMAT_LONG));
            }
            if(role.getUpdateTime() != null){
                vo.setUpdateTime(DateUtils.format(role.getUpdateTime(),DateUtils.FORMAT_LONG));
            }
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public boolean insertOne(TbRole role) {
        Map<String,Object> map = new HashMap<>();
        map.put("role_name",role.getRoleName());
        List<TbRole> list = tbRoleMapper.selectByMap(map);
        if(list.size() != 0){
            return false;
        }
        return true;
    }

    /**
     * 批量禁用角色
     * @param roleIds
     * @return
     */
    @Override
    public boolean updateRoles(Integer type,HttpSession session,Integer ... roleIds) {
        Integer result = 0;
        EmployeeInfo e = (EmployeeInfo) session.getAttribute("employeeInfo");
        TbRole role = new TbRole();
        role.setAvailable(type);
        role.setUpdateBy(e.getUserName());
        role.setUpdateTime(new Date());
        for (Integer roleId : roleIds){
            result = tbRoleMapper.update(role,new EntityWrapper<TbRole>().where("role_id="+roleId));
        }
        if(result == 1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteRoles(Integer[] roleIds) {
        Integer result = 0;
        for (Integer roleId : roleIds){
            result = tbRoleMapper.deleteById(roleId);
        }
        if(result == 1){
            return true;
        }else {
            return false;
        }
    }
}
