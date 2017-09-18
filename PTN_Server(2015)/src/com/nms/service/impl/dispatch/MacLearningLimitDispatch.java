package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.List;

import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.service.impl.wh.MacLearningLimitWHServiceImpl;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class MacLearningLimitDispatch extends DispatchBase implements DispatchInterface {

	@SuppressWarnings("unchecked")
	public String excuteDelete(Object object)throws Exception {
		OperationServiceI operationServiceI=null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck = (String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_DELETE,object);
			if(null!=siteCheck){
				return ResultString.OFF_LINE_SUCCESS;
			}
			
			operationServiceI = new MacLearningLimitWHServiceImpl();
			result = operationServiceI.excutionDelete((List)object);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally{
			operationServiceI=null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		} else {
			return result;
		}
	}

	@Override
	public String excuteInsert(Object object) throws RemoteException, Exception {
		OperationServiceI operationServiceI=null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck = (String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_INSERT,object);
			if(null!=siteCheck){
				return ResultString.OFF_LINE_SUCCESS;
			}
			
			operationServiceI = new MacLearningLimitWHServiceImpl();
			result = operationServiceI.excutionInsert(object);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally{
			operationServiceI=null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		} else {
			return result;
		}
	}

	@Override
	public String excuteUpdate(Object object) throws RemoteException, Exception {
		OperationServiceI operationServiceI=null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck = (String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_UPDATE,object);
			if(null!=siteCheck){
				return ResultString.OFF_LINE_SUCCESS;
			}
			operationServiceI = new MacLearningLimitWHServiceImpl();
			result = operationServiceI.excutionUpdate(object);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally{
			operationServiceI=null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		} else {
			return result;
		}
	}

	@Override
	public String synchro(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
