package com.benqzl.service.production;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.production.Report;

public interface ReportQueryService {
	/**
	 *综合报表查询功能
	 */
	List<Report> selectResultByDay(Map<String, Object> map) throws Exception;//日查询功能
	List<Report> selectResultByWeek(Map<String, Object> map) throws Exception;//周查询功能
	List<Report> selectResultByYear(Map<String, Object> map) throws Exception;//月查询功能
	Map<String,Object> selectTotalResult(String dateTime) throws Exception;//月或年综合查询功能
	Map<String,Object> selectResultByMonth(String dateTime,int type) throws Exception;
	int insert(Report report);
}
