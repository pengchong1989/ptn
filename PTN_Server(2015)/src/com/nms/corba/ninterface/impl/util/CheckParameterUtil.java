package com.nms.corba.ninterface.impl.util;

import globaldefs.NameAndStringValue_T;

import com.nms.corba.ninterface.framework.CorbaConfig;

public class CheckParameterUtil {

	public static boolean checkEmsName(NameAndStringValue_T[] emsName) {
		if (emsName.length == 1) {
			if (checkEmsName(emsName[0])) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkMeName(NameAndStringValue_T[] meName) {
		if (meName.length == 2) {
			if (checkEmsName(meName[0]) && checkMeName(meName[1])) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkCCName(NameAndStringValue_T[] objName) {
		if (objName.length == 3) {
			if (checkEmsName(objName[0]) && checkMeName(objName[1])
					&& objName[2].name.equals("CrossConnection")) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkEquipmentName(NameAndStringValue_T[] objName) {
		if (objName.length == 4) {
			if (checkEmsName(objName[0]) && checkMeName(objName[1])
					&& objName[2].name.equals("EquipmentHolder")
					&& objName[3].name.equals("Equipment")
					&& objName[3].value.equals("1")) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean checkEquipmentHolderName(NameAndStringValue_T[] objName) {
		if (objName.length == 3) {
			if (checkEmsName(objName[0]) && checkMeName(objName[1])
					&& objName[2].name.equals("EquipmentHolder")) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkMLSubnetName(NameAndStringValue_T[] mLSncName) {
		if (mLSncName.length == 2) {
			if (checkEmsName(mLSncName[0]) && checkMLSubnetName(mLSncName[1])) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkSNCName(NameAndStringValue_T[] sncName) {
		if (sncName.length == 3) {
			if (checkEmsName(sncName[0]) && checkMLSubnetName(sncName[1])
					&& sncName[2].name.equals("SubnetworkConnection")) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkPTPName(NameAndStringValue_T[] sncName) {
		if (sncName.length == 3) {
			if (checkEmsName(sncName[0]) && checkMeName(sncName[1])
					&& sncName[2].name.equals("PTP")) {
				return true;
			}
		}
		return false;
	}
	//::::/rack=1/shelf=1/slot=1/port=ge.1.1

	public static boolean checkPGPName(NameAndStringValue_T[] reliableSinkCtpOrGroupName) {
		if (reliableSinkCtpOrGroupName.length == 3) {
			if (checkEmsName(reliableSinkCtpOrGroupName[0]) && checkMeName(reliableSinkCtpOrGroupName[1])
					&& reliableSinkCtpOrGroupName[2].name.equals("PGP")) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean checkASAPName(NameAndStringValue_T[] aSAPName) {
		if (aSAPName.length == 2) {
			if (checkEmsName(aSAPName[0]) && aSAPName[1].name.equals("ASAP") && aSAPName[1].value.equalsIgnoreCase("1")) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean checkEmsName(NameAndStringValue_T emsName) {
		String emsValue = CorbaConfig.getInstanse().getCorbaEmsName();
		if (emsName.name.equals("EMS") && emsName.value.equals(emsValue)) {
			return true;
		}
		return false;

	}

	private static boolean checkMeName(NameAndStringValue_T ObjName) {
		if (ObjName.name.equals("ManagedElement")) {
			return true;
		}
		return false;
	}

	private static boolean checkMLSubnetName(NameAndStringValue_T mLSncName) {
		if (mLSncName.name.equals("MultiLayerSubnetwork")
				&& mLSncName.value.equals("1")) {
			return true;
		}
		return false;
	}

	/**
	 * 验证拓扑链接名称
	 * @param topoLinkName	证拓扑链接名称
	 * @return
	 */
	public static boolean checkTopologicalLinkName(
			NameAndStringValue_T[] topoLinkName) {
		if (topoLinkName.length == 2) {
			//验证EMS、拓扑链接
			if (checkEmsName(topoLinkName[0]) && "TopologicalLink".equals(topoLinkName[1].name)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 验证TNP名称
	 * @param topoLinkName	TNP名称
	 * @return
	 */
	public static boolean checkTNPName(
			NameAndStringValue_T[] tnpName) {
		if (tnpName.length == 3) {
			//验证EMS、子网名称、tnp保护
			if (checkEmsName(tnpName[0]) && checkMLSubnetName(tnpName[1]) && "TrailNtwProtection".equals(tnpName[2].name)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 验证单点以太网业务名称
	 * @param mfdfrName	单点以太网业务
	 * @return
	 */
	public static boolean checkMFDFrName(
			NameAndStringValue_T[] mfdfrName) {
		if (mfdfrName.length == 3) {
			//验证EMS、子网名称、tnp保护
			if (checkEmsName(mfdfrName[0]) && checkMeName(mfdfrName[1]) && "MatrixFlowDomainFragment".equals(mfdfrName[2].name)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 验证MEG名称
	 * @param megName MEG名称
	 * @return
	 */
	public static boolean checkMEGName(
			NameAndStringValue_T[] megName) {
		if (megName.length == 3) {
			//验证EMS、网元、MEG
			if (checkEmsName(megName[0]) && checkMeName(megName[1]) && "MEG".equals(megName[2].name)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 验证QOS名称
	 * @param qpName QOS名称
	 * @return
	 */
	public static boolean checkQOSName(
			NameAndStringValue_T[] qpName) {
		if (qpName.length == 2) {
			//验证EMS、QoSProfile
			if (checkEmsName(qpName[0]) && "QoSProfile".equals(qpName[1].name)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 验证网络以太网业务名称
	 * @param mfdfrName	网络（端到端）以太网业务
	 * @return
	 */
	public static boolean checkFlowDomainName(
			NameAndStringValue_T[] fdfrName) {
		if (fdfrName.length == 3) {
			//验证EMS、流域，流域片段
			if (checkEmsName(fdfrName[0]) && checkFdfrName(fdfrName[1])&&"FlowDomainFragment".equals(fdfrName[2].name)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 验证网络以太网业务名称 ： 前2层结构
	 * @param mfdfrName	网络（端到端）以太网业务
	 * @return
	 */
	public static boolean checkFDName(
			NameAndStringValue_T[] fdfrName) {
		if (fdfrName.length == 2) {
			//验证EMS、子网名称、tnp保护
			if (checkEmsName(fdfrName[0]) && checkFdfrName(fdfrName[1])) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 验证 以太网第2层结构
	 * @param fdName
	 * @return
	 */
	private static boolean checkFdfrName(NameAndStringValue_T fdName){
		if("FlowDomain".equals(fdName.name) && "1".equals(fdName.value)){
			return true;
		}
		return false;
	}
	/**
	 * 验证 采集性能任务的名称 2层机构
	 * @param pmTaskName
	 * @return
	 */
	public static boolean checkPMTaskName(NameAndStringValue_T[] pmTaskName){
		if(checkEmsName(pmTaskName[0])&&pmTaskName[1].name.equals("PMTask")){
			return true;
		}
		return false;
	}
	
	
}
