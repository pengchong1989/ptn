package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.port.PortLagInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.model.ptn.port.PortLagService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.PortLagCXServiceImpl;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.service.impl.wh.PortLagWHServiceImpl;
import com.nms.ui.manager.BusinessIdException;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class PortLagDispatch extends DispatchBase implements DispatchInterface {
	@SuppressWarnings("unchecked")
	public String excuteDelete(Object object) throws Exception {
		List<PortLagInfo> portLagInfoList = null;
		PortLagInfo portLagInfo = null;
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result =  ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		PortLagService_MB portLagService = null;
		try {
			portLagInfoList = (List<PortLagInfo>) object;
			// 虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck = (String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_DELETE, portLagInfoList);
			portLagService = (PortLagService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTLAG);
			if (null != siteCheck) {
				List<Integer> siteList = new ArrayList<Integer>();
				for(PortLagInfo lagInfo : portLagInfoList){
					siteList.add(lagInfo.getSiteId());
				}
				WhImplUtil whImplUtil = new WhImplUtil();
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+whImplUtil.getNeNames(siteList)+ResultString.NOT_ONLINE_SUCCESS;
			}
			portLagService.delete(portLagInfoList);
			if (portLagInfoList != null && portLagInfoList.size() > 0) {
				portLagInfo = portLagInfoList.get(0);

				manufacturer = super.getManufacturer(portLagInfo.getSiteId());
				if (manufacturer == EManufacturer.WUHAN.getValue()) {
					operationServiceI = new PortLagWHServiceImpl();
				} else {
					operationServiceI = new PortLagCXServiceImpl();
				}
				result = operationServiceI.excutionDelete(portLagInfoList);
			} else {
				throw new Exception("objectList is null");
			}
			if (!ResultString.CONFIG_SUCCESS.equals(result)) {
				for(PortLagInfo lagInfo: portLagInfoList){
					lagInfo.setId(0);
					portLagService.saveOrUpdate(lagInfo);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			portLagInfoList = null;
			portLagInfo = null;
			operationServiceI = null;
			UiUtil.closeService_MB(portLagService);
		}
		return result;
	}

	public String excuteInsert(Object object) throws Exception {
		PortLagInfo portLagInfo = null;
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result =  ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		PortLagService_MB portLagService = null;
		try {
			// List list =(List) object;
			portLagService = (PortLagService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTLAG);
			portLagInfo = (PortLagInfo) object;
			// 虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck = (String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_INSERT, portLagInfo);
			if (null != siteCheck) {
				List<Integer> siteList = new ArrayList<Integer>();
				siteList.add(portLagInfo.getSiteId());
				WhImplUtil whImplUtil = new WhImplUtil();
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+whImplUtil.getNeNames(siteList)+ResultString.NOT_ONLINE_SUCCESS;
			}
			portLagService.saveOrUpdate(portLagInfo);
			manufacturer = super.getManufacturer(portLagInfo.getSiteId());
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				operationServiceI = new PortLagWHServiceImpl();
			} else {
				operationServiceI = new PortLagCXServiceImpl();
			}
			result = operationServiceI.excutionInsert(object);
			if (!ResultString.CONFIG_SUCCESS.equals(result)) {
				portLagService.delete(portLagInfo);
			}
		} catch (BusinessIdException e) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_BUSINESSID_NULL);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			portLagInfo = null;
			operationServiceI = null;
			UiUtil.closeService_MB(portLagService);
		}
		return result;
	}

	public String excuteUpdate(Object object) throws Exception {
		PortLagInfo portLagInfo = null;
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result =  ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		PortLagService_MB portLagService = null;
		PortLagInfo lagInfoBefore = null;
		List<PortLagInfo> portLagInfos = null;
		try {
			// 虚拟网元操作
			portLagService = (PortLagService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTLAG);
			SiteUtil siteUtil = new SiteUtil();
			portLagInfo = (PortLagInfo) object;
			portLagInfos =portLagService.selectLAGByCondition(portLagInfo);
			if(portLagInfos != null && portLagInfos.size()>0){
				lagInfoBefore = portLagInfos.get(0);
			}
			String siteCheck = (String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_UPDATE, object);
			if (null != siteCheck) {
				List<Integer> siteList = new ArrayList<Integer>();
				siteList.add(portLagInfo.getSiteId());
				WhImplUtil whImplUtil = new WhImplUtil();
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+whImplUtil.getNeNames(siteList)+ResultString.NOT_ONLINE_SUCCESS;
			}
			portLagService.saveOrUpdate(portLagInfo);
			manufacturer = super.getManufacturer(portLagInfo.getSiteId());
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				operationServiceI = new PortLagWHServiceImpl();
			} else {
				operationServiceI = new PortLagCXServiceImpl();
			}
			result = operationServiceI.excutionUpdate(object);
			if (!ResultString.CONFIG_SUCCESS.equals(result)) {
				portLagService.saveOrUpdate(lagInfoBefore);
			} 
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			portLagInfo = null;
			operationServiceI = null;
			UiUtil.closeService_MB(portLagService);
		}
		return result;
	}

	/**
	 * synchro(根据网元id同步)
	 * 
	 * @author kk
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
		String result = ResultString.QUERY_FAILED;
		try {
			// 虚拟网元操作
			SiteUtil siteUtil=new SiteUtil();
			String siteCheck = (String) siteUtil.irtualSiteSynchroAction(TypeAndActionUtil.ACTION_SYNCHRO, siteId);
			if (null != siteCheck) {
				List<Integer> siteList = new ArrayList<Integer>();
				siteList.add(siteId);
				WhImplUtil whImplUtil = new WhImplUtil();
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+whImplUtil.getNeNames(siteList)+ResultString.NOT_ONLINE_SUCCESS;
			}
			if (super.getManufacturer(siteId) == EManufacturer.WUHAN.getValue()) {
				operationServiceI = new PortLagWHServiceImpl();
			} else {
				operationServiceI = new PortLagCXServiceImpl();
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
