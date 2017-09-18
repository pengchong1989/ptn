package com.nms.ui.ptn.systemconfig.dialog.siteInitialise.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.shlef.SiteService_MB;
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
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.systemconfig.dialog.siteInitialise.IPConfigDialog;
import com.nms.ui.ptn.systemconfig.dialog.siteInitialise.SNConfigDialog;
import com.nms.ui.ptn.systemconfig.dialog.siteInitialise.SiteInitialiseConfig;
import com.nms.ui.ptn.systemconfig.dialog.siteInitialise.SiteSNTable;

public class SiteInitialiseController extends AbstractController{

	private SiteInitialiseConfig siteConfig=null;
	
	public SiteInitialiseController(SiteInitialiseConfig siteConfig){
		this.siteConfig=siteConfig;
		refresh();
	}
	@Override
	public void refresh(){
		List<SiteInst> initInfoList=null;
			try {
				initInfoList=new ArrayList<SiteInst>();
				initInfoList=allFiledInitiInfo();
				siteConfig.clear();
				if(siteConfig.getLocationNe() != null){
					for (int i = 0; i < initInfoList.size(); i++) {
						if(siteConfig.getLocationNe().getSite_Inst_Id() == initInfoList.get(i).getSite_Inst_Id()){
							initInfoList.get(i).setLocationNe(true);
							break;
						}
					}
				}
				siteConfig.initData(initInfoList);
				siteConfig.updateUI();
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			} finally {

			}
		
	}
	/**
	 * 清楚设备配置
	 */
	public void clearSiteActionPerformed(){
		String result = null;
		if (siteConfig.getTable().getSelectedRowCount() != 1) {
			DialogBoxUtil.errorDialog(siteConfig, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
			UiUtil.insertOperationLog(EOperationLogType.CLEARSITE.getValue());
			return;
		} else {
			int isDelete = DialogBoxUtil.confirmDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_INITIALISE));
			if(isDelete == 0){
				SiteInst siteInst = siteConfig.getTable().getSelect();
				try {
					DispatchUtil siteDispatch = new DispatchUtil(RmiKeys.RMI_SITE);
					result = siteDispatch.clearSite(siteInst);
					//添加日志记录
					if(ResultString.CONFIG_SUCCESS.equals(result)){
						AddOperateLog.insertOperLog(this.siteConfig.getClearButton(), EOperationLogType.SITELISTINITIALIZTION.getValue(), ResultString.CONFIG_SUCCESS, 
								null, null, siteInst.getSite_Inst_Id(), siteInst.getCellId(), "");
					}
					DialogBoxUtil.succeedDialog(siteConfig, result);
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
			
		}
	}
	
	private List<SiteInst> allFiledInitiInfo( ){
		
		SiteService_MB siteService = null;
		List<SiteInst> siteInstList = null;
		List<SiteInst> siteInstListReturn = null;
			try {
				siteInstListReturn=new ArrayList<SiteInst>();
				siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);				
					siteInstList = siteService.selectRootSite(ConstantUtil.user);
					for (SiteInst site : siteInstList) {
						siteInstListReturn.add(site);
					}
				
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}finally{
				UiUtil.closeService_MB(siteService);
			}
			return siteInstListReturn;
	}

	
	/**
	 * 查询本网元SN
	 */
	public void queryLocationSnActionPerformed(SiteInst siteInst){
		List<SiteInst> siteInsts = null;
		String str = "";
		try {
			DispatchUtil dispatchUtil = new DispatchUtil(RmiKeys.RMI_SITE);
			siteInsts = dispatchUtil.querySn(siteInst, 1);
			if(siteInsts.size()>0){
				str = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
				DialogBoxUtil.succeedDialog(siteConfig, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
			}else{
				str = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FALSE);
				DialogBoxUtil.errorDialog(siteConfig, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FALSE));
			}
			AddOperateLog.insertOperLog(this.siteConfig.getClearButton(), EOperationLogType.QUERY_LOCAL_SN.getValue(), str, 
					null, null, siteInst.getSite_Inst_Id(), siteInst.getCellId(), "");
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
		}
		
	}
	
	/**
	 * 查询相邻网元SN
	 */
	public void queryAdjoinSnActionPerformed(SiteInst siteInst){
		List<SiteInst> siteInsts = null;
		Map<String, SiteInst> map = new HashMap<String,SiteInst>();
		List<SiteInst> needs = new ArrayList<SiteInst>();
		try {
			if("0.0.0.0".equals(siteInst.getCellDescribe())){
				DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_IP));
				return;
			}
			DispatchUtil dispatchUtil = new DispatchUtil(RmiKeys.RMI_SITE);
			siteInsts = dispatchUtil.querySn(siteInst, 2);
			for (int i = 0; i < siteInsts.size(); i++) {
				map.put(siteInsts.get(i).getSn(), siteInsts.get(i));
			}
			Iterator<String> keys = map.keySet().iterator();
			while(keys.hasNext()){
				String key = keys.next();
				needs.add(map.get(key));
			}
			SiteSNTable siteSNTable = new SiteSNTable(needs);
			UiUtil.showWindow(siteSNTable, 600, 350);
			AddOperateLog.insertOperLog(this.siteConfig.getClearButton(), EOperationLogType.QUERY_ADJOIN_SN.getValue(), ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS), 
					null, null, siteInst.getSite_Inst_Id(), siteInst.getCellId(), "");
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
		}
		
	}
	
	/**
	 * 设置网元IP或SN
	 */
	public void setIPorSnActionPerformed(SiteInst siteInst,int type){
		IPConfigDialog configDialog = new IPConfigDialog(siteInst,type);
		UiUtil.showWindow(configDialog, 380, 320);
		this.refresh();
	}
	
	/**
	 * 查询远程网元SN
	 */
	public void queryRemoteSn(SiteInst siteInst){
		List<SiteInst> siteInsts = null;
		try {
			if("0.0.0.0".equals(siteInst.getCellDescribe())){
				DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_IP));
				return;
			}
			DispatchUtil dispatchUtil = new DispatchUtil(RmiKeys.RMI_SITE);
			siteInsts = dispatchUtil.querySn(siteInst, 3);
			if(siteInsts.size()>0){
				DialogBoxUtil.succeedDialog(siteConfig, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
			}else{
				DialogBoxUtil.errorDialog(siteConfig, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FALSE));
				return;
			}
			AddOperateLog.insertOperLog(this.siteConfig.getClearButton(), EOperationLogType.QUERY_REMOTE_SN.getValue(), ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS), 
					null, null, siteInst.getSite_Inst_Id(), siteInst.getCellId(), "");
			for (int i = 0; i < siteConfig.getTable().getAllElement().size(); i++) {
				if(siteInsts.get(0).getSite_Inst_Id() == siteConfig.getTable().getAllElement().get(i).getSite_Inst_Id()){
					siteConfig.getTable().getAllElement().get(i).setSn(siteInsts.get(0).getSn());
					break;
				}
			}
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
		}
	}
	
	public void replaceSite(SiteInst siteInst){
//		if(siteInst.getSn() == null || siteInst.getSn().equals("")){
//			DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_NO_SN_NOTSUPPORT));
//			return;
//		}
		SNConfigDialog snConfigDialog = new SNConfigDialog(siteInst);
		UiUtil.showWindow(snConfigDialog, 380, 320);
		this.refresh();
	}
}
