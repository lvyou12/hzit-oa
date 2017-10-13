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

    private Integer deptId;
    private Integer companyId;
    private String path;
    private String instName;
    private String createBy;
    private Date createTime;
    private Integer isDelete;

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

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public InstitutionInfoVo() {
    }

    @Override
    public String toString() {
        return "InstitutionInfoVo{" +
                "instId=" + instId +
                ", deptName='" + deptName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", deptId=" + deptId +
                ", companyId=" + companyId +
                ", path='" + path + '\'' +
                ", instName='" + instName + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", isDelete=" + isDelete +
                '}';
    }
}
