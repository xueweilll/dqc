package com.benqzl.controller.system;

import java.lang.reflect.Type;
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
import com.benqzl.pojo.system.SysDevice;
import com.benqzl.service.system.SysDeviceService;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
/**
 * @author MJ006
 * 手机设备信息操作
 */
@Controller
@RequestMapping("sysDevice")
public class SysDeviceController extends BasicController {
	@Autowired
	private SysDeviceService service;
	
	public SysDeviceController() {
		super(SysDeviceController.class);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/system/sysDevice");
		return mv;
	}

	@RequestMapping(value = "bind", method = RequestMethod.POST)
	@ResponseBody
	public String bind(int page, int rows, String name) {
		String strJson = "";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p1", start);
		map.put("p2", rows);
		List<SysDevice> list = null;
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
		gson = new GsonBuilder().registerTypeAdapter(SysDevice.class,
				new JsonSerializer<SysDevice>() {
					@Override
					public JsonElement serialize(SysDevice arg0, Type arg1,
							JsonSerializationContext arg2) {
						JsonObject json = new JsonObject();
						json.addProperty("id", arg0.getId());
						json.addProperty("name", arg0.getName());
						json.addProperty("version", arg0.getVersion());
						json.addProperty("authority", arg0.getAuthority());
						json.addProperty("userid", arg0.getUserid());
						return json;
					}
				}).create();
		strJson = gson.toJson(jsonMap);
		return strJson;
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public String save(String id,Long authority) {
		SysDevice sysDevice = new SysDevice();
		sysDevice.setId(id);
		sysDevice.setAuthority(authority);
		try {
			service.updateByPrimaryKeySelective(sysDevice);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "{'result':false,'msg':'修改失敗！'}";
		}
		return "{'result':true}";
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(String id) {
		try {
			service.deleteByPrimaryKey(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "{'result':false,'msg':'失敗！'}";
		}
		return "{'result':true}";
	}

}
