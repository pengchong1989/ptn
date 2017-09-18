package com.nms.ui.ptn.clock.controller;

import java.util.ArrayList;
import java.util.List;
import com.nms.db.bean.ptn.clock.ExternalClockInterface;
import com.nms.model.ptn.clock.ExternalClockInterfaceService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.ptn.clock.view.cx.clockinterface.TabPanelOneTICX;
import com.nms.ui.ptn.clock.view.cx.dialog.ExternalClockDialog;
import com.nms.ui.ptn.clock.view.cx.dialog.ExternalClockUpdata;
import com.nms.ui.ptn.clock.view.cx.dialog.ExternalClockUpdataOther;

public class ExternalCIPanelControllerCX extends AbstractController{

	private TabPanelOneTICX tabPanelOneTICX = null;

	public ExternalCIPanelControllerCX(TabPanelOneTICX tabPanelOneTICX) {
		this.tabPanelOneTICX = tabPanelOneTICX;
	}

	/**
	 * <p>
	 * 点击创建按钮弹出对话框
	 * </p>
	 */
	@Override
	public void openCreateDialog() throws Exception {

		try {
			new ExternalClockDialog(tabPanelOneTICX);
		} catch (Exception e) {

			throw e;
		}
	};

	/**
	 * <p>
	 * 数据修改
	 * </p>
	 */
	@Override
	public void openUpdateDialog() throws Exception {

		ExternalClockInterface externalClockInterface = null;
		try {

			externalClockInterface = new ExternalClockInterface();
			externalClockInterface =  tabPanelOneTICX.getSelect();
			if(externalClockInterface.getInterfaceName().contains("extclk")){
				new ExternalClockUpdata(tabPanelOneTICX,externalClockInterface);
			}else{
			   new ExternalClockUpdataOther(tabPanelOneTICX,externalClockInterface); 
			}
		} catch (Exception e) {

			throw e;
		}
	}

	/**
	 * <p>
	 * 加载页面数据
	 * </p>
	 */
	@Override
	public void refresh() throws Exception {

		List<ExternalClockInterface> list = null;
		ExternalClockInterfaceService_MB externalClockInterfaceService=null;
		ExternalClockInterface externalClockInterface=null;
		try {
			externalClockInterfaceService=(ExternalClockInterfaceService_MB)ConstantUtil.serviceFactory.newService_MB(Services.ExternalClockInterfaceService);
			list = new ArrayList<ExternalClockInterface>();
			externalClockInterface=new ExternalClockInterface();
			externalClockInterface.setSiteId(ConstantUtil.siteId);
			list=externalClockInterfaceService.select(externalClockInterface);
			this.tabPanelOneTICX.clear();
			this.tabPanelOneTICX.initData(list);
			this.tabPanelOneTICX.updateUI();
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(externalClockInterfaceService);
		}
	}

	/**
	 * <p>
	 * 同步功能
	 * </p>
	 */
	@Override
	public void synchro() throws Exception {
		DispatchUtil externalClockDispatch;
	
		try {
			externalClockDispatch = new DispatchUtil(RmiKeys.RMI_EXTERNALCLOCK);
			externalClockDispatch.synchro(ConstantUtil.siteId);
			//添加日志记录
//			AddOperateLog.insertOperLog(  this.tabPanelOneTICX.getSynchroButton(), EOperationLogType.EXTERNALSYNCHRO.getValue(), null);
			
			this.refresh();
		} catch (Exception e1) {

			throw e1;
		}
	}
}
