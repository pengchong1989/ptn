package com.nms.service.impl.cx;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.path.Segment;
import com.nms.db.bean.ptn.qos.QosQueue;
import com.nms.db.enums.EManufacturer;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.porteth.EthPortObject;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.path.SegmentService_MB;
import com.nms.model.ptn.qos.QosQueueService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.CXOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.SynchroUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class SegmentCXServiceImpl extends CXOperationBase implements OperationServiceI {
	private List<SiteInst> siteInst = new ArrayList<SiteInst>();

	public SegmentCXServiceImpl() {

	}

	public SegmentCXServiceImpl(List<SiteInst> siteInst) {
		this.siteInst = siteInst;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String excutionDelete(List objectList) throws Exception {
		List<Segment> segments = null;
		OperationObject operationObject = null;
		String errorMessage = null;
		try {
			segments = objectList;
			operationObject = this.getMoreOperationObject(segments, TypeAndActionUtil.ACTION_DELETE);

			if (operationObject.getCxActionObjectList().size() > 0) {
				super.sendAction(operationObject);

				operationObject = super.verification(operationObject);
				if (operationObject.isSuccess()) {
					errorMessage = operationObject.getCxActionObjectList().get(0).getStatus();
				} else {
					errorMessage = super.getErrorMessage(operationObject);
					operationObject.setCxActionObjectList(super.getSuccess(operationObject, TypeAndActionUtil.ACTION_INSERT));
					super.sendAction(operationObject);
				}
			} else {
				errorMessage = ResultString.CONFIG_SUCCESS;
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			segments = null;
			operationObject = null;
		}
		return errorMessage;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		Segment segment = null;
		PortInst aPortInst = null;
		PortInst zPortInst = null;
		PortService_MB portService = null;
		OperationObject operationObject = null;
		Map<Integer, List<QosQueue>> qosMap = null;
		List<QosQueue> qosQueues = null;
		String errorMessage = ResultString.CONFIG_SUCCESS;;
		try {
			segment = (Segment) object;
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			if(0!=segment.getAPORTID()){
				aPortInst = new PortInst();
				aPortInst.setPortId(segment.getAPORTID());
				List<PortInst> aPortList = portService.select(aPortInst);
				aPortInst = aPortList.get(0);
			}
			if(0!=segment.getZPORTID()){
				zPortInst = new PortInst();
				zPortInst.setPortId(segment.getZPORTID());
				List<PortInst> zPortList = portService.select(zPortInst);
				zPortInst = zPortList.get(0);
			}
			qosMap = segment.getQosMap();
			if (qosMap.values() != null && qosMap.values().size() > 0) {
				qosQueues = new ArrayList<QosQueue>();
				for (List<QosQueue> qosQueue : qosMap.values()) {
					if (qosQueue != null && qosQueue.size() > 0)
						qosQueues.addAll(qosQueue);
				}
			}
			// result = segmentService.saveOrUpdate(segment, qosQueues, segment.getOamList());
			// segment.setId(result);
			operationObject = this.getOperationObject(TypeAndActionUtil.ACTION_INSERT, aPortInst, zPortInst, segment, qosQueues);
			if (operationObject.getCxActionObjectList().size() == 0) {
				errorMessage = ResultString.CONFIG_SUCCESS;
			} else {
				super.sendAction(operationObject);
				operationObject = super.verification(operationObject);
				if (operationObject.isSuccess()) {
					errorMessage = operationObject.getCxActionObjectList().get(0).getStatus();
				} else {
					errorMessage = super.getErrorMessage(operationObject);
					operationObject.setCxActionObjectList(super.getSuccess(operationObject, TypeAndActionUtil.ACTION_DELETE));
					super.sendAction(operationObject);
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(portService);
			segment = null;
			aPortInst = null;
			zPortInst = null;
			operationObject = null;
			qosMap = null;
			qosQueues = null;
		}
		return errorMessage;
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		Segment segment = null;
		PortInst aPortInst = null;
		PortInst zPortInst = null;
		PortService_MB portService = null;
		OperationObject operationObject = null;
		Map<Integer, List<QosQueue>> qosMap = null;
		List<QosQueue> qosQueues = null;
		String resultStr = null;
		try {
			segment = (Segment) object;
			aPortInst = new PortInst();
			zPortInst = new PortInst();
			aPortInst.setPortId(segment.getAPORTID());
			zPortInst.setPortId(segment.getZPORTID());
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			List<PortInst> aPortList = portService.select(aPortInst);
			List<PortInst> zPortList = portService.select(zPortInst);
			aPortInst = aPortList.get(0);
			zPortInst = zPortList.get(0);

			qosMap = segment.getQosMap();

			if (qosMap.values() != null && qosMap.values().size() > 0) {
				qosQueues = new ArrayList<QosQueue>();
				for (List<QosQueue> qosQueue : qosMap.values()) {
					if (qosQueue != null && qosQueue.size() > 0)
						qosQueues.addAll(qosQueue);
				}
			}
			operationObject = this.getOperationObject(TypeAndActionUtil.ACTION_UPDATE, aPortInst, zPortInst, segment, qosQueues);
			if(operationObject.getCxActionObjectList().size() > 0){
				super.sendAction(operationObject);
				operationObject = super.verification(operationObject);
				if (operationObject.isSuccess()) {
					resultStr = operationObject.getCxActionObjectList().get(0).getStatus();
				} else {
					resultStr = super.getErrorMessage(operationObject);
				}
			}else{
				resultStr = ResultString.CONFIG_SUCCESS;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			segment = null;
			aPortInst = null;
			zPortInst = null;
			UiUtil.closeService_MB(portService);
			operationObject = null;
			qosMap = null;
			qosQueues = null;
		}
		return resultStr;
	}

	/**
	 * 把Segment转换成OperationObject
	 * 
	 * @param tunnel
	 *            tunnel对象
	 * @param action
	 *            动作
	 * @return
	 * @throws Exception
	 */
	private OperationObject getOperationObject(String action, PortInst aportInst, PortInst zportInst, Segment segment, List<QosQueue> qosQueues) throws Exception {
/*		if (null == aportInst) {
			throw new Exception("aportInst is null");
		} else if (null == zportInst) {
			throw new Exception("zportInst is null");
		}*/

		OperationObject operationObject = null;
		try {
			operationObject = new OperationObject();
			SiteUtil siteUtil=new SiteUtil();
			if (aportInst != null) {
				if (EManufacturer.CHENXIAO.getValue() == super.getManufacturer(aportInst.getSiteId())&&0==siteUtil.SiteTypeUtil(aportInst.getSiteId())) {
					operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, action, aportInst, zportInst, "a", segment, qosQueues));
				}
			}
			if (zportInst != null) {
				if (EManufacturer.CHENXIAO.getValue() == super.getManufacturer(zportInst.getSiteId())&&0==siteUtil.SiteTypeUtil(zportInst.getSiteId())) {
					operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, action, aportInst, zportInst, "z", segment, qosQueues));
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
		}
		return operationObject;
	}

	private OperationObject getMoreOperationObject(List<Segment> segments, String action) throws Exception {
		PortInst aPortInst = null;
		PortInst zPortInst = null;
		PortService_MB portService = null;
		OperationObject operationObject = null;
		try {
			operationObject = new OperationObject();
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			SiteUtil siteUtil=new SiteUtil();
			for (Segment segment : segments) {
				if(0!=segment.getAPORTID()){
					aPortInst = new PortInst();
					aPortInst.setPortId(segment.getAPORTID());
					List<PortInst> aPortList = portService.select(aPortInst);
					aPortInst = aPortList.get(0);;
				}
				if(0!=segment.getZPORTID()){
					zPortInst = new PortInst();
					zPortInst.setPortId(segment.getZPORTID());
					List<PortInst> zPortList = portService.select(zPortInst);
					zPortInst = zPortList.get(0);
				}
				if (aPortInst != null) {
					if (EManufacturer.CHENXIAO.getValue() == super.getManufacturer(aPortInst.getSiteId())&&0==siteUtil.SiteTypeUtil(aPortInst.getSiteId())) {
						operationObject.getCxActionObjectList().add(this.getMoreCXActionObject(operationObject, action, aPortInst, zPortInst, "a", segment));
					}
				}
				if (zPortInst != null) {
					if (EManufacturer.CHENXIAO.getValue() == super.getManufacturer(zPortInst.getSiteId())&&0==siteUtil.SiteTypeUtil(zPortInst.getSiteId())) {
						operationObject.getCxActionObjectList().add(this.getMoreCXActionObject(operationObject, action, aPortInst, zPortInst, "z", segment));
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			aPortInst = null;
			zPortInst = null;
			UiUtil.closeService_MB(portService);
		}
		return operationObject;
	}

	private CXActionObject getCXActionObject(OperationObject operationObject, String action, PortInst aportInst, PortInst zportInst, String sitetype, Segment segment, List<QosQueue> qosQueues) throws Exception {
		CXActionObject cxActionObject = null;
		int siteId = 0;
		CXNEObject cxneObject = null;
		try {
			cxActionObject = new CXActionObject();
			if (sitetype.equals("a")) {
				siteId = aportInst.getSiteId();
				cxneObject = super.getCXNEObject(siteId);
				cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
				cxActionObject.setCxNeObject(cxneObject);
				cxActionObject.setAction(action);
				cxActionObject.setType(TypeAndActionUtil.TYPE_SEGMENT);
				cxActionObject.setOamObject(super.convertOamObject_mep("SECTION", segment.getId(), siteId, segment.getOamList(), action));
				cxActionObject.setEthPortObject(super.getEthPortObject(aportInst, aportInst.getPortType().toLowerCase(), "SECTION", action, qosQueues));
			} else if (sitetype.equals("z")) {
				siteId = zportInst.getSiteId();
				cxneObject = super.getCXNEObject(siteId);
				cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
				cxActionObject.setCxNeObject(cxneObject);
				cxActionObject.setAction(action);
				cxActionObject.setType(TypeAndActionUtil.TYPE_SEGMENT);
				cxActionObject.setOamObject(super.convertOamObject_mep("SECTION", segment.getId(), siteId, segment.getOamList(), action));
				cxActionObject.setEthPortObject(super.getEthPortObject(zportInst, zportInst.getPortType().toLowerCase(), "SECTION", action, qosQueues));
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			siteId = 0;
			cxneObject = null;
		}
		return cxActionObject;
	}

	private CXActionObject getMoreCXActionObject(OperationObject operationObject, String action, PortInst aportInst, PortInst zportInst, String sitetype, Segment segment) throws Exception {
		CXActionObject cxActionObject = null;
		int siteId = 0;
		CXNEObject cxneObject = null;
		try {
			cxActionObject = new CXActionObject();
			if (sitetype.equals("a")) {
				siteId = aportInst.getSiteId();
				cxneObject = super.getCXNEObject(siteId);
				cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
				cxActionObject.setCxNeObject(cxneObject);
				cxActionObject.setAction(action);
				cxActionObject.setType(TypeAndActionUtil.TYPE_SEGMENT);
				cxActionObject.setOamObject(super.convertOamObject_mep("SECTION", segment.getId(), siteId, segment.getOamList(), action));
				cxActionObject.setEthPortObject(super.getPortObject(aportInst, "SECTION", aportInst.getQosQueues(), aportInst.getPortType().toLowerCase()));
			} else if (sitetype.equals("z")) {
				siteId = zportInst.getSiteId();
				cxneObject = super.getCXNEObject(siteId);
				cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
				cxActionObject.setCxNeObject(cxneObject);
				cxActionObject.setAction(action);
				cxActionObject.setType(TypeAndActionUtil.TYPE_SEGMENT);
				cxActionObject.setOamObject(super.convertOamObject_mep("SECTION", segment.getId(), siteId, segment.getOamList(), action));
				cxActionObject.setEthPortObject(super.getPortObject(zportInst, "SECTION", zportInst.getQosQueues(), zportInst.getPortType().toLowerCase()));
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			siteId = 0;
			cxneObject = null;
		}
		return cxActionObject;
	}

	/**
	 * 同步
	 */
	@Override
	public Object synchro(int siteId) throws Exception {
		OperationObject operationObject = new OperationObject();
		CXActionObject cxActionObject = new CXActionObject();
		try {
//			portCXServiceImpl = new PortCXServiceImpl();
//			portCXServiceImpl.synchro(siteId);
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setAction(TypeAndActionUtil.ACTION_SELECT);
			cxActionObject.setType(TypeAndActionUtil.TYPE_PORT);
			cxActionObject.setCxNeObject(super.getCXNEObject(siteId));
			operationObject.getCxActionObjectList().add(cxActionObject);

			super.sendAction(operationObject);
			super.verification(operationObject);

			if (operationObject.isSuccess()) {
				synchro_db(operationObject.getCxActionObjectList().get(0).getEthPortObjectList(), siteId);
			}

		} catch (Exception e) {
			throw e;
		}

		return null;
	}

	private void synchro_db(List<EthPortObject> ethPortList, int siteId) throws Exception {
		try {
			getSegmentList(getHaveNbrEthPort(ethPortList), siteId);
		} catch (Exception e) {
			throw e;
		}
	}

	private void getSegmentList(List<EthPortObject> list, int siteId) throws Exception {
		Segment segment = null;
		PortInst portInstA = null;
		PortInst portInstZ = null;
		int aSiteId = 0;
		int zSiteId = 0;
		String aPortName = "";
		String zPortName = "";
		PortCXServiceImpl portCXServiceImpl = null;
		SegmentService_MB segmentService=null;
		try {
			//先同步端口
			portCXServiceImpl = new PortCXServiceImpl();
			portCXServiceImpl.synchro_db(list, siteId);
			
			for (EthPortObject ethPortObject : list) {
				//验证对端网元是否在此次搜索集合中
				boolean have = false;
				for (SiteInst siteInst : this.siteInst) {
					if (ethPortObject.getNbr().getNeid().equals(siteInst.getCellDescribe())) {
						have = true;
						break;
					} else {
						continue;
					}
				}
				//如果匹配到，组织对象，与数据库比较
				if (have) {
					segment = new Segment();
					aPortName = ethPortObject.getName();
					zPortName = ethPortObject.getNbr().getIfidx();
					aSiteId = siteId;
					if (super.getSiteByIp(ethPortObject.getNbr().getNeid() + "") == null) {
						return;
					} else {
						zSiteId = super.getSiteByIp(ethPortObject.getNbr().getNeid() + "").getSite_Inst_Id();
					}

					portInstA = new PortInst();
					portInstA.setSiteId(aSiteId);
					portInstA.setPortName(aPortName);
					portInstA=getPortId(portInstA);
					
					if(null == portInstA){
						continue;
					}
					
					segment.setAPORTID(portInstA.getPortId());
					segment.setASITEID(siteId);

					portInstZ = new PortInst();
					portInstZ.setSiteId(zSiteId);
					portInstZ.setPortName(zPortName);
					portInstZ=getPortId(portInstZ);
					
					if(null == portInstZ){
						continue;
					}
					
					segment.setZPORTID(portInstZ.getPortId());
					segment.setZSITEID(portInstZ.getSiteId());

					segment.setNAME(super.getSiteAdress(siteId) + "/" + ethPortObject.getName() + "-" + ethPortObject.getNbr().getNeid() + "/" + ethPortObject.getNbr().getIfidx());
					//					List<OamInfo> list2 = convertOamInfo_mep(ethPortObject.getOam());
					//					list2.get(0).getOamMep().setObjType(EServiceType.SECTION.toString());
					//					list2.get(0).getOamMep().setObjId(objId);
					//验证端口A和Z的速率是否相同
					segmentService = (SegmentService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT);
					if(segmentService.comparePortSpeed(portInstA, portInstZ)){
						SynchroUtil.segmentSynchro(segment, getQosQueue(segment), null);
					}
				} else {
					continue;
				}
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(segmentService);
		}
	}

	private List<QosQueue> getQosQueue(Segment segment) throws Exception {
		
		List<QosQueue> list = new ArrayList<QosQueue>();
		QosQueueService_MB qosQueueService = null;
		try {
			qosQueueService = (QosQueueService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosQueue);
			
			QosQueue qosQueue = new QosQueue();
			qosQueue.setObjType("SECTION");
			qosQueue.setObjId(segment.getAPORTID());
			list.addAll(qosQueueService.queryByCondition(qosQueue));
			QosQueue qosQueue1 = new QosQueue();
			qosQueue1.setObjType("SECTION");
			qosQueue1.setObjId(segment.getZPORTID());
			list.addAll(qosQueueService.queryByCondition(qosQueue1));
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(qosQueueService);
		}
		return list;
	}

	private List<EthPortObject> getHaveNbrEthPort(List<EthPortObject> ethPortList) {
		List<EthPortObject> nbrEthPortList = new ArrayList<EthPortObject>();
		for (EthPortObject portObject : ethPortList) {
			if (portObject.getNbr().getStat().equals("1")) {
				nbrEthPortList.add(portObject);
			}
		}
		return nbrEthPortList;
	}

	private PortInst getPortId(PortInst portInst) throws Exception {
		List<PortInst> portList = new ArrayList<PortInst>();
		PortService_MB portService = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			
			portList = portService.select(portInst);
			if(portList.size()==0){
				return null;
			}else{
				return portList.get(0);			
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(portService);
		}
	}
}
