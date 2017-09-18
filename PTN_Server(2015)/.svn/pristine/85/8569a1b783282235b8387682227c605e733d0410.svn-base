package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.equipment.port.E1Info;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.path.ces.CesInfo;
import com.nms.db.bean.ptn.path.eth.DualInfo;
import com.nms.db.bean.ptn.path.eth.ElineInfo;
import com.nms.db.bean.ptn.path.protect.PwProtect;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.pw.PwNniInfo;
import com.nms.db.bean.ptn.path.tunnel.Lsp;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.bean.ptn.port.Acbuffer;
import com.nms.db.bean.ptn.port.PortLagInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EServiceType;
import com.nms.drive.service.PtnServiceEnum;
import com.nms.drive.service.bean.ELineObject;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.bean.TMCTunnelProtectObject;
import com.nms.drive.service.bean.VpwsBuffer;
import com.nms.model.equipment.port.E1InfoService_MB;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.path.ces.CesInfoService_MB;
import com.nms.model.ptn.path.eth.DualInfoService_MB;
import com.nms.model.ptn.path.eth.ElineInfoService_MB;
import com.nms.model.ptn.path.protect.PwProtectService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.ptn.path.tunnel.LspInfoService_MB;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.ptn.port.AcBufferService_MB;
import com.nms.model.ptn.port.AcPortInfoService_MB;
import com.nms.model.ptn.port.PortLagService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.ActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.WHOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.SiteLockTypeUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class DualWHServceImpl extends WHOperationBase implements OperationServiceI{

	private int portId = 0;
	@Override
	public String excutionDelete(List objectList) throws Exception {
		List<DualInfo> dualInfolList = null;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		NEObject neObject = null;
		List<ELineObject> elineObjectList = null;
		List<TMCTunnelProtectObject> pwProtectionList = null;
		try {
			dualInfolList = objectList;
			siteIdList = this.getSiteIds(dualInfolList);
			if (super.isLockBySiteIdList(siteIdList)) {
				return ResourceUtil.srcStr(StringKeysTip.TIP_SITE_LOCK);
			}
			super.lockSite(siteIdList, SiteLockTypeUtil.DUAL_DELETE);//锁住网元
			SiteUtil siteUtil=new SiteUtil();
			WhImplUtil whImplUtil = new WhImplUtil();
			for(Integer siteId:siteIdList){
				if("0".equals(siteUtil.SiteTypeUtil(siteId)+"")){//非失连网元下发设备
					neObject = whImplUtil.siteIdToNeObject(siteId);
					pwProtectionList = getPwProtect(siteId);
					operationObjectAfter = new OperationObject();
					elineObjectList = getElineInfoObject(siteId);
					
					super.sendAction(operationObjectAfter, neObject, elineObjectList,PtnServiceEnum.ELINE);//下发tunnel配置
					operationObjectResult = super.verification(operationObjectAfter);//获取设备返回信息
					if (!(operationObjectResult.isSuccess())) {//失败
						return super.getErrorMessage(operationObjectResult);//设备返回信息有需要也可以扩展为key=siteid，value=返回信息,的map结构
					}
					super.sendAction(operationObjectAfter, neObject, pwProtectionList,PtnServiceEnum.PWPROTECT);//下发PW保护配置
					operationObjectResult = super.verification(operationObjectAfter);//获取设备返回信息
					if (!(operationObjectResult.isSuccess())) {//失败
						return super.getErrorMessage(operationObjectResult);
					}
				}
			}
			super.clearLock(siteIdList);
			return ResultString.CONFIG_SUCCESS;
			
		} catch (Exception e) {
			throw e;
		} finally {
			super.clearLock(siteIdList);
		}

	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		List<DualInfo> dualInfolList = null;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		NEObject neObject = null;
		List<ELineObject> elineObjectList = null;
		List<TMCTunnelProtectObject> pwProtectionList = null;
		try {
			dualInfolList = (List<DualInfo>)object;
			siteIdList = this.getSiteIds(dualInfolList);
			if (super.isLockBySiteIdList(siteIdList)) {
				return ResourceUtil.srcStr(StringKeysTip.TIP_SITE_LOCK);
			}
			super.lockSite(siteIdList, SiteLockTypeUtil.DUAL_DELETE);//锁住网元
			SiteUtil siteUtil=new SiteUtil();
			WhImplUtil whImplUtil = new WhImplUtil();
			for(Integer siteId:siteIdList){
				if("0".equals(siteUtil.SiteTypeUtil(siteId)+"")){//非失连网元下发设备
					neObject = whImplUtil.siteIdToNeObject(siteId);
					pwProtectionList = new ArrayList<TMCTunnelProtectObject>();
					pwProtectionList = getPwProtect(siteId);
					operationObjectAfter = new OperationObject();
					elineObjectList = new ArrayList<ELineObject>();
					elineObjectList = getElineInfoObject(siteId);
					
					super.sendAction(operationObjectAfter, neObject, elineObjectList,PtnServiceEnum.ELINE);//下发业务配置
					operationObjectResult = super.verification(operationObjectAfter);//获取设备返回信息
					if (!(operationObjectResult.isSuccess())) {//失败
						return super.getErrorMessage(operationObjectResult);//设备返回信息有需要也可以扩展为key=siteid，value=返回信息,的map结构
					}
					super.sendAction(operationObjectAfter, neObject, pwProtectionList,PtnServiceEnum.PWPROTECT);//下发PW保护配置
					operationObjectResult = super.verification(operationObjectAfter);//获取设备返回信息
					if (!(operationObjectResult.isSuccess())) {//失败
						return super.getErrorMessage(operationObjectResult);
					}
				}
			}
			super.clearLock(siteIdList);
			return ResultString.CONFIG_SUCCESS;
			
		} catch (Exception e) {
			throw e;
		} finally {
			super.clearLock(siteIdList);
		}

	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		List<DualInfo> dualInfolList = null;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		NEObject neObject = null;
		List<ELineObject> elineObjectList = null;
		List<TMCTunnelProtectObject> pwProtectionList = null;
		try {
			dualInfolList = (List<DualInfo>)object;
			siteIdList = this.getSiteIds(dualInfolList);
			if (super.isLockBySiteIdList(siteIdList)) {
				return ResourceUtil.srcStr(StringKeysTip.TIP_SITE_LOCK);
			}
			super.lockSite(siteIdList, SiteLockTypeUtil.DUAL_DELETE);//锁住网元
			SiteUtil siteUtil=new SiteUtil();
			WhImplUtil whImplUtil = new WhImplUtil();
			for(Integer siteId:siteIdList){
				if("0".equals(siteUtil.SiteTypeUtil(siteId)+"")){//非失连网元下发设备
					neObject = whImplUtil.siteIdToNeObject(siteId);
					pwProtectionList = new ArrayList<TMCTunnelProtectObject>();
					pwProtectionList = getPwProtect(siteId);
					operationObjectAfter = new OperationObject();
					elineObjectList = new ArrayList<ELineObject>();
					elineObjectList = getElineInfoObject(siteId);
					
					super.sendAction(operationObjectAfter, neObject, elineObjectList,PtnServiceEnum.ELINE);//下发业务配置
					operationObjectResult = super.verification(operationObjectAfter);//获取设备返回信息
					if (!(operationObjectResult.isSuccess())) {//失败
						return super.getErrorMessage(operationObjectResult);//设备返回信息有需要也可以扩展为key=siteid，value=返回信息,的map结构
					}
					super.sendAction(operationObjectAfter, neObject, pwProtectionList,PtnServiceEnum.PWPROTECT);//下发PW保护配置
					operationObjectResult = super.verification(operationObjectAfter);//获取设备返回信息
					if (!(operationObjectResult.isSuccess())) {//失败
						return super.getErrorMessage(operationObjectResult);
					}
				}
			}
			super.clearLock(siteIdList);
			return ResultString.CONFIG_SUCCESS;
			
		} catch (Exception e) {
			throw e;
		} finally {
			super.clearLock(siteIdList);
		}

	}

	@Override
	public Object synchro(int siteId) throws Exception 
	{
		OperationObject operObj = null;
		DualInfoService_MB dualInfoService = null;
		ElineWHServiceImpl elineWHServiceImpl = null;
		PwProtectService_MB pwProtetcService = null;
		try 
		{
//			elineWHServiceImpl = new ElineWHServiceImpl();
//			elineWHServiceImpl.synchro(siteId);
			
			pwProtetcService = (PwProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PWPROTECT);
			dualInfoService = (DualInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.DUALINFO);
			operObj = new OperationObject();
			operObj = super.getSynchroOperationObject(siteId, "dualSynchro");
			super.sendAction(operObj);
			super.verification(operObj);
			if(operObj.isSuccess())
			{
				for(ActionObject actionObject : operObj.getActionObjectList())
				{
					this.synchro_db(actionObject.getTmcTunnelProectList(),siteId,dualInfoService,pwProtetcService);
				}
			}
			return ResultString.CONFIG_SUCCESS;
		} catch (Exception e) 
		{
			ExceptionManage.dispose(e, this.getClass());
		}finally
		{
			UiUtil.closeService_MB(pwProtetcService);
			UiUtil.closeService_MB(dualInfoService);
			operObj = null;
			elineWHServiceImpl = null;
		}
		return ResultString.CONFIG_TIMEOUT;
	}

	/********************同步dual业务****************************/
	private void synchro_db(List<TMCTunnelProtectObject> dualProtectlist, int siteId,DualInfoService_MB dualInfoService,PwProtectService_MB pwProtetcService) {
		PwProtect pwProtect = null;
		int mainPwId = 0;
		int standPwId = 0;
		try 
		{
			for(TMCTunnelProtectObject TMCTunnelProtectObject : dualProtectlist)
			{
				pwProtect = new PwProtect();
				pwProtect.setMainTunnelId(getTunnelId(siteId,TMCTunnelProtectObject.getMainLspId()));
				mainPwId = getPwId(siteId,TMCTunnelProtectObject.getMainPwId());
				pwProtect.setMainPwId(mainPwId);
				pwProtect.setStandTunnelId(getTunnelId(siteId,TMCTunnelProtectObject.getStandbyLspId()));
				standPwId = getPwId(siteId,TMCTunnelProtectObject.getStandbyPwId());
				pwProtect.setStandPwId(standPwId);
				pwProtect.setDelayTime(TMCTunnelProtectObject.getProtractedTime());
				pwProtect.setBackType(TMCTunnelProtectObject.getReturnType());
				pwProtect.setBusinessId(TMCTunnelProtectObject.getTmcTunnelId());
				pwProtect.setSiteId(siteId);
				
				List<PwProtect> pwPorttectList = pwProtetcService.select(pwProtect);
				if(pwPorttectList == null || pwPorttectList.isEmpty())
				{
					pwProtect.setServiceId(setDualInfo(dualInfoService,mainPwId,standPwId,siteId));
					pwProtetcService.insert(pwProtect);
				}else
				{
					pwPorttectList.get(0).setDelayTime(TMCTunnelProtectObject.getProtractedTime());
					pwPorttectList.get(0).setBackType(TMCTunnelProtectObject.getReturnType());
					pwProtetcService.update(pwPorttectList.get(0));
				}
			}
		} catch (Exception e) 
		{
			ExceptionManage.dispose(e, getClass());
		}finally
		{
			 pwProtect = null;
		}
	}

	/**
	 *将同一类型的dual绑定为一条业务 
	 * @param dualInfoService
	 * @param mainPwId
	 * @param standPwId
	 * @param siteId
	 */
	private int  setDualInfo(DualInfoService_MB dualInfoService,int mainPwId,int standPwId,int siteId) 
	{
		int serviceId = 0;
		List<DualInfo> dualInfos = null;
		DualInfo mainDual = null;
		DualInfo standDual = null;
		try {
			dualInfos = dualInfoService.selectMainPwAndSiteId(mainPwId,standPwId,siteId);
			if(dualInfos != null && dualInfos.size() >1)
			{
				mainDual = dualInfos.get(0);
				standDual = dualInfos.get(1);
				standDual.setServiceId(mainDual.getServiceId());
				standDual.setName(mainDual.getName());
				dualInfos.clear();
				dualInfos.add(mainDual);
				dualInfos.add(standDual);
				dualInfoService.update(dualInfos);
				serviceId = mainDual.getId();
			}
		} catch (Exception e) 
		{
			ExceptionManage.dispose(e, getClass());
		}finally
		{
			dualInfos = null;
			mainDual = null;
			standDual = null;
		}
		return serviceId;
	}

	/**
	 * 获取所有网元id
	 * @param dualInfos
	 * @return
	 */
	
	private List<Integer> getSiteIds(List<DualInfo> dualInfos){
		List<Integer> siteIds = new ArrayList<Integer>();
		for(DualInfo dualInfo : dualInfos){
			if(dualInfo.getRootSite()>0){
				if(!siteIds.contains(dualInfo.getRootSite())){
					siteIds.add(dualInfo.getRootSite());
				}
			}
			if(dualInfo.getBranchMainSite()>0){
				if(!siteIds.contains(dualInfo.getBranchMainSite())){
					siteIds.add(dualInfo.getBranchMainSite());
				}
			}
			if(dualInfo.getBranchProtectSite()>0){
				if(!siteIds.contains(dualInfo.getBranchProtectSite())){
					siteIds.add(dualInfo.getBranchProtectSite());
				}
			}
		}
		return siteIds;
	}
	
	/**
	 * 根据网元ID查询ELineObject
	 * 
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	private List<ELineObject> getElineInfoObject(int siteId) throws Exception {

		ElineInfoService_MB elineService = null;
		List<ElineInfo> elineInfoList = null;
		ELineObject eLineObject = null;
		ElineInfo eline = null;
		List<ELineObject> eLineObjectList = null;
		PwInfo pw = null;
		List<ElineInfo> activeList = null;
		List<CesInfo> cesInfoList = null;
		CesInfoService_MB cesInfoService = null;
		PortService_MB portService = null;
		PortInst portInst = null;
		DualInfoService_MB dualInfoService = null;
		List<DualInfo> dualInfos = null;
		E1InfoService_MB e1InfoService = null;
		try {
			elineService = (ElineInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Eline);
			cesInfoService = (CesInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			dualInfoService = (DualInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.DUALINFO);
			e1InfoService = (E1InfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.E1Info);
			// 查询所有激活的eline
			eline = new ElineInfo();
			eline.setActiveStatus(EActiveStatus.ACTIVITY.getValue());
			elineInfoList = elineService.selectNodeBySite(siteId);
			eLineObjectList = new ArrayList<ELineObject>();
			activeList = new ArrayList<ElineInfo>();
			for (ElineInfo active : elineInfoList) {// 获取所有激活的eline
				if (active.getActiveStatus() == EActiveStatus.ACTIVITY.getValue())
					activeList.add(active);
			}
			// 查询ces业务
			cesInfoList = cesInfoService.selectNodeBySite(siteId);
			for (CesInfo cesInfo : cesInfoList) {
				if (cesInfo.getActiveStatus() == EActiveStatus.ACTIVITY.getValue()) {
					activeList.add(cesInfoTOElineInfo(cesInfo));
				}
			}
			
			//查询dual保护业务
			dualInfos = dualInfoService.selectBySiteId(siteId);
			for(DualInfo dualInfo : dualInfos){
				if(dualInfo.getActiveStatus() == EActiveStatus.ACTIVITY.getValue()){
					activeList.add(dualInfoToElineInfo(dualInfo));
				}
			}
			
			for (ElineInfo elineInfo : activeList) {
				pw = getPwInfo(elineInfo.getPwId());
				eLineObject = new ELineObject();
				// 匹配A网元
				if (elineInfo.getaSiteId() == siteId) {
					eLineObject.setVpwsId(elineInfo.getaXcId());
					eLineObject.setRole(0);
					eLineObject.setVpwsBufferList(getVpwsBuffer(elineInfo.getaAcId()));// vpwsbuffer赋值
					if (pw.getASiteId() == siteId) {
						eLineObject.setPwIdNNI(pw.getApwServiceId());
						configPwNniInfo(eLineObject, pw.getaPwNniInfo());// NNI端口赋值
					} else {
						eLineObject.setPwIdNNI(pw.getZpwServiceId());
						configPwNniInfo(eLineObject, pw.getzPwNniInfo());// NNI端口赋值
					}
					if (elineInfo.getCestype() != 3) {
						configAcInfo(eLineObject, elineInfo.getaAcId(), siteId);// UNI赋值	
					}
				}
				
				// 匹配Z网元
				if (elineInfo.getzSiteId() == siteId) {
					eLineObject.setRole(1);
					eLineObject.setVpwsId(elineInfo.getzXcId());
					eLineObject.setVpwsBufferList(getVpwsBuffer(elineInfo.getzAcId()));// vpwsbuffer赋值
					if (pw.getASiteId() == siteId) {
						eLineObject.setPwIdNNI(pw.getApwServiceId());
						configPwNniInfo(eLineObject, pw.getaPwNniInfo());// NNI端口赋值
					} else {
						eLineObject.setPwIdNNI(pw.getZpwServiceId());
						configPwNniInfo(eLineObject, pw.getzPwNniInfo());// NNI端口赋值
					}
					if (elineInfo.getCestype() != 3) {
						configAcInfo(eLineObject, elineInfo.getzAcId(), siteId);// UNI赋值
					}
				}
				
				if (elineInfo.getCestype() == 3) {
					E1Info e1Info = new E1Info();
					e1Info.setSiteId(siteId);
					VpwsBuffer vpwsBuffer = new VpwsBuffer();
					if (elineInfo.getaSiteId() == siteId) {
						e1Info.setPortId(elineInfo.getaAcId());
						portInst = portService.selectPortybyid(elineInfo.getaAcId());
					} else {
						e1Info.setPortId(elineInfo.getzAcId());
						portInst = portService.selectPortybyid(elineInfo.getzAcId());
					}
					List<E1Info> e1Infos = e1InfoService.selectByCondition(e1Info);
					if(e1Infos.size()>0){
						vpwsBuffer.setPwLable(e1Infos.get(0).getLegId());
					}
					vpwsBuffer.setVpwsBufferID(1);
					vpwsBuffer.setBufferEnable(1);
					eLineObject.setProtUNI(201);
					eLineObject.setSlotUNI(portInst.getSlotNumber());
					eLineObject.getVpwsBufferList().clear();
					eLineObject.getVpwsBufferList().add(vpwsBuffer);
				} 
				eLineObject.setType(elineInfo.getServiceType());
				eLineObjectList.add(eLineObject);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			elineInfoList = null;
			cesInfoList = null;
			UiUtil.closeService_MB(portService);
			UiUtil.closeService_MB(cesInfoService);
			UiUtil.closeService_MB(elineService);
			UiUtil.closeService_MB(dualInfoService);
			UiUtil.closeService_MB(e1InfoService);
		}
		return eLineObjectList;
	}
	/**
	 * 双归业务向eline业务转换
	 * @param dualInfo
	 * @return
	 */
	private ElineInfo dualInfoToElineInfo(DualInfo dualInfo){
		ElineInfo elineInfo = new ElineInfo();
		elineInfo.setName(dualInfo.getName());
		elineInfo.setActiveStatus(dualInfo.getActiveStatus());
		elineInfo.setPwId(dualInfo.getPwId());
		elineInfo.setIsSingle(dualInfo.getIsSingle());
		elineInfo.setServiceType(EServiceType.DUAL.getValue());
		elineInfo.setCestype(1);
		elineInfo.setCreateTime(DateUtil.getDate(DateUtil.FULLTIME));
		elineInfo.setaSiteId(dualInfo.getRootSite());
		elineInfo.setAportId(dualInfo.getAportId());
		elineInfo.setaAcId(dualInfo.getaAcId());
		elineInfo.setaXcId(dualInfo.getaXcId());
		if(dualInfo.getBranchMainSite()>0){
			elineInfo.setzSiteId(dualInfo.getBranchMainSite());
		}else if(dualInfo.getBranchProtectSite()>0){
			elineInfo.setzSiteId(dualInfo.getBranchProtectSite());
		}
		elineInfo.setZportId(dualInfo.getZportId());
		elineInfo.setzXcId(dualInfo.getzXcId());
		elineInfo.setzAcId(dualInfo.getzAcId());
		return elineInfo;
	}
	
	/**
	 * 对UNI端口进行赋值
	 * 
	 * @throws Exception
	 */
	private void configAcInfo(ELineObject eLineObject, int acId, int siteId) throws Exception {

		AcPortInfoService_MB acInfoService = null;
		AcPortInfo acPortInfo = null;
		List<Integer> idList = null;
		PortInst port = new PortInst();
		try {
			acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);

			idList = new ArrayList<Integer>();
			idList.add(acId);
			acPortInfo = acInfoService.select(idList).get(0);
			if (acPortInfo.getLagId() > 0) {
				lagId(acPortInfo, eLineObject);
				eLineObject.setSlotUNI(20);
			} else {
				port = getportId(acPortInfo.getPortId(), siteId);
				eLineObject.setProtUNI(port.getNumber());
				eLineObject.setSlotUNI(port.getSlotNumber());
			}
			eLineObject.setModelUNI(Integer.parseInt(UiUtil.getCodeById(acPortInfo.getModel()).getCodeValue()));
			eLineObject.setCirUNI(acPortInfo.getSimpleQos().getCir()/1000);
			eLineObject.setPirUNI(acPortInfo.getSimpleQos().getPir()/1000);
			eLineObject.setCm(acPortInfo.getSimpleQos().getColorSence());
			eLineObject.setCbs(acPortInfo.getSimpleQos().getCbs());
			eLineObject.setPbs(acPortInfo.getSimpleQos().getPbs());
			eLineObject.settVCTrafficPolicing(Integer.parseInt(UiUtil.getCodeById(acPortInfo.getManagerEnable()).getCodeValue()));
			eLineObject.setDownTagUNI(Integer.parseInt(UiUtil.getCodeById(acPortInfo.getExitRule()).getCodeValue()));
			eLineObject.setUpTagUNI(Integer.parseInt(UiUtil.getCodeById(acPortInfo.getTagAction()).getCodeValue()));
			eLineObject.setVlanIdUNI(Integer.parseInt(acPortInfo.getVlanId()));
			eLineObject.setVlanPriUNI(Integer.parseInt(acPortInfo.getVlanpri()));
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(acInfoService);
		}
	}

	private void lagId(AcPortInfo acPortInfo, ELineObject eLineObject) throws Exception {
		PortLagService_MB portLagService = null;
		PortLagInfo portLagInfo = null;
		try {
			portLagService = (PortLagService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTLAG);
			portLagInfo = new PortLagInfo();
			portLagInfo.setId(acPortInfo.getLagId());
			portLagInfo = portLagService.selectLAGByCondition(portLagInfo).get(0);
			eLineObject.setProtUNI(100+portLagInfo.getLagID());
			portLagInfo.setStatusActive(0);
			portLagService.saveOrUpdate(portLagInfo);

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(portLagService);
		}

	}

	private PortInst getportId(int portId, int siteId) {
		PortInst port = null;
		PortService_MB portService = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			port = new PortInst();
			port.setPortId(portId);
			port.setSiteId(siteId);
			List<PortInst> insts = portService.select(port);
			if (insts != null && insts.size() > 0) {
				port = portService.select(port).get(0);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(portService);
		}
		return port;
	}

	/**
	 * 对NNI端口进行赋值
	 * 
	 * @throws Exception
	 */
	private void configPwNniInfo(ELineObject eLineObject, PwNniInfo pwNniInfo) throws Exception {

		eLineObject.setDownTagNNI(Integer.parseInt(UiUtil.getCodeById(pwNniInfo.getExitRule()).getCodeValue()));
		eLineObject.setVlanIdNNI(Integer.parseInt(pwNniInfo.getSvlan()));
		eLineObject.setVlanPriNNI(Integer.parseInt(pwNniInfo.getVlanpri()));
		eLineObject.setUpTagNNI(Integer.parseInt(UiUtil.getCodeById(pwNniInfo.getTagAction()).getCodeValue()));
		eLineObject.setControlEnabl(Integer.parseInt(UiUtil.getCodeById(pwNniInfo.getControlEnable()).getCodeValue()));
	}

	/**
	 * 通过PWID和网元ID查询PW
	 * 
	 * @throws Exception
	 */
	private PwInfo getPwInfo(int pwid) throws Exception {

		PwInfo pwInfo = null;
		PwInfoService_MB pwInfoService = null;
		try {
			pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			pwInfo = new PwInfo();
			pwInfo.setPwId(pwid);
			pwInfo = pwInfoService.selectBypwid_notjoin(pwInfo);
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(pwInfoService);
		}
		return pwInfo;
	}

	/**
	 * 通过acid对vpwsbuffer进行赋值
	 * 
	 * @throws Exception
	 */
	private List<VpwsBuffer> getVpwsBuffer(int acid) throws Exception {

		List<VpwsBuffer> vpwsBufferList = new ArrayList<VpwsBuffer>();
		List<Acbuffer> acbufferList = null;
		AcBufferService_MB uniBufferService = null;

		try {

			uniBufferService = (AcBufferService_MB) ConstantUtil.serviceFactory.newService_MB(Services.UniBuffer);
			acbufferList = uniBufferService.select(acid);
			int i = 0;
			for (Acbuffer acbuffer : acbufferList) {
				i++;
				VpwsBuffer vpwsBuffer = new VpwsBuffer();
				vpwsBuffer.setVpwsBufferID(i);
				vpwsBuffer.set_802_1P(acbuffer.getEightIp());
				vpwsBuffer.setBufferEnable(1);
				vpwsBuffer.setCbs(acbuffer.getCbs());
				vpwsBuffer.setCir(acbuffer.getCir()/1000);
				vpwsBuffer.setCm(acbuffer.getCm());
				vpwsBuffer.setIpDscp(acbuffer.getIpDscp());
				vpwsBuffer.setModel(acbuffer.getModel());
				vpwsBuffer.setPbs(acbuffer.getPbs());
				vpwsBuffer.setPhb(Integer.parseInt(UiUtil.getCodeById(acbuffer.getPhb()).getCodeValue()));
				vpwsBuffer.setPir(acbuffer.getPir()/1000);
				vpwsBuffer.setPwLable(0);
				vpwsBuffer.setSourceIP(acbuffer.getSourceIp());
				vpwsBuffer.setSourceMac(acbuffer.getSourceMac());
				vpwsBuffer.setTargetIP(acbuffer.getTargetIp());
				vpwsBuffer.setTargetMac(acbuffer.getTargetMac());
				vpwsBuffer.setVlanId(acbuffer.getVlanId());
				vpwsBuffer.setStrategy(acbuffer.getStrategy());
				vpwsBuffer.setSourceTcpPortId(acbuffer.getSourceTcpPortId());
				vpwsBuffer.setEndTcpPortId(acbuffer.getEndTcpPortId());
				vpwsBuffer.setIPTOS(acbuffer.getIPTOS());
				vpwsBuffer.setPortType(acbuffer.getPortType());
				vpwsBufferList.add(vpwsBuffer);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(uniBufferService);
		}
		return vpwsBufferList;
	}

	/**
	 * 武汉（ces业务向eline业务转换）
	 * 
	 * @param cesInfo
	 * @return
	 */
	private ElineInfo cesInfoTOElineInfo(CesInfo cesInfo) {
		ElineInfo elineInfo = new ElineInfo();
		elineInfo.setName(cesInfo.getName());
		elineInfo.setActiveStatus(cesInfo.getActiveStatus());
		elineInfo.setPwId(cesInfo.getPwId());
		elineInfo.setIsSingle(cesInfo.getIsSingle());
		elineInfo.setServiceType(EServiceType.CES.getValue());
		elineInfo.setCestype(3);
		elineInfo.setCreateTime(DateUtil.getDate(DateUtil.FULLTIME));
		//TODO user
//		elineInfo.setCreateUser(ConstantUtil.user.getUser_Name());
		elineInfo.setaSiteId(cesInfo.getaSiteId());
		elineInfo.setAportId(cesInfo.getAportId());
		elineInfo.setaXcId(cesInfo.getAxcId());
		elineInfo.setzSiteId(cesInfo.getzSiteId());
		elineInfo.setZportId(cesInfo.getZportId());
		elineInfo.setzXcId(cesInfo.getZxcId());
		elineInfo.setaAcId(cesInfo.getaAcId());
		elineInfo.setzAcId(cesInfo.getzAcId());
		return elineInfo;
	}
	
	/**
	 * 封装pw保护
	 * @param siteId
	 * @return
	 */
	private List<TMCTunnelProtectObject> getPwProtect(int siteId){
		List<TMCTunnelProtectObject> tmcTunnelProtectObjects = null;
		PwProtectService_MB pwProtetcService = null;
		List<PwProtect> pwProtects = null;
		PwProtect pwProtect = null;
		TMCTunnelProtectObject tmcTunnelProtectObject = null;
		try {
			pwProtetcService = (PwProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PWPROTECT);
			tmcTunnelProtectObjects = new ArrayList<TMCTunnelProtectObject>();
			pwProtect = new PwProtect();
			pwProtect.setSiteId(siteId);
			pwProtects = pwProtetcService.select(pwProtect);
			for(PwProtect protect : pwProtects){
				tmcTunnelProtectObject = new TMCTunnelProtectObject();
				tmcTunnelProtectObject.setTmcTunnelId(protect.getBusinessId());
				tmcTunnelProtectObject.setProtectionType(4);
				tmcTunnelProtectObject.setProtractedTime(protect.getDelayTime());
				tmcTunnelProtectObject.setReturnType(protect.getBackType());
				tmcTunnelProtectObject.setMainSlot(1);
				tmcTunnelProtectObject.setMainLspId(getTunnelBusinessId(siteId, protect.getMainTunnelId()));
				tmcTunnelProtectObject.setMainPort(getPortNumber(siteId, portId));
				tmcTunnelProtectObject.setMainPwId(getPwBusinessId(siteId, protect.getMainPwId()));
				tmcTunnelProtectObject.setStandbySlot(1);
				tmcTunnelProtectObject.setStandbyLspId(getTunnelBusinessId(siteId, protect.getStandTunnelId()));
				tmcTunnelProtectObject.setStnadbyPort(getPortNumber(siteId, portId));
				tmcTunnelProtectObject.setStandbyPwId(getPwBusinessId(siteId, protect.getStandPwId()));
				tmcTunnelProtectObjects.add(tmcTunnelProtectObject);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(pwProtetcService);
		}
		return tmcTunnelProtectObjects;
	}
	
	/**
	 * 查询tunnel业务id
	 * @param siteId
	 * @param tunnelId
	 * @return
	 */
	private int getTunnelBusinessId(int siteId,int tunnelId){
		int tunnelBusinessId = 0;
		Tunnel tunnel = null;
		TunnelService_MB tunnelService = null;
		try {
			tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			tunnel = tunnelService.selectByTunnelIdAndSiteId(siteId, tunnelId);
			if(tunnel != null){
				for(Lsp lsp : tunnel.getLspParticularList()){
					if(lsp.getASiteId() == siteId){
						tunnelBusinessId = lsp.getAtunnelbusinessid();
						portId = lsp.getAPortId();
						break;
					}else if(lsp.getZSiteId() == siteId){
						tunnelBusinessId = lsp.getZtunnelbusinessid();
						portId = lsp.getZPortId();
						break;
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(tunnelService);
		}
		return tunnelBusinessId;
	}
	
	/**
	 * 通过网元和业务ID来获取相应的数据
	 * @param siteId
	 * @param tunnelBusssId
	 * @return
	 * @throws Exception
	 */
	private int getTunnelId(int siteId,int tunnelBusssId)throws Exception
	{
		LspInfoService_MB lspinfoservice = null;
		Lsp lsp = null;
		try {
			lspinfoservice = (LspInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.LSPINFO);	
			lsp = lspinfoservice.select(siteId, tunnelBusssId, null);
			if(lsp == null)
			{
				ExceptionManage.infor(ResultString.SY_DUAL_TUNNEL_FAILED, this.getClass());
			}
			else{
				return lsp.getTunnelId();
			}
		} catch (Exception e) 
		{
			ExceptionManage.dispose(e, getClass());
		}finally
		{
			UiUtil.closeService_MB(lspinfoservice);
			lsp = null;
		}
		return 0;
		
	}
	/**
	 * 查询pw业务id
	 * @param siteId
	 * @param pwId
	 * @return
	 */
	private int getPwBusinessId(int siteId,int pwId){
		int pwBusinessId = 0;
		PwInfo pwInfo = null;
		PwInfoService_MB pwInfoService = null;
		try {
			pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			pwInfo = new PwInfo();
			pwInfo.setPwId(pwId);
			pwInfo = pwInfoService.selectBypwid_notjoin(pwInfo);
			if(pwInfo != null){
				if(pwInfo.getASiteId() == siteId){
					pwBusinessId = pwInfo.getApwServiceId();
				}else if(pwInfo.getZSiteId() == siteId){
					pwBusinessId = pwInfo.getZpwServiceId();
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(pwInfoService);
		}
		return pwBusinessId;
	}
	
	
	/**
	 * 通过网元和业务ID来获取相应的数据
	 * @param siteId
	 * @param tunnelBusssId
	 * @return
	 * @throws Exception
	 */
	private int getPwId(int siteId,int pwBusssId)throws Exception
	{
		PwInfoService_MB pwInfoService = null;
		PwInfo pwInfo = null;
		try {
			pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			
			pwInfo = pwInfoService.select(siteId, pwBusssId);
			if(pwInfo == null)
			{
				ExceptionManage.infor(ResultString.SY_DUAL_PW_FAILED, this.getClass());
			}
			else{
				return pwInfo.getPwId();
			}
		} catch (Exception e) 
		{
			ExceptionManage.dispose(e, getClass());
		}finally
		{
			UiUtil.closeService_MB(pwInfoService);
			pwInfo = null;
		}
		return 0;
		
	}
	
	/**
	 * 查询端口号
	 * @param siteId
	 * @param portId
	 * @return
	 */
	private int getPortNumber(int siteId,int portId){
		int portNumber = 0;
		PortInst portInst = null;
		PortService_MB portService = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			portInst = new PortInst();
			portInst.setSiteId(siteId);
			portInst.setPortId(portId);
			portInst = portService.select(portInst).get(0);
			portNumber = portInst.getNumber();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(portService);
		}
		return portNumber;
	}
}
