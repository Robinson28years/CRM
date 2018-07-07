package com.fourzeroeight.crm.bean;

import java.util.Date;

public class Customers {
    private Integer id;

    private String name;

    private Integer type;

    private Integer background;

    private Integer listed;

    private Integer capital;

    private Integer sales;

    private Integer scale;

    private Integer tester;

    private String url;

    private String zipcode;

    private String address;

    private String products;

    private String services;

    private Integer userid;

    private Integer newcolumn1;

    private Date createtime;

    public Customers() {
    }

    public Customers(Integer id, String name, Integer type, Integer background, Integer listed, Integer capital, Integer sales, Integer scale, Integer tester, String url, String zipcode, String address, String products, String services, Integer userid, Integer newcolumn1, Date createtime) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.background = background;
        this.listed = listed;
        this.capital = capital;
        this.sales = sales;
        this.scale = scale;
        this.tester = tester;
        this.url = url;
        this.zipcode = zipcode;
        this.address = address;
        this.products = products;
        this.services = services;
        this.userid = userid;
        this.newcolumn1 = newcolumn1;
        this.createtime = createtime;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getType() {
        return type;
    }

    public Integer getBackground() {
        return background;
    }

    public Integer getListed() {
        return listed;
    }

    public Integer getCapital() {
        return capital;
    }

    public Integer getSales() {
        return sales;
    }

    public Integer getScale() {
        return scale;
    }

    public Integer getTester() {
        return tester;
    }

    public String getUrl() {
        return url;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getAddress() {
        return address;
    }

    public String getProducts() {
        return products;
    }

    public String getServices() {
        return services;
    }

    public Integer getUserid() {
        return userid;
    }

    public Integer getNewcolumn1() {
        return newcolumn1;
    }

    public Date getCreatetime() {
        return createtime;
    }
}