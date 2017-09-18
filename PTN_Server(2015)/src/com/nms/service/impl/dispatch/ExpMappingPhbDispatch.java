package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.qos.QosMappingMode;
import com.nms.db.enums.EManufacturer;
import com.nms.model.ptn.qos.QosMappingModeService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.ExpMappingCXServiceImpl;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.service.impl.wh.ExpMappingPhbWHServiceImpl;
import com.nms.ui.manager.BusinessIdException;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class ExpMappingPhbDispatch extends DispatchBase implements DispatchInterface{
	
	/**
	 * 添加 （针对陈晓的设备的方法， 武汉无此方法）
	 * 
	 * @param object  
	 * 
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String excuteInsert(Object object) throws RemoteException, Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		List<QosMappingMode> mappingModeList=null;
		
		try {
			if (object == null) {
				throw new Exception("mspProtect is null");
			}
			mappingModeList=(List<QosMappingMode>) object;
			for(QosMappingMode qosMappingMode:mappingModeList){
				
				//通过网元ID判断设备类型
				manufacturer = super.getManufacturer(qosMappingMode.getSiteId());
				if (manufacturer == EManufacturer.WUHAN.getValue()) {
					//武汉设备转换
				} else {
					operationServiceI = new ExpMappingCXServiceImpl();
					result = operationServiceI.excutionInsert(qosMappingMode);
				}
			}
		} catch (BusinessIdException e) {  //数量超出设备限制
			return ResourceUtil.srcStr(StringKeysTip.TIP_BUSINESSID_NULL);
		}catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			//UiUtil.insertOperationLog(EOperationLogType.EXPMAPPINGINSERT.getValue(),result);
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			if(!siteUtil.isNeOnLine(mappingModeList.get(0).getSiteId())){
				return ResultString.OFF_LINE_SUCCESS;
			}
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		} else {
			//UiUtil.insertOperationLog(EOperationLogType.EXPMAPPINGINSERT.getValue(),result);
			return result;
		}
	}
	
	
	/**
	 * 更新映射表
	 * @param mappingModeList
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String excuteUpdate(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		List<QosMappingMode> mappingModeList=null;
		QosMappingModeService_MB modeService = null;
		QosMappingMode beforeMode = null;
		try {
			if (object == null) {
				throw new Exception("mappingModeList is null");
			}
			mappingModeList=(List<QosMappingMode>) object;
			QosMappingMode qosMode = mappingModeList.get(0);
			manufacturer = super.getManufacturer(qosMode.getSiteId());
			if (manufacturer==EManufacturer.WUHAN.getValue()) {
				SiteUtil siteUtil = new SiteUtil();
				//虚拟网元操作
				String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_UPDATE, qosMode);
				if(null != siteCheck){
					result = ResultString.CONFIG_SUCCESS;
				}else{
					//保存修改以前的对象
					beforeMode = new QosMappingMode();
					beforeMode.setId(qosMode.getId());
					beforeMode.setSiteId(qosMode.getSiteId());
					modeService = (QosMappingModeService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosMappingModeService);
					beforeMode = modeService.queryByCondition(beforeMode).get(0);
					//先修改数据库，根据结果来判断是否需要回滚
					modeService.saveOrUpdate(mappingModeList);
					operationServiceI = new ExpMappingPhbWHServiceImpl();
					result = operationServiceI.excutionUpdate(qosMode);
					if (!ResultString.CONFIG_SUCCESS.equals(result)) {
						//回滚
						mappingModeList.clear();
						mappingModeList.add(beforeMode);
						modeService.saveOrUpdate(mappingModeList);
						return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
					}else{
						String str = this.getOfflineSiteNames(mappingModeList.get(0));
						if(str.equals("")){
							return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
						}else{
							return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+str+ResultString.NOT_ONLINE_SUCCESS;
						}
					}
				}
			} else {
				operationServiceI = new ExpMappingCXServiceImpl();
				result=operationServiceI.excutionUpdate(qosMode);
				if (ResultString.CONFIG_SUCCESS.equals(result)) {
					//虚拟网元操作
					SiteUtil siteUtil = new SiteUtil();
					if(!siteUtil.isNeOnLine(mappingModeList.get(0).getSiteId())){
						return ResultString.OFF_LINE_SUCCESS;
					}
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
				} else {
					return result;
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(modeService);
		}
		return result;
	}

	private String getOfflineSiteNames(QosMappingMode qosMappingMode) throws Exception {
		String str = "";
		List<Integer> siteIds = new ArrayList<Integer>();
		siteIds.add(qosMappingMode.getSiteId());
		str = new WhImplUtil().getNeNames(siteIds);
		return str;
	}


	/**
	 * 删除msp
	 * @param object  
	 * 
	 * @throws Exception
	 *  
	 * @Exception 异常对象
	 */
	@Override
	public String excuteDelete(Object object) throws RemoteException, Exception {
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
	/*	List<QosMappingMode> mappingModeList=null;
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		try {
			if (object != null ) {
				mappingModeList=(List<QosMappingMode>) object;
				
				//虚拟网元操作
				String siteCheck =(String) SiteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_DELETE,mappingModeList);
				if(null!=siteCheck){
					return siteCheck;
				}
				//通过网元ID判断设备类型
				if(null!=mappingModeList&&mappingModeList.size()>0){
					manufacturer = super.getManufacturer(mappingModeList.get(0).getSiteId());
					if (manufacturer == EManufacturer.WUHAN.getValue()) {
						//武汉设备转换
					} else {
						operationServiceI = new ExpMappingCXServiceImpl();
					}
					result = operationServiceI.excutionDelete(mappingModeList);
					if (ResultString.CONFIG_SUCCESS.equals(result)) {
						result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
					}
				} 
			}else {
				throw new Exception("CXMspProtectList is null");
			}
				
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			mappingModeList = null;
			operationServiceI = null;
		}*/
		return result;
	}

	/**
	 * 与设备进行同步
	 * @param siteId
	 * @throws Exception
	 */
	public String synchro(int siteId) throws Exception{
		OperationServiceI operationServiceI = null;
		try {
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			if(!siteUtil.isNeOnLine(siteId)){
				return ResultString.OFF_LINE_SUCCESS;
			}
			//通过网元ID判断设备类型
			if (super.getManufacturer(siteId) == EManufacturer.WUHAN.getValue()) {
				operationServiceI = new ExpMappingPhbWHServiceImpl();
				return (String)operationServiceI.synchro(siteId);
			} else {
				operationServiceI = new ExpMappingCXServiceImpl();
				return (String)operationServiceI.synchro(siteId);
			}
			
		} catch (Exception e) {
			throw e;
		}
	}


	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
