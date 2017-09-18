package com.nms.ui.ptn.systemManage;

import java.util.TimerTask;


public class AutoBackDataTask extends TimerTask{
	
   private int label;//标签用来确定是自动转储的 1:告警 2:性能
   private int count;//转储的数目
   private String fileAddress ;//文件目录地址
   private AutoDumpHisPerAndAlarm autoDumpHisPerAndAlarm;
   
   public AutoBackDataTask(int label,int count,String fileAddress){
	   this.label = label;
	   this.count = count;
	   this.fileAddress = fileAddress;
	   autoDumpHisPerAndAlarm = new AutoDumpHisPerAndAlarm();
   }
	@Override
	public void run() {
		autoDumpHisPerAndAlarm.autoDump(label, count,fileAddress);
	}

}
