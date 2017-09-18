package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.path.StaticUnicastInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.service.impl.wh.StaticUniWHServiceImpl;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class StaticUniDispatch extends DispatchBase implements DispatchInterface{
	
	public String excuteInsert(Object object) throws Exception{
		StaticUnicastInfo uniInfo = null;
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			uniInfo = (StaticUnicastInfo) object;
			
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_INSERT,uniInfo);
			if(null!=siteCheck){
				List<Integer> siteList = new ArrayList<Integer>();
				siteList.add(uniInfo.getSiteId());
				WhImplUtil whImplUtil = new WhImplUtil();
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+whImplUtil.getNeNames(siteList)+ResultString.NOT_ONLINE_SUCCESS;
			}
			
			manufacturer = super.getManufacturer(uniInfo.getSiteId());
			if (manufacturer==EManufacturer.WUHAN.getValue()) {
				operationServiceI = new StaticUniWHServiceImpl();
			} else {
			
			}
			result = operationServiceI.excutionInsert(object);
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
	public String excuteDelete(Object object) throws Exception{
		List<StaticUnicastInfo> uniInfoList = null;
		StaticUnicastInfo uniInfo = null;
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result =ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			uniInfoList = (List<StaticUnicastInfo>) object;
			if(uniInfoList != null && uniInfoList.size() > 0){
				//虚拟网元操作
				SiteUtil siteUtil = new SiteUtil();
				String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_DELETE,uniInfoList);
				if(null!=siteCheck){
					List<Integer> siteList = new ArrayList<Integer>();
					siteList.add(uniInfoList.get(0).getSiteId());
					WhImplUtil whImplUtil = new WhImplUtil();
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+whImplUtil.getNeNames(siteList)+ResultString.NOT_ONLINE_SUCCESS;
				}
				uniInfo = uniInfoList.get(0);
				manufacturer = super.getManufacturer(uniInfo.getSiteId());
				if(manufacturer==EManufacturer.WUHAN.getValue()){
					operationServiceI = new StaticUniWHServiceImpl();
				} else {
					
				}
				result = operationServiceI.excutionDelete(uniInfoList);
			} else {
				throw new Exception("objectList is null");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		} else {
			return result;
		}
	}
	
	public String excuteUpdate(Object object) throws Exception{
		StaticUnicastInfo uniInfo = null;
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			uniInfo = (StaticUnicastInfo) object;
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_UPDATE,uniInfo);
			if(null!=siteCheck){
				List<Integer> siteList = new ArrayList<Integer>();
				siteList.add(uniInfo.getSiteId());
				WhImplUtil whImplUtil = new WhImplUtil();
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+whImplUtil.getNeNames(siteList)+ResultString.NOT_ONLINE_SUCCESS;
			}
			manufacturer = super.getManufacturer(uniInfo.getSiteId());
			if (manufacturer==EManufacturer.WUHAN.getValue()) {
				operationServiceI = new StaticUniWHServiceImpl();
			} else {
				
			}
			result = operationServiceI.excutionUpdate(object);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
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
					operationServiceI = new StaticUniWHServiceImpl();
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
