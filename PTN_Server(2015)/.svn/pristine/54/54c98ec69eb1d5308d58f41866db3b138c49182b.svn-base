package com.nms.corba.test.SubnetworkConnection;

import managedElementManager.ManagedElementMgr_I;
import multiLayerSubnetwork.MultiLayerSubnetworkMgr_I;
import multiLayerSubnetwork.MultiLayerSubnetworkMgr_IHelper;

import org.omg.CORBA.StringHolder;

import subnetworkConnection.CrossConnect_T;
import subnetworkConnection.RouteCreateData_T;
import subnetworkConnection.RouteDescriptor_T;
import subnetworkConnection.RouteDescriptor_THolder;

import com.nms.corba.test.common.CorbaConnect;
import com.nms.ui.manager.ExceptionManage;
import common.Common_IHolder;

import emsMgr.EMSMgr_I;
import fileTransfer.FileTransferMgr_I;
import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;
/**
 * 路由编辑测试类
 * @author dzy
 *
 */
public class SubnetworkConnectionTest {
	//网元管理者
	ManagedElementMgr_I meMgr = null;
    //ems管理者
	EMSMgr_I emsMgr = null;
	FileTransferMgr_I fileTransferMgr = null;
	
	public static void main(String[] args) {
		try {
			CorbaConnect connect = new CorbaConnect();
			if(connect.isConnect()){
				SubnetworkConnectionTest subnetworkConnectionTest = new SubnetworkConnectionTest();
				subnetworkConnectionTest.addRoute(connect);
//				subnetworkConnectionTest.removeRoute(connect);
			}
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,SubnetworkConnectionTest.class);
		}
	}

	public void addRoute(CorbaConnect connect) throws ProcessingFailureException{
		Common_IHolder mgrHolder = new Common_IHolder();
		//不同接口管理者名称不同
		connect.emsSession.getManager("MultiLayerSubnetwork", mgrHolder);
		MultiLayerSubnetworkMgr_I multiLayerSubnetworkMgr_I = MultiLayerSubnetworkMgr_IHelper.narrow(mgrHolder.value);
		NameAndStringValue_T[] name = new NameAndStringValue_T[3];
		name[0] = new NameAndStringValue_T("EMS", "DATAX/1");
		name[1] = new NameAndStringValue_T("MultiLayerSubnetwork", "1");
		name[2] = new NameAndStringValue_T("SubnetworkConnection", "51");
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
	
	public void removeRoute(CorbaConnect connect) throws ProcessingFailureException{
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
		name[2] = new NameAndStringValue_T("SubnetworkConnection", "51");
		multiLayerSubnetworkMgr_I.removeRoute(name, "50",  NVSList_THolder);
		
		int as = 1;
		as= 3;
	}
	
}
