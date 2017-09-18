package com.nms.ui.ptn.performance.controller;

import java.util.List;

import com.nms.db.bean.perform.Capability;
import com.nms.model.perform.CapabilityService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.ptn.performance.view.PerformanceDescPanel;
import com.nms.ui.ptn.performance.view.UpdatePerformanceDescDialog;

/**
 * 告警描述控制类
 * 
 * @author kk
 * 
 */
public class PerformanceDescController extends AbstractController {

	private PerformanceDescPanel performanceDescPanel = null;

	public PerformanceDescController(PerformanceDescPanel performanceDescPanel) {
		this.performanceDescPanel = performanceDescPanel;
	}

	@Override
	public void refresh() throws Exception {

		CapabilityService_MB capabilityServiceMB = null;
		List<Capability> capabilityList = null;

		try {
			capabilityServiceMB = (CapabilityService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Capability);
			capabilityList = capabilityServiceMB.select();

			this.performanceDescPanel.clear();
			this.performanceDescPanel.initData(capabilityList);

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(capabilityServiceMB);
			capabilityList = null;
		}
	}
	
	@Override
	public void openUpdateDialog(){
		UpdatePerformanceDescDialog updatePerformanceDescDialog = new UpdatePerformanceDescDialog(this.performanceDescPanel, true, this.performanceDescPanel.getSelect().getId());
		updatePerformanceDescDialog.setLocation(UiUtil.getWindowWidth(updatePerformanceDescDialog.getWidth()), UiUtil.getWindowHeight(updatePerformanceDescDialog.getHeight()));
		updatePerformanceDescDialog.setVisible(true);
	}

}
