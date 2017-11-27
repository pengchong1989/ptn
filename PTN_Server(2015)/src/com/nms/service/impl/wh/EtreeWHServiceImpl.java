package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.path.eth.ElanInfo;
import com.nms.db.bean.ptn.path.eth.EtreeInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.pw.PwNniInfo;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.bean.ptn.port.Acbuffer;
import com.nms.db.bean.ptn.port.PortLagInfo;
import com.nms.db.bean.ptn.qos.QosInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.EServiceType;
import com.nms.drive.service.bean.ETreeNNIObject;
import com.nms.drive.service.bean.ETreeObject;
import com.nms.drive.service.bean.ETreeUNIObject;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.bean.VpwsBuffer;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.path.eth.ElanInfoService_MB;
import com.nms.model.ptn.path.eth.EtreeInfoService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.ptn.path.pw.PwNniInfoService_MB;
import com.nms.model.ptn.port.AcBufferService_MB;
import com.nms.model.ptn.port.AcPortInfoService_MB;
import com.nms.model.ptn.port.PortLagService_MB;
import com.nms.model.ptn.qos.QosInfoService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.ActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.WHOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.SynchroUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.SiteLockTypeUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class EtreeWHServiceImpl extends WHOperationBase implements OperationServiceI {
	@SuppressWarnings("unchecked")
	@Override
	public String excutionDelete(List objectList) throws Exception {
		List<EtreeInfo> etreeInfoList = null;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		List<ETreeObject> eTreeObjectList = null;
		NEObject neObject = null;
		EtreeInfoService_MB etreeService = null;
		ElanInfoService_MB elanInfoService = null;
		PwNniInfoService_MB pwNNIService = null;
		try {
			operationObjectAfter = new OperationObject();
			etreeInfoList = objectList;
			siteIdList = this.getSiteIds(etreeInfoList);
			if (siteIdList.size() > 0) {
				etreeService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
				elanInfoService = (ElanInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ElanInfo);
				pwNNIService = (PwNniInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwNniBuffer);
				super.lockSite(siteIdList, SiteLockTypeUtil.ETREE_DELETE);
				SiteUtil siteUtil=new SiteUtil();
				WhImplUtil whImplUtil = new WhImplUtil();
				for(Integer siteId : siteIdList){
					if("0".equals(siteUtil.SiteTypeUtil(siteId)+"")){////非失连网元、非虚拟网元下发设备
						neObject = whImplUtil.siteIdToNeObject(siteId);
						eTreeObjectList = this.getEtreeInfoObject(etreeService,elanInfoService,pwNNIService,siteId);//获取下发etree对象
						ActionObject actionObj = this.getActionObject(eTreeObjectList, neObject);
						operationObjectAfter.getActionObjectList().add(actionObj);
					}
				}
				super.sendAction(operationObjectAfter);
				operationObjectResult = super.verification(operationObjectAfter);//设备返回信息
				if (!(operationObjectResult.isSuccess())) {//有一次失败，直接返回配置失败
					super.clearLock(siteIdList);
					return super.getErrorMessage(operationObjectResult);
				}
			}
			return ResultString.CONFIG_SUCCESS;
		} catch (Exception e) {
			throw e;
		} finally {
			super.clearLock(siteIdList);
			UiUtil.closeService_MB(elanInfoService);
			UiUtil.closeService_MB(etreeService);
			UiUtil.closeService_MB(pwNNIService);
		}

	}

	private ActionObject getActionObject(List<ETreeObject> eTreeObjectList, NEObject neObject) {
		ActionObject actionObject = null;
		try {
			actionObject = new ActionObject();
			List<ActionObject> actionIdList = new ArrayList<ActionObject>();
			actionIdList.add(actionObject);
			actionObject.setActionId(super.getActionId(actionIdList));
			actionObject.setNeObject(neObject);
			actionObject.setType("etree");
			actionObject.setEtreeObjectList(eTreeObjectList);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return actionObject;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String excutionInsert(Object object) throws Exception {
		List<EtreeInfo> etreeInfoList = null;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		List<ETreeObject> eTreeObjectList = null;
		NEObject neObject = null;
		EtreeInfoService_MB etreeService = null;
		ElanInfoService_MB elanInfoService = null;
		PwNniInfoService_MB pwNNIService = null;
		try {
			operationObjectAfter = new OperationObject();
			etreeInfoList = (List<EtreeInfo>)object;
			siteIdList = this.getSiteIds(etreeInfoList);
			if (siteIdList.size() > 0) {
				if (super.isLockBySiteIdList(siteIdList)) {
					return ResourceUtil.srcStr(StringKeysTip.TIP_SITE_LOCK);
				}
				etreeService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
				elanInfoService = (ElanInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ElanInfo);
				pwNNIService = (PwNniInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwNniBuffer);
				super.lockSite(siteIdList, SiteLockTypeUtil.ETREE_DELETE);
				SiteUtil siteUtil=new SiteUtil();
				WhImplUtil whImplUtil = new WhImplUtil();
				for(Integer siteId : siteIdList){
					if("0".equals(siteUtil.SiteTypeUtil(siteId)+"")){////非失连网元、非虚拟网元下发设备
						neObject = whImplUtil.siteIdToNeObject(siteId);
						eTreeObjectList = this.getEtreeInfoObject(etreeService,elanInfoService,pwNNIService,siteId);//获取下发etree对象
						ActionObject actionObj = this.getActionObject(eTreeObjectList, neObject);
						operationObjectAfter.getActionObjectList().add(actionObj);
					}
				}
				super.sendAction(operationObjectAfter);
				operationObjectResult = super.verification(operationObjectAfter);//设备返回信息
				if (!(operationObjectResult.isSuccess())) {//有一次失败，直接返回配置失败
					super.clearLock(siteIdList);
					return super.getErrorMessage(operationObjectResult);
				}
			}
			return ResultString.CONFIG_SUCCESS;
		} catch (Exception e) {
			throw e;
		} finally {
			super.clearLock(siteIdList);
			UiUtil.closeService_MB(elanInfoService);
			UiUtil.closeService_MB(etreeService);
			UiUtil.closeService_MB(pwNNIService);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public String excutionUpdate(Object object) throws Exception {
		List<EtreeInfo> etreeInfoList = null;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		List<ETreeObject> eTreeObjectList = null;
		NEObject neObject = null;
		EtreeInfoService_MB etreeService = null;
		ElanInfoService_MB elanInfoService = null;
		PwNniInfoService_MB pwNNIService = null;
		try {
			etreeInfoList = (List<EtreeInfo>)object;
			siteIdList = this.getSiteIds(etreeInfoList);
			operationObjectAfter = new OperationObject();
			if (siteIdList.size() > 0) {
				if (super.isLockBySiteIdList(siteIdList)) {
					return ResourceUtil.srcStr(StringKeysTip.TIP_SITE_LOCK);
				}
				super.lockSite(siteIdList, SiteLockTypeUtil.ETREE_DELETE);
				SiteUtil siteUtil=new SiteUtil();
				WhImplUtil whImplUtil = new WhImplUtil();
				etreeService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
				elanInfoService = (ElanInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ElanInfo);
				pwNNIService = (PwNniInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwNniBuffer);
				for(Integer siteId : siteIdList){
					if("0".equals(siteUtil.SiteTypeUtil(siteId)+"")){////非失连网元、非虚拟网元下发设备
						neObject = whImplUtil.siteIdToNeObject(siteId);
						eTreeObjectList = this.getEtreeInfoObject(etreeService,elanInfoService,pwNNIService,siteId);//获取下发etree对象
						ActionObject actionObj = this.getActionObject(eTreeObjectList, neObject);
						operationObjectAfter.getActionObjectList().add(actionObj);
					}
				}
				super.sendAction(operationObjectAfter);
				operationObjectResult = super.verification(operationObjectAfter);//设备返回信息
				if (!(operationObjectResult.isSuccess())) {//有一次失败，直接返回配置失败
					super.clearLock(siteIdList);
					return super.getErrorMessage(operationObjectResult);
				}
			}
			return ResultString.CONFIG_SUCCESS;
		} catch (Exception e) {
			throw e;
		} finally {
			super.clearLock(siteIdList);
			UiUtil.closeService_MB(elanInfoService);
			UiUtil.closeService_MB(etreeService);
			UiUtil.closeService_MB(pwNNIService);
		}
	}


/**
 * siteID:网元id
 * @param siteId
 * @return
 */
	private List<ETreeObject> getEtreeInfoObject(EtreeInfoService_MB etreeService,ElanInfoService_MB elanInfoService,PwNniInfoService_MB pwNNIService ,int siteId) {
		List<ETreeObject> etreeObjList = null;
		Map<String, List<EtreeInfo>> ServiceIdAndEtreeListMap = null;
		List<String> removeServiceIdList = null;
		ETreeObject etreeObject = null;
		List<Integer> pwIdList = null;
		Map<String, List<ElanInfo>> serviceIdAndElanListMap = null;
		List<ElanInfo> elanInfos = null;
		EtreeInfo etreeInfo = null;
		try {
			
			removeServiceIdList = new ArrayList<String>();
			ServiceIdAndEtreeListMap = etreeService.selectNodeBySite(siteId);
			serviceIdAndElanListMap = elanInfoService.selectSiteNodeBy(siteId);
			
			for (String str : serviceIdAndElanListMap.keySet()) {
				elanInfos = serviceIdAndElanListMap.get(str);
				List<EtreeInfo> etreeInfos = new ArrayList<EtreeInfo>();
				for (ElanInfo elanInfo : elanInfos) {
					etreeInfo = new EtreeInfo();
					if (elanInfo.getaSiteId() == siteId) {
						etreeInfo.setaXcId(elanInfo.getAxcId());
						etreeInfo.setzXcId(elanInfo.getAxcId());
						etreeInfo.setActiveStatus(elanInfo.getActiveStatus());
						etreeInfo.setRootSite(elanInfo.getaSiteId());
						etreeInfo.setBranchSite(elanInfo.getzSiteId());
						etreeInfo.setAmostAcId(elanInfo.getAmostAcId());
						etreeInfo.setZmostAcId(elanInfo.getZmostAcId());
						etreeInfo.setaAcId(elanInfo.getaAcId());
						etreeInfo.setzAcId(elanInfo.getzAcId());
					} else {
						etreeInfo.setaXcId(elanInfo.getZxcId());
						etreeInfo.setzXcId(elanInfo.getAxcId());
						etreeInfo.setActiveStatus(elanInfo.getActiveStatus());
						etreeInfo.setRootSite(elanInfo.getzSiteId());
						etreeInfo.setBranchSite(elanInfo.getaSiteId());
						etreeInfo.setaAcId(elanInfo.getzAcId());
						etreeInfo.setzAcId(elanInfo.getaAcId());
						etreeInfo.setAmostAcId(elanInfo.getZmostAcId());
						etreeInfo.setZmostAcId(elanInfo.getAmostAcId());
					}
					etreeInfo.setServiceType(elanInfo.getServiceType());
					etreeInfo.setId(elanInfo.getId());
					etreeInfo.setServiceId(elanInfo.getServiceId());
					etreeInfo.setPwId(elanInfo.getPwId());
					etreeInfo.setServiceType(elanInfo.getServiceType());
					etreeInfo.setName(elanInfo.getName());

					etreeInfo.setCreateUser(elanInfo.getCreateUser());
					etreeInfo.setCreateTime(elanInfo.getCreateTime());
					etreeInfo.setIsSingle(elanInfo.getIsSingle());
					etreeInfo.setJobStatus(elanInfo.getJobStatus());
					etreeInfos.add(etreeInfo);
				}
				ServiceIdAndEtreeListMap.put(etreeInfos.get(0).getServiceId() + "/" + etreeInfos.get(0).getServiceType(), etreeInfos);
			}
			for (List<EtreeInfo> info : ServiceIdAndEtreeListMap.values()) {
				for (EtreeInfo etreeInfo2 : info) {
					if (etreeInfo2.getActiveStatus() != EActiveStatus.ACTIVITY.getValue()) {
						removeServiceIdList.add(etreeInfo2.getServiceId() + "/" + etreeInfo2.getServiceType());
					}
				}
			}
			for (String serviceId : removeServiceIdList) {
				ServiceIdAndEtreeListMap.remove(serviceId);
			}

			etreeObjList = new ArrayList<ETreeObject>();

			for (String str : ServiceIdAndEtreeListMap.keySet()) {
				pwIdList = new ArrayList<Integer>();
				List<EtreeInfo> etreeInfoList = ServiceIdAndEtreeListMap.get(str);
				// 根网元对应的pwIDList
				for (EtreeInfo etree : etreeInfoList) {
					if (etree.getRootSite() == siteId) {
						pwIdList.add(etree.getPwId());
					}
				}
				// 设置每一条业务的nni端口的仿真lanportId（1-64）和uni端口的lanportId（1-10）
//				configLanPortIdForEtreeService(pwNNIService,etreeInfoList, siteId);

				for (EtreeInfo eInfo : etreeInfoList) {
					if (siteId == eInfo.getRootSite()) {
						etreeObject = new ETreeObject();
						etreeObject.setRole(1);
						etreeObject.setVplsId(eInfo.getaXcId());
						configETreeUNIObject(etreeObject, eInfo.getAmostAcId());
						configETreeNNIObject(pwNNIService,etreeObject, pwIdList, siteId);
						etreeObject.setType(eInfo.getServiceType());
						etreeObjList.add(etreeObject);
						break;
					} else if (siteId == eInfo.getBranchSite()) {
						pwIdList.clear();
						etreeObject = new ETreeObject();
						etreeObject.setRole(0);
						pwIdList.add(eInfo.getPwId());
						etreeObject = new ETreeObject();
						etreeObject.setVplsId(eInfo.getzXcId());
						configETreeUNIObject(etreeObject, eInfo.getZmostAcId());
						configETreeNNIObject(pwNNIService,etreeObject, pwIdList, siteId);
						etreeObject.setType(eInfo.getServiceType());
						etreeObjList.add(etreeObject);
						break;

					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			ServiceIdAndEtreeListMap = null;
			removeServiceIdList = null;
		}
		return etreeObjList;
	}

	private void configLanPortIdForEtreeService(PwNniInfoService_MB pwNNIService,List<EtreeInfo> etreeInfoList, int siteId) throws Exception {
		Map<Integer, Integer> acIdAndLanPortIdNumberMap = new HashMap<Integer, Integer>();
		Map<Integer, Integer> pwNNIandLanPortIdNumberMap = new HashMap<Integer, Integer>();
		Set<Integer> acIdSet = new HashSet<Integer>();
		List<Integer> pwIdList = new ArrayList<Integer>();
		PwNniInfo pwNNIInfo = null;
		List<PwNniInfo> pwNNIInfoList = null;
		UiUtil uiUtil = null;
		try {
			uiUtil = new UiUtil();
			int count = 1;
			for (EtreeInfo etreeInfo : etreeInfoList) {
				if (etreeInfo.getRootSite() == siteId) {
					acIdSet.addAll(uiUtil.getAcIdSets(etreeInfo.getAmostAcId()));
				} else if (etreeInfo.getBranchSite() == siteId) {
					acIdSet.addAll(uiUtil.getAcIdSets(etreeInfo.getZmostAcId()));
				}
				pwIdList.add(etreeInfo.getPwId());
			}
			// uni
			for (Integer acId : acIdSet) {
				acIdAndLanPortIdNumberMap.put(acId, count);
				count++;
			}
			// nni
			pwNNIInfoList = new ArrayList<PwNniInfo>();
			for (Integer pwId : pwIdList) {
				pwNNIInfo = new PwNniInfo();
				pwNNIInfo.setSiteId(siteId);
				pwNNIInfo.setPwId(pwId);
				pwNNIInfoList.addAll(pwNNIService.select(pwNNIInfo));
			}
			count = 1;
			for (PwNniInfo pwNNI : pwNNIInfoList) {
				pwNNIandLanPortIdNumberMap.put(pwNNI.getId(), count);
				count++;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			acIdAndLanPortIdNumberMap = null;
			pwNNIandLanPortIdNumberMap = null;
			acIdSet = null;
			pwIdList = new ArrayList<Integer>();
			pwNNIInfo = null;
			pwNNIInfoList = null;
		}

	}

	private void configETreeNNIObject(PwNniInfoService_MB pwNniService,ETreeObject etreeObject, List<Integer> pwIdList, int siteId) {
		List<ETreeNNIObject> eTreeNNIObjectList = new ArrayList<ETreeNNIObject>();
		ETreeNNIObject eTreeNNIObject = null;
		PwNniInfo pwnniInfo = null;
		List<PwNniInfo> pwNniInfoList = null;

		PwInfoService_MB pwInfoService = null;
		PwInfo pwInfo = null;

		try {
			pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			pwNniInfoList = new ArrayList<PwNniInfo>();
			pwnniInfo = new PwNniInfo();
			for (Integer pwId : pwIdList) {
				pwnniInfo.setPwId(pwId);
				pwnniInfo.setSiteId(siteId);
				pwNniInfoList.addAll(pwNniService.select(pwnniInfo));
			}
			for (PwNniInfo nniInfo : pwNniInfoList) {
				pwInfo = new PwInfo();
				pwInfo.setPwId(nniInfo.getPwId());
				pwInfo = pwInfoService.selectBypwid_notjoin(pwInfo);
				eTreeNNIObject = new ETreeNNIObject();
				eTreeNNIObject.setDownTagRecognition(Integer.parseInt(UiUtil.getCodeById(nniInfo.getTagAction()).getCodeValue()));
				eTreeNNIObject.setLanPortId(nniInfo.getLanId()-10);
				eTreeNNIObject.setMacAddresslearn(Integer.parseInt(UiUtil.getCodeById(nniInfo.getMacAddressLearn()).getCodeValue()));
				eTreeNNIObject.setPortSplitHorizon(Integer.parseInt(UiUtil.getCodeById(nniInfo.getHorizontalDivision()).getCodeValue()));
				eTreeNNIObject.setControlEnabl(Integer.parseInt(UiUtil.getCodeById(nniInfo.getControlEnable()).getCodeValue()));
				if (siteId == pwInfo.getASiteId()) {
					eTreeNNIObject.setPwID(pwInfo.getApwServiceId());
				} else if (siteId == pwInfo.getZSiteId()) {
					eTreeNNIObject.setPwID(pwInfo.getZpwServiceId());
				}
				eTreeNNIObject.setDownTpidNni(Integer.parseInt(UiUtil.getCodeById(nniInfo.getTpid()).getCodeValue()));
				eTreeNNIObject.setUpTagBehavior(Integer.parseInt(UiUtil.getCodeById(nniInfo.getExitRule()).getCodeValue()));
				eTreeNNIObject.setUpTagValnId(Integer.parseInt(nniInfo.getSvlan()));
				eTreeNNIObject.setUpTagValnPri(Integer.parseInt(nniInfo.getVlanpri()));			
				eTreeNNIObjectList.add(eTreeNNIObject);
			}
			etreeObject.setETreeNNIObjectList(eTreeNNIObjectList);
			etreeObject.setETreeNNIObjCount(eTreeNNIObjectList.size());

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			eTreeNNIObject = null;
			UiUtil.closeService_MB(pwInfoService);
			pwnniInfo = null;
			pwNniInfoList = null;
		}
	}

	private void configETreeUNIObject(ETreeObject etreeObject, String acIds) {
		List<ETreeUNIObject> etreeUniObjList = new ArrayList<ETreeUNIObject>();
		ETreeUNIObject uniObj = null;
		AcPortInfoService_MB acSerivce = null;
		AcBufferService_MB uniBufService = null;
		QosInfoService_MB qosInfoService = null;
		List<Integer> acIdList = null;
//		AcPortInfo acPortInfo = null;
		PortService_MB portService = null;
		PortInst portInst = null;
		List<Acbuffer> bufList = null;
		List<VpwsBuffer> vpwsBufList = null;
		QosInfo qosInfo = null;
		List<QosInfo> qosInfoList = null;
		List<AcPortInfo> acPortInfoList = null;
		try {
			qosInfoList = new ArrayList<QosInfo>();
			acSerivce = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			uniBufService = (AcBufferService_MB) ConstantUtil.serviceFactory.newService_MB(Services.UniBuffer);
			qosInfoService = (QosInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosInfo);
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);

			acIdList = new ArrayList<Integer>();
			  if(null != acIds && !"".equals(acIds))
	             {
	            	 for(String acId : acIds.split(","))
	            	 {
	            		 acIdList.add(Integer.parseInt(acId.trim())); 
	            	 }
	             }
//			acPortInfo = acSerivce.select(acIdList).get(0);
			 acPortInfoList = acSerivce.select(acIdList);
			WhImplUtil whImplUtil = new WhImplUtil();
			
			if(acPortInfoList != null && acPortInfoList.size()>0)
			{
				for(AcPortInfo acPortInst: acPortInfoList)
				{
					uniObj = new ETreeUNIObject();
					// 端口
					if (acPortInst.getLagId() > 0) {
						whImplUtil.lagId(acPortInst, uniObj);
						uniObj.setSlot(20);
					} else {
						portInst = whImplUtil.getportId(acPortInst.getPortId(), acPortInst.getSiteId());
						uniObj.setPort(portInst.getNumber());
						uniObj.setSlot(portInst.getSlotNumber());
					}
					// 简单qos
					qosInfoList = qosInfoService.getQosByObj(EServiceType.ACPORT.toString(), acPortInst.getId());
					if (null != qosInfoList && !qosInfoList.isEmpty()) {
						qosInfo = qosInfoList.get(0);
					}
					// 细分流
					bufList = new ArrayList<Acbuffer>();
					bufList = uniBufService.select(acPortInst.getId());
					vpwsBufList = getVpwsBufferList(bufList);
					
					uniObj.setLanPortId(acPortInst.getLanId());
					if (null != qosInfo) {
						uniObj.setCbs(qosInfo.getCbs());
						uniObj.setCir(qosInfo.getCir()/1000);
						uniObj.setCm(qosInfo.getColorSence());
						uniObj.setPbs(qosInfo.getPbs());
						uniObj.setPir(qosInfo.getPir()/1000);
					}
					etreeObject.setMacCount(acPortInst.getMacCount());
					uniObj.setDownTpidUni(Integer.parseInt(UiUtil.getCodeById(acPortInst.getDownTpid()).getCodeValue()));
					uniObj.setDownTagBehavior(Integer.parseInt(UiUtil.getCodeById(acPortInst.getExitRule()).getCodeValue()));
					uniObj.setDownTagValnId(Integer.parseInt(acPortInst.getVlanId()));
					uniObj.setDownTagValnPri(Integer.parseInt(acPortInst.getVlanpri()));
					uniObj.setMacAddresslearn(Integer.parseInt(UiUtil.getCodeById(acPortInst.getMacAddressLearn()).getCodeValue()));
					uniObj.setModel(Integer.parseInt(UiUtil.getCodeById(acPortInst.getModel()).getCodeValue()));
					uniObj.setPortSplitHorizon(Integer.parseInt(UiUtil.getCodeById(acPortInst.getHorizontalDivision()).getCodeValue()));
					uniObj.setUpTagRecognition(Integer.parseInt(UiUtil.getCodeById(acPortInst.getTagAction()).getCodeValue()));
					uniObj.setVCTrafficPolicing(Integer.parseInt(UiUtil.getCodeById(acPortInst.getManagerEnable()).getCodeValue()));
					uniObj.setVpwsBufferCount(vpwsBufList.size());
					uniObj.setVpwsBufferList(vpwsBufList);				
					etreeUniObjList.add(uniObj);
				}
			}
			etreeObject.setETreeUNIObjectList(etreeUniObjList);
			etreeObject.setETreeUNIObjCount(etreeUniObjList.size());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(acSerivce);
			UiUtil.closeService_MB(portService);
			UiUtil.closeService_MB(uniBufService);
			UiUtil.closeService_MB(qosInfoService);
			uniObj = null;
			acIdList = null;
			portInst = null;
			bufList = null;
			vpwsBufList = null;
			qosInfo = null;
			qosInfoList = null;
			acPortInfoList = null;
		}
	}
	
	private List<VpwsBuffer> getVpwsBufferList(List<Acbuffer> bufList) {
		List<VpwsBuffer> vpwsBufferList = new ArrayList<VpwsBuffer>();
		VpwsBuffer vpwsbuf = null;
		int i = 0;
		try {
			for (Acbuffer buf : bufList) {
				i++;
				vpwsbuf = new VpwsBuffer();
				vpwsbuf.set_802_1P(buf.getEightIp());
				vpwsbuf.setBufferEnable(buf.getBufferEnable());
				vpwsbuf.setCbs(buf.getCbs());
				vpwsbuf.setCir(buf.getCir()/1000);
				vpwsbuf.setCm(buf.getCm());
				vpwsbuf.setIpDscp(buf.getIpDscp());
				vpwsbuf.setModel(buf.getModel());
				vpwsbuf.setPbs(buf.getPbs());
				vpwsbuf.setPhb(Integer.parseInt(UiUtil.getCodeById(buf.getPhb()).getCodeValue()));
				vpwsbuf.setPir(buf.getPir()/1000);
				vpwsbuf.setPwLable(20);
				vpwsbuf.setVpwsBufferID(i);
				vpwsbuf.setSourceIP(buf.getSourceIp());
				vpwsbuf.setSourceMac(buf.getSourceMac());
				vpwsbuf.setStrategy(buf.getStrategy());
				vpwsbuf.setTargetIP(buf.getTargetIp());
				vpwsbuf.setTargetMac(buf.getTargetMac());
				vpwsbuf.setVlanId(buf.getVlanId());
				vpwsbuf.setSourceTcpPortId(buf.getSourceTcpPortId());
				vpwsbuf.setEndTcpPortId(buf.getEndTcpPortId());
				vpwsbuf.setIPTOS(buf.getIPTOS());
				vpwsbuf.setPortType(buf.getPortType());
				vpwsBufferList.add(vpwsbuf);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

		return vpwsBufferList;
	}

	/**
	 * 获取siteID集合
	 * 
	 * @param List
	 *            <EtreeInfo> etreeInfoList
	 * @return siteID集合
	 * @throws Exception
	 */
	public List<Integer> getSiteIds(List<EtreeInfo> etreeInfoList) throws Exception {
		List<Integer> siteIds = null;
		try {
			siteIds = new ArrayList<Integer>();
			for (EtreeInfo etreeInfo : etreeInfoList) {
				if (etreeInfo.getRootSite() > 0 ) {
					if (!siteIds.contains(etreeInfo.getRootSite()) && super.getManufacturer(etreeInfo.getRootSite()) == EManufacturer.WUHAN.getValue()) {
						siteIds.add(etreeInfo.getRootSite());
					}
				}
				if (etreeInfo.getBranchSite() > 0) {
					if (!siteIds.contains(etreeInfo.getBranchSite()) && super.getManufacturer(etreeInfo.getBranchSite()) == EManufacturer.WUHAN.getValue()) {
						siteIds.add(etreeInfo.getBranchSite());
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return siteIds;
	}

	/**
	 * 与设备同步etree业务,同时同步etree所占用的ac端口信息,以及pw信息
	 * 
	 * @author guoqc
	 * @param siteId
	 * @return
	 * @exception
	 */
	public Object synchro(int siteId) throws Exception {
		OperationObject operaObj = new OperationObject();
		EtreeInfoService_MB etreeService = null;
		try {
			operaObj = this.getSynchroOperationObject(siteId);// 封装下发数据
			super.sendAction(operaObj);// 下发数据
			super.verification(operaObj);// 验证是否下发成功
			if (operaObj.isSuccess()) {
				/* 成功，则与数据库进行同步 */
				etreeService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
				for (ActionObject actionObject : operaObj.getActionObjectList()) {
					etreeService.updateStatusActivate(siteId, EActiveStatus.UNACTIVITY.getValue());
					this.synchro_db(etreeService,actionObject.getEtreeObjectList(), siteId);
				}
				
				return ResultString.CONFIG_SUCCESS;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(etreeService);
		}
		return ResultString.CONFIG_TIMEOUT;
	}

	/**
	 * 封装下发数据
	 * 
	 * @param siteId
	 * @return operationObject
	 * @throws Exception
	 */
	private OperationObject getSynchroOperationObject(int siteId) throws Exception {
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;
		try {
			WhImplUtil whImplUtil = new WhImplUtil();
			operationObject = new OperationObject();
			neObject = whImplUtil.siteIdToNeObject(siteId);
			actionObject = new ActionObject();
			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
			actionObject.setNeObject(neObject);
			actionObject.setType("etreeSynchro");
			operationObject.getActionObjectList().add(actionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			actionObject = null;
		}
		return operationObject;
	}

	/**
	 * 
	 * @param elineObjList
	 * @param siteId
	 * @throws Exception
	 */
	private void synchro_db(EtreeInfoService_MB etreeService,List<ETreeObject> etreeObjList, int siteId) throws Exception {
		List<EtreeInfo> etreeInfoList = null;
		List<AcPortInfo> acPortInfos = null;
		List<PwNniInfo> infos = null;
		int serviceId = 1;
		List<Integer> acIdList = null;
		AcPortInfoService_MB acInfoService = null;
		try {
			acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			SynchroUtil synchroUtil = new SynchroUtil();
			for (ETreeObject eTreeObject : etreeObjList) {
				acIdList = new ArrayList<Integer>();
				try {
					if (eTreeObject.getType() == 3) {
						//AC
						try {
							acPortInfos = this.getAcPortInfo(eTreeObject, siteId);
							for (AcPortInfo acPortInfo : acPortInfos) {
								int acId = synchroUtil.acPortInfoSynchro(acPortInfo, siteId);
								if(acId > 0 && !acIdList.contains(acId)){
									acIdList.add(acId);
								}
							}
						} catch (Exception e) {
							ExceptionManage.dispose(e, getClass());
							continue;
						}
						//业务
						try {
							etreeInfoList = this.getEtreeInfo(eTreeObject, siteId, serviceId,acIdList);
							synchroUtil.etreeSynchro(etreeService,etreeInfoList, siteId);
						} catch (Exception e) {
							ExceptionManage.dispose(e, getClass());
							acInfoService.updateState(acIdList);
							continue;
						}
						//NNI
						infos = this.getPwNniInfo(eTreeObject, siteId);
						for (PwNniInfo pwNniInfo : infos) {
							synchroUtil.pwNniBufferInfoSynchro(pwNniInfo, siteId, true);
						}
						serviceId++;
					}
				} catch (Exception e) {
					ExceptionManage.dispose(e, getClass());
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(acInfoService);
			etreeInfoList = null;
			acPortInfos = null;
			infos = null;
		}
	}

	/**
	 * 
	 * @param elanObjectList
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	private List<EtreeInfo> getEtreeInfo(ETreeObject eTreeObject, int siteId, int serviceId, List<Integer> acIdList) throws Exception {
		List<EtreeInfo> elanInfoList = new ArrayList<EtreeInfo>();
		PwInfoService_MB pwInfoService = null;
		PwInfo pwinfo = null;
		String acIds = "";
		try {
			acIds = acIdList.toString();
			pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			for (ETreeNNIObject nniObject : eTreeObject.getETreeNNIObjectList()) {
				EtreeInfo etreeInfo = new EtreeInfo();
				if(eTreeObject.getRole() ==0){
					etreeInfo.setBranchSite(siteId);
					etreeInfo.setzSiteId(siteId);
					etreeInfo.setzXcId(eTreeObject.getVplsId());
					etreeInfo.setZmostAcId(acIds.subSequence(1, acIds.length() -1).toString());
				}else if(eTreeObject.getRole() == 1){
					etreeInfo.setRootSite(siteId);
					etreeInfo.setaSiteId(siteId);
					etreeInfo.setaXcId(eTreeObject.getVplsId());
					etreeInfo.setAmostAcId(acIds.subSequence(1, acIds.length() -1).toString());
				}
				etreeInfo.setServiceId(serviceId);
				pwinfo = pwInfoService.select(siteId, nniObject.getPwID());
				etreeInfo.setPwId(pwinfo.getPwId());
				etreeInfo.setServiceType(EServiceType.ETREE.getValue());
				etreeInfo.setName("etree" + super.getNowMS());
				etreeInfo.setActiveStatus(EActiveStatus.ACTIVITY.getValue());
				etreeInfo.setIsSingle(1);
				elanInfoList.add(etreeInfo);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(pwInfoService);
			acIds = null;
		}
		return elanInfoList;
	}

	/**
	 * 
	 * @param elanObject
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	private List<PwNniInfo> getPwNniInfo(ETreeObject eTreeObject, int siteId) throws Exception {
		List<PwNniInfo> pwNniInfoList = new ArrayList<PwNniInfo>();
		PwInfoService_MB pwInfoService = null;
		PwInfo pwinfo = null;
		pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
		try {
			for (ETreeNNIObject nniObj : eTreeObject.getETreeNNIObjectList()) {
				PwNniInfo pwNniInfo = new PwNniInfo();
				pwNniInfo.setSiteId(siteId);
				pwinfo = pwInfoService.select(siteId, nniObj.getPwID());
				pwNniInfo.setPwId(pwinfo.getPwId());
				int lanId=nniObj.getLanPortId()+10;
				pwNniInfo.setLanId(lanId);
				pwNniInfo.setTagAction(UiUtil.getCodeByValue("TAGRECOGNITION", nniObj.getDownTagRecognition() + "").getId());
				pwNniInfo.setExitRule(UiUtil.getCodeByValue("PORTTAGBEHAVIOR", nniObj.getUpTagBehavior() + "").getId());
				pwNniInfo.setSvlan(nniObj.getUpTagValnId() + "");
				pwNniInfo.setVlanpri(nniObj.getUpTagValnPri() + "");
				pwNniInfo.setMacAddressLearn(UiUtil.getCodeByValue("MACLEARN", nniObj.getMacAddresslearn() + "").getId());
				pwNniInfo.setHorizontalDivision(UiUtil.getCodeByValue("VCTRAFFICPOLICING", nniObj.getPortSplitHorizon() + "").getId());
				pwNniInfo.setControlEnable(UiUtil.getCodeByValue("ENABLEDSTATUE", nniObj.getControlEnabl() + "").getId());
				pwNniInfoList.add(pwNniInfo);
			}
		} finally {
			UiUtil.closeService_MB(pwInfoService);
		}

		return pwNniInfoList;
	}

	/**
	 * 
	 * @param elanObject
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	private List<AcPortInfo> getAcPortInfo(ETreeObject eTreeObject, int siteId) throws Exception {
		List<AcPortInfo> acPortInfoList = new ArrayList<AcPortInfo>();
		AcPortInfo acPortInfo = null;
		PortInst portInst = null;
		PortService_MB portService = null;
		PortLagService_MB portLagService = null;
		QosInfo simpleQos = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			portLagService = (PortLagService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTLAG);
			for (int i = 0; i < eTreeObject.getETreeUNIObjectList().size(); i++) {
				ETreeUNIObject uniObj = eTreeObject.getETreeUNIObjectList().get(i);
				acPortInfo = new AcPortInfo();
				int portModel = 0;
				if (uniObj.getPort() < 100) {
					portInst = new PortInst();
					portInst.setNumber(uniObj.getPort());
					portInst.setSiteId(siteId);
					portInst = portService.select(portInst).get(0);
					acPortInfo.setPortId(portInst.getPortId());
					portModel = portInst.getPortAttr().getPortUniAttr().getVlanRelevance() == 51?1:0;
				} else if (uniObj.getPort() != 201) {
					PortLagInfo portLagInfo = new PortLagInfo();
					portLagInfo.setSiteId(siteId);
					portLagInfo.setLagID(uniObj.getPort() - 100);
					portLagInfo = portLagService.selectLAGByCondition(portLagInfo).get(0);
					acPortInfo.setLagId(portLagInfo.getId());
				}
				acPortInfo.setLanId(uniObj.getLanPortId());
				acPortInfo.setMacCount(eTreeObject.getMacCount());
				acPortInfo.setSiteId(siteId);
				simpleQos = new QosInfo();
				simpleQos.setPir(uniObj.getPir()*1000);
				simpleQos.setCir(uniObj.getCir()*1000);
				simpleQos.setCbs(uniObj.getCbs());
				simpleQos.setPbs(uniObj.getPbs());
				simpleQos.setCos(5);
				simpleQos.setQosType("L2");
				acPortInfo.setTagAction(UiUtil.getCodeByValue("TAGRECOGNITION", uniObj.getUpTagRecognition() + "").getId());
				acPortInfo.setExitRule(UiUtil.getCodeByValue("PORTTAGBEHAVIOR", uniObj.getDownTagBehavior() + "").getId());
				acPortInfo.setVlanId(uniObj.getDownTagValnId() + "");
				acPortInfo.setVlanpri(uniObj.getDownTagValnPri() + "");
				acPortInfo.setManagerEnable(UiUtil.getCodeByValue("VCTRAFFICPOLICING", uniObj.getVCTrafficPolicing() + "").getId());
				acPortInfo.setHorizontalDivision(UiUtil.getCodeByValue("VCTRAFFICPOLICING", uniObj.getPortSplitHorizon() + "").getId());
				acPortInfo.setMacAddressLearn(UiUtil.getCodeByValue("MACLEARN", uniObj.getMacAddresslearn()+"").getId());
				acPortInfo.setName("ac_etree" +(i+1)+"-"+ eTreeObject.getVplsId()+"_"+super.getNowMS());
				acPortInfo.setAcStatus(EActiveStatus.ACTIVITY.getValue());
				acPortInfo.setModel(UiUtil.getCodeByValue("MODEL", uniObj.getModel() + "").getId());
				// acPortInfo.setBufType(uniObj);
				acPortInfo.setBufferList(getacbuffer(uniObj, uniObj.getPort(), siteId));
				acPortInfo.setPortModel(UiUtil.getCodeByValue("UNIPORTMODE", portModel+"").getId());
				acPortInfo.setSimpleQos(simpleQos);
				acPortInfo.setIsUser(1);
				acPortInfoList.add(acPortInfo);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(portService);
			UiUtil.closeService_MB(portLagService);
		}
		return acPortInfoList;
	}

	/**
	 * 
	 * @param uniObj
	 * @param portId
	 * @return
	 * @throws Exception
	 */
	private List<Acbuffer> getacbuffer(ETreeUNIObject uniObj, int portNumber, int siteId) throws Exception {
		List<Acbuffer> acbufferList = new ArrayList<Acbuffer>();
		Acbuffer acbuffer = new Acbuffer();
//		if (portNumber < 100) {
			for (VpwsBuffer VpwsBuffer : uniObj.getVpwsBufferList()) {
				try {
					acbuffer.setBufferEnable(VpwsBuffer.getBufferEnable());
					acbuffer.setVlanId(VpwsBuffer.getVlanId());
					acbuffer.setSourceMac(VpwsBuffer.getSourceMac());
					acbuffer.setTargetMac(VpwsBuffer.getTargetMac());
					acbuffer.setEightIp(VpwsBuffer.get_802_1P());
					acbuffer.setSourceIp(VpwsBuffer.getSourceIP());
					acbuffer.setTargetIp(VpwsBuffer.getTargetIP());
					acbuffer.setIpDscp(VpwsBuffer.getIpDscp());
					acbuffer.setModel(VpwsBuffer.getModel());
					acbuffer.setCir(VpwsBuffer.getCir()*1000);
					acbuffer.setPir(VpwsBuffer.getPir()*1000);
					acbuffer.setCm(VpwsBuffer.getCm());
					acbuffer.setCbs(VpwsBuffer.getCbs());
					acbuffer.setPbs(VpwsBuffer.getPbs());
					acbuffer.setStrategy(VpwsBuffer.getStrategy());
					acbuffer.setPhb(UiUtil.getCodeByValue("CONRIRMPHB", VpwsBuffer.getPhb() + "").getId());
					acbuffer.setSourceTcpPortId(VpwsBuffer.getSourceTcpPortId());
					acbuffer.setEndTcpPortId(VpwsBuffer.getEndTcpPortId());
					acbuffer.setIPTOS(VpwsBuffer.getIPTOS());
					acbuffer.setPortType(VpwsBuffer.getPortType());
					acbuffer.setModel(VpwsBuffer.getModel());
					acbufferList.add(acbuffer);
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
//		}
		return acbufferList;
	}

	public List<EtreeInfo> consistence(int siteId) {
		List<EtreeInfo> etreeList = new ArrayList<EtreeInfo>();
		try {
			OperationObject operaObj = this.getSynchroOperationObject(siteId);// 封装下发数据
			super.sendAction(operaObj);// 下发数据
			super.verification(operaObj);// 验证是否下发成功
			if (operaObj.isSuccess()) {
				for (ActionObject actionObject : operaObj.getActionObjectList()) {
					etreeList = this.getEtreeInfoList(actionObject.getEtreeObjectList(), siteId);
					return etreeList;
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return etreeList;
	}

	private List<EtreeInfo> getEtreeInfoList(List<ETreeObject> etreeObjectList, int siteId) {
		List<EtreeInfo> etreeList = new ArrayList<EtreeInfo>();
		int serviceId = 1;
		List<Integer> acIds = new ArrayList<Integer>();
		for (ETreeObject etreeObject : etreeObjectList) {
			try {
				if (etreeObject.getType() == 3) {
					List<AcPortInfo> acList = this.getAcPortInfo(etreeObject, siteId);
					List<EtreeInfo> etreeInfoList = this.getEtreeInfo(etreeObject, siteId, serviceId,acIds);
					List<PwNniInfo> pwNniList = this.getPwNniInfo(etreeObject, siteId);
					EtreeInfo etreeInfo = etreeInfoList.get(0);
					etreeInfo.getAcPortList().addAll(acList);
					etreeInfo.getPwNniList().addAll(pwNniList);
					etreeList.add(etreeInfo);
				}
			} catch (Exception e) {
				ExceptionManage.dispose(e, this.getClass());
			}
			serviceId++;
		}
		return etreeList;
	}

//	@Override
//	public List<Integer> getSiteIdList(Object object) throws Exception {
//		List<Integer> siteIds = null;
//		try {
//			List<EtreeInfo> etreeInfoList = (List<EtreeInfo>) object;
//			siteIds = new ArrayList<Integer>();
//			for (EtreeInfo etreeInfo : etreeInfoList) {
//				if (etreeInfo.getRootSite() > 0) {
//					if (!siteIds.contains(etreeInfo.getRootSite()) && super.getManufacturer(etreeInfo.getRootSite()) == EManufacturer.WUHAN.getValue()) {
//						siteIds.add(etreeInfo.getRootSite());
//					}
//				}
//				if (etreeInfo.getBranchSite() > 0) {
//					if (!siteIds.contains(etreeInfo.getBranchSite()) && super.getManufacturer(etreeInfo.getBranchSite()) == EManufacturer.WUHAN.getValue()) {
//						siteIds.add(etreeInfo.getBranchSite());
//					}
//				}
//			}
//		} catch (Exception e) {
//			throw e;
//		}
//		return siteIds;
//	}
}