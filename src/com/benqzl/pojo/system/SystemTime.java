package com.benqzl.pojo.system;

import java.util.Date;

public class SystemTime {
    private String id;

    private Date stime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getStime() {
        return stime;
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }
}