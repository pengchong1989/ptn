package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.path.GroupSpreadInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.service.impl.wh.GroupWHServiceImpl;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class GroupSpreadDispatch extends DispatchBase implements DispatchInterface{
	
	public String excuteInsert(Object object) throws Exception{
		GroupSpreadInfo groupInfo = null;
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			groupInfo = (GroupSpreadInfo) object;
			manufacturer = super.getManufacturer(groupInfo.getSiteId());
			if (manufacturer==EManufacturer.WUHAN.getValue()) {
				operationServiceI = new GroupWHServiceImpl();
			} else {
				
			}
			result = operationServiceI.excutionInsert(object);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			List<Integer> siteList = new ArrayList<Integer>();
			siteList.add(groupInfo.getSiteId());
			WhImplUtil whImplUtil = new WhImplUtil();
			String str = whImplUtil.getNeNames(siteList);
			if(!str.equals("")){
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+str+ResultString.NOT_ONLINE_SUCCESS;
			}else{
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}
		} else {
			return result;
		}
	}
	
	@SuppressWarnings("unchecked")
	public String excuteDelete(Object object) throws Exception{
		List<GroupSpreadInfo> groupInfoList = null;
		GroupSpreadInfo groupInfo = null;
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			groupInfoList = (List<GroupSpreadInfo>) object;
			if(groupInfoList != null && groupInfoList.size() > 0){
				groupInfo = groupInfoList.get(0);
				manufacturer = super.getManufacturer(groupInfo.getSiteId());
				if(manufacturer==EManufacturer.WUHAN.getValue()){
					operationServiceI = new GroupWHServiceImpl();
				} else {
					
				}
				result = operationServiceI.excutionDelete(groupInfoList);
			} else {
				throw new Exception("objectList is null");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} 
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			List<Integer> siteList = new ArrayList<Integer>();
			siteList.add(groupInfoList.get(0).getSiteId());
			WhImplUtil whImplUtil = new WhImplUtil();
			String str = whImplUtil.getNeNames(siteList);
			if(!str.equals("")){
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+str+ResultString.NOT_ONLINE_SUCCESS;
			}else{
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}
		} else {
			return result;
		}
	}
	
	public String excuteUpdate(Object object) throws Exception{
		GroupSpreadInfo groupInfo = null;
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			groupInfo = (GroupSpreadInfo) object;
			manufacturer = super.getManufacturer(groupInfo.getSiteId());
			if (manufacturer==EManufacturer.WUHAN.getValue()) {
				operationServiceI = new GroupWHServiceImpl();
			} else {
				
			}
			result = operationServiceI.excutionUpdate(object);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} 
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			List<Integer> siteList = new ArrayList<Integer>();
			siteList.add(groupInfo.getSiteId());
			WhImplUtil whImplUtil = new WhImplUtil();
			String str = whImplUtil.getNeNames(siteList);
			if(!str.equals("")){
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+str+ResultString.NOT_ONLINE_SUCCESS;
			}else{
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}
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
