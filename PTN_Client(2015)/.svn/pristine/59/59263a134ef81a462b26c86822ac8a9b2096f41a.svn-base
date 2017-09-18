package com.nms.ui.ptn.upgradeManage.controller;

import java.util.List;

import com.nms.db.bean.equipment.manager.UpgradeManage;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.service.impl.util.ResultString;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysOperaType;
import com.nms.ui.ptn.upgradeManage.view.UpgradeManagePanel;

public class UpgradeManageController {
	private UpgradeManagePanel view;
	private List<UpgradeManage> upgradeManages;
	public UpgradeManageController(UpgradeManagePanel view) {
		this.view = view;
	}

	private void refresh(){
		SiteService_MB siteService = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			SiteInst siteInst = siteService.select(ConstantUtil.siteId);
			DispatchUtil siteDispatch = new DispatchUtil(RmiKeys.RMI_SITE);
			siteInst.setCardNumber(view.getType()+"");
			upgradeManages = siteDispatch.softwareSummary(siteInst);
			this.view.clear();
			this.view.initData(upgradeManages);
			this.view.updateUI();
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}
	}
	
//手动去下载摘要
 public void downServison(){
	    refresh();
		view.setUpgradeManages(upgradeManages);
		AddOperateLog.insertOperLog(view.getDownSoftVersion(), EOperationLogType.DOWNSSERVISON.getValue(), ResultString.CONFIG_SUCCESS, 
				null, null, ConstantUtil.siteId, ResourceUtil.srcStr(StringKeysBtn.BTN_UPGRADE_SOFTWARE), "");
 }
}
