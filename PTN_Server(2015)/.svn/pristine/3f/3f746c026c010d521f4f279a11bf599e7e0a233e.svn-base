package com.nms.corba.test;

import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import org.omg.CORBA.IntHolder;

import performance.PMCollectionTaskList_THolder;
import performance.PMCollectionTask_T;
import performance.PMCollectionTask_THolder;
import performance.PMTPSelect_T;
import performance.PerformanceManagementMgr_I;
import performance.PerformanceManagementMgr_IHelper;

import com.nms.corba.test.common.CorbaConnect;
import com.nms.ui.manager.ExceptionManage;
import common.Common_IHolder;

public class PerformanceTaskTest {

	private PerformanceManagementMgr_I perMgr ;
	
	public PerformanceTaskTest(){
		try {
			//获取管理者
			Common_IHolder mgrHolder = new Common_IHolder();
			CorbaConnect.emsSession.getManager("PerformanceManagement", mgrHolder);
			perMgr = PerformanceManagementMgr_IHelper.narrow(mgrHolder.value);
			
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	
	
	/**
	 *  function:创建性能任务
	 */
	public void createPerformanceTask(){
		
		try {
			
			PMCollectionTask_T task = new PMCollectionTask_T();
			task.taskName = new NameAndStringValue_T[1];
			//任务名称
			task.taskName[0]  = new NameAndStringValue_T("task","performance_Task_1");
			//开始时间
			task.beginTime = "2014-04-29 09:14:14";
			//结束时间
			task.endTime = "2014-04-30 14:14:14";
			//性能周期 15分钟
			task.reportInterval = "900000";
			//性能参数
			String[] perType = new String[]{"PORT", "ETH"};
			task.pMParameterList = perType;
			NameAndStringValue_T[] siteID = new NameAndStringValue_T[1];
			siteID[0] = new NameAndStringValue_T("siteID", "1");
//			task.name 
			PMTPSelect_T xx = new PMTPSelect_T();
			short[] la = new short[1];
			la[0]= 3;
			String[]  pm = {"2"};
			String[] granularityList ={"2"};
			xx.name = siteID;
			xx.layerRateList = la;
			xx.pMLocationList = pm;
			xx.granularityList = granularityList;
			task.name= xx;
			
			NameAndStringValue_T[] x = new NameAndStringValue_T[1];
			x[0] = new NameAndStringValue_T("11", "11");
			task.additionalInfo = x;
			//测试端口
			IntHolder intHolder = new IntHolder();
			perMgr.createPMCollectionTask(task, intHolder);
			if(null != intHolder){
				System.out.println(intHolder.value);
			}
			
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * function:查询"所有的性能任务"或者是 "指定的任务"
	 */
	public void selectPerformanceTask(){
		
		try {
			//测试接口
			PMCollectionTask_THolder paramTask = new PMCollectionTask_THolder();
			PMCollectionTaskList_THolder paramTaskList = new PMCollectionTaskList_THolder();
			//查询指定的任务
//			perMgr.getPMCollectionTask(1, paramTask);
//			System.out.println(paramTask.value.taskName[0].value+"::任务名称");
//			System.out.println(paramTask.value.beginTime+"::::开始时间");
//			System.out.println(paramTask.value.endTime+"::::结束时间");
			
		//	查询所有的任务
			perMgr.getAllPMCollectionTasks(paramTaskList);
			for (PMCollectionTask_T task : paramTaskList.value) {
				System.out.println(task.taskName[0].value+"::任务名称");
				System.out.println(task.beginTime+"::::开始时间");
				System.out.println(task.endTime+"::::结束时间");
			}
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	
	/**
	 * function:删除指定的任务
	 */
	public void deletePerformance(){
		try {
			perMgr.deletePMCollectionTask(6);
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * function:修改性能任务
	 * 
	 */
	public void modifyPerformanceTask(){
		
		try {
			PMCollectionTask_T task = new PMCollectionTask_T();
			task.taskName = new NameAndStringValue_T[1];
			//任务名称
			task.taskName[0]  = new NameAndStringValue_T("task","performance_Task_2");
			//开始时间
			task.beginTime = "2014-04-28 14:14:14";
			//结束时间
			task.endTime = "2014-04-29 14:14:14";
			//性能周期 15分钟
			task.reportInterval = "900000";
			//性能参数
			String[] perType = new String[]{"PORT", "ETH"};
			task.pMParameterList = perType;
			NameAndStringValue_T[] siteID = new NameAndStringValue_T[1];
			siteID[0] = new NameAndStringValue_T("siteID", "1");
			PMTPSelect_T xx = new PMTPSelect_T();
			short[] la = new short[1];
			la[0]= 3;
			String[]  pm = {"2"};
			String[] granularityList ={"2"};
			xx.name = siteID;
			xx.layerRateList = la;
			xx.pMLocationList = pm;
			xx.granularityList = granularityList;
			task.name= xx;
			
			NameAndStringValue_T[] x = new NameAndStringValue_T[1];
			x[0] = new NameAndStringValue_T("11", "11");
			task.additionalInfo = x;
			//测试端口
			perMgr.modifyPMCollectionTask(4, task);
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * function:恢复性能任务
	 */
	public void resumePerformanceType(){
		try {
			perMgr.resumePMCollectionTask(7);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * function:暂停性能任务
	 */
	public void stopPerformanceTask(){
		try {
			perMgr.suspendPMCollectionTask(7);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	public static void main(String[] args) {
		CorbaConnect corbaConnect = new CorbaConnect();
		corbaConnect.isConnect();
		PerformanceTaskTest per = new PerformanceTaskTest();
		//创建OK
		per.createPerformanceTask();
//		查询OK
//		per.selectPerformanceTask();
		//删除
//		per.deletePerformance();
		//修改性能任务
//		per.modifyPerformanceTask();
		//暂停性能任务
//		per.stopPerformanceTask();
//		per.resumePerformanceType();
		
	}
	
}
