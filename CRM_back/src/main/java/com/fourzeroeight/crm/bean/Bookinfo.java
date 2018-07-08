package com.fourzeroeight.crm.bean;

import java.util.Date;

public class Bookinfo {
    private Integer id;

    private String title;

    private String descs;

    private Date booktime;

    private Date createtime;

    public Bookinfo() {
    }

    public Bookinfo(Integer id, String title, String descs, Date booktime, Date createtime) {
        this.id = id;
        this.title = title;
        this.descs = descs;
        this.booktime = booktime;
        this.createtime = createtime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public void setBooktime(Date booktime) {
        this.booktime = booktime;
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

    public String getDescs() {
        return descs;
    }

    public Date getBooktime() {
        return booktime;
    }

    public Date getCreatetime() {
        return createtime;
    }
}