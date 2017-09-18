package com.nms.corba.test.resource;

import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;
import managedElementManager.ManagedElementMgr_I;
import managedElementManager.ManagedElementMgr_IHelper;
import terminationPoint.TerminationPointIterator_IHolder;
import terminationPoint.TerminationPointList_THolder;
import terminationPoint.TerminationPoint_THolder;

import com.nms.corba.test.snc.TestBase;
import com.nms.ui.manager.ExceptionManage;
import common.Common_IHolder;

/**
 * PTP测试类
 * 
 * @author kk
 * 
 */
public class ManagedElementMgrTest extends TestBase {

	public static void main(String[] args) {
		ManagedElementMgrTest managedElementMgrTest=new ManagedElementMgrTest();
//		managedElementMgrTest.getAllPTPsTest();
		managedElementMgrTest.getTPTest();
	}

	public ManagedElementMgrTest() {
		super();
	}

	/**
	 * 测试查询所有终端点
	 */
	public void getAllPTPsTest() {
		try {
			short[] tpLayerRateList=new short[]{};
			short[] connectionLayerRateList=new short[]{};
			TerminationPointList_THolder tpList=new TerminationPointList_THolder();
			TerminationPointIterator_IHolder tpIt=new TerminationPointIterator_IHolder();
			
			this.getMEInterface().getAllPTPs(this.getMEName(), tpLayerRateList, connectionLayerRateList, 100, tpList, tpIt);
			
			System.out.println(tpList.value.length);
		} catch (ProcessingFailureException e) {
			System.out.println(e.errorReason);
		}
	}

	/**
	 * 测试查询一个终端点
	 */
	public void getTPTest(){
		try {
			TerminationPoint_THolder terminationPoint_THolder=new TerminationPoint_THolder();
			this.getMEInterface().getTP(this.getTPName(), terminationPoint_THolder);
			
			System.out.println("查询得到的端口名为:"+terminationPoint_THolder.value.userLabel);
		} catch (ProcessingFailureException e) {
			System.out.println(e.errorReason);
		}
		
	}
	
	/**
	 * 获取ManagedElementMgr_I接口
	 * @return
	 */
	private ManagedElementMgr_I getMEInterface() {
		ManagedElementMgr_I managedElementMgr_I = null;
		try {
			Common_IHolder Common_IHolder = new Common_IHolder();
			super.getCorbaConnect().emsSession.getManager("ManagedElement", Common_IHolder);
			managedElementMgr_I = ManagedElementMgr_IHelper.narrow(Common_IHolder.value);
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return managedElementMgr_I;
	}
	
	private NameAndStringValue_T[] getMEName(){
		NameAndStringValue_T[] name=new NameAndStringValue_T[2];
		name[0]=new NameAndStringValue_T("EMS","DATAX/1");
		name[1]=new NameAndStringValue_T("ManagedElement","2");
		return name;
	}
	
	private NameAndStringValue_T[] getTPName(){
		NameAndStringValue_T[] name=new NameAndStringValue_T[3];
		name[0]=new NameAndStringValue_T("EMS","DATAX");
		name[1]=new NameAndStringValue_T("ManagedElement","1");
		name[2]=new NameAndStringValue_T("PTP","/rack=1/shelf=1/slot=1/port=fe.1.1");
		return name;
	}
}
