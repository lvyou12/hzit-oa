package com.hzitoa.entity;

public class TbAuthority {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_authority.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_authority.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_authority.url
     *
     * @mbggenerated
     */
    private String url;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_authority.pid
     *
     * @mbggenerated
     */
    private Integer pid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_authority.available
     *
     * @mbggenerated
     */
    private Byte available;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_authority.id
     *
     * @return the value of tb_authority.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_authority.id
     *
     * @param id the value for tb_authority.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_authority.name
     *
     * @return the value of tb_authority.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_authority.name
     *
     * @param name the value for tb_authority.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_authority.url
     *
     * @return the value of tb_authority.url
     *
     * @mbggenerated
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_authority.url
     *
     * @param url the value for tb_authority.url
     *
     * @mbggenerated
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_authority.pid
     *
     * @return the value of tb_authority.pid
     *
     * @mbggenerated
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_authority.pid
     *
     * @param pid the value for tb_authority.pid
     *
     * @mbggenerated
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_authority.available
     *
     * @return the value of tb_authority.available
     *
     * @mbggenerated
     */
    public Byte getAvailable() {
        return available;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_authority.available
     *
     * @param available the value for tb_authority.available
     *
     * @mbggenerated
     */
    public void setAvailable(Byte available) {
        this.available = available;
    }
}