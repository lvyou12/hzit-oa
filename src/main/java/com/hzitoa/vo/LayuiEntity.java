package com.hzitoa.vo;

/**
 * Created by Meiyang on 2017/10/13.
 */
public class LayuiEntity {
    private Integer page;//当前页码
    private Integer limit;//每页数据量
    private String condition;//搜索条件
    private String value;//搜索值

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
