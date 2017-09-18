package com.nms.ui.ptn.ne.ecn.route.routetransmit.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JTabbedPane;

import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.ne.ecn.route.staticroute.view.StaticRoutePanel;

/**
 * 路由管理
 * @author sy
 *
 */
public class RoutePanel extends javax.swing.JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoutePanel() {
		init();
	}

	private void init() {
		this.initComponents();
		this.setMainLayout();
//		setLayout();
		try {
			// getController().refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void initComponents() {
		tabbedPane = new JTabbedPane();
	
	}

	private void setMainLayout() {

		this.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTitle.TIT_LINK_MANAGE)));
		tabbedPane.addTab(ResourceUtil.srcStr(StringKeysPanel.PANEL_LINK_RELAY_TABLE), new RouteTransmitPanel());
		tabbedPane.addTab(ResourceUtil.srcStr(StringKeysPanel.PANEL_STATICS_LINK_CONFIG), new StaticRoutePanel());

		GridBagLayout contentLayout = new GridBagLayout();
		this.setLayout(contentLayout);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.insets = new Insets(0, 10, 0, 10);
		c.fill = GridBagConstraints.BOTH;
		contentLayout.setConstraints(this.tabbedPane, c);
		this.add(this.tabbedPane);
	}

//	private void setLayout() {
//		GridBagLayout panelLayout = new GridBagLayout();
//		this.setLayout(panelLayout);
//		GridBagConstraints c = null;
//		c = new GridBagConstraints();
//		c.gridx = 0;
//		c.gridy = 0;
//		c.gridheight = 1;
//		c.gridwidth = 1;
//		c.weightx = 1.0;
//		c.weighty = 1.0;
//		c.fill = GridBagConstraints.BOTH;
//		panelLayout.setConstraints(tabbedPane, c);
//		this.add(tabbedPane);
//	}

	private JTabbedPane tabbedPane;
}
