package com.hzitoa.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

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
	@TableId(value = "dept_id",type = IdType.AUTO)
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

	public DepartmentInfo() {
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	protected Serializable pkVal() {
		return this.deptId;
	}

	@Override
	public String toString() {
		return "DepartmentInfo{" +
				"deptId=" + deptId +
				", deptName='" + deptName + '\'' +
				", pid=" + pid +
				", companyId=" + companyId +
				", createBy='" + createBy + '\'' +
				", createDate=" + createDate +
				", updateBy='" + updateBy + '\'' +
				", updateDate=" + updateDate +
				'}';
	}
}
