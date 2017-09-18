package com.nms.ui.ptn.ne.group.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import com.nms.db.bean.ptn.path.GroupSpreadInfo;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.ptn.ne.group.controller.GroupSpreadController;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

public class GroupSpreadPanel extends ContentView<GroupSpreadInfo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private JButton okButton;
//	private JPanel buttonPanel;

	public GroupSpreadPanel() {
		super("groupSpreadTable",RootFactory.CORE_MANAGE);
		init();
		this.getRefreshButton().doClick();
	}
	
	private void init() {
		
		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_GROUPSPREAD_MANAGE)));
//		okButton = new JButton("设置");
//		buttonPanel = new JPanel();
		setLayout();	
	}
	
	private void setLayout() {
		GridBagLayout panelLayout = new GridBagLayout();
		this.setLayout(panelLayout);
		panelLayout.rowHeights = new int[] {400, 10};
		panelLayout.rowWeights = new double[] {0, 0};
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
//		c.gridx = 0;
//		c.gridy = 1;
//		c.gridheight = 1;
//		c.gridwidth = 1;
//		c.weightx = 1.0;
//		c.weighty = 1.0;
//		c.fill = GridBagConstraints.CENTER;
//		panelLayout.setConstraints(buttonPanel, c);
//		this.add(buttonPanel);
//		
//		FlowLayout flowLayout = new FlowLayout();
//		flowLayout.setAlignment(FlowLayout.CENTER);
//		buttonPanel.setLayout(flowLayout);
//		buttonPanel.add(okButton);
	}
	
//	@Override
//	public void setTablePopupMenuFactory() {
//		setMenuFactory(null);
//	}
//	
//	public List<GroupSpreadInfo> getSelectInfo(){
//		List<GroupSpreadInfo> list = new ArrayList<GroupSpreadInfo>();
//		list = super.getAllSelect();
//		return list;
//	}
	
	@Override
	public void setController() {
		controller = new GroupSpreadController(this);
	}
	
	@Override
	public Dimension setDefaultSize() {
		return new Dimension(160, ConstantUtil.INT_WIDTH_THREE);
	}

	public List<JButton> setNeedRemoveButtons() {
		List<JButton> buttonList = new ArrayList<JButton>(); 
		buttonList.add(getSearchButton());
		return buttonList;
	}
}
