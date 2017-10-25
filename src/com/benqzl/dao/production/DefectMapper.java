package com.benqzl.dao.production;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.production.Defect;

public interface DefectMapper {
    int deleteByPrimaryKey();

    int insert(List<Defect> list);
    
    Defect selectByPrimaryKey(Integer id);
    
    List<Defect> findByPage(Map<String, Object> map);
}