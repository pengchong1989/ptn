package com.nms.ui;

import java.util.List;

import com.nms.db.bean.perform.PerformanceTaskInfo;
import com.nms.service.impl.dispatch.PerformanceDispatch;
import com.nms.ui.manager.ExceptionManage;

/**
 * 
 * 这线程只负责处理符合条件的性能采集
 * @author Administrator
 *
 */
public class PerformanceHisPerThread implements Runnable{

	List<PerformanceTaskInfo> performanceTaskRunState = null;
	PerformanceDispatch dispatch = null;
	
	public PerformanceHisPerThread(List<PerformanceTaskInfo> performanceTaskRunState){
		
		dispatch = new PerformanceDispatch();
		this.performanceTaskRunState = performanceTaskRunState;
	}
	@Override
	public void run() {
		try {
			dispatch.executeQueryTaskCommand(performanceTaskRunState);
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
	}
}
