package com.nms.ui.ptn.safety;

import java.awt.Color;
import java.awt.Component;

import javax.swing.table.TableCellRenderer;

import twaver.Element;
import twaver.TDataBox;
import twaver.table.PageListener;
import twaver.table.TTreeTable;

public class LogDataTable extends TTreeTable {

	private static final long serialVersionUID = -4007194330395049581L;
	
	public LogDataTable(TDataBox box) {
		super(box);
		getTableModel().addPageListener(new PageListener() {
			public void pageChanged() {
			}
		});
	}

	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component prepareRenderer = super.prepareRenderer(renderer, row, column);
		Element element = this.getElementByRowIndex(row);
		if(element != null){
			if(row == 0){
				prepareRenderer.setBackground(new Color(180, 200, 255));
			}else{
				if (row % 2 == 0) {

					prepareRenderer.setBackground(new Color(180, 200, 255));
				} else {
					prepareRenderer.setBackground(Color.WHITE);
				}
			}
		}
		prepareRenderer.setForeground(Color.BLACK);
		return prepareRenderer;
	}
}
