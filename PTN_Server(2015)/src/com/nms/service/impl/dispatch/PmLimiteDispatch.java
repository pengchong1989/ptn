package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;

import com.nms.db.bean.perform.PmValueLimiteInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.model.perform.PmLimiteService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.service.impl.wh.PmLimiteWHServiceImpl;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class PmLimiteDispatch extends DispatchBase implements DispatchInterface{

	@Override
	public String excuteDelete(Object object) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String excuteInsert(Object object) throws RemoteException, Exception {
		PmValueLimiteInfo pmValueLimiteInfo=null;
		int manufacturer=0;
		OperationServiceI operationServiceI=null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		PmLimiteService_MB pmLimiteService = null;
		try {
			pmValueLimiteInfo=(PmValueLimiteInfo) object;
			
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck = (String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_INSERT,pmValueLimiteInfo);
			if(null!=siteCheck){
				return ResultString.OFF_LINE_SUCCESS;
			}
			pmLimiteService = (PmLimiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PmLimiteService);
			manufacturer=super.getManufacturer(pmValueLimiteInfo.getSiteId());
			if(manufacturer==EManufacturer.WUHAN.getValue()){
				operationServiceI=new PmLimiteWHServiceImpl();
			}else{
				
			}
			result = operationServiceI.excutionInsert(pmValueLimiteInfo);
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				pmLimiteService.saveOrUpdate(pmValueLimiteInfo);
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			} else {
				return result;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally{
			UiUtil.closeService_MB(pmLimiteService);
		}		
		
		return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
	}

	@Override
	public String excuteUpdate(Object object) throws RemoteException, Exception {
		PmValueLimiteInfo pmValueLimiteInfo=null;
		int manufacturer=0;
		OperationServiceI operationServiceI=null;
		String result =  ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		PmLimiteService_MB pmLimiteService = null;
		try {
			pmValueLimiteInfo=(PmValueLimiteInfo) object;
			
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck = (String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_UPDATE,pmValueLimiteInfo);
			if(null!=siteCheck){
				return ResultString.OFF_LINE_SUCCESS;
			}
			pmLimiteService = (PmLimiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PmLimiteService);
			manufacturer=super.getManufacturer(pmValueLimiteInfo.getSiteId());
			if(manufacturer==EManufacturer.WUHAN.getValue()){
				operationServiceI=new PmLimiteWHServiceImpl();
			}else{
				
			}
			result = operationServiceI.excutionUpdate(object);
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				pmLimiteService.saveOrUpdate(pmValueLimiteInfo);
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			} else {
				
				return result;
			}		
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally{
			UiUtil.closeService_MB(pmLimiteService);
		}
		return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		
		
	}

	@Override
	public String synchro(int siteId) throws RemoteException, Exception {
		OperationServiceI operationServiceI = null;
		try {
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			if(!siteUtil.isNeOnLine(siteId)){
				return ResultString.OFF_LINE_SUCCESS;
			}
			//通过网元ID判断设备类型
			if (super.getManufacturer(siteId) == EManufacturer.WUHAN.getValue()) {
				operationServiceI = new PmLimiteWHServiceImpl();
				return (String)operationServiceI.synchro(siteId);
			} else {
				
			}
			
		} catch (Exception e) {
			throw e;
		}
		
		return ResultString.SYNC_FAILED;
	}

	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
