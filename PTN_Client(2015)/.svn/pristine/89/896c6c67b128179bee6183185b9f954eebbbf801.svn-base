package com.nms.ui.ptn.clock.view.cx.time;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.nms.ui.manager.CheckingUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysLbl;

public class TextfieldFocusListener implements FocusListener {

	private String whichField = null;

	private int rangeFlag = 0;

	private JTextField textField = null;

	private String errorMess = null;

	public TextfieldFocusListener(String whichField, int rangeFlag, JTextField textField) {

		this.whichField = whichField;// 名字
		this.rangeFlag = rangeFlag;// 数字
		this.textField = textField;// 标签的名称
	}

	public void focusGained(FocusEvent e) {
	}

	public void focusLost(FocusEvent e) {

		int inputNumber = 0;
		try {

			/* 区间是否为0-255 */
			if (rangeFlag == 1) {

				errorMess = whichField + ": 范围[0-255]";
				inputNumber = Integer.parseInt(textField.getText());/* 写在这的目的是为了异常时输出需要信息 */
				if (inputNumber < 0 || inputNumber > 255) {

					showDialog(errorMess);
				}
			}
			/* 区间是否为0-65535 */
			if (rangeFlag == 2) {

				errorMess = whichField + ": 范围[0-65,535]";
				inputNumber = Integer.parseInt(textField.getText());
				if (inputNumber < 0 || inputNumber > 65535) {

					showDialog(errorMess);
				}
			}
			/* 区间是否为0-16777215 */
			if (rangeFlag == 3) {

				errorMess = whichField + ": 范围[0-16,777,215]";
				inputNumber = Integer.parseInt(textField.getText());
				if (inputNumber < 0 || inputNumber > 16777215) {

					showDialog(errorMess);
				}
			}
			/* 区间是否为0-4094 */
			if (rangeFlag == 4) {

				errorMess = whichField + ": 范围[0-4,094]";
				inputNumber = Integer.parseInt(textField.getText());
				if (inputNumber < 0 || inputNumber > 4094) {

					showDialog(errorMess);
				}
			}
			/* 区间是否为1-65535 */
			if (rangeFlag == 5) {

				errorMess = whichField + ": 范围[0-65,535]";
				inputNumber = Integer.parseInt(textField.getText());
				if (inputNumber < 1 || inputNumber > 65535) {

					showDialog(errorMess);
				}
			}
			/* 区间是否为-2147483638-2147483637 */
			if (rangeFlag == 6) {

				errorMess = whichField + ": 范围[-2,147,483,638-2,147,483,637]";
				inputNumber = Integer.parseInt(textField.getText());
				if (inputNumber < -2147483638 || inputNumber > 2147483637) {

					showDialog(errorMess);
				}
			}
			/* 区间是否为1-12 */
			if (rangeFlag == 7) {

				errorMess = whichField + ": 范围[1,12]";
				inputNumber = Integer.parseInt(textField.getText());
				if (inputNumber < 1 || inputNumber > 12) {

					showDialog(errorMess);
				}
			}
			/* 区间是否为0-7 */
			if (rangeFlag == 8) {

				errorMess = whichField + ": 范围[0,7]";
				inputNumber = Integer.parseInt(textField.getText());
				if (inputNumber < 0 || inputNumber > 7) {

					showDialog(errorMess);
				}
			}
			/* 区间是否为0-8191 */
			if (rangeFlag == 9) {

				errorMess = whichField + ": 范围[0,8191]";
				inputNumber = Integer.parseInt(textField.getText());
				if (inputNumber < 0 || inputNumber > 8191) {

					showDialog(errorMess);
				}
			}
			/* 区间是否为2-2047 */
			if (rangeFlag == 10) {
				errorMess = whichField + ": 范围[2,2047]";
				inputNumber = Integer.parseInt(textField.getText());
				if (inputNumber < 2 || inputNumber > 2047) {
					showDialog(errorMess);
				}
			}

			/* 区间是否为0-63 */
			if (rangeFlag == 11) {

				errorMess = whichField + ": 范围[0,63]";
				inputNumber = Integer.parseInt(textField.getText());
				if (inputNumber < 0 || inputNumber > 63) {

					showDialog(errorMess);
				}
			}
			/* 是否为IP */
			if (rangeFlag == 12) {
				errorMess = whichField + ":输入正确的IP格式";
				if (textField != null) {
					if (!CheckingUtil.checking(textField.getText(), CheckingUtil.IP_REGULAR)) {
						JOptionPane.showMessageDialog(null, errorMess);
						if (whichField.equals(ResourceUtil.srcStr(StringKeysLbl.LBL_SOURCE_IP))) {
							textField.setText("10.18.1.1");
						} else {
							textField.setText("10.18.1.2");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, errorMess);
				}
			}
			/* 是否为MAC */
			if (rangeFlag == 13) {
				errorMess = whichField + ":输入正确的MAC格式";
				if (textField != null) {
					if (!CheckingUtil.checking(textField.getText(), CheckingUtil.MAC_REGULAR)) {
						JOptionPane.showMessageDialog(null, errorMess);
						if (whichField.equals(ResourceUtil.srcStr(StringKeysLbl.LBL_SOURCE_MAC))) {
							textField.setText("00-00-00-00-00-01");
						} else {
							textField.setText("00-00-00-00-00-02");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, errorMess);
				}
			}
			/* 区间是否为0-524288 */
			if (rangeFlag == 14) {
				errorMess = whichField + ": 范围[0,"+ConstantUtil.CBS_MAXVALUE+"]";
				inputNumber = Integer.parseInt(textField.getText());
				if (inputNumber < 0 || inputNumber > ConstantUtil.CBS_MAXVALUE) {
					showDialog(errorMess);
				}
			}

			/* 区间是否为0-1000000 */
			if (rangeFlag == 15) {

				errorMess = whichField + ": 范围[0,1000000]";
				inputNumber = Integer.parseInt(textField.getText());
				if (inputNumber < 0 || inputNumber > 1000000) {

					showDialog(errorMess);
				}
			}

			/* 区间是否为2-4095 */
			if (rangeFlag == 16) {
				errorMess = whichField + ": 范围[2,4095]";
				inputNumber = Integer.parseInt(textField.getText());
				if (inputNumber < 2 || inputNumber > 4095) {
					showDialog(errorMess);
				}
			}
			
			/* 区间是否为1518-9600 */
			if (rangeFlag == 17) {
				errorMess = whichField + ": 范围[1518,9600]";
				inputNumber = Integer.parseInt(textField.getText());
				if (inputNumber < 1518 || inputNumber > 9600) {
					showDialog(errorMess);
				}
			}
			
			/* 区间是否为0-1000000 */
			if (rangeFlag == 18) {
				errorMess = whichField + ": 范围[0,1000000]";
				inputNumber = Integer.parseInt(textField.getText());
				if (inputNumber < 0 || inputNumber > 1000000) {
					showDialog(errorMess);
				}
			}
			
			/* 区间是否为1600-9600 */
			if (rangeFlag == 19) {
				errorMess = whichField + ": 范围[1600,9600]";
				inputNumber = Integer.parseInt(textField.getText());
				if (inputNumber < 1600 || inputNumber > 9600) {
					showDialog(errorMess);
				}
			}
			
			/* 区间是否为0,100000000*/
			if (rangeFlag == 20) {
				errorMess = whichField + ": 范围[1,100000000]";
				inputNumber = Integer.parseInt(textField.getText());
				if (inputNumber < 1 || inputNumber > 100000000) {
					showDialog(errorMess);
				}
			}
			
			/*TCP/UDP端口号范围 0-65535*/
			if(rangeFlag == 21){
				errorMess = whichField + ": 范围[0, 65535]";
				inputNumber = Integer.parseInt(textField.getText());
				if(inputNumber < 0 || inputNumber > 65535){
					showDialog(errorMess);
				}
			}
			/*范围2-4094*/
			if(rangeFlag == 22){
				errorMess = whichField + ": 范围[2, 4094]";
				inputNumber = Integer.parseInt(textField.getText());
				if(inputNumber < 0 || inputNumber > 4094){
					showDialog(errorMess);
				}
			}
		} catch (Exception e1) {
			showDialog(errorMess);
		}
	}

	private void showDialog(String errorMessage) {

		JOptionPane.showMessageDialog(null, errorMessage);
		if (rangeFlag == 4) {
			textField.setText("1");
		} else if (rangeFlag == 10) {
			textField.setText("2");
		} else if (rangeFlag == 16) {
			textField.setText("2");
		} else if (rangeFlag == 5) {
			textField.setText("1");
		}else if (rangeFlag ==17) {
			textField.setText("1518");
		}else if (rangeFlag ==18) {
			textField.setText("1000000");
		}else if (rangeFlag ==20) {
			textField.setText("1");
		}else if(rangeFlag == 22){
			textField.setText("2");
		}
		else {
			textField.setText("0");
		}
	}

}
