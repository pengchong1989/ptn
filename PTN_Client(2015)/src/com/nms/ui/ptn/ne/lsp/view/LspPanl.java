/*
 * LspPanl.java
 *
 * Created on __DATE__, __TIME__
 */

package com.nms.ui.ptn.ne.lsp.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import com.nms.db.bean.ptn.path.tunnel.Lsp;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.ptn.ne.lsp.controller.LspNodeController;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

/**
 *
 * @author  ZT
 */
public class LspPanl extends ContentView<Lsp> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2472915658932230349L;
	private JSplitPane splitPane;
	private JTabbedPane tabbedPane;
	
	/** Creates new form LspPanl */
	public LspPanl() {
		super("lspNodeTable",RootFactory.CORE_MANAGE);
		init();
		getRefreshButton().doClick();
	}
	
	public void init() {
		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_LSP_MANAGE)));
		initComponent();
		setLayout();
	}
	
	private void initComponent() {
		tabbedPane = new JTabbedPane();
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setOneTouchExpandable(true);
		double high = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		splitPane.setDividerLocation(Double.valueOf(high).intValue() / 2 - 20);
		splitPane.setTopComponent(this.getContentPanel());
		splitPane.setBottomComponent(tabbedPane);
	}
	
	public void setTabbedPaneLayout() {
		tabbedPane.add(ResourceUtil.srcStr(StringKeysTab.TAB_BASIC_INFO), new JPanel());
	}

	public void setLayout() {
		setTabbedPaneLayout();
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
		panelLayout.setConstraints(splitPane, c);
		this.add(splitPane);
	}

	@Override
	public void setController() {
		controller = new LspNodeController(this);
	}
	
	@Override
	public Dimension setDefaultSize() {
		return new Dimension(160, ConstantUtil.INT_WIDTH_THREE);
	}
	
	@Override
	public  List<JButton> setNeedRemoveButtons(){
		List<JButton> btList = new ArrayList<JButton>();
		btList.add(getAddButton());
		btList.add(getUpdateButton());
		btList.add(getDeleteButton());		
		return btList;
	}

	
}