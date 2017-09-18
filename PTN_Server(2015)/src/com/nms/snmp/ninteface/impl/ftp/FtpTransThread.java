package com.nms.snmp.ninteface.impl.ftp;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import com.nms.db.enums.EMonitorCycle;
import com.nms.snmp.ninteface.framework.SnmpConfig;
import com.nms.ui.manager.ExceptionManage;

/**
 * @author guoqc
 * ftp自动传输功能
 * 每隔24小时自动上传文件
 */
public class FtpTransThread implements Runnable {
//	private String collectTime = "";
	private boolean suspendFlag = false;// 控制线程的执行
	
	public static void main(String[] args) {
		SnmpConfig.getInstanse().init();
		FtpTransThread t = new FtpTransThread();
//		t.collectTime = "20140826";
		new Thread(t).start();
	}
	
	public FtpTransThread() {

	}

	@Override
	public void run() {
		try {
			//定时采集网管配置并生成文件
			long interValTime = this.getIntervalTime();
//			TimerTask task = new FtpTransTimerTask(this.collectTime);
			TimerTask task = new FtpTransTimerTask();
			Timer timer = new Timer();
			long interval_24hour = EMonitorCycle.HOUR24.getInterval();
			timer.schedule(task, interValTime, interval_24hour);//安排指定的任务开始进行重复的固定延迟执行
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	private long getIntervalTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			long startTimeL = System.currentTimeMillis();
			String startTimeS = format.format(startTimeL);
//			this.collectTime = format.format(startTimeL+EMonitorCycle.HOUR24.getInterval()).substring(0, 8);
			startTimeL = Integer.parseInt(startTimeS.substring(8, 10))*60*60*1000+
						 Integer.parseInt(startTimeS.substring(10, 12))*60*1000+
						 Integer.parseInt(startTimeS.substring(12, 14))*1000;
			return ((25*60*60*1000) - startTimeL);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return 0;
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
