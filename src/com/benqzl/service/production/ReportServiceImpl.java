package com.benqzl.service.production;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.production.ReportMapper;
import com.benqzl.pojo.production.Report;
@Service("ReportService")
public class ReportServiceImpl implements ReportService {
  @Autowired
  public ReportMapper rptmapper;
	

	@Override
	public int insertSelective(Report record) {
		rptmapper.deleteByPrimaryKey(record.getRiqi());
		return rptmapper.insertSelective(record);
	}

}
