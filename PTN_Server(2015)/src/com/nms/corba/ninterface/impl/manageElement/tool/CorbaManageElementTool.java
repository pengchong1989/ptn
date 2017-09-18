package com.nms.corba.ninterface.impl.manageElement.tool;

import globaldefs.ExceptionType_T;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import managedElement.CommunicationState_T;
import managedElement.ManagedElement_T;
import terminationPoint.Directionality_T;
import terminationPoint.TPConnectionState_T;
import terminationPoint.TPProtectionAssociation_T;
import terminationPoint.TPType_T;
import terminationPoint.TerminationMode_T;
import terminationPoint.TerminationPoint_T;
import transmissionParameters.LayeredParameters_T;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.ninterface.impl.resource.tool.CorbaAlarmTool;
import com.nms.corba.ninterface.util.CorbaConvertBase;
import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.equipment.slot.SlotInst;
import com.nms.db.bean.system.code.Code;
import com.nms.db.enums.EManufacturer;
import com.nms.model.alarm.CurAlarmService_MB;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysLbl;

/**
 * 端口、网元对象转换类
 * 
 * @author kk
 * 
 */
public class CorbaManageElementTool extends CorbaConvertBase {

	private String METYPE="MeType";//网元类型 ：PTN
	private String MECELLTYPE="MeCellType";//网元型号：700A
	private String MEALAM="MeAlam";
	private String MEIP="MeIP";//网元IP
	private String DISCHARDWARE="HardWare";//网元硬件版本
	private String PROVIDER="Provider";//网元供应商
	public  void convertManagedElementListDATAX2Corba(List<SiteInst> siteInstList, ManagedElement_T[] meList) throws ProcessingFailureException {
		int i = 0;
		for(Iterator<SiteInst> iter = siteInstList.iterator(); iter.hasNext();){
			ManagedElement_T me = new ManagedElement_T();
			convertManagedElementDATAX2Corba((SiteInst)iter.next(), me);
			meList[i] = me;
			i++;
		}
	}
	
	public  void convertManagedElementDATAX2Corba(SiteInst siteInst, ManagedElement_T me) throws ProcessingFailureException {
		
		CurAlarmService_MB currentAlamService=null;
		SlotInst slotInst=new SlotInst();
		try {
			me.name = new NameAndStringValue_T[2];//网元标示符
			me.name[0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
			me.name[1] = new NameAndStringValue_T("ManagedElement", siteInst.getSite_Inst_Id()+"");//网元ID
			me.communicationState = siteInst.getLoginstatus()==1?CommunicationState_T.CS_AVAILABLE:CommunicationState_T.CS_UNAVAILABLE;//―	网元的运行状态（可用或不可用）；
			me.location = "";
			me.emsInSyncState = true;
			me.nativeEMSName = siteInst.getCellId();
			me.owner =CorbaConfig.getInstanse().getEmsVendorName();//DATAX
			int manufacturer=Integer.parseInt(UiUtil.getCodeById(Integer.parseInt(siteInst.getCellEditon())).getCodeValue());//判断是武汉还是陈晓
			if(EManufacturer.CHENXIAO.getValue()==manufacturer){
				me.productName = "700系列";//―	产品名称
			}else{
				me.productName =  "700+系列";//―	产品名称
			}
			
			//―	网元可能支持的连接速率列表
			me.supportedRates = new short[]{(short) ELayerRate.TUNNEL.getValue(),(short) ELayerRate.PW.getValue()
					,(short) ELayerRate.CES.getValue(),(short) ELayerRate.MSP.getValue(),(short) ELayerRate.CLOCKSOURCE.getValue(),(short)ELayerRate.ETHSERVICE.getValue()};
			me.userLabel = siteInst.getCellId();
			me.version = (siteInst.getVersions() == null)? "":siteInst.getVersions();//软件版本			
			currentAlamService=(CurAlarmService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CurrentAlarm);
			slotInst.setSiteId(siteInst.getSite_Inst_Id());
			int alamLevel=currentAlamService.queryTopAlarm(slotInst);//int高级等级
			me.additionalInfo = new NameAndStringValue_T[8];
			me.additionalInfo[0] = new NameAndStringValue_T(this.METYPE,"PTN");//网元类型
			me.additionalInfo[1] = new NameAndStringValue_T(this.MECELLTYPE,siteInst.getCellType());//网元型号700a			
			me.additionalInfo[2] = new NameAndStringValue_T(this.MEALAM,CorbaAlarmTool.convertPerceivedSeverity2Corba(alamLevel).toString());//""标识没有告警（如虚拟网元）
			me.additionalInfo[3] = new NameAndStringValue_T(this.MEIP,siteInst.getCellDescribe());//网元IP
			me.additionalInfo[4] = new NameAndStringValue_T(super.CREATETIME,siteInst.getCreateTime());//创建时间
			me.additionalInfo[5] = new NameAndStringValue_T(super.CREATEUSER,siteInst.getCreateUser());//创建人
			me.additionalInfo[6] = new NameAndStringValue_T(this.DISCHARDWARE,"1.0");//硬件版本
			me.additionalInfo[7] = new NameAndStringValue_T(this.PROVIDER,CorbaConfig.getInstanse().getEmsVendorName());//网元供应商
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "转为Corba对象失败");
		} finally {
			UiUtil.closeService_MB(currentAlamService);
		}
	}

	/**
	 * function:根据网元ID 查询该网元的所有port的速率
	 * 
	 * @param value
	 *            网元ID
	 * @return TerminationPoint_T[] terminationPoint_T = new TerminationPoint_T[1];
	 */
	public TerminationPoint_T[] convertManagedElementPTPsDATAX2Corba(String siteId, short[] tpLayerRateList) throws ProcessingFailureException {

		PortService_MB portService = null;
		List<PortInst> portInstList = null;
		PortInst portInst = null;
		List<PortInst> ethPort = new ArrayList<PortInst>();
		List<PortInst> pdhPort = new ArrayList<PortInst>();
		TerminationPoint_T[] result = null;
		TerminationPoint_T[] tp_eth = new TerminationPoint_T[0];
		TerminationPoint_T[] tp_pdh = new TerminationPoint_T[0];
		short layer = 0;
		try {
			// 根据网元主键把端口查询出来
			portInst = new PortInst();
			portInst.setSiteId(Integer.parseInt(siteId));
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			portInstList = portService.select(portInst);

			// 把端口按照类型分类
			for (PortInst portInfo : portInstList) {
				layer = this.getLayer(portInfo);
				if (layer == ELayerRate.PORT.getValue()) {
					ethPort.add(portInfo);
				} else if (layer == ELayerRate.TDMPORT.getValue()) {
					pdhPort.add(portInfo);
				}
			}

			// 如果查询的条件中包含eth端口，那么转换eth端口
			if (tpLayerRateList.length == 0 || super.isExitArrays(tpLayerRateList, (short) ELayerRate.PORT.getValue())) {
				tp_eth = setPortToTerminationPoint(ethPort, (short) ELayerRate.PORT.getValue());
			}
			// 如果查询的条件中包含pdh端口，那么转换pdh端口
			if (tpLayerRateList.length == 0 || super.isExitArrays(tpLayerRateList, (short) ELayerRate.TDMPORT.getValue())) {
				tp_pdh = setPortToTerminationPoint(pdhPort, (short) ELayerRate.TDMPORT.getValue());
			}

			result = super.concatAll(tp_eth, tp_pdh);
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "convertManagedElementPTPsDATAX2Corba.");
		} finally {
			UiUtil.closeService_MB(portService);
			portInstList = null;
			portInst = null;
		}
		return result;
	}

	/**
	 * 获取不同层次的端口属性
	 * 
	 * @param portList
	 * @return
	 * @throws Exception
	 */
	private TerminationPoint_T[] setPortToTerminationPoint(List<PortInst> portList, short layer) throws ProcessingFailureException {
		TerminationPoint_T[] terminationPointArray = new TerminationPoint_T[portList.size()];
		;
		try {
			for (int i = 0; i < portList.size(); i++) {
				terminationPointArray[i] = this.convertTerminationPoint(portList.get(i), layer);
			}
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "setPortToTerminationPoint.");
		}
		return terminationPointArray;
	}

	/**
	 * @param portInst
	 *            端口信息
	 * @return 端口的速率
	 */
	private String setPortRate(PortInst portInst) throws ProcessingFailureException {

		/**
		 * 自动协商/1000M全双工/100M全双工/10M全双工 fe 端口的 “自动协商”相当于 “100M” ge 端口的 “自动协商”相当于 “1000M” xg 端口的 “自动协商”相当于 “10000M” fx 端口的 “自动协商”相当于 “1000M”
		 */
		Code portWorkModel = null;
		try {
			portWorkModel = UiUtil.getCodeById(portInst.getPortAttr().getWorkModel() == 0 ? 70 : portInst.getPortAttr().getWorkModel());
			// 端口为"自动协商"且 端口属于”fe“类型的
			if (portInst.getPortName().contains("fe") || portInst.getPortName().contains("ge") || portInst.getPortName().contains("xg") || portInst.getPortName().contains("fx")) {
				if (portWorkModel.getCodeName().equals(ResourceUtil.srcStr(StringKeysLbl.LBLZIDONG_NAME)) && portInst.getPortName().contains("fe")) {
					return "100M";
				}
				if (portWorkModel.getCodeName().equals(ResourceUtil.srcStr(StringKeysLbl.LBLZIDONG_NAME)) && portInst.getPortName().contains("ge")) {
					return "1000M";
				}
				if (portWorkModel.getCodeName().equals(ResourceUtil.srcStr(StringKeysLbl.LBLZIDONG_NAME)) && portInst.getPortName().contains("xg")) {
					return "10000M";
				}
				if (portWorkModel.getCodeName().equals(ResourceUtil.srcStr(StringKeysLbl.LBLZIDONG_NAME)) && portInst.getPortName().contains("fx")) {
					return "1000M";
				} else {
					return portWorkModel.getCodeName();
				}
			} else {
				return "0";
			}
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "setPortRate.");
		} finally {
			portWorkModel = null;
		}
	}

	/**
	 * 把端口转换为TerminationPoint_T corba对象
	 * 
	 * @param portInst
	 *            网管端口对象
	 * @return corba终端点对象
	 * @throws ProcessingFailureException
	 */
	public TerminationPoint_T convertTerminationPoint(PortInst portInst, short layer) throws ProcessingFailureException {
		TerminationPoint_T terminationPoint = new TerminationPoint_T();
		try {
			terminationPoint = new TerminationPoint_T();
			// 转换层速率
			terminationPoint.transmissionParams = this.convertLayeredParameters(portInst, layer);

			terminationPoint.name = super.convertName(ELayerRate.PORT.getValue(), portInst.getPortId(), portInst.getSiteId());
			terminationPoint.userLabel = portInst.getPortName();
			terminationPoint.nativeEMSName = portInst.getPortName();
			terminationPoint.owner = super.getOwner();
			terminationPoint.type = TPType_T.TPT_PTP;
			terminationPoint.additionalInfo = this.convertAdditionalInfo(portInst);
			terminationPoint.direction = Directionality_T.D_BIDIRECTIONAL;
			if (this.getUsingState(portInst)) {
				terminationPoint.connectionState = TPConnectionState_T.TPCS_BI_CONNECTED; // 已经连接
			} else {
				terminationPoint.connectionState = TPConnectionState_T.TPCS_NOT_CONNECTED; // 未连接
			}
			terminationPoint.tpMappingMode = TerminationMode_T.TM_NA;
			terminationPoint.tpProtectionAssociation = TPProtectionAssociation_T.TPPA_NA;
			terminationPoint.edgePoint = true;
			terminationPoint.ingressTrafficDescriptorName = new NameAndStringValue_T[0];
			terminationPoint.egressTrafficDescriptorName = new NameAndStringValue_T[0];
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, e.getMessage());
		}
		return terminationPoint;
	}

	/**
	 * 转换层速率对象
	 * 
	 * @param portInst
	 *            端口对象
	 * @return 附加信息对象
	 * @throws ProcessingFailureException
	 */
	private LayeredParameters_T[] convertLayeredParameters(PortInst portInst, short layer) throws ProcessingFailureException {

		LayeredParameters_T layeredParameters = null;
		LayeredParameters_T[] layeredParametersArray = new LayeredParameters_T[1];
		try {
			// 获取层速率下的附加信息
			NameAndStringValue_T[] nameAndStringValueArray = convertTransmissionParams(portInst, layer);

			layeredParameters = new LayeredParameters_T(layer, nameAndStringValueArray);
			layeredParametersArray[0] = layeredParameters;
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, e.getMessage());
		}

		return layeredParametersArray;
	}

	/**
	 * 获取端口的附加信息
	 * 
	 * @param portInst
	 *            端口对象
	 * @return 附加信息对象
	 * @throws Exception
	 */
	private NameAndStringValue_T[] convertAdditionalInfo(PortInst portInst) throws ProcessingFailureException {
		NameAndStringValue_T[] nameAndStringValueArray = new NameAndStringValue_T[3];
		nameAndStringValueArray[0] = new NameAndStringValue_T("card", "/rack=1/shelf=1/slot="+portInst.getSlotNumber()+"/card=1");
		nameAndStringValueArray[1] = new NameAndStringValue_T("serialNumber", portInst.getNumber() + "");
		nameAndStringValueArray[2] = new NameAndStringValue_T("PortServiceType", portInst.getModuleType().equals("(e)")?"electronic":"optical");
		return nameAndStringValueArray;
	}

	/**
	 * 获取端口的使用状态，以太网端口没有段为未使用， e1端口没有创建业务为未使用
	 * 
	 * @param portInst
	 * @return true=占用 false=空闲
	 * @throws Exception
	 */
	private boolean getUsingState(PortInst portInst) throws ProcessingFailureException {
		boolean result = false;
		PortService_MB portService = null;
		try {
			if ("e1".equals(portInst.getPortType())) {
				if (portInst.getIsOccupy() == 0) {
					result = true;
				}
			} else if ("NONE".equals(portInst.getPortType()) || "NNI".equals(portInst.getPortType()) || "UNI".equals(portInst.getPortType())) {
				portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
				result = portService.getPortUse(portInst);
			}
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "getUsingState.");
		} finally {
			UiUtil.closeService_MB(portService);
		}

		return result;
	}

	/**
	 * 获取层速率的附加信息
	 * 
	 * @param portInst
	 * @param layer
	 *            速率 用此字段验证是哪个端口
	 * @return
	 * @throws Exception
	 */
	private NameAndStringValue_T[] convertTransmissionParams(PortInst portInst, short layer) throws ProcessingFailureException {
		NameAndStringValue_T[] nameAndStringValueArray = null;
		try {
			// ETH端口
			if (layer == ELayerRate.PORT.getValue()) {
				nameAndStringValueArray = new NameAndStringValue_T[5];// 对应的属性值(根据移动规范ETH速率有9对值)
				nameAndStringValueArray[0] = new NameAndStringValue_T("DuplexMode", this.getPortModel(portInst));
				nameAndStringValueArray[1] = new NameAndStringValue_T("ErateLevel", setPortRate(portInst));
				nameAndStringValueArray[2] = new NameAndStringValue_T("JUMBOLength", portInst.getPortAttr().getMaxFrameSize());
				nameAndStringValueArray[3] = new NameAndStringValue_T("FlowControlMode", UiUtil.getCodeById(portInst.getPortAttr().getFluidControl()).getCodeValue().equals("0") ? "Disabled" : "Enabled");
				nameAndStringValueArray[4] = new NameAndStringValue_T("PortType", portInst.getPortType());
				// nameAndStringValueArray[4] = new NameAndStringValue_T("portMACAddress", portInst.getMacAddress());
				// nameAndStringValueArray[5] = new NameAndStringValue_T("PortPhysicalParameters", "");
				// nameAndStringValueArray[6] = new NameAndStringValue_T("PortTAGProperty", "");
				// nameAndStringValueArray[7] = new NameAndStringValue_T("VLAN ID", portInst.getPortAttr().getPortUniAttr().getVlanId() == null ? "" : portInst.getPortAttr().getPortUniAttr().getVlanId());
				// nameAndStringValueArray[8] = new NameAndStringValue_T("VLANPriority", portInst.getPortAttr().getPortUniAttr().getVlanPri() == null ? "" : portInst.getPortAttr().getPortUniAttr().getVlanPri());
			} else if (layer == ELayerRate.TDMPORT.getValue()) {
				nameAndStringValueArray = new NameAndStringValue_T[1];
				nameAndStringValueArray[0] = new NameAndStringValue_T("layer",ELayerRate.TDMPORT.getValue()+"");
				// nameAndStringValueArray[1] = new NameAndStringValue_T("channelizing", "");
				// nameAndStringValueArray[2] = new NameAndStringValue_T("framing", "");
			} else {
				nameAndStringValueArray = new NameAndStringValue_T[0];
			}
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "convertTransmissionParams.");
		}

		return nameAndStringValueArray;
	}

	/**
	 * 获取端口的模式
	 * 
	 * @param portInst
	 * @return NEG=自动协商 Full=全双工
	 * @throws Exception
	 */
	private String getPortModel(PortInst portInst) throws ProcessingFailureException {
		String result = "AutoDuplex";
		try {
			if (portInst.getPortAttr().getWorkModel() != 0) {
				if (!"0".equals(UiUtil.getCodeById(portInst.getPortAttr().getWorkModel()).getCodeValue())) {
					result = "FullDuplex";
				}
			}
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "getPortModel.");
		}
		return result;
	}

	/**
	 * 计算端口速率
	 * 
	 * @param portInst
	 * @return
	 * @throws ProcessingFailureException
	 */
	public short getLayer(PortInst portInst) throws ProcessingFailureException {
		short layer = 0;
		try {
			// 计算端口的速率
			if ("NONE".equals(portInst.getPortType()) || "NNI".equals(portInst.getPortType()) || "UNI".equals(portInst.getPortType())) {
				layer = (short) ELayerRate.PORT.getValue();
			} else if ("e1".equals(portInst.getPortType())) {
				layer = (short) ELayerRate.TDMPORT.getValue();
			}
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "getLayer.");
		}
		return layer;
	}
}
