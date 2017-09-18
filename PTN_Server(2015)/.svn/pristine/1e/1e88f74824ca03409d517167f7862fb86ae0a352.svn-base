package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.oam.OamLinkInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.OamTypeEnum;
import com.nms.model.ptn.oam.OamInfoService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.ETHLinkOamNodeCXServiceImpl;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.service.impl.wh.ETHLinkOAMConfigWHServiceImpl;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class ETHLinkOAMConfigDispath extends DispatchBase implements DispatchInterface{

	public String excuteInsert(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String whResult = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		OamInfoService_MB oamInfoService = null;
		OamInfo oamInfo = null;
		try {
			if (object == null) {
				throw new Exception("oamLinkInfo is null");
			}
			oamInfo=(OamInfo)object;
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_INSERT,oamInfo);
			if(null!=siteCheck){
				List<Integer> siteList = new ArrayList<Integer>();
				siteList.add(oamInfo.getOamLinkInfo().getSiteId());
				WhImplUtil whImplUtil = new WhImplUtil();
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+whImplUtil.getNeNames(siteList)+ResultString.NOT_ONLINE_SUCCESS;
			}
			oamInfoService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
			if(!oamInfo.isDataDownLoad()){ //如果是数据下载业务不进行数据库操作
				oamInfoService.saveOrUpdate(oamInfo);
			}
			manufacturer = super.getManufacturer(oamInfo.getOamLinkInfo().getSiteId());
			if (manufacturer==EManufacturer.WUHAN.getValue()) {
				operationServiceI = new ETHLinkOAMConfigWHServiceImpl();
			} else {
				operationServiceI = new ETHLinkOamNodeCXServiceImpl();
			}
			whResult=operationServiceI.excutionInsert(oamInfo);
		} catch (Exception e) {
			oamInfoService.delete(oamInfo);
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(oamInfoService);
		}
		if (ResultString.CONFIG_SUCCESS.equals(whResult)) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		} else {
			return whResult;
		}
	}

	@Override
	public String excuteDelete(Object object) throws RemoteException, Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String whResult = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		OamInfoService_MB oamInfoService = null;
		List<OamInfo> oamInfos = null;
		try {
			if (object == null) {
				throw new Exception("oamLinkInfo is null");
			}
			oamInfos=(List<OamInfo>)object;
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_DELETE,oamInfos);
			if(null!=siteCheck){
				List<Integer> siteList = new ArrayList<Integer>();
				siteList.add(oamInfos.get(0).getOamLinkInfo().getSiteId());
				WhImplUtil whImplUtil = new WhImplUtil();
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+whImplUtil.getNeNames(siteList)+ResultString.NOT_ONLINE_SUCCESS;
			}
			oamInfoService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
			oamInfoService.delete(oamInfos);
			manufacturer = super.getManufacturer(oamInfos.get(0).getOamLinkInfo().getSiteId());
			if (manufacturer==EManufacturer.WUHAN.getValue()) {
				operationServiceI = new ETHLinkOAMConfigWHServiceImpl();
			} else {
				operationServiceI = new ETHLinkOamNodeCXServiceImpl();
			}
			whResult=operationServiceI.excutionDelete(oamInfos);
			
			if (ResultString.CONFIG_SUCCESS.equals(whResult)) {
				if(!oamInfos.get(0).isDataDownLoad()){ //如果是数据下载业务不进行数据库操作
					oamInfoService.delete(oamInfos);
				}
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			} else {
				for(OamInfo info : oamInfos){
					oamInfoService.saveOrUpdate(info);
				}
				return whResult;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(oamInfoService);
		}
		return whResult;
	}

	@Override
	public String excuteUpdate(Object object) throws RemoteException, Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String whResult = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		OamInfoService_MB oamInfoService = null;
		OamInfo oamInfo = null;
		OamInfo oamInfo_before=null;
		OamLinkInfo oamLinkInfo = new OamLinkInfo();
		try {
			if (object == null) {
				throw new Exception("oamLinkInfo is null");
			}
			oamInfo=(OamInfo)object;
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_UPDATE,oamInfo);
			if(null!=siteCheck){
				List<Integer> siteList = new ArrayList<Integer>();
				siteList.add(oamInfo.getOamLinkInfo().getSiteId());
				WhImplUtil whImplUtil = new WhImplUtil();
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+whImplUtil.getNeNames(siteList)+ResultString.NOT_ONLINE_SUCCESS;
			}
			oamInfoService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
			oamInfo_before = new OamInfo();
			oamLinkInfo.setSiteId(oamInfo.getOamLinkInfo().getSiteId());
			oamLinkInfo.setObjId(oamInfo.getOamLinkInfo().getObjId());
			oamInfo_before.setOamLinkInfo(oamLinkInfo);
			oamInfo_before = oamInfoService.queryByCondition(oamInfo_before, OamTypeEnum.LINKOAM);
			
			if(!oamInfo.isDataDownLoad()){ //如果是数据下载业务不进行数据库操作
				oamInfoService.saveOrUpdate(oamInfo);
			}
			manufacturer = super.getManufacturer(oamInfo.getOamLinkInfo().getSiteId());
			if (manufacturer==EManufacturer.WUHAN.getValue()) {
				operationServiceI = new ETHLinkOAMConfigWHServiceImpl();
			} else {
				operationServiceI = new ETHLinkOamNodeCXServiceImpl();
			}
			whResult=operationServiceI.excutionUpdate(oamInfo);
		} catch (Exception e) {
			oamInfoService.saveOrUpdate(oamInfo_before);
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(oamInfoService);
		}
		if (ResultString.CONFIG_SUCCESS.equals(whResult)) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		} else {
			return whResult;
		}
	}

	@Override
	public String synchro(int siteId) throws RemoteException, Exception {
		OperationServiceI operationServiceI = null;
		try {
			//虚拟网元操作
			SiteUtil siteUtil=new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteSynchroAction(TypeAndActionUtil.ACTION_SYNCHRO,siteId);
			if(null!=siteCheck){
				List<Integer> siteList = new ArrayList<Integer>();
				siteList.add(siteId);
				WhImplUtil whImplUtil = new WhImplUtil();
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+whImplUtil.getNeNames(siteList)+ResultString.NOT_ONLINE_SUCCESS;
			}
			if (super.getManufacturer(siteId) == EManufacturer.WUHAN.getValue()) {
				operationServiceI = new ETHLinkOAMConfigWHServiceImpl();
			} else {
				operationServiceI = new ETHLinkOamNodeCXServiceImpl();
			}
			return (String)operationServiceI.synchro(siteId);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
		return ResultString.QUERY_FAILED;
	}

	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
