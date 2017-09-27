package com.hzitoa.entity;

import com.baomidou.mybatisplus.activerecord.Model;
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
@TableName("tb_authority")
public class TbAuthority extends Model<TbAuthority> {

    private static final long serialVersionUID = 1L;

	private Integer id;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		return this.id;
	}

	@Override
	public String toString() {
		return "TbAuthority{" +
				"id=" + id +
				", name='" + name + '\'' +
				", url='" + url + '\'' +
				", pid=" + pid +
				", available=" + available +
				", permission='" + permission + '\'' +
				'}';
	}
}
