package com.hzitoa.vo;

import java.util.List;

/**
 * @author 吕游
 * @company 合众艾特
 * @create 2017-10-13 10:37
 * @description
 */
public class LayuiVo<T> {
    private Integer code;
    private String msg;
    private Integer count;
    private List<T> data;

    public LayuiVo() {
    }

    public LayuiVo(Integer code, String msg, Integer count, List<T> data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LayuiVo{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }
}
