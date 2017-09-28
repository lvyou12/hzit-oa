package com.hzitoa.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
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
@TableName("institution_info")
public class InstitutionInfo extends Model<InstitutionInfo> {

    private static final long serialVersionUID = 1L;
	@TableId(value = "inst_id",type = IdType.AUTO)
	private Integer instId;
    /**
     * 部门id
     */
	@TableField("dept_id")
	private Integer deptId;
    /**
     * 公司id
     */
	@TableField("company_id")
	private Integer companyId;
    /**
     * 存储路径
     */
	private String path;
    /**
     * 制度名
     */
	private String name;
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

	public Integer getInstId() {
		return instId;
	}

	public void setInstId(Integer instId) {
		this.instId = instId;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "InstitutionInfo{" +
				"instId=" + instId +
				", deptId=" + deptId +
				", companyId=" + companyId +
				", path='" + path + '\'' +
				", name='" + name + '\'' +
				", createBy='" + createBy + '\'' +
				", createTime=" + createTime +
				'}';
	}

	public InstitutionInfo() {
	}

	@Override
	protected Serializable pkVal() {
		return this.instId;
	}

}
