package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.path.eth.DualInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EManufacturer;
import com.nms.model.ptn.path.eth.DualInfoService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.service.impl.wh.DualWHServceImpl;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class DualWhDispatch extends DispatchBase implements DispatchInterface{

	@Override
	public String excuteDelete(Object object) throws RemoteException, Exception {
		
		DualInfoService_MB dualInfoService = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		List<DualInfo> dualInfoList = null;
		OperationServiceI operationServiceI = null;
		
		try {
			dualInfoList = (List<DualInfo>)object;
			dualInfoService = (DualInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.DUALINFO);
			//虚拟网元操作
			if(1==dualInfoList.get(0).getIsSingle()){
				SiteUtil siteUtil = new SiteUtil();
				String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_DELETE,dualInfoList);
				if(null!=siteCheck){
					return restult(dualInfoList);
				}
			}
			if(dualInfoList.get(0).getActiveStatus() == EActiveStatus.UNACTIVITY.getValue()){
				dualInfoService.delete(dualInfoList);
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}else{
				
			}
			operationServiceI = new DualWHServceImpl();
			dualInfoService.delete(dualInfoList);
			result = operationServiceI.excutionDelete(dualInfoList);
			if(ResultString.CONFIG_SUCCESS.equals(result)){
				return restult(dualInfoList);
			}else{
				dualInfoService.insert(dualInfoList);
			}
			
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(dualInfoService);
		}
		return result;
	}

	@Override
	public String excuteInsert(Object object) throws RemoteException, Exception {
		
		DualInfoService_MB dualInfoService = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		List<DualInfo> dualInfoList = null;
		OperationServiceI operationServiceI = null;
		
		try {
			dualInfoList = (List<DualInfo>)object;
			dualInfoService = (DualInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.DUALINFO);
			//虚拟网元操作
			if(1==dualInfoList.get(0).getIsSingle()){
				SiteUtil siteUtil = new SiteUtil();
				String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_INSERT,dualInfoList);
				if(siteCheck != null)
				{
					return restult(dualInfoList);
				}
			}
			if(dualInfoList.get(0).getActiveStatus() == EActiveStatus.UNACTIVITY.getValue()){
				dualInfoService.insert(dualInfoList);
				return ResultString.CONFIG_SUCCESS;
			}
			if(pwIsActive(dualInfoList)){
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL_PW_ACTIVITY);
			}
			dualInfoService.insert(dualInfoList);
			operationServiceI = new DualWHServceImpl();
			result = operationServiceI.excutionInsert(dualInfoList);
			
			if(ResultString.CONFIG_SUCCESS.equals(result)){
				return restult(dualInfoList);
			}else{
				dualInfoService.delete(dualInfoList);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(dualInfoService);
		}
		return result;
	}

	@Override
	public String excuteUpdate(Object object) throws RemoteException, Exception {
		DualInfoService_MB dualInfoService = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		List<DualInfo> dualInfoList = null;
		List<DualInfo> beforeDualInfoList = null;
		OperationServiceI operationServiceI = null;
		try {
			dualInfoList = (List<DualInfo>) object;
			dualInfoService = (DualInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.DUALINFO);
			beforeDualInfoList = dualInfoService.select(dualInfoList.get(0));
			//虚拟网元操作
			if(1==dualInfoList.get(0).getIsSingle()){
				SiteUtil siteUtil = new SiteUtil();
				String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_UPDATE,dualInfoList);
				if(siteCheck != null)
				{
					return restult(dualInfoList);
				}
			}
			if(pwIsActive(dualInfoList)){
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL_PW_ACTIVITY);
			}
			dualInfoService.update(dualInfoList);
			operationServiceI = new DualWHServceImpl();
			result = operationServiceI.excutionUpdate(dualInfoList);
			if(ResultString.CONFIG_SUCCESS.equals(result)){
				return restult(dualInfoList);
			}else{
				dualInfoService.update(beforeDualInfoList);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(dualInfoService);
		}
		return result;
	}

	@Override
	public String synchro(int siteId) throws RemoteException, Exception 
	{
		OperationServiceI operationServiceI = null;
		SiteUtil siteUtil = null;
		try 
		{
			// 虚拟网元不同步设备
			siteUtil = new SiteUtil();
			if (0 == siteUtil.SiteTypeUtil(siteId)) {
				if (super.getManufacturer(siteId) == EManufacturer.WUHAN.getValue()) {
					operationServiceI = new DualWHServceImpl();
				} else {
//					operationServiceI = new ElineCXServiceImpl();
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

	private String getNotOnlineSiteIdNames(List<DualInfo> dualInfos){
		List<Integer> siteIds = new ArrayList<Integer>();
		String str = "";
		for(DualInfo dualInfo : dualInfos){
			if(dualInfo.getRootSite()>0){
				if(!siteIds.contains(dualInfo.getRootSite())){
					siteIds.add(dualInfo.getRootSite());
				}
			}
			if(dualInfo.getBranchMainSite()>0){
				if(!siteIds.contains(dualInfo.getBranchMainSite())){
					siteIds.add(dualInfo.getBranchMainSite());
				}
			}
			if(dualInfo.getBranchProtectSite()>0){
				if(!siteIds.contains(dualInfo.getBranchProtectSite())){
					siteIds.add(dualInfo.getBranchProtectSite());
				}
			}
		}
		WhImplUtil whImplUtil = new WhImplUtil();
		str = whImplUtil.getNeNames(siteIds);
		return str;
	}
	
	private String restult(List<DualInfo> dualInfoList)
	{
		String str = getNotOnlineSiteIdNames(dualInfoList);
		if(str.equals("")){
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		}else{
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+str+ResultString.NOT_ONLINE_SUCCESS;
		}
	}
	
	/**
	 * 验证pw已激活
	 * @param dualInfoList
	 * @return
	 */
	private boolean pwIsActive(List<DualInfo> dualInfoList){
		PwInfoService_MB pwInfoService = null;
		try {
			pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			for(DualInfo dualInfo :dualInfoList){
				PwInfo pwInfo = pwInfoService.selectByPwId(dualInfo.getPwId());
				if(pwInfo.getPwStatus() == EActiveStatus.UNACTIVITY.getValue()){
					return true;
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(pwInfoService);
		}
		return false;
	}
}
