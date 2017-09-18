package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;

import com.nms.db.bean.equipment.port.Port2LayerAttr;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.model.equipment.port.Port2LayerAttrService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.service.impl.wh.Port2LayerAttrWHServiceImpl;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class Port2LayerAttrDispatch extends DispatchBase implements DispatchInterface {

	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		return null;
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
	public String excuteUpdate(Object object) throws RemoteException, Exception {
		Port2LayerAttr port2LayerAttr = null;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		SiteService_MB siteService = null;
		Port2LayerAttrService_MB service = null;
		Port2LayerAttr Port2LayerAttr_before = null;
		try {
			port2LayerAttr = (Port2LayerAttr) object;
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck = (String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_UPDATE,
					port2LayerAttr);
			if(null == siteCheck){
				service = (Port2LayerAttrService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT_2LAYER_ATTR);
				Port2LayerAttr_before = service.selectByCondition(port2LayerAttr).get(0);
				service.saveOrUpdate(port2LayerAttr);
				operationServiceI = new Port2LayerAttrWHServiceImpl();
				result = operationServiceI.excutionUpdate(object);
			}else{
				siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
				SiteInst site = siteService.select(port2LayerAttr.getSiteId());
				if(site != null){
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+
					site.getCellId()+ResultString.NOT_ONLINE_SUCCESS;
				}
			}
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			} else {
				service.saveOrUpdate(Port2LayerAttr_before);
			}
		} catch (Exception e) {
			//回滚
			service.saveOrUpdate(Port2LayerAttr_before);
			ExceptionManage.dispose(e,this.getClass());
		} finally{
			UiUtil.closeService_MB(siteService);
			UiUtil.closeService_MB(service);
		}
		return result;
	}

	@Override
	public String synchro(int siteId) throws RemoteException, Exception {
		return null;
	}

}
