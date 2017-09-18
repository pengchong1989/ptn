package com.nms.ui.ptn.ne.sdhtimeslot.controller;

import java.util.List;

import com.nms.db.bean.equipment.port.PortStmTimeslot;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.port.PortStmTimeslotService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.ptn.ne.sdhtimeslot.view.SDHTimeslotPanel;
import com.nms.ui.ptn.ne.sdhtimeslot.view.UpdateTimeslotDialog;

public class SDHTimeslotController extends AbstractController {

	private SDHTimeslotPanel panel;

	public SDHTimeslotController(SDHTimeslotPanel sdhTimeslotPanel) {
		this.panel = sdhTimeslotPanel;
	}

	@Override
	public void refresh() throws Exception {
		PortStmTimeslotService_MB portStmTimeslotService = null;
		List<PortStmTimeslot> portStmTimeslotList = null;
		try {
			portStmTimeslotService = (PortStmTimeslotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTSTMTIMESLOT);

			portStmTimeslotList = portStmTimeslotService.select(ConstantUtil.siteId);

			this.panel.clear();
			this.panel.initData(portStmTimeslotList);
			this.panel.updateUI();

		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(portStmTimeslotService);
			portStmTimeslotList = null;
		}
	}
	
	@Override
	public void openUpdateDialog(){
		PortStmTimeslot portStmTimeslot = this.panel.getSelect();
		UpdateTimeslotDialog updateTimeslotDialog=new UpdateTimeslotDialog(portStmTimeslot,panel);
	}
	
	@Override
	public void synchro() {
		DispatchUtil portStmTimeslotDispatch = null;
		try {
			portStmTimeslotDispatch = new DispatchUtil(RmiKeys.RMI_PORTSTMTIMESLOT);
			portStmTimeslotDispatch.synchro(ConstantUtil.siteId);
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			portStmTimeslotDispatch = null;
		}
	}

}
