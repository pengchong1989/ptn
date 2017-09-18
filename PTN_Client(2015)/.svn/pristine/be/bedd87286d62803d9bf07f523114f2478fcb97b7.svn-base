package com.nms.ui.manager.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import com.nms.db.bean.system.UnLoading;
import com.nms.model.alarm.HisAlarmService_MB;
import com.nms.model.perform.HisPerformanceService_Mb;
import com.nms.model.system.TranferService_Mb;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.ptn.systemManage.bean.TranferInfo;

/**
 * 
 * @author zk
 *
 * fucntion: 自动转储历史性能或历史告警
 */
public class AutoDumpHisPerAndAlarm {
	
	/**
	 * @param label 1，转储历史告警； 2:转储历史性能；
	 *function:自动的转储历史性能或历史告警
	 */
   public void autoDump(int label,int autoDumpCount){
	 
	TranferService_Mb tranferService = null;
	UnLoading unload = new UnLoading();
	List<TranferInfo> autoDumpHisData = null; 
	HisPerformanceService_Mb hisPerformanceService = null;
	HisAlarmService_MB hisAlarmService = null;
	List<Integer> ids = null;
	
	try {
		hisAlarmService = (HisAlarmService_MB) ConstantUtil.serviceFactory.newService_MB(Services.HisAlarm);
		hisPerformanceService = (HisPerformanceService_Mb) ConstantUtil.serviceFactory.newService_MB(Services.HisPerformance);
		tranferService = (TranferService_Mb)ConstantUtil.serviceFactory.newService_MB(Services.TRANFERSERVICE);
		// 1:表示告警；2 表示性能
		String fileName = "data";
        File dataFile = new File(fileName);
        if(!dataFile.exists()){
        	dataFile.mkdir();
        }
		unload.setUnloadType(label);
		unload.setFileWay(fileName);
		autoDumpHisData = tranferService.getDataStr(unload,autoDumpCount);
		
		if(autoDumpHisData != null && autoDumpHisData.size()>0){
			ids = new ArrayList<Integer>();	
			if(label ==1){
				ids = unloadHistoryAlarm(autoDumpHisData,unload);
				hisAlarmService.delete(ids);
			}else{
				ids = unloadHisPerformance(autoDumpHisData,unload);
				hisPerformanceService.delete(ids);
			}
		}
	 } catch (Exception e) {
		ExceptionManage.dispose(e,this.getClass());
	 }finally{
		UiUtil.closeService_MB(hisAlarmService);
		UiUtil.closeService_MB(tranferService);
		UiUtil.closeService_MB(hisPerformanceService);
	}
}

 /**
	 *历史性能 导出
	 * @param hisList
	 * @param unload
	 * @return
	 */
	private  List<Integer> unloadHisPerformance(List<TranferInfo> tranferInfoList,UnLoading unload){
		FileWriter fileWriter=null;
		BufferedWriter bufferedWriter=null;	
		String fileName=null;
		List<Integer> idList=null;
		//  转储  生成的 文件 名
		 try {
			 idList=new ArrayList<Integer>();
			 fileName=unload.getFileWay()+File.separator+"PERFORMANCE"+".sql";
			 File file = new File(fileName);
			 if(!file.exists()){
				 file.createNewFile();
			 }
			 fileWriter = new FileWriter(file.getAbsolutePath(), true);
			 bufferedWriter = new BufferedWriter(fileWriter);
			 for(TranferInfo tranferInfo:tranferInfoList){				 			 
				bufferedWriter.write(tranferInfo.getSql());		
				idList.add(tranferInfo.getId());
				}
		  } catch (Exception e) {
			 ExceptionManage.dispose(e,this.getClass());
		  }finally{
			 // 关闭流		
			 if (null != bufferedWriter) {
				 try {
					 bufferedWriter.close();
				 } catch (Exception e) {
					 ExceptionManage.dispose(e,this.getClass());
				 } finally {
					 bufferedWriter = null;
				}
			 }	
			 fileName=null;
			 fileWriter = null;	
		 }
		 return idList;
	}
	
	/**
	 * 导出历史告警
	 * @param unload
	 */
	public List<Integer> unloadHistoryAlarm(List<TranferInfo> tranferInfoList,UnLoading unload){
		FileWriter fileWriter=null;
		BufferedWriter bufferedWriter=null;		
		String fileName=null;
		List<Integer> idList=null;
		 try {
			 idList=new ArrayList<Integer>();
			 fileName=unload.getFileWay()+File.separator+"ALARM"+".sql";
			 fileWriter = new FileWriter(fileName, true);
			 bufferedWriter=new BufferedWriter(fileWriter);
			 for(TranferInfo tranferInfo:tranferInfoList){
				 bufferedWriter.write(tranferInfo.getSql());
				 idList.add(tranferInfo.getId());
			 }
		 } catch (Exception e) {
			 ExceptionManage.dispose(e,this.getClass());
		 }finally{
			 // 关闭流		
			 if (null != bufferedWriter) {
				 try {
					 bufferedWriter.close();
				 } catch (Exception e) {
					 ExceptionManage.dispose(e,this.getClass());
				 } finally {
					 bufferedWriter = null;
				}
			 }	
			 fileName=null;
			 fileWriter = null;	
		 }
		 return idList;
	}
}
