package com.nms.service.impl.wh;


import java.util.List;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.qos.QosQueue;
import com.nms.model.ptn.qos.QosQueueService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.WHOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.UiUtil;

public class PortQosQueueWHServiceImpl extends WHOperationBase implements OperationServiceI {
	

	
	@SuppressWarnings("unchecked")
	@Override
	public String excutionDelete(List objectList) throws Exception {
		
		return null;
		
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		List<QosQueue> qosQueueList = null;
		QosQueueService_MB qosQueueService = null;
		PortInst portInst = null;
		try {
			portInst = (PortInst) object;
			qosQueueList = portInst.getQosQueues();
			qosQueueService = (QosQueueService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosQueue);
			qosQueueService.saveOrUpdate(qosQueueList);
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(qosQueueService);
		}
		return ResultString.CONFIG_SUCCESS;
		
		
	}
	
	@Override
	public String excutionUpdate(Object object) throws Exception {
		PortInst portInst = null;
		List<QosQueue> qosQueueList = null;
		QosQueueService_MB qosQueueService = null;
		try {
			portInst = (PortInst) object;
			qosQueueList = portInst.getQosQueues();
			qosQueueService = (QosQueueService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosQueue);
			qosQueueService.saveOrUpdate(qosQueueList);
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(qosQueueService);
		}
		return ResultString.CONFIG_SUCCESS;
		
	}

	@Override
	public Object synchro(int siteId) {
		return null;
	}
}
