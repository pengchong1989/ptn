package com.nms.ui.ptn.ne.statusData.ptpBasicStatus.controller;


import java.util.List;


import com.nms.db.bean.ptn.PtpBasicStatus;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.ptn.ne.statusData.ptpBasicStatus.view.PtpBasicStatusPanel;




public class PtpBasicStatusController extends AbstractController{
	private List<PtpBasicStatus> infos;
	private PtpBasicStatusPanel view;
	public PtpBasicStatusController(PtpBasicStatusPanel ptpBaiscStatusPanel) {
		view = ptpBaiscStatusPanel;
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
