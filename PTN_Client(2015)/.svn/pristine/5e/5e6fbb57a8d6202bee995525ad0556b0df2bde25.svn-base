package com.nms.ui.ptn.ne.blackAndWhiteMacManagement.controller;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.BlackAndwhiteMacInfo;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.ptn.BlackWhiteMacService_MB;
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
import com.nms.ui.ptn.ne.blackAndWhiteMacManagement.view.AddBalckAndWhiteMacDialog;
import com.nms.ui.ptn.ne.blackAndWhiteMacManagement.view.BlackAndWhiteMacManagementPanel;

public class BlackAndWhiteMacManagementController extends AbstractController {
	
	private BlackAndWhiteMacManagementPanel view;
	
	public BlackAndWhiteMacManagementController(BlackAndWhiteMacManagementPanel macManagementPanel) {
		this.view = macManagementPanel;
	}
	
	/**
	 * 新建
	 */
	@Override
	public void openCreateDialog(){
		new AddBalckAndWhiteMacDialog(null, this.view);
	}

	/**
	 * 修改
	 */
	@Override
	public void openUpdateDialog(){
		if (this.view.getAllSelect().size() == 0) {
			DialogBoxUtil.errorDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
		} else {
			new AddBalckAndWhiteMacDialog(this.view.getAllSelect().get(0), this.view);
		}
		
	}

	/**
	 * 删除
	 */
	@Override
	public void delete(){
		List<BlackAndwhiteMacInfo> macList = null;
		DispatchUtil macDispatch = null;
		String resultStr = null;
		List<Integer> siteIds = null;
		try {
			SiteUtil siteUtil = new SiteUtil();
			if(1==siteUtil.SiteTypeOnlineUtil(ConstantUtil.siteId)){
				WhImplUtil wu = new WhImplUtil();
				siteIds = new ArrayList<Integer>();
				siteIds.add(ConstantUtil.siteId);
				String str=wu.getNeNames(siteIds);
				DialogBoxUtil.errorDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_NOT_DELETEONLINE)+""+str+ResourceUtil.srcStr(StringKeysTip.TIP_ONLINENOT_DELETEONLINE));
				for(int i=0;i<this.view.getAllSelect().size();i++){
					   this.insertOpeLog(EOperationLogType.DELETEBLACKANDWHITEMAC.getValue(),ResultString.CONFIG_FAILED, null,this.view.getAllSelect().get(i));
				}
				return;  		    		
				}
			macList = this.view.getAllSelect();
			macDispatch = new DispatchUtil(RmiKeys.RMI_BLACKWHITEMAC);
			resultStr = macDispatch.excuteDelete(macList);
			DialogBoxUtil.succeedDialog(view, resultStr);
			for(int i=0;i<macList.size();i++){
			   this.insertOpeLog(EOperationLogType.BLACKANDWHITEMAC.getValue(),resultStr, null,null);
			}
			this.view.getRefreshButton().doClick();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			macList = null;
			macDispatch = null;
			resultStr = null;
			siteIds =null;
		}

	}
	private void insertOpeLog(int operationType, String result, Object oldMac, Object newMac){		
		AddOperateLog.insertOperLog(null, operationType, result, oldMac, newMac, ConstantUtil.siteId,ResourceUtil.srcStr(StringKeysTitle.TIT_BLACK_WHILTE_MAC_MANAGE),"");		
    }
	
	/**
	 * 删除前的验证
	 * 
	 * @return true 验证成功， false 验证失败
	 * @throws Exception
	 */
	@Override
	public boolean deleteChecking() throws Exception {
		
		boolean flag = false;
		try {
			if (this.view.getAllSelect().size() == 0) {
				DialogBoxUtil.errorDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
				return false;
			}
			flag = true;
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return flag;
	}
	/**
	 * 刷新
	 */
	@Override
	public void refresh() {
		this.searchAndRefreshData();
	}

	private void searchAndRefreshData() {
		BlackWhiteMacService_MB blackWhiteMacService = null;
		List<BlackAndwhiteMacInfo> allList = null;
		BlackAndwhiteMacInfo blackAndwhiteMacInfo =null;
		try {
			blackAndwhiteMacInfo = new BlackAndwhiteMacInfo();
			blackAndwhiteMacInfo.setSiteId(ConstantUtil.siteId);
			blackWhiteMacService = (BlackWhiteMacService_MB) ConstantUtil.serviceFactory.newService_MB(Services.BLACKWHITEMAC);
			allList = blackWhiteMacService.selectByBlackAndwhiteMacInfo(blackAndwhiteMacInfo);
			this.view.clear();
			this.view.initData(allList);
			this.view.updateUI();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(blackWhiteMacService);
			allList = null;
			blackAndwhiteMacInfo =null;
		}
	}
	
	/**
	 * 同步
	 */
	public void synchro() throws Exception {
		try {
			DispatchUtil macDispatch = new DispatchUtil(RmiKeys.RMI_BLACKWHITEMAC);
			String result = macDispatch.synchro(ConstantUtil.siteId);
			DialogBoxUtil.succeedDialog(null, result);
			// 添加日志记录
			UiUtil.insertOperationLog(EOperationLogType.SYSBLACKWHITEMAC.getValue(), result);
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} 
	}
}
