package com.nms.ui.ptn.ne.macManagement.controller;

import java.util.List;
import com.nms.db.bean.ptn.MacLearningInfo;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.ptn.MacLearningService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.service.impl.util.ResultString;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.ne.macManagement.view.AddMacLearningDialog;
import com.nms.ui.ptn.ne.macManagement.view.MacLearningLimitPanel;

public class MacLearningLimitController extends AbstractController {
	private MacLearningLimitPanel view;
	
	public MacLearningLimitController(MacLearningLimitPanel macLearningLimitPanel) {
		this.view = macLearningLimitPanel;
	}
	
	/**
	 * 新建
	 */
	@Override
	public void openCreateDialog(){
		new AddMacLearningDialog(null, this.view);
	}

	/**
	 * 修改
	 */
	@Override
	public void openUpdateDialog(){
		if (this.view.getAllSelect().size() == 0) {
			DialogBoxUtil.errorDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
		} else {
			MacLearningInfo mac = this.view.getAllSelect().get(0);
			new AddMacLearningDialog(mac, this.view);
		}
		
	}

	/**
	 * 删除
	 */
	@Override
	public void delete(){
		List<MacLearningInfo> macList = null;
		DispatchUtil macDispatch = null;
		String resultStr = null;
		try {
			if (this.view.getAllSelect().size() == 0) {
				DialogBoxUtil.errorDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
			}else{
				macList = this.view.getAllSelect();
				macDispatch = new DispatchUtil(RmiKeys.RMI_MACLEARNINGLIMIT);
				resultStr = macDispatch.excuteDelete(macList);
				DialogBoxUtil.succeedDialog(view, resultStr);
				for(int i=0;i<macList.size();i++){
				  this.insertOpeLog(EOperationLogType.MACLEARNINGLIMITDELETE.getValue(), resultStr, null,null);
				}
				this.view.getRefreshButton().doClick();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			macList = null;
			macDispatch = null;
			resultStr = null;
		}

	}
	
	/**
	 * 刷新
	 */
	@Override
	public void refresh() {
		this.searchAndRefreshData();
	}

	private void searchAndRefreshData() {
		List<MacLearningInfo> allList = null;
		MacLearningService_MB service = null;
		try {
			service = (MacLearningService_MB) ConstantUtil.serviceFactory.newService_MB(Services.MACLEARN);
			allList = service.selectBySiteId(ConstantUtil.siteId);
			this.view.clear();
			this.view.initData(allList);
			this.view.updateUI();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
		}
	}

	/**
	 * 同步
	 */
	public void synchro() throws Exception {
		try {
			DispatchUtil macDispatch = new DispatchUtil(RmiKeys.RMI_MACLEARNINGLIMIT);
			String result = macDispatch.synchro(ConstantUtil.siteId);
			DialogBoxUtil.succeedDialog(null, result);
			// 添加日志记录
			PtnButton deleteButton = (PtnButton) this.view.getSynchroButton();
			deleteButton.setResult(1);
			this.insertOpeLog(EOperationLogType.MACLEARNINGSYNC.getValue(), ResultString.CONFIG_SUCCESS, null,null);		
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} 
	}
	
	private void insertOpeLog(int operationType, String result, Object oldMac, Object newMac){		
		AddOperateLog.insertOperLog(null, operationType, result, oldMac, newMac, ConstantUtil.siteId,ResourceUtil.srcStr(StringKeysTitle.TIT_MAC_LIMIT_MANAGE),"");
	}
}
