package com.nms.ui.ptn.clock.view.cx.time;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.nms.db.bean.ptn.clock.TimeManageInfo;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.ptn.clock.TimeManageInfoService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnPanel;
import com.nms.ui.manager.keys.StringKeysTip;

public class TabPanelOneTCX extends PtnPanel {

	private static final long serialVersionUID = 4832592275766772652L;
	
	private TabPanelOneAttrTCX tabPanelOneAttrTCX = null;

	private TabPanelOneStatusTCX tabPanelOneStatusTCX = null;

	private TabPanelOneOtherTCX tabPanelOneOtherTCX = null;
	
	private GridBagLayout gridBagLayout = null;

	public TabPanelOneTCX() {

		try {
			
			init();
			addLinter();
		} catch (Exception e) {

			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void init() throws Exception {

		try {

			tabPanelOneAttrTCX = new TabPanelOneAttrTCX();
			tabPanelOneStatusTCX = new TabPanelOneStatusTCX();
			tabPanelOneOtherTCX = new TabPanelOneOtherTCX();
			gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[] {1 };
			gridBagLayout.columnWeights = new double[] { 1 };
			gridBagLayout.rowHeights = new int[] { 200, 200, 100 };
			gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0 };
			
			this.setBackground(Color.WHITE);
			/* Tab页一 网元PTP配置 -> 界面加载 */
			this.add(tabPanelOneAttrTCX);/* 属性界面加载 */
			this.add(tabPanelOneStatusTCX);/* 状态界面加载 */
			this.add(tabPanelOneOtherTCX);/* 其它加载 */
			this.setGridBagLayout();/* Tab页一 网元PTP配置布局 */
			this.setLayout(gridBagLayout);
			btnSearch(true);
		} catch (Exception e) {

			throw e;
		}
	}
	
	/**
	 * <p>
	 * Tab页一 网元PTP配置布局
	 * </p>
	 * 
	 * @throws Exception
	 */
	private void setGridBagLayout() throws Exception {

		GridBagConstraints gridBagConstraints = null;
		try {

			gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.insets = new Insets(5, 10, 0, 10);
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(tabPanelOneAttrTCX, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 1;
			gridBagLayout.setConstraints(tabPanelOneStatusTCX, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 2;
			gridBagLayout.setConstraints(tabPanelOneOtherTCX, gridBagConstraints);
		} catch (Exception e) {

			throw e;
		} finally {

			gridBagConstraints = null;
		}
	}
	/**
	 * 增加3个按钮的单击事件
	 */
	private void addLinter() {
		//保存的事件
		tabPanelOneOtherTCX.getSave().addActionListener(new MyActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnSaveActionListener();
				
			}

			@Override
			public boolean checking() {
				
				return true;
			}
		});
		//查找事件
		tabPanelOneOtherTCX.getSearch().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnSearch(false);
				
			}
		});
		//时间同步
		tabPanelOneOtherTCX.getTimeSynchronize().addActionListener(new MyActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnSynchro();
			}

			@Override
			public boolean checking() {
				
				return true;
			}
		});
	}
	/**
	 * 时间同步事件
	 * 查设备    同步 DB
	 */
	private void btnSynchro(){
		try {
			DispatchUtil PtpSiteDispatch=new DispatchUtil(RmiKeys.RMI_PTPSITE);
			PtpSiteDispatch.synchro(ConstantUtil.siteId);			
			btnSearch(false);
			tabPanelOneOtherTCX.getTimeSynchronize().setOperateKey(EOperationLogType.TIMEMANAGESYNCHRO.getValue());
			tabPanelOneOtherTCX.getTimeSynchronize().setResult(1);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}
	/*保存事件*/
	private void btnSaveActionListener(){
		TimeManageInfo timeManageInfo=null;
		TimeManageInfoService_MB timeManageInfoService=null;
		try {
			timeManageInfoService = (TimeManageInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.TimeManageInfoService);
			timeManageInfo=new TimeManageInfo();
			//关联ID
			timeManageInfo.setSiteId(ConstantUtil.siteId);
			//模式
			ControlKeyValue modekey_broad = (ControlKeyValue) this.tabPanelOneAttrTCX.getMode_().getSelectedItem();
			timeManageInfo.setModel(Integer.parseInt(modekey_broad.getId()));
			//时钟类型
			timeManageInfo.setClockType(Integer.parseInt(this.tabPanelOneAttrTCX.getClockType_().getText()));
			//时钟精度
			timeManageInfo.setClockPrecision(Integer.parseInt(this.tabPanelOneAttrTCX.getClockAccuracy_().getText()));
		    //时钟方差
			timeManageInfo.setClockVariance(Integer.parseInt(this.tabPanelOneAttrTCX.getClockVariance_().getText()));
		   //优先级1
			timeManageInfo.setPriorOne(Integer.parseInt(this.tabPanelOneAttrTCX.getPriority1_().getText()));
			//优先级2
			timeManageInfo.setPriorTwo(Integer.parseInt(this.tabPanelOneAttrTCX.getPriority2_().getText()));
			//厂商OUI
			timeManageInfo.setManufacturerOUI(Integer.parseInt(this.tabPanelOneAttrTCX.getManufacturerOUI_().getText()));
			//时钟域
			timeManageInfo.setClockRegion(Integer.parseInt(this.tabPanelOneAttrTCX.getClockDomain_().getText()));
		    //透传时钟域1
			timeManageInfo.setClockRegionOne(Integer.parseInt(this.tabPanelOneAttrTCX.getPassThroughClockDomain1_().getText()));
		     //透傳時鐘2是否選中passThroughClockDomain2__
			timeManageInfo.setClockRegionTwoJbox(this.tabPanelOneAttrTCX.getPassThroughClockDomain2__().isSelected()?1:0);
			//透传时钟域2                                                                  
			timeManageInfo.setClockRegionTwo(Integer.parseInt(this.tabPanelOneAttrTCX.getPassThroughClockDomain2_().getText()));
			 //透傳時鐘3是否選中
			timeManageInfo.setClockRegionThreeJbox(this.tabPanelOneAttrTCX.getPassThroughClockDomain3__().isSelected()?1:0);
			//透传时钟域3
			timeManageInfo.setClockRegionThree(Integer.parseInt(this.tabPanelOneAttrTCX.getPassThroughClockDomain3_().getText()));
			 //透傳時鐘4是否選中
			timeManageInfo.setClockRegionFourJbox(this.tabPanelOneAttrTCX.getPassThroughClockDomain4__().isSelected()?1:0);
			//透传时钟域4
			timeManageInfo.setClockRegionFour(Integer.parseInt(this.tabPanelOneAttrTCX.getPassThroughClockDomain4_().getText()));
			//透传时钟域延时机制
			ControlKeyValue PassThroughClockDelayMechanismkey_broad = (ControlKeyValue) this.tabPanelOneAttrTCX.getPassThroughClockDelayMechanism_().getSelectedItem();
			timeManageInfo.setClockRegionDelay(Integer.parseInt(PassThroughClockDelayMechanismkey_broad.getId()));
			//跟随模式
			ControlKeyValue followUpModekey_broad = (ControlKeyValue) this.tabPanelOneAttrTCX.getFollowUpMode_().getSelectedItem();
			timeManageInfo.setFollowModel(Integer.parseInt(followUpModekey_broad.getId()));
			//Tod发送时间类型
			ControlKeyValue todTransmissionTimeTypekey_broad = (ControlKeyValue) this.tabPanelOneAttrTCX.getTodTransmissionTimeType_().getSelectedItem();
			timeManageInfo.setTodsendTime(Integer.parseInt(todTransmissionTimeTypekey_broad.getId()));
		    //时钟ID
			timeManageInfo.setTimeID(this.tabPanelOneStatusTCX.getClockID_().getText());
			//时钟类型
			timeManageInfo.setTimeType(this.tabPanelOneStatusTCX.getClockType_().getText());
			//父时钟ID
			timeManageInfo.setFtimeID(this.tabPanelOneStatusTCX.getParentClockID_().getText());
			//父时钟端口ID
			timeManageInfo.setFtimePort(this.tabPanelOneStatusTCX.getParentCLockPort_().getText());
			//跳数
			timeManageInfo.setLeapNumber(this.tabPanelOneStatusTCX.getHops_().getText());
			//1588和系统时间差值
			timeManageInfo.setSystemVarianceValue(this.tabPanelOneStatusTCX.getSystemTimeAnd1588Difference_().getText());
			//当前TOD状态
			timeManageInfo.setTodState(this.tabPanelOneStatusTCX.getCurrentTODStatus_().getText());
		    //祖时钟ID
			timeManageInfo.setzTimeID(this.tabPanelOneStatusTCX.getGrandParentClockID_().getText());
			//祖时钟类型
			timeManageInfo.setzTimeTpye(this.tabPanelOneStatusTCX.getGrandParentClockType_().getText());
			//祖时钟精度
			timeManageInfo.setzTimePrecision(this.tabPanelOneStatusTCX.getGrandParentClockAccuracy_().getText());
			//祖时钟方差
			timeManageInfo.setzTimeVariance(this.tabPanelOneStatusTCX.getGrandParentClockVariance_().getText());
			//祖时钟优先级1
			timeManageInfo.setzTimePriorOne(this.tabPanelOneStatusTCX.getGrandParentClockPriority1_().getText());
			//祖时钟优先级2
			timeManageInfo.setzTimePriorTwo(this.tabPanelOneStatusTCX.getGrandParentClockPriority2_().getText());
		    //操作数据库，保存值
			//如果存在就跟新，不存在就保存
			if(null!=timeManageInfoService.select(ConstantUtil.siteId)){
				timeManageInfoService.update(timeManageInfo);
			}else{
				timeManageInfoService.insertTimeManageInfo(timeManageInfo);
			}
			DispatchUtil ptpSiteDispatch=new DispatchUtil(RmiKeys.RMI_PTPSITE);
			String result=ptpSiteDispatch.excuteUpdate(timeManageInfo);
			
			//添加日志记录
			if(result.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))){
				tabPanelOneOtherTCX.getSave().setResult(1);
			}else{
				tabPanelOneOtherTCX.getSave().setResult(2);
			}
			tabPanelOneOtherTCX.getSave().setOperateKey(EOperationLogType.TIMEMANAGEUPDATE.getValue());
			DialogBoxUtil.succeedDialog(this, result);
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(timeManageInfoService);
		}
	}
	/**
	 * 查询 
	 * @param flag
	 *   true    界面初始化， 查 DB
	 *   false   查设备（按钮事件）
	 */
	private void btnSearch(boolean flag) {
		TimeManageInfo timeManageInfo=null;
		TimeManageInfoService_MB timeManageInfoService=null;
		try {
			/*界面初始化
			 * 刷新界面数据
			 *   DB
			 */
			if(flag){
				timeManageInfoService = (TimeManageInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.TimeManageInfoService);
				timeManageInfo=timeManageInfoService.select(ConstantUtil.siteId);
			}else{
				DispatchUtil ptpSite=new DispatchUtil(RmiKeys.RMI_PTPSITE);
				timeManageInfo=ptpSite.executeQueryTimeManageInfoBySites(ConstantUtil.siteId);
			}			
			if(null!=timeManageInfo){
			//模式
				super.getComboBoxDataUtil().comboBoxSelect(this.tabPanelOneAttrTCX.getMode_(), String.valueOf(timeManageInfo.getModel()));
				//时钟类型
				this.tabPanelOneAttrTCX.getClockType_().setText(String.valueOf(timeManageInfo.getClockType()));
				//时钟精度
				this.tabPanelOneAttrTCX.getClockAccuracy_().setText(String.valueOf(timeManageInfo.getClockPrecision()));
				//时钟方差 
				this.tabPanelOneAttrTCX.getClockVariance_().setText(String.valueOf(timeManageInfo.getClockVariance()));
				//优先级1
				this.tabPanelOneAttrTCX.getPriority1_().setText(String.valueOf(timeManageInfo.getPriorOne()));
				//优先级2
				this.tabPanelOneAttrTCX.getPriority2_().setText(String.valueOf(timeManageInfo.getPriorTwo()));
				//厂商OUI
				this.tabPanelOneAttrTCX.getManufacturerOUI_().setText(String.valueOf(timeManageInfo.getManufacturerOUI()));
				//时钟域
				this.tabPanelOneAttrTCX.getClockDomain_().setText(String.valueOf(timeManageInfo.getClockRegion()));
				//透传时钟域1
				this.tabPanelOneAttrTCX.getPassThroughClockDomain1_().setText(String.valueOf(timeManageInfo.getClockRegionOne()));
				//透傳時鐘2是否選中passThroughClockDomain2__
				this.tabPanelOneAttrTCX.getPassThroughClockDomain2__().setSelected(timeManageInfo.getClockRegionTwoJbox()==1?true:false);
				//透传时钟域2                                                                  
				this.tabPanelOneAttrTCX.getPassThroughClockDomain2_().setText(String.valueOf(timeManageInfo.getClockRegionTwo()));
				//透傳時鐘3是否選中
				this.tabPanelOneAttrTCX.getPassThroughClockDomain3__().setSelected(timeManageInfo.getClockRegionThreeJbox()==1?true:false); 
				//透传时钟域3
				this.tabPanelOneAttrTCX.getPassThroughClockDomain3_().setText(String.valueOf(timeManageInfo.getClockRegionThree()));
				 //透傳時鐘4是否選中
				this.tabPanelOneAttrTCX.getPassThroughClockDomain4__().setSelected(timeManageInfo.getClockRegionFourJbox()==1?true:false); 
				//透传时钟域4
				this.tabPanelOneAttrTCX.getPassThroughClockDomain4_().setText(String.valueOf(timeManageInfo.getClockRegionFour()));
				//透传时钟域延时机制
				super.getComboBoxDataUtil().comboBoxSelect(this.tabPanelOneAttrTCX.getPassThroughClockDelayMechanism_(), String.valueOf(timeManageInfo.getClockRegionDelay()));
				//跟随模式
				super.getComboBoxDataUtil().comboBoxSelect(this.tabPanelOneAttrTCX.getFollowUpMode_(), String.valueOf(timeManageInfo.getFollowModel()));
				//Tod发送时间类型
				super.getComboBoxDataUtil().comboBoxSelect(this.tabPanelOneAttrTCX.getTodTransmissionTimeType_(), String.valueOf(timeManageInfo.getTodsendTime()));
			    //时钟ID
				this.tabPanelOneStatusTCX.getClockID_().setText(String.valueOf(timeManageInfo.getTimeID()));
				//时钟类型
				this.tabPanelOneStatusTCX.getClockType_().setText(String.valueOf(timeManageInfo.getTimeType()));
				//父时钟ID
				this.tabPanelOneStatusTCX.getParentClockID_().setText(String.valueOf(timeManageInfo.getFtimeID()));
				//父时钟端口ID
				this.tabPanelOneStatusTCX.getParentCLockPort_().setText(String.valueOf(timeManageInfo.getFtimePort()));
				//跳数
				this.tabPanelOneStatusTCX.getHops_().setText(String.valueOf(timeManageInfo.getLeapNumber()));
				//1588和系统时间差值
				this.tabPanelOneStatusTCX.getSystemTimeAnd1588Difference_().setText(String.valueOf(timeManageInfo.getSystemVarianceValue()));
				//当前TOD状态
				this.tabPanelOneStatusTCX.getCurrentTODStatus_().setText(String.valueOf(timeManageInfo.getTodState()));
			    //祖时钟ID
				this.tabPanelOneStatusTCX.getGrandParentClockID_().setText(String.valueOf(timeManageInfo.getzTimeID()));
				//祖时钟类型
				this.tabPanelOneStatusTCX.getGrandParentClockType_().setText(String.valueOf(timeManageInfo.getzTimeTpye()));
				//祖时钟精度
				this.tabPanelOneStatusTCX.getGrandParentClockAccuracy_().setText(String.valueOf(timeManageInfo.getzTimePrecision()));
				//祖时钟方差
				this.tabPanelOneStatusTCX.getGrandParentClockVariance_().setText(String.valueOf(timeManageInfo.getzTimeVariance()));
				//祖时钟优先级1
				this.tabPanelOneStatusTCX.getGrandParentClockPriority1_().setText(String.valueOf(timeManageInfo.getzTimePriorOne()));
				//祖时钟优先级2
				this.tabPanelOneStatusTCX.getGrandParentClockPriority2_().setText(String.valueOf(timeManageInfo.getzTimePriorTwo()));
			}else{
				
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			 timeManageInfo=null;
			 UiUtil.closeService_MB(timeManageInfoService);
		}
		
	}
}
