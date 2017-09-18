package com.nms.ui.ptn.business.lsppath;

import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;

public class LspPathController extends AbstractController {

	private LspPathPanel view;

	public LspPathController(LspPathPanel view) {
		this.view = view;
		try {
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 选中一条记录后，查看详细信息
	 */
	@Override
	public void initDetailInfo(){
		try {
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
			DialogBoxUtil.errorDialog(this.view, "操作失败!");
		}
	}
	
	@Override
	public void refresh() throws Exception {
//		List<LspInfo> infos = null;
//		LspInfoService lspinfoservice = null;
//		try {
//			lspinfoservice = (LspInfoService) ConstantUtil.serviceFactory
//					.newService(Services.LSPINFO);
//			infos = lspinfoservice.select();
//			this.view.clear();
//			this.view.initData(infos);
//			this.view.updateUI();
//		} catch (Exception e) {
//			ExceptionManage.dispose(e,this.getClass());
//			DialogBoxUtil.errorDialog(this.view, "操作失败!");
//		} finally {
//			 infos = null;
//			 lspinfoservice = null;
//		}
	}

}
