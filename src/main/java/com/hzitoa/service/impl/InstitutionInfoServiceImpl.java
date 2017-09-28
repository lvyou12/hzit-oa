package com.hzitoa.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hzitoa.entity.InstitutionInfo;
import com.hzitoa.mapper.InstitutionInfoMapper;
import com.hzitoa.service.IInstitutionInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hzitoa.vo.BootstrapTable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Meiyang
 * @since 2017-09-22
 */
@Service
public class InstitutionInfoServiceImpl extends ServiceImpl<InstitutionInfoMapper, InstitutionInfo> implements IInstitutionInfoService {

    @Override
    public BootstrapTable<InstitutionInfo> ajaxData(Page<InstitutionInfo> page, Wrapper<InstitutionInfo> wrapper) {
        Page<InstitutionInfo> resultPage = this.selectPage(page,wrapper);
        BootstrapTable<InstitutionInfo> bootstrapTable = new BootstrapTable<>();
        List<InstitutionInfo> institutionInfoList = page.getRecords();
        bootstrapTable.setRows(institutionInfoList);
        bootstrapTable.setTotal(institutionInfoList.size());//获取总记录数
        return bootstrapTable;
    }
}
