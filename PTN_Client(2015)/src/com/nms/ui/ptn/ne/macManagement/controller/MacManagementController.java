package com.nms.ui.ptn.ne.macManagement.controller;

import java.util.ArrayList;
import java.util.List;
import com.nms.db.bean.ptn.MacManagementInfo;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.ptn.MacManageService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.ne.macManagement.view.AddMacDialog;
import com.nms.ui.ptn.ne.macManagement.view.MacManagementPanel;

public class MacManagementController extends AbstractController {
	private MacManagementPanel view;
	
	public MacManagementController(MacManagementPanel macManagementPanel) {
		this.view = macManagementPanel;
	}
	
	/**
	 * 新建
	 */
	@Override
	public void openCreateDialog(){
		new AddMacDialog(null, this.view);
	}

	/**
	 * 修改
	 */
	@Override
	public void openUpdateDialog(){
		if (this.view.getAllSelect().size() == 0) {
			DialogBoxUtil.errorDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
		} else {
			MacManagementInfo mac = this.view.getAllSelect().get(0);
			new AddMacDialog(mac, this.view);
		}
		
	}

	/**
	 * 删除
	 */
	@Override
	public void delete(){
		List<MacManagementInfo> macList = null;
		DispatchUtil macManagementDispatch = null;
		String resultStr = null;
		List<Integer> siteIds = null;
		try {
			if (this.view.getAllSelect().size() == 0) {
				DialogBoxUtil.errorDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
			}else{
				SiteUtil siteUtil = new SiteUtil();
				if(1==siteUtil.SiteTypeOnlineUtil(ConstantUtil.siteId)){
					WhImplUtil wu = new WhImplUtil();
					siteIds = new ArrayList<Integer>();
					siteIds.add(ConstantUtil.siteId);
					String str=wu.getNeNames(siteIds);
					DialogBoxUtil.errorDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_NOT_DELETEONLINE)+""+str+ResourceUtil.srcStr(StringKeysTip.TIP_ONLINENOT_DELETEONLINE));				
					for(int i=0;i<this.view.getAllSelect().size();i++){
					 this.insertOpeLog(EOperationLogType.DELETEMACMANAGE.getValue(), ResultString.CONFIG_FAILED, null,this.view.getAllSelect().get(i));
					}
					return;  		    		
					}
				
				macList = this.view.getAllSelect();
				macManagementDispatch = new DispatchUtil(RmiKeys.RMI_BLACKLISTMAC);
				resultStr = macManagementDispatch.excuteDelete(macList);
				DialogBoxUtil.succeedDialog(view, resultStr);
				for(int i=0;i<macList.size();i++){
			    	this.insertOpeLog(EOperationLogType.DELETEMACMANAGE.getValue(), resultStr, null,null);
				}
				this.view.getRefreshButton().doClick();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			macList = null;
			macManagementDispatch = null;
			resultStr = null;
			siteIds=null;
		}

	}
	private void insertOpeLog(int operationType, String result, Object oldMac, Object newMac){	
		AddOperateLog.insertOperLog(null, operationType, result, oldMac, newMac, ConstantUtil.siteId,ResourceUtil.srcStr(StringKeysTitle.TIT_BLACK_MAC_MANAGE),"");		
	}
	
	/**
	 * 刷新
	 */
	@Override
	public void refresh() {
		this.searchAndRefreshData();
	}

	private void searchAndRefreshData() {
		List<MacManagementInfo> allList = null;
		MacManageService_MB service = null;
		try {
			service = (MacManageService_MB) ConstantUtil.serviceFactory.newService_MB(Services.MACMANAGE);
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
			DispatchUtil macDispatch = new DispatchUtil(RmiKeys.RMI_BLACKLISTMAC);
			String result = macDispatch.synchro(ConstantUtil.siteId);
			DialogBoxUtil.succeedDialog(null, result);
			// 添加日志记录
			this.insertOpeLog(EOperationLogType.SYSMACMANAGE.getValue(), result, null,null);
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} 
	}
}

