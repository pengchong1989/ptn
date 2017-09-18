package com.nms.ui.ptn.business.loopProtect;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import com.nms.db.bean.path.Segment;
import com.nms.ui.frame.ViewDataTable;

public class SegmentTablePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private ViewDataTable<Segment> table;
	private final String TABLENAME = "segmentNetworkTable";
	/**
	 * 创建一个实例
	 */
	public SegmentTablePanel() {
		initComponents();
		setLayout();
	}

	public void clear() {
		table.clear();
	}

	/**
	 * 初始化数据
	 * @param segmentList
	 * 				段列表
	 */
	public void initData(List<Segment> segmentList) {
		if (segmentList != null && segmentList.size() > 0) {
			table.initData(segmentList);
		}
	}

	/**
	 * 初始化控件
	 */
	private void initComponents() {
		table = new ViewDataTable<Segment>(TABLENAME);
		table.getTableHeader().setResizingAllowed(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table.setTableHeaderPopupMenuFactory(null);
		table.setTableBodyPopupMenuFactory(null);
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	}

	/**
	 * 布局管理
	 */
	private void setLayout() {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		GridBagLayout qosGridBagLayout = new GridBagLayout();
		this.setLayout(qosGridBagLayout);
		addComponent(this, scrollPane, 0, 0, 0.5, 1.0, 0, 1, GridBagConstraints.BOTH, new Insets(5, 0, 0, 0), GridBagConstraints.NORTHWEST, gridBagConstraints);
	}

	/**
	 * 初始化
	 * @param panel
	 * 			面板
	 * @param component
	 * 				控件
	 * @param gridx
	 * 			gridx
	 * @param gridy
	 * 			gridy
	 * @param weightx
	 * 			weightx
	 * @param weighty
	 * 			weighty
	 * @param gridwidth
	 * 				gridwidth
	 * @param gridheight
	 * 				gridheight
	 * @param fill
	 * 			fill
	 * @param insets
	 * 			insets
	 * @param anchor
	 * 			anchor
	 * @param gridBagConstraints
	 * 					gridBagConstraints
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

	public ViewDataTable<Segment> getTable() {
		return table;
	}
}
