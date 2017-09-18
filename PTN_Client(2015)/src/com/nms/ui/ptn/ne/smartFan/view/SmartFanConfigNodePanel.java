package com.nms.ui.ptn.ne.smartFan.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import com.nms.db.bean.ptn.SmartFan;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.ptn.ne.smartFan.controller.SmartFanConfigNodeController;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
/**
 * @author guoqc
 */
public class SmartFanConfigNodePanel extends ContentView<SmartFan> {
	private static final long serialVersionUID = 6809391197628746161L;
	private SmartFanConfigNodePanel view;
	
	public SmartFanConfigNodePanel() {
		super("smartFanTable", RootFactory.CORE_MANAGE);
		this.getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTab.TAB_SMARTFAN)));
		this.setLayout();
		try {
			this.view = this;
			super.controller.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 界面布局
	 */
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
	
	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getAddButton());
		needRemoveButtons.add(getDeleteButton());
		needRemoveButtons.add(getSearchButton());
		needRemoveButtons.add(getInportButton());
		needRemoveButtons.add(getExportButton());
		needRemoveButtons.add(getFiterZero());
		return needRemoveButtons;
	}

	@Override
	public void setController() {
		super.controller = new SmartFanConfigNodeController(this);
	}

	public SmartFanConfigNodePanel getView() {
		return view;
	}
}