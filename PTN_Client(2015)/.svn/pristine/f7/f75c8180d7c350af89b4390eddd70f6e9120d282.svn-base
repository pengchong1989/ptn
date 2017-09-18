package com.nms.ui.ptn.systemconfig.dialog.udConfigure.contriller;

import java.util.ArrayList;
import java.util.List;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.ptn.systemconfig.dialog.udConfigure.view.UploadDownloadConfigureRightPanle;

public class UploadDownloadConfigureRightController extends AbstractController{
	
	private UploadDownloadConfigureRightPanle view;
	public UploadDownloadConfigureRightController(UploadDownloadConfigureRightPanle uploadDownloadConfigureRightPanle) {
		view = uploadDownloadConfigureRightPanle;
		try {
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	@Override
	public void refresh() throws Exception {

		SiteService_MB siteService = null;
		List<SiteInst> siteInstList = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			view.getTable().clear();
			siteInstList = new ArrayList<SiteInst>();
			if(view.getIntegers() != null && view.getIntegers().size()>0){
				for(Integer integer : view.getIntegers()){
					SiteInst siteInst = siteService.select(integer);
					siteInstList.add(siteInst);
				}
			}
			view.updateUI();
			this.view.initData(siteInstList);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}
	
	}


}
