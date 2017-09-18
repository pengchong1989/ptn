package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.clock.PortConfigInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.model.ptn.clock.PortDispositionInfoService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.TimePortConfigCXServiceImpl;
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
/**
 * 时间的端口设置
 * @author dzy
 *
 */
public class TimePortConfigDispatch  extends DispatchBase implements DispatchInterface{
	/**
	 * 添加端口设置
	 * @param portConfigInfo 
	 * @return
	 * @throws Exception
	 */
	public String excuteInsert(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		List<PortConfigInfo> portConfigInfoList = null;
		PortConfigInfo portConfigInfo=null;
		PortDispositionInfoService_MB portDispositionInfoService = null;
		try {
			if (object == null) {
				throw new Exception("clockSource is null");
			}
			portConfigInfo=(PortConfigInfo) object;
			portDispositionInfoService = (PortDispositionInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PortDispositionInfoService);
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_INSERT,portConfigInfo);
			if(null!=siteCheck){
				return siteCheck;
			}
			
			int clockSourceId = portDispositionInfoService.insertPortDispositionInfo(portConfigInfo);
			
			manufacturer = super.getManufacturer(portConfigInfo.getSiteId());
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				
			} else {
				operationServiceI = new TimePortConfigCXServiceImpl();
			}
			result = operationServiceI.excutionInsert(portConfigInfo);
			if (clockSourceId>0&&ResultString.CONFIG_SUCCESS.equals(result)) {
				result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}else {
				portConfigInfoList = new ArrayList<PortConfigInfo>();
				portConfigInfoList.add(portConfigInfo);
				portDispositionInfoService.delete(portConfigInfoList);
			}
		} catch (BusinessIdException e) {
			return e.getMessage();
		}catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(portDispositionInfoService);
			operationServiceI = null;
			portConfigInfoList = null;
		}
		return result;
	}

	
	/**
	 * 修改端口设置
	 * @param portConfigInfo
	 * @return
	 * @throws Exception
	 */
	public String excuteUpdate(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		List<PortConfigInfo> beforeClockSource;
		PortDispositionInfoService_MB portDispositionInfoService = null;
		PortConfigInfo portConfigInfo=null;
		try {
			if (object == null) {
				throw new Exception("clockSource is null");
			}
			portDispositionInfoService = (PortDispositionInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PortDispositionInfoService);
			portConfigInfo=(PortConfigInfo) object;
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_UPDATE,portConfigInfo);
			if(null!=siteCheck){
				return siteCheck;
			}
			beforeClockSource = new ArrayList<PortConfigInfo>(); 
			beforeClockSource = portDispositionInfoService.select(portConfigInfo.getId());
			int clockSourceId = portDispositionInfoService.update(portConfigInfo);
			
			manufacturer = super.getManufacturer(portConfigInfo.getSiteId());

			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				
			} else {
				operationServiceI = new TimePortConfigCXServiceImpl();
			}
			result = operationServiceI.excutionUpdate(portConfigInfo);
			if (clockSourceId >0 && ResultString.CONFIG_SUCCESS.equals(result)) {
				result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}else{
				if(beforeClockSource.size()>0){
					portDispositionInfoService.update(beforeClockSource.get(0));
				}
				
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(portDispositionInfoService);
			operationServiceI = null;
		}
		return result;
	}

	/**
	 * 删除端口设置
	 * @param portConfigInfoList 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String excuteDelete(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		List<PortConfigInfo> portConfigInfoList=null;
		PortDispositionInfoService_MB portDispositionInfoService = null;
		try {		
			portConfigInfoList=(List<PortConfigInfo>) object;
			portDispositionInfoService = (PortDispositionInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PortDispositionInfoService);
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_DELETE,portConfigInfoList);
			if(null!=siteCheck){
				return siteCheck;
			}			
			int clockSourceId = portDispositionInfoService.delete(portConfigInfoList);
			manufacturer = super.getManufacturer(portConfigInfoList.get(0).getSiteId());
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
			} else {
				operationServiceI = new TimePortConfigCXServiceImpl();
			}
			result = operationServiceI.excutionDelete(portConfigInfoList);

			if (clockSourceId >0 && ResultString.CONFIG_SUCCESS.equals(result)) {
				result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			} else {
				for(PortConfigInfo portConfigInfo:portConfigInfoList){
					portDispositionInfoService.insertPortDispositionInfo(portConfigInfo);
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(portDispositionInfoService);
			operationServiceI = null;
		}
		return result;
	}
	
	/**
	 * synchro(根据网元id同步)
	 * 
	 * @author sy
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
		String result =  ResultString.QUERY_FAILED;
		try {
			//虚拟网元操作
			SiteUtil siteUtil=new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteSynchroAction(TypeAndActionUtil.ACTION_SYNCHRO,siteId);
			if(null!=siteCheck){
				return siteCheck;
			}
			manufacturer = super.getManufacturer(siteId);
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
			} else {
				operationServiceI = new TimePortConfigCXServiceImpl();
			}
			result =  (String)operationServiceI.synchro(siteId);

		} catch (Exception e) {
		}finally{
			operationServiceI = null;
		}
		return result;
	}


	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
