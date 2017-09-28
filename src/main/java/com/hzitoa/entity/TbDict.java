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
@TableName("tb_dict")
public class TbDict extends Model<TbDict> {

    private static final long serialVersionUID = 1L;

	@TableId(value = "dict_id",type = IdType.AUTO)
	private Integer dictId;
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

	public Integer getDictId() {
		return dictId;
	}

	public void setDictId(Integer dictId) {
		this.dictId = dictId;
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
		return this.dictId;
	}

	@Override
	public String toString() {
		return "TbDict{" +
				"dictId=" + dictId +
				", pid=" + pid +
				", name='" + name + '\'' +
				", open='" + open + '\'' +
				", checked='" + checked + '\'' +
				'}';
	}
}
