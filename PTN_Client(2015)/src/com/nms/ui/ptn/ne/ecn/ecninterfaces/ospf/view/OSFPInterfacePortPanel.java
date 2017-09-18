package com.nms.ui.ptn.ne.ecn.ecninterfaces.ospf.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import com.nms.db.bean.ptn.ecn.OSPFInterface;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.ptn.ne.ecn.ecninterfaces.ospf.controller.OSPFInterfacesPortController;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
/**
 * OSPF接口配置
 * @author Administrator
 *
 */
public class OSFPInterfacePortPanel extends ContentView<OSPFInterface> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public OSFPInterfacePortPanel() {
		super("ospfport",RootFactory.CORE_MANAGE);
		init();
	}

	/**
	 * 初始化
	 */
	private void init() {
		this.initComponents();
		setLayout();
		try {
			getController().refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	@Override
	public void setTablePopupMenuFactory() {
		setMenuFactory(null);
	}

	/**
	 * 初始化控件
	 */
	private void initComponents() {
		tabbedPane = new JTabbedPane();
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setOneTouchExpandable(true);
		double high = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		splitPane.setDividerLocation(Double.valueOf(high).intValue() / 2 - 100);
		splitPane.setTopComponent(this.getContentPanel());
		//splitPane.setBottomComponent(tabbedPane);
	}

	public void setTabbedPaneLayout() {
		tabbedPane.add(ResourceUtil.srcStr(StringKeysPanel.PANEL_DETAILED_INFORMATION), new JPanel());
	}

	/**
	 * 页面布局
	 */
	private void setLayout() {
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
		controller = new OSPFInterfacesPortController(this);
	}

	/**
	 * 设置大小
	 */
	@Override
	public Dimension setDefaultSize() {
		return new Dimension(160, ConstantUtil.INT_WIDTH_THREE);
	}

	/**
	 * 移除按钮
	 */
	 @Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getSearchButton());
		return needRemoveButtons;
	}

	private JSplitPane splitPane;
	private JTabbedPane tabbedPane;
	private JPanel areaPanel;
	private JScrollPane heightScrollPane;
}
