package com.nms.ui.ptn.alarm.tca;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import com.nms.db.bean.alarm.TCAAlarm;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

/**
 * tca告警panel
 * @author kk
 *
 */
public class TCAAlarmPanel extends ContentView<TCAAlarm>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TCAAlarmPanel() {
		super("tcaAlarm", RootFactory.ALARMMODU);
		try {
			this.setLayout();
			super.getController().refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}

	@Override
	public void setController() {
		super.controller=new TCAAlarmController(this);
	}

	/**
	 * 设置布局
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
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(super.getAddButton());
		needRemoveButtons.add(super.getUpdateButton());
		needRemoveButtons.add(super.getDeleteButton());
		needRemoveButtons.add(super.getInportButton());
		needRemoveButtons.add(super.getExportButton());
		needRemoveButtons.add(super.getFiterZero());
		needRemoveButtons.add(super.getFilterButton());
		needRemoveButtons.add(super.getClearFilterButton());
		return needRemoveButtons;
	}
}
