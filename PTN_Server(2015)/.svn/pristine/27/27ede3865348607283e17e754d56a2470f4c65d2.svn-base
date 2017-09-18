package com.nms.service.impl.dispatch.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceInterface extends Remote{
	/**
	 * 执行修改方法
	 * 
	 * @param object
	 *            要修改的对象
	 * @return 返回结果
	 */
	public String excuteUpdate(Object object) throws RemoteException,Exception;

	/**
	 * 执行新建方法
	 * 
	 * @param object
	 *            要修改的对象
	 * @return 返回结果
	 */
	public String excuteInsert(Object object) throws RemoteException,Exception;

	/**
	 * 执行删除方法
	 * 
	 * @param object
	 *            要修改的对象
	 * @return 返回结果
	 */
	public String excuteDelete(Object object) throws RemoteException,Exception;

	/**
	 * 执行同步方法
	 * 
	 * @param siteId
	 *            要同步的网元id
	 * @throws RemoteException
	 */
	public String synchro(int siteId) throws RemoteException,Exception;
	
	/**
	 * 一致性检测
	 * @param siteId
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public Object consistence(int siteId)throws RemoteException,Exception;
	
	
}
