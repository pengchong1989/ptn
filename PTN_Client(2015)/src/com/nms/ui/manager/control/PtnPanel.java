package com.nms.ui.manager.control;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.jtattoo.plaf.BaseBorders.TextFieldBorder;
import com.nms.ui.manager.CheckingUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.util.ComboBoxDataUtil;

@SuppressWarnings("serial")
public class PtnPanel extends JPanel {
	
	private ComboBoxDataUtil comboBoxDataUtil=new ComboBoxDataUtil();
	
	/**
	 * 添加控件
	 * @param panel 承载面板
	 * @param component	控件
	 * @param gridx gridx 
	 * @param gridy gridy
	 * @param weightx weightx
	 * @param weighty weighty
	 * @param gridwidth gridwidth
	 * @param gridheight gridheight
	 * @param fill fill
	 * @param insets insets
	 * @param anchor anchor
	 * @param gridBagConstraints gridBagConstraints
	 */
	public void addComponent(JPanel panel, JComponent component, int gridx, int gridy, double weightx, double weighty, int gridwidth, int gridheight, int fill, Insets insets, int anchor, GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.weightx = weightx;
		gridBagConstraints.weighty = weighty;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.fill = fill;
		gridBagConstraints.insets = insets;
		gridBagConstraints.anchor = anchor;
		panel.add(component, gridBagConstraints);
	}
	
	public ComboBoxDataUtil getComboBoxDataUtil() {
		return comboBoxDataUtil;
	}
   /**
   * @return boolean true 可以使用 false: 不能使用
   * 用于验证MAC/ip/文本框
   */
	public  void isChecking(String vlaue,int label,PtnTextField textField,JLabel labMessage,PtnButton confirm){
	   Border border = BorderFactory.createLineBorder(Color.RED); // 带颜色的文本框边框
	   Border textFieldBorder = new TextFieldBorder();
		try {
			if(label == 1){
				if (!CheckingUtil.checking(vlaue, CheckingUtil.MAC_REGULAR)) { // 判断填写是否为MAC格式

					this.style(ResourceUtil.srcStr(StringKeysTip.TIP_MACERROR),border,textField,labMessage,confirm);
				} else {
					this.style("", textFieldBorder,textField,labMessage,confirm);
				}
			}
			else if(label == 2){
				if (!CheckingUtil.checking(vlaue, CheckingUtil.IP_REGULAR)) { // 判断填写是否为ip格式

					this.style(ResourceUtil.srcStr(StringKeysTip.MESSAGE_IP), border,textField,labMessage,confirm);
				} else {

					this.style("",textFieldBorder,textField,labMessage,confirm);
				}
			}
			else if(label == 3){
				if (!CheckingUtil.checking(vlaue, CheckingUtil.NUMBER_REGULAR)) { // 判断填写是否为数字 
					this.style(ResourceUtil.srcStr(StringKeysTip.MESSAGE_NUMBER), border,textField,labMessage,confirm);
				} else {
					if(vlaue.length()>8){
						this.style(ResourceUtil.srcStr(StringKeysTip.TIP_ETHLOOPVLANID), border,textField,labMessage,confirm);	
					}else{
						int text_int = Integer.parseInt(vlaue);
						if(text_int<1 || text_int >4094){
							this.style(ResourceUtil.srcStr(StringKeysTip.TIP_ETHLOOPVLANID), border,textField,labMessage,confirm);
						}else{
							this.style("", textFieldBorder,textField,labMessage,confirm);
						}
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}	
		
	/**
	 * 设置控件布局
	 * @param message
	 * @param border
	 * @param textField
	 */
	private void style(String message, Border border,PtnTextField textField,JLabel labMessage,PtnButton confirm) {
		labMessage.setText(message);
		textField.setBorder(border);
		if ("".equals(message)) {
			labMessage.setForeground(Color.BLACK);
			labMessage.setBorder(null);
			if ("".equals(labMessage.getText())) {
				confirm.setEnabled(true);
			} else {
				confirm.setEnabled(false);
			}
		} else {
			labMessage.setForeground(Color.red);
			confirm.setEnabled(false);
		}
	}
	
	 //设置最大值和最小值
	public void setValidate(PtnTextField txtField,int max,int min) throws Exception{
		try {
			txtField.setCheckingMaxValue(true);
			txtField.setCheckingMinValue(true);
			txtField.setMaxValue(max);
			txtField.setMinValue(min);
		} catch (Exception e) {
			throw e;
		}
	}
}
