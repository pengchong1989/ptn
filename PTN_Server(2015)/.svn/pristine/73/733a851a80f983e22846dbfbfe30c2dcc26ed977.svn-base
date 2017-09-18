package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.ptn.CccInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.EServiceType;
import com.nms.model.ptn.CccService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.service.impl.wh.CccWHServiceImpl;
import com.nms.service.notify.Message.MessageType;
import com.nms.ui.manager.BusinessIdException;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class CccDispatch extends DispatchBase implements DispatchInterface{
	@SuppressWarnings("unchecked")
	public String excuteDelete(Object object) throws Exception {
		List<CccInfo> cccList = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		OperationServiceI operationServiceI_wh = null;
		String result_wh = null;
		List<CccInfo> cccList_activity=null;
		try {
			cccList = (List<CccInfo>) object;
			if (cccList != null && cccList.size() > 0) {
				cccList_activity=this.getActivity(cccList);
				if(cccList_activity.size()==0){
					this.deleteDao(cccList);
					result = ResultString.CONFIG_SUCCESS;
				}else {
					this.updateStatus(cccList, EActiveStatus.UNACTIVITY.getValue());
					// 下发武汉的
					operationServiceI_wh = new CccWHServiceImpl();
					result_wh = operationServiceI_wh.excutionDelete(cccList);
					// 判断是否都成功，都成功返回结果，有不成功的 回滚、返回错误消息
					if (ResultString.CONFIG_SUCCESS.equals(result_wh)) {
						this.deleteDao(cccList);
						result = ResultString.CONFIG_SUCCESS;						
					}else{	
						this.updateStatus(cccList, EActiveStatus.ACTIVITY.getValue());
						// 回滚
						operationServiceI_wh.excutionInsert(cccList);
						result = result_wh;
					}
				}
			} else {
				throw new Exception("objectList is null");
			}
			//成功上报操作信息到caoba
			if (cccList.get(0).getIsSingle() > 0) {
				super.notifyCorba("MFDFr", MessageType.DELETION, cccList, EServiceType.CCC.getValue()+"", result);
				}else{//端到端
				for (CccInfo cccInfo : cccList) {
					super.notifyCorba("FDFr", MessageType.DELETION, cccInfo, EServiceType.CCC.getValue()+"",result);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			cccList = null;
			operationServiceI_wh = null;
			result_wh = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			WhImplUtil whImplUtil = new WhImplUtil();
			return whImplUtil.offLineResult(object);
		} else {
			return result;
		}
	}

	
	public String excuteInsert(Object object) throws Exception {
		CccInfo cccInfo = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		CccService_MB cccService = null;		
		OperationServiceI operationServiceI_wh = null;
		String result_wh = null;
		List<CccInfo> cccinfoList = null;
		try {
			cccInfo = (CccInfo)object;

			if (null != cccInfo ) {				
				cccService = (CccService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CCCMANAGEMENT);
				/** 如果是激活状态 就下发到设备 */
				if (cccInfo.getActiveStatus() == EActiveStatus.ACTIVITY.getValue()) {										
						// 插入数据库
						cccService.insert(cccInfo);
						// 下发武汉的
						operationServiceI_wh = new CccWHServiceImpl();
						result_wh = operationServiceI_wh.excutionInsert(cccInfo);
						// 下发晨晓的	
//						operationServiceI_cx = new EtreeCXServiceImpl();
//						result_cx = operationServiceI_cx.excutionInsert(cccInfoList);

						// 如果两次下发都成功。就插入直接返回 否则获取失败消息。 并回滚已经成功的
						if ( ResultString.CONFIG_SUCCESS.equals(result_wh)) {
							result = ResultString.CONFIG_SUCCESS;
						} else {
//							// 修改数据库状态							
								cccInfo.setActiveStatus(EActiveStatus.UNACTIVITY.getValue());
								cccInfo.setAction(0);							
							    cccService.update(cccInfo);
							    cccinfoList = new ArrayList<CccInfo>();
							    cccinfoList.add(cccInfo);
							// 回滚武汉配置
							    operationServiceI_wh.excutionDelete(cccinfoList);						
							    result = result_wh;						
					}
				} else {
					cccService.insert(cccInfo);
					result = ResultString.CONFIG_SUCCESS;
				}
				
				//成功上报操作信息到caoba
				super.notifyCorba("MFDFr", MessageType.CREATION, cccInfo, EServiceType.ETREE.getValue()+"", result);
				
			} else {
				throw new Exception("object is null");
			}
		} catch (BusinessIdException e) {
			return e.getMessage();
		}catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(cccService);			
			operationServiceI_wh = null;		
			result_wh = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			WhImplUtil whImplUtil = new WhImplUtil();
			return whImplUtil.offLineResult(object);
		} else {
			return result;
		}
	}


	public String excuteUpdate(Object object) throws Exception {		
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		CccInfo cccInfo = null;
		List<CccInfo> beforecccInfoList =null;
		CccService_MB cccService = null;		
		OperationServiceI operationServiceI_wh = null;
		String result_wh = null;
		try {
			cccInfo = (CccInfo) object;			
			cccService = (CccService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CCCMANAGEMENT);
			beforecccInfoList = cccService.select(cccInfo);	
            //如果两次的AC和激活状态都没有变，则只需入库
			if(cccInfo.getAction()==0 && cccInfo.getActiveStatus() == cccInfo.getBeforeActiveStatus()){
				cccService.update(cccInfo);
				result = ResultString.CONFIG_SUCCESS;
			}else{						
				//先入库
				cccService.update(cccInfo);											
				// 下发武汉的
				operationServiceI_wh = new CccWHServiceImpl();
				result_wh = operationServiceI_wh.excutionUpdate(cccInfo);

				// 如果两次下发都成功。就插入直接返回 否则获取失败消息。 并回滚已经成功的
				if (ResultString.CONFIG_SUCCESS.equals(result_wh)) {
					// 插入数据库
					result = ResultString.CONFIG_SUCCESS;
				} else {
					List<CccInfo> cccInfoList = new ArrayList<CccInfo>();
					cccInfoList.add(cccInfo);
					this.deleteDao(cccInfoList);
					
					// 保存修改以前的对象，关系激活状态						
					cccService.insert(beforecccInfoList.get(0));
					result = result_wh;
						
				}
				
			}
			
			if(cccInfo!= null ){
				//成功上报操作信息到caoba				
				//如果修改的字段被修改，则上报消息通知
				reportMsg(cccInfo,beforecccInfoList.get(0),result);
				
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(cccService);
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			WhImplUtil whImplUtil = new WhImplUtil();
			return whImplUtil.offLineResult(object);
		} else {
			return result;
		}
	}

	/**
	 * 
	 * deleteDao(批量删除数据库)
	 * 
	 * 
	 * @author tp
	 * 
	 * @param ccc集合
	 * 
	 * @Exception 异常对象
	 */
	public void deleteDao(List<CccInfo> cccinfoList) throws Exception {
		List<Integer> serviceidList = null;
		CccService_MB cccService = null;
		try {
			cccService = (CccService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CCCMANAGEMENT);
			serviceidList = new ArrayList<Integer>();
			for (CccInfo cccInfo : cccinfoList) {
				if (!serviceidList.contains(cccInfo.getServiceId())) {
					cccService.delete(cccInfo.getServiceId());
					serviceidList.add(cccInfo.getServiceId());
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			serviceidList = null;
			UiUtil.closeService_MB(cccService);
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
	private void updateStatus(List<CccInfo> cccInfoList, int status) throws Exception {
		List<Integer> serviceIds = null;
		CccService_MB cccService = null;

		try {
			serviceIds = new ArrayList<Integer>();
			for (CccInfo cccInfo : cccInfoList) {
				if (!serviceIds.contains(cccInfo.getServiceId())) {
					serviceIds.add(cccInfo.getServiceId());
				}
			}
			cccService = (CccService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CCCMANAGEMENT);
			cccService.updateStatusActivate(serviceIds, status);
		} catch (Exception e) {
			throw e;
		} finally {
			serviceIds = null;
			UiUtil.closeService_MB(cccService);
		}
	}
	
	private List<CccInfo> getActivity(List<CccInfo> cccInfoList){
		 List<CccInfo> cccInfo_activity=new ArrayList<CccInfo>();		 
		 for(CccInfo cccInfo : cccInfoList){
			 if(cccInfo.getActiveStatus()==EActiveStatus.ACTIVITY.getValue()){
				 cccInfo_activity.add(cccInfo);
			 }
		 }
		 return cccInfo_activity;
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
					operationServiceI = new CccWHServiceImpl();
				} else {
					//operationServiceI = new EtreeCXServiceImpl();
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
	private void reportMsg(CccInfo cccInfo,CccInfo beforeCccInfo,String result) throws Exception{
		Map<String, String> attributeMap = new HashMap<String, String>();//上报的修改的属性集合
		Map<String, String> stateMap = new HashMap<String, String>();//上报的状态的属性集合
		if (null != cccInfo.getName() && null != beforeCccInfo.getName()&&!cccInfo.getName().equals(beforeCccInfo.getName())) {
			attributeMap.put("userLabel", cccInfo.getName());
		}
		if (cccInfo.getActiveStatus() != beforeCccInfo.getActiveStatus()) {
			stateMap.put("status", cccInfo.getActiveStatus()+"");
		}
		//成功上报操作信息到caoba
		try {
			if (attributeMap.size()>0) {
				attributeMap.put("id", cccInfo.getId()+"");			
					if (cccInfo.getaSiteId() > 0) {
						attributeMap.put("siteId", cccInfo.getaSiteId()+"");
					}
					super.notifyCorba("MFDFr", MessageType.ATTRIBUTECHG, attributeMap, EServiceType.CCC.getValue()+"", result);
				
			}	
			//状态改变上报
			if (stateMap.size()>0) {
				stateMap.put("id", cccInfo.getId()+"");
					if (cccInfo.getaSiteId() > 0) {
						stateMap.put("siteId", cccInfo.getaSiteId()+"");
					}
					super.notifyCorba("MFDFr", MessageType.STATECHG, stateMap, EServiceType.CCC.getValue()+"", result);
				
			}	
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		try {
			if (super.getManufacturer(siteId) == EManufacturer.WUHAN.getValue()) {
				CccWHServiceImpl operationServiceI = new CccWHServiceImpl();
				return operationServiceI.consistence(siteId);
			}
		} catch (Exception e) {
			throw e;
		}
		return null;
	}
	
}
