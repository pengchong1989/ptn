package com.nms.ui.ptn.ne.statusData.pwProtectStatus.controller;


import java.util.List;

import com.nms.db.bean.ptn.PortStatus;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.ptn.ne.statusData.pwProtectStatus.view.PwProtectStatusPanel;



public class PwProtectStatusController extends AbstractController{
	private List<PortStatus> infos;
	private PwProtectStatusPanel view;
	public PwProtectStatusController(PwProtectStatusPanel pwProtectStatusPanel) {
		view = pwProtectStatusPanel;
		try {
			refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	@Override
	public void refresh() throws Exception {
	}
}
