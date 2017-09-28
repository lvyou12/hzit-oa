package com.hzitoa.vo;

import java.util.Date;

/**
 * Created by Meiyang on 2017/9/28.
 */
public class InstitutionInfoVo {

    private Integer instId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 公司名称
     */
    private String companyName;

    private String path;
    private String name;
    private String createBy;
    private Date createTime;

    public Integer getInstId() {
        return instId;
    }

    public void setInstId(Integer instId) {
        this.instId = instId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public InstitutionInfoVo() {
    }

    @Override
    public String toString() {
        return "InstitutionInfoVo{" +
                "instId=" + instId +
                ", deptName='" + deptName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
