package com.benqzl.controller.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.benqzl.controller.BasicController;
/**
 * @author MJ006
 *  版本控制
 */
@Controller
@RequestMapping("checkVersion")
public class CheckVersionController extends BasicController {

	public CheckVersionController() {
		super(CheckVersionController.class);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/checkVersion");
		return mv;
	}
	
	@RequestMapping(value = "checkVersionInfo", method = RequestMethod.GET)
	public ModelAndView checkVersionInfo(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/checkVersionInfo");
		return mv;
	}
	
	@RequestMapping(value = "bind", method = RequestMethod.POST)
	@ResponseBody
	public String bind(HttpServletRequest request){
		String path=request.getSession().getServletContext().getRealPath("/") +"upload/softversion/version.txt";
		File file = new File(path);
		FileReader fileReader = null;
		BufferedReader reader = null;
		String jsonResult=null;
		try {
			fileReader = new FileReader(file);
			reader = new BufferedReader(fileReader);
			jsonResult=reader.readLine().toString();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				reader.close();
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String result = "{\"total\":1,\"rows\":["+jsonResult+"]}";
		return result;
	}
	@RequestMapping(value = "checkUpdate", method = RequestMethod.POST)
	@ResponseBody
	public String checkUpdate(String version,HttpServletRequest request){
		String path=request.getSession().getServletContext().getRealPath("/") +"upload/softversion/version.txt";
		File file = new File(path);
		FileReader fileReader = null;
		BufferedReader reader = null;
		String jsonResult=null;
		try {
			fileReader = new FileReader(file);
			reader = new BufferedReader(fileReader);
			jsonResult=reader.readLine().toString();
			JSONObject object = JSONObject.parseObject(jsonResult);
			if(!object.getString("version").equals(version)&&version!=null){
				jsonResult= "{'result':false,'msg':'存在新版本！'}";
			}else{
				jsonResult= "{'result':true,'msg':'已经是最新版本！'}";
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				reader.close();
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return jsonResult;
	}
}
