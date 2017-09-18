package com.nms.corba.test.tunnnel;

import globaldefs.ConnectionDirection_T;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import managedElementManager.ManagedElementMgr_I;
import managedElementManager.ManagedElementMgr_IHelper;
import subnetworkConnection.CCIterator_IHolder;
import subnetworkConnection.CrossConnectList_THolder;
import subnetworkConnection.CrossConnect_T;
import subnetworkConnection.CrossConnect_THolder;
import subnetworkConnection.SNCType_T;

import com.nms.corba.test.common.CorbaConnect;
import com.nms.ui.manager.ExceptionManage;
import common.Common_IHolder;

public class TunnelOperation {
	public static void main(String[] args) {
		CorbaConnect corbaConnect = new CorbaConnect();
		corbaConnect.isConnect();
		getAllCrossConnections(corbaConnect);
//		crossConnections(corbaConnect,"active");
//		crossConnections(corbaConnect,"deActive");
//		crossConnections(corbaConnect,"delete");
//		createCrossConnections(corbaConnect);
		List<String> list = new ArrayList<String>();
		list.add("11");
		list.add("11");
		list.add("11");
		list.add("11");
		List l = new ArrayList();
		l = Arrays.asList("1","1");
		List list2 = l.subList(0, 1);
		System.out.println(l.get(0));
		try {
			System.out.println(111);
		} catch (Exception e) {
			ExceptionManage.dispose(e,TunnelOperation.class);
		}
		
	}
	
	/**
	 * 查询某网元所有tunnel
	 * @param corbaConnect
	 */
	private static void getAllCrossConnections(CorbaConnect corbaConnect){
		CrossConnectList_THolder crossConnectListTHolder = new CrossConnectList_THolder();
		CCIterator_IHolder ccIteratorIHolder = new CCIterator_IHolder();
		
		//厂商网元信息
		NameAndStringValue_T[] andStringValueTs = new NameAndStringValue_T[2];
		NameAndStringValue_T ems = new NameAndStringValue_T();
		ems.name = "EMS";
		ems.value = "DATAX/703B";
		andStringValueTs[0] = ems;
		NameAndStringValue_T siteId = new NameAndStringValue_T();
		siteId.name = "ManagedElement";
		siteId.value = "1";
		andStringValueTs[1] = siteId;
		
		//层速率
		short[] connectionRateList = {1};
		
		//获取管理者
		Common_IHolder mgrHolder = new Common_IHolder();
		try {
			//不同接口管理者名称不同
			corbaConnect.emsSession.getManager("ManagedElement", mgrHolder);
			ManagedElementMgr_I managedElementMgr_I = ManagedElementMgr_IHelper.narrow(mgrHolder.value);
			managedElementMgr_I.getAllCrossConnections(andStringValueTs, connectionRateList, 3, crossConnectListTHolder, ccIteratorIHolder);
			
			if(crossConnectListTHolder.value != null && crossConnectListTHolder.value.length>0){//打印返回信息
				System.out.println("查询到的"+crossConnectListTHolder.value.length);
			}
			if(ccIteratorIHolder.value != null ){//遍历迭代器
				while(ccIteratorIHolder.value.next_n(1, crossConnectListTHolder)){
					System.out.println("遍历到的"+crossConnectListTHolder.value.length);
				}
				//遍历最后一次(next_n为false)
				System.out.println("遍历到的最后一次"+crossConnectListTHolder.value.length);
			}
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,TunnelOperation.class);
		}
		
	}
	
	/**
	 * 操作某条tunnel
	 * @param corbaConnect
	 */
	private static void crossConnections(CorbaConnect corbaConnect,String type){
		//厂商网元信息
		NameAndStringValue_T[] nameAndStringValueTs = new NameAndStringValue_T[4];
		NameAndStringValue_T ems = new NameAndStringValue_T();
		ems.name = "EMS";
		ems.value = "DATAX/703B";
		nameAndStringValueTs[0] = ems;
		NameAndStringValue_T siteId = new NameAndStringValue_T();
		siteId.name = "ManagedElement";
		siteId.value = "1";
		nameAndStringValueTs[1] = siteId;
		NameAndStringValue_T connectionRate = new NameAndStringValue_T();
		connectionRate.name = "connectionRate";
		connectionRate.value = "1";
		nameAndStringValueTs[2] = connectionRate;
		NameAndStringValue_T tunnelId = new NameAndStringValue_T();
		tunnelId.name = "tunnelName";
		tunnelId.value = "TUNNEL/11_20140424152411";
		nameAndStringValueTs[3] = tunnelId;
		
		try {
			//获取管理者
			Common_IHolder mgrHolder = new Common_IHolder();
			//不同接口管理者名称不同
			corbaConnect.emsSession.getManager("ManagedElement", mgrHolder);
			ManagedElementMgr_I managedElementMgr_I = ManagedElementMgr_IHelper.narrow(mgrHolder.value);
			if("active".equals(type)){
				managedElementMgr_I.activateCrossConnections(nameAndStringValueTs);
			}else if("deActive".equals(type)){
				managedElementMgr_I.deactivateCrossConnections(nameAndStringValueTs);
			}else if("delete".equals(type)){
				managedElementMgr_I.deleteCrossConnections(nameAndStringValueTs);
			}
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,TunnelOperation.class);
		}
	}
	
	private static void createCrossConnections(CorbaConnect corbaConnect){
		//厂商网元信息
		NameAndStringValue_T[] nameAndStringValueTs = new NameAndStringValue_T[3];
		NameAndStringValue_T ems = new NameAndStringValue_T();
		ems.name = "EMS";
		ems.value = "DataX/703B";
		nameAndStringValueTs[0] = ems;
		NameAndStringValue_T siteId = new NameAndStringValue_T();
		siteId.name = "ManagedElement";
		siteId.value = "1";
		nameAndStringValueTs[1] = siteId;
		NameAndStringValue_T connectionRate = new NameAndStringValue_T();
		connectionRate.name = "connectionRate";
		connectionRate.value = "1";
		nameAndStringValueTs[2] = connectionRate;
		CrossConnect_T[] ccList = new CrossConnect_T[1];
		ccList[0] = new CrossConnect_T();
		ccList[0].active = true;
		ccList[0].direction = ConnectionDirection_T.CD_BI;
		ccList[0].aEndNameList = new NameAndStringValue_T[1][1];
		ccList[0].aEndNameList[0][0] = new NameAndStringValue_T();
		ccList[0].aEndNameList[0][0].name = "ManagedElement";
		ccList[0].aEndNameList[0][0].value = "1";
		ccList[0].zEndNameList = new NameAndStringValue_T[1][1];
		ccList[0].zEndNameList[0][0] = new NameAndStringValue_T();
		ccList[0].zEndNameList[0][0].name = "ManagedElement";
		ccList[0].zEndNameList[0][0].value = "1";
		ccList[0].additionalInfo = new NameAndStringValue_T[4];
		for (int i = 0; i < ccList[0].additionalInfo.length; i++) {
			ccList[0].additionalInfo[i] = new NameAndStringValue_T();
		}
		ccList[0].ccType = SNCType_T.ST_ADD_DROP_A;
		ccList[0].additionalInfo[0].value = "1";
		ccList[0].additionalInfo[1].value = "233";
		ccList[0].additionalInfo[3].value = "234";
		CrossConnect_THolder createCrossConnections = new CrossConnect_THolder();
		try {
			//获取管理者
			Common_IHolder mgrHolder = new Common_IHolder();
			//不同接口管理者名称不同
			corbaConnect.emsSession.getManager("ManagedElement", mgrHolder);
			ManagedElementMgr_I managedElementMgr_I = ManagedElementMgr_IHelper.narrow(mgrHolder.value);
//			managedElementMgr_I.createCrossConnections(nameAndStringValueTs,(short) 1, ccList[0], createCrossConnections);
		} catch (Exception e) {
			ExceptionManage.dispose(e,TunnelOperation.class);
		}
	}
}
