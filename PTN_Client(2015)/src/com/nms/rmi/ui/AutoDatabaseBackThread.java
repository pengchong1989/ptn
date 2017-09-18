package com.nms.rmi.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.nms.db.bean.system.UnLoading;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.ptn.systemManage.AutoDumpHisPerAndAlarm;

public class AutoDatabaseBackThread implements Runnable{
 private long cycleTime;
 private long startTime;
 private int label;//标签用来确定是自动转储的 1:告警 2:性能 3:操作日志 4:登录日志
 private int count;//转储的数目
 private String fileAddress ;//文件目录地址
 private boolean flag = true;
 
 public AutoDatabaseBackThread(long startTime,UnLoading unload){
	 
	 this.cycleTime = unload.getTimeInterval()*60*60*1000;
	 this.startTime = startTime;
	 this.label = unload.getUnloadType();
	 this.count = unload.getSpillEntry();
	 this.fileAddress = unload.getFileWay();
	 
 }
	@Override
	public void run() {
      AutoDumpHisPerAndAlarm autoDumpHisPerAndAlarm = new AutoDumpHisPerAndAlarm();
      int countCurrent = 0;
		try {
			while(flag){
				//1:按指定的时间来导出
				 if(isCycleTime()){
					 autoDumpHisPerAndAlarm.autoDump(label, count,fileAddress);
				 }
				 //2.按条目数来导出
				countCurrent = autoDumpHisPerAndAlarm.countAlarmOrPerformance(label);
				if(countCurrent > count){
					autoDumpHisPerAndAlarm.autoDump(label, count,fileAddress);
				}
				Thread.sleep(10*1000);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally
		{
			autoDumpHisPerAndAlarm = null;
		}
	}
	
	private boolean isCycleTime(){
		try {
			long multipleValue = (System.currentTimeMillis() - startTime)%cycleTime;
			if(multipleValue < 6*1000 && ((System.currentTimeMillis() - startTime)/cycleTime) >0){
				return true;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			long s = format.parse("2015-1-24 11:39:15").getTime();
			long s1 = format.parse("2015-1-24 11:54:17").getTime();
			long s2 = 1000*60*15;
			System.out.println(s);
			System.out.println(s1);
			System.out.println(s2);
			System.out.println((s1 - s));
			System.out.println((s1 - s)/s2);
			System.out.println((s1 - s)%s2 < 5*1000);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	
	public void stop(){
		flag = false;
//		timer.cancel();
//		timer = null;
	}

}
