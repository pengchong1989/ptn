package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;

import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.wh.OamPingWHServiceImpl;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class OamPingDispatch extends DispatchBase implements DispatchInterface{

	@Override
	public String excuteDelete(Object object) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String excuteInsert(Object object) throws RemoteException, Exception {
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			OamPingWHServiceImpl oamPingWHServiceImpl = new OamPingWHServiceImpl();
			result = oamPingWHServiceImpl.excutionInsert(object);
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return result;
	}

	@Override
	public String excuteUpdate(Object object) throws RemoteException, Exception {
		// TODO Auto-generated method stub
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
