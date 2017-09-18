package com.nms.service.translate;

import java.io.InputStream;
import java.util.Locale;
import java.util.PropertyResourceBundle;

import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;

/**
 * 
 * 
 * @update by stones 20130925 for up
 */
public class PromptInfoTranslate {

	private static final String HEAD_FILE = "PromptInfo";
	private static final String LAST_FILE = ".properties";
	private static final String FILE_PATH = "config/";
	private static PropertyResourceBundle pr = null;

	public static final String PromptInfoTranslation(String promptInfo) {
		String fileName = null;
		Locale locale = null;
		InputStream is = null;
		String[] splitedString = null;
		String propertyValue = null;
		String[] sarr= null;
		try {
			if (null == promptInfo) {
				ExceptionManage.logDebug("Prompt Information Translation Failed!",PromptInfoTranslate.class);
				return promptInfo;
			}
//			if(pr == null){
				locale = Locale.getDefault(); /* 获取系统的区域与语言默认设置 */
				fileName = FILE_PATH + HEAD_FILE + "_" + ResourceUtil.language + LAST_FILE;/* 在网管登入时，ResourceUtil.language变量已经被赋值 */
				is = ResourceUtil.class.getClassLoader().getResourceAsStream(fileName);
				pr = new PropertyResourceBundle(is); /* 根据输入流构造PropertyResourceBundle的实例 */
//			}
			if(pr == null){return " PromptInfoTranslate error . " ;}
			splitedString = new String[2];/* 字符数组共2位：splitedString[0]包含用于在prop文件中查找用到的key；splitedString[1]包含设备返回的提示信息用于前台呈现 */
			/*
			 * 解析提示信息字符串，解析目前共分为3种 当以‘:’分隔时，左侧为key，右侧为value
			 * 当包含‘find’字符串时，用‘空格’拆分字符串，最后出现的‘空格’左侧字符串为key，右侧为value
			 * 当为其它情况时，不用拆分直接查找
			 */
			if (promptInfo.trim().indexOf(":") >= 0) {

				splitedString = propertyKeySettingI(promptInfo.trim());
				if(splitedString[1].indexOf("'")>= 0){
					sarr = splitedString[1].split("'");
				}else{
					sarr = new String[]{splitedString[1]} ;
				}				
			} else if (promptInfo.trim().length() >= 4 && "find".equalsIgnoreCase(promptInfo.trim().substring(0, 4))) {
				splitedString = propertyKeySettingII(promptInfo.trim());
			} else {
				splitedString[0] = promptInfo.trim();
				splitedString[1] = "";/* 无提示信息 */
			}
			
			propertyValue = pr.getString(splitedString[0]);
			/* 如果是要显示中文,则要进行内码的转换 */
			if (locale.equals(Locale.CHINA)) {
				propertyValue = new String(propertyValue.getBytes("ISO-8859-1"), "GB2312");
			}
			//替换解释里面的变量
			if(null != sarr){
				for(int i=0 ; i<sarr.length ; i++){
					propertyValue = propertyValue.replace(new String("$"+(i+1)), sarr[i]);
				}
			}
			
			return propertyValue;
		} catch (Exception e) {
			ExceptionManage.dispose(e,PromptInfoTranslate.class);
			return promptInfo;
		} finally {
			splitedString = null;
		}
	}

	/**
	 * <p>
	 * property文件中key字符串组合
	 * </p>
	 * 
	 * @param promptInfo
	 * @return
	 * @throws Exception
	 */
	private static final String[] propertyKeySettingI(String promptInfo) throws Exception {
		String[] splitedString = null;
		String[] splitedString_ = null;
		int length = 0;
		StringBuffer sb = null;
		try {
			sb = new StringBuffer();
			splitedString_ = new String[2];
			splitedString = promptInfo.split(":");
			length = splitedString.length;
			for (int i = 0; i < length; i++) {
				if (i == 0) {/* 第一个‘:’左侧为key */
					splitedString_[0] = splitedString[i];
				} else {/* 第一个‘:’右侧为value */
					sb.append(splitedString[i]);
				}
			}
			splitedString_[1] = sb.toString().trim();
			return splitedString_;
		} catch (Exception e) {
			throw e;
		} finally {
			splitedString = null;
			sb = null;
		}
	}

	/**
	 * <p>
	 * property文件中key字符串组合
	 * </p>
	 * 
	 * @param promptInfo
	 * @return
	 * @throws Exception
	 */
	private static final String[] propertyKeySettingII(String promptInfo) throws Exception {
		String[] splitedString = null;
		String[] splitedString_ = null;
		int length = 0;
		StringBuffer sb = null;
		try {
			sb = new StringBuffer();
			splitedString_ = new String[2];
			splitedString = promptInfo.split(" ");
			length = splitedString.length;
			for (int i = 0; i < length; i++) {
				if (i < length - 1) {/* 最后一个空格左侧为key */

					sb.append(splitedString[i] + " ");
				} else if (i == length - 1) {/* 最后一个空格右侧为value */

					splitedString_[1] = splitedString[i];
				}
			}
			splitedString_[0] = sb.toString().trim();
			return splitedString_;
		} catch (Exception e) {
			throw e;
		} finally {
			splitedString = null;
			sb = null;
		}
	}

	public static void main(String[] args) {

		try {
			// String str = PromptInfoTranslate.PromptInfoTranslation("find
			// field on path for node ne/interfaces/pweth/1");
			ResourceUtil.language="zh_CN";
			String str = PromptInfoTranslate.PromptInfoTranslation("E1MBSA2:af1'tunnel/1");
			System.out.println(str);
		} catch (Exception e) {

			ExceptionManage.dispose(e,PromptInfoTranslate.class);
		}
	}
}
