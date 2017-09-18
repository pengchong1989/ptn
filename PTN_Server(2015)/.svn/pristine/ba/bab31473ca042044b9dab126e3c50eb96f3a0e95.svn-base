package com.nms.ui.manager;

import java.awt.Component;
import java.util.Locale;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import com.nms.ui.manager.keys.StringKeysTitle;

public class DialogBoxUtil {

	public static void errorDialog(Component component, String str) {
		setLocale();
		JOptionPane.showMessageDialog(null, str, ResourceUtil.srcStr(StringKeysTitle.TIT_ERROR), JOptionPane.ERROR_MESSAGE);
	}

	public static void succeedDialog(Component component, String str) {
		setLocale();
		JOptionPane.showMessageDialog(null, str, ResourceUtil.srcStr(StringKeysTitle.TIT_PROMPT), JOptionPane.INFORMATION_MESSAGE);
	}

	public static int confirmDialog(Component component, String str) {
		setLocale();
		return JOptionPane.showConfirmDialog(null, str, ResourceUtil.srcStr(StringKeysTitle.TIT_PROMPT), JOptionPane.YES_NO_OPTION);
	}

	private static void setLocale(){
		if ("en_US".equals(ResourceUtil.language)) {
			Locale locale = new Locale("en", "us");
			JComponent.setDefaultLocale(locale);
		} else {
			Locale locale = new Locale("zh", "CN");
			JComponent.setDefaultLocale(locale);
		}
	}
	
	
}
