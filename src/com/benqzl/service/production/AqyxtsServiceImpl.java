package com.benqzl.service.production;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.production.AqyxtsMapper;
import com.benqzl.pojo.production.Aqyxts;
@Service("aqyxtsService")
public class AqyxtsServiceImpl implements AqyxtsService {
	@Autowired
	private AqyxtsMapper mapper;
	@Override
	public int update(Aqyxts record) {
		return mapper.updateByPrimaryKeySelective(record);
	}


	@Override
	public Aqyxts selectByPrimaryKey() {
		return mapper.selectByPrimaryKey();
	}

}
