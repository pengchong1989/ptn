package com.nms.ui.ptn.ne.statusData.ptpBasicStatus.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.ptn.PtpBasicStatus;
import com.nms.db.bean.ptn.SiteStatusInfo;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.service.impl.util.ResultString;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnPanel;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.manager.keys.StringKeysTip;


public class PtpBasicStatusPanel extends PtnPanel{

	private static final long serialVersionUID = 6530671232941680179L;
	
	private JLabel ptpModel;//ptp模式
	private JComboBox ptpModelJComboBox;
	private JLabel clockModel;//时钟模型	
	private JComboBox clockModelJComboBox;
	private JLabel SlaveOnlyModel;//Slave_Only模式
	private JComboBox SlaveOnlyModelJComboBox;
	private JLabel inCompensation;//输入时延补偿属性	
	private JComboBox inCompensationJComboBox;
	private JLabel inDelay;//输入时延补偿值
	private JTextField inDelayField;
	private JLabel outCompensation;  //输出时延补偿属性
	private JComboBox outCompensationJComboBox;
	private JLabel outDelay;    //输出时延补偿值
	private JTextField outDelayField;
	private JLabel TimeInfoIt;  //时间信息接口
	private JComboBox TimeInfoItJComboBox;
	private JLabel syncFreq;  //Sync报文发包频率
	private JComboBox syncFreqJComboBox;
	private JLabel delayFreq; //Delay_Req报文发包频率
	private JComboBox delayFreqJComboBox;
	private JLabel announceFreq;  //Announce报文发包频率	
	private JComboBox announceFreqJComboBox;
	private JLabel delayOver;    //Delay_Req 报文超时系数
	private JTextField delayOverField;
	private JLabel announceOver;  //Announce报文超时系数
	private JTextField announceOverField;
	private JLabel domainNumbe;   //DomainNumber
	private JTextField domainNumbeField;
	private JLabel srcclockModel;//本地源时钟模式
	private JComboBox srcclockModelJComboBox;
	private JLabel srcclockId;//本地源时钟ID
	private JTextField srcclockIdField;
	private JLabel srcclocktype;//本地源时钟类型
	private JComboBox srcclocktypeJComboBox;
	private JLabel srcclockpri1; //本地源时钟优先级1
	private JTextField srcclockpri1Field;
	private JLabel srcclockpri2; //本地源时钟优先级2
	private JTextField srcclockpri2Field;
	private JLabel srcclockLevel;//本地源时钟等级
	private JTextField srcclockLevelField;
	private JLabel srcclockaccuray;//本地源时钟精度
	private JComboBox srcclockaccurayJComboBox;
    private JLabel ptpSecond;//本地ptp秒   
    private JTextField ptpSecondField;
	private JLabel localTime;//本地源时钟时间
	private JTextField localTimeField;
	private JLabel syncSrcclockId;//同步源时钟ID
	private JTextField syncSrcclockIdField;
	private JLabel masterId;//Master端口ID
	private JTextField masterIdField;
	private JLabel slaveId;//Slave端口ID
	private JTextField slaveIdField;
	private JLabel syncSrcclocktype;//同步源时钟类型	
	private JComboBox syncSrcclocktypeJComboBox;
	private JLabel syncSrcclockpri1; //同步源时钟优先级1
	private JTextField syncSrcclockpri1Field;
	private JLabel syncSrcclockpri2; //同步源时钟优先级2
	private JTextField syncSrcclockpri2Field;
	private JLabel syncSrcclockLevel;//同步源时钟等级
	private JTextField syncSrcclockLevelField;
	private JLabel syncSrcclockaccuray;//同步源时钟精度
	private JComboBox syncSrcclockaccurayJComboBox;
    private JLabel syncSourcePtpSecond;//同步源时钟ptp秒   
    private JTextField syncSourcePtpSecondField;
	private JLabel syncSourceTime;//同步源时钟时间
    private JTextField syncSourceTimeField; 
    private JLabel syncPtpSecond;//同步时间ptp秒   
    private JTextField syncPtpSecondField; 
	private JLabel syncTime;//同步时间
	private JTextField syncTimeField;
	private JLabel timeSkewAttribute;//时间偏移属性
	private JComboBox timeSkewAttributeJComboBox;
	private JLabel timeSkew;//时间偏移量
	private JTextField timeSkewField; // 时间偏移量文本框
	private PtnButton jButton;
	public PtpBasicStatusPanel(){
		initComponent();
		setLayout();
		addActionListener();
	}
	
	public void initComponent(){
		jButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SELECT));
		ptpModel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PTPMODE));
		ptpModelJComboBox = new JComboBox();
		ptpModelJComboBox.setEnabled(false);
		clockModel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_CLOCKMODE));
		clockModelJComboBox = new JComboBox();
		clockModelJComboBox.setEnabled(false);
		SlaveOnlyModel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SLAVEONLY));
		SlaveOnlyModelJComboBox = new JComboBox();
		SlaveOnlyModelJComboBox.setEnabled(false);
		inCompensation = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_INCOMPENSATION));
		inCompensationJComboBox = new JComboBox();
		inCompensationJComboBox.setEnabled(false);
		inDelay = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_INDELAY));
		inDelayField = new JTextField("");
		inDelayField.setEditable(false);
		outCompensation = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OUTCOMPENSATION));
		outCompensationJComboBox = new JComboBox();
		outCompensationJComboBox.setEnabled(false);
		outDelay = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OUTDELAY));
		outDelayField = new JTextField("");
		outDelayField.setEditable(false);
		TimeInfoIt = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TIMEINFORITE));
		TimeInfoItJComboBox = new JComboBox();
		TimeInfoItJComboBox.setEnabled(false);
		syncFreq = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SYNCFREC));
		syncFreqJComboBox = new JComboBox();
		syncFreqJComboBox.setEnabled(false);
		delayFreq = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_DELAYFRE));
		delayFreqJComboBox = new JComboBox();
		delayFreqJComboBox.setEnabled(false);
		announceFreq = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ANNOFREC));
		announceFreqJComboBox = new JComboBox();
		announceFreqJComboBox.setEnabled(false);
		delayOver = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_DELAYFREC));
		delayOverField = new JTextField("");
		delayOverField.setEditable(false);
		announceOver = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ANNOOVER));
		announceOverField = new JTextField("");
		announceOverField.setEditable(false);
		domainNumbe = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_DOMAIN));
		domainNumbeField = new JTextField("");
		domainNumbeField.setEditable(false);
		srcclockModel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SRCCLOCKMODEL));
		srcclockModelJComboBox = new JComboBox();
		srcclockModelJComboBox.setEnabled(false);
		srcclockId = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SRCCLOCK_ID));
		srcclockIdField = new JTextField("");
		srcclockIdField.setEditable(false);
		srcclocktype = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SRCCLOCK_TYPE));
		srcclocktypeJComboBox = new JComboBox();
		srcclocktypeJComboBox.setEnabled(false);
		srcclockpri1 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SRCCLOCK_PRI1));
		srcclockpri1Field = new JTextField("");
		srcclockpri1Field.setEditable(false);
		srcclockpri2 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SRCCLOCK_PRI2));
		srcclockpri2Field = new JTextField("");
		srcclockpri2Field.setEditable(false);
		srcclockLevel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SRCCLOCK_LEVEL));
		srcclockLevelField = new JTextField("");
		srcclockLevelField.setEditable(false);
		srcclockaccuray = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SRCCLOCK_ACCURAY));
		srcclockaccurayJComboBox = new JComboBox();
		srcclockaccurayJComboBox.setEnabled(false);
		ptpSecond = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PTP_SECOND));
		ptpSecondField = new JTextField("");
		ptpSecondField.setEditable(false);
		localTime = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_LOCALTIME));
		localTimeField = new JTextField("");
		localTimeField.setEditable(false);
		syncSrcclockId = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SYNCSRCCLOCK_ID));
		syncSrcclockIdField = new JTextField("");
		syncSrcclockIdField.setEditable(false);
		masterId = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_MASTER_ID));
		masterIdField = new JTextField("");
		masterIdField.setEditable(false);
		slaveId = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SLAVE_ID));
		slaveIdField = new JTextField("");
		slaveIdField.setEditable(false);
		syncSrcclocktype = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SYNCSRCCLOCK_TYPE));
		syncSrcclocktypeJComboBox = new JComboBox();
		syncSrcclocktypeJComboBox.setEnabled(false);
		syncSrcclockpri1 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SYNCSRCCLOCK_PRI1));
		syncSrcclockpri1Field = new JTextField("");
		syncSrcclockpri1Field.setEditable(false);
		syncSrcclockpri2 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SYNCSRCCLOCK_PRI2));
		syncSrcclockpri2Field = new JTextField("");
		syncSrcclockpri2Field.setEditable(false);
		syncSrcclockLevel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SYNCSRCCLOCK_LEVEL));
		syncSrcclockLevelField = new JTextField("");
		syncSrcclockLevelField.setEditable(false);
		syncSrcclockaccuray = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SYNCSRCCLOCK_ACCURAY));
		syncSrcclockaccurayJComboBox = new JComboBox();
		syncSrcclockaccurayJComboBox.setEnabled(false);
		syncSourcePtpSecond = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SYNCSOURCE_PTPSECOND));
		syncSourcePtpSecondField = new JTextField("");
		syncSourcePtpSecondField.setEditable(false);
		syncSourceTime = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SYNCSOURCETIME));
		syncSourceTimeField = new JTextField("");
		syncSourceTimeField.setEditable(false);
		syncPtpSecond = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SYNC_PTPSECOND));
		syncPtpSecondField = new JTextField("");
		syncPtpSecondField.setEditable(false);
		syncTime = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SYNCTIME));
		syncTimeField = new JTextField("");
		syncTimeField.setEditable(false);
		timeSkewAttribute = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TIMESKEW_ATTRIBUTE));
		timeSkewAttributeJComboBox = new JComboBox();
		timeSkewAttributeJComboBox.setEnabled(false);
		timeSkew = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TIMESKEW));
		timeSkewField = new JTextField("");
		timeSkewField.setEditable(false);


		try {
			super.getComboBoxDataUtil().comboBoxData(ptpModelJComboBox, "ptpModel");
			super.getComboBoxDataUtil().comboBoxData(clockModelJComboBox, "clockModel");
			super.getComboBoxDataUtil().comboBoxData(SlaveOnlyModelJComboBox, "ENABLEDSTATUE");
			super.getComboBoxDataUtil().comboBoxData(inCompensationJComboBox, "Compensation");
			super.getComboBoxDataUtil().comboBoxData(outCompensationJComboBox, "Compensation");
			super.getComboBoxDataUtil().comboBoxData(TimeInfoItJComboBox, "TimeInfoIt");
			super.getComboBoxDataUtil().comboBoxData(syncFreqJComboBox, "SYNCSENDFREC");
			super.getComboBoxDataUtil().comboBoxData(delayFreqJComboBox, "DELAYSENDFREC");
			super.getComboBoxDataUtil().comboBoxData(announceFreqJComboBox, "DELAYSENDFREC");
			super.getComboBoxDataUtil().comboBoxData(srcclockModelJComboBox, "ENABLEDSTATUE");
			super.getComboBoxDataUtil().comboBoxData(srcclocktypeJComboBox, "srcclockType");
			super.getComboBoxDataUtil().comboBoxData(srcclockaccurayJComboBox, "srcclockaccuray");
			super.getComboBoxDataUtil().comboBoxData(syncSrcclocktypeJComboBox, "srcclockType");
			super.getComboBoxDataUtil().comboBoxData(syncSrcclockaccurayJComboBox, "srcclockaccuray");
			super.getComboBoxDataUtil().comboBoxData(timeSkewAttributeJComboBox, "Compensation");
		
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	private void setLayout(){
		GridBagLayout componentLayout = new GridBagLayout();//网格布局
		componentLayout.columnWidths = new int[]{10,30,140,10,30,40,10,120,90,5,10};
		componentLayout.columnWeights = new double[]{0,0,0,0,0,0,0,0,0,0,0};
		componentLayout.rowHeights = new int[]{33,33,33,33,33,33,33,33,33,33,33,33,33,33};
		componentLayout.rowWeights = new double[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		this.setLayout(componentLayout);
		
		GridBagConstraints gridCon = new GridBagConstraints();
		gridCon.fill = GridBagConstraints.HORIZONTAL;
		gridCon.anchor = GridBagConstraints.WEST;
		gridCon.insets = new Insets(5,5,0,5);

	
		//第二、三列
		gridCon.gridx = 1;
		gridCon.gridy = 1;	
		componentLayout.setConstraints(this.ptpModel, gridCon);
		this.add(this.ptpModel);
		gridCon.gridx = 2;
		gridCon.gridy = 1;
		componentLayout.setConstraints(this.ptpModelJComboBox, gridCon);
		this.add(this.ptpModelJComboBox);
		
		gridCon.gridx = 1;
		gridCon.gridy = 2;
		componentLayout.setConstraints(this.clockModel, gridCon);
		this.add(this.clockModel);
		gridCon.gridx = 2;
		gridCon.gridy = 2;
		componentLayout.setConstraints(this.clockModelJComboBox, gridCon);
		this.add(this.clockModelJComboBox);
		
		gridCon.gridx = 1;
		gridCon.gridy = 3;
		componentLayout.setConstraints(this.SlaveOnlyModel, gridCon);
		this.add(this.SlaveOnlyModel);
		gridCon.gridx = 2;
		gridCon.gridy = 3;
		componentLayout.setConstraints(this.SlaveOnlyModelJComboBox, gridCon);
		this.add(this.SlaveOnlyModelJComboBox);
		
		gridCon.gridx = 1;
		gridCon.gridy = 4;
		componentLayout.setConstraints(this.inCompensation, gridCon);
		this.add(this.inCompensation);
		gridCon.gridx = 2;
		gridCon.gridy = 4;
		componentLayout.setConstraints(this.inCompensationJComboBox, gridCon);
		this.add(this.inCompensationJComboBox);
		
		gridCon.gridx = 1;
		gridCon.gridy = 5;
		componentLayout.setConstraints(this.inDelay, gridCon);
		this.add(this.inDelay);
		gridCon.gridx = 2;
		gridCon.gridy = 5;
		componentLayout.setConstraints(this.inDelayField, gridCon);
		this.add(this.inDelayField);
		
		gridCon.gridx = 1;
		gridCon.gridy = 6;
		componentLayout.setConstraints(this.outCompensation, gridCon);
		this.add(this.outCompensation);
		gridCon.gridx = 2;
		gridCon.gridy = 6;
		componentLayout.setConstraints(this.outCompensationJComboBox, gridCon);
		this.add(this.outCompensationJComboBox);
		
		gridCon.gridx = 1;
		gridCon.gridy = 7;
		componentLayout.setConstraints(this.outDelay, gridCon);
		this.add(this.outDelay);
		gridCon.gridx = 2;
		gridCon.gridy = 7;
		componentLayout.setConstraints(this.outDelayField, gridCon);
		this.add(this.outDelayField);
		
		gridCon.gridx = 1;
		gridCon.gridy = 8;
		componentLayout.setConstraints(this.TimeInfoIt, gridCon);
		this.add(this.TimeInfoIt);
		gridCon.gridx = 2;
		gridCon.gridy = 8;
		componentLayout.setConstraints(this.TimeInfoItJComboBox, gridCon);
		this.add(this.TimeInfoItJComboBox);
		
		gridCon.gridx = 1;
		gridCon.gridy = 9;
		componentLayout.setConstraints(this.syncFreq, gridCon);
		this.add(this.syncFreq);
		gridCon.gridx = 2;
		gridCon.gridy = 9;
		componentLayout.setConstraints(this.syncFreqJComboBox, gridCon);
		this.add(this.syncFreqJComboBox);
		
		gridCon.gridx = 1;
		gridCon.gridy = 10;
		componentLayout.setConstraints(this.delayFreq, gridCon);
		this.add(this.delayFreq);
		gridCon.gridx = 2;
		gridCon.gridy = 10;
		componentLayout.setConstraints(this.delayFreqJComboBox, gridCon);
		this.add(this.delayFreqJComboBox);
		
		gridCon.gridx = 1;
		gridCon.gridy = 11;
		componentLayout.setConstraints(this.announceFreq, gridCon);
		this.add(this.announceFreq);
		gridCon.gridx = 2;
		gridCon.gridy = 11;
		componentLayout.setConstraints(this.announceFreqJComboBox, gridCon);
		this.add(this.announceFreqJComboBox);
		
		gridCon.gridx = 1;
		gridCon.gridy = 12;
		componentLayout.setConstraints(this.delayOver, gridCon);
		this.add(this.delayOver);
		gridCon.gridx = 2;
		gridCon.gridy = 12;
		componentLayout.setConstraints(this.delayOverField, gridCon);
		this.add(this.delayOverField);
		
		gridCon.gridx = 1;
		gridCon.gridy = 13;
		componentLayout.setConstraints(this.announceOver, gridCon);
		this.add(this.announceOver);
		gridCon.gridx = 2;
		gridCon.gridy = 13;
		componentLayout.setConstraints(this.announceOverField, gridCon);
		this.add(this.announceOverField);
		
		
		//第5、6列
				
		gridCon.gridx = 4;
		gridCon.gridy = 1;
		componentLayout.setConstraints(this.domainNumbe, gridCon);
		this.add(this.domainNumbe);
		gridCon.gridx = 5;
		gridCon.gridy = 1;
		componentLayout.setConstraints(this.domainNumbeField, gridCon);
		this.add(this.domainNumbeField);	
			
		gridCon.gridx = 4;
		gridCon.gridy = 2;
		componentLayout.setConstraints(this.srcclockModel, gridCon);
		this.add(this.srcclockModel);
		gridCon.gridx = 5;
		gridCon.gridy = 2;
		componentLayout.setConstraints(this.srcclockModelJComboBox, gridCon);
		this.add(this.srcclockModelJComboBox);
		
		gridCon.gridx = 4;
		gridCon.gridy = 3;
		componentLayout.setConstraints(this.srcclockId, gridCon);
		this.add(this.srcclockId);
		gridCon.gridx = 5;
		gridCon.gridy = 3;
		componentLayout.setConstraints(this.srcclockIdField, gridCon);
		this.add(this.srcclockIdField);
		
		gridCon.gridx = 4;
		gridCon.gridy = 4;
		componentLayout.setConstraints(this.srcclocktype, gridCon);
		this.add(this.srcclocktype);
		gridCon.gridx = 5;
		gridCon.gridy = 4;
		componentLayout.setConstraints(this.srcclocktypeJComboBox, gridCon);
		this.add(this.srcclocktypeJComboBox);
		
		gridCon.gridx = 4;
		gridCon.gridy = 5;
		componentLayout.setConstraints(this.srcclockpri1, gridCon);
		this.add(this.srcclockpri1);
		gridCon.gridx = 5;
		gridCon.gridy = 5;
		componentLayout.setConstraints(this.srcclockpri1Field, gridCon);
		this.add(this.srcclockpri1Field);

		gridCon.gridx = 4;
		gridCon.gridy = 6;
		componentLayout.setConstraints(this.srcclockpri2, gridCon);
		this.add(this.srcclockpri2);
		gridCon.gridx = 5;
		gridCon.gridy = 6;
		componentLayout.setConstraints(this.srcclockpri2Field, gridCon);
		this.add(this.srcclockpri2Field);
		
		gridCon.gridx = 4;
		gridCon.gridy = 7;
		componentLayout.setConstraints(this.srcclockLevel, gridCon);
		this.add(this.srcclockLevel);
		gridCon.gridx = 5;
		gridCon.gridy = 7;
		componentLayout.setConstraints(this.srcclockLevelField, gridCon);
		this.add(this.srcclockLevelField);
		
		gridCon.gridx = 4;
		gridCon.gridy = 8;
		componentLayout.setConstraints(this.srcclockaccuray, gridCon);
		this.add(this.srcclockaccuray);
		gridCon.gridx =5;
		gridCon.gridy = 8;
		componentLayout.setConstraints(this.srcclockaccurayJComboBox, gridCon);
		this.add(this.srcclockaccurayJComboBox);
	
		gridCon.gridx = 4;
		gridCon.gridy = 9;
		componentLayout.setConstraints(this.ptpSecond, gridCon);
		this.add(this.ptpSecond);
		gridCon.gridx =5;
		gridCon.gridy = 9;
		componentLayout.setConstraints(this.ptpSecondField, gridCon);
		this.add(this.ptpSecondField);
		
		gridCon.gridx = 4;
		gridCon.gridy = 10;
		componentLayout.setConstraints(this.localTime, gridCon);
		this.add(this.localTime);
		gridCon.gridx = 5;
		gridCon.gridy = 10;
		componentLayout.setConstraints(this.localTimeField, gridCon);
		this.add(this.localTimeField);

		gridCon.gridx = 4;
		gridCon.gridy = 11;
		componentLayout.setConstraints(this.syncSrcclockId, gridCon);
		this.add(this.syncSrcclockId);
		gridCon.gridx = 5;
		gridCon.gridy = 11;
		componentLayout.setConstraints(this.syncSrcclockIdField, gridCon);
		this.add(this.syncSrcclockIdField);
		
		gridCon.gridx = 4;
		gridCon.gridy = 12;
		componentLayout.setConstraints(this.masterId, gridCon);
		this.add(this.masterId);
		gridCon.gridx = 5;
		gridCon.gridy = 12;
		componentLayout.setConstraints(this.masterIdField, gridCon);
		this.add(this.masterIdField);
				
	
		gridCon.gridx = 7;
		gridCon.gridy = 1;
		componentLayout.setConstraints(this.slaveId, gridCon);
		this.add(this.slaveId);
		gridCon.gridx = 8;
		gridCon.gridwidth = 2;
		gridCon.gridy = 1;
		componentLayout.setConstraints(this.slaveIdField, gridCon);
		this.add(this.slaveIdField);
		
		gridCon.gridx = 7;
		gridCon.gridy = 2;
		componentLayout.setConstraints(this.syncSrcclocktype, gridCon);
		this.add(this.syncSrcclocktype);
		gridCon.gridx = 8;
		gridCon.gridwidth = 2;
		gridCon.gridy = 2;
		componentLayout.setConstraints(this.syncSrcclocktypeJComboBox, gridCon);
		this.add(this.syncSrcclocktypeJComboBox);
		
		gridCon.gridx = 7;
		gridCon.gridy = 3;
		componentLayout.setConstraints(this.syncSrcclockpri1, gridCon);
		this.add(this.syncSrcclockpri1);
		gridCon.gridx = 8;
		gridCon.gridwidth = 2;
		gridCon.gridy = 3;
		componentLayout.setConstraints(this.syncSrcclockpri1Field, gridCon);
		this.add(this.syncSrcclockpri1Field);
			
        //第 10,11列
		gridCon.gridx = 7;
		gridCon.gridy = 4;
		componentLayout.setConstraints(this.syncSrcclockpri2, gridCon);
		this.add(this.syncSrcclockpri2);
		gridCon.gridx = 8;
		gridCon.gridwidth = 2;
		gridCon.gridy = 4;
		componentLayout.setConstraints(this.syncSrcclockpri2Field, gridCon);
		this.add(this.syncSrcclockpri2Field);
	 
		gridCon.gridx = 7;
		gridCon.gridy = 5;
		componentLayout.setConstraints(this.syncSrcclockLevel, gridCon);
		this.add(this.syncSrcclockLevel);
		gridCon.gridx = 8;
		gridCon.gridwidth = 2;
		gridCon.gridy = 5;
		componentLayout.setConstraints(this.syncSrcclockLevelField, gridCon);
		this.add(this.syncSrcclockLevelField);

		gridCon.gridx = 7;
		gridCon.gridy = 6;
		componentLayout.setConstraints(this.syncSrcclockaccuray, gridCon);
		this.add(this.syncSrcclockaccuray);
		gridCon.gridx = 8;
		gridCon.gridwidth = 2;
		gridCon.gridy = 6;
		componentLayout.setConstraints(this.syncSrcclockaccurayJComboBox, gridCon);
		this.add(this.syncSrcclockaccurayJComboBox);
	
		gridCon.gridx = 7;
		gridCon.gridy = 7;
		componentLayout.setConstraints(this.syncSourcePtpSecond, gridCon);
		this.add(this.syncSourcePtpSecond);
		gridCon.gridx = 8;
		gridCon.gridwidth = 2;
		gridCon.gridy = 7;
		componentLayout.setConstraints(this.syncSourcePtpSecondField, gridCon);
		this.add(this.syncSourcePtpSecondField);
		
		gridCon.gridx = 7;
		gridCon.gridy = 8;
		componentLayout.setConstraints(this.syncSourceTime, gridCon);
		this.add(this.syncSourceTime);
		gridCon.gridx = 8;
		gridCon.gridwidth = 2;
		gridCon.gridy = 8;
		componentLayout.setConstraints(this.syncSourceTimeField, gridCon);
		this.add(this.syncSourceTimeField);
 
		gridCon.gridx = 7;
		gridCon.gridy = 9;
		componentLayout.setConstraints(this.syncPtpSecond, gridCon);
		this.add(this.syncPtpSecond);
		gridCon.gridx = 8;
		gridCon.gridwidth = 2;
		gridCon.gridy = 9;
		componentLayout.setConstraints(this.syncPtpSecondField, gridCon);
		this.add(this.syncPtpSecondField);		

		gridCon.gridx = 7;
		gridCon.gridy = 10;
		componentLayout.setConstraints(this.syncTime, gridCon);
		this.add(this.syncTime);
		gridCon.gridx = 8;
		gridCon.gridwidth = 2;
		gridCon.gridy = 10;
		componentLayout.setConstraints(this.syncTimeField, gridCon);
		this.add(this.syncTimeField);

		gridCon.gridx = 7;
		gridCon.gridy = 11;
		componentLayout.setConstraints(this.timeSkewAttribute, gridCon);
		this.add(this.timeSkewAttribute);
		gridCon.gridx = 8;
		gridCon.gridwidth = 2;
		gridCon.gridy = 11;
		componentLayout.setConstraints(this.timeSkewAttributeJComboBox, gridCon);
		this.add(this.timeSkewAttributeJComboBox);
		
		gridCon.gridx = 7;
		gridCon.gridy = 12;
		componentLayout.setConstraints(this.timeSkew, gridCon);
		this.add(this.timeSkew);
		gridCon.gridx = 8;
		gridCon.gridwidth = 2;
		gridCon.gridy = 12;
		componentLayout.setConstraints(this.timeSkewField, gridCon);
		this.add(this.timeSkewField);
		
		gridCon.gridx = 9;
		gridCon.gridwidth = 1;
		gridCon.gridy = 13;
		gridCon.anchor = GridBagConstraints.CENTER;
		componentLayout.setConstraints(this.jButton, gridCon);
		this.add(this.jButton);
	
				
	}
	public void addActionListener(){
		this.jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				SiteService_MB siteService = null;
				SiteInst siteInst = null;
				DispatchUtil siteDispatch = null;
				SiteStatusInfo result = null;
				try {
					siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
					siteInst = siteService.select(ConstantUtil.siteId);
					siteInst.setStatusMark(24);
					siteDispatch = new DispatchUtil(RmiKeys.RMI_SITE);
					result = siteDispatch.siteStatus(siteInst);
					if(null!=result&&result.getPtpBasicStatusList() != null){
						DialogBoxUtil.succeedDialog(null, ResultString.CONFIG_SELECT);
						initValue(result.getPtpBasicStatusList().get(0));
						this.insertOpeLog(EOperationLogType.PTPBASICSTATUS.getValue(),  ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS), null,null);
					}else{
						DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_FIND_DETAIL_FAIL));
						this.insertOpeLog(EOperationLogType.PTPBASICSTATUS.getValue(),  ResourceUtil.srcStr(StringKeysTip.TIP_FIND_DETAIL_FAIL), null,null);		
					}
					
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}finally{
					UiUtil.closeService_MB(siteService);
				}
			}
			
			private void insertOpeLog(int operationType, String result, Object oldMac, Object newMac){				
			   AddOperateLog.insertOperLog(jButton, operationType, result, oldMac, newMac, ConstantUtil.siteId,ResourceUtil.srcStr(StringKeysPanel.PANEL_PTPBASIC),"");				
			}
		});
	}
	
	public void initValue(PtpBasicStatus info){
		if(info != null){
		
			try {
				inDelayField.setText(info.getInDelay()+"");		   
				outDelayField.setText(info.getOutDelay()+"");
				delayOverField.setText(info.getDelayOver()+"");
				announceOverField.setText(info.getAnnounceOver()+"");
				domainNumbeField.setText(info.getDomainNumbe()+"");
				srcclockIdField.setText(Integer.toHexString(info.getSrcclockId()));
				srcclockpri1Field.setText(info.getSrcclockpri1()+"");
				srcclockpri2Field.setText(info.getSrcclockpri2()+"");
				srcclockLevelField.setText(info.getSrcclockLevel()+"");
			    ptpSecondField.setText(info.getPtpSecond()+"");
				localTimeField.setText(info.getLocalTime());
				timeSkewField.setText(info.getTimeSkew()+"");
				syncTimeField.setText(info.getSyncTime());
				syncPtpSecondField.setText(info.getSyncPtpSecond()+"");
				syncSourceTimeField.setText(info.getSyncSourceTime());
				syncSourcePtpSecondField.setText(info.getSyncSourcePtpSecond()+"");
				syncSrcclockLevelField.setText(info.getSyncSrcclockLevel()+"");
				syncSrcclockpri2Field.setText(info.getSyncSrcclockpri2()+"");
				syncSrcclockpri1Field.setText(info.getSyncSrcclockpri1()+"");
				slaveIdField.setText(Integer.toHexString(info.getSlaveId()));
				masterIdField.setText(Integer.toHexString(info.getMasterId()));
				syncSrcclockIdField.setText(Integer.toHexString(info.getSyncSrcclockId()));
			   			   		  			  			  	  		   			
				super.getComboBoxDataUtil().comboBoxSelectByValue(ptpModelJComboBox, info.getPtpModel()+"");
				super.getComboBoxDataUtil().comboBoxSelectByValue(clockModelJComboBox, info.getClockModel()+"");
				super.getComboBoxDataUtil().comboBoxSelectByValue(SlaveOnlyModelJComboBox, info.getSlaveOnlyModel()+"");
				super.getComboBoxDataUtil().comboBoxSelectByValue(inCompensationJComboBox, info.getInCompensation()+"");
				super.getComboBoxDataUtil().comboBoxSelectByValue(outCompensationJComboBox, info.getOutCompensation()+"");
				super.getComboBoxDataUtil().comboBoxSelectByValue(TimeInfoItJComboBox,info.getTimeInfoIt()+"");
				super.getComboBoxDataUtil().comboBoxSelectByValue(syncFreqJComboBox, info.getSyncFreq()+"");
				super.getComboBoxDataUtil().comboBoxSelectByValue(delayFreqJComboBox, info.getDelayFreq()+"");
				super.getComboBoxDataUtil().comboBoxSelectByValue(announceFreqJComboBox, info.getAnnounceFreq()+"");
				super.getComboBoxDataUtil().comboBoxSelectByValue(srcclockModelJComboBox,info.getSrcclockModel()+"");
				super.getComboBoxDataUtil().comboBoxSelectByValue(srcclocktypeJComboBox, Integer.toHexString(info.getSrcclocktype()));
				super.getComboBoxDataUtil().comboBoxSelectByValue(srcclockaccurayJComboBox,Integer.toHexString(info.getSrcclockaccuray()));
				super.getComboBoxDataUtil().comboBoxSelectByValue(syncSrcclocktypeJComboBox,  Integer.toHexString(info.getSyncSrcclocktype()));
				super.getComboBoxDataUtil().comboBoxSelectByValue(syncSrcclockaccurayJComboBox, Integer.toHexString(info.getSyncSrcclockaccuray()));
				super.getComboBoxDataUtil().comboBoxSelectByValue(timeSkewAttributeJComboBox, info.getTimeSkewAttribute()+"");

			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}finally{
				
			}
		}
	}
	
}
