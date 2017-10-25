package com.benqzl.controller.system.parameter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.benqzl.controller.BasicController;
@Controller
@RequestMapping("paramers")
public class ParametersController extends BasicController{
	public ParametersController() {
		super(ParametersController.class);		
	}	
	@RequestMapping(value="authority",method=RequestMethod.POST)
	@ResponseBody
	public String authority(){
		String json = "[{\"id\":\"0\",\"text\":\"普通用户\"},{\"id\":\"1\",\"text\":\"超级管理员\"}]";
		return json;
	}
}
