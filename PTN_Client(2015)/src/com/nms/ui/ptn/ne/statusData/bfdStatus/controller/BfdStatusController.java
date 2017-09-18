package com.nms.ui.ptn.ne.statusData.bfdStatus.controller;

import java.util.List;

import com.nms.db.bean.ptn.PortStatus;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.ptn.ne.statusData.bfdStatus.view.BfdStatusPanel;

public class BfdStatusController extends AbstractController{
	private List<PortStatus> infos;
	private BfdStatusPanel view;
	public BfdStatusController(BfdStatusPanel bfdStatusPanel) {
		view = bfdStatusPanel;
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
