package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.oam.OamMepInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.OamTypeEnum;
import com.nms.model.ptn.oam.OamInfoService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.service.impl.wh.TMCOAMCongigWHServiceImpl;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class TMCOAMConfigDispath extends DispatchBase implements DispatchInterface {
	public String excuteInsert(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		OamInfo oamInfo=null;
		try {
			if (object == null) {
				throw new Exception("oamInfo is null");
			}
			oamInfo=(OamInfo) object;
			
			// 虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck = (String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_SAVEANDUPDATE, oamInfo);
			if (null != siteCheck) {
				result = ResultString.CONFIG_SUCCESS;
			}else{
				manufacturer = super.getManufacturer(oamInfo.getOamMep().getSiteId());
				
				if (manufacturer == EManufacturer.WUHAN.getValue()) {
					operationServiceI = new TMCOAMCongigWHServiceImpl();
				} else {
				}
				result = operationServiceI.excutionInsert(oamInfo);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			List<OamInfo> list = new ArrayList<OamInfo>();
			if(oamInfo != null){
				list.add(oamInfo);
			}
			String str = this.getOfflineSiteIdNames(list);
			if(str.equals("")){
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}else{
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+str+ResultString.NOT_ONLINE_SUCCESS;
			}
		} else {
			return result;
		}
	}

	@SuppressWarnings("unchecked")
	public String excuteDelete(Object object) throws Exception {
		List<OamInfo> oamInfoList = null;
		OamInfo oamInfo = null;
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			oamInfoList = (List<OamInfo>) object;
			// 虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck = (String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_DELETE, oamInfoList);
			if (null != siteCheck) {
				result = ResultString.CONFIG_SUCCESS;
			}else{
				if (oamInfoList != null && oamInfoList.size() > 0) {
					oamInfo = oamInfoList.get(0);
					
					manufacturer = super.getManufacturer(oamInfo.getOamMep().getSiteId());
					if (manufacturer == EManufacturer.WUHAN.getValue()) {
						operationServiceI = new TMCOAMCongigWHServiceImpl();
					} else {
						// operationServiceI = new OamCXServiceImpl();
					}
					result = operationServiceI.excutionDelete(oamInfoList);
				} else {
					throw new Exception("objectList is null");
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			operationServiceI = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			List<OamInfo> list = new ArrayList<OamInfo>();
			if (oamInfoList != null && oamInfoList.size() > 0) {
				list.addAll(oamInfoList);
			}
			String str = this.getOfflineSiteIdNames(list);
			if(str.equals("")){
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}else{
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+str+ResultString.NOT_ONLINE_SUCCESS;
			}
		} else {
			return result;
		}
	}

	public String excuteUpdate(Object object) throws Exception {
		OamInfo oamInfo = null;
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		OamInfoService_MB oamInfoService = null;
		OamInfo oamInfoBefore = null;
		try {
			oamInfo = (OamInfo) object;
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_SAVEANDUPDATE,oamInfo);
			if(null != siteCheck){
				result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}else{
				manufacturer = super.getManufacturer(oamInfo.getOamMep().getSiteId());
				if (manufacturer == EManufacturer.WUHAN.getValue()) {
					oamInfoService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
					oamInfoBefore = oamInfoService.queryByCondition(oamInfo, OamTypeEnum.AMEP);
					//先修改数据库，根据结果来判断是否需要回滚
					oamInfoService.saveOrUpdate(oamInfo);
					operationServiceI = new TMCOAMCongigWHServiceImpl();
					result = operationServiceI.excutionUpdate(oamInfo);
					if(!ResultString.CONFIG_SUCCESS.equals(result)){
						//回滚
						oamInfoService.saveOrUpdate(oamInfoBefore);
					}
				}
			}
		} finally {
			UiUtil.closeService_MB(oamInfoService);
		}
		if(ResultString.CONFIG_SUCCESS.equals(result)){
			String str = this.getOfflineSiteNames(oamInfo);
			if(!str.equals("")){
				result = result+","+str+ResultString.NOT_ONLINE_SUCCESS;
			}
		}
		return result;
	}

	private String getOfflineSiteNames(OamInfo oamInfo) {
		String str = "";
		List<Integer> siteIds = new ArrayList<Integer>();
		siteIds.add(oamInfo.getOamMep().getSiteId());
		str = new WhImplUtil().getNeNames(siteIds);
		return str;
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
	
	private String getOfflineSiteIdNames(List<OamInfo> list) throws Exception {
		List<Integer> siteIds = null;
		String str = "";
		try {
			siteIds = new ArrayList<Integer>();
			for (OamInfo oam : list) {
				OamMepInfo mep = oam.getOamMep();
				if(mep != null){
					siteIds.add(mep.getSiteId());
				}
			}
			str = new WhImplUtil().getNeNames(siteIds);
		} catch (Exception e) {
			throw e;
		}
		return str;
	}
}
