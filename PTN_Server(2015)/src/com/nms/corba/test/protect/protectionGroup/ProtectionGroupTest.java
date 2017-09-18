package com.nms.corba.test.protect.protectionGroup;

import managedElementManager.ManagedElementMgr_I;
import protection.ProtectionGroupIterator_IHolder;
import protection.ProtectionGroupList_THolder;
import protection.ProtectionMgr_I;
import protection.ProtectionMgr_IHelper;

import com.nms.corba.test.common.CorbaConnect;
import com.nms.ui.manager.ExceptionManage;
import common.Common_IHolder;

import emsMgr.EMSMgr_I;
import fileTransfer.FileTransferMgr_I;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

public class ProtectionGroupTest {

	//网元管理者
	ManagedElementMgr_I meMgr = null;
    //ems管理者
	EMSMgr_I emsMgr = null;
	FileTransferMgr_I fileTransferMgr = null;
	
	public static void main(String[] args) {
		try {
			CorbaConnect connect = new CorbaConnect();
			if(connect.isConnect()){
				ProtectionGroupTest protectionGroupTest = new ProtectionGroupTest();
				protectionGroupTest.getProtectGroup(connect);
			}
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,ProtectionGroupTest.class);
		}
	}

	/**
	 * 查询保护组
	 * @throws ProcessingFailureException 
	 */
	public void getProtectGroup(CorbaConnect connect) throws ProcessingFailureException{
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
		name[1].value = "4";
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
}
