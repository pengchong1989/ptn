package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;

import com.nms.db.bean.ptn.EthLoopInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.service.impl.wh.EthLoopWHServiceImpl;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class EthLoopDispatch extends DispatchBase implements DispatchInterface{

	@Override
	public String excuteDelete(Object object) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String excuteInsert(Object object) throws RemoteException, Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		EthLoopInfo thLoopInfo = null;
		try {
			thLoopInfo=(EthLoopInfo) object;
			manufacturer = super.getManufacturer(thLoopInfo.getSiteId());
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				operationServiceI = new EthLoopWHServiceImpl();
			} else {
			}
			result = operationServiceI.excutionInsert(thLoopInfo);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			 operationServiceI = null;
			 thLoopInfo = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			WhImplUtil whImplUtil = new WhImplUtil();
			return whImplUtil.offLineResult(object);
		} else {
			return result;
		}
	}

	@Override
	public String excuteUpdate(Object object) throws RemoteException, Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		EthLoopInfo thLoopInfo = null;
		try {
			thLoopInfo=(EthLoopInfo) object;
			
			manufacturer = super.getManufacturer(thLoopInfo.getSiteId());

			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				operationServiceI = new EthLoopWHServiceImpl();
			}
			result = operationServiceI.excutionUpdate(thLoopInfo);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			 operationServiceI = null;
			 thLoopInfo = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			WhImplUtil whImplUtil = new WhImplUtil();
			return whImplUtil.offLineResult(object);
		} else {
			return result;
		}
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
