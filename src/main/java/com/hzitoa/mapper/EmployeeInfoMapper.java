package com.hzitoa.mapper;

import com.hzitoa.entity.EmployeeInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Meiyang
 * @since 2017-09-22
 */
public interface EmployeeInfoMapper extends BaseMapper<EmployeeInfo> {

    List<EmployeeInfo> selectAll();

}