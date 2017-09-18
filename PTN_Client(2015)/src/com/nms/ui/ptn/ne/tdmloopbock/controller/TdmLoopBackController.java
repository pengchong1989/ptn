package com.nms.ui.ptn.ne.tdmloopbock.controller;

import java.util.List;
import com.nms.db.bean.equipment.port.E1Info;
import com.nms.db.enums.EManufacturer;
import com.nms.model.equipment.port.E1InfoService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.ptn.ne.tdmloopbock.view.TdmLoopBackPanel;

public class TdmLoopBackController extends AbstractController{
	
	private TdmLoopBackPanel view;
	public TdmLoopBackController (TdmLoopBackPanel tdmLoopBackPanel){
		this.setView(tdmLoopBackPanel);
	}

	@Override
	public void refresh() throws Exception {
		searchAndrefreshdata();
	}

	private void searchAndrefreshdata(){
		E1InfoService_MB e1Service = null;
		SiteService_MB siteService = null;
		try{
			siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			if(siteService.getManufacturer(ConstantUtil.siteId) == EManufacturer.WUHAN.getValue()){
				e1Service = (E1InfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.E1Info);
				E1Info e1 = new E1Info();
				e1.setSiteId(ConstantUtil.siteId);
				List<E1Info> tdmList = e1Service.selectByCondition(e1);
				view.clear();
				view.initData(tdmList);
				view.updateUI();
			}
		}catch(Exception e){
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(e1Service);
			UiUtil.closeService_MB(siteService);
		}
	}
	public TdmLoopBackPanel getView() {
		return view;
	}

	public void setView(TdmLoopBackPanel view) {
		this.view = view;
	}
	
}
