package com.nms.corba.ninterface.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
	public static int compare_date(String beginTime, String endTime) {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			Date beginDate = df.parse(beginTime);
			Date endDate = df.parse(endTime);
			if (beginDate.getTime() > endDate.getTime()) {
				System.out.println("beginTime 在endTime前");
				return -1;
			} else if (beginDate.getTime() < endDate.getTime()) {
				System.out.println("endTime在beginTime后");
				return 1;
			} else {
				return 0;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}
	
	//转换yyyyMMddhhmmss 到yyyy-MM-dd hh:mm:ss
	public static String dateFormat(String time) {
		String str = null;
		if(time.length()!=14){
			return str;
		}
		if(!time.matches("^[0-9]*$")){
			return str;
		}
		try {
			DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = df.parse(time);
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			str = df.format(date);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return str;
	}
	/**
	 *  转换yyyy-MM-dd hh:mm:ss 到yyyyMMddhhmmss
	 * @param time
	 * @param format
	 * 		传人的时间 格式："yyyy-MM-dd hh:mm:ss"
	 * @return
	 */
	public static String converCorbaTime(String time,String format) {
		String str = null;
		
		try {
			DateFormat df = new SimpleDateFormat(format);
			Date date = df.parse(time);
			df = new SimpleDateFormat("yyyyMMddHHmmss");
			str = df.format(date);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return str;
	}
	
}
