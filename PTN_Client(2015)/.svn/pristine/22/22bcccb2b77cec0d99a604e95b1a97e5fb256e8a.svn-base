package com.nms.ui.ptn.ne.statusData;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.ptn.SiteStatusInfo;
import com.nms.db.bean.ptn.clock.FrequencyStatusInfo;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.service.impl.util.ResultString;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnPanel;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTip;

public class FrequencyBasisJPanel extends PtnPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6530671232941680179L;
	private JLabel clockStatusJLabel;//时钟工作状态
	private JComboBox clockStatusJComboBox;
	private JLabel  outclockStatusJLabel;//外时钟输出状态
	private JComboBox outclockStatusJComboBox;
	private JLabel outclockTypeJLabel;//外时钟输出类型
	private JComboBox outclockTypeComboBox;
	private JLabel outclockModeJLabel;//外时钟输出方式
	private JComboBox outclockModeComboBox;
	private JLabel qlInSaJLabel;//输入源使用sa选择
	private JComboBox qlInSaJComboBox;
	private JLabel qlOutSaJLabel;//输出源使用sa选择
	private JComboBox qlOutSaJComboBox;
	private JLabel qlEnableJLabel;//ql使能状态
	private JComboBox qlEnableJComboBox;
	private JLabel lockSourceJLabel;//锁定源
	private JComboBox lockSourceJComboBox;
	private JLabel lockSourceS1JLabel;//锁定源值s1
	private JComboBox lockSourceS1JComboBox;
	private JLabel lockSourceTypeJLabel;//锁定源值类型
	private JComboBox lockSourceTypeJComboBox;
	private JLabel lockSourceStatusJLabel;//锁定源值状态
	private JComboBox lockSourceStatusJComboBox;
	private JLabel ssmOutJLabel;//ssm门限外时钟
	private JComboBox ssmOutJComboBox;
	private JLabel ssmSystemJLabel;//ssm门限系统时钟
	private JComboBox ssmSystemJComboBox;
	private PtnButton jButton;
	public FrequencyBasisJPanel(){
		initComponent();
		setLayout();
		addActionListener();
	}
	
	public void initComponent(){
		jButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SELECT),true);
		clockStatusJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_CLOCKSTATUS));
		clockStatusJComboBox = new JComboBox();
		clockStatusJComboBox.setEnabled(false);
		outclockStatusJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OUTCLOCKSTATUS));
		outclockStatusJComboBox = new JComboBox();
		outclockStatusJComboBox.setEnabled(false);
		outclockTypeJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OUT_CLOCK_TYPE));
		outclockTypeComboBox = new JComboBox();
		outclockTypeComboBox.setEnabled(false);
		outclockModeJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OUT_CLOCK_MODEL));
		outclockModeComboBox = new JComboBox();
		outclockModeComboBox.setEnabled(false);
		qlInSaJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_INPUT_SA));
		qlInSaJComboBox = new JComboBox();
		qlInSaJComboBox.setEnabled(false);
		qlOutSaJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OUTPUT_SA));
		qlOutSaJComboBox = new JComboBox();
		qlOutSaJComboBox.setEnabled(false);
		qlEnableJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_QL_SA_STATUS));
		qlEnableJComboBox = new JComboBox();
		qlEnableJComboBox.setEnabled(false);
		lockSourceJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_LOCK_SOURCE));
		lockSourceJComboBox = new JComboBox();
		lockSourceJComboBox.setEnabled(false);
		lockSourceS1JLabel= new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_LOCK_SOURCE_S1));
		lockSourceS1JComboBox = new JComboBox();
		lockSourceS1JComboBox.setEnabled(false);
		lockSourceTypeJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_LOCK_SOURCE_TYPE));
		lockSourceTypeJComboBox = new JComboBox();
		lockSourceTypeJComboBox.setEnabled(false);
		lockSourceStatusJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_LOCK_SOURCE_STATUS));
		lockSourceStatusJComboBox = new JComboBox();
		lockSourceStatusJComboBox.setEnabled(false);
		ssmOutJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SSM_OUT_CLOCK));
		ssmOutJComboBox = new JComboBox();
		ssmOutJComboBox.setEnabled(false);
		ssmSystemJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SSM_SYSTEM));
		ssmSystemJComboBox = new JComboBox();
		ssmSystemJComboBox.setEnabled(false);
		try {
			super.getComboBoxDataUtil().comboBoxData(clockStatusJComboBox, "CLOCKSTATUS");
			super.getComboBoxDataUtil().comboBoxData(outclockStatusJComboBox, "OUTCLOCKSTATUS");
			super.getComboBoxDataUtil().comboBoxData(outclockTypeComboBox, "OUTCLOCKTYPE");
			super.getComboBoxDataUtil().comboBoxData(outclockModeComboBox, "OUTCLOCKMODEL");
			super.getComboBoxDataUtil().comboBoxData(qlInSaJComboBox, "QLSACHOOSE");
			super.getComboBoxDataUtil().comboBoxData(qlOutSaJComboBox, "QLSACHOOSE");
			super.getComboBoxDataUtil().comboBoxData(qlEnableJComboBox, "ENABLEDSTATUE");
			super.getComboBoxDataUtil().comboBoxData(lockSourceS1JComboBox, "LOCKSOURCES1");
			super.getComboBoxDataUtil().comboBoxData(lockSourceTypeJComboBox, "CURRCLOCKTYPE");
			super.getComboBoxDataUtil().comboBoxData(lockSourceStatusJComboBox, "LCOKSOURCESTATUS");
			super.getComboBoxDataUtil().comboBoxData(ssmOutJComboBox, "LOCKSOURCES1");
			super.getComboBoxDataUtil().comboBoxData(ssmSystemJComboBox, "LOCKSOURCES1");
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	private void setLayout(){
		GridBagLayout componentLayout = new GridBagLayout();//网格布局
		componentLayout.columnWidths = new int[]{20,40,80,30,40,80,30,40,20,20};
		componentLayout.columnWeights = new double[]{0,0,0,0,0,0,0};
		componentLayout.rowHeights = new int[]{15,40,40,40,40,40,40};
		componentLayout.rowWeights = new double[]{0,0,0,0,0,0,0};
		this.setLayout(componentLayout);
		
		GridBagConstraints gridCon = new GridBagConstraints();
		gridCon.fill = GridBagConstraints.HORIZONTAL;
		gridCon.anchor = GridBagConstraints.NORTH;
		gridCon.insets = new Insets(5,5,5,5);
		
		//第一行
		gridCon.gridx = 0;
		gridCon.gridy = 1;
		componentLayout.setConstraints(this.clockStatusJLabel, gridCon);
		this.add(this.clockStatusJLabel);
		gridCon.gridx = 1;
		gridCon.gridy = 1;
		componentLayout.setConstraints(this.clockStatusJComboBox, gridCon);
		this.add(this.clockStatusJComboBox);
		gridCon.gridx = 3;
		gridCon.gridy = 1;
		componentLayout.setConstraints(this.outclockStatusJLabel, gridCon);
		this.add(this.outclockStatusJLabel);
		gridCon.gridx = 4;
		gridCon.gridy = 1;
		componentLayout.setConstraints(this.outclockStatusJComboBox, gridCon);
		this.add(this.outclockStatusJComboBox);
		
		//第二行
		gridCon.gridx = 0;
		gridCon.gridy = 2;
		componentLayout.setConstraints(this.outclockTypeJLabel, gridCon);
		this.add(this.outclockTypeJLabel);
		gridCon.gridx = 1;
		gridCon.gridy = 2;
		componentLayout.setConstraints(this.outclockTypeComboBox, gridCon);
		this.add(this.outclockTypeComboBox);
		gridCon.gridx = 3;
		gridCon.gridy = 2;
		componentLayout.setConstraints(this.outclockModeJLabel, gridCon);
		this.add(this.outclockModeJLabel);
		gridCon.gridx = 4;
		gridCon.gridy = 2;
		componentLayout.setConstraints(this.outclockModeComboBox, gridCon);
		this.add(this.outclockModeComboBox);
		
		//第三行
		gridCon.gridx = 0;
		gridCon.gridy = 3;
		componentLayout.setConstraints(this.lockSourceStatusJLabel, gridCon);
		this.add(this.lockSourceStatusJLabel);
		gridCon.gridx = 1;
		gridCon.gridy = 3;
		componentLayout.setConstraints(this.lockSourceStatusJComboBox, gridCon);
		this.add(this.lockSourceStatusJComboBox);
		gridCon.gridx = 3;
		gridCon.gridy = 3;
		componentLayout.setConstraints(this.lockSourceTypeJLabel, gridCon);
		this.add(this.lockSourceTypeJLabel);
		gridCon.gridx = 4;
		gridCon.gridy = 3;
		componentLayout.setConstraints(this.lockSourceTypeJComboBox, gridCon);
		this.add(this.lockSourceTypeJComboBox);
		
		//第四行
		gridCon.gridx = 0;
		gridCon.gridy = 4;
		componentLayout.setConstraints(this.lockSourceJLabel, gridCon);
		this.add(this.lockSourceJLabel);
		gridCon.gridx = 1;
		gridCon.gridy = 4;
		componentLayout.setConstraints(this.lockSourceJComboBox, gridCon);
		this.add(this.lockSourceJComboBox);
		gridCon.gridx = 3;
		gridCon.gridy = 4;
		componentLayout.setConstraints(this.lockSourceS1JLabel, gridCon);
		this.add(this.lockSourceS1JLabel);
		gridCon.gridx = 4;
		gridCon.gridy = 4;
		componentLayout.setConstraints(this.lockSourceS1JComboBox, gridCon);
		this.add(this.lockSourceS1JComboBox);
		
		//第五行
		gridCon.gridx = 0;
		gridCon.gridy = 5;
		componentLayout.setConstraints(this.qlInSaJLabel, gridCon);
		this.add(this.qlInSaJLabel);
		gridCon.gridx = 1;
		gridCon.gridy = 5;
		componentLayout.setConstraints(this.qlInSaJComboBox, gridCon);
		this.add(this.qlInSaJComboBox);
		gridCon.gridx = 3;
		gridCon.gridy = 5;
		componentLayout.setConstraints(this.qlOutSaJLabel, gridCon);
		this.add(this.qlOutSaJLabel);
		gridCon.gridx = 4;
		gridCon.gridy = 5;
		componentLayout.setConstraints(this.qlOutSaJComboBox, gridCon);
		this.add(this.qlOutSaJComboBox);
		
		//第六行
		gridCon.gridx = 0;
		gridCon.gridy = 6;
		componentLayout.setConstraints(this.qlEnableJLabel, gridCon);
		this.add(this.qlEnableJLabel);
		gridCon.gridx = 1;
		gridCon.gridy = 6;
		componentLayout.setConstraints(this.qlEnableJComboBox, gridCon);
		this.add(this.qlEnableJComboBox);
		gridCon.gridx = 3;
		gridCon.gridy = 6;
		componentLayout.setConstraints(this.ssmOutJLabel, gridCon);
		this.add(this.ssmOutJLabel);
		gridCon.gridx = 4;
		gridCon.gridy = 6;
		componentLayout.setConstraints(this.ssmOutJComboBox, gridCon);
		this.add(this.ssmOutJComboBox);
		
		//第七行
		gridCon.gridx = 0;
		gridCon.gridy = 7;
		componentLayout.setConstraints(this.ssmSystemJLabel, gridCon);
		this.add(this.ssmSystemJLabel);
		gridCon.gridx = 1;
		gridCon.gridy = 7;
		componentLayout.setConstraints(this.ssmSystemJComboBox, gridCon);
		this.add(this.ssmSystemJComboBox);
		
		//第七行
		gridCon.gridx = 5;
		gridCon.gridy = 8;
		componentLayout.setConstraints(this.jButton, gridCon);
		this.add(this.jButton);
	}
	public void addActionListener(){
		this.jButton.addActionListener(new MyActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				SiteService_MB siteService = null;
				SiteInst siteInst = null;
				DispatchUtil siteDispatch = null;
				SiteStatusInfo result = null;
				try {
					siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
					siteInst = siteService.select(ConstantUtil.siteId);
					siteInst.setStatusMark(20);
					siteDispatch = new DispatchUtil(RmiKeys.RMI_SITE);
					result = siteDispatch.siteStatus(siteInst);
					if(null!=result&&result.getFrequencyStatusInfo() != null){
						DialogBoxUtil.succeedDialog(null, ResultString.CONFIG_SELECT);
						initValue(result.getFrequencyStatusInfo());
						this.insertOpeLog(EOperationLogType.CLOCKBASIC.getValue(),  ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS), null,null);									
					}else{
						DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_FIND_DETAIL_FAIL));
						this.insertOpeLog(EOperationLogType.CLOCKBASIC.getValue(),  ResourceUtil.srcStr(StringKeysTip.TIP_FIND_DETAIL_FAIL), null,null);	
					}
					
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}finally{
					UiUtil.closeService_MB(siteService);
				}
			}

			private void insertOpeLog(int operationType, String result, Object oldMac, Object newMac){			
			   AddOperateLog.insertOperLog(jButton, operationType, result, oldMac, newMac, ConstantUtil.siteId,ResourceUtil.srcStr(StringKeysLbl.LBL_CLOCKSTATUS),"");				
			}
			
			@Override
			public boolean checking() {
				return true;
			}
		});
	}
	
	public void initValue(FrequencyStatusInfo info){
		if(info != null){
			PortService_MB portService = null;
			try {
				portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
				super.getComboBoxDataUtil().comboBoxSelectByValue(clockStatusJComboBox, info.getClockStatus()+"");
				super.getComboBoxDataUtil().comboBoxSelectByValue(outclockStatusJComboBox, info.getOutclockStatus()+"");
				super.getComboBoxDataUtil().comboBoxSelectByValue(outclockTypeComboBox, info.getOutclockType()+"");
				super.getComboBoxDataUtil().comboBoxSelectByValue(outclockModeComboBox, info.getOutclockMode()+"");
				super.getComboBoxDataUtil().comboBoxSelectByValue(qlInSaJComboBox, info.getQlInSa()+"");
				super.getComboBoxDataUtil().comboBoxSelectByValue(qlOutSaJComboBox, info.getQlOutSa()+"");
				super.getComboBoxDataUtil().comboBoxSelectByValue(qlEnableJComboBox, info.getQlEnable()+"");
				if(info.getLockSource() >0){
					PortInst portInst = new PortInst();
					portInst.setSiteId(ConstantUtil.siteId);
					portInst.setNumber(info.getLockSource());
					List<PortInst> portList = portService.select(portInst);
					if(portList.size() > 0){
						portInst = portList.get(0);
						lockSourceJComboBox.addItem(portInst.getPortName());
						lockSourceJComboBox.setSelectedItem(portInst.getPortName());
					}
				}else{
					lockSourceJComboBox.addItem(StringKeysObj.OUTER_CLOCK);
					lockSourceJComboBox.setSelectedItem(StringKeysObj.OUTER_CLOCK);
				}
				super.getComboBoxDataUtil().comboBoxSelectByValue(lockSourceS1JComboBox, info.getLockSourceS1()+"");
				super.getComboBoxDataUtil().comboBoxSelectByValue(lockSourceTypeJComboBox, info.getLockSourceType()+"");
				super.getComboBoxDataUtil().comboBoxSelectByValue(lockSourceStatusJComboBox, info.getLockSourceStatus()+"");
				super.getComboBoxDataUtil().comboBoxSelectByValue(ssmOutJComboBox, info.getSsmOut()+"");
				super.getComboBoxDataUtil().comboBoxSelectByValue(ssmSystemJComboBox, info.getSsmSystem()+"");
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}finally{
				UiUtil.closeService_MB(portService);
			}
		}
	}
}