package com.nms.ui.ptn.clock.controller;

import java.util.ArrayList;
import java.util.List;
import com.nms.db.bean.ptn.clock.ClockSource;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.ptn.clock.FrequencyClockManageService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.clock.view.cx.dialog.FMCreateDialog;
import com.nms.ui.ptn.clock.view.cx.dialog.FMUpdateDialog;
import com.nms.ui.ptn.clock.view.cx.frequency.TabPanelTwoCX;

public class FrequencyPanelControllerCX extends AbstractController {

	private TabPanelTwoCX tabPanelTwoCX = null;

	public FrequencyPanelControllerCX(TabPanelTwoCX tabPanelTwoCX) {
		this.tabPanelTwoCX = tabPanelTwoCX;
	}

	/**
	 * <p>
	 * 点击创建按钮弹出对话框
	 * </p>
	 */
	public void openCreateDialog() throws Exception {

		try {
			new FMCreateDialog(tabPanelTwoCX);
		} catch (Exception e) {

			throw e;
		}
	};

	/**
	 * <p>
	 * 数据修改
	 * </p>
	 */
	public void openUpdateDialog() throws Exception {
		ClockSource clockSource = null;
		try {
			clockSource = new ClockSource();
			clockSource = tabPanelTwoCX.getSelect();
			new FMUpdateDialog(clockSource, tabPanelTwoCX);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * <p>
	 * 数据删除
	 * </p>
	 */
	public void delete() throws Exception {
		List<ClockSource> clockSourceList = null;
		DispatchUtil clockSourceDispatch = new DispatchUtil(RmiKeys.RMI_CLOCKSOURCE);
		try {
			clockSourceList = tabPanelTwoCX.getAllSelect();
			String result=clockSourceDispatch.excuteDelete(clockSourceList);
			PtnButton deleteButton=(PtnButton) this.tabPanelTwoCX.getDeleteButton();
			deleteButton.setOperateKey(EOperationLogType.FREQUENCYDELETE.getValue());
			if(result.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))){
				deleteButton.setResult(1);
			}else{
				deleteButton.setResult(2);
			}
			DialogBoxUtil.succeedDialog(null, result);
			//添加日志记录
			
			this.refresh();
		} catch (Exception e) {
			throw e;
		} finally {
			clockSourceDispatch = null;
			clockSourceList = null;
		}
	}

	/**
	 * <p>
	 * 加载页面数据
	 * </p>
	 */
	public void refresh() throws Exception {
		List<ClockSource> list = null;
		FrequencyClockManageService_MB frequencyClockManageService = null;
		try {
			frequencyClockManageService = (FrequencyClockManageService_MB) ConstantUtil.serviceFactory.newService_MB(Services.FrequencyClockManageService);
			list = new ArrayList<ClockSource>();
			list = frequencyClockManageService.select(ConstantUtil.siteId);
			this.tabPanelTwoCX.clear();
			this.tabPanelTwoCX.initData(list);
			this.tabPanelTwoCX.updateUI();
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(frequencyClockManageService);
		}
	}

	/**
	 * <p>
	 * 同步功能
	 * </p>
	 */
	public void synchro() throws Exception {
		DispatchUtil clockSourceCXServiceImpl;
		try {
			clockSourceCXServiceImpl = new DispatchUtil(RmiKeys.RMI_CLOCKSOURCE);
			clockSourceCXServiceImpl.synchro(ConstantUtil.siteId);
			//添加日志记录
			PtnButton deleteButton=(PtnButton) this.tabPanelTwoCX.getSynchroButton();
			deleteButton.setOperateKey(EOperationLogType.FREQUENCYSYNCHRO.getValue());
			deleteButton.setResult(1);
			this.refresh();
		} catch (Exception e) {

			throw e;
		}
	}
}
