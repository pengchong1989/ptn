package com.nms.ui.ptn.systemconfig.dialog.fieldConfig.controller;

import java.util.Iterator;
import java.util.List;
import twaver.Element;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.basicinfo.dialog.site.AddSiteDialog;
import com.nms.ui.ptn.ne.allConfig.view.DataDownLoadDialog;
import com.nms.ui.ptn.systemconfig.dialog.fieldConfig.view.FieldConfigRightPanel;
import com.nms.ui.ptn.systemconfig.dialog.fieldConfig.view.WorkIpDoalog;

public class SiteListController extends AbstractController {

	private FieldConfigRightPanel fieldConfigRightPanel;

	public SiteListController(FieldConfigRightPanel fieldConfigRightPanel) {
		this.setFieldConfigRightPanel(fieldConfigRightPanel);
	}

	@Override
	public void refresh() throws Exception {

		if (null != fieldConfigRightPanel.getField()) {
			SiteService_MB siteService = null;
			List<SiteInst> siteInstList = null;
			try {
				// view.getRightPanel().getSiteInstTable().clear();
				fieldConfigRightPanel.getTable().clear();
				siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
				SiteInst siteInst = new SiteInst();
				siteInst.setFieldID(fieldConfigRightPanel.getField().getId());
				siteInstList = siteService.selectByFieldId(siteInst);
				fieldConfigRightPanel.clear();
				fieldConfigRightPanel.setField(fieldConfigRightPanel.getField());
				fieldConfigRightPanel.initData(siteInstList);
				fieldConfigRightPanel.updateUI();
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			} finally {
				UiUtil.closeService_MB(siteService);
			}
		}
	}
	@Override
	public void delete() {
		
//		SiteService siteService = null;
		int result=0;
//		int returnValue;
		String resultStr = "";
		try {
			DispatchUtil siteDispatch = new DispatchUtil(RmiKeys.RMI_SITE);
			SiteInst site = fieldConfigRightPanel.getTable().getSelect();
//			siteService = (SiteService) ConstantUtil.serviceFactory.newService(Services.SITE);
			//验证网元是否可以被删除
//			returnValue = siteService.isDeleteSite(site);
//			if(returnValue==0){
//				result=siteService.delete(site);
		
				resultStr = siteDispatch.excuteDelete(site);
				if (resultStr.equals(ResultString.CONFIG_SUCCESS)) {
					result = 1;
				}
				this.refresh();
				fieldConfigRightPanel.getNeConfigView().getController().initTree();
//			} 	
//			else if (returnValue == 2) {
//				DialogBoxUtil.errorDialog(fieldConfigRightPanel, ResourceUtil.srcStr(StringKeysTip.TIP_TUNNEL_NEHASSEGMENT));
//			} else if (returnValue == 1) {
//				DialogBoxUtil.errorDialog(fieldConfigRightPanel, ResourceUtil.srcStr(StringKeysTip.TIP_NEHASSEGMENT));
//			}
			//添加日志记录
			   PtnButton deleteButton=this.fieldConfigRightPanel.getDeleteButton();
			   deleteButton.setOperateKey(EOperationLogType.SITELISTDELETE.getValue());
			   int operationResult=0;
			   if(result>0){
				    operationResult=1;
			     }else{
				    operationResult=2;
			     }
			
			   deleteButton.setResult(operationResult);
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
//			UiUtil.closeService(siteService);
		}
	}

	//删除之前的验证
	@Override
	public boolean deleteChecking() throws Exception {
		boolean flag = false;
		int returnValue;
		SiteService_MB siteService = null;
		try {			
			SiteInst site = fieldConfigRightPanel.getTable().getSelect();
			//在线网元托管后不能删除，改为离线网元可删除
			SiteUtil siteUtil = new SiteUtil();
			int a = ((SiteUtil) siteUtil).SiteTypeOnlineUtil(site.getSite_Inst_Id());
			if(a==1){
				DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_NOT_DELETEONLINE));
				return false;
			}
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			//验证网元是否可以被删除
			returnValue=siteService.isDeleteSite(site);
			if (returnValue == 2) {
				DialogBoxUtil.errorDialog(fieldConfigRightPanel, ResourceUtil.srcStr(StringKeysTip.TIP_TUNNEL_NEHASSEGMENT));
				UiUtil.insertOperationLog(EOperationLogType.SITEEXITTUNNEL.getValue());
				return false;
			} else if (returnValue == 1) {
				DialogBoxUtil.errorDialog(fieldConfigRightPanel, ResourceUtil.srcStr(StringKeysTip.TIP_NEHASSEGMENT));
				UiUtil.insertOperationLog(EOperationLogType.SITEEXITSEGMENT.getValue());
				return false;
			}
			flag = true;
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally{
			UiUtil.closeService_MB(siteService);
		}
		return flag;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void openUpdateDialog() {
		try {
			if (fieldConfigRightPanel.getTable().getSelectedRowCount() != 1) {
				DialogBoxUtil.errorDialog(fieldConfigRightPanel, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
				UiUtil.insertOperationLog(EOperationLogType.UPDATASITE.getValue());
				return;
			} else {
				SiteInst site = fieldConfigRightPanel.getTable().getSelect();
				AddSiteDialog addSiteDialog = new AddSiteDialog(true, site.getSite_Inst_Id() + "");
				addSiteDialog.setLocation(UiUtil.getWindowWidth(addSiteDialog.getWidth()), UiUtil.getWindowHeight(addSiteDialog.getHeight()));
				addSiteDialog.setVisible(true);
				//刷新网元表格
				this.refresh();
				
				//刷新左边树，并选中所修改的网元
				fieldConfigRightPanel.getNeConfigView().getController().getFieldConfigAction().initTree(fieldConfigRightPanel.getNeConfigView());
				List<Element> elements = fieldConfigRightPanel.getNeConfigView().getLeftPane().getBox().getAllElements();
				Iterator<Element> elementIterator = elements.iterator();
				Element element = null;
				while(elementIterator.hasNext()){
					element = (Element) elementIterator.next();
					if(element.getUserObject() instanceof SiteInst){
						SiteInst inst = (SiteInst) element.getUserObject();
						if(inst.getSite_Inst_Id() == site.getSite_Inst_Id()){
							break;
						}
					}
				}
				fieldConfigRightPanel.getNeConfigView().getLeftPane().getBox().getSelectionModel().setSelection(element);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	// 下发配置
	public void confirmActionPerformed() {
		
		try {
			if (fieldConfigRightPanel.getField() != null){
				WorkIpDoalog workIpDoalog = new WorkIpDoalog(fieldConfigRightPanel.getField());
				UiUtil.showWindow(workIpDoalog, 390, 300);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	//网元校时
	public void currectTimeActionPerformed(){
		
//		String result = null;
//		if (fieldConfigRightPanel.getTable().getSelectedRowCount() != 1) {
//			DialogBoxUtil.errorDialog(fieldConfigRightPanel, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
//			return;
//		} else {
//			SiteInst siteInst = fieldConfigRightPanel.getTable().getSelect();
//			SiteService siteService = null;
//			try {
//				siteService=(SiteService) ConstantUtil.serviceFactory.newService(Services.SITE);
//				if(siteService.getManufacturer(siteInst.getSite_Inst_Id()) == EManufacturer.CHENXIAO.getValue()){
//					DialogBoxUtil.errorDialog(fieldConfigRightPanel, "700系列网元不支持此功能");
//					return;
//				}
//				
//				DispatchUtil siteDispatch = new DispatchUtil(RmiKeys.RMI_SITE);
//				result = siteDispatch.currectTime(siteInst);
//				//添加日志记录
//			this.fieldConfigRightPanel.getCorrectTimeButton().setOperateKey(EOperationLogType.SITELISTCURRECTTIME.getValue());
//				int operationResult=0;
//				if(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS).equals(result)){
//					operationResult=1;
//				}else{
//					operationResult=2;
//				}
//				this.fieldConfigRightPanel.getCorrectTimeButton().setResult(operationResult);
//				DialogBoxUtil.succeedDialog(fieldConfigRightPanel, result);
//			} catch (Exception e) {
//				ExceptionManage.dispose(e,this.getClass());
//			} finally {
//				UiUtil.closeService(siteService);
//			}
//		}
	}
	
	/**
	 * 清楚设备配置
	 */
	public void clearSiteActionPerformed(){
		String result = null;
		if (fieldConfigRightPanel.getTable().getSelectedRowCount() != 1) {
			DialogBoxUtil.errorDialog(fieldConfigRightPanel, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
			UiUtil.insertOperationLog(EOperationLogType.CLEARSITE.getValue());
			return;
		} else {
			SiteInst siteInst = fieldConfigRightPanel.getTable().getSelect();
			try {
				DispatchUtil siteDispatch = new DispatchUtil(RmiKeys.RMI_SITE);
				result = siteDispatch.clearSite(siteInst);
				//添加日志记录
				this.fieldConfigRightPanel.getClearButton().setOperateKey(EOperationLogType.SITELISTINITIALIZTION.getValue());
				int operationResult=0;
				if(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS).equals(result)){
					operationResult=1;
				}else{
					operationResult=2;
				}
				this.fieldConfigRightPanel.getClearButton().setResult(operationResult);
				DialogBoxUtil.succeedDialog(fieldConfigRightPanel, result);
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
		}
	}
	
	/**
	 * 清楚网管数据
	 */
	public void clearDataActionPerformed(){
		String result = null;
		if (fieldConfigRightPanel.getTable().getSelectedRowCount() != 1) {
			DialogBoxUtil.errorDialog(fieldConfigRightPanel, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
			UiUtil.insertOperationLog(EOperationLogType.CLEARSITE.getValue());
			return;
		} else {
			@SuppressWarnings("unused")
			SiteInst siteInst = fieldConfigRightPanel.getTable().getSelect();
//				DispatchUtil siteDispatch = new DispatchUtil(RmiKeys.RMI_SITE);
			try {
//				result = siteDispatch.clearData(siteInst);
				DialogBoxUtil.succeedDialog(fieldConfigRightPanel, result);
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
		}
	}
	
	/*
	 * 网元同步
	 */
	@Override
	public void synchro() {
		if (fieldConfigRightPanel.getTable().getSelectedRowCount() == 0) {
			DialogBoxUtil.errorDialog(fieldConfigRightPanel, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
			UiUtil.insertOperationLog(EOperationLogType.SITESYNCHRO.getValue());
			return;
		}
		new DataDownLoadDialog(fieldConfigRightPanel,true,"synchro");
	}
	
	public FieldConfigRightPanel getFieldConfigRightPanel() {
		return fieldConfigRightPanel;
	}

	public void setFieldConfigRightPanel(FieldConfigRightPanel fieldConfigRightPanel) {
		this.fieldConfigRightPanel = fieldConfigRightPanel;
	}
	
	//数据下载
	public void dataDownLoadActionPerformed() {
		if (fieldConfigRightPanel.getTable().getSelectedRowCount() == 0) {
			DialogBoxUtil.errorDialog(fieldConfigRightPanel, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_MORE));
			UiUtil.insertOperationLog(EOperationLogType.SITEDATA.getValue());
			return;
		}
		new DataDownLoadDialog(fieldConfigRightPanel,true,"dataDownLoad");
	}
}
