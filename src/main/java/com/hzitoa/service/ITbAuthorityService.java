package com.hzitoa.service;

import com.hzitoa.entity.TbAuthority;
import com.baomidou.mybatisplus.service.IService;
import com.hzitoa.vo.AceTreeVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Meiyang
 * @since 2017-09-22
 */
public interface ITbAuthorityService extends IService<TbAuthority> {

    List<AceTreeVo> getAuthTree(Integer roleId);
}
