package com.nms.ui.frame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import twaver.GeoCoordinate;
import twaver.table.TPageNavigator;
import twaver.table.TTableModel;
import twaver.table.TTablePopupMenuFactory;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.enums.EManufacturer;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.service.impl.util.SiteUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnTextField;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.safety.roleManage.RoleRoot;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

/**
 * * table列表主面板
 * 
 * @author lp
 * 
 * @param <T>
 *            列表中一条记录对象的类类型
 */
public abstract class ContentView<T extends ViewDataObj> extends JPanel {

	private static final long serialVersionUID = 9042969010414268686L;

	private JScrollPane contentScrollPane;
	private JPanel contentPanel;
	private JPanel buttonPanel;
	private PtnButton addButton;
	private PtnButton updateButton;
	private PtnButton deleteButton;
	private JButton refreshButton;
	private PtnButton synchroButton;
	private PtnButton searchButton;
	private JButton filterButton;
	private JButton clearFilterButton;
	private PtnButton exportButton;
	private PtnButton inportButton;
	private JLabel filterText;
	private ViewDataTable<T> table;
	private String tableAttrs;
	private JSeparator separator;
	private TPageNavigator navigator;
	private TTableModel tableModel;
	public AbstractController controller;
	private JMenuItem addMenu;
	private JMenuItem updateMenu;
	private JMenuItem deleteMenu;
	private JButton fiterZero;
	private PtnButton consistenceButton;
	//权限标签，验证（与DB，是否拥有此权限）
	private int rootLabel;
	int pageSize[] = null;
	private PtnButton prevPageBtn;
	private PtnButton nextPageBtn;
	private JLabel currPageLabel;
	private JLabel divideLabel;
	private JLabel totalPageLabel;
	private PtnTextField goToTextField;
	private PtnButton goToJButton;
	private JPanel flipPanel;//分页操作面板
	
	private TTablePopupMenuFactory menuFactory = null;
	/**
	 * 修改  
	 * @author		sy
	 * @param tableAttrs 
	 * @param rootLabel
	 * 				权限标签值，>0的整数
	 */
	public ContentView(String tableAttrs,int rootLabel) {
		this.tableAttrs = tableAttrs;
		this.rootLabel=rootLabel;
		init();
	}
	
	/**
	 * 修改  
	 * @author		sy
	 * @param tableAttrs 
	 * @param rootLabel
	 * 				权限标签值，>0的整数
	 */
	public ContentView(int siteId ,String wuTableAttrs,String cxTableAttrs,int rootLabel) {
		SiteService_MB siteService = null;
		try {
			siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			if(siteService.getManufacturer(siteId) == EManufacturer.WUHAN.getValue()){
				this.tableAttrs = wuTableAttrs;
			}else{
				this.tableAttrs = cxTableAttrs;
			}
			this.rootLabel=rootLabel;
			init();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}
	}

	private void init() {
		initComponents();
		if (table.getLayoutType().equals("default")) {
			// 默认的菜单栏包括：新建、修改、删除、刷新
			setDefaultLayout();
		} else if (table.getLayoutType().equals("filter")) {
			// 有过滤条件的菜单栏，包括：新建、修改、删除、刷新、设置过滤、清除过滤
			setFilterLayout();
			setFlipLayout();
		} else if (table.getLayoutType().equals("statistics")) {
			setStatisticsLayout();
		}
		setController();
		setActinoListention();
	}

	private void setActinoListention() {

		// 刷新按钮事件
		refreshButton.addActionListener(new MyActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					controller.refresh();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}

			@Override
			public boolean checking() {
				// TODO Auto-generated method stub
				return true;
			}
		});

		// 新建按钮事件
		addButton.addActionListener(new MyActionListener() {

			@Override
			public boolean checking() {
				return true;
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controller.openCreateDialog();
				} catch (Exception e1) {
					ExceptionManage.dispose(e1, ContentView.class);
				}
				
			}
		});

		// 修改按钮事件
		updateButton.addActionListener(new MyActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (getTable().getAllSelect().size() == 0 || getTable().getAllSelect().size() > 1) {
						DialogBoxUtil.errorDialog(contentPanel, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
						return;
					}
					controller.openUpdateDialog();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}

			@Override
			public boolean checking() {
				// TODO Auto-generated method stub
				return true;
			}
		});

		// 删除按钮事件
		deleteButton.addActionListener(new MyActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					controller.delete();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}

			@Override
			public boolean checking() {
				boolean resultFlag = false;
				try {
					if (getTable().getAllSelect().size() == 0) {
						DialogBoxUtil.errorDialog(contentPanel, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
					} else {
						int result = DialogBoxUtil.confirmDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_IS_DELETE));
						if (result == 0) {
							resultFlag = controller.deleteChecking();
						}
					}
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
				return resultFlag;
			}
		});

		// 同步按钮事件
		synchroButton.addActionListener(new MyActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					controller.synchro();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}

			@Override
			public boolean checking() {
				try {
					SiteUtil siteUtil = new SiteUtil();
					if(siteUtil.SiteTypeUtil(ConstantUtil.siteId) != 0){
						DialogBoxUtil.errorDialog(contentPanel, ResourceUtil.srcStr(StringKeysTip.TIP_UP_DOWN));
						return false;
					}
				} catch (Exception e) {
					ExceptionManage.dispose(e, this.getClass());
				}
				return true;
			}
		});

		// 搜索按钮事件
		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					controller.search();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		});

		// 导出按钮事件
		exportButton.addActionListener(new MyActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					controller.export();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}

			@Override
			public boolean checking() {
				
				return true;
			}
		});

		// 导入按钮事件
		inportButton.addActionListener(new MyActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					controller.inport();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
			@Override
			public boolean checking() {
				// TODO Auto-generated method stub
				return false;
			}
		});
		
		//零值过滤按钮事件
		fiterZero.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					controller.filterZero();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		});

		// 设置过滤条件
		filterButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					controller.openFilterDialog();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		});

		// 清除过滤条件
		clearFilterButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					controller.clearFilter();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		});

		addMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					controller.openCreateDialog();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		});

		updateMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					controller.openUpdateDialog();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		});

		deleteMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					controller.delete();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		});
		setTablePopupMenuFactory();
		getTable().setTableBodyPopupMenuFactory(menuFactory);
		
		consistenceButton.addActionListener(new MyActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controller.consistence();
				} catch (Exception e1) {
					ExceptionManage.dispose(e1, this.getClass());
				}
			}
			
			@Override
			public boolean checking() {
				SiteService_MB siteService = null;
				try {
					siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
					SiteInst siteInst = siteService.select(ConstantUtil.siteId);
					if(siteInst.getLoginstatus() == 0){
						DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_ONLY_ONLINE));
						return false;
					}
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}finally{
					UiUtil.closeService_MB(siteService);
				}
				return true;
			}
		});
		
		this.prevPageBtn.addActionListener(new MyActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controller.prevPage();
				} catch (Exception e1) {
					ExceptionManage.dispose(e1, this.getClass());
				}
			}
			
			@Override
			public boolean checking() {
				return true;
			}
		});
		
		this.nextPageBtn.addActionListener(new MyActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					controller.nextPage();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
			@Override
			public boolean checking() {
				return true;
			}
		});
		
		this.goToJButton.addActionListener(new MyActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controller.goToAction();
				} catch (Exception e1) {
					ExceptionManage.dispose(e1,this.getClass());
				}
			}
			
			@Override
			public boolean checking() {
				return true;
			}
		});
	}

	/**
	 * 默认右键选项为menuFactory，若需要设置自己的右键菜单，则重写此方法
	 */
	public void setTablePopupMenuFactory() {
	}

	/**
	 * 设置面板的大小，若需要修改大小，则重写此方法
	 * 
	 * @return
	 */
	public Dimension setDefaultSize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenSize.height = 220;
		return screenSize;
	}

	/**
	 * 每一个view 都对应一个相应的controller
	 */
	public abstract void setController();

	public AbstractController getController() {
		return controller;
	}

	private void initComponents() {
		contentScrollPane = new JScrollPane();
		contentScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contentScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPanel = new JPanel();
		buttonPanel = new JPanel();
		/**修改  按钮实例化对象
		 * @author sy
		 *  添加标签值
		 * @param  label
		 */	
		addButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CREATE),false,this.rootLabel);
		updateButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_UPDATE),false,this.rootLabel);
		deleteButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_DELETE),this.rootLabel);
		refreshButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_REFRESH));
		synchroButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SYNCHRO),true,this.rootLabel);
		searchButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SEARCH),false,this.rootLabel);
		exportButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT),false);
		inportButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_INPORT),false,this.rootLabel);
		fiterZero=new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_FITER_ZERO)); 
		consistenceButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONSISTENCE), false);
		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setVisible(true);
		filterButton = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_FILTER));
		filterText = new JLabel("过滤条件=请选择过滤条件！");
		clearFilterButton = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_FILTER_CLEAR));
		this.prevPageBtn = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_PREV_PAGE), true, this.rootLabel);
		this.nextPageBtn = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_NEXT_PAGE),true, this.rootLabel);
		this.currPageLabel = new JLabel("1");
		this.divideLabel = new JLabel("/");
		this.totalPageLabel = new JLabel("1");
		goToTextField = new PtnTextField();
		goToJButton = new PtnButton("GO",true, this.rootLabel);
		table = new ViewDataTable<T>(tableAttrs);
		table.getTableHeader().setResizingAllowed(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table.setPreferredScrollableViewportSize(setDefaultSize());
		tableModel = table.getTableModel();
		pageSize = new int[] { 0, 10, 30, 50 };
		// navigator = new TPageNavigator(tableModel, pageSize);
		tableModel.setCurrentPageIndex(1);
		contentScrollPane.setViewportView(table);
		table.setTableHeaderPopupMenuFactory(null);
		table.setTableBodyPopupMenuFactory(null);
		addMenu = new JMenuItem(ResourceUtil.srcStr(StringKeysBtn.BTN_CREATE));
		updateMenu = new JMenuItem(ResourceUtil.srcStr(StringKeysBtn.BTN_UPDATE));
		deleteMenu = new JMenuItem(ResourceUtil.srcStr(StringKeysBtn.BTN_DELETE));
		flipPanel = new JPanel();
	}

	private void removeButton(List<JComponent> defaultButtons) {
		List<JButton> needRemoveButtons = setNeedRemoveButtons();
//		if (needRemoveButtons != null) 
//		{
//			needRemoveButtons.add(this.searchButton);
//		}
		if (needRemoveButtons != null) {
			for (JButton button : needRemoveButtons) {
				if (defaultButtons.contains(button)) {
					defaultButtons.remove(button);
				}
			}
		}
	}

	private void addButton(List<JComponent> defaultButtons) {
		List<JButton> needRemoveButtons = setAddButtons();
		if (needRemoveButtons != null) {
			for (JButton button : needRemoveButtons) {
				defaultButtons.add(button);
			}
		}
	}

	/**
	 * 若需要移除的按钮(增、删、改、查、设置过滤、清除过滤)，则重写此方法
	 * 
	 * @return 要移除按钮的集合
	 */
	public List<JButton> setNeedRemoveButtons() {
		return null;
	}

	public List<JButton> setAddButtons() {
		return null;
	}

	// 默认所有增、删、改、查
	private void setDefaultLayout() {
		List<JComponent> defaultButtons = new ArrayList<JComponent>();
		defaultButtons.add(addButton);
		defaultButtons.add(updateButton);
		defaultButtons.add(deleteButton);
		defaultButtons.add(refreshButton);
		defaultButtons.add(synchroButton);
		defaultButtons.add(searchButton);
		removeButton(defaultButtons);
		addButton(defaultButtons);
		FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT, 10, 5);
		buttonPanel.setLayout(flowLayout);
		for (int i = 0; i < defaultButtons.size(); i++) {
			buttonPanel.add(defaultButtons.get(i));
		}
		setViewLayout();
	}

	// // 增、删、改、查和过滤条件的按钮菜单布局
	private void setFilterLayout() {
		List<JComponent> defaultButtons = new ArrayList<JComponent>();
		defaultButtons.add(addButton);
		defaultButtons.add(updateButton);
		defaultButtons.add(deleteButton);
		defaultButtons.add(refreshButton);
		defaultButtons.add(filterButton);
		defaultButtons.add(clearFilterButton);
		defaultButtons.add(exportButton);
		defaultButtons.add(inportButton);
		defaultButtons.add(fiterZero);
		removeButton(defaultButtons);
		addButton(defaultButtons);
		FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT, 20, 5);
		buttonPanel.setLayout(flowLayout);
		for (int i = 0; i < defaultButtons.size(); i++) {
			buttonPanel.add(defaultButtons.get(i));
		}
		setViewLayout();
	}

	// // 增、删、改、查和过滤条件的按钮菜单布局
	private void setStatisticsLayout() {
		List<JComponent> defaultButtons = new ArrayList<JComponent>();
		defaultButtons.add(refreshButton);
		defaultButtons.add(exportButton);
		removeButton(defaultButtons);
		addButton(defaultButtons);
		FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT, 10, 5);
		buttonPanel.setLayout(flowLayout);
		for (int i = 0; i < defaultButtons.size(); i++) {
			buttonPanel.add(defaultButtons.get(i));
		}
		setViewLayout();
	}

	private void setFlipLayout() {
		GridBagLayout buttonLayout = new GridBagLayout();
		buttonLayout.columnWidths = new int[] { 5, 5, 5, 5,5,5, 5,5,5};
		buttonLayout.columnWeights = new double[] { 0,0, 0,0, 0, 0,0 };
		buttonLayout.rowHeights = new int[] { 10 };
		buttonLayout.rowWeights = new double[] { 0 };
		GridBagConstraints c = null;
		c = new GridBagConstraints();
		flipPanel.setLayout(buttonLayout);
		// 操作菜单按钮布局
		c.gridy = 0;
		c.gridx = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		buttonLayout.setConstraints(prevPageBtn, c);
		flipPanel.add(prevPageBtn);
		
		c.gridx = 1;
		buttonLayout.setConstraints(currPageLabel, c);
		flipPanel.add(currPageLabel);
		
		c.gridx = 2;
		buttonLayout.setConstraints(divideLabel, c);
		flipPanel.add(divideLabel);
		
		c.gridx = 3;
		buttonLayout.setConstraints(totalPageLabel, c);
		flipPanel.add(totalPageLabel);
		
		c.gridx = 4;
		buttonLayout.setConstraints(nextPageBtn, c);
		flipPanel.add(nextPageBtn);
		
		c.gridx = 5;
		c.gridwidth = 1;
		c.ipadx = 45;
		buttonLayout.setConstraints(goToTextField, c);
		flipPanel.add(goToTextField);
		 
		c.gridx = 8;
		c.gridwidth = 1;
		c.ipadx = 1;
		buttonLayout.setConstraints(goToJButton, c);
		flipPanel.add(goToJButton);
		buttonPanel.add(flipPanel);
	}
	
	// 整个面板的布局
	private void setViewLayout() {
		GridBagConstraints c;
		GridBagLayout contentLayout = new GridBagLayout();
		contentPanel.setLayout(contentLayout);
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
	}

	public void addComponent(JPanel panel, JComponent component, int gridx, int gridy, double weightx, double weighty, int gridwidth, int gridheight, int fill, Insets insets, int anchor, GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.weightx = weightx;
		gridBagConstraints.weighty = weighty;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.fill = fill;
		gridBagConstraints.insets = insets;
		gridBagConstraints.anchor = anchor;
		panel.add(component, gridBagConstraints);
	}

	public void setDimension(Dimension dimension) {
		table.setPreferredScrollableViewportSize(dimension);
		this.updateUI();
	}

	public ViewDataTable<T> getTable() {
		return table;
	}

	public TPageNavigator getNavigator() {
		return navigator;
	}

	public void setNavigator(TPageNavigator navigator) {
		this.navigator = navigator;
	}

	public PtnButton getAddButton() {
		return addButton;
	}

	public void setAddButton(PtnButton addButton) {
		this.addButton = addButton;
	}

	public PtnButton getUpdateButton() {
		return updateButton;
	}

	public void setUpdateButton(PtnButton updateButton) {
		this.updateButton = updateButton;
	}

	public PtnButton getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(PtnButton deleteButton) {
		this.deleteButton = deleteButton;
	}

	public JButton getRefreshButton() {
		return refreshButton;
	}

	public void setRefreshButton(JButton refreshButton) {
		this.refreshButton = refreshButton;
	}

	public int[] getPageSize() {
		return pageSize;
	}

	public void setPageSize(int[] pageSize) {
		this.pageSize = pageSize;
	}

	public JButton getFilterButton() {
		return filterButton;
	}

	public void setFilterButton(JButton filterButton) {
		this.filterButton = filterButton;
	}

	public JLabel getFilterText() {
		return filterText;
	}

	public void setFilterText(JLabel filterText) {
		this.filterText = filterText;
	}

	public JButton getClearFilterButton() {
		return clearFilterButton;
	}

	public void setClearFilterButton(JButton clearFilterButton) {
		this.clearFilterButton = clearFilterButton;
	}

	public PtnButton getSynchroButton() {
		return synchroButton;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public JButton getFiterZero() {
		return fiterZero;
	}

	public void setFiterZero(JButton fiterZero) {
		this.fiterZero = fiterZero;
	}

	/*
	 * 显示过滤条件
	 */
	public void setFilterInfo(String str) {
		if (table.getLayoutType().equals("default") && str.length() > 72) {
			str = str.substring(0, 72);
			str = str + "...";
		} else if (table.getLayoutType().equals("filter") && str.length() > 175) {
			str = str.substring(0, 105);
			str = str + "...";
		}
		this.filterText.setText(str);
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public void setContentPanel(JPanel contentPanel) {
		this.contentPanel = contentPanel;
	}

	public void setMenuFactory(TTablePopupMenuFactory menuFactory) {
		this.menuFactory = menuFactory;
	}

	public T getSelect() {
		return getTable().getSelect();
	}

	public void clear() {
		getTable().clear();
	}

	public List<T> getAllSelect() {
		return getTable().getAllSelect();
	}

	public void initData(List<T> objList) {
		getTable().initData(objList);
	}

	public void add(T obj) {
		getTable().add(obj);
	}

	public JMenuItem getAddMenu() {
		return addMenu;
	}

	public PtnButton getExportButton() {
		return exportButton;
	}

	public JButton getInportButton() {
		return inportButton;
	}

	public JMenuItem getUpdateMenu() {
		return updateMenu;
	}

	public JMenuItem getDeleteMenu() {
		return deleteMenu;
	}
	/** -sy
	 * 权限验证  （菜单 ）   根据菜单 的不同形式（按钮，菜单）  ，比对：传人权限标识是否  在用户的权限内
	 * @param menuItem    菜单条   
	 * @param label		权限标签 		
	 */
	public boolean checkRoot(Object object,int label){
		RoleRoot roleRoot =new RoleRoot();
		return roleRoot.setItemEnbale(object, label);
	}
	/**
	 * 权限验证  : 比对 传人参数 （权限标签）是否  存在于      登陆用户的权限之中
	 * @param label  标签 与 此角色的   可操作权限集合 验证
	 */
	public boolean checkRoot(int label){
		RoleRoot roleRoot =new RoleRoot();
		return roleRoot.root( label);
	}

	public PtnButton getConsistenceButton() {
		return consistenceButton;
	}

	public void setConsistenceButton(PtnButton consistenceButton) {
		this.consistenceButton = consistenceButton;
	}

	public PtnButton getPrevPageBtn() {
		return prevPageBtn;
	}

	public void setPrevPageBtn(PtnButton prevPageBtn) {
		this.prevPageBtn = prevPageBtn;
	}

	public PtnButton getNextPageBtn() {
		return nextPageBtn;
	}

	public void setNextPageBtn(PtnButton nextPageBtn) {
		this.nextPageBtn = nextPageBtn;
	}

	public JLabel getCurrPageLabel() {
		return currPageLabel;
	}

	public void setCurrPageLabel(JLabel currPageLabel) {
		this.currPageLabel = currPageLabel;
	}

	public JLabel getTotalPageLabel() {
		return totalPageLabel;
	}

	public void setTotalPageLabel(JLabel totalPageLabel) {
		this.totalPageLabel = totalPageLabel;
	}

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	public void setButtonPanel(JPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}

	public JLabel getDivideLabel() {
		return divideLabel;
	}

	public void setDivideLabel(JLabel divideLabel) {
		this.divideLabel = divideLabel;
	}


	public PtnTextField getGoToTextField() {
		return goToTextField;
	}

	public void setGoToTextField(PtnTextField goToTextField) {
		this.goToTextField = goToTextField;
	}

	public PtnButton getGoToJButton() {
		return goToJButton;
	}

	public void setGoToJButton(PtnButton goToJButton) {
		this.goToJButton = goToJButton;
	}

	
	
}
