package com.fourzeroeight.crm.bean;

public class Roles {
    private Integer id;

    private String name;

    private String desc;

    public Roles() {
    }

    public Roles(Integer id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}