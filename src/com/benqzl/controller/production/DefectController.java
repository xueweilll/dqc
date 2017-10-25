package com.benqzl.controller.production;

/*import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;*/
import java.lang.reflect.Type;
/*import java.text.ParseException;*/
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




/*import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;*/
import com.benqzl.controller.BasicController;
import com.benqzl.pojo.production.Defect;
import com.benqzl.pojo.production.DefectItems;
import com.benqzl.service.production.DefectItemsService;
import com.benqzl.service.production.DefectService;
import com.benqzl.unit.CommonUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
@Controller
@RequestMapping("defect")
public class DefectController extends BasicController {
	@Autowired
	private DefectService service;
	
	public DefectController() {
		super(DefectController.class);
	}
	
	@RequestMapping(value="bind",method=RequestMethod.POST)
	@ResponseBody
	public String bind(int page,int rows,String unit,String fenlei){
		Map<String, Object> map = new HashMap<String, Object>();
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		logger.info("this page rows is " + page + "|" + rows);
		map.put("p1", start);
		map.put("p2", rows);
		map.put("unit", unit);
		List<Defect> defects = new ArrayList<>();
		try {
			defects=service.findByPage(map);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		gson = new GsonBuilder().registerTypeAdapter(Defect.class, new JsonSerializer<Defect>() {
			@Override
			public JsonElement serialize(Defect arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject json = new JsonObject();
				json.addProperty("id", arg0.getId());
				if(arg0.getTitle().length()>15){
					json.addProperty("title", arg0.getTitle().substring(0, 15)+"...");
				}else{
					json.addProperty("title", arg0.getTitle());
				}
				json.addProperty("zrbm", arg0.getZrbm());
				json.addProperty("starttime", dateFormat.format(arg0.getStarttime()));
				return json;
			}
		}).create();
		String jsonStr = gson.toJson(defects);
		return jsonStr;
	}
	
	@Autowired
	private DefectItemsService defectItemsService;
	@RequestMapping(value="defectInfo",method=RequestMethod.POST)
	@ResponseBody
	public String coutentInfo(String id,final HttpServletRequest request){
		
		Defect defect = new Defect();
		List<DefectItems> defectItems = new ArrayList<>();
		try {
			defect = service.selectByPrimaryKey(Integer.parseInt(id));
			defectItems = defectItemsService.selectByParentId(Integer.parseInt(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String,Object> jsonMap = new HashMap<>();
		gson = new GsonBuilder().registerTypeAdapter(Defect.class, new JsonSerializer<Defect>() {
			@Override
			public JsonElement serialize(Defect arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject json = new JsonObject();
				json.addProperty("title", arg0.getTitle());
				json.addProperty("zrbm", arg0.getZrbm());
				json.addProperty("bh", arg0.getBh());
				json.addProperty("state", arg0.getState());
				json.addProperty("fenlei", arg0.getFenlei());
				json.addProperty("unit", arg0.getUnit());
				if(arg0.getMemo()!=null){
					json.addProperty("memo", CommonUtil.delHTMLTag(arg0.getMemo()));
				}
				json.addProperty("starttime", datetimeFormat.format(arg0.getStarttime()));
				json.addProperty("endtime", datetimeFormat.format(arg0.getEndtime()));
				return json;
			}
		}).create();
		jsonMap.put("defectInfo",gson.toJson(defect));
		gson = new GsonBuilder().registerTypeAdapter(DefectItems.class, new JsonSerializer<DefectItems>() {
			@Override
			public JsonElement serialize(DefectItems arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject json = new JsonObject();
				json.addProperty("username", arg0.getUsername());
				json.addProperty("memo", arg0.getMemo());
				json.addProperty("rectime", datetimeFormat.format(arg0.getRectime()));
				json.addProperty("bm", arg0.getBm());
				json.addProperty("op", arg0.getOp());
				return json;
			}
		}).create();
		jsonMap.put("defectItemsInfo",gson.toJson(defectItems));
		gson = new Gson();
		System.out.println(gson.toJson(jsonMap));
		return gson.toJson(jsonMap);
	}
	/*@RequestMapping(value="defectTest",method=RequestMethod.POST)
	@ResponseBody
	public String defectTest(HttpServletRequest request){
		String path=request.getSession().getServletContext().getRealPath("/") +"upload/softversion/test.txt";
		File file = new File(path);
		//FileReader fileReader = null;
		InputStreamReader isr = null;
		BufferedReader reader = null;
		String jsonResult=null;
		try {
			//fileReader = new FileReader(file);
			isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
			reader = new BufferedReader(isr);
			StringBuffer sb = new StringBuffer();
			String string=null;
			while((string=reader.readLine().toString())!=null){
				sb.append(string);
			}
			String string=reader.readLine();
			System.out.println(string);
			JSONObject obj = JSONObject.parseObject(string);
			// obj = obj.getJSONObject("data");
			JSONArray arr = obj.getJSONArray("data");
			List<Defect> defects = new ArrayList<>();
			for (int j = 0; j < arr.size(); j++) {
				JSONObject o = arr.getJSONObject(j);
				Defect defect = new Defect();
				defect.setId(o.getInteger("id"));
				try {
					defect.setStarttime(datetimeFormat.parse(o.getString("starttime")));
					defect.setEndtime(datetimeFormat.parse(o.getString("endtime")));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				defect.setBh(o.getString("bh"));
				defect.setFenlei(o.getString("fenlei"));
				defect.setMemo(o.getString("memo"));
				defect.setState(o.getString("state"));
				defect.setTitle(o.getString("title"));
				defect.setUnit("aaaa");
				defect.setZrbm(o.getString("zrbm"));
				defect.setOmit(o.getString("omit"));
				defects.add(defect);
			}
			try {
				service.insert(defects);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				reader.close();
				isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return jsonResult;
	}*/

}
