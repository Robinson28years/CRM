package com.fourzeroeight.crm.bean;

import java.util.Date;

public class Logs {
    private Integer id;

    private String title;

    private String desc;

    private Integer custid;

    private Date createtime;

    public Logs() {
    }

    public Logs(Integer id, String title, String desc, Integer custid, Date createtime) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.custid = custid;
        this.createtime = createtime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setCustid(Integer custid) {
        this.custid = custid;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public Integer getCustid() {
        return custid;
    }

    public Date getCreatetime() {
        return createtime;
    }
}