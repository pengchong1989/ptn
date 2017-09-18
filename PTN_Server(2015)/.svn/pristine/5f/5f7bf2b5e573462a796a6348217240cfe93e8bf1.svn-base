package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.equipment.port.PortDiscardInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.model.ptn.PortDiscardService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.service.impl.wh.PortDiscardWHServiceImpl;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class PortDiscardDispatch extends DispatchBase implements DispatchInterface{
	/**
	 * 修改
	 */
	public String excuteUpdate(Object object) throws Exception{
		PortDiscardInfo portDiscardInfo=null;
		int manufacturer=0;
		OperationServiceI operationServiceI=null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		PortDiscardService_MB portDiscardService = null;
		try {
			portDiscardInfo=(PortDiscardInfo) object;
			portDiscardService = (PortDiscardService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PortDiscardService);
			manufacturer=super.getManufacturer(portDiscardInfo.getSiteId());
			portDiscardService.update(portDiscardInfo);
			if(manufacturer==EManufacturer.WUHAN.getValue()){
				operationServiceI=new PortDiscardWHServiceImpl();
				result = operationServiceI.excutionUpdate(object);
			}else{
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally{
			operationServiceI=null;
			UiUtil.closeService_MB(portDiscardService);
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			List<Integer> siteList = new ArrayList<Integer>();
			siteList.add(portDiscardInfo.getSiteId());
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
	
	/**
	 *  同步
	
	* @author kk
	
	* @param   
	
	* @return 
	 * @throws Exception 
	
	* @Exception 异常对象
	 */
	public String synchro(int siteId) throws Exception{
		int manufacturer=0;
		OperationServiceI operationServiceI=null;
		String result = ResultString.QUERY_FAILED;
		try {
			//虚拟网元操作
			SiteUtil siteUtil=new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteSynchroAction(TypeAndActionUtil.ACTION_SYNCHRO,siteId);
			if(null!=siteCheck){
				return siteCheck;
			}
			manufacturer=super.getManufacturer(siteId);
			if(manufacturer==EManufacturer.WUHAN.getValue()){
				//同步武汉的NNI、UNI
				operationServiceI = new PortDiscardWHServiceImpl();
				result = (String)operationServiceI.synchro(siteId);
			}
			return result;
		} catch (Exception e) {
			throw e;
		} finally{
			operationServiceI=null;
		}
		
	}

	@Override
	public String excuteDelete(Object object) throws RemoteException, Exception {
		return null;
	}

	@Override
	public String excuteInsert(Object object) throws RemoteException, Exception {
		PortDiscardInfo portDiscardInfo=null;
		int manufacturer=0;
		OperationServiceI operationServiceI=null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		PortDiscardService_MB portDiscardService = null;
		try {
			portDiscardInfo=(PortDiscardInfo) object;
			portDiscardService = (PortDiscardService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PortDiscardService);
			manufacturer=super.getManufacturer(portDiscardInfo.getSiteId());
			portDiscardService.save(portDiscardInfo);
			if(manufacturer==EManufacturer.WUHAN.getValue()){
				operationServiceI=new PortDiscardWHServiceImpl();
				result = operationServiceI.excutionInsert(object);
			}else{
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally{
			UiUtil.closeService_MB(portDiscardService);
			operationServiceI=null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		} else {
			return result;
		}
	}

	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
