/*
 * NeConfigPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package com.nms.ui.ptn.systemconfig.dialog.fieldConfig.view;

import javax.swing.JScrollPane;

import twaver.TDataBox;
import twaver.tree.TTree;

import com.nms.db.bean.system.Field;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.ptn.systemconfig.dialog.fieldConfig.controller.FieldConfigController;

/**
 * 
 * @author zr
 */
public class FieldConfigLeftPane extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TDataBox box;
	private TTree tree;
	public  Field constField = null;
	private FieldConfigController controller;
	
	public FieldConfigLeftPane() {
		this.initComponents();
	}

	private void initComponents() {
		box = new TDataBox(ResourceUtil.srcStr(StringKeysObj.PTN_MANAGE_SYSTEM));
		tree = new TTree(box);
		tree.expandPath(tree.getPathForRow(2));
		setViewportView(tree);
	}

	
	public TDataBox getBox() {
		return box;
	}

	public void setBox(TDataBox box) {
		this.box = box;
	}

	public TTree getTree() {
		return tree;
	}

	public void setTree(TTree tree) {
		this.tree = tree;
	}

	public  Field getConstField() {
		return constField;
	}

	public  void setConstField(Field constField) {
		this.constField = constField;
	}

	public FieldConfigController getController() {
		return controller;
	}

	public void setController(FieldConfigController controller) {
		this.controller = controller;
	}

}