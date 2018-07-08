package com.fourzeroeight.crm.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Files {
    private Integer id;

    private String url;

    private String name;

    private String title;

    private String descs;

    private String type;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    private Integer custid;

    private Customers cust;

    public Files(Integer id, String url, String name, String title, String descs, String type, Date createtime, Integer custid) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.title = title;
        this.descs = descs;
        this.type = type;
        this.createtime = createtime;
        this.custid = custid;
    }

    public Customers getCust() {
        return cust;
    }

    public void setCust(Customers cust) {
        this.cust = cust;
    }

    public Files() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs == null ? null : descs.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getCustid() {
        return custid;
    }

    public void setCustid(Integer custid) {
        this.custid = custid;
    }
}