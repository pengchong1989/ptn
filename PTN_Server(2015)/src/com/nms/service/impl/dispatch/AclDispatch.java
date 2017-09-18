package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.AclInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.model.ptn.AclService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.OamCXServiceImpl;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.service.impl.wh.AclWHServiceImpl;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class AclDispatch extends DispatchBase implements DispatchInterface{

	@Override
	public String excuteDelete(Object object) throws RemoteException, Exception {
		List<AclInfo> AclInfoList = null;
		AclInfo aclInfo = null;
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			AclInfoList = (List<AclInfo>) object;
			if (AclInfoList != null && AclInfoList.size() > 0) {
				aclInfo = AclInfoList.get(0);
				manufacturer = super.getManufacturer(aclInfo.getSiteId());
				if (manufacturer == EManufacturer.WUHAN.getValue()) {
					operationServiceI=new AclWHServiceImpl();
				} else {
					operationServiceI = new OamCXServiceImpl();
				}
				result = operationServiceI.excutionDelete(AclInfoList);
			} else {
				throw new Exception("objectList is null");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			operationServiceI = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			WhImplUtil whImplUtil = new WhImplUtil();
			return whImplUtil.offLineResult(object);
//			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		}else{
			return result;
		}
	}

	@Override
	public String excuteInsert(Object object) throws RemoteException, Exception {
		AclService_MB aclService = null;
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		AclInfo aclInfo = null;
		AclInfo aclInfoBefore = null;
		try {
			if (object == null) {
				throw new Exception("aclInfo is null");
			}
			aclInfo = (AclInfo) object;
			manufacturer = super.getManufacturer(aclInfo.getSiteId());
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				SiteUtil siteUtil = new SiteUtil();
				//虚拟网元操作
				List<AclInfo> aclList = new ArrayList<AclInfo>();
				aclList.add(aclInfo);
				String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_SAVEANDUPDATE, aclList);
				if(null != siteCheck){
					result = ResultString.CONFIG_SUCCESS;
				}else{
					aclService = (AclService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ACLSERCVICE);
					if(aclInfo.getId() > 0){
						aclInfoBefore = aclService.select(aclInfo.getSiteId()).get(0);
						//先修改数据库，根据结果来判断是否需要回滚
						aclService.update(aclInfo);
					}else{
						//先修改数据库，根据结果来判断是否需要回滚
						aclService.save(aclInfo);
					}
					operationServiceI = new AclWHServiceImpl();
					result = operationServiceI.excutionInsert(aclInfo);
					if(!ResultString.CONFIG_SUCCESS.equals(result)){
						//回滚
						if(aclInfoBefore != null){
							aclService.update(aclInfoBefore);
						}
						return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
					}else{
						String str = this.getOfflineSiteNames(aclInfo);
						if(str.equals("")){
							return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
						}else{
							return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+str+ResultString.NOT_ONLINE_SUCCESS;
						}
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(aclService);
		}
		return result;
	}
	
	private String getOfflineSiteNames(AclInfo aclInfo) throws Exception {
		String str = "";
		List<Integer> siteIds = new ArrayList<Integer>();
		siteIds.add(aclInfo.getSiteId());
		str = new WhImplUtil().getNeNames(siteIds);
		return str;
	}

	@Override
	public String excuteUpdate(Object object) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String synchro(int siteId) throws RemoteException, Exception {
		OperationServiceI operationServiceI = null;
		try {
			//虚拟网元不同步设备
			SiteUtil siteUtil=new SiteUtil();
			if(0==siteUtil.SiteTypeUtil(siteId)){
				if (super.getManufacturer(siteId) == EManufacturer.WUHAN.getValue()) {
					operationServiceI = new AclWHServiceImpl();
				} else {
					
				}
				return (String)operationServiceI.synchro(siteId);
			}
		} catch (Exception e) {
			throw e;
		}
		return ResultString.QUERY_FAILED;
	}

	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
