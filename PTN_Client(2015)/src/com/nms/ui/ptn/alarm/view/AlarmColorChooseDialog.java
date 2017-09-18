package com.nms.ui.ptn.alarm.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;

import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnDialog;

/**
 * 告警颜色选择器
 * @author guoqc
 *
 */
public class AlarmColorChooseDialog extends PtnDialog {
	private static final long serialVersionUID = 5742571289128836496L;
	private JColorChooser colorChooser;
	private JPanel colorPanel;
	private JPanel btnPanel;
	private JButton btnSave;
	private JButton btnCancel;
	private Color color = null;
	
	public AlarmColorChooseDialog(){
		super();
		this.init();
		UiUtil.showWindow(this, 500, 500);
	}

	private void init() {
		this.initCompoment();
		this.setLayout();
		this.addActionListener();
	}

	private void initCompoment() {
		this.colorPanel = new JPanel();
		this.colorChooser = new JColorChooser();
		this.colorPanel.add(this.colorChooser);
		this.btnSave = new JButton("保存");
		this.btnCancel = new JButton("取消");
		this.btnPanel = new JPanel();
	}

	private void setLayout() {
		this.setBtnLayout();
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 450 };
		componentLayout.rowHeights = new int[] { 420, 30 };
		this.setLayout(componentLayout);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.BOTH;
		componentLayout.setConstraints(this.colorPanel, c);
		this.add(this.colorPanel);
		
		c.gridy = 1;
		componentLayout.setConstraints(this.btnPanel, c);
		this.add(this.btnPanel);
	}

	private void setBtnLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 350, 20 };
		componentLayout.rowHeights = new int[] { 30 };
		this.btnPanel.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 5, 5);
		componentLayout.setConstraints(this.btnSave, c);
		this.btnPanel.add(this.btnSave);

		c.gridx = 1;
		c.anchor = GridBagConstraints.CENTER;
		componentLayout.setConstraints(this.btnCancel, c);
		this.btnPanel.add(this.btnCancel);
	}

	private void addActionListener() {
		this.btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				color = colorChooser.getColor();
				dispose();
			}
		});
		
		this.btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
