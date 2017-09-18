package com.nms.ui.ptn.ne.ecn.route.staticroute.controller;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.equipment.port.PortStm;
import com.nms.model.equipment.port.PortStmService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.ptn.ne.ecn.route.staticroute.view.SaveStaticRouteDialog;
import com.nms.ui.ptn.ne.ecn.route.staticroute.view.StaticRoutePanel;

public class StaticRouteController extends AbstractController {
	private StaticRoutePanel panel;

	public StaticRouteController(StaticRoutePanel staticRoutePanel) {
		this.panel = staticRoutePanel;
	}

	@Override
	public void refresh() throws Exception {
		PortStmService_MB portstmservice = null;
		List<PortStm> portStms = null;
		try {
			portStms= new ArrayList<PortStm>();
			portstmservice = (PortStmService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTSTM);
			portStms = portstmservice.queryBySiteid(ConstantUtil.siteId);
			this.panel.clear();
			this.panel.initData(portStms);
			this.panel.updateUI();
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(portstmservice);
			portStms = null;
		}
	}
	
	
	@Override
	public void openCreateDialog(){
		PortStm portstm = this.panel.getSelect();
		SaveStaticRouteDialog updatesdhdialog=new SaveStaticRouteDialog(portstm, panel);
	}
}
