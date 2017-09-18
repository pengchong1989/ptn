package com.nms.ui.ptn.help;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTab;

public class AboutHelp extends PtnDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1764822082934422233L;
	private JPanel jPanel = null;
	private JLabel jLabel3 = null;
	private String imagePath;
	private GridBagLayout gridBagLayout = null;
	private int height=0;
	private int weight=0;
	public AboutHelp() {
		init();
	}
	/**
	 *初始化
	 */
	private void init() {
		 Image image=null;
		 Dimension	screenSize=null;
		try {
			imagePath = UiUtil.getIconImagePath();
			
			image = Toolkit.getDefaultToolkit().getImage(getClass().getResource(imagePath));
			gridBagLayout = new GridBagLayout();
			jLabel3 = new JLabel();
			jPanel = new JPanel(new BorderLayout());
			jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource(imagePath)));
			jPanel=new JPanel();
			
			initBorderLayout();
			this.setTitle(ResourceUtil.srcStr(StringKeysTab.TAB_ABOUT));
			this.add(jPanel);
			this.add(jLabel3);
			setGridBagLayout();/* 主窗口布局 */
			this.setLayout(gridBagLayout);
			screenSize=Toolkit.getDefaultToolkit().getScreenSize();
			this.setSize(image.getWidth(jLabel3),(int)screenSize.getHeight()/2);
			height=(int)screenSize.getHeight()/2;
			weight=image.getWidth(jLabel3);
			this.setResizable(false);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void setGridBagLayout() {
		GridBagConstraints gridBagConstraints=null;
		try {
			gridBagConstraints=new GridBagConstraints();
			gridBagLayout.columnWidths = new int[] {weight};
			gridBagLayout.columnWeights = new double[] {0,0};
			gridBagLayout.rowHeights = new int[] { 0,35};
			gridBagLayout.rowWeights = new double[] { 0, 0, 0, 0, 0, 0, 0, 0};
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(jLabel3, gridBagConstraints);
			
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 1;
			gridBagLayout.setConstraints(jPanel, gridBagConstraints);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}
	private void initBorderLayout() {
		JLabel jlabel1 = null;
		JLabel jlabel2 = null;
		JLabel jlabel3 = null;
		JLabel jlabel4 = null;
		JLabel jlabel5 = null;
		JLabel jlabel6 = null;
		JLabel jlabel7 = null;
		GridBagLayout gridBagLayouts = null;
		GridBagConstraints gridBagConstraints = null;
		try {
			gridBagLayouts=new GridBagLayout();
			gridBagConstraints = new GridBagConstraints();
			jlabel1 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_JLABTL1_PTN));
			jlabel1.setFont(new java.awt.Font("微软雅黑", 1, 14));
			jlabel2 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_JLABTL2_PTN));
			jlabel2.setFont(new java.awt.Font("微软雅黑", 1, 14));
			jlabel3 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_JLABTL3_PTN));
			jlabel3.setFont(new java.awt.Font("微软雅黑", 1, 14));
			jlabel4 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_JLABTL4_PTN)+ConstantUtil.serviceBean.getMaxConnection());
			jlabel4.setFont(new java.awt.Font("微软雅黑", 1, 14));
			jlabel5 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_JLABTL5_PTN)+ConstantUtil.serviceBean.getMaxSiteNumner());
			jlabel5.setFont(new java.awt.Font("微软雅黑", 1, 14));
			jlabel6 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_JLABTL6_PTN)+ConstantUtil.serviceBean.getDueDate());
			jlabel6.setFont(new java.awt.Font("微软雅黑", 1, 14));
			jlabel7 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_JLABTL7_PTN));
			jlabel7.setFont(new java.awt.Font("微软雅黑", 1, 15));
			
			jPanel.setLayout(gridBagLayouts);
			gridBagLayouts.columnWidths = new int[] { 50, 100, 50, 100 };
			gridBagLayouts.columnWeights = new double[] { 0, 1, 0, 1 };
			gridBagLayouts.rowHeights = new int[] { 20, 20, 20, 20, 20, 20, 20};
			gridBagLayouts.rowWeights = new double[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			gridBagConstraints.insets = new Insets(5, 10, 0, 0);
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagLayouts.setConstraints(jlabel1, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 1;
			gridBagLayouts.setConstraints(jlabel2, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 2;
			gridBagLayouts.setConstraints(jlabel3, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 3;
			gridBagLayouts.setConstraints(jlabel4, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 4;
			gridBagLayouts.setConstraints(jlabel5, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 5;
			gridBagLayouts.setConstraints(jlabel6, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 6;
			gridBagLayouts.setConstraints(jlabel7, gridBagConstraints);

			jPanel.add(jlabel1);
			jPanel.add(jlabel2);
			jPanel.add(jlabel3);
			jPanel.add(jlabel4);
			jPanel.add(jlabel5);
			jPanel.add(jlabel6);
//			jPanel.add(jlabel7);
			jPanel.setLayout(gridBagLayouts);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	public JLabel getjLabel3() {
		return jLabel3;
	}
	public void setjLabel3(JLabel jLabel3) {
		this.jLabel3 = jLabel3;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
}
