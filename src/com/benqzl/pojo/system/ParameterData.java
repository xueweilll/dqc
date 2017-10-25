package com.benqzl.pojo.system;

import java.util.List;

public class ParameterData {
	private List<String> dateList; //X轴显示数据 
	private List<String> valueList;//y轴显示数据 
	public List<String> getDateList() {
		return dateList;
	}
	public void setDateList(List<String> dateList) {
		this.dateList = dateList;
	}
	public List<String> getValueList() {
		return valueList;
	}
	public void setValueList(List<String> valueList) {
		this.valueList = valueList;
	}
}
