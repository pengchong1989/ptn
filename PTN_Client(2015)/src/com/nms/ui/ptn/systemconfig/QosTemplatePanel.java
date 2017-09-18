/*
 * QosTemplatePanel.java
 *
 * Created on __DATE__, __TIME__
 */

package com.nms.ui.ptn.systemconfig;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
import com.nms.ui.ptn.systemconfig.dialog.qos.controller.QosManagerController;
/**
 *
 * @author  __USER__
 */
@SuppressWarnings("serial")
public class QosTemplatePanel extends javax.swing.JPanel {

	@SuppressWarnings("unused")
	private QosManagerController qosManagerController = null;
	
	public QosTemplatePanel() {
		initComponents();
		qosManagerController = new QosManagerController(this);
	}

	private void initComponents() {
		
		upPanel = new JPanel();
		upPanel.setBorder(BorderFactory
				.createTitledBorder(ResourceUtil.srcStr(StringKeysObj.QOS_TEMPLATE_CONFIG)));
		downPanel = new JPanel();
		downPanel.setBorder(BorderFactory
				.createTitledBorder(ResourceUtil.srcStr(StringKeysObj.TEMPLATE_DETAIL_INFO)));
		setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		addComponent(this,upPanel,0,0,0.0,0.1,1,1,
				GridBagConstraints.BOTH,new Insets(0,5,5,5),GridBagConstraints.NORTH,
				gridBagConstraints);
		addComponent(this,downPanel,0,1,0.1,0.1,1,1,
				GridBagConstraints.BOTH,new Insets(0,5,5,5),GridBagConstraints.SOUTH,
				gridBagConstraints);
		buttonPanel = new JPanel();
		addButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CREATE),false,RootFactory.DEPLOY_MANAGE);
		modifyButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_UPDATE),false,RootFactory.DEPLOY_MANAGE);
		deleteButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_DELETE),false,RootFactory.DEPLOY_MANAGE);
		refreshButton = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_REFRESH));
		FlowLayout buttonLayout = new FlowLayout();
		buttonLayout.setAlignment(FlowLayout.LEFT);
		buttonPanel.setLayout(buttonLayout);
		buttonPanel.add(addButton);
		buttonPanel.add(modifyButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(refreshButton);
		upPanel.setLayout(new GridBagLayout());
		addComponent(upPanel,buttonPanel,0,0,0.1,0.001,1,1,
				GridBagConstraints.BOTH,new Insets(0,5,5,5),GridBagConstraints.NORTHWEST,
				gridBagConstraints);
		qosResultScrollPane = new JScrollPane();
		qosResultTable = new JTable();
		qosResultScrollPane.setViewportView(qosResultTable);
		setQosResultModel();
		qosResultTable.setModel(qosResultTableModel);
		setTableAtrribute(qosResultTable);
		addComponent(upPanel,qosResultScrollPane,0,1,0.1,0.1,1,1,
				GridBagConstraints.BOTH,new Insets(0,5,5,5),GridBagConstraints.CENTER,
				gridBagConstraints);
		downPanel.setLayout(new GridBagLayout());
		detailScrollPane = new JScrollPane();
		detailTable = new JTable();
		detailScrollPane.setViewportView(detailTable);
		setDetailModel();
		detailTable.setModel(detailTableModel);
		setTableAtrribute(detailTable);
		addComponent(downPanel,detailScrollPane,0,1,0.1,0.1,1,1,
				GridBagConstraints.BOTH,new Insets(0,5,5,5),GridBagConstraints.NORTHWEST,
				gridBagConstraints);
	}
	
	private void setTableAtrribute(JTable table) {
		table.getTableHeader().setReorderingAllowed(false);
		TableColumn column = table.getColumnModel().getColumn(0);
		column.setMaxWidth(50);
		column.setResizable(false);
		column.setCellRenderer(new DefaultTableCellRenderer(){
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

	private void addComponent(JPanel panel, JComponent component,int gridx,int gridy,
			double weightx,double weighty,int gridwidth,int gridheight,int fill,
			Insets insets,int anchor,
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
		panel.add(component,gridBagConstraints);
	}
	private void setQosResultModel() {
		qosResultTableModel = new DefaultTableModel(null,
				new String[] { ResourceUtil.srcStr(StringKeysObj.ORDER_NUM), ResourceUtil.srcStr(StringKeysLbl.LBL_TEMPLATE_NAME), ResourceUtil.srcStr(StringKeysLbl.LBL_QOS_TYPE)}){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		setQosResultTableModel(qosResultTableModel);
	}
	private void setDetailModel() {
		detailTableModel = new DefaultTableModel(	null, 
				new String[] { ResourceUtil.srcStr(StringKeysObj.ORDER_NUM), "COS", ResourceUtil.srcStr(StringKeysLbl.LBL_DIR),"CIR(kbps)",ResourceUtil.srcStr(StringKeysLbl.LBL_CBS),
				"EIR(kbps)",ResourceUtil.srcStr(StringKeysObj.EBS_BYTE),"PIR(kbps)"}){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};	
		setDetailTableModel(detailTableModel);
	}
	private PtnButton addButton;
	private PtnButton deleteButton;
	private JPanel downPanel;
	private PtnButton modifyButton;
	private JButton refreshButton;
	private JPanel upPanel;
	private JScrollPane qosResultScrollPane;
	private JTable qosResultTable;
	private DefaultTableModel qosResultTableModel;
	private JPanel buttonPanel;
	private JScrollPane detailScrollPane;
	private JTable detailTable;
	private DefaultTableModel detailTableModel;
	
	public PtnButton getAddButton() {
		return addButton;
	}

	public void setAddButton(PtnButton addButton) {
		this.addButton = addButton;
	}

	public PtnButton getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(PtnButton deleteButton) {
		this.deleteButton = deleteButton;
	}

	public PtnButton getModifyButton() {
		return modifyButton;
	}

	public void setModifyButton(PtnButton modifyButton) {
		this.modifyButton = modifyButton;
	}

	public JButton getRefreshButton() {
		return refreshButton;
	}

	public void setRefreshButton(JButton refreshButton) {
		this.refreshButton = refreshButton;
	}

	public JPanel getUpPanel() {
		return upPanel;
	}

	public void setUpPanel(JPanel upPanel) {
		this.upPanel = upPanel;
	}


	public JTable getQosResultTable() {
		return qosResultTable;
	}

	public void setQosResultTable(JTable qosResultTable) {
		this.qosResultTable = qosResultTable;
	}

	public DefaultTableModel getQosResultTableModel() {
		return qosResultTableModel;
	}


	public JScrollPane getDetailScrollPane() {
		return detailScrollPane;
	}

	public void setDetailScrollPane(JScrollPane detailScrollPane) {
		this.detailScrollPane = detailScrollPane;
	}

	public JTable getDetailTable() {
		return detailTable;
	}

	public void setDetailTable(JTable detailTable) {
		this.detailTable = detailTable;
	}

	public DefaultTableModel getDetailTableModel() {
		return detailTableModel;
	}

	public void setQosResultTableModel(DefaultTableModel qosResultTableModel) {
		this.qosResultTableModel = qosResultTableModel;
	}

	public void setDetailTableModel(DefaultTableModel detailTableModel) {
		this.detailTableModel = detailTableModel;
	}

	public static void main(String []args){
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame jf = new JFrame();
				QosTemplatePanel dialog = new QosTemplatePanel();
				jf.add(dialog);
				jf.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				jf.setSize(1000, 580);
				jf.setVisible(true);
			}
		});
	}
}