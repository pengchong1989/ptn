package com.nms.corba.ninterface.impl.service;

import org.apache.log4j.Logger;
import org.omg.CORBA.StringHolder;

import subnetworkConnection.TPDataList_THolder;
import terminationPoint.TerminationPointIterator_IHolder;
import terminationPoint.TerminationPointList_THolder;
import terminationPoint.TerminationPoint_THolder;
import topologicalLink.TopologicalLinkIterator_IHolder;
import topologicalLink.TopologicalLinkList_THolder;
import transmissionParameters.LayeredParameterList_THolder;

import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.impl.service.proxy.CorbaFlowDomainProxy;
import com.nms.corba.ninterface.impl.service.proxy.CorbaMFDFRProxy;
import com.nms.corba.ninterface.util.AuthorityManager;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
import common.CapabilityList_THolder;

import flowDomain.CPTP_Role_T;
import flowDomain.FDCreateData_T;
import flowDomain.FDIterator_IHolder;
import flowDomain.FDList_THolder;
import flowDomain.FDModifyData_T;
import flowDomain.FTPCreateData_T;
import flowDomain.FlowDomainMgr_IPOA;
import flowDomain.FlowDomain_THolder;
import flowDomain.MFDCreateData_T;
import flowDomain.MFDIterator_IHolder;
import flowDomain.MFDList_THolder;
import flowDomain.MFDModifyData_T;
import flowDomain.MatrixFlowDomain_THolder;
import flowDomainFragment.FDFrCreateData_T;
import flowDomainFragment.FDFrIterator_IHolder;
import flowDomainFragment.FDFrList_THolder;
import flowDomainFragment.FDFrModifyData_T;
import flowDomainFragment.FDFrRoute_THolder;
import flowDomainFragment.FlowDomainFragment_THolder;
import flowDomainFragment.MatrixFlowDomainFragmentList_THolder;
import flowDomainFragment.MatrixFlowDomainFragment_T;
import flowDomainFragment.MatrixFlowDomainFragment_THolder;
import globaldefs.ExceptionType_T;
import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.NamingAttributesList_THolder;
import globaldefs.ProcessingFailureException;

public class FlowDomainMgr_Impl extends FlowDomainMgr_IPOA {
	private Logger LOG = Logger.getLogger(FlowDomainMgr_Impl.class.getName());
	private ICorbaSession userSession = null;

	public FlowDomainMgr_Impl(ICorbaSession userSession) {
		this.userSession = userSession;
	}

	@Override
	public void getAllFlowDomains(int how_many, FDList_THolder flowDomains, FDIterator_IHolder fdIt) throws ProcessingFailureException {
		// 验证用户权限 无权限抛异常 业务查看
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_SELECT);
		CorbaFlowDomainProxy corbaFlowDomainProxy = new CorbaFlowDomainProxy(this.userSession);
		corbaFlowDomainProxy.getAllFlowDomains(how_many, flowDomains, fdIt);

	}

	@Override
	public void getFlowDomainsByUserLabel(String userLabel, FDList_THolder flowDomains) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getFlowDomain(NameAndStringValue_T[] fdName, FlowDomain_THolder flowDomain) throws ProcessingFailureException {
		// 验证用户权限 无权限抛异常 业务查看
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_SELECT);
		CorbaFlowDomainProxy corbaFlowDomainProxy = new CorbaFlowDomainProxy(this.userSession);
		corbaFlowDomainProxy.getFlowDomain(fdName, flowDomain);

	}

	@Override
	public void getAssociatingFD(NameAndStringValue_T[] mfdName, FlowDomain_THolder flowDomain) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getTransmissionParams(NameAndStringValue_T[] name, String[] filter, LayeredParameterList_THolder transmissionParams) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void createFlowDomain(FDCreateData_T createData, NamingAttributesList_THolder assignedCPTPs, TPDataList_THolder tpsToModify, FlowDomain_THolder theFD, StringHolder errorReason) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteFlowDomain(NameAndStringValue_T[] fdName, TPDataList_THolder tpsToModify, StringHolder errorReason) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifyFlowDomain(NameAndStringValue_T[] fdName, FDModifyData_T fdModifyData, FlowDomain_THolder modifiedFD, StringHolder failedAttributes, StringHolder errorReason) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void associateMFDsWithFlowDomain(NameAndStringValue_T[] fdName, NameAndStringValue_T[][] mfdNames, TPDataList_THolder tpsToModify, StringHolder errorReason) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deAssociateMFDsFromFlowDomain(NameAndStringValue_T[] fdName, NameAndStringValue_T[][] mfdNames, TPDataList_THolder tpsToModify, StringHolder errorReason) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void associateCPTPsWithFlowDomain(NameAndStringValue_T[] fdName, NameAndStringValue_T[][] cptpNames, TPDataList_THolder tpsToModify, StringHolder errorReason) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deAssociateCPTPsFromFlowDomain(NameAndStringValue_T[] fdName, NameAndStringValue_T[][] tpNames, TPDataList_THolder tpsToModify, StringHolder errorReason) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllAssociatedMFDs(NameAndStringValue_T[] tmdOrFdName, int how_many, MFDList_THolder mfds, MFDIterator_IHolder mfdIt) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllSupportedMFDs(NameAndStringValue_T[] holderName, int how_many, MFDList_THolder mfds, MFDIterator_IHolder mfdIt) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getMFD(NameAndStringValue_T[] mfdName, MatrixFlowDomain_THolder mfd) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAssigningMFD(NameAndStringValue_T[] cptpName, MatrixFlowDomain_THolder mfd) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void createMFD(MFDCreateData_T createData, TPDataList_THolder tpsToModify, MatrixFlowDomain_THolder theMFD, StringHolder errorReason) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteMFD(NameAndStringValue_T[] mfdName, TPDataList_THolder tpsToModify, StringHolder errorReason) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifyMFD(NameAndStringValue_T[] mfdName, MFDModifyData_T mfdModifyData, MatrixFlowDomain_THolder newMFD, StringHolder failedAttributes, StringHolder errorReason) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void assignCPTPsToMFD(NameAndStringValue_T[] mfdName, NameAndStringValue_T[][] tpNames, TPDataList_THolder tpsToModify, StringHolder errorReason) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void unassignCPTPsFromMFD(NameAndStringValue_T[] mfdName, NameAndStringValue_T[][] tpNames, TPDataList_THolder tpsToModify, StringHolder errorReason) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void createFTP(FTPCreateData_T createData, TPDataList_THolder tpsToModify, TerminationPoint_THolder theFTP, StringHolder errorReason) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteFTP(NameAndStringValue_T[] ftpName, TPDataList_THolder tpsToModify, StringHolder errorReason) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllCPTPs(NameAndStringValue_T[] fdName, CPTP_Role_T cptpRole, int how_many, TerminationPointList_THolder cptpList, TerminationPointIterator_IHolder cptpIt) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllAssignedCPTPs(NameAndStringValue_T[] mfdName, int how_many, TerminationPointList_THolder cptpList, TerminationPointIterator_IHolder cptpIt) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllAssignableCPTPs(NameAndStringValue_T[] mfdName, int how_many, TerminationPointList_THolder cptpList, TerminationPointIterator_IHolder cptpIt) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllFDFrs(NameAndStringValue_T[] fdName, int how_many, short[] connectivityRateList, FDFrList_THolder fdfrList, FDFrIterator_IHolder fdfrIt) throws ProcessingFailureException {
		// 验证用户权限 无权限抛异常 业务查看
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_SELECT);
		CorbaFlowDomainProxy corbaFlowDomainProxy = new CorbaFlowDomainProxy(this.userSession);
		corbaFlowDomainProxy.getAllFDFrs(fdName, how_many, connectivityRateList, fdfrList, fdfrIt);

	}

	@Override
	public void getFDFrsWithTP(NameAndStringValue_T[] cptpName, int how_many, FDFrList_THolder fdfrList, FDFrIterator_IHolder fdfrIt) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getFDFrsByUserLabel(String userLabel, FDFrList_THolder fdfrs) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getFDFr(NameAndStringValue_T[] fdfrName, FlowDomainFragment_THolder fdfr) throws ProcessingFailureException {
		// 验证用户权限 无权限抛异常 业务查看
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_SELECT);
		CorbaFlowDomainProxy corbaFlowDomainProxy = new CorbaFlowDomainProxy(this.userSession);
		corbaFlowDomainProxy.getFDFr(fdfrName, fdfr);

	}

	@Override
	public void createAndActivateFDFr(FDFrCreateData_T createData, MatrixFlowDomainFragment_T[] mfdfrs, FlowDomainFragment_THolder theFDFr, StringHolder errorReason) throws ProcessingFailureException {
		// 验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		CorbaFlowDomainProxy corbaFlowDomainProxy = new CorbaFlowDomainProxy(this.userSession);
		corbaFlowDomainProxy.createAndActivateFDFr(createData, mfdfrs, theFDFr, errorReason);

	}

	@Override
	public void deactivateAndDeleteFDFr(NameAndStringValue_T[] fdfrName, StringHolder errorReason) throws ProcessingFailureException {
		// 验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		CorbaFlowDomainProxy corbaFlowDomainProxy = new CorbaFlowDomainProxy(this.userSession);
		corbaFlowDomainProxy.deactivateAndDeleteFDFr(fdfrName, errorReason);
	}

	@Override
	public void modifyFDFr(NameAndStringValue_T[] fdfrName, FDFrModifyData_T fdfrModifyData, FlowDomainFragment_THolder newFDFr, StringHolder errorReason) throws ProcessingFailureException {
		// 验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		CorbaFlowDomainProxy corbaFlowDomainProxy = new CorbaFlowDomainProxy(this.userSession);
		corbaFlowDomainProxy.modifyFDFr(fdfrName, fdfrModifyData, newFDFr, errorReason);

	}

	@Override
	public void getAllTopologicalLinksOfFD(NameAndStringValue_T[] flowDomainName, int how_many, TopologicalLinkList_THolder topoList, TopologicalLinkIterator_IHolder topoIt) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getFDFrRoute(NameAndStringValue_T[] fdfrName, FDFrRoute_THolder route) throws ProcessingFailureException {
		// 验证用户权限 无权限抛异常 业务查看
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_SELECT);
		CorbaFlowDomainProxy corbaFlowDomainProxy = new CorbaFlowDomainProxy(this.userSession);
		corbaFlowDomainProxy.getFDFrRoute(fdfrName, route);

	}

	@Override
	public void setNativeEMSName(NameAndStringValue_T[] objectName, String nativeEMSName) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUserLabel(NameAndStringValue_T[] objectName, String userLabel, boolean enforceUniqueness) throws ProcessingFailureException {
		// 验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		CorbaMFDFRProxy corbaMFDFRProxy = new CorbaMFDFRProxy(userSession);
		corbaMFDFRProxy.setUserLabel(objectName, userLabel, enforceUniqueness);
	}

	@Override
	public void setOwner(NameAndStringValue_T[] objectName, String owner) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getCapabilities(CapabilityList_THolder capabilities) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAdditionalInfo(NameAndStringValue_T[] objectName, NVSList_THolder additionalInfo) throws ProcessingFailureException {
		// 验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		CorbaMFDFRProxy corbaMFDFRProxy = new CorbaMFDFRProxy(userSession);
		corbaMFDFRProxy.setAdditionalInfo(objectName, additionalInfo);
	}

	@Override
	public void activateFDFr(NameAndStringValue_T[] fdfrName, FlowDomainFragment_THolder fdfr) throws ProcessingFailureException {
		// 验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		CorbaFlowDomainProxy corbaFlowDomainProxy = new CorbaFlowDomainProxy(this.userSession);
		corbaFlowDomainProxy.activateFDFr(fdfrName, fdfr);
	}

	/**
	 * function:激活业务
	 * 
	 * @param nameAndStringValue
	 *            所需激活的业务的数据
	 * @throws ProcessingFailureException
	 */
	@Override
	public void activateMFDFr(NameAndStringValue_T[][] nameAndStringValue) throws ProcessingFailureException {
		// 验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		CorbaMFDFRProxy corbaServiceProxy = null;
		corbaServiceProxy = new CorbaMFDFRProxy(userSession);
		corbaServiceProxy.activateMFDFr(nameAndStringValue);

	}

	@Override
	public void deactivateFDFr(NameAndStringValue_T[] fdfrName, FlowDomainFragment_THolder fdfr) throws ProcessingFailureException {
		// 验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		CorbaFlowDomainProxy corbaFlowDomainProxy = new CorbaFlowDomainProxy(this.userSession);
		corbaFlowDomainProxy.deactivateFDFr(fdfrName, fdfr);
	}

	/**
	 * @author guoqc 去激活单点业务
	 * @param objName
	 *            厂商信息/网元id/业务类型/主键id
	 */
	@Override
	public void deactivateMFDFr(NameAndStringValue_T[][] objName) throws ProcessingFailureException {
		// 验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		CorbaMFDFRProxy corbaServiceProxy = new CorbaMFDFRProxy(userSession);
		corbaServiceProxy.deactivateMFDFr(objName);
	}

	/**
	 * 删除单点业务
	 * 
	 * @param objName
	 *            网元对象以及业务名称
	 */
	@Override
	public void deleteMFDFr(NameAndStringValue_T[][] objName) throws ProcessingFailureException {
		// 验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		CorbaMFDFRProxy corbaServiceProxy = null;
		corbaServiceProxy = new CorbaMFDFRProxy(userSession);
		corbaServiceProxy.deleteMFDFr(objName);
	}

	@Override
	public void getMeMFDFrs(NameAndStringValue_T[] meName, MatrixFlowDomainFragmentList_THolder mfdfrList) throws ProcessingFailureException {
		// 验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_SELECT);
		CorbaMFDFRProxy corbaServiceProxy = null;
		corbaServiceProxy = new CorbaMFDFRProxy(userSession);
		corbaServiceProxy.getMeMFDFrs(meName, mfdfrList);
	}

	/**
	 * function:创建单点业务
	 * 
	 * @param matrixFlowDomainFragment
	 *            创建业务的数据
	 * @param tPData
	 * 
	 * @param matrixFlowDomainFragment_t
	 *            返回给上一层的数据
	 * @throws ProcessingFailureException
	 */
	@Override
	public void createMFDFr(MatrixFlowDomainFragment_T matrixFlowDomainFragment, MatrixFlowDomainFragment_THolder matrixFlowDomainFragment_t) throws ProcessingFailureException {
		// 验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		CorbaMFDFRProxy corbaServiceProxy = new CorbaMFDFRProxy(userSession);
		if (matrixFlowDomainFragment == null) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "createData is NULL");
		}
		corbaServiceProxy.createMFDFr(matrixFlowDomainFragment, matrixFlowDomainFragment_t);
	}

	/**
	 * 查询一条以太网业务
	 */
	@Override
	public void getMFDFr(NameAndStringValue_T[] mfdfrName, MatrixFlowDomainFragment_THolder mfdfr) throws ProcessingFailureException {
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		CorbaMFDFRProxy corbaServiceProxy = new CorbaMFDFRProxy(userSession);
		corbaServiceProxy.getMFDFr(mfdfrName, mfdfr);
	}

}
