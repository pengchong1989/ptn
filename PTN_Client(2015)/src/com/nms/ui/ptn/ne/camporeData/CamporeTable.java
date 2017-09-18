package com.nms.ui.ptn.ne.camporeData;

/*
 * This source code is part of TWaver 4.1
 *
 * Serva Software PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * Copyright 2002 - 2011 Serva Software. All rights reserved.
 */

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultTreeCellRenderer;

import twaver.Element;
import twaver.TDataBox;
import twaver.TWaverConst;
import twaver.TWaverUtil;
import twaver.table.PageListener;
import twaver.table.TTreeTable;

public class CamporeTable extends TTreeTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4548235905575370690L;
	private Color tableFillColor = new Color(99, 130, 191);
	private Color selectedColor = new Color(0, 0, 100);
	private boolean needPack = false;

	public CamporeTable(TDataBox box) {
		super(box);
		getTableModel().addPageListener(new PageListener() {
			public void pageChanged() {
				needPack = true;
			}
		});
	}

	protected JTableHeader createDefaultTableHeader() {
		JTableHeader tableHeader = new JTableHeader(columnModel) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -1964166602137234593L;

			public void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				Rectangle bounds = new Rectangle(0, 0, this.getWidth(), this.getHeight());
				g2.setPaint(TWaverUtil.createGradientPaint(TWaverConst.GRADIENT_LINE_N, bounds, Color.BLACK, tableFillColor));
				g2.fillRect(0, 0, this.getWidth(), this.getHeight());
				super.paintComponent(g);
			}
		};
		return tableHeader;
	}

	public void paintComponent(Graphics g) {
		this.setOpaque(false);
		Graphics2D g2 = (Graphics2D) g;
		Rectangle bounds = new Rectangle(0, 0, this.getWidth(), this.getHeight());
		g2.setPaint(TWaverUtil.createGradientPaint(TWaverConst.GRADIENT_LINE_N, bounds, Color.BLACK, tableFillColor));
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		if (needPack) {
			needPack = false;
			packAllColumns(true, 0);
		}
		super.paintComponent(g);
	}

	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component prepareRenderer = super.prepareRenderer(renderer, row, column);
		Element element = this.getElementByRowIndex(row);
		if(element != null){
			if (element.isSelected()) {
				prepareRenderer.setBackground(selectedColor);
			} else {
				if (row % 2 == 0) {
					prepareRenderer.setBackground(new Color(95, 119, 165));
				} else {
					prepareRenderer.setBackground(new Color(64, 95, 159));
				}
			}
		}
		prepareRenderer.setForeground(Color.WHITE);
		return prepareRenderer;
	}

	public void prepareTreeColumnRenderer(JLabel renderer, Element element) {
		renderer.setForeground(Color.WHITE);
		renderer.setBackground(new Color(0, 0, 0, 0));
		if (renderer instanceof DefaultTreeCellRenderer) {
			DefaultTreeCellRenderer cellRender = (DefaultTreeCellRenderer) renderer;
			cellRender.setBackgroundNonSelectionColor(null);
		}
		if (element != null && element.isSelected()) {
			renderer.setBackground(selectedColor);
		}
	}

	public boolean getScrollableTracksViewportWidth() {
		return getPreferredSize().width < getParent().getWidth();
	}
}