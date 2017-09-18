package com.nms.corba.test.equipment;

import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;
import managedElement.ManagedElement_T;
import managedElement.ManagedElement_THolder;
import managedElementManager.ManagedElementMgr_I;
import managedElementManager.ManagedElementMgr_IHelper;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.test.common.CorbaConnect;
import common.Common_IHolder;

/**
 * @author guoqc
 * 测试  查询设备信息
 */
public class SiteTest { 
	private ManagedElementMgr_I equimentI;
	
	public SiteTest() throws ProcessingFailureException{
		Common_IHolder common = new Common_IHolder();
		CorbaConnect.emsSession.getManager("ManagedElement", common);
		equimentI = ManagedElementMgr_IHelper.narrow(common.value);
	}
	
	public static void main(String[] args) throws ProcessingFailureException {
		CorbaConnect c = new CorbaConnect();
		c.isConnect();
		SiteTest siteTest = new SiteTest();
		siteTest.getManagedElement();//查询指定设备信息
	}
	
	/**
	 * 查询指定设备信息
	 * @throws ProcessingFailureException 
	 */
	private void getManagedElement() throws ProcessingFailureException{
		NameAndStringValue_T[] equipmentOrHolderName = new NameAndStringValue_T[2];
		NameAndStringValue_T manufacture = new NameAndStringValue_T((CorbaConfig.getInstanse().getEmsType()), 
				(CorbaConfig.getInstanse().getCorbaEmsName()));//厂商信息
		NameAndStringValue_T siteId = new NameAndStringValue_T("siteId", "3");//网元Id
		equipmentOrHolderName[0] = manufacture;
		equipmentOrHolderName[1] = siteId;
		ManagedElement_THolder equip = new ManagedElement_THolder();
		equip.value = new ManagedElement_T();
		equimentI.getManagedElement(equipmentOrHolderName, equip);
		NameAndStringValue_T[] add = equip.value.additionalInfo;
		System.out.println("网元名称 : " + equip.value.nativeEMSName);
		for (int j = 0; j < add.length; j++) {
			if("cellType".equals(add[j].name)){
				System.out.print("网元类型 : ");//网元类型
			}else if("IP".equals(add[j].name)){
				System.out.print("ip : ");//ip
			}else if("discAlarm".equals(add[j].name)){
				System.out.print("网元告警状态 : ");//网元告警状态
			}else if("discHardware".equals(add[j].name)){
				System.out.print("单元盘硬件版本  : ");//单元盘硬件版本 
			}
			System.out.println(add[j].value);
		}
	}
}
