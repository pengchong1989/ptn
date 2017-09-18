package com.nms.ui.ptn.ne.elan.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import twaver.table.TTable;
import twaver.table.TTablePopupMenuFactory;

import com.nms.db.bean.ptn.path.eth.ElanInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnMenuItem;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysMenu;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.ptn.ne.elan.controller.ElanController;
import com.nms.ui.ptn.ne.eline.view.AcElinePanel;
import com.nms.ui.ptn.ne.eline.view.PwElinePanel;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

public class ElanPanel extends ContentView<ElanInfo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6683604589878918117L;
	private JSplitPane splitPane;
	private JTabbedPane tabbedPane;
	private JPanel infoPanel;
	private AcElinePanel acElinePanel;
	private PwElinePanel pwElinePanel;
	// 激活右键菜单
	private PtnMenuItem activeItem;
	// 去激活右键菜单
	private PtnMenuItem unActiveItem;
	
	public ElanPanel() {
		super("elanNodeTable",RootFactory.CORE_MANAGE);
		try {
			this.init();
			super.getController().refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	private void init() {
		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTab.TAB_ELANINFO)));
		initComponent();
		setLayout();
		this.setActionListention();
	}
	
	/**
	 * 添加监听
	 */
	private void setActionListention() {
		getTable().addElementClickedActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (getSelect() == null) {
					acElinePanel.clear();
					pwElinePanel.clear();
					return;
				} else {
					getController().initDetailInfo();
				}
			}
		});
		// 激活处理事件
		activeItem.addActionListener(new MyActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				((ElanController) getController()).doActive();
			}

			@Override
			public boolean checking() {
				return true;
			}
		});

		// 去激活处理事件
		unActiveItem.addActionListener(new MyActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				((ElanController) getController()).doUnActive();
			}
			
			@Override
			public boolean checking() {
				return true;
			}
		});
	}
	
	private void setLayout() {
		setInfoPanelLayout();
		setTabbedPaneLayout();
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
		panelLayout.setConstraints(splitPane, c);
		this.add(splitPane);
	}
	
	private void setTabbedPaneLayout() {
		tabbedPane.add(ResourceUtil.srcStr(StringKeysTab.TAB_BASIC_INFO), this.infoPanel);
	}
	
	private void setInfoPanelLayout() {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		GridBagLayout qosGridBagLayout = new GridBagLayout();
		qosGridBagLayout.columnWeights = new double[] { 0.5, 0.5 };
		infoPanel.setLayout(qosGridBagLayout);
		addComponent(infoPanel, acElinePanel, 0, 0, 0.5, 1.0, 1, 1, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(infoPanel, pwElinePanel, 1, 0, 0.5, 1.0, 1, 1, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), GridBagConstraints.NORTHWEST, gridBagConstraints);
	}
	
	private void initComponent() {
		tabbedPane = new JTabbedPane();
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setOneTouchExpandable(true);
		activeItem = new PtnMenuItem(ResourceUtil.srcStr(StringKeysMenu.MENU_ACTIVATION), true);
		unActiveItem = new PtnMenuItem(ResourceUtil.srcStr(StringKeysMenu.MENU_GO_ACTIVATION), true);
		int high = Double.valueOf(Toolkit.getDefaultToolkit().getScreenSize().getHeight()).intValue() / 2;
		splitPane.setDividerLocation(high - 65);
		splitPane.setTopComponent(this.getContentPanel());
		splitPane.setBottomComponent(tabbedPane);
		infoPanel=new JPanel();
		acElinePanel=new AcElinePanel();
		acElinePanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_AC_LIST)));
		pwElinePanel=new PwElinePanel();
		pwElinePanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_PW_LIST)));
	}
	
	@Override
	public void setTablePopupMenuFactory() {
		TTablePopupMenuFactory menuFactory = new TTablePopupMenuFactory() {
			@Override
			public JPopupMenu getPopupMenu(TTable ttable, MouseEvent evt) {
				if (SwingUtilities.isRightMouseButton(evt)) {
					int count = ttable.getSelectedRows().length;
					if (count > 0) {
						JPopupMenu menu = new JPopupMenu();
						menu.add(activeItem);
						menu.add(unActiveItem);
						checkRoot(activeItem, RootFactory.CORE_MANAGE);
						checkRoot(unActiveItem, RootFactory.CORE_MANAGE);
						menu.show(evt.getComponent(), evt.getX(), evt.getY());
						return menu;
					}
				}
				return null;
			}
		};
		SiteService_MB siteService = null;
		try {
			siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			if(siteService.getManufacturer(ConstantUtil.siteId) == EManufacturer.WUHAN.getValue()){
				super.setMenuFactory(menuFactory);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}
	}

	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getSearchButton());
		return needRemoveButtons;
	}

	@Override
	public Dimension setDefaultSize() {
		return new Dimension(160, ConstantUtil.INT_WIDTH_THREE);
	}

	@Override
	public void setController() {
		super.controller=new ElanController(this);
	}
	
	@Override
	public List<JButton> setAddButtons() {
		List<JButton> needAddButtons = new ArrayList<JButton>();
		needAddButtons.add(this.getFilterButton());
		needAddButtons.add(this.getClearFilterButton());
		needAddButtons.add(this.getConsistenceButton());
		return needAddButtons;
	}
	
	public AcElinePanel getAcElinePanel() {
		return acElinePanel;
	}

	public PwElinePanel getPwElinePanel() {
		return pwElinePanel;
	}


}
