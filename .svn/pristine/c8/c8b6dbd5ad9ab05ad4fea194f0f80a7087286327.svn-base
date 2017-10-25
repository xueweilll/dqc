package com.benqzl.service.system;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.system.Menu;

public interface MenuService {
	int deleteByPrimaryKey(Integer id) throws Exception;

    int insertSelective(Menu record)throws Exception;

    Menu selectByPrimaryKey(Integer id)throws Exception;

    int updateByPrimaryKeySelective(Menu record)throws Exception;
    
    List<Menu> findByPage(Map<String, Object> map)throws Exception;

    Integer pageCount(Map<String, Object> map)throws Exception;

    List<Menu> selectAllByClasses(Integer classes)throws Exception;

}
