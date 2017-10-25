package com.benqzl.service.production;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.production.DefectItemsMapper;
import com.benqzl.pojo.production.DefectItems;
@Service("defectItemsService")
public class DefectItemsServiceImpl implements DefectItemsService {
	@Autowired
	private DefectItemsMapper mapper;
	
	@Override
	public int insert(List<DefectItems> list) throws Exception {
		return mapper.insert(list);
	}

	@Override
	public List<DefectItems> selectByParentId(Integer parentId)
			throws Exception {
		return mapper.selectByParentId(parentId);
	}

	@Override
	public void deleteAll() throws Exception {
		mapper.deleteAll();
	}

}
