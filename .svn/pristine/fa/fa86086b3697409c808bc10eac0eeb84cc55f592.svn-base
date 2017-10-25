package com.benqzl.core;

import java.util.Map;

import com.alibaba.fastjson.JSON;

public class Pager extends BasicCore {

	/**
	 * @Fields page : TODO(当前页码)
	 */
	private int page;

	/**
	 * @Fields rows : TODO(每页行数)
	 */
	private int rows;

	/**
	 * @Fields jsonMap : TODO(分页需要两个键值对：total分页总数，rows：当前页内容)
	 */
	private Map<String, Object> jsonMap;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		page = (page == 0 ? 1 : page);
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		rows = (rows == 0 ? 1 : rows);
		this.rows = rows;
	}

	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(jsonMap);
	}
}
