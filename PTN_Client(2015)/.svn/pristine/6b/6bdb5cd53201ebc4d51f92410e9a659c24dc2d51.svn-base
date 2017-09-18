package com.nms.ui.ptn.systemconfig.dialog.qos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.nms.db.enums.QosCosLevelEnum;
import com.nms.db.enums.QosTemplateTypeEnum;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTab;


public abstract class QosCommonConfig extends PtnDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel mainPanel;
	public JPanel titlePanel;
	public JPanel namePanel;
	public JPanel nameZPanel;
	public JPanel sectionAPanel;
	public JPanel sectionZPanel;
	public JPanel tablePanel;
	public JPanel buttonPanel;
	public JLabel titleLabel;
	public JLabel nameLabel;
	public JComboBox nameList;
	public JLabel qosTypeLabel;
	public JComboBox qosTypeComboBox;
	public JScrollPane tableScrollPane;
	public JTable qosTable;
	public DefaultTableModel qosTableModel;
	public JLabel queueALabel;
	public JLabel queueZLabel;
	public JComboBox sectionAQosQueueComboBox;
	public JScrollPane sectionAScrollPane;
	protected JTable sectionAQosTable;
	protected DefaultTableModel sectionAQosTableModel;
	public JComboBox sectionZQosQueueComboBox;
	public JScrollPane sectionZScrollPane;
	protected JTable sectionZQosTable;
	protected DefaultTableModel sectionZQosTableModel;

	public JButton saveButton;
	public JButton cancelButton;
	public JComboBox comboBox;
	public DefaultComboBoxModel nameListComboBoxModel;
//	public static QosCommonConfig qosConfigDialog = null;
	protected Vector<String> columnName = null;
	public TableColumn cosColumn;
	public TableColumn cirColumn;
	public TableColumn cbsColumn;
	public TableColumn eirColumn;
	public TableColumn ebsColumn;
	public TableColumn pirColumn;
	public TableColumn weightColumn;
	public TableColumn greenLowColumn;
	public TableColumn greenHighColumn;
	public TableColumn greenProbility;
	public TableColumn yellowLowColumn;
	public TableColumn yellowHighColumn;
	public TableColumn yellowProbility;
	public TableColumn idColumn;
	public JTextField idField;

	public QosCommonConfig() {
		initComponents();
	}

	public QosCommonConfig(boolean modal, String title) {
		this.setModal(modal);
		initComponents();
	}

	protected void initComponents() {
		mainPanel = new JPanel();
		titlePanel = new JPanel();
		namePanel = new JPanel();
		nameZPanel = new JPanel();
		tablePanel = new JPanel();
		buttonPanel = new JPanel();
		sectionAPanel = new JPanel();
		sectionZPanel = new JPanel();
		titleLabel = new JLabel(ResourceUtil.srcStr(StringKeysObj.STRING_QOS_CONFIG));
		nameLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TEMPLATE_NAME));
		nameList = new JComboBox();
		nameListComboBoxModel = (DefaultComboBoxModel) nameList.getModel();
		qosTypeLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_QOS_TYPE));
		qosTypeComboBox = new JComboBox();
		qosTypeComboBox.addItem(QosTemplateTypeEnum.LLSP.toString());
		qosTypeComboBox.addItem(QosTemplateTypeEnum.ELSP.toString());

		qosTable = new JTable();
		qosTable.getTableHeader().setReorderingAllowed(false);
		tableScrollPane = new JScrollPane();
		tableScrollPane.setViewportView(qosTable);

		saveButton = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM));
		cancelButton = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));

		queueALabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_QUEUE_SCHEDULING));
		queueZLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_QUEUE_SCHEDULING));
		sectionAQosQueueComboBox = new JComboBox();
		sectionAQosQueueComboBox.addItem("SP+DWRR");
		sectionAQosQueueComboBox.addItem("SP");
		sectionZQosQueueComboBox = new JComboBox();
		sectionZQosQueueComboBox.addItem("SP+DWRR");
		sectionZQosQueueComboBox.addItem("SP");
		sectionAScrollPane = new JScrollPane();
		sectionAPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTab.TAB_A_QOS_CONFIG)));
		sectionAQosTable = new JTable();
		sectionAQosTable.getTableHeader().setReorderingAllowed(false);
		sectionAScrollPane.setViewportView(sectionAQosTable);

		sectionZScrollPane = new JScrollPane();
		sectionZPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTab.TAB_Z_QOS_CONFIG)));
		sectionZQosTable = new JTable();
		sectionZQosTable.getTableHeader().setReorderingAllowed(false);
		sectionZScrollPane.setViewportView(sectionZQosTable);

		mainPanel.setLayout(new GridBagLayout());

		titlePanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		titleLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
		addComponent(titlePanel, titleLabel, 0, 0, 0.1, 0.1, 1, 1, GridBagConstraints.BOTH, new Insets(0, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);

		FlowLayout buttonFlowLayout = new FlowLayout();
		buttonFlowLayout.setAlignment(FlowLayout.RIGHT);
		buttonPanel.setLayout(buttonFlowLayout);
		saveButton.setPreferredSize(new Dimension(60, 20));
		cancelButton.setPreferredSize(new Dimension(60, 20));
		buttonPanel.add(saveButton);
		buttonPanel.add(cancelButton);

		configCosComboBox();
	}

	/*
	 * 对cos所对应的列的单元格添加comboBox
	 */
	public void configCosComboBox() {
		comboBox = new JComboBox();
		for (QosCosLevelEnum qos : QosCosLevelEnum.values()) {
			comboBox.addItem(qos);
		}

	}

	/*
	 * 对设置带宽的列的单元格添加Spinner
	 */
	public abstract void configSpinner(JTable table);

	public abstract void setTableModel();

	public void setIdColumnAttribute(JTable table) {
		idColumn = table.getColumn(ResourceUtil.srcStr(StringKeysObj.ORDER_NUM));
		idColumn.setResizable(false);
		idColumn.setPreferredWidth(50);
		idColumn.setCellRenderer(new DefaultTableCellRenderer() {
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

	public void addComponent(JPanel panel, JComponent component, int gridx, int gridy, double weightx, double weighty, int gridwidth, int gridheight, int fill, Insets insets, int anchor, GridBagConstraints gridBagConstraints) {
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

	public JComboBox getQosTypeComboBox() {
		return qosTypeComboBox;
	}

	public void setQosTypeComboBox(JComboBox qosTypeComboBox) {
		this.qosTypeComboBox = qosTypeComboBox;
	}

	public JTable getQosTable() {
		return qosTable;
	}

	public void setQosTable(JTable qosTable) {
		this.qosTable = qosTable;
	}

	public JButton getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(JButton saveButton) {
		this.saveButton = saveButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

	public DefaultTableModel getQosTableModel() {
		return qosTableModel;
	}

	public void setQosTableModel(DefaultTableModel qosTableModel) {
		this.qosTableModel = qosTableModel;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}

	public JComboBox getNameList() {
		return nameList;
	}

	public void setNameList(JComboBox nameList) {
		this.nameList = nameList;
	}

	public DefaultComboBoxModel getNameListComboBoxModel() {
		return nameListComboBoxModel;
	}

	public void setNameListComboBoxModel(DefaultComboBoxModel nameListComboBoxModel) {
		this.nameListComboBoxModel = nameListComboBoxModel;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	@SuppressWarnings("rawtypes")
	public Vector getColumnName() {
		return columnName;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setColumnName(Vector columnName) {
		this.columnName = columnName;
	}

	public JComboBox getSectionAQosQueueComboBox() {
		return sectionAQosQueueComboBox;
	}

	public void setSectionAQosQueueComboBox(JComboBox sectionAQosQueueComboBox) {
		this.sectionAQosQueueComboBox = sectionAQosQueueComboBox;
	}

	public JComboBox getSectionZQosQueueComboBox() {
		return sectionZQosQueueComboBox;
	}

	public void setSectionZQosQueueComboBox(JComboBox sectionZQosQueueComboBox) {
		this.sectionZQosQueueComboBox = sectionZQosQueueComboBox;
	}

	public JTable getSectionAQosTable() {
		return sectionAQosTable;
	}

	public void setSectionAQosTable(JTable sectionAQosTable) {
		this.sectionAQosTable = sectionAQosTable;
	}

	public DefaultTableModel getSectionAQosTableModel() {
		return sectionAQosTableModel;
	}

	public void setSectionAQosTableModel(DefaultTableModel sectionAQosTableModel) {
		this.sectionAQosTableModel = sectionAQosTableModel;
	}

	public JTable getSectionZQosTable() {
		return sectionZQosTable;
	}

	public void setSectionZQosTable(JTable sectionZQosTable) {
		this.sectionZQosTable = sectionZQosTable;
	}

	public DefaultTableModel getSectionZQosTableModel() {
		return sectionZQosTableModel;
	}

	public void setSectionZQosTableModel(DefaultTableModel sectionZQosTableModel) {
		this.sectionZQosTableModel = sectionZQosTableModel;
	}

	public JScrollPane getTableScrollPane() {
		return tableScrollPane;
	}

	public void setTableScrollPane(JScrollPane tableScrollPane) {
		this.tableScrollPane = tableScrollPane;
	}

//	public static QosCommonConfig getQosConfigDialog() {
//		return qosConfigDialog;
//	}
//
//	public static void setQosConfigDialog(QosCommonConfig qosConfigDialog) {
//		QosCommonConfig.qosConfigDialog = qosConfigDialog;
//	}

}
