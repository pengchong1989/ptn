package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.List;

import com.nms.db.bean.ptn.clock.TimeSyncInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.dispatch.rmi.TimeSyncDispatchI;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.wh.TimeSyncWHServiceImpl;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class TimeSyncDispatch extends DispatchBase implements TimeSyncDispatchI {

	

	@Override
	public String excuteInsert(Object object) throws RemoteException, Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		TimeSyncInfo timeSyncInfo = null;
		try {
			
			timeSyncInfo=(TimeSyncInfo) object;
			
			manufacturer = super.getManufacturer(timeSyncInfo.getSiteId());

			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				operationServiceI = new TimeSyncWHServiceImpl();
				result = operationServiceI.excutionInsert(timeSyncInfo);
			} else {
				// operationServiceI = new AcCXServiceImpl();
			}
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}

	
	public String excutionUpdates(List<TimeSyncInfo> timesyns,Object object) throws RemoteException {
		int manufacturer = 0;
		TimeSyncWHServiceImpl operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		TimeSyncInfo timeSyncInfo = null;
		try {
			timeSyncInfo=(TimeSyncInfo) object;
			int siteId=timeSyncInfo.getSiteId();
			manufacturer = super.getManufacturer(siteId);
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				operationServiceI = new TimeSyncWHServiceImpl();
				result = operationServiceI.excutionUpdates(timesyns,timeSyncInfo);
			}
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				SiteUtil siteUtil = new SiteUtil();
				if(!siteUtil.isNeOnLine(siteId))
				{
					return ResultString.OFF_LINE_SUCCESS;
				}
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		  return result;
	}

	@Override
	public String excuteUpdate(Object object) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String excuteDelete(Object object) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String synchro(int siteId) throws RemoteException, Exception {
		OperationServiceI operationServiceI = null;
		SiteUtil siteUtil = null;
		try 
		{
			// 虚拟网元不同步设备
			siteUtil = new SiteUtil();
			if (0 == siteUtil.SiteTypeUtil(siteId)) {
				if (super.getManufacturer(siteId) == EManufacturer.WUHAN.getValue()) {
					operationServiceI = new TimeSyncWHServiceImpl();
				} else {

				}
				return (String)operationServiceI.synchro(siteId);
			}
		} catch (Exception e) 
		{
			ExceptionManage.dispose(e, getClass());
		}finally
		{
			siteUtil = null;
			operationServiceI = null;
		}
		return ResultString.QUERY_FAILED;
	}

	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}



}
