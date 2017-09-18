/*
 * This source code is part of TWaver 4.1
 *
 * Serva Software PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * Copyright 2002 - 2011 Serva Software. All rights reserved.
 */

package com.nms.ui.frame;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

import twaver.table.TTable;
import twaver.table.TTableModel;

public class RowIDRenderer extends DefaultTableCellRenderer{

	private static final long serialVersionUID = 9003569419416419693L;

	public RowIDRenderer(){
		this.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if(table instanceof TTable){
			TTableModel model = ((TTable)table).getTableModel();			
			value = (row + model.getPageRowSize() * (model.getCurrentPageIndex()-1) )+1;
		}else{
			value = row +1;
		}
		super.getTableCellRendererComponent(table, value, isSelected, false, row, column);
		this.setForeground(Color.BLUE);
		this.setBackground(UIManager.getColor("Panel.background"));
		return this;
	}
	
}