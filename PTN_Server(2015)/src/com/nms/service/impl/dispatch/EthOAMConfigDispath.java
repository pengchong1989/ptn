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
import com.nms.service.impl.wh.EthOAMConfigWHServiceImpl;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class EthOAMConfigDispath extends DispatchBase implements DispatchInterface{
	/**
	 *新建 
	 * @param oamInfoList
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String excuteInsert(Object object) throws Exception {
	int manufacturer = 0;
	OperationServiceI operationServiceI = null;
	String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
	List<OamInfo> oamInfoList = null;
	try {
		if (object == null) {
			throw new Exception("oamInfo is null");
		}
		oamInfoList=(List<OamInfo>) object;
		
		manufacturer = super.getManufacturer(oamInfoList.get(0).getOamEthernetInfo().getSiteId());
		if (manufacturer==EManufacturer.WUHAN.getValue()) {
			operationServiceI=new EthOAMConfigWHServiceImpl();
		} else {
		}
		result=operationServiceI.excutionInsert(oamInfoList);
	} catch (Exception e) {
		ExceptionManage.dispose(e,this.getClass());
	}
	if (ResultString.CONFIG_SUCCESS.equals(result)) {
		return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
	} else {
		return result;
	}
}
	/**
	 *删除 
	 */
	public String excuteDelete(Object object) throws Exception {
		List<OamInfo> oamInfoList = null;
		OamInfo oamInfo = null;
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			oamInfoList = (List<OamInfo>) object;
			if (oamInfoList != null && oamInfoList.size() > 0) {
				oamInfo = oamInfoList.get(0);
				manufacturer = super.getManufacturer(oamInfo.getOamEthernetInfo().getSiteId());
				if (manufacturer == EManufacturer.WUHAN.getValue()) {
					operationServiceI=new EthOAMConfigWHServiceImpl();
				} else {
					operationServiceI = new OamCXServiceImpl();
				}
				result = operationServiceI.excutionDelete(oamInfoList);
			} else {
				throw new Exception("objectList is null");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			operationServiceI = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		} else {
			return result;
		}
	}
	@Override
	public String excuteUpdate(Object object) throws RemoteException, Exception {
		return null;
	}
	@Override
	public String synchro(int siteId) throws RemoteException, Exception {
		
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResultString.QUERY_FAILED;
		List<OamInfo> oamInfoList = null;
		try {
			SiteUtil siteUtil=new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteSynchroAction(TypeAndActionUtil.ACTION_SYNCHRO,siteId);
			if(null!=siteCheck){
				List<Integer> siteList = new ArrayList<Integer>();
				siteList.add(siteId);
				WhImplUtil whImplUtil = new WhImplUtil();
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+whImplUtil.getNeNames(siteList)+ResultString.NOT_ONLINE_SUCCESS;
			}
			manufacturer = super.getManufacturer(siteId);
			if (manufacturer==EManufacturer.WUHAN.getValue()) {
				operationServiceI=new EthOAMConfigWHServiceImpl();
			} else {
			}
			result=(String)operationServiceI.synchro(siteId);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
		return result;
	}
	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
