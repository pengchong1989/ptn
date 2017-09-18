package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;

import com.nms.db.bean.equipment.port.PortStm;
import com.nms.db.enums.EManufacturer;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.PortStmCXServiceImpl;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class PortStmDispatch extends DispatchBase implements DispatchInterface {
	public String excuteUpdate(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		PortStm portstm = null;
		try {
			portstm = (PortStm) object;

			// 虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck = (String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_UPDATE, portstm);
			if (null != siteCheck) {
				return siteCheck;
			}

			manufacturer = super.getManufacturer(portstm.getSiteid());

			if (manufacturer == EManufacturer.WUHAN.getValue()) {

			} else {
				operationServiceI = new PortStmCXServiceImpl();
			}
			result = operationServiceI.excutionUpdate(portstm);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationServiceI = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		} else {
			return result;
		}
	}

	/**
	 * synchro(根据网元id同步)
	 * 
	 * @author wangwf
	 * 
	 * @param
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	public String synchro(int siteId) throws Exception {
		OperationServiceI operationServiceI = null;
		String result =  ResultString.QUERY_FAILED;
		try {
			// 虚拟网元操作
			SiteUtil siteUtil=new SiteUtil();
			String siteCheck = (String) siteUtil.irtualSiteSynchroAction(TypeAndActionUtil.ACTION_SYNCHRO, siteId);
			if (null != siteCheck) {
				return siteCheck;
			}
			if (super.getManufacturer(siteId) == EManufacturer.WUHAN.getValue()) {

			} else {
				operationServiceI = new PortStmCXServiceImpl();
			}
			result = (String)operationServiceI.synchro(siteId);

		} catch (Exception e) {
			throw e;
		}
		return result;
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
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
