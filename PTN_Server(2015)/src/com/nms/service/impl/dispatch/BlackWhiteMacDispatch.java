package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.List;

import com.nms.db.bean.ptn.BlackAndwhiteMacInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.service.impl.wh.BlackWhiteMacWHServiceImpl;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class BlackWhiteMacDispatch extends DispatchBase implements DispatchInterface{

	@Override
	public String excuteDelete(Object object) throws RemoteException, Exception {
		
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		List<BlackAndwhiteMacInfo> blackAndwhiteMacInfoList = null;
		try {
			blackAndwhiteMacInfoList=(List<BlackAndwhiteMacInfo>) object;
			manufacturer = super.getManufacturer(blackAndwhiteMacInfoList.get(0).getSiteId());
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				operationServiceI = new BlackWhiteMacWHServiceImpl();
				result = operationServiceI.excutionDelete(blackAndwhiteMacInfoList);
			} else {
				// operationServiceI = new AcCXServiceImpl();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			 operationServiceI = null;
			 blackAndwhiteMacInfoList = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			WhImplUtil whImplUtil = new WhImplUtil();
			return whImplUtil.offLineResult(object);
//			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		}else{
			return result;
		}
	}

	@Override
	public String excuteInsert(Object object) throws RemoteException, Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		BlackAndwhiteMacInfo blackAndwhiteMacInfo = null;
		try {
			blackAndwhiteMacInfo=(BlackAndwhiteMacInfo) object;
			manufacturer = super.getManufacturer(blackAndwhiteMacInfo.getSiteId());
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				operationServiceI = new BlackWhiteMacWHServiceImpl();
				result = operationServiceI.excutionInsert(blackAndwhiteMacInfo);
			} else {
				// operationServiceI = new AcCXServiceImpl();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			 operationServiceI = null;
			 blackAndwhiteMacInfo = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
//			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			WhImplUtil whImplUtil = new WhImplUtil();
			return whImplUtil.offLineResult(object);
		}else{
			return result;
		}
	}

	@Override
	public String excuteUpdate(Object object) throws RemoteException, Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		BlackAndwhiteMacInfo blackAndwhiteMacInfo = null;
		try {
			blackAndwhiteMacInfo=(BlackAndwhiteMacInfo) object;
			manufacturer = super.getManufacturer(blackAndwhiteMacInfo.getSiteId());
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				operationServiceI = new BlackWhiteMacWHServiceImpl();
				result = operationServiceI.excutionUpdate(blackAndwhiteMacInfo);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			 operationServiceI = null;
			 blackAndwhiteMacInfo = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			WhImplUtil whImplUtil = new WhImplUtil();
			return whImplUtil.offLineResult(object);
//			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		}else{
			return result;
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
					operationServiceI = new BlackWhiteMacWHServiceImpl();
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
