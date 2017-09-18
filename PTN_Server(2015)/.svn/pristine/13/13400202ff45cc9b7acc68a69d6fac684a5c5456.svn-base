package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.ptn.path.eth.EtreeInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.EServiceType;
import com.nms.model.ptn.path.eth.EtreeInfoService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.EtreeCXServiceImpl;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.service.impl.wh.EtreeWHServiceImpl;
import com.nms.service.notify.Message.MessageType;
import com.nms.ui.manager.BusinessIdException;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class EtreeDispatch extends DispatchBase implements DispatchInterface{
	@SuppressWarnings("unchecked")
	public String excuteDelete(Object object) throws Exception {
		List<EtreeInfo> etreeList = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		OperationServiceI operationServiceI_wh = null;
		OperationServiceI operationServiceI_cx = null;
		String result_wh = null;
		String result_cx = null;
		List<EtreeInfo> etreeList_activity=null;
		try {			
			etreeList = (List<EtreeInfo>) object;
			if (etreeList != null && etreeList.size() > 0) {
				etreeList_activity=this.getActivity(etreeList);
				if(etreeList_activity.size()==0){
					this.deleteDao(etreeList);				
					result = ResultString.CONFIG_SUCCESS;
				}else {
					this.updateStatus(etreeList, EActiveStatus.UNACTIVITY.getValue());
					// 下发武汉的
					operationServiceI_wh = new EtreeWHServiceImpl();
					result_wh = operationServiceI_wh.excutionDelete(etreeList);
					// 下发晨晓的
					operationServiceI_cx = new EtreeCXServiceImpl();
					result_cx = operationServiceI_cx.excutionDelete(etreeList);
					// 判断是否都成功，都成功返回结果，有不成功的 回滚、返回错误消息
					if (ResultString.CONFIG_SUCCESS.equals(result_cx) && ResultString.CONFIG_SUCCESS.equals(result_wh)) {
						this.deleteDao(etreeList);
						result = ResultString.CONFIG_SUCCESS;
						
					}else{	
						this.updateStatus(etreeList, EActiveStatus.ACTIVITY.getValue());
						// 回滚
						operationServiceI_wh.excutionInsert(etreeList);
						// 如果晨晓的成功。 错误消息返回武汉的
						if (ResultString.CONFIG_SUCCESS.equals(result_cx)) {
							operationServiceI_cx.excutionInsert(etreeList);
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
			if (etreeList.get(0).getIsSingle() > 0) {
				super.notifyCorba("MFDFr", MessageType.DELETION, etreeList, EServiceType.ETREE.getValue()+"", result);
				}else{//端到端
				for (EtreeInfo etreeInfo : etreeList) {
					super.notifyCorba("FDFr", MessageType.DELETION, etreeInfo, EServiceType.ETREE.getValue()+"",result);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			etreeList = null;
			operationServiceI_wh = null;
			operationServiceI_cx = null;
			result_wh = null;
			result_cx = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			WhImplUtil whImplUtil = new WhImplUtil();
			return whImplUtil.offLineResult(object);
		} else {
			return result;
		}
	}

	@SuppressWarnings("unchecked")
	public String excuteInsert(Object object) throws Exception {
		List<EtreeInfo> etreeInfoList = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		EtreeInfoService_MB etreeService = null;
		List<Integer> pwIdList = new ArrayList<Integer>();
		OperationServiceI operationServiceI_wh = null;
		OperationServiceI operationServiceI_cx = null;
		String result_wh = null;
		String result_cx = null;
		try {
			etreeInfoList = (List<EtreeInfo>) object;

			if (null != etreeInfoList && etreeInfoList.size() > 0) {
				
				etreeService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);

				/** 如果是激活状态 就下发到设备 */
				if (etreeInfoList.get(0).getActiveStatus() == EActiveStatus.ACTIVITY.getValue()) {
					// 获取所有pw
					for (EtreeInfo etreeInfo : etreeInfoList) {
						pwIdList.add(etreeInfo.getPwId());
					}
					if (etreeInfoList.get(0).getActiveStatus() == EActiveStatus.ACTIVITY.getValue() && !isPwActive(pwIdList)) {
						result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL_PW_ACTIVITY);
					} else {
						// 插入数据库
						etreeService.insert(etreeInfoList);
						// 下发武汉的
						operationServiceI_wh = new EtreeWHServiceImpl();
						result_wh = operationServiceI_wh.excutionInsert(etreeInfoList);
						// 下发晨晓的	
						operationServiceI_cx = new EtreeCXServiceImpl();
						result_cx = operationServiceI_cx.excutionInsert(etreeInfoList);

						// 如果两次下发都成功。就插入直接返回 否则获取失败消息。 并回滚已经成功的
						if (ResultString.CONFIG_SUCCESS.equals(result_cx) && ResultString.CONFIG_SUCCESS.equals(result_wh)) {							
							result = ResultString.CONFIG_SUCCESS;
						} else {
							// 修改数据库状态
							for (EtreeInfo etree : etreeInfoList) {
								etree.setActiveStatus(EActiveStatus.UNACTIVITY.getValue());
								etree.setAction(0);
							}
							etreeService.update(etreeInfoList);							
							// 回滚武汉配置
							operationServiceI_wh.excutionDelete(etreeInfoList);
							// 如果晨晓的成功。 错误消息返回武汉的
							if (ResultString.CONFIG_SUCCESS.equals(result_cx)) {
								//因为晨晓不是全量数据所以在内部做回滚
								operationServiceI_cx.excutionDelete(etreeInfoList);
								result = result_wh;
							} else {
								result = result_cx;
							}
						}
					}
				} else {
					etreeService.insert(etreeInfoList);
					result = ResultString.CONFIG_SUCCESS;
				}
				//成功上报操作信息到caoba
				if(etreeInfoList.get(0).getIsSingle()>0){//单网元
					super.notifyCorba("MFDFr", MessageType.CREATION, etreeInfoList, EServiceType.ETREE.getValue()+"", result);
				}else{//端到端
					for (EtreeInfo etreeInfo : etreeInfoList) {
						super.notifyCorba("FDFr", MessageType.CREATION, etreeInfo, EServiceType.ETREE.getValue()+"",result);
					}
				}
			} else {
				throw new Exception("object is null");
			}
		} catch (BusinessIdException e) {
			return e.getMessage();
		}catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			etreeInfoList = null;
			UiUtil.closeService_MB(etreeService);
			pwIdList = new ArrayList<Integer>();
			operationServiceI_wh = null;
			operationServiceI_cx = null;
			result_wh = null;
			result_cx = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			WhImplUtil whImplUtil = new WhImplUtil();
			return whImplUtil.offLineResult(object);
		} else {
			return result;
		}
	}

	@SuppressWarnings("unchecked")
	public String excuteUpdate(Object object) throws Exception {
		List<EtreeInfo> etreeInfoList = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		EtreeInfo etreeInfo = null;
		List<EtreeInfo> beforeetreeInfoList = null;
		EtreeInfoService_MB etreeService = null;
		List<Integer> pwIdList = null;
		OperationServiceI operationServiceI_wh = null;
		OperationServiceI operationServiceI_cx = null;
		String result_wh = null;
		String result_cx = null;
		try {
			etreeInfoList = (List<EtreeInfo>) object;
			
			etreeService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
			// 保存修改以前的对象，关系激活状态
			etreeInfo = new EtreeInfo();
			etreeInfo.setServiceId(etreeInfoList.get(0).getServiceId());
			beforeetreeInfoList = etreeService.select(etreeInfo);
			etreeService.clearLanId(beforeetreeInfoList);
			for(EtreeInfo etreeInfo_update:etreeInfoList){
				etreeInfo_update.setBeforeActiveStatus(beforeetreeInfoList.get(0).getActiveStatus());
			}

			//验证两次的激活状态
			if (beforeetreeInfoList.get(0).getActiveStatus() == EActiveStatus.UNACTIVITY.getValue() && etreeInfoList.get(0).getActiveStatus() == EActiveStatus.UNACTIVITY.getValue()) {
				etreeService.update(etreeInfoList);	
				result = ResultString.CONFIG_SUCCESS;
			} else {
				//先入库
				etreeService.update(etreeInfoList);				
				// 激活操作要作校验,承载的pw必须为激活状态
				pwIdList = new ArrayList<Integer>();
				for (EtreeInfo info : etreeInfoList) {
					pwIdList.add(info.getPwId());
				}
				if (etreeInfoList.get(0).getActiveStatus() == EActiveStatus.ACTIVITY.getValue() && !isPwActive(pwIdList)) {
					result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL_PW_ACTIVITY);
					this.deleteDao(etreeInfoList);
					etreeService.insert(beforeetreeInfoList);
				}else{
					//关联pw
					etreeService.relevancePw(etreeInfoList);
					// 下发武汉的
					operationServiceI_wh = new EtreeWHServiceImpl();
					result_wh = operationServiceI_wh.excutionUpdate(etreeInfoList);
					// 下发晨晓的
					operationServiceI_cx = new EtreeCXServiceImpl();
					result_cx = operationServiceI_cx.excutionUpdate(etreeInfoList);
					// 如果两次下发都成功。就插入直接返回 否则获取失败消息。 并回滚已经成功的
					if (ResultString.CONFIG_SUCCESS.equals(result_cx) && ResultString.CONFIG_SUCCESS.equals(result_wh)) {
						// 插入数据库
						result = ResultString.CONFIG_SUCCESS;
					} else {
//						etreeService.update(beforeetreeInfoList);
						this.deleteDao(etreeInfoList);
						etreeService.insert(beforeetreeInfoList);
						// 回滚武汉配置
//						operationServiceI_wh.excutionDelete(etreeInfoList);
						// 如果晨晓的成功。 错误消息返回武汉的
						if (ResultString.CONFIG_SUCCESS.equals(result_cx)) {
							//因为晨晓不是全量数据所以在内部做回滚
							operationServiceI_cx.excutionDelete(etreeInfoList);
							result = result_wh;
						} else {
							result = result_cx;
						}
					}
				
				}
			}
			if(etreeInfoList != null && etreeInfoList.size()>0){
				//成功上报操作信息到caoba
				for (int i = 0; i < beforeetreeInfoList.size(); i++) {
					//如果修改的字段被修改，则上报消息通知
					reportMsg(etreeInfoList.get(i),beforeetreeInfoList.get(i),result);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(etreeService);
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			WhImplUtil whImplUtil = new WhImplUtil();
			return whImplUtil.offLineResult(object);
		} else {
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
	public void deleteDao(List<EtreeInfo> etreeinfoList) throws Exception {
		List<Integer> serviceidList = null;
		EtreeInfoService_MB etreeService = null;
		try {
			etreeService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
			serviceidList = new ArrayList<Integer>();
			for (EtreeInfo etreeInfo : etreeinfoList) {
				if (!serviceidList.contains(etreeInfo.getServiceId())) {
					etreeService.delete(etreeInfo.getServiceId());
					serviceidList.add(etreeInfo.getServiceId());
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			serviceidList = null;
			UiUtil.closeService_MB(etreeService);
		}
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
	private void updateStatus(List<EtreeInfo> etreeInfoList, int status) throws Exception {
		List<Integer> serviceIds = null;
		EtreeInfoService_MB etreeService = null;

		try {
			serviceIds = new ArrayList<Integer>();
			for (EtreeInfo etreeInfo : etreeInfoList) {
				if (!serviceIds.contains(etreeInfo.getServiceId())) {
					serviceIds.add(etreeInfo.getServiceId());
				}
			}
			etreeService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
			etreeService.updateStatusActivate(serviceIds, status);
		} catch (Exception e) {
			throw e;
		} finally {
			serviceIds = null;
			UiUtil.closeService_MB(etreeService);
		}
	}
	
	private List<EtreeInfo> getActivity(List<EtreeInfo> etreeInfoList){
		 List<EtreeInfo> etreeInfo_activity=new ArrayList<EtreeInfo>();
		 
		 for(EtreeInfo etreeInfo : etreeInfoList){
			 if(etreeInfo.getActiveStatus()==EActiveStatus.ACTIVITY.getValue()){
				 etreeInfo_activity.add(etreeInfo);
			 }
		 }
		 return etreeInfo_activity;
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
		try {
			SiteUtil siteUtil=new SiteUtil();
			if(0==siteUtil.SiteTypeUtil(siteId)){
				if (super.getManufacturer(siteId) == EManufacturer.WUHAN.getValue()) {
					operationServiceI = new EtreeWHServiceImpl();
				} else {
					operationServiceI = new EtreeCXServiceImpl();
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
	 * @param etreeInfo 要修改的etreeInfo
	 * @param beforeEtreeInfo 原有的etreeInfo
	 * @param result 是否上传标识
	 * @throws Exception 
	 */
	private void reportMsg(EtreeInfo etreeInfo,EtreeInfo beforeEtreeInfo,String result) throws Exception{
		Map<String, String> attributeMap = new HashMap<String, String>();//上报的修改的属性集合
		Map<String, String> stateMap = new HashMap<String, String>();//上报的状态的属性集合
		if (null != etreeInfo.getName() && null != beforeEtreeInfo.getName()&&!etreeInfo.getName().equals(beforeEtreeInfo.getName())) {
			attributeMap.put("userLabel", etreeInfo.getName());
		}
		if (etreeInfo.getActiveStatus() != beforeEtreeInfo.getActiveStatus()) {
			stateMap.put("status", etreeInfo.getActiveStatus()+"");
		}
		//成功上报操作信息到caoba
		try {
			if (attributeMap.size()>0) {
				attributeMap.put("id", etreeInfo.getId()+"");
				if(etreeInfo.getIsSingle()>0){//单网元
					if (etreeInfo.getaSiteId() > 0) {
						attributeMap.put("siteId", etreeInfo.getaSiteId()+"");
					}else if (etreeInfo.getzSiteId() > 0) {
						attributeMap.put("siteId", etreeInfo.getzSiteId()+"");
					}
					super.notifyCorba("MFDFr", MessageType.ATTRIBUTECHG, attributeMap, EServiceType.ETREE.getValue()+"", result);
				}else{//端到端
					super.notifyCorba("FDFr", MessageType.ATTRIBUTECHG, attributeMap, EServiceType.ETREE.getValue()+"",result);
				}
			}	
			//状态改变上报
			if (stateMap.size()>0) {
				stateMap.put("id", etreeInfo.getId()+"");
				if(etreeInfo.getIsSingle()>0){//单网元
					if (etreeInfo.getaSiteId() > 0) {
						stateMap.put("siteId", etreeInfo.getaSiteId()+"");
					}else if (etreeInfo.getzSiteId() > 0) {
						stateMap.put("siteId", etreeInfo.getzSiteId()+"");
					}
					super.notifyCorba("MFDFr", MessageType.STATECHG, stateMap, EServiceType.ETREE.getValue()+"", result);
				}else{//端到端
					super.notifyCorba("FDFr", MessageType.STATECHG, stateMap, EServiceType.ETREE.getValue()+"",result);
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
				EtreeWHServiceImpl operationServiceI = new EtreeWHServiceImpl();
				return operationServiceI.consistence(siteId);
			}
		} catch (Exception e) {
			throw e;
		}
		return null;
	}
	
}
