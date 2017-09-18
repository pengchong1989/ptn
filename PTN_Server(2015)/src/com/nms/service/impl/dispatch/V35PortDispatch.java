package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;

import com.nms.db.bean.equipment.port.V35PortInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.model.equipment.port.V35PortService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.OamCXServiceImpl;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.service.impl.wh.TmsOamWHServiceImpl;
import com.nms.service.impl.wh.V35PortWHServiceImpl;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class V35PortDispatch extends DispatchBase implements DispatchInterface {
	
	@SuppressWarnings("unchecked")
	public String excuteDelete(Object object) throws Exception {
		return "";
	}

	public String excuteInsert(Object object) throws Exception {
		V35PortInfo v35PortInfo = null;
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		V35PortService_MB v35PortService = null;
		try {
			v35PortInfo = (V35PortInfo) object;
			v35PortService = (V35PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.V35PORT);
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_SAVEANDUPDATE,v35PortInfo);
			if(null!=siteCheck){
				return siteCheck;
			}
			manufacturer = super.getManufacturer(v35PortInfo.getSiteId());
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				operationServiceI = new V35PortWHServiceImpl();
			} else {
				operationServiceI = new OamCXServiceImpl();
			}
			result = operationServiceI.excutionInsert(v35PortInfo);
			
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				v35PortService.saveOrUpdate(v35PortInfo);
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			} else {
				return result;
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			operationServiceI = null;
			manufacturer = 0;
			operationServiceI = null;
			UiUtil.closeService_MB(v35PortService);
		}
		
	}

	public String excuteUpdate(Object object) throws Exception {
		V35PortInfo v35PortInfo = null;
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		V35PortService_MB v35PortService = null;
		try {
			v35PortInfo = (V35PortInfo) object;
			v35PortService = (V35PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.V35PORT);
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_SAVEANDUPDATE,v35PortInfo);
			if(null!=siteCheck){
				return siteCheck;
			}
			
			manufacturer = super.getManufacturer(v35PortInfo.getSiteId());
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				operationServiceI = new V35PortWHServiceImpl();
			} else {
				operationServiceI = new OamCXServiceImpl();
			}
			result = operationServiceI.excutionUpdate(v35PortInfo);
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				v35PortService.saveOrUpdate(v35PortInfo);
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			} else {
				return result;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(v35PortService);
			operationServiceI = null;
			manufacturer = 0;
			operationServiceI = null;
		}
		
	}

	@Override
	public String synchro(int siteId) throws RemoteException, Exception {
		OperationServiceI operationServiceI = null;
		try {
			//虚拟网元操作
			SiteUtil siteUtil=new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteSynchroAction(TypeAndActionUtil.ACTION_SYNCHRO,siteId);
			if(null!=siteCheck){
				return siteCheck;
			}
			if (super.getManufacturer(siteId) == EManufacturer.WUHAN.getValue()) {
				operationServiceI = new TmsOamWHServiceImpl();
			} else {
//				operationServiceI = new TunnelCXServiceImpl();
			}
			return (String)operationServiceI.synchro(siteId);

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return ResultString.QUERY_FAILED;
	}

	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
}