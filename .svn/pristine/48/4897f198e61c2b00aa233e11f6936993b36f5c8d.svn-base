package com.benqzl.dao.system;

import java.util.List;

import com.benqzl.pojo.system.Department;

public interface DepartmentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
    
    List<Department> selectAll();
    Department selectByName(String name);
 }