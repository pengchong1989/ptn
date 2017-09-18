package com.nms.ui.ptn.alarm.view;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.nms.db.bean.alarm.CurrentAlarmInfo;
import com.nms.db.bean.alarm.HisAlarmInfo;
import com.nms.db.bean.alarm.WarningLevel;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.enums.EObjectType;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.alarm.CurAlarmService_MB;
import com.nms.model.alarm.HisAlarmService_MB;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.service.impl.util.SiteUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysMenu;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.alarm.AlarmTools;
import com.nms.ui.ptn.clock.view.cx.time.TextFiledKeyListener;
import com.nms.ui.ptn.clock.view.cx.time.TextfieldFocusListener;

/**
 * 
 * @author Administrator
 * �澯��תģʽ��Ԫ���ñ�
 */
public class AlarmReversalPanel extends PtnDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9125315668377808981L;
	private JLabel neJlJLabel;
	private JComboBox neComboBox;
	private JCheckBox alarmModel;
	private JComboBox alarmModelComboBox;
	private JButton confirmButton;
	private JButton cancelButton;
	private JCheckBox alarmDelay;//�Ƿ�����Ԫ�澯�ϱ��ӳ�
	private JTextField delayText;//�ӳ�ʱ��
	
	
	public AlarmReversalPanel(){
		init();
	}

	//��ʼ��
	private void init() {
		
		try{
			
			this.setTitle(ResourceUtil.srcStr(StringKeysMenu.TAB_ALARM_REVERSAL));
			this.initComponents();
			this.setLayout();
			this.addActionListener();
			addKeyListenerForTextfield();
			addFocusListenerForTextfield();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}


	private void addActionListener() {
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		
		neComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControlKeyValue controlKeyValue = (ControlKeyValue) ((JComboBox)e.getSource()).getSelectedItem();
				int alarmMode = ((SiteInst)controlKeyValue.getObject()).getAlarmReversalModel();
				alarmModelComboBox.setSelectedIndex(alarmMode);
			}
		});
		confirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				confirm();
			}
		});
		
		alarmDelay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(alarmDelay.isSelected()){
					delayText.setText("20");
					delayText.setEditable(true);
				}else{
					delayText.setText("");
					delayText.setEditable(false);
				}
			}
		});
		
		alarmModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(alarmModel.isSelected()){
					alarmModelComboBox.setEnabled(true);
				}else{
					alarmModelComboBox.setEnabled(false);
				}
			}
		});
	}
	
  //ȡ���¼�
	private void cancel() {
		this.dispose();
	}

	private void setLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 50, 150, 50 };
		componentLayout.columnWeights = new double[] { 0, 0, 0,0};
		componentLayout.rowHeights = new int[] {40,40,40,40,40,40};
		componentLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.4};
		this.setLayout(componentLayout);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		//  ��һ��
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(40, 5, 15, 5);
		componentLayout.setConstraints(this.neJlJLabel, c);
		this.add(this.neJlJLabel);
		
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		componentLayout.setConstraints(this.neComboBox, c);
		this.add(this.neComboBox);
		
//		c.fill = GridBagConstraints.NONE;
		// �ڶ���
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(15, 5, 15, 5);
		componentLayout.setConstraints(this.alarmModel, c);
		this.add(this.alarmModel);
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		componentLayout.setConstraints(this.alarmModelComboBox, c);
		this.add(this.alarmModelComboBox);
		
		// ������
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(15, 5, 15, 5);
		componentLayout.setConstraints(this.alarmDelay, c);
		this.add(this.alarmDelay);
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		componentLayout.setConstraints(this.delayText, c);
		this.add(this.delayText);
		
		
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 20, 10);
		componentLayout.setConstraints(this.confirmButton, c);
		this.add(this.confirmButton);
		c.gridx = 2;
		c.insets = new Insets(5, 5, 20, 10);
		componentLayout.setConstraints(this.cancelButton, c);
		this.add(this.cancelButton);
		
		this.setLayout(componentLayout);
	}

	private void initComponents() {
		
	    neJlJLabel = new JLabel(ResourceUtil.srcStr(StringKeysObj.NET_BASE));
		neComboBox = new JComboBox();
		this.comboBoxData(neComboBox);
		alarmModel = new JCheckBox(ResourceUtil.srcStr(StringKeysLbl.LBL_ALARM_MODEL));
		
		alarmModelComboBox = new JComboBox();
		alarmModelComboBox.addItem(ResourceUtil.srcStr(StringKeysLbl.LBL_ALARM_NO_REVERSAL));
		alarmModelComboBox.addItem(ResourceUtil.srcStr(StringKeysLbl.LBL_ALARM_REVERSALS));
//		alarmModelComboBox.addItem(ResourceUtil.srcStr(StringKeysLbl.LBL_ALARM_MODEL_ATUO));
		alarmModelComboBox.setEnabled(false);
		
		confirmButton = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE));
		cancelButton = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		
		alarmDelay = new JCheckBox(ResourceUtil.srcStr(StringKeysLbl.LBL_ALARMDELAY));
		delayText = new JTextField();
		delayText.setEditable(false);
		
		if((ControlKeyValue) neComboBox.getItemAt(0) != null){
			SiteInst siteInfo = ((SiteInst)((ControlKeyValue) neComboBox.getItemAt(0)).getObject());
			if(siteInfo.getIsAlarmReversal()==1){
			  alarmModel.setSelected(true);
			  alarmModelComboBox.setSelectedIndex(siteInfo.getAlarmReversalModel());
			  alarmModelComboBox.setEnabled(true);
			}else{
			  alarmModel.setSelected(false);
			}
			if(siteInfo.getIsDelayAlarmTrap() ==1 ){
				alarmDelay.setSelected(true);
				delayText.setEditable(true);
				if(siteInfo.getDelayTime().contains("-")){
					delayText.setText(siteInfo.getDelayTime().split("-")[1]);
				}
			}else{
				alarmDelay.setSelected(false);
			}
		}
		
	}
	public void comboBoxData(JComboBox jComboBox){
		
        SiteService_MB siteService = null;
		DefaultComboBoxModel defaultComboBoxModel = null;
		List<SiteInst> siteList = new ArrayList<SiteInst>();
		try {
			siteService = (SiteService_MB)ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteList = siteService.select();
			defaultComboBoxModel = (DefaultComboBoxModel) jComboBox.getModel();
			for (SiteInst siteInfo : siteList) {
				defaultComboBoxModel.addElement(new ControlKeyValue(siteInfo.getSite_Inst_Id() + "", siteInfo.getCellId(), siteInfo));
			}
			jComboBox.setModel(defaultComboBoxModel);

		} catch (Exception e) {
			ExceptionManage.dispose(e, AlarmReversalPanel.class);
		} finally {
			siteList = null;
			UiUtil.closeService_MB(siteService);
			defaultComboBoxModel = null;
		}
	}
	
/**
 * �ύ�û��������
 */
	private void confirm() {
		
		SiteService_MB siteService = null;
		PortService_MB portService = null;
		List<PortInst> portList = null;
		int alarmReverAgoState = 0;
		
		try {
			
		portList = new ArrayList<PortInst>();
        siteService = (SiteService_MB)ConstantUtil.serviceFactory.newService_MB(Services.SITE);
        portService = (PortService_MB)ConstantUtil.serviceFactory.newService_MB(Services.PORT);
        SiteInst siteInfo =  (SiteInst)((ControlKeyValue) neComboBox.getSelectedItem()).getObject();
        
        if(alarmModel.isSelected()){
        	if(neComboBox.getSelectedItem()!= null){
        		alarmReverAgoState = siteInfo.getAlarmReversalModel();
        		siteInfo.setAlarmReversalModel(alarmModelComboBox.getSelectedIndex());
        		PortInst portInst =	new PortInst();
        		portInst.setSiteId(siteInfo.getSite_Inst_Id());
        		portList = portService.select(portInst);
        		
        		if(isEnable(portList)){
        			isAlarmReversal(siteInfo,portList,alarmReverAgoState);
        			siteInfo.setIsAlarmReversal(1);
        		}else{
        			DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_PORT_ALARM));
        			return ;
        		}
        	}else{
        		DialogBoxUtil.succeedDialog(null,  ResourceUtil.srcStr(StringKeysTip.TIP_NOT_FULL));
        		return ;
          }
        }else{
        	siteInfo.setIsAlarmReversal(0);
        }
        if(alarmDelay.isSelected()){
        	siteInfo.setIsDelayAlarmTrap(1);
        	siteInfo.setDelayTime(System.currentTimeMillis()+"-"+Long.parseLong(delayText.getText().trim())+"");
        }else{
        	siteInfo.setIsDelayAlarmTrap(0);
        }
        siteService.updateSite(siteInfo);
        DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
        UiUtil.insertOperationLog(EOperationLogType.ALARMREVERSAL.getValue(),ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
        this.dispose();
		} catch (Exception e) {
			ExceptionManage.dispose(e, AlarmReversalPanel.class);
		}finally{
			UiUtil.closeService_MB(siteService);
			UiUtil.closeService_MB(portService);
		}
	}
	
	/**
	 * ���Ӹ澯��ת�ĸ澯�������������
	 * @param alarmInfos
	 */
	
	private void isAlarmReversal(SiteInst siteInst,List<PortInst> portList,int alarmReverAgoState){
		
//		SiteService siteService = null;
//		PortService portService = null;
		List<CurrentAlarmInfo> alarmInfosList = null;
		CurAlarmService_MB alarmService = null;
		HisAlarmService_MB hisService = null;
		
		try {
			alarmService = (CurAlarmService_MB)ConstantUtil.serviceFactory.newService_MB(Services.CurrentAlarm);
//			siteService = (SiteService)ConstantUtil.serviceFactory.newService(Services.SITE);
//			portService = (PortService)ConstantUtil.serviceFactory.newService(Services.PORT);
			hisService = (HisAlarmService_MB) ConstantUtil.serviceFactory.newService_MB(Services.HisAlarm);
			
			alarmInfosList = alarmService.select();
			SiteUtil siteUtil = new SiteUtil();
			 if(siteInst.getAlarmReversalModel()!= alarmReverAgoState && "0".equals(siteUtil.SiteTypeUtil(siteInst.getSite_Inst_Id())+"")){
				if(portList != null && portList.size()>0){
					for(PortInst port: portList){
						if(port.getIsEnabledAlarmReversal() == 1 && (port.getPortType().equals("NNI")|| port.getPortType().equals("UNI"))){
							updateAlarmTopo(siteInst,port,alarmInfosList,alarmService,hisService);
					 }
					}
				  }
				}   
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(alarmService);
//			UiUtil.closeService(siteService);
//			UiUtil.closeService(portService);
			UiUtil.closeService_MB(hisService);
		}
	}

	private void updateAlarmTopo(SiteInst siteInfo,PortInst port,List<CurrentAlarmInfo> alarmInfos,
			CurAlarmService_MB curAlarmService,HisAlarmService_MB hisService ){
		try {
			AlarmTools alarmTools = new AlarmTools();
			CurrentAlarmInfo losAlarm = null;
			for(CurrentAlarmInfo currentAlarmInfo : alarmInfos){
				if(currentAlarmInfo.getSiteId() == port.getSiteId() && currentAlarmInfo.getObjectName().equals(port.getPortName()) && currentAlarmInfo.getAlarmCode() ==11){
					losAlarm = currentAlarmInfo;
				}
			}
			//�����ڽ���ת����ɾ��������һ�����;
			if(losAlarm != null){
				curAlarmService.deleteCurrentAlarmInfo(losAlarm);
				
				HisAlarmInfo hisLosAlarm = new HisAlarmInfo();
				hisLosAlarm.setAckUser(ConstantUtil.user.getUser_Name());
				hisLosAlarm.setAlarmCode(losAlarm.getAlarmCode());
				hisLosAlarm.setAlarmDesc(losAlarm.getAlarmDesc());
				hisLosAlarm.setAlarmLevel(losAlarm.getAlarmLevel());
				hisLosAlarm.setObjectName(losAlarm.getObjectName());
				hisLosAlarm.setObjectId(port.getPortId());
				hisLosAlarm.setObjectType(EObjectType.PORT);
				hisLosAlarm.setSiteId(port.getSiteId());
				hisLosAlarm.setSiteName(siteInfo.getCellId());
				hisLosAlarm.setWarningLevel_temp(5);
				hisLosAlarm.setAlarmSeverity(alarmTools.getAlarmSeverity(5));
				WarningLevel level = new WarningLevel();
				level.setId(11);
				level.setManufacturer(1);
				level.setWarningcode(losAlarm.getAlarmCode());
				level.setWarningdescribe(losAlarm.getAlarmDesc());
				level.setWarninglevel(losAlarm.getAlarmLevel());
				level.setWarninglevel_temp(losAlarm.getAlarmLevel());
				level.setWarningname(losAlarm.getWarningLevel().getWarningname());
				level.setWarningnote(losAlarm.getWarningLevel().getWarningnote());
				hisLosAlarm.setWarningLevel(level);
				hisLosAlarm.setRaisedTime(new Date());
				hisLosAlarm.setAckTime(new Date());
				hisLosAlarm.setClearedTime(new Date());
				hisService.saveOrUpdate(hisLosAlarm);
			}else{
				losAlarm = new CurrentAlarmInfo();
				losAlarm.setAlarmCode(11);
				losAlarm.setAlarmDesc(ResourceUtil.srcStr(StringKeysLbl.LBL_LINK_LOSE));
				losAlarm.setAlarmLevel(5);
				losAlarm.setObjectId(port.getPortId());
				losAlarm.setObjectName(port.getPortName());
				losAlarm.setObjectType(EObjectType.PORT);
				losAlarm.setSiteId(port.getSiteId());
				losAlarm.setSiteName(siteInfo.getCellId());
				losAlarm.setWarningLevel_temp(5);
				WarningLevel level = new WarningLevel();
				level.setId(11);
				level.setManufacturer(1);
				level.setWarningcode(11);
				level.setWarningdescribe(ResourceUtil.srcStr(StringKeysLbl.LBL_LINK_LOSE));
				level.setWarninglevel(5);
				level.setWarninglevel_temp(5);
				level.setWarningname("LINK_LOS");
				level.setWarningnote(ResourceUtil.srcStr(StringKeysLbl.LBL_LINK_LOSE));
				losAlarm.setWarningLevel(level);
				losAlarm.setRaisedTime(new Date());
				curAlarmService.saveOrUpdate(losAlarm);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	private boolean isEnable(List<PortInst> portList){
		boolean falg = false;
		
		try {
			for(PortInst port : portList){
				if(port.getIsEnabledAlarmReversal() == 1 && (port.getPortType().equals("NNI")|| port.getPortType().equals("UNI"))){
					return true;
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return falg;
	}
	
	/**
	 * <p>
	 * textfield��Ӽ���ֻ������������
	 * </p>
	 * @throws Exception
	 */
	private void addKeyListenerForTextfield()throws Exception{
		
		TextFiledKeyListener textFIledKeyListener=null;
		try{
			/* Ϊ1��ֻ�������� **/
			textFIledKeyListener = new TextFiledKeyListener(1);
			this.delayText.addKeyListener(textFIledKeyListener);
		}catch(Exception e){
			
			throw e;
		}
	}
	/**
	 * <p>
	 * �ж�������ֵ�Ƿ���ָ�������
	 * </p>
	 * @throws Exception
	 */
	private void addFocusListenerForTextfield()throws Exception{
		
		TextfieldFocusListener textfieldFocusListener=null;
		String whichTextTield=null;
		try{
			whichTextTield=ResourceUtil.srcStr(StringKeysLbl.LBL_LOCKSCRESS);
			textfieldFocusListener = new TextfieldFocusListener(whichTextTield,20,this.delayText);
			this.delayText.addFocusListener(textfieldFocusListener);
			
		}catch(Exception e){
			
			throw e;
		}
	}
}
