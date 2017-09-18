package com.nms.ui.ptn.performance.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import com.nms.db.bean.perform.PerformanceTaskInfo;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.ptn.performance.controller.PerformanceTaskController;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

/**
 * 长期性能任务面板
 * 
 * @author Administrator
 * 
 */
public class PerformanceTaskPanel extends ContentView<PerformanceTaskInfo> {

	private static final long serialVersionUID = 5759887972999119535L;
	public PerformanceTaskPanel() {
		super("perTaskTable",RootFactory.PROFOR_MANAGE);
		init();
	}

	private void init() {
		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_LONG_PERFORMANCE_TASK_LIST)));
		setLayout();
		try {
			getController().refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
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
		panelLayout.setConstraints(this.getContentPanel(), c);
		this.add(this.getContentPanel());
	}
	
	@Override
	public void setController() {
		this.controller = new PerformanceTaskController(this);
	}

	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getExportButton());
		needRemoveButtons.add(getInportButton());
		needRemoveButtons.add(getFiterZero());
		return needRemoveButtons;
	}
	
	
}
