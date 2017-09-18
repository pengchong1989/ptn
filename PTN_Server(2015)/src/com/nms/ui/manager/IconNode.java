package com.nms.ui.manager;

import javax.swing.Icon;
import javax.swing.tree.DefaultMutableTreeNode;

// 定义节点类
public class IconNode extends DefaultMutableTreeNode {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2918763480155982098L;
	protected Icon icon;
	protected String txt;
	protected ControlKeyValue controlKeyValue;

	// 只包含文本的节点构造
	public IconNode(String txt) {
		super();
		this.txt = txt;
	}

	// 包含文本和图片的节点构造
	public IconNode(Icon icon, String txt,ControlKeyValue controlKeyValue) {
		super();
		this.icon = icon;
		this.txt = txt;
		this.controlKeyValue=controlKeyValue;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setText(String txt) {
		this.txt = txt;
	}

	public String getText() {
		return txt;
	}

	public ControlKeyValue getControlKeyValue() {
		return controlKeyValue;
	}

	public void setControlKeyValue(ControlKeyValue controlKeyValue) {
		this.controlKeyValue = controlKeyValue;
	}

	
}