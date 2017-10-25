package com.benqzl.controller.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.system.SysDevice;
import com.benqzl.pojo.system.User;
import com.benqzl.service.system.SysDeviceService;
import com.benqzl.service.system.UserService;
import com.benqzl.unit.CommonUtil;

@Controller
@RequestMapping("login")
public class LoginController extends BasicController {

	@Autowired
	private UserService service;
	@Autowired
	private SysDeviceService deviceService;

	public LoginController() {
		super(LoginController.class);
	}

	/**
	 * @Title: index
	 * @Description: TODO(主页框架)
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main/login");
		return mv;
	}
	
	@RequestMapping(value = "editpwdInfo", method = RequestMethod.GET)
	public ModelAndView editpwdInfo(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main/editpwdInfo");
		return mv;
	}

	/**
	 * @Title: verify
	 * @Description: TODO(身份驗證)
	 * @param @param username
	 * @param @param password
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "verify", method = RequestMethod.POST)
	@ResponseBody
	public String verify(String username, String password,
			HttpServletRequest request) {
		String result = "";
		try {
			User user = service.selectByName(username);
			if (user != null) {
				if (user.getAuthority() == 1) {
					if (user.getUserpwd().equals(
							CommonUtil.GetMD5Code(password))) {
						request.getSession().setAttribute("loginUser", user);
						result = "{'result':true}";
					} else {
						result = "{'result':false,'msg':'密码错误！'}";
					}
				} else {
					result = "{'result':false,'msg':'请使用超级管理员账户登录！'}";
				}
			} else {
				result = "{'result':false,'msg':'用户名不存在！'}";
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			result = "{'result':false,'msg':'用户名或密码错误！'}";
		}
		return result;
	}

	@RequestMapping(value = "verifyMoblie", method = RequestMethod.POST)
	@ResponseBody
	public String verifyMoblie(String username, String password, String uuid,
			String vendor, String model, HttpServletRequest request) {
		String result = "";
		try {
			User user = service.selectByName(username);
			if (user != null) {
				SysDevice sysDevices = new SysDevice();
				sysDevices = deviceService.selectByUserid(user.getId());
				if (sysDevices != null) {
					if (!sysDevices.getId().equals(uuid)) {
						return "{'result':false,'msg':'该账户已绑定其他设备！'}";
					}
					if (sysDevices.getAuthority().equals(new Long(1))) {
						return "{'result':false,'msg':'该设备不允许登录！'}";
					}
				} else {
					SysDevice sysDevice = new SysDevice();
					sysDevice = deviceService.selectByPrimaryKey(uuid);
					if (sysDevice != null) {
						return "{'result':false,'msg':'该设备已绑定其他账户！'}";
					} else {
						SysDevice sysDevice1 = new SysDevice();
						sysDevice1.setId(uuid);
						sysDevice1.setName(vendor);
						sysDevice1.setVersion(model);
						sysDevice1.setIsdel(new Long(0));
						sysDevice1.setAuthority(new Long(0));
						sysDevice1.setUserid(user.getId());
						deviceService.insertSelective(sysDevice1);
					}
				}
				if (user.getUserpwd().equals(CommonUtil.GetMD5Code(password))) {
					request.getSession().setAttribute("loginUser", user);
					result = "{'result':true}";
				} else {
					result = "{'result':false,'msg':'密码错误！'}";
				}
			} else {
				result = "{'result':false,'msg':'用户名不存在！'}";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result = "{'result':false,'msg':'用户名或密码错误！'}";
		}
		return result;
	}

	@RequestMapping(value = "logout", method = RequestMethod.POST)
	@ResponseBody
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("loginUser");
		return "{'result':true}";
	}

	public static User getLoginUser(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("loginUser");
		return user;
	}

	@RequestMapping(value = "editpwd", method = RequestMethod.POST)
	@ResponseBody
	public String editpwd(String oldpwd, String newpwd,HttpServletRequest request) {
		User user= (User) request.getSession().getAttribute("loginUser");
		if(user.getUserpwd().equals(CommonUtil.GetMD5Code(oldpwd))){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", user.getId());
			map.put("newpwd", CommonUtil.GetMD5Code(newpwd));
			try {
				user.setUserpwd(CommonUtil.GetMD5Code(newpwd));
				request.getSession().setAttribute("loginUser", user);
				service.editpwd(map);
				return "{'result':true,'msg':'密码修改成功！'}";
			} catch (Exception e) {
				logger.error(e.getMessage());
				return "{'result':false,'msg':'密码修改失败！'}";
			}
		}else{
			return "{'result':false,'msg':'旧密码输入有误！'}";
		}
		
	}

	@RequestMapping(value = "usernameBind", method = RequestMethod.POST)
	@ResponseBody
	public String usernameBind(String uuid) {
		String result = "";
		SysDevice sysDevice = new SysDevice();
		sysDevice = deviceService.selectByPrimaryKey(uuid);
		try {
			if (sysDevice != null) {
				User user = service.selectByPrimaryKey(sysDevice.getUserid());
				result = "{'result':true,'msg':'"+user.getUsername()+"'}";
			}else{
				result = "{'result':false,'msg':'该设备没有绑定账户'}";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result = "{'result':false,'msg':'该设备没有绑定账户'}";
		}
		return result;
	}

}
