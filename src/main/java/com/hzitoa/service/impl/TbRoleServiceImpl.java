package com.hzitoa.service.impl;

import com.hzitoa.entity.TbRole;
import com.hzitoa.mapper.TbRoleMapper;
import com.hzitoa.service.ITbRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hzitoa.vo.StatusVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
public class TbRoleServiceImpl extends ServiceImpl<TbRoleMapper, TbRole> implements ITbRoleService {

    @Autowired
    private TbRoleMapper tbRoleMapper;
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

    @Override
    public void editRole() {

    }
}
