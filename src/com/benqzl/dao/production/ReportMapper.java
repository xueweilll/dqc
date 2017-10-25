package com.benqzl.dao.production;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.benqzl.pojo.production.Report;

public interface ReportMapper {
    int deleteByPrimaryKey(Date id);

    int insertSelective(Report record);
	/**
	 *综合报表查询功能
	 */
	List<Report> selectResultByDay(Map<String, Object> map);//日查询功能
	List<Report> selectResultByWeek(Map<String, Object> map);//周查询功能
	List<Report> selectResultByYear(Map<String, Object> map);//月查询功能
	Report selectTotalResult(Map<String, Object> map);//月或年综合查询功能
	Report selectResultByMonth(Map<String, Object> map);//月同比查询功能
}