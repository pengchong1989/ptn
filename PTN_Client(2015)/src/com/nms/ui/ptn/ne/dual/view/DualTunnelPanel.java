package com.nms.ui.ptn.ne.dual.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import com.nms.db.bean.ptn.path.protect.DualTunnel;
import com.nms.ui.frame.ViewDataTable;
import com.nms.ui.manager.control.PtnPanel;

/**
 * 业务隧道信息
 * 
 * @author kk
 * 
 */
public class DualTunnelPanel extends PtnPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JScrollPane scrollPane; // 滚动条
	private ViewDataTable<DualTunnel> table; // table对象
	private final String TABLENAME = "dualTunnelTable"; // table表名

	/**
	 * 创建一个新的实例
	 */
	public DualTunnelPanel() {
		this.initComponent();
		this.setLayout();
	}

	/**
	 * 清空表
	 */
	public void clear() {
		this.table.clear();
	}

	/**
	 * 绑定数据
	 * 
	 * @param mspPortInfoList
	 */
	public void initData(List<DualTunnel> dualTunnelList) {
		if (dualTunnelList != null && dualTunnelList.size() > 0) {
			table.initData(dualTunnelList);
		}
	}

	/**
	 * 初始化控件
	 */
	private void initComponent() {
		this.table = new ViewDataTable<DualTunnel>(this.TABLENAME);
		this.table.getTableHeader().setResizingAllowed(true);
		this.table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		this.table.setTableHeaderPopupMenuFactory(null);
		this.table.setTableBodyPopupMenuFactory(null);
		this.scrollPane = new JScrollPane();
		this.scrollPane.setViewportView(this.table);
		this.scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	}

	/**
	 * 设置布局
	 */
	private void setLayout() {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		GridBagLayout qosGridBagLayout = new GridBagLayout();
		this.setLayout(qosGridBagLayout);
		super.addComponent(this, this.scrollPane, 0, 0, 0.5, 1.0, 0, 1, GridBagConstraints.BOTH, new Insets(5, 0, 0, 0), GridBagConstraints.NORTHWEST, gridBagConstraints);
	}
}
