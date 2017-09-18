package com.nms.main;
import java.awt.Font;
import java.util.Enumeration;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.nms.ui.Login;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {   
			javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");

			Font font = new Font("宋体", Font.PLAIN, 12);
			Enumeration<Object> keys = UIManager.getDefaults().keys();
			while (keys.hasMoreElements()) {
				Object key = keys.nextElement();
				Object value = UIManager.get(key);
				if (value instanceof javax.swing.plaf.FontUIResource)
					UIManager.put(key, font);
			}
			Login frame = new Login();
			frame.setLocation(UiUtil.getWindowWidth(frame.getWidth()), UiUtil.getWindowHeight(frame.getHeight()));
			frame.setVisible(true);

		} catch (ClassNotFoundException e) {
			ExceptionManage.dispose(e,Main.class);
		} catch (InstantiationException e) {
			ExceptionManage.dispose(e,Main.class);
		} catch (IllegalAccessException e) {
			ExceptionManage.dispose(e,Main.class);
		} catch (UnsupportedLookAndFeelException e) {
			ExceptionManage.dispose(e,Main.class);
		}
	}

}
