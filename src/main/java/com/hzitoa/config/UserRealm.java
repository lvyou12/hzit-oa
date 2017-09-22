package com.hzitoa.config;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hzitoa.entity.EmployeeInfo;
import com.hzitoa.entity.TbAuthority;
import com.hzitoa.entity.TbRole;
import com.hzitoa.service.IEmployeeInfoService;
import com.hzitoa.service.ITbAuthorityService;
import com.hzitoa.service.ITbRoleService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 验证用户登录
 */
@Component("userRealm")
public class UserRealm extends AuthorizingRealm {
	private Logger logger = LoggerFactory.getLogger(UserRealm.class);
	@Autowired
	private IEmployeeInfoService iEmployeeInfoService;
	@Autowired
	private ITbRoleService iTbRoleService;
	@Autowired
	private ITbAuthorityService iTbAuthorityService;
	/**
	 * 密码加密方式
     */
	public UserRealm() {
		setName("UserRealm");
		// 采用MD5加密
		setCredentialsMatcher(new HashedCredentialsMatcher("md5"));
	}

	//权限资源角色
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		//根据用户查找该用户所能访问的资源
		Set<String> set = new HashSet<>();
		EmployeeInfo employeeInfo = iEmployeeInfoService.selectOne(
				new EntityWrapper<EmployeeInfo>().where("name='"+username+"'").and("isLocked=0"));
		TbRole tbRole = iTbRoleService.selectOne(
				new EntityWrapper<TbRole>().where("role_name'"+employeeInfo.getRoleName()+"'"));
		String ids = tbRole.getResourceIds();
		String[] idArr = ids.split(",");
		//到tb_authority表查询该用户所能访问的permission
		List<TbAuthority> tbAuthorityList = iTbAuthorityService.selectBatchIds(Arrays.asList(idArr));
		for(TbAuthority tbAuthority: tbAuthorityList){
			if(tbAuthority.getPermission()!=null && !"".equals(tbAuthority.getPermission())){
				set.add(tbAuthority.getPermission());
			}
		}
		info.setStringPermissions(set);
		return info;
	}
	
	//登录验证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
		UsernamePasswordToken upt = (UsernamePasswordToken) token;
		String userName = upt.getUsername();
		EmployeeInfo user = iEmployeeInfoService.selectOne(
				new EntityWrapper<EmployeeInfo>().where("name='"+userName+"'").and("isLocked=0"));//employeeInfoService.findByAccount(userName);
		if (user == null) {
			user = new EmployeeInfo();
			logger.error("-------------未知账号错误!--------------------");
			throw new AccountException("用户名或密码不正确！");
		}else if(user.getIsDimission()==2){
			/**
			 * 如果用户的status为禁用。那么就抛出<code>DisabledAccountException</code>
			 */
			throw new DisabledAccountException("该员工已离职！");
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName, user.getPassword(), getName());
		return info;
	}
}