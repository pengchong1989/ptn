package com.nms.ui.ptn.event.oamEvent.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import com.nms.db.bean.event.OamEventInfo;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.ptn.event.oamEvent.controller.OamEventController;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

public class OamEventPanel extends ContentView<OamEventInfo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 913791563506441097L;

	public OamEventPanel() {
		super("oamEvent", RootFactory.DEPLOYMODU);
		init();
	}

	private void init() {
		
		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_OAM_EVENT)));
		setLayout();	
	}
	
	private void setLayout() {
		GridBagLayout panelLayout = new GridBagLayout();
		this.setLayout(panelLayout);
		panelLayout.rowHeights = new int[] {400, 10};
		panelLayout.rowWeights = new double[] {0, 0};
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
	public void setController() {
		controller = new OamEventController(this);
	}
	
	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needButtons = new ArrayList<JButton>();
		needButtons.add(getAddButton());
		needButtons.add(getSearchButton());
		needButtons.add(getUpdateButton());
		needButtons.add(getDeleteButton());
		needButtons.add(getSynchroButton());
		return needButtons;
	}
	
//	@Override
//	public List<JButton> setAddButtons() {
//		List<JButton> buttons = new ArrayList<JButton>();
//		buttons.add(getExportButton());
//		return buttons;
//	}
}
