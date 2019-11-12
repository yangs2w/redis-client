package com.yalogs.redisclient.commons;

import java.io.Serializable;
import java.util.List;

public class BaseResult implements Serializable {

    private Integer status;
    private String msg;
    private List<Object> data;

    public BaseResult() {
    }

    public BaseResult(Integer status, String msg, List<Object> data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
}
