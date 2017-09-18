package com.nms.rmi.ui;

import java.util.List;
import java.util.Map;

import com.nms.ui.manager.ExceptionManage;

/**
 * 
 * @author zk
 *
 */
public class AutoDatabaseBackThradUtil {

	public void createThread(List<String> tableNames, String filePath, long startTime,long cycleTime){
		
		Map<String,Runnable> threadMap;
		AutoDatabaseBackAllThread autoDatabaseBackThread ;//线程
		AutoDatabaseBackTask task ; //定时任务
		try {
			//通过一个线程来启用一个定时器来固定时间来执行任务
			task = new AutoDatabaseBackTask(tableNames, filePath);
			autoDatabaseBackThread = new AutoDatabaseBackAllThread(task, startTime, cycleTime);
			String threadName = "task_autoData_4";
			Thread thread = new Thread(autoDatabaseBackThread,threadName);
			threadMap = ServiceMainFrame.getServiceMainFrame().getThreadRunableMap();
			if(threadMap != null && threadMap.size() >0){
			if(threadMap.get(thread.getName()) != null){
			 ((AutoDatabaseBackAllThread)threadMap.get(thread.getName())).stop();
		    	 threadMap.remove(thread.getName());
			 }
			 } 
			thread.start();
			threadMap.put(threadName, autoDatabaseBackThread);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}
}
