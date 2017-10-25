package com.benqzl.dao.system;

import com.benqzl.pojo.system.SystemTime;

public interface SystemTimeMapper {
    int deleteByPrimaryKey(String id);

    int insert(SystemTime record);

    int insertSelective(SystemTime record);

    SystemTime selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemTime record);

    int updateByPrimaryKey(SystemTime record);
}