package com.hzitoa.mapper;

import com.hzitoa.entity.EmployeeInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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

    List<EmployeeInfo> loginSelect(@Param("map") Map<String,Object> map);

}