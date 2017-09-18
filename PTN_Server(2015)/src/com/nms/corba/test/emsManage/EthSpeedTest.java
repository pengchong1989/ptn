package com.nms.corba.test.emsManage;

import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;
import managedElementManager.ManagedElementMgr_I;
import managedElementManager.ManagedElementMgr_IHelper;
import terminationPoint.TerminationPointIterator_IHolder;
import terminationPoint.TerminationPointList_THolder;

import com.nms.corba.test.common.CorbaConnect;
import com.nms.ui.manager.ExceptionManage;
import common.Common_IHolder;

public class EthSpeedTest {

	
	public static void main(String[] args) {
		CorbaConnect corbaConnect = new CorbaConnect();
		corbaConnect.isConnect();
		//以太网层速率参数"
		EthSpeedTest.getAllPTPs();
		
	}
	
	
	
	/**
	 * @author zk
	 * function:测试" 以太网层速率参数"
	 * 
	 */
  public static void getAllPTPs(){
	  
	NameAndStringValue_T[] elementName = new NameAndStringValue_T[2]; 
	elementName[0] = new NameAndStringValue_T("ManagedElementName", "DATAX");
	elementName[1] = new NameAndStringValue_T("EthSpeed", "1");
	TerminationPointList_THolder tpList = new TerminationPointList_THolder();
	TerminationPointIterator_IHolder tpIt = new TerminationPointIterator_IHolder();
	Common_IHolder mgrHoldr = new Common_IHolder();
	short[] tpLayerRateList = {1}; 
    short[] connectionLayerRateList = {1};
	try {
		CorbaConnect.emsSession.getManager("ManagedElement", mgrHoldr);
		ManagedElementMgr_I meMgr = ManagedElementMgr_IHelper.narrow(mgrHoldr.value);
		//测试接口
		meMgr.getAllPTPs(elementName, tpLayerRateList,  connectionLayerRateList, 1,tpList, tpIt);
		
		if(tpList.value != null && tpList.value.length >0){
           for(int i = 0; i<tpList.value.length; i++){
        	   System.out.println(tpList.value[i].transmissionParams[0].transmissionParams[0].value+"-----");
           }
		}
		if(tpIt.value != null){
			while(tpIt.value.next_n(1, tpList)){
				 for(int i = 0; i<tpList.value.length; i++){
		        	   System.out.println(tpList.value[i].transmissionParams[0].transmissionParams[0].value+"-----Iitearor");
		           }
				 tpIt.value.destroy();
			}
		}
	} catch (ProcessingFailureException e) {
		ExceptionManage.dispose(e,EthSpeedTest.class);
	}
  }
}
