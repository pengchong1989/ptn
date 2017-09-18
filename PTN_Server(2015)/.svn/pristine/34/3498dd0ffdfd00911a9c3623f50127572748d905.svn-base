package com.nms.snmp.ninteface.impl.performance;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.nms.db.enums.EMonitorCycle;
import com.nms.ui.manager.ExceptionManage;
/**
 * @author guoqc
 * 性能数据上报功能
 * 此线程是根据时间粒度去查询历史性能,生成性能文件,便于上层网管进行采集
 */
public class PerformanceTrapThread implements Runnable {
	private boolean suspendFlag = false;// 控制线程的执行
	//根据长期性能任务id去采集
	private List<Integer> taskIdList = new ArrayList<Integer>();
	//长期性能任务创建时间
	public PerformanceTrapThread(List<Integer> taskIdList) {
		try {
			this.taskIdList = taskIdList;
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	@Override
	public void run() {
		try {
			//定时采集历史性能并生成文件
			TimerTask task_15min = new PerformanceTrapTimerTask(this.taskIdList, 1);
//			TimerTask task_24hour = new PerformanceTrapTimerTask(this.taskIdList, 2);
			Timer timer_15min = new Timer();
			long interval_15min = EMonitorCycle.MIN15.getInterval();
			timer_15min.schedule(task_15min, 5*60*1000, interval_15min);//安排指定的任务从指定的延迟后开始进行重复的固定延迟执行
//			Timer timer_24hour = new Timer();
//			long interval_24hour = EMonitorCycle.HOUR24.getInterval();
//			timer_24hour.schedule(task_24hour, 60*60*1000, interval_24hour);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * 线程暂停
	 */
	public void setSuspendFlag() {
		this.suspendFlag = true;
	}

	/**
	 * 唤醒线程
	 */
	public synchronized void setResume() {
		this.suspendFlag = false;
		notify();
	}
	
	/**
	 * 返回线程状态
	 * @return
	 */
	public boolean getSuspendFlag(){
		return this.suspendFlag;
	}
}
