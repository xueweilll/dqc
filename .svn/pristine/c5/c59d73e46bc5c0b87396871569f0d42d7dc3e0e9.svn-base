package com.benqzl.service.manage;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.manage.OAContentMapper;
import com.benqzl.pojo.manage.OAContent;

@Service("oaContentService")
public class OAContentServiceImpl implements OAContentService {
	@Autowired
	private OAContentMapper mapper;
	@Override
	public int insert(List<OAContent> list) throws Exception {
		int result=0;
		try {
			result=mapper.insert(list);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Override
	public OAContent selectByPrimaryKey(String id) throws Exception {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<OAContent> selectByAll(Map<String, Object> map)
			throws Exception {
		return mapper.selectByAll(map);
	}

}
