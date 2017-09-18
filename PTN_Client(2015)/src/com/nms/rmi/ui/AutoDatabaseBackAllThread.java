package com.nms.rmi.ui;
import java.util.Timer;
import java.util.TimerTask;

public class AutoDatabaseBackAllThread implements Runnable{
	
 private TimerTask task;
 private Timer timer;
 private long cycleTime;
 private long startTime;
 
 public AutoDatabaseBackAllThread(TimerTask task,long startTime,long cycleTime){
	 
	 this.cycleTime = cycleTime;
	 this.startTime = startTime;
	 this.task = task;
	 timer = new Timer();
	 
 }
	@Override
	public void run() {
		
		if(startTime < System.currentTimeMillis()){
			timer.schedule(task,5,cycleTime);// 安排指定的任务从指定的延迟后开始进行重复的固定延迟执行。
		}else{
			timer.schedule(task,startTime-System.currentTimeMillis(),cycleTime);// 安排指定的任务从指定的延迟后开始进行重复的固定延迟执行。
		}
	}
	
	public void stop(){
		timer.cancel();
		timer = null;
	}

}