package com.nms.corba.test.managedElementMgr;

import globaldefs.NamingAttributesIterator_IHolder;
import globaldefs.NamingAttributesList_THolder;
import managedElementManager.ManagedElementMgr_I;
import managedElementManager.ManagedElementMgr_IHelper;

import com.nms.corba.test.common.CorbaConnect;
import com.nms.ui.manager.ExceptionManage;
import common.Common_IHolder;


public class QuerySiteName {
	public static void main(String[] args) {
		CorbaConnect corbaConnect = new CorbaConnect();
		corbaConnect.isConnect();
		getAllSiteName(corbaConnect);
	}
	
	private static void getAllSiteName(CorbaConnect corbaConnect){
		NamingAttributesList_THolder nameList = new NamingAttributesList_THolder();
		NamingAttributesIterator_IHolder nameIt = new NamingAttributesIterator_IHolder();
		//获取管理者
		Common_IHolder mgrHolder = new Common_IHolder();
		//不同接口管理者名称不同
		try {
			corbaConnect.emsSession.getManager("ManagedElement", mgrHolder);
			ManagedElementMgr_I managedElementMgr_I = ManagedElementMgr_IHelper.narrow(mgrHolder.value);
			managedElementMgr_I.getAllManagedElementNames(2, nameList, nameIt);
			if(nameList.value != null && nameList.value.length>0){//打印返回信息
				for (int i = 0; i < nameList.value.length; i++) {
					System.out.println(nameList.value[i][1].value);
				}
			}
			if(nameIt.value != null ){//遍历迭代器
				while(nameIt.value.next_n(1, nameList)){
					for (int i = 0; i < nameList.value.length; i++) {
						System.out.println(nameList.value[i][1].value);
					}
				}
				//遍历最后一次(next_n为false)
				for (int i = 0; i < nameList.value.length; i++) {
					System.out.println(nameList.value[i][1].value);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,QuerySiteName.class);
		}
		
	}
}
