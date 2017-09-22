package com.hzitoa.web;

import com.hzitoa.entity.EmployeeInfo;
import com.hzitoa.service.IEmployeeInfoService;
import com.hzitoa.vo.StatusVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
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
@RequestMapping("/employeeInfo")
public class EmployeeInfoController {
    private Logger logger = LoggerFactory.getLogger(EmployeeInfoController.class);
    @Autowired
    private IEmployeeInfoService iEmployeeInfoService;

    public StatusVO login(EmployeeInfo employeeInfo, HttpServletRequest request){
        StatusVO statusVO = new StatusVO();
        Subject subject = SecurityUtils.getSubject();//从SecurityUtils中获取主体对象

        UsernamePasswordToken token = new UsernamePasswordToken(employeeInfo.getName(), employeeInfo.getPassword());
        try{
            subject.login(token);
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("name",employeeInfo.getName());
            paramMap.put("isLocked","0");

            List<EmployeeInfo> employeeInfoList = iEmployeeInfoService.selectByMap(paramMap);
            HttpSession httpSession = request.getSession();
            if(employeeInfoList!=null && employeeInfoList.size() >0){
                employeeInfo = employeeInfoList.get(0);
                employeeInfo.setPassword("");
            }
            httpSession.setAttribute("employeeInfo",employeeInfo);
            statusVO.setCode(200);
            statusVO.setMsg("登录成功");
        }catch (AuthenticationException e){
            logger.error("------------用户登录出错----------------"+e.getMessage());
            statusVO.setCode(300);
            statusVO.setMsg("登录失败");
        }
        return null;
    }


	
}
