package com.nms.service.impl.wh;

import java.util.List;
import java.util.Map;

import com.nms.db.bean.equipment.port.PortDiscardInfo;
import com.nms.drive.service.bean.EthServiceInfoObject;
import com.nms.drive.service.bean.EthServiceObject;
import com.nms.drive.service.bean.NEObject;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.qos.QosQueueService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.ActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.WHOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.UiUtil;

public class PortDiscardWHServiceImpl extends WHOperationBase implements OperationServiceI {

	private String type = "";

	/**
	 * 修改
	 */
	@Override
	public String excutionUpdate(Object object) throws Exception {
		PortDiscardInfo portDiscardInfo = null;
		PortService_MB portService = null;
		Map<Integer, ActionObject> operationObjectBefore = null;
		OperationObject operationObjectResult = null;
		QosQueueService_MB qosQueueService = null;
		try {
			portDiscardInfo = (PortDiscardInfo) object;
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			qosQueueService = (QosQueueService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosQueue);
			
			SiteUtil siteUtil=new SiteUtil();
			if(siteUtil.SiteTypeUtil(portDiscardInfo.getSiteId()) == 0){
				OperationObject operationObject = this.getSendOperation(portDiscardInfo);
				super.sendAction(operationObject);

				operationObjectResult = super.verification(operationObject);
				if (operationObjectResult.isSuccess()) {
					
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				} else {
					boolean flag = super.rollback(operationObjectResult, operationObjectBefore);
					if (flag) {
						System.out.println("回滚成功");
					} else {
						System.out.println("回滚失败");
					}
					return super.getErrorMessage(operationObjectResult);
				}
			}else{
				return ResultString.CONFIG_SUCCESS;
			}
		} catch (Exception e) {
			throw e;
		} finally {
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
	private OperationObject getSendOperation(PortDiscardInfo portDiscardInfo) throws Exception {
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;

		try {
			operationObject = new OperationObject();
			WhImplUtil whImplUtil = new WhImplUtil();
			neObject = whImplUtil.siteIdToNeObject(portDiscardInfo.getSiteId());
			actionObject = new ActionObject();
			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
			actionObject.setNeObject(neObject);
			actionObject.setType("portDiscard");
			actionObject.setEthServiceObject(getEthServiceObject(portDiscardInfo));
			operationObject.getActionObjectList().add(actionObject);

		} catch (Exception e) {
			throw e;
		} finally {
			actionObject = null;
			neObject = null;
		}
		return operationObject;
	}

	private EthServiceObject getEthServiceObject(PortDiscardInfo portDiscardInfo){
		EthServiceObject ethServiceObject = new EthServiceObject();
		EthServiceInfoObject ethServiceInfoObject = new EthServiceInfoObject();
		ethServiceInfoObject.setPortLine1Object(portDiscardInfo.getPortLine1());
		ethServiceInfoObject.setPortLine2Object(portDiscardInfo.getPortLine2());
		ethServiceInfoObject.setPortLine3Object(portDiscardInfo.getPortLine3());
		ethServiceInfoObject.setPortLine4Object(portDiscardInfo.getPortLine4());
		ethServiceObject.getEthServiceInfoObjectList().add(ethServiceInfoObject);
		return ethServiceObject;
	}


	@Override
	@SuppressWarnings("rawtypes")
	public String excutionDelete(List objectList) throws Exception {
		 
		return null;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		PortDiscardInfo portDiscardInfo = null;
		PortService_MB portService = null;
		Map<Integer, ActionObject> operationObjectBefore = null;
		OperationObject operationObjectResult = null;
		QosQueueService_MB qosQueueService = null;
		try {
			portDiscardInfo = (PortDiscardInfo) object;
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			qosQueueService = (QosQueueService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosQueue);
			
			SiteUtil siteUtil=new SiteUtil();
			if(siteUtil.SiteTypeUtil(portDiscardInfo.getSiteId()) == 0){
				OperationObject operationObject = this.getSendOperation(portDiscardInfo);
				super.sendAction(operationObject);

				operationObjectResult = super.verification(operationObject);
				if (operationObjectResult.isSuccess()) {
					
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				} else {
					boolean flag = super.rollback(operationObjectResult, operationObjectBefore);
					if (flag) {
						System.out.println("回滚成功");
					} else {
						System.out.println("回滚失败");
					}
					return super.getErrorMessage(operationObjectResult);
				}
			}else{
				return ResultString.CONFIG_SUCCESS;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(portService);
			UiUtil.closeService_MB(qosQueueService);
		}
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
		return "超时";
	}

}
