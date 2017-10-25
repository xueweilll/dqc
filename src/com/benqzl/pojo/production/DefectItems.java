package com.benqzl.pojo.production;

import java.util.Date;

public class DefectItems {
    private Integer id;

    private Integer parentid;

    private String op;

    private String ip;

    private String username;

    private String memo;

    private Date rectime;

    private String bm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op == null ? null : op.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Date getRectime() {
        return rectime;
    }

    public void setRectime(Date rectime) {
        this.rectime = rectime;
    }

    public String getBm() {
        return bm;
    }

    public void setBm(String bm) {
        this.bm = bm == null ? null : bm.trim();
    }
}