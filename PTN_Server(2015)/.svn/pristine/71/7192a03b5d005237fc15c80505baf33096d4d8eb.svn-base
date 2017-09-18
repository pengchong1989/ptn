package com.nms.corba.ninterface.impl.resource;

import mLSNPP.MLSNPPIterator_IHolder;
import mLSNPP.MLSNPPList_THolder;
import mLSNPPLink.MLSNPPLinkIterator_IHolder;
import mLSNPPLink.MLSNPPLinkList_THolder;
import multiLayerSubnetwork.SubnetworkIterator_IHolder;
import multiLayerSubnetwork.SubnetworkList_THolder;
import notifications.AlarmAndTCAIDList_THolder;
import notifications.AlarmOrTCAIdentifier_T;
import notifications.AlarmType_T;
import notifications.EventIterator_IHolder;
import notifications.EventList_THolder;
import notifications.PerceivedSeverity_T;

import org.apache.log4j.Logger;

import topologicalLink.TLCreateData_T;
import topologicalLink.TopologicalLinkIterator_IHolder;
import topologicalLink.TopologicalLinkList_THolder;
import topologicalLink.TopologicalLink_THolder;
import aSAP.ASAPCreateModifyData_T;
import aSAP.ASAPIterator_IHolder;
import aSAP.ASAPList_THolder;
import aSAP.ASAP_THolder;

import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.impl.resource.proxy.CorbaAlarmProxy;
import com.nms.corba.ninterface.impl.resource.proxy.CorbaEMSMgrProxy;
import com.nms.corba.ninterface.impl.resource.proxy.CorbaResourceProxy;
import com.nms.corba.ninterface.impl.topo.TopoLinkProxy;
import com.nms.corba.ninterface.impl.util.CheckParameterUtil;
import com.nms.corba.ninterface.util.AuthorityManager;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
import common.CapabilityList_THolder;

import emsMgr.EMSMgr_IPOA;
import emsMgr.EMS_T;
import emsMgr.EMS_THolder;
import globaldefs.ExceptionType_T;
import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.NamingAttributesIterator_IHolder;
import globaldefs.NamingAttributesList_THolder;
import globaldefs.ProcessingFailureException;

public class EMSMgr_Impl extends EMSMgr_IPOA {
	private Logger LOG = Logger.getLogger(EMSMgr_Impl.class.getName());
	private ICorbaSession userSession = null;

	public EMSMgr_Impl(ICorbaSession userSession) {
		this.userSession = userSession;
	}

	@Override
	public void getEMS(EMS_THolder emsInfo) throws ProcessingFailureException {

		CorbaResourceProxy proxy = new CorbaResourceProxy(userSession);
		boolean bRet = false;
		emsInfo.value = new EMS_T();
		bRet = proxy.getEms(emsInfo.value);
		if (!bRet) {
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR,
					"Internal error : The process has internal error!");
		}
	}

	@Override
	public void getAllMLRAs(int how_many, SubnetworkList_THolder mLRAList,
			SubnetworkIterator_IHolder sIt) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	/**
	 * 查询子网信息对象
	 */
	@Override
	public void getAllTopLevelSubnetworks(int how_many,
			SubnetworkList_THolder sList, SubnetworkIterator_IHolder sIt)
			throws ProcessingFailureException {
		// 验证用户权限 无权限抛异常 配置查看
		AuthorityManager.checkAuthority(this.userSession,
				RootFactory.DEPLOY_SELECT);
		// 查询子网信息配置文件得到SubNetworkInfo类从而得到其中的名称及属性
		CorbaEMSMgrProxy proxy = new CorbaEMSMgrProxy(this.userSession);
		proxy.getAllTopLevelSubnetworks(sList);
	}

	@Override
	public void getAllTopLevelSubnetworkNames(int how_many,
			NamingAttributesList_THolder nameList,
			NamingAttributesIterator_IHolder nameIt)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllMLSNPPLinks(boolean sNPListRequested, int how_many,
			MLSNPPLinkList_THolder mLSNPPLinkList,
			MLSNPPLinkIterator_IHolder mLSNPPLinkIt)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllMLSNPPLinksWithTP(NameAndStringValue_T[] tPName,
			boolean sNPListRequested, int how_many,
			MLSNPPLinkList_THolder mLSNPPLinkList,
			MLSNPPLinkIterator_IHolder mLSNPPLinkIt)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllMLSNPPLinksWithMLSNs(NameAndStringValue_T[][] mLRANames,
			boolean sNPListRequested, int how_many,
			MLSNPPLinkList_THolder mLSNPPLinkList,
			MLSNPPLinkIterator_IHolder mLSNPPLinkIt)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllMLSNPPLinksWithTNAs(String[] tNAList,
			boolean sNPListRequested, int how_many,
			MLSNPPLinkList_THolder mLSNPPLinkList,
			MLSNPPLinkIterator_IHolder mLSNPPLinkIt)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllMLSNPPs(boolean sNPListRequested, int how_many,
			MLSNPPList_THolder mLSNPPList, MLSNPPIterator_IHolder mLSNPPIt)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllMLSNPPsWithTP(NameAndStringValue_T[] tPName,
			boolean sNPListRequested, int how_many,
			MLSNPPList_THolder mLSNPPList, MLSNPPIterator_IHolder mLSNPPIt)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllMLSNPPsWithTNA(String[] tNAList,
			boolean sNPListRequested, int how_many,
			MLSNPPList_THolder mLSNPPList, MLSNPPIterator_IHolder mLSNPPIt)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	/**
	 * 查询所有拓扑连接
	 */
	@Override
	public void getAllTopLevelTopologicalLinks(int how_many,
			TopologicalLinkList_THolder topoList,
			TopologicalLinkIterator_IHolder topoIt)
			throws ProcessingFailureException {
		//验证用户权限 无权限抛异常	拓扑查看
		AuthorityManager.checkAuthority(this.userSession, RootFactory.TOPOLOGY_SELECT);
		CorbaEMSMgrProxy corbaEMSMgrProxy = new CorbaEMSMgrProxy(userSession);
		corbaEMSMgrProxy.getAllTopologicalLinks(how_many,topoList,topoIt);
	}

	@Override
	public void getAllTopLevelTopologicalLinkNames(int how_many,
			NamingAttributesList_THolder nameList,
			NamingAttributesIterator_IHolder nameIt)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	/**
	 * 查询指定拓扑连接
	 */
	@Override
	public void getTopLevelTopologicalLink(NameAndStringValue_T[] topoLinkName,
			TopologicalLink_THolder topoLink) throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 拓扑查看
		AuthorityManager.checkAuthority(this.userSession, RootFactory.TOPOLOGY_SELECT);
		CorbaEMSMgrProxy corbaEMSMgrProxy = new CorbaEMSMgrProxy(userSession);
		corbaEMSMgrProxy.getTopologicalLink(topoLinkName,topoLink);
	}

	@Override
	public void getAllEMSAndMEActiveAlarms(String[] excludeProbCauseList,
			PerceivedSeverity_T[] excludeSeverityList, int how_many,
			EventList_THolder eventList, EventIterator_IHolder eventIt)
			throws ProcessingFailureException {

	}

	@Override
	public void getAllEMSSystemActiveAlarms(
			PerceivedSeverity_T[] excludeSeverityList, int how_many,
			EventList_THolder eventList, EventIterator_IHolder eventIt)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	/**
	 * @author guoqc 创建段 入参 newTLCreateData 出参 newTopologicalLink
	 */
	@Override
	public void createTopologicalLink(TLCreateData_T newTLCreateData,
			TopologicalLink_THolder newTopologicalLink)
			throws ProcessingFailureException {
		TopoLinkProxy proxy = new TopoLinkProxy(this.userSession);
		try {
			proxy.createTopologicalLink(newTLCreateData, newTopologicalLink);
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	/**
	 * @author guoqc 删除段 入参 topoLinkName 根据段的主键id删除
	 */
	@Override
	public void deleteTopologicalLink(NameAndStringValue_T[] topoLinkName)
			throws ProcessingFailureException {
		TopoLinkProxy proxy = new TopoLinkProxy(this.userSession);
		try {
			proxy.deleteTopologicalLink(topoLinkName);
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	@Override
	public void acknowledgeAlarms(AlarmOrTCAIdentifier_T[] acknowledgeIDList,
			NameAndStringValue_T[] additionalInfo,
			AlarmAndTCAIDList_THolder failedAcknowledgeIDList)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void unacknowledgeAlarms(
			AlarmOrTCAIdentifier_T[] unacknowledgeIDList,
			NameAndStringValue_T[] additionalInfo,
			AlarmAndTCAIDList_THolder failedunAcknowledgeIDList)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllEMSAndMEUnacknowledgedActiveAlarms(
			String[] excludeProbCauseList,
			PerceivedSeverity_T[] excludeSeverityList, int how_many,
			EventList_THolder eventList, EventIterator_IHolder eventIt)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllEMSSystemUnacknowledgedActiveAlarms(
			PerceivedSeverity_T[] excludeSeverityList, int how_many,
			EventList_THolder eventList, EventIterator_IHolder eventIt)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void createASAP(ASAPCreateModifyData_T newASAPCreateData,
			ASAP_THolder newASAP, NVSList_THolder additionalInfo)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteASAP(NameAndStringValue_T[] aSAPName,
			NVSList_THolder additionalInfo) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void assignASAP(NameAndStringValue_T[] aSAPName,
			NameAndStringValue_T[] resourceName, short layerRate,
			NVSList_THolder additionalInfo) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deassignASAP(NameAndStringValue_T[] resourceName,
			short layerRate, NVSList_THolder additionalInfo)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	/**
	 * @author guoqc 修改告警级别表 入参 aSAPName 厂商信息和修改的数据类型 入参 aSAPModifyData
	 *         包含需要修改的告警级别表,告警级别,告警码 出参 newASAP 修改后的告警级别表 出参 additionalInfo 附加信息
	 */
	@Override
	public void modifyASAP(NameAndStringValue_T[] aSAPName,
			ASAPCreateModifyData_T aSAPModifyData, ASAP_THolder newASAP,
			NVSList_THolder additionalInfo) throws ProcessingFailureException {
		// 验证用户权限 无权限抛异常 告警管理
		AuthorityManager.checkAuthority(this.userSession,RootFactory.ALARM_MANAGE);
		try {
			CorbaAlarmProxy alarmProxy = new CorbaAlarmProxy(userSession);
			alarmProxy.modifyASAP(aSAPName, aSAPModifyData, newASAP, additionalInfo);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

	/**
	 * @author guoqc 查询告警级别表 入参 how_many 出参 aSAPList 告警信息 出参 asapIt 迭代器
	 */
	@Override
	public void getAllASAPs(int how_many, ASAPList_THolder aSAPList,
			ASAPIterator_IHolder aSAPIt) throws ProcessingFailureException {
		// 验证用户权限 无权限抛异常 告警查询
		AuthorityManager.checkAuthority(this.userSession, RootFactory.ALARM_SELECT);
		if(how_many < 1){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "how_many must be more than 0!");
		}
		try {
			CorbaAlarmProxy alarmProxy = new CorbaAlarmProxy(userSession);
			alarmProxy.getAllASAPs(how_many, aSAPList, aSAPIt);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "occur inner error!");
		}
	}

	@Override
	public void getAllASAPNames(int how_many,
			NamingAttributesList_THolder nameList,
			NamingAttributesIterator_IHolder nameIt)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	/**
	 * @author guoqc 查询某一条告警级别表 入参 aSAPName 告警级别,告警码,厂商信息和修改的数据类型 出参 anASAP
	 *         告警级别表
	 */
	@Override
	public void getASAP(NameAndStringValue_T[] aSAPName, ASAP_THolder anASAP)
			throws ProcessingFailureException {
		// 验证用户权限 无权限抛异常 告警查询
		AuthorityManager.checkAuthority(this.userSession, RootFactory.ALARM_SELECT);
		try {
			CorbaAlarmProxy alarmProxy = new CorbaAlarmProxy(userSession);
			alarmProxy.getASAP(aSAPName, anASAP);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "occur inner error!");
		}
	}

	@Override
	public void getASAPbyResource(NameAndStringValue_T[] resourceName,
			short[] layerRateList, int how_many, ASAPList_THolder aSAPList,
			ASAPIterator_IHolder asapIt) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getASAPAssociatedResourceNames(NameAndStringValue_T[] aSAPName,
			int how_many, NamingAttributesList_THolder nameList,
			NamingAttributesIterator_IHolder nameIt)
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
		
		try{
			if(CheckParameterUtil.checkTopologicalLinkName(objectName)){
				// 验证用户权限 无权限抛异常 拓扑管理
				AuthorityManager.checkAuthority(this.userSession,RootFactory.TOPOLOGY_MANAGE);
				CorbaEMSMgrProxy corbaEMSMgrProxy = new CorbaEMSMgrProxy(userSession);
				corbaEMSMgrProxy.setTopoUserLabel(objectName,userLabel);
			}else{
				// 验证用户权限 无权限抛异常 拓扑管理
				AuthorityManager.checkAuthority(this.userSession,RootFactory.DEPLOY_MANAGE);
				CorbaResourceProxy resProxy = new CorbaResourceProxy(userSession);
				resProxy.setUserLabel(objectName, userLabel);
			}
			
		}catch(ProcessingFailureException e){
			throw e;
		}catch(Exception ex){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "internal error");
		}
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
		// 验证用户权限 无权限抛异常 配置管理
		AuthorityManager.checkAuthority(this.userSession,
				RootFactory.DEPLOY_MANAGE);
		try{
			CorbaResourceProxy resProxy = new CorbaResourceProxy(userSession);
			resProxy.setAdditionalInfo(objectName, additionalInfo);
		}catch(Exception ex){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "internal error");
		}
	}

	@Override
	public void getAllActiveAlarmsByCond(String startTime, String endTime,
			NameAndStringValue_T[][] objectNameList,
			String[] inludeProbCauseList,
			PerceivedSeverity_T[] includeSeverityList,
			AlarmType_T[] includeAlarmTypeList, int how_many,
			EventList_THolder eventList, EventIterator_IHolder eventIt)
			throws ProcessingFailureException {
		// 验证用户权限 无权限抛异常 告警查询
		AuthorityManager.checkAuthority(this.userSession,
				RootFactory.ALARM_SELECT);
		CorbaAlarmProxy alarmProxy = new CorbaAlarmProxy(userSession);
		alarmProxy.getCurrentAlarmsByCond(startTime, endTime,objectNameList,
				inludeProbCauseList, includeSeverityList, includeAlarmTypeList, how_many, eventList,
				eventIt);
		
	}


}
