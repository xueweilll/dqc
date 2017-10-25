package com.benqzl.service.production;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.production.UnitParameter;

public interface UnitParameterService {
	int deleteByPrimaryKey(String id) throws Exception;

    int insertSelective(UnitParameter record)throws Exception;

    UnitParameter selectByPrimaryKey(String id)throws Exception;

    int updateByPrimaryKeySelective(UnitParameter record)throws Exception;
    
    List<UnitParameter> findByPage(Map<String, Object> map)throws Exception;
    
    int pageCount(Map<String, Object> map)throws Exception;

	List<UnitParameter> selectAll() throws Exception;
    
    
}
