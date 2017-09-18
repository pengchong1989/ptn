package com.nms.ui;

import java.util.Map;

import com.nms.rmi.ui.ServiceMainFrame;
import com.nms.ui.manager.ExceptionManage;

/**
 * 长期性能任务线程工厂
 * @author Administrator
 */
public class PerforTaskThreadFactory {
	
	private static PerforTaskThreadFactory factory = null;
	private PerforTaskThreadFactory() {
	}

	//懒汉式的单例模式
	public static synchronized PerforTaskThreadFactory getInstance() {
		if (factory == null) {
				factory = new PerforTaskThreadFactory();
		}
		return factory;
	}
	
  /**
   * 重新修改性能任务的查询规则
   */
	public void createPerforTaskThread() {
		String threadName = "performanceTaskThreadName";
		Map<String,Thread> threadMap = ServiceMainFrame.getServiceMainFrame().getThreadMap();
	      try {
//	    	  if(!threadMap.containsKey(threadName)){
	    	      PerformanceThread taskThread = new PerformanceThread();
	    	      Thread thread = new Thread(taskThread,threadName);
	    		  thread.start();
	    		  threadMap.put(threadName, thread);
//	    	  }
		 } catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/*
	 * 取消定时任务
	 * 适用于长期性能任务
	 */
	public void stopPerformanceThread(String threadName) {
		try {
			Thread taskThread = (Thread)ServiceMainFrame.getServiceMainFrame().getThreadMap().get(threadName);
			
			if(!taskThread.isInterrupted()){
				try {
					taskThread.interrupt();
				} catch (Exception e) {
				}
			}
			ServiceMainFrame.getServiceMainFrame().getThreadMap().remove(threadName);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/*
	 * 返回定时任务
	 */
	public Thread getPerformanceTaskThread(String threadName) {
		return (Thread)ServiceMainFrame.getServiceMainFrame().getThreadMap().get(threadName);
	}

}
