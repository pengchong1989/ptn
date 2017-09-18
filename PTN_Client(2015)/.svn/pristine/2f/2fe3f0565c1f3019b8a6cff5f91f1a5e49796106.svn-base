package com.nms.ui.ptn.systemconfig.dialog.qos.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import twaver.table.editor.SpinnerNumberEditor;

import com.nms.db.enums.QosCosLevelEnum;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTab;

public class QosQueueWHPortConfigPanel extends PortQosQueueCommonConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TableColumn modelColumn;
	
	public QosQueueWHPortConfigPanel() {
		this.initComponents();
	}
		
	@Override
	public void initComponents() {
		super.initComponents();
		setTableModel();
		portQosTable.setModel(portQosTableModel);
		setTableDatas();

		portQosQueueScrollPane.setViewportView(portQosTable);
		portQosQueueScrollPane.setPreferredSize(new Dimension(0,165));

		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		addComponent(this, portQosQueuePanel, 0, 0, 0.1, 0.1, 1, 1, GridBagConstraints.BOTH, new Insets(0, 5, 5, 5), GridBagConstraints.CENTER, gbc);
		addComponent(this, buttonPanel, 0, 1, 0.1, 0.1, 1, 1, GridBagConstraints.BOTH, new Insets(0, 5, 5, 5), GridBagConstraints.CENTER, gbc);

		portQosQueuePanel.setLayout(new BorderLayout());
		portQosQueuePanel.add(namePanel, BorderLayout.NORTH);
		portQosQueuePanel.add(portQosQueueScrollPane, BorderLayout.CENTER);
		this.configSpinner(portQosTable);

	}

	/** 
	 * 对设置带宽的列的单元格添加Spinner
	 */
	@Override
	public void configSpinner(JTable table) {
		
		cosColumn = table.getColumn("PHB");
		cosColumn.setCellEditor(new DefaultCellEditor(comboBox));

		cirColumn = table.getColumn("CIR(kbps)");
		cirColumn.setCellEditor(new SpinnerNumberEditor("0", "1000","1"));

		weightColumn = table.getColumn(ResourceUtil.srcStr(StringKeysObj.SCHEDUL));
		weightColumn.setCellEditor(new SpinnerNumberEditor("1", "127", "1"));

		modelColumn = table.getColumn(ResourceUtil.srcStr(StringKeysLbl.LBL_QUEUE_SCHEDULING));
		modelColumn.setCellEditor(new DefaultCellEditor(portQosQueueComboBox));
		
		greenLowColumn = table.getColumn(ResourceUtil.srcStr(StringKeysObj.GREEN_LOW).substring(2));
		greenLowColumn.setCellEditor(new SpinnerNumberEditor("0", "100", "1"));

		greenHighColumn = table.getColumn(ResourceUtil.srcStr(StringKeysObj.GREEN_HIGH).substring(2));
		greenHighColumn.setCellEditor(new SpinnerNumberEditor("0", "100", "1"));
		
		disCard = table.getColumn(ResourceUtil.srcStr(StringKeysObj.GREEN_DISCARD));
		disCard.setCellEditor(new SpinnerNumberEditor("0", "100","50"));
	}

	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	@Override
	public void setTableModel() {
		columnName = new Vector();
		columnName.add(ResourceUtil.srcStr(StringKeysObj.ORDER_NUM));
		columnName.add("PHB");
		columnName.add("CIR(kbps)");
		columnName.add(ResourceUtil.srcStr(StringKeysObj.SCHEDUL));
		columnName.add(ResourceUtil.srcStr(StringKeysLbl.LBL_QUEUE_SCHEDULING));
		columnName.add(ResourceUtil.srcStr(StringKeysObj.GREEN_LOW).substring(2));
		columnName.add(ResourceUtil.srcStr(StringKeysObj.GREEN_HIGH).substring(2));
		columnName.add(ResourceUtil.srcStr(StringKeysObj.OPEN_WRED_YESORNO));
		columnName.add(ResourceUtil.srcStr(StringKeysObj.MAX_BANDWIDTH));
		columnName.add(ResourceUtil.srcStr(StringKeysObj.GREEN_DISCARD));
		
		portQosTableModel = new DefaultTableModel(null, columnName.toArray()) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 0 || column == 8) {
					return false;
				}
				return true;
			}

			@Override
			public Class getColumnClass(int c) {
				return getValueAt(0, c).getClass();
			}

		};
	}

	private void setTableDatas() {
		this.portQosTableModel.getDataVector().clear();
		this.portQosTableModel.fireTableDataChanged();
		Object data[] = new Object[] {};
		int rowCount = 0;
		for (QosCosLevelEnum level : QosCosLevelEnum.values()) {
			data = new Object[] {ResourceUtil.srcStr(StringKeysTab.TAB_USER_PRIORITY_LEVEL) + ++rowCount, level.toString(), 0, 1, "SP", 50, 90, Boolean.FALSE,50};
			this.portQosTableModel.addRow(data);
		}
	}

}
