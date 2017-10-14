package com.hzitoa.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
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
@TableName("tb_authority")
public class TbAuthority extends Model<TbAuthority> {

    private static final long serialVersionUID = 1L;

	@TableId(value = "auth_id",type = IdType.AUTO)
	private Integer authId;
    /**
     * 模块名
     */
	@TableField(value = "auth_name")
	private String authName;
    /**
     * 模块地址
     */
	private String url;
	/**
	 * 权限菜单对应图标
	 */
	private String icon;
    /**
     * 父模块id
     */
	private Integer pid;
    /**
     * 是否启用(0:禁用;1:启用)
     */
	private Integer available;
	private String permission;
	/**
	 * 是否为菜单权限(0:不是菜单权限，1:是菜单权限)
	 */
	private Integer isMenu;
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


	public TbAuthority() {
	}

	public Integer getAuthId() {
		return authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Integer getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(Integer isMenu) {
		this.isMenu = isMenu;
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
		return this.authId;
	}

	@Override
	public String toString() {
		return "TbAuthority{" +
				"authId=" + authId +
				", authName='" + authName + '\'' +
				", url='" + url + '\'' +
				", icon='" + icon + '\'' +
				", pid=" + pid +
				", available=" + available +
				", permission='" + permission + '\'' +
				", isMenu=" + isMenu +
				", createBy='" + createBy + '\'' +
				", createTime=" + createTime +
				", updateBy='" + updateBy + '\'' +
				", updateTime=" + updateTime +
				'}';
	}
}
