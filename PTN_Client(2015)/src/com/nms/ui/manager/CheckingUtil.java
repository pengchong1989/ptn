package com.nms.ui.manager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式验证辅助类
 * @author kk
 *
 */
public class CheckingUtil {
	
	/**
	 * 正则表达式，特殊字符
	 */
	public static final String SPECIAL_REGULAR="[`~%!@#^=''?~！@#￥……&——‘”“'？*()（），,。、 ]";
	/**
	 * 正则表达式，必须是数字
	 */
	public static final String NUMBER_REGULAR="^[0-9_]+$";
	
	public static final String NUMBER_NUM="^(-)?[0-9][0-9]*$";
	/**
	 * 正则表达式，必须是字母
	 */
	public static final String LETTER_REGULAR="^[A-Za-z_]+$";
	/**
	 *   
	 * 正则表达式，必须是IP
	 */
	public static final String IP_REGULAR="^((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)$";
	
	/**
	 * 正则表达式，必须是mac                       [0-9A-F]{2}-[0-9A-F]{2}-[0-9A-F]{2}-[0-9A-F]{2}-[0-9A-F]{2}-[0-9A-F]{2}

	 */
	public static final String MAC_REGULAR="^(([0-9A-F]{2}-[0-9A-F]{2}-[0-9A-F]{2}-[0-9A-F]{2}-[0-9A-F]{2}-[0-9A-F]{2}))$";
	
	public static final String TRAPYNUMBER="^(([0-9a-fA-F]{2}-[0-9a-fA-F]{2}))$";
	
	/**
	 * 正则表达式，必须是字母和数字的字符串
	 */
	public static final String PASSWORD_REGULAR="[A-Za-z].*[0-9]|[0-9].*[A-Za-z]";
	/**
	 * 正则表达式,必须符合时间规则
	 */
	public static final String TIME_REGULAR="^(((20[0-3][0-9]-(0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|(20[0-3][0-9]-(0[2469]|11)-(0[1-9]|[12][0-9]|30))) (20|21|22|23|[0-1][0-9]):[0-5][0-9]:[0-5][0-9])$";
	
	/**
	 * 验证是否通过增则表达式的验证
	 * 
	 * @return
	 */
	public static boolean checking(String text,String regular) {
		
		Pattern pattern = Pattern.compile(regular);
		Matcher matcher = pattern.matcher(text.trim());

		return matcher.find();
	}
	/**
	 * 正则表达式，必须是电话格式
	 */
	public static final String PHONE = "(1[0-9]{10})|([0-9]{3}-[0-9]{7-8})|([0-9]{4}-[0-9]{7-8})";
	
	public static final String SIXTEEN = "^[0-9a-fA-F]{1,16}$";
	
	public static final String NUM1_9 = "";
	
}
