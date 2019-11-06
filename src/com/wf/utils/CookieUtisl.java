package com.wf.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtisl {
		//生成cookie的方法
	public static void addCookie(String search,HttpServletRequest request, HttpServletResponse response) {
		//获取cookie中的内容
		String info = getCookieInfo(request);
		
		if ("".equals(info)) {
			info=search;
		} else {
			//不存重的数据  
			if (!info.contains(search)) {
				//判断是否包含#
			boolean isContains = info.contains("#");
			if (isContains) {
				String[] split = info.split("#");
			//只能存三个数据
				if (split.length==3) {
					info=split[1]+split[2];
			}	
		
				}
				
			info=info+"#"+search;
		}
	}
		Cookie cookie=new Cookie("search",info);
		response.addCookie(cookie);
	}
	//获取cookie中内容的方法
	public static String getCookieInfo(HttpServletRequest request) {
		String info="";
		Cookie[] cookies = request.getCookies();
		//非空判断
		if (!(cookies==null||cookies.length==0)) {
		for (Cookie cookie : cookies) {
			if ("search".equals(cookie.getName())) {
				info=cookie.getValue();
			}
		}
		} 
		return info;
	}
}
