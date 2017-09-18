package com.nms.corba.ninterface.impl.resource;

import globaldefs.ExceptionType_T;
import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.NamingAttributesIterator_IHolder;
import globaldefs.NamingAttributesList_THolder;
import globaldefs.ProcessingFailureException;
import managedElement.ManagedElementIterator_IHolder;
import managedElement.ManagedElementList_THolder;
import managedElement.ManagedElement_THolder;
import managedElementManager.ManagedElementMgr_IPOA;
import notifications.EventIterator_IHolder;
import notifications.EventList_THolder;
import notifications.PerceivedSeverity_T;

import org.apache.log4j.Logger;
import org.omg.CORBA.StringHolder;

import subnetworkConnection.CCIterator_IHolder;
import subnetworkConnection.CrossConnectList_THolder;
import subnetworkConnection.CrossConnect_T;
import subnetworkConnection.CrossConnect_THolder;
import subnetworkConnection.TPData_T;
import terminationPoint.Directionality_T;
import terminationPoint.GTPEffort_T;
import terminationPoint.GTP_THolder;
import terminationPoint.GTPiterator_IHolder;
import terminationPoint.GTPlist_THolder;
import terminationPoint.TerminationPointIterator_IHolder;
import terminationPoint.TerminationPointList_THolder;
import terminationPoint.TerminationPoint_THolder;
import transmissionParameters.LayeredParameterList_THolder;

import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.impl.manageElement.proxy.CorbaManageElementProxy;
import com.nms.corba.ninterface.impl.resource.proxy.CorbaResourceProxy;
import com.nms.corba.ninterface.util.AuthorityManager;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
import common.CapabilityList_THolder;

public class ManagedElementMgr_Impl extends ManagedElementMgr_IPOA {

	private Logger LOG = Logger.getLogger(ManagedElementMgr_Impl.class.getName());
	private ICorbaSession userSession = null;

	public ManagedElementMgr_Impl(ICorbaSession userSession) {
		this.userSession = userSession;
	}

	/**
	 * 获取所有网元 in：how_many
	 */
	@Override
	public void getAllManagedElements(int how_many, ManagedElementList_THolder meList, ManagedElementIterator_IHolder meIt) throws ProcessingFailureException {
		CorbaManageElementProxy proxy = new CorbaManageElementProxy(this.userSession);
		try {
			proxy.getAllManagedElements(how_many, meList, meIt);
		} catch (ProcessingFailureException ex) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "getAllManagedElements failed.");
		}
	}

	/**
	 * 获取所有网元名称
	 */
	@Override
	public void getAllManagedElementNames(int how_many, NamingAttributesList_THolder nameList, NamingAttributesIterator_IHolder nameIt) throws ProcessingFailureException {
		CorbaManageElementProxy proxy = new CorbaManageElementProxy(this.userSession);
		try {
			proxy.getAllManagedElementNames(how_many, nameList, nameIt);
		} catch (ProcessingFailureException ex) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "getAllManagedElementNames failed.");
		}
	}

	/**
	 * 网元所属子网名称
	 */
	@Override
	public void getContainingSubnetworkNames(NameAndStringValue_T[] managedElementName, NamingAttributesList_THolder subnetNames) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	/**
	 * function:通过此方法来获取" 以太网层速率参数"
	 * 
	 * @param managedElementName
	 *            NMS传递的数据 NameAndStringValue_T[] 数组 name="网元ID" value="厂商识别号"
	 * @param tpLayerRateList
	 *            层速率
	 * @param connectionLayerRateList
	 *            ？？
	 * @param how_many
	 *            表示 第一批返回的数据个数
	 * @param tpList
	 *            要返回的数据 TerminationPointList_THolder（长度根据 == how_many）
	 * @param tpList
	 *            TerminationPointIterator_IHolder 迭代器 将多余的数据都存放在 tpList 中
	 * 
	 */
	@Override
	public void getAllPTPs(NameAndStringValue_T[] managedElementName, short[] tpLayerRateList, short[] connectionLayerRateList, int how_many, TerminationPointList_THolder tpList, TerminationPointIterator_IHolder tpIt) throws ProcessingFailureException {

		CorbaManageElementProxy proxy = null;
		LOG.info("[查询所有物理终端点]begin.");
		proxy = new CorbaManageElementProxy(this.userSession);
		proxy.getAllPTPs(managedElementName, tpLayerRateList, connectionLayerRateList, how_many, tpList, tpIt);
		LOG.info("[查询所有物理终端点]end.");
	}

	@Override
	public void getAllPTPsWithoutFTPs(NameAndStringValue_T[] managedElementName, short[] tpLayerRateList, short[] connectionLayerRateList, int how_many, TerminationPointList_THolder tpList, TerminationPointIterator_IHolder tpIt) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllFTPs(NameAndStringValue_T[] managedElementName, short[] tpLayerRateList, short[] connectionLayerRateList, int how_many, TerminationPointList_THolder tpList, TerminationPointIterator_IHolder tpIt) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllPTPNames(NameAndStringValue_T[] managedElementName, short[] tpLayerRateList, short[] connectionLayerRateList, int how_many, NamingAttributesList_THolder nameList, NamingAttributesIterator_IHolder nameIt) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllPTPNamesWithoutFTPs(NameAndStringValue_T[] managedElementName, short[] tpLayerRateList, short[] connectionLayerRateList, int how_many, NamingAttributesList_THolder nameList, NamingAttributesIterator_IHolder nameIt) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllFTPNames(NameAndStringValue_T[] managedElementName, short[] tpLayerRateList, short[] connectionLayerRateList, int how_many, NamingAttributesList_THolder nameList, NamingAttributesIterator_IHolder nameIt) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getTP(NameAndStringValue_T[] tpName, TerminationPoint_THolder tp) throws ProcessingFailureException {
		CorbaManageElementProxy proxy = null;
		LOG.info("[查询物理终端点]begin.");
		proxy = new CorbaManageElementProxy(this.userSession);
		proxy.getTP(tpName, tp);
		LOG.info("[查询物理终端点]end.");
	}

	@Override
	public void getManagedElement(NameAndStringValue_T[] managedElementName, ManagedElement_THolder me) throws ProcessingFailureException {
		CorbaManageElementProxy proxy = new CorbaManageElementProxy(this.userSession);
		try {
			proxy.getManagedElement(managedElementName, me);
		} catch (ProcessingFailureException ex) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "getManagedElement failed.");
		}
	}

	@Override
	public void getContainedPotentialTPs(NameAndStringValue_T[] tpName, short[] layerRateList, int how_many, TerminationPointList_THolder tpList, TerminationPointIterator_IHolder tpIt) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getContainedPotentialTPNames(NameAndStringValue_T[] tpName, short[] layerRateList, int how_many, NamingAttributesList_THolder nameList, NamingAttributesIterator_IHolder nameIt) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getContainedInUseTPs(NameAndStringValue_T[] tpName, short[] layerRateList, int how_many, TerminationPointList_THolder tpList, TerminationPointIterator_IHolder tpIt) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getContainedInUseTPNames(NameAndStringValue_T[] tpName, short[] layerRateList, int how_many, NamingAttributesList_THolder nameList, NamingAttributesIterator_IHolder nameIt) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getContainedCurrentTPs(NameAndStringValue_T[] tpName, short[] layerRateList, int how_many, TerminationPointList_THolder tpList, TerminationPointIterator_IHolder tpIt) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getContainedCurrentTPNames(NameAndStringValue_T[] tpName, short[] layerRateList, int how_many, NamingAttributesList_THolder nameList, NamingAttributesIterator_IHolder nameIt) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getContainingTPs(NameAndStringValue_T[] tpName, TerminationPointList_THolder tpList) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getContainingTPNames(NameAndStringValue_T[] tpName, NamingAttributesList_THolder tpNameList) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllActiveAlarms(NameAndStringValue_T[] meName, String[] excludeProbCauseList, PerceivedSeverity_T[] excludeSeverityList, int how_many, EventList_THolder eventList, EventIterator_IHolder eventIt) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllUnacknowledgedActiveAlarms(NameAndStringValue_T[] meName, String[] excludeProbCauseList, PerceivedSeverity_T[] excludeSeverityList, int how_many, EventList_THolder eventList, EventIterator_IHolder eventIt) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTPData(TPData_T tpInfo, TerminationPoint_THolder modifiedTP) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	/**
	 * function:查询tunnel或PW的数据
	 * 
	 * @param managedElementName
	 *            1、所要修改的厂商对象，建议用网管系统名称或网管系统标识符标识 2、所要修改的段的主键ID Name=类型，value=对象
	 * @param connectionRateList
	 *            层速率
	 * @param enforceUniqueness
	 *            是否检测新内容的唯一性，如检测，如不唯一将返回false
	 */

	@Override
	public void getAllCrossConnections(NameAndStringValue_T[] managedElementName, short[] connectionRateList, int how_many, CrossConnectList_THolder ccList, CCIterator_IHolder ccIt) throws ProcessingFailureException {
		// 验证用户权限 无权限抛异常 业务查看
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_SELECT);
		CorbaResourceProxy corbaResourceProxy = new CorbaResourceProxy(this.userSession);
		corbaResourceProxy.getAllCrossConnections(managedElementName, connectionRateList, how_many, ccList, ccIt);
	}

	@Override
	public void createGTP(String userLabel, boolean forceUniqueness, String owner, NameAndStringValue_T[][] listOfTPs, NameAndStringValue_T[] initialCTPname, int numberOfCTPs, GTPEffort_T gtpEffort, NameAndStringValue_T[] additionalCreationInfo, GTP_THolder newGTP) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteGTP(NameAndStringValue_T[] gtpName) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifyGTP(NameAndStringValue_T[] gtpName, NameAndStringValue_T[][] tpNames, String actionType, GTP_THolder modifiedGTP) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getGTP(NameAndStringValue_T[] gtpName, GTP_THolder gtp) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllGTPs(NameAndStringValue_T[] managedElementName, short[] tpLayerRateList, int how_many, GTPlist_THolder gtpList, GTPiterator_IHolder gtpIt) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllGTPNames(NameAndStringValue_T[] managedElementName, short[] tpLayerRateList, int how_many, NamingAttributesList_THolder nameList, NamingAttributesIterator_IHolder nameIt) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getContainingGTP(NameAndStringValue_T[] ctpName, GTP_THolder gtp) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setGtpAlarmReportingOn(NameAndStringValue_T[] gtpName) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setGtpAlarmReportingOff(NameAndStringValue_T[] gtpName) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void verifyTMDAssignment(NameAndStringValue_T[] tpName, Directionality_T direction, StringHolder tmdAssignmentState, LayeredParameterList_THolder transmissionParams, NVSList_THolder additionalTPInfo) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllFixedCrossConnections(NameAndStringValue_T[] managedElementName, short[] connectionRateList, int how_many, CrossConnectList_THolder ccList, CCIterator_IHolder ccIt) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getPotentialFixedCCs(NameAndStringValue_T[] inputTP, NVSList_THolder ContainingTP, NVSList_THolder potentialCCList) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setNativeEMSName(NameAndStringValue_T[] objectName, String nativeEMSName) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUserLabel(NameAndStringValue_T[] objectName, String userLabel, boolean enforceUniqueness) throws ProcessingFailureException {
		AuthorityManager.checkAuthority(this.userSession, RootFactory.DEPLOY_MANAGE);
		CorbaResourceProxy resProxy = new CorbaResourceProxy(userSession);
		resProxy.setUserLabel(objectName, userLabel);

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
		AuthorityManager.checkAuthority(this.userSession, RootFactory.DEPLOY_MANAGE);
		try {
			CorbaResourceProxy resProxy = new CorbaResourceProxy(userSession);
			resProxy.setAdditionalInfo(objectName, additionalInfo);
		} catch (Exception ex) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "internal error");
		}

	}

	/**
	 * 激活
	 */
	@Override
	public void activateCrossConnections(NameAndStringValue_T[] objectName) throws ProcessingFailureException {
		// 验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		CorbaResourceProxy corbaResourceProxy = new CorbaResourceProxy(this.userSession);
		corbaResourceProxy.activateCrossConnections(objectName);
		corbaResourceProxy = null;
	}

	/**
	 * 去激活
	 */
	@Override
	public void deactivateCrossConnections(NameAndStringValue_T[] objectName) throws ProcessingFailureException {
		// 验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		CorbaResourceProxy corbaResourceProxy = new CorbaResourceProxy(this.userSession);
		corbaResourceProxy.deactivateCrossConnections(objectName);
		corbaResourceProxy = null;
	}

	/**
	 * 删除
	 */
	@Override
	public void deleteCrossConnections(NameAndStringValue_T[] objectName) throws ProcessingFailureException {
		// 验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		CorbaResourceProxy corbaResourceProxy = new CorbaResourceProxy(this.userSession);
		corbaResourceProxy.deleteCrossConnections(objectName);
		corbaResourceProxy = null;
	}

	/**
	 * 根据网元id，名称查询某一条tunnel/pw
	 */
	@Override
	public void getCrossConnection(NameAndStringValue_T[] ccName, CrossConnect_THolder crossConnection) throws ProcessingFailureException {
		// 验证用户权限 无权限抛异常 业务查看
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_SELECT);
		CorbaResourceProxy corbaResourceProxy = new CorbaResourceProxy(this.userSession);
		corbaResourceProxy.getCrossConnection(ccName, crossConnection);
	}

	@Override
	public void setAlarmReportOff(NameAndStringValue_T[][] objectNameList, short[] rateList) throws ProcessingFailureException {
		// 验证用户权限 无权限抛异常 告警管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.ALARM_MANAGE);
	}

	@Override
	public void setAlarmReportOn(NameAndStringValue_T[][] objectNameList, short[] rateList) throws ProcessingFailureException {
		// TODO Auto-generated method stub
		// 验证用户权限 无权限抛异常 告警管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.ALARM_MANAGE);
	}

	/**
	 * function:创建单点PW或者tunnel
	 * 
	 * @param objectName
	 *            NMS传递的数据 datax/703B 数据格式: 1.name="EMS";value="Huawei/T2000" 2.name="ManagedElement";value="589825"
	 * @param ccList
	 *            创建业务的数据
	 * @param addSuccess
	 *            创建成功有那些数据
	 * @throws ProcessingFailureException
	 */
	public void createCrossConnections(NameAndStringValue_T[] nameAndStringValue_T, short layer, CrossConnect_T crossConnect_T, CrossConnect_THolder CrossConnect_THolder) throws ProcessingFailureException {
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		CorbaResourceProxy corbaResourceProxy = new CorbaResourceProxy(this.userSession);
		corbaResourceProxy.createCrossConnections(nameAndStringValue_T, layer, crossConnect_T, CrossConnect_THolder);
		corbaResourceProxy = null;
	}

}
