package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.oam.OamLinkInfo;
import com.nms.drive.service.bean.ETHLinkOAMObject;
import com.nms.drive.service.bean.NEObject;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.oam.OamInfoService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.ActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.WHOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SynchroUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.SiteLockTypeUtil;
import com.nms.ui.manager.UiUtil;

public class ETHLinkOAMConfigWHServiceImpl extends WHOperationBase implements
		OperationServiceI {

	@Override
	public String excutionDelete(List objectList) throws Exception {
		List<OamInfo> oamInfoList = (List<OamInfo>)objectList;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		try {
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(oamInfoList.get(0).getOamLinkInfo().getSiteId());
			super.lockSite(siteIdList, SiteLockTypeUtil.ETHLINK_INSERT);
			operationObjectAfter = this.getOperationObject(siteIdList);
			if (operationObjectAfter.getActionObjectList().size() > 0) {
				super.sendAction(operationObjectAfter);
				operationObjectResult = super.verification(operationObjectAfter);
				if (operationObjectResult.isSuccess()) {
					super.clearLock(siteIdList);
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				} else {
					super.clearLock(siteIdList);
					return super.getErrorMessage(operationObjectResult);
				}
			} else {
				return ResultString.CONFIG_SUCCESS;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			super.clearLock(siteIdList);
		}
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		OamInfo oamInfo = (OamInfo)object;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		try {
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(oamInfo.getOamLinkInfo().getSiteId());
			super.lockSite(siteIdList, SiteLockTypeUtil.ETHLINK_INSERT);
			operationObjectAfter = this.getOperationObject(siteIdList);
			if (operationObjectAfter.getActionObjectList().size() > 0) {
				super.sendAction(operationObjectAfter);
				operationObjectResult = super.verification(operationObjectAfter);
				if (operationObjectResult.isSuccess()) {
					super.clearLock(siteIdList);
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				} else {
					super.clearLock(siteIdList);
					return super.getErrorMessage(operationObjectResult);
				}
			} else {
				return ResultString.CONFIG_SUCCESS;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			super.clearLock(siteIdList);
		}
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		OamInfo oamInfo = (OamInfo)object;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		try {
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(oamInfo.getOamLinkInfo().getSiteId());
			super.lockSite(siteIdList, SiteLockTypeUtil.ETHLINK_INSERT);
			operationObjectAfter = this.getOperationObject(siteIdList);
			if (operationObjectAfter.getActionObjectList().size() > 0) {
				super.sendAction(operationObjectAfter);
				operationObjectResult = super.verification(operationObjectAfter);
				if (operationObjectResult.isSuccess()) {
					super.clearLock(siteIdList);
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				} else {
					super.clearLock(siteIdList);
					return super.getErrorMessage(operationObjectResult);
				}
			} else {
				return ResultString.CONFIG_SUCCESS;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			super.clearLock(siteIdList);
		}
	}

	private OperationObject getOperationObject(List<Integer> siteIdList)
			throws Exception {
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;
		try {
			operationObject = new OperationObject();
			actionObject = new ActionObject();
			if(true){
				WhImplUtil whImplUtil = new WhImplUtil();
				neObject = whImplUtil.siteIdToNeObject(siteIdList.get(0));
				actionObject.setActionId(super.getActionId(operationObject
						.getActionObjectList()));
				actionObject.setNeObject(neObject);
				actionObject.setType("ethLinkOAMConfig");
				actionObject.setEthLinkOAMObject(getEthLinlOamObjList(siteIdList.get(0)));
				operationObject.getActionObjectList().add(actionObject);
			}else{
				actionObject.setStatus(ResultString.CONFIG_SUCCESS);
				operationObject.getActionObjectList().add(actionObject);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			actionObject = null;
			neObject = null;
		}
		return operationObject;
	}

	/**
	 * 查询该网元下所有以太网链路oam
	 * @param siteId
	 * @return
	 */
	private List<ETHLinkOAMObject> getEthLinlOamObjList(int siteId) {
		List<OamInfo> oamInfos = null;
		OamInfoService_MB oamInfoService = null;
		OamInfo info= null;
		OamLinkInfo oamLinkInfo = null;
		List<ETHLinkOAMObject> ethLinkOAMObjects = null;
		PortService_MB portService = null;
		PortInst portInst = null;
		try {
			oamInfoService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			info= new OamInfo();
			oamLinkInfo = new OamLinkInfo();
			oamLinkInfo.setSiteId(siteId);
			oamLinkInfo.setObjType("LINKOAM");
			info.setOamLinkInfo(oamLinkInfo);
			oamInfos = oamInfoService.queryLinkOAMBySiteId(info);
			ethLinkOAMObjects = new ArrayList<ETHLinkOAMObject>();
			int i = 1;
			if(oamInfos != null && oamInfos.size()>0){
				for(OamInfo oamInfo : oamInfos){
					oamLinkInfo = oamInfo.getOamLinkInfo();
					ETHLinkOAMObject ethLinkOAMObject = new ETHLinkOAMObject();
					portInst = new PortInst();
					portInst.setSiteId(siteId);
					portInst.setPortId(oamInfo.getOamLinkInfo().getObjId());
					portService.select(portInst);
					if(portService.select(portInst) != null && portService.select(portInst).size()>0){
						portInst = portService.select(portInst).get(0);
					}
					ethLinkOAMObject.setCout(i);
					ethLinkOAMObject.setPort(portInst.getNumber());
					ethLinkOAMObject.setOam(oamLinkInfo.isOamEnable()?1:2);
					ethLinkOAMObject.setOamModel(oamLinkInfo.getMode()+1);
					ethLinkOAMObject.setLoopBack(oamLinkInfo.getRemoteLoop());
					ethLinkOAMObject.setLoopBackTime(oamLinkInfo.getResponseOutTimeThreshold());
					ethLinkOAMObject.setLoopBackNumber(oamLinkInfo.getLinkEvent());
					ethLinkOAMObject.setOppositeLoop(oamLinkInfo.getMib());
					ethLinkOAMObject.setOppositeVariablel(oamLinkInfo.getMaxFrameLength());
					ethLinkOAMObject.setDyingGasp(oamLinkInfo.getOrganicId());
					ethLinkOAMObject.setCritical(oamLinkInfo.getFactoryInfo());
					ethLinkOAMObject.setErrorSignCycle(oamLinkInfo.getErrorSymboEventCycle());
					ethLinkOAMObject.setErrorSignThreshold(oamLinkInfo.getErrorSymboEventThreshold());
					ethLinkOAMObject.setErrorFrameEventCycle(oamLinkInfo.getErrorFrameEventCycle());
					ethLinkOAMObject.setErrorFrameEventThreshold(oamLinkInfo.getErrorFrameEventThreshold());
					ethLinkOAMObject.setErrorFrameCylceEventCycle(oamLinkInfo.getErrorFrameCycleEventCycle());
					ethLinkOAMObject.setErrorFrameCycleEventThreshold(oamLinkInfo.getErrorFrameCycleEventThreshold());
					ethLinkOAMObject.setErrorFrameSecondEventCycle(oamLinkInfo.getErrorFrameSecondEventCycle());
					ethLinkOAMObject.setErrorFrameSecondEventThreshold(oamLinkInfo.getErrorFrameSecondEventThreshold());
					ethLinkOAMObject.setMaxFrame(oamLinkInfo.getOamFarme());
					ethLinkOAMObjects.add(ethLinkOAMObject);
					i++;
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(portService);
			UiUtil.closeService_MB(oamInfoService);
		}
		return ethLinkOAMObjects;
	}
	@Override
	public Object synchro(int siteId) {
		OperationObject operaObj = new OperationObject();
		try {
			operaObj = this.getSynchroOperationObject(siteId);// 封装下发数据
			super.sendAction(operaObj);// 下发数据
			super.verification(operaObj);// 验证是否下发成功
			if (operaObj.isSuccess()) {
				/* 成功，则与数据库进行同步 */
				for (ActionObject actionObject : operaObj.getActionObjectList()) {
					this.synchro_db(actionObject.getEthLinkOAMObject(), siteId);
				}
				return ResultString.CONFIG_SUCCESS;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operaObj = null;
		}
		return "超时";
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
			operationObject = new OperationObject();
			WhImplUtil whImplUtil = new WhImplUtil();
			neObject = whImplUtil.siteIdToNeObject(siteId);
			actionObject = new ActionObject();
			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
			actionObject.setNeObject(neObject);
			actionObject.setType("ethlinkOamSynchro");
			operationObject.getActionObjectList().add(actionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			actionObject = null;
		}
		return operationObject;
	}
	
	/**
	 * 转换后的oam信息与数据库进行对比
	 * @param tmsoamObjects
	 * @param siteId
	 */
	private void synchro_db(List<ETHLinkOAMObject> linkOAMObjects,int siteId){
		List<OamInfo> infos = this.getOaminfo(linkOAMObjects, siteId);
		try {
			SynchroUtil synchroUtil = new SynchroUtil();
			synchroUtil.updateOam(infos);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private List<OamInfo> getOaminfo(List<ETHLinkOAMObject> linkOAMObjects, int siteId) {
		PortService_MB portService = null;
		PortInst portInst = null;
		List<OamInfo> oamInfos = null;
		OamInfo oamInfo = null;
		OamLinkInfo oamLinkInfo = null;
		try {
			oamInfos = new ArrayList<OamInfo>();
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			for(ETHLinkOAMObject ethLinkOAMObject : linkOAMObjects){
				oamInfo = new OamInfo();
				portInst = new PortInst();
				portInst.setSiteId(siteId);
				portInst.setNumber(ethLinkOAMObject.getPort());
				portInst = portService.select(portInst).get(0);
				oamLinkInfo = new OamLinkInfo();
				oamLinkInfo.setSiteId(siteId);
				oamLinkInfo.setObjType("LINKOAM");
				oamLinkInfo.setObjId(portInst.getPortId());
				oamLinkInfo.setOamEnable(ethLinkOAMObject.getOam() == 1?true:false);
				oamLinkInfo.setMode(ethLinkOAMObject.getOamModel()-1);
				oamLinkInfo.setRemoteLoop(ethLinkOAMObject.getLoopBack());
				oamLinkInfo.setResponseOutTimeThreshold(ethLinkOAMObject.getLoopBackTime());
				oamLinkInfo.setLinkEvent(ethLinkOAMObject.getLoopBackNumber());
				oamLinkInfo.setMib(ethLinkOAMObject.getOppositeLoop());
				oamLinkInfo.setMaxFrameLength(ethLinkOAMObject.getOppositeVariablel());
				oamLinkInfo.setOrganicId(ethLinkOAMObject.getDyingGasp());
				oamLinkInfo.setFactoryInfo(ethLinkOAMObject.getCritical());
				oamLinkInfo.setErrorSymboEventCycle(ethLinkOAMObject.getErrorSignCycle());
				oamLinkInfo.setErrorSymboEventThreshold(ethLinkOAMObject.getErrorSignThreshold());
				oamLinkInfo.setErrorFrameEventCycle(ethLinkOAMObject.getErrorFrameEventCycle());
				oamLinkInfo.setErrorFrameEventThreshold(ethLinkOAMObject.getErrorFrameEventThreshold());
				oamLinkInfo.setErrorFrameCycleEventCycle(ethLinkOAMObject.getErrorFrameCylceEventCycle());
				oamLinkInfo.setErrorFrameCycleEventThreshold(ethLinkOAMObject.getErrorFrameCycleEventThreshold());
				oamLinkInfo.setErrorFrameSecondEventCycle(ethLinkOAMObject.getErrorFrameSecondEventCycle());
				oamLinkInfo.setErrorFrameSecondEventThreshold(ethLinkOAMObject.getErrorFrameSecondEventThreshold());
				oamLinkInfo.setOamFarme(ethLinkOAMObject.getMaxFrame());
				oamInfo.setOamLinkInfo(oamLinkInfo);
				oamInfos.add(oamInfo);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(portService);
		}
		return oamInfos;
	}


}
