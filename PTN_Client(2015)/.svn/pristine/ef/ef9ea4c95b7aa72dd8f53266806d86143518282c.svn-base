package com.nms.ui.ptn.business.dialog.cespath;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import twaver.TWaverUtil;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.port.PortStmTimeslot;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.ptn.oam.OamMepInfo;
import com.nms.db.bean.ptn.path.ces.CesInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.bean.ptn.qos.QosInfo;
import com.nms.db.bean.system.code.Code;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.ECesType;
import com.nms.db.enums.EOperationLogType;
import com.nms.db.enums.EPwType;
import com.nms.db.enums.EQosDirection;
import com.nms.db.enums.EServiceType;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.port.PortStmTimeslotService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.oam.OamInfoService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.ptn.qos.QosInfoService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.service.impl.util.ResultString;
import com.nms.ui.frame.ViewDataTable;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.AutoNamingUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnSpinner;
import com.nms.ui.manager.control.PtnTextField;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.business.dialog.cespath.modal.CesPortInfo;
import com.nms.ui.ptn.business.dialog.pwpath.AddPDialog;
import com.nms.ui.ptn.business.dialog.pwpath.TunnelTopology;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
import com.nms.ui.ptn.systemconfig.dialog.qos.controller.QosConfigController;

public class AddCESAllDialog extends AddPDialog{
	private static final long serialVersionUID = 2194594396067226943L;
	private List<QosInfo> qosList = new ArrayList<QosInfo>();
	private TunnelTopology tunnelTopology = null;
	private List<Tunnel> useTunnelList = new ArrayList<Tunnel>();//已选tunnel
	private List<Tunnel> allTunnelList = null;//拓扑上所有tunnel
	private PwInfo pwInfo = null;
	private int siteId_a = 0;
	private int siteId_z = 0;
	
	public AddCESAllDialog() {
		super();
		this.setModal(true);
		this.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_BATCH_CREATE_CES));
		this.initComponents();
		this.setLayout();
		this.addListener();
		this.initPwTypeComBox();
		this.pwInfo = new PwInfo();
		this.tunnelTopology = new TunnelTopology(this);
		this.jSplitPanel.setRightComponent(this.tunnelTopology);
		this.setLocation(UiUtil.getWindowWidth(this.getWidth()), UiUtil.getWindowHeight(this.getHeight()));
		this.setVisible(true);
	}
	
	private void initComponents() {
		this.jSplitPanel = new JSplitPane();
		this.leftPanel = new JPanel();
		Dimension dimension = new Dimension(1200, 700);
		this.setSize(dimension);
		this.setMinimumSize(dimension);
		this.lblMessage = new JLabel();
		this.saveBtn = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE),true,RootFactory.COREMODU,this);
		this.lblName = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_NAME));
		this.autoNamingBtn = new JButton(ResourceUtil.srcStr(StringKeysLbl.LBL_AUTO_NAME));
		this.lblPwType = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TYPE));
		this.pwTypeJcbBox = new JComboBox();
		this.pwTypeJcbBox.setPreferredSize(new Dimension(155, 20));
		this.lblIsActive = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ACTIVITY_STATUS));
		this.lblPayload = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PAYLOAD));
		this.payloadJcbBox = new JComboBox();
		try {
			this.nameTextField = new PtnTextField(true, PtnTextField.STRING_MAXLENGTH, this.lblMessage, this.saveBtn, this);
			super.getComboBoxDataUtil().comboBoxData(this.payloadJcbBox, "PAYLOAD");
			super.getComboBoxDataUtil().comboBoxSelect(this.payloadJcbBox, UiUtil.getCodeByValue("PAYLOAD", "2").getId() + "");
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		this.lblZPort = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_Z_SIDE_PORT));
		this.lblAPort = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_A_SIDE_PORT));
		this.portTable_a = new ViewDataTable<CesPortInfo>("cesPortTable");
		this.portTable_z = new ViewDataTable<CesPortInfo>("cesPortTable");
		this.portTable_a.getTableHeader().setResizingAllowed(true);
		this.portTable_a.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		this.portTable_a.setTableHeaderPopupMenuFactory(null);
		this.portTable_a.setTableBodyPopupMenuFactory(null);
		this.portTable_z.getTableHeader().setResizingAllowed(true);
		this.portTable_z.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		this.portTable_z.setTableHeaderPopupMenuFactory(null);
		this.portTable_z.setTableBodyPopupMenuFactory(null);
		this.jscrollPane_portTable_a = new JScrollPane();
		this.jscrollPane_portTable_z = new JScrollPane();
		this.jscrollPane_portTable_a.setViewportView(this.portTable_a);
		this.jscrollPane_portTable_z.setViewportView(this.portTable_z);
		this.aPortBtn = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIG));
		this.zPortBtn = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIG));
		this.lblQos = new JLabel("QoS");
		this.qosConfigBtn = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIG));
		this.qosTextField = new PtnTextField();
		this.qosTextField.setEnabled(false);
		this.lblNumber = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_CREATE_NUM));
		this.ptnSpinnerNumber = new PtnSpinner(1, 1, 100, 1);
		this.isActiveCBox = new JCheckBox();
		this.isActiveCBox.setSelected(true);
	}
	
	private void setLayout() {
		this.add(this.jSplitPanel);
		this.jSplitPanel.setLeftComponent(this.leftPanel);
		this.leftPanel.setPreferredSize(new Dimension(205, 700));
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 5, 150, 50 };
		layout.columnWeights = new double[] { 0, 0.1, 0 };
		layout.rowHeights = new int[] {30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30,200};
		layout.rowWeights = new double[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		this.leftPanel.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		int i = 0;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = i++;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(this.lblMessage, c);
		this.leftPanel.add(this.lblMessage);

		 //第一行名称LABLE,文本框3列 有2列合并
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = i;
		c.gridheight = 1;
		c.gridwidth = 1;
		layout.setConstraints(this.lblName, c);
		this.leftPanel.add(this.lblName);
		c.gridx = 1;
		c.gridy = i;
		c.insets = new Insets(5, 5, 5, 5);
		layout.addLayoutComponent(this.nameTextField, c);
		this.leftPanel.add(this.nameTextField);
		c.fill = GridBagConstraints.NONE;
		c.gridx = 2;
		c.gridy = i++;
		layout.addLayoutComponent(this.autoNamingBtn, c);
		this.leftPanel.add(this.autoNamingBtn);
		 
		// 第二行 pw类型
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = i;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(this.lblPwType, c);
		this.leftPanel.add(this.lblPwType);
		c.gridx = 1;
		c.gridy = i++;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		layout.addLayoutComponent(this.pwTypeJcbBox, c);
		this.leftPanel.add(this.pwTypeJcbBox);
		
		//第三行 负载净荷
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = i;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(this.lblPayload, c);
		this.leftPanel.add(this.lblPayload);
		c.gridx = 1;
		c.gridy = i++;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		layout.addLayoutComponent(this.payloadJcbBox, c);
		this.leftPanel.add(this.payloadJcbBox);

		//第四行：a端端口选择
		c.gridx = 0;
		c.gridy = i;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.addLayoutComponent(this.lblAPort, c);
		this.leftPanel.add(this.lblAPort);
		c.gridx = 1;
		c.gridy = i;
		c.gridheight = 3;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.BOTH;
		layout.addLayoutComponent(this.jscrollPane_portTable_a, c);
		this.leftPanel.add(this.jscrollPane_portTable_a);
		c.gridx = 2;
		c.gridy = i++;
		c.gridheight = 1;
		c.fill = GridBagConstraints.NONE;
		layout.addLayoutComponent(this.aPortBtn, c);
		this.leftPanel.add(this.aPortBtn);
		
		//第五行 z端端口选择
		i += 2;
		c.gridx = 0;
		c.gridy = i;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(this.lblZPort, c);
		this.leftPanel.add(this.lblZPort);
		c.gridx = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.BOTH;
		c.gridheight = 3;
		layout.addLayoutComponent(this.jscrollPane_portTable_z, c);
		this.leftPanel.add(this.jscrollPane_portTable_z);
		c.gridx = 2;
		c.gridy = i++;
		c.fill = GridBagConstraints.NONE;
		c.gridheight = 1;
		layout.addLayoutComponent(this.zPortBtn, c);
		this.leftPanel.add(this.zPortBtn);

		 //第6行,QosLable,文本框,按钮
		i += 2;
		c.gridx = 0;
		c.gridy = i;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		layout.setConstraints(this.lblQos, c);
		this.leftPanel.add(this.lblQos);
		c.gridx = 1;
		c.gridy = i;
		c.insets = new Insets(5, 5, 5, 5);
		layout.addLayoutComponent(this.qosTextField, c);
		this.leftPanel.add(this.qosTextField);
		c.fill = GridBagConstraints.NONE;
		c.gridx = 2;
		c.gridy = i++;
		c.insets = new Insets(5, 5, 5, 5);
		layout.addLayoutComponent(this.qosConfigBtn, c);
		this.leftPanel.add(this.qosConfigBtn);

		// 第7行，批量创建的数量
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = i;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(this.lblNumber, c);
		this.leftPanel.add(this.lblNumber);
		c.gridx = 1;
		c.gridy = i++;
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.CENTER;
		layout.addLayoutComponent(this.ptnSpinnerNumber, c);
		this.leftPanel.add(this.ptnSpinnerNumber);

		// 第8行 , 是否激活,单选按钮,有2列合并
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = i;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(this.lblIsActive, c);
		this.leftPanel.add(this.lblIsActive);
		c.gridx = 1;
		c.gridy = i++;
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.CENTER;
		layout.addLayoutComponent(this.isActiveCBox, c);
		this.leftPanel.add(this.isActiveCBox);

		// 第10行 确定按钮 中间空出一行
		c.gridx = 2;
		c.gridy = i;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		layout.addLayoutComponent(this.saveBtn, c);
		this.leftPanel.add(this.saveBtn);
	}

	private void addListener() {
		this.autoNamingBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				autoNamingActionPerformed();
			}
		});
		
		this.aPortBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectE1Dialog("A");
			}
		});
		
		this.zPortBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectE1Dialog("Z");
			}
		});
		
		this.qosConfigBtn.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				qosConfigButtonActionPerformed(evt);
				int sum = 0;
				if (qosList.size() > 0) {
					for (int i = 0; i < qosList.size(); i++) {
						sum += qosList.get(i).getCir();
					}
				}
				
				qosTextField.setText("totalCir=" + sum);
			}
		});
		
		this.saveBtn.addActionListener(new MyActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveCesInfo();
			}

			@Override
			public boolean checking() {
				return verify();
			}
		});
	}

	/**
	 * 自动命名
	 */
	private void autoNamingActionPerformed() {
		try {
			CesInfo cesInfo = new CesInfo();
			cesInfo.setIsSingle(0);
			AutoNamingUtil autoNamingUtil = new AutoNamingUtil();
			String autoNaming = (String)autoNamingUtil.autoNaming(cesInfo, null, null);
			this.nameTextField.setText(autoNaming);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}

	/**
	 * 选择E1端口
	 */
	private void selectE1Dialog(String type){
		SiteInst siteInst = null;
		if(type.equals("A")){
			siteInst = this.tunnelTopology.getSiteA();	
		}else{
			siteInst = this.tunnelTopology.getSiteZ();
		}
		if(siteInst != null && siteInst.getSite_Inst_Id() > 0){
			new SelectE1Dialog(this, true, type, siteInst);
		}else{
			DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_AZ_SITE));
		}
	}
	
	/**
	 * 新建QoS
	 */
	private void qosConfigButtonActionPerformed(ActionEvent evt) {
		QosConfigController controller = new QosConfigController();
		controller.openQosConfig(controller, "PW", this.pwInfo, (EPwType)((ControlKeyValue) this.pwTypeJcbBox.getSelectedItem()).getObject(), this);
	}
	
	/**
	 * 保存按钮事件
	 */
	private void saveCesInfo(){
		boolean routeFlag = false;
		//普通PW计算路由
		routeFlag = this.autoRoute();
		if (!routeFlag) {
			this.portTable_a.clear();
			this.portTable_z.clear();
			DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_NO_ROUTE));
		}else{
			//验证带宽
			boolean qosFlag = this.checkQosIsEnough(this.useTunnelList.get(0));
			if(qosFlag){
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_QOSISNOTENOUGH_E1));
			}else{
				//批量建PW
				String pwFlag = this.buildPWBatch();
				
				//批量建CES
				if(pwFlag.contains(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))){
					String cesFlag = this.buildCESBatch();
					DialogBoxUtil.succeedDialog(this, cesFlag);
				}else{
					//创建失败
					DialogBoxUtil.succeedDialog(this, pwFlag);
				}
			}
			TWaverUtil.clearImageIconCache();
			this.dispose();
		}
	}
	
	/**
	 * 计算普通pw路由
	 */
	private boolean autoRoute(){
		this.useTunnelList.clear();
//		this.tunnelTopology.setLinkColor(Color.GREEN);
		if(tunnelTopology.tunnelMust.size() > 1){
			return false;
		}
		//获取拓扑所有tunnel
		if (this.allTunnelList == null) {
			TunnelService_MB tunnelService = null;
			this.allTunnelList = new ArrayList<Tunnel>();
			try {
				tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
				this.allTunnelList = tunnelService.select();
			} catch (Exception e) {
				ExceptionManage.dispose(e, this.getClass());
			} finally {
				UiUtil.closeService_MB(tunnelService);
			}
		}
		List<Tunnel> tunnelMustList = this.tunnelTopology.tunnelMust;
		int aSiteId = tunnelTopology.getSiteA().getSite_Inst_Id();
		int zSiteId = tunnelTopology.getSiteZ().getSite_Inst_Id();
		for (Tunnel tunnel : this.allTunnelList) {
			if ((tunnel.getASiteId() == aSiteId && tunnel.getZSiteId() == zSiteId)
					|| (tunnel.getZSiteId() == aSiteId && tunnel.getASiteId() == zSiteId)) {
				if(tunnelMustList.size() == 1){
					if(tunnel.getTunnelId() == tunnelMustList.get(0).getTunnelId()){
						this.useTunnelList.add(tunnel);
						break;
					}
				}else{
					tunnelMustList.add(tunnel);
					this.useTunnelList.add(tunnel);
					break;
				}
			}
		}
		if (this.useTunnelList.size() == 1) {
//			tunnelTopology.setPath(useTunnelList);
			return true;
		}
		return false;
	}

	/**
	 * 验证带宽是否匹配
	 * true/false = 带宽不足/带宽满足
	 */
	private boolean checkQosIsEnough(Tunnel tunnel) {
		QosInfoService_MB qosService = null;
		try {
			Map<Integer, Integer> preMap = new HashMap<Integer, Integer>();
			Map<Integer, Integer> nextMap = new HashMap<Integer, Integer>();
			qosService = (QosInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosInfo);
			List<QosInfo> tunnelQosInfoList = new ArrayList<QosInfo>();
			tunnelQosInfoList = qosService.getQosByObj(EServiceType.TUNNEL.toString(), tunnel.getTunnelId());
			for (QosInfo q : tunnelQosInfoList) {
				if (Integer.parseInt(q.getDirection()) == EQosDirection.FORWARD.getValue()) {
					preMap.put(q.getCos(), q.getCir());
				}
				if (Integer.parseInt(q.getDirection()) == EQosDirection.BACKWARD.getValue()) {
					nextMap.put(q.getCos(), q.getCir());
				}
			}
			// 找出多条在同一条隧道上的pw
			List<Integer> tunnelIdList = new ArrayList<Integer>();
			tunnelIdList.add(tunnel.getTunnelId());
			List<PwInfo> pwList = this.filterPwList(tunnelIdList);
			// 减去已经被在同一条隧道上pw使用过的qos
			int used = 0;//已用带宽
			int use = 0;//总带宽
			if(pwList != null && !pwList.isEmpty()){
				for (PwInfo pw : pwList) {
					List<QosInfo> pwUsedQosInfoList = new ArrayList<QosInfo>();
					pwUsedQosInfoList = qosService.getQosByObj(EServiceType.PW.toString(), pw.getPwId());
					for (QosInfo q : pwUsedQosInfoList) {
						if (Integer.parseInt(q.getDirection()) == EQosDirection.FORWARD.getValue()) {
							use = preMap.get(q.getCos());
							used = q.getCir();
							preMap.put(q.getCos(), use - used);
						}
						if (Integer.parseInt(q.getDirection()) == EQosDirection.BACKWARD.getValue()) {
							use = nextMap.get(q.getCos());
							used = q.getCir();
							nextMap.put(q.getCos(), use - used);
						}
					}
				}
			}
			List<QosInfo> pwQosInfoList = this.getQosList();
			int pwCount = Integer.parseInt(this.ptnSpinnerNumber.getTxtData());
			for (QosInfo q : pwQosInfoList) {
				if (Integer.parseInt(q.getDirection()) == EQosDirection.FORWARD.getValue()) {
					if (preMap.get(q.getCos()) != null) {
						if (q.getCir()*pwCount > preMap.get(q.getCos())) {
							return true;
						}
					}
				}
				if (Integer.parseInt(q.getDirection()) == EQosDirection.BACKWARD.getValue()) {
					if (nextMap.get(q.getCos()) != null) {
						if (q.getCir()*pwCount > nextMap.get(q.getCos())) {
							return true;
						}
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(qosService);
		}
		return false;
	}
	
	/**
	 * 查找已用的pw
	 */
	private List<PwInfo> filterPwList(List<Integer> tunnelIdList) {
		PwInfoService_MB pwService = null;
		List<PwInfo> pwList = null;
		try {
			pwService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			List<PwInfo> pwInfoList = pwService.selectPwInfoByTunnelId(tunnelIdList);
			pwList = new ArrayList<PwInfo>();
			if(pwInfoList != null && !pwInfoList.isEmpty()){
				for (PwInfo pwInfo : pwInfoList) {
					if(!(pwInfo.getASiteId() == this.siteId_a && pwInfo.getZSiteId() == this.siteId_z
							&& pwInfo.getType().getValue() == EPwType.PDH.getValue() && 
							pwInfo.getBusinessType().equals("0") && pwInfo.getRelatedServiceId() == 0
							&& pwInfo.getPwStatus() == EActiveStatus.ACTIVITY.getValue())){
						pwList.add(pwInfo);
					}
				}
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(pwService);
		}
		return pwList;
	}

	
	/**
	 * 批量创建pw
	 */
	private String buildPWBatch() {
		SiteService_MB siteService = null;
		PwInfoService_MB pwService = null;
		try {
			int num = Integer.parseInt(this.ptnSpinnerNumber.getTxtData());
			Code payload = (Code)((ControlKeyValue) this.payloadJcbBox.getSelectedItem()).getObject();
			ControlKeyValue pwTypeValue = (ControlKeyValue)this.pwTypeJcbBox.getSelectedItem();
			siteService = (SiteService_MB)ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			DispatchUtil pwOperationImpl = new DispatchUtil(RmiKeys.RMI_PW);
			String aIP = siteService.getSiteID(this.siteId_a);
			String zIP = siteService.getSiteID(this.siteId_z);
			List<PwInfo> pwInfoList = new ArrayList<PwInfo>();
			List<PwInfo> pwList = this.getpwList();
			num -= pwList.size();
			String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			if(num > 0){
				for(int i = 1; i <= num; i++){
					PwInfo pwInfo = new PwInfo();
					pwInfo.setASiteId(this.siteId_a);
					pwInfo.setZSiteId(this.siteId_z);
					pwInfo.setQosList(this.getQosList());
					pwInfo.setAoppositeId(aIP);
					pwInfo.setZoppositeId(zIP);
					pwInfo.setInlabelValue(0);
					pwInfo.setOutlabelValue(0);
					pwInfo.setTunnelId(this.useTunnelList.get(0).getTunnelId());
					pwInfo.setPayload(payload.getId());
					pwInfo.setIsSingle(0);
					pwInfo.setQosModel(0);//模式
					pwInfo.setOamList(this.getOamList());
					pwInfo.setType((EPwType) pwTypeValue.getObject());
					pwInfo.setCreateTime(DateUtil.getDate(DateUtil.FULLTIME));
					pwInfo.setCreateUser(ConstantUtil.user.getUser_Name());
					pwInfo.setPwStatus(this.isActiveCBox.isSelected() == true ? EActiveStatus.ACTIVITY.getValue() : EActiveStatus.UNACTIVITY.getValue());
					pwInfo.setBusinessType(0+"");//业务类型为普通
					pwInfo.setaSourceMac("00-00-00-33-44-55");
					pwInfo.setAtargetMac("00-00-00-AA-BB-CC");
					pwInfo.setZtargetMac("00-00-00-33-44-55");
					pwInfo.setzSourceMac("00-00-00-AA-BB-CC");
					pwInfo.setPwName(pwInfo.getType().toString()+"/"+System.currentTimeMillis()+"_Copy" + i);
					pwInfoList.add(pwInfo);
				}
				result = pwOperationImpl.excuteInsert(pwInfoList);
			}
			// 添加日志记录
			pwService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			for (PwInfo pwInfo : pwInfoList) {
				if(result.contains(ResultString.CONFIG_SUCCESS)){
					pwInfo = pwService.selectBypwid_notjoin(pwInfo);
					this.insertOpeLog(EOperationLogType.PWINSERT.getValue(), result, null, pwInfo);
				}
			}
			return result;
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally {
			UiUtil.closeService_MB(siteService);
			UiUtil.closeService_MB(pwService);
		}
		return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
	}
	
	/**
	 * 批量创建ces
	 */
	private String buildCESBatch() {
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			int num = Integer.parseInt(this.ptnSpinnerNumber.getTxtData());
			List<PwInfo> pwList = this.getpwList();
			if(pwList.size() >= num){
				DispatchUtil cesDispatch = new DispatchUtil(RmiKeys.RMI_CES);
				List<Integer> aPortIdList = this.getPortIdList(portTable_a);
				List<Integer> zPortIdList = this.getPortIdList(portTable_z);
				List<CesInfo> cesInfoList = new ArrayList<CesInfo>();
				for(int i = 0; i < num; i++){
					CesInfo cesInfo = new CesInfo();
					cesInfo.setName(this.nameTextField.getText()+"_Copy" + (i+1));
					cesInfo.setServiceType(EServiceType.CES.getValue());
					cesInfo.setActiveStatus(this.isActiveCBox.isSelected() == true ? EActiveStatus.ACTIVITY.getValue() : EActiveStatus.UNACTIVITY.getValue());
					cesInfo.setCreateUser(ConstantUtil.user.getUser_Name());
					cesInfo.setCreateTime(DateUtil.getDate(DateUtil.FULLTIME));
					cesInfo.setaSiteId(this.siteId_a);
					cesInfo.setzSiteId(this.siteId_z);
					cesInfo.setPwId(pwList.get(i).getPwId());
					ControlKeyValue controlKeyValue = (ControlKeyValue) this.pwTypeJcbBox.getSelectedItem();
					EPwType pwType = (EPwType) controlKeyValue.getObject();
					cesInfo.setCestype(pwType.getValue());
					cesInfo.setaAcId(aPortIdList.get(i));
					cesInfo.setzAcId(zPortIdList.get(i));
					if(cesInfo.getaAcId() != 0 && cesInfo.getzAcId() != 0){
						cesInfoList.add(cesInfo);
					}
				}
				result = cesDispatch.excuteInsert(cesInfoList);
				// 添加日志记录
				 for (CesInfo ces : cesInfoList) {
					 this.getAcName(ces);
					 AddOperateLog.insertOperLog(this.saveBtn, EOperationLogType.CESINSERT.getValue(), result, 
							 null, ces, ces.getaSiteId(), ces.getName(), "ces");
					 AddOperateLog.insertOperLog(this.saveBtn, EOperationLogType.CESINSERT.getValue(), result, 
							 null, ces, ces.getzSiteId(), ces.getName(), "ces");
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return result;
	}
	
	private void getAcName(CesInfo ces) throws Exception{
		if(ces.getCestype() == ECesType.PDH.getValue()){
			ces.setaAcName(initPDHPort(ces.getaSiteId(),ces.getaAcId()).getPortName());
			ces.setzAcName(initPDHPort(ces.getzSiteId(),ces.getzAcId()).getPortName());
		}else if(ces.getCestype() == ECesType.SDH.getValue()){
			ces.setaAcName(initSDHPort(ces.getaSiteId(),ces.getaAcId()).getTimeslotname());
			ces.setzAcName(initSDHPort(ces.getzSiteId(),ces.getzAcId()).getTimeslotname());
		}else if(ces.getCestype() == ECesType.PDHSDH.getValue()){
			ces.setaAcName(initPDHPort(ces.getaSiteId(),ces.getaAcId()).getPortName());
			ces.setzAcName(initSDHPort(ces.getzSiteId(),ces.getzAcId()).getTimeslotname());
		}else if(ces.getCestype() == ECesType.SDHPDH.getValue()){
			ces.setaAcName(initSDHPort(ces.getaSiteId(),ces.getaAcId()).getTimeslotname());
			ces.setzAcName(initPDHPort(ces.getzSiteId(),ces.getzAcId()).getPortName());
		}
	}
	
	/**
	 * 查询pdh端口
	 * @param siteId 网元ID
	 * @param portId 端口ID
	 * @return
	 * @throws Exception
	 */
	private PortInst initPDHPort(int siteId ,int portId) throws Exception {
		PortInst portInst = new PortInst();
		PortService_MB portServiceMB = null;
		try {
			portServiceMB = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			portInst.setPortId(portId);
			portInst = portServiceMB.select(portInst).get(0);
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(portServiceMB);
		}
		return portInst;
	}
	
	/**
	 * 查询sdh端口
	 * @param siteId 网元ID
	 * @param portId 端口ID
	 * @return
	 * @throws Exception
	 */
	private PortStmTimeslot initSDHPort(int siteId ,int portId) throws Exception {
		PortStmTimeslotService_MB portStmTimeslotService = (PortStmTimeslotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTSTMTIMESLOT);
		PortStmTimeslot p = null;
		try {
			p = portStmTimeslotService.selectById(portId);
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(portStmTimeslotService);
		}
		return p;
	}
	
	/**
	 * 获取可用的pdh类型的pw集合
	 */
	private List<PwInfo> getpwList() {
		PwInfoService_MB pwService = null;
		List<PwInfo> pwList = new ArrayList<PwInfo>();
		try {
			pwService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			PwInfo pw = new PwInfo();
			pw.setASiteId(this.siteId_a);
			pw.setZSiteId(this.siteId_z);
			pw.setType(EPwType.PDH);
			pw.setBusinessType("0");
			pw.setTunnelId(this.pwInfo.getTunnelId());
			pw.setPwStatus(EActiveStatus.ACTIVITY.getValue());
			List<PwInfo> pwListCondition = pwService.select(pw);
			if(!pwListCondition.isEmpty()){
				for (PwInfo pwInfo : pwListCondition) {
					if(pwInfo.getRelatedServiceId() == 0){
						pwList.add(pwInfo);
					}
				}
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(pwService);
		}
		return pwList;
	}

	/**
	 * 获取界面端口id
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getPortIdList(ViewDataTable<CesPortInfo> portTable) {
		List<CesPortInfo> cesportInfoList = portTable.getDataBox().getAllElements();
		List<Integer> portIdList = new ArrayList<Integer>();
		for (CesPortInfo cesportInfo : cesportInfoList) {
			if (cesportInfo.getE1PortInst() != null) {
				portIdList.add(cesportInfo.getE1PortInst().getPortId());
			}
		}
		return portIdList;
	}
	
	/**
	 * 验证界面值
	 */
	private boolean verify() {
		OamInfoService_MB oamInfoService = null;
		try {
			if (this.nameTextField.getText().trim().length() == 0) {
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_NOT_FULL));
				return false;
			}
			if(tunnelTopology.getSiteA() == null){
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_PLEASE_SELECT_A));
				return false;
			}else if(tunnelTopology.getSiteZ() == null){
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_PLEASE_SELECT_Z));
				return false;
			}
			oamInfoService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
			for(Tunnel tunnel : this.useTunnelList){
				// 验证改tunnel上是否有lck告警
				OamMepInfo oamMepInfo = new OamMepInfo();
				oamMepInfo.setObjId(tunnel.getTunnelId());
				oamMepInfo.setObjType("TUNNEL_TEST");
				oamMepInfo.setLck(true);
				if((oamInfoService.selectByOamMepInfo(oamMepInfo).size()>0)){
					DialogBoxUtil.errorDialog(this, tunnel.getTunnelName()+ResourceUtil.srcStr(StringKeysTip.TIP_TUNNEL_LCK));
					return false;
				}
			}
			// 对OAM、QoS作校验
			if (this.getQosList() == null || this.getQosList().size() == 0) {
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_QOS_FILL));
				return false;
			}
			// 对端口数量做校验
			int batchCount = 0;
			try {
				batchCount = Integer.parseInt(this.ptnSpinnerNumber.getTxtData());
			} catch (Exception e) {
				ExceptionManage.dispose(e, this.getClass());
			}
			int aPortCount = this.portTable_a.getDataBox().getAllElements().size();
			int zPortCount = this.portTable_z.getDataBox().getAllElements().size();
			if(batchCount != aPortCount || batchCount != zPortCount){
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_PORTCOUNT_NOT_EQUAL));
				return false;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(oamInfoService);
		}
		return true;
	}

	/**
	 * 初始化pw类型下拉列表
	 */
	private void initPwTypeComBox() {
		DefaultComboBoxModel defaultComboBoxModel = (DefaultComboBoxModel)this.pwTypeJcbBox.getModel();
		for (EPwType type : EPwType.values()) {
			if (type.getValue() == EPwType.PDH.getValue())
				defaultComboBoxModel.addElement(new ControlKeyValue(type.getValue() + "", type.toString(), type));
		}
		this.pwTypeJcbBox.setModel(defaultComboBoxModel);
	}
	
	public void loadPortTable_a(List<CesPortInfo> cesPortInfoList, int siteId) {
		getPortTable_a().clear();
		setaSiteId(siteId);
		getPortTable_a().initData(cesPortInfoList);
	}

	public void loadPortTable_z(List<CesPortInfo> cesPortInfoList, int siteId) {
		getPortTable_z().clear();
		setzSiteId(siteId);
		getPortTable_z().initData(cesPortInfoList);
	}
	
	public void clearPortTableA(){
		getPortTable_a().clear();
	}
	
	public void clearPortTableZ(){
		getPortTable_z().clear();
	}
	
	private JLabel lblName;//名称
	private JTextField nameTextField;
	private JButton autoNamingBtn;//自动命名按钮
	private JLabel lblPwType;//pw类型
	private JComboBox pwTypeJcbBox;
	private JLabel lblPayload;//负载净荷
	private JComboBox payloadJcbBox;
	private JLabel lblZPort;//a端端口
	private JLabel lblAPort;//z端端口
	private ViewDataTable<CesPortInfo> portTable_a;
	private ViewDataTable<CesPortInfo> portTable_z;
	private JScrollPane jscrollPane_portTable_a;
	private JScrollPane jscrollPane_portTable_z;
	private JButton aPortBtn;//选择a端端口
	private JButton zPortBtn;//选择z端端口
	private JLabel lblNumber;//创建数量
	private PtnSpinner ptnSpinnerNumber;
	private JLabel lblIsActive;//激活状态
	private JCheckBox isActiveCBox;
	private PtnButton saveBtn;//保存按钮
	private JLabel lblQos;//QoS
	private JButton qosConfigBtn;
	private JTextField qosTextField;
	private JLabel lblMessage;
	private JSplitPane jSplitPanel;
	private JPanel leftPanel;
	public JComboBox getPwType() {
		return this.pwTypeJcbBox;
	}

	public ViewDataTable<CesPortInfo> getPortTable_a() {
		return portTable_a;
	}

	public ViewDataTable<CesPortInfo> getPortTable_z() {
		return portTable_z;
	}

	public void setaSiteId(int aSiteId) {
		this.siteId_a = aSiteId;
	}

	public void setzSiteId(int zSiteId) {
		this.siteId_z = zSiteId;
	}
}
