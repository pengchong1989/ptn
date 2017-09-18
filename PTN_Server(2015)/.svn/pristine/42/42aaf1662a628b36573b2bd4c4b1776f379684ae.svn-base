package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.equipment.port.Port2LayerAttr;
import com.nms.db.bean.equipment.port.PortAttr;
import com.nms.db.bean.equipment.port.PortDiscardInfo;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.qos.QosQueue;
import com.nms.drive.service.bean.EthServiceObject;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.bean.Port2LayerObject;
import com.nms.drive.service.bean.PortLAGbuffer;
import com.nms.drive.service.bean.PortLAGexitQueue;
import com.nms.drive.service.bean.PortObject;
import com.nms.drive.service.bean.PortSeniorConfig;
import com.nms.drive.service.bean.TMSOAMObject;
import com.nms.drive.service.bean.UniObject;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.model.equipment.port.Port2LayerAttrService_MB;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.PortDiscardService_MB;
import com.nms.model.ptn.qos.QosQueueService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.ActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.WHOperationBase;
import com.nms.service.impl.util.SynchroUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.SiteLockTypeUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTip;

public class PortWHServiceImpl extends WHOperationBase implements OperationServiceI {

	private String type = "";

	/**
	 * 修改
	 */
	@Override
	public String excutionUpdate(Object object) throws Exception {
		PortInst portInst = null;
		PortService_MB portService = null;
		List<Integer> siteIdList = null;
		Map<Integer, ActionObject> operationObjectBefore = null;
		OperationObject operationObjectResult = null;
		QosQueueService_MB qosQueueService = null;
		try {
			portInst = (PortInst) object;
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			qosQueueService = (QosQueueService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosQueue);
			
			/** 获取此网元ID */
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(portInst.getSiteId());
			/** 锁住此网元 */
			super.lockSite(siteIdList, SiteLockTypeUtil.PORT_UPDATE);
			OperationObject operationObject = this.getSendOperation(portInst);
			super.sendAction(operationObject);

			operationObjectResult = super.verification(operationObject);
			if (operationObjectResult.isSuccess()) {
				portService.saveOrUpdate(portInst);
				if(portInst.getQosQueues() != null && portInst.getQosQueues().size() != 8){
					qosQueueService.saveOrUpdate(portInst.getQosQueues());
				}
				
				super.clearLock(siteIdList);
				return operationObjectResult.getActionObjectList().get(0).getStatus();
			} else {
				boolean flag = super.rollback(operationObjectResult, operationObjectBefore);
				if (flag) {
					System.out.println("回滚成功");
				} else {
					System.out.println("回滚失败");
				}
				super.clearLock(siteIdList);
				return super.getErrorMessage(operationObjectResult);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			super.clearLock(siteIdList);
			UiUtil.closeService_MB(portService);
			UiUtil.closeService_MB(qosQueueService);
		}
	}

	/**
	 * 下发设备
	 * @param portInst
	 * @return
	 * @throws Exception
	 */
	private OperationObject getSendOperation(PortInst portInst) throws Exception {
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;

		try {
			operationObject = new OperationObject();
			WhImplUtil whImplUtil = new WhImplUtil();
			neObject = whImplUtil.siteIdToNeObject(portInst.getSiteId());
			actionObject = new ActionObject();
			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
			actionObject.setNeObject(neObject);
			
			if(portInst.getIsPriority() == 1){
				actionObject.setType("port_pri");
				actionObject.setProtObjectList(this.getPort_Pri(portInst));
			}else{
				actionObject.setProtObjectList(this.getPort(portInst));
				actionObject.setType("port");
			}
			
			operationObject.getActionObjectList().add(actionObject);

		} catch (Exception e) {
			throw e;
		} finally {
			actionObject = null;
			neObject = null;
		}
		return operationObject;
	}

	private List<PortObject> getPort_Pri(PortInst portInst) {
		List<PortObject> portObjectList = new ArrayList<PortObject>(); 
		PortObject portObject = new PortObject();
		UniObject uniObject = new UniObject();
		uniObject.setPortNumber(portInst.getNumber());
		portObject.setUniObject(uniObject);
		PortSeniorConfig seniorPortConfig = new PortSeniorConfig();
		seniorPortConfig.setPri_Priority(portInst.getPriority());
		portObject.setPortSeniorConfig(seniorPortConfig);
		portObjectList.add(portObject);
		return portObjectList;
	}

	/**
	 * 转换驱动层对象
	 * @param portInst
	 * @return
	 * @throws Exception
	 */
	private List<PortObject> getPort(PortInst portInst) throws Exception {
		List<PortObject> PortObjectList = null;
		PortObject PortObject = null;
		UniObject uniObject = null;
		List<QosQueue> qosQueues = portInst.getQosQueues();
		String priority = "";
		
		try {
			PortObjectList = new ArrayList<PortObject>();
			PortObject = new PortObject();
			uniObject = new UniObject();
			configRelateAttrData(uniObject, portInst);
			uniObject.setPortEnable(portInst.getIsEnabled_code());
			uniObject.setPortNumber(portInst.getNumber());
			uniObject.setQinqEnable(portInst.getIsEnabled_QinQ());
			uniObject.setLaserEnable(portInst.getIsEnabledLaser());
			uniObject.setServicePort(portInst.getServicePort());
			uniObject.setMappingEnable(portInst.getPortAttr().getPortUniAttr().getMappingEnable());
			
			if(portInst.getPortType().equals("NNI")){
				uniObject.setPortMode(2);
			}else if(portInst.getPortType().equals("UNI")){
				uniObject.setPortMode(1);
			}else{
				uniObject.setPortMode(0);
			}
			
			PortSeniorConfig SeniorPortConfig = new PortSeniorConfig();
			List<PortLAGexitQueue> exitQueueList = new ArrayList<PortLAGexitQueue>();// (出口队列调度策略)
			List<PortLAGbuffer> bufferList = new ArrayList<PortLAGbuffer>();// (队列缓存管理策略)
			if(qosQueues != null && qosQueues.size()>0){
				for(QosQueue qosQueue : qosQueues){
					PortLAGexitQueue portLAGexitQueue = new PortLAGexitQueue();
					portLAGexitQueue.setMode("SP+DWRR".equals(qosQueue.getQueueType())?1:0);
					portLAGexitQueue.setPopedom(qosQueue.getWeight());
					portLAGexitQueue.setDiscardProbability(qosQueue.getDisCard());
					exitQueueList.add(portLAGexitQueue);
					PortLAGbuffer portLAGbuffer = new PortLAGbuffer();
					portLAGbuffer.setMode(qosQueue.isWredEnable()?1:0);
					portLAGbuffer.setEndThreshold(qosQueue.getGreenHighThresh());
					portLAGbuffer.setStartThreshold(qosQueue.getGreenLowThresh());
					bufferList.add(portLAGbuffer);
					priority += qosQueue.getCos()+",";
				}
				SeniorPortConfig.setPriority(priority.substring(0, priority.length() - 1));
			}else{
				PortLAGexitQueue portLAGexitQueue = new PortLAGexitQueue();
				exitQueueList.add(portLAGexitQueue);
				exitQueueList.add(portLAGexitQueue);
				exitQueueList.add(portLAGexitQueue);
				exitQueueList.add(portLAGexitQueue);
				exitQueueList.add(portLAGexitQueue);
				exitQueueList.add(portLAGexitQueue);
				exitQueueList.add(portLAGexitQueue);
				exitQueueList.add(portLAGexitQueue);
				PortLAGbuffer portLAGbuffer = new PortLAGbuffer();
				bufferList.add(portLAGbuffer);
				bufferList.add(portLAGbuffer);
				bufferList.add(portLAGbuffer);
				bufferList.add(portLAGbuffer);
				bufferList.add(portLAGbuffer);
				bufferList.add(portLAGbuffer);
				bufferList.add(portLAGbuffer);
				bufferList.add(portLAGbuffer);
			}
			SeniorPortConfig.setExitQueueList(exitQueueList);
			SeniorPortConfig.setBufferList(bufferList);
			String exitLimit = portInst.getPortAttr().getExitLimit();
			if(exitLimit == null || ("-1").equals(exitLimit) || ("").equals(exitLimit)){
				exitLimit = 10000000+"";
			}
			SeniorPortConfig.setPortLimitation(Integer.parseInt(exitLimit));// 端口出口限速
			SeniorPortConfig.setBroadcastBate(Integer.parseInt(UiUtil.getCodeById(portInst.getPortAttr().getPortUniAttr().getIsBroadcast()).getCodeValue()));// 广播包抑制 (0)/1=(关)/开
			if(!"".equals(portInst.getPortAttr().getPortUniAttr().getBroadcast())){
				SeniorPortConfig.setBroadcastFlux(Integer.parseInt(portInst.getPortAttr().getPortUniAttr().getBroadcast()));// 广播包流量
			}
			SeniorPortConfig.setGroupBroadcastBate(Integer.parseInt(UiUtil.getCodeById(portInst.getPortAttr().getPortUniAttr().getIsMulticast()).getCodeValue()));// 组播包抑制
			if(!"".equals(portInst.getPortAttr().getPortUniAttr().getMulticast())){
				SeniorPortConfig.setGroupBroadcastFlux(Integer.parseInt(portInst.getPortAttr().getPortUniAttr().getMulticast()));// 组播包流量
			}
			SeniorPortConfig.setFloodBate(Integer.parseInt(UiUtil.getCodeById(portInst.getPortAttr().getPortUniAttr().getIsUnicast()).getCodeValue()));// 洪泛包抑制
			if(!"".equals(portInst.getPortAttr().getPortUniAttr().getBroadcast())){
				SeniorPortConfig.setFloodFlux(Integer.parseInt(portInst.getPortAttr().getPortUniAttr().getUnicast()));// 洪泛包流量
			}
			PortObject.setPortSeniorConfig(SeniorPortConfig);
			PortObject.setUniObject(uniObject);
			PortObjectList.add(PortObject);
		} catch (Exception e) {
			throw e;
		} finally {
			PortObject = null;
			uniObject = null;
			priority = null;
		}
		return PortObjectList;
	}

	/**
	 * 获取下发之前之前
	 * 
	 * @param siteIdList
	 * @return
	 * @throws Exception
	 */
	private Map<Integer, ActionObject> getOperationBefore(List<Integer> siteIdList) throws Exception {

		Map<Integer, ActionObject> operationBefore = null;
		OperationObject operationObject = null;
		try {
			operationBefore = new HashMap<Integer, ActionObject>();
			operationObject = this.getOperationObject(siteIdList);
			for (ActionObject actionObject : operationObject.getActionObjectList()) {
				operationBefore.put(actionObject.getNeObject().getNeAddress(), actionObject);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			operationObject = null;
		}
		return operationBefore;
	}

	/**
	 * 获取operationobject对象
	 * 
	 * @param siteIdList
	 *            网元ID
	 * @return
	 * @throws Exception
	 */
	private OperationObject getOperationObject(List<Integer> siteIdList) throws Exception {
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;

		try {
			operationObject = new OperationObject();
			WhImplUtil whImplUtil = new WhImplUtil();
			for (int siteId : siteIdList) {
				neObject = whImplUtil.siteIdToNeObject(siteId);

				actionObject = new ActionObject();
				actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
				actionObject.setNeObject(neObject);
				actionObject.setType(this.type);
				actionObject.setProtObjectList(this.getPortObject(siteId));
				actionObject.setTmsOamObject(this.getTMSObject(siteId));
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
	 * 根据网元id把tmsoam绑定到对应的端口上
	 * 
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	private TMSOAMObject getTMSObject(int siteId) throws Exception {

		TMSOAMObject tmsOAMObject = new TMSOAMObject();
		PortService_MB portService = null;
		List<PortInst> portInstList = null;
		PortInst portInstSelect = null;
		OamWHServiceImpl oamWHServiceImpl = null;

		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			portInstSelect = new PortInst();
			portInstSelect.setSiteId(siteId);
			portInstSelect.setPortType(this.type);
			portInstList = portService.select(portInstSelect);

			if (null == portInstList) {
				throw new Exception("查询portinst出错");
			}

			oamWHServiceImpl = new OamWHServiceImpl();
			for (PortInst portinst : portInstList) {

				if ("NNI".equalsIgnoreCase(this.type)) {
					oamWHServiceImpl.getOamInfo(tmsOAMObject, siteId, portinst.getPortId());
				} else if ("UNI".equalsIgnoreCase(this.type)) {
					oamWHServiceImpl.getOamInfo(tmsOAMObject, siteId, portinst.getPortId());
				}

			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(portService);
			portInstList = null;
			portInstSelect = null;
			oamWHServiceImpl = null;
		}
		return tmsOAMObject;
	}

	/**
	 * 获取网元下的接口
	 * 
	 * @param siteId
	 *            网元ID
	 * @return
	 * @throws Exception
	 */
	private List<PortObject> getPortObject(int siteId) throws Exception {

		List<PortObject> PortObjectList = null;
		PortService_MB portService = null;
		List<PortInst> portInstList = null;
		PortInst portInstSelect = null;
		PortObject PortObject = null;
		UniObject uniObject = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			portInstSelect = new PortInst();
			portInstSelect.setSiteId(siteId);
			portInstSelect.setPortType(this.type);
			portInstList = portService.select(portInstSelect);

			if (null == portInstList) {
				throw new Exception("查询portinst出错");
			}

			PortObjectList = new ArrayList<PortObject>();
			for (PortInst portinst : portInstList) {
				PortObject = new PortObject();
				uniObject = new UniObject();
				configRelateAttrData(uniObject, portinst);
				uniObject.setPortEnable(portinst.getIsEnabled_code());
				uniObject.setQinqEnable(portinst.getIsEnabled_QinQ());
				uniObject.setLaserEnable(portinst.getIsEnabledLaser());
				PortObject.setUniObject(uniObject);
				List<PortLAGexitQueue> exitQueueList = new ArrayList<PortLAGexitQueue>();// (出口队列调度策略)
				PortLAGexitQueue portLAGexitQueue = new PortLAGexitQueue();
				exitQueueList.add(portLAGexitQueue);
				exitQueueList.add(portLAGexitQueue);
				exitQueueList.add(portLAGexitQueue);
				exitQueueList.add(portLAGexitQueue);
				exitQueueList.add(portLAGexitQueue);
				exitQueueList.add(portLAGexitQueue);
				exitQueueList.add(portLAGexitQueue);
				exitQueueList.add(portLAGexitQueue);
				List<PortLAGbuffer> bufferList = new ArrayList<PortLAGbuffer>();// (队列缓存管理策略)
				PortLAGbuffer portLAGbuffer = new PortLAGbuffer();
				bufferList.add(portLAGbuffer);
				bufferList.add(portLAGbuffer);
				bufferList.add(portLAGbuffer);
				bufferList.add(portLAGbuffer);
				bufferList.add(portLAGbuffer);
				bufferList.add(portLAGbuffer);
				bufferList.add(portLAGbuffer);
				bufferList.add(portLAGbuffer);
				PortObject.getPortSeniorConfig().setBufferList(bufferList);
				PortObject.getPortSeniorConfig().setExitQueueList(exitQueueList);
				PortObjectList.add(PortObject);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(portService);
			portInstList = null;
			portInstSelect = null;
			PortObject = null;
			uniObject = null;

		}
		return PortObjectList;
	}

	/**
	 * 设置关系参数数据
	 * @param uniObject
	 * @param portInst
	 * @throws Exception
	 */
	private void configRelateAttrData(UniObject uniObject, PortInst portInst) throws Exception {
		try {

			uniObject.setRelevanceVlan(Integer.parseInt(UiUtil.getCodeById(portInst.getPortAttr().getPortUniAttr().getVlanRelevance()).getCodeValue()));
			uniObject.setRelevance802_1P(Integer.parseInt(UiUtil.getCodeById(portInst.getPortAttr().getPortUniAttr().getEightIpRelevance()).getCodeValue()));
			uniObject.setRelevanceSourceMac(Integer.parseInt(UiUtil.getCodeById(portInst.getPortAttr().getPortUniAttr().getSourceMacRelevance()).getCodeValue()));
			uniObject.setRelevanceTargetMac(Integer.parseInt(UiUtil.getCodeById(portInst.getPortAttr().getPortUniAttr().getDestinationMacRelevance()).getCodeValue()));
			uniObject.setRelevanceSourceIP(Integer.parseInt(UiUtil.getCodeById(portInst.getPortAttr().getPortUniAttr().getSourceIpRelevance()).getCodeValue()));
			uniObject.setRelevanceTargetIP(Integer.parseInt(UiUtil.getCodeById(portInst.getPortAttr().getPortUniAttr().getDestinationIpRelevance()).getCodeValue()));
			uniObject.setRelevancePw(Integer.parseInt(UiUtil.getCodeById(portInst.getPortAttr().getPortUniAttr().getPwRelevance()).getCodeValue()));
			uniObject.setRelevanceDSCP(Integer.parseInt(UiUtil.getCodeById(portInst.getPortAttr().getPortUniAttr().getDscpRelevance()).getCodeValue()));
			uniObject.setPortEnable(portInst.getIsEnabled_code());
			uniObject.setWorkPattern(Integer.parseInt(UiUtil.getCodeById(portInst.getPortAttr().getWorkModel()).getCodeValue()));
			uniObject.setBuffer802_3Enable(Integer.parseInt(UiUtil.getCodeById(portInst.getPortAttr().getFluidControl()).getCodeValue()));
			uniObject.setMtu(Integer.parseInt(portInst.getPortAttr().getMaxFrameSize()));
			uniObject.setMappingEnable(portInst.getPortAttr().getPortUniAttr().getMappingEnable());
			uniObject.setSourceTcpPortRelevance(portInst.getPortAttr().getPortUniAttr().getSourceTcpPortRelevance());
			uniObject.setEndTcpPortRelevance(portInst.getPortAttr().getPortUniAttr().getEndTcpPortRelevance());
			uniObject.setToSRelevance(portInst.getPortAttr().getPortUniAttr().getTosRelevance());
		} catch (Exception e) {
			throw e;
		} finally {

		}
	}

	@Override
	@SuppressWarnings("rawtypes")
	public String excutionDelete(List objectList) throws Exception {
		 
		return null;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		return null;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 与设备同步ETH端口
	 * 
	 * @author guoqc
	 * @param siteId
	 * @return
	 * @exception
	 */
	public Object synchro(int siteId) throws Exception {
		OperationObject operaObj = new OperationObject();
		OperationObject priOperationObject = new OperationObject();
		OperationObject prioperationObjectResult = null;
		try {
			operaObj = this.getSynchroOperationObject(siteId,"portSynchro");// 封装下发数据
			super.sendAction(operaObj);// 下发数据
			super.verification(operaObj);// 验证是否下发成功
			if (operaObj.isSuccess()) {
				/* 成功，则与数据库进行同步 */
				for (ActionObject actionObject : operaObj.getActionObjectList()) {
					this.synchro_db(actionObject.getProtObjectList(), siteId);
				}
			}
			priOperationObject = this.getSynchroOperationObject(siteId,"portPriSynchro");// 封装下发数据
			super.sendAction(priOperationObject);// 下发数据
			prioperationObjectResult = super.verification(priOperationObject);// 验证是否下发成功
			if (prioperationObjectResult.isSuccess()) {
				/* 成功，则与数据库进行同步 */
				for (ActionObject actionObject : priOperationObject.getActionObjectList()) {
					this.synchroPri_db(actionObject.getProtObjectList(), siteId);
				}
			}
			
			priOperationObject = this.getSynchroOperationObject(siteId,"portDiscardSynchro");// 封装下发数据
			super.sendAction(priOperationObject);// 下发数据
			prioperationObjectResult = super.verification(priOperationObject);// 验证是否下发成功
			if (prioperationObjectResult.isSuccess()) {
				/* 成功，则与数据库进行同步 */
				for (ActionObject actionObject : priOperationObject.getActionObjectList()) {
					this.synchroPortDiscard_db(actionObject.getEthServiceObject(), siteId);
				}
			}
			
			priOperationObject = this.getSynchroOperationObject(siteId,"port2LayerSynchro");// 封装下发数据
			super.sendAction(priOperationObject);// 下发数据
			prioperationObjectResult = super.verification(priOperationObject);// 验证是否下发成功
			if (prioperationObjectResult.isSuccess()) {
				/* 成功，则与数据库进行同步 */
				for (ActionObject actionObject : priOperationObject.getActionObjectList()) {
					this.synchroPort2Layer_db(actionObject.getPort2LayerObjectList(), siteId);
				}
			}
			
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operaObj = null;
		}
		return "超时";
	}

	private void synchroPort2Layer_db(List<Port2LayerObject> port2LayerObjectList, int siteId) {
		Port2LayerAttrService_MB service = null;
		try {
			service = (Port2LayerAttrService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT_2LAYER_ATTR);
			Port2LayerAttr condition = new Port2LayerAttr();
			condition.setSiteId(siteId);
			List<Port2LayerAttr> attrList = service.selectByCondition(condition);
			if(port2LayerObjectList.size() == attrList.size()){
				for (Port2LayerObject obj : port2LayerObjectList) {
					int portNumber = obj.getPortNumber();
					for (Port2LayerAttr attr : attrList) {
						if(portNumber == attr.getPortNumber()){
							CoderUtils.copy(obj,attr);
							service.saveOrUpdate(attr);
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
		}
	}

	private void synchroPortDiscard_db(EthServiceObject ethServiceObject, int siteId) {
		PortDiscardService_MB portDiscardService = null;
		List<PortDiscardInfo> portDiscardInfos = null;
		try {
			portDiscardService = (PortDiscardService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PortDiscardService);
			portDiscardInfos = portDiscardService.select(siteId);
			if(ethServiceObject.getEthServiceInfoObjectList().size()>0){
				if(portDiscardInfos.size()>0){
					PortDiscardInfo portDiscardInfo = portDiscardInfos.get(0);
					portDiscardInfo.setPortLine1(ethServiceObject.getEthServiceInfoObjectList().get(0).getPortLine1Object());
					portDiscardInfo.setPortLine2(ethServiceObject.getEthServiceInfoObjectList().get(0).getPortLine2Object());
					portDiscardInfo.setPortLine3(ethServiceObject.getEthServiceInfoObjectList().get(0).getPortLine3Object());
					portDiscardInfo.setPortLine4(ethServiceObject.getEthServiceInfoObjectList().get(0).getPortLine4Object());
					portDiscardService.update(portDiscardInfo);
				}else{
					PortDiscardInfo portDiscardInfo = new PortDiscardInfo();
					portDiscardInfo.setSiteId(siteId);
					portDiscardInfo.setPortLine1(ethServiceObject.getEthServiceInfoObjectList().get(0).getPortLine1Object());
					portDiscardInfo.setPortLine2(ethServiceObject.getEthServiceInfoObjectList().get(0).getPortLine2Object());
					portDiscardInfo.setPortLine3(ethServiceObject.getEthServiceInfoObjectList().get(0).getPortLine3Object());
					portDiscardInfo.setPortLine4(ethServiceObject.getEthServiceInfoObjectList().get(0).getPortLine4Object());
					portDiscardService.save(portDiscardInfo);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(portDiscardService);
		}
	}

	private void synchroPri_db(List<PortObject> protObjectList, int siteId) {
		List<PortInst> portInsts = null;
		PortService_MB portService = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			for(PortObject portObject : protObjectList){
				PortInst portInst = new PortInst();
				portInst.setSiteId(siteId);
				portInst.setNumber(portObject.getUniObject().getPortNumber());
				portInsts = portService.select(portInst);
				if(portInsts.size()>0){
					portInst = portInsts.get(0);
					portInst.setPriority(portObject.getPortSeniorConfig().getPri_Priority());
					portService.saveOrUpdate(portInst);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(portService);
		}
	}

	/**
	 * 与数据库进行同步
	 * 
	 * @param PortObjectList
	 * @param siteId
	 * @throws Exception
	 */
	private void synchro_db(List<PortObject> PortObjectList, int siteId) throws Exception {
		List<PortInst> protInfoList = null;
		PortService_MB portService = null;
		try {
			protInfoList = this.getportInfo(PortObjectList, siteId);
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			portService.initializtionSite(siteId);
			SynchroUtil synchroUtil = new SynchroUtil();
			for (PortInst portInst : protInfoList) {
				try {
					synchroUtil.updatePort(portInst);
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(portService);
			portService = null;
		}
	}

	/**
	 * 将设备信息封装到portInfo表中
	 * 
	 * @param PortObjectList
	 * @param siteId
	 * @return List<PortInst>
	 * @throws Exception
	 */
	private List<PortInst> getportInfo(List<PortObject> PortObjectList, int siteId) throws Exception {
		List<PortInst> protInfoList = new ArrayList<PortInst>();
		UniObject uniObj = null;
		PortInst portInst = null;
		for (PortObject portObject : PortObjectList) {
			try {
				uniObj = portObject.getUniObject();

				portInst = new PortInst();
				portInst.setSiteId(siteId);
				// 端口类型为uni
				if (null != uniObj) {
					if(uniObj.getPortMode() == 0){
						portInst.setPortType("NONE");
					}else if(uniObj.getPortMode() == 1){
						portInst.setPortType("UNI");
					}else{
						portInst.setPortType("NNI");
					}
					portInst.setNumber(uniObj.getPortNumber());
				}
				portInst.setPortAttr(this.getPortAttr(portObject, siteId));
				portInst.setIsEnabled_code(portObject.getUniObject().getPortEnable());
				portInst.setIsEnabled_QinQ(portObject.getUniObject().getQinqEnable());
				portInst.setIsEnabledLaser(portObject.getUniObject().getLaserEnable());
				portInst.setServicePort(portObject.getUniObject().getServicePort());
				//队列
				portInst.setQosQueues(getPortQosInfo(portObject.getPortSeniorConfig(),siteId));
				protInfoList.add(portInst);

				
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
		}
		return protInfoList;
	}

	/**
	 * 将设备信息封装到portLagInfo表中,注意端口类型(uni,nni,none),出口队列调度策略exportQueue(要转换成String)
	 * 
	 * @param PortObject
	 * @param siteId
	 * @return portLagInfo
	 * @throws Exception
	 */
	private PortAttr getPortAttr(PortObject PortObject, int siteId) throws Exception {
		PortAttr portAttr = null;
		UniObject uniObj = null;
		try {
			uniObj = PortObject.getUniObject();

			portAttr = new PortAttr();
			portAttr.setSiteId(siteId);

			if (null != uniObj) {
				portAttr.getPortUniAttr().setVlanRelevance((UiUtil.getCodeByValue("GUANLIAN", uniObj.getRelevanceVlan()+"")).getId());
				portAttr.getPortUniAttr().setEightIpRelevance((UiUtil.getCodeByValue("GUANLIAN", uniObj.getRelevance802_1P()+"")).getId());
				portAttr.getPortUniAttr().setSourceMacRelevance((UiUtil.getCodeByValue("GUANLIAN", uniObj.getRelevanceSourceMac()+"")).getId());
				portAttr.getPortUniAttr().setDestinationMacRelevance((UiUtil.getCodeByValue("GUANLIAN", uniObj.getRelevanceTargetMac()+"")).getId());
				portAttr.getPortUniAttr().setSourceIpRelevance((UiUtil.getCodeByValue("GUANLIAN", uniObj.getRelevanceSourceIP()+"")).getId());
				portAttr.getPortUniAttr().setDestinationIpRelevance((UiUtil.getCodeByValue("GUANLIAN", uniObj.getRelevanceTargetIP()+"")).getId());
				portAttr.getPortUniAttr().setPwRelevance((UiUtil.getCodeByValue("GUANLIAN", uniObj.getRelevancePw()+"")).getId());
				portAttr.getPortUniAttr().setDscpRelevance((UiUtil.getCodeByValue("GUANLIAN", uniObj.getRelevanceDSCP()+"")).getId());
				portAttr.setWorkModel((UiUtil.getCodeByValue("WORKMODE", uniObj.getWorkPattern()+"")).getId());
				portAttr.setFluidControl((UiUtil.getCodeByValue("ENABLEDSTATUE", uniObj.getBuffer802_3Enable()+"")).getId());
				portAttr.setMaxFrameSize(PortObject.getUniObject().getMtu()+"");
				portAttr.setExitLimit(PortObject.getPortSeniorConfig().getPortLimitation()+"");
				portAttr.getPortUniAttr().setBroadcast(PortObject.getPortSeniorConfig().getBroadcastFlux()+"");
				portAttr.getPortUniAttr().setIsBroadcast((UiUtil.getCodeByValue("VCTRAFFICPOLICING", PortObject.getPortSeniorConfig().getBroadcastBate()+"")).getId());
				portAttr.getPortUniAttr().setUnicast(PortObject.getPortSeniorConfig().getFloodFlux()+"");
				portAttr.getPortUniAttr().setIsUnicast((UiUtil.getCodeByValue("VCTRAFFICPOLICING", PortObject.getPortSeniorConfig().getFloodBate()+"")).getId());
				portAttr.getPortUniAttr().setMulticast(PortObject.getPortSeniorConfig().getGroupBroadcastFlux()+"");
				portAttr.getPortUniAttr().setIsMulticast((UiUtil.getCodeByValue("VCTRAFFICPOLICING", PortObject.getPortSeniorConfig().getGroupBroadcastBate()+"")).getId());
				portAttr.getPortUniAttr().setMappingEnable(uniObj.getMappingEnable());
				portAttr.getPortUniAttr().setSourceTcpPortRelevance(uniObj.getSourceTcpPortRelevance());
				portAttr.getPortUniAttr().setEndTcpPortRelevance(uniObj.getEndTcpPortRelevance());
				portAttr.getPortUniAttr().setTosRelevance(uniObj.getToSRelevance());
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return portAttr;
	}
	
	/**
	 * 同步Qos队列信息
	 * @param portSeniorConfig
	 * @param siteId
	 * @return
	 */
	private List<QosQueue> getPortQosInfo(PortSeniorConfig portSeniorConfig,int siteId){
		
		List<QosQueue> qosQueueList = new ArrayList<QosQueue>();
		try {
			if(portSeniorConfig != null && portSeniorConfig.getExitQueueList()!=null){
				for(int i =0;i<portSeniorConfig.getExitQueueList().size();i++){
					QosQueue qosQueue = new QosQueue();
					qosQueue.setServiceId(0);
					qosQueue.setSiteId(siteId);
					qosQueue.setObjType("SECTION");
					qosQueue.setQueueType(portSeniorConfig.getExitQueueList().get(i).getMode()==1?"SP+DWRR":"SP");
					qosQueue.setCir(i);
					qosQueue.setCos(i);
					qosQueue.setMostBandwidth(ResourceUtil.srcStr(StringKeysObj.QOS_UNLIMITED));
					qosQueue.setWeight(portSeniorConfig.getExitQueueList().get(i).getPopedom());
					qosQueue.setDisCard(portSeniorConfig.getExitQueueList().get(i).getDiscardProbability());
					if(portSeniorConfig.getBufferList()!= null && portSeniorConfig.getBufferList().size()>0){
						qosQueue.setWredEnable(portSeniorConfig.getBufferList().get(i).getMode()==1?Boolean.TRUE:Boolean.FALSE);
						qosQueue.setGreenHighThresh(portSeniorConfig.getBufferList().get(i).getEndThreshold());
						qosQueue.setGreenLowThresh(portSeniorConfig.getBufferList().get(i).getStartThreshold());
					}
					qosQueueList.add(qosQueue);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return qosQueueList;
	}
	
	
	
//	/**
//	 * 封装下发数据
//	 * 
//	 * @param siteId
//	 * @return operationObject
//	 * @throws Exception
//	 */
//	private OperationObject getSynchroOperationObject(int siteId,String type) throws Exception {
//		OperationObject operationObject = null;
//		ActionObject actionObject = null;
//		NEObject neObject = null;
//		try {
//			WhImplUtil whImplUtil = new WhImplUtil();
//			operationObject = new OperationObject();
//			neObject = whImplUtil.siteIdToNeObject(siteId);
//			actionObject = new ActionObject();
//			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
//			actionObject.setNeObject(neObject);
//			actionObject.setType(type);
//			operationObject.getActionObjectList().add(actionObject);
//		} catch (Exception e) {
//			ExceptionManage.dispose(e,this.getClass());
//		} finally {
//			actionObject = null;
//		}
//		return operationObject;
//	}
	
	/**
	 * 返回设备数据
	 * @param siteId
	 * @return
	 */
	public List<PortInst> consistence(int siteId){
		List<PortInst> portInsts = null;
		OperationObject operaObj = null;
		try {
			operaObj = this.getSynchroOperationObject(siteId,"portSynchro");// 封装下发数据
			super.sendAction(operaObj);// 下发数据
			super.verification(operaObj);// 验证是否下发成功
			if (operaObj.isSuccess()) {
				/* 成功，则与数据库进行同步 */
				for (ActionObject actionObject : operaObj.getActionObjectList()) {
					portInsts = this.getportInfo(actionObject.getProtObjectList(), siteId);
					return portInsts;
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		portInsts = new ArrayList<PortInst>();
		return portInsts;
	}
}
