package com.benqzl.service.production;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.production.UnitHisTimeDateMapper;
import com.benqzl.pojo.production.UnitHisTimeDate;
@Service("unitHisTimeDateService")
public class UnitHisTimeDateServiceImpl implements UnitHisTimeDateService {
	@Autowired
	private UnitHisTimeDateMapper mapper;


	@Override
	public int insert(List<UnitHisTimeDate> record) {
		int result = 0;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			long long2 = new Date().getTime()-(1000*60*180);
			Date date1=new Date(long2);
			map.put("endTime", date1);
			mapper.deleteByPrimaryKey(map);
			result = mapper.insert(record);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<UnitHisTimeDate> selectByPrimaryKey(String id) {
		boolean sysResult=false;
		switch (id) {
		case "46664689-a550-4ee0-ab63-05587f0e538e":
			sysResult=true;
			break;
		case "e3476aa7-e0a2-4aa5-a13a-e7c9ea6ca747":
			sysResult=true;
			break;
		case "ea960703-821e-4ff1-b704-64787ddf80ec":
			sysResult=true;
			break;
		case "fcf32bb0-20b9-4d43-8fa0-bcbd7fc16875":
			sysResult=true;
			break;
		case "f28a17e7-82a2-48aa-8b5e-7cb04b24f9b4":
			sysResult=true;
			break;
		case "07cc63f8-50f7-4489-8403-a2c69b63fa7a":
			sysResult=true;
			break;
		default:
			sysResult=false;
			break;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		long long1;
		if(sysResult){
			long1 = new Date().getTime()-(1000*60*120);
		}else{
			long1 = new Date().getTime()-(1000*60*60);
		}
		Date date=new Date(long1);
		map.put("startTime",date);
		map.put("endTime", new Date());
		map.put("id", id);
		return mapper.selectByPrimaryKey(map);
	}

	@Override
	public int deleteByAll() throws Exception {
		return mapper.deleteByAll();
	}

}
