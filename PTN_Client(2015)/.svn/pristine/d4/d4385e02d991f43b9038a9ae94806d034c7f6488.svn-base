package com.nms.ui.ptn.ne.tunnel.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.nms.db.bean.ptn.path.protect.ProtectRorateInfo;
import com.nms.db.enums.EOperationLogType;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysTitle;

public class ProtectRorateDialog extends PtnDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6059346418061833981L;
	private static ProtectRorateDialog protectRorateDialog;
	private JRadioButton recoverMain;//恢复到主用
	private JRadioButton forceStand;//强制到备用
	private JRadioButton lockMain;//锁定在主用
	private JRadioButton manpowerStand;//人工倒换到备用
	private ProtectRorateInfo protectRorateInfo;
	private JRadioButton clear;//清除
	private JRadioButton roratePractise;//倒换练习
	private JCheckBox siteRorate;//网元倒换
	private ButtonGroup buttonGroup;
	private JButton confirmButton;//保存
	private JButton cancleButton;//取消
	private JPanel contentPanel;
	private JPanel buttonPanel;
	private JScrollPane scrollPanel;
	private JPanel titlePanel;
	private JLabel lblTitle;
	
	@SuppressWarnings("static-access")
	public ProtectRorateDialog(ProtectRorateInfo protectRorateInfo) {
		this.protectRorateDialog = this;
		this.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_PROTECTRORATE));
		this.protectRorateInfo = protectRorateInfo;
		this.initComponent();
		this.setLayout();
		this.addListener();
		this.setVlue();
	}

	/**
	 * 初始化控件
	 */ 
	private void initComponent() {
		titlePanel = new JPanel();
		lblTitle = new JLabel();
		recoverMain = new JRadioButton(ResourceUtil.srcStr(StringKeysBtn.BTN_RECOVERMAIN));
		forceStand = new JRadioButton(ResourceUtil.srcStr(StringKeysBtn.BTN_FORCESTAND));
		lockMain = new JRadioButton(ResourceUtil.srcStr(StringKeysBtn.BTN_LOCKMAIN));
		manpowerStand = new JRadioButton(ResourceUtil.srcStr(StringKeysBtn.BTN_MANPOWERSTAND));
		clear = new JRadioButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CLEAR));
		clear.setSelected(true);
		roratePractise = new JRadioButton(ResourceUtil.srcStr(StringKeysBtn.BTN_RORATEPRACTISE));
		siteRorate = new JCheckBox(ResourceUtil.srcStr(StringKeysBtn.BTN_SITERORATE));
		siteRorate.setSelected(false);
		buttonGroup = new ButtonGroup();
		buttonGroup.add(recoverMain);
		buttonGroup.add(forceStand);
		buttonGroup.add(lockMain);
		buttonGroup.add(manpowerStand);
		buttonGroup.add(clear);
		buttonGroup.add(roratePractise);
		contentPanel = new JPanel();
		contentPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTitle.TIT_EXTERNAL_ORDER)));
		scrollPanel = new JScrollPane();
		scrollPanel.setViewportView(contentPanel);
		scrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		buttonPanel = new JPanel();
		cancleButton = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		confirmButton = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE));
	}
	
	/**
	 * 控件布局
	 */
	private void setLayout() {
		
		// title面板布局
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		layout.rowHeights = new int[] { 20 };
		layout.rowWeights = new double[] { 0 };
		layout.columnWidths = new int[] { 60, ConstantUtil.INT_WIDTH_THREE - 60 };
		layout.columnWeights = new double[] { 0, 1.0 };
		titlePanel.setLayout(layout);
		addComponent(titlePanel, lblTitle, 0, 0, 0, 0, 1, 1, GridBagConstraints.BOTH, new Insets(5, 20, 5, 5), GridBagConstraints.CENTER, c);
		// 主面板布局
		layout = new GridBagLayout();
		layout.rowHeights = new int[] { 60, 300, 60 };
		layout.rowWeights = new double[] { 0, 0.7, 0 };
		layout.columnWidths = new int[] { ConstantUtil.INT_WIDTH_THREE };
		layout.columnWeights = new double[] { 1 };
		this.setLayout(layout);
		addComponentJDialog(this, titlePanel, 0, 0, 0, 0, 1, 1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, c);
		addComponentJDialog(this, scrollPanel, 0, 1, 0, 0.2, 1, 1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, c);
		addComponentJDialog(this, buttonPanel, 0, 2, 0, 0, 1, 1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, c);

		// content面板布局
		setRorateButton();
		// button面板布局
		setButtonLayout();
	}
	
	/**
	 * 保存，取消按钮布局
	 */
	private void setButtonLayout() {
		GridBagLayout buttonLayout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		buttonLayout.columnWidths = new int[] { 60, 60, 60, 6 };
		buttonLayout.columnWeights = new double[] { 1.0, 0, 0, 0 };
		buttonLayout.rowHeights = new int[] { 60 };
		buttonLayout.rowWeights = new double[] { 1 };
		buttonPanel.setLayout(buttonLayout);
		addComponent(buttonPanel, cancleButton, 1, 0, 0, 0, 1, 1, GridBagConstraints.WEST, new Insets(5, 5, 5, 20), GridBagConstraints.WEST, c);
		addComponent(buttonPanel, confirmButton, 2, 0, 0, 0, 1, 1, GridBagConstraints.WEST, new Insets(5, 5, 5, 20), GridBagConstraints.WEST, c);
	}

	
	/**
	 * 倒换按钮面板布局
	 */
	private void setRorateButton(){
		GridBagLayout contentLayout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPanel.setLayout(contentLayout);
		Insets insert = new Insets(5, 50, 5, 5);
		addComponent(contentPanel, recoverMain, 0, 0, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, forceStand, 1, 0, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, lockMain, 0, 1, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, manpowerStand, 1, 1, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, clear, 0, 2, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, roratePractise, 1, 2, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, siteRorate, 0, 3, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
	}
	
	public void addComponent(JPanel panel, JComponent component, int gridx, int gridy, double weightx, double weighty, int gridwidth, int gridheight, int fill, Insets insets, int anchor, GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.weightx = weightx;
		gridBagConstraints.weighty = weighty;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.fill = fill;
		gridBagConstraints.insets = insets;
		gridBagConstraints.anchor = anchor;
		panel.add(component, gridBagConstraints);
		
	}
	
	public void addComponentJDialog(JDialog panel, JComponent component, int gridx, int gridy, double weightx, double weighty, int gridwidth, int gridheight, int fill, Insets insets, int anchor, GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.weightx = weightx;
		gridBagConstraints.weighty = weighty;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.fill = fill;
		gridBagConstraints.insets = insets;
		gridBagConstraints.anchor = anchor;
		panel.add(component, gridBagConstraints);
		
	}
	
	/**
	 * 监听事件
	 */
	private void addListener() {
		
		cancleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				 
				dispose();
			}
		});
		confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				 
				try {
					confrimAction();
					
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		});
	}
	
	/**
	 * 控件赋值
	 */
	private void setVlue() {
		if(protectRorateInfo != null){
			recoverMain.setSelected(protectRorateInfo.getRecoverMain()==0?false:true);
			forceStand.setSelected(protectRorateInfo.getForceStand()==0?false:true);
			lockMain.setSelected(protectRorateInfo.getLockMain()==0?false:true);
			manpowerStand.setSelected(protectRorateInfo.getManpowerStand()==0?false:true);
			clear.setSelected(protectRorateInfo.getClear()==0?false:true);
			roratePractise.setSelected(protectRorateInfo.getRoratePractise()==0?false:true);
			siteRorate.setSelected(protectRorateInfo.getSiteRorate()==0?false:true);
		}
	}
	
	/**
	 * 收集界面数据
	 */
	private void confrimAction() {
		 
		protectRorateInfo.setRecoverMain(recoverMain.isSelected()?1:0);
		protectRorateInfo.setForceStand(forceStand.isSelected()?1:0);
		protectRorateInfo.setLockMain(lockMain.isSelected()?1:0);
		protectRorateInfo.setManpowerStand(manpowerStand.isSelected()?1:0);
		protectRorateInfo.setClear(clear.isSelected()?1:0);
		protectRorateInfo.setRoratePractise(roratePractise.isSelected()?1:0);
		protectRorateInfo.setSiteRorate(siteRorate.isSelected()?1:0);
		
		String result = "";
		try {
			DispatchUtil protectRorateDispatch = new DispatchUtil(RmiKeys.RMI_PROTECTRORATE);
			if(protectRorateInfo.getId()>0){
				result = protectRorateDispatch.excuteUpdate(protectRorateInfo);
			}else{
				result = protectRorateDispatch.excuteInsert(protectRorateInfo);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		DialogBoxUtil.succeedDialog(this, result);
		this.dispose();
	}

	public static ProtectRorateDialog getDialog() {
		if (protectRorateDialog == null) {
			protectRorateDialog = new ProtectRorateDialog(null);
		}
		return protectRorateDialog;
	}
}
