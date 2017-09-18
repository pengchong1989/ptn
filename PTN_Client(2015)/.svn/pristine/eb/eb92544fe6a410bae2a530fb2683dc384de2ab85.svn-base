package com.nms.ui.frame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import com.nms.ui.manager.ExceptionManage;

/**
 * 记录日志类
 * 
 * @author lepan
 * 
 */
public class LogUtil {
	private static long MAXSIZE = 1024 * 1024 * 5L; // 默认日志文件大小为5M
	private static int INDEX = 0;
	private static String fileName = "log";
	private static StringBuffer path = new StringBuffer(fileName).append(".txt");
	private static File file = new File(System.getProperty("user.dir") + path);
	private static PrintStream ps;

	public static void start() {
		try {
			if (file.length() > MAXSIZE) {
				path = new StringBuffer();
				INDEX++;
				fileName = "log" + INDEX;
				path.append(fileName).append(".txt");
				file = new File(System.getProperty("user.dir") + path);
			}
			ps = new PrintStream(new FileOutputStream(file, true));
			System.setErr(ps);
			System.setOut(ps);
		} catch (FileNotFoundException e) {
			ExceptionManage.dispose(e,LogUtil.class);
		}
	}

	public static void end() {
		file = null;
		ps.close();
		ps = null;
	}
}
