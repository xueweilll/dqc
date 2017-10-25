package com.benqzl.dao.system;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.system.Menu;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);
    
    List<Menu> findByPage(Map<String, Object> map);
    
    Integer pageCount(Map<String, Object> map);
    
    List<Menu> selectAllByClasses(Integer classes);
}