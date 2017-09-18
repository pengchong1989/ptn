package com.nms.ui.ptn.ne.ecn.ecninterfaces.mcn.view;

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

import com.nms.db.bean.ptn.ecn.MCN;
import com.nms.db.enums.EOperationLogType;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
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
import com.nms.ui.manager.keys.StringKeysTitle;

public class UpdateMcnDialog extends PtnDialog {
	private MCN mcn;
	private McnPanel mcnPanel;
	
	/**
	 * 创建一个新的实例
	 * @param mcn
	 * @param mcnPanel
	 */
	public UpdateMcnDialog(MCN mcn, McnPanel mcnPanel) {
		this.setModal(true);
		this.mcn = mcn;
		this.mcnPanel = mcnPanel;
		try {
			super.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_UPDATE_MCN));
			initComponents();
			setLayout();
			initUpdateDate();
			addListener();
			showWindow();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 初始化控件
	 */
	private void initComponents() {
		jPanel = new JPanel();
		interfacesIp = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_LOCAL_INTERFACE_IP));
		interfacesIpText = new JTextField();
		mask = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_MASK_BITS));
		maskText = new JTextField();
		mtu = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_MTU));
		mtuText = new JTextField();
		btnConfirmation = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE),false);
		btnCanel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
	}

	/**
	 * 初始化数据
	 */
	private void initUpdateDate() {
		interfacesIpText.setText(mcn.getIp().substring(0, mcn.getIp().length() - 3));
		maskText.setText(mcn.getIp().substring(mcn.getIp().length() - 2));
		mtuText.setText(mcn.getMtu() + "");
	}

	/**
	 * 布局
	 */
	private void setLayout() {

		Dimension dimension = new Dimension(410, 250);
		this.setPreferredSize(dimension);
		this.setMinimumSize(dimension);

		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 70, 120, 50 };
		layout.rowHeights = new int[] { 35, 35, 35, 35, 35 };
		this.jPanel.setLayout(layout);
		this.add(jPanel);

		GridBagConstraints c = new GridBagConstraints();

		/** 第一行 接口ip */
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.setConstraints(interfacesIp, c);
		this.jPanel.add(interfacesIp);
		c.gridx = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		layout.addLayoutComponent(interfacesIpText, c);
		this.jPanel.add(interfacesIpText);

		/** 第二行 最大传输单元(字节) */
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(mask, c);
		this.jPanel.add(mask);
		c.gridx = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		layout.addLayoutComponent(maskText, c);
		this.jPanel.add(maskText);

		/** 第三行 本地接口IP */
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(mtu, c);
		this.jPanel.add(mtu);
		c.gridx = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		layout.addLayoutComponent(mtuText, c);
		this.jPanel.add(mtuText);

		/** 第无五行 确定按钮空出一行 */
		c.gridx = 1;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 10, 5, 5);
		c.fill = GridBagConstraints.EAST;
		layout.setConstraints(btnConfirmation, c);
		this.jPanel.add(btnConfirmation);
		c.gridx = 2;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 10, 5, 5);
		c.fill = GridBagConstraints.EAST;
		layout.addLayoutComponent(btnCanel, c);
		this.jPanel.add(btnCanel);
	}

	/**
	 * 页面尺寸
	 */
	private void showWindow() {
		this.setLocation(UiUtil.getWindowWidth(this.getWidth()), UiUtil.getWindowHeight(this.getHeight()));
		this.setVisible(true);
	}

	/**
	 * 监听事件
	 */
	private void addListener() {
		btnConfirmation.addActionListener(new MyActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UpdateMcnDialog.this.updateMCN();
			}

			@Override
			public boolean checking() {
				
				return true;
			}
		});

		btnCanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UpdateMcnDialog.this.dispose();
			}
		});
	}

	/**
	 * 更新方法
	 */
	public void updateMCN() {
		String message = "";
		try {
			DispatchUtil mcnDispatch = new DispatchUtil(RmiKeys.RMI_MCN);
			mcn.setIp(interfacesIpText.getText() + "/" + maskText.getText());
			mcn.setMtu(Integer.valueOf(mtuText.getText()));
			mcn.setNeId(ConstantUtil.siteId + "");
			message = mcnDispatch.excuteUpdate(mcn);
			DialogBoxUtil.succeedDialog(this, message);
			mcn = null;
			this.mcnPanel.controller.refresh();
			this.dispose();
		} catch (Exception e) {
			DialogBoxUtil.errorDialog(this, e.getMessage());
		}
	}

	private JLabel mtu;
	private JLabel interfacesIp;
	private JLabel mask;
	private JTextField maskText;
	private JTextField mtuText;
	private JTextField interfacesIpText;
	private PtnButton btnConfirmation;
	private JButton btnCanel;
	private JPanel jPanel;
}
