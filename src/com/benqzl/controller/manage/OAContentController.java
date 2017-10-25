package com.benqzl.controller.manage;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.manage.OAContent;
import com.benqzl.pojo.production.UnitHisTimeDate;
import com.benqzl.service.manage.OAContentService;
import com.benqzl.service.production.UnitHisTimeDateService;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
@Controller
@RequestMapping("oaContent")
public class OAContentController extends BasicController {
	@Autowired
	private OAContentService service;
	public OAContentController() {
		super(OAContentController.class);
	}
	
	@RequestMapping(value="bind",method=RequestMethod.POST)
	@ResponseBody
	public String bind(int page,int rows){
		Map<String, Object> map = new HashMap<String, Object>();
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		//rows = start+rows;
		logger.info("this page rows is " + page + "|" + rows);
		map.put("p1", start);
		map.put("p2", rows);
		List<OAContent> contents = new ArrayList<>();
		try {
			contents=service.selectByAll(map);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		gson = new GsonBuilder().registerTypeAdapter(OAContent.class, new JsonSerializer<OAContent>() {
			@Override
			public JsonElement serialize(OAContent arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject json = new JsonObject();
				json.addProperty("id", arg0.getId());
				json.addProperty("title", arg0.getTitle());
				json.addProperty("cdate", dateFormat.format(arg0.getFbri()));
				return json;
			}
		}).create();
		String jsonStr = gson.toJson(contents);
		return jsonStr;
	}
	
	@RequestMapping(value="oaCoutentInfo",method=RequestMethod.POST)
	@ResponseBody
	public String oaCoutentInfo(String id,final HttpServletRequest request){
		
		OAContent content = new OAContent();
		try {
			content = service.selectByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		gson = new GsonBuilder().registerTypeAdapter(OAContent.class, new JsonSerializer<OAContent>() {
			@Override
			public JsonElement serialize(OAContent arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject json = new JsonObject();
				json.addProperty("id", arg0.getId());
				json.addProperty("content", arg0.getContent().replaceAll("style=\"[^>]*?\"", "\"\""));
				json.addProperty("title", arg0.getTitle());
				json.addProperty("name", arg0.getName());
				json.addProperty("fbdw", arg0.getFbdw());
				json.addProperty("reads", arg0.getReads());
				json.addProperty("fbri", dateFormat.format(arg0.getFbri()));
				json.addProperty("yxri", dateFormat.format(arg0.getYxri()));
				return json;
			}
		}).create();
		return gson.toJson(content);
	}
	@Autowired
	private UnitHisTimeDateService dateService;
	@RequestMapping(value="save",method=RequestMethod.POST)
	@ResponseBody
	public String save(){
		List<UnitHisTimeDate> dates = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			UnitHisTimeDate date = new UnitHisTimeDate();
			date.setId(UUID.randomUUID().toString());
			date.setUnitid("053d11d5-7a97-4e48-9179-a1b7a75754b1");
			date.setCdate(new Date());
			date.setPvalue(new Float(1.1));
			dates.add(date);
		}
		UnitHisTimeDate date = new UnitHisTimeDate();
		dates.add(date);
		try {
			dateService.insert(dates);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
