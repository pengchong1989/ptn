package com.nms.ui.topology;

/**
 * @author zhangkun 描述字符串长度的类
 */
public class TextLength {
	private boolean isChineseChar(char c) throws Exception {// 判断是否是一个汉字
		return String.valueOf(c).getBytes("UTF-8").length > 1;// 汉字的字节数大于1
	}

	private int getChineseCount(String s) {// 获得汉字的长度
		char c;
		int chineseCount = 0;
		try {
			if (!"".equals("")) {// 判断是否为空
				s = new String(s.getBytes(), "UTF-8"); // 进行统一编码
			}
			for (int i = 0; i < s.length(); i++) {// for循环
				c = s.charAt(i); // 获得字符串中的每个字符
				if (isChineseChar(c)) {// 调用方法进行判断是否是汉字
					chineseCount++; // 等同于chineseCount=chineseCount+1
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chineseCount; // 返回汉字个数
	}

	public int getStringInfo(String s) {// 获得字母、数字、空格，下划线 的个数
		int count = 0;
		char ch;
		int character = 0, blank = 0, number = 0, num_ber = 0;
		try {
			for (int i = 0; i < s.length(); i++) // for循环
			{
				ch = s.charAt(i);
				if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))// 统计字母
					character++; // 等同于character=character+1
				else if (ch == ' ') // 统计空格
					blank++; // 等同于blank=blank+1
				else if (ch >= '0' && ch <= '9') // 统计数字
					number++; // 等同于number=number+1;
				else if (ch == '_') // 统计数字
					num_ber++; // 等同于number=number+1;
			}
			count = getChineseCount(s) * 2;

			return count + character + blank + number + num_ber;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}
}
