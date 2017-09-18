package com.nms.ui.manager;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Verification {

	/**
	 * 文本框验证是否为空
	 * 
	 * @param jTextField
	 *            传入的文本框
	 * @return
	 */
	public static boolean jtextfieldNull(JTextField jTextField) {

		if (jTextField.getText().trim().length() == 0 || jTextField.getText().length() > 100) {

			jTextField.setBorder(new LineBorder(Color.RED));
			return true;

		}

		jTextField.setBorder(new LineBorder(Color.BLACK));

		return false;
	}

	/**
	 * 文本框验证是否包含字符
	 * 
	 * @return
	 */
	public static boolean jtextfieldIsString(JTextField jTextField) {

		Pattern p = Pattern.compile("[a-z A-Z `~%!@#^=''?~！@#￥……&——‘”“'？*()（），,。.、]");
		Matcher m = p.matcher(jTextField.getText().trim());

		if (jTextField.getText().trim().length() == 0 || jTextField.getText().length() > 100 || m.find()) {
			{
				jTextField.setBorder(new LineBorder(Color.RED));
				return true;
			}
		}
		return false;
	}

	/**
	 * 密码框验证是否为空
	 * 
	 * @param jpasswordfield
	 *            传入的密码框
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static boolean jpasswordfieldNull(JPasswordField jpasswordfield) {
		if (jpasswordfield.getText().trim().length() == 0 || jpasswordfield.getText().length() > 100) {
			{
				return true;
			}
		}
		jpasswordfield.setBorder(new LineBorder(Color.BLACK));
		return false;
	}

	/**
	 * 下拉列表验证是否为空
	 * 
	 * @param jComboBox
	 * @return
	 */
	public static boolean jComboBoxNull(JComboBox jComboBox) {
		ControlKeyValue selectgroup = (ControlKeyValue) jComboBox.getSelectedItem();
		if (null == selectgroup || selectgroup.getId().equals("0") || selectgroup.getId().equals("")) {
			{
				return true;
			}
		}

		jComboBox.setBorder(new LineBorder(Color.BLACK));
		return false;
	}

	/**
	 * 判断IP格式是否正确
	 * 
	 * @param jTextField
	 *            传入的格式
	 * @return
	 */
	public static boolean ip(JTextField jTextField) {

		Pattern p = Pattern.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");

		Matcher m = p.matcher(jTextField.getText().trim());
		if (!m.matches() || jTextField.getText().trim() == null || jTextField.getText().length() > 100) {
			jTextField.setBorder(new LineBorder(Color.RED));
			return true;
		}

		jTextField.setBorder(new LineBorder(Color.BLACK));
		return false;
	}
}
