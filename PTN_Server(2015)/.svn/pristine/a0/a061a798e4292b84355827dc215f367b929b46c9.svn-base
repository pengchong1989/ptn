package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.ptn.path.eth.ElineInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.EServiceType;
import com.nms.model.ptn.path.eth.ElineInfoService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.ElineCXServiceImpl;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.service.impl.wh.ElineWHServiceImpl;
import com.nms.service.notify.Message.MessageType;
import com.nms.ui.manager.BusinessIdException;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class ElineDispatch extends DispatchBase implements DispatchInterface {

	@SuppressWarnings("unchecked")
	public String excuteDelete(Object object) throws Exception {
		List<ElineInfo> elineList = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		OperationServiceI operationServiceI_wh = null;
		OperationServiceI operationServiceI_cx = null;
		String result_wh = null;
		String result_cx = null;
		try {
			elineList = (List<ElineInfo>) object;
			if (elineList != null && !elineList.isEmpty()) {
				List<ElineInfo> elineList_activity = this.getActivityEline(elineList);
				if(elineList_activity.isEmpty()){
					this.deleteEline(elineList);
					result = ResultString.CONFIG_SUCCESS;
				}else{
					this.updateStatus(elineList, EActiveStatus.UNACTIVITY.getValue());
					// 下发武汉的
					operationServiceI_wh = new ElineWHServiceImpl();
					result_wh = operationServiceI_wh.excutionDelete(elineList);
					// 下发晨晓的
					operationServiceI_cx = new ElineCXServiceImpl();
					result_cx = operationServiceI_cx.excutionDelete(elineList);
					// 判断是否都成功，都成功返回结果，有不成功的 回滚、返回错误消息
					if (ResultString.CONFIG_SUCCESS.equals(result_cx) && ResultString.CONFIG_SUCCESS.equals(result_wh)) {
						this.deleteEline(elineList);
						result = ResultString.CONFIG_SUCCESS;
					} else {
						this.updateStatus(elineList, EActiveStatus.ACTIVITY.getValue());

						// 回滚
						for (ElineInfo elineInfo : elineList) {
							operationServiceI_wh.excutionInsert(elineInfo);
						}

						// 如果晨晓的成功。 错误消息返回武汉的
						if (ResultString.CONFIG_SUCCESS.equals(result_cx)) {
							for (ElineInfo elineInfo : elineList) {
								operationServiceI_cx.excutionInsert(elineInfo);
							}
							result = result_wh;
						} else {
							result = result_cx;
						}
					}
				}
			} else {
				throw new Exception("objectList is null");
			}
			// 成功上报操作信息到caoba
			for (ElineInfo elineInfo : elineList) {
				if(elineInfo.getIsSingle()>0){//单网元
					super.notifyCorba("MFDFr", MessageType.DELETION, elineInfo, EServiceType.ELINE.getValue()+"", result);
				}else{//端到端
					super.notifyCorba("FDFr", MessageType.DELETION, elineInfo, EServiceType.ELINE.getValue()+"", result);
				}
			}
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				if (elineList != null && elineList.size() > 0) {
					String str = this.getOfflineSiteIdNames(elineList);
					if(str.equals("")){
						return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
					}else{
						return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+str+ResultString.NOT_ONLINE_SUCCESS;
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			elineList = null;
			operationServiceI_wh = null;
			operationServiceI_cx = null;
		}
		return result;
	}

	/**
	 * 获取激活的eline
	 */
	private List<ElineInfo> getActivityEline(List<ElineInfo> elineList) {
		List<ElineInfo> elineInfoList_activity = new ArrayList<ElineInfo>();
		for (ElineInfo elineInfo : elineList) {
			if (elineInfo.getActiveStatus() == EActiveStatus.ACTIVITY.getValue()) {
				elineInfoList_activity.add(elineInfo);
			}
		}
		return elineInfoList_activity;
	}

	public String excuteInsert(Object object) throws Exception {
		ElineInfo elineInfo = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		ElineInfoService_MB elineService = null;
		List<Integer> pwIdList = new ArrayList<Integer>();
		OperationServiceI operationServiceI_wh = null;
		OperationServiceI operationServiceI_cx = null;
		String result_wh = null;
		String result_cx = null;
		List<ElineInfo> elineinfoList = null;
		try {
			elineInfo = (ElineInfo) object;
			elineService = (ElineInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Eline);

			/** 如果是激活状态 就下发到设备 */
			if (elineInfo.getActiveStatus() == EActiveStatus.ACTIVITY.getValue()) {
				// 获取所有pw
				pwIdList.add(elineInfo.getPwId());
				if (elineInfo.getActiveStatus() == EActiveStatus.ACTIVITY.getValue() && !isPwActive(pwIdList)) {
					result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL_PW_ACTIVITY);
				} else {
					// 插入数据库
					int elineid = elineService.save(elineInfo);
					elineInfo.setId(elineid);
					// 下发武汉的
					operationServiceI_wh = new ElineWHServiceImpl();
					result_wh = operationServiceI_wh.excutionInsert(elineInfo);
					// 下发晨晓的
					operationServiceI_cx = new ElineCXServiceImpl();
					result_cx = operationServiceI_cx.excutionInsert(elineInfo);

					// 如果两次下发都成功。就插入直接返回 否则获取失败消息。 并回滚已经成功的
					if (ResultString.CONFIG_SUCCESS.equals(result_cx) && ResultString.CONFIG_SUCCESS.equals(result_wh)) {
						result = ResultString.CONFIG_SUCCESS;
					} else {
						// // 修改数据库状态
						elineInfo.setActiveStatus(EActiveStatus.UNACTIVITY.getValue());
						elineService.update(elineInfo);

						elineinfoList = new ArrayList<ElineInfo>();
						elineinfoList.add(elineInfo);
						// 回滚武汉配置
						operationServiceI_wh.excutionDelete(elineinfoList);
						// 如果晨晓的成功。 错误消息返回武汉的
						if (ResultString.CONFIG_SUCCESS.equals(result_cx)) {
							// 因为晨晓不是全量数据所以在内部做回滚
							operationServiceI_cx.excutionDelete(elineinfoList);
							result = result_wh;
						} else {
							result = result_cx;
						}
					}
				}
			} else {
				elineService.save(elineInfo);
				result = ResultString.CONFIG_SUCCESS;
			}
			//成功上报操作信息到caoba
			if (elineInfo.getIsSingle()>0) {//单网元
				super.notifyCorba("MFDFr", MessageType.CREATION, elineInfo, EServiceType.ELINE.getValue()+"", result);
			}else {//端到端
				super.notifyCorba("FDFr", MessageType.CREATION, elineInfo, EServiceType.ELINE.getValue()+"", result);
			}
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				List<ElineInfo> list = new ArrayList<ElineInfo>();
				if(elineInfo != null){
					list.add(elineInfo);
				}
				String str = this.getOfflineSiteIdNames(list);
				if(str.equals("")){
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
				}else{
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+str+ResultString.NOT_ONLINE_SUCCESS;
				}
			}
		} catch (BusinessIdException e) {
//			return e.getMessage();
			ExceptionManage.dispose(e, this.getClass());
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			elineInfo = null;
			UiUtil.closeService_MB(elineService);
			pwIdList = new ArrayList<Integer>();
			operationServiceI_wh = null;
			operationServiceI_cx = null;
			result_wh = null;
			result_cx = null;
			elineinfoList = null;
		}
		return result;
	}

	public String excuteUpdate(Object object) throws Exception {
		ElineInfo elineInfo = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		ElineInfoService_MB elineService = null;
		List<Integer> pwIdList = new ArrayList<Integer>();
		OperationServiceI operationServiceI_wh = null;
		OperationServiceI operationServiceI_cx = null;
		String result_wh = null;
		String result_cx = null;
		ElineInfo beforeEline = null;
		/*
		 * 添加集合：因etree,etree转换的都是集合，固添加集合，以便Corbar统一调用以太网业务
		 */
		List<ElineInfo> elineInfoList = null;
		try {
			if (object instanceof List) {
				elineInfoList = (List) object;
				if (elineInfoList != null && elineInfoList.size() > 0) {
					elineInfo = elineInfoList.get(0);
				} else {
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
				}
			} else if (object instanceof ElineInfo) {
				elineInfo = (ElineInfo) object;
			}

			elineService = (ElineInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Eline);

			// 保存修改以前的对象，关系激活状态
			beforeEline = new ElineInfo();
			beforeEline.setId(elineInfo.getId());
			beforeEline = elineService.selectByCondition_nojoin(beforeEline).get(0);
			
			elineInfo.setBeforeActiveStatus(beforeEline.getActiveStatus());

			// 两次都是非激活状态，不用下发设备
			if (beforeEline.getActiveStatus() == EActiveStatus.UNACTIVITY.getValue() && elineInfo.getActiveStatus() == EActiveStatus.UNACTIVITY.getValue()) {
				elineService.update(elineInfo);
				result = ResultString.CONFIG_SUCCESS;

			} else {
				// 先入库
				elineService.update(elineInfo);
				// 获取所有pw
				pwIdList.add(elineInfo.getPwId());
				if (elineInfo.getActiveStatus() == EActiveStatus.ACTIVITY.getValue() && !isPwActive(pwIdList)) {
					result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL_PW_ACTIVITY);
					elineService.update(beforeEline);
				} else {
					// 下发武汉的
					operationServiceI_wh = new ElineWHServiceImpl();
					result_wh = operationServiceI_wh.excutionUpdate(elineInfo);
					// 下发晨晓的
					operationServiceI_cx = new ElineCXServiceImpl();
					// result_cx = ResultString.CONFIG_SUCCESS;
					result_cx = operationServiceI_cx.excutionUpdate(elineInfo);

					// 如果两次下发都成功。就插入直接返回 否则获取失败消息。 并回滚已经成功的
					if (ResultString.CONFIG_SUCCESS.equals(result_cx) && ResultString.CONFIG_SUCCESS.equals(result_wh)) {
						// 更新数据库
						result = ResultString.CONFIG_SUCCESS;
					} else {
						// 还原之前数据
						elineService.update(beforeEline);
						// 回滚武汉配置
						operationServiceI_wh.excutionUpdate(beforeEline);
						// 如果晨晓的成功。 错误消息返回武汉的
						if (ResultString.CONFIG_SUCCESS.equals(result_cx)) {
							// 因为晨晓不是全量数据所以在内部做回滚
							operationServiceI_cx.excutionUpdate(beforeEline);
							result = result_wh;
						} else {
							result = result_cx;
						}
					}
				}
			}
			// 如果修改的字段被修改，则上报消息通知
			reportMsg(elineInfo, beforeEline, result);
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				List<ElineInfo> list = new ArrayList<ElineInfo>();
				if(elineInfo != null){
					list.add(elineInfo);
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
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			elineInfo = null;
			UiUtil.closeService_MB(elineService);
			pwIdList = new ArrayList<Integer>();
			operationServiceI_wh = null;
			operationServiceI_cx = null;
			result_wh = null;
			result_cx = null;
		}
		return result;
	}

	private void deleteEline(List<ElineInfo> elineList) throws Exception {
		ElineInfoService_MB elineService = null;
		try {
			elineService = (ElineInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Eline);
			elineService.delete(elineList);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(elineService);
		}
	}

	/**
	 * 修改状态
	 * 
	 * @param elinelList
	 *            eline集合
	 * @param status
	 *            2为修改成删除 1为修改成激活
	 * @throws Exception
	 */
	private void updateStatus(List<ElineInfo> elineInfoList, int status) throws Exception {
		ElineInfoService_MB elineService = null;
		try {
			List<Integer> idList = new ArrayList<Integer>();
			for (ElineInfo eline : elineInfoList) {
				idList.add(eline.getId());
			}
			elineService = (ElineInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Eline);
			elineService.updateActiveStatus(idList, status);
		} finally {
			UiUtil.closeService_MB(elineService);
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
	@SuppressWarnings("unchecked")
	public String synchro(int siteId) throws Exception {
		OperationServiceI operationServiceI = null;
		try {
			// 虚拟网元不同步设备
			SiteUtil siteUtil=new SiteUtil();
			if (0 == siteUtil.SiteTypeUtil(siteId)) {
				if (super.getManufacturer(siteId) == EManufacturer.WUHAN.getValue()) {
					operationServiceI = new ElineWHServiceImpl();
				} else {
					operationServiceI = new ElineCXServiceImpl();
				}
				return (String)operationServiceI.synchro(siteId);
			}
		} catch (Exception e) {
			throw e;
		}
		return ResultString.QUERY_FAILED;
	}

	/**
	 * 通过PWID和网元ID查询PW
	 * 
	 * @throws Exception
	 */
	private PwInfo getPwInfo(int pwid) throws Exception {

		PwInfo pwInfo = null;
		PwInfoService_MB pwInfoService = null;
		try {
			pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			pwInfo = new PwInfo();
			pwInfo.setPwId(pwid);
			pwInfo = pwInfoService.selectBypwid_notjoin(pwInfo);
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(pwInfoService);
		}
		return pwInfo;
	}

	/**
	 * 判断是否符合修改上报
	 * @param elineInfo 要修改的elineInfo
	 * @param beforeEline 原有的elineInfo
	 * @param result 是否上报消息的标识
	 * @throws Exception 
	 */
	private void reportMsg(ElineInfo elineInfo,ElineInfo beforeEline,String result) throws Exception{
		Map<String, String> attributeMap = new HashMap<String, String>();//上报的修改的属性集合
		Map<String, String> stateMap = new HashMap<String, String>();//上报的状态的属性集合
		if (null != elineInfo.getName() && null !=beforeEline.getName() && !elineInfo.getName().equals(beforeEline.getName())) {
			attributeMap.put("userLabel", elineInfo.getName());
		}
		if (elineInfo.getActiveStatus() != beforeEline.getActiveStatus()) {
			stateMap.put("status", elineInfo.getActiveStatus()+"");
		}
		//成功上报操作信息到caoba
		try {
			if (attributeMap.size()>0) {
				attributeMap.put("id", elineInfo.getId()+"");
				if(elineInfo.getIsSingle()>0){//单网元
					if (elineInfo.getaSiteId()>0) {
						attributeMap.put("siteId", elineInfo.getaSiteId()+"");
					}else if (elineInfo.getzSiteId()>0) {
						attributeMap.put("siteId", elineInfo.getzSiteId()+"");
					}
					super.notifyCorba("MFDFr", MessageType.ATTRIBUTECHG, attributeMap, EServiceType.ELINE.getValue()+"", result);
				}else{//端到端
					super.notifyCorba("FDFr", MessageType.ATTRIBUTECHG, attributeMap, EServiceType.ELINE.getValue()+"" ,result);
				}
			}
			//状态修改上报
			if (stateMap.size()>0) {
				stateMap.put("id", elineInfo.getId()+"");
				if(elineInfo.getIsSingle()>0){//单网元
					if (elineInfo.getaSiteId()>0) {
						stateMap.put("siteId", elineInfo.getaSiteId()+"");
					}else if (elineInfo.getzSiteId()>0) {
						stateMap.put("siteId", elineInfo.getzSiteId()+"");
					}
					super.notifyCorba("MFDFr", MessageType.STATECHG, stateMap, EServiceType.ELINE.getValue()+"", result);
				}else{//端到端
					super.notifyCorba("FDFr", MessageType.STATECHG, stateMap, EServiceType.ELINE.getValue()+"" ,result);
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
	private String getOfflineSiteIdNames(List<ElineInfo> elineList) throws Exception {
		List<Integer> siteIds = null;
		String str = "";
		try {
			siteIds = new ArrayList<Integer>();
			for (ElineInfo eline : elineList) {
				siteIds.add(eline.getaSiteId());
				siteIds.add(eline.getzSiteId());
			}
			str = new WhImplUtil().getNeNames(siteIds);
		} catch (Exception e) {
			throw e;
		}
		return str;
	}
}
