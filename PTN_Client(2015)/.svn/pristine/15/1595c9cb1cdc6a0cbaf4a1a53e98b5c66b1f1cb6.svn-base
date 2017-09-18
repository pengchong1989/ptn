package com.nms.ui.ptn.statistics.sement;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.NeTreeSelectPanel;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

public class SegmentStatisticsWidthPanel extends JPanel{

	private static final long serialVersionUID = -594595617407337315L;
	private JSplitPane splitPane;
	private NeTreeSelectPanel leftPane;
	private SegmentWidthRightPane widthRightPane;
	private JPanel rightPane;
	private JScrollPane scrollPane;
	private ButtonGroup buttonGroup;
	private JRadioButton btnAll;
	private JRadioButton btnForward;
	private JRadioButton btnBack;
	private PtnButton btnExport;
	private SegmentStatisticsWidthController controller;
	
	public SegmentStatisticsWidthPanel() {
		try {
			init();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

    public void init() throws Exception {
    	this.initComponents();
    	this.setLayout();
		this.btnAll.setSelected(true);
		controller = new SegmentStatisticsWidthController(this);
		this.setController(controller);
	}

	private void initComponents() {
		buttonGroup = new ButtonGroup();
		btnAll = new JRadioButton(ResourceUtil.srcStr(StringKeysBtn.BTN_ALL));
		btnForward = new JRadioButton(ResourceUtil.srcStr(StringKeysObj.STRING_FORWARD));
		btnBack = new JRadioButton(ResourceUtil.srcStr(StringKeysObj.STRING_BACKWARD));
		buttonGroup.add(btnAll);
		buttonGroup.add(btnForward);
		buttonGroup.add(btnBack);
		btnExport = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT),false,RootFactory.COUNTMODU);
		
		leftPane = new NeTreeSelectPanel();
		rightPane = new JPanel();
		widthRightPane = new SegmentWidthRightPane();
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(widthRightPane);
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setOneTouchExpandable(true);
		splitPane.setLeftComponent(leftPane);
		splitPane.setRightComponent(rightPane);
	}

	private void setLayout() {
		this.setRightLayOut();
		GridBagLayout panelLayout = new GridBagLayout();
		this.setLayout(panelLayout);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		panelLayout.setConstraints(splitPane, c);
		this.add(splitPane);
	}

	private void setRightLayOut() {
		GridBagLayout panelLayout = new GridBagLayout();
//		panelLayout.columnWidths = new int[] { 20,20,20,900 };
//		panelLayout.columnWeights = new double[] { 0,0,0,0 };                              
//		panelLayout.rowHeights = new int[] {10,10,10,10,510};
//		panelLayout.rowWeights = new double[] {0,0,0,0,0};
		GridBagConstraints c = new GridBagConstraints();
		this.rightPane.setLayout(panelLayout);
		this.scrollPane.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTitle.TIT_REMAIN_TOTAL)));
		
		//第一行
		c.gridx = 0;
		c.gridy = 0;
		panelLayout.setConstraints(this.btnAll, c); 
		this.rightPane.add(this.btnAll);
		
		c.gridx = 1;
		c.gridy = 0;
		panelLayout.setConstraints(this.btnForward, c);
		this.rightPane.add(this.btnForward);
		
		c.gridx = 2;
		c.gridy = 0;
		panelLayout.setConstraints(this.btnBack, c);
		this.rightPane.add(this.btnBack);
		
		//第二行
		c.gridx = 0;
		c.gridy = 2;
		panelLayout.setConstraints(this.btnExport, c);
		this.rightPane.add(this.btnExport);
		
		//第三行
		c.gridx = 0;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 4;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		panelLayout.setConstraints(this.scrollPane, c);
		this.rightPane.add(this.scrollPane);
	}
	
	public NeTreeSelectPanel getLeftPane() {
		return leftPane;
	}

	public void setLeftPane(NeTreeSelectPanel leftPane) {
		this.leftPane = leftPane;
	}

	public SegmentWidthRightPane getWidthRightPane() {
		return widthRightPane;
	}

	public void setWidthRightPane(SegmentWidthRightPane widthRightPane) {
		this.widthRightPane = widthRightPane;
	}

	public SegmentStatisticsWidthController getController() {
		return controller;
	}

	public void setController(SegmentStatisticsWidthController controller) {
		this.controller = controller;
	}

	public JPanel getRightPane() {
		return rightPane;
	}

	public void setRightPane(JPanel rightPane) {
		this.rightPane = rightPane;
	}

	public JRadioButton getBtnAll() {
		return btnAll;
	}

	public void setBtnAll(JRadioButton btnAll) {
		this.btnAll = btnAll;
	}

	public JRadioButton getBtnForward() {
		return btnForward;
	}

	public void setBtnForward(JRadioButton btnForward) {
		this.btnForward = btnForward;
	}

	public JRadioButton getBtnBack() {
		return btnBack;
	}

	public void setBtnBack(JRadioButton btnBack) {
		this.btnBack = btnBack;
	}

	public PtnButton getBtnExport() {
		return btnExport;
	}

	public void setBtnExport(PtnButton btnExport) {
		this.btnExport = btnExport;
	}
}