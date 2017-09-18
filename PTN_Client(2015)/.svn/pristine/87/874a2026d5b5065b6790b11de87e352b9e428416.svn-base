package com.nms.ui.manager.control;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 带有选择按钮的网元树
 * @author Administrator
 *
 */
public class NeTreeSelectPanel extends JPanel {

	private static final long serialVersionUID = -1267873479538816905L;
	private JButton btnImport;
	private NeTreePanel neTreePanel=null;	//tree面板
	
	public NeTreeSelectPanel(){
		this.initComponents();
		this.setLayout();
	}

	private void initComponents() {
		btnImport = new JButton(">");
		this.neTreePanel=new NeTreePanel(true,2,null,false);
	}
	
	private void setLayout() {
		GridBagLayout c = new GridBagLayout();
		c.columnWidths = new int[] { 200,50 };
		c.columnWeights = new double[] { 0.1,0 };                              
		c.rowHeights = new int[] {1000};
		c.rowWeights = new double[] {};
		this.setLayout(c);
		
		GridBagConstraints g = new GridBagConstraints();
		g.fill = GridBagConstraints.BOTH;
		g.insets = new Insets(5,5,5,5);
		
		//第一行 第一列
		g.gridx = 0;
		g.gridy = 0;
		c.setConstraints(this.neTreePanel, g);
		this.add(this.neTreePanel);
		
		//第一行 第二列
		g.gridx = 1;
		g.fill = GridBagConstraints.NONE;
		c.setConstraints(this.btnImport, g);
		this.add(this.btnImport);
	}

//
//	public void setConstField(Field constField) {
//		FieldConfigLeftPane.constField = constField;
//	}

	public NeTreePanel getNeTreePanel() {
		return neTreePanel;
	}

	public JButton getBtnImport() {
		return btnImport;
	}

	public void setBtnImport(JButton btnImport) {
		this.btnImport = btnImport;
	}
}
