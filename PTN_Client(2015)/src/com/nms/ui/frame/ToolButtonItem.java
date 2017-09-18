package com.nms.ui.frame;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.plaf.metal.MetalComboBoxIcon;

public class ToolButtonItem extends JComponent implements ActionListener {

	private static final long serialVersionUID = 8797225121267774606L;

	private JPopupMenu popupMenu;
	private JButton itemButton; //下拉菜单按钮
	private JButton toolButton;  //工具栏上的工具按钮
	private JPanel panel;
	public ToolButtonItem(JButton toolButton) {
		this.toolButton = toolButton;
		panel = new JPanel();
		itemButton = new JButton(new MetalComboBoxIcon());
		Insets insert = itemButton.getMargin();
		itemButton.setMargin(new Insets(insert.top, 1, insert.bottom, 1));
		itemButton.addActionListener(this);
	}
	
	public JComponent getItem(){
		setLayout();
		return panel;
	}
	
	private void setLayout(){
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		panel.setLayout(gbl);
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		gbl.setConstraints(toolButton, c);
		panel.add(toolButton);

		c.weightx = 0;
		c.gridx++;
		gbl.setConstraints(itemButton, c);
		panel.add(itemButton);
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		popupMenu.show(toolButton, toolButton.getX(),
				itemButton.getHeight());
	}
	
	public void addPopupMenu(JPopupMenu popupMenu){
		this.popupMenu = popupMenu;
	}

}
