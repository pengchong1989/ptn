package com.nms.service.impl.dispatch.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * 告警RMI接口
 * 
 * @author kk
 * 
 */
public interface AlarmDispatchI extends Remote {
	/**
	 * 查询网元告警
	 * 
	 * @param objectList
	 *            网元集合
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String executeQueryHisAlarmBySites(List<Integer> objectList) throws RemoteException, Exception;
	
	/**
	 * 同步网元告警
	 * @param siteId
	 * @throws Exception
	 */
	public void synchroCurrentAlarm(Object siteId)throws Exception;

	/**
	 * 清除告警
	 * @param object
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void clearAlarm(Object object)throws RemoteException, Exception;

	/**
	 * 告警屏蔽
	 * @param object
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String alarmSheild(Object object)throws RemoteException, Exception;
	
}
