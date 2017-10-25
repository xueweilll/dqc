package com.benqzl.pojo.manage;

import java.util.Date;

public class Content {
    private String id;

    private String sort;

    private String title1;

    private String title2;

    private String color;

    private String content;

    private String name;

    private Integer ctop;

    private Integer viewnum;

    private Integer ding;

    private Date cdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1 == null ? null : title1.trim();
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2 == null ? null : title2.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCtop() {
        return ctop;
    }

    public void setCtop(Integer ctop) {
        this.ctop = ctop;
    }

    public Integer getViewnum() {
        return viewnum;
    }

    public void setViewnum(Integer viewnum) {
        this.viewnum = viewnum;
    }

    public Integer getDing() {
        return ding;
    }

    public void setDing(Integer ding) {
        this.ding = ding;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }
}