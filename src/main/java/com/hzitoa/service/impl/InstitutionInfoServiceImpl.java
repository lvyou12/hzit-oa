package com.hzitoa.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hzitoa.entity.DepartmentInfo;
import com.hzitoa.entity.InstitutionInfo;
import com.hzitoa.entity.TbDict;
import com.hzitoa.mapper.DepartmentInfoMapper;
import com.hzitoa.mapper.InstitutionInfoMapper;
import com.hzitoa.mapper.TbDictMapper;
import com.hzitoa.service.IInstitutionInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hzitoa.vo.BootstrapTable;
import com.hzitoa.vo.InstitutionInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private TbDictMapper tbDictMapper;

    @Autowired
    private DepartmentInfoMapper departmentInfoMapper;

    @Override
    public BootstrapTable<InstitutionInfoVo> ajaxData(Page<InstitutionInfo> page, Wrapper<InstitutionInfo> wrapper) {
        Page<InstitutionInfo> resultPage = this.selectPage(page,wrapper);
        BootstrapTable<InstitutionInfoVo> bootstrapTable = new BootstrapTable<>();
//        bootstrapTable.setTotal(resultPage.getTotal());//获取总记录数
        List<InstitutionInfo> institutionInfoList = page.getRecords();
        List<InstitutionInfoVo> institutionInfoVoList = getInstitutionInfoVos(institutionInfoList);
        bootstrapTable.setRows(institutionInfoVoList);
        bootstrapTable.setTotal(institutionInfoVoList.size());//获取总记录数
        return bootstrapTable;
    }

    private List<InstitutionInfoVo> getInstitutionInfoVos(List<InstitutionInfo> institutionInfos){
        List<InstitutionInfoVo> institutionInfoVos = new ArrayList<InstitutionInfoVo>();
        InstitutionInfoVo institutionInfoVo = null;
        TbDict tbDict =  null;
        DepartmentInfo departmentInfo = null;
        for(InstitutionInfo info : institutionInfos){
            institutionInfoVo = new InstitutionInfoVo();
            BeanUtils.copyProperties(info, institutionInfoVo);
            //部门
            if(info.getDeptId()!=null){
               departmentInfo = departmentInfoMapper.selectById(info.getDeptId());
                institutionInfoVo.setDeptName(departmentInfo.getDeptName());
            }
            //公司
            if(info.getCompanyId() != null){
                tbDict = tbDictMapper.selectById(info.getCompanyId());
                institutionInfoVo.setCompanyName(tbDict.getDictName());
            }
            institutionInfoVos.add(institutionInfoVo);
        }
        return institutionInfoVos;
    }


}
