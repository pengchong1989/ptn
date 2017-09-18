package com.nms.ui.ptn.client.view;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import com.nms.db.bean.client.Client;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.ptn.client.controller.ClientManagerController;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

/**
 * 客户信息
 * @author dzy
 *
 */
public class ClientInfoPanel extends ContentView<Client>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7135694577504213369L;
	public ClientInfoPanel() {
		super("ClientInfoTable",RootFactory.DEPLOY_MANAGE);
		try {
			init();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * 初始化控件
	 * @throws Exception
	 */
	private void init() throws Exception{
		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTab.TAB_CLIENTINFO)));
		setLayout();
	}

	/**
	 * 布局管理
	 */
	private void setLayout() {
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
		panelLayout.setConstraints(getContentPanel(), c);
		this.add(getContentPanel());
		
		
	}
	
	@Override
	public void setController() {
	//	controller = new ClientManagerController(this);
		
	}

	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getSearchButton());
		needRemoveButtons.add(getSynchroButton());
		needRemoveButtons.add(getAddButton());
		needRemoveButtons.add(getDeleteButton());
		needRemoveButtons.add(getUpdateButton());
		needRemoveButtons.add(getRefreshButton());
		return needRemoveButtons;
	}
	
	public ClientManagerController getController() {
		return (ClientManagerController) super.controller;
	}

	

}
