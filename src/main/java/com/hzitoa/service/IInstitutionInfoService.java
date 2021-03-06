package com.hzitoa.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hzitoa.entity.InstitutionInfo;
import com.baomidou.mybatisplus.service.IService;
import com.hzitoa.vo.BootstrapTable;
import com.hzitoa.vo.InstitutionInfoVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Meiyang
 * @since 2017-09-22
 */
public interface IInstitutionInfoService extends IService<InstitutionInfo> {

    BootstrapTable<InstitutionInfoVo> ajaxData(Page<InstitutionInfo> page, Wrapper<InstitutionInfo> wrapper);
	
}
