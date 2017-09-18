package com.nms.ui.ptn.ne.tunnel.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.nms.db.bean.ptn.SiteRoate;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.enums.ERotateType;
import com.nms.model.ptn.SiteRoateService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysTitle;

/**
 * 武汉 tunnel  倒换界面
 * @author sy
 *
 */
public class WURoatePanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6059346418061833981L;
	private JRadioButton recoverMain;//恢复到主用
	private JRadioButton forceStand;//强制到备用
	private JRadioButton lockMain;//锁定在主用
	private JRadioButton manpowerStand;//人工倒换到备用
	private JRadioButton clear;//清除
	private JRadioButton roratePractise;//倒换练习
	private JCheckBox siteRorate;//网元倒换
	private ButtonGroup buttonGroup;
	private JPanel contentPanel;
	private JScrollPane scrollPanel;
	private JPanel titlePanel;
	private JLabel lblTitle;
	private Tunnel tunnel;
	private boolean flag;
	
	/**
	 * 新建一个 武汉 倒换tunnel  的实例
	 * @param tunnel
	 *   tunnel   对象
	 * @param flag
	 *    true   A 端
	 *    false  Z 端
	 */
	public WURoatePanel(Tunnel tunnel,boolean flag) {
		this.tunnel=tunnel;
		this.flag=flag;
		this.initComponent();
		setRainValue();
		this.setLayout();
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
		// 给单选按钮赋值，取值时用
		this.recoverMain.setName(ERotateType.FORCESWORK.getValue() + "");
		this.forceStand.setName(ERotateType.FORCESPRO.getValue() + "");
		this.lockMain.setName(ERotateType.MANUALWORK.getValue() + "");
		this.manpowerStand.setName(ERotateType.MANUALPRO.getValue() + "");
		this.roratePractise.setName(ERotateType.LOCK.getValue() + "");
		this.clear.setName(ERotateType.CLEAR.getValue() + "");
	
		contentPanel = new JPanel();
		contentPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTitle.TIT_EXTERNAL_ORDER)));
		scrollPanel = new JScrollPane();
		scrollPanel.setViewportView(contentPanel);
		scrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	}
	/**
	 * 设置  页面 的 倒换命令值
	 */
	public void setRainValue(){
		SiteRoateService_MB siteRoateServiceMB = null;
		SiteRoate siteRoate=null;
		SiteRoate roata=null;
		int roate=0;
		try{
			siteRoateServiceMB = (SiteRoateService_MB) ConstantUtil.serviceFactory
					.newService_MB(Services.SITEROATE);
			siteRoate = new SiteRoate();
			if (flag) {
				siteRoate.setSiteId(tunnel.getASiteId());
			} else {
				siteRoate.setSiteId(tunnel.getZSiteId());
			}
			siteRoate.setTypeId(this.tunnel.getProtectTunnelId());
			siteRoate.setType("tunnel");
			roata = siteRoateServiceMB.select(siteRoate);
			/**
			 * 通过 tunnel id，网元id,查找tunnel倒换对象
			 *    确认 此tunnel倒换是否有倒换值
			 *     有，则修改界面倒换命令的显示
			 */
			if(roata!=null){
				if(roata!=null){
					roate = roata.getRoate();
					if (roate == ERotateType.FORCESWORK.getValue()) {
						recoverMain.setSelected(true);
					} else if (roate == ERotateType.FORCESPRO.getValue()) {
						forceStand.setSelected(true);
					} else if (roate == ERotateType.MANUALWORK.getValue()) {
						lockMain.setSelected(true);
					} else if (roate == ERotateType.MANUALPRO.getValue()) {
						manpowerStand.setSelected(true);
					} else if (roate == ERotateType.LOCK.getValue()) {
						roratePractise.setSelected(true);
						lockMain.setEnabled(true);
						manpowerStand.setEnabled(true);
						forceStand.setEnabled(true);
						recoverMain.setEnabled(true);

					} else if (roate == ERotateType.CLEAR.getValue()) {
						this.buttonGroup.clearSelection();
						lockMain.setEnabled(true);
						manpowerStand.setEnabled(true);
						forceStand.setEnabled(true);
						recoverMain.setEnabled(true);
					}
				}
				if(roata.getSiteRoate()==1){				
					this.siteRorate.setSelected(true);
				}
			}
				 
		}catch(Exception e){
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(siteRoateServiceMB);
		}
	}
	/**
	 * 主页面布局
	 */
	private void setLayout() {
		
//		// title面板布局
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
//		layout.rowHeights = new int[] { 20 };
//		layout.rowWeights = new double[] { 0 };
//		layout.columnWidths = new int[] { 60, ConstantUtil.INT_WIDTH_THREE - 60 };
//		layout.columnWeights = new double[] { 0, 1.0 };
//		titlePanel.setLayout(layout);
//		addComponent(titlePanel, lblTitle, 0, 0, 0, 0, 1, 1, GridBagConstraints.BOTH, new Insets(5, 20, 5, 5), GridBagConstraints.CENTER, c);
		// 主面板布局
		layout = new GridBagLayout();
		layout.rowHeights = new int[] { 200 };
		layout.rowWeights = new double[] { 0.1 };
		layout.columnWidths = new int[] { 120 };
		layout.columnWeights = new double[] { 0.1 };
		this.setLayout(layout);
		addComponent(this, contentPanel, 0, 0, 0.1, 0.1, 1, 1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, c);
		setRorateButton();
	}
	
	/**
	 * 倒换按钮面板布局
	 */
	private void setRorateButton(){
		GridBagLayout contentLayout = new GridBagLayout();
		contentLayout.columnWidths = new int[] { 100,100 };
		contentLayout.columnWeights = new double[] { 0.1,0.1 };
		contentLayout.rowHeights = new int[] { 10,30,30,10 };
		contentLayout.rowWeights = new double[] { 0,0.1,0.1,0.1 };
		GridBagConstraints c = new GridBagConstraints();
		contentPanel.setLayout(contentLayout);
		Insets insert = new Insets(5, 5, 5, 5);
		addComponent(contentPanel, recoverMain, 0, 0, 0.1, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, forceStand, 1, 0, 0.1, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, lockMain, 0, 1, 0.1, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, manpowerStand, 1, 1, 0.1, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		//addComponent(contentPanel, clear, 0, 2, 0.1, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		//addComponent(contentPanel, roratePractise, 1, 2, 0.1, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, siteRorate, 0, 2, 0.1, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
	}
	/**
	 * 添加控件方法
	 * @param panel
	 * @param component
	 * @param gridx
	 * @param gridy
	 * @param weightx
	 * @param weighty
	 * @param gridwidth
	 * @param gridheight
	 * @param fill
	 * @param insets
	 * @param anchor
	 * @param gridBagConstraints
	 */
	private void addComponent(JPanel panel, JComponent component, int gridx, int gridy, double weightx, double weighty, int gridwidth, int gridheight, int fill, Insets insets, int anchor, GridBagConstraints gridBagConstraints) {
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
	
//	private void addComponentJDialog(JDialog panel, JComponent component, int gridx, int gridy, double weightx, double weighty, int gridwidth, int gridheight, int fill, Insets insets, int anchor, GridBagConstraints gridBagConstraints) {
//		gridBagConstraints.gridx = gridx;
//		gridBagConstraints.gridy = gridy;
//		gridBagConstraints.weightx = weightx;
//		gridBagConstraints.weighty = weighty;
//		gridBagConstraints.gridwidth = gridwidth;
//		gridBagConstraints.gridheight = gridheight;
//		gridBagConstraints.fill = fill;
//		gridBagConstraints.insets = insets;
//		gridBagConstraints.anchor = anchor;
//		panel.add(component, gridBagConstraints);
//		
//	}

	public JRadioButton getRecoverMain() {
		return recoverMain;
	}

	public void setRecoverMain(JRadioButton recoverMain) {
		this.recoverMain = recoverMain;
	}

	public JRadioButton getForceStand() {
		return forceStand;
	}

	public void setForceStand(JRadioButton forceStand) {
		this.forceStand = forceStand;
	}

	public JRadioButton getLockMain() {
		return lockMain;
	}

	public void setLockMain(JRadioButton lockMain) {
		this.lockMain = lockMain;
	}

	public JRadioButton getManpowerStand() {
		return manpowerStand;
	}

	public void setManpowerStand(JRadioButton manpowerStand) {
		this.manpowerStand = manpowerStand;
	}

	public JRadioButton getClear() {
		return clear;
	}

	public void setClear(JRadioButton clear) {
		this.clear = clear;
	}

	public JRadioButton getRoratePractise() {
		return roratePractise;
	}

	public void setRoratePractise(JRadioButton roratePractise) {
		this.roratePractise = roratePractise;
	}

	public JCheckBox getSiteRorate() {
		return siteRorate;
	}

	public void setSiteRorate(JCheckBox siteRorate) {
		this.siteRorate = siteRorate;
	}

	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}

	public void setButtonGroup(ButtonGroup buttonGroup) {
		this.buttonGroup = buttonGroup;
	}
	public JPanel getContentPanel() {
		return contentPanel;
	}

	public void setContentPanel(JPanel contentPanel) {
		this.contentPanel = contentPanel;
	}
	
}
