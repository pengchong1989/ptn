package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;

import com.nms.db.bean.ptn.clock.FrequencyInfoNeClock;
import com.nms.db.bean.ptn.clock.TimeManageInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.PtpSiteServiceImpl;
import com.nms.service.impl.dispatch.rmi.ClockDispatchI;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

/**
 * 网元PTP配置 下发
 * 
 * @author sy
 * 
 */
public class PtpSiteDispatch extends DispatchBase implements ClockDispatchI {

	/**
	 * 保存
	 * 
	 * @param timeManageInfo
	 * @return
	 * @throws Exception
	 */
	public String excuteUpdate(Object object) throws Exception {
		int manufacturer = 0;
		PtpSiteServiceImpl ptpSiteServiceI = null;
		String result =  ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		TimeManageInfo timeManageInfo = null;
		try {
			if (object == null) {
				throw new Exception("timeManageInfo is null");
			}
			timeManageInfo = (TimeManageInfo) object;
			// 虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck = (String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_SAVEANDUPDATE, timeManageInfo);
			if (null != siteCheck) {
				return siteCheck;
			}
			// int manuReturn= timeManageInfoService.update(timeManageInfo);
			manufacturer = super.getManufacturer(timeManageInfo.getSiteId());
			if (manufacturer == EManufacturer.WUHAN.getValue()) {

			} else {
				ptpSiteServiceI = new PtpSiteServiceImpl();
			}
			result = ptpSiteServiceI.excutionUpdate(timeManageInfo);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		} else {
			return result;
		}

	}

	/**
	 * 单网元
	 * 
	 * @param objectList
	 *            网元数据库id 查 DB
	 * @return
	 * @throws Exception
	 */
	public TimeManageInfo executeQueryTimeManageInfoBySites(int siteId) throws Exception {
		PtpSiteServiceImpl ptpSiteServiceImpl = null;
		TimeManageInfo timeManageInfo = null;
		int manufacturer = 0;
		try {
			manufacturer = super.getManufacturer(siteId);
			if (manufacturer == EManufacturer.CHENXIAO.getValue()) {
				ptpSiteServiceImpl = new PtpSiteServiceImpl();
			} else {
			}
			timeManageInfo = ptpSiteServiceImpl.excutionSelect(siteId);

		} catch (Exception e) {
		} finally {
			ptpSiteServiceImpl = null;
		}
		return timeManageInfo;
	}

	/**
	 * 同步
	 * 
	 * @param siteId
	 * @throws Exception
	 */
	public String synchro(int siteId) throws Exception {
		OperationServiceI operationServiceI = null;
		TimeManageInfo timeManageInfo=null;
		String result =  ResultString.QUERY_FAILED;
		try { 
			timeManageInfo=new TimeManageInfo();
			timeManageInfo.setSiteId(siteId);
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_SELECT,timeManageInfo);
			if(null!=siteCheck){
				return siteCheck;
			}
			if (super.getManufacturer(siteId) == EManufacturer.CHENXIAO.getValue()) {
				operationServiceI = new PtpSiteServiceImpl();
			} else {
				
			}
			result = (String)operationServiceI.synchro(siteId);

		} catch (Exception e) {
			throw e;
		}
           return result;
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
	public String clcokRorate(Object object) throws RemoteException, Exception {
		return null;
	}

	@Override
	public FrequencyInfoNeClock executeQueryFrequencyBySites(int siteId) throws RemoteException, Exception {
		return null;
	}

	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
