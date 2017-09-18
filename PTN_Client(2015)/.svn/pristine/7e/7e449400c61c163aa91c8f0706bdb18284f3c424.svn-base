package com.nms.ui.ptn.ne.macManagement.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import com.nms.db.bean.ptn.MacManagementInfo;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.ne.macManagement.controller.MacManagementController;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

public class MacManagementPanel extends ContentView<MacManagementInfo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5638118410017167790L;
	private MacManagementPanel view;
	
	public MacManagementPanel() {
		super("blackListMacTable", RootFactory.CORE_MANAGE);
		init();
		view = this;
		try {
			this.controller.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void init(){
		this.getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTitle.TIT_BLACK_MAC_MANAGE)));
		this.setLayout();
	}
	
	private void setLayout() {
		GridBagLayout panelLayout = new GridBagLayout();
		this.setLayout(panelLayout);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		panelLayout.setConstraints(this.getContentPanel(), c);
		this.add(this.getContentPanel());
	}

	public MacManagementPanel getView(){
		return view;
	}
	
	@Override
	public void setController() {
		this.controller = new MacManagementController(this);
	}
	
	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getSearchButton());
		return needRemoveButtons;
	}
}
