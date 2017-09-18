package com.nms.ui.ptn.ne.mapping.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import com.nms.db.bean.ptn.qos.QosMappingMode;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.ptn.ne.mapping.controller.PhbMappingExpContorller;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

public class PhbMappingExpInfoPanel extends ContentView<QosMappingMode>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6182445022115279023L;

	public PhbMappingExpInfoPanel() {
		super("qosMappingTable",RootFactory.CORE_MANAGE);
		try {
			init();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void init() throws Exception{
		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_TIP_PHBToEXP)));//"PHB到TMC/TMP EXP映射表"
		this.setLayout();
		try {
			getController().refresh();
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
		controller = new PhbMappingExpContorller(this);
	}

	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needButtons = new ArrayList<JButton>();
		needButtons.add(getAddButton());
		needButtons.add(getDeleteButton());
		needButtons.add(getSearchButton());
		return needButtons;
	}
}
