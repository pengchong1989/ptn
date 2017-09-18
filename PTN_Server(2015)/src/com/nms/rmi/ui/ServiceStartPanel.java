package com.nms.rmi.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.champor.license.Features;
import com.nms.rmi.thread.StartThread;
import com.nms.rmi.thread.StopThread;
import com.nms.rmi.ui.util.LicenseClientUtil;
import com.nms.rmi.ui.util.ServerConstant;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysLbl;

/**
 * 服务器启动管理
 * 
 * @author kk
 * 
 */
public class ServiceStartPanel extends JPanel {

	/**
	 * 用来记录此次点击按钮是启动还是停止
	 */
	private boolean isSrart = true;
	private static ServiceStartPanel serviceStartPanel = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceStartPanel() {		
		this.initComponent();
		this.setLayout();
		this.addListener();
		serviceStartPanel = this;
	}


		
	public static ServiceStartPanel getServiceStartPanel() {
		if (null == serviceStartPanel) {
			serviceStartPanel = new ServiceStartPanel();
		}
		return serviceStartPanel;
	}
	
	
	/**
	 * 添加监听
	 */
	private void addListener() {
		this.btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (isSrart) {
					startListener();
				} else {
					stopListener();
				}

			}

		});
	}

	/**
	 * 按钮执行后回调
	 * 
	 * @param isStart
	 *            true为启动后 false为关闭后
	 * @param isSuccess
	 *            按钮点击事件处理是否成功
	 * @param str
	 *            启动或关闭结果
	 */
	public void buttonResult(boolean isStart, boolean isSuccess, String str) {
		if (isSuccess) {
			// 启动成功，按钮换成关闭图片，isStart=false
			if (isStart) {
				this.btnStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/nms/ui/images/allStop.png")));
				this.isSrart = false;
			} else {
				// 关闭成功，按钮换成启动图片，isStart=true
				this.btnStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/nms/ui/images/allStart.png")));
				this.isSrart = true;
			}
		}
		// 等待条图标设置为null 并且把返回消息显示在界面中
		this.lblWait.setIcon(null);
		this.lblWait.setText(str);
		this.btnStart.setEnabled(true);
	}

	/**
	 * 启动按钮事件
	 */
	private void startListener() {

		try {
			    this.waitShow();
			    ConstantUtil.hasStop = false;	
			   //判断当前的license中的网卡是否有开启
		    	String resultStr;			
				//刷新IP
				LicenseClientUtil licenseClientUtil = new LicenseClientUtil();
				List<Features> f= new ArrayList<Features>();
				Features features=null;
				List<String> ipList = licenseClientUtil.getIp();
				ConstantUtil.allip = ipList;	
//				   for(int i=0;i<ConstantUtil.allip.size();i++){
//				       features = licenseClientUtil.getFeatures(ServerConstant.LICENSEPATH+ServerConstant.LICENSEFILENAME,ConstantUtil.allip.get(i).toString());
//				        f.add(features);
//				   }
				 					
			        Set<Features> s=new HashSet<Features>(f);	
			      //s.size()为0时表示所有网卡都被禁用；s.size()为1时,null == f.get(0)判断license与网卡不匹配
//			        if(s.size()==0  ){
//			        	resultStr = ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_CARD);
//						serviceStartPanel.buttonResult(true, false, resultStr);
//					    return;			   
//		 	    	}else if(s.size()==1 && null == f.get(0)){		 	    	
//		 	    		resultStr = ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_ROOTKEY);
//						serviceStartPanel.buttonResult(true, false, resultStr);
//					    return;		 	    		
//		 	    	}else{		 			 	    		
		 	    	//没有设置IP是将第一个值给serviceIp	
		 			if(ConstantUtil.serviceIp.equals("127.0.0.1") ){
		 				ConstantUtil.serviceIp=ipList.get(0);
		 				System.setProperty("java.rmi.server.hostname",ConstantUtil.serviceIp);
			            new Thread(new StartThread(this)).start();
		 			}else{			 			
		 				 features = licenseClientUtil.getFeatures(ServerConstant.LICENSEPATH+ServerConstant.LICENSEFILENAME,ConstantUtil.serviceIp);
		 				//设置了IP之后判断更换license有没有更换IP
		 				 if(null == features){
		 		        	resultStr = ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_SELECTIP);
		 					serviceStartPanel.buttonResult(true, false, resultStr);
		 					return; 
		 				 }else{
			             System.setProperty("java.rmi.server.hostname",ConstantUtil.serviceIp);
			             new Thread(new StartThread(this)).start();
		 				 }
		 				
		 			}
//			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

	}

	/**
	 * 停止按钮时间
	 */
	private void stopListener() {

		try {
			this.waitShow();
			ConstantUtil.hasStop = true;
			new Thread(new StopThread(this)).start();

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 点击按钮之前，把进度条显示出来
	 */
	private void waitShow() {
		this.btnStart.setEnabled(false);
		this.lblWait.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/nms/ui/images/progress.gif")));
		this.lblWait.setText("");

	}

	/**
	 * 控件初始化
	 */
	private void initComponent() {
		this.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_START_MANAGE)));

		this.btnStart = new JButton();
		this.btnStart.setPreferredSize(new Dimension(50, 40));
		this.btnStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/nms/ui/images/allStart.png")));

		this.panel_all = new JPanel();
		this.panel_all.setBorder(BorderFactory.createTitledBorder(""));
		this.lblBtnExplain = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_KEY_STARTORCLOSE));
		this.lblWait = new JLabel();
	}

	/**
	 * 设置一键启动布局
	 */
	private void setPanelAllLayout() {

		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 60, 90, 270 };
		componentLayout.columnWeights = new double[] { 0, 0, 0.1 };
		componentLayout.rowHeights = new int[] { 70 };
		componentLayout.rowWeights = new double[] { 0.1 };
		this.panel_all.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 10, 0, 5);
		componentLayout.setConstraints(this.btnStart, c);
		this.panel_all.add(this.btnStart);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.insets = new Insets(0, 5, 0, 15);
		componentLayout.setConstraints(this.lblBtnExplain, c);
		this.panel_all.add(this.lblBtnExplain);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.insets = new Insets(0, 15, 0, 10);
		c.anchor = GridBagConstraints.CENTER;
		componentLayout.setConstraints(this.lblWait, c);
		this.panel_all.add(this.lblWait);
	}

	/**
	 * 布局
	 */
	private void setLayout() {
		this.setPanelAllLayout();

		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWeights = new double[] { 0.1 };
		componentLayout.rowHeights = new int[] {};
		componentLayout.rowWeights = new double[] { 0.1 };
		this.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTH;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(100, 0, 0, 0);
		componentLayout.setConstraints(this.panel_all, c);
		this.add(this.panel_all);

	}

	private JButton btnStart; // 启动按钮
	private JPanel panel_all; // 启动panel
	private JLabel lblBtnExplain; // 启动按钮说明
	private JLabel lblWait; // 启动等待条
}
