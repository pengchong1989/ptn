package com.nms.corba.test.clockSource;

import managedElementManager.ManagedElementMgr_I;
import clockSource.ClockSourceList_THolder;
import clockSource.ClockSource_I;
import clockSource.ClockSource_IHelper;
import clockSource.ClockSource_T;

import com.nms.corba.test.common.CorbaConnect;
import com.nms.ui.manager.ExceptionManage;
import common.Common_IHolder;

import emsMgr.EMSMgr_I;
import fileTransfer.FileTransferMgr_I;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

/**
 * 时钟源测试类
 * @author dzy
 *
 */
public class ClockSourceTest {

	//网元管理者
	ManagedElementMgr_I meMgr = null;
    //ems管理者
	EMSMgr_I emsMgr = null;
	FileTransferMgr_I fileTransferMgr = null;
	ClockSourceTest clockSourceTest = new ClockSourceTest();
	public static void main(String[] args) {
		try {
			CorbaConnect connect = new CorbaConnect();
			if(connect.isConnect()){
				ClockSourceTest clockSourceTest = new ClockSourceTest();
				clockSourceTest.clockSourceTest(connect);
			}
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,ClockSourceTest.class);
		}
	}

	
	public void clockSourceTest(CorbaConnect connect) throws ProcessingFailureException{
		
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
			ExceptionManage.dispose(e,this.getClass());
		}
	}
}
