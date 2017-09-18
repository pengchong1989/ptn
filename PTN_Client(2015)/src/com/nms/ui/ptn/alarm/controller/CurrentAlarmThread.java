package com.nms.ui.ptn.alarm.controller;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 当前告警线程，每3秒查询设备告警信息，并呈现在拓扑图上
 * @author lp
 *
 */
public class CurrentAlarmThread implements Runnable{
	private static long DELAY = 3*1000L;
	private Timer timer = null;
	private TimerTask task = null;
	public CurrentAlarmThread(TimerTask task) {
		timer = new Timer();
		this.task = task;
	}
	/*
	 * 取消定时任务
	 */
	public void cancel(){
		timer.cancel();
	}
	@Override
	public void run() {
		timer.schedule(task, new Date(), DELAY);
	}

}
