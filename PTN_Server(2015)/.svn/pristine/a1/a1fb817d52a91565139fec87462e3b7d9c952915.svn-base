package com.nms.ui.manager;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.Set;

/**
 * 
 * ＜p＞Title:多国语言的后台实现类 ＜/p＞
 * 
 * ＜p＞Description: 根据属性文件及区域与语言设置,得到对应的语言＜/p＞
 * 
 * @author:王文峰
 * 
 * @version 1.0
 * 
 */

public class ResourceUtil {

	private static final String HEAD_FILE = "ATTR"; // 属性文件名的前缀

	private static final String LAST_FILE = ".properties"; // 属性文件名的后缀

	private static final String FILE_PATH = "config/"; // 定义属性文件存放的目录

	public static String language = "zh_CN"; // 选择的语言

	private static Map<String, String> map = null;

	public ResourceUtil() {

	}

	/* 以下是根据传入的属性文件中的"键",而得到与区域与语言设置相对应的"值" */

	public static String srcStr(String disStr) {

		String result = "";

		try {

			if (null == map) {
				map = new HashMap<String, String>();

				String baseName = new StringBuffer().append(FILE_PATH)

				.append(HEAD_FILE).append("_").append(language)

				.append(LAST_FILE).toString(); // 根据所选语言,前缀以及后缀生成文件名

				InputStream is = ResourceUtil.class.getClassLoader().getResourceAsStream(baseName); // 生成文件输入流

				PropertyResourceBundle pr = new PropertyResourceBundle(is); // 根据输入流构造PropertyResourceBundle的实例

				Set<String> set = pr.keySet();

				for (String key : set) {
					String value = pr.getString(key);
					map.put(key, new String(value.getBytes("ISO-8859-1"), "GB2312"));
				}

				is.close();
			}

			result = map.get(disStr);

		} catch (Exception e) {

			ExceptionManage.dispose(e, ResourceUtil.class);

			return disStr;
		}

		return result;
	}

}
