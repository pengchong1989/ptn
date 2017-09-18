package com.nms.ui.ptn.clock.view.cx.time;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnPanel;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;

public class TabPanelOneAttrTCX extends PtnPanel {

	private static final long serialVersionUID = -7152718670416702941L;

	private JLabel mode = null;

	private JComboBox mode_ = null;

	private JLabel clockType = null;

	private JTextField clockType_ = null;

	private JLabel clockAccuracy = null;

	private JTextField clockAccuracy_ = null;

	private JLabel clockVariance = null;

	private JTextField clockVariance_ = null;

	private JLabel priority1 = null;

	private JTextField priority1_ = null;

	private JLabel priority2 = null;

	private JTextField priority2_ = null;

	private JLabel manufacturerOUI = null;

	private JTextField manufacturerOUI_ = null;

	private JLabel clockDomain = null;

	private JTextField clockDomain_ = null;

	private JLabel passThroughClockDomain1 = null;

	private JTextField passThroughClockDomain1_ = null;

	private JLabel passThroughClockDomain2 = null;

	private JCheckBox passThroughClockDomain2__ = null;

	private JTextField passThroughClockDomain2_ = null;

	private JLabel passThroughClockDomain3 = null;

	private JCheckBox passThroughClockDomain3__ = null;

	private JTextField passThroughClockDomain3_ = null;

	private JLabel passThroughClockDomain4 = null;

	private JCheckBox passThroughClockDomain4__ = null;

	private JTextField passThroughClockDomain4_ = null;

	private JLabel passThroughClockDelayMechanism = null;

	private JComboBox passThroughClockDelayMechanism_ = null;

	private JLabel followUpMode = null;

	private JComboBox followUpMode_ = null;

	private JLabel todTransmissionTimeType = null;

	private JComboBox todTransmissionTimeType_ = null;

	private GridBagLayout gridBagLayout = null;

	public TabPanelOneAttrTCX() {

		try {

			init();
			addItemListenerForCheckbox();/*checkbox添加事件，如果选择，那么关联的textfield会被激活*/
			addFocusListenerForTextfield();/*textfield聚焦事件监听，当离开此textfield判断值是否在指定范围内*/
			addKeyListenerForTextfield();/*textfield添加键盘事件监听，只允许数字输入*/
		} catch (Exception e) {

			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void init() throws Exception {

		try {

			gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[] { 100, 200, 100, 5, 200 };
			gridBagLayout.columnWeights = new double[] { 0, 0, 0, 0, 0 };
			gridBagLayout.rowHeights = new int[] { 21, 21, 21, 21, 21, 21, 21, 21, 21 };
			gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0, 0, 0, 0, 0 };

			mode = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_MODAL));
			mode_ = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(this.mode_, "mode");
			clockType = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_CLOCK_TYPE));
			clockType_ = new JTextField("0");
			clockAccuracy = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_CLOCK_ACCURACY));
			clockAccuracy_ = new JTextField("0");
			clockVariance = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_CLOCK_VARIANCE));
			clockVariance_ = new JTextField("0");
			priority1 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PRIORITY_1));
			priority1_ = new JTextField("0");
			priority2 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PRIORITY_2));
			priority2_ = new JTextField("0");
			manufacturerOUI = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_MANUFACTURER_OUI));
			manufacturerOUI_ = new JTextField("0");
			clockDomain = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_CLOCK_DOMAIN));
			clockDomain_ = new JTextField("0");
			passThroughClockDomain1 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PASS_THROUGH_CLOCK_DOMAIN_1));
			passThroughClockDomain1_ = new JTextField("0");
			passThroughClockDomain2 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PASS_THROUGH_CLOCK_DOMAIN_2));
			passThroughClockDomain2__ = new JCheckBox();
			passThroughClockDomain2__.setBackground(Color.WHITE);
			passThroughClockDomain2_ = new JTextField("0");
			passThroughClockDomain2_.setEnabled(false);
			passThroughClockDomain3 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PASS_THROUGH_CLOCK_DOMAIN_3));
			passThroughClockDomain3__ = new JCheckBox();
			passThroughClockDomain3__.setBackground(Color.WHITE);
			passThroughClockDomain3_ = new JTextField("0");
			passThroughClockDomain3_.setEnabled(false);
			passThroughClockDomain4 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PASS_THROUGH_CLOCK_DOMAIN_4));
			passThroughClockDomain4__ = new JCheckBox();
			passThroughClockDomain4__.setBackground(Color.WHITE);
			passThroughClockDomain4_ = new JTextField("0");
			passThroughClockDomain4_.setEnabled(false);
			passThroughClockDelayMechanism = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PASS_THROUGH_CLOCK_DELAY_MECHANISM));
			passThroughClockDelayMechanism_ = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(this.passThroughClockDelayMechanism_, "passThroughClockDelayMechanism");
			followUpMode = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_FOLLOW_UP_MODE));
			followUpMode_ = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(this.followUpMode_, "followUpMode");
			todTransmissionTimeType = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TOD_TRANSMISSION_TIME_TYPE));
			todTransmissionTimeType_ = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(this.todTransmissionTimeType_, "todTransmissionTimeType");

			this.setBackground(Color.WHITE);
			this.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysObj.STRING_ATTRIBUTE)));
			this.add(mode);
			this.add(mode_);
			this.add(clockType);
			this.add(clockType_);
			this.add(clockAccuracy);
			this.add(clockAccuracy_);
			this.add(clockVariance);
			this.add(clockVariance_);
			this.add(priority1);
			this.add(priority1_);
			this.add(priority2);
			this.add(priority2_);
			this.add(manufacturerOUI);
			this.add(manufacturerOUI_);
			this.add(clockDomain);
			this.add(clockDomain_);
			this.add(passThroughClockDomain1);
			this.add(passThroughClockDomain1_);
			this.add(passThroughClockDomain2);
			this.add(passThroughClockDomain2__);
			this.add(passThroughClockDomain2_);
			this.add(passThroughClockDomain3);
			this.add(passThroughClockDomain3__);
			this.add(passThroughClockDomain3_);
			this.add(passThroughClockDomain4);
			this.add(passThroughClockDomain4__);
			this.add(passThroughClockDomain4_);
			this.add(passThroughClockDelayMechanism);
			this.add(passThroughClockDelayMechanism_);
			this.add(followUpMode);
			this.add(followUpMode_);
			this.add(todTransmissionTimeType);
			this.add(todTransmissionTimeType_);

			this.setGridBagLayout();/* Tab页一 网元时钟状态/属性 -> 属性布局 */
			this.setLayout(gridBagLayout);
		} catch (Exception e) {

			throw e;
		}
	}

	/**
	 * <p>
	 * Tab页一 网元时钟状态/属性 -> 属性布局
	 * </p>
	 * 
	 * @throws Exception
	 */
	private void setGridBagLayout() throws Exception {

		GridBagConstraints gridBagConstraints = null;
		try {

			gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.insets = new Insets(5, 10, 0, 0);
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(mode, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 1;
			gridBagLayout.setConstraints(clockType, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 2;
			gridBagLayout.setConstraints(clockAccuracy, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 3;
			gridBagLayout.setConstraints(clockVariance, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 4;
			gridBagLayout.setConstraints(priority1, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 5;
			gridBagLayout.setConstraints(priority2, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 6;
			gridBagLayout.setConstraints(manufacturerOUI, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 7;
			gridBagLayout.setConstraints(clockDomain, gridBagConstraints);

			/** *************************************************************************************** */
			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(mode_, gridBagConstraints);

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 1;
			gridBagLayout.setConstraints(clockType_, gridBagConstraints);

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 2;
			gridBagLayout.setConstraints(clockAccuracy_, gridBagConstraints);

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 3;
			gridBagLayout.setConstraints(clockVariance_, gridBagConstraints);

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 4;
			gridBagLayout.setConstraints(priority1_, gridBagConstraints);

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 5;
			gridBagLayout.setConstraints(priority2_, gridBagConstraints);

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 6;
			gridBagLayout.setConstraints(manufacturerOUI_, gridBagConstraints);

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 7;
			gridBagLayout.setConstraints(clockDomain_, gridBagConstraints);

			/** ***************************************************************** */
			gridBagConstraints.insets = new Insets(5, 0, 0, 0);
			gridBagConstraints.gridx = 3;
			gridBagConstraints.gridy = 1;
			gridBagLayout.setConstraints(passThroughClockDomain2__, gridBagConstraints);

			gridBagConstraints.gridx = 3;
			gridBagConstraints.gridy = 2;
			gridBagLayout.setConstraints(passThroughClockDomain3__, gridBagConstraints);

			gridBagConstraints.gridx = 3;
			gridBagConstraints.gridy = 3;
			gridBagLayout.setConstraints(passThroughClockDomain4__, gridBagConstraints);

			/** *************************************************************************************** */
			gridBagConstraints.insets = new Insets(5, 200, 0, 0);
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(passThroughClockDomain1, gridBagConstraints);

			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 1;
			gridBagLayout.setConstraints(passThroughClockDomain2, gridBagConstraints);

			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 2;
			gridBagLayout.setConstraints(passThroughClockDomain3, gridBagConstraints);

			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 3;
			gridBagLayout.setConstraints(passThroughClockDomain4, gridBagConstraints);

			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 4;
			gridBagLayout.setConstraints(passThroughClockDelayMechanism, gridBagConstraints);

			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 5;
			gridBagLayout.setConstraints(followUpMode, gridBagConstraints);

			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 6;
			gridBagLayout.setConstraints(todTransmissionTimeType, gridBagConstraints);

			/** *************************************************************************************** */
			gridBagConstraints.insets = new Insets(5, 10, 0, 10);
			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints.gridx = 4;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(passThroughClockDomain1_, gridBagConstraints);

			gridBagConstraints.gridx = 4;
			gridBagConstraints.gridy = 1;
			gridBagLayout.setConstraints(passThroughClockDomain2_, gridBagConstraints);

			gridBagConstraints.gridx = 4;
			gridBagConstraints.gridy = 2;
			gridBagLayout.setConstraints(passThroughClockDomain3_, gridBagConstraints);

			gridBagConstraints.gridx = 4;
			gridBagConstraints.gridy = 3;
			gridBagLayout.setConstraints(passThroughClockDomain4_, gridBagConstraints);

			gridBagConstraints.gridx = 4;
			gridBagConstraints.gridy = 4;
			gridBagLayout.setConstraints(passThroughClockDelayMechanism_, gridBagConstraints);

			gridBagConstraints.gridx = 4;
			gridBagConstraints.gridy = 5;
			gridBagLayout.setConstraints(followUpMode_, gridBagConstraints);

			gridBagConstraints.gridx = 4;
			gridBagConstraints.gridy = 6;
			gridBagLayout.setConstraints(todTransmissionTimeType_, gridBagConstraints);

		} catch (Exception e) {

			throw e;
		} finally {

			gridBagConstraints = null;
		}
	}

	/**
	 * <p>
	 * 为3个Checkbox添加事件
	 * </p>
	 * 
	 * @throws Exception
	 */
	private void addItemListenerForCheckbox() throws Exception {

		try {

			/*checkbox 1 添加监听 */
			passThroughClockDomain2__.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent itemEvent) {

					JCheckBox box = (JCheckBox) itemEvent.getItemSelectable();
					if (box.isSelected())

						passThroughClockDomain2_.setEnabled(true);

					else

						passThroughClockDomain2_.setEnabled(false);

				}
			});

			/*checkbox 2 添加监听 */
			passThroughClockDomain3__.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent itemEvent) {

					JCheckBox box = (JCheckBox) itemEvent.getItemSelectable();
					if (box.isSelected())

						passThroughClockDomain3_.setEnabled(true);

					else

						passThroughClockDomain3_.setEnabled(false);

				}
			});

			/*checkbox 3 添加监听 */
			passThroughClockDomain4__.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent itemEvent) {

					JCheckBox box = (JCheckBox) itemEvent.getItemSelectable();
					if (box.isSelected())

						passThroughClockDomain4_.setEnabled(true);

					else

						passThroughClockDomain4_.setEnabled(false);

				}
			});
		} catch (Exception e) {

			throw e;
		}
	}

	/**
	 * <p>
	 * textfield添加监听，只允许输入数字
	 * </p>
	 * @throws Exception
	 */
	private void addKeyListenerForTextfield()throws Exception{
		
		TextFiledKeyListener textFIledKeyListener=null;
		try{
			
			/* 为1：只接受数字 */
			textFIledKeyListener = new TextFiledKeyListener(1);
			clockType_.addKeyListener(textFIledKeyListener);
			clockAccuracy_.addKeyListener(textFIledKeyListener);
			clockVariance_.addKeyListener(textFIledKeyListener);
			priority1_.addKeyListener(textFIledKeyListener);
			priority2_.addKeyListener(textFIledKeyListener);
			manufacturerOUI_.addKeyListener(textFIledKeyListener);
			clockDomain_.addKeyListener(textFIledKeyListener);
			passThroughClockDomain1_.addKeyListener(textFIledKeyListener);
			passThroughClockDomain2_.addKeyListener(textFIledKeyListener);
			passThroughClockDomain3_.addKeyListener(textFIledKeyListener);
			passThroughClockDomain4_.addKeyListener(textFIledKeyListener);
		}catch(Exception e){
			
			throw e;
		}
	}
	
	/**
	 * <p>
	 * 判断输入数值是否在指定区间内
	 * </p>
	 * @throws Exception
	 */
	private void addFocusListenerForTextfield()throws Exception{
		
		TextfieldFocusListener textfieldFocusListener=null;
		String whichTextTield=null;
		try{
			
			whichTextTield=ResourceUtil.srcStr(StringKeysLbl.LBL_CLOCK_TYPE);
			textfieldFocusListener = new TextfieldFocusListener(whichTextTield,1,clockType_);
			clockType_.addFocusListener(textfieldFocusListener);
			
			whichTextTield=ResourceUtil.srcStr(StringKeysLbl.LBL_CLOCK_ACCURACY);
			textfieldFocusListener = new TextfieldFocusListener(whichTextTield,1,clockAccuracy_);
			clockAccuracy_.addFocusListener(textfieldFocusListener);
			
			whichTextTield=ResourceUtil.srcStr(StringKeysLbl.LBL_CLOCK_VARIANCE);
			textfieldFocusListener = new TextfieldFocusListener(whichTextTield,2,clockVariance_);
			clockVariance_.addFocusListener(textfieldFocusListener);
			
			whichTextTield=ResourceUtil.srcStr(StringKeysLbl.LBL_PRIORITY_1);
			textfieldFocusListener = new TextfieldFocusListener(whichTextTield,1,priority1_);
			priority1_.addFocusListener(textfieldFocusListener);
			
			whichTextTield=ResourceUtil.srcStr(StringKeysLbl.LBL_PRIORITY_2);
			textfieldFocusListener = new TextfieldFocusListener(whichTextTield,1,priority2_);
			priority2_.addFocusListener(textfieldFocusListener);
			
			whichTextTield=ResourceUtil.srcStr(StringKeysLbl.LBL_MANUFACTURER_OUI);
			textfieldFocusListener = new TextfieldFocusListener(whichTextTield,3,manufacturerOUI_);
			manufacturerOUI_.addFocusListener(textfieldFocusListener);
			
			whichTextTield=ResourceUtil.srcStr(StringKeysLbl.LBL_CLOCK_DOMAIN);
			textfieldFocusListener = new TextfieldFocusListener(whichTextTield,1,clockDomain_);
			clockDomain_.addFocusListener(textfieldFocusListener);
			
			whichTextTield=ResourceUtil.srcStr(StringKeysLbl.LBL_PASS_THROUGH_CLOCK_DOMAIN_1);
			textfieldFocusListener = new TextfieldFocusListener(whichTextTield,1,passThroughClockDomain1_);
			passThroughClockDomain1_.addFocusListener(textfieldFocusListener);
			
			whichTextTield=ResourceUtil.srcStr(StringKeysLbl.LBL_PASS_THROUGH_CLOCK_DOMAIN_2);
			textfieldFocusListener = new TextfieldFocusListener(whichTextTield,1,passThroughClockDomain2_);
			passThroughClockDomain2_.addFocusListener(textfieldFocusListener);
			
			whichTextTield=ResourceUtil.srcStr(StringKeysLbl.LBL_PASS_THROUGH_CLOCK_DOMAIN_3);
			textfieldFocusListener = new TextfieldFocusListener(whichTextTield,1,passThroughClockDomain3_);
			passThroughClockDomain3_.addFocusListener(textfieldFocusListener);
			
			whichTextTield=ResourceUtil.srcStr(StringKeysLbl.LBL_PASS_THROUGH_CLOCK_DOMAIN_4);
			textfieldFocusListener = new TextfieldFocusListener(whichTextTield,1,passThroughClockDomain4_);
			passThroughClockDomain4_.addFocusListener(textfieldFocusListener);
		}catch(Exception e){
			
			throw e;
		}
	}

	public JLabel getMode() {
		return mode;
	}

	public void setMode(JLabel mode) {
		this.mode = mode;
	}

	public JComboBox getMode_() {
		return mode_;
	}

	public void setMode_(JComboBox mode) {
		mode_ = mode;
	}

	public JLabel getClockType() {
		return clockType;
	}

	public void setClockType(JLabel clockType) {
		this.clockType = clockType;
	}

	public JTextField getClockType_() {
		return clockType_;
	}

	public void setClockType_(JTextField clockType) {
		clockType_ = clockType;
	}

	public JLabel getClockAccuracy() {
		return clockAccuracy;
	}

	public void setClockAccuracy(JLabel clockAccuracy) {
		this.clockAccuracy = clockAccuracy;
	}

	public JTextField getClockAccuracy_() {
		return clockAccuracy_;
	}

	public void setClockAccuracy_(JTextField clockAccuracy) {
		clockAccuracy_ = clockAccuracy;
	}

	public JLabel getClockVariance() {
		return clockVariance;
	}

	public void setClockVariance(JLabel clockVariance) {
		this.clockVariance = clockVariance;
	}

	public JTextField getClockVariance_() {
		return clockVariance_;
	}

	public void setClockVariance_(JTextField clockVariance) {
		clockVariance_ = clockVariance;
	}

	public JLabel getPriority1() {
		return priority1;
	}

	public void setPriority1(JLabel priority1) {
		this.priority1 = priority1;
	}

	public JTextField getPriority1_() {
		return priority1_;
	}

	public void setPriority1_(JTextField priority1) {
		priority1_ = priority1;
	}

	public JLabel getPriority2() {
		return priority2;
	}

	public void setPriority2(JLabel priority2) {
		this.priority2 = priority2;
	}

	public JTextField getPriority2_() {
		return priority2_;
	}

	public void setPriority2_(JTextField priority2) {
		priority2_ = priority2;
	}

	public JLabel getManufacturerOUI() {
		return manufacturerOUI;
	}

	public void setManufacturerOUI(JLabel manufacturerOUI) {
		this.manufacturerOUI = manufacturerOUI;
	}

	public JTextField getManufacturerOUI_() {
		return manufacturerOUI_;
	}

	public void setManufacturerOUI_(JTextField manufacturerOUI) {
		manufacturerOUI_ = manufacturerOUI;
	}

	public JLabel getClockDomain() {
		return clockDomain;
	}

	public void setClockDomain(JLabel clockDomain) {
		this.clockDomain = clockDomain;
	}

	public JTextField getClockDomain_() {
		return clockDomain_;
	}

	public void setClockDomain_(JTextField clockDomain) {
		clockDomain_ = clockDomain;
	}

	public JLabel getPassThroughClockDomain1() {
		return passThroughClockDomain1;
	}

	public void setPassThroughClockDomain1(JLabel passThroughClockDomain1) {
		this.passThroughClockDomain1 = passThroughClockDomain1;
	}

	public JTextField getPassThroughClockDomain1_() {
		return passThroughClockDomain1_;
	}

	public void setPassThroughClockDomain1_(JTextField passThroughClockDomain1) {
		passThroughClockDomain1_ = passThroughClockDomain1;
	}

	public JLabel getPassThroughClockDomain2() {
		return passThroughClockDomain2;
	}

	public void setPassThroughClockDomain2(JLabel passThroughClockDomain2) {
		this.passThroughClockDomain2 = passThroughClockDomain2;
	}

	public JCheckBox getPassThroughClockDomain2__() {
		return passThroughClockDomain2__;
	}

	public void setPassThroughClockDomain2__(JCheckBox passThroughClockDomain2) {
		passThroughClockDomain2__ = passThroughClockDomain2;
	}

	public JTextField getPassThroughClockDomain2_() {
		return passThroughClockDomain2_;
	}

	public void setPassThroughClockDomain2_(JTextField passThroughClockDomain2) {
		passThroughClockDomain2_ = passThroughClockDomain2;
	}

	public JLabel getPassThroughClockDomain3() {
		return passThroughClockDomain3;
	}

	public void setPassThroughClockDomain3(JLabel passThroughClockDomain3) {
		this.passThroughClockDomain3 = passThroughClockDomain3;
	}

	public JCheckBox getPassThroughClockDomain3__() {
		return passThroughClockDomain3__;
	}

	public void setPassThroughClockDomain3__(JCheckBox passThroughClockDomain3) {
		passThroughClockDomain3__ = passThroughClockDomain3;
	}

	public JTextField getPassThroughClockDomain3_() {
		return passThroughClockDomain3_;
	}

	public void setPassThroughClockDomain3_(JTextField passThroughClockDomain3) {
		passThroughClockDomain3_ = passThroughClockDomain3;
	}

	public JLabel getPassThroughClockDomain4() {
		return passThroughClockDomain4;
	}

	public void setPassThroughClockDomain4(JLabel passThroughClockDomain4) {
		this.passThroughClockDomain4 = passThroughClockDomain4;
	}

	public JCheckBox getPassThroughClockDomain4__() {
		return passThroughClockDomain4__;
	}

	public void setPassThroughClockDomain4__(JCheckBox passThroughClockDomain4) {
		passThroughClockDomain4__ = passThroughClockDomain4;
	}

	public JTextField getPassThroughClockDomain4_() {
		return passThroughClockDomain4_;
	}

	public void setPassThroughClockDomain4_(JTextField passThroughClockDomain4) {
		passThroughClockDomain4_ = passThroughClockDomain4;
	}

	public JLabel getPassThroughClockDelayMechanism() {
		return passThroughClockDelayMechanism;
	}

	public void setPassThroughClockDelayMechanism(
			JLabel passThroughClockDelayMechanism) {
		this.passThroughClockDelayMechanism = passThroughClockDelayMechanism;
	}

	public JComboBox getPassThroughClockDelayMechanism_() {
		return passThroughClockDelayMechanism_;
	}

	public void setPassThroughClockDelayMechanism_(
			JComboBox passThroughClockDelayMechanism) {
		passThroughClockDelayMechanism_ = passThroughClockDelayMechanism;
	}

	public JLabel getFollowUpMode() {
		return followUpMode;
	}

	public void setFollowUpMode(JLabel followUpMode) {
		this.followUpMode = followUpMode;
	}

	public JComboBox getFollowUpMode_() {
		return followUpMode_;
	}

	public void setFollowUpMode_(JComboBox followUpMode) {
		followUpMode_ = followUpMode;
	}

	public JLabel getTodTransmissionTimeType() {
		return todTransmissionTimeType;
	}

	public void setTodTransmissionTimeType(JLabel todTransmissionTimeType) {
		this.todTransmissionTimeType = todTransmissionTimeType;
	}

	public JComboBox getTodTransmissionTimeType_() {
		return todTransmissionTimeType_;
	}

	public void setTodTransmissionTimeType_(JComboBox todTransmissionTimeType) {
		todTransmissionTimeType_ = todTransmissionTimeType;
	}

	public GridBagLayout getGridBagLayout() {
		return gridBagLayout;
	}

	public void setGridBagLayout(GridBagLayout gridBagLayout) {
		this.gridBagLayout = gridBagLayout;
	}
	
}
