package com.hzitoa.entity;

public class EmployeeInfo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee_info.user_id
     *
     * @mbggenerated
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee_info.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee_info.password
     *
     * @mbggenerated
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee_info.wechat_id
     *
     * @mbggenerated
     */
    private String wechatId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee_info.dingding_id
     *
     * @mbggenerated
     */
    private String dingdingId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee_info.email
     *
     * @mbggenerated
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee_info.dept_id
     *
     * @mbggenerated
     */
    private Integer deptId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee_info.user_id
     *
     * @return the value of employee_info.user_id
     *
     * @mbggenerated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee_info.user_id
     *
     * @param userId the value for employee_info.user_id
     *
     * @mbggenerated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee_info.name
     *
     * @return the value of employee_info.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee_info.name
     *
     * @param name the value for employee_info.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee_info.password
     *
     * @return the value of employee_info.password
     *
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee_info.password
     *
     * @param password the value for employee_info.password
     *
     * @mbggenerated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee_info.wechat_id
     *
     * @return the value of employee_info.wechat_id
     *
     * @mbggenerated
     */
    public String getWechatId() {
        return wechatId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee_info.wechat_id
     *
     * @param wechatId the value for employee_info.wechat_id
     *
     * @mbggenerated
     */
    public void setWechatId(String wechatId) {
        this.wechatId = wechatId == null ? null : wechatId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee_info.dingding_id
     *
     * @return the value of employee_info.dingding_id
     *
     * @mbggenerated
     */
    public String getDingdingId() {
        return dingdingId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee_info.dingding_id
     *
     * @param dingdingId the value for employee_info.dingding_id
     *
     * @mbggenerated
     */
    public void setDingdingId(String dingdingId) {
        this.dingdingId = dingdingId == null ? null : dingdingId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee_info.email
     *
     * @return the value of employee_info.email
     *
     * @mbggenerated
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee_info.email
     *
     * @param email the value for employee_info.email
     *
     * @mbggenerated
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employee_info.dept_id
     *
     * @return the value of employee_info.dept_id
     *
     * @mbggenerated
     */
    public Integer getDeptId() {
        return deptId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employee_info.dept_id
     *
     * @param deptId the value for employee_info.dept_id
     *
     * @mbggenerated
     */
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
}