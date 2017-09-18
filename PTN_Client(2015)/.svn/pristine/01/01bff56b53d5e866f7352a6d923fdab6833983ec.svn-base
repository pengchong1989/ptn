package com.nms.ui.manager.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysLbl;

public class PtnSpinner extends JSpinner {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5477807866512400215L;

	private JCheckBox chb;// 复选框控件
	private int maximum;// 最大值
	private int defaultData;// 默认值
	JTextField txt = null;// Jspinner的子组件
	private int stepSize;
	private String whichField;

	/**
	 * 类型：等待恢复时间
	 */
	public static final int TYPE_DELAYTIME = 1;
	/**
	 * 类型：等待恢复时间
	 */
	public static final int TYPE_WAITTIME = 2;

	/**
	 * 
	 * 创建一个新的实例PtnSpinner.
	 * 
	 */
	public PtnSpinner() {

	}

	/**
	 * 
	 * 创建一个新的实例PtnSpinner.
	 * 
	 * @throws Exception
	 * 
	 */
	public PtnSpinner(JCheckBox chb, int maximum) throws Exception {
		if (chb == null) {
			throw new Exception("create ptnJspinner error : JCheckBox is null");
		}
		if (0 == maximum) {
			throw new Exception("create ptnJspinner error : Maximum is 0");
		}

		this.chb = chb;
		this.maximum = maximum;
		this.txt = ((JSpinner.NumberEditor) (this.getComponents()[2])).getTextField();

		this.init(0);
	}

	/**
	 * 
	 * 创建一个新的实例PtnSpinner.
	 * 
	 * @throws Exception
	 * 
	 */
	public PtnSpinner(JCheckBox chb, int maximum, int minmum, int defaultData, int stepSize) throws Exception {
		if (chb == null) {
			throw new Exception("create ptnJspinner error : JCheckBox is null");
		}
		if (0 == maximum) {
			throw new Exception("create ptnJspinner error : Maximum is 0");
		}
		// if(0==defaultData){
		// throw new Exception("create ptnJspinner error : defaultData is 0");
		// }
		this.stepSize = stepSize;
		this.chb = chb;
		this.maximum = maximum;
		this.defaultData = defaultData;
		this.txt = ((JSpinner.NumberEditor) (this.getComponents()[2])).getTextField();

		this.init(minmum);
	}

	public PtnSpinner(int maximum, int defaultData, int stepSize, String whichField) throws Exception {

		if (0 == maximum) {
			throw new Exception("create ptnJspinner error : Maximum is 0");
		}
		// if(0==defaultData){
		// throw new Exception("create ptnJspinner error : defaultData is 0");
		// }
		this.stepSize = stepSize;
		this.maximum = maximum;
		this.defaultData = defaultData;
		this.txt = ((JSpinner.NumberEditor) (this.getComponents()[2])).getTextField();
		this.whichField = whichField;
		this.init(defaultData);
	}

	public PtnSpinner(int maximum, int defaultData, int stepSize) throws Exception {

		if (0 == maximum) {
			throw new Exception("create ptnJspinner error : Maximum is 0");
		}
		// if(0==defaultData){
		// throw new Exception("create ptnJspinner error : defaultData is 0");
		// }
		this.stepSize = stepSize;
		this.maximum = maximum;
		this.defaultData = defaultData;
		this.txt = ((JSpinner.NumberEditor) (this.getComponents()[2])).getTextField();
		this.init(defaultData);
	}

	/**
	 * 新实例
	 * 
	 * @author kk
	 * @param defaultData
	 *            默认值
	 * @param minimum
	 *            最小值
	 * @param maximum
	 *            最大值
	 * @param stepSize
	 *            步长
	 */
	public PtnSpinner(int defaultData, int minimum, int maximum, int stepSize) {
		super(new SpinnerNumberModel(defaultData, minimum, maximum, stepSize));
		this.txt = ((JSpinner.NumberEditor) (this.getComponents()[2])).getTextField();
	}
	
	/**
	 * 根据类型创建默认的实例 
	 * 如传入等待恢复时间  控件的最大值最小值步长都为默认值
	 * @author kk
	 * @param type 对应此类中的静态变量
	 */
	@SuppressWarnings("static-access")
	public PtnSpinner(int type) {
		if (type == this.TYPE_DELAYTIME) {
			super.setModel(new SpinnerNumberModel(0,0,10000,100));
		}else if(type == this.TYPE_WAITTIME){
			super.setModel(new SpinnerNumberModel(5, 1, 12,1));
		}
		this.txt = ((JSpinner.NumberEditor) (this.getComponents()[2])).getTextField();
	}

	/**
	 * 初始化控件
	 * 
	 * @throws Exception
	 */
	private void init(int minValue) throws Exception {
		SpinnerNumberModel numModel = (SpinnerNumberModel) this.getModel();
		numModel.setValue(new Integer(0));
		numModel.setMaximum(maximum);
		if (this.defaultData == 60) {
			numModel.setMinimum(new Integer(60));
		} else {
			numModel.setMinimum(new Integer(minValue));
		}
		numModel.setStepSize(new Integer(stepSize));

		this.addListener(minValue);
	}

	/**
	 * 事件监听
	 * 
	 * @throws Exception
	 */
	private void addListener(final int devalue) throws Exception {

		txt.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					if (stepSize == 64) {
						verifySpeed();
					} else {
						verifyData(devalue);
					}
				} catch (Exception e) {
					ExceptionManage.dispose(e, this.getClass());
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
			}

		});

		if (chb != null) {
			chb.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						isSelected();
					} catch (Exception e) {
						ExceptionManage.dispose(e, this.getClass());
					}
				}

			});
		}
	}

	/**
	 * 验证端口限速
	 */
	private void verifySpeed() {
		int speed = 0;
		int temp = 0;
		boolean flag = true;
		try {
			// 验证数据是否是数字
			flag = checkingNumber(txt.getText().replace(",", ""));
			// 验证数据是否合理
			if (flag) {
				try {
					speed = Integer.parseInt(txt.getText().replace(",", ""));
					if (speed >= maximum) {
						speed = maximum;
					} else if (speed <= defaultData) {
						speed = defaultData;
					} else {
						temp = speed % stepSize;
						if (temp > 0 && temp < stepSize) {
							speed = (speed / stepSize + 1) * stepSize;
						}
					}
				} catch (Exception e) {
					speed = defaultData;
				}
			} else {
				speed = defaultData;
			}

			txt.setText(speed + "");
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			speed = 0;
			temp = 0;
		}

	}

	/**
	 * 核选框监听事件
	 * 
	 * @throws Exception
	 */
	private void isSelected() throws Exception {
		try {
			if (!chb.isSelected()) {
				this.setNoLimit();
			} else {
				this.setLimit();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}

	}

	/**
	 * 验证数据输入是否合理
	 * 
	 * @throws Exception
	 */
	private void verifyData(int devalue) throws Exception {
		int speed = 0;
		int temp = 0;
		boolean flag = true;
		try {
			// 验证数据是否是数字
			flag = checkingNumber(txt.getText().replace(",", ""));
			// 验证数据是否合理
			if (flag) {
				try {
					speed = Integer.parseInt(txt.getText().replace(",", ""));
					if (this.defaultData == 60) {
						if (speed < 60) {
							speed = 60;
						} else if (speed > maximum) {
							speed = maximum;
						}
					} else {
						if (speed < devalue) {
							if (maximum == 10000) {
								JOptionPane.showMessageDialog(null, whichField + ": 范围[0,10000]");
							} else if (maximum == 12 || maximum == 10) {
								JOptionPane.showMessageDialog(null, whichField + ": 范围[" + devalue + "," + maximum + "]");
							}
							speed = devalue;
						} else if (speed > maximum) {
							if ((defaultData == 0 && maximum == 10000)) {
								JOptionPane.showMessageDialog(null, whichField + ": 范围[0,10000]");
							} else if ((maximum == 12 || maximum == 10) && (defaultData == 0 || defaultData == 1)) {
								JOptionPane.showMessageDialog(null, whichField + ": 范围[" + devalue + "," + maximum + "]");
							}
							speed = maximum;
						}
					}

					temp = speed % stepSize;
					if (temp > 0 && temp < stepSize) {
						speed = (speed / stepSize + 1) * stepSize;
					}
					txt.setText(speed + "");
				} catch (Exception e) {

					if ((defaultData == 0 && maximum == 10000)) {
						JOptionPane.showMessageDialog(null, whichField + ": 范围[0,10000]");
					} else if ((maximum == 12 || maximum == 10) && (defaultData == 0 || defaultData == 1)) {
						JOptionPane.showMessageDialog(null, whichField + ": 范围[" + devalue + "," + maximum + "]");
					}
					speed = maximum;
					txt.setText(speed + "");
				}
			} else {
				if (this.defaultData == 60) {
					txt.setText("60");
				} else {
					if ((defaultData == 0 && maximum == 10000)) {
						JOptionPane.showMessageDialog(null, whichField + ": 范围[0,10000]");
					} else if ((maximum == 12 || maximum == 10) && (defaultData == 0 || defaultData == 1)) {
						JOptionPane.showMessageDialog(null, whichField + ": 范围[" + devalue + "," + maximum + "]");
					}
					txt.setText(String.valueOf(devalue));
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			speed = 0;
			temp = 0;
		}
	}

	/**
	 * 文本框验证是否为数字
	 * 
	 * @return
	 */
	private boolean checkingNumber(String txtData) throws Exception {
		if (txtData.trim().length() > 0) {
			Pattern pattern = Pattern.compile("^[0-9_]+$");
			Matcher matcher = pattern.matcher(txtData.trim());

			return matcher.find();
		} else {
			return true;
		}
	}

	/**
	 * 初始化数据时给文本框赋值
	 * 
	 * @throws Exception
	 */
	public void setTxtData(String initData) throws Exception {
		if ("-1".equals(initData) || "0".equals(initData)) {
			if (this.defaultData == 60) {
				txt.setText(initData);
			} else {
				txt.setText(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT_EXPORT_SPEED_NO_LIMIT));
			}
			// txt.setEditable(false);
			this.setEnabled(false);
			chb.setSelected(false);
		} else if ("".equals(initData) || null == initData) {
			// 赋默认值
			txt.setText(defaultData + "");
			txt.setEditable(true);
			chb.setSelected(true);
		} else {
			txt.setText(initData);
			txt.setEditable(true);
			chb.setSelected(true);
		}
	}

	/**
	 * 从界面上收集用户输入的数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTxtData() throws Exception {
		String checkData = txt.getText().replace(",", "");
		boolean flag = checkingNumber(checkData);
		if (flag) {
			return checkData;
		} else {
			if (this.defaultData == 60) {
				checkData = "60";
			} else {
				checkData = "-1";
			}
			return checkData;
		}
	}

	/**
	 * 不限速
	 * 
	 * @throws Exception
	 */
	private void setNoLimit() throws Exception {
		if (this.defaultData == 60) {
			txt.setText("0");
		} else {
			txt.setText(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT_EXPORT_SPEED_NO_LIMIT));
		}
		txt.setEditable(false);
		this.setEnabled(false);
	}

	/**
	 * 限速
	 * 
	 * @throws Exception
	 */
	private void setLimit() throws Exception {
		if (this.defaultData == 60) {
			txt.setText("60");
		} else {
			txt.setText("0");
		}
		txt.setEditable(true);
		this.setEnabled(true);
	}

	public JCheckBox getChb() {
		return chb;
	}

	public void setChb(JCheckBox chb) {
		this.chb = chb;
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	public JTextField getTxt() {
		return txt;
	}

	public void setTxt(JTextField txt) {
		this.txt = txt;
	}

	public int getDefaultData() {
		return defaultData;
	}

	public void setDefaultData(int defaultData) {
		this.defaultData = defaultData;
	}

	public int getStepSize() {
		return stepSize;
	}

	public void setStepSize(int stepSize) {
		this.stepSize = stepSize;
	}

}
