package com.nms.ui.ptn.upgradeManage.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import com.nms.db.bean.equipment.manager.UpgradeManage;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysMenu;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
import com.nms.ui.ptn.upgradeManage.controller.UpgradeManageController;

public class UpgradeManagePanel extends ContentView<UpgradeManage> {

	private static final long serialVersionUID = 5868922143235615805L;
	private UpgradeManageController controller;
	private JSplitPane splitPane;
	private JPanel componentsPanel;
	private JTextArea usedVersionsTextArea;
	private JTextArea versionsInfoTextArea;
	private JScrollPane usedVersionsScrollPane;
	private JScrollPane versionsInfoScrollPane;
	private JPanel butttonPanel;
	private PtnButton upgradeButton;
	private PtnButton activateButton;
	private PtnButton synchronizationButton;
	private PtnButton removeButton;
	private PtnButton restartButton;
	private JButton reflashButton;
	private List<UpgradeManage> upgradeManages;
	private PtnButton downSoftVersion;//改为手动下载摘要
	private UpgradeManagePanel upgradeManagePanel;
	private int type = 1;
	public UpgradeManagePanel() {
		super("upgradeTable",RootFactory.CORE_MANAGE);
		init();
		upgradeManagePanel = this;
		controller = new UpgradeManageController(this);
	}

	private void init() {
		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_VERSIONS_INFO)));
		initComponents();
		setComponentsLayout();
	}

	private void initComponents() {
		upgradeButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_UPGRADE),false,RootFactory.CORE_MANAGE);
		activateButton = new PtnButton(ResourceUtil.srcStr(StringKeysMenu.MENU_ACTIVATION),false,RootFactory.CORE_MANAGE);
		synchronizationButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SYNCHRO),false,RootFactory.CORE_MANAGE);
		removeButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_DELETE),false,RootFactory.CORE_MANAGE);
		restartButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_RESTART),false,RootFactory.CORE_MANAGE);
		reflashButton = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_REFRESH));

		butttonPanel = new JPanel();

		usedVersionsTextArea = new JTextArea(10, 10);
		usedVersionsTextArea.setEditable(false);
		usedVersionsTextArea.setLineWrap(true);
		usedVersionsTextArea.setWrapStyleWord(true);
		usedVersionsTextArea.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_USABLE_VERSIONS)));
		usedVersionsScrollPane = new JScrollPane();
		usedVersionsScrollPane.setViewportView(usedVersionsTextArea);
		usedVersionsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		usedVersionsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		versionsInfoTextArea = new JTextArea(10, 10);
		versionsInfoTextArea.setEditable(false);
		versionsInfoTextArea.setLineWrap(true);
		versionsInfoTextArea.setWrapStyleWord(true);
		versionsInfoTextArea.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_VERSIONS_INFO)));

		versionsInfoScrollPane = new JScrollPane();
		versionsInfoScrollPane.setViewportView(versionsInfoTextArea);
		versionsInfoScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		versionsInfoScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		componentsPanel = new JPanel();

		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		// splitPane.setOneTouchExpandable(true);
		splitPane.setTopComponent(this.getContentPanel());
		splitPane.setBottomComponent(componentsPanel);
		int high = Double.valueOf(Toolkit.getDefaultToolkit().getScreenSize().getHeight()).intValue() / 2;
		splitPane.setDividerLocation(high - 60);

	}

	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getAddButton());
		needRemoveButtons.add(getDeleteButton());
		needRemoveButtons.add(getUpdateButton());
		needRemoveButtons.add(getRefreshButton());
		needRemoveButtons.add(getFilterButton());
		needRemoveButtons.add(getClearFilterButton());
		needRemoveButtons.add(getSearchButton());
		needRemoveButtons.add(getSynchroButton());
		return needRemoveButtons;
	}

	@Override
	public List<JButton> setAddButtons() {
		List<JButton> needButtons = new ArrayList<JButton>();
		this.upgradeButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_UPGRADE),false,RootFactory.CORE_MANAGE);
		this.downSoftVersion = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_UPGRADE_SOFTWARE),true,RootFactory.CORE_MANAGE);
		this.upgradeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if(upgradeManagePanel.getTable().getAllElement().size() ==0){
					DialogBoxUtil.succeedDialog(null,ResourceUtil.srcStr(StringKeysLbl.UPDOWN_SOFTWARE));
					return;
				}
				final UpgradeVersionsDialog dialog = new UpgradeVersionsDialog(upgradeManages,upgradeManagePanel);
				dialog.setSize(new Dimension(450, 400));
				dialog.setLocation(UiUtil.getWindowWidth(dialog.getWidth()), UiUtil.getWindowHeight(dialog.getHeight()));
//				dialog.addWindowListener(new WindowAdapter() {
//					@Override
//					public void windowClosed(WindowEvent e) {
//						dialog.dispose();
//					}
//				});
				dialog.setVisible(true);
			}
		});
		downSoftVersion.addActionListener(new MyActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.downServison();
			}

			@Override
			public boolean checking() {
				// TODO Auto-generated method stub
				return true;
			}
		});
		
		needButtons.add(downSoftVersion);
		needButtons.add(upgradeButton);
		return needButtons;
	}
	private void setButtonLayout() {
		GridBagLayout buttonLayout = new GridBagLayout();
		buttonLayout.columnWidths = new int[] { 30, 30, 30, 30, 30, 30, 30 };
		buttonLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		buttonLayout.rowHeights = new int[] { 10 };
		buttonLayout.rowWeights = new double[] { 0.0 };
		butttonPanel.setLayout(buttonLayout);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = new Insets(5, 5, 10, 5);
		buttonLayout.setConstraints(upgradeButton, c);
//		butttonPanel.add(upgradeButton);
		c.gridx = 1;
		c.insets = new Insets(5, 5, 10, 5);
		buttonLayout.setConstraints(activateButton, c);
//		butttonPanel.add(activateButton);
		c.gridx = 2;
		c.insets = new Insets(5, 5, 10, 5);
		buttonLayout.setConstraints(synchronizationButton, c);
//		butttonPanel.add(synchronizationButton);
		c.gridx = 3;
		c.insets = new Insets(5, 5, 10, 5);
		buttonLayout.setConstraints(removeButton, c);
//		butttonPanel.add(removeButton);
		c.gridx = 4;
		c.insets = new Insets(5, 5, 10, 5);
		buttonLayout.setConstraints(restartButton, c);
//		butttonPanel.add(restartButton);
		c.gridx = 5;
		c.insets = new Insets(5, 5, 10, 5);
		buttonLayout.setConstraints(reflashButton, c);
//		butttonPanel.add(reflashButton);
	}

	private void setComponentsLayout() {
		setButtonLayout();
		GridBagLayout layout = new GridBagLayout();
		componentsPanel.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.insets = new Insets(3, 3, 3, 3);
		layout.setConstraints(usedVersionsScrollPane, c);
		componentsPanel.add(usedVersionsScrollPane);
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.insets = new Insets(3, 3, 3, 3);
		layout.setConstraints(versionsInfoScrollPane, c);
		componentsPanel.add(versionsInfoScrollPane);
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.weightx = 0.0;
		c.weighty = 0.0;

		layout.setConstraints(butttonPanel, c);
		componentsPanel.add(butttonPanel);

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

	@Override
	public Dimension setDefaultSize() {
		return new Dimension(200, ConstantUtil.INT_WIDTH_THREE);
	}

	@Override
	public void setController() {

	}

	public PtnButton getUpgradeButton() {
		return upgradeButton;
	}

	public void setUpgradeButton(PtnButton upgradeButton) {
		this.upgradeButton = upgradeButton;
	}

	public PtnButton getActivateButton() {
		return activateButton;
	}

	public void setActivateButton(PtnButton activateButton) {
		this.activateButton = activateButton;
	}

	public PtnButton getSynchronizationButton() {
		return synchronizationButton;
	}

	public void setSynchronizationButton(PtnButton synchronizationButton) {
		this.synchronizationButton = synchronizationButton;
	}

	public PtnButton getRemoveButton() {
		return removeButton;
	}

	public void setRemoveButton(PtnButton removeButton) {
		this.removeButton = removeButton;
	}

	public PtnButton getRestartButton() {
		return restartButton;
	}

	public void setRestartButton(PtnButton restartButton) {
		this.restartButton = restartButton;
	}

	public JButton getReflashButton() {
		return reflashButton;
	}

	public void setReflashButton(PtnButton reflashButton) {
		this.reflashButton = reflashButton;
	}

	public List<UpgradeManage> getUpgradeManages() {
		return upgradeManages;
	}

	public void setUpgradeManages(List<UpgradeManage> upgradeManages) {
		this.upgradeManages = upgradeManages;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public PtnButton getDownSoftVersion() {
		return downSoftVersion;
	}

	public void setDownSoftVersion(PtnButton downSoftVersion) {
		this.downSoftVersion = downSoftVersion;
	}

	// public static void main(String[] args) {
	// java.awt.EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// JFrame jf = new JFrame();
	// UpgradeManagePanel dialog = new UpgradeManagePanel();
	// jf.add(dialog);
	// jf.addWindowListener(new java.awt.event.WindowAdapter() {
	// public void windowClosing(java.awt.event.WindowEvent e) {
	// System.exit(0);
	// }
	// });
	// jf.setSize(1000, 580);
	// jf.setVisible(true);
	// }
	// });
	// }

	
}
