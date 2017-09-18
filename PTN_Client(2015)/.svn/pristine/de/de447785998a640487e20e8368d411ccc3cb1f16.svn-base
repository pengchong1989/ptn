package com.nms.ui.ptn.ne.statusData.ptpPortStatus.controller;


import java.util.List;


import com.nms.db.bean.ptn.PtpPortStatus;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.ptn.ne.statusData.ptpPortStatus.view.PtpPortStatusPanel;



public class PtpPortStatusController extends AbstractController{
	private List<PtpPortStatus> infos;
	private PtpPortStatusPanel view;
	public PtpPortStatusController(PtpPortStatusPanel ptpPortStatusPanel) {
		view = ptpPortStatusPanel;
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
