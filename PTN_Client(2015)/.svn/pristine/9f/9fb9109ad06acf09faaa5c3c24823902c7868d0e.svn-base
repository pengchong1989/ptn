package com.nms.ui.ptn.systemManage.view;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import com.nms.db.bean.system.UnLoading;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysOperaType;
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
import com.nms.ui.ptn.systemManage.controller.UnLoadingController;
/**
 * 转储管理
 * 主界面
 * 面板
 * @author sy
 *
 */
public class UnLoadingPanel extends ContentView<UnLoading> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSplitPane splitPane;
	private JTabbedPane tabbedPane;
	private JTextArea textArea;


	public UnLoadingPanel() {
		super("unloadingPanel",RootFactory.SYSTEM_MANAGE);
		init();		
	}
	public void init(){	
		try{			
		this.initComponents();
			this.setViewLayout();
			
			setLayout();
			this.getController().refresh();
		}catch(Exception e){
			ExceptionManage.dispose(e,this.getClass());
		}			
	}
	private void initComponents() {
		this.getInportButton().setText(ResourceUtil.srcStr(StringKeysOperaType.BTN_UNLOAD_INPORT));
		this.getExportButton().setText(ResourceUtil.srcStr(StringKeysOperaType.BTN_UNLOAD_EXPORT));
		super.checkRoot(this.getExportButton(), RootFactory.SYSTEM_MANAGE);
		tabbedPane = new JTabbedPane();
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setOneTouchExpandable(true);
		int high = Double.valueOf(Toolkit.getDefaultToolkit().getScreenSize().getHeight()).intValue() / 2;
		splitPane.setDividerLocation(high - 65);
		splitPane.setTopComponent(this.getContentPanel());
		splitPane.setBottomComponent(tabbedPane);		
	}
	public void setLayout() {
		setTabbedPaneLayout();
		GridBagLayout panelLayout = new GridBagLayout();
		this.setLayout(panelLayout);
		GridBagConstraints c = null;
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		panelLayout.setConstraints(splitPane, c);
		this.add(splitPane);
	}
	@Override
	public void setController() {
		controller = new UnLoadingController(this);	
	}
	private void setViewLayout(){		
		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTitle.TT_UNLOADING)));
//		this.add(getContentPanel());
	}	
	@Override
	public void setTablePopupMenuFactory() {
		setMenuFactory(null);
	}
	/**
	 * 添加消息顯示
	 */
	public void setTabbedPaneLayout() {
		textArea=new javax.swing.JTextArea();
		textArea.setEditable(false);
		tabbedPane.addTab(ResourceUtil.srcStr(StringKeysTab.TAB_UNLOADING_INFO), textArea);
	}

	@Override
	public Dimension setDefaultSize() {
		return new Dimension(160, ConstantUtil.INT_WIDTH_THREE);
	}		
	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getAddButton());
		needRemoveButtons.add(getDeleteButton());
		needRemoveButtons.add(this.getFilterButton());
		needRemoveButtons.add(this.getClearFilterButton());
		needRemoveButtons.add(getSynchroButton());
		needRemoveButtons.add(this.getFiterZero());
		needRemoveButtons.add(getSearchButton());
		return needRemoveButtons;
	}
	public JTextArea getTextArea() {
		return textArea;
	}
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
	

}