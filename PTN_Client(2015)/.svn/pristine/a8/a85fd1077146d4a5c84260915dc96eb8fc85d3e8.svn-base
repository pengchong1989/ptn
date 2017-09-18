package com.nms.ui.ptn.ne.ecn.route.routetransmit.view;

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

import com.nms.db.bean.equipment.port.PortStm;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

/**
 * 路由转发表
 * @author sy
 *
 */
public class RouteTransmitPanel extends ContentView<PortStm>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RouteTransmitPanel() {
		super("routetransmitTable",RootFactory.CORE_MANAGE);
		init();
	}

	private void init() {
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
		routetransmitpanel = new JPanel();
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		double high = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		splitPane.setDividerLocation(Double.valueOf(high).intValue() / 2 - 100);
		splitPane.setTopComponent(this.getContentPanel());
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
		panelLayout.setConstraints(splitPane, c);
		this.add(splitPane);
	}

	@Override
	public void setController() {
//		controller = new SDHPanelController(this);
	}
	
	@Override
	public Dimension setDefaultSize() {
		return new Dimension(160, ConstantUtil.INT_WIDTH_THREE);
	}
	
	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getAddButton());
		needRemoveButtons.add(getUpdateButton());
		needRemoveButtons.add(getDeleteButton());
		needRemoveButtons.add(getSearchButton());
		return needRemoveButtons;
	}
	
	private JSplitPane splitPane;
	private JPanel routetransmitpanel; 
	private JScrollPane heightScrollPane; 
}
