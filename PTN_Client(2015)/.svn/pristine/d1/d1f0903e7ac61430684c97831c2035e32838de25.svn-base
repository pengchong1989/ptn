/*
 * UpdatePortDialog.java
 *
 * Created on __DATE__, __TIME__
 */

package com.nms.ui.ptn.systemconfig.dialog.fieldConfig.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.jtattoo.plaf.BaseBorders.TextFieldBorder;
import com.nms.db.bean.system.Field;
import com.nms.db.bean.system.WorkIps;
import com.nms.db.enums.EOperationLogType;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.CheckingUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.keys.StringKeysTitle;

/**
 * 
 * @author pc
 */
public class WorkIpDoalog extends PtnDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5734796126953088912L;
	private Field info;
	private WorkIpDoalog view;

	public WorkIpDoalog(Field field) {
		view = this;
		super.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_WORKIP));
		info = field;
		initComponents();
		setLayout();
		refreshUIData(info);
		addListeners();
	}

	public void refreshUIData(Field field) {
		if (field.getWorkIps()!= null) {
			workIPTextField.setText(field.getWorkIps().getWorkIp1());
			if(field.getWorkIps().getWorkIp1() != null){
				workIPJRadioButton1.setSelected(true);
				workIPTextField.setEditable(true);
			}
			workIPTextField2.setText(field.getWorkIps().getWorkIp2());
			if(field.getWorkIps().getWorkIp2() != null){
				workIPJRadioButton2.setSelected(true);
				workIPTextField2.setEditable(true);
			}
			workIPTextField3.setText(field.getWorkIps().getWorkIp3());
			if(field.getWorkIps().getWorkIp3() != null){
				workIPJCheckBox3.setSelected(true);
				workIPTextField3.setEditable(true);
			}
			workIPTextField4.setText(field.getWorkIps().getWorkIp4());
			if(field.getWorkIps().getWorkIp4() != null){
				workIPJCheckBox4.setSelected(true);
				workIPTextField4.setEditable(true);
			}
			workIPTextField5.setText(field.getWorkIps().getWorkIp5());
			if(field.getWorkIps().getWorkIp5() != null){
				workIPJCheckBox5.setSelected(true);
				workIPTextField5.setEditable(true);
			}
			workIPTextField6.setText(field.getWorkIps().getWorkIp6());
			if(field.getWorkIps().getWorkIp6() != null){
				workIPJCheckBox6.setSelected(true);
				workIPTextField6.setEditable(true);
			}
		}
	}

	private void initComponents() {
		jpanel = new JPanel();
		lblMessage = new JLabel();
		workIPLable = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_WORKIP) + "1 IP");
		workIPJRadioButton1 = new JCheckBox();
		workIPTextField = new JTextField();
		workIPTextField.setEditable(false);
		workIPLable2 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_WORKIP) + "2 IP");
		workIPJRadioButton2 = new JCheckBox();
		workIPTextField2 = new JTextField();
		workIPTextField2.setEditable(false);
		workIPLable3 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_WORKIP) + "3 IP");
		workIPJCheckBox3 = new JCheckBox();
		workIPTextField3 = new JTextField();
		workIPTextField3.setEditable(false);
		workIPLable4 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_WORKIP) + "4 IP");
		workIPJCheckBox4 = new JCheckBox();
		workIPTextField4 = new JTextField();
		workIPTextField4.setEditable(false);
		workIPLable5 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_WORKIP) + "5 IP");
		workIPJCheckBox5 = new JCheckBox();
		workIPTextField5 = new JTextField();
		workIPTextField5.setEditable(false);
		workIPLable6 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_WORKIP) + "6 IP");
		workIPJCheckBox6 = new JCheckBox();
		workIPTextField6 = new JTextField();
		workIPTextField6.setEditable(false);
		saveBtn = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE),true);
		cancelBtn = new javax.swing.JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		buttonJPanel = new JPanel();
	}

	public void setLayout() {
		setE1InfoPanelLayout();
		setButtonLayout();
		// this.add(BorderLayout.NORTH,e1InfoJPanel);
		// this.add(BorderLayout.SOUTH,b)
		GridBagConstraints c = null;
		c = new GridBagConstraints();
		GridBagLayout contentLayout = new GridBagLayout();
		contentLayout.columnWidths = new int[] { 300, 100 };
		contentLayout.columnWeights = new double[] { 0, 0 };
		contentLayout.rowHeights = new int[] { 15, 35 };
		contentLayout.rowWeights = new double[] { 0, 0 };
		c.fill = GridBagConstraints.BOTH;
		this.setLayout(contentLayout);

		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 10);
		contentLayout.setConstraints(jpanel, c);
		this.add(jpanel);

		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		contentLayout.setConstraints(buttonJPanel, c);
		this.add(buttonJPanel);

	}

	public void setE1InfoPanelLayout() {
		GridBagConstraints c = null;
		c = new GridBagConstraints();
		GridBagLayout layout = new GridBagLayout();

		layout.columnWidths = new int[] { 40, 150 };
		layout.columnWeights = new double[] { 0, 0 };
		layout.rowHeights = new int[] { 10, 25 };
		layout.rowWeights = new double[] { 0, 0 };
		c.fill = GridBagConstraints.BOTH;
		this.jpanel.setLayout(layout);

		/* 第一行 */
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(lblMessage, c);
		this.jpanel.add(lblMessage);

		/* 第二行 */
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.workIPLable, c);
		this.jpanel.add(this.workIPLable);

		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.workIPTextField, c);
		this.jpanel.add(this.workIPTextField);

		c.gridx = 2;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.workIPJRadioButton1, c);
		this.jpanel.add(this.workIPJRadioButton1);

		/* 第三行 */
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.workIPLable2, c);
		this.jpanel.add(this.workIPLable2);

		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.workIPTextField2, c);
		this.jpanel.add(this.workIPTextField2);

		c.gridx = 2;
		c.gridy = 2;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.workIPJRadioButton2, c);
		this.jpanel.add(this.workIPJRadioButton2);

		/* 第四行 */
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.workIPLable3, c);
		this.jpanel.add(this.workIPLable3);

		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.workIPTextField3, c);
		this.jpanel.add(this.workIPTextField3);

		c.gridx = 2;
		c.gridy = 3;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.workIPJCheckBox3, c);
		this.jpanel.add(this.workIPJCheckBox3);

		/* 第五行 */
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.workIPLable4, c);
		this.jpanel.add(this.workIPLable4);

		c.gridx = 1;
		c.gridy = 4;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.workIPTextField4, c);
		this.jpanel.add(this.workIPTextField4);

		c.gridx = 2;
		c.gridy = 4;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.workIPJCheckBox4, c);
		this.jpanel.add(this.workIPJCheckBox4);

		/* 第六行 */
		c.gridx = 0;
		c.gridy = 5;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.workIPLable5, c);
		this.jpanel.add(this.workIPLable5);

		c.gridx = 1;
		c.gridy = 5;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.workIPTextField5, c);
		this.jpanel.add(this.workIPTextField5);

		c.gridx = 2;
		c.gridy = 5;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.workIPJCheckBox5, c);
		this.jpanel.add(this.workIPJCheckBox5);

		/* 第七行 */
		c.gridx = 0;
		c.gridy = 6;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.workIPLable6, c);
		this.jpanel.add(this.workIPLable6);

		c.gridx = 1;
		c.gridy = 6;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.workIPTextField6, c);
		this.jpanel.add(this.workIPTextField6);

		c.gridx = 2;
		c.gridy = 6;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.workIPJCheckBox6, c);
		this.jpanel.add(this.workIPJCheckBox6);

	}

	public void setButtonLayout() {
		buttonJPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonJPanel.add(saveBtn);
		buttonJPanel.add(cancelBtn);

	}

	private void addListeners() {
		saveBtn.addActionListener(new MyActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					List<String> workips = new ArrayList<String>();
					getWorkIPs(workips);
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}

			@Override
			public boolean checking() {
				// TODO Auto-generated method stub
				return true;
			}
		});

		cancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		workIPJRadioButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				jcheckSelect(workIPJRadioButton1,workIPTextField);
			}
		});
		workIPJRadioButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				jcheckSelect(workIPJRadioButton2,workIPTextField2);
			}
		});

		workIPJCheckBox3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				jcheckSelect(workIPJCheckBox3,workIPTextField3);
			}
		});

		workIPJCheckBox4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				jcheckSelect(workIPJCheckBox4,workIPTextField4);
			}
		});

		workIPJCheckBox5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				jcheckSelect(workIPJCheckBox5,workIPTextField5);
			}
		});

		workIPJCheckBox6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				jcheckSelect(workIPJCheckBox6,workIPTextField6);

			}
		});

	}

	/**
	 * 
	 * @param workIPJCheckBox
	 * @param workIPTextField
	 */
	private void jcheckSelect(JCheckBox workIPJCheckBox,JTextField workIPTextField) {
		if (workIPJCheckBox.isSelected()) {
			workIPTextField.setEditable(true);
			if (!CheckingUtil.checking(workIPTextField.getText(), CheckingUtil.IP_REGULAR)) {
				workIPTextField.setBorder(BorderFactory.createLineBorder(Color.RED));
			}
			documentChange(workIPTextField);
		} else {
			workIPTextField.setEditable(false);
			workIPTextField.setBorder(new TextFieldBorder());
		}
	}

	private void documentChange(final JTextField workIPTextField) {
		workIPTextField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				check(workIPTextField);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				check(workIPTextField);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				check(workIPTextField);
			}
		});

	}

	private void check(JTextField workIPTextField) {
		if (!CheckingUtil.checking(workIPTextField.getText(), CheckingUtil.IP_REGULAR)) {
			workIPTextField.setBorder(BorderFactory.createLineBorder(Color.RED));
		} else {
			workIPTextField.setBorder(new TextFieldBorder());
		}
	}

	private void getWorkIPs(List<String> workIPList) {
		if(info.getWorkIps() == null){
			WorkIps workIps = new WorkIps();
			info.setWorkIps(workIps);
		}
		if (workIPJRadioButton1.isSelected()) {
			if (CheckingUtil.checking(workIPTextField.getText(), CheckingUtil.IP_REGULAR)) {
				workIPList.add(workIPTextField.getText().trim());
				info.getWorkIps().setWorkIp1(workIPTextField.getText().trim());
			} else {
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysLbl.LBL_WORKIP) + "1" + ResourceUtil.srcStr(StringKeysTip.TIP_IP_ERROR));
				UiUtil.insertOperationLog(EOperationLogType.WORKIPFORMAT1ERROR.getValue());
				return;
			}
		}
		if (workIPJRadioButton2.isSelected()) {
			if (CheckingUtil.checking(workIPTextField2.getText(), CheckingUtil.IP_REGULAR)) {
				workIPList.add(workIPTextField2.getText().trim());
				info.getWorkIps().setWorkIp2(workIPTextField2.getText().trim());
			} else {
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysLbl.LBL_WORKIP) + "2" + ResourceUtil.srcStr(StringKeysTip.TIP_IP_ERROR));
				UiUtil.insertOperationLog(EOperationLogType.WORKIPFORMAT2ERROR.getValue());
				return;
			}
		}
		if (workIPJCheckBox3.isSelected()) {
			if (CheckingUtil.checking(workIPTextField3.getText(), CheckingUtil.IP_REGULAR)) {
				workIPList.add(workIPTextField3.getText().trim());
				info.getWorkIps().setWorkIp3(workIPTextField3.getText().trim());
			} else {
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysLbl.LBL_WORKIP) + "3" + ResourceUtil.srcStr(StringKeysTip.TIP_IP_ERROR));
				UiUtil.insertOperationLog(EOperationLogType.WORKIPFORMAT3ERROR.getValue());
				return;
			}
		}
		if (workIPJCheckBox4.isSelected()) {
			if (CheckingUtil.checking(workIPTextField4.getText(), CheckingUtil.IP_REGULAR)) {
				workIPList.add(workIPTextField4.getText().trim());
				info.getWorkIps().setWorkIp4(workIPTextField4.getText().trim());
			} else {
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysLbl.LBL_WORKIP) + "4" + ResourceUtil.srcStr(StringKeysTip.TIP_IP_ERROR));
				UiUtil.insertOperationLog(EOperationLogType.WORKIPFORMAT4ERROR.getValue());
				return;
			}
		}
		if (workIPJCheckBox5.isSelected()) {
			if (CheckingUtil.checking(workIPTextField5.getText(), CheckingUtil.IP_REGULAR)) {
				workIPList.add(workIPTextField5.getText().trim());
				info.getWorkIps().setWorkIp5(workIPTextField5.getText().trim());
			} else {
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysLbl.LBL_WORKIP) + "5" + ResourceUtil.srcStr(StringKeysTip.TIP_IP_ERROR));
				UiUtil.insertOperationLog(EOperationLogType.WORKIPFORMAT5ERROR.getValue());
				return;
			}
		}
		if (workIPJCheckBox6.isSelected()) {
			if (CheckingUtil.checking(workIPTextField6.getText(), CheckingUtil.IP_REGULAR)) {
				workIPList.add(workIPTextField6.getText().trim());
				info.getWorkIps().setWorkIp6(workIPTextField6.getText().trim());
			} else {
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysLbl.LBL_WORKIP) + "6" + ResourceUtil.srcStr(StringKeysTip.TIP_IP_ERROR));
				UiUtil.insertOperationLog(EOperationLogType.WORKIPFORMAT6ERROR.getValue());
				return;
			}
		}
		if(!(workIPList.size()>0)){
			DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysLbl.LBL_MUST_WORKIP));
			UiUtil.insertOperationLog(EOperationLogType.WORKIPERROR.getValue());
			return;
		}
		info.setWorkIP(workIPList);
		info.getWorkIps().setField(info.getId());
		WhImplUtil implUtil = new WhImplUtil();
		List<Integer> integers = new ArrayList<Integer>();
		integers.add(info.getmSiteId());
		info.setSiteName(implUtil.getNeName(integers));
		try {
			DispatchUtil configDispath = new DispatchUtil(RmiKeys.RMI_ADMINISTRATECONFIG);
			String result = null;
			result = configDispath.excuteInsert(info);
			DialogBoxUtil.succeedDialog(view, result);
			AddOperateLog.insertOperLog(saveBtn, EOperationLogType.SITEWORKIP.getValue(), result, 
					null, info, 0, ResourceUtil.srcStr(StringKeysPanel.PANEL_SITECONFIG), "fieldLog");
			view.dispose();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

	}

	private javax.swing.JPanel jpanel;
	private javax.swing.JPanel buttonJPanel;
	private javax.swing.JButton cancelBtn;
	private PtnButton saveBtn;
	private JLabel workIPLable;
	private JTextField workIPTextField;
	private JCheckBox workIPJRadioButton1;
	private JLabel workIPLable2;
	private JTextField workIPTextField2;
	private JCheckBox workIPJRadioButton2;
	private JLabel lblMessage;
	private JLabel workIPLable3;
	private JTextField workIPTextField3;
	private JCheckBox workIPJCheckBox3;
	private JLabel workIPLable4;
	private JTextField workIPTextField4;
	private JCheckBox workIPJCheckBox4;
	private JLabel workIPLable5;
	private JTextField workIPTextField5;
	private JCheckBox workIPJCheckBox5;
	private JLabel workIPLable6;
	private JTextField workIPTextField6;
	private JCheckBox workIPJCheckBox6;
	// End of variables declaration//GEN-END:variables

}