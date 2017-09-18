package com.nms.corba.ninterface.impl.protection;

import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.NamingAttributesIterator_IHolder;
import globaldefs.NamingAttributesList_THolder;
import globaldefs.ProcessingFailureException;
import protection.EProtectionGroupIterator_IHolder;
import protection.EProtectionGroupList_THolder;
import protection.EProtectionGroup_THolder;
import protection.ESwitchDataList_THolder;
import protection.ProtectionCommand_T;
import protection.ProtectionGroupIterator_IHolder;
import protection.ProtectionGroupList_THolder;
import protection.ProtectionGroup_THolder;
import protection.ProtectionMgr_IPOA;
import protection.SwitchDataList_THolder;
import protection.SwitchData_THolder;

import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.impl.protection.proxy.CorbaProtectProxy;
import com.nms.corba.ninterface.util.AuthorityManager;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
import common.CapabilityList_THolder;

/**
 * 保护组
 * @author dzy
 *
 */
public class ProtectionMgr_Impl extends ProtectionMgr_IPOA {

	private ICorbaSession userSession = null; //用户Session
	
	/**
	 * 创建一个实例
	 * @param userSession
	 */
	public void ProtectionMgr_Imp(ICorbaSession userSession){
		this.userSession = userSession;
	}
	
	/**
	 * 查询所有保护
	 * @param meName 网元标识
	 * @param pgList	第一组返回保护组集合
	 * @param pgpIt		保护组集合迭代
	 */
	@Override
	public void getAllProtectionGroups(NameAndStringValue_T[] meName,
			int how_many, ProtectionGroupList_THolder pgList,
			ProtectionGroupIterator_IHolder pgpIt)
			throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 业务查询
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_SELECT);
		CorbaProtectProxy proxy = new CorbaProtectProxy(this.userSession);
		proxy.getAllProtectionGroups(meName,how_many, pgList, pgpIt);
	}

	@Override
	public void getAllEProtectionGroups(NameAndStringValue_T[] meName,
			int how_many, EProtectionGroupList_THolder epgpList,
			EProtectionGroupIterator_IHolder epgpIt)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub
		//验证用户权限 无权限抛异常 业务查询
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_SELECT);

	}

	@Override
	public void getProtectionGroup(NameAndStringValue_T[] pgName,
			ProtectionGroup_THolder protectionGroup)
			throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 业务查询
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_SELECT);
		CorbaProtectProxy proxy = new CorbaProtectProxy(this.userSession);
		proxy.getProtectionGroup(pgName,protectionGroup);
	}

	@Override
	public void getEProtectionGroup(NameAndStringValue_T[] ePGPname,
			EProtectionGroup_THolder eProtectionGroup)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllNUTTPNames(NameAndStringValue_T[] pgName, int how_many,
			NamingAttributesList_THolder nameList,
			NamingAttributesIterator_IHolder nameIt)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllPreemptibleTPNames(NameAndStringValue_T[] pgName,
			int how_many, NamingAttributesList_THolder nameList,
			NamingAttributesIterator_IHolder nameIt)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllProtectedTPNames(NameAndStringValue_T[] pgName,
			int how_many, NamingAttributesList_THolder nameList,
			NamingAttributesIterator_IHolder nameIt)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void retrieveSwitchData(
			NameAndStringValue_T[] reliableSinkCtpOrGroupName,
			SwitchDataList_THolder switchData)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void retrieveESwitchData(NameAndStringValue_T[] ePGPName,
			ESwitchDataList_THolder eSwitchDataList)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	/**
	 * 人工保护倒换
	 * @param protectionCommand 倒换类型
	 * @param reliableSinkCtpOrGroupName 倒换对象
	 * @param switchData 保护倒换信息通知
	 */
	@Override
	public void performProtectionCommand(ProtectionCommand_T protectionCommand,
			NameAndStringValue_T[] reliableSinkCtpOrGroupName,
			NameAndStringValue_T[] fromTp, NameAndStringValue_T[] toTp,
			SwitchData_THolder switchData) throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		CorbaProtectProxy proxy = new CorbaProtectProxy(this.userSession);
		proxy.performProtectionCommand(protectionCommand, reliableSinkCtpOrGroupName, toTp,switchData);
	}

	@Override
	public void getContainingPGNames(NameAndStringValue_T[] pTPName,
			NamingAttributesList_THolder pgNameList)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setNativeEMSName(NameAndStringValue_T[] objectName,
			String nativeEMSName) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	/**
	 * 修改保护组名称环网保护、msp保护、lsp1:1保护
	 */
	@Override
	public void setUserLabel(NameAndStringValue_T[] objectName,
			String userLabel, boolean enforceUniqueness)
			throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		CorbaProtectProxy proxy = new CorbaProtectProxy(this.userSession);
		proxy.setUserLabel(objectName, userLabel, enforceUniqueness);
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
	
	public ProtectionMgr_Impl(ICorbaSession userSession){
		this.userSession = userSession;
	}
}
