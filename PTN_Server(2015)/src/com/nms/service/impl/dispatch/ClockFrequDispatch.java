package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.clock.FrequencyInfo;
import com.nms.db.bean.ptn.clock.FrequencyInfoNeClock;
import com.nms.db.bean.ptn.clock.TimeManageInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.dispatch.rmi.ClockDispatchI;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.service.impl.wh.ClockFrequWHServiceImpl;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class ClockFrequDispatch extends DispatchBase implements ClockDispatchI{
	public String excuteInsert(Object object) throws Exception {
		FrequencyInfo info = null;
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			info =  (FrequencyInfo) object;
			manufacturer = super.getManufacturer(info.getSiteId());
			// if(manufacturer.equals("武汉")){
			operationServiceI = new ClockFrequWHServiceImpl();
			// }else{
			// }
			result = operationServiceI.excutionInsert(object);
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				List<FrequencyInfo> list = new ArrayList<FrequencyInfo>();
				if(info != null){
					list.add(info);
				}
				String str = this.getOfflineSiteIdNames(list);
				if(str.equals("")){
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
				}else{
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+str+ResultString.NOT_ONLINE_SUCCESS;
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			info = null;
			operationServiceI = null;
		}
			return result;
	}

	public String excuteUpdate(Object object) throws Exception {
		FrequencyInfo info = null;
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			info =  (FrequencyInfo) object;
			manufacturer = super.getManufacturer(info.getSiteId());
			// if(manufacturer.equals("武汉")){
			operationServiceI = new ClockFrequWHServiceImpl();
			// }else{
			// }
			result = operationServiceI.excutionUpdate(object);
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				List<FrequencyInfo> list = new ArrayList<FrequencyInfo>();
				if(info != null){
					list.add(info);
				}
				String str = this.getOfflineSiteIdNames(list);
				if(str.equals("")){
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
				}else{
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+str+ResultString.NOT_ONLINE_SUCCESS;
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			info = null;
			operationServiceI = null;
		}
			return result;
	}
	/**
	 * 时钟倒换
	 * @param object
	 * @return
	 */
	public String clcokRorate(Object object){

		FrequencyInfo info = null;
		int manufacturer = 0;
		ClockFrequWHServiceImpl operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			info =  (FrequencyInfo) object;
			manufacturer = super.getManufacturer(info.getSiteId());
			// if(manufacturer.equals("武汉")){
			operationServiceI = new ClockFrequWHServiceImpl();
			// }else{
			// }
			result = operationServiceI.clockRorate(info);
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				List<FrequencyInfo> list = new ArrayList<FrequencyInfo>();
				if(info != null){
					list.add(info);
				}
				String str = this.getOfflineSiteIdNames(list);
				if(str.equals("")){
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
				}else{
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+str+ResultString.NOT_ONLINE_SUCCESS;
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			info = null;
			operationServiceI = null;
		}
		return result;
	}
	
	private String getOfflineSiteIdNames(List<FrequencyInfo> list) throws Exception {
		List<Integer> siteIds = null;
		String str = "";
		try {
			siteIds = new ArrayList<Integer>();
			for (FrequencyInfo freq : list) {
				siteIds.add(freq.getSiteId());
			}
			str = new WhImplUtil().getNeNames(siteIds);
		} catch (Exception e) {
			throw e;
		}
		return str;
	}

	@Override
	public String excuteDelete(Object object) throws RemoteException, Exception {
		return null;
	}

	@Override
	public String synchro(int siteId) throws RemoteException, Exception {
		OperationServiceI operationServiceI = null;
		try {
			//虚拟网元不同步设备
			SiteUtil siteUtil=new SiteUtil();
			if(0==siteUtil.SiteTypeUtil(siteId)){
				if (super.getManufacturer(siteId) == EManufacturer.WUHAN.getValue()) {
					operationServiceI = new ClockFrequWHServiceImpl();
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
	public FrequencyInfoNeClock executeQueryFrequencyBySites(int siteId) throws RemoteException, Exception {
		return null;
	}

	@Override
	public TimeManageInfo executeQueryTimeManageInfoBySites(int siteId) throws RemoteException, Exception {
		return null;
	}

	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
}

