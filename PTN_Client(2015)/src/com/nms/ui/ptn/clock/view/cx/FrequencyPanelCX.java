package com.nms.ui.ptn.clock.view.cx;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import com.nms.db.bean.ptn.clock.FrequencyInfoNeClock;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.ptn.clock.FrequencyInfoNeClockService_MB;
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
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.clock.view.cx.frequency.TabPanelOneCX;
import com.nms.ui.ptn.clock.view.cx.frequency.TabPanelTwoCX;

public class FrequencyPanelCX extends PtnPanel {

	private static final long serialVersionUID = 187732657056707436L;
	private JTabbedPane tabbedPane = null;
	private GridBagLayout gridBagLayout = null;
	private TabPanelOneCX tabPanelOneCX = null;
	private TabPanelTwoCX tabPanelTwoCX = null;

	public FrequencyPanelCX() {
		try {
			init();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void init() throws Exception {

		try {

			tabbedPane = new JTabbedPane();
			gridBagLayout = new GridBagLayout();
			tabPanelOneCX = new TabPanelOneCX();
			tabPanelTwoCX = new TabPanelTwoCX();
			tabbedPane.add(ResourceUtil.srcStr(StringKeysTab.TAB_NE_CLOCK_STATUS_OR_ATTRIBUTE), tabPanelOneCX);
			tabbedPane.add(ResourceUtil.srcStr(StringKeysTab.TAB_NE_CLOCK_SOURCE_CONFIG_OR_STATUS), tabPanelTwoCX);
			setGridBagLayout();/* 主页面布局 */
			this.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_FREQUENCY_MANAGE)));
			this.setLayout(gridBagLayout);
			this.add(tabbedPane);
			this.initData();
			this.addListener();
		} catch (Exception e) {

			throw e;
		}
	}

	/**
	 * <p>
	 * 主页面布局
	 * </p>
	 * 
	 * @throws Exception
	 */
	private void setGridBagLayout() throws Exception {

		GridBagConstraints gridBagConstraints = null;
		try {
			gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.gridheight = 1;
			gridBagConstraints.gridwidth = 1;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.insets = new Insets(0, 10, 0, 10);
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagLayout.setConstraints(tabbedPane, gridBagConstraints);
		} catch (Exception e) {
			throw e;
		} finally {

			gridBagConstraints = null;
		}
	}

	/**
	 * 
	 * 增加監聽事件
	 */
	public void addListener() {
		try {
			this.tabPanelOneCX.getTabPanelOneOtherCX().getSave().addActionListener(new MyActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					btnSaveListener();
				}

				@Override
				public boolean checking() {
					
					return true;
				}
			});

			this.tabPanelOneCX.getTabPanelOneOtherCX().getSearch().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					btnSrearchListener();
				}
			});
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 *查找数据根据stieDI
	 */
	private void btnSrearchListener() {
		FrequencyInfoNeClock frequencyInfo_neClock = null;
		try {
			DispatchUtil frequency = new DispatchUtil(RmiKeys.RMI_FREQUENCYSITE);
			frequencyInfo_neClock=frequency.executeQueryFrequencyBySites(ConstantUtil.siteId);				
			if (null != frequencyInfo_neClock) {
				// 启动选择协议
				super.getComboBoxDataUtil().comboBoxSelect(this.tabPanelOneCX.getTabPanelOneAttrCX().getEnableSelectedProtocol_(), frequencyInfo_neClock.getStartTreaty()
						+ "");
				// 操作模式
				super.getComboBoxDataUtil().comboBoxSelect(this.tabPanelOneCX.getTabPanelOneAttrCX().getOperationMode_(), frequencyInfo_neClock.getHandleModel() + "");
				// 自由振荡级别
				super.getComboBoxDataUtil().comboBoxSelect(this.tabPanelOneCX.getTabPanelOneAttrCX().getFreeOscillationLevel_(), frequencyInfo_neClock
						.getOscillationLevel()
						+ "");
				// 质量未知映射等级
				super.getComboBoxDataUtil().comboBoxSelect(this.tabPanelOneCX.getTabPanelOneAttrCX().getQualityUnknownReflectLevel_(), frequencyInfo_neClock
						.getQualityLevel()
						+ "");
				// 导出时钟模式
				super.getComboBoxDataUtil().comboBoxSelect(this.tabPanelOneCX.getTabPanelOneAttrCX().getExportClockMode_(), frequencyInfo_neClock.getExportClockModel()
						+ "");
				// 外时钟压制门限
				super.getComboBoxDataUtil().comboBoxSelect(this.tabPanelOneCX.getTabPanelOneAttrCX().getExternalClockSupressThreshold_(), frequencyInfo_neClock
						.getClockSuppress()
						+ "");
				// 系统时钟等待回复时间
				this.tabPanelOneCX.getTabPanelOneAttrCX().getSystemClockWaitingForRecoveryTime_().setValue(
						frequencyInfo_neClock.getSystemRecoverTime());
				// 导出时钟等待回复时间
				this.tabPanelOneCX.getTabPanelOneAttrCX().getExportClockWaitingForRecoveryTime_().setValue(
						frequencyInfo_neClock.getExportRecoverTime());
				// 系统工作状态
				this.tabPanelOneCX.getTabPanelOneStatusCX().getSystemClockPanel().getWorkingStatus_().setText(
						frequencyInfo_neClock.getSystemJobStatus());
				// 系统实际参考源
				this.tabPanelOneCX.getTabPanelOneStatusCX().getSystemClockPanel().getActualReferenceSource_().setText(
						frequencyInfo_neClock.getSystemSourcce());
				// 导出时钟工作状态
				this.tabPanelOneCX.getTabPanelOneStatusCX().getExportClockPanel().getWorkingStatus_().setText(
						frequencyInfo_neClock.getExportJobStatus());
				// 导出时钟实际参考源
				this.tabPanelOneCX.getTabPanelOneStatusCX().getExportClockPanel().getActualReferenceSource_().setText(
						frequencyInfo_neClock.getExportSourcce());
			}else{
				//System.out.println( ResourceUtil.srcStr(StringKeysTip.TIP_DATA_SITE));
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
		}
	}

	/**
	 *初始化界面
	 */
	private void initData() {
		int setid = 0;
		FrequencyInfoNeClockService_MB frequencyInfo_neClockService = null;
		FrequencyInfoNeClock frequencyInfo_neClock = null;
		try {
			frequencyInfo_neClockService = (FrequencyInfoNeClockService_MB) ConstantUtil.serviceFactory.newService_MB(Services.FrequencyInfo_neClockService);
			setid = ConstantUtil.siteId;
			frequencyInfo_neClock = frequencyInfo_neClockService.select(setid);
			// 这个siteID 存在就用数据库的数据，不存在就直接附初始值
			if (null != frequencyInfo_neClock) {
				// 启动选择协议
				super.getComboBoxDataUtil().comboBoxSelect(this.tabPanelOneCX.getTabPanelOneAttrCX().getEnableSelectedProtocol_(), 
						              frequencyInfo_neClock.getStartTreaty()
						+ "");
				// 操作模式
				super.getComboBoxDataUtil().comboBoxSelect(this.tabPanelOneCX.getTabPanelOneAttrCX().getOperationMode_(), 
						               frequencyInfo_neClock.getHandleModel() + "");
				// 自由振荡级别
				super.getComboBoxDataUtil().comboBoxSelect(this.tabPanelOneCX.getTabPanelOneAttrCX().getFreeOscillationLevel_(), 
						              frequencyInfo_neClock.getOscillationLevel()+ "");
				// 质量未知映射等级
				super.getComboBoxDataUtil().comboBoxSelect(this.tabPanelOneCX.getTabPanelOneAttrCX().getQualityUnknownReflectLevel_(), 
						              frequencyInfo_neClock.getQualityLevel()+ "");
				// 导出时钟模式
				super.getComboBoxDataUtil().comboBoxSelect(this.tabPanelOneCX.getTabPanelOneAttrCX().getExportClockMode_(), 
						              frequencyInfo_neClock.getExportClockModel()+ "");
				// 外时钟压制门限
				super.getComboBoxDataUtil().comboBoxSelect(this.tabPanelOneCX.getTabPanelOneAttrCX().getExternalClockSupressThreshold_(), 
					                frequencyInfo_neClock.getClockSuppress()+ "");
				// 系统时钟等待回复时间
				this.tabPanelOneCX.getTabPanelOneAttrCX().getSystemClockWaitingForRecoveryTime_().setValue(frequencyInfo_neClock.getSystemRecoverTime());
				// 导出时钟等待回复时间
				this.tabPanelOneCX.getTabPanelOneAttrCX().getExportClockWaitingForRecoveryTime_().setValue( frequencyInfo_neClock.getExportRecoverTime());
				// 系统工作状态
				this.tabPanelOneCX.getTabPanelOneStatusCX().getSystemClockPanel().getWorkingStatus_().setText(frequencyInfo_neClock.getSystemJobStatus());
				// 系统实际参考源
				this.tabPanelOneCX.getTabPanelOneStatusCX().getSystemClockPanel().getActualReferenceSource_().setText(frequencyInfo_neClock.getSystemSourcce());
				// 导出时钟工作状态
				this.tabPanelOneCX.getTabPanelOneStatusCX().getExportClockPanel().getWorkingStatus_().setText(frequencyInfo_neClock.getExportJobStatus());
				// 导出时钟实际参考源
				this.tabPanelOneCX.getTabPanelOneStatusCX().getExportClockPanel().getActualReferenceSource_().setText(frequencyInfo_neClock.getExportSourcce());
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(frequencyInfo_neClockService);
		}
	}

	/**
	 *监听保存按钮事物
	 */
	private void btnSaveListener() {
		FrequencyInfoNeClock frequencyInfo_neClock = null;
		FrequencyInfoNeClockService_MB frequencyInfo_neClockService = null;
		try {
			frequencyInfo_neClock = new FrequencyInfoNeClock();
			frequencyInfo_neClockService = (FrequencyInfoNeClockService_MB) ConstantUtil.serviceFactory.newService_MB(Services.FrequencyInfo_neClockService);
			frequencyInfo_neClock.setSiteId(ConstantUtil.siteId);
			// 启动选择协议
			ControlKeyValue selectedProtocolkey_broad = (ControlKeyValue) this.tabPanelOneCX.getTabPanelOneAttrCX().getEnableSelectedProtocol_().getSelectedItem();
			frequencyInfo_neClock.setStartTreaty(Integer.parseInt(selectedProtocolkey_broad.getId()));
			// 操作模式
			ControlKeyValue operationModeKey_broad = (ControlKeyValue) this.tabPanelOneCX.getTabPanelOneAttrCX().getOperationMode_().getSelectedItem();
			frequencyInfo_neClock.setHandleModel(Integer.parseInt(operationModeKey_broad.getId()));
			// 自由振荡级别
			ControlKeyValue freeOscillationLevelModeKey_broad = (ControlKeyValue) this.tabPanelOneCX.getTabPanelOneAttrCX().getFreeOscillationLevel_().getSelectedItem();
			frequencyInfo_neClock.setOscillationLevel(Integer.parseInt(freeOscillationLevelModeKey_broad.getId()));
			// 质量未知映射等级
			ControlKeyValue qualityUnknownReflectLevelModeKey_broad = (ControlKeyValue) this.tabPanelOneCX.getTabPanelOneAttrCX().getQualityUnknownReflectLevel_().getSelectedItem();
			frequencyInfo_neClock.setQualityLevel(Integer.parseInt(qualityUnknownReflectLevelModeKey_broad.getId()));
			// 导出时钟模式
			ControlKeyValue exportClockModeModeKey_broad = (ControlKeyValue) this.tabPanelOneCX.getTabPanelOneAttrCX().getExportClockMode_().getSelectedItem();
			frequencyInfo_neClock.setExportClockModel(Integer.parseInt(exportClockModeModeKey_broad.getId()));
			// 外时钟压制门限
			ControlKeyValue externalClockSupressThresholdKey_broad = (ControlKeyValue) this.tabPanelOneCX.getTabPanelOneAttrCX().getExternalClockSupressThreshold_().getSelectedItem();
			frequencyInfo_neClock.setClockSuppress(Integer.parseInt(externalClockSupressThresholdKey_broad.getId()));
			// 系统时钟等待回复时间
			frequencyInfo_neClock.setSystemRecoverTime((Integer) this.tabPanelOneCX.getTabPanelOneAttrCX().getSystemClockWaitingForRecoveryTime_().getValue());
			// 导出时钟等待回复时间
			frequencyInfo_neClock.setExportRecoverTime((Integer) this.tabPanelOneCX.getTabPanelOneAttrCX().getExportClockWaitingForRecoveryTime_().getValue());
			// 系统工作状态
			frequencyInfo_neClock.setSystemJobStatus(this.tabPanelOneCX.getTabPanelOneStatusCX().getSystemClockPanel().getWorkingStatus_().getText());
			// 系统实际参考源
			frequencyInfo_neClock.setSystemSourcce(this.tabPanelOneCX.getTabPanelOneStatusCX().getSystemClockPanel().getActualReferenceSource_().getText());
			// 导出时钟工作状态
			frequencyInfo_neClock.setExportJobStatus(this.tabPanelOneCX.getTabPanelOneStatusCX().getExportClockPanel().getWorkingStatus_().getText());
			// 导出时钟实际参考源
			frequencyInfo_neClock.setExportSourcce(this.tabPanelOneCX.getTabPanelOneStatusCX().getExportClockPanel().getActualReferenceSource_().getText());
			// 设置关联网元表主键
			frequencyInfo_neClock.setSiteId(ConstantUtil.siteId);
			// 操作数据库這個存在SITEID 就跟新，如果不岑在就插入
			if (null != frequencyInfo_neClockService.select(ConstantUtil.siteId)) {
				frequencyInfo_neClockService.update(frequencyInfo_neClock);
				
			} else {
				frequencyInfo_neClockService.insertFrequencyInfo_neClock(frequencyInfo_neClock);
			}
		
			
			DispatchUtil frequencySiteDispatch = new DispatchUtil(RmiKeys.RMI_FREQUENCYSITE);
			String result=frequencySiteDispatch.excuteUpdate(frequencyInfo_neClock);
			//添加日志记录
			this.tabPanelOneCX.getTabPanelOneOtherCX().getSave().setOperateKey(EOperationLogType.FREQUENCYINFOUPDATE.getValue());
			if(result.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))){
				this.tabPanelOneCX.getTabPanelOneOtherCX().getSave().setResult(1);
			}else{
				this.tabPanelOneCX.getTabPanelOneOtherCX().getSave().setResult(2);
			}
			DialogBoxUtil.succeedDialog(this, result);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(frequencyInfo_neClockService);
		}
	}

	public static void main(String[] args) {

		try {
			FrequencyPanelCX frequencyPanelNew = new FrequencyPanelCX();
			JFrame frame = new JFrame();
			frame.setSize(500, 400);
			frame.setContentPane(frequencyPanelNew);
			frame.setVisible(true);
		} catch (Exception e) {

			ExceptionManage.dispose(e,FrequencyPanelCX.class);
		}
	}

}
