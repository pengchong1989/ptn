package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.ptn.path.eth.ElanInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.EServiceType;
import com.nms.model.ptn.path.eth.ElanInfoService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.ELanCXServiceImpl;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.service.impl.wh.ElanWHServiceImpl;
import com.nms.service.notify.Message.MessageType;
import com.nms.ui.manager.BusinessIdException;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class ElanDispatch extends DispatchBase implements DispatchInterface {
	@SuppressWarnings("unchecked")
	public String excuteDelete(Object object) throws Exception {
		List<ElanInfo> elanList = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		OperationServiceI operationServiceI_wh = null;
		OperationServiceI operationServiceI_cx = null;
		String result_wh = null;
		String result_cx = null;
		List<ElanInfo> elanList_activity = null;		
		try {		
			elanList = (List<ElanInfo>) object;
			if (elanList != null && elanList.size() > 0) {

				elanList_activity = this.getActivity(elanList);
				if (elanList_activity.size() == 0) {
					this.deleteDao(elanList);
					result = ResultString.CONFIG_SUCCESS;
				} else {
					this.updateStatus(elanList, EActiveStatus.UNACTIVITY.getValue());
					// 下发武汉的
					operationServiceI_wh = new ElanWHServiceImpl();
					result_wh = operationServiceI_wh.excutionDelete(elanList);
					// 下发晨晓的
					operationServiceI_cx = new ELanCXServiceImpl();
					result_cx = operationServiceI_cx.excutionDelete(elanList);
					// 判断是否都成功，都成功返回结果，有不成功的 回滚、返回错误消息
					if (ResultString.CONFIG_SUCCESS.equals(result_cx) && ResultString.CONFIG_SUCCESS.equals(result_wh)) {
						this.deleteDao(elanList);
						result = ResultString.CONFIG_SUCCESS;
					} else {
						this.updateStatus(elanList, EActiveStatus.ACTIVITY.getValue());
						// 回滚
						operationServiceI_wh.excutionInsert(elanList);
						// 如果晨晓的成功。 错误消息返回武汉的
						if (ResultString.CONFIG_SUCCESS.equals(result_cx)) {
							operationServiceI_cx.excutionInsert(elanList);
							result = result_wh;
						} else {
							result = result_cx;
						}
					}
				}
			} else {
				throw new Exception("objectList is null");
			}
			//成功上报操作信息到caoba
			if (elanList.get(0).getIsSingle() > 0) {
				super.notifyCorba("MFDFr", MessageType.DELETION, elanList, EServiceType.ELAN.getValue()+"", result);
			}else {
				//端到端
			for (ElanInfo elanInfo : elanList) {
					super.notifyCorba("FDFr", MessageType.DELETION, elanInfo, EServiceType.ELAN.getValue()+"",result);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			elanList = null;
			operationServiceI_wh = null;
			operationServiceI_cx = null;
			result_wh = null;
			result_cx = null;
			elanList_activity = null;			
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
//			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			WhImplUtil whImplUtil = new WhImplUtil();
			return whImplUtil.offLineResult(object);
		} else{
			return result;
		}
	}

	@SuppressWarnings("unchecked")
	public String excuteInsert(Object object) throws Exception {
		List<ElanInfo> elanInfoList = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		ElanInfoService_MB elanInfoService = null;
		List<Integer> pwIdList = new ArrayList<Integer>();
		OperationServiceI operationServiceI_wh = null;
		OperationServiceI operationServiceI_cx = null;
		String result_wh = null;
		String result_cx = null;
		try {
			elanInfoList = (List<ElanInfo>) object;
			if (null != elanInfoList && elanInfoList.size() > 0) {
				elanInfoService = (ElanInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ElanInfo);

				/** 如果是激活状态 就下发到设备 */
				if (elanInfoList.get(0).getActiveStatus() == EActiveStatus.ACTIVITY.getValue()) {
					// 获取所有pw
					for (ElanInfo elanInfo : elanInfoList) {
						pwIdList.add(elanInfo.getPwId());
					}
					if (elanInfoList.get(0).getActiveStatus() == EActiveStatus.ACTIVITY.getValue() && !isPwActive(pwIdList)) {
						result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL_PW_ACTIVITY);
					} else {
						// 插入数据库
						elanInfoService.insert(elanInfoList);						
						// 下发武汉的
						operationServiceI_wh = new ElanWHServiceImpl();
						result_wh = operationServiceI_wh.excutionInsert(elanInfoList);
						// 下发晨晓的
						operationServiceI_cx = new ELanCXServiceImpl();
						result_cx = operationServiceI_cx.excutionInsert(elanInfoList);

						// 如果两次下发都成功。就插入直接返回 否则获取失败消息。 并回滚已经成功的
						if (ResultString.CONFIG_SUCCESS.equals(result_cx) && ResultString.CONFIG_SUCCESS.equals(result_wh)) {							
							result = ResultString.CONFIG_SUCCESS;
						} else {
							// 修改数据库状态
							for (ElanInfo elan : elanInfoList) {
								elan.setActiveStatus(EActiveStatus.UNACTIVITY.getValue());
								elan.setAction(0);
							}
							elanInfoService.update(elanInfoList);
							// 回滚武汉配置
							operationServiceI_wh.excutionDelete(elanInfoList);
							// 如果晨晓的成功。 错误消息返回武汉的
							if (ResultString.CONFIG_SUCCESS.equals(result_cx)) {
								// 因为晨晓不是全量数据所以在内部做回滚
								operationServiceI_cx.excutionDelete(elanInfoList);
								result = result_wh;
							} else {
								result = result_cx;
							}
						}
					}
				} else {
					elanInfoService.insert(elanInfoList);
					result = ResultString.CONFIG_SUCCESS;
				}
			} else {
				throw new Exception("object is null");
			}
			//成功上报操作信息到caoba
			if(elanInfoList.get(0).getIsSingle()>0){//单网元
				super.notifyCorba("MFDFr", MessageType.CREATION, elanInfoList, EServiceType.ELAN.getValue()+"", result);
			}else{//端到端
				for (ElanInfo elanInfo : elanInfoList) {
					super.notifyCorba("FDFr", MessageType.CREATION, elanInfo, EServiceType.ELAN.getValue()+"",result);
				}
			}
		} catch (BusinessIdException e) {
			return e.getMessage();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			elanInfoList = null;
			UiUtil.closeService_MB(elanInfoService);
			pwIdList = null;
			operationServiceI_wh = null;
			operationServiceI_cx = null;
			result_wh = null;
			result_cx = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			WhImplUtil whImplUtil = new WhImplUtil();
			return whImplUtil.offLineResult(object);
		}else{
			return result;
		}
	}

	@SuppressWarnings("unchecked")
	public String excuteUpdate(Object object) throws Exception {
		List<ElanInfo> elanInfoList = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		ElanInfo elanInfo = null;
		ElanInfoService_MB elanInfoService = null;
		List<Integer> pwIdList = null;
		OperationServiceI operationServiceI_wh = null;
		OperationServiceI operationServiceI_cx = null;
		String result_wh = null;
		String result_cx = null;
		List<ElanInfo> beforeelanInfoList = null;// 下设备前的elanInfo
		AcPortInfo acPortInfo;
		try {
			elanInfoList = (List<ElanInfo>) object;

			if (null != elanInfoList && elanInfoList.size() > 0) {
				elanInfoService = (ElanInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ElanInfo);
				// 保存修改以前的对象，关系激活状态
				elanInfo = new ElanInfo();
				elanInfo.setServiceId(elanInfoList.get(0).getServiceId());
				beforeelanInfoList = elanInfoService.select(elanInfo);
				//释放之前的lan Id				
				elanInfoService.clearLanId(beforeelanInfoList);
				for(ElanInfo elanInfo_update:elanInfoList){
					elanInfo_update.setBeforeActiveStatus(beforeelanInfoList.get(0).getActiveStatus());
				}
				
				//判断是否需要下发设备
				if (elanInfoList.get(0).getActiveStatus() == EActiveStatus.UNACTIVITY.getValue()&&beforeelanInfoList.get(0).getActiveStatus() == EActiveStatus.UNACTIVITY.getValue()) {					
					elanInfoService.update(elanInfoList);
					result = ResultString.CONFIG_SUCCESS;
				} else {
					
					// 获取所有pw
					pwIdList = new ArrayList<Integer>();
					for (ElanInfo elanInfos : elanInfoList) {
						pwIdList.add(elanInfos.getPwId());
					}
					if (elanInfoList.get(0).getActiveStatus() == EActiveStatus.ACTIVITY.getValue() && !isPwActive(pwIdList)) {
						result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL_PW_ACTIVITY);
					} else {

						//先入库
						elanInfoService.update(elanInfoList);
						
						// 下发武汉的
						operationServiceI_wh = new ElanWHServiceImpl();
						result_wh = operationServiceI_wh.excutionUpdate(elanInfoList);
						// 下发晨晓的
						operationServiceI_cx = new ELanCXServiceImpl();
						result_cx = operationServiceI_cx.excutionUpdate(elanInfoList);

						// 如果两次下发都成功。就插入直接返回 否则获取失败消息。 并回滚已经成功的
						if (ResultString.CONFIG_SUCCESS.equals(result_cx) && ResultString.CONFIG_SUCCESS.equals(result_wh)) {
							result = ResultString.CONFIG_SUCCESS;
						} else {
							//不成功就将原来的数据还原
							/**/
							this.deleteDao(elanInfoList);
							elanInfoService.insert(beforeelanInfoList);
							/**/
							// 回滚武汉配置
							// operationServiceI_wh.excutionDelete(elanInfoList);
							// 如果晨晓的成功。 错误消息返回武汉的
							if (ResultString.CONFIG_SUCCESS.equals(result_cx)) {
								// 因为晨晓不是全量数据所以在内部做回滚
								operationServiceI_cx.excutionDelete(elanInfoList);
								result = result_wh;
							} else {
								result = result_cx;
							}
						}

					}
				}
			}
			//成功上报操作信息到caoba
			for (int i = 0; i < beforeelanInfoList.size(); i++) {
				//如果修改的字段被修改，则上报消息通知
				reportMsg(elanInfoList.get(i), beforeelanInfoList.get(i),result);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			elanInfoList = null;
			elanInfo = null;
			UiUtil.closeService_MB(elanInfoService);
			beforeelanInfoList = null;
			pwIdList = null;
			operationServiceI_wh = null;
			operationServiceI_cx = null;
			result_wh = null;
			result_cx = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			WhImplUtil whImplUtil = new WhImplUtil();
			return whImplUtil.offLineResult(object);
		}else{
			return result;
		}
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
		boolean flag = false;
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
			flag = true;
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(pwService);
			pwInfoList = null;
			pwInfo = null;
		}
		return flag;
	}

	/**
	 * 修改状态
	 * 
	 * @param tunnelList
	 *            tunnel集合
	 * @param status
	 *            2为修改成删除 1为修改成激活
	 * @throws Exception
	 */
	private void updateStatus(List<ElanInfo> elanInfoList, int status) throws Exception {
		List<Integer> serviceIds = null;
		ElanInfoService_MB elanInfoService = null;

		try {
			serviceIds = new ArrayList<Integer>();
			for (ElanInfo elanInfo : elanInfoList) {
				if (!serviceIds.contains(elanInfo.getServiceId())) {
					serviceIds.add(elanInfo.getServiceId());
				}
			}
			elanInfoService = (ElanInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ElanInfo);
			elanInfoService.updateStatusActivate(serviceIds, status);
		} catch (Exception e) {
			throw e;
		} finally {
			serviceIds = null;
			UiUtil.closeService_MB(elanInfoService);
		}
	}

	/**
	 * 
	 * deleteDao(批量删除数据库)
	 * 
	 * 
	 * @author kk
	 * 
	 * @param etree集合
	 * 
	 * @Exception 异常对象
	 */
	public void deleteDao(List<ElanInfo> elanInfoList) throws Exception {
		List<Integer> serviceidList = null;
		ElanInfoService_MB elanInfoService = null;
		try {
			elanInfoService = (ElanInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ElanInfo);
			serviceidList = new ArrayList<Integer>();
			for (ElanInfo elaninfo : elanInfoList) {
				if (!serviceidList.contains(elaninfo.getServiceId())) {
					elanInfoService.delete(elaninfo.getServiceId());
					serviceidList.add(elaninfo.getServiceId());
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			serviceidList = null;
			UiUtil.closeService_MB(elanInfoService);
		}
	}

	private List<ElanInfo> getActivity(List<ElanInfo> elaninfoList) {
		List<ElanInfo> elaninfoList_activity = new ArrayList<ElanInfo>();

		for (ElanInfo elanInfo : elaninfoList) {
			if (elanInfo.getActiveStatus() == EActiveStatus.ACTIVITY.getValue()) {
				elaninfoList_activity.add(elanInfo);
			}
		}
		return elaninfoList_activity;
	}

	/**
	 * synchro(根据网元id同步)
	 * 
	 * @author wangwf
	 * 
	 * @param siteId
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
					operationServiceI = new ElanWHServiceImpl();
				} else {
					operationServiceI = new ELanCXServiceImpl();
				}
				result =  (String)operationServiceI.synchro(siteId);
			}
		} catch (Exception e) {
			throw e;
		}
		return result;

	}
	
	/**
	 * 判断是否符合修改上报
	 * @param elanInfo 要修改的elanInfo
	 * @param beforeElanInfo 原有的elanInfo
	 * @param result 是否上传标识
	 * @throws Exception 
	 */
	private void reportMsg(ElanInfo elanInfo,ElanInfo beforeElanInfo,String result) throws Exception{
		Map<String, String> attributeMap = new HashMap<String, String>();//上报的修改的属性集合
		Map<String, String> stateMap = new HashMap<String, String>();//上报的状态的属性集合
		if (null != elanInfo.getName() &&null!= beforeElanInfo.getName()&& !elanInfo.getName().equals(beforeElanInfo.getName())) {
			attributeMap.put("userLabel", elanInfo.getName());
		}
		if (elanInfo.getActiveStatus() != beforeElanInfo.getActiveStatus()) {
			stateMap.put("status", elanInfo.getActiveStatus()+"");
		}
		//成功上报操作信息到caoba
		try {
			if (attributeMap.size()>0) {
				attributeMap.put("id", elanInfo.getId()+"");
				if(elanInfo.getIsSingle()>0){//单网元
					if (elanInfo.getaSiteId()>0) {
						attributeMap.put("siteId", elanInfo.getaSiteId()+"");
					}else if (elanInfo.getzSiteId()>0) {
						attributeMap.put("siteId", elanInfo.getzSiteId()+"");
					}
					super.notifyCorba("MFDFr", MessageType.ATTRIBUTECHG, attributeMap, EServiceType.ELAN.getValue()+"", result);
				}else{//端到端
					super.notifyCorba("FDFr", MessageType.ATTRIBUTECHG, attributeMap, EServiceType.ELAN.getValue()+"",result);
				}
			}
			//状态修改上报
			if (stateMap.size()>0) {
				stateMap.put("id", elanInfo.getId()+"");
				if (elanInfo.getIsSingle()>0) {//单网元
					if (elanInfo.getaSiteId()>0) {
						stateMap.put("siteId", elanInfo.getaSiteId()+"");
					}else if (elanInfo.getzSiteId()>0) {
						stateMap.put("siteId", elanInfo.getzSiteId()+"");
					}
					super.notifyCorba("MFDFr", MessageType.STATECHG, stateMap, EServiceType.ELAN.getValue()+"", result);
				}else {//端到端
					super.notifyCorba("FDFr", MessageType.STATECHG, stateMap, EServiceType.ELAN.getValue()+"",result);
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
				ElanWHServiceImpl operationServiceI = new ElanWHServiceImpl();
				return operationServiceI.consistence(siteId);
			}
		} catch (Exception e) {
			throw e;
		}
		return null;
	}
}