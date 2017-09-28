package com.hzitoa.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hzitoa.entity.DepartmentInfo;
import com.hzitoa.entity.EmployeeInfo;
import com.hzitoa.entity.InstitutionInfo;
import com.hzitoa.service.IDepartmentInfoService;
import com.hzitoa.service.IEmployeeInfoService;
import com.hzitoa.service.IInstitutionInfoService;
import com.hzitoa.utils.FileUtils;
import com.hzitoa.vo.BootstrapEntity;
import com.hzitoa.vo.BootstrapTable;
import com.hzitoa.vo.StatusVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Meiyang
 * @since 2017-09-22
 */
@Controller
public class InstitutionInfoController {

    @Autowired
    private IInstitutionInfoService iInstitutionInfoService;

    @Autowired
    private IDepartmentInfoService iDepartmentInfoService;

    @RequestMapping("/institutionInfo/institutionList")
    public String toInstitutionList(){
        return "institutionInfo/institutionList";
    }

    @RequestMapping("/institutionInfo/importPage")
    public String toImportPage(){
        return "/institutionInfo/importPage";
    }

    /**
     * 预览所选制度
     */
    @RequestMapping("/institutionInfo/showInstitution")
    public String showDetail(InstitutionInfo institutionInfo,Model model){
        model.addAttribute("institutionInfo",institutionInfo);
        return "/institutionInfo/showInstitution";
    }


    /**
     * 制度上传
     * @param file
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("/institutionInfo/importPDF")
    @ResponseBody
    public StatusVO uploadPDF(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpSession session){
        StatusVO statusVO = new StatusVO();
        EmployeeInfo em = (EmployeeInfo) session.getAttribute("employeeInfo");
        DepartmentInfo departmentInfo = iDepartmentInfoService.selectById(em.getDeptId());
        String deptName  = departmentInfo.getDeptName();

        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
       /* System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);*/
        String path = "/uploadPDF/"+deptName+"/";
        String filePath = request.getSession().getServletContext().getRealPath(path);
        File pdfFile = new File(filePath+fileName);
//        boolean b = pdfFile.exists();
       if(!pdfFile.exists()){
           try {
               //上传文件
               FileUtils.uploadFile(file.getBytes(), filePath, fileName);
               //插入institutionInfo数据
               InstitutionInfo institutionInfo = new InstitutionInfo();
               institutionInfo.setDeptId(em.getDeptId());
               institutionInfo.setCompanyId(departmentInfo.getCompanyId());
               institutionInfo.setPath(path);
               institutionInfo.setName(fileName);
               institutionInfo.setCreateBy(em.getName());
               institutionInfo.setCreateTime(new Date());
               boolean result = iInstitutionInfoService.insertOrUpdate(institutionInfo);
               if(result){
                   statusVO.setCode(200);
                   statusVO.setMsg("文件上传成功");
               }else{
                   statusVO.setCode(300);
                   statusVO.setMsg("数据插入失败");
               }
           } catch (Exception e) {
               e.printStackTrace();
               statusVO.setCode(300);
               statusVO.setMsg("文件上传失败");
           }
       }else {
           statusVO.setCode(400);
           statusVO.setMsg("该文件已存在");
       }
        return statusVO;
    }

    @RequestMapping("/institutionInfo/listData")
    @ResponseBody
    public BootstrapTable<InstitutionInfo> listData(BootstrapEntity bt,HttpSession session){
        if (bt.getOffset() == null || bt.getLimit() == null) {
            bt.setOffset(1);
            bt.setLimit(20);
        } else {
            bt.setOffset(bt.getOffset() / bt.getLimit());
        }
        Page<InstitutionInfo> page = new Page<InstitutionInfo>(bt.getOffset(), bt.getLimit());
        if("-1".equals(bt.getCondition()) ){
            bt.setCondition("");
        }
        EmployeeInfo employeeInfo = (EmployeeInfo) session.getAttribute("employeeInfo");
//        DepartmentInfo departmentInfo = iDepartmentInfoService.selectById(employeeInfo.getDeptId());
        Wrapper<InstitutionInfo> wrapper = null;
        wrapper = new EntityWrapper<InstitutionInfo>()
                .where("dept_id=" + employeeInfo.getDeptId())
                .orderBy("create_time desc");
        BootstrapTable<InstitutionInfo> bootstrapTable = iInstitutionInfoService.ajaxData(page,wrapper);
        return bootstrapTable;
    }
}
