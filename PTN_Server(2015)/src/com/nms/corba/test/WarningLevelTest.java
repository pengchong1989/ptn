package com.nms.corba.test;

import aSAP.ASAPCreateModifyData_T;
import aSAP.ASAPIterator_IHolder;
import aSAP.ASAPList_THolder;
import aSAP.ASAP_T;
import aSAP.ASAP_THolder;
import aSAP.AlarmSeverityAssignment_T;
import aSAP.AssignedSeverity_T;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.test.common.CorbaConnect;
import com.nms.ui.manager.ExceptionManage;
import common.Common_IHolder;

import emsMgr.EMSMgr_I;
import emsMgr.EMSMgr_IHelper;
import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

/**
 * @author guoqc
 * 测试 查询/修改 告警级别表
 */
public class WarningLevelTest {
	private EMSMgr_I emsMgrI;
	
	public WarningLevelTest() throws ProcessingFailureException{
		Common_IHolder common = new Common_IHolder();
		CorbaConnect.emsSession.getManager("EMS", common);
		emsMgrI = EMSMgr_IHelper.narrow(common.value);
	}
	
	public static void main(String[] args) throws ProcessingFailureException {
		CorbaConnect c = new CorbaConnect();
		c.isConnect();
		WarningLevelTest warningTest = new WarningLevelTest();
		warningTest.getAllASAPs();//查询所有的告警级别表
		warningTest.modifyASAP();//修改告警级别表
		warningTest.getASAP();//查询某一条告警级别表
	}
	
	/**
	 * 查询所有的告警级别表
	 * @throws ProcessingFailureException 
	 */
	private void getAllASAPs() throws ProcessingFailureException{
		ASAPList_THolder asap = new ASAPList_THolder();
		try {
			emsMgrI.getAllASAPs(1, asap, new ASAPIterator_IHolder());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		for (int i = 0; i < asap.value[0].alarmSeverityAssignmentList.length; i++) {
			AlarmSeverityAssignment_T a = asap.value[0].alarmSeverityAssignmentList[i];
			System.out.print(a.probableCause+", ");//告警原因
			System.out.print(a.probableCauseQualifier+", ");//告警码
			System.out.print(a.nativeProbableCause+", ");//告警名称
			System.out.print(a.serviceAffecting.value()+", ");//本地告警级别
			System.out.print(a.serviceNonAffecting.value()+", ");//协议告警级别
			System.out.println(a.serviceIndependentOrUnknown.value());//协议告警级别
		}
	}
	
	
	/**
	 * 修改告警级别表
	 * @throws ProcessingFailureException 
	 */
	private void modifyASAP() throws ProcessingFailureException{
		NameAndStringValue_T[] aSAPName = new NameAndStringValue_T[2];
		aSAPName[0] = new NameAndStringValue_T((CorbaConfig.getInstanse().getEmsType()), 
				(CorbaConfig.getInstanse().getCorbaEmsName()));//厂商信息
		aSAPName[1] = new NameAndStringValue_T("ASAP", "warninglevel");//
		ASAPCreateModifyData_T aSAPModifyData = new ASAPCreateModifyData_T();
		aSAPModifyData.userLabel = "";
		aSAPModifyData.forceUniqueness = true;
		aSAPModifyData.owner = "";
		aSAPModifyData.alarmSeverityAssignmentList = new AlarmSeverityAssignment_T[1];
		AlarmSeverityAssignment_T alarm = new AlarmSeverityAssignment_T();
		alarm.probableCause = "LBD_DISCOVERY";//告警原因
		alarm.probableCauseQualifier = "72";//告警码
		alarm.nativeProbableCause = "";;//告警名称
		alarm.serviceAffecting = AssignedSeverity_T.from_int(4);//本地告警级别
		alarm.serviceNonAffecting = AssignedSeverity_T.from_int(4);//协议告警级别
		alarm.serviceIndependentOrUnknown = AssignedSeverity_T.from_int(4);//协议告警级别
		aSAPModifyData.alarmSeverityAssignmentList[0] = alarm; 
		aSAPModifyData.additionalInfo = new NameAndStringValue_T[0];
		ASAP_THolder newASAP = new ASAP_THolder();
		newASAP.value = new ASAP_T();
		NVSList_THolder additionalInfo = new NVSList_THolder();
		emsMgrI.modifyASAP(aSAPName, aSAPModifyData, newASAP, additionalInfo);
	}
	
	/**
	 * 查询某一条告警级别表
	 * @throws ProcessingFailureException 
	 */
	private void getASAP() throws ProcessingFailureException{
		NameAndStringValue_T[] aSAPName = new NameAndStringValue_T[4];
		aSAPName[0] = new NameAndStringValue_T((CorbaConfig.getInstanse().getEmsType()), 
				(CorbaConfig.getInstanse().getCorbaEmsName()));//厂商信息
		aSAPName[1] = new NameAndStringValue_T("ASAP", "warninglevel");//
		aSAPName[2] = new NameAndStringValue_T("", "4");//告警级别
		aSAPName[3] = new NameAndStringValue_T("", "72");//告警码
		ASAP_THolder newASAP = new ASAP_THolder();
		newASAP.value = new ASAP_T();
		emsMgrI.getASAP(aSAPName, newASAP);
		AlarmSeverityAssignment_T a = newASAP.value.alarmSeverityAssignmentList[0];
		System.out.print(a.probableCause+", ");//告警原因
		System.out.print(a.probableCauseQualifier+", ");//告警码
		System.out.print(a.nativeProbableCause+", ");//告警名称
		System.out.print(a.serviceAffecting.value()+", ");//本地告警级别
		System.out.print(a.serviceNonAffecting.value()+", ");//协议告警级别
		System.out.println(a.serviceIndependentOrUnknown.value());//协议告警级别
	}
}
