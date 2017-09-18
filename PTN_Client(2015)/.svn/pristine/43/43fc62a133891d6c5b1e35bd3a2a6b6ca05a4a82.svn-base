package com.nms.ui.ptn.systemconfig.dialog.bsUpdate.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import com.nms.ui.manager.control.NeTreePanel;




public class BatchSoftwareUpgradePanel extends JPanel{


	private static final long serialVersionUID = 1L;
	private BatchSoftwareUpdateRightPanle batchSoftwareUpdateRightPanle;
	private NeTreePanel neTreePanel;
	private JSplitPane splitPane=null;
	private int type = 1;
	public BatchSoftwareUpgradePanel(){
		this.initComponents();
		this.setLayout();
		
	}

	private void initComponents() {
		batchSoftwareUpdateRightPanle = new BatchSoftwareUpdateRightPanle();
		neTreePanel = new NeTreePanel(true,2,batchSoftwareUpdateRightPanle.getTable(),false);
		this.splitPane=new JSplitPane();
		this.splitPane.add(this.neTreePanel, JSplitPane.LEFT);
		this.splitPane.add(this.batchSoftwareUpdateRightPanle, JSplitPane.RIGHT);
	}
	
	private void setLayout() {
		GridBagLayout panelLayout = new GridBagLayout();
		this.setLayout(panelLayout);
		GridBagConstraints c = null;
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		panelLayout.setConstraints(splitPane, c);
		this.add(splitPane);
	}

	public BatchSoftwareUpdateRightPanle getBatchSoftwareUpdateRightPanle() {
		return batchSoftwareUpdateRightPanle;
	}

	public void setBatchSoftwareUpdateRightPanle(BatchSoftwareUpdateRightPanle batchSoftwareUpdateRightPanle) {
		this.batchSoftwareUpdateRightPanle = batchSoftwareUpdateRightPanle;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void clear() {
		// TODO Auto-generated method stub
		
	}

	
	
}
