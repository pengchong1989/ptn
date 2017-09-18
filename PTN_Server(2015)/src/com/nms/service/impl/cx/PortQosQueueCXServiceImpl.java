package com.nms.service.impl.cx;

import java.util.List;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.port.PortLagInfo;
import com.nms.db.bean.ptn.qos.QosQueue;
import com.nms.db.enums.QosCosLevelEnum;
import com.nms.drivechenxiao.service.bean.porteth.EthPortObject;
import com.nms.model.ptn.qos.QosQueueService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.CXOperationBase;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class PortQosQueueCXServiceImpl extends CXOperationBase implements OperationServiceI {

	@SuppressWarnings("unchecked")
	@Override
	public String excutionDelete(List objectList) throws Exception {
		 
		return null;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		 
		return null;
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		PortInst portInst = null;
		QosQueueService_MB qosQueueService = null;
		OperationObject operationObject = null;
		String result = null;
		List<QosQueue> qosQueueList = null;
		PortLagInfo portLagInfo = null;
		try {
			if (object instanceof PortInst) {
				portInst = (PortInst) object;
				qosQueueList = portInst.getQosQueues();
			} else {
				portLagInfo = (PortLagInfo) object;
				qosQueueList = portLagInfo.getQosQueueList();
			}

			qosQueueService = (QosQueueService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosQueue);
			qosQueueService.saveOrUpdate(qosQueueList);

			operationObject = this.getOperationObject(object);

			super.sendAction(operationObject);
			operationObject = super.verification(operationObject);

			if (operationObject.isSuccess()) {
				result = operationObject.getCxActionObjectList().get(0).getStatus();
			} else {
				for (QosQueue qosQueue : qosQueueList) {
					qosQueueService.delete(qosQueue);
				}
				result = super.getErrorMessage(operationObject);
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(qosQueueService);
		}
		return result;
	}

	/**
	 * 获得OperationObject对象
	 * 
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	public OperationObject getOperationObject(Object object) throws Exception {
		CXActionObject cxActionObject = null;
		 PortInst portInst = null;
		OperationObject operationObject = null;
		PortLagInfo portLagInfo = null;
		try {
			operationObject = new OperationObject();

			cxActionObject = new CXActionObject();
			if (object instanceof PortInst) {
				portInst = (PortInst) object;
				cxActionObject.setCxNeObject(super.getCXNEObject(portInst.getSiteId()));
				cxActionObject.setEthPortObject(super.getPortObject(portInst, "SECTION", portInst.getQosQueues(), portInst.getPortType()));
			} else {
				portLagInfo=(PortLagInfo) object;
				cxActionObject.setCxNeObject(super.getCXNEObject(portLagInfo.getSiteId()));
				cxActionObject.setEthPortObject(this.getLagObject(portLagInfo, "LAG", portLagInfo.getQosQueueList()));
			}

			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setType(TypeAndActionUtil.TYPE_PORTQOS);
			cxActionObject.setAction(TypeAndActionUtil.ACTION_UPDATE);
			cxActionObject.setOamObject(null);
			operationObject.getCxActionObjectList().add(cxActionObject);

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			portInst = null;
			cxActionObject = null;
		}
		return operationObject;
	}
	
	/**
	 *  转换LAG的驱动层对象
	
	* @author kk
	
	* @param   
	
	* @return 
	 * @throws Exception 
	
	* @Exception 异常对象
	 */
	private EthPortObject getLagObject(PortLagInfo portLagInfo, String objtype, List<QosQueue> qosQueues) throws Exception{
		EthPortObject ethPortObject = null;

		if (portLagInfo == null) {
			throw new Exception("portLagInfo is null");
		}
		try {
			ethPortObject = new EthPortObject();
			ethPortObject.setName(portLagInfo.getLagID()+"");
			ethPortObject.setPortType("lag");

//			ethPortObject.setAdmin(portInst.getIsEnabled_code() == 0 ? "down" : "up");
//			ethPortObject.setNeType(this.getSiteType(portInst.getSiteId()));
			if (qosQueues == null) {
				return ethPortObject;
			}

			if (qosQueues.size() < 1) {
				ethPortObject.getBe().setYellowwredstart("64");
				ethPortObject.getAf1().setCir("0");
				ethPortObject.getAf2().setCir("0");
				ethPortObject.getAf3().setCir("0");
				ethPortObject.getAf4().setCir("0");
				ethPortObject.getEf().setCir("0");
				ethPortObject.getCs6().setCir("0");
				ethPortObject.getCs7().setCir("0");
			} else if (qosQueues.size() > 0) {
				for (QosQueue newqosQueue : qosQueues) {
					if (newqosQueue.getCos() == QosCosLevelEnum.BE.getValue()) {
						ethPortObject.getBe().setYellowwredstart(newqosQueue.getYellowLowThresh() + "");
					} else if (newqosQueue.getCos() == QosCosLevelEnum.AF1.getValue()) {
						ethPortObject.getAf1().setCir(newqosQueue.getCir() + "");
					} else if (newqosQueue.getCos() == QosCosLevelEnum.AF2.getValue()) {
						ethPortObject.getAf2().setCir(newqosQueue.getCir() + "");
					} else if (newqosQueue.getCos() == QosCosLevelEnum.AF3.getValue()) {
						ethPortObject.getAf3().setCir(newqosQueue.getCir() + "");
					} else if (newqosQueue.getCos() == QosCosLevelEnum.AF4.getValue()) {
						ethPortObject.getAf4().setCir(newqosQueue.getCir() + "");
					} else if (newqosQueue.getCos() == QosCosLevelEnum.EF.getValue()) {
						ethPortObject.getEf().setCir(newqosQueue.getCir() + "");
					} else if (newqosQueue.getCos() == QosCosLevelEnum.CS6.getValue()) {
						// if (this.getSiteType(portInst.getSiteId()).equals("cxt20a")) {
						// ethPortObject.getCs3().setCir(newqosQueue.getCir() + "");
						// } else {
						ethPortObject.getCs6().setCir(newqosQueue.getCir() + "");
						// }
					} else if (newqosQueue.getCos() == QosCosLevelEnum.CS7.getValue()) {
						ethPortObject.getCs7().setCir(newqosQueue.getCir() + "");
					}
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return ethPortObject;
	}
	

	@Override
	public Object synchro(int siteId) {
		return null;
	}
}
