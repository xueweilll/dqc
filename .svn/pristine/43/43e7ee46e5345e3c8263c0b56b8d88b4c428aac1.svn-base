package com.benqzl.service.manage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.manage.WatchMapper;
import com.benqzl.pojo.manage.Watch;
@Service("watchService")
public class WatchServiceImpl implements WatchService{
	@Autowired
	private WatchMapper mapper;
	@Override
	public int insert(List<Watch> record) throws Exception {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		mapper.deleteByPrimaryKey(dateformat.format(new Date()));
		return mapper.insert(record);
	}

	@Override
	public List<Watch> findByPage(Map<String, Object> map) throws Exception {
		return mapper.findByPage(map);
	}

}
