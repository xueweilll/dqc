package com.benqzl.pojo.manage;

import java.util.Date;

public class OAContent {
    private String id;

    private String title;

    private String fbdw;

    private String name;

    private Date yxri;

    private Date fbri;

    private String reads;

    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getFbdw() {
        return fbdw;
    }

    public void setFbdw(String fbdw) {
        this.fbdw = fbdw == null ? null : fbdw.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getYxri() {
        return yxri;
    }

    public void setYxri(Date yxri) {
        this.yxri = yxri;
    }

    public Date getFbri() {
        return fbri;
    }

    public void setFbri(Date fbri) {
        this.fbri = fbri;
    }

    public String getReads() {
        return reads;
    }

    public void setReads(String reads) {
        this.reads = reads == null ? null : reads.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}