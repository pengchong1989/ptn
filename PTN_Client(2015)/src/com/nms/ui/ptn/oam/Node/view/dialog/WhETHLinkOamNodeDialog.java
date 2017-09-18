package com.nms.ui.ptn.oam.Node.view.dialog;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.oam.OamLinkInfo;
import com.nms.db.enums.EOperationLogType;
import com.nms.db.enums.EServiceType;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.oam.OamInfoService_MB;
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
import com.nms.ui.manager.control.PtnTextField;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.oam.Node.controller.WhETHLinkOAMController;

public class WhETHLinkOamNodeDialog extends PtnDialog {
	private static final long serialVersionUID = -5677652809768711073L;
	private JLabel portLabel;
	private JComboBox portComboBox;
	private JPanel oamConfigPanel;
	private JButton configRecover;
	private JLabel oamEnableLabel;
	private JCheckBox oamEnableCheckBox;
	private JLabel workWayLabel;
	private JComboBox workWayComboBox;
	private JLabel remoteLoopLabel;
	private JCheckBox remoteLoopCheckBox;
	private JLabel loopTimeOutLabel;
	private PtnTextField loopTimeOutField;
	private JLabel linkEnableLabel;
	private PtnTextField linkEnableCheckBox;// 协议修改为环回请求重传次数
	private JLabel variableLabel;
	private JCheckBox variableCheckBox;// 协议修改为对端环回
	private JLabel oamMessageLengthLabel;
	private JCheckBox oamMessageLengthField;// 协议修改为对端变量获取
	private JLabel organicIdLabel;
	private JCheckBox organicIdField;// 协议修改为Dying gasp使能
	private JLabel factoryInfoLabel;
	private JCheckBox factoryInfoField;// 协议修改为本端critical事件使能

	private JPanel oamEventPanel;
	private JButton eventRecoverButton;
	private JLabel errorSignEventCycleLabel;
	private PtnTextField errorSignEventCycleField;
	private JLabel errorSignEventLimitLabel;
	private PtnTextField errorSignEventLimitField;
	private JLabel errorFrameEventCycleLabel;
	private PtnTextField errorFrameEventCycleField;
	private JLabel errorFrameEventLimitLabel;
	private PtnTextField errorFrameEventLimitField;
	private JLabel errorFrameCycleEventCycleLabel;
	private PtnTextField errorFrameCycleEventCycleField;
	private JLabel errorFrameCycleEventLimitLabel;
	private PtnTextField errorFrameCycleEventLimitField;
	private JLabel errorSecondEventCycleLabel;
	private PtnTextField errorSecondEventCycleField;
	private JLabel errorSecondEventLimitLabel;
	private PtnTextField errorSecondEventLimitField;
	private JLabel oamFrameLabel;
	private PtnTextField oamFrameField;
	private JPanel buttonPanel;
	private JPanel portPanel;
	private PtnButton confirm;
	private JButton cancel;

	private WhETHLinkOAMController controller;
	private OamInfo oamInfo;
	private JLabel lblMessage;
	private OamLinkInfo linkOamBefore;//保留修改前的数据，log日志用到
	public WhETHLinkOamNodeDialog(OamInfo info) {
		this.setModal(true);
		try {
			initComponent();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		setComponentLayout();
		addListener();
		controller = new WhETHLinkOAMController(this);
		intalCombox();
		configRecover.doClick();
		eventRecoverButton.doClick();
		if (info != null) {
			oamInfo = info;
			this.linkOamBefore = new OamLinkInfo();
			CoderUtils.copy(oamInfo.getOamLinkInfo(), this.linkOamBefore);
			setValue();
			updateValue(portComboBox,oamInfo);
		}else{
			intalPortCombox(portComboBox);
		}

	}

	private void initComponent() throws Exception {
		this.lblMessage = new JLabel();
		portLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT));
		portComboBox = new JComboBox();
		confirm = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM),true);
		oamConfigPanel = new JPanel();
		oamConfigPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_CONFIGOAM)));
		configRecover = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SET_DEFAULT));
		oamEnableLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_AGREEMENT));
		oamEnableCheckBox = new JCheckBox();
		workWayLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_WORK_MODEL));
		workWayComboBox = new JComboBox();
		remoteLoopLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_FAR_LOOP_YESORNO));
		remoteLoopCheckBox = new JCheckBox();
		loopTimeOutLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_LOOP_TIME));
		loopTimeOutField = new PtnTextField(true, PtnTextField.TYPE_INT,PtnTextField.INT_MAXLENGTH, this.lblMessage, this.confirm, this);
		setValidate(loopTimeOutField, 15, 1);
		linkEnableLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_LOOP_COUNT));
		linkEnableCheckBox = new PtnTextField(true, PtnTextField.TYPE_INT,PtnTextField.INT_MAXLENGTH, this.lblMessage, this.confirm, this);
		setValidate(linkEnableCheckBox, 5, 1);
		variableLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OPPOSITE_LOOP));
		variableCheckBox = new JCheckBox();
		oamMessageLengthLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OPPOSITE_VARIABLEL));
		oamMessageLengthField = new JCheckBox();
		organicIdLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_DYING_GASP));
		organicIdField = new JCheckBox();
		factoryInfoLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_CRITICAL));
		factoryInfoField = new JCheckBox();

		oamEventPanel = new JPanel();
		oamEventPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_EVENT)));
		eventRecoverButton = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SET_DEFAULT));
		errorSignEventCycleLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_ERROR_SIGNAL_EVENT_PERIOD));
		errorSignEventCycleField = new PtnTextField(true, PtnTextField.TYPE_INT,PtnTextField.INT_MAXLENGTH, this.lblMessage, this.confirm, this);
		setValidate(errorSignEventCycleField, 60, 1);
		errorSignEventLimitLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_ERROR_SIGNAL_EVENT_THRESHOLD));
		errorSignEventLimitField = new PtnTextField(true, PtnTextField.TYPE_INT,PtnTextField.INT_MAXLENGTH, this.lblMessage, this.confirm, this);
		setValidate(errorSignEventLimitField, 65535, 0);
		errorFrameEventCycleLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_ERROR_FRAME_EVENT_PERIOD));
		errorFrameEventCycleField = new PtnTextField(true, PtnTextField.TYPE_INT,PtnTextField.INT_MAXLENGTH, this.lblMessage, this.confirm, this);
		setValidate(errorFrameEventCycleField, 60, 1);
		errorFrameEventLimitLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_ERROR_FRAME_EVENT_THRESHOLD));
		errorFrameEventLimitField = new PtnTextField(true, PtnTextField.TYPE_INT,PtnTextField.INT_MAXLENGTH, this.lblMessage, this.confirm, this);
		setValidate(errorFrameEventLimitField, 65535, 0);
		errorFrameCycleEventCycleLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_ERROR_FRAME_PERIOD_EVENT_PERIOD));
		errorFrameCycleEventCycleField = new PtnTextField(true, PtnTextField.TYPE_INT,PtnTextField.INT_MAXLENGTH, this.lblMessage, this.confirm, this);
		setValidate(errorFrameCycleEventCycleField, 600, 1);
		errorFrameCycleEventLimitLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_ERROR_FRAME_PERIOD_EVENT_THRESHOLD));
		errorFrameCycleEventLimitField = new PtnTextField(true, PtnTextField.TYPE_INT,PtnTextField.INT_MAXLENGTH, this.lblMessage, this.confirm, this);
		setValidate(errorFrameCycleEventLimitField, 65535, 0);
		errorSecondEventCycleLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_ERROR_SECONDS_EVENT_PERIOD));
		errorSecondEventCycleField = new PtnTextField(true, PtnTextField.TYPE_INT,PtnTextField.INT_MAXLENGTH, this.lblMessage, this.confirm, this);
		setValidate(errorSecondEventCycleField, 900, 10);
		errorSecondEventLimitLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_ERROR_SECONDS_EVENT_THRESHOLD));
		errorSecondEventLimitField = new PtnTextField(true, PtnTextField.TYPE_INT,PtnTextField.INT_MAXLENGTH, this.lblMessage, this.confirm, this);
		setValidate(errorSecondEventLimitField, 65535, 0);
		oamFrameLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_FRAME));
		oamFrameField = new PtnTextField(true, PtnTextField.TYPE_INT,PtnTextField.INT_MAXLENGTH, this.lblMessage, this.confirm, this);
		oamFrameField.setText(10+"");
		setValidate(oamFrameField, 100, 1);
		buttonPanel = new JPanel();
		portPanel = new JPanel();
		cancel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		

	}

	private void setComponentLayout() {
		this.portComboxLayout();
		this.buttonPanelLayout();
		this.setOamConfigPanelLayout();
		this.setOamEventPanelLayout();
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 80, 80, 80, 80 };
		layout.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0 };
		layout.rowHeights = new int[] { 50, 150, 30 };
		layout.rowWeights = new double[] { 0.5, 1.0, 0.0 };
		this.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(portPanel, c);
		this.add(portPanel);

		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(oamConfigPanel, c);
		this.add(oamConfigPanel);
		c.gridx = 2;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(oamEventPanel, c);
		this.add(oamEventPanel);

		c.gridx = 2;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(buttonPanel, c);
		this.add(buttonPanel);

	}

	private void portComboxLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] {5, 75, 80};
		componentLayout.columnWeights = new double[] { 1.0, 1.0 ,1.0};
		this.portPanel.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 5, 5);
		componentLayout.setConstraints(this.portLabel, c);
		this.portPanel.add(this.portLabel);

		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		componentLayout.setConstraints(this.portComboBox, c);
		this.portPanel.add(this.portComboBox);
	}

	private void setOamConfigPanelLayout() {
		GridBagLayout configLayout = new GridBagLayout();
		configLayout.columnWidths = new int[] { 80, 80 };
		configLayout.columnWeights = new double[] { 1.0, 1.0 };
		configLayout.rowHeights = new int[] { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 };
		configLayout.rowWeights = new double[] { 0.0, 0.03, 0.03, 0.03, 0.03, 0.03, 0.03, 0.03, 0.03, 0.03, 0.03, 0.03 };
		oamConfigPanel.setLayout(configLayout);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(5, 5, 5, 5);
		configLayout.setConstraints(configRecover, c);
		oamConfigPanel.add(configRecover);
		c.insets = new Insets(5, 5, 5, 5);
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		configLayout.setConstraints(oamEnableLabel, c);
		oamConfigPanel.add(oamEnableLabel);
		c.gridx = 1;
		c.gridy = 1;
		configLayout.setConstraints(oamEnableCheckBox, c);
		oamConfigPanel.add(oamEnableCheckBox);

		c.gridx = 0;
		c.gridy = 2;
		configLayout.setConstraints(workWayLabel, c);
		oamConfigPanel.add(workWayLabel);
		c.gridx = 1;
		c.gridy = 2;
		configLayout.setConstraints(workWayComboBox, c);
		oamConfigPanel.add(workWayComboBox);

		c.gridx = 0;
		c.gridy = 3;
		configLayout.setConstraints(remoteLoopLabel, c);
		oamConfigPanel.add(remoteLoopLabel);
		c.gridx = 1;
		c.gridy = 3;
		configLayout.setConstraints(remoteLoopCheckBox, c);
		oamConfigPanel.add(remoteLoopCheckBox);

		c.gridx = 0;
		c.gridy = 4;
		configLayout.setConstraints(loopTimeOutLabel, c);
		oamConfigPanel.add(loopTimeOutLabel);
		c.gridx = 1;
		c.gridy = 4;
		configLayout.setConstraints(loopTimeOutField, c);
		oamConfigPanel.add(loopTimeOutField);

		c.gridx = 0;
		c.gridy = 5;
		configLayout.setConstraints(linkEnableLabel, c);
		oamConfigPanel.add(linkEnableLabel);
		c.gridx = 1;
		c.gridy = 5;
		configLayout.setConstraints(linkEnableCheckBox, c);
		oamConfigPanel.add(linkEnableCheckBox);

		c.gridx = 0;
		c.gridy = 6;
		configLayout.setConstraints(variableLabel, c);
		oamConfigPanel.add(variableLabel);
		c.gridx = 1;
		c.gridy = 6;
		configLayout.setConstraints(variableCheckBox, c);
		oamConfigPanel.add(variableCheckBox);

		c.gridx = 0;
		c.gridy = 7;
		configLayout.setConstraints(oamMessageLengthLabel, c);
		oamConfigPanel.add(oamMessageLengthLabel);
		c.gridx = 1;
		c.gridy = 7;
		configLayout.setConstraints(oamMessageLengthField, c);
		oamConfigPanel.add(oamMessageLengthField);

		c.gridx = 0;
		c.gridy = 8;
		configLayout.setConstraints(organicIdLabel, c);
		oamConfigPanel.add(organicIdLabel);
		c.gridx = 1;
		c.gridy = 8;
		configLayout.setConstraints(organicIdField, c);
		oamConfigPanel.add(organicIdField);

		c.gridx = 0;
		c.gridy = 9;
		configLayout.setConstraints(factoryInfoLabel, c);
		oamConfigPanel.add(factoryInfoLabel);
		c.gridx = 1;
		c.gridy = 9;
		configLayout.setConstraints(factoryInfoField, c);
		oamConfigPanel.add(factoryInfoField);
		
		c.gridx = 0;
		c.gridy = 10;
		configLayout.setConstraints(oamFrameLabel, c);
		oamConfigPanel.add(oamFrameLabel);
		c.gridx = 1;
		c.gridy = 10;
		configLayout.setConstraints(oamFrameField, c);
		oamConfigPanel.add(oamFrameField);
	}

	private void setOamEventPanelLayout() {
		GridBagLayout eventPanelLayout = new GridBagLayout();
		eventPanelLayout.columnWidths = new int[] { 80, 80 };
		eventPanelLayout.columnWeights = new double[] { 1.0, 1.0 };
		eventPanelLayout.rowHeights = new int[] { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 };
		eventPanelLayout.rowWeights = new double[] { 0.0, 0.03, 0.03, 0.03, 0.03, 0.03, 0.03, 0.03, 0.03, 0.03 };
		oamEventPanel.setLayout(eventPanelLayout);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		eventPanelLayout.setConstraints(eventRecoverButton, c);
		oamEventPanel.add(eventRecoverButton);
		c.insets = new Insets(5, 5, 5, 5);
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		eventPanelLayout.setConstraints(errorSignEventCycleLabel, c);
		oamEventPanel.add(errorSignEventCycleLabel);
		c.gridx = 1;
		c.gridy = 1;
		eventPanelLayout.setConstraints(errorSignEventCycleField, c);
		oamEventPanel.add(errorSignEventCycleField);

		c.gridx = 0;
		c.gridy = 2;
		eventPanelLayout.setConstraints(errorSignEventLimitLabel, c);
		oamEventPanel.add(errorSignEventLimitLabel);
		c.gridx = 1;
		c.gridy = 2;
		eventPanelLayout.setConstraints(errorSignEventLimitField, c);
		oamEventPanel.add(errorSignEventLimitField);

		c.gridx = 0;
		c.gridy = 3;
		eventPanelLayout.setConstraints(errorFrameEventCycleLabel, c);
		oamEventPanel.add(errorFrameEventCycleLabel);
		c.gridx = 1;
		c.gridy = 3;
		eventPanelLayout.setConstraints(errorFrameEventCycleField, c);
		oamEventPanel.add(errorFrameEventCycleField);

		c.gridx = 0;
		c.gridy = 4;
		eventPanelLayout.setConstraints(errorFrameEventLimitLabel, c);
		oamEventPanel.add(errorFrameEventLimitLabel);
		c.gridx = 1;
		c.gridy = 4;
		eventPanelLayout.setConstraints(errorFrameEventLimitField, c);
		oamEventPanel.add(errorFrameEventLimitField);

		c.gridx = 0;
		c.gridy = 5;
		eventPanelLayout.setConstraints(errorFrameCycleEventCycleLabel, c);
		oamEventPanel.add(errorFrameCycleEventCycleLabel);
		c.gridx = 1;
		c.gridy = 5;
		eventPanelLayout.setConstraints(errorFrameCycleEventCycleField, c);
		oamEventPanel.add(errorFrameCycleEventCycleField);

		c.gridx = 0;
		c.gridy = 6;
		eventPanelLayout.setConstraints(errorFrameCycleEventLimitLabel, c);
		oamEventPanel.add(errorFrameCycleEventLimitLabel);
		c.gridx = 1;
		c.gridy = 6;
		eventPanelLayout.setConstraints(errorFrameCycleEventLimitField, c);
		oamEventPanel.add(errorFrameCycleEventLimitField);

		c.gridx = 0;
		c.gridy = 7;
		eventPanelLayout.setConstraints(errorSecondEventCycleLabel, c);
		oamEventPanel.add(errorSecondEventCycleLabel);
		c.gridx = 1;
		c.gridy = 7;
		eventPanelLayout.setConstraints(errorSecondEventCycleField, c);
		oamEventPanel.add(errorSecondEventCycleField);

		c.gridx = 0;
		c.gridy = 8;
		eventPanelLayout.setConstraints(errorSecondEventLimitLabel, c);
		oamEventPanel.add(errorSecondEventLimitLabel);
		c.gridx = 1;
		c.gridy = 8;
		eventPanelLayout.setConstraints(errorSecondEventLimitField, c);
		oamEventPanel.add(errorSecondEventLimitField);

	}

	private void buttonPanelLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] {90, 20, 20 };
		componentLayout.columnWeights = new double[] { 1.0, 1.0 ,1.0};
		this.buttonPanel.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;

		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.insets = new Insets(5, 5, 5, 5);
		componentLayout.setConstraints(this.lblMessage, c);
		this.buttonPanel.add(this.lblMessage);

		c.gridx = 2;
		c.insets = new Insets(5, 5, 5, 5);
		componentLayout.setConstraints(this.confirm, c);
		this.buttonPanel.add(this.confirm);

		c.gridx = 3;
		c.insets = new Insets(5, 5, 5, 5);
		componentLayout.setConstraints(this.cancel, c);
		this.buttonPanel.add(this.cancel);
	}

	private void addListener() {
		configRecover.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				oamEnableCheckBox.setSelected(false);
				remoteLoopCheckBox.setSelected(false);
				workWayComboBox.setSelectedIndex(0);
				loopTimeOutField.setText("5");
				linkEnableCheckBox.setText("3");
				variableCheckBox.setSelected(true);
				oamMessageLengthField.setSelected(true);
				organicIdField.setSelected(true);
				factoryInfoField.setSelected(true);
				oamFrameField.setText("10");
			}
		});
		eventRecoverButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				errorSignEventCycleField.setText("1");
				errorSignEventLimitField.setText("1");
				errorFrameEventCycleField.setText("1");
				errorFrameEventLimitField.setText("1");
				errorFrameCycleEventCycleField.setText("1");
				errorFrameCycleEventLimitField.setText("1");
				errorSecondEventCycleField.setText("60");
				errorSecondEventLimitField.setText("1");
			}
		});
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		confirm.addActionListener(new MyActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				collectData();
			}

			@Override
			public boolean checking() {
				return true;
			}
		});

	}

	private void intalCombox() {
		intalWorkWayCombox(workWayComboBox);
	}

	private void intalPortCombox(JComboBox combox) {
		DefaultComboBoxModel boxModel = (DefaultComboBoxModel) combox.getModel();
		PortInst portInst = null;
		PortService_MB portService = null;
		List<PortInst> portList = null;
		OamInfo oamInfo = null;
		OamLinkInfo oamLinkInfo = null;
		OamInfoService_MB oamInfoService = null;
		OamInfo info = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			oamInfoService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
			portInst = new PortInst();
			portInst.setSiteId(ConstantUtil.siteId);
			portList = portService.select(portInst);
			for (PortInst inst : portList) {
				if (inst.getNumber() > 0 && !"e1".equals(inst.getPortType()) && !"NC".equals(inst.getPortName())) {
					oamInfo = new OamInfo();
					oamLinkInfo = new OamLinkInfo();
					oamLinkInfo.setObjId(inst.getPortId());
					oamLinkInfo.setSiteId(ConstantUtil.siteId);
					oamLinkInfo.setObjType("LINKOAM");
					oamInfo.setOamLinkInfo(oamLinkInfo);
					info = oamInfoService.queryByCondition(oamInfo, null);
					if (info.getOamLinkInfo().getId()==0) {
						boxModel.addElement(new ControlKeyValue(inst.getPortId() + "", inst.getPortName(), inst));
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			portInst = null;
			UiUtil.closeService_MB(portService);
			UiUtil.closeService_MB(oamInfoService);
			portList = null;

		}

	}

	private void updateValue(JComboBox combox,OamInfo oamInfo) {
		DefaultComboBoxModel boxModel = (DefaultComboBoxModel) combox.getModel();
		PortInst portInst = null;
		PortService_MB portService = null;
		List<PortInst> portList = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			portInst = new PortInst();
			portInst.setSiteId(ConstantUtil.siteId);
			portInst.setPortId(oamInfo.getOamLinkInfo().getObjId());
			portList = portService.select(portInst);
			boxModel.addElement(new ControlKeyValue(portList.get(0).getPortId() + "", portList.get(0).getPortName(), portList.get(0)));
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(portService);
		}
	}

	private void intalWorkWayCombox(JComboBox comboBox) {
		DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) comboBox.getModel();
		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
		map.put(0, ResourceUtil.srcStr(StringKeysObj.STRING_INITIATIVE));
		map.put(1, ResourceUtil.srcStr(StringKeysObj.STRING_PASSIVE));
		for (Integer key : map.keySet()) {
			comboBoxModel.addElement(new ControlKeyValue(key + "", map.get(key)));
		}
	}

	private void setValue() {
		OamLinkInfo oamLinkInfo = oamInfo.getOamLinkInfo();
		portComboBox.setEnabled(false);
		oamEnableCheckBox.setSelected(oamLinkInfo.isOamEnable());
		super.getComboBoxDataUtil().comboBoxSelect(workWayComboBox, oamLinkInfo.getMode() + "");
		remoteLoopCheckBox.setSelected(oamLinkInfo.getRemoteLoop() == 1 ? true : false);
		loopTimeOutField.setText(oamLinkInfo.getResponseOutTimeThreshold() + "");
		linkEnableCheckBox.setText(oamLinkInfo.getLinkEvent() + "");
		variableCheckBox.setSelected(oamLinkInfo.getMib() == 1 ? true : false);
		oamMessageLengthField.setSelected(oamLinkInfo.getMaxFrameLength() == 1 ? true : false);
		organicIdField.setSelected(oamLinkInfo.getOrganicId() == 1 ? true : false);
		factoryInfoField.setSelected(oamLinkInfo.getFactoryInfo() == 1 ? true : false);
		errorSignEventCycleField.setText(oamLinkInfo.getErrorSymboEventCycle() + "");
		errorSignEventLimitField.setText(oamLinkInfo.getErrorSymboEventThreshold() + "");
		errorFrameEventCycleField.setText(oamLinkInfo.getErrorFrameEventCycle() + "");
		errorFrameEventLimitField.setText(oamLinkInfo.getErrorFrameEventThreshold() + "");
		errorFrameCycleEventCycleField.setText(oamLinkInfo.getErrorFrameCycleEventCycle() + "");
		errorFrameCycleEventLimitField.setText(oamLinkInfo.getErrorFrameCycleEventThreshold() + "");
		errorSecondEventCycleField.setText(oamLinkInfo.getErrorFrameSecondEventCycle() + "");
		errorSecondEventLimitField.setText(oamLinkInfo.getErrorFrameSecondEventThreshold() + "");
		oamFrameField.setText(oamLinkInfo.getOamFarme()+"");
	}

	private void collectData() {
		OamLinkInfo oamLinkInfo = null;
		if (oamInfo == null) {
			oamInfo = new OamInfo();
			oamLinkInfo = new OamLinkInfo();
			oamInfo.setOamLinkInfo(oamLinkInfo);
		}else{
			oamLinkInfo = oamInfo.getOamLinkInfo();
		}
		
		if (portComboBox.getSelectedItem() == null || portComboBox.getSelectedItem() == null) {
			DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_MUSTNETWORK_BEFORE));
			return;
		}
		
		PortInst portInst = (PortInst)((ControlKeyValue) portComboBox.getSelectedItem()).getObject();
		oamLinkInfo.setObjId(portInst.getPortId());
		oamLinkInfo.setObjType(EServiceType.LINKOAM.toString());
		oamLinkInfo.setOamEnable(oamEnableCheckBox.isSelected());
		ControlKeyValue controlKeyValue = (ControlKeyValue) this.workWayComboBox.getSelectedItem();
		oamLinkInfo.setMode(Integer.parseInt(controlKeyValue.getId()));
		oamLinkInfo.setRemoteLoop(remoteLoopCheckBox.isSelected() ? 1 : 2);
		oamLinkInfo.setResponseOutTimeThreshold(Integer.parseInt(loopTimeOutField.getText().trim()));

		oamLinkInfo.setLinkEvent(Integer.parseInt(linkEnableCheckBox.getText()));
		oamLinkInfo.setMib(variableCheckBox.isSelected() ? 1 : 2);
		oamLinkInfo.setMaxFrameLength(oamMessageLengthField.isSelected() ? 1 : 2);
		oamLinkInfo.setOrganicId(organicIdField.isSelected() ? 1 : 2);
		oamLinkInfo.setFactoryInfo(factoryInfoField.isSelected() ? 1 : 2);

		oamLinkInfo.setErrorSymboEventCycle(Integer.parseInt(errorSignEventCycleField.getText()));
		oamLinkInfo.setErrorSymboEventThreshold(Integer.parseInt(errorSignEventLimitField.getText()));
		oamLinkInfo.setErrorFrameEventCycle(Integer.parseInt(errorFrameEventCycleField.getText()));
		oamLinkInfo.setErrorFrameEventThreshold(Integer.parseInt(errorFrameEventLimitField.getText()));
		oamLinkInfo.setErrorFrameCycleEventCycle(Integer.parseInt(errorFrameCycleEventCycleField.getText()));
		oamLinkInfo.setErrorFrameCycleEventThreshold(Integer.parseInt(errorFrameCycleEventLimitField.getText()));
		oamLinkInfo.setErrorFrameSecondEventCycle(Integer.parseInt(errorSecondEventCycleField.getText()));
		oamLinkInfo.setErrorFrameSecondEventThreshold(Integer.parseInt(errorSecondEventLimitField.getText()));
		oamLinkInfo.setOamFarme(Integer.parseInt(oamFrameField.getText()));
		oamLinkInfo.setSiteId(ConstantUtil.siteId);
		DispatchUtil dispath = null;
		String message = "";
		try {
			dispath = new DispatchUtil(RmiKeys.RMI_ETHLINKOAMCONFIG);
			int operationLogValue = 0;
			if(oamLinkInfo.getId()>0){
				message = dispath.excuteUpdate(oamInfo);
				operationLogValue = EOperationLogType.ETHLINKOAMUPDATE.getValue();
				linkOamBefore.setPortNameLog(portInst.getPortName());
				linkOamBefore.setMode(linkOamBefore.getMode()==0?1:0);
				linkOamBefore.setMib(linkOamBefore.getMib()==2?0:1);
				linkOamBefore.setRemoteLoop(linkOamBefore.getRemoteLoop()==2?0:1);
				linkOamBefore.setMaxFrameLength(linkOamBefore.getMaxFrameLength()==2?0:1);
				linkOamBefore.setOrganicId(linkOamBefore.getOrganicId()==2?0:1);
				linkOamBefore.setFactoryInfo(linkOamBefore.getFactoryInfo()==2?0:1);
			}else{
				message = dispath.excuteInsert(oamInfo);
				operationLogValue = EOperationLogType.ETHLINKOAMINSERT.getValue();
			}
			oamLinkInfo.setPortNameLog(portInst.getPortName());
			oamLinkInfo.setMode(oamLinkInfo.getMode()==0?1:0);
			oamLinkInfo.setMib(oamLinkInfo.getMib()==2?0:1);
			oamLinkInfo.setRemoteLoop(oamLinkInfo.getRemoteLoop()==2?0:1);
			oamLinkInfo.setMaxFrameLength(oamLinkInfo.getMaxFrameLength()==2?0:1);
			oamLinkInfo.setOrganicId(oamLinkInfo.getOrganicId()==2?0:1);
			oamLinkInfo.setFactoryInfo(oamLinkInfo.getFactoryInfo()==2?0:1);
			AddOperateLog.insertOperLog(confirm, operationLogValue, message, linkOamBefore, oamLinkInfo, ConstantUtil.siteId, oamLinkInfo.getPortNameLog()+"_EFM", "linkOAM");
			DialogBoxUtil.succeedDialog(this, message);
			this.getController().refresh();		
			dispose();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

	}

	public WhETHLinkOAMController getController() {
		return controller;
	}

	public void setController(WhETHLinkOAMController controller) {
		this.controller = controller;
	}
	private void setValidate(PtnTextField txtField,int max,int min) throws Exception{
		try {
			txtField.setCheckingMaxValue(true);
			txtField.setCheckingMinValue(true);
			txtField.setMaxValue(max);
			txtField.setMinValue(min);
		} catch (Exception e) {
			throw e;
		}
	}
}
