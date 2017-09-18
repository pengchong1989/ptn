package com.nms.ui.lockscreen.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

public class HandLockThreadFactory {
	private  Map<String, HandLockThrad> threadMap = null;
	private static HandLockThreadFactory factory;
	private String threadName;
	private HandLockThreadFactory() {
		threadMap = new HashMap<String, HandLockThrad>();
	}

	public static HandLockThreadFactory getInstance() {
		synchronized (HandLockThreadFactory.class) {
			if (factory == null) {
				synchronized (HandLockThreadFactory.class) {
					factory = new HandLockThreadFactory();
				}
			}
		}
		return factory;
	}
	
	public void createLockThread(TimerTask task, long interval) {
		HandLockThrad taskThread = new HandLockThrad(task, interval);
		Thread thread = new Thread(taskThread);
		thread.start();
		threadName=thread.getName();
		threadMap.put(threadName, taskThread);
	}
	
	public void canelLockThread() {
		HandLockThrad thread =threadMap.get(threadName);
		thread.stop();
	}

	
}
