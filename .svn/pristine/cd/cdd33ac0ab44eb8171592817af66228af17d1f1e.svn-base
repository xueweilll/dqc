package com.benqzl.text;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.benqzl.pojo.production.DefectItems;
//import com.benqzl.service.production.DefectItemsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:spring.xml","classpath:spring-mybatis.xml"})
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class TestStationAlarm {

	/*@Autowired
	private  DefectItemsService itemsService;*/
	@Test
	public void subflowIsOverTest(){
		try {
			List<DefectItems> defectItems = new ArrayList<>();
			for (int j = 0; j < 20; j++) {
				DefectItems items = new DefectItems();
				items.setId(j+100);
				items.setParentid(54);
				items.setOp(j+"状态");
				items.setUsername(j+"接收人");
				items.setRectime(new Date());
				items.setIp(j+"ip");
				//items.setBm(j+"部门");
				items.setMemo(j+"备注");
				defectItems.add(items);
			}
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			String str=gson.toJson(defectItems);
			JSONArray arr = JSONArray.parseArray(str);
			for (int j = 0; j < arr.size(); j++) {
				JSONObject o = arr.getJSONObject(j);
				DefectItems items = new DefectItems();
				items.setId(o.getInteger("id"));
				items.setParentid(o.getInteger("parentid"));
				items.setOp(o.getString("op"));
				items.setUsername(o.getString("username"));
				items.setRectime(o.getDate("rectime"));
				items.setIp(o.getString("ip"));
				items.setBm(o.getString("bm"));
				items.setMemo(o.getString("memo"));
				System.out.println(items.getBm());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
