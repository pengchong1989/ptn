package com.nms.ui.ptn.alarm.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import twaver.Alarm;
import twaver.Element;
import twaver.TDataBox;
import twaver.table.TAlarmTable;
import twaver.table.TTablePopupMenuFactory;

import com.nms.db.bean.alarm.HisAlarmInfo;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.ptn.alarm.AlarmTools;
import com.nms.ui.ptn.alarm.controller.SiteHisAlarmController;

/**
 * 历史告警面板
 * 
 * @author wangwf
 * 
 */
public class SiteHisAlarmPanel extends JPanel {
	private static final long serialVersionUID = -4046119221737730984L;
	private JScrollPane contentScrollPane;
	private JPanel contentPanel;
	private TDataBox box = new TDataBox();
	private TAlarmTable alarmTable;
	private JButton refreshButton;
	private JButton exportButton; // 导出按钮
	private SiteHisAlarmController controller;
	private JPanel buttonPanel;
	int pageSize[] = null;

	public SiteHisAlarmPanel() {
		controller = new SiteHisAlarmController(this);
		init();
	}

	/*
	 * 初始化界面和数据
	 */
	public void init() {
		initComponents();
		setLayout();
		addListention();
		controller.refresh();
	}

	/**
	 * 监听事件
	 */
	private void addListention() {
		this.refreshButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.refresh();
			}
		});

		exportButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				controller.export();
			}
		});

		this.alarmTable.addAlarmClickedActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Alarm alarm = (Alarm) e.getSource();
				Element element = SiteHisAlarmPanel.this.box.getElementByID(alarm.getElementID());
				SiteHisAlarmPanel.this.box.getSelectionModel().setSelection(element);
			}
		});

	}

	/*
	 * 设置右键菜单
	 */
	public void setTablePopupMenuFactory(TTablePopupMenuFactory factory) {
		alarmTable.setTableBodyPopupMenuFactory(factory);
	}

	/**
	 * 初始化控件
	 */
	public void initComponents() {
		contentScrollPane = new JScrollPane();
		contentScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contentScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPanel = new JPanel();
		contentPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTab.TAB_HISALARM)));
		refreshButton = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_REFRESH));
		exportButton = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT));
		AlarmTools alarmTools=new AlarmTools();
		alarmTable = new TAlarmTable(box, alarmTools.createDefaultColumns());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenSize.height = 220;
		screenSize.width = 120;
		alarmTable.setPreferredScrollableViewportSize(screenSize);
		alarmTable.getTableHeader().setResizingAllowed(true);
		alarmTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		alarmTable.setDragEnabled(false);
		contentScrollPane.setViewportView(alarmTable);
		alarmTable.setTableHeaderPopupMenuFactory(null);
		alarmTable.setTableBodyPopupMenuFactory(null);
		pageSize = new int[] { 0, 10, 30, 50 };
		buttonPanel = new JPanel();
	}

	/*
	 * 工具按钮布局
	 */
	public void setButtonLayout() {
		GridBagLayout buttonLayout = new GridBagLayout();
		buttonLayout.columnWidths = new int[] { 40, 40, 40, 180, 80 };
		buttonLayout.columnWeights = new double[] { 0, 0, 0, 0.4, 0 };
		buttonLayout.rowHeights = new int[] { 40 };
		buttonLayout.rowWeights = new double[] { 0 };
		GridBagConstraints c = null;
		c = new GridBagConstraints();
		buttonPanel.setLayout(buttonLayout);
		// 操作菜单按钮布局
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		buttonLayout.setConstraints(refreshButton, c);
		buttonPanel.add(refreshButton);
		
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(0, 5, 0, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		buttonLayout.setConstraints(exportButton, c);
		buttonPanel.add(exportButton); 
	}

	/**
	 * 页面布局
	 */
	public void setLayout() {
		setButtonLayout();
		GridBagLayout contentLayout = new GridBagLayout();
		contentPanel.setLayout(contentLayout);
		GridBagConstraints c = null;
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 0.0;
		c.insets = new Insets(0, 0, 0, 0);
		c.fill = GridBagConstraints.BOTH;
		contentLayout.setConstraints(buttonPanel, c);
		contentPanel.add(buttonPanel);
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 0.4;
		contentLayout.setConstraints(contentScrollPane, c);
		contentPanel.add(contentScrollPane);
		GridBagLayout panelLayout = new GridBagLayout();
		this.setLayout(panelLayout);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		panelLayout.setConstraints(contentPanel, c);
		this.add(contentPanel);
	}

	public JButton getRefreshButton() {
		return refreshButton;
	}

	public void setRefreshButton(JButton refreshButton) {
		this.refreshButton = refreshButton;
	}

	public SiteHisAlarmController getController() {
		return controller;
	}

	/*
	 * 初始化数据
	 */
	public void initData(List<HisAlarmInfo> infos) {
		if (infos != null && infos.size() > 0) {
			for (HisAlarmInfo info : infos) {
				info.putClientProperty();
				box.getAlarmModel().addAlarm(info);
			}
		}

	}

	public TAlarmTable getAlarmTable() {
		return alarmTable;
	}

	public TDataBox getBox() {
		return box;
	}
}
