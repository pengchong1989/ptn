package com.nms.corba.ninterface.impl.service;

import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.NamingAttributesIterator_IHolder;
import globaldefs.NamingAttributesList_THolder;
import globaldefs.ProcessingFailureException;
import mLSNPP.MLSNPPIterator_IHolder;
import mLSNPP.MLSNPPList_THolder;
import mLSNPPLink.MLSNPPLinkIterator_IHolder;
import mLSNPPLink.MLSNPPLinkList_THolder;
import mLSNPPLink.MultiLayerSNPPLink_THolder;
import managedElement.ManagedElementIterator_IHolder;
import managedElement.ManagedElementList_THolder;
import multiLayerSubnetwork.EMSFreedomLevel_T;
import multiLayerSubnetwork.MultiLayerSubnetworkMgr_IPOA;
import multiLayerSubnetwork.MultiLayerSubnetwork_THolder;
import multiLayerSubnetwork.RoutePerRouteType_THolder;
import multiLayerSubnetwork.SubnetworkIterator_IHolder;
import multiLayerSubnetwork.SubnetworkList_THolder;
import multiLayerSubnetwork.TPPoolCreateData_T;

import org.apache.log4j.Logger;
import org.omg.CORBA.BooleanHolder;
import org.omg.CORBA.IntHolder;
import org.omg.CORBA.StringHolder;

import subnetworkConnection.GradesOfImpact_T;
import subnetworkConnection.ProtectionEffort_T;
import subnetworkConnection.RouteCreateData_T;
import subnetworkConnection.RouteDescriptor_THolder;
import subnetworkConnection.RouteList_THolder;
import subnetworkConnection.RouteNameAndAdminStateList_THolder;
import subnetworkConnection.Route_THolder;
import subnetworkConnection.SNCCreateDataList_THolder;
import subnetworkConnection.SNCCreateData_T;
import subnetworkConnection.SNCIterator_IHolder;
import subnetworkConnection.SNCModifyData_T;
import subnetworkConnection.SNCState_THolder;
import subnetworkConnection.SubnetworkConnectionList_THolder;
import subnetworkConnection.SubnetworkConnection_THolder;
import subnetworkConnection.TPDataList_THolder;
import subnetworkConnection.TPData_T;
import terminationPoint.TerminationPointIterator_IHolder;
import terminationPoint.TerminationPointList_THolder;
import terminationPoint.TerminationPoint_THolder;
import topologicalLink.TopologicalLinkIterator_IHolder;
import topologicalLink.TopologicalLinkList_THolder;
import topologicalLink.TopologicalLink_THolder;
import callSNC.CallAndTopLevelConnectionsAndSNCsIterator_IHolder;
import callSNC.CallAndTopLevelConnectionsAndSNCsList_THolder;
import callSNC.CallAndTopLevelConnectionsAndSNCs_THolder;
import callSNC.CallAndTopLevelConnectionsIterator_IHolder;
import callSNC.CallAndTopLevelConnectionsList_THolder;
import callSNC.CallAndTopLevelConnections_THolder;
import callSNC.CallCreateData_T;
import callSNC.CallIdList_THolder;
import callSNC.CallModifyData_T;
import callSNC.Call_THolder;
import callSNC.Diversity_T;
import callSNC.RouteGroupInfo_T;
import callSNC.SNCAndRouteList_THolder;

import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.impl.service.proxy.CorbaServiceProxy;
import com.nms.corba.ninterface.impl.topo.TopoLinkProxy;
import com.nms.corba.ninterface.util.AuthorityManager;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
import common.CapabilityList_THolder;

public class MultiLayerSubnetworkMgr_Impl extends MultiLayerSubnetworkMgr_IPOA {
	private Logger LOG = Logger.getLogger(MultiLayerSubnetworkMgr_Impl.class.getName());
	private CorbaServiceProxy corbaServiceProxy=null;
	private ICorbaSession userSession = null;
	public MultiLayerSubnetworkMgr_Impl(ICorbaSession userSession){
		this.userSession=userSession;
	}

	@Override
	public void addConnections(NameAndStringValue_T[] arg0,
			SNCCreateData_T[] arg1, boolean arg2, TPDataList_THolder arg3,
			SubnetworkConnectionList_THolder arg4,
			SubnetworkConnectionList_THolder arg5, StringHolder arg6)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkValidSNC(SNCCreateData_T arg0, TPData_T[] arg1,
			boolean arg2, BooleanHolder arg3) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void createAndActivateSNC(SNCCreateData_T arg0,
			GradesOfImpact_T arg1, EMSFreedomLevel_T arg2,
			TPDataList_THolder arg3, SubnetworkConnection_THolder arg4,
			StringHolder arg5) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void createModifiedSNC(NameAndStringValue_T[] arg0, String arg1,
			SNCModifyData_T arg2, GradesOfImpact_T arg3,
			ProtectionEffort_T arg4, EMSFreedomLevel_T arg5,
			TPDataList_THolder arg6, SubnetworkConnection_THolder arg7,
			StringHolder arg8) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void createTPPool(TPPoolCreateData_T arg0,
			TerminationPoint_THolder arg1) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deactivateAndDeleteSNC(NameAndStringValue_T[] arg0,
			GradesOfImpact_T arg1, EMSFreedomLevel_T arg2,
			TPDataList_THolder arg3, SubnetworkConnection_THolder arg4,
			StringHolder arg5) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteTPPool(NameAndStringValue_T[] arg0)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void establishCall(CallCreateData_T arg0, SNCCreateData_T[] arg1,
			String arg2, TPDataList_THolder arg3,
			CallAndTopLevelConnectionsAndSNCs_THolder arg4,
			SNCCreateDataList_THolder arg5,
			SubnetworkConnectionList_THolder arg6, StringHolder arg7)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllCallIdsWithSNPPOrTNAName(NameAndStringValue_T arg0,
			CallIdList_THolder arg1) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllCallIdsWithTP(NameAndStringValue_T[] arg0,
			CallIdList_THolder arg1) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllCallsAndTopLevelConnections(NameAndStringValue_T[] arg0,
			int arg1, CallAndTopLevelConnectionsList_THolder arg2,
			CallAndTopLevelConnectionsIterator_IHolder arg3)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllCallsAndTopLevelConnectionsAndSNCs(
			NameAndStringValue_T[] arg0, int arg1,
			CallAndTopLevelConnectionsAndSNCsList_THolder arg2,
			CallAndTopLevelConnectionsAndSNCsIterator_IHolder arg3)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllCallsAndTopLevelConnectionsAndSNCsWithME(
			NameAndStringValue_T[] arg0, NameAndStringValue_T[] arg1, int arg2,
			CallAndTopLevelConnectionsAndSNCsList_THolder arg3,
			CallAndTopLevelConnectionsAndSNCsIterator_IHolder arg4)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllCallsAndTopLevelConnectionsAndSNCsWithTP(
			NameAndStringValue_T[] arg0, NameAndStringValue_T[] arg1, int arg2,
			CallAndTopLevelConnectionsAndSNCsList_THolder arg3,
			CallAndTopLevelConnectionsAndSNCsIterator_IHolder arg4)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllCallsAndTopLevelConnectionsWithME(
			NameAndStringValue_T[] arg0, NameAndStringValue_T[] arg1, int arg2,
			CallAndTopLevelConnectionsList_THolder arg3,
			CallAndTopLevelConnectionsIterator_IHolder arg4)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllEdgeMLSNPPLinks(NameAndStringValue_T[] arg0,
			boolean arg1, int arg2, MLSNPPLinkList_THolder arg3,
			MLSNPPLinkIterator_IHolder arg4) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllEdgePointNames(NameAndStringValue_T[] arg0, short[] arg1,
			short[] arg2, int arg3, NamingAttributesList_THolder arg4,
			NamingAttributesIterator_IHolder arg5)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllEdgePoints(NameAndStringValue_T[] arg0, short[] arg1,
			short[] arg2, int arg3, TerminationPointList_THolder arg4,
			TerminationPointIterator_IHolder arg5)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllFixedSubnetworkConnectionNames(
			NameAndStringValue_T[] arg0, short[] arg1, int arg2,
			NamingAttributesList_THolder arg3,
			NamingAttributesIterator_IHolder arg4)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllFixedSubnetworkConnectionNamesWithTP(
			NameAndStringValue_T[] arg0, short[] arg1, int arg2,
			NamingAttributesList_THolder arg3,
			NamingAttributesIterator_IHolder arg4)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllFixedSubnetworkConnections(NameAndStringValue_T[] arg0,
			short[] arg1, int arg2, SubnetworkConnectionList_THolder arg3,
			SNCIterator_IHolder arg4) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllFixedSubnetworkConnectionsWithTP(
			NameAndStringValue_T[] arg0, short[] arg1, int arg2,
			SubnetworkConnectionList_THolder arg3, SNCIterator_IHolder arg4)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllInternalMLSNPPLinks(NameAndStringValue_T[] arg0,
			boolean arg1, int arg2, MLSNPPLinkList_THolder arg3,
			MLSNPPLinkIterator_IHolder arg4) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllMLSNPPLinks(NameAndStringValue_T[] arg0, boolean arg1,
			int arg2, MLSNPPLinkList_THolder arg3,
			MLSNPPLinkIterator_IHolder arg4) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllMLSNPPs(NameAndStringValue_T[] arg0, boolean arg1,
			int arg2, MLSNPPList_THolder arg3, MLSNPPIterator_IHolder arg4)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllManagedElementNames(NameAndStringValue_T[] arg0,
			int arg1, NamingAttributesList_THolder arg2,
			NamingAttributesIterator_IHolder arg3)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllManagedElements(NameAndStringValue_T[] arg0, int arg1,
			ManagedElementList_THolder arg2, ManagedElementIterator_IHolder arg3)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllSubnetworkConnectionNames(NameAndStringValue_T[] arg0,
			short[] arg1, int arg2, NamingAttributesList_THolder arg3,
			NamingAttributesIterator_IHolder arg4)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllSubnetworkConnectionNamesWithTP(
			NameAndStringValue_T[] arg0, short[] arg1, int arg2,
			NamingAttributesList_THolder arg3,
			NamingAttributesIterator_IHolder arg4)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllSubnetworkConnections(NameAndStringValue_T[] subnetName,
			short[] connectionRateList, int how_many, SubnetworkConnectionList_THolder sncList,
			SNCIterator_IHolder sncIt) throws ProcessingFailureException {

		//验证用户权限 无权限抛异常 业务查看
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_SELECT);
		
		corbaServiceProxy=new CorbaServiceProxy(this.userSession);
		corbaServiceProxy.getAllSubnetworkConnections(subnetName,connectionRateList, how_many, sncList, sncIt);
	}

	@Override
	public void getAllSubnetworkConnectionsWithTP(NameAndStringValue_T[] arg0,
			short[] arg1, int arg2, SubnetworkConnectionList_THolder arg3,
			SNCIterator_IHolder arg4) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllSubordinateMLSNs(NameAndStringValue_T[] arg0, int arg1,
			SubnetworkList_THolder arg2, SubnetworkIterator_IHolder arg3)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllSubordinateRAidsWithConnection(
			NameAndStringValue_T[] arg0, NameAndStringValue_T[] arg1,
			String arg2, RoutePerRouteType_THolder arg3)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllTPPoolNames(NameAndStringValue_T[] arg0, int arg1,
			NamingAttributesList_THolder arg2,
			NamingAttributesIterator_IHolder arg3)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllTPPools(NameAndStringValue_T[] arg0, int arg1,
			TerminationPointList_THolder arg2,
			TerminationPointIterator_IHolder arg3)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	/**
	 * 获取该域下所有的段的名称
	 * @param subnetName
	 * @param howMany
	 * @param nameList
	 * @param nameIt
	 */
	@Override
	public void getAllTopologicalLinkNames(NameAndStringValue_T[] subnetName,
			int how_many, NamingAttributesList_THolder nameList,
			NamingAttributesIterator_IHolder nameIt)
			throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 业务查看
		AuthorityManager.checkAuthority(this.userSession, RootFactory.TOPOLOGY_SELECT);
		TopoLinkProxy topo = new TopoLinkProxy(this.userSession); 
		
		try {
			topo.getAllTopologicalLinkNames(subnetName, how_many, nameList, nameIt);
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	/**
	 * 获取该域下的所有段的信息
	 * @param subnetName
	 * @param howMany
	 * @param topoList
	 * @param topoIt
	 */
	@Override
	public void getAllTopologicalLinks(NameAndStringValue_T[] subnetName,
			int how_many, TopologicalLinkList_THolder topoList,
			TopologicalLinkIterator_IHolder topoIt)
			throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 业务查看
		AuthorityManager.checkAuthority(this.userSession, RootFactory.TOPOLOGY_SELECT);
		TopoLinkProxy topo = new TopoLinkProxy(this.userSession); 
		
		try {
			topo.getAllTopologicalLinks(subnetName, how_many, topoList, topoIt);
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	@Override
	public void getAssociatedTP(NameAndStringValue_T[] arg0,
			TerminationPointList_THolder arg1)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getBackupRoutes(NameAndStringValue_T[] arg0, String arg1,
			boolean arg2, NVSList_THolder arg3, RouteList_THolder arg4)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getCall(NameAndStringValue_T[] arg0, Call_THolder arg1)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getCallAndTopLevelConnections(NameAndStringValue_T[] arg0,
			String arg1, CallAndTopLevelConnections_THolder arg2)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getCallAndTopLevelConnectionsAndSNCs(
			NameAndStringValue_T[] arg0,
			CallAndTopLevelConnectionsAndSNCs_THolder arg1)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getConnectionsAndRouteDetails(String arg0,
			NameAndStringValue_T[] arg1, String arg2, boolean arg3,
			String arg4, SNCAndRouteList_THolder arg5)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getIntendedRoute(NameAndStringValue_T[] arg0, boolean arg1,
			NVSList_THolder arg2, Route_THolder arg3)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getMLSNPPLink(NameAndStringValue_T[] arg0, boolean arg1,
			MultiLayerSNPPLink_THolder arg2) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getMultiLayerSubnetwork(NameAndStringValue_T[] arg0,
			MultiLayerSubnetwork_THolder arg1)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getRoute(NameAndStringValue_T[] arg0, boolean arg1,
			Route_THolder arg2) throws ProcessingFailureException {
		corbaServiceProxy=new CorbaServiceProxy(userSession);
		corbaServiceProxy.getRoute(arg0, arg2);

	}

	@Override
	public void getRouteAndTopologicalLinks(NameAndStringValue_T[] arg0,
			Route_THolder arg1, TopologicalLinkList_THolder arg2)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getSNC(NameAndStringValue_T[] sncName,
			SubnetworkConnection_THolder snc)
			throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 业务查看
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_SELECT);		
		corbaServiceProxy=new CorbaServiceProxy(userSession);
		corbaServiceProxy.getSNC(sncName, snc);
	}

	@Override
	public void getSNCsByUserLabel(String arg0,
			SubnetworkConnectionList_THolder arg1)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getTPGroupingRelationships(NameAndStringValue_T[] arg0,
			int arg1, NamingAttributesList_THolder arg2,
			NamingAttributesIterator_IHolder arg3)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getTPPool(NameAndStringValue_T[] arg0,
			TerminationPoint_THolder arg1, IntHolder arg2, IntHolder arg3,
			StringHolder arg4) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	/**
	 * 获取指定的段信息
	 * @param topoLinkName 段名称
	 * @param topoLink
	 */
	@Override
	public void getTopologicalLink(NameAndStringValue_T[] topoLinkName,
			TopologicalLink_THolder topoLink) throws ProcessingFailureException {
		TopoLinkProxy topo = new TopoLinkProxy(this.userSession); 
		
		try {
			topo.getTopologicalLink(topoLinkName, topoLink);
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	@Override
	public void modifyCall(NameAndStringValue_T[] arg0, CallModifyData_T arg1,
			Call_THolder arg2) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifyDiversityAndCorouting(NameAndStringValue_T[] arg0,
			Diversity_T arg1, RouteGroupInfo_T[] arg2, boolean arg3,
			String arg4, NVSList_THolder arg5,
			CallAndTopLevelConnections_THolder arg6)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifyTPPool(NameAndStringValue_T[] arg0,
			NameAndStringValue_T[][] arg1, String arg2,
			TerminationPoint_THolder arg3) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void releaseCall(NameAndStringValue_T[] arg0,
			TPDataList_THolder arg1, SubnetworkConnectionList_THolder arg2,
			StringHolder arg3) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeConnections(NameAndStringValue_T[] arg0,
			NameAndStringValue_T[][] arg1, TPDataList_THolder arg2,
			SubnetworkConnectionList_THolder arg3, StringHolder arg4)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	public void setIntendedRoute(NameAndStringValue_T[] arg0, String arg1,
			NVSList_THolder arg2) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setRoutesAdminState(NameAndStringValue_T[] arg0,
			RouteNameAndAdminStateList_THolder arg1, SNCState_THolder arg2)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void swapSNC(NameAndStringValue_T[] arg0,
			NameAndStringValue_T[] arg1, EMSFreedomLevel_T arg2,
			GradesOfImpact_T arg3, TPDataList_THolder arg4,
			SNCState_THolder arg5, StringHolder arg6)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void switchRoute(NameAndStringValue_T[] arg0, String arg1,
			GradesOfImpact_T arg2, EMSFreedomLevel_T arg3,
			TPDataList_THolder arg4, NVSList_THolder arg5,
			SNCState_THolder arg6, StringHolder arg7)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getCapabilities(CapabilityList_THolder arg0)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAdditionalInfo(NameAndStringValue_T[] arg0,
			NVSList_THolder arg1) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setNativeEMSName(NameAndStringValue_T[] arg0, String arg1)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setOwner(NameAndStringValue_T[] arg0, String arg1)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUserLabel(NameAndStringValue_T[] arg0, String arg1,
			boolean arg2) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}
	@Override
	public void activateSNC(NameAndStringValue_T[] sncName, SubnetworkConnection_THolder subnetConn,
								StringHolder result) throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		corbaServiceProxy = new CorbaServiceProxy(userSession);
		corbaServiceProxy.activateSNC(sncName, subnetConn, result);
	}
	
	/**
	 * 添加保护路由
	 */
	@Override
	public void addRoute(NameAndStringValue_T[] sncName, RouteCreateData_T createRoute,
							RouteDescriptor_THolder theRoute, StringHolder errorReason)
								throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		corbaServiceProxy = new CorbaServiceProxy(userSession);
		this.corbaServiceProxy.addRoute(sncName, createRoute, theRoute, errorReason);
	}
	
	@Override
	public void createSNC(SNCCreateData_T createData, SubnetworkConnection_THolder sncDataHolder,
							StringHolder result) throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		corbaServiceProxy = new CorbaServiceProxy(userSession);		
		corbaServiceProxy.createSNC(createData, sncDataHolder, result);
	}
	
	@Override
	public void deactivateSNC(NameAndStringValue_T[] sncName, SubnetworkConnection_THolder subnetConn,
								StringHolder result) throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession , RootFactory.CORE_MANAGE);
		corbaServiceProxy=new CorbaServiceProxy(this.userSession);		
		corbaServiceProxy.deactivateSNC(sncName, subnetConn, result);
	}
	
	@Override
	public void deleteSNC(NameAndStringValue_T[] sncName) throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		corbaServiceProxy = new CorbaServiceProxy(this.userSession);
		corbaServiceProxy.deleteSNC(sncName);
	}
	
	@Override
	public void modifySNC(NameAndStringValue_T[] sncName, SNCModifyData_T modifyData,
							SubnetworkConnection_THolder subnetConn, StringHolder result)
								throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		corbaServiceProxy = new CorbaServiceProxy(userSession);
		corbaServiceProxy.modifySNC(sncName, modifyData, subnetConn, result);
	}
	
	/**
	 * 删除保护路由
	 */
	@Override
	public void removeRoute(NameAndStringValue_T[] sncName, String routerId,
								NVSList_THolder result) throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		corbaServiceProxy = new CorbaServiceProxy(userSession);
		this.corbaServiceProxy.removeRoute(sncName, routerId, result);
	}

}
