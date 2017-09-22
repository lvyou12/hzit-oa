package com.hzitoa.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import java.util.Date;
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
@TableName("department_info")
public class DepartmentInfo extends Model<DepartmentInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 部门id
     */
	@TableId("dept_id")
	private Integer deptId;
    /**
     * 部门名称
     */
	@TableField("dept_name")
	private String deptName;
    /**
     * 上级部门id
     */
	private Integer pid;
    /**
     * 所属公司id
     */
	@TableField("company_id")
	private Integer companyId;
    /**
     * 创建人
     */
	@TableField("create_by")
	private String createBy;
    /**
     * 创建时间
     */
	@TableField("create_date")
	private Date createDate;
    /**
     * 修改人
     */
	@TableField("update_by")
	private String updateBy;
    /**
     * 修改时间
     */
	@TableField("update_date")
	private Date updateDate;


	public Integer getDeptId() {
		return deptId;
	}

	public DepartmentInfo setDeptId(Integer deptId) {
		this.deptId = deptId;
		return this;
	}

	public String getDeptName() {
		return deptName;
	}

	public DepartmentInfo setDeptName(String deptName) {
		this.deptName = deptName;
		return this;
	}

	public Integer getPid() {
		return pid;
	}

	public DepartmentInfo setPid(Integer pid) {
		this.pid = pid;
		return this;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public DepartmentInfo setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}

	public String getCreateBy() {
		return createBy;
	}

	public DepartmentInfo setCreateBy(String createBy) {
		this.createBy = createBy;
		return this;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public DepartmentInfo setCreateDate(Date createDate) {
		this.createDate = createDate;
		return this;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public DepartmentInfo setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
		return this;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public DepartmentInfo setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.deptId;
	}

}
