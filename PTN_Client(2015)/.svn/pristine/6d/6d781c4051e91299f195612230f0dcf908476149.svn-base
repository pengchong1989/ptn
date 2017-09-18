/**   
 * 文件名：PtnTextField.java   
 * 创建人：kk   
 * 创建时间：2013-4-26 下午04:08:07 
 *   
 */
package com.nms.ui.manager.control;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.jtattoo.plaf.BaseBorders.TextFieldBorder;
import com.nms.ui.manager.CheckingUtil;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

/**
 * 
 * 项目名称：WuHanPTN2012 类名称：PtnTextField 类描述：自定义文本框，有表单验证 创建人：kk 创建时间：2013-4-26 下午04:08:07 修改人：kk 修改时间：2013-4-26 下午04:08:07 修改备注：
 * 
 * @version
 * 
 */
public class PtnTextField extends JTextField {

	/**
	 * @since Ver 1.1
	 */

	private static final long serialVersionUID = 1144447593116135876L;

	/** 文本框类型 */
	public static final int TYPE_INT = 1; // 文本框int类型 [0,++]
	public static final int TYPE_STRING = 2; // 文本框String类型
	public static final int TYPE_IP = 3;
	public static final int TYPE_MAC = 5;
	public static final int TYPE_PHONE = 4;// 电话类型
	public static final int TYPE_STRING_NUMBER = 6;// 数字/字母/数字字母
	public static final int TYPE_INT_ONE = 7; // 文本框int类型 [-++,++] 所有整数
	/** 最大长度 */
	public static final int INT_MAXLENGTH = 8; // int类型的最大长度
	public static final int IP_MAXLENGTH = 15; // ip类型的最大长度
	public static final int MAC_MAXLENGTH = 17; // MAC类型的最大长度
	public static final int PHONE_MAXLENGTH = 13; // 电话类型的最大长度
	public static final int STRING_MAXLENGTH = 50; // 文本最大长度
	public static final int STRING_NUMBER_MAXLENGTH = 43; // 文本最大长度
	/** 文本框样式 */
	private final Color color = Color.RED; // 颜色
	private final Border border = BorderFactory.createLineBorder(color); // 带颜色的文本框边框
	public static final Border textFieldBorder = new TextFieldBorder(); // 默认文本框边框

	private boolean mustFill; // 是否必填
	private int maxLength; // 最大长度
	private JLabel lblMessage; // 提示文本控件
	private JButton btnSave; // 保存按钮
	private int type; // 文本框类型
	private String errorMessage;
	private PtnDialog dialog;
	private JCheckBox box;

	private boolean isCheckingMaxValue; // 是否验证最大值
	private int maxValue; // 最大值
	private boolean isCheckingMinValue; // 是否验证最小值
	private int minValue; // 最小值
	private boolean specialRegular;

	/**
	 * 
	 * 创建一个新的实例 PtnTextField.
	 * 
	 */
	public PtnTextField() {
		this.addListenerMaxLength();
	}

	/**
	 * 
	 * 创建一个新的实例 String类型的 PtnTextField.
	 * 
	 * @param mustFill
	 *            是否必填
	 * @param maxLength
	 *            最大长度
	 * @param lblMessage
	 *            提示label控件
	 * @param btnSave
	 *            保存按钮
	 * @param dialog
	 *            对话框
	 * @throws Exception
	 */
	public PtnTextField(boolean mustFill, int maxLength, JLabel lblMessage, JButton btnSave, PtnDialog dialog) throws Exception {
		if (maxLength == 0) {
			throw new Exception("create text error : maxLength is 0");
		}
		if (null == lblMessage) {
			throw new Exception("create text error : lblMessage is null");
		}
		if (null == btnSave) {
			throw new Exception("create text error : btnSave is null");
		}
		if (null == dialog) {
			throw new Exception("create text error : dialog is null");
		}
		this.type = TYPE_STRING;
		this.maxLength = maxLength;
		this.mustFill = mustFill;
		this.lblMessage = lblMessage;
		this.btnSave = btnSave;
		this.dialog = dialog;

		this.init();
	}

	/**
	 * 
	 * 创建一个新的实例 特殊类型的 PtnTextField.
	 * 
	 * @param mustFill
	 *            是否必填
	 * @param type
	 *            类型，区分是int ip 等
	 * @param maxLength
	 *            最大长度
	 * @param lblMessage
	 *            提示label控件
	 * @param btnSave
	 *            保存按钮
	 * @throws Exception
	 */
	public PtnTextField(boolean mustFill, int type, int maxLength, JLabel lblMessage, JButton btnSave, PtnDialog dialog) throws Exception {
		if (null == lblMessage) {
			throw new Exception("create text error : lblMessage is null");
		}
		if (null == btnSave) {
			throw new Exception("create text error : btnSave is null");
		}
		if (null == dialog) {
			throw new Exception("create text error : dialog is null");
		}
		this.type = type;
		this.maxLength = maxLength;
		this.mustFill = mustFill;
		this.lblMessage = lblMessage;
		this.btnSave = btnSave;
		this.dialog = dialog;

		this.init();
	}

	/**
	 * 
	 * 创建一个新的实例 特殊类型的 PtnTextField.
	 * 
	 * @param mustFill
	 *            是否必填
	 * @param type
	 *            类型，区分是int ip 等
	 * @param maxLength
	 *            最大长度
	 * @param lblMessage
	 *            提示label控件
	 * @param btnSave
	 *            保存按钮
	 * @throws Exception
	 */
	public PtnTextField(boolean mustFill, JCheckBox box, int type, int maxLength, JLabel lblMessage, JButton btnSave, PtnDialog dialog) throws Exception {
		if (null == lblMessage) {
			throw new Exception("create text error : lblMessage is null");
		}
		if (null == btnSave) {
			throw new Exception("create text error : btnSave is null");
		}
		if (null == dialog) {
			throw new Exception("create text error : dialog is null");
		}
		this.box = box;
		this.type = type;
		this.maxLength = maxLength;
		this.mustFill = mustFill;
		this.lblMessage = lblMessage;
		this.btnSave = btnSave;
		this.dialog = dialog;

		this.init();
	}

	/**
	 * init(初始化控件)
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private void init() {
		lblMessage.setForeground(color);
		if (this.mustFill) {
			if (this.getText().trim().length() == 0) {
				this.style(ResourceUtil.srcStr(StringKeysTip.TIP_NOT_FULL), this.border);
			} else {
				this.style("", this.textFieldBorder);
			}
		}
		this.addListener();
		dialog.setPtnTextFieldList(this);
	}

	/**
	 * addListener(添加监听)
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private void addListener() {
		this.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				checking(e);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				checking(e);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				checking(e);
			}
		});
		this.addListenerMaxLength();
	}

	private void addListenerMaxLength() {
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if(maxLength>0){
					if (getText().length() >= maxLength) {
						e.consume();
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
	}

	/**
	 * 
	 * checking(校验)
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private void checking(DocumentEvent e) {
		// 判断是否填写
		if (this.mustFill && this.getText().trim().length() == 0) {

			this.style(ResourceUtil.srcStr(StringKeysTip.TIP_NOT_FULL), this.border);

		}  else if (this.getText().trim().length() > maxLength) { // 判断长度

			this.style(ResourceUtil.srcStr(StringKeysTip.MESSAGE_LENG) + maxLength, this.border);
			// this.style(ResourceUtil.srcStr(StringKeysTip.MESSAGE_VALUE_SCOPE) + 0 + "~" + this.maxLength+ResourceUtil.srcStr(StringKeysTip.MESSAGE_VALUE_SCOPE2), this.border);

		} else if (this.type == TYPE_INT) { // 判断是否为int [0,++]
			if ((box == null) || (box != null && box.isSelected())) {
				if (!CheckingUtil.checking(this.getText(), CheckingUtil.NUMBER_REGULAR)) { // 判断填写是否为数字
					this.style(ResourceUtil.srcStr(StringKeysTip.MESSAGE_NUMBER), this.border);
				} else {
					int text_int = Integer.parseInt(this.getText());
					isMaxandMin(text_int);
				}
			}

		} else if (this.type == TYPE_IP) { // 判断填写是否为IP

			if (!CheckingUtil.checking(this.getText(), CheckingUtil.IP_REGULAR)) { // 判断填写是否为ip格式

				this.style(ResourceUtil.srcStr(StringKeysTip.MESSAGE_IP), this.border);
			} else {

				this.style("", this.textFieldBorder);
			}
		} else if (this.type == TYPE_PHONE) {
			if (!CheckingUtil.checking(this.getText(), CheckingUtil.PHONE)) { // 判断填写是否为电话格式

				this.style(ResourceUtil.srcStr(StringKeysTip.MESSAGE_PHONENUMBER), this.border);
			} else {

				this.style("", this.textFieldBorder);
			}
		} else if (this.type == TYPE_MAC) { // 判断填写是否为MAC

			if (!CheckingUtil.checking(this.getText(), CheckingUtil.MAC_REGULAR)) { // 判断填写是否为MAC格式

				this.style(ResourceUtil.srcStr(StringKeysTip.TIP_MACERROR), this.border);
			} else {

				this.style("", this.textFieldBorder);

			}
		} else if (this.type == TYPE_STRING_NUMBER) { // 判断填写是否为数字或者字母或字母数字组合

			if (CheckingUtil.checking(this.getText(), CheckingUtil.NUMBER_REGULAR) || CheckingUtil.checking(this.getText(), CheckingUtil.LETTER_REGULAR) || CheckingUtil.checking(this.getText(), CheckingUtil.PASSWORD_REGULAR) || this.getText().trim().equals("")) { // 判断填写是否为字母和数字
				this.style("", this.textFieldBorder);
			} else {
				this.style(ResourceUtil.srcStr(StringKeysTip.MESSAGE_NUMBERANDLETTER), this.border);

			}
		} else if (this.type == TYPE_INT_ONE) { // 判断是否为int [--,++]
			if ((box == null) || (box != null && box.isSelected())) {
				if (!CheckingUtil.checking(this.getText(), CheckingUtil.NUMBER_NUM)) { // 判断填写是否为数字
					this.style(ResourceUtil.srcStr(StringKeysTip.MESSAGE_NUMBER), this.border);
				} else {
					int text_int = Integer.parseInt(this.getText());
					isMaxandMin(text_int);
				}
			}
		} else {
			this.style("", this.textFieldBorder);
		}

	}

	private void isMaxandMin(int text_int) {
		// 验证最大值和最小值的是否验证是否为true
		if (this.isCheckingMaxValue && this.isCheckingMinValue) { // 验证最大值和最小值
			if (text_int < this.minValue || text_int > this.maxValue) {
				this.style(ResourceUtil.srcStr(StringKeysTip.MESSAGE_VALUE_SCOPE) + this.minValue + "~" + this.maxValue + ResourceUtil.srcStr(StringKeysTip.MESSAGE_VALUE_SCOPE2), this.border);
			} else {
				this.style("", this.textFieldBorder);
			}
		} else if (this.isCheckingMaxValue) { // 验证最大值
			if (text_int > this.maxValue) {
				this.style(ResourceUtil.srcStr(StringKeysTip.MESSAGE_VALUE_MAX) + this.maxValue, this.border);
			} else {
				this.style("", this.textFieldBorder);
			}
		} else if (this.isCheckingMinValue) { // 验证最小值
			if (text_int < this.minValue) {
				this.style(ResourceUtil.srcStr(StringKeysTip.MESSAGE_VALUE_MIN) + this.minValue, this.border);
			} else {
				this.style("", this.textFieldBorder);
			}
		} else {
			this.style("", this.textFieldBorder);
		}

	}

	/**
	 * 
	 * style(设置样式)
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private void style(String message, Border border) {
		// String messageStr = null;
		// if ("".equals(message)) {
		// messageStr = "";
		// } else {
		// messageStr = ResourceUtil.srcStr(message);
		// }

		this.lblMessage.setText(message);
		this.setErrorMessage(message);
		this.setBorder(border);
		if ("".equals(this.getErrorMessage())) {
			lblMessage.setText(dialog.checkingMessage());
			if ("".equals(lblMessage.getText())) {
				this.btnSave.setEnabled(true);
			} else {
				this.btnSave.setEnabled(false);
			}
		} else {
			this.btnSave.setEnabled(false);
		}
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean isMustFill() {
		return mustFill;
	}

	public void setMustFill(boolean mustFill) {
		this.mustFill = mustFill;
		this.init();
		if (!mustFill) {
			this.style("", this.textFieldBorder);
		}
	}

	public boolean isCheckingMaxValue() {
		return isCheckingMaxValue;
	}

	public void setCheckingMaxValue(boolean isCheckingMaxValue) {
		this.isCheckingMaxValue = isCheckingMaxValue;
	}

	public int getMaxValue() {
		return maxValue;
	}

	/**
	 * 当文本框类型为INT 并且isCheckingMaxValue为true 此验证生效
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public boolean isCheckingMinValue() {
		return isCheckingMinValue;
	}

	public void setCheckingMinValue(boolean isCheckingMinValue) {
		this.isCheckingMinValue = isCheckingMinValue;
	}

	public int getMinValue() {
		return minValue;
	}

	/**
	 * 当文本框类型为INT 并且isCheckingMinValue为true 此验证生效
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}

	public void setSpecialRegular(boolean specialRegular) {
		this.specialRegular = specialRegular;
	}

	public boolean isSpecialRegular() {
		return specialRegular;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
}
