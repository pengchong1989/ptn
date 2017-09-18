package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.List;

import com.nms.db.bean.ptn.ecn.OspfRedistribute;
import com.nms.db.enums.EManufacturer;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.RedistributeCXServiceImpl;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class RedistributeDispatch extends DispatchBase implements DispatchInterface {
	public String excuteUpdate(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		OspfRedistribute ospfRedistribute = null;
		try {
			if (object == null) {
				throw new Exception("OspfRedistribute is null");
			}
			ospfRedistribute = (OspfRedistribute) object;

			manufacturer = super.getManufacturer(Integer.valueOf(ospfRedistribute.getNeId()));
			if (manufacturer == EManufacturer.WUHAN.getValue()) {

			} else {
				operationServiceI = new RedistributeCXServiceImpl();
			}
			result = operationServiceI.excutionUpdate(ospfRedistribute);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		} else {
			return result;
		}
	}

	public String excuteInsert(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		OspfRedistribute ospfRedistribute = null;
		try {
			if (object == null) {
				throw new Exception("OspfRedistribute is null");
			}
			ospfRedistribute = (OspfRedistribute) object;

			manufacturer = super.getManufacturer(Integer.valueOf(ospfRedistribute.getNeId()));
			if (manufacturer == EManufacturer.WUHAN.getValue()) {

			} else {
				operationServiceI = new RedistributeCXServiceImpl();
			}
			result = operationServiceI.excutionInsert(ospfRedistribute);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		} else {
			return result;
		}
	}

	@SuppressWarnings("unchecked")
	public String excuteDelete(Object object) throws Exception {
		if (object == null) {
			throw new Exception("ospfRedistributes is null");
		}

		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		List<OspfRedistribute> ospfRedistributes = null;
		try {
			ospfRedistributes=(List<OspfRedistribute>) object;
			
			manufacturer = super.getManufacturer(Integer.valueOf(ospfRedistributes.get(0).getNeId()));
			if (manufacturer == EManufacturer.WUHAN.getValue()) {

			} else {
				operationServiceI = new RedistributeCXServiceImpl();
			}
			result = operationServiceI.excutionDelete(ospfRedistributes);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
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
	 * @param siteId
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

			if (super.getManufacturer(siteId) == EManufacturer.WUHAN.getValue()) {
				// operationServiceI = new ces();
			} else {
				operationServiceI = new RedistributeCXServiceImpl();
			}
			result = (String)operationServiceI.synchro(siteId);

		} catch (Exception e) {
			throw e;
		}
        return result;
	}

	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
