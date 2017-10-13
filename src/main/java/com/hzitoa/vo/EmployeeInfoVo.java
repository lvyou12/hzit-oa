package com.hzitoa.vo;

import java.util.Date;

/**
 * Created by Meiyang on 2017/10/13.
 */
public class EmployeeInfoVo {

    private Integer userId;
    private String userName;
    private String password;
    private String wechatId;
    private String dingdingId;
    private String email;
    /**
     * 职位名称
     */
    private String deptName;
    /**
     * 公司名称
     */
    private String companyName;
    private String roleName;
    private Integer isEmailActive;
    private Integer isLocked;
    private Integer isPermission;
    private Integer isDimission;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public String getDingdingId() {
        return dingdingId;
    }

    public void setDingdingId(String dingdingId) {
        this.dingdingId = dingdingId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getIsEmailActive() {
        return isEmailActive;
    }

    public void setIsEmailActive(Integer isEmailActive) {
        this.isEmailActive = isEmailActive;
    }

    public Integer getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Integer isLocked) {
        this.isLocked = isLocked;
    }

    public Integer getIsPermission() {
        return isPermission;
    }

    public void setIsPermission(Integer isPermission) {
        this.isPermission = isPermission;
    }

    public Integer getIsDimission() {
        return isDimission;
    }

    public void setIsDimission(Integer isDimission) {
        this.isDimission = isDimission;
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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public EmployeeInfoVo() {
    }

    @Override
    public String toString() {
        return "EmployeeInfoVo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", wechatId='" + wechatId + '\'' +
                ", dingdingId='" + dingdingId + '\'' +
                ", email='" + email + '\'' +
                ", deptName='" + deptName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", roleName='" + roleName + '\'' +
                ", isEmailActive=" + isEmailActive +
                ", isLocked=" + isLocked +
                ", isPermission=" + isPermission +
                ", isDimission=" + isDimission +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
