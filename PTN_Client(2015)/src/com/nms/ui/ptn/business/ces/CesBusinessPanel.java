package com.nms.ui.ptn.business.ces;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import twaver.table.TTable;
import twaver.table.TTablePopupMenuFactory;

import com.nms.db.bean.ptn.path.ces.CesInfo;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnMenuItem;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysMenu;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.ptn.business.dialog.cespath.AddCESAllDialog;
import com.nms.ui.ptn.business.dialog.cespath.AddCesDialog;
import com.nms.ui.ptn.business.pw.PwNetworkTablePanel;
import com.nms.ui.ptn.business.topo.TopoPanel;
import com.nms.ui.ptn.client.view.ClientInfoPanel;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
import com.nms.ui.topology.Schematize;

/**
 * 网络侧CES 界面
 * @author lepan
 */
public class CesBusinessPanel extends ContentView<CesInfo> {
	private static final long serialVersionUID = -5153949231603674993L;

	private JSplitPane splitPane;
	private JTabbedPane tabbedPane;
	//激活右键菜单
	private PtnMenuItem activeItem;
	//去激活右键菜单
	private PtnMenuItem unActiveItem;
	private TopoPanel topoPanel;
	private CesPortNetworkTablePanel cesPortNetworkTablePanel;
	private Schematize schematize_panel = null;	//图形化显示界面
	private ClientInfoPanel clientInfoPanel;
	private AddCesDialog dialog;
	private PwNetworkTablePanel pwNetworkTablePanel;
	
	public CesBusinessPanel() {
		super("cesBusinessTable",RootFactory.CORE_MANAGE);
		init();
	}

	public void init() {
		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTab.TAB_CESINFO)));
		initComponent();
		setLayout();
		setActionListention();
	}

	private void setActionListention() {
		getTable().addElementClickedActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (getSelect() == null) {
					//清除详细面板数据
					topoPanel.clear();
					cesPortNetworkTablePanel.clear();
					schematize_panel.clear();
					clientInfoPanel.clear();
					pwNetworkTablePanel.clear();
					return;
				} else {
					getController().initDetailInfo();
				}
			}
		});

		//激活/去激活处理事件
		activeItem.addActionListener(new MyActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				((CesBusinessController) getController()).doActive();
			}

			@Override
			public boolean checking() {
				return true;
			}
		});

		unActiveItem.addActionListener(new MyActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				((CesBusinessController) getController()).doUnActive();
			}
			
			@Override
			public boolean checking() {
				return true;
			}
		});
	}

	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getSynchroButton());
		needRemoveButtons.add(getInportButton());
		needRemoveButtons.add(getExportButton());
		needRemoveButtons.add(getFiterZero());
		return needRemoveButtons;
	}
	
	private void initComponent() {
		cesPortNetworkTablePanel=new CesPortNetworkTablePanel();
		tabbedPane = new JTabbedPane();
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setOneTouchExpandable(true);
		int high = Double.valueOf(Toolkit.getDefaultToolkit().getScreenSize().getHeight()).intValue() / 2;
		splitPane.setDividerLocation(high - 65);
		splitPane.setTopComponent(this.getContentPanel());
		splitPane.setBottomComponent(tabbedPane);
		activeItem = new PtnMenuItem(ResourceUtil.srcStr(StringKeysMenu.MENU_ACTIVATION), true);
		unActiveItem = new PtnMenuItem(ResourceUtil.srcStr(StringKeysMenu.MENU_GO_ACTIVATION), true);
		topoPanel = new TopoPanel();
		schematize_panel=new Schematize();
		clientInfoPanel = new ClientInfoPanel();
		pwNetworkTablePanel = new PwNetworkTablePanel();
	}

	public void setTabbedPaneLayout() {
		tabbedPane.add(ResourceUtil.srcStr(StringKeysPanel.PANEL_TOPO_SHOW), topoPanel);
		tabbedPane.add(ResourceUtil.srcStr(StringKeysPanel.PANEL_PWSTATUS), pwNetworkTablePanel);
		tabbedPane.add(ResourceUtil.srcStr(StringKeysTab.TAB_PORT_INFO), cesPortNetworkTablePanel);
		tabbedPane.add(ResourceUtil.srcStr(StringKeysPanel.PANEL_SCHEMATIZE), this.schematize_panel);
		tabbedPane.add(ResourceUtil.srcStr(StringKeysTab.TAB_CLIENTINFO), this.clientInfoPanel);
	}

	public void setLayout() {
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
	@Override
	public void setTablePopupMenuFactory() {
		TTablePopupMenuFactory menuFactory = new TTablePopupMenuFactory() {
			@Override
			public JPopupMenu getPopupMenu(TTable table, MouseEvent evt) {
				if (SwingUtilities.isRightMouseButton(evt)) {
					int count = table.getSelectedRows().length;
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
		super.setMenuFactory(menuFactory);
	}
	
	@Override
	public List<JButton> setAddButtons() {
		List<JButton> addBtnList = new ArrayList<JButton>();
		addBtnList.add(super.getSearchButton());
		JButton btnAddAll = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_ADD_RAPID),RootFactory.CORE_MANAGE);
		btnAddAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AddCESAllDialog();
				try {
					controller.refresh();
				} catch (Exception e1) {
					ExceptionManage.dispose(e1, this.getClass());
				}
			}

		});
		addBtnList.add(btnAddAll);
		return addBtnList;
	}

	@Override
	public void setController() {
		controller = new CesBusinessController(this);
	}

	public TopoPanel getTopoPanel() {
		return topoPanel;
	}

	public void setTopoPanel(TopoPanel topoPanel) {
		this.topoPanel = topoPanel;
	}

	public CesPortNetworkTablePanel getCesPortNetworkTablePanel() {
		return cesPortNetworkTablePanel;
	}
	public Schematize getSchematize_panel() {
		return schematize_panel;
	}

	public ClientInfoPanel getClientInfoPanel() {
		return clientInfoPanel;
	}public AddCesDialog getDialog() {
		return dialog;
	}

	public void setDialog(AddCesDialog dialog) {
		this.dialog = dialog;
	}

	public PwNetworkTablePanel getPwNetworkTablePanel() {
		return pwNetworkTablePanel;
	}
}
