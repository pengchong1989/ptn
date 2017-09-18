package com.nms.ui.ptn.ne.ces.controller;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.path.ServiceInfo;
import com.nms.db.bean.ptn.path.ces.CesInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.pw.PwNniInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.ptn.path.ces.CesInfoService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.filter.impl.CesNeFilterDialog;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ListingFilter;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.ne.camporeData.CamporeDataDialog;
import com.nms.ui.ptn.ne.ces.view.CesEditDialog;
import com.nms.ui.ptn.ne.ces.view.CesPanel;

public class CesController extends AbstractController {

	private CesPanel cesPanel;
	private CesInfo cesInfo=null;
	public CesController(CesPanel cesPanel) {
		this.setCesPanel(cesPanel);
	}

	@Override
	public void refresh() throws Exception {

		CesInfoService_MB cesInfoServiceMB = null;
		List<CesInfo> cesInfos1 = null;
		List<CesInfo> cesInfos2 = null;
		List<CesInfo> cesInfos = null;
		ListingFilter filter = null;
		try {
			cesInfos=new ArrayList<CesInfo>();
			cesInfoServiceMB = (CesInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);
			cesInfos1 = cesInfoServiceMB.selectNodeBySite(ConstantUtil.siteId);
			filter = new ListingFilter();
			
			if(null==this.cesInfo){
				this.cesInfo=new CesInfo();
			}
			cesInfos2 = (List<CesInfo>) filter.filterList(cesInfoServiceMB.filterSingle(this.cesInfo,ConstantUtil.siteId));
			for(CesInfo ces1:cesInfos1)
			{
				for(CesInfo ces2:cesInfos2)
				{
					if(ces1.getId() == ces2.getId())
					{
						cesInfos.add(ces1);
					}
				}
			}
			this.getCesPanel().clear();
			this.getCesPanel().getCesInfoPanel().clear();
			this.getCesPanel().initData(cesInfos);
			this.getCesPanel().updateUI();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(cesInfoServiceMB);
			cesInfos = null;
		}
	}

	@Override
	public void delete() throws Exception {

		List<CesInfo> cesInfoList = null;
		DispatchUtil cesDispatch = null;
		String resultStr = null;
		try {
			cesInfoList = this.getCesPanel().getAllSelect();
			cesDispatch = new DispatchUtil(RmiKeys.RMI_CES);
			resultStr = cesDispatch.excuteDelete(cesInfoList);
			DialogBoxUtil.succeedDialog(this.getCesPanel(), resultStr);
			//添加日志记录
			for (CesInfo cesInfo : cesInfoList) {
				AddOperateLog.insertOperLog(null, EOperationLogType.CESDELETE.getValue(), resultStr, 
						null, null, ConstantUtil.siteId, cesInfo.getName(), null);
			}
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			resultStr = null;
			cesDispatch = null;
			cesInfoList = null;
		}

	}
	
	@Override
	public boolean deleteChecking() {
		boolean flag = false;
		List<CesInfo> cesInfoList = null;
		List<Integer> siteIds = null;
		try {
			cesInfoList = this.getCesPanel().getAllSelect();

			for (CesInfo cesInfo : cesInfoList) {
				if (cesInfo.getIsSingle() == 0) {
					flag = true;
					break;
				}
			}
			if (flag) {
				DialogBoxUtil.errorDialog(this.getCesPanel(), ResourceUtil.srcStr(StringKeysTip.TIP_DELETE_NODE));
				return false;
			}else{
				SiteUtil siteUtil = new SiteUtil();
				if(1==siteUtil.SiteTypeOnlineUtil(ConstantUtil.siteId)){
					WhImplUtil wu = new WhImplUtil();
					siteIds = new ArrayList<Integer>();
					siteIds.add(ConstantUtil.siteId);
					String str=wu.getNeNames(siteIds);
					DialogBoxUtil.errorDialog(this.getCesPanel(), ResourceUtil.srcStr(StringKeysTip.TIP_NOT_DELETEONLINE)+""+str+ResourceUtil.srcStr(StringKeysTip.TIP_ONLINENOT_DELETEONLINE));
					return false;  		    		
					}
			}
			flag = true;
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			siteIds = null;
			cesInfoList = null;
		}
		return flag;
	}

	/**
	 * 选中一条记录后，查看详细信息
	 */
	@Override
	public void initDetailInfo() {
		try {
			initInfoData();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 查询详细信息
	 * 
	 * @throws Exception
	 */
	private void initInfoData() throws Exception {
		CesInfo cesInfo = null;
		try {
			cesInfo = this.getCesPanel().getSelect();

			this.getCesPanel().getCesInfoPanel().initData(cesInfo);
			this.getCesPanel().getCesInfoPanel().updateUI();

		} catch (Exception e) {
			throw e;
		} finally {
			cesInfo = null;
		}
	}

	@Override
	public void synchro() {
		DispatchUtil cesDispatch = null;
		try {
			cesDispatch = new DispatchUtil(RmiKeys.RMI_CES);
			String result = cesDispatch.synchro(ConstantUtil.siteId);
			DialogBoxUtil.succeedDialog(null, result);
			//添加日志记录
			AddOperateLog.insertOperLog(null, EOperationLogType.CESSYNCHRO.getValue(), result, 
					null, null, ConstantUtil.siteId, ResourceUtil.srcStr(StringKeysTab.TAB_CESINFO), null);
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			cesDispatch = null;
		}
	}

	/**
	 * 激活处理事件
	 */
	public void doActive() {
		String result = null;
		DispatchUtil dispatch = null;
		List<CesInfo> cesInfoList = null;
		try {
			dispatch = new DispatchUtil(RmiKeys.RMI_CES);
			cesInfoList = this.cesPanel.getAllSelect();
			int failCount = 0;
			if (cesInfoList != null && cesInfoList.size() > 0) {
				for (CesInfo cesInfo : cesInfoList) {
					cesInfo.setActiveStatus(EActiveStatus.ACTIVITY.getValue());
					result = dispatch.excuteUpdate(cesInfo);
					if(result == null || !result.contains(ResultString.CONFIG_SUCCESS)){
						failCount++;
					}
					//添加日志记录*************************/
					AddOperateLog.insertOperLog(null, EOperationLogType.CESSINGACTIVE.getValue(), result, null, null, ConstantUtil.siteId, cesInfo.getName(), null);
					//************************************/
				}
				result = ResourceUtil.srcStr(StringKeysTip.TIP_BATCH_CREATE_RESULT);
				result = result.replace("{C}", (cesInfoList.size()-failCount) + "");
				result = result.replace("{S}", failCount + "");
			}
			String str = this.getOfflineSiteIdNames(cesInfoList);
			if(!str.equals("")){
				result += ","+str+ResultString.NOT_ONLINE_SUCCESS;
			}
			DialogBoxUtil.succeedDialog(this.cesPanel, result);
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			result = null;
			dispatch = null;
		}
	}
	
	private String getOfflineSiteIdNames(List<CesInfo> cesList) throws Exception {
		List<Integer> siteIds = null;
		String str = "";
		try {
			siteIds = new ArrayList<Integer>();
			for (CesInfo ces : cesList) {
				siteIds.add(ces.getaSiteId());
				siteIds.add(ces.getzSiteId());
			}
			str = new WhImplUtil().getNeNames(siteIds);
		} catch (Exception e) {
			throw e;
		}
		return str;
	}


	/**
	 * 去激活处理事件
	 */
	public void doUnActive() {
		String result = null;
		DispatchUtil dispatch = null;
		List<CesInfo> cesInfoList = null;
		try {
			dispatch = new DispatchUtil(RmiKeys.RMI_CES);
			cesInfoList = this.cesPanel.getAllSelect();
			int failCount = 0;
			if (cesInfoList != null && cesInfoList.size() > 0) {
				for (CesInfo cesInfo : cesInfoList) {
					cesInfo.setActiveStatus(EActiveStatus.UNACTIVITY.getValue());
					result = dispatch.excuteUpdate(cesInfo);
					if(result == null || !result.contains(ResultString.CONFIG_SUCCESS)){
						failCount++;
					}
					//添加日志记录*************************/
					AddOperateLog.insertOperLog(null, EOperationLogType.CESSINGNOACTIVE.getValue(), result, null, null, ConstantUtil.siteId, cesInfo.getName(), null);
					//************************************/
				}
				result = ResourceUtil.srcStr(StringKeysTip.TIP_BATCH_CREATE_RESULT);
				result = result.replace("{C}", (cesInfoList.size()-failCount) + "");
				result = result.replace("{S}", failCount + "");
			}
			String str = this.getOfflineSiteIdNames(cesInfoList);
			if(!str.equals("")){
				result += ","+str+ResultString.NOT_ONLINE_SUCCESS;
			}
			DialogBoxUtil.succeedDialog(this.cesPanel, result);
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			result = null;
			dispatch = null;
		}
	}
	
	@Override
	public void openCreateDialog() throws Exception {
		new CesEditDialog(null, this.getCesPanel());
	}

	@Override
	public void openUpdateDialog() throws Exception {
		CesInfo cesInfo = this.cesPanel.getSelect();
		if(cesInfo.getIsSingle() != 0)
		{
			new CesEditDialog(cesInfo, this.getCesPanel());
		}
		else
		{
			DialogBoxUtil.errorDialog(this.getCesPanel(), ResourceUtil.srcStr(StringKeysTip.TIP_UPDATE_NODE));
			return;
		}
	}
	
	@Override
	public void openFilterDialog() throws Exception {
		if(null==this.cesInfo){
			this.cesInfo=new CesInfo();
		}
		new CesNeFilterDialog(this.cesInfo);
		this.refresh();
	}
	
	// 清除过滤
	public void clearFilter() throws Exception {
		this.cesInfo=null;
		this.refresh();
	}
	
	public CesPanel getCesPanel() {
		return cesPanel;
	}

	public void setCesPanel(CesPanel cesPanel) {
		this.cesPanel = cesPanel;
	}
	
	@SuppressWarnings("unchecked")
	public void consistence(){
		CesInfoService_MB cesServiceMB = null;
		PwInfoService_MB pwServiceMB = null;
		try {
			SiteUtil siteUtil=new SiteUtil();
			if (0 == siteUtil.SiteTypeUtil(ConstantUtil.siteId)) {
				DispatchUtil cesDispatch = new DispatchUtil(RmiKeys.RMI_CES);
				List<ServiceInfo> neList = new ArrayList<ServiceInfo>();
				try {
					neList = (List<ServiceInfo>) cesDispatch.consistence(ConstantUtil.siteId);
				} catch (Exception e) {
					ExceptionManage.dispose(e, this.getClass());
				}
				cesServiceMB = (CesInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);
				pwServiceMB = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
				List<CesInfo> emsList = new ArrayList<CesInfo>();
				try {
					emsList = cesServiceMB.selectNodeBySite(ConstantUtil.siteId);
				} catch (Exception e) {
					ExceptionManage.dispose(e, this.getClass());
				}
				for (CesInfo cesInfo : emsList) {
					cesInfo.getPwNniList().add(this.getPwNniInfo(ConstantUtil.siteId, cesInfo, pwServiceMB));
				}
				this.filterCesList(neList);
				if(emsList.size() > 0 && neList.size() > 0){
					CamporeDataDialog dialog = new CamporeDataDialog(ResourceUtil.srcStr(StringKeysTip.TIP_CES),
							emsList, neList, this);
					UiUtil.showWindow(dialog, 700, 600);
				}else{
					DialogBoxUtil.errorDialog(this.cesPanel, ResourceUtil.srcStr(StringKeysTip.TIP_DATAISNULL));
				}
			}else{
				DialogBoxUtil.errorDialog(this.cesPanel, ResultString.QUERY_FAILED);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(cesServiceMB);
			UiUtil.closeService_MB(pwServiceMB);
		}
	}
	
	/**
	 * 过滤出eline业务
	 */
	private void filterCesList(List<ServiceInfo> neList) {
		List<CesInfo> cesList = new ArrayList<CesInfo>();
		for (ServiceInfo cesInfo : neList) {
			if(cesInfo.getServiceType() == 0){
				cesList.add((CesInfo)cesInfo);
			}
		}
		neList.clear();
		neList.addAll(cesList);
	}

	private PwNniInfo getPwNniInfo(int siteId, CesInfo cesInfo, PwInfoService_MB pwServiceMB) throws Exception {
		PwInfo pw = new PwInfo();
		pw.setPwId(cesInfo.getPwId());
		pw = pwServiceMB.selectBypwid_notjoin(pw);
		if(pw != null){
			if(pw.getASiteId() == siteId){
				return pw.getaPwNniInfo();
			}else if(pw.getZSiteId() == siteId){
				return pw.getzPwNniInfo();
			}
		}
		return null;
	}
}
