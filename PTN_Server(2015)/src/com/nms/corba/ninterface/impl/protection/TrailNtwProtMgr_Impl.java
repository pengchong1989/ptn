package com.nms.corba.ninterface.impl.protection;

import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import org.omg.CORBA.StringHolder;

import protection.ProtectionCommand_T;
import trailNtwProtection.TNPSwitchData_THolder;
import trailNtwProtection.TrailNtwProtCreateData_T;
import trailNtwProtection.TrailNtwProtMgr_IPOA;
import trailNtwProtection.TrailNtwProtModifyData_T;
import trailNtwProtection.TrailNtwProtectionIterator_IHolder;
import trailNtwProtection.TrailNtwProtectionList_THolder;
import trailNtwProtection.TrailNtwProtection_THolder;

import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.impl.protection.proxy.CorbaTNPProxy;
import com.nms.corba.ninterface.util.AuthorityManager;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
import common.CapabilityList_THolder;

public class TrailNtwProtMgr_Impl extends TrailNtwProtMgr_IPOA {

	private ICorbaSession userSession = null;
	public TrailNtwProtMgr_Impl(ICorbaSession userSession){
		this.userSession = userSession;
	}
	
	/**
	 * 查询所有路径网络保护（TNP）
	 * @param resourceName	资源名称
	 * @param how_many	首次获取的数目
	 * @param tnpList	首次获取的TNP信息
	 * @param tnpIt	迭代获取TNP接口
	 */
	@Override
	public void getAllTrailNtwProtections(NameAndStringValue_T[] resourceName,
			int how_many, TrailNtwProtectionList_THolder tnpList,
			TrailNtwProtectionIterator_IHolder tnpIt)
			throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 业务查看
		AuthorityManager.checkAuthority(this.userSession , RootFactory.CORE_SELECT);
		CorbaTNPProxy corbaTNPProxy = new CorbaTNPProxy(this.userSession);
		corbaTNPProxy.getAllTNP(resourceName,how_many,tnpList,tnpIt);
	}

	/**
	 * 查询路径网络保护（TNP）
	 * @param tnpName	TNP名称
	 * @param tNtwProtection	TNP信息
	 */
	@Override
	public void getTrailNtwProtection(NameAndStringValue_T[] tnpName,
			TrailNtwProtection_THolder tNtwProtection)
			throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 业务查看
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_SELECT);
		CorbaTNPProxy corbaTNPProxy = new CorbaTNPProxy(this.userSession);
		corbaTNPProxy.getTNP(tnpName,tNtwProtection);
	}

	/**
	 * 创建TNP
	 * @param tnpCreateData		创建的TNP对象
	 * @param theTNP	返回TNP对象
	 * @param errorReason	返回参数
	 */
	@Override
	public void createTrailNtwProtection(
			TrailNtwProtCreateData_T tnpCreateData,
			TrailNtwProtection_THolder theTNP, StringHolder errorReason)
			throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		CorbaTNPProxy corbaTNPProxy = new CorbaTNPProxy(this.userSession);
		corbaTNPProxy.createTNP(tnpCreateData,theTNP,errorReason);
	}

	/**
	 * 修改路径网络保护（TNP）信息
	 * @param tnpName 要修改的TNP标示符
	 * @param tnpModifyData 修改的TNP数据
	 * @param modifiedTNP 修改的TNP
	 * @param errorReason 错误原因
	 * @throws ProcessingFailureException 
	 */
	@Override
	public void modifyTrailNtwProtection(NameAndStringValue_T[] tnpName,
			TrailNtwProtModifyData_T tnpModifyData,
			TrailNtwProtection_THolder modifiedTNP, StringHolder errorReason)
			throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		CorbaTNPProxy proxy = new CorbaTNPProxy(this.userSession);
		proxy.modifyTrailNtwProtection(tnpName, tnpModifyData, modifiedTNP, errorReason);
	}

	/**
	 * 删除路径网络保护
	 * @param tnpName 删除的TNP标示符
	 * @param keepPGs true：相关的保护组保留，false 相关的保护组删除
	 * @param swapTPname 倒换端口名称
	 * @param additionalInfo 附加信息
	 * @param deletedPGList 删除的保护组
	 * @param deletedTNP 删除的TNP
	 * @param errorReason 错误原因
	 * @throws ProcessingFailureException 
	 */
	@Override
	public void deleteTrailNtwProtection(NameAndStringValue_T[] tnpName, StringHolder errorReason)
			throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		CorbaTNPProxy proxy = new CorbaTNPProxy(this.userSession);
		proxy.deleteTrailNtwProtection(tnpName, errorReason);
	}

	/**
	 * 人工保护倒换
	 * @param protectionCommand 倒换命令
	 * @param tnpName 倒换对象标识符
	 * @param switchDirection 倒换方向
	 * @param tnpSwitchData 倒换信息通知
	 * @param protectionGroupAName a端保护组名称
	 * @param protectionGroupZName z端保护组名称
	 * @param workerTrailList 工作链路组
	 * @param protectionTrail 保护链路
	 */
	@Override
	public void performTNPCommand(ProtectionCommand_T protectionCommand,
			NameAndStringValue_T[] tnpName, String switchDirection,
			NameAndStringValue_T[] protectionGroupAName,
			NameAndStringValue_T[] protectionGroupZName,
			NameAndStringValue_T[][][] workerTrailList,
			NameAndStringValue_T[][] protectionTrail,
			TNPSwitchData_THolder tnpSwitchData)
			throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		CorbaTNPProxy proxy = new CorbaTNPProxy(this.userSession);
		proxy.performTNPCommand(protectionCommand, tnpName, switchDirection,tnpSwitchData);
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
