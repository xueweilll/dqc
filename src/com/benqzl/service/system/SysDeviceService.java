package com.benqzl.service.system;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.system.SysDevice;

public interface SysDeviceService {
	List<SysDevice> findByPage(Map<String, Object> map);
	
	int pageCount(Map<String, Object> map);
    
	int deleteByPrimaryKey(String id);

    int insertSelective(SysDevice record);

    SysDevice selectByPrimaryKey(String id);
    SysDevice selectByUserid(String id);

    int updateByPrimaryKeySelective(SysDevice record);
}
