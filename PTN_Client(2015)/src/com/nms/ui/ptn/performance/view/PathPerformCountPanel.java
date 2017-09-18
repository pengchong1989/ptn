package com.nms.ui.ptn.performance.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import com.nms.db.bean.perform.PathPerformCountInfo;
import com.nms.ui.frame.ContentView;
import com.nms.ui.ptn.performance.controller.PathPerformCountController;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

/**
 * 端到端性能统计面板
 * guoqc
 */
public class PathPerformCountPanel extends ContentView<PathPerformCountInfo> {
	private static final long serialVersionUID = -7047330173179201099L;
	
	public PathPerformCountPanel() {
		super("pathPerCountTable", RootFactory.PROFOR_MANAGE);
		init();
	}

	public void init() {
		getContentPanel().setBorder(BorderFactory.createTitledBorder("端到端性能统计"));
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
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getAddButton());
		needRemoveButtons.add(getDeleteButton());
		needRemoveButtons.add(getUpdateButton());
		needRemoveButtons.add(getInportButton());
		needRemoveButtons.add(getRefreshButton());
		needRemoveButtons.add(getClearFilterButton());
		needRemoveButtons.add(getFiterZero());
		return needRemoveButtons;
	}
	
	@Override
	public void setTablePopupMenuFactory() {
		setMenuFactory(null);
	}
	
	@Override
	public void setController() {
		controller = new PathPerformCountController(this);
	}
}
