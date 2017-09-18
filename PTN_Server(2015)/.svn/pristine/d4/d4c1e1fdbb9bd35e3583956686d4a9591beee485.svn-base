package com.nms.ui.ptn.performance.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

import com.nms.db.bean.perform.PerformanceTaskInfo;
import com.nms.service.impl.dispatch.PerformanceDispatch;
import com.nms.ui.manager.ExceptionManage;

/**
 * 长期性能定时任务对象，主要完成查询设备性能值和保存数据库操作
 * @author lp
 *
 */
public class PerformanceTimerTask  extends TimerTask {
	
	PerformanceDispatch dispatch=null;
	PerformanceTaskInfo taskInfo = null;
	
	public PerformanceTimerTask(PerformanceTaskInfo taskInfo) {
		this.taskInfo = taskInfo;
		try {
			dispatch =new PerformanceDispatch();
		} catch (Exception e) {
			
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	@Override
	public void run() {
		SimpleDateFormat format = null;
		Date date = null;
		try {
			
			date = new Date();
			format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(null != taskInfo.getEndTime()&& !taskInfo.getEndTime().trim().equals("")){
				if(date.getTime()<format.parse(taskInfo.getEndTime().trim()).getTime()){
					//根据网元数据库id，创建长期性能任务
					dispatch.executeQueryHisPerforByTask(taskInfo);
				}else{
					// 关闭定时任务线程
//					if(PerforTaskThreadFactory.getInstance().getTaskThread(taskInfo.getThreadName())!=null){
//						PerforTaskThreadFactory.getInstance().stop(taskInfo.getThreadName());
//					}
				}
			}else{
				//根据网元数据库id，创建长期性能任务
				dispatch.executeQueryHisPerforByTask(taskInfo);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			format = null;
			date= null;
		}
		
	}

	public PerformanceTaskInfo getTaskInfo() {		
		return taskInfo;
	}

	public void setTaskInfo(PerformanceTaskInfo taskInfo) {
		this.taskInfo = taskInfo;
	}
	
}
