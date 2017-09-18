package com.nms.ui.ptn.clock.controller;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.clock.LineClockInterface;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.ptn.clock.TimeLineClockInterfaceService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.ptn.clock.view.cx.clockinterface.TabPanelTwoTICX;
import com.nms.ui.ptn.clock.view.cx.dialog.CIUpdateDialog;

public class LineCLPanelControllerCX extends AbstractController {

	private TabPanelTwoTICX tabPanelTwoTICX = null;

	public LineCLPanelControllerCX(TabPanelTwoTICX tabPanelTwoTICX) {
		this.tabPanelTwoTICX = tabPanelTwoTICX;
	}

	/**
	 * <p>
	 * 数据修改
	 * </p>
	 */
	@Override
	public void openUpdateDialog() throws Exception {

		LineClockInterface lineClockInterface = null;
		try {
			lineClockInterface = new LineClockInterface();
			lineClockInterface = tabPanelTwoTICX.getSelect();
		    new CIUpdateDialog(lineClockInterface,tabPanelTwoTICX);
		} catch (Exception e) {

			throw e;
		}
	}

	/**
	 * <p>
	 * 加载页面数据
	 * </p>
	 */
	@Override
	public void refresh() throws Exception {
		List<LineClockInterface> list = null;
		TimeLineClockInterfaceService_MB timeLineClockInterfaceService=null;
		try {
			list = new ArrayList<LineClockInterface>();
			timeLineClockInterfaceService=(TimeLineClockInterfaceService_MB)ConstantUtil.serviceFactory.newService_MB(Services.TimeLineClockInterfaceService);
			list=timeLineClockInterfaceService.select(ConstantUtil.siteId);
			
			this.tabPanelTwoTICX.clear();
			this.tabPanelTwoTICX.initData(list);
			this.tabPanelTwoTICX.updateUI();
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(timeLineClockInterfaceService);
		}
	}

	/**
	 * <p>
	 * 同步功能
	 * </p>
	 */
	@Override
	public void synchro() throws Exception {
		DispatchUtil lineClockInterfaceDispatch;
		try {
			lineClockInterfaceDispatch = new DispatchUtil(RmiKeys.RMI_LINECLOCKINTERFACE);
			lineClockInterfaceDispatch.synchro(ConstantUtil.siteId);
			//添加日志记录
			PtnButton deleteButton=(PtnButton) this.tabPanelTwoTICX.getSynchroButton();
			deleteButton.setOperateKey(EOperationLogType.LINECLSYNCHRO.getValue());
			deleteButton.setResult(1);
			this.refresh();
		} catch (Exception e) {

			throw e;
		}
	}

}
