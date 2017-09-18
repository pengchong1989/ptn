package com.nms.ui.ptn.statistics.layerRate;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;

import com.nms.db.bean.system.ELayerRateInfo;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysMenu;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

public class LayerRateCountPanel extends ContentView<ELayerRateInfo> {
	private static final long serialVersionUID = 5674854431723298678L;
	public LayerRateCountPanel() {
		super("layerRateTable", RootFactory.PROFOR_MANAGE);
		this.init();
	}
	
	private void init(){
		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysMenu.MENU_LAYERRATECOUNT)));
		this.setLayout();
		try {
			this.controller.refresh();
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
		panelLayout.setConstraints(this.getContentPanel(), c);
		this.add(this.getContentPanel());
	}
	
	@Override
	public void setTablePopupMenuFactory() {
		setMenuFactory(null);
	}

	@Override
	public void setController() {
		this.controller = new LayerRateCountController(this);
	}
}
