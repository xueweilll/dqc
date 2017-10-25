package com.benqzl.controller.system;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
import com.benqzl.pojo.system.Menu;
import com.benqzl.service.system.MenuService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
@Controller
@RequestMapping("menu")
public class MenuController extends BasicController{

	public MenuController() {
		super(MenuController.class);
	}
	
	@Autowired
	private MenuService service;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/system/menu");
		return mv;
	}
	
	@RequestMapping(value = "menuInfo", method = RequestMethod.GET)
	public ModelAndView menuInfo(Integer id) {
		ModelAndView mv = new ModelAndView();
		Menu menu = new Menu();
		if(id!=0){
			try {
				menu= service.selectByPrimaryKey(id);
				mv.addObject("menu", menu);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}
		mv.setViewName("/system/menuInfo");
		mv.addObject("id", id);
		return mv;
	}
	
	@RequestMapping(value = "bind", method = RequestMethod.POST)
	@ResponseBody
	public String bind(int page, int rows,String starttime,
			String endtime,String classes) {
		String strJson = "";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p1", start);
		map.put("p2", rows);
		if (starttime == null || starttime.trim().equals("")) {
			map.put("starttime", null);
		} else {
			try {
				map.put("starttime", datetimeFormat.parseObject(starttime));
			} catch (ParseException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}
		if (classes == null || classes.trim().equals("")) {
			map.put("classes", null);
		} else {
			map.put("classes", Integer.parseInt(classes));
		}
		if (endtime == null || endtime.trim().equals("")) {
			map.put("endtime", null);
		} else {
			try {
				map.put("endtime", datetimeFormat.parseObject(endtime));
			} catch (ParseException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}
		List<Menu> list = null;
		int count = 0;
		try {
			list = service.findByPage(map);
			count = service.pageCount(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", count);
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(Menu.class,
				new JsonSerializer<Menu>() {
					@Override
					public JsonElement serialize(Menu arg0, Type arg1,
							JsonSerializationContext arg2) {
						JsonObject json = new JsonObject();
						json.addProperty("id", arg0.getId());
						json.addProperty("name", arg0.getName());
						json.addProperty("url", arg0.getUrl());
						json.addProperty("classes", arg0.getClasses());
						json.addProperty("createtime",
								datetimeFormat.format(arg0.getCratetime()));
						json.addProperty("edittime",
								datetimeFormat.format(arg0.getEdittime()));
						return json;
					}
				}).create();
		strJson = gson.toJson(jsonMap);
		return strJson;
	}
	
	@RequestMapping(value = "menuBind", method = RequestMethod.GET)
	@ResponseBody
	public String menuBind(String classes) {
		String strJson = "";
		List<Menu> list = new ArrayList<>();
		try {
			list = service.selectAllByClasses(Integer.parseInt(classes));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		gson = new GsonBuilder().registerTypeAdapter(Menu.class,
				new JsonSerializer<Menu>() {
					@Override
					public JsonElement serialize(Menu arg0, Type arg1,
							JsonSerializationContext arg2) {
						JsonObject json = new JsonObject();
						json.addProperty("id", arg0.getId());
						json.addProperty("name", arg0.getName());
						json.addProperty("url", arg0.getUrl());
						return json;
					}
				}).create();
		strJson = gson.toJson(list);
		return strJson;
	}
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public String save(String jsonStr) {
		Gson gson = new Gson();
		Menu menu = gson.fromJson(jsonStr, Menu.class);
		String result="";
		try {
			if (menu.getId()==0) {//新增
				menu.setCratetime(new Date());
				menu.setEdittime(new Date());
				service.insertSelective(menu);
			} else {//修改
				menu.setEdittime(new Date());
				service.updateByPrimaryKeySelective(menu);
			}
			result= "{'result':true}";
		} catch (Exception e) {
			e.printStackTrace();
			result= "{'result':false,'msg':'保存失败'}";
		}

		return result;
	}
	
	/** 
	* @Title: delete 
	* @Description: TODO(删除) 
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(int id){
		String result="";
		try {
			service.deleteByPrimaryKey(id);
			result= "{'result':true,'msg':'删除成功'}";
		} catch (Exception e) {
			e.printStackTrace();
			result= "{'result':false,'msg':'删除失败'}";
		}
		return result;
	}
}
