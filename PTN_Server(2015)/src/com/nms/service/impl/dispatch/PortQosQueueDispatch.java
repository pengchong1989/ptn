package com.nms.service.impl.dispatch;

import java.util.List;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.qos.QosQueue;
import com.nms.db.enums.EManufacturer;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.service.impl.wh.PortQosQueueWHServiceImpl;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class PortQosQueueDispatch extends DispatchBase {

	public String excuteUpdate(List<QosQueue> qosQueueList) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);

		try {
			if (qosQueueList == null) {
				throw new Exception("acPortInfo is null");
			}
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			List<QosQueue> QosQueueList = ((PortInst) qosQueueList).getQosQueues();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_SAVEANDUPDATE,QosQueueList);
			if(null!=siteCheck){
				return siteCheck;
			}	
			manufacturer = super.getManufacturer(qosQueueList.get(0).getSiteId());

			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				operationServiceI = new PortQosQueueWHServiceImpl();
			} else {
				// operationServiceI = new PortQosQueueWHServiceImpl();
			}
			result=operationServiceI.excutionUpdate(qosQueueList);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		} else {
			return result;
		}
	}

	public String excuteInsert(List<QosQueue> qosQueueList) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);

		try {
			if (qosQueueList == null) {
				throw new Exception("qosQueueList is null");
			}
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			List<QosQueue> QosQueueList= ((PortInst) qosQueueList).getQosQueues();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_SAVEANDUPDATE,QosQueueList);
			if(null!=siteCheck){
				return siteCheck;
			}	
			manufacturer = super.getManufacturer(qosQueueList.get(0).getSiteId());

			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				operationServiceI = new PortQosQueueWHServiceImpl();
			} else {
				// operationServiceI = new PortQosQueueWHServiceImpl();
			}
			result=operationServiceI.excutionInsert(qosQueueList);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		} else {
			return result;
		}
	}

	@SuppressWarnings("unchecked")
	public String excutionDelete(List objectList) throws Exception {
		return null;

	}
}
