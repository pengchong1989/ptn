package com.nms.ui.ptn.ne.ecn.ecninterfaces.ccn.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.nms.db.bean.ptn.ecn.CCN;
import com.nms.db.bean.system.code.Code;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.keys.StringKeysTitle;

public class SaveCCNDialog extends PtnDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CCNPanel panel;
	private CCN ccn = new CCN();
	private boolean isUpdate = false;

	public SaveCCNDialog(CCN ccn, CCNPanel panel) {
		this.setModal(true);
		try {
			initComponents();
			this.panel = panel;
			setLayout();
			comboBoxDate();
			initDate();
			addListener();
			if (ccn != null) {
				this.ccn = ccn;
				initUpdateDate(ccn);
				super.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_UPDATE_CCN));
			} else {
				super.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_CCN));
			}
			this.showWindow();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

	}

	private void initComponents() {
		jPanel = new JPanel();
		adminstatus = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_MANAGE_STATUS));
		adminstatuscom = new JComboBox();
		max = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_MTU));
		maxtext = new JTextField();
		interfacesip = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_LOCAL_INTERFACE_IP));
		interfacesiptext = new JTextField();
		digit = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_MASK_BITS));
		digittext = new JTextField();
		peersiteid = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PEER_SITE_ID));
		peersiteidtext = new JTextField();
		btnsave = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE));
		btncanel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		//jcbStatus=new JCheckBox();
	//	jcbStatus.setText(ResourceUtil.srcStr(StringKeysLbl.LBL_ACTIVITY_STATUS));
		//jblStatus=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ACTIVITY_STATUS));
	}

	private void setLayout() {

		Dimension dimension = new Dimension(410, 330);
		this.setPreferredSize(dimension);
		this.setMinimumSize(dimension);

		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 70, 120, 50 };
		layout.columnWeights = new double[] { 0, 0 };
		layout.rowHeights = new int[] { 35, 35, 35, 35, 35, 35, 35, 35 };
		layout.rowWeights = new double[] { 0, 0, 0, 0, 0, 0, 0, 0.2 };
		this.jPanel.setLayout(layout);
		this.add(jPanel);

		GridBagConstraints c = new GridBagConstraints();

		/** 第一行 管理状态 */
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.setConstraints(adminstatus, c);
		this.jPanel.add(adminstatus);
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		layout.addLayoutComponent(adminstatuscom, c);
		this.jPanel.add(adminstatuscom);

		/** 第二行 最大传输单元(字节) */
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(max, c);
		this.jPanel.add(max);
		c.gridx = 1;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(maxtext, c);
		this.jPanel.add(maxtext);

		/** 第三行 本地接口IP */
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(interfacesip, c);
		this.jPanel.add(interfacesip);
		c.gridx = 1;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(interfacesiptext, c);
		this.jPanel.add(interfacesiptext);

		/** 第四行 掩码位数 */
		c.gridx = 0;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(digit, c);
		this.jPanel.add(digit);
		c.gridx = 1;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(digittext, c);
		this.jPanel.add(digittext);

		/** 第五行 对端网元ID */
		c.gridx = 0;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(peersiteid, c);
		this.jPanel.add(peersiteid);
		c.gridx = 1;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.BOTH;
		layout.addLayoutComponent(peersiteidtext, c);
		this.jPanel.add(peersiteidtext);
		
//		/** 第6行 激活状态*/
//		c.gridx = 0;
//		c.gridy = 6;
//		c.gridheight = 1;
//		c.gridwidth = 1;
//		c.insets = new Insets(5, 10, 5, 5);
//		layout.setConstraints(this.jblStatus, c);
//		this.jPanel.add(jblStatus);
//		c.gridx = 1;
//		c.gridy = 6;
//		c.gridheight = 1;
//		c.gridwidth = 1;
//		c.insets = new Insets(5, 10, 5, 5);
//		layout.setConstraints(this.jcbStatus, c);
//		this.jPanel.add(jcbStatus);

		/** 第7行 确定按钮空出一行 */
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
		this.setLocation(UiUtil.getWindowWidth(this.getWidth()), UiUtil.getWindowHeight(this.getHeight()));
		this.setVisible(true);
	}

	private void comboBoxDate() throws Exception {
		try {
			super.getComboBoxDataUtil().comboBoxData(adminstatuscom, "ENABLEDSTATUE");
		} catch (Exception e) {
			throw e;
		}
	}

	private void initUpdateDate(CCN ccn) throws Exception {
		try {
			maxtext.setText(ccn.getMtu() + "");
			interfacesiptext.setText(ccn.getIp().substring(0, ccn.getIp().length() - 3));
			digittext.setText(ccn.getIp().substring(ccn.getIp().length() - 2));
			peersiteidtext.setText(ccn.getPeer());
			isUpdate = true;
			super.getComboBoxDataUtil().comboBoxSelect(adminstatuscom, ccn.getAdmin());
		} catch (Exception e) {
			throw e;
		}
	}

	private void initDate() throws Exception {
		try {
			maxtext.setText("1400");
			interfacesiptext.setText("0.0.0.0");
			digittext.setText("24");
			peersiteidtext.setText("0.0.0.0");
		} catch (Exception e) {
			throw e;
		}
	}

	private void addListener() {
		btnsave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SaveCCNDialog.this.savaCCN();
			}
		});

		btncanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SaveCCNDialog.this.dispose();
			}
		});
	}

	public void savaCCN() {
//		CCNCXServiceImpl ccnXcServiceImpl = null;
		ControlKeyValue controlKeyValueManage = null;
		DispatchUtil ccnDispatch = null;
		String result = null;
		Code code = null;
		CCN ccn = new CCN();
		try {
//			ccnXcServiceImpl = new CCNCXServiceImpl();
			controlKeyValueManage = (ControlKeyValue) this.adminstatuscom.getSelectedItem();
			code = new Code();
			code = (Code) controlKeyValueManage.getObject();

			ccn.setNeId(ConstantUtil.siteId + "");
//			List<CCN> ccnList = ccnXcServiceImpl.excutionQuery(ccn);
			ccn.setAdmin(code.getId()+"");
			ccn.setIp(interfacesiptext.getText() + "/" + digittext.getText());
			ccn.setMtu(Integer.valueOf(maxtext.getText()));
			ccn.setPeer(peersiteidtext.getText());
			ccn.setStatus(1);
			ccnDispatch = new DispatchUtil(RmiKeys.RMI_CCN);

			if (isUpdate) {
				ccn.setCcnName((this.ccn.getCcnName()) + "");
				ccn.setId(this.ccn.getId());
				ccn.setOper(this.ccn.getOper());
				ccn.setPortname(this.ccn.getPortname());
				
				result = ccnDispatch.excuteUpdate(ccn);
			} else {
//				ccn.setCcnName((ccnList.size() + 1) + "");
				result = ccnDispatch.excuteInsert(ccn);
			}
			DialogBoxUtil.succeedDialog(this, result);
			this.panel.getController().refresh();
			this.dispose();

		} catch (Exception e) {
			DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_NOT_FULL));
		} finally {
			controlKeyValueManage = null;
			ccnDispatch = null;
		}

	}

	private JLabel adminstatus;
	private JComboBox adminstatuscom;
	private JLabel max;
	private JTextField maxtext;
	private JLabel interfacesip;
	private JTextField interfacesiptext;
	private JLabel digit;
	private JTextField digittext;
	private JLabel peersiteid;
	private JTextField peersiteidtext;
	private JButton btnsave;
	private JButton btncanel;
	private JPanel jPanel;
//	private JCheckBox jcbStatus;
//	private JLabel jblStatus;

}
