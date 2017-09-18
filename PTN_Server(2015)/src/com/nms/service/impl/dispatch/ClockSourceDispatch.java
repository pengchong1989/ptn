package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.clock.ClockSource;
import com.nms.db.enums.EManufacturer;
import com.nms.model.ptn.clock.FrequencyClockManageService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.ClockSourceCXServiceImpl;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.BusinessIdException;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

/**
 * 时钟源配置/状态设备调度
 * 
 * @author dzy
 * 
 */
public class ClockSourceDispatch extends DispatchBase implements DispatchInterface {
	/**
	 * 添加时钟源
	 * 
	 * @param clockSource
	 * @return
	 * @throws Exception
	 */
	public String excuteInsert(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		List<ClockSource> clockSourceIdList = null;
		ClockSource clockSource=null;
		FrequencyClockManageService_MB frequencyClockManageService = null;
		try {
			if (object == null) {
				throw new Exception("clockSource is null");
			}
			clockSource=(ClockSource) object;

			// 虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck = (String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_INSERT, clockSource);
			if (null != siteCheck) {
				return siteCheck;
			}
			frequencyClockManageService = (FrequencyClockManageService_MB) ConstantUtil.serviceFactory.newService_MB(Services.FrequencyClockManageService);
			int clockSourceId = frequencyClockManageService.insert(clockSource);
			manufacturer = super.getManufacturer(clockSource.getSiteId());
			if (manufacturer == EManufacturer.WUHAN.getValue()) {

			} else {
				operationServiceI = new ClockSourceCXServiceImpl();
			}
			result = operationServiceI.excutionInsert(clockSource);
			if (clockSourceId > 0 && ResultString.CONFIG_SUCCESS.equals(result)) {
				result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			} else {
				clockSourceIdList = new ArrayList<ClockSource>();
				clockSourceIdList.add(clockSource);
			//	frequencyClockManageService.delete(clockSourceIdList);
			}
		} catch (BusinessIdException e) {
			clockSourceIdList = new ArrayList<ClockSource>();
			clockSourceIdList.add(clockSource);
//			return e.getMessage();
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(frequencyClockManageService);
			operationServiceI = null;
			clockSourceIdList = null;
		}
		return result;
	}

	/**
	 * 修改时钟源
	 * 
	 * @param clockSource
	 * @return
	 * @throws Exception
	 */
	public String excuteUpdate(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		List<ClockSource> beforeClockSource;
		ClockSource clockSource=null;
		FrequencyClockManageService_MB frequencyClockManageService = null;
		try {
			if (object == null) {
				throw new Exception("clockSource is null");
			}
			clockSource=(ClockSource) object;
			// 虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck = (String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_UPDATE, clockSource);
			if (null != siteCheck) {
				return siteCheck;
			}
			frequencyClockManageService = (FrequencyClockManageService_MB) ConstantUtil.serviceFactory.newService_MB(Services.FrequencyClockManageService);
			beforeClockSource = new ArrayList<ClockSource>();
			beforeClockSource = frequencyClockManageService.select(clockSource.getId());
			int clockSourceId = frequencyClockManageService.update(clockSource);

			manufacturer = super.getManufacturer(clockSource.getSiteId());

			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				// operationServiceI = new ClockSourceWHServiceImpl();
			} else {
				operationServiceI = new ClockSourceCXServiceImpl();
			}
			result = operationServiceI.excutionUpdate(clockSource);
			if (clockSourceId > 0 && ResultString.CONFIG_SUCCESS.equals(result)) {
				result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			} else {
				frequencyClockManageService.update(beforeClockSource.get(0));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(frequencyClockManageService);
		}
		return result;
	}

	/**
	 * 删除时钟源
	 * 
	 * @param clockSourceList
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String excuteDelete(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		List<ClockSource> clockSourceList = null;
		FrequencyClockManageService_MB frequencyClockManageService = null;
		try {
			clockSourceList=(List<ClockSource>) object;
			
			// 虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck = (String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_DELETE, clockSourceList);
			if (null != siteCheck) {
				return siteCheck;
			}
			frequencyClockManageService = (FrequencyClockManageService_MB) ConstantUtil.serviceFactory.newService_MB(Services.FrequencyClockManageService);
			int clockSourceId = frequencyClockManageService.delete(clockSourceList);
			manufacturer = super.getManufacturer(clockSourceList.get(0).getSiteId());
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				// operationServiceI = new ClockSourceWHServiceImpl();
			} else {
				operationServiceI = new ClockSourceCXServiceImpl();
			}
			result = operationServiceI.excutionDelete(clockSourceList);
			if (clockSourceId > 0 && ResultString.CONFIG_SUCCESS.equals(result)) {
				result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			} else {
				for (ClockSource clockSource : clockSourceList) {
					frequencyClockManageService.insert(clockSource);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(frequencyClockManageService);
		}
		return result;
	}

	/**
	 * 同步
	 * 
	 * @author dzy
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
		int manufacturer = 0;
		String result = ResultString.QUERY_FAILED;
		try {
			// 虚拟网元操作
			SiteUtil siteUtil=new SiteUtil();
			String siteCheck = (String) siteUtil.irtualSiteSynchroAction(TypeAndActionUtil.ACTION_SYNCHRO, siteId);
			if (null != siteCheck) {
				return siteCheck;
			}
			manufacturer = super.getManufacturer(siteId);
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				// operationServiceI = new ClockSourceWHServiceImpl();
			} else {
				operationServiceI = new ClockSourceCXServiceImpl();
			}
			result = (String)operationServiceI.synchro(siteId);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
