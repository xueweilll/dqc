package com.benqzl.dao.production;

import com.benqzl.pojo.production.UnitTimeDate;

public interface UnitTimeDateMapper {
    int deleteByPrimaryKey(String id);

    int insert(UnitTimeDate record);

    int insertSelective(UnitTimeDate record);

    UnitTimeDate selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UnitTimeDate record);

    int updateByPrimaryKey(UnitTimeDate record);
}