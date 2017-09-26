package com.hzitoa.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hzitoa.entity.EmployeeInfo;
import com.hzitoa.mapper.EmployeeInfoMapper;
import com.hzitoa.service.IEmployeeInfoService;
import com.hzitoa.vo.StatusVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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
    private EmployeeInfoMapper employeeInfoMapper;

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
//            Subject subject = SecurityUtils.getSubject();//从SecurityUtils中获取主体对象
//            UsernamePasswordToken token = new UsernamePasswordToken(employeeInfo.getName(), employeeInfo.getPassword());
//            subject.login(token);
//            Map<String,Object> paramMap = new HashMap<>();
//            paramMap.put("name",employeeInfo.getName());
//            paramMap.put("password",employeeInfo.getPassword());
//            paramMap.put("isLocked","0");

//            List<EmployeeInfo> employeeInfoList = iEmployeeInfoService.selectByMap(paramMap);
             ;
            List<EmployeeInfo> employeeInfoList = iEmployeeInfoService.selectList(new EntityWrapper<EmployeeInfo>()
                                                        .where("name='" + employeeInfo.getName() + "'")
                                                        .and(("password='"+employeeInfo.getPassword()+"'"))
                                                        .and("isLocked=0"));
            HttpSession httpSession = request.getSession();
            if(employeeInfoList!=null && employeeInfoList.size() >0){
                employeeInfo = employeeInfoList.get(0);
                employeeInfo.setPassword("");

                httpSession.setAttribute("employeeInfo",employeeInfo);
                statusVO.setCode(200);
                statusVO.setMsg("登录成功");
            }else{
                statusVO.setCode(300);
                statusVO.setMsg("用户名或密码错误");
            }

        }catch (Exception e){
//                AuthenticationException e){
            logger.error("------------用户登录出错----------------"+e.getMessage());
            statusVO.setCode(300);
            statusVO.setMsg("登录失败");
        }
        return statusVO;
    }


	
}
