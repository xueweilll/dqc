package com.benqzl.pojo.manage;

import java.util.Date;

public class Watch {
    private String id;

    private String bm;

    private Date riqi;

    private Date shijian;

    private String renyuan;

    private String sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBm() {
        return bm;
    }

    public void setBm(String bm) {
        this.bm = bm == null ? null : bm.trim();
    }

    public Date getRiqi() {
        return riqi;
    }

    public void setRiqi(Date riqi) {
        this.riqi = riqi;
    }

    public Date getShijian() {
        return shijian;
    }

    public void setShijian(Date shijian) {
        this.shijian = shijian;
    }

    public String getRenyuan() {
        return renyuan;
    }

    public void setRenyuan(String renyuan) {
        this.renyuan = renyuan == null ? null : renyuan.trim();
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort == null ? null : sort.trim();
    }
}