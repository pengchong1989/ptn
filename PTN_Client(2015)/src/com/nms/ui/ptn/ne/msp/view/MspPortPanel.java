package com.nms.ui.ptn.ne.msp.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import com.nms.ui.frame.ViewDataTable;
import com.nms.ui.ptn.ne.msp.bean.MspPortInfo;

/**
 * MPS保护下方端口信息面板
 * 
 * @author kk
 * 
 */
public class MspPortPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JScrollPane scrollPane; // 滚动条
	private ViewDataTable<MspPortInfo> table; // table对象
	private final String TABLENAME = "mspPortTable"; // table表名

	/**
	 * 创建一个新的实例
	 */
	public MspPortPanel() {
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
	 * @param mspPortInfoList
	 */
	public void initData(List<MspPortInfo> mspPortInfoList) {
		if (mspPortInfoList != null && mspPortInfoList.size() > 0) {
			table.initData(mspPortInfoList);
		}
	}

	/**
	 * 初始化控件
	 */
	private void initComponent() {
		this.table = new ViewDataTable<MspPortInfo>(this.TABLENAME);
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
		addComponent(this, this.scrollPane, 0, 0, 0.5, 1.0, 0, 1, GridBagConstraints.BOTH, new Insets(5, 0, 0, 0), GridBagConstraints.NORTHWEST, gridBagConstraints);
	}

	/**
	 * 添加控件
	 * 
	 * @param panel
	 *            要添加的容器
	 * @param component
	 *            要添加的控件
	 */
	private void addComponent(JPanel panel, JComponent component, int gridx, int gridy, double weightx, double weighty, int gridwidth, int gridheight, int fill, Insets insets, int anchor, GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.weightx = weightx;
		gridBagConstraints.weighty = weighty;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.fill = fill;
		gridBagConstraints.insets = insets;
		gridBagConstraints.anchor = anchor;
		panel.add(component, gridBagConstraints);
	}

}
