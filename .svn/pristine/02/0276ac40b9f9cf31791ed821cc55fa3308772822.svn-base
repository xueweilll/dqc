package com.benqzl.controller.system;

import java.util.Date;

public class UtilData implements Comparable<UtilData>{
	private Date time;
	private String value;
	public Date getTime() {
		return time;
	}
	public void setTime(Date date) {
		this.time = date;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public int compareTo(UtilData o) {
		return this.getTime().compareTo(o.getTime());
	}
	
}
