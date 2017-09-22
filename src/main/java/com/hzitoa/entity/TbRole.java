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
@TableName("tb_role")
public class TbRole extends Model<TbRole> {

    private static final long serialVersionUID = 1L;

	private Integer id;
    /**
     * 角色名称
     */
	@TableField("role_name")
	private String roleName;
    /**
     * 拥有的权限
     */
	@TableField("resource_ids")
	private String resourceIds;
    /**
     * 是否启用(0:禁用;1:启用)
     */
	private Integer available;
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


	public Integer getId() {
		return id;
	}

	public TbRole setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getRoleName() {
		return roleName;
	}

	public TbRole setRoleName(String roleName) {
		this.roleName = roleName;
		return this;
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public TbRole setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
		return this;
	}

	public Integer getAvailable() {
		return available;
	}

	public TbRole setAvailable(Integer available) {
		this.available = available;
		return this;
	}

	public String getCreateBy() {
		return createBy;
	}

	public TbRole setCreateBy(String createBy) {
		this.createBy = createBy;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public TbRole setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public TbRole setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public TbRole setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
