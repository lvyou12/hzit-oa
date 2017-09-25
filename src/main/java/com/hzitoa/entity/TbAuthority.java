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


	public Integer getId() {
		return id;
	}

	public TbAuthority setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public TbAuthority setName(String name) {
		this.name = name;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public TbAuthority setUrl(String url) {
		this.url = url;
		return this;
	}

	public Integer getPid() {
		return pid;
	}

	public TbAuthority setPid(Integer pid) {
		this.pid = pid;
		return this;
	}

	public Integer getAvailable() {
		return available;
	}

	public TbAuthority setAvailable(Integer available) {
		this.available = available;
		return this;
	}

	public String getPermission() {
		return permission;
	}

	public TbAuthority setPermission(String permission) {
		this.permission = permission;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
