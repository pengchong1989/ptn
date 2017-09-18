package com.nms.ui.ptn.systemManage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.nms.db.bean.system.UnLoading;
import com.nms.model.alarm.HisAlarmService_MB;
import com.nms.model.perform.HisPerformanceService_Mb;
import com.nms.model.system.OperationLogService_MB;
import com.nms.model.system.TranferService_Mb;
import com.nms.model.system.loginlog.LoginLogServiece_Mb;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DateUtil;
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
	 * @param label 1，转储历史告警； 2:转储历史性能；3:转储操作日志；4:转储登录日志
	 *function:自动的转储历史性能或历史告警
	 */
   public void autoDump(int label,int autoDumpCount,String fileAddress){
	 
	TranferService_Mb tranferService = null;
	UnLoading unload = new UnLoading();
	List<TranferInfo> autoDumpHisData = null; 
	HisPerformanceService_Mb hisPerformanceService = null;
	HisAlarmService_MB hisAlarmService = null;
	List<Integer> ids = null;
	OperationLogService_MB operationLogService = null;
	LoginLogServiece_Mb loginlogServiece = null;
	try {
		hisAlarmService = (HisAlarmService_MB) ConstantUtil.serviceFactory.newService_MB(Services.HisAlarm);
		hisPerformanceService = (HisPerformanceService_Mb) ConstantUtil.serviceFactory.newService_MB(Services.HisPerformance);
		tranferService = (TranferService_Mb)ConstantUtil.serviceFactory.newService_MB(Services.TRANFERSERVICE);
		operationLogService = (OperationLogService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OPERATIONLOGSERVIECE);
		loginlogServiece = (LoginLogServiece_Mb) ConstantUtil.serviceFactory.newService_MB(Services.LOGINLOGSERVIECE);
		// 1:告警 2:性能 3:操作日志 4:登录日志
		unload.setUnloadType(label);
		unload.setFileWay(fileAddress);
		autoDumpHisData = tranferService.getDataStr(unload,autoDumpCount);
		
		if(autoDumpHisData != null && autoDumpHisData.size()>0){
			ids = new ArrayList<Integer>();	
			if(label == 1){
				ids = unloadHistoryAlarm(autoDumpHisData,unload);
				hisAlarmService.delete(ids);
			}else if(label == 2){
				ids = unloadHisPerformance(autoDumpHisData,unload);
				hisPerformanceService.delete(ids);
			}else if(label == 3){
				ids = unloadOperationLog(autoDumpHisData,unload);
				operationLogService.delete(ids);
			}else{
				ids = unloadLoginLog(autoDumpHisData,unload);
				loginlogServiece.delete(ids);
			}
		}
	 } catch (Exception e) {
		ExceptionManage.dispose(e,this.getClass());
	 }finally{
		UiUtil.closeService_MB(hisAlarmService);
		UiUtil.closeService_MB(tranferService);
		UiUtil.closeService_MB(hisPerformanceService);
		UiUtil.closeService_MB(operationLogService);
		UiUtil.closeService_MB(loginlogServiece);
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
			 fileName=unload.getFileWay()+File.separator+"PERFORMANCE_"+DateUtil.getDate(DateUtil.FULLTIMESDF)+".sql";
			 File file = new File(fileName);
			 File fileMider = new File(unload.getFileWay());
			 if(!fileMider.exists()){
				 fileMider.mkdirs();
			 }
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
		DateFormat dateFile = new SimpleDateFormat("yyyyMMddHHmmss");
		 try {
			 idList=new ArrayList<Integer>();
			 fileName=unload.getFileWay()+File.separator+"ALARM"+dateFile.format(new Date())+".sql";
			 
			 File fileMider = new File(unload.getFileWay());
			 if(!fileMider.exists()){
				 fileMider.mkdirs();
			 }
			 
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
			 dateFile = null;
		 }
		 return idList;
	}
	
	/**
	 * 导出操作日志
	 * @param operationLogList
	 * @param unload
	 * @return
	 */
	private List<Integer> unloadOperationLog(List<TranferInfo> operationLogList, UnLoading unload) {
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;		
		String fileName = null;
		List<Integer> idList = null;
		DateFormat dateFile = new SimpleDateFormat("yyyyMMddHHmmss");
		 try {
			 idList = new ArrayList<Integer>();
			 fileName = unload.getFileWay()+File.separator+"OPERATIONLOG"+dateFile.format(new Date())+".sql";
			 
			 File fileMider = new File(unload.getFileWay());
			 if(!fileMider.exists()){
				 fileMider.mkdirs();
			 }
			 
			 fileWriter = new FileWriter(fileName, true);
			 bufferedWriter=new BufferedWriter(fileWriter);
			 for(TranferInfo tranferInfo : operationLogList){
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
			 fileName = null;
			 fileWriter = null;	
			 dateFile = null;
		 }
		 return idList;
   }

   /**
    * 导出登录日志
    * @param loginLogList
    * @param unload
    * @return
    */
   private List<Integer> unloadLoginLog(List<TranferInfo> loginLogList, UnLoading unload) {
	   FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;		
		String fileName = null;
		List<Integer> idList = null;
		DateFormat dateFile = new SimpleDateFormat("yyyyMMddHHmmss");
		 try {
			 idList = new ArrayList<Integer>();
			 fileName = unload.getFileWay()+File.separator+"LOGINLOG"+dateFile.format(new Date())+".sql";
			 
			 File fileMider = new File(unload.getFileWay());
			 if(!fileMider.exists()){
				 fileMider.mkdirs();
			 }
			 
			 fileWriter = new FileWriter(fileName, true);
			 bufferedWriter=new BufferedWriter(fileWriter);
			 for(TranferInfo tranferInfo : loginLogList){
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
			 fileName = null;
			 fileWriter = null;	
			 dateFile = null;
		 }
		 return idList;
   }
	
	/**
	 * 用于来读取性能或告警或操作日志或登录日志的总数目
	 * @param label 1:历史告警 2:历史性能  3:操作日志 4:登录日志
	 * @return
	 */
	public int countAlarmOrPerformance(int label){
		int count = 0;
		HisPerformanceService_Mb hisPerformanceService = null;
		HisAlarmService_MB hisAlarmService = null;
		OperationLogService_MB operationLogService = null;
		LoginLogServiece_Mb loginLogService = null;
		try {
			if(label == 1){
				hisAlarmService = (HisAlarmService_MB) ConstantUtil.serviceFactory.newService_MB(Services.HisAlarm);
				count = hisAlarmService.selectCount(null, null);
			}else if(label == 2){
				hisPerformanceService = (HisPerformanceService_Mb) ConstantUtil.serviceFactory.newService_MB(Services.HisPerformance);
				count = hisPerformanceService.selectCount();
			}else if(label == 3){
				operationLogService = (OperationLogService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OPERATIONLOGSERVIECE);
				count = operationLogService.selectCount();
			}else{
				loginLogService = (LoginLogServiece_Mb) ConstantUtil.serviceFactory.newService_MB(Services.LOGINLOGSERVIECE);
				count = loginLogService.selectLogCount();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally{
			UiUtil.closeService_MB(hisAlarmService);
			UiUtil.closeService_MB(hisPerformanceService);
			UiUtil.closeService_MB(operationLogService);
			UiUtil.closeService_MB(loginLogService);
		}
		return count;
	}
	

	
}
