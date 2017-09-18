package com.nms.ui.ptn.performance.view;

import javax.swing.JButton;

import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;

public class CurPerFilterDialog extends PtnDialog{

	private static final long serialVersionUID = 2219190791950771503L;
	private JButton confirmButton;
	private JButton cancelButton;

	public CurPerFilterDialog() {
		init();
		this.setModal(true);
		this.setVisible(true);
	}
	
	public void init() {
		initComponents();
		setLayout();
	}

	private void setLayout() {
		this.add(confirmButton);
		this.add(cancelButton);
	}

	private void initComponents() {
		
		confirmButton = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM));
		cancelButton = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
	}
	
}
