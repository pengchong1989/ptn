package com.nms.ui.ptn.ne.ecn.ecninterfaces.ospf.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.nms.db.bean.ptn.ecn.CCN;
import com.nms.db.bean.ptn.ecn.MCN;
import com.nms.db.bean.ptn.ecn.OSPFAREAInfo;
import com.nms.db.bean.ptn.ecn.OSPFInterface;
import com.nms.db.bean.system.code.Code;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.ptn.ecn.OSPFAREAService_MB;
import com.nms.model.ptn.ecn.OSPFInterfaceService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTitle;

public class SaveOSPFInterfacesDialog extends PtnDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OSPFInterface ospfInterface;
	private OSFPInterfacePortPanel panel;
	private boolean isUpdate = false;

	public SaveOSPFInterfacesDialog(OSPFInterface ospfInterface, OSFPInterfacePortPanel panel) {
		this.setModal(true);
		try {
			initComponents();
			this.panel = panel;
			setLayout();
			addListener();
			if (ospfInterface != null) {
				this.ospfInterface = ospfInterface;
				super.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_UPDATE_OSPF_INTERFACE));
				initUpdateDate();
			} else {
				super.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_OSPF_INTERFACE));
				this.ospfInterface = new OSPFInterface();
				this.ospfInterface.setActiveStatus(EActiveStatus.ACTIVITY.getValue());
				initDate();
			}
			this.showWindow();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

	}

	private void initComponents() {
		jPanel = new JPanel();
		portname = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT_NAME));
		portnamecom = new JComboBox();
		type = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TYPE));
		typecom = new JComboBox();
		cost = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_COST));
		costtext = new JTextField();
		hellointerval = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_HELLO_INTERVAL));
		hellointervaltext = new JTextField();
		retransmissioninterval = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_RETRANSMISSION_INTERVAL));
		retransmissionintervaltext = new JTextField();
		authenticationtype = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_AUTHENTICATION_TYPE));
		authenticationtypecom = new JComboBox();
		ospfareaid = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OSPF_AREA_ID));
		ospfareaidcom = new JComboBox();
		model = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PASSIVE_MODE));
		modelistrue = new JCheckBox();
		priority = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PRIORITY));
		prioritytext = new JTextField();
		deadinterval = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_DEAD_INTERVAL));
		deadintervaltext = new JTextField();
		message = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TRANSMISSION_DELAY));
		messagetext = new JTextField();
		btnsave = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE),false);
		btncanel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
	}

	private void setLayout() {

		Dimension dimension = new Dimension(480, 310);
		this.setPreferredSize(dimension);
		this.setMinimumSize(dimension);

		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 70, 120, 30, 50, 120 };
		layout.columnWeights = new double[] { 0, 0, 0, 0 };
		layout.rowHeights = new int[] { 35, 35, 35, 35, 35, 35, 35, 35, 35, 35 };
		layout.rowWeights = new double[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.2 };
		this.jPanel.setLayout(layout);
		this.add(jPanel);

		GridBagConstraints c = new GridBagConstraints();

		/** 第一行 端口名称 OSPF区域ID */
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		c.anchor = GridBagConstraints.WEST;
		layout.setConstraints(portname, c);
		this.jPanel.add(portname);
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(portnamecom, c);
		this.jPanel.add(portnamecom);
		c.gridx = 3;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(ospfareaid, c);
		this.jPanel.add(ospfareaid);
		c.gridx = 4;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(ospfareaidcom, c);
		this.jPanel.add(ospfareaidcom);

		/** 第二行 类型 被动模式 */
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(type, c);
		this.jPanel.add(type);
		c.gridx = 1;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(typecom, c);
		this.jPanel.add(typecom);
		c.gridx = 3;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(model, c);
		this.jPanel.add(model);
		c.gridx = 4;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(modelistrue, c);
		this.jPanel.add(modelistrue);

		/** 第三行 代价值 优先级 */
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(cost, c);
		this.jPanel.add(cost);
		c.gridx = 1;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(costtext, c);
		this.jPanel.add(costtext);
		c.gridx = 3;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(priority, c);
		this.jPanel.add(priority);
		c.gridx = 4;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(prioritytext, c);
		this.jPanel.add(prioritytext);

		/** 第四行 Hello间隔 Dead间隔 */
		c.gridx = 0;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(hellointerval, c);
		this.jPanel.add(hellointerval);
		c.gridx = 1;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(hellointervaltext, c);
		this.jPanel.add(hellointervaltext);
		c.gridx = 3;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(deadinterval, c);
		this.jPanel.add(deadinterval);
		c.gridx = 4;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(deadintervaltext, c);
		this.jPanel.add(deadintervaltext);

		/** 第五行 重传间隔 报文传输延时 */
		c.gridx = 0;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(retransmissioninterval, c);
		this.jPanel.add(retransmissioninterval);
		c.gridx = 1;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(retransmissionintervaltext, c);
		this.jPanel.add(retransmissionintervaltext);
		c.gridx = 3;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(message, c);
		this.jPanel.add(message);
		c.gridx = 4;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(messagetext, c);
		this.jPanel.add(messagetext);

		/** 第六行 认证类型 密码 */
		c.gridx = 0;
		c.gridy = 6;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(authenticationtype, c);
		this.jPanel.add(authenticationtype);
		c.gridx = 1;
		c.gridy = 6;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(authenticationtypecom, c);
		this.jPanel.add(authenticationtypecom);

		/** 第八行 确定按钮空出一行 */
		c.gridx = 3;
		c.gridy = 7;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		layout.setConstraints(btnsave, c);
		this.jPanel.add(btnsave);
		c.gridx = 4;
		c.gridy = 7;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		layout.addLayoutComponent(btncanel, c);
		this.jPanel.add(btncanel);
	}

	private void showWindow() {
		this.setLocation(UiUtil.getWindowWidth(this.getWidth()), UiUtil.getWindowHeight(this.getHeight()));
		this.setVisible(true);
	}

	private void initUpdateDate() throws Exception {
		try {
			initDateCom();
			costtext.setText(this.ospfInterface.getCost() + "");
			hellointervaltext.setText(this.ospfInterface.getHello_interval() + "");
			retransmissionintervaltext.setText(this.ospfInterface.getRetransmit_interval() + "");
			modelistrue.setSelected(this.ospfInterface.isPassive());
			prioritytext.setText(this.ospfInterface.getPrioriy() + "");
			deadintervaltext.setText(this.ospfInterface.getDead_interval() + "");
			messagetext.setText(this.ospfInterface.getTransmit_delay()+"");
			isUpdate = true;
		} catch (Exception e) {
			throw e;
		}
	}

	private void initDate() throws Exception {

		try {
			initDateCom();
			costtext.setText("0");
			hellointervaltext.setText("10");
			retransmissionintervaltext.setText("5");
			modelistrue.setSelected(false);
			prioritytext.setText("100");
			deadintervaltext.setText("40");
			messagetext.setText("1");

		} catch (Exception e) {
			throw e;
		}
	}

	private void initDateCom() throws Exception {
		
		OSPFInterfaceService_MB ospfInterfaceService = null;
		OSPFAREAService_MB oSPFAREAService = null;
		
		try {
			DefaultComboBoxModel comboBoxModelPort = new DefaultComboBoxModel();
			DefaultComboBoxModel comboBoxModelArea = new DefaultComboBoxModel();
//			CCNCXServiceImpl ccnService = new CCNCXServiceImpl();
			
			ospfInterfaceService = (OSPFInterfaceService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OSPFINTERFACE);
			List<CCN> ccnList = new ArrayList<CCN>();
			CCN ccn = new CCN();
			ccn.setNeId(ConstantUtil.siteId + "");
//			MCNCXServiceImpl mcnService = new MCNCXServiceImpl();
			List<MCN> mcnList = new ArrayList<MCN>();
			MCN mcn = new MCN();
			mcn.setNeId(ConstantUtil.siteId + "");
			List<OSPFAREAInfo> areaList = new ArrayList<OSPFAREAInfo>();
//			ccnList = ccnService.excutionQuery(ccn);
//			mcnList = mcnService.excutionQuery(mcn);
			
			List<OSPFInterface> ecn_interfacesList = ospfInterfaceService.queryByCondition(new OSPFInterface());
			oSPFAREAService = (OSPFAREAService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OSPFAREA);
			areaList = oSPFAREAService.queryByNeID(ConstantUtil.siteId);
			
			if (mcnList != null) {
				comboBoxModelPort.addElement("MCN/1");
			}
			if (ccnList != null) {
				for (int i = 0; i < ccnList.size(); i++) {
					int j = i + 1;
					comboBoxModelPort.addElement("CCN/" + j);
				}
			}
			if (areaList != null) {
				for (int i = 0; i < areaList.size(); i++) {
					comboBoxModelArea.addElement(areaList.get(i).getArea_range());
				}
			}
			/**
			 * 去除已经存在的 ospf接口（查库）
			 */
			if(ecn_interfacesList!=null&&ecn_interfacesList.size()>0){
				for(OSPFInterface ospfInfo:ecn_interfacesList){
					comboBoxModelPort.removeElement(ospfInfo.getInterfaceName());
				}
			}
			portnamecom.setModel(comboBoxModelPort);
			ospfareaidcom.setModel(comboBoxModelArea);
			super.getComboBoxDataUtil().comboBoxData(typecom, "OSPFINTERFACETYPE");
			super.getComboBoxDataUtil().comboBoxData(authenticationtypecom, "AUYHENTICATIONTYPE");
			if(portnamecom.getSelectedItem()==null){
				btnsave.setEnabled(false);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(oSPFAREAService);
			UiUtil.closeService_MB(ospfInterfaceService);
		}
	}

	private void addListener() {
		btnsave.addActionListener(new MyActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SaveOSPFInterfacesDialog.this.savaPortStmTimeslot();
			}

			@Override
			public boolean checking() {
				
				return true;
			}
		});

		btncanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SaveOSPFInterfacesDialog.this.dispose();
			}
		});
	}

	public void savaPortStmTimeslot() {

		DispatchUtil ospfInterfaceDispatch = null;
		String result = null;
		try {
			ospfInterface.setNeId(ConstantUtil.siteId + "");
			
			ospfInterface.setInterfaceName(portnamecom.getSelectedItem().toString());
			Code cod=(Code) ((ControlKeyValue) typecom.getSelectedItem()).getObject();
			ospfInterface.setType(cod.getCodeValue());
			ospfInterface.setCost(Integer.valueOf(costtext.getText()));
			ospfInterface.setHello_interval(Integer.valueOf(hellointervaltext.getText()));
			ospfInterface.setDead_interval(Integer.valueOf(deadintervaltext.getText()));
			ospfInterface.setRetransmit_interval(Integer.valueOf(retransmissionintervaltext.getText()));
			ospfInterface.setAuthentication_type(((Code) (((ControlKeyValue) authenticationtypecom.getSelectedItem()).getObject())).getCodeName());
			ospfInterface.setArea(ospfareaidcom.getSelectedItem().toString());
			ospfInterface.setPassive(modelistrue.isSelected() ? true : false);
			ospfInterface.setPrioriy(Integer.valueOf(prioritytext.getText()));
			ospfInterface.setTransmit_delay(Integer.valueOf(messagetext.getText()));

			ospfInterfaceDispatch = new DispatchUtil(RmiKeys.RMI_OSPFINTERFACE);
			if (isUpdate) {
				result = ospfInterfaceDispatch.excuteUpdate(ospfInterface);
			} else {
				result = ospfInterfaceDispatch.excuteInsert(ospfInterface);
			}
			DialogBoxUtil.succeedDialog(this, result);
		
			this.panel.getController().refresh();
			this.dispose();

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			ospfInterfaceDispatch = null;
			ospfInterface = null;
		}

	}

	private JLabel portname;
	private JComboBox portnamecom;
	private JLabel type;
	private JComboBox typecom;
	private JLabel cost;
	private JTextField costtext;
	private JLabel hellointerval;
	private JTextField hellointervaltext;
	private JLabel retransmissioninterval;
	private JTextField retransmissionintervaltext;
	private JLabel authenticationtype;
	private JComboBox authenticationtypecom;
	private JLabel ospfareaid;
	private JComboBox ospfareaidcom;
	private JLabel model;
	private JCheckBox modelistrue;
	private JLabel priority;
	private JTextField prioritytext;
	private JLabel deadinterval;
	private JTextField deadintervaltext;
	private JLabel message;
	private JTextField messagetext;
	private PtnButton btnsave;
	private JButton btncanel;
	private JPanel jPanel;
}
