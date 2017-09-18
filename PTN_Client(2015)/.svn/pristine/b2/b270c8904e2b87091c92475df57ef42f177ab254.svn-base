package com.nms.ui.ptn.ne.ssMacStudy.controller;


import java.util.ArrayList;
import java.util.List;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.ptn.SecondMacStudyService_MB;
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
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.db.bean.ptn.SsMacStudy;

import com.nms.ui.ptn.ne.ssMacStudy.view.AddSsMacStudyDialog;
import com.nms.ui.ptn.ne.ssMacStudy.view.StaticSecondMacPanel;

public class StaticSecondStudyController extends AbstractController {
	private StaticSecondMacPanel view;
	
	public StaticSecondStudyController(StaticSecondMacPanel staticSecondMacPanel) {
		this.view = staticSecondMacPanel;
	}
	
	/**
	 * 新建
	 */
	@Override
	public void openCreateDialog(){
		
		SsMacStudy ssMacStudy = new SsMacStudy();
		if (this.view.getSelect() != null) {
			this.view.getSelect().setName("");
		}	
		new AddSsMacStudyDialog(ssMacStudy, this);
	}

	/**
	 * 修改
	 */
	@Override
	public void openUpdateDialog(){
		
		if (this.view.getAllSelect().size() == 0) {
			DialogBoxUtil.errorDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
		} else {
			SsMacStudy mac = this.view.getAllSelect().get(0);
			new AddSsMacStudyDialog(mac, this);
		}
		
	}

	/**
	 * 删除
	 */
	@Override
	public void delete(){
		List<SsMacStudy> ssMacStudy = null;
		DispatchUtil SsmDispatch = null;
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
			    	this.insertOpeLog(EOperationLogType.DELETESECONDMAC.getValue(), ResultString.CONFIG_FAILED, null, this.view.getAllSelect().get(i));	
				}
				return;  		    		
				}
			ssMacStudy = this.view.getAllSelect();
			SsmDispatch = new DispatchUtil(RmiKeys.RMI_MACSTUDY);				
			resultStr = SsmDispatch.excuteDelete(ssMacStudy);
			DialogBoxUtil.succeedDialog(this.view, resultStr);
			//添加日志记录
			for(int i=0;i<ssMacStudy.size();i++){
			   this.insertOpeLog(EOperationLogType.SECONDMACDELETE.getValue(), resultStr, null, null);	
			}
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			resultStr = null;
			ssMacStudy = null;
			SsmDispatch = null;
			siteIds = null;
		}

	}
	
	private void insertOpeLog(int operationType, String result, Object oldMac, Object newMac){
		AddOperateLog.insertOperLog(null, operationType, result, oldMac, newMac, ConstantUtil.siteId,ResourceUtil.srcStr(StringKeysTab.TAB_STATIC_SECOND_MAC),"");		
	}
	
	/**
	 * 选中一条记录后，查看详细信息
	 */
	@Override
	public void initDetailInfo() {
		
		try {
			initInfoData();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 查询详细信息
	 * 
	 * @throws Exception
	 */
	private void initInfoData() throws Exception {

		SsMacStudy ssMacStudy = null;
		try {
			ssMacStudy = this.view.getSelect();						       
			this.view.getssMacStudyInfo().initData(ssMacStudy);
			this.view.getssMacStudyInfo().updateUI();
		} catch (Exception e) {
			throw e;
		} finally {
			ssMacStudy = null;
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
		
		List<SsMacStudy> allList = null;
		SecondMacStudyService_MB secondMacStudyService = null;
		SsMacStudy ssMacStudyInfo =null;
		try {
			ssMacStudyInfo = new SsMacStudy();
			ssMacStudyInfo.setSiteId(ConstantUtil.siteId);
			secondMacStudyService = (SecondMacStudyService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SECONDMACSTUDY);			
			allList = secondMacStudyService.selectBySecondMacStudyInfo(ConstantUtil.siteId);
			this.view.clear();	
			this.view.getssMacStudyInfo().clear();
			this.view.initData(allList);
			this.view.updateUI();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(secondMacStudyService);
		}
	}
	
	
	@Override
	public void synchro(){
		try {
			DispatchUtil dispatch = new DispatchUtil(RmiKeys.RMI_MACSTUDY);
			String result = dispatch.synchro(ConstantUtil.siteId);
			DialogBoxUtil.succeedDialog(this.view, result);
			this.insertOpeLog(EOperationLogType.SYSSECONDMAC.getValue(), result, null, null);		
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}
}

