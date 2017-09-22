package com.hzitoa.vo;

/**
 * 异步返回的数据
 * Created by xianyaoji on 2017/6/26.
 */
public class StatusVO {
    private int code ;//返回的状态信息  自定义状态信息  600 表示成功  700表示失败
    private String msg;//返回的消息

    public StatusVO() {
    }

    public StatusVO(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "StatusVO{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
