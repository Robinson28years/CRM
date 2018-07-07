package com.fourzeroeight.crm.bean;

import java.util.Date;

public class Contacts {
    private Integer id;

    private Integer custid;

    private String name;

    private String dept;

    private String position;

    private String telephone;

    private String mobile;

    private String email;

    private String qq;

    private Integer degree;

    private String moreinfo;

    private Date createtime;

    private Integer bookid;

    private Integer title;

    public Contacts() {
    }

    public Contacts(Integer id, Integer custid, String name, String dept, String position, String telephone, String mobile, String email, String qq, Integer degree, String moreinfo, Date createtime, Integer bookid, Integer title) {
        this.id = id;
        this.custid = custid;
        this.name = name;
        this.dept = dept;
        this.position = position;
        this.telephone = telephone;
        this.mobile = mobile;
        this.email = email;
        this.qq = qq;
        this.degree = degree;
        this.moreinfo = moreinfo;
        this.createtime = createtime;
        this.bookid = bookid;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCustid() {
        return custid;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public String getPosition() {
        return position;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getQq() {
        return qq;
    }

    public Integer getDegree() {
        return degree;
    }

    public String getMoreinfo() {
        return moreinfo;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public Integer getBookid() {
        return bookid;
    }

    public Integer getTitle() {
        return title;
    }
}