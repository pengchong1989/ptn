package com.nms.ui.ptn.ne.tunnel.view;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.ui.frame.ViewDataTable;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.ptn.ne.tunnel.controller.QosAddAfterTunnelDialogController;

public class QosAddAfterTunnelDialog extends PtnDialog {

	private static final long serialVersionUID = -6910742046105119493L;
	private QosAddAfterTunnelDialogController controller;
	private JPanel componentPanel;// 控件面板
	private JPanel buttonPanel;// 按钮面板
	private JPanel explainPanel;// 文字说明面板

	private JLabel modelName;
	private JTextField modelField;
	private JLabel qosTypeLabel;
	private JComboBox qosTypeComboBox;
	private JScrollPane scrollPane;
	private ViewDataTable<OamInfo> viewDataTable;

	private JButton returnButton;
	private JButton confirm;
	private JButton cancel;

	private JLabel operateLabel;
	private JLabel stepLabel;
	private JLabel errorLabel;
	private JLabel promptLabel;// 用于显示错误提示
	private Tunnel tunnel;

	public QosAddAfterTunnelDialog(Tunnel tunnel) {
		setModal(true);
		this.tunnel = tunnel;
		initComponent();
		setLayout();
		controller = new QosAddAfterTunnelDialogController(this);
	}

	private void initComponent() {

		componentPanel = new JPanel();
		modelName = new JLabel("模板名称");
		modelField = new JTextField(50);
		qosTypeLabel = new JLabel("qos类型");
		qosTypeComboBox = new JComboBox();
		viewDataTable = new ViewDataTable("pwBusinessQoSTable");
		viewDataTable.getTableHeader().setResizingAllowed(true);
		viewDataTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		viewDataTable.setTableHeaderPopupMenuFactory(null);
		viewDataTable.setTableBodyPopupMenuFactory(null);

		scrollPane = new JScrollPane();
		scrollPane.setViewportView(viewDataTable);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		buttonPanel = new JPanel();
		returnButton = new JButton("上一步");
		confirm = new JButton("完成");
		cancel = new JButton("取消");

		explainPanel = new JPanel();
		operateLabel = new JLabel("操作步骤");
		stepLabel = new JLabel("创建tunnel第二步");
		errorLabel = new JLabel("错误提示：");
		promptLabel = new JLabel("  ");

		explainPanel.setBorder(BorderFactory.createEtchedBorder());
		// buttonPanel.setBorder(BorderFactory.createEtchedBorder());
		// componentPanel.setBorder(BorderFactory.createCompoundBorder());

	}

	private void setLayout() {
		setComponentLayout();
		setButtonLayout();
		explainLayout();
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 100, 400 };
		layout.columnWeights = new double[] { 0.3, 0.5 };
		layout.rowHeights = new int[] { 400, 50 };
		layout.rowWeights = new double[] { 1.0, 0.0 };
		this.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 2;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(explainPanel, c);
		this.add(explainPanel);
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(componentPanel, c);
		this.add(componentPanel);
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(buttonPanel, c);
		this.add(buttonPanel);

	}

	private void setButtonLayout() {
		FlowLayout buttonLayout = new FlowLayout();
		buttonLayout.setAlignment(FlowLayout.RIGHT);
		buttonPanel.setLayout(buttonLayout);
		buttonPanel.add(returnButton);
		buttonPanel.add(confirm);
		buttonPanel.add(cancel);
	}

	private void explainLayout() {
		GridBagLayout layout = new GridBagLayout();
		layout.rowHeights = new int[] { 30, 30, 30, 30, 30, 30, 30 };
		layout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		explainPanel.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.NORTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(operateLabel, c);
		explainPanel.add(operateLabel);
		c.fill = GridBagConstraints.NORTH;
		c.gridx = 0;
		c.gridy = 1;
		layout.setConstraints(stepLabel, c);
		explainPanel.add(stepLabel);

		c.gridy = 3;
		c.fill = GridBagConstraints.SOUTH;
		layout.setConstraints(errorLabel, c);
		explainPanel.add(errorLabel);
		c.fill = GridBagConstraints.SOUTH;
		c.gridy = 4;
		layout.setConstraints(promptLabel, c);
		explainPanel.add(promptLabel);
	}

	private void setComponentLayout() {
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 60, 60, 60, 60 };
		layout.columnWeights = new double[] { 0.5, 0.5, 0.5, 0.5 };
		layout.rowHeights = new int[] { 30, 100 };
		layout.rowWeights = new double[] { 0.0, 1.0 };
		componentPanel.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(modelName, c);
		componentPanel.add(modelName);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(modelField, c);
		componentPanel.add(modelField);
		c.fill = GridBagConstraints.EAST;
		c.gridx = 2;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(qosTypeLabel, c);
		componentPanel.add(qosTypeLabel);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 3;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(qosTypeComboBox, c);
		componentPanel.add(qosTypeComboBox);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 4;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(scrollPane, c);
		componentPanel.add(scrollPane);
	}

	public QosAddAfterTunnelDialogController getController() {
		return controller;
	}

	public void setController(QosAddAfterTunnelDialogController controller) {
		this.controller = controller;
	}

	public JButton getReturnButton() {
		return returnButton;
	}

	public void setReturnButton(JButton returnButton) {
		this.returnButton = returnButton;
	}

	public JButton getConfirm() {
		return confirm;
	}

	public void setConfirm(JButton confirm) {
		this.confirm = confirm;
	}

	public JButton getCancel() {
		return cancel;
	}

	public void setCancel(JButton cancel) {
		this.cancel = cancel;
	}

	public Tunnel getTunnel() {
		return tunnel;
	}

	public void setTunnel(Tunnel tunnel) {
		this.tunnel = tunnel;
	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				QosAddAfterTunnelDialog dialog = new QosAddAfterTunnelDialog(null);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}
}