package com.nms.ui.ptn.ne.sdh.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.nms.db.bean.equipment.port.PortStm;
import com.nms.db.bean.system.code.Code;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.ControlKeyValue;
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
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.manager.keys.StringKeysTitle;

public class UpdateSDHDialog extends PtnDialog {
	private PortStm portStm;
	private SDHPanel panel;

	public UpdateSDHDialog(PortStm portstm , SDHPanel panel) {
		this.setModal(true);
		try {
			initComponents();
			this.portStm = portstm;
			this.panel= panel;
			setLayout();
			super.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_UPDATE_SDH));
			this.comboBoxDate();
			this.initDate(portstm);
			addListener();
			this.showWindow();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

	}

	private void initComponents() {
		tabbedPane = new JTabbedPane();
		jPanel = new JPanel();
		jPane2 = new JPanel();
		jPane3 = new JPanel();
		lblmanage = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT_NAME));
		txtexpectj2 = new JTextField();
		txtexpectj2.setEnabled(false);
		lblexpectj2 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ENABLED_STATUS));
		admintype = new JComboBox();
		lblsendj2 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_JOB_STATUS));
		txtsendj2 = new JTextField();
		txtsendj2.setEnabled(false);
		lblrealityj2 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TYPE));
		txtrealityj2 = new JTextField();
		txtrealityj2.setEnabled(false);
		lbllptim = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SFP_EXPECT));
		sfptype = new JComboBox();
		lblexpectv5 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_JOB_WAVELENGTH));
		txtexpectv5 = new JTextField();
		txtexpectv5.setEnabled(false);
		lblsendv5 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SFP_REALITY));
		txtrealityv5 = new JTextField();
		txtrealityv5.setEnabled(false);
		lblrealityv5 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_MANUFACTURERS));
		jTextArea = new JTextArea(5, 5);
		jTextArea.setLineWrap(true);
		jTextArea.setEnabled(false);
		jTabbedPane = new JTabbedPane();
		JPanel component = new JPanel();
		JPanel component1 = new JPanel();
		component1.setName(ResourceUtil.srcStr(StringKeysTab.TAB_SEGMENT_PAY_EXPENSES));
		JPanel component2 = new JPanel();
		component1.setName("");
		jTabbedPane.addTab(ResourceUtil.srcStr(StringKeysTab.TAB_PHYSICS_PORT), component);
		hidden = new JTextField(0);
		hidden.setVisible(false);
		siteid = new JTextField(0);
		siteid.setVisible(false);
		jScrollPane = new JScrollPane(jTextArea);
		// jTabbedPane.add(component1);
		// jTabbedPane.add(component2);
		btnSave = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE),false);
		btnCanel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
	}

	private void setLayout() {
		tabbedPane.add(ResourceUtil.srcStr(StringKeysTab.TAB_PHYSICS_PORT), jPanel);
		// tabbedPane.add("段开销信息", jPane2);
		// tabbedPane.add("高阶通道信息", jPane3);
		this.add(tabbedPane);

		Dimension dimension = new Dimension(550, 380);
		this.setPreferredSize(dimension);
		this.setMinimumSize(dimension);

		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 70, 120, 50, 120 ,20};
		layout.columnWeights = new double[] { 0, 0, 0, 0 };
		layout.rowHeights = new int[] { 35, 35, 35, 35, 35, 90, 20, 35, 35 ,35};
		layout.rowWeights = new double[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 , 0.2 };
		this.jPanel.setLayout(layout);
		
		GridBagConstraints c = new GridBagConstraints();

		/** 第一行 端口名称 */
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.setConstraints(lblmanage, c);
		this.jPanel.add(lblmanage);
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(txtexpectj2, c);
		this.jPanel.add(txtexpectj2);

		/** 第二行 管理状态 工作状态 */
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(lblexpectj2, c);
		this.jPanel.add(lblexpectj2);
		c.gridx = 1;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(admintype, c);
		this.jPanel.add(admintype);
		c.gridx = 2;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(lblsendj2, c);
		this.jPanel.add(lblsendj2);
		c.gridx = 3;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(txtsendj2, c);
		this.jPanel.add(txtsendj2);

		/** 第三行 类型 SFP期望类型 */
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(lblrealityj2, c);
		this.jPanel.add(lblrealityj2);
		c.gridx = 1;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(txtrealityj2, c);
		this.jPanel.add(txtrealityj2);
		c.gridx = 2;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(lbllptim, c);
		this.jPanel.add(lbllptim);
		c.gridx = 3;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(sfptype, c);
		this.jPanel.add(sfptype);

		/** 第四行工作波长 SFP实际类型 */
		c.gridx = 0;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(lblexpectv5, c);
		this.jPanel.add(lblexpectv5);
		c.gridx = 1;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(txtexpectv5, c);
		this.jPanel.add(txtexpectv5);
		c.gridx = 2;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(lblsendv5, c);
		this.jPanel.add(lblsendv5);
		c.gridx = 3;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(txtrealityv5, c);
		this.jPanel.add(txtrealityv5);

		/** 第五行 期望V5 */
		c.gridx = 0;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(lblrealityv5, c);
		this.jPanel.add(lblrealityv5);
		c.gridx = 1;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.BOTH;
		layout.addLayoutComponent(jScrollPane, c);
		this.jPanel.add(jScrollPane);

		/** 第7行 确定按钮空出一行 */
		c.gridx = 2;
		c.gridy = 7;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 10, 5, 60);
		c.fill = GridBagConstraints.NONE;
		c.anchor=GridBagConstraints.EAST;
		layout.setConstraints(btnSave, c);
		this.jPanel.add(btnSave);
		c.gridx = 3;
		c.gridy = 7;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 10, 5, 10);
		c.anchor=GridBagConstraints.EAST;
		layout.addLayoutComponent(btnCanel, c);
		this.jPanel.add(btnCanel);
		
		/** 第八行 隐藏文本框 */
		c.gridx = 3;
		c.gridy = 8;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		c.fill = GridBagConstraints.WEST;
		layout.setConstraints(hidden, c);
		this.jPanel.add(hidden);
	}

	private void showWindow() {
		// Dimension dimension = new Dimension(350, 450);
		// this.setSize(dimension);
		// this.setMinimumSize(dimension);
		this.setLocation(UiUtil.getWindowWidth(this.getWidth()), UiUtil.getWindowHeight(this.getHeight()));
		this.setVisible(true);
	}

	private void comboBoxDate() throws Exception {
		try {
			super.getComboBoxDataUtil().comboBoxData(admintype, "ENABLEDSTATUE");
			super.getComboBoxDataUtil().comboBoxData(sfptype, "sfptype");
		} catch (Exception e) {
			throw e;
		}

	}

	private void initDate(PortStm portStm) throws Exception {
		try {
			this.txtexpectj2.setText(portStm.getName());
			super.getComboBoxDataUtil().comboBoxSelectByValue(admintype, portStm.getStatus() + "");
			this.txtsendj2.setText(portStm.getJobstatus());
			this.txtrealityj2.setText(portStm.getType());
			super.getComboBoxDataUtil().comboBoxSelect(sfptype, portStm.getSfpexpect() + "");
			this.txtexpectv5.setText(portStm.getJobwavelength() + "");
			this.txtrealityv5.setText(portStm.getSfpreality());
			this.jTextArea.setText(portStm.getSfpvender());
			this.hidden.setText(portStm.getPortid()+"");
			this.siteid.setText(portStm.getSiteid()+"");

		} catch (Exception e) {
			throw e;
		}
	}

	private void addListener(){
		btnCanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UpdateSDHDialog.this.dispose();
			}
		});
		
		btnSave.addActionListener(new MyActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UpdateSDHDialog.this.savaPortStmTimeslot();
			}

			@Override
			public boolean checking() {
				return true;
			}
		});
	}
	
	public void savaPortStmTimeslot() {

		ControlKeyValue controlKeyValueManage = null;
		ControlKeyValue controlKeyValueManage1 = null;
		DispatchUtil portStmDispatch = null;
		String result = null;
		Code code = null;
		Code code2 = null;
		try {
			controlKeyValueManage = (ControlKeyValue) this.admintype.getSelectedItem();
			code = new Code();
			code = (Code) controlKeyValueManage.getObject();
			controlKeyValueManage1 = (ControlKeyValue) this.sfptype.getSelectedItem();
			code2 = new Code();
			code2 = (Code) controlKeyValueManage1.getObject();

			this.portStm.setName(txtexpectj2.getText());
			this.portStm.setPortid(Integer.parseInt(hidden.getText()));
			this.portStm.setJobwavelength(Integer.parseInt(txtexpectv5.getText()));
			this.portStm.setSfpexpect(code2.getId()+"");
			this.portStm.setSfpreality(txtrealityv5.getText());
			this.portStm.setSfpvender(jTextArea.getText());
			this.portStm.setStatus(Integer.parseInt(code.getCodeValue()));
			this.portStm.setSiteid(Integer.parseInt(siteid.getText()));
			portStmDispatch = new DispatchUtil(RmiKeys.RMI_PORTSTM);

			result = portStmDispatch.excuteUpdate(this.portStm);
			DialogBoxUtil.succeedDialog(this, result);
			this.panel.getController().refresh();
			this.dispose();

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			controlKeyValueManage = null;
			portStmDispatch = null;
		}

	}

	private JLabel lblmanage;
	private JLabel lblexpectj2;
	private JLabel lblsendj2;
	private JLabel lblrealityj2;
	private JLabel lbllptim;
	private JLabel lblexpectv5;
	private JLabel lblsendv5;
	private JLabel lblrealityv5;
	private JLabel lblcheck;
	private JComboBox admintype;
	private JTextField txtexpectj2;
	private JTextField txtsendj2;
	private JTextField txtrealityj2;
	private JCheckBox ckblptim;
	private JTextField txtexpectv5;
	private JComboBox sfptype;
	private JTextField txtrealityv5;
	private JCheckBox ckbcheck;
	private PtnButton btnSave;
	private JButton btnCanel;
	private JTextArea jTextArea;
	private JTabbedPane jTabbedPane;
	private javax.swing.JList jList;
	private JTabbedPane tabbedPane;
	private JPanel jPanel;
	private JPanel jPane2;
	private JPanel jPane3;
	private javax.swing.JTextField hidden;
	private javax.swing.JTextField siteid;
	private javax.swing.JSplitPane jSplitPane;
	private javax.swing.JScrollPane jScrollPane;
}
