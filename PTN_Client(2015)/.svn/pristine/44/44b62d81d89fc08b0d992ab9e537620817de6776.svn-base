package com.nms.ui.ptn.ne.ecn.ecninterfaces;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JTabbedPane;

import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.ne.ecn.ecninterfaces.ccn.view.CCNPanel;
import com.nms.ui.ptn.ne.ecn.ecninterfaces.mcn.view.McnPanel;
import com.nms.ui.ptn.ne.ecn.ecninterfaces.ospf.view.OSFPInterfacePortPanel;

/**
 * MCN管理
 * @author sy
 *
 */
public class EcninterfacesPanel extends javax.swing.JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EcninterfacesPanel() {
		init();
	}

	private void init() {
		this.initComponents();
		this.setMainLayout();
		// setLayout();
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

		this.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTitle.TIT_ECN_INTERFACE_MANAGE)));
		tabbedPane.addTab(ResourceUtil.srcStr(StringKeysPanel.PANEL_MCN_MANAGE), new McnPanel());
		tabbedPane.addTab(ResourceUtil.srcStr(StringKeysPanel.PANEL_CCN_MANAGE), new CCNPanel());
		tabbedPane.addTab(ResourceUtil.srcStr(StringKeysPanel.PANEL_OSPF_INTERFACE_CONFIG), new OSFPInterfacePortPanel());
		// 暂时不实现
		// tabbedPane.addTab("数据平面拓扑", new DataPlaneTopoPanel());

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

	private JTabbedPane tabbedPane;
}
