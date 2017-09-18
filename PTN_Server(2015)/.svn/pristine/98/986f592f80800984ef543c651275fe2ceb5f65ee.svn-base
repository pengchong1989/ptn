package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;

import com.nms.db.bean.ptn.L2cpInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.service.impl.wh.L2CPWHService;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class L2cpDispath extends DispatchBase implements DispatchInterface{

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
		L2cpInfo l2cpInfo = null;
		try {
			l2cpInfo = (L2cpInfo)object;
			manufacturer = super.getManufacturer(l2cpInfo.getSiteId());
			if(manufacturer == EManufacturer.WUHAN.getValue()){
				operationServiceI = new L2CPWHService();
				result = operationServiceI.excutionInsert(object);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally{
			l2cpInfo = null;
			operationServiceI = null;
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
		L2cpInfo l2cpInfo = null;
		try {
			l2cpInfo = (L2cpInfo)object;
			manufacturer = super.getManufacturer(l2cpInfo.getSiteId());
			if(manufacturer == EManufacturer.WUHAN.getValue()){
				operationServiceI = new L2CPWHService();
				result = operationServiceI.excutionUpdate(object);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally{
			l2cpInfo = null;
			operationServiceI = null;
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
		OperationServiceI operationServiceI = null;
		try {
			//虚拟网元不同步设备
			SiteUtil siteUtil=new SiteUtil();
			if(0==siteUtil.SiteTypeUtil(siteId)){
				if (super.getManufacturer(siteId) == EManufacturer.WUHAN.getValue()) {
					operationServiceI = new L2CPWHService();
				} else {
					
				}
				return (String)operationServiceI.synchro(siteId);
			}
		} catch (Exception e) {
			throw e;
		}
		return ResultString.QUERY_FAILED;
	}

	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
