package com.benqzl.pojo.system;

import java.util.List;

import com.benqzl.controller.system.UtilData;

public class SysParameter {
    private String id;//主键

    private String name;//参数显示名称

    private String pkey;//参数查询关键字
    
    private String rating;//系统额定功率

	private String memo;//描述

    private String pvalue;//获取到的实时值

    private List<UtilData> utilDatas;
    
    public List<UtilData> getUtilDatas() {
		return utilDatas;
	}

	public void setUtilDatas(List<UtilData> utilDatas) {
		this.utilDatas = utilDatas;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
    
    public String getPvalue() {
		return pvalue;
	}

	public void setPvalue(String pvalue) {
		this.pvalue = pvalue;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPkey() {
        return pkey;
    }

    public void setPkey(String pkey) {
        this.pkey = pkey == null ? null : pkey.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

}