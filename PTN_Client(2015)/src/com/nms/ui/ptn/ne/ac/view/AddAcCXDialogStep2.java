package com.nms.ui.ptn.ne.ac.view;

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
public class AddAcCXDialogStep2 extends PtnDialog {
	private JPanel leftPanel;
	private JPanel rightPanel;

	private JPanel topPanel;
	private JPanel buttomPanel;

	private JLabel operJLabel; // 操作步骤
	private JSeparator js1;
	private JLabel createStep2JLabel; // 创建第二步


	private JLabel lblExitRule;//出口规则
	private JLabel lblVlanId;//vlanid
	private JLabel lblVlanCri;//vlancri
	private JLabel lblVlanPri;//vlanpri
	
	private JComboBox cmbExitRule;
	private JTextField txtVlanId;
	private JTextField txtVlanCri;
	private JTextField txtVlanPri;
	


	private JButton nextBtn;
	private JButton previousBtn;
	private JButton cancelBtn;

	public AddAcCXDialogStep2() {
		this.setModal(true);
		initComponents();
		setLayout();
	}

	private void setLayout() {
		GridBagConstraints gbc = new GridBagConstraints();
		this.rightPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_TIP_SECOND)));
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 150, 250, 150 };
		layout.columnWeights = new double[] { 0, 0, 0 };
		layout.rowHeights = new int[] { 40, 40, 40, 40, 40, 40, 80 };
		layout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.6 };
		rightPanel.setLayout(layout);
		addComponent(rightPanel, lblExitRule, 0, 0, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(10, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, lblVlanId, 0, 1, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, lblVlanCri, 0, 2, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, lblVlanPri, 0, 3, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);

		addComponent(rightPanel, cmbExitRule, 1, 0, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(10, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, txtVlanId, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, txtVlanCri, 1, 2, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, txtVlanPri, 1, 3, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);

		topPanel.setLayout(new BorderLayout());
		topPanel.add(rightPanel, BorderLayout.CENTER);

		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.RIGHT);
		buttomPanel.setLayout(fl);
		buttomPanel.add(previousBtn);
		buttomPanel.add(nextBtn);
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
		createStep2JLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_CREATE_AC_SECOND)); // 创建第一步
		
		this.lblExitRule=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_EXIT_RULE));
		this.lblVlanId=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_VLAN_ID));
		this.lblVlanCri=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_VLAN_CRI));
		this.lblVlanPri=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_VLAN_PRI));
		
		this.cmbExitRule=new JComboBox();
		this.txtVlanId=new JTextField();
		this.txtVlanCri=new JTextField();
		this.txtVlanPri=new JTextField();
		this.txtVlanId.setText("1");
		this.txtVlanCri.setText("0");
		this.txtVlanPri.setText("0");
		
		nextBtn = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_NEXT));
		previousBtn = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_BACK));
		cancelBtn = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
	}

	public JButton getNextBtn() {
		return nextBtn;
	}

	public JButton getPreviousBtn() {
		return previousBtn;
	}

	public JButton getCancelBtn() {
		return cancelBtn;
	}

	public JPanel getLeftPanel() {
		return leftPanel;
	}

	public void setLeftPanel(JPanel leftPanel) {
		this.leftPanel = leftPanel;
	}

	public JPanel getRightPanel() {
		return rightPanel;
	}

	public void setRightPanel(JPanel rightPanel) {
		this.rightPanel = rightPanel;
	}

	public JPanel getTopPanel() {
		return topPanel;
	}

	public void setTopPanel(JPanel topPanel) {
		this.topPanel = topPanel;
	}

	public JPanel getButtomPanel() {
		return buttomPanel;
	}

	public void setButtomPanel(JPanel buttomPanel) {
		this.buttomPanel = buttomPanel;
	}

	public JLabel getOperJLabel() {
		return operJLabel;
	}

	public void setOperJLabel(JLabel operJLabel) {
		this.operJLabel = operJLabel;
	}

	public JSeparator getJs1() {
		return js1;
	}

	public void setJs1(JSeparator js1) {
		this.js1 = js1;
	}

	public JLabel getCreateStep2JLabel() {
		return createStep2JLabel;
	}

	public void setCreateStep2JLabel(JLabel createStep2JLabel) {
		this.createStep2JLabel = createStep2JLabel;
	}

	public JLabel getLblExitRule() {
		return lblExitRule;
	}

	public void setLblExitRule(JLabel lblExitRule) {
		this.lblExitRule = lblExitRule;
	}

	public JLabel getLblVlanId() {
		return lblVlanId;
	}

	public void setLblVlanId(JLabel lblVlanId) {
		this.lblVlanId = lblVlanId;
	}

	public JLabel getLblVlanCri() {
		return lblVlanCri;
	}

	public void setLblVlanCri(JLabel lblVlanCri) {
		this.lblVlanCri = lblVlanCri;
	}

	public JLabel getLblVlanPri() {
		return lblVlanPri;
	}

	public void setLblVlanPri(JLabel lblVlanPri) {
		this.lblVlanPri = lblVlanPri;
	}

	public JComboBox getCmbExitRule() {
		return cmbExitRule;
	}

	public void setCmbExitRule(JComboBox cmbExitRule) {
		this.cmbExitRule = cmbExitRule;
	}

	public JTextField getTxtVlanId() {
		return txtVlanId;
	}

	public void setTxtVlanId(JTextField txtVlanId) {
		this.txtVlanId = txtVlanId;
	}

	public JTextField getTxtVlanCri() {
		return txtVlanCri;
	}

	public void setTxtVlanCri(JTextField txtVlanCri) {
		this.txtVlanCri = txtVlanCri;
	}

	public JTextField getTxtVlanPri() {
		return txtVlanPri;
	}

	public void setTxtVlanPri(JTextField txtVlanPri) {
		this.txtVlanPri = txtVlanPri;
	}

	public void setNextBtn(JButton nextBtn) {
		this.nextBtn = nextBtn;
	}

	public void setPreviousBtn(JButton previousBtn) {
		this.previousBtn = previousBtn;
	}

	public void setCancelBtn(JButton cancelBtn) {
		this.cancelBtn = cancelBtn;
	}

}
