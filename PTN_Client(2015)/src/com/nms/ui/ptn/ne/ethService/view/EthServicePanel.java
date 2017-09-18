package com.nms.ui.ptn.ne.ethService.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import com.nms.db.bean.ptn.EthServiceInfo;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.ptn.ne.ethService.controller.EthServiceController;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

public class EthServicePanel extends  ContentView<EthServiceInfo>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -676382937682457300L;

	public EthServicePanel() {
		super("ethService",RootFactory.CORE_MANAGE);
		init();
	}

	private void init(){                 
		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.ETHSERVICE_TABLE)));
		setLayout();
		try {
			getController().refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
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
	public Dimension setDefaultSize() {
		return new Dimension(200, ConstantUtil.INT_WIDTH_THREE);
	}
	
	@Override
	public void setController() {
		controller = new EthServiceController(this);
	}
	

	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getExportButton());
		needRemoveButtons.add(getSearchButton());
		return needRemoveButtons;
	}

	public List<JButton> setAddButtons() {
		
		List<JButton> needAddButtons = new ArrayList<JButton>();
		needAddButtons.add(getAddButton());
		needAddButtons.add(getUpdateButton());
		needAddButtons.add(getDeleteButton());
		needAddButtons.add(getRefreshButton());
		needAddButtons.add(getSynchroButton());
		return needAddButtons;
	}
}
