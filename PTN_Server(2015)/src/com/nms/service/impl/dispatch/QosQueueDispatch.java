package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.port.PortLagInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.model.ptn.qos.QosQueueService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.PortQosQueueCXServiceImpl;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.service.impl.wh.PortWHServiceImpl;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class QosQueueDispatch extends DispatchBase implements DispatchInterface{
	public String excuteUpdate(Object object) throws Exception{
		PortInst portInst=null;
		int manufacturer=0;
		OperationServiceI operationServiceI=null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		PortLagInfo portLagInfo=null;
		QosQueueService_MB qosQueueService = null;
		boolean isPortInst = false;//是否为端口
		try {
			
			
			if(object instanceof PortInst){
				portInst=(PortInst) object;
				isPortInst = true;
				//虚拟网元操作
				SiteUtil siteUtil = new SiteUtil();
				String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_SAVEANDUPDATE,portInst.getQosQueues());
				if(null!=siteCheck){
					return siteCheck;
				}
				manufacturer=super.getManufacturer(portInst.getSiteId());
			}else{
				portLagInfo=(PortLagInfo) object;
				//虚拟网元操作
				SiteUtil siteUtil = new SiteUtil();
				String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_SAVEANDUPDATE,portLagInfo.getQosQueueList());
				if(null!=siteCheck){
					return siteCheck;
				}
				manufacturer=super.getManufacturer(portLagInfo.getSiteId());
			}
			if(manufacturer==EManufacturer.WUHAN.getValue()){
				if(isPortInst){
					qosQueueService = (QosQueueService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosQueue);
					qosQueueService.saveOrUpdate(((PortInst) object).getQosQueues());
					operationServiceI=new PortWHServiceImpl();
				}
				
			}else{
				operationServiceI = new PortQosQueueCXServiceImpl();
			}
			result= operationServiceI.excutionUpdate(object);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally{
			UiUtil.closeService_MB(qosQueueService);
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		} else {
			return result;
		}
	}

	@Override
	public String excuteDelete(Object object) throws RemoteException, Exception {
		return null;
	}

	@Override
	public String excuteInsert(Object object) throws RemoteException, Exception {
		return null;
	}

	@Override
	public String synchro(int siteId) throws RemoteException, Exception {
		return null;
	}

	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
