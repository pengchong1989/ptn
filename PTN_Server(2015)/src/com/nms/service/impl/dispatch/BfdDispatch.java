package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.BfdInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.service.impl.wh.BfdWHServiceImpl;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class BfdDispatch extends DispatchBase implements DispatchInterface{
	@SuppressWarnings("unchecked")
	public String excuteDelete(Object object) throws Exception {
		List<BfdInfo> bfdList = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		OperationServiceI operationServiceI = null;
		String result_wh = null;		
		int manufacturer = 0;
		try {
			if (object != null ) {
				bfdList = (List<BfdInfo>) object;				
				//虚拟网元操作
				SiteUtil siteUtil = new SiteUtil();
				String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_DELETE,bfdList);
				if(null!=siteCheck){
					return siteCheck;
				}
				//通过网元ID判断设备类型
				if(null!=bfdList&&bfdList.size()>0){
					manufacturer = super.getManufacturer(bfdList.get(0).getSiteId());
					if (manufacturer == EManufacturer.WUHAN.getValue()) {
						//武汉设备转换
						operationServiceI = new BfdWHServiceImpl();
					} else {
						//operationServiceI = new DualCXServiceImpl();
					}
					result = operationServiceI.excutionDelete(bfdList);
					if (ResultString.CONFIG_SUCCESS.equals(result)) {
						result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
					}
				} 
			}else {
				throw new Exception("bfdList is null");
			}
			if (ResultString.CONFIG_SUCCESS.equals(result)) {		
				String str = getOfflineSiteIdNames(bfdList.get(0).getSiteId());
				if(str.equals("")){
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
				}else{
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+str+ResultString.NOT_ONLINE_SUCCESS;
				}
			} else {
				return result;
			}		
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			bfdList = null;
			operationServiceI = null;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public String excuteInsert(Object object) throws Exception {
		
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);		
		BfdInfo bfdInfo = null;
		try {
			if (object == null) {
				throw new Exception("bdfInfo is null");
			}
			bfdInfo=(BfdInfo) object;
			manufacturer = super.getManufacturer(bfdInfo.getSiteId());
			if (manufacturer==EManufacturer.WUHAN.getValue()) {
				operationServiceI = new BfdWHServiceImpl();
				result = operationServiceI.excutionInsert(bfdInfo);
			}
			
			if (ResultString.CONFIG_SUCCESS.equals(result)) {		
				String str = getOfflineSiteIdNames(bfdInfo.getSiteId());
				if(str.equals("")){
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
				}else{
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+str+ResultString.NOT_ONLINE_SUCCESS;
				}
			} else {
				return result;
			}		
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			operationServiceI = null;
			bfdInfo = null;
		}
		return result;
	}


	public String excuteUpdate(Object object) throws Exception {		
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);		
		BfdInfo bfdInfo = null;
		try {
			if (object == null) {
				throw new Exception("bdfInfo is null");
			}
			bfdInfo=(BfdInfo) object;
			manufacturer = super.getManufacturer(bfdInfo.getSiteId());
			if (manufacturer==EManufacturer.WUHAN.getValue()) {
				operationServiceI = new BfdWHServiceImpl();
				result = operationServiceI.excutionUpdate(bfdInfo);
			}
			
			if (ResultString.CONFIG_SUCCESS.equals(result)) {		
				String str = getOfflineSiteIdNames(bfdInfo.getSiteId());
				if(str.equals("")){
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
				}else{
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+str+ResultString.NOT_ONLINE_SUCCESS;
				}
			} else {
				return result;
			}		
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			operationServiceI = null;
			bfdInfo = null;
		}
		return result;
	}

		
	/**
	 * synchro(根据网元id同步)
	 * @return
	 * @throws Exception
	 */
	public String synchro(int siteId) throws Exception {
		OperationServiceI operationServiceI = null;
		try {
			SiteUtil siteUtil=new SiteUtil();
			if(0==siteUtil.SiteTypeUtil(siteId)){
				if (super.getManufacturer(siteId) == EManufacturer.WUHAN.getValue()) {
					operationServiceI = new BfdWHServiceImpl();
				} else {
					//operationServiceI = new BfdCXServiceImpl();
				}
				return (String)operationServiceI.synchro(siteId);
			}
		} catch (Exception e) {
			throw e;
		}
		
		return ResultString.QUERY_FAILED; 
	}
			
	/**
	 * 获取离线网元名称
	 */
	private String getOfflineSiteIdNames(int siteId) throws Exception {
		List<Integer> siteIds = null;
		String str = "";
		try {
			siteIds = new ArrayList<Integer>();		
			siteIds.add(siteId);			
			str = new WhImplUtil().getNeNames(siteIds);
		} catch (Exception e) {
			throw e;
		}
		return str;
	}

	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
