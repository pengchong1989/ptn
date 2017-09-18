package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.OamCXServiceImpl;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.service.impl.wh.OamWHServiceImpl;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class OamDispatch extends DispatchBase implements DispatchInterface {

	@SuppressWarnings("unchecked")
	public String excuteDelete(Object object) throws Exception {
		List<OamInfo> oamlList = null;
		OamInfo oamInfo = null;
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		int siteId = 0;
		try {
			oamlList = (List<OamInfo>) object;

			if (oamlList != null && oamlList.size() > 0) {

				oamInfo = oamlList.get(0);
				if(null!=oamInfo.getOamMep()&&0!=oamInfo.getOamMep().getSiteId()){
					manufacturer = super.getManufacturer(oamInfo.getOamMep().getSiteId());
					siteId = oamInfo.getOamMep().getSiteId();
				}
				if(null!=oamInfo.getOamMip()&&0!=oamInfo.getOamMip().getSiteId()){
					manufacturer = super.getManufacturer(oamInfo.getOamMip().getSiteId());
					siteId = oamInfo.getOamMip().getSiteId();
				}
				
				// 虚拟网元操作
				SiteUtil siteUtil = new SiteUtil();
				String siteCheck = (String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_DELETE, oamlList);
				if (null != siteCheck) {
					List<Integer> siteList = new ArrayList<Integer>();
					siteList.add(siteId);
					WhImplUtil whImplUtil = new WhImplUtil();
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+whImplUtil.getNeNames(siteList)+ResultString.NOT_ONLINE_SUCCESS;
				}

				
				if (manufacturer == EManufacturer.WUHAN.getValue()) {
					operationServiceI = new OamWHServiceImpl();
				} else {
					operationServiceI = new OamCXServiceImpl();
				}
				result = operationServiceI.excutionDelete(oamlList);
			} else {
				throw new Exception("objectList is null");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			oamlList = null;
			oamInfo = null;
			operationServiceI = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		} else {
			return result;
		}
	}

	public String excuteInsert(Object object) throws Exception {
		OamInfo oamInfo = null;
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			oamInfo = (OamInfo) object;
			// 虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck = (String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_SAVEANDUPDATE, oamInfo);
			if (null != siteCheck) {
				return siteCheck;
			}
			if(null!=oamInfo.getOamMep()&&0!=oamInfo.getOamMep().getSiteId()){
				manufacturer = super.getManufacturer(oamInfo.getOamMep().getSiteId());
			}
			if(null!=oamInfo.getOamMip()&&0!=oamInfo.getOamMip().getSiteId()){
				manufacturer = super.getManufacturer(oamInfo.getOamMip().getSiteId());
			}
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				operationServiceI = new OamWHServiceImpl();
			} else {
				 operationServiceI=new OamCXServiceImpl();
			}
			result = operationServiceI.excutionInsert(object);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			oamInfo = null;
			operationServiceI = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		} else {
			return result;
		}
	}

	public String excuteUpdate(Object object) throws Exception {
		OamInfo oamInfo = null;
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			oamInfo = (OamInfo) object;

			// 虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck = (String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_SAVEANDUPDATE, oamInfo);
			if (null != siteCheck) {
				return siteCheck;
			}

			if(null!=oamInfo.getOamMep()&&0!=oamInfo.getOamMep().getSiteId()){
				manufacturer = super.getManufacturer(oamInfo.getOamMep().getSiteId());
			}
			if(null!=oamInfo.getOamMip()&&0!=oamInfo.getOamMip().getSiteId()){
				manufacturer = super.getManufacturer(oamInfo.getOamMip().getSiteId());
			}
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				operationServiceI = new OamWHServiceImpl();
			} else {
				operationServiceI = new OamCXServiceImpl();
			}
			result = operationServiceI.excutionUpdate(object);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			oamInfo = null;
			operationServiceI = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
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
