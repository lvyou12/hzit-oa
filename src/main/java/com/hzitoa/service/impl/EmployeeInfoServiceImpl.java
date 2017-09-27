package com.hzitoa.service.impl;

import com.hzitoa.entity.EmployeeInfo;
import com.hzitoa.mapper.EmployeeInfoMapper;
import com.hzitoa.service.IEmployeeInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * 登录查询
     * @param map
     * @return
     */
    @Override
    public List<EmployeeInfo> loginSelect(Map<String, Object> map) {
        return employeeInfoMapper.loginSelect(map);
    }
}
