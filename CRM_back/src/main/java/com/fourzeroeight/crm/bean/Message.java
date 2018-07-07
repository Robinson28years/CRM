package com.fourzeroeight.crm.bean;

public class Message {
    private int code;
    private String mess;
    private Object res;

    public Message(){

    }
    public Message(int code, String mess, Object res) {
        this.code = code;
        this.mess = mess;
        this.res = res;
    }

    public Message(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public Object getRes() {
        return res;
    }

    public void setRes(Object res) {
        this.res = res;
    }
}
