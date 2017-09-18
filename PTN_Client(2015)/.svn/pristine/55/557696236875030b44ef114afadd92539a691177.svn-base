package com.nms.ui.ptn.systemconfig.dialog.qos.dialog;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
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
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTab;

public abstract class PortQosQueueCommonConfig extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1109367574496805778L;
	public JPanel titlePanel;
	public JPanel namePanel;
	public JPanel portQosQueuePanel;
	public JPanel tablePanel;
	public JPanel buttonPanel;
	public JLabel titleLabel;
	public JLabel nameLabel;
	public JComboBox nameList;
	public JScrollPane tableScrollPane;
	public JTable qosTable;
	public DefaultTableModel qosTableModel;
	public JLabel queueLabel;
	public JComboBox portQosQueueComboBox;
	public JScrollPane portQosQueueScrollPane;
	protected JTable portQosTable;
	protected DefaultTableModel portQosTableModel;

	public PtnButton saveButton;

	public JComboBox comboBox;
	public DefaultComboBoxModel nameListComboBoxModel;
	//public static PortQosQueueCommonConfig qosConfigDialog = null;
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
	public TableColumn disCard;
	public PortQosQueueCommonConfig() {

	}

	protected void initComponents() {
//		mainPanel = new JPanel();
		titlePanel = new JPanel();
		namePanel = new JPanel();
		tablePanel = new JPanel();
		buttonPanel = new JPanel();
		portQosQueuePanel = new JPanel();

		nameLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TEMPLATE_NAME));
		nameList = new JComboBox();
		nameListComboBoxModel = (DefaultComboBoxModel) nameList.getModel();

		qosTable = new JTable();
		qosTable.getTableHeader().setReorderingAllowed(false);
		tableScrollPane = new JScrollPane();
		tableScrollPane.setViewportView(qosTable);
	
		saveButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE));
		
		queueLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_QUEUE_SCHEDULING));
		portQosQueueComboBox = new JComboBox();
		portQosQueueComboBox.addItem("SP");
		portQosQueueComboBox.addItem("SP+DWRR");
		
		portQosQueueScrollPane = new JScrollPane();
		portQosQueuePanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTab.TAB_QOS_CONFIG)));
		portQosTable = new JTable();
		portQosTable.getTableHeader().setReorderingAllowed(false);
		
		FlowLayout buttonFlowLayout = new FlowLayout();
		buttonFlowLayout.setAlignment(FlowLayout.RIGHT);
		buttonPanel.setLayout(buttonFlowLayout);
		saveButton.setPreferredSize(new Dimension(60, 20));

		buttonPanel.add(saveButton);
	
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

	public abstract void setTableModel() ;

	@SuppressWarnings("serial")
	public void setIdColumnAttribute(JTable table){
		idColumn = table.getColumn(ResourceUtil.srcStr(StringKeysObj.ORDER_NUM));
		idColumn.setResizable(false);
		idColumn.setPreferredWidth(50);
		idColumn.setCellRenderer(new DefaultTableCellRenderer(){
			@Override
			public java.awt.Dimension getSize() {
				return new Dimension(50,20);
			};
			@Override
			public int getHorizontalAlignment() {
				return SwingConstants.CENTER;
			};
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value,
					boolean isSelected, boolean hasFocus, int row, int column) {
				super.getTableCellRendererComponent(table, value, isSelected, false, row, column);
				this.setForeground(Color.BLUE);
				this.setBackground(UIManager.getColor("Panel.background"));
				return this;
			};
		} );
	}
	public void addComponent(JPanel panel, JComponent component, int gridx,
			int gridy, double weightx, double weighty, int gridwidth,
			int gridheight, int fill, Insets insets, int anchor,
			GridBagConstraints gridBagConstraints) {
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

	public JTable getQosTable() {
		return qosTable;
	}

	public void setQosTable(JTable qosTable) {
		this.qosTable = qosTable;
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

	public void setNameListComboBoxModel(
			DefaultComboBoxModel nameListComboBoxModel) {
		this.nameListComboBoxModel = nameListComboBoxModel;
	}


	@SuppressWarnings("rawtypes")
	public Vector getColumnName() {
		return columnName;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setColumnName(Vector columnName) {
		this.columnName = columnName;
	}


	public JComboBox getPortQosQueueComboBox() {
		return portQosQueueComboBox;
	}

	public void setPortQosQueueComboBox(JComboBox portQosQueueComboBox) {
		this.portQosQueueComboBox = portQosQueueComboBox;
	}


	public JTable getPortQosTable() {
		return portQosTable;
	}

	public void setPortQosTable(JTable portQosTable) {
		this.portQosTable = portQosTable;
	}

	public DefaultTableModel getPortQosTableModel() {
		return portQosTableModel;
	}

	public void setPortQosTableModel(DefaultTableModel portQosTableModel) {
		this.portQosTableModel = portQosTableModel;
	}

//	public static PortQosQueueCommonConfig getQosConfigDialog() {
//		return qosConfigDialog;
//	}
//
//	public static void setQosConfigDialog(PortQosQueueCommonConfig qosConfigDialog) {
//		PortQosQueueCommonConfig.qosConfigDialog = qosConfigDialog;
//	}
}
