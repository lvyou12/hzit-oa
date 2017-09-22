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


	public Integer getId() {
		return id;
	}

	public TbDict setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getPid() {
		return pid;
	}

	public TbDict setPid(Integer pid) {
		this.pid = pid;
		return this;
	}

	public String getName() {
		return name;
	}

	public TbDict setName(String name) {
		this.name = name;
		return this;
	}

	public String getOpen() {
		return open;
	}

	public TbDict setOpen(String open) {
		this.open = open;
		return this;
	}

	public String getChecked() {
		return checked;
	}

	public TbDict setChecked(String checked) {
		this.checked = checked;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
