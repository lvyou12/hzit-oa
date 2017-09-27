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
@TableName("tb_dict")
public class TbDict extends Model<TbDict> {

    private static final long serialVersionUID = 1L;

	private Integer id;
    /**
     * 父级id
     */
	private Integer pid;
    /**
     * 标签名
     */
	private String name;
    /**
     * 标签是否打开
     */
	private String open;
	private String checked;


	public TbDict() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TbDict{" +
				"id=" + id +
				", pid=" + pid +
				", name='" + name + '\'' +
				", open='" + open + '\'' +
				", checked='" + checked + '\'' +
				'}';
	}
}
