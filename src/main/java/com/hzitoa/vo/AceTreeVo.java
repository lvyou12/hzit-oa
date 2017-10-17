package com.hzitoa.vo;

/**
 * @author 吕游
 * @company 合众艾特
 * @create 2017-10-16 10:39
 * @description
 */
public class AceTreeVo {
    /**
     * 节点名
     */
    private String name;
    /**
     * 节点类型: folder - 拥有子节点,item - 没有子节点
     */
    private String type;
    /**
     * 节点配置
     */
    private AdditionalParameters additionalParameters;

    public AceTreeVo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AdditionalParameters getAdditionalParameters() {
        return additionalParameters;
    }

    public void setAdditionalParameters(AdditionalParameters additionalParameters) {
        this.additionalParameters = additionalParameters;
    }

    @Override
    public String toString() {
        return "AceTreeVo{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", additionalParameters=" + additionalParameters +
                '}';
    }
}
