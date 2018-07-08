package com.fourzeroeight.crm.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setBackground(Integer background) {
        this.background = background;
    }

    public void setListed(Integer listed) {
        this.listed = listed;
    }

    public void setCapital(Integer capital) {
        this.capital = capital;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public void setTester(Integer tester) {
        this.tester = tester;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public void setNewcolumn1(Integer newcolumn1) {
        this.newcolumn1 = newcolumn1;
    }

    public void setCreatetime(Date createtime) {
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