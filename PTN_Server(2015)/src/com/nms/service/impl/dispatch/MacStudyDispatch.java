package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.SsMacStudy;
import com.nms.db.enums.EManufacturer;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.service.impl.wh.SsmacWHServiceImpl;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class MacStudyDispatch extends DispatchBase implements DispatchInterface{

	@SuppressWarnings("unchecked")
	@Override
	public String excuteDelete(Object object) throws RemoteException, Exception {
		List<SsMacStudy> ssMacStudy = null;
		SsMacStudy macInfo = null;
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;		
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			ssMacStudy = (List<SsMacStudy>) object;
			if(ssMacStudy != null && ssMacStudy.size() > 0){
				//虚拟网元操作
				SiteUtil siteUtil = new SiteUtil();
				String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_DELETE,ssMacStudy);
				if(null!=siteCheck){
					List<Integer> siteList = new ArrayList<Integer>();
					siteList.add(ssMacStudy.get(0).getSiteId());
					WhImplUtil whImplUtil = new WhImplUtil();
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+whImplUtil.getNeNames(siteList)+ResultString.NOT_ONLINE_SUCCESS;
				}
				macInfo = ssMacStudy.get(0);
				manufacturer = super.getManufacturer(macInfo.getSiteId());
				if(manufacturer==EManufacturer.WUHAN.getValue()){
					operationServiceI = new SsmacWHServiceImpl();
				} else {
					
				}
				result = operationServiceI.excutionDelete(ssMacStudy);				
			} else {
				throw new Exception("objectList is null");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally {
			operationServiceI = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {		
			String str = getOfflineSiteIdNames(ssMacStudy);
			if(str.equals("")){
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}else{
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+str+ResultString.NOT_ONLINE_SUCCESS;
			}
		} else {
			return result;
		}
	}


	public String excuteInsert(Object object) throws RemoteException, Exception {
		
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);		
		SsMacStudy ssMacStudy = null;
		try {
			if (object == null) {
				throw new Exception("ssMacStudy is null");
			}
			ssMacStudy=(SsMacStudy) object;
			manufacturer = super.getManufacturer(ssMacStudy.getSiteId());
			if (manufacturer==EManufacturer.WUHAN.getValue()) {
				operationServiceI = new SsmacWHServiceImpl();
				result = operationServiceI.excutionInsert(ssMacStudy);
			}
			
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				List<SsMacStudy> list = new ArrayList<SsMacStudy>();
				if(ssMacStudy != null){
					list.add(ssMacStudy);
				}
				String str = this.getOfflineSiteIdNames(list);
				if(str.equals("")){
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
				}else{
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+str+ResultString.NOT_ONLINE_SUCCESS;
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			operationServiceI = null;
			ssMacStudy = null;
		}
		return result;
		
		
	}

	@Override
	public String excuteUpdate(Object object) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String synchro(int siteId) throws RemoteException, Exception {
		OperationServiceI operationServiceI = null;
		SiteUtil siteUtil = null;
		try 
		{
			// 虚拟网元不同步设备
			siteUtil = new SiteUtil();
			if (0 == siteUtil.SiteTypeUtil(siteId)) {
				if (super.getManufacturer(siteId) == EManufacturer.WUHAN.getValue()) {
					operationServiceI = new SsmacWHServiceImpl();
				} else {

				}
				return (String)operationServiceI.synchro(siteId);
			}
		} catch (Exception e) 
		{
			ExceptionManage.dispose(e, getClass());
		}finally
		{
			siteUtil = null;
			operationServiceI = null;
		}
		return ResultString.QUERY_FAILED;
	}

	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 获取离线网元名称
	 */
	private String getOfflineSiteIdNames(List<SsMacStudy> ssMacStudyList) throws Exception {
		List<Integer> siteIds = null;
		String str = "";
		try {
			siteIds = new ArrayList<Integer>();
			for (SsMacStudy ssMacStudy : ssMacStudyList) {
				siteIds.add(ssMacStudy.getSiteId());
				
			}
			str = new WhImplUtil().getNeNames(siteIds);
		} catch (Exception e) {
			throw e;
		}
		return str;
	}
}
