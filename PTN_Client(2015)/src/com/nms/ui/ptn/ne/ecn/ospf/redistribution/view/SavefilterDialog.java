package com.nms.ui.ptn.ne.ecn.ospf.redistribution.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;

import com.nms.db.bean.equipment.port.PortStm;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

public class SavefilterDialog extends ContentView<PortStm>{
	public SavefilterDialog() {
		super("datetopo",RootFactory.CORE_MANAGE);
		init();
	}

	private void init() {
		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTitle.TIT_FILTER_ITEM)));
		this.initComponents();
		setLayout();
		try {
//			getController().refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	@Override
	public void setTablePopupMenuFactory() {
		setMenuFactory(null);
	}

	private void initComponents() {
		segmentPanel = new JPanel();
		heightScrollPane = new JScrollPane();
		heightScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		heightScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
//		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
//		splitPane.setOneTouchExpandable(true);
		splitPane.setTopComponent(this.getContentPanel());
		int high = Double.valueOf(Toolkit.getDefaultToolkit().getScreenSize().getHeight()).intValue() / 2;
		splitPane.setDividerLocation(high - 65);
	}

	private void setLayout() {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		GridBagLayout segmentGridBagLayout = new GridBagLayout();
		segmentGridBagLayout.columnWeights = new double[] { 0.5, 0.5 };
		segmentPanel.setLayout(segmentGridBagLayout);
		this.setLayout(new GridBagLayout());
		addComponent(this, splitPane, 0, 1, 1.0, 1.0, 1, 1, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), GridBagConstraints.NORTHWEST, gridBagConstraints);
	}

	@Override
	public void setController() {
//		controller = new SDHPanelController(this);
	}
	
	@Override
	public Dimension setDefaultSize() {
		return new Dimension(1000, ConstantUtil.INT_WIDTH_THREE);
	}
	
	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getRefreshButton());
		return needRemoveButtons;
	}
	
	private JSplitPane splitPane;
	private JPanel segmentPanel; 
	private JScrollPane heightScrollPane; 
}
