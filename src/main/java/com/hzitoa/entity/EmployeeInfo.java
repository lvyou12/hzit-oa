package com.hzitoa.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;


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
	@TableId(value = "user_id",type = IdType.AUTO)
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
	 * 所属公司id
	 */
	@TableField("company_id")
	private Integer companyId;
    /**
     * 角色名
     */
	@TableField("role_name")
	private String roleName;
	/**
	 * 邮件是否发送成功(0:不成功,1:成功)
	 */
	private Integer isEmailActive;
    /**
     * 是否禁用(0:启用,1:禁用)
     */
	private Integer isLocked;
    /**
     * 是否授权(0:未赋角色,1:赋予角色)
     */
	private Integer isPermission;
    /**
     * 是否离职(0:在职,1:离职)
     */
	private Integer isDimission;
	/**
	 * 创建人
	 */
	@TableField("create_by")
	private String createBy;
	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private Date createTime;
	/**
	 * 修改人
	 */
	@TableField("update_by")
	private String updateBy;
	/**
	 * 修改时间
	 */
	@TableField("update_time")
	private Date updateTime;

	public EmployeeInfo() {
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	public String getDingdingId() {
		return dingdingId;
	}

	public void setDingdingId(String dingdingId) {
		this.dingdingId = dingdingId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Integer isLocked) {
		this.isLocked = isLocked;
	}

	public Integer getIsPermission() {
		return isPermission;
	}

	public void setIsPermission(Integer isPermission) {
		this.isPermission = isPermission;
	}

	public Integer getIsDimission() {
		return isDimission;
	}

	public void setIsDimission(Integer isDimission) {
		this.isDimission = isDimission;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getIsEmailActive() {
		return isEmailActive;
	}

	public void setIsEmailActive(Integer isEmailActive) {
		this.isEmailActive = isEmailActive;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.userId;
	}

	@Override
	public String toString() {
		return "EmployeeInfo{" +
				"userId=" + userId +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", wechatId='" + wechatId + '\'' +
				", dingdingId='" + dingdingId + '\'' +
				", email='" + email + '\'' +
				", deptId=" + deptId +
				", companyId=" + companyId +
				", roleName='" + roleName + '\'' +
				", isEmailActive=" + isEmailActive +
				", isLocked=" + isLocked +
				", isPermission=" + isPermission +
				", isDimission=" + isDimission +
				", createBy='" + createBy + '\'' +
				", createTime=" + createTime +
				", updateBy='" + updateBy + '\'' +
				", updateTime=" + updateTime +
				'}';
	}
}
