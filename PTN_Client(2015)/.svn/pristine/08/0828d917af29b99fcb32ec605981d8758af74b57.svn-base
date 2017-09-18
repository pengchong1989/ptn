package com.nms.service.impl.dispatch.rmi;

import java.rmi.RemoteException;
import java.util.List;

import com.nms.db.bean.perform.CurrentPerforInfo;
import com.nms.db.bean.perform.PerformanceTaskInfo;
import com.nms.ui.ptn.performance.model.CurrentPerformanceFilter;

/**
 * 性能rmi接口
 * 
 * @author kk
 * 
 */
public interface PerformanceDispatchI extends DispatchInterface{

	/**
	 * 查询当前性能
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	public List<CurrentPerforInfo> executeQueryCurrPerforByFilter(CurrentPerformanceFilter filter) throws RemoteException, Exception;
	
	/**
	 * 清除设备性能
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	public String executeCleanCurrPerforByFilter(CurrentPerformanceFilter filter) throws RemoteException, Exception;
	
	/**
	 * 查看长期性能
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	public String executeQueryHisPerforByTask(PerformanceTaskInfo taskInfo) throws RemoteException, Exception;
	
	/**
	 * 
	 * 根据过滤查询性能
	 * @param object
	 * @return
	 */
	public List<PerformanceTaskInfo> getAllPerformanceTask(Object object)throws RemoteException, Exception;
	
}
