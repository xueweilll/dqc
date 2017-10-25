package com.benqzl.pojo.system;

import java.util.Date;

public class Employee {
    private String id;

    private String departmentid;

    private Date cratetime;

    private String name;

    private Date edittime;

    private String moblie;

    private String phs;

    private String cornet;

    private String memo;

    private Integer state;
    
    private Department department;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid == null ? null : departmentid.trim();
    }

    public Date getCratetime() {
        return cratetime;
    }

    public void setCratetime(Date cratetime) {
        this.cratetime = cratetime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getEdittime() {
        return edittime;
    }

    public void setEdittime(Date edittime) {
        this.edittime = edittime;
    }

    public String getMoblie() {
        return moblie;
    }

    public void setMoblie(String moblie) {
        this.moblie = moblie == null ? null : moblie.trim();
    }

    public String getPhs() {
        return phs;
    }

    public void setPhs(String phs) {
        this.phs = phs == null ? null : phs.trim();
    }

    public String getCornet() {
        return cornet;
    }

    public void setCornet(String cornet) {
        this.cornet = cornet == null ? null : cornet.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}