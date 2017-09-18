package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;

import com.nms.db.bean.ptn.AllConfigInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.wh.AllConfigWHServiceImpl;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class AllConfigDispatch extends DispatchBase implements DispatchInterface {

	public String excuteInsert(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		AllConfigInfo wholeConfigInfo = null;
		try {
			
			wholeConfigInfo=(AllConfigInfo) object;
			
			manufacturer = super.getManufacturer(wholeConfigInfo.getSiteId());

			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				operationServiceI = new AllConfigWHServiceImpl();
				result = operationServiceI.excutionInsert(wholeConfigInfo);
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

	public String excuteUpdate(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		AllConfigInfo wholeConfigInfo = null;
		try {
			wholeConfigInfo=(AllConfigInfo) object;
			int siteId=wholeConfigInfo.getSiteId();
			manufacturer = super.getManufacturer(siteId);

			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				operationServiceI = new AllConfigWHServiceImpl();
				result = operationServiceI.excutionUpdate(wholeConfigInfo);
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
	public String excuteDelete(Object object) throws RemoteException, Exception {
		return null;
	}

	@Override
	public String synchro(int siteId) throws RemoteException, Exception {
		OperationServiceI operationServiceI = null;
		try {
			//虚拟网元不同步设备
			SiteUtil siteUtil=new SiteUtil();
			if(0==siteUtil.SiteTypeUtil(siteId)){
				if (super.getManufacturer(siteId) == EManufacturer.WUHAN.getValue()) {
					operationServiceI = new AllConfigWHServiceImpl();
				} else {
					
				}
				return (String)operationServiceI.synchro(siteId);
			}
		} catch (Exception e) {
			throw e;
		}finally{
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
