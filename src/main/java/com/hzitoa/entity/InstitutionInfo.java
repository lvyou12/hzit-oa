package com.hzitoa.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import java.util.Date;
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
@TableName("institution_info")
public class InstitutionInfo extends Model<InstitutionInfo> {

    private static final long serialVersionUID = 1L;

	private Integer id;
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


	public Integer getId() {
		return id;
	}

	public InstitutionInfo setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public InstitutionInfo setDeptId(Integer deptId) {
		this.deptId = deptId;
		return this;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public InstitutionInfo setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}

	public String getPath() {
		return path;
	}

	public InstitutionInfo setPath(String path) {
		this.path = path;
		return this;
	}

	public String getName() {
		return name;
	}

	public InstitutionInfo setName(String name) {
		this.name = name;
		return this;
	}

	public String getCreateBy() {
		return createBy;
	}

	public InstitutionInfo setCreateBy(String createBy) {
		this.createBy = createBy;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public InstitutionInfo setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
