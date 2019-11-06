package com.wf.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTool {
//����ת�ַ����ķ���
	private static SimpleDateFormat sdf=new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static String dateToString(Date date) {
		return sdf.format(date);	
	}
//	�ַ���ת���ڵķ���
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
