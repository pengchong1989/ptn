package com.nms.ui.ptn.clock.view.cx.frequency;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.nms.ui.manager.ExceptionManage;

public class TabPanelOneCX extends JPanel {

	private static final long serialVersionUID = 5344060217658931056L;

	private TabPanelOneAttrCX tabPanelOneAttrCX = null;

	private TabPanelOneStatusCX tabPanelOneStatusCX = null;

	private TabPanelOneOtherCX tabPanelOneOtherCX = null;

	private GridBagLayout gridBagLayout = null;

	public TabPanelOneCX() {

		try {

			init();
		} catch (Exception e) {

			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void init() throws Exception {

		try {

			tabPanelOneAttrCX = new TabPanelOneAttrCX();
			tabPanelOneStatusCX = new TabPanelOneStatusCX();
			tabPanelOneOtherCX = new TabPanelOneOtherCX();
			gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[] {1 };
			gridBagLayout.columnWeights = new double[] { 1 };
			gridBagLayout.rowHeights = new int[] { 105, 200, 105 };
			gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0 };
			
			this.setBackground(Color.WHITE);
			/* Tab页一 网元时钟状态/属性 -> 界面加载 */
			this.add(tabPanelOneAttrCX);/* 属性界面加载 */
			this.add(tabPanelOneStatusCX);/* 状态界面加载 */
			this.add(tabPanelOneOtherCX);/* 其它加载 */
			this.setGridBagLayout();/* Tab页一 网元时钟状态/属性布局 */
			this.setLayout(gridBagLayout);
			
		} catch (Exception e) {

			throw e;
		}
	}

	/**
	 * <p>
	 * Tab页一 网元时钟状态/属性布局
	 * </p>
	 * 
	 * @throws Exception
	 */
	private void setGridBagLayout() throws Exception {

		GridBagConstraints gridBagConstraints = null;
		try {

			gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.insets = new Insets(5, 10, 0, 10);
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(tabPanelOneAttrCX, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 1;
			gridBagLayout.setConstraints(tabPanelOneStatusCX, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 2;
			gridBagLayout.setConstraints(tabPanelOneOtherCX, gridBagConstraints);
		} catch (Exception e) {

			throw e;
		} finally {

			gridBagConstraints = null;
		}
	}
	
	public TabPanelOneAttrCX getTabPanelOneAttrCX() {
		return tabPanelOneAttrCX;
	}

	public void setTabPanelOneAttrCX(TabPanelOneAttrCX tabPanelOneAttrCX) {
		this.tabPanelOneAttrCX = tabPanelOneAttrCX;
	}

	public TabPanelOneStatusCX getTabPanelOneStatusCX() {
		return tabPanelOneStatusCX;
	}

	public void setTabPanelOneStatusCX(TabPanelOneStatusCX tabPanelOneStatusCX) {
		this.tabPanelOneStatusCX = tabPanelOneStatusCX;
	}

	public TabPanelOneOtherCX getTabPanelOneOtherCX() {
		return tabPanelOneOtherCX;
	}

	public void setTabPanelOneOtherCX(TabPanelOneOtherCX tabPanelOneOtherCX) {
		this.tabPanelOneOtherCX = tabPanelOneOtherCX;
	}

	public static void main(String[] args) {

		try {
			TabPanelOneCX tabPanelOneCX = new TabPanelOneCX();
			JFrame frame = new JFrame();
			frame.setSize(500, 400);
			frame.setContentPane(tabPanelOneCX);
			frame.setVisible(true);
		} catch (Exception e) {

			ExceptionManage.dispose(e,TabPanelOneCX.class);
		}
	}

}
