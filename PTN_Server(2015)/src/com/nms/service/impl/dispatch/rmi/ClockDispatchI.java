package com.nms.service.impl.dispatch.rmi;

import java.rmi.RemoteException;

import com.nms.db.bean.ptn.clock.FrequencyInfoNeClock;
import com.nms.db.bean.ptn.clock.TimeManageInfo;

/**
 * 时钟模块rmi接口
 * 
 * @author kk
 * 
 */
public interface ClockDispatchI extends DispatchInterface {

	/**
	 * 倒换
	 * 
	 * @param object
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String clcokRorate(Object object) throws RemoteException, Exception;

	/**
	 * 根据网元查询对象
	 * @param siteId
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public FrequencyInfoNeClock executeQueryFrequencyBySites(int siteId) throws RemoteException, Exception;
	
	public TimeManageInfo executeQueryTimeManageInfoBySites(int siteId) throws RemoteException, Exception; 
}
