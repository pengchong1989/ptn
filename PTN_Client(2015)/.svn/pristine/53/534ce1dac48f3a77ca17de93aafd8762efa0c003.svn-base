/*
 * PwNNIPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package com.nms.ui.ptn.ne.pwnni.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import com.nms.db.bean.ptn.path.pw.PwNniInfo;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.ptn.ne.pwnni.controller.PwNNIController;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

/**
 *
 * @author  __USER__
 */
public class PwNNIPanel extends ContentView<PwNniInfo> {

	/** Creates new form PwNNIPanel */
	public PwNNIPanel() {
		super("pwNNIStreamTable",RootFactory.CORE_MANAGE);
		init();
		this.getRefreshButton().doClick();
	}
	
	public void init() {
		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_PW_PORT_MANAGE)));
		setLayout();
	}

	public void setLayout() {
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
		panelLayout.setConstraints(this.getContentPanel(), c);
		this.add(this.getContentPanel());
	}

	@Override
	public void setTablePopupMenuFactory() {
		setMenuFactory(null);
	}
	
	public List<PwNniInfo> getSelectInfo(){
		List<PwNniInfo> list = new ArrayList<PwNniInfo>();
		list = super.getAllSelect();
		return list;
	}
	
	@Override
	public void setController() {
		controller = new PwNNIController(this);
	}
	
	@Override
	public Dimension setDefaultSize() {
		return new Dimension(160, ConstantUtil.INT_WIDTH_THREE);
	}
	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getSearchButton());
		return needRemoveButtons;
	}
}