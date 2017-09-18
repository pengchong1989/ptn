package com.nms.ui.ptn.statistics.sement;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.TableColumn;

import com.nms.db.bean.report.PathStatisticsWidthRate;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
/**
 * 网元物理连接宽带统计
 * @author sy
 *
 */
public class SegmentWidthRightPane extends ContentView<PathStatisticsWidthRate>{

	private static final long serialVersionUID = -8908193206850294453L;
	public SegmentWidthRightPane() {
		super("segmentWidthTable",RootFactory.CORE_MANAGE);
		try {
			init();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * 初始化时设置列宽
	 */
	private void setColumnWidth(){
		super.getTable().getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(67);
		for (int i = 1; i < 18; i++) {
			TableColumn tc = super.getTable().getTableHeader().getColumnModel().getColumn(i);
			tc.setPreferredWidth(74);
			tc.setMinWidth(24);
			tc.setMaxWidth(124);
		}
	}
	
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getAddButton());
		needRemoveButtons.add(getUpdateButton());
		needRemoveButtons.add(getDeleteButton());
		needRemoveButtons.add(getSynchroButton());
		needRemoveButtons.add(getSearchButton());
		needRemoveButtons.add(getRefreshButton());
		needRemoveButtons.add(getExportButton());
		return needRemoveButtons;
	}
	
	private void init() throws Exception {
		setColumnWidth();
		setLayout();
		try {
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	

	private void setLayout() {
		GridBagLayout panelLayout = new GridBagLayout();
		this.setLayout(panelLayout);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		panelLayout.setConstraints(getContentPanel(), c);
		this.add(getContentPanel());
	}

	@Override
	public void setController() {
	}
}
