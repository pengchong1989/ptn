package com.nms.ui.ptn.ne.dual.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.ui.frame.ViewDataTable;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysPanel;

/**
 * MPS保护下方端口信息面板
 * 
 * @author dzy
 * 
 */
public class AllTunnelPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1576957269410364251L;
	private JScrollPane scrollPane; // 滚动条
	private ViewDataTable<Tunnel> table; // table对象
	private final String TABLENAME = "selectTunnelTable"; // table表名
	List<Tunnel> tunnelList ;
	/**
	 * 创建一个新的实例
	 */
	public AllTunnelPanel(List<Tunnel> tunnelList) {
		try {
			this.initComponent();
			this.setLayout();
			this.tunnelList = tunnelList;
			initData(tunnelList);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
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
	 * @throws Exception 
	 */
	public void initData(List<Tunnel> tunnelList) throws Exception {
		if(null!=tunnelList&&tunnelList.size()>0){
			table.initData(tunnelList);
		}
	}

	/**
	 * 初始化控件
	 */
	private void initComponent() {
		this.table = new ViewDataTable<Tunnel>(this.TABLENAME);
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
		this.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_SELECTABLE_TUNNEL)));
		GridBagConstraints c = new GridBagConstraints();
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 230 };
		layout.columnWeights = new double[] { 0 };
		layout.rowHeights = new int[] {200};
		layout.rowWeights = new double[] { 0.1};
		this.setLayout(layout);
		addComponent(this, this.scrollPane, 0, 0, 0.5, 1.0, 0, 1, GridBagConstraints.BOTH, new Insets(5, 0, 0, 0), GridBagConstraints.NORTHWEST, c);
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
	public ViewDataTable<Tunnel> getTable() {
		return table;
	}

	public List<Tunnel> getTunnelList() {
		return tunnelList;
	}

	public void setTunnelList(List<Tunnel> tunnelList) {
		this.tunnelList = tunnelList;
	}
}
