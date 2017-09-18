package com.nms.ui.ptn.business.dual;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.client.Client;
import com.nms.db.bean.ptn.ARPInfo;
import com.nms.db.bean.ptn.path.eth.DualInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.client.ClientService_MB;
import com.nms.model.ptn.ARPInfoService_MB;
import com.nms.model.ptn.path.eth.DualInfoService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ListingFilter;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.business.dialog.dualpath.AddDualDialog;
import com.nms.ui.ptn.business.dialog.dualpath.UpdateDualDialog;

public class DualBusinessController extends AbstractController{

	private DualBusinessPanel view;
	private Map<Integer,List<DualInfo>> dualInfoMap;
	
	public DualBusinessController(DualBusinessPanel dualBusinessPanel) {
		view = dualBusinessPanel;
		try {
			refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}
	@Override
	public void delete() throws Exception {
		List<DualInfo> infos = null;
		DispatchUtil dualDisPatch = null;
		String result = null;
		try {
			infos = new ArrayList<DualInfo>();
			for (DualInfo dualInfo : this.view.getAllSelect()) {
				if(hasARPInfo(dualInfo)){
					DialogBoxUtil.errorDialog(view, ResourceUtil.srcStr(StringKeysTip.TIP_DELETE_ARP));
					return;
				}
				infos.addAll(this.dualInfoMap.get(dualInfo.getServiceId()));
			}
			dualDisPatch = new DispatchUtil(RmiKeys.RMI_DUAL);
			result = dualDisPatch.excuteDelete(infos);
			//添加日志记录
			for(DualInfo dual : infos){
				AddOperateLog.insertOperLog(null, EOperationLogType.DUALPROTECTDELETE.getValue(), result, 
						null, null, -1, dual.getName(), null);
			}
			DialogBoxUtil.succeedDialog(this.view, result);	
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			infos = null;
		}
	}
	
	/**
	 * 存在arp/不存在 true/false
	 * @param dualInfo
	 * @return
	 */
	private boolean hasARPInfo(DualInfo dualInfo){
		ARPInfoService_MB service = null;
		try {
			if(dualInfo.getPwProtect() != null){
				service = (ARPInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ARP);
				List<ARPInfo> arpList = service.queryBySiteId(dualInfo.getPwProtect().getSiteId());
				if(arpList != null && arpList.size() > 0){
					for (ARPInfo arpInfo : arpList) {
						if(arpInfo.getPwProtectId() == dualInfo.getPwProtect().getBusinessId()){
							return true;
						}
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
		}
		return false;
	}

	@Override
	public void openCreateDialog() throws Exception {
		try {
			AddDualDialog addDualDialog = new AddDualDialog(this.view, true);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	@Override
	public void openUpdateDialog() throws Exception {
		DualInfo info = null;
		DualInfoService_MB dualInfoServiceMB = null;
		try {
			info = this.view.getSelect();
			if (info == null) {
				DialogBoxUtil.errorDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
			} else {
				dualInfoServiceMB = (DualInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.DUALINFO);
				List<DualInfo> dualInfos = dualInfoServiceMB.select(info);
				UpdateDualDialog updateDualDialog = new UpdateDualDialog(dualInfos,this.view);
				UiUtil.showWindow(updateDualDialog, 350, 350);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(dualInfoServiceMB);
		}
	}
	
	@Override
	public void refresh() throws Exception {
		DualInfoService_MB service = null;
		List<DualInfo> infos = null;
		ListingFilter listingFilter = null;
		try {
			listingFilter = new ListingFilter();
			service = (DualInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.DUALINFO);
			dualInfoMap = service.select(0,0);
			infos = new ArrayList<DualInfo>();
			if(null!=dualInfoMap&&dualInfoMap.size()>0){
			for (Map.Entry<Integer, List<DualInfo>> entrySet : dualInfoMap.entrySet()) {
				if (listingFilter.filterByList(entrySet.getValue())) {
					infos.add(entrySet.getValue().get(0));
				}
			}
			}
			this.view.clear();
			if (view.getTopoPanel() != null) {
				view.getTopoPanel().clear();
			}
			if (view.getPortNetworkTablePanel() != null) {
				view.getPortNetworkTablePanel().clear();
			}
			if (view.getClientInfoPanel() != null) {
				view.getClientInfoPanel().clear();
			}
			if (view.getSchematize_panel() != null) {
				view.getSchematize_panel().clear();
			}
			this.view.initData(infos);
			this.view.updateUI();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
		}
	}
	
	/**
	 * 选中一条记录后，查看详细信息
	 */
	@Override
	public void initDetailInfo() {
		try {
			initTopoPanel();
			this.initAcPanel();
			this.initSchematizePanel();
			initClientInfo();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * 列表点击事件（客户信息）
	 */
	private void initClientInfo() {
		DualInfo dualInfo = null;
		ClientService_MB clientServiceMB = null;
		List<Client> clientList = null;
		try {
			clientServiceMB = (ClientService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CLIENTSERVICE);
			dualInfo = view.getSelect();
			if (0 != dualInfo.getClientId()) {
				clientList = clientServiceMB.select(dualInfo.getClientId());
				this.view.getClientInfoPanel().clear();
				this.view.getClientInfoPanel().initData(clientList);
			} else {
				this.view.getClientInfoPanel().clear();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(clientServiceMB);
		}

	}
	
	/**
	 * 初始化图形化界面数据
	 * 
	 * 
	 * @Exception 异常对象
	 */
	private void initSchematizePanel() {
		DualInfo dualInfo = null;
		try {
			dualInfo = view.getSelect();
			this.view.getSchematize_panel().clear();
			this.view.getSchematize_panel().initData(dualInfo);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void initTopoPanel() throws Exception {
		DualInfo dualInfo = null;
		try {
			dualInfo = view.getSelect();
			view.getTopoPanel().clear();
			view.getTopoPanel().initData(dualInfo);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 绑定AC列表数据
	 * 
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private void initAcPanel() {
		DualInfo dualInfo = null;
		List<Integer> acIdList = null;
		DualInfoService_MB dualInfoServiceMB = null;
		List<DualInfo> dualInfoList = null;
		try {
			acIdList = new ArrayList<Integer>();
			dualInfo = view.getSelect();

			// 查询出此dual的所有信息
			dualInfoServiceMB = (DualInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.DUALINFO);
			dualInfoList = dualInfoServiceMB.select(dualInfo);

			for (int i = 0; i < dualInfoList.size(); i++) {
				if (!acIdList.contains(dualInfoList.get(i).getaAcId())) {
					acIdList.add(dualInfoList.get(i).getaAcId());
				}
				if (!acIdList.contains(dualInfoList.get(i).getzAcId())) {
					acIdList.add(dualInfoList.get(i).getzAcId());
				}
			}

			view.getPortNetworkTablePanel().clear();
			view.getPortNetworkTablePanel().initData(acIdList);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			dualInfo = null;
			acIdList = null;
			dualInfoList = null;
			UiUtil.closeService_MB(dualInfoServiceMB);
		}
	}
	
	public void doActive() {
		activeOperate(EActiveStatus.ACTIVITY.getValue(), EOperationLogType.DUALACTIVE.getValue());
	}
	
	public void doUnActive() {
		activeOperate(EActiveStatus.UNACTIVITY.getValue(), EOperationLogType.DUALUNACTIVE.getValue());
	}
	
	private void activeOperate(int active,int operate){
		List<DualInfo> dualInfoList = null;
		DualInfoService_MB dualInfoServiceMB = null;
		List<DualInfo> infos = new ArrayList<DualInfo>();
		DispatchUtil dispatch = null;
		String result = null;
		try {
			dualInfoList = this.view.getAllSelect();
			dualInfoServiceMB = (DualInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.DUALINFO);
			if(dualInfoList != null && !dualInfoList.isEmpty()){
				for (DualInfo dualInfo : dualInfoList) {
					List<DualInfo> dualInfos = dualInfoServiceMB.select(dualInfo);
					for(DualInfo dual : dualInfos){
						dual.setActiveStatus(active);
						infos.add(dual);
					}
				}
			}
			dispatch = new DispatchUtil(RmiKeys.RMI_DUAL);
			result = dispatch.excuteUpdate(infos);
			DialogBoxUtil.succeedDialog(this.view, result);
			//添加日志记录*************************/
			if (infos != null && infos.size() > 0) {
				for (DualInfo info : infos) {
					AddOperateLog.insertOperLog(null, operate, result, null, null, -1, info.getName(), null);
				}
			}
			//************************************/
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(dualInfoServiceMB);
		}
	}
}
