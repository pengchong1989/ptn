package com.nms.ui.ptn.systemconfig.dialog.qos.dialog;

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
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysObj;

public class QosSectionConfigDialog extends QosCommonConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QosSectionConfigDialog(boolean modal, String title) {
		this.setModal(modal);
		//qosConfigDialog = this;
		this.initComponents();
	}

	@Override
	public void initComponents() {
		super.initComponents();
		setTableModel();
		sectionAQosTable.setModel(sectionAQosTableModel);
		sectionZQosTable.setModel(sectionZQosTableModel);

		GridBagConstraints gbc = new GridBagConstraints();


		addComponent(mainPanel, sectionAPanel, 0, 1, 0.1, 0.1, 1, 1, GridBagConstraints.BOTH, new Insets(0, 5, 5, 5), GridBagConstraints.SOUTH, gbc);
		addComponent(mainPanel, sectionZPanel, 0, 2, 0.1, 0.1, 1, 1, GridBagConstraints.BOTH, new Insets(0, 5, 5, 5), GridBagConstraints.SOUTH, gbc);
		addComponent(mainPanel, buttonPanel, 0, 3, 0.1, 0.0, 1, 1, GridBagConstraints.BOTH, new Insets(0, 5, 5, 5), GridBagConstraints.SOUTH, gbc);

		sectionAPanel.setLayout(new GridBagLayout());
		addComponent(sectionAPanel, namePanel, 0, 0, 0.1, 0.0, 1, 1, GridBagConstraints.BOTH, new Insets(0, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);

		addComponent(sectionAPanel, sectionAScrollPane, 0, 1, 0.1, 0.1, 1, 1, GridBagConstraints.BOTH, new Insets(0, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);

		sectionZPanel.setLayout(new GridBagLayout());
		addComponent(sectionZPanel, nameZPanel, 0, 0, 0.1, 0.0, 1, 1, GridBagConstraints.BOTH, new Insets(0, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(sectionZPanel, sectionZScrollPane, 0, 1, 0.1, 0.1, 1, 1, GridBagConstraints.BOTH, new Insets(0, 5, 5, 5), GridBagConstraints.CENTER, gbc);

		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.LEFT);
		namePanel.setLayout(fl);
		namePanel.add(queueALabel);
		namePanel.add(new JLabel("  "));
		namePanel.add(sectionAQosQueueComboBox);
		nameZPanel.setLayout(fl);
		nameZPanel.add(queueZLabel);
		nameZPanel.add(new JLabel("  "));
		nameZPanel.add(sectionZQosQueueComboBox);

		sectionAQosQueueComboBox.setActionCommand("sectionA");
		sectionZQosQueueComboBox.setActionCommand("sectionZ");
		this.add(mainPanel);
		this.configSpinner(sectionAQosTable,"a");
		this.configSpinner(sectionZQosTable,"z");
	}

	/*
	 * 对设置带宽的列的单元格添加Spinner
	 */
	@Override
	public void configSpinner(JTable table,String type) {
		setIdColumnAttribute(table);
		cosColumn = table.getColumn("COS");
		cosColumn.setCellEditor(new DefaultCellEditor(comboBox));

//		if (CodeConfigItem.getInstance().getWuhan() == 1) {
//			cirColumn = table.getColumn("CIR(Mbps)");
//			if("a".equals(type)){
//				cirColumn.setCellEditor(new SpinnerNumberEditor("0", ConstantUtil.QOS_SEGMENT_A+"", "1"));
//			}else{
//				cirColumn.setCellEditor(new SpinnerNumberEditor("0", ConstantUtil.QOS_SEGMENT_Z+"", "1"));
//			}
//			
//			greenLowColumn = table.getColumn(ResourceUtil.srcStr(StringKeysObj.GREEN_LOW));
//			greenLowColumn.setCellEditor(new SpinnerNumberEditor("0", "100"));
//
//			greenHighColumn = table.getColumn(ResourceUtil.srcStr(StringKeysObj.GREEN_HIGH));
//			greenHighColumn.setCellEditor(new SpinnerNumberEditor("0", "100"));
//		}else{
			cirColumn = table.getColumn("CIR(kbps)");
			if("a".equals(type)){
				cirColumn.setCellEditor(new SpinnerNumberEditor("0", ConstantUtil.QOS_SEGMENT_A+"", "64"));
			}else{
				cirColumn.setCellEditor(new SpinnerNumberEditor("0", ConstantUtil.QOS_SEGMENT_Z+"", "64"));
			}
			
			greenLowColumn = table.getColumn(ResourceUtil.srcStr(StringKeysObj.GREEN_LOW));
			greenLowColumn.setCellEditor(new SpinnerNumberEditor("0", "100000"));

			greenHighColumn = table.getColumn(ResourceUtil.srcStr(StringKeysObj.GREEN_HIGH));
			greenHighColumn.setCellEditor(new SpinnerNumberEditor("0", "100000"));
//		}
		
		weightColumn = table.getColumn(ResourceUtil.srcStr(StringKeysObj.SCHEDUL));
		weightColumn.setCellEditor(new SpinnerNumberEditor("0", "100000"));

		greenProbility = table.getColumn(ResourceUtil.srcStr(StringKeysObj.GREEN_PROBABILITY));
		greenProbility.setCellEditor(new SpinnerNumberEditor("0", "100"));

		yellowLowColumn = table.getColumn(ResourceUtil.srcStr(StringKeysObj.YELLOW_LOW));
		yellowLowColumn.setCellEditor(new SpinnerNumberEditor("0", "100000"));

		yellowHighColumn = table.getColumn(ResourceUtil.srcStr(StringKeysObj.YELLOW_HIGH));
		yellowHighColumn.setCellEditor(new SpinnerNumberEditor("0", "100000"));

		yellowProbility = table.getColumn(ResourceUtil.srcStr(StringKeysObj.YELLOW_PROBABILITY));
		yellowProbility.setCellEditor(new SpinnerNumberEditor("0", "100"));

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setTableModel() {
		columnName = new Vector();
		
		columnName.add(ResourceUtil.srcStr(StringKeysObj.ORDER_NUM));
		columnName.add("COS");
		
//		if (CodeConfigItem.getInstance().getWuhan() == 1) {
//			columnName.add("CIR(Mbps)");
//		}else{
			columnName.add("CIR(kbps)");
//		}
		
		columnName.add(ResourceUtil.srcStr(StringKeysObj.SCHEDUL));
		columnName.add(ResourceUtil.srcStr(StringKeysObj.GREEN_LOW));
		columnName.add(ResourceUtil.srcStr(StringKeysObj.GREEN_HIGH));
		columnName.add(ResourceUtil.srcStr(StringKeysObj.GREEN_PROBABILITY));
		columnName.add(ResourceUtil.srcStr(StringKeysObj.YELLOW_LOW));
		columnName.add(ResourceUtil.srcStr(StringKeysObj.YELLOW_HIGH));
		columnName.add(ResourceUtil.srcStr(StringKeysObj.YELLOW_PROBABILITY));
		columnName.add(ResourceUtil.srcStr(StringKeysObj.OPEN_WRED_YESORNO));
		columnName.add(ResourceUtil.srcStr(StringKeysObj.MAX_BANDWIDTH));
		sectionAQosTableModel = new DefaultTableModel(null, columnName.toArray()) {
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
		sectionZQosTableModel = new DefaultTableModel(null, columnName.toArray()) {
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
}
