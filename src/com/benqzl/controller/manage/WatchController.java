package com.benqzl.controller.manage;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.manage.Watch;
import com.benqzl.service.manage.WatchService;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
@Controller
@RequestMapping("watch")
public class WatchController extends BasicController {

	public WatchController() {
		super(WatchController.class);
	}
	@Autowired
	private WatchService service;
	
	@RequestMapping(value="bind",method=RequestMethod.POST)
	@ResponseBody
	public String bind(int page,int rows,String startTime,String endTime){
		Map<String, Object> map = new HashMap<String, Object>();
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		//rows = start+rows;
		logger.info("this page rows is " + page + "|" + rows);
		map.put("p1", start);
		map.put("p2", rows);
		if(startTime!=null&&!startTime.trim().equals("")){
			try {
				map.put("startTime", dateFormat.parseObject(startTime));
			} catch (ParseException e) {
				logger.error(e.getMessage());
			}
		}else{
			map.put("startTime", new Date());
		}
		if(endTime!=null&&!endTime.trim().equals("")){
			try {
				map.put("endTime", dateFormat.parseObject(endTime));
			} catch (ParseException e) {
				logger.error(e.getMessage());
			}
		}else{
			map.put("endTime", null);
		}
		List<Watch> watchs = null;
		try {
			watchs = service.findByPage(map);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		gson = new GsonBuilder().registerTypeAdapter(Watch.class, new JsonSerializer<Watch>() {
			@Override
			public JsonElement serialize(Watch arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject json = new JsonObject();
				json.addProperty("id", arg0.getId());
				json.addProperty("bm", arg0.getBm());
				json.addProperty("riqi", dateFormat.format(arg0.getRiqi()));
				json.addProperty("renyuan", arg0.getRenyuan());
				return json;
			}
		}).create();
		String jsonStr = gson.toJson(watchs);
		return jsonStr;
	}
	

}
