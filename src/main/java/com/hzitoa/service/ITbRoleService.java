package com.hzitoa.service;

import com.hzitoa.entity.TbRole;
import com.baomidou.mybatisplus.service.IService;
import com.hzitoa.vo.StatusVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Meiyang
 * @since 2017-09-22
 */
public interface ITbRoleService extends IService<TbRole> {

    boolean insertOne(TbRole role);

    void editRole();
}
