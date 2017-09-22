package com.hzitoa.vo;

import java.util.Map;

/**
 * 异步返回的数据
 * Created by xianyaoji on 2017/6/26.
 */
public class StatusVO {
    private int code ;//返回的状态信息  自定义状态信息  600 表示成功  700表示失败
    private String msg;//返回的消息
    private Map<String,Object> map;

    public StatusVO(int code, String msg, Map<String, Object> map) {
        this.code = code;
        this.msg = msg;
        this.map = map;
    }

    public StatusVO() {
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

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "StatusVO{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", map=" + map +
                '}';
    }
}
