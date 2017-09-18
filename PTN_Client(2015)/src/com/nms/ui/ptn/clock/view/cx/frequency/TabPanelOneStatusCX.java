package com.nms.ui.ptn.clock.view.cx.frequency;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysPanel;

public class TabPanelOneStatusCX extends JPanel {

	private static final long serialVersionUID = 744472367700039913L;
	
	private SystemClockPanelCX systemClockPanel = null;
	
	private ExportClockPanelCX exportClockPanel = null;
	
	private GridBagLayout gridBagLayout = null;

	public TabPanelOneStatusCX() {

		try {

			 init();
		} catch (Exception e) {

			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	private void init() throws Exception {

		try {

			systemClockPanel = new SystemClockPanelCX();
			exportClockPanel = new ExportClockPanelCX();
			gridBagLayout = new GridBagLayout();
			
			this.setBackground(Color.WHITE);
			this.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_NE_CLOCK_STATUS)));
			this.add(systemClockPanel);
			this.add(exportClockPanel);
			this.setGridBagLayout();/* Tab页一 网元时钟状态/属性 -> 状态布局 */
			this.setLayout(gridBagLayout);
		} catch (Exception e) {

			throw e;
		}
	}
	
	/**
	 * <p>
	 * Tab页一 网元时钟状态/属性 -> 状态布局
	 * </p>
	 * @throws Exception
	 */
	private void setGridBagLayout()throws Exception{
		
		GridBagConstraints gridBagConstraints = null;
		try{
			
			gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridheight = 1;
			gridBagConstraints.gridwidth = 1;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.insets = new Insets(5, 10, 5, 10);
			
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(systemClockPanel, gridBagConstraints);

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(exportClockPanel, gridBagConstraints);

		}catch(Exception e){
			
			throw e;
		}finally{
			
			gridBagConstraints=null;
		}
	}

	public SystemClockPanelCX getSystemClockPanel() {
		return systemClockPanel;
	}

	public void setSystemClockPanel(SystemClockPanelCX systemClockPanel) {
		this.systemClockPanel = systemClockPanel;
	}

	public ExportClockPanelCX getExportClockPanel() {
		return exportClockPanel;
	}

	public void setExportClockPanel(ExportClockPanelCX exportClockPanel) {
		this.exportClockPanel = exportClockPanel;
	}
	
}



