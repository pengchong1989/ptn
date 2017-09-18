package com.nms.ui.ptn.e1;

import java.util.List;

import com.nms.db.bean.equipment.port.E1Info;
import com.nms.model.equipment.port.E1InfoService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.UiUtil;

public class E1Contorller extends AbstractController{

	private EPanel panel;

	public E1Contorller(EPanel panel) {
		this.panel = panel;
	}
	@Override
	public void refresh() throws Exception {
		E1InfoService_MB e1InfoService = null;
		List<E1Info> e1InfoList = null;
		E1Info e1Info = new E1Info();
		try {
			e1InfoService = (E1InfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.E1Info);
			e1Info.setSiteId(ConstantUtil.siteId);
			e1InfoList = e1InfoService.selectByCondition(e1Info);
			this.panel.clear();
			this.panel.initData(e1InfoList);
			this.panel.updateUI();
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(e1InfoService);
			e1InfoList = null;
		}
	}
}
