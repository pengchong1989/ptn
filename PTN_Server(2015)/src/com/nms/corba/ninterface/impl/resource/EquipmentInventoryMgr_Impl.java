package com.nms.corba.ninterface.impl.resource;

import org.apache.log4j.Logger;

import terminationPoint.TerminationPointIterator_IHolder;
import terminationPoint.TerminationPointList_THolder;

import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.impl.resource.proxy.EquipmentProxy;
import com.nms.corba.ninterface.util.AuthorityManager;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
import common.CapabilityList_THolder;

import equipment.EQTCreateData_T;
import equipment.EquipmentInventoryMgr_IPOA;
import equipment.EquipmentOrHolderIterator_IHolder;
import equipment.EquipmentOrHolderList_THolder;
import equipment.EquipmentOrHolder_THolder;
import equipment.Equipment_THolder;
import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.NamingAttributesIterator_IHolder;
import globaldefs.NamingAttributesList_THolder;
import globaldefs.ProcessingFailureException;

public class EquipmentInventoryMgr_Impl extends EquipmentInventoryMgr_IPOA {
	private Logger LOG = Logger.getLogger(EquipmentProxy.class.getName());
	private ICorbaSession userSession = null;

	public EquipmentInventoryMgr_Impl(ICorbaSession userSession) {
		this.userSession = userSession;
	}

	@Override
	public void provisionEquipment(EQTCreateData_T equipmentCreateData,
			Equipment_THolder createdEquipment)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void unprovisionEquipment(NameAndStringValue_T[] equipmentName)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAlarmReportingOn(NameAndStringValue_T[] equipmentOrHolderName)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAlarmReportingOff(
			NameAndStringValue_T[] equipmentOrHolderName)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getContainedEquipment(
			NameAndStringValue_T[] equipmentHolderName,
			EquipmentOrHolderList_THolder equipmentOrHolderList)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	/**
	 * 获取指定设备信息
	 * @param  equipmentOrHolderName	名值对，指定设备板卡ID号以及厂商信息
	 * @param  equip	查询的结果
	 */
	@Override
	public void getEquipment(NameAndStringValue_T[] equipmentOrHolderName,
				 EquipmentOrHolder_THolder equip) 
				 throws ProcessingFailureException {
		//验证用户权限 无权限抛异常	核心查看
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_SELECT);
		EquipmentProxy equment = new EquipmentProxy(this.userSession);
		equment.getEquipment(equipmentOrHolderName, equip);
	}

	/**
	 * 获取所有设备信息
	 * 入参  meOrHolderName 厂商信息/网元ID/板卡ID
	 * 入参  how_many 指定每次迭代的个数
	 * 出参  eqList 设备信息对象
	 * 出参  eqIt 迭代器
	 */
	@Override
	public void getAllEquipment(NameAndStringValue_T[] meOrHolderName, int how_many,
				 EquipmentOrHolderList_THolder eqList,
				 EquipmentOrHolderIterator_IHolder eqIt)
				 throws ProcessingFailureException {
		//验证用户权限 无权限抛异常	核心查看
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_SELECT);
		EquipmentProxy equment = new EquipmentProxy(this.userSession);
		equment.getAllEquipment(meOrHolderName, how_many, eqList, eqIt);
	}

	/**
	 * @author guoqc
	 * 获取网元或支架中包含的所有的子设备的板卡名称
	 * 入参  meOrHolderName 厂商信息
	 * 入参  how_many 指定每次迭代的个数
	 * 出参  nameList 设备板卡名称信息
	 * 出参  nameIt 迭代器
	 */
	@Override
	public void getAllEquipmentNames(NameAndStringValue_T[] meOrHolderName, int how_many,
					NamingAttributesList_THolder nameList,
					NamingAttributesIterator_IHolder nameIt)
					throws ProcessingFailureException {
		EquipmentProxy equment = new EquipmentProxy(this.userSession);
		try {
			equment.getAllEquipmentNames(meOrHolderName, how_many, nameList, nameIt);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

	@Override
	public void getAllSupportedPTPs(NameAndStringValue_T[] equipmentName,
			int how_many, TerminationPointList_THolder tpList,
			TerminationPointIterator_IHolder tpIt)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllSupportedPTPNames(NameAndStringValue_T[] equipmentName,
			int how_many, NamingAttributesList_THolder nameList,
			NamingAttributesIterator_IHolder nameIt)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllSupportingEquipment(NameAndStringValue_T[] ptpOrMfdName,
			EquipmentOrHolderList_THolder eqList)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllSupportingEquipmentNames(
			NameAndStringValue_T[] ptpOrMfdName,
			NamingAttributesList_THolder nameList)
			throws ProcessingFailureException {
		EquipmentProxy equment = new EquipmentProxy(this.userSession);
		equment.getAllSupportingEquipmentNames(ptpOrMfdName, nameList);
	}

	@Override
	public void getSupportingEquipment(NameAndStringValue_T[] equipmentName,
			EquipmentOrHolderList_THolder eqList)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getSupportingEquipmentNames(
			NameAndStringValue_T[] equipmentName,
			NamingAttributesList_THolder nameList)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getSupportedEquipment(NameAndStringValue_T[] equipmentName,
			EquipmentOrHolderList_THolder eqList)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getSupportedEquipmentNames(
			NameAndStringValue_T[] equipmentName,
			NamingAttributesList_THolder nameList)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setNativeEMSName(NameAndStringValue_T[] objectName,
			String nativeEMSName) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUserLabel(NameAndStringValue_T[] objectName,
			String userLabel, boolean enforceUniqueness)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setOwner(NameAndStringValue_T[] objectName, String owner) 
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getCapabilities(CapabilityList_THolder capabilities)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAdditionalInfo(NameAndStringValue_T[] objectName,
			NVSList_THolder additionalInfo) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

}
