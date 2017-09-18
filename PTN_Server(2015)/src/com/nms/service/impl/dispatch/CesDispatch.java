package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.db.bean.ptn.path.ces.CesInfo;
import com.nms.db.bean.ptn.path.eth.ElineInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.EServiceType;
import com.nms.model.ptn.path.ces.CesInfoService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.CesCXServiceImpl;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.service.impl.wh.ElineWHServiceImpl;
import com.nms.service.notify.Message.MessageType;
import com.nms.ui.manager.BusinessIdException;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class CesDispatch extends DispatchBase implements DispatchInterface {
	
	public String excuteUpdate(Object object) throws Exception {
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		CesInfoService_MB cesInfoService = null;
		List<Integer> pwIdList = new ArrayList<Integer>();
		OperationServiceI operationServiceI_wh = null;
		OperationServiceI operationServiceI_cx = null;
		String result_wh =  null; 
		String result_cx =  null;
		CesInfo cesInfo = null;
		CesInfo beforeCesInfo = null;
		try {
			cesInfo = (CesInfo) object;
			
			if (null != cesInfo ) {
				cesInfoService = (CesInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);

				beforeCesInfo = new CesInfo();
				beforeCesInfo.setId(cesInfo.getId());
				beforeCesInfo = cesInfoService.selectServiceInfoById(cesInfo.getId());
				
				cesInfo.setBeforeActiveStatus(beforeCesInfo.getActiveStatus());
				
				
				/** 如果是激活状态 就下发到设备 */
				if (!(beforeCesInfo.getActiveStatus() == EActiveStatus.UNACTIVITY.getValue() && cesInfo.getActiveStatus() == EActiveStatus.UNACTIVITY.getValue())) {
					// 获取所有pw
					pwIdList.add(cesInfo.getPwId());
					if (cesInfo.getActiveStatus() == EActiveStatus.ACTIVITY.getValue() && !isPwActive(pwIdList)) {
						result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL_PW_ACTIVITY);
					} else {
						//先入库
						cesInfoService.update(cesInfo);
						
						// 下发武汉的 
						operationServiceI_wh = new ElineWHServiceImpl();
						result_wh = operationServiceI_wh.excutionUpdate(this.cesInfoTOElineInfo(cesInfo));
						// 下发晨晓的
						operationServiceI_cx = new CesCXServiceImpl();
						result_cx = operationServiceI_cx.excutionUpdate(cesInfo);
						// 如果两次下发都成功。就插入直接返回 否则获取失败消息。 并回滚已经成功的
						if (ResultString.CONFIG_SUCCESS.equals(result_cx) && ResultString.CONFIG_SUCCESS.equals(result_wh)) {
							// 插入数据库
							result = ResultString.CONFIG_SUCCESS;
						} else {
							// 修改数据库状态
							cesInfo.setActiveStatus(EActiveStatus.UNACTIVITY.getValue());
							cesInfoService.update(cesInfo);
							// // 回滚武汉配置
							// operationServiceI_wh.excutionDelete(cesInfoList);
							// // 如果晨晓的成功。 错误消息返回武汉的
							// if (TipMessageUtil.CONFIG_SUCCESS.equals(result_cx)) {
							// //因为晨晓不是全量数据所以在内部做回滚
							// operationServiceI_cx.excutionDelete(cesInfoList);
							// result = result_wh;
							// } else {
							// result = result_cx;
							// }
							result = result_wh;
						}
					}
				} else {
					cesInfoService.update(cesInfo);
					result = ResultString.CONFIG_SUCCESS;
				}
			} else {
				throw new Exception("object is null");
			}
			//如果修改的字段被修改，则上报消息通知
			reportMsg(cesInfo, beforeCesInfo,result);
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				List<CesInfo> list = new ArrayList<CesInfo>();
				if(cesInfo != null){
					list.add(cesInfo);
				}
				String str = this.getOfflineSiteIdNames(list);
				if(str.equals("")){
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
				}else{
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+str+ResultString.NOT_ONLINE_SUCCESS;
				}
			}
		} catch (BusinessIdException e) {
			return e.getMessage();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			pwIdList = null;
			operationServiceI_cx = null;
			result_cx = null;
			UiUtil.closeService_MB(cesInfoService);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public String excuteInsert(Object object) throws Exception {
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		CesInfoService_MB cesInfoService = null;
		List<Integer> pwIdList = new ArrayList<Integer>();
		OperationServiceI operationServiceI_wh = null;
		OperationServiceI operationServiceI_cx = null;
		String result_wh = null;
		String result_cx = null;
		List<CesInfo> cesInfoList = null;
		try {
			cesInfoList = (List<CesInfo>) object;

			if (null != cesInfoList && cesInfoList.size() > 0) {
				cesInfoService = (CesInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);

				/** 如果是激活状态 就下发到设备 */
				if (cesInfoList.get(0).getActiveStatus() == EActiveStatus.ACTIVITY.getValue()) {
					// 获取所有pw
					for (CesInfo cesInfo : cesInfoList) {
						pwIdList.add(cesInfo.getPwId());
					}
					if (cesInfoList.get(0).getActiveStatus() == EActiveStatus.ACTIVITY.getValue() && !isPwActive(pwIdList)) {
						result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL_PW_ACTIVITY);
					} else {
						// 插入数据库
						cesInfoService.save(cesInfoList);
						// 下发武汉的 武汉没有ces配置块 不用下发
						operationServiceI_wh = new ElineWHServiceImpl();
						result_wh = operationServiceI_wh.excutionInsert(this.cesInfoTOElineInfo(cesInfoList).get(0));
						// 下发晨晓的
						operationServiceI_cx = new CesCXServiceImpl();
						result_cx = operationServiceI_cx.excutionInsert(cesInfoList);

						// 如果两次下发都成功。就插入直接返回 否则获取失败消息。 并回滚已经成功的
						if (ResultString.CONFIG_SUCCESS.equals(result_cx) && ResultString.CONFIG_SUCCESS.equals(result_wh)) {
							result = ResultString.CONFIG_SUCCESS;
						} else {
							// 修改数据库状态
							for (CesInfo cesInfo : cesInfoList) {
								cesInfo.setActiveStatus(EActiveStatus.UNACTIVITY.getValue());
							}
							cesInfoService.update(cesInfoList);
							// // 回滚武汉配置
							// operationServiceI_wh.excutionDelete(cesInfoList);
							// // 如果晨晓的成功。 错误消息返回武汉的
							// if (TipMessageUtil.CONFIG_SUCCESS.equals(result_cx)) {
							// //因为晨晓不是全量数据所以在内部做回滚
							// operationServiceI_cx.excutionDelete(cesInfoList);
							// result = result_wh;
							// } else {
							// result = result_cx;
							// }
							result = result_wh;
						}
					}
				} else {
					cesInfoService.save(cesInfoList);
					result = ResultString.CONFIG_SUCCESS;
				}
				//成功上报操作信息到caoba
				for (CesInfo cesInfo : cesInfoList) {
					if(cesInfo.getIsSingle()>0){//单网元
						super.notifyCorba("CrossConnection", MessageType.CREATION, cesInfo, ELayerRate.CES.getValue()+"", result);
					}else{//端到端
					super.notifyCorba("SubnetworkConnection", MessageType.CREATION, cesInfo, ELayerRate.CES.getValue()+"",result);
				}
				}
			} else {
				throw new Exception("object is null");
			}
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				if (null != cesInfoList && cesInfoList.size() > 0) {
					String str = this.getOfflineSiteIdNames(cesInfoList);
					if(str.equals("")){
						return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
					}else{
						return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+str+ResultString.NOT_ONLINE_SUCCESS;
					}
				}
			}
		} catch (BusinessIdException e) {
			return e.getMessage();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			pwIdList = null;
			UiUtil.closeService_MB(cesInfoService);
			operationServiceI_cx = null;
			result_cx = null;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public String excuteDelete(Object object) throws Exception {
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		OperationServiceI operationServiceI_wh = null;
		OperationServiceI operationServiceI_cx = null;
		String result_wh = null;
		String result_cx = null;
		List<CesInfo> cesInfoList_activity = null;
		List<CesInfo> cesInfoList = null;
		try {
			cesInfoList = (List<CesInfo>) object;
			if (cesInfoList != null && cesInfoList.size() > 0) {
				cesInfoList_activity = this.getActivity(cesInfoList);
				if (cesInfoList_activity.size() == 0) {
					this.deleteCes(cesInfoList);
					result = ResultString.CONFIG_SUCCESS;
					return ResultString.CONFIG_SUCCESS;
				} else {
					this.updateStatus(cesInfoList_activity, EActiveStatus.UNACTIVITY.getValue());
					// // 下发武汉的 武汉没有ces配置块 不用下发
					operationServiceI_wh = new ElineWHServiceImpl();
					result_wh = operationServiceI_wh.excutionDelete(this.cesInfoTOElineInfo(cesInfoList_activity));
					// 下发晨晓的
					operationServiceI_cx = new CesCXServiceImpl();
					result_cx = operationServiceI_cx.excutionDelete(cesInfoList_activity);
					// 判断是否都成功，都成功返回结果，有不成功的 回滚、返回错误消息
					if (ResultString.CONFIG_SUCCESS.equals(result_cx) && ResultString.CONFIG_SUCCESS.equals(result_wh)) {
						this.deleteCes(cesInfoList);
						result = ResultString.CONFIG_SUCCESS;
					} else {
						this.updateStatus(cesInfoList, EActiveStatus.ACTIVITY.getValue());

						// // 回滚
						// operationServiceI_wh.excutionInsert(cesInfoList);
						// 如果晨晓的成功。 错误消息返回武汉的
						// if (TipMessageUtil.CONFIG_SUCCESS.equals(result_cx)) {
						// operationServiceI_cx.excutionInsert(cesInfoList);
						// result = result_wh;
						// } else {
						result = result_wh;
						// }
					}
				}
			} else {
				throw new Exception("objectList is null");
			}
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				if (null != cesInfoList && cesInfoList.size() > 0) {
					String str = this.getOfflineSiteIdNames(cesInfoList);
					if(str.equals("")){
						return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
					}else{
						return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+str+ResultString.NOT_ONLINE_SUCCESS;
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			//成功上报操作信息到caoba
			if(cesInfoList != null && cesInfoList.size()>0){
				for (CesInfo cesInfo : cesInfoList) {
					if(cesInfo.getIsSingle()>0){//单网元
						super.notifyCorba("CrossConnection", MessageType.DELETION, cesInfo, ELayerRate.CES.getValue()+"", result);
					}else{//端到端
						super.notifyCorba("SubnetworkConnection", MessageType.DELETION, cesInfo, ELayerRate.CES.getValue()+"",result);
					}
				}
			}
			operationServiceI_cx = null;
		}
		return result;
	}

	/**
	 * isPwActive(验证pw是否都为已激活)
	 * 
	 * @author kk
	 * @param
	 * @return true已激活 false未激活
	 * @Exception 异常对象
	 */
	private boolean isPwActive(List<Integer> pwIdList) throws Exception {
		PwInfoService_MB pwService = null;
		List<PwInfo> pwInfoList = null;
		PwInfo pwInfo = null;
		boolean falg = false;
		try {
			pwService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			pwInfoList = new ArrayList<PwInfo>();
			pwInfo = new PwInfo();
			for (Integer pwId : pwIdList) {
				pwInfo.setPwId(pwId);
				pwInfoList.addAll(pwService.select(pwInfo));
			}
			for (PwInfo pw : pwInfoList) {
				if (pw.getPwStatus() != EActiveStatus.ACTIVITY.getValue())
					return false;
			}
			falg = true;
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(pwService);
			pwInfoList = null;
			pwInfo = null;
		}
		return falg;
	}

	private void deleteCes(List<CesInfo> cesInfoList) throws Exception {
		CesInfoService_MB cesInfoService = null;
		try {
			cesInfoService = (CesInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);
			cesInfoService.delete(cesInfoList);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(cesInfoService);
		}
	}

	/**
	 * 修改状态
	 * 
	 * @param cesInfoList
	 *            cesInfo集合
	 * @param status
	 *            2为修改成删除 1为修改成激活
	 * @throws Exception
	 */
	private void updateStatus(List<CesInfo> cesInfoList, int status) throws Exception {
		List<Integer> serviceIds = null;
		CesInfoService_MB cesInfoService = null;

		try {
			serviceIds = new ArrayList<Integer>();
			for (CesInfo cesInfo : cesInfoList) {
				if (!serviceIds.contains(cesInfo.getServiceId())) {
					serviceIds.add(cesInfo.getId());
				}
			}
			cesInfoService = (CesInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);
			cesInfoService.updateActiveStatus(serviceIds, status);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			serviceIds = null;
			UiUtil.closeService_MB(cesInfoService);
		}
	}

	/**
	 * getActivity(获取已经激活的ces)
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private List<CesInfo> getActivity(List<CesInfo> cesInfoList) throws Exception {

		List<CesInfo> cesInfoList_activity = new ArrayList<CesInfo>();

		for (CesInfo cesInfo : cesInfoList) {
			if (cesInfo.getActiveStatus() == EActiveStatus.ACTIVITY.getValue()) {
				cesInfoList_activity.add(cesInfo);
			}
		}
		return cesInfoList_activity;
	}

	/**
	 * synchro(根据网元id同步)
	 * 
	 * @author kk
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
		String result = ResultString.QUERY_FAILED;
		try {
			//虚拟网元不同步设备
			SiteUtil siteUtil=new SiteUtil();
			if(0==siteUtil.SiteTypeUtil(siteId)){
				if (super.getManufacturer(siteId) == EManufacturer.WUHAN.getValue()) {
					operationServiceI = new ElineWHServiceImpl();
				} else {
					operationServiceI = new CesCXServiceImpl();
				}
				result =  (String)operationServiceI.synchro(siteId);
			}
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	/**
	 * 武汉（ces业务向eline业务转换）
	 * 
	 * @param cesInfo
	 * @return
	 */
	private ElineInfo cesInfoTOElineInfo(CesInfo cesInfo) {
		ElineInfo elineInfo = new ElineInfo();
		elineInfo.setName(cesInfo.getName());
		elineInfo.setActiveStatus(cesInfo.getActiveStatus());
		elineInfo.setPwId(cesInfo.getPwId());
		elineInfo.setIsSingle(cesInfo.getIsSingle());
		elineInfo.setServiceType(EServiceType.ELINE.getValue());
		elineInfo.setCestype(3);
		elineInfo.setCreateTime(DateUtil.getDate(DateUtil.FULLTIME));
		elineInfo.setCreateUser(cesInfo.getCreateUser());
		elineInfo.setaSiteId(cesInfo.getaSiteId());
		elineInfo.setAportId(cesInfo.getAportId());
		elineInfo.setaXcId(elineInfo.getaXcId());
		elineInfo.setzSiteId(cesInfo.getzSiteId());
		elineInfo.setZportId(cesInfo.getZportId());
		elineInfo.setzXcId(elineInfo.getzXcId());
		return elineInfo;
	}

	/**
	 * 武汉（ces业务向eline业务转换）
	 * 
	 * @param cesInfo
	 * @return
	 */
	private List<ElineInfo> cesInfoTOElineInfo(List<CesInfo> cesInfoList) {
		List<ElineInfo> elineInfoList = new ArrayList<ElineInfo>();
		for (CesInfo cesInfo : cesInfoList) {
			ElineInfo elineInfo = new ElineInfo();
			elineInfo.setName(cesInfo.getName());
			elineInfo.setActiveStatus(cesInfo.getActiveStatus());
			elineInfo.setPwId(cesInfo.getPwId());
			elineInfo.setIsSingle(cesInfo.getIsSingle());
			elineInfo.setServiceType(EServiceType.ELINE.getValue());
			elineInfo.setCestype(3);
			elineInfo.setCreateTime(DateUtil.getDate(DateUtil.FULLTIME));
			elineInfo.setCreateUser(cesInfo.getCreateUser());
			elineInfo.setaSiteId(cesInfo.getaSiteId());
			elineInfo.setAportId(cesInfo.getAportId());
			elineInfo.setaXcId(elineInfo.getaXcId());
			elineInfo.setzSiteId(cesInfo.getzSiteId());
			elineInfo.setZportId(cesInfo.getZportId());
			elineInfo.setzXcId(elineInfo.getzXcId());
			elineInfoList.add(elineInfo);
		}
		return elineInfoList;
	}
	
	/**
	 * 判断是否符合修改上报
	 * @param cesInfo 要修改的cesInfo
	 * @param beforeCesInfo 原有的cesInfo
	 * @param result 是否上报的标识
	 * @throws Exception 
	 */
	private void reportMsg(CesInfo cesInfo,CesInfo beforeCesInfo,String result) throws Exception{
		Map<String, String> attributeMap = new HashMap<String, String>();//上报的修改的属性集合
		Map<String, String> stateMap = new HashMap<String, String>();//上报的状态的属性集合
		if (null != cesInfo.getName()&&null != beforeCesInfo.getName() && !cesInfo.getName().equals(beforeCesInfo.getName())) {
			attributeMap.put("userLabel", cesInfo.getName());
		}
		if (cesInfo.getActiveStatus() != beforeCesInfo.getActiveStatus()) {
			stateMap.put("status", cesInfo.getActiveStatus()+"");
		}
		//成功上报操作信息到caoba
		try {
			if (attributeMap.size() > 0 ) {
				attributeMap.put("id", cesInfo.getId()+"");
				if(cesInfo.getIsSingle()>0){//单网元
					if (cesInfo.getaSiteId()>0) {
						attributeMap.put("siteId", cesInfo.getaSiteId()+"");
					}else if (cesInfo.getzSiteId()>0) {
						attributeMap.put("siteId", cesInfo.getzSiteId()+"");
					}
					super.notifyCorba("CrossConnection", MessageType.ATTRIBUTECHG, attributeMap, ELayerRate.CES.getValue()+"", result);
				}else{//端到端
				super.notifyCorba("SubnetworkConnection", MessageType.ATTRIBUTECHG, attributeMap, ELayerRate.CES.getValue()+"", result);
			}
			}
			//状态修改上报
			if (stateMap.size()>0) {
				stateMap.put("id", cesInfo.getId()+"");
				if (cesInfo.getIsSingle()>0) {//单网元
					if (cesInfo.getaSiteId()>0) {
						stateMap.put("siteId", cesInfo.getaSiteId()+"");
					}else if (cesInfo.getzSiteId()>0) {
						stateMap.put("siteId", cesInfo.getzSiteId()+"");
					}
					super.notifyCorba("CrossConnection", MessageType.STATECHG, stateMap, ELayerRate.CES.getValue()+"",result);
				}else {//端到端
					super.notifyCorba("SubnetworkConnection", MessageType.STATECHG , stateMap, ELayerRate.CES.getValue()+"",result);
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		try {
			if (super.getManufacturer(siteId) == EManufacturer.WUHAN.getValue()) {
				ElineWHServiceImpl operationServiceI = new ElineWHServiceImpl();
				return operationServiceI.consistence(siteId);
			}
		} catch (Exception e) {
			throw e;
		}
		return null;
	}
	
	/**
	 * 获取离线网元名称
	 */
	private String getOfflineSiteIdNames(List<CesInfo> cesList) throws Exception {
		List<Integer> siteIds = null;
		String str = "";
		try {
			siteIds = new ArrayList<Integer>();
			for (CesInfo ces : cesList) {
				siteIds.add(ces.getaSiteId());
				siteIds.add(ces.getzSiteId());
			}
			str = new WhImplUtil().getNeNames(siteIds);
		} catch (Exception e) {
			throw e;
		}
		return str;
	}
}
