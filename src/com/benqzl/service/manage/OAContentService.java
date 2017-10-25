package com.benqzl.service.manage;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.manage.OAContent;

public interface OAContentService {
	
	int insert(List<OAContent> list) throws Exception;

	OAContent selectByPrimaryKey(String id) throws Exception;

	List<OAContent> selectByAll(Map<String, Object> map) throws Exception;
}
