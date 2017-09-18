package com.nms.ui.ptn.ne.ac.controller;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.bean.ptn.port.Acbuffer;
import com.nms.db.bean.ptn.qos.QosInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.port.AcPortInfoService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.ne.ac.view.AcPanel;
import com.nms.ui.ptn.ne.ac.view.AddACDialog;

public class AcPanelController extends AbstractController {

	private AcPanel view;

	public AcPanelController(AcPanel view) {
		this.view = view;
	}

	@Override
	public void openCreateDialog() throws Exception {
		SiteService_MB siteService = null;
		try {
			AddACDialog dialog = new AddACDialog(view,0);
			AcHandlerController addacController = new AcHandlerController(this, dialog, ConstantUtil.siteId);
			addacController.initData(true,0);
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			if (siteService.getManufacturer(ConstantUtil.siteId) == EManufacturer.WUHAN.getValue()) {
				dialog.getStep1().setVisible(true);
			} else {
				dialog.getStep1_cx().setVisible(true);
			}
			
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(siteService);
		}
	}

	@Override
	public void openUpdateDialog() throws Exception {
		this.updateAcPortWindow();
	}

	private void updateAcPortWindow() {
		AcPortInfo acportInfo = view.getSelect();
		SiteService_MB siteService = null;
		AcPortInfoService_MB acInfoService = null;
		try {
			if (acportInfo != null) {
				acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
				acportInfo = acInfoService.selectById(acportInfo.getId());
				AddACDialog dialog = new AddACDialog(view, acportInfo, acportInfo.getBufferList());
				AcHandlerController addacController = new AcHandlerController(this, dialog, ConstantUtil.siteId);
				addacController.initData(false,0);
				siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
				if (siteService.getManufacturer(ConstantUtil.siteId) == EManufacturer.WUHAN.getValue()) {
					addacController.refresh();
					dialog.getStep1().setVisible(true);
				} else {
					addacController.refresh_cx();
					dialog.getStep1_cx().setVisible(true);
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(acInfoService);
			UiUtil.closeService_MB(siteService);
		}
	}

	@Override
	public void delete() throws Exception {
		DispatchUtil acDispatch = null;
		List<AcPortInfo> acPortInfoList = null;
		try {
			acDispatch = new DispatchUtil(RmiKeys.RMI_AC);
			acPortInfoList = view.getAllSelect();
			String message = acDispatch.excuteDelete(acPortInfoList);
			DialogBoxUtil.succeedDialog(this.view, message);
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			acDispatch = null;
			acPortInfoList = null;
		}
	}

	@Override
	public boolean deleteChecking() {
		List<AcPortInfo> acPortInfoList = null;
		boolean flag = false;
		AcPortInfoService_MB acInfoService = null;
		try {
			acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			acPortInfoList = view.getAllSelect();
			for (AcPortInfo acInfo : acPortInfoList) {
				AcPortInfo info = acInfoService.selectById(acInfo.getId());
				if(info != null && info.getIsUser() == 1){
					DialogBoxUtil.errorDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_ACISUSED));
//					flag = false;
//					break;
					return false;
				}
			}
			flag = true;
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(acInfoService);
		}
		return flag;
	}

	public void clearTabpanel() {
		this.view.getTagReconfigTF().setText("");
		this.view.getTagActionTF().setText("");
		this.view.getAddVlanIdTF().setText("");
		this.view.getAddVlanPriTF().setText("");
		this.view.getSimpleqosTable().clear();
		this.view.getDetailqosTable().clear();
	}

	@Override
	public void refresh() throws Exception {
		AcPortInfoService_MB acService = null;
		List<AcPortInfo> acPortInfoList = null;
		AcPortInfo acPortInfo = null;
		try {
			acService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			acPortInfo = new AcPortInfo();
			acPortInfo.setSiteId(ConstantUtil.siteId);
			acPortInfoList = acService.selectBySiteIdOnly(acPortInfo);
			acService.getAcBuffer(acPortInfoList);
//			for (AcPortInfo acInfo : acPortInfoList) {
//				acInfo.setBufferList(bufService.select(acInfo.getId()));
//				acInfo.setSimpleQos(qosInfoService.getQosByObj(EServiceType.ACPORT.toString(), acInfo.getId()).get(0));
//			}
			view.clear();
			view.initData(acPortInfoList);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(acService);
		}
		clearTabpanel();
	}

	/**
	 * 选中一条记录后，初始化tabber页的详细信息
	 */
	@Override
	public void initDetailInfo() {
		AcPortInfo acPortInfo = null;

		try {
			acPortInfo = view.getSelect();
			initBasicInfo(acPortInfo);
			initBufferInfo(acPortInfo);

			view.updateUI();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			acPortInfo = null;
		}
	}

	private void initBufferInfo(AcPortInfo acPortInfo) {
		List<QosInfo> qosInfoList = new ArrayList<QosInfo>();
		List<Acbuffer> buffList = new ArrayList<Acbuffer>();
		AcPortInfoService_MB acPortInfoServiceMB = null;
		try {
			acPortInfoServiceMB = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			List<AcPortInfo> acPortInfos = new ArrayList<AcPortInfo>();
			acPortInfos.add(acPortInfo);
			acPortInfoServiceMB.getAcBuffer(acPortInfos);
			acPortInfoServiceMB.getAcQos(acPortInfos);
		} catch (Exception e) {
			ExceptionManage.dispose(e, AcHandlerController.class);
		}finally{
			UiUtil.closeService_MB(acPortInfoServiceMB);
		}
		
		qosInfoList.add(acPortInfo.getSimpleQos());
		buffList.addAll(acPortInfo.getBufferList());
		
//		if(CodeConfigItem.getInstance().getWuhan() == 1){
//			for (QosInfo qosInfo : qosInfoList) {
//				qosInfo.setCir(qosInfo.getCir()*1000);
//				qosInfo.setEir(qosInfo.getEir()*1000);
//				qosInfo.setPir(qosInfo.getPir()*1000);
//			}
//		}
		view.getSimpleqosTable().initData(qosInfoList);
		//显示完毕要还原
//		if(CodeConfigItem.getInstance().getWuhan() == 1){
//			for (QosInfo qosInfo : qosInfoList) {
//				qosInfo.setCir(qosInfo.getCir()/1000);
//				qosInfo.setEir(qosInfo.getEir()/1000);
//				qosInfo.setPir(qosInfo.getPir()/1000);
//			}
//		}
		
//		if(CodeConfigItem.getInstance().getWuhan() == 1){
//			for (Acbuffer ac : buffList) {
//				ac.setCir(ac.getCir()*1000);
//				ac.setEir(ac.getEir()*1000);
//				ac.setPir(ac.getPir()*1000);
//			}
//		}
		view.getDetailqosTable().initData(buffList);
		//显示完毕要还原
//		if(CodeConfigItem.getInstance().getWuhan() == 1){
//			for (Acbuffer ac : buffList) {
//				ac.setCir(ac.getCir()/1000);
//				ac.setEir(ac.getEir()/1000);
//				ac.setPir(ac.getPir()/1000);
//			}
//		}
	}

	private void initBasicInfo(AcPortInfo acPortInfo) {
		// view.getTagReconfigTF().setText(acPortInfo.getTagRecognition() == 0 ? ResourceUtil.srcStr(StringKeysObj.AC_DISTINGUISH_NOT) : ResourceUtil.srcStr(StringKeysObj.AC_DISTINGUISH));
		// view.getTagActionTF().setText(acPortInfo.getTagBehavior() == 0 ? "NOP" : acPortInfo.getTagBehavior() == 1 ? ResourceUtil.srcStr(StringKeysObj.AC_ADD) : ResourceUtil.srcStr(StringKeysObj.AC_DELETE));
		// view.getAddVlanIdTF().setText("" + acPortInfo.getTagValnId());
		// view.getAddVlanPriTF().setText("" + acPortInfo.getTagValnPri());
	}

	public AcPanel getView() {
		return view;
	}

	@Override
	public void synchro() {
		DispatchUtil acDispatch = null;
		try {
			
			acDispatch = new DispatchUtil(RmiKeys.RMI_AC);
			String result = acDispatch.synchro(ConstantUtil.siteId);
			DialogBoxUtil.succeedDialog(null, result);
			//添加日志记录
			PtnButton synchroButton=(PtnButton) this.view.getSynchroButton();
			synchroButton.setOperateKey(EOperationLogType.ACSELECT.getValue());
			synchroButton.setResult(1);

			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			acDispatch = null;
		}
	}

}
