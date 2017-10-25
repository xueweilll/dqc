package com.benqzl.controller.system;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.system.Systimedate;
import com.benqzl.pojo.system.Unittimedate;

import com.benqzl.service.system.TimedateService;



import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

@Controller
@RequestMapping("timedata")
public class TimeDataController extends BasicController{

	@Autowired
	private TimedateService service;
	public TimeDataController() {
		super(TimeDataController.class);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/system/timedata");
		return mv;
	}
		
	@RequestMapping(value = "systimedatebind", method = RequestMethod.GET)		
	@ResponseBody
	public String systimedatebind(){
		List<Systimedate> list = new ArrayList<>();
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("ptype", null);
			list=service.findbypage(map);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		gson = new GsonBuilder().registerTypeAdapter(Systimedate.class, new JsonSerializer<Systimedate>() {
			@Override
			public JsonElement serialize(Systimedate arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject json = new JsonObject();
				json.addProperty("id", arg0.getId());
				json.addProperty("sysid", arg0.getSysid());
				json.addProperty("cdate", datetimeFormat.format(arg0.getCdate()));
				json.addProperty("pvalue", arg0.getPvalue());
				json.addProperty("name", arg0.getSysparameter().getName());
				json.addProperty("pkey", arg0.getSysparameter().getPkey());
				json.addProperty("memo", arg0.getSysparameter().getMemo());
				json.addProperty("rating", arg0.getSysparameter().getRating());
				return json;
			}
		}).create();
		String jsonStr = null;
		jsonStr = gson.toJson(list);
		return jsonStr;
	}

	@RequestMapping(value = "unittimedatebind", method = RequestMethod.GET)
	@ResponseBody
	public String unittimedatebind(){
		List<Unittimedate> list = new ArrayList<>();
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("ptype", null);
			list=service.findUnittimedataBypage(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		gson = new GsonBuilder().registerTypeAdapter(Unittimedate.class, new JsonSerializer<Unittimedate>() {
			@Override
			public JsonElement serialize(Unittimedate arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject json = new JsonObject();
				json.addProperty("id", arg0.getId());
				json.addProperty("unitid", arg0.getUnitid());
				json.addProperty("cdate", datetimeFormat.format(arg0.getCdate()));
				json.addProperty("pvalue", arg0.getPvalue());
				json.addProperty("name", arg0.getUnitparameter().getName());
				json.addProperty("pkey", arg0.getUnitparameter().getPkey());
				json.addProperty("memo", arg0.getUnitparameter().getMemo());
				json.addProperty("measurement", arg0.getUnitparameter().getMeasurement());
				json.addProperty("sysid", arg0.getUnitparameter().getSysid());
				json.addProperty("isdel", arg0.getUnitparameter().getIsdel());
				return json;
			}
		}).create();
		String jsonStr = null;
		jsonStr = gson.toJson(list);
		return jsonStr;
	}



}
