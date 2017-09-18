package com.nms.ui.ptn.business.topo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.ui.frame.ViewDataTable;

public class OamPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7966596108411693291L;
	private ViewDataTable<OamInfo> oamTable;
	private JScrollPane oamScrollPane;
	
	public OamPanel() {
		init();
	}

	private void init() {
		initComponents();
		setLayout();
		clear();
	}
	
	public void initData(List<OamInfo> oamList){
		this.clear();
		oamTable.initData(oamList);
		this.updateUI();
	}

	private void initComponents() {
		oamTable = new ViewDataTable<OamInfo>("pwBusinessOAMTable");
		oamTable.getTableHeader().setResizingAllowed(true);
		oamTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		oamTable.setTableHeaderPopupMenuFactory(null);
		oamTable.setTableBodyPopupMenuFactory(null);
		oamScrollPane = new JScrollPane();
		oamScrollPane.setViewportView(oamTable);
		oamScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		oamScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	}

	public void clear() {
		oamTable.clear();
		oamTable.clearSelection();
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
		c.anchor = GridBagConstraints.NORTH;
		c.insets = new Insets(0, 0, 0, 0);
		panelLayout.setConstraints(oamScrollPane, c);
		this.add(oamScrollPane);
	}

}
