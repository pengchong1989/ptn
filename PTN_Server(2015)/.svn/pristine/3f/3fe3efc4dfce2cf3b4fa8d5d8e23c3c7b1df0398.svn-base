package com.nms.corba.test;

import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;
import maintenanceOps.MaintenanceMgr_I;
import maintenanceOps.MaintenanceMgr_IHelper;
import maintenanceOps.OAMLinkLBResult_THolder;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.test.common.CorbaConnect;
import common.Common_IHolder;

/**
 * @author guoqc
 * 测试  启动LB功能/启动OAM性能测量功能/使能/禁止锁定功能（LCK）
 * 		/以太网OAM链路发现功能/以太网OAM链路环回/启动以太网业务OAM性能测量功能
 */
public class TestOamTest {
	private MaintenanceMgr_I oamMgrI;
	public TestOamTest() throws ProcessingFailureException{
		Common_IHolder common = new Common_IHolder();
		CorbaConnect.emsSession.getManager("Maintenance", common);
		oamMgrI = MaintenanceMgr_IHelper.narrow(common.value);
	}
	
	public static void main(String[] args) throws ProcessingFailureException {
		CorbaConnect c = new CorbaConnect();
		c.isConnect();
		TestOamTest oamTest = new TestOamTest();
		oamTest.setMEGLB();// 启动LB功能
//		oamTest.setMEGPerfCollection();//启动OAM性能测量功能
//		oamTest.setAdditionalInfoForLCK();//使能/禁止锁定功能（LCK）
//		oamTest.setOAMLinkDiscovery();//以太网OAM链路发现功能
//		oamTest.setOAMLinkLB();//以太网OAM链路环回
		oamTest.setAdditionalInfo();//启动以太网业务OAM性能测量功能
	}
	
	/**
	 * 启动LB功能
	 * @throws ProcessingFailureException 
	 */
	private void setMEGLB() throws ProcessingFailureException{
		NameAndStringValue_T[] megName = new NameAndStringValue_T[3];
		megName[0] = new NameAndStringValue_T("EMS", 
				(CorbaConfig.getInstanse().getCorbaEmsName()));//德信厂商
		megName[1] = new NameAndStringValue_T("ManagedElement", "6");//网元主键id
		megName[2] = new NameAndStringValue_T("MEG", "3/90");//主键id
		int enableLB = 1;
		NameAndStringValue_T[] additionalInfo = new NameAndStringValue_T[5];
		additionalInfo[0] = new NameAndStringValue_T("RingCycle", "1s");//环回周期 
		//离线TLV类型  伪随机码/全0  pseudoRandomCode/allZero
		additionalInfo[1] = new NameAndStringValue_T("OffLineTestTLV", "pseudoRandomCode");
		additionalInfo[2] = new NameAndStringValue_T("RingTestWay", "onLine");//环回方式  在线/离线 onLine/offLine
		additionalInfo[3] = new NameAndStringValue_T("RingTLVLength", "60");//环回TLV长度  
		additionalInfo[4] = new NameAndStringValue_T("RingTLVInfo", "21");//环回TLV测试内容
		NVSList_THolder lbResult = new NVSList_THolder();
		oamMgrI.setMEGLB(megName, new NameAndStringValue_T[]{}, 1, enableLB, additionalInfo, lbResult);
		int a = 1;
	}

	/**
	 * 启动OAM性能测量功能
	 * @throws ProcessingFailureException 
	 */
	private void setMEGPerfCollection() throws ProcessingFailureException{
		NameAndStringValue_T[] megName = new NameAndStringValue_T[3];
		megName[0] = new NameAndStringValue_T("EMS", 
				(CorbaConfig.getInstanse().getCorbaEmsName()));//德信厂商
		megName[1] = new NameAndStringValue_T("ManagedElement", "6");//网元主键id
		megName[2] = new NameAndStringValue_T("MEG", "3/19");//主键id
		NameAndStringValue_T[] collectParam = new NameAndStringValue_T[4];
		collectParam[0] = new NameAndStringValue_T("Lm", "1");//丢包率 LM帧发送使能 0/1
		collectParam[1] = new NameAndStringValue_T("LMCYCLE", "1s");//LM帧发送周期 1s/100ms
		collectParam[2] = new NameAndStringValue_T("Dm", "1");//延时帧发送使能 0/1
		collectParam[3] = new NameAndStringValue_T("DMCYCLE", "1s");//延时帧发送周期1s/100ms
		NVSList_THolder lbResult = new NVSList_THolder();
		oamMgrI.setMEGPerfCollection(megName, collectParam, lbResult);
		int a= 23;
	}
	
	/**
	 * 使能/禁止锁定功能（LCK）
	 * @throws ProcessingFailureException 
	 */
	private void setAdditionalInfoForLCK() throws ProcessingFailureException{
		NameAndStringValue_T[] objectName = new NameAndStringValue_T[3];
		objectName[0] = new NameAndStringValue_T("EMS", 
				(CorbaConfig.getInstanse().getCorbaEmsName()));//德信厂商
		objectName[1] = new NameAndStringValue_T("ManagedElement", "1");//网元主键id
		objectName[2] = new NameAndStringValue_T("MEG", "5/91");//主键id
		NVSList_THolder lbResult = new NVSList_THolder();
		lbResult.value = new NameAndStringValue_T[2];
		lbResult.value[0]  = new NameAndStringValue_T("TYPE", "1");
		lbResult.value[1]  = new NameAndStringValue_T("LCK", "1");
		oamMgrI.setAdditionalInfo(objectName, lbResult);
		int a = 1;
		a = 3;
	}
	
	/**
	 * 以太网OAM链路发现功能
	 * @throws ProcessingFailureException 
	 */
	private void setOAMLinkDiscovery() throws ProcessingFailureException{
		NameAndStringValue_T[] megName = new NameAndStringValue_T[3];
		megName[0] = new NameAndStringValue_T("EMS", 
				(CorbaConfig.getInstanse().getCorbaEmsName()));//德信厂商
		megName[1] = new NameAndStringValue_T("ManagedElement", "1");//网元主键id
		megName[2] = new NameAndStringValue_T("PTP", "58");//主键id
		oamMgrI.setOAMLinkDiscovery(megName, 1, 1);
		int a = 1;
	}
	
	/**
	 * 以太网OAM链路环回
	 * @throws ProcessingFailureException 
	 */
	private void setOAMLinkLB() throws ProcessingFailureException{
		NameAndStringValue_T[] portName = new NameAndStringValue_T[3];
		portName[0] = new NameAndStringValue_T("EMS", (CorbaConfig.getInstanse().getCorbaEmsName()));//德信厂商
		portName[1] = new NameAndStringValue_T("ManagedElement", "1");//网元主键id
		portName[2] = new NameAndStringValue_T("PTP", "58");//主键id
		NameAndStringValue_T[] linkName = new NameAndStringValue_T[]{};
		OAMLinkLBResult_THolder oamlinkLBResult = new OAMLinkLBResult_THolder();
		oamMgrI.setOAMLinkLB(portName, linkName , 1, oamlinkLBResult);
		int a = 1;
	}
	
	/**
	 * 启动以太网业务OAM性能测量功能
	 * @throws ProcessingFailureException 
	 */
	private void setAdditionalInfo() throws ProcessingFailureException{
		NameAndStringValue_T[] objectName = new NameAndStringValue_T[3];
		objectName[0] = new NameAndStringValue_T("EMS", 
				(CorbaConfig.getInstanse().getCorbaEmsName()));//德信厂商
		objectName[1] = new NameAndStringValue_T("ManagedElement", "1");//网元主键id
		objectName[2] = new NameAndStringValue_T("MEG", "2/91");//主键id
		NVSList_THolder lbResult = new NVSList_THolder();
		lbResult.value = new NameAndStringValue_T[3];
		lbResult.value[0]  = new NameAndStringValue_T("TYPE", "0");
		lbResult.value[1]  = new NameAndStringValue_T("CV", "1");
		lbResult.value[2]  = new NameAndStringValue_T("CVCLE", "2");
		oamMgrI.setAdditionalInfo(objectName, lbResult);
		int a =  1;
	}
}
