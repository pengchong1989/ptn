package com.nms.corba.test;

import maintenanceOps.MaintenanceMgr_I;
import maintenanceOps.MaintenanceMgr_IHelper;
import managedElementManager.ManagedElementMgr_I;
import multiLayerSubnetwork.MultiLayerSubnetworkMgr_I;
import multiLayerSubnetwork.MultiLayerSubnetworkMgr_IHelper;

import org.omg.CORBA.StringHolder;

import subnetworkConnection.CrossConnect_T;
import subnetworkConnection.RouteCreateData_T;
import subnetworkConnection.RouteDescriptor_T;
import subnetworkConnection.RouteDescriptor_THolder;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.corba.test.common.CorbaConnect;
import com.nms.ui.manager.ExceptionManage;
import common.Common_IHolder;

import emsMgr.EMSMgr_I;
import fileTransfer.FileTransferMgr_I;
import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

public class CorbaTestN {
	//网元管理者
	ManagedElementMgr_I meMgr = null;
    //ems管理者
	EMSMgr_I emsMgr = null;
	FileTransferMgr_I fileTransferMgr = null;
	private MaintenanceMgr_I oamMgrI;
	private MaintenanceMgr_I meMgrr = null;
	
	public static void main(String[] args) {
		try {
			CorbaConnect connect = new CorbaConnect();
 			if(connect.isConnect()){
 				/****     5.1.10   ****/
				//查询保护组
//				getProtectGroup(connect);//yes
//				人工保护倒换
//				protectionCommand(connect);//tunnel yes,msp yes
				
 				/****     5.1.14   ****/
				//查询tnp保护组信息
//				getTNP(connect);//yes
 				//生成tnp**************************************************************************
//				createTNP(connect);
				//删除tnp yes
//				deleteTNP(connect);
 				//tnp人工倒换  yes
//				performTNPCommand(connect);
				
 				/****     5.1.16   ****/
 				// 查询网元时钟源 yes
//				clockSourceTest(connect);
				
				/****     5.1.18   ****/
				//增添路由信息 yes 
//				addRoute(connect);
 				//删除路由信息 yes
//				removeRoute(connect);
				
				CorbaTestN corbaTestN = new CorbaTestN();
//				corbaTestN.setAdditionalInfo();//yes
				corbaTestN.setMEGLB();
//				corbaTestN.getOam();
				
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,CorbaTestN.class);
		}
	}
	
	public CorbaTestN() {
		try {
			// 获取管理者
			Common_IHolder mgrHolder = new Common_IHolder();
			// 不同的接口管理名称不同
			CorbaConnect.emsSession.getManager("Maintenance", mgrHolder);
			meMgrr = MaintenanceMgr_IHelper.narrow(mgrHolder.value);

		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
		
//		try {
//			Common_IHolder common = new Common_IHolder();
//			CorbaConnect.emsSession.getManager("Maintenance", common);
//			oamMgrI = MaintenanceMgr_IHelper.narrow(common.value);
//		} catch (ProcessingFailureException e) {
//			ExceptionManage.dispose(e,this.getClass());
//		}

	}
	////////////////////////////////////////////////////////////////////////////////////////////YES!!//////////////
	/**
	 * 人工保护倒换测试
	 */
//	public static void protectionCommand(CorbaConnect connect) throws ProcessingFailureException{
//		ProtectionMgr_I mgr_I = null;
//		try {
//			System.out.println("初始化 EMS 管理器!");
//			Common_IHolder mgrHolder = new Common_IHolder();
//			connect.emsSession.getManager("Protection", mgrHolder);
//			mgr_I = ProtectionMgr_IHelper.narrow(mgrHolder.value);
//			System.out.println("EMS_Manager To String ---" + mgr_I.toString());
//		} catch (ProcessingFailureException pfe) {
//			System.out
//					.println("初始化 EMS 管理器异常!---ProcessingFailureException---");
//			System.out.println(pfe.toString());
//		}
//		
//		NameAndStringValue_T[] reliableSinkCtpOrGroupName = new NameAndStringValue_T[3];
//		
//		reliableSinkCtpOrGroupName[0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
//		reliableSinkCtpOrGroupName[1] = new NameAndStringValue_T("ManagedElement","1");
//		reliableSinkCtpOrGroupName[2] = new NameAndStringValue_T("PGP",EProtectionType.MSPPROTECT.getValue()+"/1");
////		reliableSinkCtpOrGroupName[2] = new NameAndStringValue_T("PGP",EProtectionType.TUNNEL.getValue()+"/21");
//
//		
//		NameAndStringValue_T[] toTp = new NameAndStringValue_T[3];
//		toTp[0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
//		toTp[1] = new NameAndStringValue_T("MultiLayerSubnetwork", "1");
////		toTp[2] = new NameAndStringValue_T("PTP", "20");//tunnel
//		toTp[2] = new NameAndStringValue_T("PTP", "75");//msp Protect Id
//		
//		SwitchData_THolder switchData = new SwitchData_THolder();
//		switchData.value = new SwitchData_T();
//		try {
//			mgr_I.performProtectionCommand(ProtectionCommand_T.from_int(3),reliableSinkCtpOrGroupName, toTp, toTp,switchData);
//			System.out.println("succes");
//		} catch (ProcessingFailureException e) {
//			ExceptionManage.dispose(e,this.getClass());
//		}
//		
//	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////YES!!//////////////
//	public static void deleteTNP(CorbaConnect connect) throws ProcessingFailureException{
//		
//		Common_IHolder mgrHolder = new Common_IHolder();
//		connect.emsSession.getManager("TrailNtwProtection", mgrHolder);
//		TrailNtwProtMgr_I trailNtwProtMgr_Impl = TrailNtwProtMgr_IHelper.narrow(mgrHolder.value);
//
//		NameAndStringValue_T[] name = new NameAndStringValue_T[3];
//		name[0] = new NameAndStringValue_T("EMS", "DATAX/1");
//		name[1] = new NameAndStringValue_T("MultiLayerSubnetwork", "1");
//		name[2] = new NameAndStringValue_T("TrailNtwProtection", ELayerRate.TNP.getValue()+"/29");
//		
//		StringHolder errorReason = new StringHolder();
//		trailNtwProtMgr_Impl.deleteTrailNtwProtection(name ,errorReason);
//	}

	
	///////////////////////////////////////////////////////////////////////////////////////////////////YES!!//////////////
	/**
	 * TNP人工保护倒换
	 */
//	public static void performTNPCommand(CorbaConnect connect) throws ProcessingFailureException{
//		Common_IHolder mgrHolder = new Common_IHolder();
//		connect.emsSession.getManager("TrailNtwProtection", mgrHolder);
//		TrailNtwProtMgr_I trailNtwProtMgr_Impl = TrailNtwProtMgr_IHelper.narrow(mgrHolder.value);
//
//		NameAndStringValue_T[] name = new NameAndStringValue_T[3];
//		name[0] = new NameAndStringValue_T("EMS", "DATAX/1");
//		name[1] = new NameAndStringValue_T("MultiLayerSubnetwork", "1");
//		name[2] = new NameAndStringValue_T("TrailNtwProtection", ELayerRate.TNP.getValue()+"/31");
//		
//		StringHolder errorReason = new StringHolder();
//		String switchDirection = ESwitchDirection.WORK.toString();
//
//		ProtectionCommand_T protectionCommand = ProtectionCommand_T.PC_MANUAL_SWITCH;
//
//		NameAndStringValue_T[] protectionGroupAName = new NameAndStringValue_T[0];
//		NameAndStringValue_T[] protectionGroupZName = new NameAndStringValue_T[0];
//		NameAndStringValue_T[][][] workerTrailList = new NameAndStringValue_T[0][0][0];
//		NameAndStringValue_T[][] protectionTrail = new NameAndStringValue_T[0][0];
//		
//		TNPSwitchData_THolder tnpSwitchData = new TNPSwitchData_THolder();
//		
//		trailNtwProtMgr_Impl.performTNPCommand(protectionCommand,name, switchDirection,protectionGroupAName,protectionGroupZName,workerTrailList,protectionTrail,tnpSwitchData);
//	
//	}
	
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////YES!!//////////////
	/**
	 * 查询保护组
	 * @throws ProcessingFailureException 
	 */
//	public static void getProtectGroup(CorbaConnect connect) throws ProcessingFailureException{
//		//获取管理者
//		Common_IHolder mgrHolder = new Common_IHolder();
//		//不同接口管理者名称不同
//			connect.emsSession.getManager("Protection", mgrHolder);
//			ProtectionMgr_I protectionMgr = ProtectionMgr_IHelper.narrow(mgrHolder.value);
//			ProtectionGroupList_THolder tnpList = new ProtectionGroupList_THolder();
//			ProtectionGroupIterator_IHolder tnpIt = new ProtectionGroupIterator_IHolder();
//			NameAndStringValue_T[] name = new NameAndStringValue_T[2];
//			name[0] = new NameAndStringValue_T();
//			name[0].name = "EMS";//子网标示符
//			name[0].value = CorbaConfig.getInstanse().getCorbaEmsName();
//			CorbaConfig.getInstanse().getEmsVendorName();
//			name[1] = new NameAndStringValue_T();
//			name[1].name = "ManagedElement";
//			name[1].value = "7";//网元id
//			protectionMgr.getAllProtectionGroups(name, 5, tnpList, tnpIt);
//	System.out.println("*************!!!!!!!!!!!!************");
//	System.out.println(CorbaConfig.getInstanse().getCorbaEmsName());
//			if (tnpList.value != null && tnpList.value.length > 0) {
//				for (int i = 0; i < tnpList.value.length; i++) {
//					System.out.println(tnpList.value[i].nativeEMSName);
//				}
//			}
//			if (tnpIt.value != null) {
//				while (tnpIt.value.next_n(2, tnpList)) {
//					for (int i = 0; i < tnpList.value.length; i++) {
//						System.out.println(tnpList.value[i].nativeEMSName);
//					}
//				}
//				tnpIt.value.destroy();
//			}
//	}
	
	///////////////////////////////////////////////////////////////////////////////////YES!!//////////////
//	public static void clockSourceTest(CorbaConnect connect) throws ProcessingFailureException{
//		
//		try {
//			//获取管理者
//			Common_IHolder mgrHolder = new Common_IHolder();
//			//不同接口管理者名称不同
//			connect.emsSession.getManager("ClockSource", mgrHolder);
//			ClockSource_I clockSource_Impl = ClockSource_IHelper.narrow(mgrHolder.value);
//			NameAndStringValue_T[] name = new NameAndStringValue_T[2];
////			NameAndStringValue_T[] name = new NameAndStringValue_T[3];
//			name[0] = new NameAndStringValue_T();
//			name[0].name = "EMS";//子网标示符
//			name[0].value = "DATAX/1";
//			name[1] = new NameAndStringValue_T();
//			name[1].name = "ManagedElement";//
//			name[1].value = "6";//网元id
//			name[2] = new NameAndStringValue_T();
////			name[2].name = "ClockSource";
////			name[2].value = ELayerRate.CLOCKSOURCE.getValue()+"/"+"1";//ELayerRate.CLOCKSOURCE.getValue()+"/"+
//			ClockSourceList_THolder a = new ClockSourceList_THolder();
//			clockSource_Impl.getMEClockSource(name, a);
//	
//			System.out.println(a.value.length);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			ExceptionManage.dispose(e,this.getClass());
//		}
//	}
	
	
	///////////////////////////////////////////////////////////////////////////////////YES!!//////////////
	public static void addRoute(CorbaConnect connect) throws ProcessingFailureException{
		Common_IHolder mgrHolder = new Common_IHolder();
		//不同接口管理者名称不同
		connect.emsSession.getManager("MultiLayerSubnetwork", mgrHolder);
		MultiLayerSubnetworkMgr_I multiLayerSubnetworkMgr_I = MultiLayerSubnetworkMgr_IHelper.narrow(mgrHolder.value);
		NameAndStringValue_T[] name = new NameAndStringValue_T[3];
		name[0] = new NameAndStringValue_T("EMS", "DATAX/1");
		name[1] = new NameAndStringValue_T("MultiLayerSubnetwork", "1");
		name[2] = new NameAndStringValue_T("SubnetworkConnection", ELayerRate.TUNNEL.getValue()+"/31");
		RouteCreateData_T routeCreateData = new RouteCreateData_T();
		routeCreateData.neTpInclusions = new NameAndStringValue_T[2][3];
		routeCreateData.neTpInclusions[0][0] = new NameAndStringValue_T("EMS","DATAX/1");
		routeCreateData.neTpInclusions[0][1] = new NameAndStringValue_T("ManagedElement","4");
		routeCreateData.neTpInclusions[0][2] = new NameAndStringValue_T("PTP","455");
		routeCreateData.neTpInclusions[1][0] = new NameAndStringValue_T("EMS","DATAX/1");
		routeCreateData.neTpInclusions[1][1] = new NameAndStringValue_T("ManagedElement","3");
		routeCreateData.neTpInclusions[1][2] = new NameAndStringValue_T("PTP","102");
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
	
	
    ////////////////////////////删除保护/////////////////////////////////////////////////YES!!!/////////////
//	public static void removeRoute(CorbaConnect connect) throws ProcessingFailureException{
//		Common_IHolder mgrHolder = new Common_IHolder();
//		//不同接口管理者名称不同
//		NVSList_THolder  NVSList_THolder = new NVSList_THolder();
//		NVSList_THolder.value =  new NameAndStringValue_T[1]; 
//		NVSList_THolder.value[0] = new NameAndStringValue_T("MultiLayerSubnetwork","MultiLayerSubnetwork");
//		connect.emsSession.getManager("MultiLayerSubnetwork", mgrHolder);
//		MultiLayerSubnetworkMgr_I multiLayerSubnetworkMgr_I = MultiLayerSubnetworkMgr_IHelper.narrow(mgrHolder.value);
//		NameAndStringValue_T[] name = new NameAndStringValue_T[3];
//		name[0] = new NameAndStringValue_T("EMS", "DATAX/1");
//		name[1] = new NameAndStringValue_T("MultiLayerSubnetwork", "1");
//		name[2] = new NameAndStringValue_T("TrailNtwProtection", ELayerRate.TNP.getValue()+"/31");
//		
//		multiLayerSubnetworkMgr_I.removeRoute(name, "30",  NVSList_THolder);
//		
//	}
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////YES!!!/////////////
//	public static void getTNP(CorbaConnect connect) throws ProcessingFailureException {
//		Common_IHolder mgrHolder = new Common_IHolder();
//		connect.emsSession.getManager("TrailNtwProtection", mgrHolder);
//		TrailNtwProtMgr_I trailNtwProtMgr_Impl = TrailNtwProtMgr_IHelper.narrow(mgrHolder.value);
//
//		NameAndStringValue_T[] name = new NameAndStringValue_T[3];
//		name[0] = new NameAndStringValue_T("EMS", "DATAX/1");
//		name[1] = new NameAndStringValue_T("MultiLayerSubnetwork", "1");
//		name[2] = new NameAndStringValue_T("TrailNtwProtection", ELayerRate.TNP.getValue()+"/29");
//		TrailNtwProtection_THolder trailNtwProtection_THolder = new TrailNtwProtection_THolder(); 
//		TrailNtwProtectionList_THolder  tnpList  = new TrailNtwProtectionList_THolder();
//		TrailNtwProtectionIterator_IHolder tnpIt= new TrailNtwProtectionIterator_IHolder();
//		trailNtwProtMgr_Impl.getAllTrailNtwProtections(name, 3, tnpList, tnpIt);
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
//		
//		trailNtwProtMgr_Impl.getTrailNtwProtection(name, trailNtwProtection_THolder);//查询具体某一个
//		System.out.println(trailNtwProtection_THolder.value.nativeEMSName);
//	}
	
	
	/////////////////////////////创建tnp//////////////////////////////////////////////YES!!!/////////////
//	public static void createTNP(CorbaConnect connect) throws ProcessingFailureException{
//		
//		Common_IHolder mgrHolder = new Common_IHolder();
//		connect.emsSession.getManager("TrailNtwProtection", mgrHolder);
//		TrailNtwProtMgr_I trailNtwProtMgr = (TrailNtwProtMgr_I) TrailNtwProtMgr_IHelper.narrow(mgrHolder.value);
//		TrailNtwProtCreateData_T trailNtwProtCreateData_T = new TrailNtwProtCreateData_T();
//		TrailNtwProtection_THolder trailNtwProtection_THolder = new TrailNtwProtection_THolder();
//		trailNtwProtCreateData_T.modifiableAttributes = new TrailNtwProtModifyData_T[2]; 
//		trailNtwProtection_THolder.value = new TrailNtwProtection_T();
//		//工作
//		trailNtwProtCreateData_T.modifiableAttributes[0] = new TrailNtwProtModifyData_T();
//		trailNtwProtCreateData_T.modifiableAttributes[0].nativeEMSName = "TUNNEL/700D_203-700D_202_20140527145015";
//		trailNtwProtCreateData_T.modifiableAttributes[0].pgATPList =  new NameAndStringValue_T[1][3]; 
//		trailNtwProtCreateData_T.modifiableAttributes[0].pgATPList[0][0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
//		trailNtwProtCreateData_T.modifiableAttributes[0].pgATPList[0][1] = new NameAndStringValue_T("ManagedElement","4");
//		trailNtwProtCreateData_T.modifiableAttributes[0].pgATPList[0][2] = new NameAndStringValue_T("PTP","455");
//		trailNtwProtCreateData_T.modifiableAttributes[0].pgZTPList =  new NameAndStringValue_T[1][3]; 
//		trailNtwProtCreateData_T.modifiableAttributes[0].pgZTPList[0][0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
//		trailNtwProtCreateData_T.modifiableAttributes[0].pgZTPList[0][1] = new NameAndStringValue_T("ManagedElement","3");
//		trailNtwProtCreateData_T.modifiableAttributes[0].pgZTPList[0][2] = new NameAndStringValue_T("PTP","102");
//		
//		trailNtwProtCreateData_T.modifiableAttributes[0].route = new CrossConnect_T[0];
//		/*trailNtwProtCreateData_T.modifiableAttributes[0].crossConnects[0][0] = new CrossConnect_T();
//		trailNtwProtCreateData_T.modifiableAttributes[0].crossConnects[0][0].aEndNameList = new NameAndStringValue_T[1][3];
//		trailNtwProtCreateData_T.modifiableAttributes[0].crossConnects[0][0].zEndNameList = new NameAndStringValue_T[1][3];
//		trailNtwProtCreateData_T.modifiableAttributes[0].crossConnects[0][0].zEndNameList[0][0] = new NameAndStringValue_T("EMS","DataX/1");
//		trailNtwProtCreateData_T.modifiableAttributes[0].crossConnects[0][0].zEndNameList[0][1] = new NameAndStringValue_T("ManagedElement","1");
//		trailNtwProtCreateData_T.modifiableAttributes[0].crossConnects[0][0].zEndNameList[0][2] = new NameAndStringValue_T("PTP","30");
//		trailNtwProtCreateData_T.modifiableAttributes[0].crossConnects[0][0].aEndNameList[0][0] = new NameAndStringValue_T("EMS","DataX/1");
//		trailNtwProtCreateData_T.modifiableAttributes[0].crossConnects[0][0].aEndNameList[0][1] = new NameAndStringValue_T("ManagedElement","1");
//		trailNtwProtCreateData_T.modifiableAttributes[0].crossConnects[0][0].aEndNameList[0][2] = new NameAndStringValue_T("PTP","29");*/
//		
//		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo = new NameAndStringValue_T[22]; 
//		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[0] = new NameAndStringValue_T("isActive", "1");
//		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[1] = new NameAndStringValue_T("CreateTime", "2014-06-12 10:02:09");
//		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[2] = new NameAndStringValue_T("createuser", "admin");
//		
//		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[3] = new NameAndStringValue_T("qosName", "");
//		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[4] = new NameAndStringValue_T("qosType", "LLSP");
//		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[5] = new NameAndStringValue_T("qosCos", "1");
//		
//		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[6] = new NameAndStringValue_T("A2ZCIR", "0");
//		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[7] = new NameAndStringValue_T("A2ZPIR", "0");
//		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[8] = new NameAndStringValue_T("A2ZCBS", "-1");
//		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[9] = new NameAndStringValue_T("A2ZPBS", "0");
//		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[10] = new NameAndStringValue_T("A2ZEBS", "-1");
//		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[11] = new NameAndStringValue_T("A2ZEIR", "0");
//		
//		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[12] = new NameAndStringValue_T("Z2ACIR", "0");
//		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[13] = new NameAndStringValue_T("Z2APIR", "0");
//		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[14] = new NameAndStringValue_T("Z2ACBS", "-1");
//		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[15] = new NameAndStringValue_T("Z2APBS", "0");
//		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[16] = new NameAndStringValue_T("Z2AEBS", "-1");
//		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[17] = new NameAndStringValue_T("Z2AEIR", "0");
//		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[18] = new NameAndStringValue_T("SrcInLabel", "27");
//		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[19] = new NameAndStringValue_T("SrcOutLabel", "28");
//		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[20] = new NameAndStringValue_T("DestInLabel", "28");
//		trailNtwProtCreateData_T.modifiableAttributes[0].additionalInfo[21] = new NameAndStringValue_T("DestOutLabel", "27");
//		
//		
//	/*	trailNtwProtCreateData_T.modifiableAttributes[0].crossConnects[0][0].additionalInfo = new NameAndStringValue_T[4];
//		trailNtwProtCreateData_T.modifiableAttributes[0].crossConnects[0][0].additionalInfo[0] = new NameAndStringValue_T("SrcInLabel", "67217");
//		trailNtwProtCreateData_T.modifiableAttributes[0].crossConnects[0][0].additionalInfo[1] = new NameAndStringValue_T("SrcOutLabel", "67218");
//		trailNtwProtCreateData_T.modifiableAttributes[0].crossConnects[0][0].additionalInfo[2] = new NameAndStringValue_T("DestInLabel", "67219");
//		trailNtwProtCreateData_T.modifiableAttributes[0].crossConnects[0][0].additionalInfo[3] = new NameAndStringValue_T("DestOutLabel", "67220");*/
//		
//		
//		//保护
//		trailNtwProtCreateData_T.modifiableAttributes[1] = new TrailNtwProtModifyData_T();
//		trailNtwProtCreateData_T.modifiableAttributes[1].route = new CrossConnect_T[1];
//		trailNtwProtCreateData_T.modifiableAttributes[1].route[0] = new CrossConnect_T();
//		trailNtwProtCreateData_T.modifiableAttributes[1] = new TrailNtwProtModifyData_T();
//		trailNtwProtCreateData_T.modifiableAttributes[1].pgATPList =  new NameAndStringValue_T[1][3]; 
//		trailNtwProtCreateData_T.modifiableAttributes[1].pgATPList[0][0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
//		trailNtwProtCreateData_T.modifiableAttributes[1].pgATPList[0][1] = new NameAndStringValue_T("ManagedElement","4");
//		trailNtwProtCreateData_T.modifiableAttributes[1].pgATPList[0][2] = new NameAndStringValue_T("PTP","455");
//		trailNtwProtCreateData_T.modifiableAttributes[1].pgZTPList =  new NameAndStringValue_T[1][3]; 
//		trailNtwProtCreateData_T.modifiableAttributes[1].pgZTPList[0][0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
//		trailNtwProtCreateData_T.modifiableAttributes[1].pgZTPList[0][1] = new NameAndStringValue_T("ManagedElement","3");
//		trailNtwProtCreateData_T.modifiableAttributes[1].pgZTPList[0][2] = new NameAndStringValue_T("PTP","102");
//		
//		trailNtwProtCreateData_T.modifiableAttributes[1].route = new CrossConnect_T[0];
//		/*trailNtwProtCreateData_T.modifiableAttributes[1].crossConnects[0][0] = new CrossConnect_T();
//		trailNtwProtCreateData_T.modifiableAttributes[1].crossConnects[0][0].aEndNameList = new NameAndStringValue_T[1][3];
//		trailNtwProtCreateData_T.modifiableAttributes[1].crossConnects[0][0].zEndNameList = new NameAndStringValue_T[1][3];
//		trailNtwProtCreateData_T.modifiableAttributes[1].crossConnects[0][0].zEndNameList[0][0] = new NameAndStringValue_T("EMS","DataX/1");
//		trailNtwProtCreateData_T.modifiableAttributes[1].crossConnects[0][0].zEndNameList[0][1] = new NameAndStringValue_T("ManagedElement","1");
//		trailNtwProtCreateData_T.modifiableAttributes[1].crossConnects[0][0].zEndNameList[0][2] = new NameAndStringValue_T("PTP","30");
//		trailNtwProtCreateData_T.modifiableAttributes[1].crossConnects[0][0].aEndNameList[0][0] = new NameAndStringValue_T("EMS","DataX/1");
//		trailNtwProtCreateData_T.modifiableAttributes[1].crossConnects[0][0].aEndNameList[0][1] = new NameAndStringValue_T("ManagedElement","1");
//		trailNtwProtCreateData_T.modifiableAttributes[1].crossConnects[0][0].aEndNameList[0][2] = new NameAndStringValue_T("PTP","29");*/
//		
//		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo = new NameAndStringValue_T[22]; 
//		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[0] = new NameAndStringValue_T("isActive", "1");
//		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[1] = new NameAndStringValue_T("CreateTime", "2014-06-12 10:02:09");
//		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[2] = new NameAndStringValue_T("createuser", "admin");
//		
//		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[3] = new NameAndStringValue_T("qosName", "");
//		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[4] = new NameAndStringValue_T("qosType", "LLSP");
//		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[5] = new NameAndStringValue_T("qosCos", "1");
//		
//		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[6] = new NameAndStringValue_T("A2ZCIR", "0");
//		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[7] = new NameAndStringValue_T("A2ZPIR", "0");
//		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[8] = new NameAndStringValue_T("A2ZCBS", "-1");
//		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[9] = new NameAndStringValue_T("A2ZPBS", "0");
//		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[10] = new NameAndStringValue_T("A2ZEBS", "-1");
//		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[11] = new NameAndStringValue_T("A2ZEIR", "0");
//		trailNtwProtCreateData_T.rate = (short) ELayerRate.TNP.getValue();
//		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[12] = new NameAndStringValue_T("Z2ACIR", "0");
//		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[13] = new NameAndStringValue_T("Z2APIR", "0");
//		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[14] = new NameAndStringValue_T("Z2ACBS", "-1");
//		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[15] = new NameAndStringValue_T("Z2APBS", "0");
//		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[16] = new NameAndStringValue_T("Z2AEBS", "-1");
//		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[17] = new NameAndStringValue_T("Z2AEIR", "0");
//		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[18] = new NameAndStringValue_T("SrcInLabel", "30");
//		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[19] = new NameAndStringValue_T("SrcOutLabel", "31");
//		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[20] = new NameAndStringValue_T("DestInLabel", "31");
//		trailNtwProtCreateData_T.modifiableAttributes[1].additionalInfo[21] = new NameAndStringValue_T("DestOutLabel", "30");
//		
//	/*	trailNtwProtCreateData_T.modifiableAttributes[1].crossConnects[0][0].additionalInfo = new NameAndStringValue_T[4];
//		trailNtwProtCreateData_T.modifiableAttributes[1].crossConnects[0][0].additionalInfo[0] = new NameAndStringValue_T("SrcInLabel", "67210");
//		trailNtwProtCreateData_T.modifiableAttributes[1].crossConnects[0][0].additionalInfo[1] = new NameAndStringValue_T("SrcOutLabel", "67211");
//		trailNtwProtCreateData_T.modifiableAttributes[1].crossConnects[0][0].additionalInfo[2] = new NameAndStringValue_T("DestInLabel", "67215");
//		trailNtwProtCreateData_T.modifiableAttributes[1].crossConnects[0][0].additionalInfo[3] = new NameAndStringValue_T("DestOutLabel", "67216");*/
//		StringHolder errorReason = new StringHolder();
//		
//		//EMS不需要字段
//		/*trailNtwProtCreateData_T.modifiableAttributes[0].crossConnects[0][0].direction = ConnectionDirection_T.CD_BI;
//		trailNtwProtCreateData_T.modifiableAttributes[1].crossConnects[0][0].direction = ConnectionDirection_T.CD_BI;
//		trailNtwProtCreateData_T.modifiableAttributes[1].crossConnects[0][0].ccType = SNCType_T.ST_ADD_DROP_A;
//		trailNtwProtCreateData_T.modifiableAttributes[0].crossConnects[0][0].ccType = SNCType_T.ST_ADD_DROP_A;*/
//		trailNtwProtCreateData_T.protectionGroupAName = new NameAndStringValue_T[0];
//		trailNtwProtCreateData_T.protectionGroupZName = new NameAndStringValue_T[0];
//		trailNtwProtCreateData_T.modifiableAttributes[0].reversionMode = ReversionMode_T.RM_NON_REVERTIVE;
//		trailNtwProtCreateData_T.modifiableAttributes[1].reversionMode = ReversionMode_T.RM_NON_REVERTIVE;
//		trailNtwProtCreateData_T.modifiableAttributes[0].tnpParameters = new NameAndStringValue_T[2];
//		trailNtwProtCreateData_T.modifiableAttributes[0].tnpParameters[0] = new NameAndStringValue_T("WaitTime", "7");
//		trailNtwProtCreateData_T.modifiableAttributes[0].tnpParameters[1] = new NameAndStringValue_T("DelayTime", "750");
//		trailNtwProtCreateData_T.modifiableAttributes[1].tnpParameters = new NameAndStringValue_T[0];
//		trailNtwProtCreateData_T.workerTrailList = new NameAndStringValue_T[0][0][0];
//		trailNtwProtCreateData_T.protectionTrail = new NameAndStringValue_T[0][0];
//		
//		trailNtwProtMgr.createTrailNtwProtection(trailNtwProtCreateData_T, trailNtwProtection_THolder, errorReason);
//	}
	
	/**
	 * function:修改 使能/禁止CC联通性功能，含段，LSP，PW等
	 * 
	 */
    ////////////////////////////////////////////////////////////////////////////YES!!!/////////////
//	public void setAdditionalInfo() throws Exception{
//		
//		NameAndStringValue_T[] objectName = new NameAndStringValue_T[3];
//		objectName[0] = new NameAndStringValue_T("EMS", 
//				(CorbaConfig.getInstanse().getCorbaEmsName()));//德信厂商
//		objectName[1] = new NameAndStringValue_T("ManagedElement", "1");//网元主键id
//		objectName[2] = new NameAndStringValue_T("MEG", "2/1");//主键id
//		NVSList_THolder lbResult = new NVSList_THolder();
//		lbResult.value = new NameAndStringValue_T[3];
//		lbResult.value[0]  = new NameAndStringValue_T("TYPE", "0");
//		lbResult.value[1]  = new NameAndStringValue_T("CV", "2");
//		lbResult.value[2]  = new NameAndStringValue_T("CVCLE", "2");
//		
////		lbResult.value = new NameAndStringValue_T[2];
////		lbResult.value[0]  = new NameAndStringValue_T("TYPE", "1");
////		lbResult.value[1]  = new NameAndStringValue_T("LCK", "2");
//		meMgrr.setAdditionalInfo(objectName, lbResult);
//		int a =  1;
//	}
	
	/**
	 * 启动LB功能
	 * @throws ProcessingFailureException 
	 */
	////////////////////////////////////////////////////////////////////////////YES!!!/////////////
	public void setMEGLB() throws ProcessingFailureException{
		NameAndStringValue_T[] megName = new NameAndStringValue_T[3];
		megName[0] = new NameAndStringValue_T("EMS", 
				(CorbaConfig.getInstanse().getCorbaEmsName()));//德信厂商
		megName[1] = new NameAndStringValue_T("ManagedElement", "7");//网元主键id
		megName[2] = new NameAndStringValue_T("MEG", "9/42");//主键id 类型/id  oam的各种类型EOAMType

		int enableLB = 1;
		NameAndStringValue_T[] additionalInfo = new NameAndStringValue_T[5];
		additionalInfo[0] = new NameAndStringValue_T("RingCycle", "1s");//环回周期 
		//离线TLV类型  伪随机码/全0  pseudoRandomCode/allZero
		additionalInfo[1] = new NameAndStringValue_T("OffLineTestTLV", "pseudoRandomCode");
		additionalInfo[2] = new NameAndStringValue_T("RingTestWay", "onLine");//环回方式  在线/离线 onLine/offLine
		additionalInfo[3] = new NameAndStringValue_T("RingTLVLength", "80");//环回TLV长度  
		additionalInfo[4] = new NameAndStringValue_T("RingTLVInfo", "21");//环回TLV测试内容
		NVSList_THolder lbResult = new NVSList_THolder();
		meMgrr.setMEGLB(megName, new NameAndStringValue_T[]{}, 1, enableLB, additionalInfo, lbResult);
		int a = 1;
	}
	
	/**
	 * 
	 * 查询一条OAM维护实体，含段，LSP，PW等
	 */
	////////////////////////////////////////////////////////////////////////////YES!!!/////////////
//	public void getOam() throws Exception {
//
//		try {
//			NameAndStringValue_T[] selectOamValue = new NameAndStringValue_T[3];
//			// megCreateData_T.megType = 0 表示“段层OAM” 1:表示 “tunnel层OAM”
//			// 2:表示“PW层OAM”
//			selectOamValue[0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
//			// 网元ID
//			selectOamValue[1] = new NameAndStringValue_T("ManagedElement", "3");
//			// objId (段中A端口主键ID） (tunnel/PW的业务的业务ID)
//			selectOamValue[2] = new NameAndStringValue_T("MEG", "9/36");
//			MEG_THolder paramMEG_THolder = new MEG_THolder();
//			// 测试接口
//			meMgrr.getMEG(selectOamValue, paramMEG_THolder);
//
//			if (null != paramMEG_THolder) {
//				for (NameAndStringValue_T oamValue : paramMEG_THolder.value.additionalInfo) {
//					System.out.println(oamValue.value);
//				}
//			}
//		} catch (ProcessingFailureException e) {
//			ExceptionManage.dispose(e,this.getClass());
//			throw e;
//		}
//	}
}
