/*
 * NeConfigPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package com.nms.ui.ptn.systemconfig;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JPanel;

import com.nms.ui.ptn.systemconfig.dialog.fieldConfig.controller.FieldConfigController;
import com.nms.ui.ptn.systemconfig.dialog.fieldConfig.view.FieldConfigLeftPane;
import com.nms.ui.ptn.systemconfig.dialog.fieldConfig.view.FieldConfigRightPanel;

/**
 * 域配置界面
 * 
 * @author zr
 */
public class NeConfigView extends JPanel {

	private static final long serialVersionUID = 1L;

	private FieldConfigLeftPane leftPane;
	private FieldConfigRightPanel rightPanel;
	private FieldConfigController controller;

	public NeConfigView() {
		init();
		controller = new FieldConfigController(this);
		getLeftPane().setController(controller);
	}

	private void init() {
		this.initComponents();
	}

	private void initComponents() {
		leftPane = new FieldConfigLeftPane();
		rightPanel = new FieldConfigRightPanel(this);

		setViewLayout();
	}

	private void setViewLayout() {
		this.setLayout(new GridBagLayout());

		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		addComponent(this, leftPane, 0, 0, 0.2, 1.0, 1, 1, GridBagConstraints.BOTH, new Insets(5, 0, 0, 0), GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(this, rightPanel, 1, 0, 1.0, 1.0, 1, 1, GridBagConstraints.BOTH, new Insets(5, 0, 0, 0), GridBagConstraints.NORTHWEST, gridBagConstraints);
	}

	private void addComponent(JPanel panel, JComponent component, int gridx, int gridy, double weightx, double weighty, int gridwidth, int gridheight, int fill, Insets insets, int anchor, GridBagConstraints gridBagConstraints) {
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

	public FieldConfigLeftPane getLeftPane() {
		return leftPane;
	}

	public void setLeftPane(FieldConfigLeftPane leftPane) {
		this.leftPane = leftPane;
	}

	public FieldConfigRightPanel getRightPanel() {
		return rightPanel;
	}

	public void setRightPanel(FieldConfigRightPanel rightPanel) {
		this.rightPanel = rightPanel;
	}

	public FieldConfigController getController() {
		return controller;
	}

	public void setController(FieldConfigController controller) {
		this.controller = controller;
	}
}