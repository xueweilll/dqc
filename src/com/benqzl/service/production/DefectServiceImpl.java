package com.benqzl.service.production;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.production.DefectMapper;
import com.benqzl.pojo.production.Defect;

@Service("defectService")
public class DefectServiceImpl implements DefectService {
	@Autowired
	private DefectMapper mapper;
	@Override
	public int insert(List<Defect> list) throws Exception {
		mapper.deleteByPrimaryKey();
		return mapper.insert(list);
	}
	@Override
	public Defect selectByPrimaryKey(Integer id) throws Exception {
		return mapper.selectByPrimaryKey(id);
	}
	@Override
	public List<Defect> findByPage(Map<String, Object> map) throws Exception {
		return mapper.findByPage(map);
	}

}
