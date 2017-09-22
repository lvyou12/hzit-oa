package com.hzitoa.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author Meiyang
 * @since 2017-09-22
 */
@TableName("employee_info")
public class EmployeeInfo extends Model<EmployeeInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
	@TableId("user_id")
	private Integer userId;
    /**
     * 用户名
     */
	private String name;
    /**
     * 密码
     */
	private String password;
    /**
     * 企业微信id
     */
	@TableField("wechat_id")
	private String wechatId;
    /**
     * 企业钉钉id
     */
	@TableField("dingding_id")
	private String dingdingId;
    /**
     * 企业邮箱
     */
	private String email;
    /**
     * 所属部门id
     */
	@TableField("dept_id")
	private Integer deptId;
    /**
     * 角色名
     */
	@TableField("role_name")
	private String roleName;
    /**
     * 是否禁用(1:启用,2:禁用)
     */
	private Integer isLocked;
    /**
     * 是否授权(1:赋予角色,2:未赋角色)
     */
	private Integer isPermission;
    /**
     * 是否离职(1:在职,2:离职)
     */
	private Integer isDimission;


	public Integer getUserId() {
		return userId;
	}

	public EmployeeInfo setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}

	public String getName() {
		return name;
	}

	public EmployeeInfo setName(String name) {
		this.name = name;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public EmployeeInfo setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getWechatId() {
		return wechatId;
	}

	public EmployeeInfo setWechatId(String wechatId) {
		this.wechatId = wechatId;
		return this;
	}

	public String getDingdingId() {
		return dingdingId;
	}

	public EmployeeInfo setDingdingId(String dingdingId) {
		this.dingdingId = dingdingId;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public EmployeeInfo setEmail(String email) {
		this.email = email;
		return this;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public EmployeeInfo setDeptId(Integer deptId) {
		this.deptId = deptId;
		return this;
	}

	public String getRoleName() {
		return roleName;
	}

	public EmployeeInfo setRoleName(String roleName) {
		this.roleName = roleName;
		return this;
	}

	public Integer getIsLocked() {
		return isLocked;
	}

	public EmployeeInfo setIsLocked(Integer isLocked) {
		this.isLocked = isLocked;
		return this;
	}

	public Integer getIsPermission() {
		return isPermission;
	}

	public EmployeeInfo setIsPermission(Integer isPermission) {
		this.isPermission = isPermission;
		return this;
	}

	public Integer getIsDimission() {
		return isDimission;
	}

	public EmployeeInfo setIsDimission(Integer isDimission) {
		this.isDimission = isDimission;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.userId;
	}

}
