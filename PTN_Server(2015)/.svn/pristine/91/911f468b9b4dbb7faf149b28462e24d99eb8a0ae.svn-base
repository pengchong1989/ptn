package com.nms.ui.ptn.alarm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.nms.drive.analysis.AnalysisObjectService;
import com.nms.drive.service.bean.AlarmObject;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.ptn.alarm.AlarmTools;

/**
 * 处理主动轮询设备当前告警(暂时会开启2个线程处理)
 * @author pc
 *
 */
public class OperateCurrAlarmTask extends Thread{
	private boolean isRun = false;
	private AnalysisObjectService analysisObjectService;
	private List<AlarmObject> alarmObjectList;
	private AlarmTools alarmTools;
	public OperateCurrAlarmTask(){
		this.setName("OperateCurrAlarmTask");
		analysisObjectService = new AnalysisObjectService();
		alarmObjectList = new ArrayList<AlarmObject>();
		alarmTools=new AlarmTools();
		this.start();
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
	
	@Override
	public void run(){
		while(isRun){
			ConcurrentHashMap<String,byte[]> concurrentHashMap = ConstantUtil.alarmObjectService.getConcurrentHashMap();
			byte[] alarmData;
			try {
				if(concurrentHashMap.size()>100){
					ExceptionManage.infor("当前告警拥塞"+concurrentHashMap.size(), OperateCurrAlarmTask.class);
				}
				for(String str:concurrentHashMap.keySet()){
	        		alarmData = concurrentHashMap.get(str);
	        		concurrentHashMap.remove(str);
	        		AlarmObject alarmObject = analysisObjectService.AnalysisCommadsToAllReportAlarm(alarmData);
	        		alarmObjectList.clear();
	        		if(alarmObject.getNeAddress()>0){
	        			alarmObjectList.add(alarmObject);
		        		alarmTools.operateCurrentAlarm(alarmObjectList);
	        		}
				}
				Thread.sleep(500);
			} catch (InterruptedException e) {
				ExceptionManage.dispose(e,this.getClass());
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
		}
	}
}
