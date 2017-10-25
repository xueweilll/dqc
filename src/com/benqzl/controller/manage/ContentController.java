package com.benqzl.controller.manage;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.manage.Content;
import com.benqzl.service.manage.ContentService;
import com.benqzl.unit.CommonUtil;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
@Controller
@RequestMapping("content")
public class ContentController extends BasicController{
	@Autowired
	private ContentService service;
	
	public ContentController() {
		super(ContentController.class);
	}
	
	@RequestMapping(value="bind",method=RequestMethod.POST)
	@ResponseBody
	public String bind(int page,int rows,String sort){
		Map<String, Object> map = new HashMap<String, Object>();
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		//rows = start+rows;
		logger.info("this page rows is " + page + "|" + rows);
		map.put("p1", start);
		map.put("p2", rows);
		map.put("sort", sort);
		List<Content> contents = new ArrayList<>();
		try {
			contents=service.findByType(map);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		gson = new GsonBuilder().registerTypeAdapter(Content.class, new JsonSerializer<Content>() {
			@Override
			public JsonElement serialize(Content arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject json = new JsonObject();
				json.addProperty("id", arg0.getId());
				json.addProperty("title1", arg0.getTitle1());
				json.addProperty("cdate", datetimeFormat.format(arg0.getCdate()));
				return json;
			}
		}).create();
		String jsonStr = gson.toJson(contents);
		return jsonStr;
	}
	@RequestMapping(value="coutentInfo",method=RequestMethod.POST)
	@ResponseBody
	public String coutentInfo(String id,final HttpServletRequest request){
		
		Content content = new Content();
		try {
			content = service.selectByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		gson = new GsonBuilder().registerTypeAdapter(Content.class, new JsonSerializer<Content>() {
			@Override
			public JsonElement serialize(Content arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject json = new JsonObject();
				json.addProperty("id", arg0.getId());
				json.addProperty("content", CommonUtil.stripHtml(arg0.getContent(),request.getLocalAddr()));
				json.addProperty("title1", arg0.getTitle1());
				json.addProperty("title2", arg0.getTitle2());
				json.addProperty("color", arg0.getColor());
				json.addProperty("name", arg0.getName());
				json.addProperty("viewnum", arg0.getViewnum());
				json.addProperty("ding", arg0.getDing());
				json.addProperty("cdate", datetimeFormat.format(arg0.getCdate()));
				return json;
			}
		}).create();
		return gson.toJson(content);
	}
}
