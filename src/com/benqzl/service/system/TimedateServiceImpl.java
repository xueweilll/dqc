package com.benqzl.service.system;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.system.TimeDataMapper;
import com.benqzl.pojo.system.Systimedate;
import com.benqzl.pojo.system.Unittimedate;
@Service ("timedataService")
public class TimedateServiceImpl implements TimedateService {
	@Autowired	
	TimeDataMapper mapper;
	@Override
	public List<Systimedate> findbypage(Map<String, Object> map) {		
		return mapper.findbypage(map);
	}
	@Override
	public List<Unittimedate> findUnittimedataBypage(Map<String, Object> map) {
		return mapper.findUnittimedataBypage(map);
	}
	@Override
	public void insertSystimedateList(List<Systimedate> systimedates) {
		mapper.insertSystimedateList(systimedates);
	}
	@Override
	public void insertUnittimedateList(List<Unittimedate> unittimedates) {
		mapper.insertUnittimedateList(unittimedates);
	}
	
}
