package com.nms.ui.ptn.alarm.controller;

import com.nms.service.impl.dispatch.AlarmDispatch;
import com.nms.ui.manager.ExceptionManage;

/**
 * 当前告警定时任务
 * 
 * @author 定时查询所有网元告警信息，并在拓扑图中显示
 * 
 */
public class CurAlarmTimerTask extends Thread {
	private AlarmDispatch alarmDispatch = null;
	
	private boolean isRun = false;

	public CurAlarmTimerTask() {
		this.setName("CurAlarmTimerTask");
		alarmDispatch = new AlarmDispatch();
		this.start();
	}

	@Override
	public void run() {
		try {

			while (isRun) {
				alarmDispatch.convertAlarm();
				Thread.sleep(2000);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {

		}
	}

	public AlarmDispatch getAlarmDispatch() {
		return alarmDispatch;
	}

	public void setAlarmDispatch(AlarmDispatch alarmDispatch) {
		this.alarmDispatch = alarmDispatch;
	}

	/**
	 * 外部停止线程
	 */
	public void stopThread() {
		this.isRun = false;
	}

	/**
	 * 外部开启线程
	 */
	public void startThread() {
		this.isRun = true;
	}
}
