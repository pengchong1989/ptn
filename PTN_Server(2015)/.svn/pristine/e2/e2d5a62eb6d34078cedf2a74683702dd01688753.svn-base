package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.db.bean.ptn.path.ces.CesInfo;
import com.nms.db.bean.ptn.path.eth.DualInfo;
import com.nms.db.bean.ptn.path.eth.ElanInfo;
import com.nms.db.bean.ptn.path.eth.ElineInfo;
import com.nms.db.bean.ptn.path.eth.EtreeInfo;
import com.nms.db.bean.ptn.path.pw.MsPwInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.EObjectType;
import com.nms.db.enums.EServiceType;
import com.nms.model.ptn.path.ces.CesInfoService_MB;
import com.nms.model.ptn.path.eth.DualInfoService_MB;
import com.nms.model.ptn.path.eth.ElanInfoService_MB;
import com.nms.model.ptn.path.eth.ElineInfoService_MB;
import com.nms.model.ptn.path.eth.EtreeInfoService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.PwCXServiceImpl;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.service.impl.wh.PwWHServiceImpl;
import com.nms.service.notify.Message.MessageType;
import com.nms.ui.manager.BusinessIdException;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class PwDispatch extends DispatchBase implements DispatchInterface{

	@SuppressWarnings("unchecked")
	public String excuteDelete(Object object) throws Exception {
		List<PwInfo> pwList = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		OperationServiceI operationServiceI_wh = null;
		OperationServiceI operationServiceI_cx = null;
		String result_wh = null;
		String result_cx = null;
		PwInfoService_MB pwInfoService = null;
		List<PwInfo> pwInfoList_activate = null;
		pwList = (List<PwInfo>) object;
		String str = getNotOnlineSiteIdNames(pwList);
		try {
			if (pwList != null && pwList.size() > 0) {

				/** 验证pw是否在业务中被引用 */
				if (this.pwIsUsedByService(pwList)) {
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL_PW_USE);
				}
				
				/** 获取已经激活的pw */
				pwInfoList_activate = this.getActivate(pwList);
				if (pwInfoList_activate.size() > 0) {
					
					this.updateStatus(pwInfoList_activate, EActiveStatus.UNACTIVITY.getValue());
					// 下发武汉的
					operationServiceI_wh = new PwWHServiceImpl();
					result_wh = operationServiceI_wh.excutionDelete(pwInfoList_activate);
					// 下发晨晓的
					operationServiceI_cx = new PwCXServiceImpl();
					result_cx = operationServiceI_cx.excutionDelete(pwInfoList_activate);

					// 如果两次下发都成功。就插入直接返回 否则获取失败消息。 并回滚已经成功的
					if (ResultString.CONFIG_SUCCESS.equals(result_cx) && ResultString.CONFIG_SUCCESS.equals(result_wh)) {
						pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
						pwInfoService.delete(pwList);
						result = ResultString.CONFIG_SUCCESS;
					} else {
						this.updateStatus(pwInfoList_activate, EActiveStatus.ACTIVITY.getValue()); // kk

						// 回滚武汉 kk
						for (PwInfo pwInfo : pwInfoList_activate) {
							operationServiceI_wh.excutionInsert(pwInfo);
						}

						// 如果晨晓的成功 新建晨晓。 错误消息返回武汉的
						if (ResultString.CONFIG_SUCCESS.equals(result_cx)) {
							for (PwInfo pwInfo : pwInfoList_activate) {
								operationServiceI_cx.excutionInsert(pwInfo);
							}
							result = result_wh;
						} else {
							result = result_cx;
						}
					}
				} else {
					pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
					pwInfoService.delete(pwList);
					result = ResultString.CONFIG_SUCCESS;
				}

				// pwInfo=pwList.get(0);
				//				
				// manufacturer=super.getManufacturer(pwInfo.getASiteId());
				// if(manufacturer==EManufacturer.WUHAN.getValue()){
				// operationServiceI=new PwWHServiceImpl();
				// }else{
				// operationServiceI=new PwCXServiceImpl();
				// }

			} else {
				throw new Exception("objectList is null");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(pwInfoService);
			operationServiceI_cx = null;
			operationServiceI_wh = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			WhImplUtil whImplUtil = new WhImplUtil();
			for(PwInfo pwInfo : pwList){
				whImplUtil.deleteAlarmAndPerformance(EObjectType.PW, pwInfo.getASiteId(),pwInfo.getApwServiceId());
				whImplUtil.deleteAlarmAndPerformance(EObjectType.PW, pwInfo.getZSiteId(),pwInfo.getZpwServiceId());
			}
			//成功上报操作信息到caoba
			for (PwInfo pwInfo : pwList) {
				if (pwInfo.getIsSingle()>0) {//单网元
					super.notifyCorba("CrossConnection", MessageType.DELETION, pwInfo, ELayerRate.PW.getValue()+"",result);
				}else {//端到端
					super.notifyCorba("SubnetworkConnection", MessageType.DELETION, pwInfo, ELayerRate.PW.getValue()+"",result);
				}
			}
			if(str.equals("")){
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}else{
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+str+ResultString.NOT_ONLINE_SUCCESS;
			}
		} else {
			return result;
		}
	}

	public String excuteInsert(Object object) throws Exception {
		OperationServiceI operationServiceIWH = null;
		PwInfoService_MB pwService = null;
		List <PwInfo> pwInfos = null;
		String result = ResultString.CONFIG_SUCCESS;
		try {

			pwService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			pwInfos = (List <PwInfo>) object;
			
			if (pwInfos.get(0).getPwStatus() == EActiveStatus.ACTIVITY.getValue()) 
			{
				operationServiceIWH = new PwWHServiceImpl();
				if (!isTunnelActive(pwInfos.get(0)))
				{
					result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL_TUNNEL_ACTIVITY);
				}
				else
				{
					for(PwInfo pw:pwInfos)
					{
						pwService.save(pw);
					}
					result = operationServiceIWH.excutionInsert(object);
				}
			} 
			else 
			{
				// 直接插入数据库
				for(PwInfo pw:pwInfos)
				{
					pwService.save(pw);
				}
			}
		} catch (BusinessIdException e) {
			return e.getMessage();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(pwService);
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			List<PwInfo> list = new ArrayList<PwInfo>();
			list.add(pwInfos.get(0));
			String str = getNotOnlineSiteIdNames(list);
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
		PwInfo pwInfo = null;
		PwInfo beforePwInfo = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		PwInfoService_MB pwInfoService = null;
		OperationServiceI operationServiceI_wh = null;
		OperationServiceI operationServiceI_cx = null;
		String result_wh = null;
		String result_cx = null;
		try {
			pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			pwInfo = (PwInfo) object;
			
			// 保存修改以前的对象，失败后回滚用
			beforePwInfo = new PwInfo();
			beforePwInfo.setPwId(pwInfo.getPwId());
			beforePwInfo = pwInfoService.selectBypwid_notjoin(beforePwInfo);
			
			// 两次状态都是为非激活，则不用下设备
			if (beforePwInfo.getPwStatus() == EActiveStatus.UNACTIVITY.getValue() && pwInfo.getPwStatus() == EActiveStatus.UNACTIVITY.getValue()) {
				pwInfoService.update(pwInfo);
				result = ResultString.CONFIG_SUCCESS;
			} else {
				// 去激活操作作校验
				if (pwInfo.getPwStatus() == EActiveStatus.UNACTIVITY.getValue() && this.isUsedByActiveService(beforePwInfo)) {
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL_PW_USE);
				}
				if (pwInfo.getPwStatus() == EActiveStatus.ACTIVITY.getValue() && !isTunnelActive(pwInfo)) {
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL_TUNNEL_ACTIVITY);
				}
			
				// 先修改数据库
				pwInfoService.update(pwInfo);
				// 把之前的激活状态给要修改的pwInfo对象
				pwInfo.setBefore_activity(beforePwInfo.getPwStatus());
				// 下发武汉的
				operationServiceI_wh = new PwWHServiceImpl(); // kk
				result_wh = operationServiceI_wh.excutionUpdate(pwInfo);
				// 下发晨晓的
				operationServiceI_cx = new PwCXServiceImpl(); // kk
				result_cx = operationServiceI_cx.excutionUpdate(pwInfo);
				// 如果两次下发都成功。直接返回 否则获取失败消息。 并回滚已经成功的
				if (ResultString.CONFIG_SUCCESS.equals(result_cx) && ResultString.CONFIG_SUCCESS.equals(result_wh)) {
					result = ResultString.CONFIG_SUCCESS;
				} else {
					// 回滚数据库
					pwInfoService.update(beforePwInfo);
					operationServiceI_wh.excutionUpdate(beforePwInfo); // kk
					operationServiceI_cx.excutionUpdate(beforePwInfo);
					// 如果晨晓的成功 修改晨晓。 错误消息返回武汉的
					if (ResultString.CONFIG_SUCCESS.equals(result_cx)) {
						result = result_wh;
					} else {
						result = result_cx;
					}
				}
			}
			if (result.equals(ResultString.CONFIG_SUCCESS)) {
				//如果修改的字段被修改，则上报消息通知
				reportMsg(pwInfo, beforePwInfo,result);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			beforePwInfo = null;
			UiUtil.closeService_MB(pwInfoService);
			operationServiceI_wh = null;
			operationServiceI_cx = null;
			result_wh = null;
			result_cx = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			List<PwInfo> list = new ArrayList<PwInfo>();
			list.add(pwInfo);
			String str = getNotOnlineSiteIdNames(list);
			if(str.equals("")){
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}else{
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+str+ResultString.NOT_ONLINE_SUCCESS;
			}
		} else {
			return result;
		}
	}

	private boolean isTunnelActive(PwInfo pwInfo) {
		TunnelService_MB tunnelService = null;
		Tunnel tunnel = new Tunnel();
		try {
			tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			if(pwInfo.getTunnelId()>0){
				tunnel.setTunnelId(pwInfo.getTunnelId());
				tunnel = tunnelService.select_nojoin(tunnel).get(0);
			}else{
				for(MsPwInfo msPwInfo : pwInfo.getMsPwInfos()){
					tunnel = tunnelService.selectId(msPwInfo.getBackTunnelId());
					if(tunnel.getTunnelStatus() != EActiveStatus.ACTIVITY.getValue()){
						return false;
					}
					tunnel = tunnelService.selectId(msPwInfo.getFrontTunnelId());
					if(tunnel.getTunnelStatus() != EActiveStatus.ACTIVITY.getValue()){
						return false;
					}
				}
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(tunnelService);
		}
		return tunnel.getTunnelStatus() == EActiveStatus.ACTIVITY.getValue() ? true : false;
	}

	private boolean pwIsUsedByService(List<PwInfo> pwInfoList) {
		PwInfoService_MB pwInfoService = null;
		PwInfo pwInfo = null;
		PwInfo info = null;
		try {
			pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			for (PwInfo obj : pwInfoList) {
				pwInfo = new PwInfo();
				pwInfo.setPwId(obj.getPwId());
				info = pwInfoService.selectBypwid_notjoin(pwInfo);
				if(info.getRelatedServiceId()>0){
					return true;
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(pwInfoService);
		}
		return false;
	}

	private List<PwInfo> getActivate(List<PwInfo> pwInfoList) {
		List<PwInfo> pwActivate = new ArrayList<PwInfo>();
		for (PwInfo pw : pwInfoList) {
			if (pw.getPwStatus() == EActiveStatus.ACTIVITY.getValue()) {
				pwActivate.add(pw);
			}
		}
		return pwActivate;
	}

	/**
	 * 修改pw状态
	 * 
	 * @param pwList
	 *            pwinfo集合
	 * @param status
	 *            2为修改成删除 1为修改成激活
	 * @throws Exception
	 */
	private void updateStatus(List<PwInfo> pwInfoList, int status) throws Exception {
		List<Integer> pwInfoIds = null;
		PwInfoService_MB pwInfoService = null;

		try {
			pwInfoIds = new ArrayList<Integer>();
			for (PwInfo pwInfo : pwInfoList) {
				pwInfoIds.add(pwInfo.getPwId());
			}

			pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);

			if (EActiveStatus.ACTIVITY.getValue() == status) {
				
				pwInfoService.updateActiveStatus(pwInfoIds, status);
				
				
			} else if (EActiveStatus.UNACTIVITY.getValue() == status) {
				
				
				pwInfoService.updateActiveStatus(pwInfoIds, status);
				
			} else {
				throw new Exception("status is error");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			pwInfoIds = null;
			UiUtil.closeService_MB(pwInfoService);
		}
	}

	private boolean isUsedByActiveService(PwInfo pw) {
		int serviceType = pw.getRelatedServiceType();

		ElineInfoService_MB elineService = null;
		EtreeInfoService_MB etreeService = null;
		ElanInfoService_MB elanService = null;
		CesInfoService_MB cesService = null;

		ElineInfo eline = null;
		CesInfo ces = null;

		List<ElineInfo> elineList = null;
		List<EtreeInfo> etreeList = null;
		List<ElanInfo> elanList = null;
		List<CesInfo> cesList = null;

		DualInfoService_MB dualInfoService = null;
		try {
			if (EServiceType.ELINE.getValue() == serviceType) {
				elineService = (ElineInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Eline);
				eline = new ElineInfo();
				eline.setPwId(pw.getPwId());
				elineList = elineService.selectByPwId(eline);
				for (ElineInfo obj : elineList) {
					if (obj.getActiveStatus() == EActiveStatus.ACTIVITY.getValue())
						return true;
				}
			} else if (EServiceType.ETREE.getValue() == serviceType) {
				etreeService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
				List<Integer> integers = new ArrayList<Integer>();
				integers.add(pw.getPwId());
				etreeList = etreeService.selectEtreeByPwId(integers);
				for (EtreeInfo obj : etreeList) {
					if (obj.getActiveStatus() == EActiveStatus.ACTIVITY.getValue())
						return true;
				}
			} else if (EServiceType.ELAN.getValue() == serviceType) {
				elanService = (ElanInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ElanInfo);
				List<Integer> integers = new ArrayList<Integer>();
				integers.add(pw.getPwId());
				elanList = elanService.selectElanbypwid(integers);
				for (ElanInfo obj : elanList) {
					if (obj.getActiveStatus() == EActiveStatus.ACTIVITY.getValue())
						return true;
				}
			}else if (EServiceType.CES.getValue() == serviceType) {
				 cesService = (CesInfoService_MB)ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);
				 ces = new CesInfo();
				 ces.setPwId(pw.getPwId());
				 cesList = cesService.selectByPwId(ces);
				 for(CesInfo obj : cesList){
					 if(obj.getActiveStatus()==EActiveStatus.ACTIVITY.getValue())
						 return true;
				 }
			}else if(EServiceType.DUAL.getValue() == serviceType){
				dualInfoService = (DualInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.DUALINFO);
				List<Integer> integers = new ArrayList<Integer>();
				integers.add(pw.getPwId());
				List<DualInfo> dualInfos = dualInfoService.selectByPwIDs(integers);
				 for(DualInfo obj : dualInfos){
					 if(obj.getActiveStatus()==EActiveStatus.ACTIVITY.getValue())
						 return true;
				 }
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(elanService);
			UiUtil.closeService_MB(etreeService);
			UiUtil.closeService_MB(elineService);
			UiUtil.closeService_MB(cesService);
			UiUtil.closeService_MB(dualInfoService);
		}
		return false;
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
		try {
			//虚拟网元不同步设备
			SiteUtil siteUtil=new SiteUtil();
			if(0==siteUtil.SiteTypeUtil(siteId)){
				if (super.getManufacturer(siteId) == EManufacturer.WUHAN.getValue()) {
					operationServiceI = new PwWHServiceImpl();
				} else {
					operationServiceI = new PwCXServiceImpl();
				}
				return (String)operationServiceI.synchro(siteId);
			}
		} catch (Exception e) {
			throw e;
		}
		return ResultString.QUERY_FAILED;
	}
	
	/**
	 * 判断是否符合修改上报
	 * @param pwInfo 要修改的pwInfo
	 * @param beforePwInfo 原有的pwInfo
	 * @param result 是否上报标识
	 * @throws Exception 
	 */
	private void reportMsg(PwInfo pwInfo,PwInfo beforePwInfo,String result) throws Exception{
		Map<String, String> attributeMap = new HashMap<String, String>();//上报的修改的属性集合
		Map<String, String> stateMap = new HashMap<String, String>();//上报的状态的属性集合
		if (null != pwInfo.getPwName()&& null!= beforePwInfo.getPwName() && !beforePwInfo.getPwName().equals(pwInfo.getPwName())) {
			attributeMap.put("userLabel", pwInfo.getPwName());
		}
		if (pwInfo.getPwStatus() != beforePwInfo.getPwStatus()) {
			stateMap.put("status", pwInfo.getPwStatus()+"");
		}
		//成功上报操作信息到caoba
		try {
			if (attributeMap.size()>0) {
				attributeMap.put("id", pwInfo.getPwId()+"");
				if (pwInfo.getIsSingle()>0) {//单网元
					if (pwInfo.getASiteId()>0) {
						attributeMap.put("siteId", pwInfo.getASiteId()+"");
					}else if (pwInfo.getZSiteId()>0) {
						attributeMap.put("siteId", pwInfo.getZSiteId()+"");
					}
					super.notifyCorba("CrossConnection", MessageType.ATTRIBUTECHG, attributeMap, ELayerRate.PW.getValue()+"",result);
				}else {//端到端
					super.notifyCorba("SubnetworkConnection", MessageType.ATTRIBUTECHG, attributeMap, ELayerRate.PW.getValue()+"",result);
				}
			}
			//状态修改上报
			if (stateMap.size()>0) {
				stateMap.put("id", pwInfo.getPwId()+"");
				if (pwInfo.getIsSingle()>0) {//单网元
					if (pwInfo.getASiteId()>0) {
						stateMap.put("siteId", pwInfo.getASiteId()+"");
					}else if (pwInfo.getZSiteId()>0) {
						stateMap.put("siteId", pwInfo.getZSiteId()+"");
					}
					super.notifyCorba("CrossConnection", MessageType.STATECHG, stateMap, ELayerRate.PW.getValue()+"",result);
				}else {//端到端
					super.notifyCorba("SubnetworkConnection", MessageType.STATECHG , stateMap, ELayerRate.PW.getValue()+"",result);
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		try {
			if(super.getManufacturer(siteId) == EManufacturer.WUHAN.getValue()){
				PwWHServiceImpl	operationServiceI = new PwWHServiceImpl();
				return operationServiceI.consistence(siteId);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return null;
	}
	
	private String getNotOnlineSiteIdNames(List<PwInfo> pwInfoActivate) throws Exception {
		List<Integer> siteIds = new ArrayList<Integer>();
		String str = "";
		try {
			for (PwInfo pw : pwInfoActivate) {
				if (!siteIds.contains(pw.getASiteId()) && pw.getASiteId() > 0 && super.getManufacturer(pw.getASiteId()) == EManufacturer.WUHAN.getValue()) {
					siteIds.add(pw.getASiteId());
				}
				if (!siteIds.contains(pw.getZSiteId()) && pw.getZSiteId() > 0 && EManufacturer.WUHAN.getValue() == super.getManufacturer(pw.getZSiteId()))// 单点对象的Z端不用下发
				{
					siteIds.add(pw.getZSiteId());
				}
				if (pw.getMsPwInfos() != null && pw.getMsPwInfos().size() > 0) {
					for (MsPwInfo msPwInfo : pw.getMsPwInfos()) {
						if (!siteIds.contains(msPwInfo.getSiteId())) {
							siteIds.add(msPwInfo.getSiteId());
						}
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
		WhImplUtil whImplUtil = new WhImplUtil();
		str = whImplUtil.getNeNames(siteIds);
		return str;
	}
	
}
