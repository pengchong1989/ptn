package com.nms.ui.ptn.ne.ac.view;
/**
 * 
 * @author minhezhao
 * 类描述:Ac创建步骤
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysPanel;

public class AddAcCXDialogStep1 extends PtnDialog {
	private JPanel leftPanel;
	private JPanel rightPanel;

	private JPanel topPanel;
	private JPanel buttomPanel;

	private JLabel operJLabel; // 操作步骤
	private JSeparator js1;
	private JLabel createStep1JLabel; // 创建第一步

	private JLabel lblName;//名称
	private JLabel lblPort;//承载接口
	private JLabel lblMode;//端口模式
	private JLabel lblOperatorVlanId;//运营商VLANID
	private JLabel lblClientVlanId;//客户运营商ID
	private JLabel lblManagerEnable;//管理状态，关联code表主键 = TMC流量监管使能

	private JTextField txtName;
	private JComboBox cmbPort;
	private JComboBox cmbMode;
	private JTextField txtOperatorVlanId; 
	private JTextField txtClientVlanId;
	private JComboBox cmbManagerEnable;
	
	private JButton nextBtnStep;
	private JButton cancelBtn;
	private JButton autoNamingBtn;
	public AddAcCXDialogStep1() {
		this.setModal(true);
		initComponents();
		setLayout();
	}

	private void setLayout() {
		GridBagConstraints gbc = new GridBagConstraints();
		this.rightPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_TIP_FIRST)));
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 150, 250, 150 };
		layout.columnWeights = new double[] { 0, 0, 0 };
		layout.rowHeights = new int[] { 40, 40, 40, 40, 40, 40, 80 };
		layout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.6 };
		rightPanel.setLayout(layout);
		
		addComponent(rightPanel, lblName, 0, 0, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(10, 15, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, lblPort, 0, 1, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 15, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, lblMode, 0, 2, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 15, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, lblOperatorVlanId, 0, 3, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 15, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, lblClientVlanId, 0, 4, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 15, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, lblManagerEnable, 0, 5, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 15, 5, 5), GridBagConstraints.NORTHWEST, gbc);

		addComponent(rightPanel, txtName, 1, 0, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(10, 5, 5, 90), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, this.autoNamingBtn, 1, 0, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(10, 200, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, cmbPort, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, cmbMode, 1, 2, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, txtOperatorVlanId, 1, 3, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, txtClientVlanId, 1, 4, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, cmbManagerEnable, 1, 5, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		

		topPanel.setLayout(new BorderLayout());
		topPanel.add(rightPanel, BorderLayout.CENTER);

		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.RIGHT);
		buttomPanel.setLayout(fl);
		buttomPanel.add(nextBtnStep);
		buttomPanel.add(cancelBtn);

		JPanel jpanel = new JPanel(new BorderLayout());
		buttomPanel.setPreferredSize(new Dimension(600, 40));

		jpanel.add(topPanel, BorderLayout.CENTER);
		jpanel.add(buttomPanel, BorderLayout.SOUTH);

		this.add(jpanel);

	}

	private void addComponent(JPanel panel, JComponent component, int gridx, int gridy, int gridwidth, int gridheight, int fill, Insets insets, int anchor, GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.fill = fill;
		gridBagConstraints.insets = insets;
		gridBagConstraints.anchor = anchor;
		panel.add(component, gridBagConstraints);
	}

	private void initComponents() {
		leftPanel = new JPanel();
		rightPanel = new JPanel();

		topPanel = new JPanel();
		buttomPanel = new JPanel();

		operJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OPERATING_STEPS)); // 操作步骤
		js1 = new JSeparator();
		createStep1JLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_CREATE_AC_FIRST));// 创建第一步
		
		lblName=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_NAME));
		lblPort=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT_ID));
		lblMode=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT_MODAL));
		lblOperatorVlanId=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OPER_VLAN));
		lblClientVlanId=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_CLIENT_VLAN));
		lblManagerEnable=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_MANAGE_STATUS));
		
		txtName=new JTextField();
		cmbPort=new JComboBox();
		cmbMode=new JComboBox();
		txtOperatorVlanId=new JTextField();
		txtClientVlanId=new JTextField();
		cmbManagerEnable=new JComboBox();

		nextBtnStep = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_NEXT));
		cancelBtn = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		this.txtOperatorVlanId.setText("1");
		this.txtClientVlanId.setText("1");
		this.autoNamingBtn = new JButton(ResourceUtil.srcStr(StringKeysLbl.LBL_AUTO_NAME));
	}

	public JLabel getLblName() {
		return lblName;
	}

	public void setLblName(JLabel lblName) {
		this.lblName = lblName;
	}

	public JLabel getLblPort() {
		return lblPort;
	}

	public void setLblPort(JLabel lblPort) {
		this.lblPort = lblPort;
	}

	public JLabel getLblMode() {
		return lblMode;
	}

	public void setLblMode(JLabel lblMode) {
		this.lblMode = lblMode;
	}

	public JLabel getLblOperatorVlanId() {
		return lblOperatorVlanId;
	}

	public void setLblOperatorVlanId(JLabel lblOperatorVlanId) {
		this.lblOperatorVlanId = lblOperatorVlanId;
	}

	public JLabel getLblClientVlanId() {
		return lblClientVlanId;
	}

	public void setLblClientVlanId(JLabel lblClientVlanId) {
		this.lblClientVlanId = lblClientVlanId;
	}

	public JLabel getLblManagerEnable() {
		return lblManagerEnable;
	}

	public void setLblManagerEnable(JLabel lblManagerEnable) {
		this.lblManagerEnable = lblManagerEnable;
	}

	public JTextField getTxtName() {
		return txtName;
	}

	public void setTxtName(JTextField txtName) {
		this.txtName = txtName;
	}

	public JComboBox getCmbPort() {
		return cmbPort;
	}

	public void setCmbPort(JComboBox cmbPort) {
		this.cmbPort = cmbPort;
	}

	public JComboBox getCmbMode() {
		return cmbMode;
	}

	public void setCmbMode(JComboBox cmbMode) {
		this.cmbMode = cmbMode;
	}

	public JTextField getTxtOperatorVlanId() {
		return txtOperatorVlanId;
	}

	public void setTxtOperatorVlanId(JTextField txtOperatorVlanId) {
		this.txtOperatorVlanId = txtOperatorVlanId;
	}

	public JTextField getTxtClientVlanId() {
		return txtClientVlanId;
	}

	public void setTxtClientVlanId(JTextField txtClientVlanId) {
		this.txtClientVlanId = txtClientVlanId;
	}

	public JComboBox getCmbManagerEnable() {
		return cmbManagerEnable;
	}

	public void setCmbManagerEnable(JComboBox cmbManagerEnable) {
		this.cmbManagerEnable = cmbManagerEnable;
	}

	public void setLeftPanel(JPanel leftPanel) {
		this.leftPanel = leftPanel;
	}

	public void setRightPanel(JPanel rightPanel) {
		this.rightPanel = rightPanel;
	}

	public void setTopPanel(JPanel topPanel) {
		this.topPanel = topPanel;
	}

	public void setButtomPanel(JPanel buttomPanel) {
		this.buttomPanel = buttomPanel;
	}

	public void setOperJLabel(JLabel operJLabel) {
		this.operJLabel = operJLabel;
	}

	public void setJs1(JSeparator js1) {
		this.js1 = js1;
	}

	public void setCreateStep1JLabel(JLabel createStep1JLabel) {
		this.createStep1JLabel = createStep1JLabel;
	}

	public void setNextBtnStep(JButton nextBtnStep) {
		this.nextBtnStep = nextBtnStep;
	}

	public void setCancelBtn(JButton cancelBtn) {
		this.cancelBtn = cancelBtn;
	}

	public JPanel getLeftPanel() {
		return leftPanel;
	}

	public JPanel getRightPanel() {
		return rightPanel;
	}

	public JPanel getTopPanel() {
		return topPanel;
	}

	public JPanel getButtomPanel() {
		return buttomPanel;
	}

	public JLabel getOperJLabel() {
		return operJLabel;
	}

	public JSeparator getJs1() {
		return js1;
	}

	public JLabel getCreateStep1JLabel() {
		return createStep1JLabel;
	}

	public JButton getNextBtnStep() {
		return nextBtnStep;
	}

	public JButton getCancelBtn() {
		return cancelBtn;
	}

	public JButton getAutoNamingBtn() {
		return autoNamingBtn;
	}

}