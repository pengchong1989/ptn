package com.nms.corba.ninterface.impl.security;

import globaldefs.ExceptionType_T;
import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.NamingAttributesIterator_IHolder;
import globaldefs.NamingAttributesList_THolder;
import globaldefs.ProcessingFailureException;

import org.apache.log4j.Logger;
import org.omg.CORBA.StringHolder;

import subnetworkConnection.TPDataList_THolder;
import terminationPoint.TerminationPointIterator_IHolder;
import terminationPoint.TerminationPointList_THolder;
import transmissionDescriptor.QoSProfileCreateData_T;
import transmissionDescriptor.QoSProfileIterator_IHolder;
import transmissionDescriptor.QoSProfileList_THolder;
import transmissionDescriptor.QoSProfile_T;
import transmissionDescriptor.QoSProfile_THolder;
import transmissionDescriptor.TMDCreateData_T;
import transmissionDescriptor.TMDModifyData_T;
import transmissionDescriptor.TransmissionDescriptorIterator_IHolder;
import transmissionDescriptor.TransmissionDescriptorList_THolder;
import transmissionDescriptor.TransmissionDescriptorMgr_IPOA;
import transmissionDescriptor.TransmissionDescriptor_THolder;
import transmissionParameters.LayeredParameterList_THolder;

import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.impl.security.proxy.CorbaTransmissionProxy;
import com.nms.corba.ninterface.util.AuthorityManager;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
import common.CapabilityList_THolder;

public class TransmissionDescriptorMgr_Impl extends
		TransmissionDescriptorMgr_IPOA {
	private Logger LOG = Logger.getLogger(TransmissionDescriptorMgr_Impl.class.getName());
	private ICorbaSession userSession = null;

	public TransmissionDescriptorMgr_Impl(ICorbaSession userSession) {
		this.userSession = userSession;
	}
	
	@Override
	public void getAllTransmissionDescriptors(int how_many,
			TransmissionDescriptorList_THolder transmissionDescList,
			TransmissionDescriptorIterator_IHolder transmissionDescIt)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllTransmissionDescriptorNames(int how_many,
			NamingAttributesList_THolder nameList,
			NamingAttributesIterator_IHolder nameIt)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getTransmissionDescriptor(NameAndStringValue_T[] tmdName,
			TransmissionDescriptor_THolder tmd)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAssociatedTPs(
			NameAndStringValue_T[] transmissionDescriptorName, int how_many,
			TerminationPointList_THolder tpList,
			TerminationPointIterator_IHolder tpIt)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void createTransmissionDescriptor(TMDCreateData_T newTMDCreateData,
			TransmissionDescriptor_THolder newTransmissionDescriptor)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteTransmissionDescriptor(
			NameAndStringValue_T[] transmissionDescriptorName)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifyTransmissionDescriptor(NameAndStringValue_T[] tmdName,
			TMDModifyData_T tmdModifyData, TPDataList_THolder tpsToModify,
			TransmissionDescriptor_THolder modifiedTransmissionDescriptor,
			NamingAttributesList_THolder failedMEList,
			NamingAttributesList_THolder failedTPsMFDsList,
			StringHolder errorReason) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void validateTMDAssignmentToObject(
			NameAndStringValue_T[] objectName,
			StringHolder objectAssignmentState,
			LayeredParameterList_THolder transmissionParams,
			NVSList_THolder additionalTPInfo) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	/**
	 * @author guoqc
	 * 查询所有QoS策略描述属性
	 * 入参  how_many
	 * 出参 qosProfileList qos信息
	 * 出参 qosProfileIt 迭代器
	 */
	@Override
	public void getAllQoSProfile(int how_many, QoSProfileList_THolder qosProfileList,
									QoSProfileIterator_IHolder qosProfileIt)
										throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 业务查看
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_SELECT);
		CorbaTransmissionProxy corbaTransmissionProxy = new CorbaTransmissionProxy(this.userSession);
		corbaTransmissionProxy.getAllQoSProfile(how_many, qosProfileList, qosProfileIt);
	}

	/**
	 * @author guoqc
	 * 查询某一条QoS策略描述属性
	 * 入参 qpName 网元ID，业务类型，业务ID
	 * 出参 qp qos信息
	 */
	@Override
	public void getQoSProfile(NameAndStringValue_T[] qpName, QoSProfile_THolder qp)
								throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 业务查看
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_SELECT);
		CorbaTransmissionProxy corbaTransmissionProxy = new CorbaTransmissionProxy(this.userSession);
		corbaTransmissionProxy.getQoSProfile(qpName, qp);
	}

	@Override
	public void createQoSProfile(QoSProfileCreateData_T newQPCreateData,
			QoSProfile_THolder qp) throws ProcessingFailureException {
		// TODO Auto-generated method stub
		//验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
	}

	/**
	 * @author guoqc
	 * 删除一条QoS策略描述属性
	 * arg0  需要删除的qos信息
	 */
	@Override
	public void deleteQoSProfile(NameAndStringValue_T[] qpName)
									throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		CorbaTransmissionProxy corbaTransmissionProxy = new CorbaTransmissionProxy(this.userSession);
		corbaTransmissionProxy.deleteQoSProfile(qpName);
	}

	/**
	 * @author guoqc
	 * 修改某一条QoS策略描述属性
	 * 入参  modifyQPData 需要修改的qos信息
	 * 出参  qosprofile 修改后的qos信息
	 */
	@Override
	public void modifyQoSProfile(QoSProfile_T modifyQPData, QoSProfile_THolder qp)
									throws ProcessingFailureException {
		
		try {
			//验证用户权限 无权限抛异常 业务管理
			AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
			CorbaTransmissionProxy corbaTransmissionProxy = new CorbaTransmissionProxy(this.userSession);
			corbaTransmissionProxy.modifyQoSProfile(modifyQPData, qp);
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"modifyQoSProfile ex.");
		}
	}

	@Override
	public void assignQoSProfile(NameAndStringValue_T[] qpName,
			NameAndStringValue_T[][] objectNameList, short layerRate,
			int pkgType, NameAndStringValue_T[] additionalInfo)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub
		//验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
	}

	@Override
	public void unassignQoSProfile(NameAndStringValue_T[] qpName,
			NameAndStringValue_T[][] objectNameList, short layerRate,
			int pkgType, NameAndStringValue_T[] additionalInfo)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub
		//验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
	}

	@Override
	public void getQoSProfileAssignedObject(NameAndStringValue_T[] qpName,
			NamingAttributesList_THolder objectNameList)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub
		//验证用户权限 无权限抛异常 业务查看
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_SELECT);
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
