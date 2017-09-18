package com.nms.ui.ptn.systemconfig.dialog.qos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import twaver.table.editor.SpinnerNumberEditor;

import com.nms.db.enums.QosCosLevelEnum;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysObj;

public class QosQueuePortConfigPanel extends PortQosQueueCommonConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public  QosQueuePortConfigPanel() {
		this.initComponents();
	}
	@Override
	public void initComponents() {
		super.initComponents();
		setTableModel();
		portQosTable.setModel(portQosTableModel);
		setPortQosTableModel(portQosTableModel);
		setTableDatas();
		
		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.LEFT);
		namePanel.setLayout(fl);
		namePanel.add(queueLabel);       
		namePanel.add(new JLabel("  "));
		namePanel.add(portQosQueueComboBox);		
		portQosQueueComboBox.setActionCommand("portQosQueue");
		portQosQueueScrollPane.setViewportView(portQosTable);

		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		addComponent(mainPanel, portQosQueuePanel, 0, 0, 0.1, 0.1, 1, 1,
				GridBagConstraints.BOTH, new Insets(0, 5, 5, 5),
				GridBagConstraints.CENTER, gbc);
		addComponent(mainPanel, buttonPanel, 0, 1, 0.1, 0.1, 1, 1,
				GridBagConstraints.BOTH, new Insets(0, 5, 5, 5),
				GridBagConstraints.CENTER, gbc);
		
		portQosQueuePanel.setLayout(new BorderLayout());
		portQosQueuePanel.add(namePanel, BorderLayout.NORTH);
		portQosQueuePanel.add(portQosQueueScrollPane,BorderLayout.CENTER);

		
		mainPanel.setPreferredSize(new Dimension(1000, 400));

		this.add(mainPanel);
		this.configSpinner(portQosTable);

	}

	/*
	 * 对设置带宽的列的单元格添加Spinner
	 */
	@Override
	public void configSpinner(JTable table) {
		setIdColumnAttribute(table);
		cosColumn = table.getColumn("COS");
		cosColumn.setCellEditor(new DefaultCellEditor(comboBox));
		
		cirColumn = table.getColumn("CIR(kbps)");
		cirColumn.setCellEditor(new SpinnerNumberEditor("0", ConstantUtil.QOS_CIR_MAX_10G+""));

		weightColumn = table.getColumn(ResourceUtil.srcStr(StringKeysObj.SCHEDUL));
		weightColumn.setCellEditor(new SpinnerNumberEditor("0", "100000"));

		greenLowColumn = table.getColumn(ResourceUtil.srcStr(StringKeysObj.GREEN_LOW));
		greenLowColumn.setCellEditor(new SpinnerNumberEditor("0", "100000"));

		greenHighColumn = table.getColumn(ResourceUtil.srcStr(StringKeysObj.GREEN_HIGH));
		greenHighColumn.setCellEditor(new SpinnerNumberEditor("0", "100000"));

		greenProbility = table.getColumn(ResourceUtil.srcStr(StringKeysObj.GREEN_PROBABILITY));
		greenProbility.setCellEditor(new SpinnerNumberEditor("0", "100"));

		yellowLowColumn = table.getColumn(ResourceUtil.srcStr(StringKeysObj.YELLOW_LOW));
		yellowLowColumn.setCellEditor(new SpinnerNumberEditor("0", "100000"));

		yellowHighColumn = table.getColumn(ResourceUtil.srcStr(StringKeysObj.YELLOW_HIGH));
		yellowHighColumn.setCellEditor(new SpinnerNumberEditor("0", "100000"));

		yellowProbility = table.getColumn(ResourceUtil.srcStr(StringKeysObj.YELLOW_PROBABILITY));
		yellowProbility.setCellEditor(new SpinnerNumberEditor("0", "100"));

	}

	@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
	@Override
	public void setTableModel() {
		columnName = new Vector();
		columnName.add(ResourceUtil.srcStr(StringKeysObj.ORDER_NUM));
		columnName.add("COS");
		columnName.add("CIR(kbps)");
		columnName.add(ResourceUtil.srcStr(StringKeysObj.SCHEDUL));
		columnName.add(ResourceUtil.srcStr(StringKeysObj.GREEN_LOW));
		columnName.add(ResourceUtil.srcStr(StringKeysObj.GREEN_HIGH));
		columnName.add(ResourceUtil.srcStr(StringKeysObj.GREEN_PROBABILITY));
		columnName.add(ResourceUtil.srcStr(StringKeysObj.YELLOW_LOW));
		columnName.add(ResourceUtil.srcStr(StringKeysObj.YELLOW_HIGH));
		columnName.add(ResourceUtil.srcStr(StringKeysObj.YELLOW_PROBABILITY));
		columnName.add(ResourceUtil.srcStr(StringKeysObj.OPEN_WRED_YESORNO));
		columnName.add(ResourceUtil.srcStr(StringKeysObj.MAX_BANDWIDTH));
		portQosTableModel = new DefaultTableModel(null, columnName
				.toArray()) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 0 || column == 1 || column == 11) {
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
	
	public  void setTableDatas() {
		this.portQosTableModel.getDataVector().clear();
		this.portQosTableModel.fireTableDataChanged();
		Object data[] = new Object[] {};
		int rowCount = 0;

		for (QosCosLevelEnum level : QosCosLevelEnum.values()) {
			data = new Object[] { ++rowCount, level.toString(), 0, 16, 96, 128,
					100, 64, 96, 100, Boolean.TRUE, ResourceUtil.srcStr(StringKeysObj.QOS_UNLIMITED) };
			this.portQosTableModel.addRow(data);
		}
	}

}
