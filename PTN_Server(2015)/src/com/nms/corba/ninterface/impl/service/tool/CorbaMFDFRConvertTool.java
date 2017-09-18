package com.nms.corba.ninterface.impl.service.tool;

import flowDomainFragment.MatrixFlowDomainFragment_T;
import globaldefs.ConnectionDirection_T;
import globaldefs.ExceptionType_T;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import transmissionParameters.LayeredParameters_T;

import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.impl.util.CheckParameterUtil;
import com.nms.corba.ninterface.util.CorbaConvertBase;
import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.path.ServiceInfo;
import com.nms.db.bean.ptn.path.eth.ElanInfo;
import com.nms.db.bean.ptn.path.eth.ElineInfo;
import com.nms.db.bean.ptn.path.eth.EtreeInfo;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.bean.ptn.qos.QosInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.EServiceType;
import com.nms.db.enums.QosCosLevelEnum;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.port.AcPortInfoService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.UiUtil;

/**
 * 单点以太网业务转换工具类
 * 
 * @author kk
 * 
 */
public class CorbaMFDFRConvertTool extends CorbaConvertBase {

	private final String VLANID = "VlanId";
	private final String EXITRULE = "ExitRule";
	private final String VLANPRI = "Vlanpri";
	private final String PORTMODEL = "PortModel"; // 端口模式
	private final String HORIZONTALDIVISION = "HorizontalDivision"; // 水平分割
	private final String MACADDRESSLEARN = "MacAddressLearn"; // MAC地址学习
	private final String ISBROADCAST = "IsBroadcast"; // 广播状态
	private final String BROADCAST = "Broadcast";// 广播门限
	private final String ISUNICAST = "IsUnicast"; // 单 播状态
	private final String UNICAST = "Unicast";// 单 播门限
	private final String ISMULTICAST = "IsMulticast"; // 组播状态
	private final String MULTICAST = "Multicast";// 组播门限
	private final String CIR = "Cir";
	private final String CBS = "Cbs";
	private final String EIR = "Eir";
	private final String EBS = "Ebs";
	private final String PIR = "Pir";

	/**
	 * 转换eline业务
	 * 
	 * @param siteId
	 * @param elineInfos
	 * @return
	 * @throws ProcessingFailureException
	 */
	public MatrixFlowDomainFragment_T[] convertMFDFr_eline(int siteId, List<ElineInfo> elineInfos) throws ProcessingFailureException {
		// 分为AZ两端
		MatrixFlowDomainFragment_T[] domainFragmentTs = new MatrixFlowDomainFragment_T[elineInfos.size()];
		int i = 0;
		try {
			for (ElineInfo elineInfo : elineInfos) {
				domainFragmentTs[i++] = this.convertMFDFr_eline(elineInfo);
			}
		}catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}

		return domainFragmentTs;
	}

	/**
	 * 转换elan业务
	 * 
	 * @param siteId
	 * @param elanInfoList
	 * @return
	 * @throws ProcessingFailureException
	 */
	public MatrixFlowDomainFragment_T[] convertMFDFr_elan(int siteId, Map<Integer, List<ElanInfo>> elanInfoMap) throws ProcessingFailureException {
		MatrixFlowDomainFragment_T[] domainFragmentTs = new MatrixFlowDomainFragment_T[elanInfoMap.keySet().size()];
		try {
			int i = 0;
			for (int key : elanInfoMap.keySet()) {
				domainFragmentTs[i++] = this.convertMFDFr_elan(elanInfoMap.get(key));
			}
		}catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}


		return domainFragmentTs;
	}

	/**
	 * 转换etree业务
	 * 
	 * @param siteId
	 * @param etreeInfoList
	 * @return
	 * @throws ProcessingFailureException
	 */
	public MatrixFlowDomainFragment_T[] convertMFDFr_etree(int siteId, Map<Integer, List<EtreeInfo>> etreeInfoMap) throws ProcessingFailureException {
		MatrixFlowDomainFragment_T[] domainFragmentTs = new MatrixFlowDomainFragment_T[etreeInfoMap.keySet().size()];
		try {
			int i = 0;
			for (int key : etreeInfoMap.keySet()) {
				domainFragmentTs[i++] = this.convertMFDFr_etree(etreeInfoMap.get(key));
			}
		}catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}


		return domainFragmentTs;
	}

	private MatrixFlowDomainFragment_T convertMFDFr(ServiceInfo serviceInfo, String mfdfrType, int acId, List<Integer> pwIdList) throws ProcessingFailureException {
		MatrixFlowDomainFragment_T mfdfr = null;
		AcPortInfoService_MB acInfoService = null;
		AcPortInfo acPortInfo = null;
		try {
			mfdfr = new MatrixFlowDomainFragment_T();
			mfdfr.direction = ConnectionDirection_T.CD_BI;
			mfdfr.active = serviceInfo.getActiveStatus() == EActiveStatus.ACTIVITY.getValue() ? true : false;
			mfdfr.mfdfrType = mfdfrType;
			mfdfr.flexible = true;
			mfdfr.transmissionParams = new LayeredParameters_T((short)ELayerRate.ETHSERVICE.getValue(), new NameAndStringValue_T[0]);

			// 根据AC主键查询AC对象
			acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			acPortInfo = acInfoService.selectById(acId);

			// A端为端口标识
			mfdfr.aEnd = new NameAndStringValue_T[][] { super.convertName(ELayerRate.PORT.getValue(), acPortInfo.getPortId(), acPortInfo.getSiteId()) };
			// Z端为PW标识
			mfdfr.zEnd = this.convertPwEnd(acPortInfo.getSiteId(), pwIdList);

			mfdfr.additionalInfo = this.convertAdditionalInfo(serviceInfo, acPortInfo);

		}catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		} finally {
			UiUtil.closeService_MB(acInfoService);
		}

		return mfdfr;
	}

	/**
	 * 把pw主键列表转换成NameAndStringValue_T[][]
	 * 
	 * @param siteId
	 *            网元主键
	 * @param pwIdList
	 *            pw主键列表
	 * @return
	 * @throws ProcessingFailureException
	 */
	private NameAndStringValue_T[][] convertPwEnd(int siteId, List<Integer> pwIdList) throws ProcessingFailureException {
		NameAndStringValue_T[][] zEnd = new NameAndStringValue_T[pwIdList.size()][];

		for (int i = 0; i < pwIdList.size(); i++) {
			zEnd[i] = super.convertName(ELayerRate.PW.getValue(), pwIdList.get(i), siteId);
		}
		return zEnd;
	}

	/**
	 * eline对象转换为MFDFR对象
	 * 
	 * @param elineInfo
	 *            eline对象
	 * @return
	 */
	public MatrixFlowDomainFragment_T convertMFDFr_eline(ElineInfo elineInfo) throws ProcessingFailureException {

		try {
			int acId = 0;
			if (elineInfo.getaSiteId() > 0) {
				acId = elineInfo.getaAcId();
			} else {
				acId = elineInfo.getzAcId();
			}

			List<Integer> pwIdList = new ArrayList<Integer>();
			pwIdList.add(elineInfo.getPwId());

			return this.convertMFDFr(elineInfo, EServiceType.ELINE.toString(), acId, pwIdList);
		}catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}

	}

	/**
	 * elan对象转换为MFDFR对象
	 * 
	 * @param elanInfoList
	 *            elan集合，是一组elan
	 * @return
	 * @throws ProcessingFailureException
	 */
	public MatrixFlowDomainFragment_T convertMFDFr_elan(Object object) throws ProcessingFailureException {

		try {
			List<ElanInfo> elanInfoList = (List<ElanInfo>) object;

			if (null == elanInfoList || elanInfoList.size() == 0) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
			}

			int acId = 0;
			if (elanInfoList.get(0).getaSiteId() > 0) {
				acId = elanInfoList.get(0).getaAcId();
			} else {
				acId = elanInfoList.get(0).getzAcId();
			}

			List<Integer> pwIdList = new ArrayList<Integer>();

			for (ElanInfo elanInfo : elanInfoList) {
				pwIdList.add(elanInfo.getPwId());
			}

			return this.convertMFDFr(elanInfoList.get(0), EServiceType.ELAN.toString(), acId, pwIdList);
		}catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}

	}

	/**
	 * etree对象转换为MFDFR对象
	 * 
	 * @param etreeInfoList
	 *            etree集合，是一组elan
	 * @return
	 * @throws ProcessingFailureException
	 */
	public MatrixFlowDomainFragment_T convertMFDFr_etree(Object object) throws ProcessingFailureException {

		try {
			List<EtreeInfo> etreeInfoList = (List<EtreeInfo>) object;

			if (null == etreeInfoList || etreeInfoList.size() == 0) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
			}

			int acId = 0;
			if (etreeInfoList.get(0).getRootSite() > 0) {
				acId = etreeInfoList.get(0).getaAcId();
			} else {
				acId = etreeInfoList.get(0).getzAcId();
			}

			List<Integer> pwIdList = new ArrayList<Integer>();

			for (EtreeInfo etreeInfo : etreeInfoList) {
				pwIdList.add(etreeInfo.getPwId());
			}

			return this.convertMFDFr(etreeInfoList.get(0), EServiceType.ETREE.toString(), acId, pwIdList);
		}catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}

	}

	/**
	 * 转换附加信息
	 * 
	 * @param serviceInfo
	 *            业务对象
	 * @param acPortInfo
	 *            ac端口对象
	 * @return
	 * @throws ProcessingFailureException
	 */
	private NameAndStringValue_T[] convertAdditionalInfo(ServiceInfo serviceInfo, AcPortInfo acPortInfo) throws ProcessingFailureException {
		NameAndStringValue_T[] nameAndStringValueArray = new NameAndStringValue_T[22];
		CorbaFlowDomainConvertTools corbaFlowDomainConvertTools = new CorbaFlowDomainConvertTools();
		try {
			nameAndStringValueArray[0] = new NameAndStringValue_T(super.USERLABEL, serviceInfo.getName()); // 友好名称
			nameAndStringValueArray[1] = new NameAndStringValue_T(super.NATIVEEMSNAME, serviceInfo.getName()); // 本地名称
			nameAndStringValueArray[2] = new NameAndStringValue_T(super.MATRIXFLOWDOMAINFRAGMENT, serviceInfo.getId() + ""); // 业务标识
			nameAndStringValueArray[3] = new NameAndStringValue_T(this.VLANID, acPortInfo.getVlanId());// vlanid=下话增加VLAN ID
			nameAndStringValueArray[4] = new NameAndStringValue_T(this.EXITRULE, corbaFlowDomainConvertTools.getExitRule_select(acPortInfo) + ""); // 出口规则，关联code表主键 = 下话TAG行为
			nameAndStringValueArray[5] = new NameAndStringValue_T(this.VLANPRI, acPortInfo.getVlanpri());// vlanpri=下话增加VLAN PRI
			nameAndStringValueArray[6] = new NameAndStringValue_T(this.PORTMODEL, UiUtil.getCodeById(acPortInfo.getPortModel()).getCodeValue());// 端口模式，关联code表主键

			// 水平分割
			String horizontalDivision = "false";
			if (acPortInfo.getHorizontalDivision() != 0) {
				horizontalDivision = UiUtil.getCodeById(acPortInfo.getHorizontalDivision()).getCodeValue().equals("0") ? "false" : "true";
			}
			nameAndStringValueArray[7] = new NameAndStringValue_T(this.HORIZONTALDIVISION, horizontalDivision);// 水平分割，false=关 true=开

			// mac地址学习
			String macAddressLearn = "false";
			if (acPortInfo.getMacAddressLearn() > 0) {
				macAddressLearn = UiUtil.getCodeById(acPortInfo.getMacAddressLearn()).getCodeValue().equals("0") ? "false" : "true";
			}
			nameAndStringValueArray[8] = new NameAndStringValue_T(this.MACADDRESSLEARN, macAddressLearn);// mac地址使能，false=不使能 true=使能

			int index = 9;
			index = this.convertPortInfo(nameAndStringValueArray, acPortInfo.getPortId(), index);
			this.convertQos(acPortInfo.getSimpleQos(), nameAndStringValueArray, index);

		}catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}

		return nameAndStringValueArray;
	}

	/**
	 * 把端口的信息转换到nameAndStringValueArray中 包括广播/组播/未知单播的报文抑制使能状态 、报文抑制门限
	 * 
	 * @param nameAndStringValueArray
	 * @param portId
	 *            端口主键
	 * @param index
	 *            nameAndStringValueArray下一个的索引
	 * @return 增加后的索引
	 * @throws ProcessingFailureException
	 */
	private int convertPortInfo(NameAndStringValue_T[] nameAndStringValueArray, int portId, int index) throws ProcessingFailureException {
		PortService_MB portService = null;
		List<PortInst> portInstList = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			PortInst portInst = new PortInst();
			portInst.setPortId(portId);
			portInstList = portService.select(portInst);

			if (null != portInstList && portInstList.size() == 1) {
				portInst = portInstList.get(0);
				nameAndStringValueArray[index++] = new NameAndStringValue_T(this.ISBROADCAST, this.getInhibition(portInst.getPortAttr().getPortUniAttr().getIsBroadcast()));
				nameAndStringValueArray[index++] = new NameAndStringValue_T(this.BROADCAST, portInst.getPortAttr().getPortUniAttr().getBroadcast() == null ? "" : portInst.getPortAttr().getPortUniAttr().getBroadcast());
				nameAndStringValueArray[index++] = new NameAndStringValue_T(this.ISUNICAST, this.getInhibition(portInst.getPortAttr().getPortUniAttr().getIsUnicast()));
				nameAndStringValueArray[index++] = new NameAndStringValue_T(this.UNICAST, portInst.getPortAttr().getPortUniAttr().getUnicast() == null ? "" : portInst.getPortAttr().getPortUniAttr().getUnicast());
				nameAndStringValueArray[index++] = new NameAndStringValue_T(this.ISMULTICAST, this.getInhibition(portInst.getPortAttr().getPortUniAttr().getIsMulticast()));
				nameAndStringValueArray[index++] = new NameAndStringValue_T(this.MULTICAST, portInst.getPortAttr().getPortUniAttr().getMulticast() == null ? "" : portInst.getPortAttr().getPortUniAttr().getMulticast());
			}

		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		} finally {
			UiUtil.closeService_MB(portService);
		}

		return index;
	}

	/**
	 * 转换广播/组播/未知单播的报文抑制使能状态
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	private String getInhibition(int value) throws ProcessingFailureException {
		String result = "";
		try {
			switch (value) {
			case 0:
				result = "false";
				break;
			case 1:
				result = "true";
				break;

			default:
				result = UiUtil.getCodeById(value).getCodeValue().equals("0") ? "false" : "true";
				break;
			}
		}catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}

		return result;
	}

	/**
	 * 把qos转换成nameAndStringValueArray对象
	 * 
	 * @param qosInfo
	 * @param nameAndStringValueArray
	 * @param index
	 */
	private void convertQos(QosInfo qosInfo, NameAndStringValue_T[] nameAndStringValueArray, int index) {
		nameAndStringValueArray[index++] = new NameAndStringValue_T(super.QOSTYPE, qosInfo.getQosType());
		nameAndStringValueArray[index++] = new NameAndStringValue_T(super.QOSCOS, QosCosLevelEnum.from(qosInfo.getCos()).toString());
		nameAndStringValueArray[index++] = new NameAndStringValue_T(this.CIR, qosInfo.getCir() + "");
		nameAndStringValueArray[index++] = new NameAndStringValue_T(this.CBS, qosInfo.getCbs() + "");
		nameAndStringValueArray[index++] = new NameAndStringValue_T(this.EIR, qosInfo.getEir() + "");
		nameAndStringValueArray[index++] = new NameAndStringValue_T(this.EBS, qosInfo.getEbs() + "");
		nameAndStringValueArray[index++] = new NameAndStringValue_T(this.PIR, qosInfo.getPir() + "");
	}

	/**
	 * 创建时。把corba转换为service对象
	 * 
	 * @param createData
	 * @param session
	 * @return
	 * @throws ProcessingFailureException
	 */
	public List<ServiceInfo> convertServiceInfo(MatrixFlowDomainFragment_T createData, ICorbaSession session) throws ProcessingFailureException {

		List<ServiceInfo> serviceInfoList = new ArrayList<ServiceInfo>();
		ServiceInfo serviceInfo = null;
		AcPortInfo acPortInfo = null;
		int siteId = 0;

		// A端为端口信息，只能有一个，Z端为pw信息可以有多个
		if (createData.aEnd.length == 1 && createData.zEnd.length > 0) {

			siteId = Integer.parseInt(createData.aEnd[0][1].value);
			acPortInfo = this.convertAc(createData.additionalInfo, Integer.parseInt(super.getValueByKey(createData.aEnd[0], super.PTP, siteId)), siteId);

			// 数据库的结构是，有几个pw、就有几条数据，所以直接遍历pw来创建serviceInfo
			for (NameAndStringValue_T[] pwNameArray : createData.zEnd) {

				if (!CheckParameterUtil.checkPTPName(createData.aEnd[0])) {
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "aEnd is error");
				}
				if (!CheckParameterUtil.checkCCName(pwNameArray)) {
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "zEnd is error");
				}

				// 创建eline对象
				if (EServiceType.ELINE.toString().equals(createData.mfdfrType)) {
					serviceInfo = new ElineInfo();
					serviceInfo.setServiceType(EServiceType.ELINE.getValue());
					serviceInfo.setaSiteId(Integer.parseInt(createData.aEnd[0][1].value));
					serviceInfo.setCreateAc_a(acPortInfo);

				} else if (EServiceType.ETREE.toString().equals(createData.mfdfrType)) {
					serviceInfo = new EtreeInfo();
					serviceInfo.setServiceType(EServiceType.ETREE.getValue());

					// 叶子节点、赋值Z端
					if (createData.zEnd.length == 1) {
						serviceInfo.setzSiteId(siteId);
						serviceInfo.setCreateAc_z(acPortInfo);
					} else {
						serviceInfo.setaSiteId(siteId);
						serviceInfo.setCreateAc_a(acPortInfo);
					}

				} else if (EServiceType.ELAN.toString().equals(createData.mfdfrType)) {
					serviceInfo = new ElanInfo();
					serviceInfo.setServiceType(EServiceType.ELAN.getValue());
					serviceInfo.setaSiteId(siteId);
					serviceInfo.setCreateAc_a(acPortInfo);
				} else {
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "mfdfrType is error");
				}

				serviceInfo.setName(super.getValueByKey(createData.additionalInfo, super.USERLABEL));
				serviceInfo.setPwId(super.getPrimaryKey(pwNameArray));
				serviceInfo.setActiveStatus(createData.active ? EActiveStatus.ACTIVITY.getValue() : EActiveStatus.UNACTIVITY.getValue());
				serviceInfo.setCreateUser(session.getUserName());
				serviceInfo.setCreateTime(DateUtil.getDate(DateUtil.FULLTIME));
				serviceInfo.setIsSingle(1);

				serviceInfoList.add(serviceInfo);
			}

		}
		return serviceInfoList;
	}

	/**
	 * 
	 * @param additionalInfo
	 * @param portId
	 * @param siteId
	 * @return
	 * @throws ProcessingFailureException
	 */
	private AcPortInfo convertAc(NameAndStringValue_T[] additionalInfo, int portId, int siteId) throws ProcessingFailureException {

		SiteService_MB siteService = null;
		AcPortInfo acPortInfo = null;
		CorbaFlowDomainConvertTools corbaFlowDomainConvertTools = new CorbaFlowDomainConvertTools();
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);

			acPortInfo = new AcPortInfo();
			acPortInfo.setName("AC_" + new Date().getTime());
			acPortInfo.setPortId(portId);
			acPortInfo.setSiteId(siteId);

			acPortInfo.setClientVlanId(super.getValueByKey(additionalInfo, this.VLANID));// vlanid=下话增加VLAN ID
			acPortInfo.setVlanpri(super.getValueByKey(additionalInfo, this.VLANPRI));// vlanpri=下话增加VLAN PRI
			acPortInfo.setBufType(UiUtil.getCodeByValue("BUFTYPE", "0").getId());
			acPortInfo.setAcStatus(EActiveStatus.ACTIVITY.getValue());

			if (siteService.getManufacturer(acPortInfo.getSiteId()) == EManufacturer.CHENXIAO.getValue()) {
				acPortInfo.setVlanId(1 + "");
				acPortInfo.setOperatorVlanId(1 + "");
				acPortInfo.setVlancri(0 + "");
				acPortInfo.setManagerEnable(UiUtil.getCodeByValue("MACLEARN", "0").getId());// 管理状态，关联code表主键
				acPortInfo.setPortModel(UiUtil.getCodeByValue("portModel", super.getValueByKey(additionalInfo, this.PORTMODEL)).getId());// 端口模式，关联code表主键
				acPortInfo.setExitRule(corbaFlowDomainConvertTools.getExitRule(true, super.getValueByKey(additionalInfo, this.EXITRULE)));// 出口规则，关联code表主键 = 下话TAG行为
			} else {
				acPortInfo.setPortModel(UiUtil.getCodeByValue("UNIPORTMODE", "0").getId());// 端口模式，关联code表主键
				acPortInfo.setMacAddressLearn(UiUtil.getCodeByValue("MACLEARN", super.getValueByKey(additionalInfo, this.MACADDRESSLEARN).equals("true") ? "1" : "0").getId());// MAC，关联code表主键
				acPortInfo.setHorizontalDivision(UiUtil.getCodeByValue("VCTRAFFICPOLICING", super.getValueByKey(additionalInfo, this.HORIZONTALDIVISION).equals("true") ? "1" : "0").getId());// 水平分割，关联code表主键
				acPortInfo.setManagerEnable(UiUtil.getCodeByValue("VCTRAFFICPOLICING", "0").getId());// TMC流量监管使能
				acPortInfo.setModel(UiUtil.getCodeByValue("MODEL", "0").getId());// 模式，关联code表主键
				acPortInfo.setExitRule(corbaFlowDomainConvertTools.getExitRule(false, super.getValueByKey(additionalInfo, this.EXITRULE)));// 出口规则，关联code表主键 = 下话TAG行为
				acPortInfo.setTagAction(UiUtil.getCodeByValue("TAGRECOGNITION", "0").getId());
				acPortInfo.setBufferList(super.getAcBufferList(siteId));
			}
			acPortInfo.setSimpleQos(this.convertQosinfo(additionalInfo, siteId));
		}catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		} finally {
			UiUtil.closeService_MB(siteService);
		}

		return acPortInfo;
	}

	/**
	 * 把附加信息转换为qos信息
	 * 
	 * @param additionalInfo
	 * @return
	 * @throws ProcessingFailureException
	 */
	private QosInfo convertQosinfo(NameAndStringValue_T[] additionalInfo, int siteId) throws ProcessingFailureException {
		QosInfo qosInfo = new QosInfo();
		try {
			qosInfo.setId(siteId);
			qosInfo.setQosType("L2");
			qosInfo.setCos(QosCosLevelEnum.from(super.getValueByKey(additionalInfo, super.QOSCOS)));
			qosInfo.setCir(Integer.parseInt(super.getValueByKey(additionalInfo, this.CIR)));
			qosInfo.setCbs(Integer.parseInt(super.getValueByKey(additionalInfo, this.CBS)));
			qosInfo.setEir(Integer.parseInt(super.getValueByKey(additionalInfo, this.EIR)));
			qosInfo.setEbs(Integer.parseInt(super.getValueByKey(additionalInfo, this.EBS)));
			qosInfo.setPir(Integer.parseInt(super.getValueByKey(additionalInfo, this.PIR)));

		}catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}

		return qosInfo;
	}

	/**
	 * 把附加信息转换为端口信息。包括― 使能广播/组播/未知单播报文抑制使能状态；（*），― 广播/组播/未知单播报文抑制门限；
	 * 
	 * @param portInst
	 * @param additionalInfo
	 * @throws ProcessingFailureException
	 */
	public void convertPort(PortInst portInst, NameAndStringValue_T[] additionalInfo) throws ProcessingFailureException {

		String isBroadcast = super.getValueByKey(additionalInfo, this.ISBROADCAST);
		String broadcast = super.getValueByKey(additionalInfo, this.BROADCAST);
		String isUnicast = super.getValueByKey(additionalInfo, this.ISUNICAST);
		String unicast = super.getValueByKey(additionalInfo, this.UNICAST);
		String isMulticast = super.getValueByKey(additionalInfo, this.ISMULTICAST);
		String multicast = super.getValueByKey(additionalInfo, this.MULTICAST);

		// 广播门限
		if (!"".equals(broadcast)) {
			portInst.getPortAttr().getPortUniAttr().setBroadcast(broadcast);
		}
		// 广播状态
		if (!"".equals(isBroadcast)) {
			portInst.getPortAttr().getPortUniAttr().setIsBroadcast(this.getInhibition(portInst.getSiteId(), isBroadcast));
			if (portInst.getPortAttr().getPortUniAttr().getIsBroadcast() == 1 && "".equals(portInst.getPortAttr().getPortUniAttr().getBroadcast())) {
				portInst.getPortAttr().getPortUniAttr().setBroadcast("15000000");
			} else if(portInst.getPortAttr().getPortUniAttr().getIsBroadcast() == 0 && !"".equals(portInst.getPortAttr().getPortUniAttr().getBroadcast())){
				portInst.getPortAttr().getPortUniAttr().setBroadcast("");
			}
		}
		
		// 单播门限
		if (!"".equals(unicast)) {
			portInst.getPortAttr().getPortUniAttr().setUnicast(unicast);
		}
		// 单播状态
		if (!"".equals(isUnicast)) {
			portInst.getPortAttr().getPortUniAttr().setIsUnicast(this.getInhibition(portInst.getSiteId(), isUnicast));
			if (portInst.getPortAttr().getPortUniAttr().getIsUnicast() == 1 && "".equals(portInst.getPortAttr().getPortUniAttr().getUnicast())) {
				portInst.getPortAttr().getPortUniAttr().setUnicast("15000000");
			}else if(portInst.getPortAttr().getPortUniAttr().getIsUnicast() == 0 && !"".equals(portInst.getPortAttr().getPortUniAttr().getUnicast())){
				portInst.getPortAttr().getPortUniAttr().setUnicast("");
			}
		}
		
		// 组播门限
		if (!"".equals(multicast)) {
			portInst.getPortAttr().getPortUniAttr().setMulticast(multicast);
		}
		// 组播状态
		if (!"".equals(isMulticast)) {
			portInst.getPortAttr().getPortUniAttr().setIsMulticast(this.getInhibition(portInst.getSiteId(), isMulticast));
			if (portInst.getPortAttr().getPortUniAttr().getIsMulticast() == 1 && "".equals(portInst.getPortAttr().getPortUniAttr().getMulticast())) {
				portInst.getPortAttr().getPortUniAttr().setMulticast("15000000");
			}else if(portInst.getPortAttr().getPortUniAttr().getIsMulticast() == 0 && !"".equals(portInst.getPortAttr().getPortUniAttr().getMulticast())){
				portInst.getPortAttr().getPortUniAttr().setMulticast("");
			}
		}

	}

	/**
	 * 转换广播/组播/未知单播的报文抑制使能状态 corba转db
	 * 
	 * @param siteId
	 *            判断武汉还是晨晓
	 * @param value
	 *            corba传入值 false 或 true
	 * @return
	 * @throws ProcessingFailureException
	 */
	private int getInhibition(int siteId, String value) throws ProcessingFailureException {
		String convertValue = "";
		SiteService_MB siteService = null;
		int result = 0;
		try {

			if ("true".equals(value)) {
				convertValue = "1";
			} else {
				convertValue = "0";
			}

			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			if (siteService.getManufacturer(siteId) == EManufacturer.CHENXIAO.getValue()) {
				result = Integer.parseInt(convertValue);
			} else {
				result = UiUtil.getCodeByValue("VCTRAFFICPOLICING", convertValue).getId();
			}
		}catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}finally {
			UiUtil.closeService_MB(siteService);
		}


		return result;
	}
}
