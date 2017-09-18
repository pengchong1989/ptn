package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.EthServiceInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.service.impl.wh.EthServiceImpl;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class EthServiceDispatch extends DispatchBase implements DispatchInterface {

	@Override
	public String excuteDelete(Object object) throws RemoteException, Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		List<EthServiceInfo> ethServiceInfoList = null;
		try {
			if (object == null) {
				throw new Exception("ethServiceInfo is null");
			}
			ethServiceInfoList=(List<EthServiceInfo>) object;
			manufacturer = super.getManufacturer(ethServiceInfoList.get(0).getSiteId());
			if (manufacturer==EManufacturer.WUHAN.getValue()) {
				operationServiceI = new EthServiceImpl();
				result = operationServiceI.excutionDelete(ethServiceInfoList);
			} 
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			operationServiceI = null;
			ethServiceInfoList = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			WhImplUtil whImplUtil = new WhImplUtil();
			return whImplUtil.offLineResult(object);
		} else {
			return result;
		}
	}

	@Override
	public String excuteInsert(Object object) throws RemoteException, Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		EthServiceInfo ethServiceInfo = null;
		List<EthServiceInfo> ethServiceList = null;
		try {
			if (object == null) {
				throw new Exception("ethServiceInfo is null");
			}
			ethServiceList = (List<EthServiceInfo>) object;
			
			manufacturer = super.getManufacturer(ethServiceList.get(0).getSiteId());
			if (manufacturer==EManufacturer.WUHAN.getValue()) {
				operationServiceI=new EthServiceImpl();
				result=operationServiceI.excutionInsert(ethServiceList);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			operationServiceI = null;
			ethServiceInfo = null;
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
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String synchro(int siteId) throws RemoteException, Exception {
		try {
			//虚拟网元操作
			SiteUtil siteUtil=new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteSynchroAction(TypeAndActionUtil.ACTION_SYNCHRO, siteId);
			if(null != siteCheck){
				return restult(siteId);
			}
			if (super.getManufacturer(siteId) == EManufacturer.WUHAN.getValue()) {
				OperationServiceI operationServiceI = new EthServiceImpl();
				return (String)operationServiceI.synchro(siteId);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return ResultString.QUERY_FAILED;
	}

	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String restult(int siteId)
	{
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(siteId);
		WhImplUtil whImplUtil = new WhImplUtil();
		String str = whImplUtil.getNeNames(ids);
		if(str.equals("")){
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		}else{
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+str+ResultString.NOT_ONLINE_SUCCESS;
		}
	}

}
