package com.nms.ui.ptn.performance.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import com.nms.db.bean.perform.CurrentPerforInfo;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.ptn.performance.controller.CurrentPerformanceController;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

/**
 * 当前性能面板
 * 
 * @author Administrator
 * 
 */
public class CurrentPerformancePanel extends ContentView<CurrentPerforInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2380268802007219880L;
	
	public CurrentPerformancePanel() {
		super("currPerTable",RootFactory.PROFOR_MANAGE);
		init();
	}

	public void init() {
		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_PERFORMANCE_LIST)));
		setLayout();
	}

	public void setLayout() {
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
		panelLayout.setConstraints(this.getContentPanel(), c);
		this.add(this.getContentPanel());
	}

	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getAddButton());
		needRemoveButtons.add(getDeleteButton());
		needRemoveButtons.add(getUpdateButton());
//		needRemoveButtons.add(getExportButton());
		needRemoveButtons.add(getInportButton());
		return needRemoveButtons;
	}
	
	@Override
	public List<JButton> setAddButtons() {

		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(this.getCleanButton());
		return needRemoveButtons;

	}
	
	@Override
	public void setTablePopupMenuFactory() {
		setMenuFactory(null);
	}
	
	/**
	 * 设置性能清零按钮
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private JButton getCleanButton() {
		PtnButton jButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_PERFORMANCE_CLEAN),false,RootFactory.PROFOR_MANAGE);
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new PerformanceCleanDialog();
			}
		});
		
		return jButton;
	}

	@Override
	public void setController() {
		controller = new CurrentPerformanceController(this);
	}
	
}
