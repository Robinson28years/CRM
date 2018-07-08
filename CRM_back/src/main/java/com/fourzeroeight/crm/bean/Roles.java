package com.fourzeroeight.crm.bean;

public class Roles {
    private Integer id;

    private String name;

    private String descs;

    public Roles() {
    }

    public Roles(Integer id, String name, String descs) {
        this.id = id;
        this.name = name;
        this.descs = descs;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }
}