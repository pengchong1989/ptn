package com.nms.ui.ptn.systemconfig.dialog.qos.dialog;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import twaver.table.editor.SpinnerNumberEditor;

import com.nms.db.enums.QosCosLevelEnum;
import com.nms.db.enums.QosTemplateTypeEnum;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;

public class AddQosTemplateDialog extends PtnDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2576762115137672999L;
	private JPanel mainPanel;
	private JPanel middlePanel;
	private JPanel tablePanel;
	private JLabel tempNameLabel;
	private JTextField tempNameField;
	private JLabel qosTypeLabel;
	private JComboBox qosTypeComboBox;
	private JScrollPane tableScrollPane;
	private DefaultTableModel qosTableModel;
	private JTable tempTable;
	private PtnButton saveButton;
	private PtnButton cancelButton;
	private JPanel buttonPanel;
	private JComboBox comboBox;
	private String identify;
	//public static AddQosTemplateDialog addQosTemplateDialog = null;
	private Vector<String> columnName = null;
	public TableColumn idColumn;
	private TableColumn cosColumn;
	private TableColumn cirColumn;
	private TableColumn cbsColumn;
	private TableColumn eirColumn;
	private TableColumn ebsColumn;
	private TableColumn pirColumn;

	public AddQosTemplateDialog() {
		initComponents();
	}

	public AddQosTemplateDialog(JPanel jPanel, boolean modal, String title, String identify) {
		this.setModal(modal);
		this.setTitle(title);
		this.identify = identify;
		initComponents();
	}

	/* 初始化面板 */
	private void initComponents() {
		mainPanel = new JPanel();
		middlePanel = new JPanel();
		tablePanel = new JPanel();
		buttonPanel = new JPanel();

		tempNameLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TEMPLATE_NAME));
		tempNameField = new JTextField();
		qosTypeLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_QOS_TYPE));
		qosTypeComboBox = new JComboBox();
		qosTypeComboBox.addItem(QosTemplateTypeEnum.LLSP.toString());
		qosTypeComboBox.addItem(QosTemplateTypeEnum.ELSP.toString());

		tableScrollPane = new JScrollPane();
		tempTable = new JTable();
		tableScrollPane.setViewportView(tempTable);
		setTableModel();
		tempTable.getTableHeader().setReorderingAllowed(false);
		tempTable.setModel(qosTableModel);
		saveButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE),false);
		cancelButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL),false);
		this.add(mainPanel);
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		addComponent(mainPanel, middlePanel, 0, 1, 0.0, 0.0, 1, 1, GridBagConstraints.BOTH, new Insets(5, 0, 5, 0), GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(mainPanel, tablePanel, 0, 2, 0.1, 0.1, 1, 1, GridBagConstraints.BOTH, new Insets(5, 0, 5, 0), GridBagConstraints.CENTER, gridBagConstraints);
		addComponent(mainPanel, buttonPanel, 0, 3, 0.0, 0.005, 1, 1, GridBagConstraints.BOTH, new Insets(0, 5, 5, 5), GridBagConstraints.SOUTH, gridBagConstraints);

		middlePanel.setLayout(new GridBagLayout());
		addComponent(middlePanel, tempNameLabel, 0, 0, 0.002, 0.0, 1, 1, GridBagConstraints.BOTH, new Insets(10, 20, 0, 0), GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(middlePanel, tempNameField, 1, 0, 0.015, 0.0, 1, 1, GridBagConstraints.BOTH, new Insets(10, 2, 0, 10), GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(middlePanel, qosTypeLabel, 2, 0, 0.002, 0.0, 1, 1, GridBagConstraints.BOTH, new Insets(10, 20, 0, 0), GridBagConstraints.NORTHWEST, gridBagConstraints);

		addComponent(middlePanel, qosTypeComboBox, 3, 0, 0.015, 0.0, 1, 1, GridBagConstraints.BOTH, new Insets(10, 2, 0, 20), GridBagConstraints.NORTHWEST, gridBagConstraints);
		tablePanel.setLayout(new GridBagLayout());
		addComponent(tablePanel, tableScrollPane, 0, 0, 0.1, 0.1, 1, 1, GridBagConstraints.BOTH, new Insets(0, 5, 5, 5), GridBagConstraints.NORTHWEST, gridBagConstraints);
	
		FlowLayout buttonFlowLayout = new FlowLayout();
		buttonFlowLayout.setAlignment(FlowLayout.RIGHT);
		buttonPanel.setLayout(buttonFlowLayout);
		saveButton.setPreferredSize(new Dimension(60, 20));
		cancelButton.setPreferredSize(new Dimension(60, 20));
		buttonPanel.add(saveButton);
		buttonPanel.add(cancelButton);
		configCosComboBox();
		configSpinner();
	}

	/*
	 * 对cos所对应的列的单元格添加comboBox
	 */
	public void configCosComboBox() {
		comboBox = new JComboBox();
		for (QosCosLevelEnum qos : QosCosLevelEnum.values()) {
			comboBox.addItem(qos);
		}
		TableColumn column = tempTable.getColumn("COS");
		column.setCellEditor(new DefaultCellEditor(comboBox));
	}

	/*
	 * 对设置带宽的列的单元格添加Spinner
	 */
	public void configSpinner() {
		setTableAtrribute(tempTable);
//		if(CodeConfigItem.getInstance().getWuhan() == 1 || CodeConfigItem.getInstance().getFiberhome() == 1){
//			//如果是武汉设备或者烽火设备,cir/pir/eir的单位是Mbps
//			cirColumn = tempTable.getColumn("CIR(Mbps)");
//			cirColumn.setCellEditor(new SpinnerNumberEditor("0", ConstantUtil.QOS_CIR_MAX_10G/1000+"", "1"));
//			
//			eirColumn = tempTable.getColumn("EIR(Mbps)");
//			eirColumn.setCellEditor(new SpinnerNumberEditor("0", ConstantUtil.QOS_CIR_MAX_10G/1000+"", "1"));
//			
//			pirColumn = tempTable.getColumn("PIR(Mbps)");
//			pirColumn.setCellEditor(new DefaultCellEditor(new JTextField()));
//		}else{
			cirColumn = tempTable.getColumn("CIR(kbps)");
			cirColumn.setCellEditor(new SpinnerNumberEditor("0", ConstantUtil.QOS_CIR_MAX_10G+"", "64"));
			
			eirColumn = tempTable.getColumn("EIR(kbps)");
			eirColumn.setCellEditor(new SpinnerNumberEditor("0", ConstantUtil.QOS_CIR_MAX_10G+"", "64"));
			
			pirColumn = tempTable.getColumn("PIR(kbps)");
			pirColumn.setCellEditor(new DefaultCellEditor(new JTextField()));
//		}

		cbsColumn = tempTable.getColumn(ResourceUtil.srcStr(StringKeysLbl.LBL_CBS));
		cbsColumn.setCellEditor(new SpinnerNumberEditor("0", ConstantUtil.CBS_MAXVALUE+"", "1"));

		

		ebsColumn = tempTable.getColumn(ResourceUtil.srcStr(StringKeysObj.EBS_BYTE));
		ebsColumn.setCellEditor(new SpinnerNumberEditor("0", ConstantUtil.CBS_MAXVALUE+"", "1"));
	}

	private void setTableAtrribute(JTable table) {
		table.setRowSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		TableColumn column = table.getColumnModel().getColumn(0);
		column.setMaxWidth(50);
		column.setResizable(false);
		column.setCellRenderer(new DefaultTableCellRenderer() {
			@Override
			public java.awt.Dimension getSize() {
				return new Dimension(50, 20);
			};

			@Override
			public int getHorizontalAlignment() {
				return SwingConstants.CENTER;
			};

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				super.getTableCellRendererComponent(table, value, isSelected, false, row, column);
				this.setForeground(Color.BLUE);
				this.setBackground(UIManager.getColor("Panel.background"));
				return this;
			};
		});
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setTableModel() {
		columnName = new Vector();
		columnName.add(ResourceUtil.srcStr(StringKeysObj.ORDER_NUM));
		columnName.add("COS");
		columnName.add(ResourceUtil.srcStr(StringKeysLbl.LBL_DIR));
//		if(CodeConfigItem.getInstance().getWuhan() == 1 || CodeConfigItem.getInstance().getFiberhome() == 1){
//			//如果是武汉设备或者烽火设备,cir/pir/eir的单位是Mbps
//			columnName.add("CIR(Mbps)");
//		}else{
			columnName.add("CIR(kbps)");
//		}
		
		columnName.add(ResourceUtil.srcStr(StringKeysLbl.LBL_CBS));
//		if(CodeConfigItem.getInstance().getWuhan() == 1 || CodeConfigItem.getInstance().getFiberhome() == 1){
//			//如果是武汉设备或者烽火设备,cir/pir/eir的单位是Mbps
//			columnName.add("EIR(Mbps)");
//		}else{
			columnName.add("EIR(kbps)");
//		}
		columnName.add(ResourceUtil.srcStr(StringKeysObj.EBS_BYTE));
//		if(CodeConfigItem.getInstance().getWuhan() == 1 || CodeConfigItem.getInstance().getFiberhome() == 1){
//			//如果是武汉设备或者烽火设备,cir/pir/eir的单位是Mbps
//			columnName.add("PIR(Mbps)");
//		}else{
			columnName.add("PIR(kbps)");
//		}
		qosTableModel = new DefaultTableModel(null, columnName.toArray()) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 0 || column == 2) {
					return false;
				}
				return true;
			}
		};
	}

	public JComboBox getQosTypeComboBox() {
		return qosTypeComboBox;
	}

	public void setQosTypeComboBox(JComboBox qosTypeComboBox) {
		this.qosTypeComboBox = qosTypeComboBox;
	}

	public JTable getTempTable() {
		return tempTable;
	}

	public void setTempTable(JTable tempTable) {
		this.tempTable = tempTable;
	}

	public PtnButton getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(PtnButton saveButton) {
		this.saveButton = saveButton;
	}

	public DefaultTableModel getQosTableModel() {
		return qosTableModel;
	}

	public void setQosTableModel(DefaultTableModel qosTableModel) {
		this.qosTableModel = qosTableModel;
	}

	public JScrollPane getTableScrollPane() {
		return tableScrollPane;
	}

	public void setTableScrollPane(JScrollPane tableScrollPane) {
		this.tableScrollPane = tableScrollPane;
	}

	public JTextField getTempNameField() {
		return tempNameField;
	}

	public void setTempNameField(JTextField tempNameField) {
		this.tempNameField = tempNameField;
	}

	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public PtnButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(PtnButton cancelButton) {
		this.cancelButton = cancelButton;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	public Vector<String> getColumnName() {
		return columnName;
	}

	public void setColumnName(Vector<String> columnName) {
		this.columnName = columnName;
	}

	public TableColumn getCosColumn() {
		return cosColumn;
	}

	public void setCosColumn(TableColumn cosColumn) {
		this.cosColumn = cosColumn;
	}

	public TableColumn getCirColumn() {
		return cirColumn;
	}

	public void setCirColumn(TableColumn cirColumn) {
		this.cirColumn = cirColumn;
	}

	public TableColumn getCbsColumn() {
		return cbsColumn;
	}

	public void setCbsColumn(TableColumn cbsColumn) {
		this.cbsColumn = cbsColumn;
	}

	public TableColumn getEirColumn() {
		return eirColumn;
	}

	public void setEirColumn(TableColumn eirColumn) {
		this.eirColumn = eirColumn;
	}

	public TableColumn getEbsColumn() {
		return ebsColumn;
	}

	public void setEbsColumn(TableColumn ebsColumn) {
		this.ebsColumn = ebsColumn;
	}

	public TableColumn getPirColumn() {
		return pirColumn;
	}

	public void setPirColumn(TableColumn pirColumn) {
		this.pirColumn = pirColumn;
	}

}
