package com.hzitoa.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hzitoa.entity.TbRole;
import com.baomidou.mybatisplus.service.IService;
import com.hzitoa.vo.LayuiVo;
import com.hzitoa.vo.StatusVO;
import com.hzitoa.vo.TbRoleVo;

import javax.servlet.http.HttpSession;

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

    boolean updateRoles(Integer type,HttpSession session,Integer ... roleIds);

    boolean deleteRoles(Integer[] roleIds);

    LayuiVo<TbRoleVo> ajaxData(Page<TbRole> pageRole, Wrapper<TbRole> wrapper);
}
