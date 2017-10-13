package com.hzitoa.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hzitoa.entity.DepartmentInfo;
import com.hzitoa.entity.EmployeeInfo;
import com.hzitoa.entity.TbDict;
import com.hzitoa.mapper.DepartmentInfoMapper;
import com.hzitoa.mapper.EmployeeInfoMapper;
import com.hzitoa.mapper.TbDictMapper;
import com.hzitoa.service.IEmployeeInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hzitoa.vo.BootstrapTable;
import com.hzitoa.vo.EmployeeInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private TbDictMapper tbDictMapper;

    @Autowired
    private DepartmentInfoMapper departmentInfoMapper;

    /**
     * 登录查询
     * @param map
     * @return
     */
    @Override
    public List<EmployeeInfo> loginSelect(Map<String, Object> map) {
        return employeeInfoMapper.loginSelect(map);
    }

    @Override
    public BootstrapTable<EmployeeInfoVo> ajaxData(Page<EmployeeInfo> page, Wrapper<EmployeeInfo> wrapper) {
        Page<EmployeeInfo> resultPage = this.selectPage(page, wrapper);
        BootstrapTable<EmployeeInfoVo> bootstrapTable = new BootstrapTable<>();
        //        bootstrapTable.setTotal(resultPage.getTotal());//获取总记录数
       List<EmployeeInfo> employeeInfoList = page.getRecords();
        List<EmployeeInfoVo> employeeInfoVoList = getEmployeeInfoVos(employeeInfoList);
        bootstrapTable.setRows(employeeInfoVoList);
        bootstrapTable.setTotal(employeeInfoVoList.size());//获取总记录数
        return bootstrapTable;
    }

    private List<EmployeeInfoVo> getEmployeeInfoVos(List<EmployeeInfo> employeeInfoList){
        List<EmployeeInfoVo> employeeInfoVoList = new ArrayList<>();
        EmployeeInfoVo employeeInfoVo = null;
        TbDict tbDict =  null;
        DepartmentInfo departmentInfo = null;
        for(EmployeeInfo info : employeeInfoList){
            employeeInfoVo = new EmployeeInfoVo();
            BeanUtils.copyProperties(info,employeeInfoVo);
            //部门
            if(info.getDeptId()!=null){
                departmentInfo = departmentInfoMapper.selectById(info.getDeptId());
                employeeInfoVo.setDeptName(departmentInfo.getDeptName());
            }
            //公司
            if(info.getCompanyId() != null){
                tbDict = tbDictMapper.selectById(info.getCompanyId());
                employeeInfoVo.setCompanyName(tbDict.getDictName());
            }
            employeeInfoVoList.add(employeeInfoVo);
        }
        return  employeeInfoVoList;
    }
}
