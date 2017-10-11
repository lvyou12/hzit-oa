package com.hzitoa.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hzitoa.email.EmailUtil;
import com.hzitoa.entity.EmployeeInfo;
import com.hzitoa.mapper.EmployeeInfoMapper;
import com.hzitoa.service.IEmployeeInfoService;
import com.hzitoa.utils.Md5Util;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

    @RequestMapping("/employeeInfo/selectAll")
    @ResponseBody
    public List<EmployeeInfo> findAll(){
        List<EmployeeInfo> list = iEmployeeInfoService.selectList(new EntityWrapper<EmployeeInfo>());
//        List<EmployeeInfo> list = employeeInfoMapper.selectAll();
        return list;
    }

    @RequestMapping(value = "/employeeInfo/login",method= RequestMethod.POST)
    @ResponseBody
    public StatusVO login(EmployeeInfo employeeInfo, HttpServletRequest request){
        StatusVO statusVO = new StatusVO();
        try{
            Subject subject = SecurityUtils.getSubject();//从SecurityUtils中获取主体对象
            UsernamePasswordToken token = new UsernamePasswordToken(employeeInfo.getName(), employeeInfo.getPassword());
            subject.login(token);
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("name",employeeInfo.getName());
            paramMap.put("email",employeeInfo.getEmail());
            paramMap.put("password",employeeInfo.getPassword());
            paramMap.put("isLocked","0");

            List<EmployeeInfo> employeeInfoList = iEmployeeInfoService.loginSelect(paramMap);

            HttpSession httpSession = request.getSession();
            if(employeeInfoList!=null && employeeInfoList.size() >0){
                employeeInfo = employeeInfoList.get(0);
                employeeInfo.setPassword("");

                httpSession.setAttribute("employeeInfo",employeeInfo);
                statusVO.setCode(200);
                statusVO.setMsg("登录成功");
            }
        }catch (
//                Exception e){
                AuthenticationException e){
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

    @RequestMapping("/employeeInfo/add")
    @ResponseBody
    public StatusVO add(EmployeeInfo employeeInfo, HttpServletRequest request){
        StatusVO statusVO = new StatusVO();
        Random random = new Random();
        int randomValue = random.nextInt(1000000);
        String sendEmilMsg ="";
        try {
            employeeInfo.setPassword(Md5Util.getMD5(Md5Util.getMD5("hzit#" + randomValue)));
            //发送邮件!!
            sendEmilMsg = EmailUtil.sendEmail("", "", employeeInfo.getEmail(),
                    "合众艾特咨询系统登录用户名:" + employeeInfo.getName() + " 密码:" + randomValue);//发送随机密码
        } catch (Exception e) {
            e.printStackTrace();
        }
        employeeInfo = new EmployeeInfo();
        employeeInfo.setName("mm");
        employeeInfo.setPassword("123");
        employeeInfo.setEmail("@qq.c");
        employeeInfo.setDeptId(1);
        employeeInfo.setIsLocked(0);
        iEmployeeInfoService.insert(employeeInfo);
    }


	
}