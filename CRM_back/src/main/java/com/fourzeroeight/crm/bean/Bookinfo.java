package com.fourzeroeight.crm.bean;

import java.util.Date;

public class Bookinfo {
    private Integer id;

    private String title;

    private Integer desc;

    private Date booktime;

    private Date createtime;

    public Bookinfo() {
    }

    public Bookinfo(Integer id, String title, Integer desc, Date booktime, Date createtime) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.booktime = booktime;
        this.createtime = createtime;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getDesc() {
        return desc;
    }

    public Date getBooktime() {
        return booktime;
    }

    public Date getCreatetime() {
        return createtime;
    }
}