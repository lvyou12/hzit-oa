package com.hzitoa.web;

import com.hzitoa.entity.DepartmentInfo;
import com.hzitoa.entity.EmployeeInfo;
import com.hzitoa.entity.InstitutionInfo;
import com.hzitoa.service.IDepartmentInfoService;
import com.hzitoa.service.IEmployeeInfoService;
import com.hzitoa.service.IInstitutionInfoService;
import com.hzitoa.utils.FileUtils;
import com.hzitoa.vo.StatusVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        String path = "uploadPDF/"+deptName+"/";
        String filePath = request.getSession().getServletContext().getRealPath(path);
        File pdfFile = new File(path+fileName);
        if(!pdfFile.exists()){
            try {
                //上传文件
                FileUtils.uploadFile(file.getBytes(), filePath, fileName);

                //插入institutionInfo数据
                InstitutionInfo institutionInfo = new InstitutionInfo();
                institutionInfo.setDeptId(em.getDeptId());
                institutionInfo.setCompanyId(departmentInfo.getCompanyId());
                institutionInfo.setPath(path);
                institutionInfo.setPath(fileName);
                institutionInfo.setCreateBy(em.getName());
                institutionInfo.setCreateTime(new Date());
                boolean result = iInstitutionInfoService.insert(institutionInfo);
                if(result){
                    statusVO.setCode(200);
                    statusVO.setMsg("文件上传成功");
                }else{
                    statusVO.setCode(200);
                    statusVO.setMsg("数据插入失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
                statusVO.setCode(300);
                statusVO.setMsg("文件上传失败");
            }
        }else{
            statusVO.setCode(300);
            statusVO.setMsg("文件上已存在");
        }
        return statusVO;
    }




}
