/**
 * 
 */
package com.nms.ui.manager;


import javax.swing.JTextPane;

import twaver.TWaverConst;
import twaver.network.ui.ComponentAttachment;
import twaver.network.ui.ElementUI;

public class TopoAttachment extends ComponentAttachment {
	
	JTextPane textPane = new JTextPane();
	@SuppressWarnings("unused")
	private ElementUI ui = null;
	
	/**
	 * @param name
	 * @param ui
	 */
	public TopoAttachment(String name, ElementUI ui) {
		super(name, ui);
		this.ui = ui;
		
		textPane.setText(ui.getElement().getBusinessObject().toString());
		this.setStyle(TWaverConst.ATTACHMENT_STYLE_BUBBLE);
		this.setShadowVisible(true);
		this.setBodyVisible(true);
		this.setPosition(TWaverConst.POSITION_INNER_TOPRIGHT);
		this.setXOffset(5);
		this.setYOffset(-8);
//		this.setClosable(true);
//		this.setMinimizable(true);
		this.setSize(textPane.getPreferredSize());
		
		this.setComponent(textPane);
	}

}
