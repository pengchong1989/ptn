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
import com.nms.ui.manager.control.PtnSpinner;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysPanel;

public class AddAcDialogStep1 extends PtnDialog {
	private JPanel leftPanel;
	private JPanel rightPanel;

	private JPanel topPanel;
	private JPanel buttomPanel;

	private JLabel operJLabel; // 操作步骤
	private JSeparator js1;
	private JLabel createStep1JLabel; // 创建第一步

	private JLabel nameJLabel;
	private JLabel portJLabel;
	private JLabel modeJLabel;
	private JLabel MACLearnJLabel;
	private JLabel splitCutJLabel;
	private JLabel VCEnableJLabel;

	private JTextField nameJTF;
	private JComboBox portJCB;
	private JComboBox modeJCB;
	private JComboBox MACLearnJCB;
	private JComboBox splitCutJCB;
	private JComboBox VCEnableJCB;

	private JButton nextBtnStep;
	private JButton cancelBtn;
	private JButton autoNamingBtn;
	private JLabel lblNumber;//批量创建数量
	private PtnSpinner ptnSpinnerNumber;
	private JButton batch;//批量设置
	public AddAcDialogStep1() {
		this.setModal(true);
		initComponents();
		setLayout();
	}

	private void setLayout() {
		GridBagConstraints gbc = new GridBagConstraints();
//		leftPanel.setLayout(new GridBagLayout());
//		leftPanel.setBorder(BorderFactory.createTitledBorder(""));
		// leftPanel.setPreferredSize(new Dimension(arg0, arg1))
//		addComponent(leftPanel, operJLabel, 0, 0, 0.1, 0, 1, 1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
//		addComponent(leftPanel, js1, 0, 1, 0.1, 0, 1, 1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
//		addComponent(leftPanel, createStep1JLabel, 0, 2, 0.1, 0.6, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.NORTH, gbc);

		this.rightPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_TIP_FIRST)));
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 150, 250, 150 };
		layout.columnWeights = new double[] { 0, 0, 0 };
		layout.rowHeights = new int[] { 40, 40, 40, 40, 40, 40, 80 };
		layout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.6 };
		rightPanel.setLayout(layout);

		addComponent(rightPanel, nameJLabel, 0, 0, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(10, 15, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, portJLabel, 0, 1, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 15, 5, 5), GridBagConstraints.NORTHWEST, gbc);
//		addComponent(rightPanel, modeJLabel, 0, 2, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 15, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, MACLearnJLabel, 0, 2, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 15, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, splitCutJLabel, 0, 3, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 15, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, VCEnableJLabel, 0, 4, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 15, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, lblNumber, 0, 5, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 15, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, batch, 0, 6, 1, 1, GridBagConstraints.NONE, new Insets(5, 15, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		
		addComponent(rightPanel, nameJTF, 1, 0, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(10, 5, 5, 90), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, this.autoNamingBtn, 1, 0, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(10, 200, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, portJCB, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
//		addComponent(rightPanel, modeJCB, 1, 2, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, MACLearnJCB, 1, 2, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, splitCutJCB, 1, 3, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, VCEnableJCB, 1, 4, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, ptnSpinnerNumber, 1, 5, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);

		topPanel.setLayout(new BorderLayout());
		/*
		 * addComponent(topPanel, leftPanel, 0, 0, 0.05, 0.8, 1, 1, GridBagConstraints.BOTH, new Insets(5, 0, 0, 0), GridBagConstraints.NORTHWEST ,gbc); addComponent(topPanel, rightPanel, 1, 0, 0.3, 0.8, 1, 1, GridBagConstraints.BOTH, new Insets(5, 0, 0, 0), GridBagConstraints.NORTHWEST ,gbc);
		 */
//		leftPanel.setPreferredSize(new Dimension(150, 300));
//		topPanel.add(leftPanel, BorderLayout.WEST);
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

		nameJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_NAME));
		portJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_LOAD_PORT));
		modeJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT_MODAL));
		MACLearnJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_MAC_LEARN));
		splitCutJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_HORIZONAL));
		VCEnableJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_VCFLOW));

		nameJTF = new JTextField();
		portJCB = new JComboBox();
		modeJCB = new JComboBox();
		modeJCB.setEnabled(false);
		MACLearnJCB = new JComboBox();
		splitCutJCB = new JComboBox();
		VCEnableJCB = new JComboBox();
		lblNumber = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_CREATE_NUM));
		ptnSpinnerNumber = new PtnSpinner(1, 1, 100, 1);
		
		nextBtnStep = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_NEXT));
		cancelBtn = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		this.autoNamingBtn = new JButton(ResourceUtil.srcStr(StringKeysLbl.LBL_AUTO_NAME));
		batch = new JButton(ResourceUtil.srcStr(StringKeysLbl.LBL_BATCH_ATTR));
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

	public JLabel getNameJLabel() {
		return nameJLabel;
	}

	public JLabel getPortJLabel() {
		return portJLabel;
	}

	public JLabel getModeJLabel() {
		return modeJLabel;
	}

	public JLabel getMACLearnJLabel() {
		return MACLearnJLabel;
	}

	public JLabel getSplitCutJLabel() {
		return splitCutJLabel;
	}

	public JLabel getVCEnableJLabel() {
		return VCEnableJLabel;
	}

	public JTextField getNameJTF() {
		return nameJTF;
	}

	public JComboBox getPortJCB() {
		return portJCB;
	}

	public JComboBox getModeJCB() {
		return modeJCB;
	}

	public JComboBox getMACLearnJCB() {
		return MACLearnJCB;
	}

	public JComboBox getSplitCutJCB() {
		return splitCutJCB;
	}

	public JComboBox getVCEnableJCB() {
		return VCEnableJCB;
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

	public PtnSpinner getPtnSpinnerNumber() {
		return ptnSpinnerNumber;
	}

	public void setPtnSpinnerNumber(PtnSpinner ptnSpinnerNumber) {
		this.ptnSpinnerNumber = ptnSpinnerNumber;
	}

	public JButton getBatch() {
		return batch;
	}

	public void setBatch(JButton batch) {
		this.batch = batch;
	}

}
