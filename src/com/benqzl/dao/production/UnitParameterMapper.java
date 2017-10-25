package com.benqzl.dao.production;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.production.UnitParameter;

public interface UnitParameterMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective(UnitParameter record);

    UnitParameter selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UnitParameter record);
    
    List<UnitParameter> findByPage(Map<String, Object> map);
    
    List<UnitParameter> selectAll();
    
    int pageCount(Map<String, Object> map);

}