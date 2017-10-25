package com.benqzl.service.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.system.SysParameterMapper;
import com.benqzl.pojo.system.SysParameter;

@Service("sysParmeterService")
public class SysParameterServiceImpl implements SysParameterService{
	@Autowired
	private SysParameterMapper mapper;
	@Override
	public List<SysParameter> selectAll() throws Exception {
		return mapper.selectAll();
	}

	@Override
	public SysParameter selectByPrimaryKey(String id) throws Exception {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SysParameter record)
			throws Exception {
		return mapper.updateByPrimaryKeySelective(record);
	}

}
