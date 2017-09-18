package com.nms.ui.ptn.clock.view.cx.time;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JSplitPane;

import com.nms.db.bean.ptn.clock.PortConfigInfo;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysOperaType;
import com.nms.ui.ptn.clock.controller.ClockPanelControllerTopCX;
import com.nms.ui.ptn.clock.view.cx.dialog.PortMapCreateDialog;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

public class TabPanelTwoTCX extends ContentView<PortConfigInfo> {

	private static final long serialVersionUID = 6384428869330292309L;
	
	private JSplitPane splitPane = null;

	private TabPanelTwoBottomTCX tabPanelTwoBottomtTCX = null;

	private GridBagLayout gridBagLayout = null;

	private JButton portMapping = null;
	private TabPanelTwoTCX tabPanelTwoTCX=this;

	public TabPanelTwoTCX() {

		super("portConfigInfo",RootFactory.CORE_MANAGE);
		try {

			init();
			super.getController().refresh();/* 加载页面数据 */
		} catch (Exception e) {

			ExceptionManage.dispose(e,this.getClass());
		}
	}
	

	public void setController() {

		controller = new ClockPanelControllerTopCX(this);

	}

	public Dimension setDefaultSize() {
		return new Dimension(160, ConstantUtil.INT_WIDTH_THREE);
	}
	
	/**
	 * <p>
	 * 添加‘端口Map表’按钮
	 * </p>
	 */
	public List<JButton> setAddButtons() {
		this.portMapping = new PtnButton(ResourceUtil.srcStr(StringKeysOperaType.BTN_PORT_MAP),RootFactory.CORE_MANAGE);
		this.portMapping.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
////					final ArrangeClockDialog dialog = new ArrangeClockDialog();
//					dialog.setSize(new Dimension(450, 480));
//					dialog.setLocation(UiUtil.getWindowWidth(dialog.getWidth()), UiUtil.getWindowHeight(dialog.getHeight()));
//					dialog.addWindowListener(new WindowAdapter() {
//						@Override
//						public void windowClosed(WindowEvent e) {
//							dialog.dispose();
//						}
//					});
//					dialog.setVisible(true);
					new PortMapCreateDialog(tabPanelTwoTCX);
				} catch (Exception e2) {
				}
			}
		});
		List<JButton> needButtons = new ArrayList<JButton>();
		needButtons.add(this.portMapping);
		return needButtons;
	}

	/**
	 * <p>
	 * 去掉不需要用到的按钮‘search’
	 * </p>
	 */
	public List<JButton> setNeedRemoveButtons() {

		List<JButton> needButtons = new ArrayList<JButton>();
		needButtons.add(super.getSearchButton());
		return needButtons;
	}
	
	private void init() throws Exception {

		int high = 0;
		try {

			tabPanelTwoBottomtTCX = new TabPanelTwoBottomTCX();
			splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
			gridBagLayout = new GridBagLayout();

			splitPane.setOneTouchExpandable(true);
			high = Double.valueOf(Toolkit.getDefaultToolkit().getScreenSize().getHeight()).intValue() / 2;
			splitPane.setDividerLocation(high - 65);
			splitPane.setTopComponent(this.getContentPanel());
			splitPane.setBottomComponent(tabPanelTwoBottomtTCX);
			setGridBagLayout();
			this.setLayout(gridBagLayout);
			this.add(splitPane);
			this.setBackground(Color.WHITE);
		} catch (Exception e) {

			throw e;
		}
	}
	
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
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagLayout.setConstraints(splitPane, gridBagConstraints);
		} catch (Exception e) {

			throw e;
		}
	}
}
