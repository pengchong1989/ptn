package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;

import com.nms.db.bean.ptn.clock.FrequencyInfoNeClock;
import com.nms.db.bean.ptn.clock.TimeManageInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.FrequencySiteServiceImpl;
import com.nms.service.impl.dispatch.rmi.ClockDispatchI;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;
/**
 * 网元时钟状态
 * @author sy
 *
 */
public class FrequencySiteDispatch extends DispatchBase implements ClockDispatchI{
	/**
	 * 保存
	 * @param frequencyInfo_neClock
	 * @return
	 * @throws Exception
	 */
	public String excuteUpdate(Object object) throws Exception {
		int manufacturer = 0;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		OperationServiceI frequencySiteServiecI=null;
		FrequencyInfoNeClock frequencyInfo_neClock=null;
		try {
			if (object == null) {
				throw new Exception("frequencyInfo_neClock is null");
			}
			frequencyInfo_neClock=(FrequencyInfoNeClock) object;
			
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_SAVEANDUPDATE,frequencyInfo_neClock);
			if(null!=siteCheck){
				return siteCheck;
			}
//			int manuReturn= frequencyInfo_neClockService.update(frequencyInfo_neClock);
			manufacturer = super.getManufacturer(frequencyInfo_neClock.getSiteId());
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
			} else {
				frequencySiteServiecI=new FrequencySiteServiceImpl();
			}
			result = frequencySiteServiecI.excutionUpdate(frequencyInfo_neClock);
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
	 * @param objectList
	 *       网元数据库id
	 *       查    DB
	 * @return
	 * @throws Exception
	 */
	public FrequencyInfoNeClock executeQueryFrequencyBySites(int siteId) throws Exception {
		FrequencySiteServiceImpl frequencySiteServiecImpl=null;
		FrequencyInfoNeClock frequencyInfo_neClock = null;
		int manufacturer = 0;
		try {			
			frequencyInfo_neClock = new FrequencyInfoNeClock();
			manufacturer = super.getManufacturer(siteId);				
			if (manufacturer==EManufacturer.CHENXIAO.getValue()) {
				
				frequencySiteServiecImpl=new FrequencySiteServiceImpl();
			} else {
			}
			frequencyInfo_neClock=frequencySiteServiecImpl.excutionSelect(siteId);
		} catch (Exception e) {
		} finally {
			frequencySiteServiecImpl=null;
		}
		return frequencyInfo_neClock;
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
	public String synchro(int siteId) throws RemoteException, Exception {
		return null;
	}

	@Override
	public String clcokRorate(Object object) throws RemoteException, Exception {
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
