package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.AcCXServiceImpl;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.service.impl.wh.AcWHServiceImpl;
import com.nms.ui.manager.BusinessIdException;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class AcDispatch extends DispatchBase implements DispatchInterface{

	public String excuteUpdate(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		AcPortInfo acPortInfo = null;
		try {
			if (object == null) {
				throw new Exception("acPortInfo is null");
			}
			acPortInfo = (AcPortInfo) object;
			
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck = (String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_UPDATE,acPortInfo);
			if(null!=siteCheck){
				return siteCheck;
			}
			manufacturer = super.getManufacturer(acPortInfo.getSiteId());
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				operationServiceI = new AcWHServiceImpl();
			} else {
				operationServiceI = new AcCXServiceImpl();
			}
			ExceptionManage.infor("acDispatch--acIsUser = "+acPortInfo.getIsUser(), this.getClass());
			result = operationServiceI.excutionUpdate(acPortInfo);
			
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
              return result;
	}

	public String excuteInsert(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		List<AcPortInfo> acPortInfos=null;
		try {
			if (object == null) {
				throw new Exception("acPortInfo is null");
			}
			acPortInfos = (ArrayList<AcPortInfo>)object;
			
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_INSERT,acPortInfos);
			if(null!=siteCheck){
				return siteCheck;
			}
			
			manufacturer = super.getManufacturer(acPortInfos.get(0).getSiteId());
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				operationServiceI = new AcWHServiceImpl();
			} else {
				operationServiceI = new AcCXServiceImpl();
			}
			result = operationServiceI.excutionInsert(acPortInfos);
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}
		} catch (BusinessIdException e) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_BUSINESSID_NULL);
		}catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public String excuteDelete(Object object) throws Exception {
		List<AcPortInfo> acPortInfosList = null;
		int manufacturer = 0;
		OperationServiceI operationServiceI_WH = null;
		OperationServiceI operationServiceI_CX = null;
		List<AcPortInfo> WHacPortinfoList = null;
		List<AcPortInfo> CXacPortinfoList = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			if (object != null ) {
				acPortInfosList = (List<AcPortInfo>) object;
				
				CXacPortinfoList = new ArrayList<AcPortInfo>();
				WHacPortinfoList = new ArrayList<AcPortInfo>();

				//虚拟网元操作
				SiteUtil siteUtil = new SiteUtil();
				String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_DELETE,acPortInfosList);
				if(null!=siteCheck){
					return siteCheck;
				}
				//把acPortInfosList集合按照厂商分类
				for (AcPortInfo acPortInfo : acPortInfosList) {
					manufacturer = super.getManufacturer(acPortInfo.getSiteId());
					if (manufacturer == EManufacturer.WUHAN.getValue()) {
						WHacPortinfoList.add(acPortInfo);
					} else {
						CXacPortinfoList.add(acPortInfo);
					}
				}
				//wu下发
				if(null!=WHacPortinfoList&&WHacPortinfoList.size()>0){
					operationServiceI_WH = new AcWHServiceImpl();
					result = operationServiceI_WH.excutionDelete(WHacPortinfoList);
				}
				 //cx下发
				if(null!=CXacPortinfoList&&CXacPortinfoList.size()>0){
					operationServiceI_CX = new AcCXServiceImpl();
					result = operationServiceI_CX.excutionDelete(acPortInfosList);
				}
				
				if (ResultString.CONFIG_SUCCESS.equals(result)) {
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
				}
			} else {
				throw new Exception("objectList is null");
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			acPortInfosList = null;
			CXacPortinfoList = null;
			operationServiceI_CX = null;
			CXacPortinfoList = null;
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
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteSynchroAction(TypeAndActionUtil.ACTION_SYNCHRO,siteId);
			if(null!=siteCheck){
				return siteCheck;
			}
			if (super.getManufacturer(siteId) == EManufacturer.WUHAN.getValue()) {
				operationServiceI = new AcWHServiceImpl();
			} else {
				operationServiceI = new AcCXServiceImpl();
			}
			result=(String)operationServiceI.synchro(siteId);
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
