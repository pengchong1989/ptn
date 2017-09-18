package com.nms.ui.ptn.ne.ecn.ospf.areaconfig.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.nms.db.bean.equipment.port.PortStm;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTitle;

public class SaveAddressDialog extends PtnDialog {
	private PortStm portStm;
	private SavaAdreePanel panel;

	public SaveAddressDialog(PortStm portstm, SavaAdreePanel panel2) {
		this.setModal(true);
		try {
			initComponents();
			this.portStm = portstm;
			this.panel = panel2;
			setLayout();
			super.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_ADDRESS));
			addListener();
			this.showWindow();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

	}

	private void initComponents() {
		jPanel = new JPanel();
		network = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TARGET_NETWORK));
		networktext = new JTextField();
		mask = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_MASK_BITS));
		masktext = new JTextField();
		polymerization = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_COST_AGGREGATION));
		polymerizationtext = new JTextField();
		open = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ENABLE_NOTIFICATION));
		opencom = new JCheckBox();
		btnsave = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE));
		btncanel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
	}

	private void setLayout() {

		Dimension dimension = new Dimension(370, 280);
		this.setPreferredSize(dimension);
		this.setMinimumSize(dimension);

		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 70, 120, 50 };
		layout.columnWeights = new double[] { 0, 0 };
		layout.rowHeights = new int[] { 35, 35, 35, 35, 35, 0, 0, 0 };
		layout.rowWeights = new double[] { 0, 0, 0, 0, 0, 0, 0, 0.2 };
		this.jPanel.setLayout(layout);
		this.add(jPanel);

		GridBagConstraints c = new GridBagConstraints();

		/** 第一行  目标网络 */
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		c.fill = GridBagConstraints.BOTH;
		layout.setConstraints(network, c);
		this.jPanel.add(network);
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		layout.addLayoutComponent(networktext, c);
		this.jPanel.add(networktext);

		/** 第二行 掩码位数 */
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(mask, c);
		this.jPanel.add(mask);
		c.gridx = 1;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(masktext, c);
		this.jPanel.add(masktext);

		/** 第三行 聚合代价 */
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(polymerization, c);
		this.jPanel.add(polymerization);
		c.gridx = 1;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(polymerizationtext, c);
		this.jPanel.add(polymerizationtext);

		/** 第四行 启用通告*/
		c.gridx = 0;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(open, c);
		this.jPanel.add(open);
		c.gridx = 1;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(opencom, c);
		this.jPanel.add(opencom);

		/** 第六行 确定按钮空出一行 */
		c.gridx = 1;
		c.gridy = 6;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 10, 5, 5);
		c.fill = GridBagConstraints.EAST;
		layout.setConstraints(btnsave, c);
		this.jPanel.add(btnsave);
		c.gridx = 2;
		c.gridy = 6;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 10, 5, 5);
		c.fill = GridBagConstraints.EAST;
		layout.addLayoutComponent(btncanel, c);
		this.jPanel.add(btncanel);

	}

	private void showWindow() {
		// Dimension dimension = new Dimension(350, 450);
		// this.setSize(dimension);
		// this.setMinimumSize(dimension);
		this.setLocation(UiUtil.getWindowWidth(this.getWidth()), UiUtil.getWindowHeight(this.getHeight()));
		this.setVisible(true);
	}

	//	private void initDate(PortStm portStm) throws Exception {
	//		try {
	//			this.txtexpectj2.setText(portStm.getName());
	//			UiUtil.comboBoxSelectByValue(admintype, portStm.getStatus() + "");
	//			this.txtsendj2.setText(portStm.getJobstatus());
	//			this.txtrealityj2.setText(portStm.getType());
	//			UiUtil.comboBoxSelect(sfptype, portStm.getSfpexpect() + "");
	//			this.txtexpectv5.setText(portStm.getJobwavelength() + "");
	//			this.txtrealityv5.setText(portStm.getSfpreality());
	//			this.jTextArea.setText(portStm.getSfpvender());
	//			this.hidden.setText(portStm.getPortid()+"");
	//			this.siteid.setText(portStm.getSiteid()+"");
	//
	//		} catch (Exception e) {
	//			throw e;
	//		}
	//	}

	private void addListener() {
		btnsave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SaveAddressDialog.this.dispose();
			}
		});

		btncanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SaveAddressDialog.this.savaPortStmTimeslot();
			}
		});
	}

	public void savaPortStmTimeslot() {

		//		ControlKeyValue controlKeyValueManage = null;
		//		ControlKeyValue controlKeyValueManage1 = null;
		//		PortStmDispatch portStmDispatch = null;
		//		String result = null;
		//		Code code = null;
		//		Code code2 = null;
		//		try {
		//			controlKeyValueManage = (ControlKeyValue) this.admintype.getSelectedItem();
		//			code = new Code();
		//			code = (Code) controlKeyValueManage.getObject();
		//			controlKeyValueManage1 = (ControlKeyValue) this.sfptype.getSelectedItem();
		//			code2 = new Code();
		//			code2 = (Code) controlKeyValueManage1.getObject();
		//
		//			this.portStm.setName(txtexpectj2.getText());
		//			this.portStm.setPortid(Integer.parseInt(hidden.getText()));
		//			this.portStm.setJobwavelength(Integer.parseInt(txtexpectv5.getText()));
		//			this.portStm.setSfpexpect(code2.getCodeName());
		//			this.portStm.setSfpreality(txtrealityv5.getText());
		//			this.portStm.setSfpvender(jTextArea.getText());
		//			this.portStm.setStatus(Integer.parseInt(code.getCodeValue()));
		//			this.portStm.setSiteid(Integer.parseInt(siteid.getText()));
		//			portStmDispatch = new PortStmDispatch();
		//
		//			result = portStmDispatch.executeUpdate(this.portStm);
		//			DialogBoxUtil.succeedDialog(this, result);
		//			this.panel.getController().refresh();
		//			this.dispose();
		//
		//		} catch (Exception e) {
		//			ExceptionManage.dispose(e,this.getClass());
		//		} finally {
		//			controlKeyValueManage = null;
		//			portStmDispatch = null;
		//		}

	}

	private JLabel network;
	private JTextField networktext;
	private JLabel mask;
	private JTextField masktext;
	private JLabel polymerization;
	private JTextField polymerizationtext;
	private JLabel open;
	private JCheckBox opencom;
	private JButton btnsave;
	private JButton btncanel;
	private JPanel jPanel;
}
