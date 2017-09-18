package com.nms.ui.ptn.ne.acl.controller;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.AclInfo;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.ptn.AclService_MB;
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
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.ne.acl.view.AclPanel;
import com.nms.ui.ptn.ne.acl.view.AddAclDialog;

public class AclController extends AbstractController{
	private  AclPanel view ;

	public AclController(AclPanel aclPanel) {
		this.view = aclPanel;
	}

	@Override
	public void refresh() throws Exception {
		AclService_MB aclService = null;
		List<AclInfo> aclInfoList = null;
		try {
			this.view.clear();
			aclInfoList = new ArrayList<AclInfo>();
			aclService = (AclService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ACLSERCVICE);
			aclInfoList = aclService.select(ConstantUtil.siteId);
				
			this.view.initData(aclInfoList);
			this.view.updateUI();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			aclInfoList = null;
			UiUtil.closeService_MB(aclService);
		}
	}
	//新建
	@Override
	public void openCreateDialog() throws Exception {
		AddAclDialog dialog = new AddAclDialog(null,this);
		dialog.setSize(new Dimension(550,400));
		dialog.setLocation(UiUtil.getWindowWidth(dialog.getWidth()), UiUtil.getWindowHeight(dialog.getHeight()));
		dialog.setResizable(false);
		dialog.setVisible(true);
	};
	
	@Override
	// 修改
	public void openUpdateDialog() throws Exception {
		try {
			if (this.view.getAllSelect().size() == 0) {
				DialogBoxUtil.errorDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
			}else{
				AddAclDialog dialog = new AddAclDialog(view.getSelect(),this);
				dialog.setSize(new Dimension(550,400));
				dialog.setLocation(UiUtil.getWindowWidth(dialog.getWidth()), UiUtil.getWindowHeight(dialog.getHeight()));
				dialog.setResizable(false);
				dialog.setVisible(true);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
		
	};
	
	@Override
	public void synchro() throws Exception {
		DispatchUtil dispath = null; 
		try {
			dispath = new DispatchUtil(RmiKeys.RMI_ACL);
			String result = dispath.synchro(ConstantUtil.siteId);
			DialogBoxUtil.succeedDialog(null, result);
			this.insertOpeLog(EOperationLogType.SYSACL.getValue(), result, null, null);
			refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	};
	
	private void insertOpeLog(int operationType, String result, Object oldMac, Object newMac){
		AddOperateLog.insertOperLog(null, operationType, result, oldMac, newMac,ConstantUtil.siteId,ResourceUtil.srcStr(StringKeysPanel.PANEL_ACL_MANAGE),"");		
	}
	
	@Override
	// 删除
	public void delete() throws Exception {
		DispatchUtil dispath = null; 
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
					   this.insertOpeLog(EOperationLogType.DELETEACL.getValue(), ResultString.CONFIG_FAILED, null, this.view.getAllSelect().get(i));
					}
					return;  		    		
					}
				dispath = new DispatchUtil(RmiKeys.RMI_ACL);
				String result = dispath.excuteDelete(this.view.getAllSelect());
				DialogBoxUtil.succeedDialog(null, result);
				for(int i=0;i<this.view.getAllSelect().size();i++){
				   this.insertOpeLog(EOperationLogType.ACLDELETE.getValue(), result, null, null);
				}
				refresh();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			siteIds = null;
			dispath = null;
		}
	};
}
