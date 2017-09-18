package com.nms.ui.ptn.systemManage.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import com.nms.db.bean.system.UnLoading;
import com.nms.ui.frame.ViewDataTable;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysOperaType;
/**
 * 转储管理列表管理  Panel
 * @author sy
 *
 */
@SuppressWarnings("serial")
public class UnLoadManagerPanel extends JPanel {
	
	private ViewDataTable<UnLoading> table;
	private   String TABLENAME = "unloadupdatePanel";
	private JScrollPane scrollPane;// 滚动

	@SuppressWarnings("unused")
	private UnLoadUpdateDialog unloadingPanel=null;
	public UnLoadManagerPanel(UnLoadUpdateDialog unloadingPanel) {
		this.unloadingPanel=unloadingPanel;
		initComponents();
		setLayout();

	}
	
	
	private void initComponents() {
		
		table = new ViewDataTable<UnLoading>(TABLENAME);
		table.getTableHeader().setResizingAllowed(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table.setTableHeaderPopupMenuFactory(null);
		table.setTableBodyPopupMenuFactory(null);
		table.setPreferredScrollableViewportSize(new Dimension( 
		(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()-200, 
		(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()-200));
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
	}

	private void setLayout() {
		this.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysOperaType.BTN_UNLOAD_INPORT_LIST)));
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		GridBagLayout qosGridBagLayout = new GridBagLayout();
		this.setLayout(qosGridBagLayout);
		
		addComponent(this, scrollPane, 0, 0, 0.5, 1.0, 0, 1, GridBagConstraints.BOTH, new Insets(5, 0, 0, 0), GridBagConstraints.NORTHWEST, gridBagConstraints);
	}

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
