package com.nms.ui.ptn.performance.controller;

import java.util.Timer;
import java.util.TimerTask;

public class PerformanceRAMThread implements Runnable{

	 private TimerTask task;
	 private Timer timer;
	 private long cycleTime = 0;
	 
	 public PerformanceRAMThread(TimerTask task,long cycleTime){
		 this.cycleTime = cycleTime;
		 this.task = task;
		 timer = new Timer();
	 }
		@Override
		public void run() {
			timer.schedule(task,1000,cycleTime);// 安排指定的任务从指定的延迟后开始进行重复的固定延迟执行。
		}
		
		public void stop(){
			timer.cancel();
			timer = null;
		}

}
