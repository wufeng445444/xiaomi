package com.wf.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtisl {
		//����cookie�ķ���
	public static void addCookie(String search,HttpServletRequest request, HttpServletResponse response) {
		//��ȡcookie�е�����
		String info = getCookieInfo(request);
		
		if ("".equals(info)) {
			info=search;
		} else {
			//�����ص�����  
			if (!info.contains(search)) {
				//�ж��Ƿ����#
			boolean isContains = info.contains("#");
			if (isContains) {
				String[] split = info.split("#");
			//ֻ�ܴ���������
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
	//��ȡcookie�����ݵķ���
	public static String getCookieInfo(HttpServletRequest request) {
		String info="";
		Cookie[] cookies = request.getCookies();
		//�ǿ��ж�
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
