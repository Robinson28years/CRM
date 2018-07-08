package com.fourzeroeight.crm.bean;

import java.util.Date;

public class Users {
    private Integer id;

    private String username;

    private String password;

    private String sex;

    private Date birthday;

    private Integer roleid;

    private Integer status;

    private Roles roles;

    public Users() {

    }

    public Users(Integer id, String username, String password, String sex, Date birthday, Integer roleid, Integer status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.birthday = birthday;
        this.roleid = roleid;
        this.status = status;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSex() {
        return sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public Integer getStatus() {
        return status;
    }
}