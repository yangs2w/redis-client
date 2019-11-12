package com.yalogs.redisclient.commons;

import java.io.Serializable;
import java.util.List;

public class KeysResult implements Serializable {

    private int code;
    private String msg;
    private Integer count;
    private List<Keys> data;

    public KeysResult() {
    }

    public KeysResult(int code, String msg, int count, List<Keys> data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Keys> getData() {
        return data;
    }

    public void setData(List<Keys> data) {
        this.data = data;
    }
}
