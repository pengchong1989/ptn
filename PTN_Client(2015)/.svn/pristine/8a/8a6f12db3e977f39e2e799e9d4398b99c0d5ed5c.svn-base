package com.nms.ui.ptn.business.pw;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import twaver.table.TTable;
import twaver.table.TTablePopupMenuFactory;

import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.path.pw.MsPwInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EOperationLogType;
import com.nms.db.enums.EServiceType;
import com.nms.model.ptn.path.pw.MsPwInfoService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.frame.ContentView;
import com.nms.ui.frame.ViewDataTable;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnMenuItem;
import com.nms.ui.manager.keys.StringKeysMenu;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.business.testoam.TestOamBusinessController;
import com.nms.ui.ptn.business.topo.TopoPanel;
import com.nms.ui.ptn.business.tunnel.LspNetworkTablePanel;
import com.nms.ui.ptn.ne.pwnni.PwVlanMainDialog;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
import com.nms.ui.ptn.systemconfig.dialog.qos.controller.QosConfigController;
import com.nms.ui.topology.Schematize;
/**
 * 网络层 pw 的 VLAN配置界面
 * @author lepan
 */
public class PwBusinessPanel extends ContentView<PwInfo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5464726456543388260L;
	private JSplitPane splitPane;
	private JTabbedPane tabbedPane;
	private PwQosQueuePanel qosPanel;
	private JScrollPane oamScrollPane;
	private ViewDataTable<OamInfo> oamTable;
	private TopoPanel topoPanel;
	//private static PwBusinessPanel pwBusinessPanel;
	private Schematize schematize_panel = null;	//图形化显示界面
	private JMenuItem miUpdateTestOam;//右键修改按需oam
	private JMenuItem miDeleteTestOam;//右键删除按需oam
	private PwXcTablePanel pwXcTablePanel; 	
	private PtnMenuItem activateMenu;//激活
	private PtnMenuItem unActivateMenu;//去激活
	private LspNetworkTablePanel lspNetworkTablePanel;//lsp信息
	public PwBusinessPanel() {
		super("pwBusinessTable",RootFactory.CORE_MANAGE);
		init();
		//pwBusinessPanel = this;
	}

	/*public static PwBusinessPanel getPwBusinessPanel() {
		return pwBusinessPanel;
	}*/

	public void init() {
		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTab.TAB_PWINFO)));
		initComponent();
		setLayout();
		setActionListention();
		try {
			getController().refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void setActionListention() {
		getTable().addElementClickedActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (getSelect() == null) {
					// 清除详细面板数据
					oamTable.clear();
					qosPanel.clear();
					topoPanel.clear();
					lspNetworkTablePanel.clear();
					schematize_panel.clear();
					return;
				} else {
					getController().initDetailInfo();
				}
			}
		});
		miUpdateQos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				PwInfo pwinfo = null;
				QosConfigController qoscontroller = null;
				try {
					pwinfo = selectPwInfo();
					qoscontroller = new QosConfigController();
					qoscontroller.openQosConfig(qoscontroller, "PW", pwinfo, pwinfo.getType(),PwBusinessPanel.this);

				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				} finally {
					pwinfo = null;
					qoscontroller = null;
				}
			}

		});
		
		//新建或修改按需oam
		miUpdateTestOam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				PwInfo pw = null;
				TestOamBusinessController oamcontroller = null;
				try {
					pw = selectPwInfo();
					if(pw != null && pw.getOamList().size() > 1){
						oamcontroller = new TestOamBusinessController();
						oamcontroller.openTestOamConfig(EServiceType.PW.toString(), pw);
					}else{
						DialogBoxUtil.succeedDialog(PwBusinessPanel.this, ResourceUtil.srcStr(StringKeysTip.TIP_OAM_CONFIG));
						return;
					}
					
					controller.refresh();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				} finally {
					pw = null;
					oamcontroller = null;
				}
			}

		});
		
		//删除按需oam
		miDeleteTestOam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				PwInfo pw = null;
				TestOamBusinessController oamcontroller = null;
				try {
					pw = selectPwInfo();
					if(pw != null){
						oamcontroller = new TestOamBusinessController();
						String message = oamcontroller.deleteTestOamConfig(EServiceType.PW.toString(), pw);
						DialogBoxUtil.succeedDialog(PwBusinessPanel.this, message);
						AddOperateLog.insertOperLog(null, EOperationLogType.TESTOAMPWDELETE.getValue(), message,
								null, null, pw.getASiteId(), pw.getPwName(), null);
						AddOperateLog.insertOperLog(null, EOperationLogType.TESTOAMPWDELETE.getValue(), message,
								null, null, pw.getZSiteId(), pw.getPwName(), null);
					}
					
					controller.refresh();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				} finally {
					pw = null;
					oamcontroller = null;
				}
			}

		});
		
		//激活按钮
		activateMenu.addActionListener(new MyActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					operateActive(selectPwList(), EActiveStatus.ACTIVITY.getValue(), EOperationLogType.PWACTIVE.getValue());
				} catch (Exception e) {
					ExceptionManage.dispose(e, PwBusinessPanel.this.getClass());
				}
			}
			
			@Override
			public boolean checking() {
				return true;
			}
		});
		//去激活按钮
		unActivateMenu.addActionListener(new MyActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					operateActive(selectPwList(), EActiveStatus.UNACTIVITY.getValue(), EOperationLogType.PWUNACTIVE.getValue());
				} catch (Exception e) {
					ExceptionManage.dispose(e, PwBusinessPanel.this.getClass());
				}
			}

			@Override
			public boolean checking() {
				return true;
			}
		});
	}
	
	private PwInfo selectPwInfo(){
		PwInfoService_MB pwInfoService = null;
		PwInfo pwinfo = this.getSelect();
		MsPwInfoService_MB msPwInfoService = null;
		try {
			pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			msPwInfoService = (MsPwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.MSPWSERVICE);
			pwinfo = pwInfoService.selectByPwId(pwinfo.getPwId());
			MsPwInfo msPw = new MsPwInfo();
			msPw.setPwId(pwinfo.getPwId());
			pwinfo.setMsPwInfos(msPwInfoService.select(msPw));
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(pwInfoService);
			UiUtil.closeService_MB(msPwInfoService);
		}
		return pwinfo;
	}
	
	private List<PwInfo> selectPwList(){
		PwInfoService_MB pwInfoService = null;
		List<PwInfo> pwinfoList = this.getAllSelect();
		MsPwInfoService_MB msPwInfoService = null;
		try {
			if(pwinfoList != null){
				List<Integer> pwIdList = new ArrayList<Integer>();
				for (PwInfo pw : pwinfoList) {
					pwIdList.add(pw.getPwId());
				}
				pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
				pwinfoList = pwInfoService.findPwByPWIds(pwIdList);
				msPwInfoService = (MsPwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.MSPWSERVICE);
				for (PwInfo pw : pwinfoList) {
					if(pw.getTunnelId() == 0){
						MsPwInfo msPw = new MsPwInfo();
						msPw.setPwId(pw.getPwId());
						pw.setMsPwInfos(msPwInfoService.select(msPw));
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(pwInfoService);
			UiUtil.closeService_MB(msPwInfoService);
		}
		return pwinfoList;
	}

	/**
	 * 激活/去激活
	 * @throws Exception 
	 * @throws RemoteException 
	 */
	private void operateActive(List<PwInfo> pwList, int statusValue, int logValue) throws RemoteException, Exception {
		int failCount = 0;
		String result = null;
		if(pwList != null && !pwList.isEmpty()){
			DispatchUtil dispatchUtil = new DispatchUtil(RmiKeys.RMI_PW);
			for (PwInfo pwInfo : pwList) {
				pwInfo.setPwStatus(statusValue);
				result = dispatchUtil.excuteUpdate(pwInfo);
				if(result == null || !result.contains(ResultString.CONFIG_SUCCESS)){
					failCount++;
				}
				//添加日志记录*************************/
				AddOperateLog.insertOperLog(null, logValue, result, null, null, -1, pwInfo.getPwName(), null);
				//************************************/
			}
			result = ResourceUtil.srcStr(StringKeysTip.TIP_BATCH_CREATE_RESULT);
			result = result.replace("{C}", (pwList.size()-failCount) + "");
			result = result.replace("{S}", failCount + "");
		}
		String offLineStr = this.getNotOnlineSiteIdNames(pwList);
		if(!offLineStr.equals("")){
			result += ","+offLineStr+ResultString.NOT_ONLINE_SUCCESS;
		}
		DialogBoxUtil.succeedDialog(this, result);
		this.controller.refresh();
	}
	
	private String getNotOnlineSiteIdNames(List<PwInfo> pwInfoActivate) throws Exception {
		List<Integer> siteIds = new ArrayList<Integer>();
		String str = "";
		try {
			for (PwInfo pw : pwInfoActivate) {
				if (!siteIds.contains(pw.getASiteId()) && pw.getASiteId() > 0) {
					siteIds.add(pw.getASiteId());
				}
				if (!siteIds.contains(pw.getZSiteId()) && pw.getZSiteId() > 0)
				{
					siteIds.add(pw.getZSiteId());
				}
				if (pw.getMsPwInfos() != null && pw.getMsPwInfos().size() > 0) {
					for (MsPwInfo msPwInfo : pw.getMsPwInfos()) {
						if (!siteIds.contains(msPwInfo.getSiteId())) {
							siteIds.add(msPwInfo.getSiteId());
						}
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
		WhImplUtil whImplUtil = new WhImplUtil();
		str = whImplUtil.getNeNames(siteIds);
		return str;
	}

	private void initComponent() {
		tabbedPane = new JTabbedPane();
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setOneTouchExpandable(true);
		int high = Double.valueOf(Toolkit.getDefaultToolkit().getScreenSize().getHeight()).intValue() / 2;
		splitPane.setDividerLocation(high - 65);
		splitPane.setTopComponent(this.getContentPanel());
		splitPane.setBottomComponent(tabbedPane);
		lspNetworkTablePanel=new LspNetworkTablePanel();
		qosPanel = new PwQosQueuePanel();
		oamTable = new ViewDataTable<OamInfo>("pwBusinessOAMTable");
		oamTable.getTableHeader().setResizingAllowed(true);
		oamTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		oamTable.setTableHeaderPopupMenuFactory(null);
		oamTable.setTableBodyPopupMenuFactory(null);
		oamScrollPane = new JScrollPane();
		oamScrollPane.setViewportView(oamTable);
		oamScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		oamScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		topoPanel = new TopoPanel();
		miUpdateQos = new JMenuItem(ResourceUtil.srcStr(StringKeysMenu.MENU_QOS_UPDATE));
		schematize_panel=new Schematize();
		miUpdateTestOam = new JMenuItem(ResourceUtil.srcStr(StringKeysMenu.MENU_TEST_OAM_CONFIG));
		miDeleteTestOam = new JMenuItem(ResourceUtil.srcStr(StringKeysMenu.MENU_TEST_OAM_DELETE));
		activateMenu = new PtnMenuItem(ResourceUtil.srcStr(StringKeysMenu.MENU_ACTIVATION), true);
		unActivateMenu = new PtnMenuItem(ResourceUtil.srcStr(StringKeysMenu.MENU_GO_ACTIVATION), true);
		pwXcTablePanel = new PwXcTablePanel();
	}

	public void setTabbedPaneLayout() {
		tabbedPane.add(ResourceUtil.srcStr(StringKeysPanel.PANEL_TOPO_SHOW), topoPanel);
		tabbedPane.add(ResourceUtil.srcStr(StringKeysTab.TAB_ROUTE_INFO), lspNetworkTablePanel);
		tabbedPane.add(ResourceUtil.srcStr(StringKeysTab.TAB_QOS_INFO), qosPanel);
		tabbedPane.add(ResourceUtil.srcStr(StringKeysTab.TAB_OAM_INFO), oamScrollPane);
		tabbedPane.add(ResourceUtil.srcStr(StringKeysTab.TAB_PW_XC), pwXcTablePanel);
		tabbedPane.add(ResourceUtil.srcStr(StringKeysPanel.PANEL_SCHEMATIZE), this.schematize_panel);
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
			public JPopupMenu getPopupMenu(TTable ttable, MouseEvent evt) {
				if (SwingUtilities.isRightMouseButton(evt)) {
					int count = ttable.getSelectedRows().length;
					if (count > 0) {
						JPopupMenu menu = new JPopupMenu();
						menu.add(activateMenu);
						menu.add(unActivateMenu);
						checkRoot(activateMenu, RootFactory.CORE_MANAGE);
						checkRoot(unActivateMenu, RootFactory.CORE_MANAGE);
						if(count == 1){
							menu.add(miUpdateQos);
							menu.add(miUpdateTestOam);
							menu.add(miDeleteTestOam);
							checkRoot(miUpdateTestOam, RootFactory.CORE_MANAGE);
							checkRoot(miDeleteTestOam, RootFactory.CORE_MANAGE);
							checkRoot(miUpdateQos, RootFactory.CORE_MANAGE);
						}
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
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getSynchroButton());
		needRemoveButtons.add(getInportButton());
		needRemoveButtons.add(getExportButton());
		needRemoveButtons.add(getFiterZero());
		return needRemoveButtons;
	}
	
	@Override
	public List<JButton> setAddButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		JButton jButton = new PtnButton(ResourceUtil.srcStr(StringKeysPanel.PANEL_PW_PORT_CONFIGE),RootFactory.CORE_MANAGE);
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (getAllSelect().size() == 0 || getAllSelect().size() > 1) {
						DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
					} else {
						/**
						 * 修改
						 *     -----sy
						 *      统一使用PwVlanMainDialog作为所有pw的VLAN配置
						 */
						new PwVlanMainDialog(true,getAllSelect().get(0));
					}
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		});
		needRemoveButtons.add(jButton);
		needRemoveButtons.add(getSearchButton());
		return needRemoveButtons;
	}
	
	@Override
	public void setController() {
		controller = new PwBusinessController(this);
	}

	public PwQosQueuePanel getQosPanel() {
		return qosPanel;
	}

	public ViewDataTable<OamInfo> getOamTable() {
		return oamTable;
	}

	public TopoPanel getTopoPanel() {
		return topoPanel;
	}

	public void setTopoPanel(TopoPanel topoPanel) {
		this.topoPanel = topoPanel;
	}
	public Schematize getSchematize_panel() {
		return schematize_panel;
	}

	private JMenuItem miUpdateQos;
	public PwXcTablePanel getPwXcTablePanel() {
		return pwXcTablePanel;
	}

	public void setPwXcTablePanel(PwXcTablePanel pwXcTablePanel) {
		this.pwXcTablePanel = pwXcTablePanel;
	}

	public LspNetworkTablePanel getLspNetworkTablePanel() {
		return lspNetworkTablePanel;
	}
}
