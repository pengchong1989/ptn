package com.nms.corba.test.protect.TNP;

import managedElementManager.ManagedElementMgr_I;

import org.omg.CORBA.StringHolder;

import protection.ReversionMode_T;
import subnetworkConnection.CrossConnect_T;
import trailNtwProtection.TrailNtwProtCreateData_T;
import trailNtwProtection.TrailNtwProtMgr_I;
import trailNtwProtection.TrailNtwProtMgr_IHelper;
import trailNtwProtection.TrailNtwProtModifyData_T;
import trailNtwProtection.TrailNtwProtectionIterator_IHolder;
import trailNtwProtection.TrailNtwProtectionList_THolder;
import trailNtwProtection.TrailNtwProtection_T;
import trailNtwProtection.TrailNtwProtection_THolder;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.corba.test.common.CorbaConnect;
import common.Common_IHolder;

import emsMgr.EMSMgr_I;
import fileTransfer.FileTransferMgr_I;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;
/**
 * TNP测试类
 * @author dzy
 *
 */
public class TNPTest {

	//网元管理者
	ManagedElementMgr_I meMgr = null;
    //ems管理者
	EMSMgr_I emsMgr = null;
	FileTransferMgr_I fileTransferMgr = null;
	
	public static void main(String[] args) {
		try {
			CorbaConnect connect = new CorbaConnect();
			if(connect.isConnect()){
				TNPTest TNPTest = new TNPTest();
				TNPTest.getTNP(connect);
//				TNPTest.createTNP(connect);
				
			}
		} catch (ProcessingFailureException e) {
			System.out.println(e.errorReason);
		}
	}
	
	public void getTNP(CorbaConnect connect) throws ProcessingFailureException {
		Common_IHolder mgrHolder = new Common_IHolder();
		connect.emsSession.getManager("TrailNtwProtection", mgrHolder);
		TrailNtwProtMgr_I trailNtwProtMgr_Impl = TrailNtwProtMgr_IHelper.narrow(mgrHolder.value);

		NameAndStringValue_T[] name = new NameAndStringValue_T[2];
		name[0] = new NameAndStringValue_T("EMS", "DATAX");
		name[1] = new NameAndStringValue_T("ManagedElement", "3");
		
		TrailNtwProtection_THolder trailNtwProtection_THolder = new TrailNtwProtection_THolder(); 
		TrailNtwProtectionList_THolder  tnpList  = new TrailNtwProtectionList_THolder();
		TrailNtwProtectionIterator_IHolder tnpIt= new TrailNtwProtectionIterator_IHolder();
		
//		trailNtwProtMgr_Impl.getAllTrailNtwProtections(name, 1, tnpList, tnpIt);
//		
//		if (tnpList.value != null && tnpList.value.length > 0) {
//			for (int i = 0; i < tnpList.value.length; i++) {
//				System.out.println(tnpList.value[i].nativeEMSName);
//			}
//		}
//		if (tnpIt.value != null) {
//			while (tnpIt.value.next_n(2, tnpList)) {
//				for (int i = 0; i < tnpList.value.length; i++) {
//					System.out.println(tnpList.value[i].nativeEMSName);
//				}
//			}
//			tnpIt.value.destroy();
//		}
		name = new NameAndStringValue_T[3];
		name[0] = new NameAndStringValue_T("EMS", "DATAX/1");
		name[1] = new NameAndStringValue_T("MultiLayerSubnetwork", "1");
		name[2] = new NameAndStringValue_T("TrailNtwProtection", ELayerRate.TUNNEL.getValue()+"/18");
		trailNtwProtMgr_Impl.getTrailNtwProtection(name, trailNtwProtection_THolder);
		
		System.out.println(trailNtwProtection_THolder.value.userLabel);
	}
	
	public void createTNP(CorbaConnect connect) throws ProcessingFailureException{
		Common_IHolder mgrHolder = new Common_IHolder();
		connect.emsSession.getManager("TrailNtwProtection", mgrHolder);
		TrailNtwProtMgr_I trailNtwProtMgr = (TrailNtwProtMgr_I) TrailNtwProtMgr_IHelper.narrow(mgrHolder.value);
		TrailNtwProtCreateData_T trailNtwProtCreateData_T = new TrailNtwProtCreateData_T();
		TrailNtwProtection_THolder trailNtwProtection_THolder = new TrailNtwProtection_THolder();
		trailNtwProtCreateData_T.modifiableAttributes = new TrailNtwProtModifyData_T[2]; 
		trailNtwProtection_THolder.value = new TrailNtwProtection_T();
		//工作
		trailNtwProtCreateData_T.modifiableAttributes[0] = new TrailNtwProtModifyData_T();
		trailNtwProtCreateData_T.modifiableAttributes[0].nativeEMSName = "TUNNEL/700D_203-700D_202_20140527145015";
		trailNtwProtCreateData_T.modifiableAttributes[0].pgATPList =  new NameAndStringValue_T[1][3]; 
		trailNtwProtCreateData_T.modifiableAttributes[0].pgATPList[0][0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
		trailNtwProtCreateData_T.modifiableAttributes[0].pgATPList[0][1] = new NameAndStringValue_T("ManagedElement","3");
		trailNtwProtCreateData_T.modifiableAttributes[0].pgATPList[0][2] = new NameAndStringValue_T("PTP","126");
		trailNtwProtCreateData_T.modifiableAttributes[0].pgZTPList =  new NameAndStringValue_T[1][3]; 
		trailNtwProtCreateData_T.modifiableAttributes[0].pgZTPList[0][0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
		trailNtwProtCreateData_T.modifiableAttributes[0].pgZTPList[0][1] = new NameAndStringValue_T("ManagedElement","1");
		trailNtwProtCreateData_T.modifiableAttributes[0].pgZTPList[0][2] = new NameAndStringValue_T("PTP","29");
		
		trailNtwProtCreateData_T.modifiableAttributes[0].route = new CrossConnect_T[0];
		/*trailNtwProtCreateData_T.modifiableAttributes[0].crossConnects[0][0] = new CrossConnect_T();
		trailNtwProtCreateData_T.modifiableAttributes[0].crossConnects[0][0].aEndNameList = new NameAndStringValue_T[1][3];
		trailNtwProtCreateData_T.modifiableAttributes[0].crossConnects[0][0].zEndNameList = new NameAndStringValue_T[1][3];
		trailNtwProtCreateData_T.modifiableAttributes[0].crossConnects[0][0].zEndNameList[0][0] = new NameAndStringValue_T("EMS","DataX/1");
		trailNtwProtCreateData_T.modifiableAttributes[0].crossConnects[0][0].zEndNameList[0][1] = new NameAndStringValue_T("ManagedElement","1");
		trailNtwProtCreateData_T.modifiableAttributes[0].crossConnects[0][0].zEndNameList[0][2] = new NameAndStringValue_T("PTP","30");
		trailNtwProtCreateData_T.modifiableAttributes[0].crossConnects[0][0].aEndNameList[0][0] = new NameAndStringValue_T("EMS","DataX/1");
		trailNtwProtCreateData_T.modifiableAttributes[0].crossConnects[0][0].aEndNameList[0][1] = new NameAndStringValue_T("ManagedElement","1");
		trailNtwProtCreateData_T.modifiableAttributes[0].crossConnects[0][0].aEndNameList[0][2] = new NameAndStringValue_T("PTP","29");*/
		
		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo = new NameAndStringValue_T[22]; 
		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[0] = new NameAndStringValue_T("isActive", "1");
		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[1] = new NameAndStringValue_T("CreateTime", "2014-04-28 16:02:09");
		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[2] = new NameAndStringValue_T("createuser", "admin");
		
		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[3] = new NameAndStringValue_T("qosName", "");
		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[4] = new NameAndStringValue_T("qosType", "LLSP");
		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[5] = new NameAndStringValue_T("qosCos", "1");
		
		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[6] = new NameAndStringValue_T("A2ZCIR", "0");
		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[7] = new NameAndStringValue_T("A2ZPIR", "0");
		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[8] = new NameAndStringValue_T("A2ZCBS", "-1");
		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[9] = new NameAndStringValue_T("A2ZPBS", "0");
		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[10] = new NameAndStringValue_T("A2ZEBS", "-1");
		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[11] = new NameAndStringValue_T("A2ZEIR", "0");
		
		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[12] = new NameAndStringValue_T("Z2ACIR", "0");
		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[13] = new NameAndStringValue_T("Z2APIR", "0");
		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[14] = new NameAndStringValue_T("Z2ACBS", "-1");
		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[15] = new NameAndStringValue_T("Z2APBS", "0");
		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[16] = new NameAndStringValue_T("Z2AEBS", "-1");
		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[17] = new NameAndStringValue_T("Z2AEIR", "0");
		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[18] = new NameAndStringValue_T("SrcInLabel", "27");
		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[19] = new NameAndStringValue_T("SrcOutLabel", "28");
		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[20] = new NameAndStringValue_T("DestInLabel", "28");
		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[21] = new NameAndStringValue_T("DestOutLabel", "27");
		
		
	/*	trailNtwProtCreateData_T.modifiableAttributes[0].crossConnects[0][0].additionalInfo = new NameAndStringValue_T[4];
		trailNtwProtCreateData_T.modifiableAttributes[0].crossConnects[0][0].additionalInfo[0] = new NameAndStringValue_T("SrcInLabel", "67217");
		trailNtwProtCreateData_T.modifiableAttributes[0].crossConnects[0][0].additionalInfo[1] = new NameAndStringValue_T("SrcOutLabel", "67218");
		trailNtwProtCreateData_T.modifiableAttributes[0].crossConnects[0][0].additionalInfo[2] = new NameAndStringValue_T("DestInLabel", "67219");
		trailNtwProtCreateData_T.modifiableAttributes[0].crossConnects[0][0].additionalInfo[3] = new NameAndStringValue_T("DestOutLabel", "67220");*/
		
		
		//保护
		trailNtwProtCreateData_T.modifiableAttributes[1] = new TrailNtwProtModifyData_T();
		trailNtwProtCreateData_T.modifiableAttributes[1].route = new CrossConnect_T[1];
		trailNtwProtCreateData_T.modifiableAttributes[1].route[0] = new CrossConnect_T();
		trailNtwProtCreateData_T.modifiableAttributes[1] = new TrailNtwProtModifyData_T();
		trailNtwProtCreateData_T.modifiableAttributes[1].pgATPList =  new NameAndStringValue_T[1][3]; 
		trailNtwProtCreateData_T.modifiableAttributes[1].pgATPList[0][0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
		trailNtwProtCreateData_T.modifiableAttributes[1].pgATPList[0][1] = new NameAndStringValue_T("ManagedElement","3");
		trailNtwProtCreateData_T.modifiableAttributes[1].pgATPList[0][2] = new NameAndStringValue_T("PTP","126");
		trailNtwProtCreateData_T.modifiableAttributes[1].pgZTPList =  new NameAndStringValue_T[1][3]; 
		trailNtwProtCreateData_T.modifiableAttributes[1].pgZTPList[0][0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
		trailNtwProtCreateData_T.modifiableAttributes[1].pgZTPList[0][1] = new NameAndStringValue_T("ManagedElement","1");
		trailNtwProtCreateData_T.modifiableAttributes[1].pgZTPList[0][2] = new NameAndStringValue_T("PTP","29");
		
		trailNtwProtCreateData_T.modifiableAttributes[1].route = new CrossConnect_T[0];
		/*trailNtwProtCreateData_T.modifiableAttributes[1].crossConnects[0][0] = new CrossConnect_T();
		trailNtwProtCreateData_T.modifiableAttributes[1].crossConnects[0][0].aEndNameList = new NameAndStringValue_T[1][3];
		trailNtwProtCreateData_T.modifiableAttributes[1].crossConnects[0][0].zEndNameList = new NameAndStringValue_T[1][3];
		trailNtwProtCreateData_T.modifiableAttributes[1].crossConnects[0][0].zEndNameList[0][0] = new NameAndStringValue_T("EMS","DataX/1");
		trailNtwProtCreateData_T.modifiableAttributes[1].crossConnects[0][0].zEndNameList[0][1] = new NameAndStringValue_T("ManagedElement","1");
		trailNtwProtCreateData_T.modifiableAttributes[1].crossConnects[0][0].zEndNameList[0][2] = new NameAndStringValue_T("PTP","30");
		trailNtwProtCreateData_T.modifiableAttributes[1].crossConnects[0][0].aEndNameList[0][0] = new NameAndStringValue_T("EMS","DataX/1");
		trailNtwProtCreateData_T.modifiableAttributes[1].crossConnects[0][0].aEndNameList[0][1] = new NameAndStringValue_T("ManagedElement","1");
		trailNtwProtCreateData_T.modifiableAttributes[1].crossConnects[0][0].aEndNameList[0][2] = new NameAndStringValue_T("PTP","29");*/
		
		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo = new NameAndStringValue_T[22]; 
		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[0] = new NameAndStringValue_T("isActive", "1");
		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[1] = new NameAndStringValue_T("CreateTime", "2014-04-28 16:02:09");
		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[2] = new NameAndStringValue_T("createuser", "admin");
		
		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[3] = new NameAndStringValue_T("qosName", "");
		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[4] = new NameAndStringValue_T("qosType", "LLSP");
		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[5] = new NameAndStringValue_T("qosCos", "1");
		
		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[6] = new NameAndStringValue_T("A2ZCIR", "0");
		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[7] = new NameAndStringValue_T("A2ZPIR", "0");
		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[8] = new NameAndStringValue_T("A2ZCBS", "-1");
		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[9] = new NameAndStringValue_T("A2ZPBS", "0");
		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[10] = new NameAndStringValue_T("A2ZEBS", "-1");
		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[11] = new NameAndStringValue_T("A2ZEIR", "0");
		trailNtwProtCreateData_T.rate = (short) ELayerRate.TNP.getValue();
		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[12] = new NameAndStringValue_T("Z2ACIR", "0");
		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[13] = new NameAndStringValue_T("Z2APIR", "0");
		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[14] = new NameAndStringValue_T("Z2ACBS", "-1");
		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[15] = new NameAndStringValue_T("Z2APBS", "0");
		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[16] = new NameAndStringValue_T("Z2AEBS", "-1");
		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[17] = new NameAndStringValue_T("Z2AEIR", "0");
		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[18] = new NameAndStringValue_T("SrcInLabel", "30");
		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[19] = new NameAndStringValue_T("SrcOutLabel", "31");
		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[20] = new NameAndStringValue_T("DestInLabel", "31");
		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[21] = new NameAndStringValue_T("DestOutLabel", "30");
		
	/*	trailNtwProtCreateData_T.modifiableAttributes[1].crossConnects[0][0].additionalInfo = new NameAndStringValue_T[4];
		trailNtwProtCreateData_T.modifiableAttributes[1].crossConnects[0][0].additionalInfo[0] = new NameAndStringValue_T("SrcInLabel", "67210");
		trailNtwProtCreateData_T.modifiableAttributes[1].crossConnects[0][0].additionalInfo[1] = new NameAndStringValue_T("SrcOutLabel", "67211");
		trailNtwProtCreateData_T.modifiableAttributes[1].crossConnects[0][0].additionalInfo[2] = new NameAndStringValue_T("DestInLabel", "67215");
		trailNtwProtCreateData_T.modifiableAttributes[1].crossConnects[0][0].additionalInfo[3] = new NameAndStringValue_T("DestOutLabel", "67216");*/
		StringHolder errorReason = new StringHolder();
		
		//EMS不需要字段
		/*trailNtwProtCreateData_T.modifiableAttributes[0].crossConnects[0][0].direction = ConnectionDirection_T.CD_BI;
		trailNtwProtCreateData_T.modifiableAttributes[1].crossConnects[0][0].direction = ConnectionDirection_T.CD_BI;
		trailNtwProtCreateData_T.modifiableAttributes[1].crossConnects[0][0].ccType = SNCType_T.ST_ADD_DROP_A;
		trailNtwProtCreateData_T.modifiableAttributes[0].crossConnects[0][0].ccType = SNCType_T.ST_ADD_DROP_A;*/
		trailNtwProtCreateData_T.protectionGroupAName = new NameAndStringValue_T[0];
		trailNtwProtCreateData_T.protectionGroupZName = new NameAndStringValue_T[0];
		trailNtwProtCreateData_T.modifiableAttributes[0].reversionMode = ReversionMode_T.RM_NON_REVERTIVE;
		trailNtwProtCreateData_T.modifiableAttributes[1].reversionMode = ReversionMode_T.RM_NON_REVERTIVE;
		trailNtwProtCreateData_T.modifiableAttributes[0].tnpParameters = new NameAndStringValue_T[2];
		trailNtwProtCreateData_T.modifiableAttributes[0].tnpParameters[0] = new NameAndStringValue_T("WaitTime", "7");
		trailNtwProtCreateData_T.modifiableAttributes[0].tnpParameters[1] = new NameAndStringValue_T("DelayTime", "750");
		trailNtwProtCreateData_T.modifiableAttributes[1].tnpParameters = new NameAndStringValue_T[0];
		trailNtwProtCreateData_T.workerTrailList = new NameAndStringValue_T[0][0][0];
		trailNtwProtCreateData_T.protectionTrail = new NameAndStringValue_T[0][0];
		
		
		trailNtwProtMgr.createTrailNtwProtection(trailNtwProtCreateData_T, trailNtwProtection_THolder, errorReason);
		int as = 1;
		as= 3;
	}
		
}
