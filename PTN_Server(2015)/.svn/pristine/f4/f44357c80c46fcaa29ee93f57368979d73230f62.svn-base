package com.nms.corba.test.sy_test_wh;

import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import org.omg.CORBA.IntHolder;

import performance.PMCollectionTaskList_THolder;
import performance.PMCollectionTask_T;
import performance.PMCollectionTask_THolder;
import performance.PMDataIterator_IHolder;
import performance.PMDataList_THolder;
import performance.PMTPSelect_T;
import performance.PMThresholdType_T;
import performance.PMThresholdValue_T;
import performance.PerformanceManagementMgr_I;
import performance.PerformanceManagementMgr_IHelper;
import performance.TCAParameters_T;
import performance.TCAParameters_THolder;

import com.nms.ui.manager.ExceptionManage;
import common.Common_IHolder;

/**
 * 测试 （武汉写的）性能模块
 * @author sy
 *
 */
public class Test_Performance  extends sy_testBase{
	
	private PerformanceManagementMgr_I performanceMgr_I=null;
	public Test_Performance(){
		super();
	}
	
	public static void main(String arga[]){
		Test_Performance test=new Test_Performance();
		test.createPMCollectionTask();
		System.out.println("测试完成！！");
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
//		try {
//			
//			System.out.println(sdf.parse("1989-11-11 10-10-10"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			ExceptionManage.dispose(e,this.getClass());
//		}
	
	}
	/**
	 * 设置性能门限值
	 */
	private void setTCATPParameter(){
		this.getManager();
		try {
			TCAParameters_THolder tcap=new TCAParameters_THolder();
			tcap.value=new TCAParameters_T();
			tcap.value.granularity="";
			tcap.value.tcaTypeValues=new PMThresholdValue_T[5];
			tcap.value.tcaTypeValues[0]=setTCATPPa("highTemp",340);
			tcap.value.tcaTypeValues[1]=setTCATPPa("crcError",340);
			tcap.value.tcaTypeValues[2]=setTCATPPa("lossNum",340);
			tcap.value.tcaTypeValues[3]=setTCATPPa("receiveBadWrap",340);
			tcap.value.tcaTypeValues[4]=setTCATPPa("tmsWorsen",340);
//			tcap.value.tcaTypeValues[5]=setTCATPPa("tmsLose",340);
//			tcap.value.tcaTypeValues[6]=setTCATPPa("align",340);
//			tcap.value.tcaTypeValues[7]=setTCATPPa("lowTemp",340);
//			tcap.value.tcaTypeValues[8]=setTCATPPa("tmpWorsen",340);
//			tcap.value.tcaTypeValues[9]=setTCATPPa("tmpLose",340);
//			tcap.value.tcaTypeValues[10]=setTCATPPa("tmcWorsen",340);
//			tcap.value.tcaTypeValues[11]=setTCATPPa("tmcLose",340);
			
			performanceMgr_I.setTCATPParameter(this.getName(),tcap );
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * 查询性能门限
	 */
	private void getTCATPParameter(){
		this.getManager();
		try {
			TCAParameters_THolder tcap=new TCAParameters_THolder();
			
			performanceMgr_I.getTCATPParameter(this.getName(), (short)1, "", tcap);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 测试： 恢复到指定采集任务
	 */
	private void resumePMCollectionTask(){
		this.getManager();
		try {
			PMCollectionTaskList_THolder pm=new PMCollectionTaskList_THolder();
			
			performanceMgr_I.resumePMCollectionTask(2);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 测试： 暂停采集任务
	 */
	private void suspendPMCollectionTask(){
		this.getManager();
		try {
			PMCollectionTaskList_THolder pm=new PMCollectionTaskList_THolder();
			
			performanceMgr_I.suspendPMCollectionTask(2);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 获取所有的当前性能值
	 * 测试完成
	 */
	private void getAllCurrentPMData(){
		this.getManager();
		try {
			PMTPSelect_T[] pm=new PMTPSelect_T[1];
			pm[0]=new PMTPSelect_T();
			pm[0].name=super.getName();
			pm[0].pMLocationList=new String[2];
			pm[0].pMLocationList[0]="15min";
			pm[0].pMLocationList[1]="0";
			
			pm[0].layerRateList=new short[0];
			pm[0].granularityList=new String[0];
			String[] str=new String[0];
			PMDataList_THolder pmData=new PMDataList_THolder();
			PMDataIterator_IHolder pmIt=new PMDataIterator_IHolder();
			performanceMgr_I.getAllCurrentPMData(pm, str, 10, pmData, pmIt);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 测试完成
	 * 查询历史性能
	 */
	private void getHistoryPMData(){
		this.getManager();
		try {
			PMTPSelect_T[] pm=new PMTPSelect_T[1];
			pm[0]=new PMTPSelect_T();
			pm[0].name=super.getName();
			pm[0].pMLocationList=new String[2];
			pm[0].pMLocationList[0]="15min";
			pm[0].pMLocationList[1]="0";
			
			pm[0].layerRateList=new short[0];
			pm[0].granularityList=new String[0];
			String[] str=new String[0];
			PMDataList_THolder pmData=new PMDataList_THolder();
			PMDataIterator_IHolder pmIt=new PMDataIterator_IHolder();
			
			String startTime="2012-6-4 10-5-5";
			String endTime="2015-8-4 10-5-5";
			String destination="127.0.0.1|/hisperf.txt";
			performanceMgr_I.getHistoryPMData(destination, "admin", "admin", pm, new String[0], startTime, endTime, true);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 查询所有长期性能任务
	 * 测试完成
	 * 文档没处理
	 */
	private void getAllPMCollectionTasks(){
		this.getManager();
		try {
			PMCollectionTaskList_THolder pm=new PMCollectionTaskList_THolder();
			
			performanceMgr_I.getAllPMCollectionTasks(pm);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 查询指定采集任务
	 */
	private void getPMCollectionTask(){
		this.getManager();
		try {
			PMCollectionTask_THolder pm=new PMCollectionTask_THolder();
			
			performanceMgr_I.getPMCollectionTask(2, pm);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 测试 ： 修改长期性能任务
	 */
	private void modifyPMCollectionTask(){
		this.getManager();
		try {
			int id=25;//要修改的采集任务的主键ID
			performanceMgr_I.modifyPMCollectionTask(id, getPMCollectionTask_T());
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 测试： 删除长期性能任务
	 * 完成
	 */
	private void deletePMCollectionTask(){
		this.getManager();
		try {
			int id=1;
			performanceMgr_I.deletePMCollectionTask(id);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 创建长期性能任务
	 * 	
	 */
	private void createPMCollectionTask(){
		this.getManager();
		try {			
			IntHolder intHolder=new IntHolder();
			performanceMgr_I.createPMCollectionTask(getPMCollectionTask_T(), intHolder);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 获取  创建。修改 长期性能任务对象
	 * @return
	 */
	private PMCollectionTask_T getPMCollectionTask_T(){
		PMCollectionTask_T pm=new PMCollectionTask_T();
		pm.taskName=new NameAndStringValue_T[1];
		pm.additionalInfo=new NameAndStringValue_T[0];
		pm.taskName[0]=new NameAndStringValue_T("","sy_23423");
		pm.beginTime="20140609112030";
		pm.endTime="20140630115300";
		pm.reportInterval="900000";
		pm.pMParameterList=new String[0];
		pm.name=getPMTPSelect_T();
		return pm;
	}
	/*
	 * 获取 查询 性能对象信息
	 */
	private PMTPSelect_T getPMTPSelect_T(){
		PMTPSelect_T pm=new PMTPSelect_T();
	
		pm.name=super.getName();
		pm.pMLocationList=new String[2];
		pm.pMLocationList[0]="15min";
		pm.pMLocationList[1]="0";
		pm.layerRateList=new short[0];
		pm.granularityList=new String[1];
		pm.granularityList[0]="900000";
		return pm;
	}
	/**
	 * 设置性能门限值
	 * @param name
	 * @param value
	 * @return
	 */
	private PMThresholdValue_T setTCATPPa(String name,float value){
		
		PMThresholdValue_T t=new PMThresholdValue_T();
		t.pmParameterName=name;
		t.value=value;
		t.pmLocation="";
		t.triggerFlag=true;
		t.thresholdType=PMThresholdType_T.TWM_HIGH;
		return t;
	}
	/**
	 * 获取管理者信息
	 */
	public void getManager(){
		Common_IHolder common_IHolder=new Common_IHolder();
		try {
			super.connnect.emsSession.getManager("PerformanceManagement", common_IHolder);
			performanceMgr_I=PerformanceManagementMgr_IHelper.narrow(common_IHolder.value);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
}
