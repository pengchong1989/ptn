package com.nms.corba.test.equipment;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.test.common.CorbaConnect;
import common.Common_IHolder;

import equipment.EquipmentInventoryMgr_I;
import equipment.EquipmentInventoryMgr_IHelper;
import equipment.EquipmentOrHolderIterator_IHolder;
import equipment.EquipmentOrHolderList_THolder;
import equipment.EquipmentOrHolder_T;
import equipment.EquipmentOrHolder_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.NamingAttributesIterator_IHolder;
import globaldefs.NamingAttributesList_THolder;
import globaldefs.ProcessingFailureException;

/**
 * @author guoqc
 * 测试  查询设备信息
 */
public class EquipmentTest { 
	private EquipmentInventoryMgr_I equimentI;
	
	public EquipmentTest() throws ProcessingFailureException{
		Common_IHolder common = new Common_IHolder();
		CorbaConnect.emsSession.getManager("EquipmentInventory", common);
		equimentI = EquipmentInventoryMgr_IHelper.narrow(common.value);
	}
	
	public static void main(String[] args) throws ProcessingFailureException {
		CorbaConnect c = new CorbaConnect();
		c.isConnect();
		EquipmentTest equipmentTest = new EquipmentTest();
		equipmentTest.getEquipment();//查询指定设备信息
//		equipmentTest.getAllEquipment();//查询所有设备信息
//		equipmentTest.getAllEquipmentNames();//查询所有设备名称
	}
	
	/**
	 * 查询指定设备信息
	 * @throws ProcessingFailureException 
	 */
	private void getEquipment() throws ProcessingFailureException{
		NameAndStringValue_T[] equipmentOrHolderName = new NameAndStringValue_T[4];
		equipmentOrHolderName[0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
		equipmentOrHolderName[1] = new NameAndStringValue_T("ManagedElement", "2");
		equipmentOrHolderName[2] = new NameAndStringValue_T("EquipmentHolder", "1");
		equipmentOrHolderName[3] = new NameAndStringValue_T("Equipment", "1");
		EquipmentOrHolder_THolder equip = new EquipmentOrHolder_THolder();
		equip.value = new EquipmentOrHolder_T();
		equimentI.getEquipment(equipmentOrHolderName, equip);
		equipmentOrHolderName = null;
	}
	
	/**
	 * 查询所有设备信息
	 * @throws ProcessingFailureException 
	 */
	public void getAllEquipment() throws ProcessingFailureException{
		NameAndStringValue_T[] equipmentOrHolderName = new NameAndStringValue_T[2];
		equipmentOrHolderName[0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
		equipmentOrHolderName[1] = new NameAndStringValue_T("ManagedElement", "1");
		
		EquipmentOrHolderList_THolder eqList = new EquipmentOrHolderList_THolder();
		EquipmentOrHolderIterator_IHolder eqIt = new EquipmentOrHolderIterator_IHolder();
		
		equimentI.getAllEquipment(equipmentOrHolderName, 2, eqList, eqIt);
		
		if (eqList.value != null && eqList.value.length > 0) {
			for (int i = 0; i < eqList.value.length; i++) {
				System.out.println(eqList.value[i].holder().nativeEMSName);
			}
		}
		if (eqIt.value != null) {
			while (eqIt.value.next_n(2, eqList)) {
				for (int i = 0; i < eqList.value.length; i++) {
					System.out.println(eqList.value[i].holder().nativeEMSName);
				}
			}
			eqIt.value.destroy();
		}
		
		EquipmentOrHolder_T[] addition = eqList.value;
		for (int i = 0; i < addition.length; i++) {
			System.out.println("网元名称: " + addition[i].equip().nativeEMSName);
			NameAndStringValue_T[] add = addition[i].equip().additionalInfo;
			for (int j = 0; j < add.length; j++) {
				if("discType".equals(add[j].name)){
					System.out.print("单元盘类型 : ");//单元盘类型
				}else if("discSoftware".equals(add[j].name)){
					System.out.print("单元盘软件版本 : ");//单元盘软件版本
				}else if("discSerialNumber".equals(add[j].name)){
					System.out.print("单元盘序列号 : ");//单元盘序列号
				}else if("discSlot".equals(add[j].name)){
					System.out.print("单元盘所属机槽 : ");//单元盘所属机槽
				}else if("discModel".equals(add[j].name)){
					System.out.print("单元盘型号 : ");//单元盘型号
				}else if("discIsProtected".equals(add[j].name)){
					System.out.print("是否有保护 : ");//是否有保护
				}else if("discProtectType".equals(add[j].name)){
					System.out.print("保护方式 : ");//保护方式
				}else if("discState".equals(add[j].name)){
					System.out.print("单元盘使用状态 : ");//单元盘使用状态
				}else if("discAlarm".equals(add[j].name)){
					System.out.print("单元盘告警状态 : ");//单元盘告警状态
				}else if("discHardware".equals(add[j].name)){
					System.out.print("单元盘硬件版本 : ");//单元盘硬件版本
				}
				System.out.println(add[j].value);
			}
		}
	}
	
	/**
	 * 查询所有设备名称
	 * @throws ProcessingFailureException 
	 */
	private void getAllEquipmentNames() throws ProcessingFailureException{
		NameAndStringValue_T[] equipmentOrHolderName = new NameAndStringValue_T[2];
		equipmentOrHolderName[0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
		equipmentOrHolderName[1] = new NameAndStringValue_T("ManagedElement", "1");
		NamingAttributesList_THolder nameList = new NamingAttributesList_THolder();
		nameList.value = new NameAndStringValue_T[][]{};
		NamingAttributesIterator_IHolder nameIt = new NamingAttributesIterator_IHolder();
		equimentI.getAllEquipmentNames(equipmentOrHolderName, 2, nameList, nameIt);
		if (nameList.value != null && nameList.value.length > 0) {
			for (int i = 0; i < nameList.value.length; i++) {
				System.out.println(nameList.value[i][0].value);
			}
		}
		if (nameIt.value != null) {
			while (nameIt.value.next_n(2, nameList)) {
				for (int i = 0; i < nameList.value.length; i++) {
					System.out.println(nameList.value[i][0].value);
				}
			}
			nameIt.value.destroy();
		}
		
	}
}
