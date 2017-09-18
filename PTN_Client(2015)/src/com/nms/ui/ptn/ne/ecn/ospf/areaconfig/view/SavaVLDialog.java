package com.nms.ui.ptn.ne.ecn.ospf.areaconfig.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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

public class SavaVLDialog extends PtnDialog {
	private PortStm portStm;
	private SaveVLPanel panel;

	public SavaVLDialog(PortStm portstm , SaveVLPanel panel2) {
		this.setModal(true);
		try {
			initComponents();
			this.portStm = portstm;
			this.panel= panel2;
			setLayout();
			super.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_VL));
			addListener();
			this.showWindow();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

	}

	private void initComponents() {
		jPanel = new JPanel();
		ipaddress = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_IP_ADDRESS));
	    ipaddresstext = new JTextField();
	    heelointerval = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_HELLO_INTERVAL));
	    heelointervaltext = new JTextField();
	    deadinterval = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_DEAD_INTERVAL));
	    deadintervaltext = new JTextField();
	    retransmission = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_RETRANSMISSION_INTERVAL));
	    retransmissiontext = new JTextField();
	    message = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TRANSMISSION_DELAY));
	    messagetext = new JTextField();
	    btnsave = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE));
	    btncanel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
	}

	private void setLayout() {
		
		Dimension dimension = new Dimension(370, 280);
		this.setPreferredSize(dimension);
		this.setMinimumSize(dimension);

		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 70, 120 ,50};
		layout.columnWeights = new double[] { 0, 0};
		layout.rowHeights = new int[] { 35, 35, 35, 35, 35, 0, 0 , 0};
		layout.rowWeights = new double[] { 0, 0, 0, 0, 0, 0 ,  0 , 0.2 };
		this.jPanel.setLayout(layout);
		this.add(jPanel);
		
		GridBagConstraints c = new GridBagConstraints();

		/** 第一行  IP地址 */
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		c.fill = GridBagConstraints.BOTH;
		layout.setConstraints(ipaddress, c);
		this.jPanel.add(ipaddress);
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		layout.addLayoutComponent(ipaddresstext, c);
		this.jPanel.add(ipaddresstext);

		/** 第二行 HELLO（间隔） */
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(heelointerval, c);
		this.jPanel.add(heelointerval);
		c.gridx = 1;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(heelointervaltext, c);
		this.jPanel.add(heelointervaltext);

		/** 第三行 DEAD（间隔） */
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(deadinterval, c);
		this.jPanel.add(deadinterval);
		c.gridx = 1;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(deadintervaltext, c);
		this.jPanel.add(deadintervaltext);

		/** 第四行 重传间隔*/
		c.gridx = 0;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(retransmission, c);
		this.jPanel.add(retransmission);
		c.gridx = 1;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(retransmissiontext, c);
		this.jPanel.add(retransmissiontext);

		/** 第五行 报文传输延时*/
		c.gridx = 0;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(message, c);
		this.jPanel.add(message);
		c.gridx = 1;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(messagetext, c);
		this.jPanel.add(messagetext);
		
		/** 第七行 确定按钮空出一行 */
		c.gridx = 1;
		c.gridy = 7;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 10, 5, 5);
		c.fill = GridBagConstraints.EAST;
		layout.setConstraints(btnsave, c);
		this.jPanel.add(btnsave);
		c.gridx = 2;
		c.gridy = 7;
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

	private void addListener(){
		btnsave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SavaVLDialog.this.dispose();
			}
		});
		
		btncanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SavaVLDialog.this.savaPortStmTimeslot();
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

    private JLabel ipaddress;
    private JTextField ipaddresstext;
    private JLabel heelointerval;
    private JTextField heelointervaltext;
    private JLabel deadinterval;
    private JTextField deadintervaltext;
    private JLabel retransmission;
    private JTextField retransmissiontext;
    private JLabel message;
    private JTextField messagetext;
	private JButton btnsave;
	private JButton btncanel;
	private JPanel jPanel;
}
