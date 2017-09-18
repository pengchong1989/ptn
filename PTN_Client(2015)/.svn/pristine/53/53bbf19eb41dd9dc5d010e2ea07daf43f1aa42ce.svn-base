package com.nms.ui.ptn.ne.ecn.ecninterfaces.mcn.controller;

import java.util.List;

import com.nms.db.bean.ptn.ecn.MCN;
import com.nms.db.enums.EOperationLogType;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.ptn.ne.ecn.ecninterfaces.mcn.view.McnPanel;
import com.nms.ui.ptn.ne.ecn.ecninterfaces.mcn.view.UpdateMcnDialog;
/**
 * MCNController
 * @author Administrator
 *
 */
public class MCNController extends AbstractController {

	private McnPanel panel;

	public MCNController(McnPanel mcnPanel) {
		this.panel = mcnPanel;
	}

	/**
	 * 刷新
	 */
	@Override
	public void refresh() throws Exception {
		 
//		MCNCXServiceImpl mcnServiceImpl = null;
		List<MCN> mcnList = null;
		MCN mcn = new MCN();
		try {
//			mcnServiceImpl = new MCNCXServiceImpl();
			mcn.setNeId(ConstantUtil.siteId + "");
//			mcnList = mcnServiceImpl.excutionQuery(mcn);

			this.panel.clear();
			this.panel.initData(mcnList);
			this.panel.updateUI();

		} catch (Exception e) {
			throw e;
		} finally {
			mcnList = null;
			mcn = null;
		}
	}

	/**
	 * 修改
	 */
	@Override
	public void openUpdateDialog() throws Exception {
		MCN mcn = this.panel.getSelect();
		UpdateMcnDialog updatedialog = new UpdateMcnDialog(mcn, panel);
	}
	
	/**
	 * 同步
	 */
	@Override
	public void synchro() {
		DispatchUtil mcnDispatch = null;
		try {
			mcnDispatch = new DispatchUtil(RmiKeys.RMI_MCN);
			mcnDispatch.synchro(ConstantUtil.siteId);
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			mcnDispatch = null;
		}
	}
	
}
