package com.hzitoa.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hzitoa.email.EmailUtil;
import com.hzitoa.entity.DepartmentInfo;
import com.hzitoa.entity.EmployeeInfo;
import com.hzitoa.entity.TbDict;
import com.hzitoa.entity.TbRole;
import com.hzitoa.mapper.EmployeeInfoMapper;
import com.hzitoa.service.IDepartmentInfoService;
import com.hzitoa.service.IEmployeeInfoService;
import com.hzitoa.service.ITbDictService;
import com.hzitoa.service.ITbRoleService;
import com.hzitoa.utils.Md5Util;
import com.hzitoa.vo.BootstrapEntity;
import com.hzitoa.vo.BootstrapTable;
import com.hzitoa.vo.EmployeeInfoVo;
import com.hzitoa.vo.StatusVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Meiyang
 * @since 2017-09-22
 */
@Controller
public class EmployeeInfoController {
    private Logger logger = LoggerFactory.getLogger(EmployeeInfoController.class);
    @Autowired
    private IEmployeeInfoService iEmployeeInfoService;

    @Autowired
    private ITbDictService iTbDictService;

    @Autowired
    private IDepartmentInfoService iDepartmentInfoService;

    @Autowired
    private ITbRoleService iTbRoleService;

    @RequestMapping("/employeeInfo/employeeList")
    public String toEmployeeList(){
        return "/employeeInfo/employeeList";
    }


    @RequestMapping(value = "/employeeInfo/login",method= RequestMethod.POST)
    @ResponseBody
    public StatusVO login(EmployeeInfo employeeInfo, HttpServletRequest request){
        StatusVO statusVO = new StatusVO();
        try{
//            Subject subject = SecurityUtils.getSubject();//从SecurityUtils中获取主体对象
//            UsernamePasswordToken token = new UsernamePasswordToken(employeeInfo.getUserName(), employeeInfo.getPassword());
//            subject.login(token);
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("userName",employeeInfo.getUserName());
            paramMap.put("email",employeeInfo.getEmail());
//            paramMap.put("password",employeeInfo.getPassword());
//            paramMap.put("isLocked","0");

            List<EmployeeInfo> employeeInfoList = iEmployeeInfoService.loginSelect(paramMap);

            HttpSession httpSession = request.getSession();
            if(employeeInfoList!=null && employeeInfoList.size() >0){
                employeeInfo = employeeInfoList.get(0);
                employeeInfo.setPassword("");
            }
            httpSession.setAttribute("employeeInfo",employeeInfo);
            statusVO.setCode(200);
            statusVO.setMsg("登录成功");
        }catch (
                Exception e){
//                AuthenticationException e){
            logger.error("------------用户登录出错----------------"+e.getMessage());
            statusVO.setCode(300);
            statusVO.setMsg("登录失败");
        }
        return statusVO;
    }

    /**
     * 系统注销!
     * @return
     */
    @RequestMapping(value = "/employeeInfo/logout")
    protected String logout(HttpServletRequest request){
        SecurityUtils.getSubject().logout();//系统注销!!
        return "redirect:/login";//跳转到登录页
    }

    /**
     * 检查用户是否存在
     * @param employeeInfo
     * @return
     */
    @RequestMapping("/employeeInfo/checkEmployeeInfo")
    @ResponseBody
    protected StatusVO checkEmployeInfo(EmployeeInfo employeeInfo){
        StatusVO statusVO = new StatusVO();
        //检查用户名
        if(employeeInfo.getUserName() != null){
            EmployeeInfo info  = iEmployeeInfoService.selectOne(new EntityWrapper<EmployeeInfo>()
                    .where("user_name='"+employeeInfo.getUserName()+"'"));
            if(info == null){
                statusVO.setCode(200);
                statusVO.setMsg("该用户可以录入");
            }else{
                statusVO.setCode(300);
                statusVO.setMsg("该用户已经存在");
            }
        }
        //检查邮箱
        if(employeeInfo.getEmail() != null){
            EmployeeInfo info  = iEmployeeInfoService.selectOne(new EntityWrapper<EmployeeInfo>()
                    .where("email='"+employeeInfo.getEmail()+"'"));
            if(info == null){
                statusVO.setCode(200);
                statusVO.setMsg("该企业邮箱可以录入");
            }else{
                statusVO.setCode(300);
                statusVO.setMsg("该企业邮箱已经存在");
            }
        }
        return statusVO;
    }

    /**
     * 跳转到添加用户页面
     * @return
     */
    @RequestMapping(value = "/employeeInfo/add",method = RequestMethod.GET)
    public String add(Model model){
        List<TbDict> companyList = iTbDictService.selectList(new EntityWrapper<TbDict>().where("pid=1"));
        List<DepartmentInfo> departmentInfoList = iDepartmentInfoService.selectList(new EntityWrapper<DepartmentInfo>()
                .where("company_id=2"));
        List<TbRole> tbRoleList = iTbRoleService.selectList(new EntityWrapper<TbRole>());
        model.addAttribute("companyList",companyList);
        model.addAttribute("departmentInfoList",departmentInfoList);
        model.addAttribute("tbRoleList",tbRoleList);
        return "employeeInfo/add";
    }

    /**
     * 添加用户
     * @param employeeInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/employeeInfo/add",method = RequestMethod.POST)
    @ResponseBody
    public StatusVO add(EmployeeInfo employeeInfo, HttpServletRequest request){
        StatusVO statusVO = new StatusVO();
        HttpSession httpSession = request.getSession();
        EmployeeInfo employeeInfo1 = (EmployeeInfo) httpSession.getAttribute("employeeInfo");
        Random random = new Random();
        int randomValue = random.nextInt(1000000);
        String sendEmailMsg ="";
        try {
            employeeInfo.setPassword(Md5Util.getMD5(Md5Util.getMD5("hzit#" + randomValue)));
            //发送邮件!!
            sendEmailMsg = EmailUtil.sendEmail("", "", employeeInfo.getEmail(),
                    "合众艾特员工管理系统 登录用户名:" + employeeInfo.getUserName() + " 密码:" + randomValue);//发送随机密码
            employeeInfo.setCreateBy(employeeInfo1.getUserName());//录入人
            employeeInfo.setCreateTime(new Date());
            if("成功".equals(sendEmailMsg)){
                employeeInfo.setIsEmailActive(1);
            }
            boolean result = iEmployeeInfoService.insert(employeeInfo);
            if(result){
                statusVO.setCode(200);
                statusVO.setMsg("用户创建成功+发送邮件:"+sendEmailMsg);
            }else {
                statusVO.setCode(300);
                statusVO.setMsg("用户创建失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            statusVO.setCode(300);
            statusVO.setMsg("邮件发送:"+sendEmailMsg);
        }
        return statusVO;

    }

    /**
     * 根据公司id获取所有部门信息
     * @param companyId
     * @return
     */
    @RequestMapping("/employeeInfo/getDept")
    @ResponseBody
    protected List<DepartmentInfo> getDept(int companyId){
        List<DepartmentInfo> departmentInfoList = iDepartmentInfoService.selectList(new EntityWrapper<DepartmentInfo>()
                .where("company_id=" + companyId));
        return departmentInfoList;
    }

//    /**
//     * 根据部门id获取所有角色信息
//     * @param deptId
//     * @return
//     */
//    @RequestMapping("/employeeInfo/getRole")
//    @ResponseBody
//    protected List<TbRole> getRole(int deptId){
//        List<TbRole> tbRoleList = iTbRoleService.selectList(new EntityWrapper<TbRole>()
//                .where("dept_id=" + deptId));
//        return tbRoleList;
//    }


    /**
     * 登录密码重置
     * @param email
     * @return
     */
    @RequestMapping("/employeeInfo/resetPwd")
    @ResponseBody
    protected StatusVO resetPwd(String email){
        StatusVO statusVO = new StatusVO();
        EmployeeInfo employeeInfo = iEmployeeInfoService.selectOne(new EntityWrapper<EmployeeInfo>()
                .where("email='" + email + "'"));
        if(employeeInfo != null){
            Random random = new Random();
            int randomValue = random.nextInt(1000000);
            String sendEmailMsg = "";
            try{
                employeeInfo.setPassword(Md5Util.getMD5(Md5Util.getMD5("hzit#"+randomValue)));
                sendEmailMsg = EmailUtil.sendEmail("","",employeeInfo.getEmail(),
                        "合众艾特员工管理系统 密码重置成功 登录账号为:"+employeeInfo.getUserName()+",密码为:"+randomValue+"");
                iEmployeeInfoService.updateById(employeeInfo);
                if("成功".equals(sendEmailMsg)){
                    statusVO.setCode(200);
                    statusVO.setMsg("密码已重置，请查看邮箱");
                }

            }   catch ( Exception e){
                statusVO.setCode(300);
                statusVO.setMsg("密码重置失败");
            }
        }else{
            statusVO.setCode(300);
            statusVO.setMsg("该邮箱不存在");
        }
        return statusVO;
    }

    @RequestMapping("/employeeInfo/listData")
    @ResponseBody
    public BootstrapTable<EmployeeInfoVo> listData(BootstrapEntity bt,HttpSession session){
        if (bt.getOffset() == null || bt.getLimit() == null) {
            bt.setOffset(1);
            bt.setLimit(20);
        } else {
            bt.setOffset(bt.getOffset() / bt.getLimit());
        }
        Page<EmployeeInfo> page = new Page<>(bt.getOffset(),bt.getLimit());
        if("-1".equals(bt.getCondition()) ){
            bt.setCondition("");
        }
        Wrapper<EmployeeInfo> wrapper = null;
        wrapper = new EntityWrapper<EmployeeInfo>().where("isLocked=0");
        BootstrapTable<EmployeeInfoVo> bootstrapTable = iEmployeeInfoService.ajaxData(page, wrapper);
        return bootstrapTable;
    }
	
}