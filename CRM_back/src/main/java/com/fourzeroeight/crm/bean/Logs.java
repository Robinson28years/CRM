package com.fourzeroeight.crm.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Logs {
    private Integer id;

    private String title;

    private String descs;

    private Integer custid;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    public Logs() {
    }

    public Logs(Integer id, String title, String descs, Integer custid, Date createtime) {
        this.id = id;
        this.title = title;
        this.descs = descs;
        this.custid = custid;
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

    public String getDescs() {
        return descs;
    }

    public Integer getCustid() {
        return custid;
    }

    public Date getCreatetime() {
        return createtime;
    }
}