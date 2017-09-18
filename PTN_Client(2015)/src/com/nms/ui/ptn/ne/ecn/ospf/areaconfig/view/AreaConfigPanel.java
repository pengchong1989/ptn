/*
 * LspPanl.java
 *
 * Created on __DATE__, __TIME__
 */

package com.nms.ui.ptn.ne.ecn.ospf.areaconfig.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import com.nms.db.bean.ptn.ecn.OSPFAREAInfo;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.ptn.ne.ecn.ospf.areaconfig.controller.AreaPanelController;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

/**
 * Area配置
 * @author
 */
public class AreaConfigPanel extends ContentView<OSPFAREAInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2472915658932230349L;
	private JSplitPane splitPane;
	private JTabbedPane tabbedPane;

	/** Creates new form LspPanl */
	public AreaConfigPanel() {
		super("areaTable",RootFactory.CORE_MANAGE);
		init();
	}

	public void init() {
		initComponent();
		setLayout();
		initData();
	}

	public void initData() {
		this.getRefreshButton().doClick();
	}

	private void initComponent() {
		// tabbedPane = new JTabbedPane();//临时注掉暂时还没有这部分功能
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setOneTouchExpandable(true);
		int high = Double.valueOf(Toolkit.getDefaultToolkit().getScreenSize().getHeight()).intValue() / 2;
		splitPane.setDividerLocation(high - 100);
		splitPane.setTopComponent(this.getContentPanel());
		// splitPane.setBottomComponent(tabbedPane);
	}

	public void setTabbedPaneLayout() {
		tabbedPane.add(ResourceUtil.srcStr(StringKeysPanel.PANEL_REGIONAL_BOUNDARY_ALLOCATION_TABLE), new JPanel());
		tabbedPane.add(ResourceUtil.srcStr(StringKeysPanel.PANEL_VIRTUAL_LINK_CONFIGURATION_TABLE), new JPanel());
	}

	public void setLayout() {
		// setTabbedPaneLayout();
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
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();		
		needRemoveButtons.add(super.getSearchButton());
		return needRemoveButtons;
	}

	@Override
	public void setController() {
		controller = new AreaPanelController(this);
	}

	@Override
	public Dimension setDefaultSize() {
		return new Dimension(160, ConstantUtil.INT_WIDTH_THREE);
	}
}