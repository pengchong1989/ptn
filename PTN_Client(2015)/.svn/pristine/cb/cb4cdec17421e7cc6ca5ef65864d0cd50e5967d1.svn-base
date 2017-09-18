package com.nms.ui.ptn.basicinfo.dialog.site;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.ptn.basicinfo.dialog.site.controller.SiteSearchController;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
/**
 * 网元搜索  列表
 * @author Dzy
 *
 */
public class SiteSearchTablePanel extends ContentView<SiteInst>{
	
	
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 4394432724350657516L;
		public SiteSearchTablePanel() {
			super("SiteSearchTable",RootFactory.SATYMODU);
			try {
				init();
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
		}
	
	private void init() throws Exception{
		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_SITEINFO)));
		setLayout();
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
		controller = new SiteSearchController(this);
		
	}
	
	/**
	 * 按钮控制
	 */
	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getSynchroButton());
		needRemoveButtons.add(getDeleteButton());
		needRemoveButtons.add(getUpdateButton());
		return needRemoveButtons;
	}
	@Override
	public SiteSearchController getController() {
		return (SiteSearchController) super.controller;
	}
	

}
