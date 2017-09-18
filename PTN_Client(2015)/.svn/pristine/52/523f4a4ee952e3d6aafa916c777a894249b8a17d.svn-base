package com.nms.ui.ptn.clock.view.cx.frequency;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTab;

public class TabPanelTwoBottomCX extends JTabbedPane{

	private static final long serialVersionUID = -3559954558140126277L;
	
	private JPanel basicInfoPanel = null;
	
	private JLabel workingStatus = null;

	private JLabel managingStatus = null;
	
	private JTextField workingStatus_ = null;
	
	private JTextField managingStatus_ = null;
	
	private GridBagLayout gridBagLayout = null;
	
	public TabPanelTwoBottomCX() {

		try {
			init();
		} catch (Exception e) {

			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	private void init() throws Exception {

		try {

			basicInfoPanel = new JPanel();
			workingStatus = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_JOB_STATUS));
			managingStatus = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_MANAGE_STATUS));
			workingStatus_ = new JTextField();
			managingStatus_ = new JTextField();
			gridBagLayout = new GridBagLayout();
			
			workingStatus_.setEnabled(false);
			managingStatus_.setEnabled(false);
			
			basicInfoPanel.add(workingStatus);
			basicInfoPanel.add(managingStatus);
			basicInfoPanel.add(workingStatus_);
			basicInfoPanel.add(managingStatus_);
			basicInfoPanel.setBackground(Color.WHITE);
			basicInfoPanel.setLayout(gridBagLayout);
			setGridBagLayout();/*对tab页内的panel布局*/
			this.add(ResourceUtil.srcStr(StringKeysTab.TAB_BASIC_INFO), basicInfoPanel);
			this.setBackground(Color.WHITE);
		} catch (Exception e) {

			throw e;
		}
	}
	
	private void setGridBagLayout()throws Exception{
		
		GridBagConstraints gridBagConstraints = null;
		try{
			
			gridBagLayout.columnWidths = new int[] { 100, 300, 100, 300 };
			gridBagLayout.columnWeights = new double[] { 0, 0, 0, 0 };
			gridBagLayout.rowHeights = new int[] { 1};
			gridBagLayout.rowWeights = new double[] { 0};
			
			gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.insets = new Insets(5, 10, 5, 0);
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(managingStatus, gridBagConstraints);
			
			
			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(workingStatus, gridBagConstraints);
			
			gridBagConstraints.insets = new Insets(5, 10, 5, 50);
			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(managingStatus_, gridBagConstraints);
			
			gridBagConstraints.insets = new Insets(5, 10, 5, 10);
			gridBagConstraints.gridx = 3;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(workingStatus_, gridBagConstraints);
		}catch(Exception e){
			
			throw e;
		}
	}
	
	public JTextField getWorkingStatus_() {
		return workingStatus_;
	}

	public void setWorkingStatus_(JTextField workingStatus) {
		workingStatus_ = workingStatus;
	}

	public JTextField getManagingStatus_() {
		return managingStatus_;
	}

	public void setManagingStatus_(JTextField managingStatus) {
		managingStatus_ = managingStatus;
	}

	public static void main(String[] args) {

		try {
			TabPanelTwoBottomCX frequencyPanelNew = new TabPanelTwoBottomCX();
			JFrame frame = new JFrame();
			frame.setSize(500, 400);
			frame.setContentPane(frequencyPanelNew);
			frame.setVisible(true);
		} catch (Exception e) {

			ExceptionManage.dispose(e,TabPanelTwoBottomCX.class);
		}
	}

}
