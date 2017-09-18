package com.nms.ui.manager.control;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.border.Border;

import com.jtattoo.plaf.BaseBorders.TextFieldBorder;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.util.ComboBoxDataUtil;

public class PtnComboBox extends JComboBox {

	/**
	 * 
	 * @since Ver 1.1
	 */

	private static final long serialVersionUID = 6120927853343174529L;

	/** 错误消息提示 */
	private final String MESSAGE_FILL = "请填写完整";

	/** 文本框样式 */
	private final Color color = Color.RED; // 颜色
	private final Border border = BorderFactory.createLineBorder(color); // 带颜色的文本框边框
	private final Border textFieldBorder = new TextFieldBorder(); // 默认文本框边框

	private String codeIdentity;
	private boolean mustFill; // 是否必填
	private JLabel lblMessage; // 提示文本控件
	private JButton btnSave; // 保存按钮
	private String errorMessage; // 错误消息提示
	private PtnDialog ptnDailog; // 弹出页对象

	/**
	 * 
	 * 创建一个新的实例 PtnComboBox.
	 * 
	 */
	public PtnComboBox() {

	}

	/**
	 * 
	 * 创建一个新的实例 默认绑定值 PtnComboBox.
	 * 
	 * @param mustFill
	 *            是否必须选择
	 * @param codeIdentity
	 *            相关code组标识
	 * @param lblMessage
	 * @param btnSave
	 * @param ptnDailog
	 * @throws Exception
	 */
	public PtnComboBox(boolean mustFill, String codeIdentity, JLabel lblMessage, JButton btnSave, PtnDialog ptnDailog) throws Exception {
		if (null == codeIdentity || "".equals(codeIdentity)) {
			throw new Exception("create ptnComboBox is error : codeIdentity is null");
		}
		if (null == lblMessage) {
			throw new Exception("create ptnComboBox error : lblMessage is null");
		}
		if (null == btnSave) {
			throw new Exception("create ptnComboBox error : btnSave is null");
		}
		if (null == ptnDailog) {
			throw new Exception("create ptnComboBox error : ptnDailog is null");
		}

		this.mustFill = mustFill;
		this.lblMessage = lblMessage;
		this.btnSave = btnSave;
		this.ptnDailog = ptnDailog;
		this.codeIdentity = codeIdentity;

		init();
	}

	/**
	 * 
	 * 创建一个新的实例 默认绑定值 PtnComboBox.
	 * 
	 * @param mustFill
	 *            是否必须选择
	 * @param codeIdentity
	 *            相关code组标识
	 * @param lblMessage
	 * @param btnSave
	 * @param ptnDailog
	 * @throws Exception
	 */
	public PtnComboBox(boolean mustFill, JLabel lblMessage, JButton btnSave, PtnDialog ptnDailog) throws Exception {
		if (null == lblMessage) {
			throw new Exception("create ptnComboBox error : lblMessage is null");
		}
		if (null == btnSave) {
			throw new Exception("create ptnComboBox error : btnSave is null");
		}
		if (null == ptnDailog) {
			throw new Exception("create ptnComboBox error : ptnDailog is null");
		}

		this.mustFill = mustFill;
		this.lblMessage = lblMessage;
		this.btnSave = btnSave;
		this.ptnDailog = ptnDailog;

		init();
	}

	private void init() throws Exception {

		if (null != this.codeIdentity && !"".equals(this.codeIdentity)) {
			ComboBoxDataUtil comboBoxDataUtil=new ComboBoxDataUtil();
			comboBoxDataUtil.comboBoxData(this, this.codeIdentity);
		}

		lblMessage.setForeground(color);
		if (this.mustFill) {
			if (this.getSelectedItem() == null) {
				this.style(MESSAGE_FILL, this.border);
			} else {
				this.style("", this.textFieldBorder);
			}
		}
		addListener();
		this.ptnDailog.setPtnComboBoxList(this);
	}

	public void addListener() {
		this.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				if (evt.getStateChange() == 1) {
					try {
						checking();
					} catch (Exception e) {
						ExceptionManage.dispose(e,this.getClass());
					}
				}
			}
		});
	}

	/**
	 * 
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
	private void checking() {
		
		if(this.mustFill){
			if(null == getSelectedItem()){
				style(MESSAGE_FILL, border);
			}else{
				style("", this.textFieldBorder);
			}
		}else{
			style("", this.textFieldBorder);
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
		this.lblMessage.setText(message);
		this.setBorder(border);
		this.setErrorMessage(message);
		if ("".equals(this.getErrorMessage())) {
			lblMessage.setText(ptnDailog.checkingMessage());
			if ("".equals(lblMessage.getText())) {
				this.btnSave.setEnabled(true);
			} else {
				this.btnSave.setEnabled(false);
			}
		} else {
			this.btnSave.setEnabled(false);
		}
	}

	public boolean isMustFill() {
		return mustFill;
	}

	public void setMustFill(boolean mustFill) {
		this.mustFill = mustFill;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
