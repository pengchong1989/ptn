package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.List;

import com.nms.db.bean.ptn.ecn.CCN;
import com.nms.db.enums.EManufacturer;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.CCNCXServiceImpl;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

/**
 * ccn功能暂时不用
 * @author kk
 *
 */
public class CCNDispatch extends DispatchBase implements DispatchInterface{

	public String excuteUpdate(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		CCN ccn=null;
		try {
			if (object == null) {
				throw new Exception("ccn is null");
			}
			ccn=(CCN) object;
			
			manufacturer = super.getManufacturer(Integer.valueOf(ccn.getNeId()));
			if (manufacturer == EManufacturer.WUHAN.getValue()) {

			} else {
				operationServiceI = new CCNCXServiceImpl();
			}
			result = operationServiceI.excutionUpdate(ccn);
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
			return result;
	}

	public String excuteInsert(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		CCN ccn=null;
		try {
			if (object == null) {
				throw new Exception("ccn is null");
			}
			ccn=(CCN) object;
			
			manufacturer = super.getManufacturer(Integer.valueOf(ccn.getNeId()));
			if (manufacturer == EManufacturer.WUHAN.getValue()) {

			} else {
				operationServiceI = new CCNCXServiceImpl();
			}
			result = operationServiceI.excutionInsert(ccn);
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}

	public String excuteDelete(Object object) throws Exception {
		if (object == null) {
			throw new Exception("ccn is null");
		}

		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		
		List<CCN> ccn= null;
		try {
			ccn=(List<CCN>) object;
			manufacturer = super.getManufacturer(Integer.valueOf(ccn.get(0).getNeId()));
			if (manufacturer == EManufacturer.WUHAN.getValue()) {

			} else {
				operationServiceI = new CCNCXServiceImpl();
			}
			result = operationServiceI.excutionDelete(ccn);
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}

	public String synchro(int neId) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResultString.QUERY_FAILED;//TIP_CONFIG_FAIL
		try {
			manufacturer = super.getManufacturer(neId);
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				
			} else {
				operationServiceI = new CCNCXServiceImpl();
			}
			result = (String)operationServiceI.synchro(neId);
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return result;
	}

	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
