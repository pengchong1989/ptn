package com.nms.ui.ptn.ne.etree.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.nms.db.bean.ptn.path.StaticUnicastInfo;
import com.nms.db.bean.ptn.path.eth.EtreeInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.pw.PwNniInfo;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.ptn.path.SingleSpreadService_MB;
import com.nms.model.ptn.path.eth.EtreeInfoService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.ptn.port.AcPortInfoService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.filter.impl.EthNeFilterDialog;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysOperaType;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.ne.camporeData.CamporeDataDialog;
import com.nms.ui.ptn.ne.etree.view.EtreeEditDialog;
import com.nms.ui.ptn.ne.etree.view.EtreePanel;

public class EtreeController extends AbstractController {

	private EtreePanel etreePanel;
	private Map<Integer, List<EtreeInfo>> etreeMap = null;
	private EtreeInfo etreeInfo=null;
	// private static final Logger logger = Logger.getLogger(EtreeController.class);

	public EtreeController(EtreePanel etreePanel) {
		this.setEtreePanel(etreePanel);
	}

	@Override
	public void refresh() throws Exception {
		EtreeInfoService_MB etreeService = null;
		List<EtreeInfo> infos = null;
		List<EtreeInfo> eTreeinfos = null;
		try {
			if(null == this.etreeInfo){
				this.etreeInfo=new EtreeInfo();
			}
			infos = new ArrayList<EtreeInfo>();
			etreeService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
			this.etreeInfo.setaSiteId(ConstantUtil.siteId);
			etreeMap = etreeService.filterSelect(this.etreeInfo);
			infos = new ArrayList<EtreeInfo>();
			if (null != etreeMap && etreeMap.size() > 0) {
				Iterator iter = etreeMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					infos.addAll((Collection<? extends EtreeInfo>) entry.getValue());
				}
			}

			for (EtreeInfo etreeInfo_ne : infos) {
				if (etreeInfo_ne.getRootSite() == ConstantUtil.siteId) {
					etreeInfo_ne.putClientProperty("type", ResourceUtil.srcStr(StringKeysObj.ROOT));
				} else {
					etreeInfo_ne.putClientProperty("type", ResourceUtil.srcStr(StringKeysObj.LEAF));
				}
			}

			eTreeinfos = removeRepeatedTree(infos);
			this.getEtreePanel().clear();
			this.getEtreePanel().getPwElinePanel().clear();
			this.getEtreePanel().getAcElinePanel().clear();
			this.getEtreePanel().initData(eTreeinfos);
			this.getEtreePanel().updateUI();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			infos = null;
			UiUtil.closeService_MB(etreeService);
		}
	}
	
	private List<EtreeInfo> removeRepeatedTree(List<EtreeInfo> EtreeList) {
		List<EtreeInfo> NorepeatedEtree = EtreeList;
		for (int i = 0; i < NorepeatedEtree.size() - 1; i++) {
			for (int j = NorepeatedEtree.size() - 1; j > i; j--) {
				if (NorepeatedEtree.get(j).getServiceId() == NorepeatedEtree.get(i).getServiceId()) {
					NorepeatedEtree.remove(j);
				}
			}
		}

		return NorepeatedEtree;
	}

	/**
	 * 选中一条记录后，查看详细信息
	 * 
	 * @throws Exception
	 */
	@Override
	public void initDetailInfo() {
		EtreeInfo etreeInfo = null;
		try {
			etreeInfo = this.getEtreePanel().getSelect();

			this.initPwData(etreeInfo);
			this.initAcData(etreeInfo);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}

	/**
	 * 初始化pw数据
	 * 
	 * @param etreeInfo
	 * @throws Exception
	 */
	private void initPwData(EtreeInfo etreeInfo) throws Exception {
		PwInfoService_MB pwInfoService = null;
		PwInfo pwinfo = null;
		List<PwInfo> pwinfoList = null;
		EtreeInfoService_MB etreeService = null;
		List<EtreeInfo> etreeInfoList = null;
		try {
			pwinfoList = new ArrayList<PwInfo>();
			// 如果此条数据是根 就查询所有pw 否则只查询此叶子的pw
			if (etreeInfo.getRootSite() == ConstantUtil.siteId) {
				etreeService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
				etreeInfoList = etreeService.select(etreeInfo);
			} else {
				etreeInfoList = new ArrayList<EtreeInfo>();
				etreeInfoList.add(etreeInfo);
			}

			pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			for (int i = 0; i < etreeInfoList.size(); i++) {
				pwinfo = new PwInfo();
				pwinfo.setPwId(etreeInfoList.get(i).getPwId());
				pwinfo = pwInfoService.selectBypwid_notjoin(pwinfo);
				pwinfoList.add(pwinfo);
			}

			this.getEtreePanel().getPwElinePanel().initData(pwinfoList);
			this.getEtreePanel().updateUI();
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(etreeService);
			UiUtil.closeService_MB(pwInfoService);
			pwinfo = null;
			pwinfoList = null;
			etreeInfoList = null;
		}
	}

	/**
	 * 初始化ac数据
	 * 
	 * @param etreeInfo
	 * @throws Exception
	 */
	private void initAcData(EtreeInfo etreeInfo) throws Exception {

		AcPortInfoService_MB acInfoService = null;
		List<AcPortInfo> acportInfoList = null;
		Set<Integer> acIdSet = null;
		UiUtil uiUtil = null;
		List<Integer> acIds = null;
		try {
			acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			acIdSet = new HashSet<Integer>();
			uiUtil = new UiUtil();
			acIds = new ArrayList<Integer>();
			acportInfoList = new ArrayList<AcPortInfo>();
			if (etreeInfo.getRootSite() == ConstantUtil.siteId) {
				acIdSet.addAll(uiUtil.getAcIdSets(etreeInfo.getAmostAcId()));
			} else {
				acIdSet.addAll(uiUtil.getAcIdSets(etreeInfo.getZmostAcId()));
			}
			if(acIdSet.size() >0)
			{
				acIds.addAll(acIdSet);
				acportInfoList = acInfoService.select(acIds);
			}
			this.getEtreePanel().getAcElinePanel().initData(acportInfoList);
			this.getEtreePanel().updateUI();
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(acInfoService);
			acportInfoList = null;
			acIdSet = null;
			uiUtil = null;
			acIds = null;
		}

	}

	@Override
	public void delete() throws Exception {

		List<EtreeInfo> etreeInfoList = null;
		DispatchUtil etreeDispatch = null;
		String resultStr = null;
		List<EtreeInfo> etreeInfoList_delete = null;
		try {
			etreeInfoList = this.getEtreePanel().getAllSelect();

			etreeInfoList_delete = new ArrayList<EtreeInfo>();
			for (EtreeInfo etreeInfo : etreeInfoList) {
				etreeInfoList_delete.addAll(this.etreeMap.get(etreeInfo.getServiceId()));
			}

			etreeDispatch = new DispatchUtil(RmiKeys.RMI_ETREE);
			resultStr = etreeDispatch.excuteDelete(etreeInfoList_delete);
			DialogBoxUtil.succeedDialog(this.getEtreePanel(), resultStr);
			// 添加日志记录
			for (EtreeInfo etreeInfo : etreeInfoList) {
				AddOperateLog.insertOperLog(null, EOperationLogType.ETREEDELETE.getValue(), resultStr,
						null, null, ConstantUtil.siteId, etreeInfo.getName(), null);
			}
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			resultStr = null;
			etreeInfoList = null;
			etreeDispatch = null;
		}

	}

	@Override
	public boolean deleteChecking() {
		List<EtreeInfo> etreeInfoList = null;
		boolean flag = false;
		List<Integer> siteIds = null;
			
		try {
			etreeInfoList = this.getEtreePanel().getAllSelect();

			for (EtreeInfo etreeInfo : etreeInfoList) {
				if (etreeInfo.getIsSingle() == 0) {
					flag = true;
					break;
				}
			}
			if (flag) {
				DialogBoxUtil.errorDialog(this.getEtreePanel(), ResourceUtil.srcStr(StringKeysTip.TIP_DELETE_NODE));
				return false;
			}else{
				SiteUtil siteUtil = new SiteUtil();
				if(1==siteUtil.SiteTypeOnlineUtil(ConstantUtil.siteId)){
					WhImplUtil wu = new WhImplUtil();
					siteIds = new ArrayList<Integer>();
					siteIds.add(ConstantUtil.siteId);
					String str=wu.getNeNames(siteIds);
					DialogBoxUtil.errorDialog(this.getEtreePanel(), ResourceUtil.srcStr(StringKeysTip.TIP_NOT_DELETEONLINE)+""+str+ResourceUtil.srcStr(StringKeysTip.TIP_ONLINENOT_DELETEONLINE));
					return false;  		    		
				}else{
					SingleSpreadService_MB uniService = null;
					StaticUnicastInfo staticUni =null;
					StaticUnicastInfo staticUni1 =null;
					List<StaticUnicastInfo> staticUniList = null;
					List<StaticUnicastInfo> staticUniInfo = null;
					try {
					     uniService = (SingleSpreadService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SINGELSPREAD);					   
					     staticUniList = new ArrayList<StaticUnicastInfo>();
							for(int i=0;i<etreeInfoList.size();i++){
								staticUni = new StaticUnicastInfo();
								staticUni1 = new StaticUnicastInfo();
								if(etreeInfoList.get(i).getaSiteId()>0){
								   staticUni.setSiteId(etreeInfoList.get(i).getaSiteId());
								   staticUni.setVplsVs(etreeInfoList.get(i).getaXcId());
								}
								if(etreeInfoList.get(i).getzSiteId()>0){
								  staticUni1.setSiteId(etreeInfoList.get(i).getzSiteId());
								  staticUni1.setVplsVs(etreeInfoList.get(i).getzXcId());
								}
								staticUniList.add(staticUni);
								staticUniList.add(staticUni1);
							}
							for(int i=0;i<staticUniList.size();i++){
							    for(int j=staticUniList.size()-1;j>i;j--){
							    	if(staticUniList.get(j).getSiteId() == staticUniList.get(i).getSiteId() && 
							    			staticUniList.get(j).getVplsVs() == staticUniList.get(i).getVplsVs()){
							    		staticUniList.remove(j);
							    	}
							    }				    	
							}	
							int count=0;
							for(int i=0;i<staticUniList.size();i++){
								staticUniInfo = uniService.selectByStaticUniInfo(staticUniList.get(i));
							     if(staticUniInfo.size()>0 && staticUniInfo !=null){				   
								    count++;
							     }
							}
							if(count!=0){
							   DialogBoxUtil.succeedDialog(this.getEtreePanel(), ResourceUtil.srcStr(StringKeysTip.TIP_DELETE_NOT));
							   return false;
							}
					     
					} catch (Exception e) {
						ExceptionManage.dispose(e, this.getClass());
					} finally {
						UiUtil.closeService_MB(uniService);
						staticUni=null;
						staticUni1=null;
						staticUniList=null;
						staticUniInfo=null;
					}
					
				}
			}
			flag = true;
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			siteIds = null;
			etreeInfoList=null;
		}
		return flag;
	}

	@Override
	public void synchro() {
		DispatchUtil etreeDispatch = null;
		try {
			etreeDispatch = new DispatchUtil(RmiKeys.RMI_ETREE);
			String result = etreeDispatch.synchro(ConstantUtil.siteId);
			DialogBoxUtil.succeedDialog(null, result);
			// 添加日志记录
			AddOperateLog.insertOperLog(null, EOperationLogType.ETREESYNCHRO.getValue(), result,
					null, null, ConstantUtil.siteId, ResourceUtil.srcStr(StringKeysOperaType.BTN_ETREE_SYNCHRO), null);
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			etreeDispatch = null;
		}
	}

	/**
	 * 激活处理事件
	 */
	public void doActive() {
		List<EtreeInfo> infos = null;
		String result = null;
		DispatchUtil dispatch = null;
		List<EtreeInfo> etreeInfos = null;
		EtreeInfoService_MB etreeService = null;
		List<EtreeInfo> etreeInfos2 = null;
		try {
			infos = this.etreePanel.getAllSelect();
			if (infos != null && infos.size() > 0) {
				etreeInfos = new ArrayList<EtreeInfo>();
				etreeService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
				etreeInfos2 = new ArrayList<EtreeInfo>();
				for (EtreeInfo info : infos) {
					etreeInfos = etreeService.selectByServiceId(info.getServiceId());
					for (EtreeInfo etreeInfo : etreeInfos) {
						etreeInfo.setActiveStatus(EActiveStatus.ACTIVITY.getValue());
						etreeInfos2.add(etreeInfo);
					}
				}
			}
			dispatch = new DispatchUtil(RmiKeys.RMI_ETREE);
			result = dispatch.excuteUpdate(etreeInfos2);
			DialogBoxUtil.succeedDialog(this.etreePanel, result);
			//添加日志记录*************************/
			if (infos != null && infos.size() > 0) {
				for (EtreeInfo info : infos) {
					AddOperateLog.insertOperLog(null, EOperationLogType.ETREESINGACTIVE.getValue(), result, null, null, ConstantUtil.siteId, info.getName(), null);
				}
			}
			//************************************/
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			infos = null;
			dispatch = null;
			etreeInfos = null;
			UiUtil.closeService_MB(etreeService);
			etreeInfos2 = null;
		}
	}

	/**
	 * 去激活处理事件
	 */
	public void doUnActive() {
		List<EtreeInfo> infos = null;
		String result = null;
		DispatchUtil dispatch = null;
		List<EtreeInfo> etreeInfos = null;
		EtreeInfoService_MB etreeService = null;
		List<EtreeInfo> etreeInfos2 = null;
		try {
			infos = this.etreePanel.getAllSelect();
			if (infos != null && infos.size() > 0) {
				etreeInfos = new ArrayList<EtreeInfo>();
				etreeService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
				etreeInfos2 = new ArrayList<EtreeInfo>();
				for (EtreeInfo info : infos) {
					etreeInfos = etreeService.selectByServiceId(info.getServiceId());
					for (EtreeInfo etreeInfo : etreeInfos) {
						etreeInfo.setActiveStatus(EActiveStatus.UNACTIVITY.getValue());
						etreeInfos2.add(etreeInfo);
					}
				}
			}
			dispatch = new DispatchUtil(RmiKeys.RMI_ETREE);
			result = dispatch.excuteUpdate(etreeInfos2);
			DialogBoxUtil.succeedDialog(this.etreePanel, result);
			//添加日志记录*************************/
			if (infos != null && infos.size() > 0) {
				for (EtreeInfo info : infos) {
					AddOperateLog.insertOperLog(null, EOperationLogType.ETREESINGNOACTIVE.getValue(), result, null, null, ConstantUtil.siteId, info.getName(), null);
				}
			}
			//************************************/
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			infos = null;
			dispatch = null;
			etreeInfos = null;
			UiUtil.closeService_MB(etreeService);
			etreeInfos2 = null;
		}
	}

	@Override
	public void openCreateDialog() throws Exception {
		new EtreeEditDialog(null, this.getEtreePanel());
	}

	// 修改
	@Override
	public void openUpdateDialog() throws Exception {
		//只能修改单网元
		if(this.etreePanel.getSelect().getIsSingle() == 0){
			DialogBoxUtil.succeedDialog(this.getEtreePanel(), ResourceUtil.srcStr(StringKeysTip.TIP_UPDATE_NODE));
			return;
		}
		SingleSpreadService_MB uniService = null;
		StaticUnicastInfo staticUni =null;
		List<StaticUnicastInfo> staticUniList = null;
		try {
		     uniService = (SingleSpreadService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SINGELSPREAD);
		     staticUni = new StaticUnicastInfo();
		     if(this.etreePanel.getSelect().getaSiteId() !=0){
			    staticUni.setSiteId(this.etreePanel.getSelect().getaSiteId());
			    staticUni.setVplsVs(this.etreePanel.getSelect().getaXcId());
		     }else{
			    staticUni.setSiteId(this.etreePanel.getSelect().getzSiteId());
			    staticUni.setVplsVs(this.etreePanel.getSelect().getzXcId());
		     }		     
		     staticUniList = uniService.selectByStaticUniInfo(staticUni);
		     if(staticUniList.size()>0 && staticUniList !=null){
			    DialogBoxUtil.succeedDialog(this.getEtreePanel(), ResourceUtil.srcStr(StringKeysTip.TIP_UPDATE_NOT));
			    return;
		     }else{
		        new EtreeEditDialog(this.etreePanel.getSelect(), this.getEtreePanel());
		     }
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(uniService);
			staticUni=null;
			staticUniList=null;
		}
	}
	
	@Override
	public void openFilterDialog() throws Exception {
		if(null==this.etreeInfo){
			this.etreeInfo=new EtreeInfo();
		}
		new EthNeFilterDialog(this.etreeInfo);
		this.refresh();
	}
	
	// 清除过滤
	public void clearFilter() throws Exception {
		this.etreeInfo=null;
		this.refresh();
	}
	
	public EtreePanel getEtreePanel() {
		return etreePanel;
	}

	public void setEtreePanel(EtreePanel etreePanel) {
		this.etreePanel = etreePanel;
	}
	
	@SuppressWarnings("unchecked")
	public void consistence(){
		EtreeInfoService_MB etreeService = null;
		try {
			SiteUtil siteUtil=new SiteUtil();
			if (0 == siteUtil.SiteTypeUtil(ConstantUtil.siteId)) {
				DispatchUtil etreeDispatch = new DispatchUtil(RmiKeys.RMI_ETREE);
				List<EtreeInfo> neList = new ArrayList<EtreeInfo>();
				try {
					neList = (List<EtreeInfo>)etreeDispatch.consistence(ConstantUtil.siteId);
				} catch (Exception e) {
					ExceptionManage.dispose(e, this.getClass());
				}
				etreeService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
				Map<String, List<EtreeInfo>> emsMap = etreeService.selectNodeBySite(ConstantUtil.siteId);
				List<EtreeInfo> emsList = this.getEmsList(emsMap);
				if(emsList.size() > 0 && neList.size() > 0){
					CamporeDataDialog dialog = new CamporeDataDialog(ResourceUtil.srcStr(StringKeysTip.TIP_ETREE),emsList, neList, this);
					UiUtil.showWindow(dialog, 700, 600);
				}else{
					DialogBoxUtil.errorDialog(this.etreePanel, ResourceUtil.srcStr(StringKeysTip.TIP_DATAISNULL));
				}
			}else{
				DialogBoxUtil.errorDialog(this.etreePanel, ResultString.QUERY_FAILED);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(etreeService);
		}
	}

	private List<EtreeInfo> getEmsList(Map<String, List<EtreeInfo>> emsMap) {
		AcPortInfoService_MB acService = null;
		PwInfoService_MB pwService = null;
		List<EtreeInfo> etreeList = new ArrayList<EtreeInfo>();
		try {
			acService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			pwService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			for (String id_Type : emsMap.keySet()) {
				List<EtreeInfo> etreeInfoList = emsMap.get(id_Type);
				EtreeInfo etree = etreeInfoList.get(0);
				etree.getAcPortList().addAll(this.getAcInfo(ConstantUtil.siteId, etreeInfoList.get(0), acService));
				for (EtreeInfo etreeInfo : etreeInfoList) {
					etree.getPwNniList().add(this.getPwNniInfo(ConstantUtil.siteId, etreeInfo, pwService));
				}
				etreeList.add(etree);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(acService);
			UiUtil.closeService_MB(pwService);
		}
		return etreeList;
	}

	private List<AcPortInfo> getAcInfo(int siteId, EtreeInfo etreeInfo, AcPortInfoService_MB acService) throws Exception {
		UiUtil uiutil = null;
		Set<Integer> acIds = null;
		List<Integer> acIdList = null;
		try {
			acIds = new HashSet<Integer>();
			uiutil = new UiUtil();
			if(etreeInfo.getRootSite() == siteId){
				acIds.addAll(uiutil.getAcIdSets(etreeInfo.getAmostAcId()));
			}else{
				acIds.addAll(uiutil.getAcIdSets(etreeInfo.getZmostAcId()));
			}
			if(acIds.size() > 0)
			{
				acIdList = new ArrayList<Integer>(acIds);
				return  acService.select(acIdList);
			}
		} catch (Exception e)
		{
			ExceptionManage.dispose(e, getClass());
		}finally
		{
			 uiutil = null;
			 acIds = null;
			 acIdList = null;
		}
		return null;
	}

	private PwNniInfo getPwNniInfo(int siteId, EtreeInfo etreeInfo, PwInfoService_MB pwService) throws Exception {
		PwInfo pw = new PwInfo();
		pw.setPwId(etreeInfo.getPwId());
		pw = pwService.selectBypwid_notjoin(pw);
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
