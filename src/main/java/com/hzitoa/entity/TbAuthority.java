package com.hzitoa.entity;

import com.baomidou.mybatisplus.activerecord.Model;
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
@TableName("tb_authority")
public class TbAuthority extends Model<TbAuthority> {

    private static final long serialVersionUID = 1L;

	@TableId(value = "auth_id",type = IdType.AUTO)
	private Integer authId;
    /**
     * 模块名
     */
	private String name;
    /**
     * 模块地址
     */
	private String url;
    /**
     * 父模块id
     */
	private Integer pid;
    /**
     * 是否启用(0:禁用;1:启用)
     */
	private Integer available;
	private String permission;


	public TbAuthority() {
	}

	public Integer getAuthId() {
		return authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	@Override
	protected Serializable pkVal() {
		return this.authId;
	}

	@Override
	public String toString() {
		return "TbAuthority{" +
				"authId=" + authId +
				", name='" + name + '\'' +
				", url='" + url + '\'' +
				", pid=" + pid +
				", available=" + available +
				", permission='" + permission + '\'' +
				'}';
	}
}
