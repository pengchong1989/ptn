package com.nms.corba.test;

import globaldefs.ConnectionDirection_T;
import globaldefs.NameAndStringValue_T;
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

public class PWTest {
	
	private ManagedElementMgr_I mgr ;
	public PWTest(){
		try {
			System.out.println(1);
			Common_IHolder common = new Common_IHolder();
			CorbaConnect.emsSession.getManager("ManagedElement", common);
			mgr = ManagedElementMgr_IHelper.narrow(common.value);
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * function:根据网元查询pw
	 * 
	 */
	public void selectPW(){
		
		try {
			NameAndStringValue_T[] pwdata = new NameAndStringValue_T[2];
			pwdata[0] = new NameAndStringValue_T("ems", "DATAX/703B");
			pwdata[1] = new NameAndStringValue_T("siteId","1");
			short[] rateList = new short[1];
			rateList[0] = 2;
			CrossConnectList_THolder cclist = new CrossConnectList_THolder();
			CCIterator_IHolder ccIt = new CCIterator_IHolder();
			mgr.getAllCrossConnections(pwdata, rateList, 2, cclist, ccIt);
			
			if(cclist.value != null && cclist.value.length>0){
				for(int i = 0; i < cclist.value.length; i++){
					System.out.println(cclist.value[i].additionalInfo[0].value);
				}
			}
			if(ccIt.value != null){
				while(ccIt.value.next_n(1, cclist)){
				   for(int i = 0; i < cclist.value.length; i++){
					   System.out.println(cclist.value[i].additionalInfo[0].value);
				   }
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	
	public void craetePW(){
		
		try {
			NameAndStringValue_T[] pwData = new NameAndStringValue_T[3];
			
			pwData[0] = new NameAndStringValue_T("ems", "DATAX/703B");
			pwData[1] = new NameAndStringValue_T("siteID","1"); 
			pwData[2] = new NameAndStringValue_T("type","2");
			
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
			ccList[0].ccType = SNCType_T.ST_ADD_DROP_A;
			NameAndStringValue_T[] addInfo = new NameAndStringValue_T[4];
			addInfo[0] = new NameAndStringValue_T("inLabel", "222");//PW入标签
			addInfo[1] = new NameAndStringValue_T("outLabel", "222");//PW出标签
			addInfo[2] = new NameAndStringValue_T("tunnelId", "1");//LSP标签交换标识符
			addInfo[3] = new NameAndStringValue_T("epwType", "1");//LSP标签交换标识符
			ccList[0].additionalInfo = addInfo;
			//测试接口
			CrossConnect_THolder addSuccess = new CrossConnect_THolder();
//			mgr.createCrossConnections(pwData,(short) 2, ccList[0], addSuccess);
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	public static void main(String[] args) {
		CorbaConnect corBaConnect = new CorbaConnect();
		corBaConnect.isConnect();
		PWTest pwTest = new PWTest();
		//查询pw
		pwTest.selectPW();
		//创建pw
//		pwTest.craetePW();
	}
	
}