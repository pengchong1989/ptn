package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.clock.ExternalClockInterface;
import com.nms.db.enums.EManufacturer;
import com.nms.model.ptn.clock.ExternalClockInterfaceService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.ExternalClockInterfaceCXServiceImpl;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.BusinessIdException;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class ExternalClockDispatch  extends DispatchBase implements DispatchInterface{
	/**
	 * 添加外时钟
	 * @param clockSource 
	 * @return
	 * @throws Exception
	 */
	public String excuteInsert(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		ExternalClockInterfaceService_MB externalClockInterfaceService=null;
		ExternalClockInterface externalClockInterface=null;
		try {
			if (object == null) {
				throw new Exception("clockSource is null");
			}
			externalClockInterfaceService = (ExternalClockInterfaceService_MB)ConstantUtil.serviceFactory.newService_MB(Services.ExternalClockInterfaceService);
			externalClockInterface=(ExternalClockInterface) object;
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_INSERT,externalClockInterface);
			if(null!=siteCheck){
				return siteCheck;
			}
			
			int clockSourceId = externalClockInterfaceService.insertTimeManageInfo(externalClockInterface);
			
			manufacturer = super.getManufacturer(externalClockInterface.getSiteId());
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				
			} else {
				operationServiceI = new ExternalClockInterfaceCXServiceImpl();
			}
			result = operationServiceI.excutionInsert(externalClockInterface);
			if (clockSourceId>0&&ResultString.CONFIG_SUCCESS.equals(result)) {
				result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}else {
				externalClockInterfaceService.delete(externalClockInterface);
			}
		} catch (BusinessIdException e) {
			return e.getMessage();
		}catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			operationServiceI = null;
			UiUtil.closeService_MB(externalClockInterfaceService);
		}
		return result;
	}

	
	/**
	 * 修改外时钟
	 * @param clockSource
	 * @return
	 * @throws Exception
	 */
	public String excuteUpdate(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		List<ExternalClockInterface> beforeClockSource;
		ExternalClockInterfaceService_MB externalClockInterfaceService=null;
		ExternalClockInterface externalClockInterface=null;
		try {
			if (object == null) {
				throw new Exception("clockSource is null");
			}
			externalClockInterfaceService = (ExternalClockInterfaceService_MB)ConstantUtil.serviceFactory.newService_MB(Services.ExternalClockInterfaceService);
			externalClockInterface=(ExternalClockInterface) object;
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_UPDATE,externalClockInterface);
			if(null!=siteCheck){
				return siteCheck;
			}
			beforeClockSource = new ArrayList<ExternalClockInterface>(); 
			beforeClockSource = externalClockInterfaceService.select(externalClockInterface);
			int clockSourceId = externalClockInterfaceService.update(externalClockInterface);
			
			manufacturer = super.getManufacturer(externalClockInterface.getSiteId());

			if (manufacturer == EManufacturer.WUHAN.getValue()) {

			} else {
				operationServiceI = new ExternalClockInterfaceCXServiceImpl();
			}
			result = operationServiceI.excutionUpdate(externalClockInterface);
			if (clockSourceId >0 && ResultString.CONFIG_SUCCESS.equals(result)) {
				result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}else{
				externalClockInterfaceService.update(beforeClockSource.get(0));
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(externalClockInterfaceService);
		}
		return result;
	}

	/**
	 * 删除外时钟
	 * @param clockSourceList 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String excuteDelete(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		List<ExternalClockInterface> externalClockInterfaceList;
		ExternalClockInterfaceService_MB externalClockInterfaceService=null;
		ExternalClockInterface externalClockInterface=null;
		try {
			externalClockInterfaceService = (ExternalClockInterfaceService_MB)ConstantUtil.serviceFactory.newService_MB(Services.ExternalClockInterfaceService);
			externalClockInterface=(ExternalClockInterface) object;
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_DELETE,externalClockInterface);
			if(null!=siteCheck){
				return siteCheck;
			}
			
			int clockSourceId =  externalClockInterfaceService.delete(externalClockInterface);
			manufacturer = super.getManufacturer(externalClockInterface.getSiteId());
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
	//			operationServiceI = new ClockSourceWHServiceImpl();
			} else {
				operationServiceI = new ExternalClockInterfaceCXServiceImpl();
			}
			externalClockInterfaceList = new ArrayList<ExternalClockInterface>();
			externalClockInterfaceList.add(externalClockInterface);
			result = operationServiceI.excutionDelete(externalClockInterfaceList);
			if (clockSourceId >0 && ResultString.CONFIG_SUCCESS.equals(result)) {
				result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			} else {
				externalClockInterfaceService.insertTimeManageInfo(externalClockInterface);
				
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationServiceI = null;
			UiUtil.closeService_MB(externalClockInterfaceService);
		}
		return result;
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
		int manufacturer = 0;
		String result = ResultString.QUERY_FAILED; 
		try {
			//虚拟网元操作
			SiteUtil siteUtil=new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteSynchroAction(TypeAndActionUtil.ACTION_SYNCHRO,siteId);
			if(null!=siteCheck){
				return siteCheck;
			}
			manufacturer = super.getManufacturer(siteId);
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
	//			operationServiceI = new ClockSourceWHServiceImpl();
			} else {
				operationServiceI = new ExternalClockInterfaceCXServiceImpl();
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
