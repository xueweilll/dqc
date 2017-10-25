package com.benqzl.service.manage;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.manage.ContentMapper;
import com.benqzl.pojo.manage.Content;
@Service("contentService")
public class ContentServiceImpl implements ContentService{
	@Autowired
	private ContentMapper mapper;

	@Override
	public List<Content> findByType(Map<String, Object> map) throws Exception {
		return mapper.findByType(map);
	}

	@Override
	public int insert(List<Content> record) throws Exception {
		mapper.deleteByPrimaryKey(record.get(0).getSort());
		return mapper.insert(record);
	}

	@Override
	public Content selectByPrimaryKey(String id) throws Exception {
		return mapper.selectByPrimaryKey(id);
	}

}
