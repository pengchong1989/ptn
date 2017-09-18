package com.nms.ui.ptn.clock.view.cx;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.ptn.clock.view.cx.clockinterface.TabPanelOneTICX;
import com.nms.ui.ptn.clock.view.cx.clockinterface.TabPanelTwoTICX;

public class ClockInterfacePancelCX extends JPanel{

	private static final long serialVersionUID = 5182271274809553394L;
	
	private JTabbedPane tabbedPane = null;

	private GridBagLayout gridBagLayout = null;
	
	private TabPanelOneTICX tabPanelOneTICX = null;
	private TabPanelTwoTICX tabPanelTwoTICX = null;

	public ClockInterfacePancelCX() {

		try {
			init();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	private void init() throws Exception {

		try {
			
			tabbedPane = new JTabbedPane();
			gridBagLayout = new GridBagLayout();
			tabPanelOneTICX = new TabPanelOneTICX();
			tabPanelTwoTICX = new TabPanelTwoTICX();
			
			tabbedPane.add(ResourceUtil.srcStr(StringKeysTab.TAB_EXTERNAL_CLOCK_INTERFACE), tabPanelOneTICX);
			tabbedPane.add(ResourceUtil.srcStr(StringKeysTab.TAB_LINE_CLOCK_INTERFACE), tabPanelTwoTICX);
			setGridBagLayout();/* 主页面布局 */
			this.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_CLOCK_INTERFACE)));
			this.setLayout(gridBagLayout);
			this.add(tabbedPane);

		} catch (Exception e) {

			throw e;
		}
	}
	
	/**
	 * <p>
	 * 主页面布局
	 * </p>
	 * 
	 * @throws Exception
	 */
	private void setGridBagLayout() throws Exception {

		GridBagConstraints gridBagConstraints = null;
		try {

			gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.gridheight = 1;
			gridBagConstraints.gridwidth = 1;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.insets = new Insets(0, 10, 0, 10);
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagLayout.setConstraints(tabbedPane, gridBagConstraints);
		} catch (Exception e) {

			throw e;
		} finally {

			gridBagConstraints = null;
		}
	}
	
	public static void main(String[] args) {

		try {
			ClockInterfacePancelCX frequencyPanelNew = new ClockInterfacePancelCX();
			JFrame frame = new JFrame();
			frame.setSize(500, 400);
			frame.setContentPane(frequencyPanelNew);
			frame.setVisible(true);
		} catch (Exception e) {

			ExceptionManage.dispose(e,ClockInterfacePancelCX.class);
		}
	}
}
