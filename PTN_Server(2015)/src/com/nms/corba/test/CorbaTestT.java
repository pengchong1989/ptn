package com.nms.corba.test;

import managedElementManager.ManagedElementMgr_I;
import multiLayerSubnetwork.MultiLayerSubnetworkMgr_I;
import multiLayerSubnetwork.MultiLayerSubnetworkMgr_IHelper;
import multiLayerSubnetwork.SubnetworkIterator_IHolder;
import multiLayerSubnetwork.SubnetworkList_THolder;

import org.omg.CORBA.StringHolder;

import protection.ProtectionCommand_T;
import protection.ProtectionGroupIterator_IHolder;
import protection.ProtectionGroupList_THolder;
import protection.ProtectionMgr_I;
import protection.ProtectionMgr_IHelper;
import protection.ReversionMode_T;
import protection.SwitchData_T;
import protection.SwitchData_THolder;
import securityManager.SecurityMgr_I;
import securityManager.SecurityMgr_IHelper;
import subnetworkConnection.CrossConnect_T;
import subnetworkConnection.RouteCreateData_T;
import subnetworkConnection.RouteDescriptor_T;
import subnetworkConnection.RouteDescriptor_THolder;
import timeMgr.TimeMgr_I;
import timeMgr.TimeMgr_IHelper;
import topologicalLink.TopologicalLinkIterator_IHolder;
import topologicalLink.TopologicalLinkList_THolder;
import topologicalLink.TopologicalLink_THolder;
import trailNtwProtection.TNPSwitchData_THolder;
import trailNtwProtection.TrailNtwProtCreateData_T;
import trailNtwProtection.TrailNtwProtMgr_I;
import trailNtwProtection.TrailNtwProtMgr_IHelper;
import trailNtwProtection.TrailNtwProtModifyData_T;
import trailNtwProtection.TrailNtwProtectionIterator_IHolder;
import trailNtwProtection.TrailNtwProtectionList_THolder;
import trailNtwProtection.TrailNtwProtection_T;
import trailNtwProtection.TrailNtwProtection_THolder;
import clockSource.ClockSourceList_THolder;
import clockSource.ClockSource_I;
import clockSource.ClockSource_IHelper;
import clockSource.ClockSource_T;

import com.nms.corba.ninterface.enums.EProtectionType;
import com.nms.corba.ninterface.enums.ESwitchDirection;
import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.corba.test.common.CorbaConnect;
import com.nms.ui.manager.ExceptionManage;
import common.Common_IHolder;

import emsMgr.EMSMgr_I;
import emsMgr.EMSMgr_IHelper;
import fileTransfer.FileTransferMgr_I;
import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

public class CorbaTestT {
	//网元管理者
	ManagedElementMgr_I meMgr = null;
    //ems管理者
	EMSMgr_I emsMgr = null;
	FileTransferMgr_I fileTransferMgr = null;
	
	public static void main(String[] args) {
		try {
			CorbaConnect connect = new CorbaConnect();
			if(connect.isConnect()){
				getTNP(connect);
			}
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,CorbaTestT.class);
		}
	}
	
	/**
	 * 查询保护组
	 * @throws ProcessingFailureException 
	 */
	public static void getProtectGroup(CorbaConnect connect) throws ProcessingFailureException{
		//获取管理者
		Common_IHolder mgrHolder = new Common_IHolder();
		//不同接口管理者名称不同
		connect.emsSession.getManager("Protection", mgrHolder);
		ProtectionMgr_I protectionMgr = ProtectionMgr_IHelper.narrow(mgrHolder.value);
		ProtectionGroupList_THolder tnpList = new ProtectionGroupList_THolder();
		ProtectionGroupIterator_IHolder tnpIt = new ProtectionGroupIterator_IHolder();
		NameAndStringValue_T[] name = new NameAndStringValue_T[2];
		name[0] = new NameAndStringValue_T();
		name[0].name = "EMS";//子网标示符
		name[0].value = "DATAX/1";
		name[1] = new NameAndStringValue_T();
		name[1].name = "ManagedElement";//子网供应商名称
		name[1].value = "3";
		protectionMgr.getAllProtectionGroups(name, 2, tnpList, tnpIt);
		
		if (tnpList.value != null && tnpList.value.length > 0) {
			for (int i = 0; i < tnpList.value.length; i++) {
				System.out.println(tnpList.value[i].nativeEMSName);
			}
		}
		if (tnpIt.value != null) {
			while (tnpIt.value.next_n(2, tnpList)) {
				for (int i = 0; i < tnpList.value.length; i++) {
					System.out.println(tnpList.value[i].nativeEMSName);
				}
			}
			tnpIt.value.destroy();
		}
	}
	
	
	public static void clockSourceTest(CorbaConnect connect) throws ProcessingFailureException{
		
		try {
			//获取管理者
			Common_IHolder mgrHolder = new Common_IHolder();
			//不同接口管理者名称不同
			connect.emsSession.getManager("ClockSource", mgrHolder);
			ClockSource_I clockSource_Impl = ClockSource_IHelper.narrow(mgrHolder.value);
			NameAndStringValue_T[] name = new NameAndStringValue_T[2];
			name[0] = new NameAndStringValue_T();
			name[0].name = "EMS";//子网标示符
			name[0].value = "DATAX/1";
			name[1] = new NameAndStringValue_T();
			name[1].name = "ManagedElement";//子网供应商名称
			name[1].value = "4";
			
			ClockSourceList_THolder a = new ClockSourceList_THolder();
			a.value = new ClockSource_T[0];
			clockSource_Impl.getMEClockSource(name, a);
	
			int ae = 1; 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,CorbaTestT.class);
		}
	}
	
	public static void addRoute(CorbaConnect connect) throws ProcessingFailureException{
		Common_IHolder mgrHolder = new Common_IHolder();
		//不同接口管理者名称不同
		connect.emsSession.getManager("MultiLayerSubnetwork", mgrHolder);
		MultiLayerSubnetworkMgr_I multiLayerSubnetworkMgr_I = MultiLayerSubnetworkMgr_IHelper.narrow(mgrHolder.value);
		NameAndStringValue_T[] name = new NameAndStringValue_T[3];
		name[0] = new NameAndStringValue_T("EMS", "DATAX/1");
		name[1] = new NameAndStringValue_T("MultiLayerSubnetwork", "1");
		name[2] = new NameAndStringValue_T("SubnetworkConnection", ELayerRate.TUNNEL.getValue()+"/51");
		RouteCreateData_T routeCreateData = new RouteCreateData_T();
		routeCreateData.neTpInclusions = new NameAndStringValue_T[2][3];
		routeCreateData.neTpInclusions[0][0] = new NameAndStringValue_T("EMS","DATAX/1");
		routeCreateData.neTpInclusions[0][1] = new NameAndStringValue_T("ManagedElement","3");
		routeCreateData.neTpInclusions[0][2] = new NameAndStringValue_T("PTP","126");
		routeCreateData.neTpInclusions[1][0] = new NameAndStringValue_T("EMS","DATAX/1");
		routeCreateData.neTpInclusions[1][1] = new NameAndStringValue_T("ManagedElement","1");
		routeCreateData.neTpInclusions[1][2] = new NameAndStringValue_T("PTP","29");
	/*	routeCreateData.ccInclusions = new CrossConnect_T[1];
		routeCreateData.ccInclusions[0] = new CrossConnect_T();
		routeCreateData.ccInclusions[0].aEndNameList = new NameAndStringValue_T[2][3]; 
		routeCreateData.ccInclusions[0].aEndNameList[0][0] = new NameAndStringValue_T("EMS","DATAX/1");
		routeCreateData.ccInclusions[0].aEndNameList[0][1] = new NameAndStringValue_T("ManagedElement","1");
		routeCreateData.ccInclusions[0].aEndNameList[0][2] = new NameAndStringValue_T("PTP","29");
		
		routeCreateData.ccInclusions[0].zEndNameList = new NameAndStringValue_T[2][3]; 
		routeCreateData.ccInclusions[0].zEndNameList[0][0] = new NameAndStringValue_T("EMS","DATAX/1");
		routeCreateData.ccInclusions[0].zEndNameList[0][1] = new NameAndStringValue_T("ManagedElement","1");
		routeCreateData.ccInclusions[0].zEndNameList[0][2] = new NameAndStringValue_T("PTP","30");
		
		
		routeCreateData.ccInclusions[1] = new CrossConnect_T();
		routeCreateData.ccInclusions[1].aEndNameList = new NameAndStringValue_T[2][3]; 
		routeCreateData.ccInclusions[0].aEndNameList[1][0] = new NameAndStringValue_T("EMS","DataX/1");
		routeCreateData.ccInclusions[0].aEndNameList[1][1] = new NameAndStringValue_T("ManageElement","1");
		routeCreateData.ccInclusions[0].aEndNameList[1][2] = new NameAndStringValue_T("PTP","30");
		
		routeCreateData.ccInclusions[1].zEndNameList = new NameAndStringValue_T[2][3]; 
		routeCreateData.ccInclusions[0].zEndNameList[1][0] = new NameAndStringValue_T("EMS","DataX/1");
		routeCreateData.ccInclusions[0].zEndNameList[1][1] = new NameAndStringValue_T("ManageElement","2");
		routeCreateData.ccInclusions[0].zEndNameList[1][2] = new NameAndStringValue_T("PTP","61");*/
		
		routeCreateData.additionalCreationInfo = new NameAndStringValue_T[6];
		routeCreateData.additionalCreationInfo[0] = new NameAndStringValue_T("SrcInLabel","29");
		routeCreateData.additionalCreationInfo[1] = new NameAndStringValue_T("SrcOutLabel","28");
		routeCreateData.additionalCreationInfo[2] = new NameAndStringValue_T("DestInLabel","28");
		routeCreateData.additionalCreationInfo[3] = new NameAndStringValue_T("DestOutLabel","29");
		routeCreateData.additionalCreationInfo[4] = new NameAndStringValue_T("WaitTime","5");
		routeCreateData.additionalCreationInfo[5] = new NameAndStringValue_T("DelayTime","5");
		routeCreateData.ccInclusions = new CrossConnect_T[0];
	/*	routeCreateData.ccInclusions[0].additionalInfo = new NameAndStringValue_T[4];
		routeCreateData.ccInclusions[0].additionalInfo[0] = new NameAndStringValue_T("SrcInLabel","57244");
		routeCreateData.ccInclusions[0].additionalInfo[1] = new NameAndStringValue_T("SrcOutLabel","57245");
		routeCreateData.ccInclusions[0].additionalInfo[2] = new NameAndStringValue_T("DestInLabel","57246");
		routeCreateData.ccInclusions[0].additionalInfo[3] = new NameAndStringValue_T("DestOutLabel","57247");*/
		StringHolder errorReason = new StringHolder();
		RouteDescriptor_THolder routeDescriptor_THolder = new RouteDescriptor_THolder();
		routeDescriptor_THolder.value = new RouteDescriptor_T();
		routeDescriptor_THolder.value.additionalInfo = new NameAndStringValue_T[0]; 
		routeDescriptor_THolder.value.routeXCs = new CrossConnect_T[0];
		routeCreateData.neTpSncExclusions = new NameAndStringValue_T[0][0];
		multiLayerSubnetworkMgr_I.addRoute(name, routeCreateData, 
				routeDescriptor_THolder, errorReason);
		
		int as = 1;
		as= 3;
	}
	
	public static void removeRoute(CorbaConnect connect) throws ProcessingFailureException{
		Common_IHolder mgrHolder = new Common_IHolder();
		//不同接口管理者名称不同
		NVSList_THolder  NVSList_THolder = new NVSList_THolder();
		NVSList_THolder.value =  new NameAndStringValue_T[1]; 
		NVSList_THolder.value[0] = new NameAndStringValue_T("MultiLayerSubnetwork","MultiLayerSubnetwork");
		connect.emsSession.getManager("MultiLayerSubnetwork", mgrHolder);
		MultiLayerSubnetworkMgr_I multiLayerSubnetworkMgr_I = MultiLayerSubnetworkMgr_IHelper.narrow(mgrHolder.value);
		NameAndStringValue_T[] name = new NameAndStringValue_T[3];
		name[0] = new NameAndStringValue_T("EMS", "DATAX/1");
		name[1] = new NameAndStringValue_T("MultiLayerSubnetwork", "1");
		name[2] = new NameAndStringValue_T("TrailNtwProtection", ELayerRate.TNP.getValue()+"/51");
		multiLayerSubnetworkMgr_I.removeRoute(name, "50",  NVSList_THolder);
		
		int as = 1;
		as= 3;
	}
	
	public static void getTNP(CorbaConnect connect) throws ProcessingFailureException {
		Common_IHolder mgrHolder = new Common_IHolder();
		connect.emsSession.getManager("TrailNtwProtection", mgrHolder);
		TrailNtwProtMgr_I trailNtwProtMgr_Impl = TrailNtwProtMgr_IHelper.narrow(mgrHolder.value);

		NameAndStringValue_T[] name = new NameAndStringValue_T[2];
		name[0] = new NameAndStringValue_T("EMS", "DATAX");
		name[1] = new NameAndStringValue_T("ManagedElement", "6");
		TrailNtwProtection_THolder trailNtwProtection_THolder = new TrailNtwProtection_THolder(); 
		TrailNtwProtectionList_THolder  tnpList  = new TrailNtwProtectionList_THolder();
		TrailNtwProtectionIterator_IHolder tnpIt= new TrailNtwProtectionIterator_IHolder();
		trailNtwProtMgr_Impl.getAllTrailNtwProtections(name, 2, tnpList, tnpIt);
		if (tnpList.value != null && tnpList.value.length > 0) {
			for (int i = 0; i < tnpList.value.length; i++) {
				System.out.println(tnpList.value[i].nativeEMSName);
			}
		}
		if (tnpIt.value != null) {
			while (tnpIt.value.next_n(2, tnpList)) {
				for (int i = 0; i < tnpList.value.length; i++) {
					System.out.println(tnpList.value[i].nativeEMSName);
				}
			}
			tnpIt.value.destroy();
		}
		name = new NameAndStringValue_T[3];
		name[0] = new NameAndStringValue_T("EMS", "DATAX");
		name[1] = new NameAndStringValue_T("MultiLayerSubnetwork", "");
		name[2] = new NameAndStringValue_T("TNP", ELayerRate.TNP.getValue()+"/55");
		trailNtwProtMgr_Impl.getTrailNtwProtection(name, trailNtwProtection_THolder);
		int as;
		as= 3;
	}
	
	public static void createTNP(CorbaConnect connect) throws ProcessingFailureException{
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
	
	
	
	/**
	 * 查询拓扑
	 * @throws ProcessingFailureException 
	 */
	public static void getTopo(CorbaConnect connect) throws ProcessingFailureException{
		//获取管理者
		Common_IHolder mgrHolder = new Common_IHolder();
		//不同接口管理者名称不同
		connect.emsSession.getManager("EMS", mgrHolder);
		EMSMgr_I EMSMgr_I = EMSMgr_IHelper.narrow(mgrHolder.value);
		TopologicalLink_THolder topo = new TopologicalLink_THolder();
		TopologicalLinkList_THolder topoList = new TopologicalLinkList_THolder();
		TopologicalLinkIterator_IHolder topoIt = new TopologicalLinkIterator_IHolder();
		NameAndStringValue_T[] name = new NameAndStringValue_T[1];
		name[0] = new NameAndStringValue_T();
		name[0].name = "EMS";//子网标示符
		name[0].value = "DATAX/1";
		
	/*	EMSMgr_I.getAllTopLevelTopologicalLinks(1, topoList, topoIt);
		
		if (topoList.value != null && topoList.value.length > 0) {
			for (int i = 0; i < topoList.value.length; i++) {
				System.out.println(topoList.value[i].nativeEMSName);
			}
		}
		if (topoIt.value != null) {
			while (topoIt.value.next_n(1, topoList)) {
				for (int i = 0; i < topoList.value.length; i++) {
					System.out.println(topoList.value[i].nativeEMSName);
				}
			}
			topoIt.value.destroy();
		}*/
		
		name = new NameAndStringValue_T[2];
		name[0] = new NameAndStringValue_T();
		name[0].name = "EMS";//子网标示符
		name[0].value = "DATAX/1";
		name[1] = new NameAndStringValue_T();
		name[1].name = "TopologicalLink";//子网供应商名称
		name[1].value = "18/2";
		
		EMSMgr_I.getTopLevelTopologicalLink(name, topo);
		
		name[0].name = "EMS";//子网标示符
		name[0].value = "DATAX/1";
	}
	
	/**
	 * 查询拓扑
	 * @throws ProcessingFailureException 
	 */
	public static void setTopoUserLabel(CorbaConnect connect) throws ProcessingFailureException{
		//获取管理者
		Common_IHolder mgrHolder = new Common_IHolder();
		//不同接口管理者名称不同
		connect.emsSession.getManager("EMS", mgrHolder);
		EMSMgr_I EMSMgr_I = EMSMgr_IHelper.narrow(mgrHolder.value);
		TopologicalLink_THolder topo = new TopologicalLink_THolder();
		TopologicalLinkList_THolder topoList = new TopologicalLinkList_THolder();
		TopologicalLinkIterator_IHolder topoIt = new TopologicalLinkIterator_IHolder();
		
		NameAndStringValue_T[] name = new NameAndStringValue_T[2];
		name[0] = new NameAndStringValue_T();
		name[0].name = "EMS";//子网标示符
		name[0].value = "DATAX/1";
		name[1] = new NameAndStringValue_T();
		name[1].name = "TopologicalLink";//子网供应商名称
		name[1].value = "18/2";
		
		EMSMgr_I.setUserLabel(name, "20.0.0.201/ge.10.4-20.0.0.203/ge.1.11", false);
		
		name[0].name = "EMS";//子网标示符
		name[0].value = "DATAX/1";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//徐晓鑫代码部分
	
	
	/**
	 * 测试子网信息查询
	 * @throws ProcessingFailureException 
	 */
	private static void getSNCMessage(CorbaConnect connect) throws ProcessingFailureException {
		EMSMgr_I emsMgr = null;
		System.out.println("初始化 EMS 管理器!");
		Common_IHolder mgrHolder = new Common_IHolder();
		connect.emsSession.getManager("EMS", mgrHolder);
		emsMgr = EMSMgr_IHelper.narrow(mgrHolder.value);
		System.out.println("EMS_Manager To String ---" + emsMgr.toString());
		
		//获取管理者
	
		SubnetworkList_THolder sList = new SubnetworkList_THolder();
		SubnetworkIterator_IHolder sIt = new SubnetworkIterator_IHolder();
	
		
		try {
			emsMgr.getAllTopLevelSubnetworks(1, sList, sIt);
			System.out.println("检测完毕！！");
			if (sList.value != null && sList.value.length >0 ) {
				for (int i = 0; i < sList.value.length; i++) {
					System.out.println(sList.value[i].name[1].value+"**");
					System.out.println(sList.value[0].owner+"*///");
				}
			}
			if (sIt.value != null) {
				while (sIt.value.next_n(1, sList)) {
					for (int i = 0; i < sList.value.length; i++) {
						System.out.println(sList.value[i].name[1].value);
					}
				}
				for (int i = 0; i < sList.value.length; i++) {
					System.out.println(sList.value[i].name[1].value+"***");
				}
				sIt.value.destroy();
			}
			
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,CorbaTestT.class);
		}
	}

	/**
	 * 测试子网修改
	 */
	private static void changeSNCMessage(CorbaConnect connect)throws ProcessingFailureException  {
		System.out.println(1112);
		EMSMgr_I emsMgr = null;
		try {
			System.out.println("初始化 EMS 管理器!");
			Common_IHolder mgrHolder = new Common_IHolder();
			connect.emsSession.getManager("EMS", mgrHolder);
			emsMgr = EMSMgr_IHelper.narrow(mgrHolder.value);
			System.out.println("EMS_Manager To String ---" + emsMgr.toString());
		} catch (ProcessingFailureException pfe) {
			System.out
					.println("初始化 EMS 管理器异常!---ProcessingFailureException---");
			System.out.println(pfe.toString());
		}
//		null != objectName && objectName.length >=2&&null != objectName[1].name
//		&&objectName[1].name.equals("MultiLayerSubnetwork")
		NameAndStringValue_T[] objectName = new NameAndStringValue_T[2];
		objectName[0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
		objectName[1] = new NameAndStringValue_T("MultiLayerSubnetwork","subnetworkconnection");
		
		try {
	//		emsMgr.setUserLabel(objectName, "hhii", false);
			
			NVSList_THolder NVSList_THolder = new NVSList_THolder();
			NVSList_THolder.value= new NameAndStringValue_T[1]; 
			NVSList_THolder.value[0] = new NameAndStringValue_T("macLocation", "hhii"); 
			emsMgr.setAdditionalInfo(objectName, NVSList_THolder);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,CorbaTestT.class);
		}
		
	}
	
	
	/**
	 * 修改网络保护组测试单网元侧
	 */
	
	private static void setUserLabel(CorbaConnect connect) throws ProcessingFailureException{
		System.out.println(222);
		ProtectionMgr_I mgr_I = null;
		try {
			System.out.println("初始化 EMS 管理器!");
			Common_IHolder mgrHolder = new Common_IHolder();
			connect.emsSession.getManager("Protection", mgrHolder);
			mgr_I = ProtectionMgr_IHelper.narrow(mgrHolder.value);
			System.out.println("EMS_Manager To String ---" + mgr_I.toString());
		} catch (ProcessingFailureException pfe) {
			System.out
					.println("初始化 EMS 管理器异常!---ProcessingFailureException---");
			System.out.println(pfe.toString());
		}
//		if (null == objectName|| objectName.length < 3 || null == objectName[1].name ||null == objectName[2].name
//				|| !objectName[1].name.equals("ManagedElement")||!objectName[2].name.equals("PGP")
//				||null == userLabel||"".equals(userLabel)) {
//			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"参数错误");
//		}
		NameAndStringValue_T[] objectName = new NameAndStringValue_T[3];
		
		objectName[0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
		objectName[1] = new NameAndStringValue_T("ManagedElement","1");
//		objectName[2] = new NameAndStringValue_T("PGP",EProtectionType.MSPPROTECT.getValue()+"/1");
//		System.out.println("msp测试修改");
//		objectName[2] = new NameAndStringValue_T("PGP",EProtectionType.TUNNEL.getValue()+"/18");
//		System.out.println("tunnel测试修改");
//		objectName[2] = new NameAndStringValue_T("PGP",EProtectionType.MSPPROTECT.getValue()+"/1");
		objectName[2] = new NameAndStringValue_T("PGP",EProtectionType.LOOPPROTECT.getValue()+"/1");
		System.out.println("loop测试修改");
		try {
			mgr_I.setUserLabel(objectName, "LOOPPROTECT/11", false);
		} catch (ProcessingFailureException e) {
//			 TODO Auto-generated catch block
			ExceptionManage.dispose(e,CorbaTestT.class);
		}
		
	}
	
	/**
	 * 人工保护倒换测试
	 */
	private static void protectionCommand(CorbaConnect connect) throws ProcessingFailureException{
		ProtectionMgr_I mgr_I = null;
		try {
			System.out.println("初始化 EMS 管理器!");
			Common_IHolder mgrHolder = new Common_IHolder();
			connect.emsSession.getManager("Protection", mgrHolder);
			mgr_I = ProtectionMgr_IHelper.narrow(mgrHolder.value);
			System.out.println("EMS_Manager To String ---" + mgr_I.toString());
		} catch (ProcessingFailureException pfe) {
			System.out
					.println("初始化 EMS 管理器异常!---ProcessingFailureException---");
			System.out.println(pfe.toString());
		}
		
		NameAndStringValue_T[] reliableSinkCtpOrGroupName = new NameAndStringValue_T[3];
		
		reliableSinkCtpOrGroupName[0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
		reliableSinkCtpOrGroupName[1] = new NameAndStringValue_T("ManagedElement","1");
//		reliableSinkCtpOrGroupName[2] = new NameAndStringValue_T("PGP",EProtectionType.MSPPROTECT.getValue()+"/1");
		reliableSinkCtpOrGroupName[2] = new NameAndStringValue_T("PGP",EProtectionType.TUNNEL.getValue()+"/5");

		
		NameAndStringValue_T[] toTp = new NameAndStringValue_T[3];
		toTp[0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
		toTp[1] = new NameAndStringValue_T("ManagedElement", "1");
		toTp[2] = new NameAndStringValue_T("PTP", "26");//msp
		
		
		SwitchData_THolder switchData = new SwitchData_THolder();
		switchData.value = new SwitchData_T();
		try {
			mgr_I.performProtectionCommand(ProtectionCommand_T.from_int(2),reliableSinkCtpOrGroupName, toTp, toTp,switchData);
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,CorbaTestT.class);
		}
		
	}
	
	/**
	 * 查询和修改时间测试
	 */
	private static void setandgetTimeTest(CorbaConnect connect) throws ProcessingFailureException{
		TimeMgr_I mgr_I = null;
		try {System.out.println(31);
			System.out.println("初始化 EMS 管理器!");
			Common_IHolder mgrHolder = new Common_IHolder();
			connect.emsSession.getManager("Time", mgrHolder);
			mgr_I = TimeMgr_IHelper.narrow(mgrHolder.value);
			System.out.println("EMS_Manager To String ---" + mgr_I.toString());
		} catch (ProcessingFailureException pfe) {
			System.out
					.println("初始化 EMS 管理器异常!---ProcessingFailureException---");
			System.out.println(pfe.toString());
		}
		NameAndStringValue_T[] emsName = new NameAndStringValue_T[1];
		
		emsName[0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());

		StringHolder emsTime = new StringHolder();
		String time = "2015-06-07 11:21:41";
		try {
//			mgr_I.getEMSTime(emsName,emsTime);
			mgr_I.setEMSTime(emsName, time);
			System.out.println(emsTime.value);
		} catch (ProcessingFailureException e) {
//			 TODO Auto-generated catch block
			ExceptionManage.dispose(e,CorbaTestT.class);
		}
		
	}
	
	/**
	 * 修改用户口令测试
	 */
	private static void modifyPassword(CorbaConnect connect) throws ProcessingFailureException{
		SecurityMgr_I mgr_I = null;
		try {System.out.println(31);
		System.out.println("初始化 EMS 管理器!");
		Common_IHolder mgrHolder = new Common_IHolder();
		connect.emsSession.getManager("Security", mgrHolder);
		mgr_I = SecurityMgr_IHelper.narrow(mgrHolder.value);
		System.out.println("EMS_Manager To String ---" + mgr_I.toString());
		mgr_I.modifyPassword("admin", "1", "2", "2");
	} catch (ProcessingFailureException pfe) {
		System.out
				.println("初始化 EMS 管理器异常!---ProcessingFailureException---");
		System.out.println(pfe.toString());
	}
	
	
	}
	
	public static void delTNP(CorbaConnect connect) throws ProcessingFailureException {
		Common_IHolder mgrHolder = new Common_IHolder();
		connect.emsSession.getManager("TrailNtwProtection", mgrHolder);
		TrailNtwProtMgr_I trailNtwProtMgr_Impl = TrailNtwProtMgr_IHelper.narrow(mgrHolder.value);

		NameAndStringValue_T[] name = new NameAndStringValue_T[3];
		StringHolder e = new StringHolder();
		name[0] = new NameAndStringValue_T("EMS", "DATAX");
		name[1] = new NameAndStringValue_T("MultiLayerSubnetwork", "");
		name[2] = new NameAndStringValue_T("TNP", ELayerRate.TNP.getValue()+"/32");
		trailNtwProtMgr_Impl.deleteTrailNtwProtection(name, e);
		int as = 1;
		as= 3;
	}
	
	public static void rotateTNP(CorbaConnect connect) throws ProcessingFailureException {
		Common_IHolder mgrHolder = new Common_IHolder();
		connect.emsSession.getManager("TrailNtwProtection", mgrHolder);
		TrailNtwProtMgr_I trailNtwProtMgr_Impl = TrailNtwProtMgr_IHelper.narrow(mgrHolder.value);

		NameAndStringValue_T[] name = new NameAndStringValue_T[3];
		StringHolder e = new StringHolder();
		name[0] = new NameAndStringValue_T("EMS", "DATAX");
		name[1] = new NameAndStringValue_T("MultiLayerSubnetwork", "");
		name[2] = new NameAndStringValue_T("TNP", ELayerRate.TNP.getValue()+"/25");
		TNPSwitchData_THolder switchDate = new TNPSwitchData_THolder();
		trailNtwProtMgr_Impl.performTNPCommand(ProtectionCommand_T.PC_MANUAL_SWITCH, name, ESwitchDirection.PROTECT.toString(), new NameAndStringValue_T[0],
				new NameAndStringValue_T[0] ,
				new NameAndStringValue_T[0][0][0] ,
				new NameAndStringValue_T[0][0] , switchDate);
		int as = 1;
		as= 3;
	}
}

