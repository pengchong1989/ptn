package com.nms.ui.ptn.ne.ecn.ecninterfaces.ospf.controller;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.ecn.OSPFInterface;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.ptn.ecn.OSPFInterfaceService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.ptn.ne.ecn.ecninterfaces.ospf.view.OSFPInterfacePortPanel;
import com.nms.ui.ptn.ne.ecn.ecninterfaces.ospf.view.SaveOSPFInterfacesDialog;
/**
 * OSPF控制类
 * @author Administrator
 *
 */
public class OSPFInterfacesPortController extends AbstractController {

	private OSFPInterfacePortPanel panel;

	/**
	 * 设置CONTROLLER
	 * @param osfpInterfacePortPanel
	 */
	public OSPFInterfacesPortController(OSFPInterfacePortPanel osfpInterfacePortPanel) {
		this.panel = osfpInterfacePortPanel;
	}

	/**
	 * 刷新
	 */
	@Override
	public void refresh() throws Exception {
		OSPFInterfaceService_MB ospfInterfaceService = null;
		List<OSPFInterface> ospfInterfaceList = null;
		try {
			ospfInterfaceList = new ArrayList<OSPFInterface>();
			ospfInterfaceService = (OSPFInterfaceService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OSPFINTERFACE);
			ospfInterfaceList = ospfInterfaceService.queryByNeID(ConstantUtil.siteId);
			this.panel.clear();
			this.panel.initData(ospfInterfaceList);
			this.panel.updateUI();
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(ospfInterfaceService);
			ospfInterfaceList = null;
		}
	}

	/**
	 * 新建
	 */
	@Override
	public void openCreateDialog() throws Exception {
		SaveOSPFInterfacesDialog updatesdhdialog = new SaveOSPFInterfacesDialog(null, panel);
	}

	/**
	 * 修改
	 */
	@Override
	public void openUpdateDialog() throws Exception {
		OSPFInterface ospfInterface = this.panel.getSelect();
		SaveOSPFInterfacesDialog updatesdhdialog = new SaveOSPFInterfacesDialog(ospfInterface, panel);
	}

	/**
	 * 删除
	 */
	@Override
	public void delete() throws Exception {

		DispatchUtil ospfInterfaceDispatch = new DispatchUtil(RmiKeys.RMI_OSPFINTERFACE);
		String message = "";
		try {
			List<OSPFInterface> ospfInterface = this.panel.getAllSelect();
			message = ospfInterfaceDispatch.excuteDelete(ospfInterface);
			DialogBoxUtil.succeedDialog(this.panel, message);
			refresh();
		} catch (Exception e) {
			DialogBoxUtil.errorDialog(this.panel, e.getMessage());
			throw e;
		} finally {
			ospfInterfaceDispatch = null;

		}
	}

	/**
	 * 同步
	 */
	@Override
	public void synchro() {
		DispatchUtil ospfInterfacesDispatch =null;
		try {
			ospfInterfacesDispatch =  new DispatchUtil(RmiKeys.RMI_OSPFINTERFACE);
			ospfInterfacesDispatch.synchro(ConstantUtil.siteId);
			//添加日志记录
			PtnButton deleteButton=(PtnButton) this.panel.getSynchroButton();
			deleteButton.setOperateKey(EOperationLogType.OSPFSYNCHRO.getValue());
			deleteButton.setResult(1);
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			ospfInterfacesDispatch = null;
		}
	}

}
