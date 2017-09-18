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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import twaver.TDataBox;
import twaver.table.TAlarmTable;
import twaver.table.TTablePopupMenuFactory;

import com.nms.db.enums.EManufacturer;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.ViewDataTable;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.ptn.alarm.AlarmTools;
import com.nms.db.bean.alarm.CurrentAlarmInfo;
import com.nms.ui.ptn.alarm.controller.SiteCurrentAlarmController;
import com.nms.ui.ptn.alarm.service.AlarmAnalyse;

/**
 * 当前告警面板
 * 
 * @author wangwf
 * 
 */
public class SiteCurrentAlarmPanel extends JPanel {

	private static final long serialVersionUID = -7556197239484214641L;
	private JScrollPane contentScrollPane;
	private JPanel contentPanel;
	private TDataBox box = new TDataBox();
	private TAlarmTable alarmTable; // 告警table表
	private JButton refreshButton; // 刷新按钮
	private JButton exportButton; // 导出按钮
	private JTabbedPane tabbedPane;
	private JPanel userInfoPanel;
	private JSplitPane splitPane;
	private SiteCurrentAlarmController controller;
	private JPanel buttonPanel;
	private JLabel warningNameLabel;
	private JTextField warningNameText;
	private JLabel warningDescLabel;
	private JTextArea warnningDescText;
	private JLabel effectLabel;// 影响;
	private JTextArea effectText;
	private JLabel solutionLabel;// 解决方案
	private JTextArea solutionText;
	private JLabel reserveLabel;// 备注
	private JTextArea reserveText;
	private JLabel mayreasonLabel;// 告警可能原因
	private JTextArea mayreasonText;
//	private JLabel relatedPathLabel;// 关联路径
//	private JTextArea relatedPathText;
	private JSplitPane analysisAlarmSplitPane;// 告警分析
	private JScrollPane mainBusinessScrollPane;
	private JScrollPane relatedBusinessScrollPane;
	private JPanel mainBusinessPanel;// 主要业务
	private JPanel relatedBusinessPanel;// 关联影响业务
	private JLabel mainBusinessLabel;
	private JLabel relatedBusinessLabel;
	@SuppressWarnings("rawtypes")
	private ViewDataTable mainBusinessTable;
	@SuppressWarnings("rawtypes")
	private ViewDataTable relatedBusinessTable;
	private JButton synchroButton;//同步按钮
	int pageSize[] = null;

	/**
	 * 设置控制类
	 */
	public SiteCurrentAlarmPanel() {
		controller = new SiteCurrentAlarmController(this);
		init();
	}

	/**
	 * 初始化
	 */
	public void init() {
		initComponents();
		setLayout();
		addListention();
	}

	/**
	 * 时间监听
	 */
	private void addListention() {
		refreshButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				controller.refresh();
			}
		});
		
		exportButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				controller.export();
			}
		});

		alarmTable.addAlarmClickedActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				initDetailInfo();
			}
		});

		tabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				int index = tabbedPane.getSelectedIndex();
				switch (index) {
				// 用户信息面板
				case 0:
					// initUserInfoPanel();
					break;
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				default:
					break;
				}
			}
		});
		synchroButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				controller.synchro();
				
			}
		});
	}

	/**
	 * 详细面板事件
	 */
	public void initDetailInfo() {
		// 新增告警分析 add dxh
		if (alarmTable.getAllSelectedAlarms() != null && alarmTable.getAllSelectedAlarms().size() > 0) {
			CurrentAlarmInfo currentAlarmInfo = (CurrentAlarmInfo) alarmTable.getAllSelectedAlarms().get(0);
			AlarmAnalyse aa = new AlarmAnalyse(currentAlarmInfo, mainBusinessTable, relatedBusinessTable);
			if (alarmTable.getSelectedRowCount() > 0) {			
				aa.show();
				// 新增用户信息
				warningNameText.setText(currentAlarmInfo.getWarningLevel().getWarningname());// 告警名称
				warnningDescText.setText(currentAlarmInfo.getAlarmDesc());// 描述
				effectText.setText(currentAlarmInfo.getWarningLevel().getWaringeffect());// 影响
				reserveText.setText(currentAlarmInfo.getAlarmComments());// 备注
				solutionText.setText(currentAlarmInfo.getWarningLevel().getWarningadvice());// 解决方案	
				mayreasonText.setText(currentAlarmInfo.getWarningLevel().getWarningmayreason());//告警可能原因
			} else {
				aa.clearData();
			}
		}
		
	}
	
	/**
	 * 改变列头弹出菜单
	 * @param factory
	 */
	public void setTablePopupMenuFactory(TTablePopupMenuFactory factory) {
		alarmTable.setTableBodyPopupMenuFactory(factory);
	}

	/**
	 * 初始化控件
	 */
	@SuppressWarnings("rawtypes")
	public void initComponents() {
		contentScrollPane = new JScrollPane();
		contentScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contentScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPanel = new JPanel();
		contentPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTab.TAB_CURRALARM)));
		refreshButton = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_REFRESH));
		exportButton = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT));
		synchroButton = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SYNCHRO));
		AlarmTools alarmTools=new AlarmTools();
		alarmTable = new TAlarmTable(box, alarmTools.createDefaultColumns());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenSize.height = 220;
		screenSize.width = 120;
		alarmTable.setPreferredScrollableViewportSize(screenSize);
		alarmTable.getTableHeader().setResizingAllowed(true);
		alarmTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		alarmTable.setDragEnabled(true);
		alarmTable.setColumnResizable(true);
		alarmTable.setRowResizable(false);
		contentScrollPane.setViewportView(alarmTable);
		// 用户信息面板
		userInfoPanel = new JPanel();
		warningNameLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ALARM_ALARM));
		warningNameText = new JTextField(40);
		warningNameText.setEditable(false);
		warningDescLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ALARM_DESC));
		warnningDescText = new JTextArea(5, 40);
		warnningDescText.setEditable(false);
		warnningDescText.setLineWrap(true);
		warnningDescText.setWrapStyleWord(true);
		warnningDescText.setBorder(BorderFactory.createEtchedBorder());
		effectLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ALARM_IMPACT));
		effectText = new JTextArea(5, 40);
		effectText.setEditable(false);
		effectText.setLineWrap(true);
		effectText.setWrapStyleWord(true);
		effectText.setBorder(BorderFactory.createEtchedBorder());
		solutionLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ALARM_SOLUTION));
		solutionText = new JTextArea(5, 40);
		solutionText.setEditable(false);
		solutionText.setLineWrap(true);
		solutionText.setWrapStyleWord(true);
		solutionText.setBorder(BorderFactory.createEtchedBorder());
	
		
		reserveLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ALARM_REMARK));
		reserveText = new JTextArea(5, 40);
		reserveText.setEditable(false);
		reserveText.setLineWrap(true);
		reserveText.setWrapStyleWord(true);
		reserveText.setBorder(BorderFactory.createEtchedBorder());
		
		mayreasonLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ALARM_MAY_REASON));
		mayreasonText = new JTextArea(10, 40);
		mayreasonText.setEditable(false);
		mayreasonText.setLineWrap(true);
		mayreasonText.setWrapStyleWord(true);
		mayreasonText.setBorder(BorderFactory.createEtchedBorder());
		// 告警分析面板
		// 主要业务
		mainBusinessScrollPane = new JScrollPane();
		mainBusinessPanel = new JPanel();
		mainBusinessLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ALARM_MAINLY_AFFECT_BUSINESS));

		// 关联影响业务
		relatedBusinessScrollPane = new JScrollPane();
		relatedBusinessPanel = new JPanel();
		relatedBusinessLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ALARM_ASSOCIATED_AFFECT_BUSINESS));
		// table
		mainBusinessTable = new ViewDataTable("analysisAlarmTable");
		mainBusinessTable.getTableHeader().setResizingAllowed(true);
		mainBusinessTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		mainBusinessTable.setTableHeaderPopupMenuFactory(null);
		mainBusinessTable.setTableBodyPopupMenuFactory(null);

		Dimension screenSize2 = Toolkit.getDefaultToolkit().getScreenSize();
		screenSize2.height = 220;
		screenSize2.width = 120;
		mainBusinessTable.setPreferredScrollableViewportSize(screenSize2);
		mainBusinessScrollPane.setViewportView(mainBusinessTable);
		mainBusinessScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		relatedBusinessTable = new ViewDataTable("analysisAlarmTable");
		relatedBusinessTable.getTableHeader().setResizingAllowed(true);
		relatedBusinessTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		relatedBusinessTable.setTableHeaderPopupMenuFactory(null);
		relatedBusinessTable.setTableBodyPopupMenuFactory(null);
		relatedBusinessTable.setPreferredScrollableViewportSize(screenSize2);
		relatedBusinessScrollPane.setViewportView(relatedBusinessTable);
		relatedBusinessScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		analysisAlarmSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		analysisAlarmSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		analysisAlarmSplitPane.setTopComponent(mainBusinessPanel);
		analysisAlarmSplitPane.setBottomComponent(relatedBusinessPanel);
		analysisAlarmSplitPane.setOneTouchExpandable(true);
		double high2 = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		// ---------------------------------
		analysisAlarmSplitPane.setDividerLocation(Double.valueOf(high2).intValue() / 10);
		tabbedPane = new JTabbedPane();
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setOneTouchExpandable(true);
		int high = Double.valueOf(Toolkit.getDefaultToolkit().getScreenSize().getHeight()).intValue() / 2;
		splitPane.setDividerLocation(high - 65);
		splitPane.setTopComponent(contentPanel);
		splitPane.setBottomComponent(tabbedPane);
		alarmTable.setTableHeaderPopupMenuFactory(null);
		alarmTable.setTableBodyPopupMenuFactory(null);
		pageSize = new int[] { 0, 10, 30, 50 };
		buttonPanel = new JPanel();
	}

	/**
	 * 按钮布局
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
		c.insets = new Insets(0, 5, 0, 5);
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
		SiteService_MB siteService = null;
		try {
			siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			if(EManufacturer.WUHAN.getValue() == siteService.getManufacturer(ConstantUtil.siteId)){
				c.gridx = 2;
				c.gridy = 0;
				c.gridheight = 1;
				c.gridwidth = 1;
				c.insets = new Insets(0, 5, 0, 5);
				c.fill = GridBagConstraints.HORIZONTAL;
				buttonLayout.setConstraints(synchroButton, c);
				buttonPanel.add(synchroButton);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}
	}

	/**
	 * 添加tabbedPane
	 */
	public void setTabbedPaneLayout() {
		setUserInfoLayout();
		tabbedPane.add(ResourceUtil.srcStr(StringKeysTab.TAB_USER_INFO), userInfoPanel);
		tabbedPane.setSelectedIndex(0);
		setAnalysisAlarmLayout();
		tabbedPane.add(ResourceUtil.srcStr(StringKeysLbl.LBL_ALARM_ALARM_ANALYSIS), analysisAlarmSplitPane);
	}

	/**
	 * 用户信息布局
	 */
	public void setUserInfoLayout() {
		GridBagLayout userInfoLayout = new GridBagLayout();
		userInfoLayout.columnWidths = new int[] { 40, 80, 40, 80, 40, 80 };
		userInfoLayout.columnWeights = new double[] { 0, 0.2, 0, 0.2, 0, 0.2 };
		userInfoLayout.rowHeights = new int[] { 40, 50, 50 };
		userInfoLayout.rowWeights = new double[] { 0, 0.1, 0.1 };
		userInfoPanel.setLayout(userInfoLayout);
		GridBagConstraints c = null;
		c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);

		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 0;
		c.fill = GridBagConstraints.BOTH;
		userInfoLayout.setConstraints(warningNameLabel, c);
		userInfoPanel.add(warningNameLabel);

		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		userInfoLayout.setConstraints(warningNameText, c);
		userInfoPanel.add(warningNameText);

		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 0;
		userInfoLayout.setConstraints(warningDescLabel, c);
		userInfoPanel.add(warningDescLabel);

		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		userInfoLayout.setConstraints(warnningDescText, c);
		userInfoPanel.add(warnningDescText);

		c.gridx = 2;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 0;
		userInfoLayout.setConstraints(effectLabel, c);
		userInfoPanel.add(effectLabel);

		c.gridx = 3;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		userInfoLayout.setConstraints(effectText, c);
		userInfoPanel.add(effectText);

		c.gridx = 4;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 0;
		userInfoLayout.setConstraints(mayreasonLabel, c);
		userInfoPanel.add(mayreasonLabel);

		c.gridx = 5;
		c.gridy = 1;
		c.gridheight = 2;
		c.gridwidth = 1;
		userInfoLayout.setConstraints(mayreasonText, c);
		userInfoPanel.add(mayreasonText);

		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 0;
		userInfoLayout.setConstraints(solutionLabel, c);
		userInfoPanel.add(solutionLabel);

		c.gridx = 1;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		userInfoLayout.setConstraints(solutionText, c);
		userInfoPanel.add(solutionText);

		c.gridx = 2;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 0;
		userInfoLayout.setConstraints(reserveLabel, c);
		userInfoPanel.add(reserveLabel);

		c.gridx = 3;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		userInfoLayout.setConstraints(reserveText, c);
		userInfoPanel.add(reserveText);

	}

	/**
	 * 布局
	 */
	public void setAnalysisAlarmLayout() {
		GridBagLayout mainBusinessLayout = new GridBagLayout();
		mainBusinessPanel.setLayout(mainBusinessLayout);
		GridBagConstraints c = null;
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 0;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0, 10, 0, 0);
		mainBusinessLayout.setConstraints(mainBusinessLabel, c);
		mainBusinessPanel.add(mainBusinessLabel);

		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 10;
		c.weightx = 0.2;
		c.weighty = 0.4;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0, 0, 0, 2);
		mainBusinessLayout.setConstraints(mainBusinessScrollPane, c);
		mainBusinessPanel.add(mainBusinessScrollPane);

		GridBagLayout relatedBusinessLayout = new GridBagLayout();
		relatedBusinessPanel.setLayout(relatedBusinessLayout);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 0;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0, 10, 0, 0);
		relatedBusinessLayout.setConstraints(relatedBusinessLabel, c);
		relatedBusinessPanel.add(relatedBusinessLabel);

		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 0.2;
		c.weighty = 0.4;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0, 0, 0, 2);
		relatedBusinessLayout.setConstraints(relatedBusinessScrollPane, c);
		relatedBusinessPanel.add(relatedBusinessScrollPane);

	}

	/**
	 * 页面布局
	 */
	public void setLayout() {
		setButtonLayout();
		setTabbedPaneLayout();
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
		c.insets = new Insets(0, 0, 2, 0);
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
		panelLayout.setConstraints(splitPane, c);
		this.add(splitPane);
	}

	public void add() {
		
	}

	public JButton getRefreshButton() {
		return refreshButton;
	}

	public void setRefreshButton(JButton refreshButton) {
		this.refreshButton = refreshButton;
	}

	public JButton getExportButton() {
		return exportButton;
	}

	public void setExportButton(JButton exportButton) {
		this.exportButton = exportButton;
	}

	public SiteCurrentAlarmController getController() {
		return controller;
	}

	public void setController(SiteCurrentAlarmController controller) {
		this.controller = controller;
	}

	/**
	 * 初始化数据
	 * @param infos
	 * 			告警列表
	 */
	public void initData(List<CurrentAlarmInfo> infos) {
		if (infos != null && infos.size() > 0) {
			for (CurrentAlarmInfo info : infos) {
				info.putClientProperty();
				box.getAlarmModel().addAlarm(info);
			}
		}

	}

	public TDataBox getBox() {
		return box;
	}

	public void setBox(TDataBox box) {
		this.box = box;
	}

}
