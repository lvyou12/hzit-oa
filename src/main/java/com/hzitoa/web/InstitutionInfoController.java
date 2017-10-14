package com.hzitoa.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hzitoa.entity.DepartmentInfo;
import com.hzitoa.entity.EmployeeInfo;
import com.hzitoa.entity.InstitutionInfo;
import com.hzitoa.service.IDepartmentInfoService;
import com.hzitoa.service.IInstitutionInfoService;
import com.hzitoa.utils.FileUtils;
import com.hzitoa.vo.BootstrapEntity;
import com.hzitoa.vo.BootstrapTable;
import com.hzitoa.vo.InstitutionInfoVo;
import com.hzitoa.vo.StatusVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Meiyang
 * @since 2017-09-22
 */
@Controller
@RequestMapping("/institutionInfo")
public class InstitutionInfoController {

    private Logger logger = LoggerFactory.getLogger(InstitutionInfoController.class);

    @Autowired
    private IInstitutionInfoService iInstitutionInfoService;

    @Autowired
    private IDepartmentInfoService iDepartmentInfoService;

    @RequestMapping(value="/institutionList")
    public String hello(){
        return "/institutionInfo/institutionList";
    }

    @RequestMapping("/importPage")
    public String toImportPage(){
        return "/institutionInfo/importPage";
    }

    /**
     * 预览所选制度
     */
    @RequestMapping("/showInstitution")
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
    @RequestMapping("/importPDF")
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
                institutionInfo.setInstName(fileName);
                institutionInfo.setCreateBy(em.getUserName());
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
                logger.error("---文件上传失败---");
                statusVO.setCode(300);
                statusVO.setMsg("文件上传失败");
            }
        }else {
            statusVO.setCode(400);
            statusVO.setMsg("该文件已存在");
        }
        return statusVO;
    }

    @RequestMapping(value = "/downLoadPdf",method = RequestMethod.GET)
    @ResponseBody
    public void  downLoadPdf(InstitutionInfo institutionInfo,HttpServletResponse response){
        institutionInfo = iInstitutionInfoService.selectById(institutionInfo.getInstId());
        String fileName = institutionInfo.getInstName();
        try {
            response.setHeader("Content-disposition", "attachment; filename="+
                    new String(fileName.getBytes("utf-8"),"ISO-8859-1"));// 设定输出文件头
        } catch (UnsupportedEncodingException e) {
            logger.error("---编码设置异常---");
        }
        response.setContentType("application/pdf");// 定义输出类型
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(institutionInfo.getPath()+fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            logger.error("---制度文件下载失败---");
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 异步加载数据
     * @param bt
     * @param session
     * @return
     */
    @RequestMapping("/listData")
    @ResponseBody
    public BootstrapTable<InstitutionInfoVo> listData(BootstrapEntity bt,HttpSession session){
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
                .and("isDelete=0")
                .like(bt.getCondition(), bt.getValue())
                .orderBy("create_time desc");
        BootstrapTable<InstitutionInfoVo> bootstrapTable = iInstitutionInfoService.ajaxData(page,wrapper);
        return bootstrapTable;
    }

    /**
     * 逻辑删除数据库中的数据
     * @param institutionInfo
     * @param request
     * @return
     */
    @RequestMapping("/deleteData")
    //@Transactional
    @ResponseBody
    public StatusVO deleteData(InstitutionInfo institutionInfo,HttpServletRequest request){
        StatusVO statusVO = new StatusVO();
        if(institutionInfo == null){
            statusVO.setCode(300);
            statusVO.setMsg("删除失败!请稍后再试!");
            return statusVO;
        }
        institutionInfo = iInstitutionInfoService.selectById(institutionInfo.getInstId());
        institutionInfo.setIsDelete(1);
        boolean result = false;
        try {
            result = iInstitutionInfoService.updateById(institutionInfo);
        }catch (Exception e){

            statusVO.setCode(300);
            statusVO.setMsg("删除失败!请稍后再试!");
            return statusVO;
        }
        if(result){
            String path = request.getSession().getServletContext().getRealPath(institutionInfo.getPath());
            File file = new File(path+institutionInfo.getInstName());
            try{
                file.delete();
                statusVO.setCode(200);
                statusVO.setMsg("成功删除数据!");
            }catch (Exception e){
                logger.error("---本地文件删除失败!---");
            }
        }else{
            statusVO.setCode(300);
            statusVO.setMsg("删除失败!请稍后再试!");
        }
        return statusVO;
    }


}
