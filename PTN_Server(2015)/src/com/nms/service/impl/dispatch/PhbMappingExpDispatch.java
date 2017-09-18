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
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.service.impl.wh.PhbMappingExpWHServiceImpl;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class PhbMappingExpDispatch extends DispatchBase implements DispatchInterface{
	/**
	 * 更新映射表
	 * @param mappingModeList
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String excuteUpdate(Object object) throws Exception {
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		List<QosMappingMode> mappingModeList = null;
		QosMappingMode beforeMode = null;
		QosMappingModeService_MB modeService = null;
		try {
			if (object == null) {
				throw new Exception("mappingModeList is null");
			}
			mappingModeList = (List<QosMappingMode>) object;
			QosMappingMode qosMode = mappingModeList.get(0);
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
				operationServiceI = new PhbMappingExpWHServiceImpl();
				result = operationServiceI.excutionUpdate(qosMode);
				if (!ResultString.CONFIG_SUCCESS.equals(result)) {
					//回滚
					mappingModeList.clear();
					mappingModeList.add(beforeMode);
					modeService.saveOrUpdate(mappingModeList);
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
				}else{
					String str = this.getOfflineSiteIdNames(mappingModeList.get(0));
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
			UiUtil.closeService_MB(modeService);
		}
		return result;
	}

	private String getOfflineSiteIdNames(QosMappingMode qosMappingMode) throws Exception {
		List<Integer> siteIds = null;
		String str = "";
		try {
			siteIds = new ArrayList<Integer>();
			siteIds.add(qosMappingMode.getSiteId());
			str = new WhImplUtil().getNeNames(siteIds);
		} catch (Exception e) {
			throw e;
		}
		return str;
	}

	/**
	 * 与设备进行同步
	 * @param siteId
	 * @throws Exception
	 */
	public String synchro(int siteId) throws Exception{
		OperationServiceI operationServiceI = null;
		try {
			//虚拟网元不同步设备
			SiteUtil siteUtil=new SiteUtil();
			if(0==siteUtil.SiteTypeUtil(siteId)){
				if (super.getManufacturer(siteId) == EManufacturer.WUHAN.getValue()) {
					operationServiceI = new PhbMappingExpWHServiceImpl();
				} else {
					
				}
				return (String)operationServiceI.synchro(siteId);
			}
		} catch (Exception e) {
			throw e;
		}
		return ResultString.QUERY_FAILED;
	}

	@Override
	public String excuteDelete(Object object) throws RemoteException, Exception {
		return null;
	}

	@Override
	public String excuteInsert(Object object) throws RemoteException, Exception {
		return null;
	}

	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
