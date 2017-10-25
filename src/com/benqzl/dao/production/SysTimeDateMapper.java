package com.benqzl.dao.production;

import com.benqzl.pojo.production.SysTimeDate;

public interface SysTimeDateMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysTimeDate record);

    int insertSelective(SysTimeDate record);

    SysTimeDate selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysTimeDate record);

    int updateByPrimaryKey(SysTimeDate record);
}