package com.hzitoa.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * @author 吕游
 * @company 合众艾特
 * @create 2017-10-16 10:42
 * @description
 */
public class AdditionalParameters {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 是否选中
     */
    @JsonProperty("item-selected") //转换成json数据格式后的属性名替换
    private boolean item_selected;
    /**
     * 子节点
     */
    private Map<String,AceTreeVo> children;

    public AdditionalParameters() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isItem_selected() {
        return item_selected;
    }

    public void setItem_selected(boolean item_selected) {
        this.item_selected = item_selected;
    }

    public Map<String, AceTreeVo> getChildren() {
        return children;
    }

    public void setChildren(Map<String, AceTreeVo> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "AdditionalParameters{" +
                "id=" + id +
                ", item_selected=" + item_selected +
                ", children=" + children +
                '}';
    }
}
