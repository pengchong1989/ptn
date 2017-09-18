package com.nms.main;
import java.awt.Font;
import java.util.Enumeration;
import java.util.Locale;

import javax.swing.UIManager;

import com.nms.rmi.ui.ServiceMainFrame;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.util.StartUtil;

public class ServiceMain {
	/**
	 * @param args
	 */

	public static void main(String[] args) {

		//判断程序是否启动		
		StartUtil startUtil = null;
		boolean flag = true; // 程序是否已经启动
		try {
			javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
			//设置语言为系统默认语言
			if(!Locale.getDefault().toString().equals("zh_CN")){
				ResourceUtil.language="en_US";
			}
			//设置界面字体
			Font font = new Font("宋体", Font.PLAIN, 12);
			Enumeration<Object> keys = UIManager.getDefaults().keys();
			while (keys.hasMoreElements()) {
				Object key = keys.nextElement();
				Object value = UIManager.get(key);
				if (value instanceof javax.swing.plaf.FontUIResource)
					UIManager.put(key, font);
			}
			
			startUtil = new StartUtil();
			flag = startUtil.checkingServer();
			if (flag) {
				DialogBoxUtil.succeedDialog(null, "程序已启动");
				System.exit(0);
			} else {
				
				new ServiceMainFrame();
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,ServiceMain.class);
		}

	}

}
