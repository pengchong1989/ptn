package com.nms.ui.ptn.ne.statusData.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysObj;

public class MacStudySelect extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2472418882275304164L;
	
	private PortMacStudy portMacStudy ;
	private VlanMacStudy vlanMacStudy;
	
	public MacStudySelect(){
		init();
	}

	private void init() {
		portMacStudy = new PortMacStudy();
		vlanMacStudy = new VlanMacStudy();
		//设置主页布局
		setsetLayout();
		this.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysObj.STUDYMACCOUNT)));
	}

	/**
	 * 设置布局
	 */
	public void setsetLayout() {
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 40,600,40};
		layout.columnWeights = new double[] { 0, 0, 0, 0.4};
		layout.rowHeights = new int[] {100,100,100};
		layout.rowWeights = new double[] { 0 };
		GridBagConstraints c = null;
		c = new GridBagConstraints();
		this.setLayout(layout);
		// 操作菜单按钮布局
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.setConstraints(portMacStudy, c);
		this.add(portMacStudy);
		
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(0, 5, 0, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.setConstraints(vlanMacStudy, c);
		this.add(vlanMacStudy); 
	}
}
