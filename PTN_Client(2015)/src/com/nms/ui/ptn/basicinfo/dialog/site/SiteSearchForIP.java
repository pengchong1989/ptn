package com.nms.ui.ptn.basicinfo.dialog.site;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.nms.db.bean.system.code.Code;
import com.nms.db.enums.EOperationLogType;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnComboBox;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.control.PtnTextField;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.keys.StringKeysTitle;

/**
 * 按网关搜索 
 * @author dzy
 */
public class SiteSearchForIP extends PtnDialog {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel siteIpLb;
	private JTextField siteIPText;
	private PtnButton confirmButton;
	private JButton canelButton;
	private JLabel lblMessage;
	private JComboBox proSeriesCombo;
	private JLabel proSeriesLb;
	private SiteSearchTablePanel siteSearchTablePanel;
	/**
	 * 创建一个新的实例
	 * @param siteSearchTablePanel
	 * 			 	主界面。关闭窗口前 做刷新用
	 * @param modal
	 */
	public SiteSearchForIP(SiteSearchTablePanel siteSearchTablePanel, boolean modal) {
		this.setModal(modal);
		try {
			this.siteSearchTablePanel=siteSearchTablePanel;
			initComponentss();
			this.setLayout();
			this.addListener();
			this.initData();
			UiUtil.showWindow(this, 350, 180);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}
	
	/**
	 * 初始化数据
	 * @throws Exception
	 */
	private void initData() throws Exception{
		this.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_SITE_SEARCH_BYGATEWAY));
	}
	
	/**
	 * 添加监听事件
	 */
	private void addListener() {
		//确定按钮
		this.confirmButton.addActionListener(new MyActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jButton1ActionPerformed(e);
			}

			@Override
			public boolean checking() {
				return true;
			}
		});
		//取消按钮
		this.canelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jButton2ActionPerformed(e);
			}
		});
	}

	/**
	 * 初始化控件
	 * @throws Exception
	 */
	private void initComponentss() throws Exception {
		try {
			this.confirmButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM));
			this.canelButton = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
			this.lblMessage=new JLabel();
			this.siteIpLb = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_GATEWAY_SITEIP));
			this.siteIPText = new PtnTextField(true,PtnTextField.TYPE_IP, PtnTextField.IP_MAXLENGTH, this.lblMessage, this.confirmButton,this);
			this.proSeriesLb =  new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SITE_MANUFACTURER));
			this.proSeriesCombo = new PtnComboBox();
			super.getComboBoxDataUtil().comboBoxData(proSeriesCombo, "EDITION");
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	/**
	 * 布局管理
	 */
	private void setLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 50, 200, 50 };
		componentLayout.columnWeights = new double[] { 0, 0, 0 };
		componentLayout.rowHeights = new int[] { 25, 35, 40, 40, 15, 40, 15 };
		componentLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0, 0, 0.2 };
		this.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.insets = new Insets(5, 5, 5, 5);
		componentLayout.setConstraints(this.lblMessage, c);
		this.add(this.lblMessage);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.insets = new Insets(10, 5, 10, 5);
		componentLayout.setConstraints(this.proSeriesLb, c);
		this.add(this.proSeriesLb);
		c.gridx = 1;
		c.gridwidth = 2;
		componentLayout.setConstraints(this.proSeriesCombo, c);
		this.add(this.proSeriesCombo);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.insets = new Insets(10, 5, 10, 5);
		componentLayout.setConstraints(this.siteIpLb, c);
		this.add(this.siteIpLb);
		c.gridx = 1;
		c.gridwidth = 2;
		componentLayout.setConstraints(this.siteIPText, c);
		this.add(this.siteIPText);

		c.anchor = GridBagConstraints.EAST;
		c.fill = GridBagConstraints.NONE;
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.confirmButton, c);
		this.add(this.confirmButton);
		c.gridx = 2;
		componentLayout.setConstraints(this.canelButton, c);
		this.add(this.canelButton);
	}

	/**
	 * 取消事件
	 * @param evt
	 */
	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		this.dispose();
	}

	/**
	 * 确定事件
	 * @param evt
	 */
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		int  proSeries ;
		String ip = this.siteIPText.getText();
		String result;
		proSeries = Integer.parseInt(((Code) ((ControlKeyValue) this.proSeriesCombo.getSelectedItem()).getObject()).getCodeValue());
		try {
			result = this.siteSearchTablePanel.getController().siteSearch(ip, proSeries);
			if (ResourceUtil.srcStr(StringKeysTip.TIP_IPERROR_SEARCH).equals(result)) {
				DialogBoxUtil.errorDialog(this, result);
			}else{
				//添加日志记录
				this.confirmButton.setOperateKey(EOperationLogType.SITEPORTSELECT.getValue());
				confirmButton.setResult(1);
			}
			
			this.dispose();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
}