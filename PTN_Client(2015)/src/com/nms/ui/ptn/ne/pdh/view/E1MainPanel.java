package com.nms.ui.ptn.ne.pdh.view;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import com.nms.db.bean.equipment.port.E1Info;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.ptn.ne.pdh.controller.E1MainContorller;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

/**
 * PDH端口
 * @author Administrator
 *
 */
public class E1MainPanel extends ContentView<E1Info> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7600150273816980516L;

	public E1MainPanel() throws Exception {
		super("cxE1",RootFactory.CORE_MANAGE);
		init();
	}

	private void init() throws Exception {

		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_PDH_PORT_MANAGE)));
		setLayout();
		try {
			getController().refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needButtons = new ArrayList<JButton>();
		needButtons.add(getAddButton());
		needButtons.add(getDeleteButton());
		needButtons.add(getSearchButton());
		return needButtons;
	}

	public void setLayout(){
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
	public void setTablePopupMenuFactory() {
		setMenuFactory(null);
	}
	
	@Override
	public void setController() {
		controller = new E1MainContorller(this);
	}
	
	@Override
	public Dimension setDefaultSize() {
		return new Dimension(160, ConstantUtil.INT_WIDTH_THREE);
	}
}
