package com.nms.rmi.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.champor.license.Features;
import com.nms.rmi.ui.util.LicenseClientUtil;
import com.nms.rmi.ui.util.ServerConstant;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;


/**
 * license导入panel
 * 
 * @author kk
 * 
 */
public class SystemConfigPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public SystemConfigPanel() {
		this.initComponent();			
		this.initIpData();		
		this.setLayout();
		this.addListener();
				
	}

	/**
	 * 初始化IP地址
	 */
	public void initIpData() {
	
		try {		
			this.initIpComboxData();	
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {

		}
	}

	/**
	 * 添加监听事件
	 */
	private void addListener() {

		// 确定按钮
		this.btnSelect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnSelectAction();
			}

		});
	}
	

	
	

	/**
	 * 确定按钮事件
	 * @throws Exception 
	 */
	private void btnSelectAction() {
		
		String ip = null;
		ip = (String) this.ipSelect.getSelectedItem();
		if (null == ip){
			DialogBoxUtil.errorDialog(null,  ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_IP_SELECT));
			return;
		}
		ConstantUtil.serviceIp = (String) this.ipSelect.getSelectedItem();
		DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_IP_SUCCESS));


	}

	/**
	 * 初始化ip下拉列表
	 * @throws Exception 
	 */

	private void initIpComboxData() throws Exception {	
		
		Features features=null;
		LicenseClientUtil licenseClientUtil=null;
		List<Features> f= new ArrayList<Features>();
		List<String> iplist=new ArrayList<String>();
		try {
			DefaultComboBoxModel defaultComboBoxModel = null;
			try {
				
				defaultComboBoxModel = (DefaultComboBoxModel) ipSelect.getModel();
				licenseClientUtil=new LicenseClientUtil();
				iplist = licenseClientUtil.getIp();
				for(int i=0;i<iplist.size();i++){					
					features = licenseClientUtil.getFeatures(ServerConstant.LICENSEPATH+ServerConstant.LICENSEFILENAME,iplist.get(i));
					f.add(features);
					if(null != features){							
					   defaultComboBoxModel.addElement(iplist.get(i).toString());
					   ipSelect.setModel(defaultComboBoxModel);
							}						
					 }				
				
				// 数据库是否已经启动		
				if(ServerConstant.registry!=null){
					   this.ipSelect.setSelectedItem(ConstantUtil.serviceIp);
					   this.ipSelect.setEnabled(false);
					   this.btnSelect.setEnabled(false);					  
				 }

			} catch (Exception e) {
				ExceptionManage.dispose(e, UiUtil.class);
			} finally {
				defaultComboBoxModel = null;
				
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			licenseClientUtil = null;
		}		
	}
	
		
	/**
	 * 初始化控件
	 */
	private void initComponent() {
		this.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_SYSTEM_CONFIG)));
		this.lblIp = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_IP_ADDRESS));
		this.ipSelect = new JComboBox();
		this.btnSelect = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM));
		this.panel_select = new JPanel();
		this.panel_select.setBorder(null);
		this.txtSetIp = new JTextField();
		this.txtSetIp.setEditable(false);
		new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_SET_IP));
		
	}


	/**
	 * 系统IP设置panel布局
	 */
	private void setLayoutSelect() {

		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 120, 200, 120 };
		componentLayout.columnWeights = new double[] { 0, 0.1,0 };
		componentLayout.rowHeights = new int[] { 20, 20 };
		componentLayout.rowWeights = new double[] { 0.1, 0.1 };
				
		this.panel_select.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;

		// 本机Ip的label
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(25, 5, 5, 5);
		componentLayout.setConstraints(this.lblIp, c);
		this.panel_select.add(this.lblIp);

		// 本机Ip的选择
		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		componentLayout.setConstraints(this.ipSelect, c);
		this.panel_select.add(this.ipSelect);

		// 确定按钮
		c.fill = GridBagConstraints.NONE;
		c.gridx = 2;
		componentLayout.setConstraints(this.btnSelect, c);
		this.panel_select.add(this.btnSelect);
				
	}


	/**
	 * 此页面总布局
	 */
	private void setLayout() {
		this.setLayoutSelect();

		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWeights = new double[] { 0.1 };
		componentLayout.rowWeights = new double[] { 0.1, 0.1 };
		this.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTH;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(30, 0, 15, 0);
		componentLayout.setConstraints(this.panel_select, c);
		this.add(this.panel_select);

	}

	
	private JLabel lblIp; // 本机IPlabel
	private JComboBox ipSelect;
	private JButton btnSelect; // 确定按钮
	private JPanel panel_select; // 查询本机ID的panel
	private JTextField txtSetIp; // 系统设置IP文本框

	
	public static void main(String[] args) {
		try {
			FileOutputStream  fs = new FileOutputStream( System.getProperty("user.dir") + "license.xml");
			try {
				fs.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
