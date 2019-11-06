package com.wf.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class UploadUtils {
public static String upload(String errorPath, Part part, HttpServletRequest request, HttpServletResponse response){
	String photo = "";
//	获取文件名称
	photo = part.getSubmittedFileName();
	photo =UUID.randomUUID()+photo;//解决重名问题
//	获取上传文件的类型
	String type = photo.substring(photo.lastIndexOf(".")+1);
	if (!("jpg".equals(type)||"jpeg".equals(type)||"png".equals(type))) {
		request.setAttribute("msg","上传的文件只能上传图片类型");
		try {
			request.getRequestDispatcher(errorPath).forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		photo ="";
	}	
//	获取tomcat的根目录
	String realPath = "E:/upload";//解决了文件丢失的问题
	
	File file =new File(realPath);
	if (!file.exists()) {
		file.mkdirs();
		}
		try {
			part.write(realPath+"/"+photo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return photo;
	
}}
