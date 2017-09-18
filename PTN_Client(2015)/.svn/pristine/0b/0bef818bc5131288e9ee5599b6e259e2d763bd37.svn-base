package com.nms.ui.ptn.systemconfig.dialog.fieldConfig.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.nms.ui.ptn.systemconfig.NeConfigView;
import com.nms.ui.ptn.systemconfig.dialog.fieldConfig.action.FieldConfigAction;

public class FieldConfigController {

	private NeConfigView view;
	private FieldConfigAction fieldConfigAction = new FieldConfigAction();

	public FieldConfigController(NeConfigView view) {
		this.view = view;
		fieldConfigAction.initTree(view);
		addActionListener();
	}
	
	public void initTree(){
		fieldConfigAction.initTree(view);
	}
	
	private void addActionListener() {
		view.getLeftPane().getTree().addTreeNodeClickedActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				fieldConfigAction.addTreeNodeClickAction(view);
			}
		});
	}

	public FieldConfigAction getFieldConfigAction() {
		return fieldConfigAction;
	}

	public void setFieldConfigAction(FieldConfigAction fieldConfigAction) {
		this.fieldConfigAction = fieldConfigAction;
	}
	
}
