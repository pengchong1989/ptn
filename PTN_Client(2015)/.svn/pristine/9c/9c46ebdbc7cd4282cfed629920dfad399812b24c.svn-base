/*
 * AlarmDescPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package com.nms.ui.ptn.configinfo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import com.nms.db.bean.alarm.WarningLevel;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

/**
 * 
 * @author __USER__
 */
public class AlarmDescPanel extends ContentView<WarningLevel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlarmDescPanel() {
		super("alarmTable",RootFactory.ALARM_MANAGE);
		try {
			super.getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTab.TAB_ALARM)));
			setLayout();
			super.getController().refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

	}

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
		super.controller = new AlarmDescController(this);
	}
	
	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getAddButton());
		needRemoveButtons.add(getDeleteButton());
		needRemoveButtons.add(getSearchButton());
		needRemoveButtons.add(getSynchroButton());
		return needRemoveButtons;
	}

}