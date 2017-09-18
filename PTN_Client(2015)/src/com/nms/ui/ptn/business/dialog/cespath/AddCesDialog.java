package com.nms.ui.ptn.business.dialog.cespath;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import twaver.Element;
import twaver.Node;
import twaver.PopupMenuGenerator;
import twaver.TView;
import twaver.network.TNetwork;
import com.nms.db.bean.client.Client;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.port.PortStmTimeslot;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.ptn.path.ces.CesInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.system.code.Code;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.ECesType;
import com.nms.db.enums.EPwType;
import com.nms.db.enums.EServiceType;
import com.nms.model.client.ClientService_MB;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.ViewDataTable;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ListingFilter;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.control.PtnTextField;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysMenu;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.business.ces.CesBusinessPanel;
import com.nms.ui.ptn.business.dialog.cespath.controller.ChooseE1Controller;
import com.nms.ui.ptn.business.dialog.cespath.controller.ChooseTimeSlotController;
import com.nms.ui.ptn.business.dialog.cespath.modal.CesPortInfo;
import com.nms.ui.ptn.business.dialog.tunnel.TunnelTopoPanel;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

/**
 *  新建CES
 * @author __USER__
 */
public class AddCesDialog extends PtnDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7365420009838403755L;
	private CesBusinessPanel cesPathPanel;
	private PtnButton submit;
	private JCheckBox ckbActivity;
	private JLabel lblName; // pw列表
	private JLabel lblZPort;
	private JLabel lblAPort;
	private JLabel lblActivity;
	private JLabel lblTunner;
	private JPanel leftPanel;
	private JSplitPane jSplitPane1;
	private JTextField tfName;
	private JScrollPane pwListJSP;
	private JList pwList;
	private JLabel selPwLabel;
	private JScrollPane selPwListJSP;
	private JList selPwList;
	private JLabel serviceTypeJLabel;
	private JComboBox serviceTypeCbox;
	private PortInst portInst_a = null;
	private PortInst portInst_z = null;
	private PortStmTimeslot portStmTime_a = null;
	private PortStmTimeslot portStmTime_z = null;
	private CesInfo cesInfo;
	private int siteId_a;
	private int siteId_z;
	private Vector<Object> pwVector = new Vector<Object>();
	private final Vector<Object> selpwVector = new Vector<Object>();
	private ViewDataTable<CesPortInfo> portTable_a;
	private ViewDataTable<CesPortInfo> portTable_z;
	private JScrollPane jscrollPane_portTable_a;
	private JScrollPane jscrollPane_portTable_z;
	private JButton leftBtn;
	private JButton rightBtn;
	private JLabel lblMessage;
	private JLabel client;
	public JComboBox clientComboBox;
	private JButton autoNamingButton;
	private TunnelTopoPanel tunnelTopoPanel=null;

	public AddCesDialog(CesBusinessPanel cesPathPanel, boolean modal) {
		try {
			this.setModal(modal);
			cesPathPanel.setDialog(this);
			this.cesPathPanel = cesPathPanel;
			initDialog();
			clientComboxData(this.clientComboBox);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

	}

	public AddCesDialog(CesBusinessPanel cesPathPanel, boolean modal, CesInfo cesInfo) {
		
		try {
			this.setModal(modal);
			cesPathPanel.setDialog(this);
			this.cesPathPanel = cesPathPanel;
			this.cesInfo = cesInfo;
			initDialog();
			clientComboxData(this.clientComboBox);
			
			if (this.cesInfo != null) {
               initUpdate();
               super.getComboBoxDataUtil().comboBoxSelect(this.clientComboBox, this.cesInfo.getClientId()+"");
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
		}
	}

	private void initUpdate() {
		List<CesPortInfo> cesPortInfoList = null;
		PortService_MB portService = null;
		PortInst portinst=null;
		List<PortInst> portList=null;
		PwInfo pwInfo=null;
		 ControlKeyValue kv=null;
		try {
			pwInfo=new PwInfo();
			cesPortInfoList = new ArrayList<CesPortInfo>();
			portList=new ArrayList<PortInst>();
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			// 类型
			// UiUtil.comboBoxSelect(serviceTypeCbox,String.valueOf(cesInfo.getCestype()));
			super.getComboBoxDataUtil().comboBoxSelectByValue(serviceTypeCbox, String.valueOf(cesInfo.getCestype()));
			//CES名称
			tfName.setText(cesInfo.getName());
			// 客户类型
			if (cesInfo.getClientId() != 0) {
				super.getComboBoxDataUtil().comboBoxSelect(clientComboBox, String.valueOf(cesInfo.getClientId()));
			}
			// 是否激活
			ckbActivity.setSelected(EActiveStatus.ACTIVITY.getValue() == cesInfo.getActiveStatus() ? true : false);
			// A端端口
			int aAcId=cesInfo.getaAcId();
			portinst = new PortInst();
			portinst.setPortType("e1");
			portinst.setPortId(aAcId);
			portList = portService.select(portinst);
			CesPortInfo cesPort = null;
			for (PortInst e1 : portList) {
				cesPort = new CesPortInfo();
				cesPort.setE1PortInst(e1);
				cesPortInfoList.add(cesPort);
			}
			if(portList!=null&&portList.size()>0){
				this.siteId_a=portList.get(0).getSiteId();
				this.loadPortTable_a(cesPortInfoList, siteId_a);
			}
			// Z端端口
			portinst = new PortInst();
			portinst.setPortType("e1");
			portinst.setPortId(cesInfo.getzAcId());
			portList = portService.select(portinst);
			if(cesPortInfoList!=null){
				cesPortInfoList.clear();
			}
			for (PortInst e1 : portList) {
				cesPort = new CesPortInfo();
				cesPort.setE1PortInst(e1);
				cesPortInfoList.add(cesPort);
			}
			if(portList!=null&&portList.size()>0){
				this.siteId_z=portList.get(0).getSiteId();
				this.loadPortTable_z(cesPortInfoList, siteId_z);
			}
			//加载已经选择的PW列表
			pwInfo=getPwNameById(cesInfo.getPwId());
		    kv = new ControlKeyValue("" + cesInfo.getPwId(), "NE:" + cesInfo.getASiteName() + "-NE:" + cesInfo.getZSiteName() + "/" + "pwName:" +pwInfo.getPwName(),pwInfo);
			selpwVector.add(kv);
			selPwList.setListData(selpwVector);
			  
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			cesPortInfoList=null;
			portinst=null;
			kv=null;
			portinst=null;
			pwInfo=null;
			UiUtil.closeService_MB(portService);
		}
	}

	/**
	 * 
	 * @param cesPathPanel
	 *            父面板
	 * @param modal
	 *            当modal为true时，代表用户必须结束对话框才能回到原来所属的窗口。当modal为 false时，代表对话框与所属窗口可以互相切换，彼此之间在操作上没有顺序性。
	 * @param selectId
	 *            修改时，选中ces的主键id
	 * @throws Exception
	 */
	/*
	 * public AddCesDialog(CesBusinessPanel cesPathPanel, boolean modal, int selectId) { this.setModal(modal); this.cesPathPanel = cesPathPanel; setDefaultCesInfo(selectId); initDialog(); }
	 */

	public void initDialog() throws Exception {
		initComponents();
		this.addListener();
		initDates();
	}

	public void initComponents() throws Exception {
		createComponent();
		setLayout();
	}

	/**
	 * 添加监听
	 */
	private void addListener() {
		this.serviceTypeCbox.addItemListener(new java.awt.event.ItemListener() {
			@Override
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				if (evt.getStateChange() == 1) {
					try {
						initTopo();
					} catch (Exception e) {
						ExceptionManage.dispose(e,this.getClass());
					}
				}
			}
		});
	}

	private void createComponent() throws Exception {

		submit = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE),true,RootFactory.COREMODU,this);
		lblMessage = new JLabel();
		jSplitPane1 = new JSplitPane();
		leftPanel = new JPanel();
		lblName = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_NAME));
		tfName = new PtnTextField(true, PtnTextField.STRING_MAXLENGTH, this.lblMessage, this.submit, this);
		lblZPort = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_Z_SIDE_PORT));
		lblAPort = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_A_SIDE_PORT));
		lblActivity = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ACTIVITY_STATUS));
		ckbActivity = new JCheckBox();
		lblTunner = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PW_LIST));
		pwListJSP = new JScrollPane();
		pwList = new JList();
		pwListJSP.setViewportView(pwList);
		selPwLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OPTED_PW_LIST));
		selPwListJSP = new JScrollPane();
		selPwList = new JList();
		selPwListJSP.setViewportView(selPwList);
		pwList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		serviceTypeJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SERVICE_TYPE));
		serviceTypeCbox = new JComboBox();
		portTable_a = new ViewDataTable<CesPortInfo>("cesPortTable");
		portTable_z = new ViewDataTable<CesPortInfo>("cesPortTable");

		portTable_a.getTableHeader().setResizingAllowed(true);
		portTable_a.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		portTable_a.setTableHeaderPopupMenuFactory(null);
		portTable_a.setTableBodyPopupMenuFactory(null);

		portTable_z.getTableHeader().setResizingAllowed(true);
		portTable_z.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		portTable_z.setTableHeaderPopupMenuFactory(null);
		portTable_z.setTableBodyPopupMenuFactory(null);

		jscrollPane_portTable_a = new JScrollPane();
		jscrollPane_portTable_z = new JScrollPane();

		jscrollPane_portTable_a.setViewportView(portTable_a);
		jscrollPane_portTable_z.setViewportView(portTable_z);

		leftBtn = new JButton("▲");
		rightBtn = new JButton("▼");
		client = new javax.swing.JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_CLIENT_NAME));
		clientComboBox = new javax.swing.JComboBox();
		autoNamingButton = new JButton(ResourceUtil.srcStr(StringKeysLbl.LBL_AUTO_NAME));
	}

	public void initDates() {
		try {
			super.getComboBoxDataUtil().comboBoxData(serviceTypeCbox, "CESSERVICETYPE");
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void initTopo() {
		TNetwork network = null;
		try {

			tunnelTopoPanel.boxDataByPws(this.getPwinfoList());
			network =tunnelTopoPanel.getNetWork();
//			network.doLayout(TWaverConst.LAYOUT_CIRCULAR);
			network.setPopupMenuGenerator(new PopupMenuGenerator() {
				@Override
				public JPopupMenu generate(TView tview, MouseEvent mouseEvent) {

					JPopupMenu menu = new JPopupMenu();

					if (!tview.getDataBox().getSelectionModel().isEmpty()) {
						final Element element = tview.getDataBox().getLastSelectedElement();

						if (element instanceof Node) {
							if (element.getBusinessObject() != null) {

								JMenuItem ajMenuItem = new JMenuItem(ResourceUtil.srcStr(StringKeysMenu.MENU_SELECT_A_PORT));
								ajMenuItem.addActionListener(new java.awt.event.ActionListener() {
									@Override
									public void actionPerformed(java.awt.event.ActionEvent evt) {
										selectPort("A", element);
									}

								});
								JMenuItem zjMenuItem = new JMenuItem(ResourceUtil.srcStr(StringKeysMenu.MENU_SELECT_Z_PORT));
								zjMenuItem.addActionListener(new java.awt.event.ActionListener() {
									@Override
									public void actionPerformed(java.awt.event.ActionEvent evt) {
										selectPort("Z", element);
									}
								});
								menu.add(ajMenuItem);
								menu.add(zjMenuItem);
							} else {
								return menu;
							}
						}
					}
					return menu;
				}

			});
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			network = null;
		}
	}

	/**
	 * 根据ces业务类型，获取不同的pw集合。用来刷新拓扑
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private List<PwInfo> getPwinfoList() throws Exception {
		List<PwInfo> pwInfoList = null;
		PwInfoService_MB pwInfoService = null;
		PwInfo pwInfo = null;
		Code code_type = null;
		List<PwInfo> pwInfoList_result = null;
		ListingFilter filter=null;
		try {
			filter=new ListingFilter();
			pwInfoList_result=new ArrayList<PwInfo>();
			pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			code_type = (Code) ((ControlKeyValue) serviceTypeCbox.getSelectedItem()).getObject();
			pwInfo = new PwInfo();
			if(cesInfo==null){
				pwInfo.setType(EPwType.forms(Integer.parseInt(code_type.getCodeValue())));
				pwInfo.setPwStatus(EActiveStatus.ACTIVITY.getValue());//先显示激活的PW
				pwInfoList = (List<PwInfo>) filter.filterList(pwInfoService.select(pwInfo));
				for (PwInfo pwInfo_select : pwInfoList) {
					if (pwInfo_select.getRelatedServiceId() == 0) {
						pwInfoList_result.add(pwInfo_select);
					}
				}
			}else{
				pwInfo.setPwId(cesInfo.getPwId());
				pwInfo.setType(EPwType.forms(cesInfo.getCestype()));
				pwInfoList =(List<PwInfo>) filter.filterList( pwInfoService.select(pwInfo));
				for (PwInfo pwInfo_select : pwInfoList) {
					if (pwInfo_select.getRelatedServiceId()!=0) {
						pwInfoList_result.add(pwInfo_select);
					}
				}
			}
		} catch (Exception e) {
			throw e;
		} finally{
			UiUtil.closeService_MB(pwInfoService);
			pwInfo = null;
			filter=null;
			code_type = null;
		}
		return pwInfoList_result;
	}

	private void selectPort(String type, Element element) {
		Code code = (Code) ((ControlKeyValue) serviceTypeCbox.getSelectedItem()).getObject();
		int cestype = Integer.parseInt(code.getCodeValue());

		if (cestype == ECesType.SDHPDH.getValue()) {
			if ("A".equals(type)) {
				showStm1Dialog(element, type);
			} else {
				showE1Dialog(element, type);
			}
		} else if (cestype == ECesType.PDHSDH.getValue()) { // 弹出E1界面
			if ("A".equals(type)) {
				showE1Dialog(element, type);
			} else {
				showStm1Dialog(element, type);
			}
		} else if (cestype == ECesType.SDH.getValue()) {
			showStm1Dialog(element, type);
		} else if (cestype == ECesType.PDH.getValue()) {
			showE1Dialog(element, type);
		}

	}

	// 弹出E1界面
	private void showE1Dialog(Element element, final String type) {
		SiteInst siteInst = (SiteInst) element.getUserObject();
		final ChooseE1Dialog dialog = new ChooseE1Dialog(this, true, type);
		dialog.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_SELECT_E1_PORT));
		dialog.setSize(new Dimension(650, 450));
		dialog.setLocation(UiUtil.getWindowWidth(dialog.getWidth()), UiUtil.getWindowHeight(dialog.getHeight()));

		ChooseE1Controller CEcontroller = new ChooseE1Controller(dialog, siteInst, this);
		CEcontroller.initData();
		dialog.setVisible(true);
	}

	private void showStm1Dialog(Element element, final String type) {
		SiteInst siteInst = (SiteInst) element.getUserObject();
		ChooseTimeSlotDialog dialog = new ChooseTimeSlotDialog(this, true, type);
		dialog.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_SELECT_STM));
		dialog.setSize(new Dimension(650, 450));
		dialog.setLocation(UiUtil.getWindowWidth(dialog.getWidth()), UiUtil.getWindowHeight(dialog.getHeight()));
		ChooseTimeSlotController CTScontroller = new ChooseTimeSlotController(dialog, siteInst);
		CTScontroller.initData();
		dialog.setVisible(true);
	}

	public void update(CesInfo obj) {
		obj.setName(tfName.getText().trim());
		obj.setActiveStatus(ckbActivity.isSelected() == true ? EActiveStatus.ACTIVITY.getValue() : EActiveStatus.UNACTIVITY.getValue());

	}

	public CesInfo get(PwInfo pw) throws Exception {
		CesInfo cesInfo=null;
		if(this.cesInfo!=null){
			cesInfo=this.cesInfo;
		}else{
			cesInfo = new CesInfo();
		}
		ControlKeyValue client = null;
		client = (ControlKeyValue) this.clientComboBox.getSelectedItem();
		cesInfo.setName(tfName.getText().trim());
		cesInfo.setServiceType(EServiceType.CES.getValue());
		cesInfo.setActiveStatus(ckbActivity.isSelected() == true ? EActiveStatus.ACTIVITY.getValue() : EActiveStatus.UNACTIVITY.getValue());
		cesInfo.setCreateUser(ConstantUtil.user.getUser_Name());
		cesInfo.setCreateTime(DateUtil.getDate(DateUtil.FULLTIME));
		cesInfo.setaSiteId(this.siteId_a);
		cesInfo.setzSiteId(this.siteId_z);
		cesInfo.setPwId(pw.getPwId());
		cesInfo.setPwName(pw.getPwName());
		if (!"".equals(client.getId())) 
			cesInfo.setClientId(Integer.parseInt(client.getId()));
		if(((Client)client.getObject()) != null){
			cesInfo.setClientName(((Client)client.getObject()).getName());
		}
		ControlKeyValue controlKeyValue = (ControlKeyValue) this.serviceTypeCbox.getSelectedItem();
		Code code = (Code) controlKeyValue.getObject();
		cesInfo.setCestype(Integer.parseInt(code.getCodeValue()));
		return cesInfo;
	}

	private void setLayout() {
		this.jSplitPane1.setLeftComponent(this.leftPanel);
		this.leftPanel.setPreferredSize(new Dimension(280, 700));
		tunnelTopoPanel=new TunnelTopoPanel();
		this.jSplitPane1.setRightComponent(tunnelTopoPanel);

		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 50, 180, 50 };
		layout.columnWeights = new double[] { 0, 0, 0 };
		layout.rowHeights = new int[] { 25, 30, 30, 30, 100, 100, 30, 100, 30, 100, 15, 30, 30 };
		layout.rowWeights = new double[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.2 };
		this.leftPanel.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(this.lblMessage, c);
		this.leftPanel.add(this.lblMessage);

		/** 第一行 */
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(lblName, c);
		this.leftPanel.add(lblName);
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 70);
		layout.addLayoutComponent(tfName, c);
		this.leftPanel.add(tfName);
		c.gridx = 2;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		layout.addLayoutComponent(autoNamingButton, c);
		this.leftPanel.add(autoNamingButton);
		/** 第二行 */
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(this.serviceTypeJLabel, c);
		this.leftPanel.add(this.serviceTypeJLabel);
		c.gridx = 1;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		layout.addLayoutComponent(this.serviceTypeCbox, c);
		this.leftPanel.add(this.serviceTypeCbox);

		/** 第3行 */
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(this.client, c);
		this.leftPanel.add(this.client);
		c.gridx = 1;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.BOTH;
		layout.addLayoutComponent(this.clientComboBox, c);
		this.leftPanel.add(this.clientComboBox);

		/** 第4行 */
		c.gridx = 0;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(this.lblAPort, c);
		this.leftPanel.add(this.lblAPort);
		c.gridx = 1;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.BOTH;
		layout.addLayoutComponent(this.jscrollPane_portTable_a, c);
		this.leftPanel.add(this.jscrollPane_portTable_a);
		/** 第5行 */
		c.gridx = 0;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.setConstraints(this.lblZPort, c);
		this.leftPanel.add(this.lblZPort);
		c.gridx = 1;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.BOTH;
		layout.addLayoutComponent(this.jscrollPane_portTable_z, c);
		this.leftPanel.add(this.jscrollPane_portTable_z);
		/** 第6行 */
		c.gridx = 0;
		c.gridy = 6;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.setConstraints(this.lblActivity, c);
		this.leftPanel.add(this.lblActivity);
		c.gridx = 1;
		c.gridy = 6;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.ckbActivity, c);
		this.leftPanel.add(this.ckbActivity);
		/** 第7行 */
		c.gridx = 0;
		c.gridy = 7;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(this.lblTunner, c);
		this.leftPanel.add(this.lblTunner);
		c.gridx = 1;
		c.gridy = 7;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.BOTH;
		layout.setConstraints(this.pwListJSP, c);
		this.leftPanel.add(this.pwListJSP);
		// 第8行
		c.gridx = 1;
		c.gridy = 8;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		c.fill = GridBagConstraints.VERTICAL;
		c.anchor = GridBagConstraints.EAST;
		layout.setConstraints(this.leftBtn, c);
		this.leftPanel.add(this.leftBtn);
		c.gridx = 1;
		c.gridy = 8;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.WEST;
		layout.setConstraints(this.rightBtn, c);
		this.leftPanel.add(this.rightBtn);
		// 第9行
		c.gridx = 0;
		c.gridy = 9;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.setConstraints(this.selPwLabel, c);
		this.leftPanel.add(this.selPwLabel);
		c.gridx = 1;
		c.gridy = 9;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.BOTH;
		layout.setConstraints(this.selPwListJSP, c);
		this.leftPanel.add(this.selPwListJSP);

		// 第10行
		c.gridx = 2;
		c.gridy = 11;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.setConstraints(this.submit, c);
		this.leftPanel.add(this.submit);

		this.setLayout(new BorderLayout());
		// this.add(topPanel, BorderLayout.NORTH);
		this.add(jSplitPane1, BorderLayout.CENTER);
	}

	/*
	 * public void setDefaultCesInfo(int selectId) { CesInfoService service = null; try { service = (CesInfoService) ConstantUtil.serviceFactory .newService(Services.CesInfo); this.cesInfo = service.select(new CesInfo(selectId)).get(0); } catch (Exception e) { ExceptionManage.dispose(e,this.getClass()); this.cesInfo = null; } finally { service = null; }
	 * 
	 * }
	 */

	public void loadPortTable_a(List<CesPortInfo> cesPortInfoList, int siteId) {
		getPortTable_a().clear();
		setSiteId_a(siteId);
		getPortTable_a().initData(cesPortInfoList);
	}

	public void loadPortTable_z(List<CesPortInfo> cesPortInfoList, int siteId) {
		getPortTable_z().clear();
		setSiteId_z(siteId);
		getPortTable_z().initData(cesPortInfoList);
	}
	//用于显示PW列表的
	public void loadPw(Collection<PwInfo> pwInfoCollection) {
		this.selpwVector.removeAllElements();
		getPwVector().removeAllElements();
		// 界面a端和z端表中的端口
		ControlKeyValue kv = null;
		try {
			// A表中端口和Z表中端口
			for (PwInfo disPw : pwInfoCollection) {
				kv = new ControlKeyValue(disPw.getPwId() + "", "NE:" + getSiteAdress(disPw.getASiteId()) + "-" + "NE:" + getSiteAdress(disPw.getZSiteId()) + "/" + "pwName:" + disPw.getPwName(), disPw);
				getPwVector().add(kv);
			}
			this.pwList.setListData(this.pwVector);
		} catch (Exception e) {
		} finally {
			kv = null;
		}
	}
	//用于显示已近选择的PW列表
	public void loadSelectPw(Collection<PwInfo> pwInfoCollection) {
		getSelpwVector().removeAllElements();
		// 界面a端和z端表中的端口
		ControlKeyValue kv = null;
		try {
			// A表中端口和Z表中端口
			for (PwInfo disPw : pwInfoCollection) {
				kv = new ControlKeyValue(disPw.getPwId() + "", "NE:" + getSiteAdress(disPw.getASiteId()) + "-" + "NE:" + getSiteAdress(disPw.getZSiteId()) + "/" + "pwName:" + disPw.getPwName(), disPw);
				getSelpwVector().add(kv);
			}
			this.selPwList.setListData(this.selpwVector);
		} catch (Exception e) {
		} finally {
			kv = null;
		}
	}
	/**
	 * 根据网元ID查询网元地址
	 * 
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	public String getSiteAdress(int siteId) throws Exception {
		SiteService_MB siteService = null;
		SiteInst siteInst = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteInst = siteService.select(siteId);

			if (siteInst != null) {
				return siteInst.getCellId();
			} else {
				throw new Exception(ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_SITEINST_ERROR));
			}

		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(siteService);
		}
	}

	public List<Integer> getTablePortIdList() {
		List<Integer> tableportIdList;
		tableportIdList = new ArrayList<Integer>();
		getAtablePortIdList(tableportIdList);
		getZtablePortIdList(tableportIdList);
		return tableportIdList;
	}

	@SuppressWarnings("unchecked")
	public void getZtablePortIdList(List<Integer> tableportIdList) {
		List<CesPortInfo> z_cesportInfoList = this.portTable_z.getDataBox().getAllElements();
		for (CesPortInfo cesportInfo : z_cesportInfoList) {
			if (cesportInfo.getPortStmTimeSlot() != null) {
				tableportIdList.add(cesportInfo.getPortStmTimeSlot().getId());
			}
			if (cesportInfo.getE1PortInst() != null) {
				tableportIdList.add(cesportInfo.getE1PortInst().getPortId());
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void getAtablePortIdList(List<Integer> tableportIdList) {
		List<CesPortInfo> a_cesportInfoList = this.portTable_a.getDataBox().getAllElements();
		
		for (CesPortInfo cesportInfo : a_cesportInfoList) {
			if (cesportInfo.getPortStmTimeSlot() != null) {
				tableportIdList.add(cesportInfo.getPortStmTimeSlot().getId());
			}
			if (cesportInfo.getE1PortInst() != null) {
				tableportIdList.add(cesportInfo.getE1PortInst().getPortId());
			}
		}
	}

	/**
	 * 客户信息下拉列表
	 * 
	 * @param jComboBox1
	 */
	public void clientComboxData(JComboBox jComboBox1) {

		ClientService_MB service = null;
		List<Client> clientList = null;
		DefaultComboBoxModel defaultComboBoxModel = (DefaultComboBoxModel) clientComboBox.getModel();
		try {
			service = (ClientService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CLIENTSERVICE);
			clientList = service.refresh();
			defaultComboBoxModel.addElement(new ControlKeyValue("0", "", null));
			for (Client client : clientList) {
				defaultComboBoxModel.addElement(new ControlKeyValue(client.getId() + "", client.getName(), client));
			}
			clientComboBox.setModel(defaultComboBoxModel);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
			clientList = null;

		}
	}

	public PtnButton getSubmit() {
		return submit;
	}

	public JCheckBox getCkbActivity() {
		return ckbActivity;
	}

	public JTextField getTfName() {
		return tfName;
	}

	public JList getPwList() {
		return pwList;
	}

	public JComboBox getServiceTypeCbox() {
		return serviceTypeCbox;
	}

	public CesBusinessPanel getCesPathPanel() {
		return cesPathPanel;
	}

	public CesInfo getCesInfo() {
		return cesInfo;
	}

	public void setCesInfo(CesInfo cesInfo) {
		this.cesInfo = cesInfo;
	}

	public PortInst getPortInst_a() {
		return portInst_a;
	}

	public void setPortInst_a(PortInst portInst_a) {
		this.portInst_a = portInst_a;
	}

	public PortInst getPortInst_z() {
		return portInst_z;
	}

	public void setPortInst_z(PortInst portInst_z) {
		this.portInst_z = portInst_z;
	}

	public PortStmTimeslot getPortStmTime_a() {
		return portStmTime_a;
	}

	public void setPortStmTime_a(PortStmTimeslot portStmTimeA) {
		portStmTime_a = portStmTimeA;
	}

	public PortStmTimeslot getPortStmTime_z() {
		return portStmTime_z;
	}

	public void setPortStmTime_z(PortStmTimeslot portStmTimeZ) {
		portStmTime_z = portStmTimeZ;
	}

	public int getSiteId_a() {
		return siteId_a;
	}

	public void setSiteId_a(int siteIdA) {
		siteId_a = siteIdA;
	}

	public int getSiteId_z() {
		return siteId_z;
	}

	public void setSiteId_z(int siteIdZ) {
		siteId_z = siteIdZ;
	}

	public Vector<Object> getPwVector() {
		return pwVector;
	}

	public void setPwVector(Vector<Object> pwVector) {
		this.pwVector = pwVector;
	}

	public ViewDataTable<CesPortInfo> getPortTable_a() {
		return portTable_a;
	}

	public ViewDataTable<CesPortInfo> getPortTable_z() {
		return portTable_z;
	}

	public JButton getLeftBtn() {
		return leftBtn;
	}

	public JButton getRightBtn() {
		return rightBtn;
	}

	public JList getSelPwList() {
		return selPwList;
	}

	public Vector<Object> getSelpwVector() {
		return selpwVector;
	}

	public JButton getAutoNamingButton() {
		return autoNamingButton;
	}

	/**
	 * @param pwId
	 *            通过pwID 来获取PW的名称
	 * @return
	 */
	private PwInfo getPwNameById(int pwId) {
		PwInfoService_MB pwService = null;
		PwInfo pwinfo = null;
		List<PwInfo> list = null;
		try {
			pwService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			pwinfo = new PwInfo();
			pwinfo.setPwId(pwId);
			list = pwService.select(pwinfo);
			if (list.size() > 0)
				return list.get(0);

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(pwService);
			pwinfo = null;
			list = null;
		}
		return null;
	}
	public TunnelTopoPanel getTunnelTopoPanel() {
		return tunnelTopoPanel;
	}

	public void setTunnelTopoPanel(TunnelTopoPanel tunnelTopoPanel) {
		this.tunnelTopoPanel = tunnelTopoPanel;
	}
}