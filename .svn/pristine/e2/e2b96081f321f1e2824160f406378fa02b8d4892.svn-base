package com.benqzl.pojo.production;

import java.util.List;

import com.benqzl.controller.system.UtilData;

public class UnitParameter implements Comparable<UnitParameter>{
	private String id;// 主键

	private String name;// 显示名称

	private String pkey;// 查询关键字

	private String measurement;// 显示单位

	private Integer isdel;// 是否删除
	
	private Integer type;// 是否删除
	
	private Integer sort;// 是否删除

	private String memo;// 描述

	private String pvalue;// 实时显示值

	private String sysid;// 机组ID

	private List<UtilData> utilDatas;
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public List<UtilData> getUtilDatas() {
		return utilDatas;
	}

	public void setUtilDatas(List<UtilData> utilDatas) {
		this.utilDatas = utilDatas;
	}

	public String getSysid() {
		return sysid;
	}

	public void setSysid(String sysid) {
		this.sysid = sysid;
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

	public String getMeasurement() {
		return measurement;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement == null ? null : measurement.trim();
	}

	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo == null ? null : memo.trim();
	}

	@Override
	public int compareTo(UnitParameter o) {
		return this.getSort().compareTo(o.getSort());
	}
}