package com.nms.ui.lockscreen.controller;

import java.util.Timer;
import java.util.TimerTask;

public class HandLockThrad implements Runnable{
	//间隔时间
	private long interval;
	private Timer timer;
	TimerTask task;
	public HandLockThrad(TimerTask task,long interval) {
		this.task = task;
		this.interval = interval;
		timer = new Timer();
	}
	
	@Override
	public void run() {
		timer.schedule(task,30*1000,interval);
	}
	
	//结束任务,释放资源
	public void stop(){
		timer.cancel();
		timer = null;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public TimerTask getTask() {
		return task;
	}

	public void setTask(TimerTask task) {
		this.task = task;
	}
}
