package com.yalogs.redisclient.commons;

import java.io.Serializable;

public class Keys implements Serializable {

    private String type;
    private String key;

    public Keys() {
    }

    public Keys(String type, String key) {
        this.type = type;
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
