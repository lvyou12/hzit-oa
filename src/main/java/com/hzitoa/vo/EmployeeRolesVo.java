package com.hzitoa.vo;

/**
 * Created by Meiyang on 2017/10/16.
 */
public class EmployeeRolesVo {
    private String roleName;//角色名称
    private Integer isChecked;//用户是否拥有该角色：0：不拥有 1：拥有

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Integer isChecked) {
        this.isChecked = isChecked;
    }

    @Override
    public String toString() {
        return "EmployeeRolesVo{" +
                "roleName='" + roleName + '\'' +
                ", isChecked=" + isChecked +
                '}';
    }
}
