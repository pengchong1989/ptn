package com.nms.ui.ptn.ne.ecn.ospf.redistribution.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;

import com.nms.db.bean.ptn.ecn.OspfRedistribute;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.ptn.ne.ecn.ospf.redistribution.controller.DistributePanelController;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

/**
 * 重分发配置
 * @author sy
 *
 */
public class DistributePanel extends ContentView<OspfRedistribute> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DistributePanel() {
		super("distributeTable",RootFactory.CORE_MANAGE);
		init();
	}

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

	private void initComponents() {
		tabbedPane = new JTabbedPane();
		distributePanel = new JPanel();
		heightScrollPane = new JScrollPane();
		heightScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		heightScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		tabbedPane.addTab(ResourceUtil.srcStr(StringKeysPanel.PANEL_DETAILED_INFORMATION), distributePanel);
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setOneTouchExpandable(true);
		splitPane.setTopComponent(this.getContentPanel());
		//splitPane.setBottomComponent(tabbedPane);
		int high = Double.valueOf(Toolkit.getDefaultToolkit().getScreenSize().getHeight()).intValue() / 2;
		splitPane.setDividerLocation(high - 100);
	}

	private void setLayout() {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		GridBagLayout segmentGridBagLayout = new GridBagLayout();
		segmentGridBagLayout.columnWeights = new double[] { 0.5, 0.5 };
		distributePanel.setLayout(segmentGridBagLayout);
		this.setLayout(new GridBagLayout());
		addComponent(this, splitPane, 0, 1, 1.0, 1.0, 1, 1, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), GridBagConstraints.NORTHWEST, gridBagConstraints);
	}

	@Override
	public void setController() {
		controller = new DistributePanelController(this);
	}
	
	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();		
		needRemoveButtons.add(super.getSearchButton());
		return needRemoveButtons;
	}
	
	@Override
	public Dimension setDefaultSize() {
		return new Dimension(160, ConstantUtil.INT_WIDTH_THREE);
	}

	private JSplitPane splitPane;
	private JTabbedPane tabbedPane;
	private JPanel distributePanel;
	private JScrollPane heightScrollPane;
}
