package com.nms.ui.ptn.systemconfig.dialog.qos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import twaver.table.editor.SpinnerNumberEditor;

import com.nms.db.enums.QosCosLevelEnum;
import com.nms.db.enums.QosTemplateTypeEnum;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;

@SuppressWarnings("serial")
public class AddQosTemplateDialog extends PtnDialog {

	private JPanel mainPanel;
	private JPanel titlePanel;
	private JPanel middlePanel;
	private JPanel tablePanel;
	private JLabel titleLabel;
	private JLabel vertifyLabel;
	private JLabel tempNameLabel;
	private JTextField tempNameField;
	private JLabel qosTypeLabel;
	private JComboBox qosTypeComboBox;
	private JScrollPane tableScrollPane;
	private DefaultTableModel qosTableModel;
	private JTable tempTable;
	private JButton saveButton;
	private JButton modifyButton;
	private JPanel buttonPanel;
	private JComboBox comboBox;
	private String titleNameLabel;
	private String identify;
//	public static AddQosTemplateDialog addQosTemplateDialog = null;
	private Vector<String> columnName = null;
	public TableColumn idColumn;
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
		//addQosTemplateDialog = this;
		this.titleNameLabel = title;
		this.identify = identify;
		initComponents();
	}

	/* 初始化面板 */
	private void initComponents() {
		mainPanel = new JPanel();
		titlePanel = new JPanel();
		middlePanel = new JPanel();
		tablePanel = new JPanel();
		buttonPanel = new JPanel();

		titleLabel = new JLabel(this.titleNameLabel);
		vertifyLabel = new JLabel("   ");
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
		saveButton = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE));
		modifyButton = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_UPDATE));
		this.add(mainPanel);
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		addComponent(mainPanel, titlePanel, 0, 0, 0.0, 0.02, 1, 1, GridBagConstraints.BOTH, new Insets(0, 5, 5, 5), GridBagConstraints.NORTH, gridBagConstraints);
		titlePanel.setBorder(BorderFactory.createEtchedBorder());
		addComponent(mainPanel, middlePanel, 0, 1, 0.0, 0.0, 1, 1, GridBagConstraints.BOTH, new Insets(5, 0, 5, 0), GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(mainPanel, tablePanel, 0, 2, 0.1, 0.1, 1, 1, GridBagConstraints.BOTH, new Insets(5, 0, 5, 0), GridBagConstraints.CENTER, gridBagConstraints);
		addComponent(mainPanel, buttonPanel, 0, 3, 0.0, 0.005, 1, 1, GridBagConstraints.BOTH, new Insets(0, 5, 5, 5), GridBagConstraints.SOUTH, gridBagConstraints);

		titlePanel.setLayout(new GridBagLayout());
		titleLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
		addComponent(titlePanel, titleLabel, 0, 0, 0.1, 0.1, 1, 1, GridBagConstraints.BOTH, new Insets(0, 5, 5, 5), GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(titlePanel, vertifyLabel, 0, 1, 0.1, 0.1, 1, 1, GridBagConstraints.BOTH, new Insets(0, 5, 5, 5), GridBagConstraints.NORTHWEST, gridBagConstraints);
		middlePanel.setLayout(new GridBagLayout());
		addComponent(middlePanel, tempNameLabel, 0, 0, 0.002, 0.0, 1, 1, GridBagConstraints.BOTH, new Insets(10, 20, 0, 0), GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(middlePanel, tempNameField, 1, 0, 0.015, 0.0, 1, 1, GridBagConstraints.BOTH, new Insets(10, 2, 0, 10), GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(middlePanel, qosTypeLabel, 2, 0, 0.002, 0.0, 1, 1, GridBagConstraints.BOTH, new Insets(10, 20, 0, 0), GridBagConstraints.NORTHWEST, gridBagConstraints);

		addComponent(middlePanel, qosTypeComboBox, 3, 0, 0.015, 0.0, 1, 1, GridBagConstraints.BOTH, new Insets(10, 2, 0, 20), GridBagConstraints.NORTHWEST, gridBagConstraints);
		tablePanel.setLayout(new GridBagLayout());
		addComponent(tablePanel, tableScrollPane, 0, 0, 0.1, 0.1, 1, 1, GridBagConstraints.BOTH, new Insets(0, 5, 5, 5), GridBagConstraints.NORTHWEST, gridBagConstraints);
		buttonPanel.setLayout(new BorderLayout());
		saveButton.setPreferredSize(new Dimension(80, 25));
		modifyButton.setPreferredSize(new Dimension(80, 25));
		if (identify.equals("add")) {
			buttonPanel.add(saveButton, BorderLayout.EAST);
		} else {
			buttonPanel.add(modifyButton, BorderLayout.EAST);
		}

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
		idColumn = tempTable.getColumn(ResourceUtil.srcStr(StringKeysObj.ORDER_NUM));
		idColumn.setResizable(false);

		cirColumn = tempTable.getColumn("CIR(kbps)");
		cirColumn.setCellEditor(new SpinnerNumberEditor("0", ConstantUtil.QOS_CIR_MAX_10G+"", "64"));

		cbsColumn = tempTable.getColumn(ResourceUtil.srcStr(StringKeysLbl.LBL_CBS));
		cbsColumn.setCellEditor(new SpinnerNumberEditor("0", ConstantUtil.CBS_MAXVALUE+"", "1"));

		eirColumn = tempTable.getColumn("EIR(kbps)");
		eirColumn.setCellEditor(new SpinnerNumberEditor("0", ConstantUtil.QOS_CIR_MAX_10G+"", "64"));

		ebsColumn = tempTable.getColumn(ResourceUtil.srcStr(StringKeysObj.EBS_BYTE));
		ebsColumn.setCellEditor(new SpinnerNumberEditor("0", ConstantUtil.CBS_MAXVALUE+"", "1"));

		pirColumn = tempTable.getColumn("PIR(kbps)");
		pirColumn.setCellEditor(new DefaultCellEditor(new JTextField()));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setTableModel() {
		columnName = new Vector();
		columnName.add(ResourceUtil.srcStr(StringKeysObj.ORDER_NUM));
		columnName.add("COS");
		columnName.add(ResourceUtil.srcStr(StringKeysLbl.LBL_DIR));
		columnName.add("CIR(kbps)");
		columnName.add(ResourceUtil.srcStr(StringKeysLbl.LBL_CBS));
		columnName.add("EIR(kbps)");
		columnName.add(ResourceUtil.srcStr(StringKeysObj.EBS_BYTE));
		columnName.add("PIR(kbps)");
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

	public JButton getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(JButton saveButton) {
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

	public JButton getModifyButton() {
		return modifyButton;
	}

	public void setModifyButton(JButton modifyButton) {
		this.modifyButton = modifyButton;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	public JLabel getVertifyLabel() {
		return vertifyLabel;
	}

	public void setVertifyLabel(JLabel vertifyLabel) {
		this.vertifyLabel = vertifyLabel;
	}

//	public static AddQosTemplateDialog getAddQosTemplateDialog() {
//		return addQosTemplateDialog;
//	}
//
//	public static void setAddQosTemplateDialog(AddQosTemplateDialog addQosTemplateDialog) {
//		AddQosTemplateDialog.addQosTemplateDialog = addQosTemplateDialog;
//	}

	public Vector<String> getColumnName() {
		return columnName;
	}

	public void setColumnName(Vector<String> columnName) {
		this.columnName = columnName;
	}

}
