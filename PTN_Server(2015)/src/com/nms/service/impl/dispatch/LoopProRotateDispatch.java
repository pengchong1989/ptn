package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;

import com.nms.db.enums.EManufacturer;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.wh.LoopProRotateWHServicveImpl;
import com.nms.ui.manager.ExceptionManage;

public class LoopProRotateDispatch extends DispatchBase implements DispatchInterface {

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
		try {
			LoopProRotateWHServicveImpl rotateWHServiceImpl = new LoopProRotateWHServicveImpl();
			return rotateWHServiceImpl.excutionUpdate(object);
		} catch (Exception e) {
			throw e;
		}
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
					operationServiceI = new LoopProRotateWHServicveImpl();
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