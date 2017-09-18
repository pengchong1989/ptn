package com.nms.rmi.ui;

import java.util.Map;

import com.nms.ui.Ptnf;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.ptn.performance.controller.PerformanceRAMThread;
import com.nms.ui.ptn.performance.controller.PerformanceRamTimerTask;

/**
 * 
 * @author zk
 *
 */
public class AutoDatabaseBackThradUtil {
	/**
	 * 创建性能内存的线程
	 * @param tableNames
	 * @param filePath
	 * @param startTime
	 * @param cycleTime
	 */
	public void createThread(long cycleTime){
		
		Map<String,Runnable> threadMap = Ptnf.getPtnf().getThreadMap();
		PerformanceRAMThread performanceRAMThread ;//线程
		PerformanceRamTimerTask task ; //定时任务
		try {
			//通过一个线程来启用一个定时器来固定时间来执行任务
			task = new PerformanceRamTimerTask();
			performanceRAMThread = new PerformanceRAMThread(task,cycleTime);
			String threadName = "task_performanceRam_4";
			Thread thread = new Thread(performanceRAMThread,threadName);
			
			if(threadMap != null && threadMap.size() >0){
			   if(threadMap.get(thread.getName()) != null){
			   ((PerformanceRAMThread)threadMap.get(thread.getName())).stop();
		    	 threadMap.remove(thread.getName());
			   }
			 } 
			thread.start();
			threadMap.put(threadName, performanceRAMThread);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}
	
}
