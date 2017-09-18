package com.nms.ui.ptn.ne.statusData.protectStatus.controller;

import java.util.List;

import com.nms.db.bean.ptn.PortStatus;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.ptn.ne.statusData.protectStatus.view.ProtectStatusPanel;

public class ProtectStatusController extends AbstractController{
	private List<PortStatus> infos;
	private ProtectStatusPanel view;
	public ProtectStatusController(ProtectStatusPanel protectStatusPanel) {
		view = protectStatusPanel;
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
