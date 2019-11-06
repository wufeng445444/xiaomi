package com.wf.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTool {
//日期转字符串的方法
	private static SimpleDateFormat sdf=new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static String dateToString(Date date) {
		return sdf.format(date);	
	}
//	字符串转日期的方法
	public static Date stringToDate(String dateStr) {
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
	}
	
}
