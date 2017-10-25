package com.benqzl.core;



import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.benqzl.controller.main.LoginController;
import com.benqzl.pojo.system.User;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	 private static final String[] IGNORE_URI = {"login/verify.html","login/verifyMoblie.html","checkVersion/checkUpdate.html","login/usernameBind.html","test.html","error.html"};
	    @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	        boolean flag = false;
	        String[] strings = request.getRequestURL().toString().split("qdc/"); 
	        String url=null;
	        if(strings.length==1){
	        	url="login.html";
	        }else{
	        	url=strings[1];
	        }
	        System.out.println(">>>: " + url);
	        for (String s : IGNORE_URI) {
	            if (url.contains(s)) {
	                flag = true;
	                break;
	            }
	        }
	        if (!flag) {
	            User user = LoginController.getLoginUser(request);
	            if (user != null){
	            	flag = true;
	            } 
	            else{
	            	if(request.getHeader("user-agent").contains("Mobile")){
	            		PrintWriter printWriter = response.getWriter();
		            	printWriter.write("{\"error\":true}");
		            	printWriter.flush();
	            	}else{
	            		response.sendRedirect(request.getContextPath()+"/login.html");
	            	}
	            }
	        }
	        return flag;
	    }
	 
	    @Override  
	    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {  
	    }  
	    @Override  
	    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {  
	    } 
}
