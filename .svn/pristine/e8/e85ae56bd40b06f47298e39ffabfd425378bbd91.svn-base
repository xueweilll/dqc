package com.benqzl.service.system;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.system.MenuMapper;
import com.benqzl.pojo.system.Menu;
@Service("menuService")
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuMapper mapper;
	@Override
	public int deleteByPrimaryKey(Integer id) throws Exception {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Menu record) throws Exception {
		return mapper.insertSelective(record);
	}

	@Override
	public Menu selectByPrimaryKey(Integer id) throws Exception {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Menu record) throws Exception {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Menu> findByPage(Map<String, Object> map) throws Exception {
		return mapper.findByPage(map);
	}

	@Override
	public Integer pageCount(Map<String, Object> map) throws Exception {
		return mapper.pageCount(map);
	}

	@Override
	public List<Menu> selectAllByClasses(Integer classes) throws Exception {
		return mapper.selectAllByClasses(classes);
	}

}
