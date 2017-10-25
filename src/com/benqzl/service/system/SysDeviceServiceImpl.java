package com.benqzl.service.system;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.system.SysDeviceMapper;
import com.benqzl.pojo.system.SysDevice;
@Service("sysDeviceService")
public class SysDeviceServiceImpl implements SysDeviceService {
	@Autowired
	private SysDeviceMapper mapper;
	@Override
	public List<SysDevice> findByPage(Map<String, Object> map) {
		return mapper.findByPage(map);
	}

	@Override
	public int pageCount(Map<String, Object> map) {
		return mapper.pageCount(map);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(SysDevice record) {
		return mapper.insertSelective(record);
	}

	@Override
	public SysDevice selectByPrimaryKey(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SysDevice record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public SysDevice selectByUserid(String id) {
		return mapper.selectByUserid(id);
	}

}
