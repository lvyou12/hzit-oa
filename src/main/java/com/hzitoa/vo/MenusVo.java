package com.hzitoa.vo;

import com.hzitoa.entity.TbAuthority;

import java.util.Date;
import java.util.List;

/**
 * Created by Meiyang on 2017/10/14.
 */
public class MenusVo {
    private Integer authId;
    private String authName;
    private String url;
    private String icon;
    private Integer pid;
    private Integer available;
    private String permission;
    private Integer isMenu;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private List<TbAuthority> subAuthorityList;

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Integer getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(Integer isMenu) {
        this.isMenu = isMenu;
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

    public List<TbAuthority> getSubAuthorityList() {
        return subAuthorityList;
    }

    public void setSubAuthorityList(List<TbAuthority> subAuthorityList) {
        this.subAuthorityList = subAuthorityList;
    }

    @Override
    public String toString() {
        return "MenusVo{" +
                "authId=" + authId +
                ", authName='" + authName + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", pid=" + pid +
                ", available=" + available +
                ", permission='" + permission + '\'' +
                ", isMenu=" + isMenu +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", subAuthorityList=" + subAuthorityList +
                '}';
    }
}
