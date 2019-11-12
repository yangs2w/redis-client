package com.yalogs.redisclient.commons;

import java.io.Serializable;

public class ConnResult implements Serializable {

    // 状态码
    // 200 正确
    // 400 发生异常
    private int status;
    // 返回信息
    private String message;
    // 同时返回的数据
    private Object data;

    public ConnResult() {
    }

    public ConnResult(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ConnResult(int status, Object data) {
        this.status = status;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
