package com.nms.ui.ptn.safety.roleManage;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.nms.model.util.RoleRootPanel;

/**
 * 角色管理主界面
 * 
 * @author kk
 * 
 */
public class RoleManagePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 创建一个新的实例
	 */
	public RoleManagePanel() {
		this.initComponent();
		this.setLayout();
	}

	/**
	 * 初始化控件
	 */
	private void initComponent() {

		this.splitPane = new JSplitPane();
		this.rightPanel = new RoleRootPanel(false);
		this.leftPane = new RoleInfoLeftPanel(this.rightPanel);
		this.splitPane.setLeftComponent(this.leftPane);
		this.splitPane.setRightComponent(this.rightPanel);
		this.splitPane.setOneTouchExpandable(true);
		//设置左边列表默认宽度为屏幕总宽的8/10
		int width = Double.valueOf(Toolkit.getDefaultToolkit().getScreenSize().getWidth()).intValue() / 10 * 8;
		this.leftPane.setPreferredSize(new Dimension(width, 0));
	}

	/**
	 * 设置布局
	 */
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
		panelLayout.setConstraints(this.splitPane, c);
		this.add(this.splitPane);
	}

	private JSplitPane splitPane; // 分割panel
	private RoleInfoLeftPanel leftPane; // 左边列表panel
	private RoleRootPanel rightPanel; // 右边树panel
}
