package com.nms.corba.ninterface.impl.resource.tool;

import globaldefs.ConnectionDirection_T;
import globaldefs.ExceptionType_T;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import java.util.ArrayList;
import java.util.List;

import subnetworkConnection.CrossConnect_T;
import subnetworkConnection.CrossConnect_THolder;
import subnetworkConnection.Route_THolder;
import subnetworkConnection.SNCType_T;
import topologicalLink.TopologicalLink_T;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.impl.service.tool.CorbaServiceConvrtTool;
import com.nms.corba.ninterface.util.CorbaConvertBase;
import com.nms.corba.ninterface.util.DateTimeUtil;
import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.db.bean.path.Segment;
import com.nms.db.bean.ptn.path.ces.CesInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.tunnel.Lsp;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.bean.ptn.qos.QosInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.ECesType;
import com.nms.db.enums.EPwType;
import com.nms.db.enums.EQosDirection;
import com.nms.db.enums.EServiceType;
import com.nms.db.enums.QosCosLevelEnum;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.util.Services;
import com.nms.service.impl.dispatch.CesDispatch;
import com.nms.service.impl.dispatch.PwDispatch;
import com.nms.service.impl.dispatch.TunnelDispatch;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.VerifyNameUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class CorbaResConvertTool extends CorbaConvertBase {

	private final String CROSSCONNECTION = "CrossConnection";
	private final String LAYER = "Layer";
	private final String INGRESSCIR = "IngressCIR";
	private final String INGRESSCBS = "IngressCBS";
	private final String INGRESSEIR = "IngressEIR";
	private final String INGRESSEBS = "IngressEBS";
	private final String INGRESSPIR = "IngressPIR";
	private final String EGRESSCIR = "EgressCIR";
	private final String EGRESSCBS = "EgressCBS";
	private final String EGRESSEIR = "EgressEIR";
	private final String EGRESSEBS = "EgressEBS";
	private final String EGRESSPIR = "EgressPIR";

	public CorbaResConvertTool() {

	}

	/**
	 * corba 转换为tunnel
	 * 
	 * @param tunnels
	 * @return
	 * @throws ProcessingFailureException
	 */
	public List<CrossConnect_T> tunnelDATAXToCorba(List<Tunnel> tunnels) throws ProcessingFailureException {

		List<CrossConnect_T> crossConnectList = new ArrayList<CrossConnect_T>();
		try {
			for (Tunnel tunnel : tunnels) {
				crossConnectList.add(this.convertCrossConnect(tunnel));
			}

		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "tunnelDATAXToCorba.");
		}
		return crossConnectList;
	}

	/**
	 * 设置crossConnect的基本信息
	 * 
	 * @param activeStatus
	 * @return
	 */
	private CrossConnect_T convertCrossConnect(int activeStatus) {
		CrossConnect_T crossConnect = new CrossConnect_T();
		crossConnect.active = activeStatus == EActiveStatus.ACTIVITY.getValue();
		crossConnect.direction = ConnectionDirection_T.CD_BI;
		crossConnect.ccType = SNCType_T.ST_SIMPLE;
		return crossConnect;
	}

	/**
	 * tunnel转换为CrossConnect_T对象
	 * 
	 * @param tunnel
	 * @return
	 * @throws ProcessingFailureException
	 */
	public CrossConnect_T convertCrossConnect(Tunnel tunnel) throws ProcessingFailureException {
		CrossConnect_T crossConnect = null;
		CorbaServiceConvrtTool corbaServiceConvrtTool = null;
		Route_THolder route_THolder = null;
		String type = "";
		try {
			// 说明是XC直接调用网络层转换XC的方法。
			if (tunnel.getLspParticularList().size() == 2) {
				corbaServiceConvrtTool = new CorbaServiceConvrtTool();
				route_THolder = new Route_THolder();
				corbaServiceConvrtTool.convertRouteToTunnel(tunnel, route_THolder);
				// 数组的length=1说明转化正确。
				if (null != route_THolder.value && route_THolder.value.length == 1) {
					crossConnect = route_THolder.value[0];
					type = "xc";
				}
			} else if (tunnel.getLspParticularList().size() == 1) {
				// A端或者Z端
				crossConnect = this.convertCrossConnect(tunnel.getTunnelStatus());
				// A端给crossConnect的A赋值，Z默认
				if (tunnel.getASiteId() > 0) {
					crossConnect.aEndNameList = new NameAndStringValue_T[1][];
					crossConnect.aEndNameList[0] = this.convertName(ELayerRate.PORT.getValue(), tunnel.getLspParticularList().get(0).getAPortId(), tunnel.getLspParticularList().get(0).getASiteId());

					crossConnect.zEndNameList = new NameAndStringValue_T[0][];
					type = "a";
				} else {
					// Z端给crossConnect的Z赋值，A默认
					crossConnect.zEndNameList = new NameAndStringValue_T[1][];
					crossConnect.zEndNameList[0] = this.convertName(ELayerRate.PORT.getValue(), tunnel.getLspParticularList().get(0).getZPortId(), tunnel.getLspParticularList().get(0).getZSiteId());

					crossConnect.aEndNameList = new NameAndStringValue_T[0][];
					type = "z";
				}
				crossConnect.additionalInfo = this.convertAdditionalInfo_label(type, tunnel.getLspParticularList().get(0).getFrontLabelValue(), tunnel.getLspParticularList().get(0).getBackLabelValue());
			} else {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "convertCrossConnect.");
			}

			this.convertAdditionalInfo(crossConnect, tunnel, type);

		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "convertCrossConnect.");
		}

		return crossConnect;
	}

	/**
	 * pw转换为CrossConnect_T对象
	 * 
	 * @param tunnel
	 * @return
	 * @throws ProcessingFailureException
	 */
	public CrossConnect_T convertCrossConnect(PwInfo pwInfo) throws ProcessingFailureException {
		CrossConnect_T crossConnect = this.convertCrossConnect(pwInfo.getPwStatus());
		String type = "";
		// A端给crossConnect的A赋值，Z默认
		if (pwInfo.getASiteId() > 0) {
			crossConnect.aEndNameList = new NameAndStringValue_T[1][];
			crossConnect.aEndNameList[0] = this.convertName(ELayerRate.TUNNEL.getValue(), pwInfo.getTunnelId(), pwInfo.getASiteId());

			crossConnect.zEndNameList = new NameAndStringValue_T[0][];
			type = "a";
		} else {
			// Z端给crossConnect的Z赋值，A默认
			crossConnect.zEndNameList = new NameAndStringValue_T[1][];
			crossConnect.zEndNameList[0] = this.convertName(ELayerRate.TUNNEL.getValue(), pwInfo.getTunnelId(), pwInfo.getZSiteId());

			crossConnect.aEndNameList = new NameAndStringValue_T[0][];
			type = "z";
		}

		crossConnect.additionalInfo = this.convertAdditionalInfo_label(type, pwInfo.getInlabelValue(), pwInfo.getOutlabelValue());
		this.convertAdditionalInfo(crossConnect, pwInfo, type);
		return crossConnect;
	}

	/**
	 * 创建tunnel
	 * 
	 * @param tunnelConnectTs
	 */
	public void createTunnel(CrossConnect_T crossConnectT, int siteId, CrossConnect_THolder crossConnectTHolder, ICorbaSession userSession) throws ProcessingFailureException {
		TunnelDispatch tunnelDispatch = null;
		String result = "";
		Tunnel tunnel = new Tunnel();
		List<Lsp> lspList = new ArrayList<Lsp>();
		String type = "";
		TunnelService_MB tunnelService  = null;
		try {
			tunnel.setTunnelType(185 + "");
			tunnel.setTunnelStatus(crossConnectT.active ? EActiveStatus.ACTIVITY.getValue() : EActiveStatus.UNACTIVITY.getValue());
			tunnel.setIsSingle(1);
			tunnel.setTunnelName(super.getValueByKey(crossConnectT.additionalInfo, super.USERLABEL));

			// 验证tunnel的名称是否重复
			VerifyNameUtil verifyNameUtil = new VerifyNameUtil();
			if (verifyNameUtil.verifyNameBySingle(EServiceType.TUNNEL.getValue(), tunnel.getTunnelName(), null, siteId)) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "userlabel repeat");
			}

			tunnel.setCreateTime(DateUtil.getDate(DateUtil.FULLTIME));
			tunnel.setCreateUser(userSession.getUserName());
			tunnel.setIsReverse(1);
			tunnel.setPosition(1);
			tunnel.setProtectBack(1);

			// A\Z都有终端点，说明是xc
			if (crossConnectT.aEndNameList.length > 0 && crossConnectT.zEndNameList.length > 0) { // xc
				Lsp frontLsp = new Lsp();
				frontLsp.setZSiteId(siteId);
				frontLsp.setZPortId(Integer.parseInt(super.getValueByKey(crossConnectT.aEndNameList[0], super.PTP,frontLsp.getZSiteId())));
				frontLsp.setFrontLabelValue(Integer.parseInt(super.getValueByKey(crossConnectT.additionalInfo, super.SRCOUTLABEL)));// Z端出
				frontLsp.setBackLabelValue(Integer.parseInt(super.getValueByKey(crossConnectT.additionalInfo, super.SRCINLABEL)));// Z端入
				frontLsp.setSourceMac("00-00-00-00-00-00");
				frontLsp.setTargetMac("00-00-00-00-00-00");
				frontLsp.setFrontTtl(255);
				frontLsp.setBackTtl(255);
				frontLsp.setAoppositeId("0.0.0.0");
				frontLsp.setZoppositeId("0.0.0.0");
				lspList.add(frontLsp);

				Lsp brackLsp = new Lsp();
				brackLsp.setASiteId(siteId);
				brackLsp.setAPortId(Integer.parseInt(super.getValueByKey(crossConnectT.zEndNameList[0], super.PTP,brackLsp.getASiteId())));
				brackLsp.setFrontLabelValue(Integer.parseInt(super.getValueByKey(crossConnectT.additionalInfo, super.DESTINLABEL)));// A端入
				brackLsp.setBackLabelValue(Integer.parseInt(super.getValueByKey(crossConnectT.additionalInfo, super.DESTOUTLABEL)));// A端出
				brackLsp.setSourceMac("00-00-00-00-00-00");
				brackLsp.setTargetMac("00-00-00-00-00-00");
				brackLsp.setFrontTtl(255);
				brackLsp.setBackTtl(255);
				brackLsp.setAoppositeId("0.0.0.1");
				brackLsp.setZoppositeId("0.0.0.1");
				lspList.add(brackLsp);
				tunnel.setLspParticularList(lspList);

				type = "xc";

			} else if (crossConnectT.zEndNameList.length > 0) {// Z端

				tunnel.setZSiteId(siteId);
				tunnel.setZPortId(Integer.parseInt(super.getValueByKey(crossConnectT.zEndNameList[0], super.PTP,tunnel.getZSiteId())));
				Lsp lsp = new Lsp();
				lsp.setZSiteId(siteId);
				lsp.setZPortId(Integer.parseInt(super.getValueByKey(crossConnectT.zEndNameList[0], super.PTP,lsp.getZSiteId())));
				lsp.setFrontLabelValue(Integer.parseInt(super.getValueByKey(crossConnectT.additionalInfo, super.DESTOUTLABEL)));// Z端出
				lsp.setBackLabelValue(Integer.parseInt(super.getValueByKey(crossConnectT.additionalInfo, super.DESTINLABEL)));// Z端入
				lsp.setSourceMac("00-00-00-00-00-00");
				lsp.setTargetMac("00-00-00-00-00-00");
				lsp.setFrontTtl(255);
				lsp.setBackTtl(255);
				lsp.setAoppositeId("0.0.0.0");
				lsp.setZoppositeId("0.0.0.0");
				lspList.add(lsp);
				tunnel.setLspParticularList(lspList);

				type = "z";

			} else if (crossConnectT.aEndNameList.length > 0) {// A端

				tunnel.setASiteId(siteId);
				tunnel.setAPortId(Integer.parseInt(super.getValueByKey(crossConnectT.aEndNameList[0], super.PTP,tunnel.getASiteId())));
				Lsp lsp = new Lsp();
				lsp.setASiteId(siteId);
				lsp.setAPortId(Integer.parseInt(super.getValueByKey(crossConnectT.aEndNameList[0], super.PTP,lsp.getASiteId())));
				lsp.setFrontLabelValue(Integer.parseInt(super.getValueByKey(crossConnectT.additionalInfo, super.SRCINLABEL)));// A端入
				lsp.setBackLabelValue(Integer.parseInt(super.getValueByKey(crossConnectT.additionalInfo, super.SRCOUTLABEL)));// A端出
				lsp.setSourceMac("00-00-00-00-00-00");
				lsp.setTargetMac("00-00-00-00-00-00");
				lsp.setFrontTtl(255);
				lsp.setBackTtl(255);
				lsp.setAoppositeId("0.0.0.0");
				lsp.setZoppositeId("0.0.0.0");
				lspList.add(lsp);
				tunnel.setLspParticularList(lspList);

				type = "a";
			}

			tunnel.setQosList(this.convertQos(crossConnectT, type, null));

			// 验证qos是否充足
			tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			lspList = tunnelService.getAllLsp(tunnel);
			int maxCreateQos = tunnelService.getMinQosNum(lspList, tunnel.getQosList(), null);
			if (maxCreateQos == 0) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "Lack of qos");
			}

			// 下发适配
			tunnelDispatch = new TunnelDispatch();
			result = tunnelDispatch.excuteInsert(tunnel);
			if (ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS).equals(result)) {// 获取成功的
				crossConnectTHolder.value = crossConnectT;
			} else {
				crossConnectTHolder.value = this.setDefaultCrossConnect_T();
			}
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "createTunnel.");
		}finally{
			UiUtil.closeService_MB(tunnelService);
		}
	}

	/**
	 * 创建时，把corba对象转换为qos
	 * 
	 * @param crossConnectT
	 * @param type
	 *            A还是Z
	 * @return
	 * @throws ProcessingFailureException
	 */
	private List<QosInfo> convertQos(CrossConnect_T crossConnectT, String type, EPwType ePwType) throws ProcessingFailureException {

		List<QosInfo> qosInfoList = new ArrayList<QosInfo>();
		try {
			QosInfo qosInfo_forward = new QosInfo();
			qosInfo_forward.setDirection(EQosDirection.FORWARD.getValue() + "");
			qosInfo_forward.setQosType(super.getValueByKey(crossConnectT.additionalInfo, super.QOSTYPE));
			qosInfo_forward.setCos(QosCosLevelEnum.from(super.getValueByKey(crossConnectT.additionalInfo, super.QOSCOS)));

			QosInfo qosInfo_backward = new QosInfo();
			qosInfo_backward.setDirection(EQosDirection.BACKWARD.getValue() + "");
			qosInfo_backward.setQosType(super.getValueByKey(crossConnectT.additionalInfo, super.QOSTYPE));
			qosInfo_backward.setCos(QosCosLevelEnum.from(super.getValueByKey(crossConnectT.additionalInfo, super.QOSCOS)));

			if ("a".equals(type)) {
				if (null != ePwType && (ePwType.getValue() == EPwType.PDH.getValue() || ePwType.getValue() == EPwType.SDH.getValue())) {
					qosInfo_forward.setCir(2448);
					qosInfo_backward.setCir(2448);
				} else {
					qosInfo_forward.setCir(Integer.parseInt(super.getValueByKey(crossConnectT.additionalInfo, this.EGRESSCIR)));
					qosInfo_backward.setCir(Integer.parseInt(super.getValueByKey(crossConnectT.additionalInfo, this.INGRESSCIR)));
				}
				qosInfo_forward.setCbs(Integer.parseInt(super.getValueByKey(crossConnectT.additionalInfo, this.EGRESSCBS)));
				qosInfo_forward.setEir(Integer.parseInt(super.getValueByKey(crossConnectT.additionalInfo, this.EGRESSEIR)));
				qosInfo_forward.setEbs(Integer.parseInt(super.getValueByKey(crossConnectT.additionalInfo, this.EGRESSEBS)));
				qosInfo_forward.setPir(Integer.parseInt(super.getValueByKey(crossConnectT.additionalInfo, this.EGRESSPIR)));

				qosInfo_backward.setCbs(Integer.parseInt(super.getValueByKey(crossConnectT.additionalInfo, this.INGRESSCBS)));
				qosInfo_backward.setEir(Integer.parseInt(super.getValueByKey(crossConnectT.additionalInfo, this.INGRESSEIR)));
				qosInfo_backward.setEbs(Integer.parseInt(super.getValueByKey(crossConnectT.additionalInfo, this.INGRESSEBS)));
				qosInfo_backward.setPir(Integer.parseInt(super.getValueByKey(crossConnectT.additionalInfo, this.INGRESSPIR)));
			} else {

				if (null != ePwType && (ePwType.getValue() == EPwType.PDH.getValue() || ePwType.getValue() == EPwType.SDH.getValue())) {
					qosInfo_forward.setCir(2448);
					qosInfo_backward.setCir(2448);
				} else {
					qosInfo_forward.setCir(Integer.parseInt(super.getValueByKey(crossConnectT.additionalInfo, this.EGRESSCIR)));
					qosInfo_backward.setCir(Integer.parseInt(super.getValueByKey(crossConnectT.additionalInfo, this.INGRESSCIR)));
				}

				qosInfo_backward.setCbs(Integer.parseInt(super.getValueByKey(crossConnectT.additionalInfo, this.EGRESSCBS)));
				qosInfo_backward.setEir(Integer.parseInt(super.getValueByKey(crossConnectT.additionalInfo, this.EGRESSEIR)));
				qosInfo_backward.setEbs(Integer.parseInt(super.getValueByKey(crossConnectT.additionalInfo, this.EGRESSEBS)));
				qosInfo_backward.setPir(Integer.parseInt(super.getValueByKey(crossConnectT.additionalInfo, this.EGRESSPIR)));

				qosInfo_forward.setCbs(Integer.parseInt(super.getValueByKey(crossConnectT.additionalInfo, this.INGRESSCBS)));
				qosInfo_forward.setEir(Integer.parseInt(super.getValueByKey(crossConnectT.additionalInfo, this.INGRESSEIR)));
				qosInfo_forward.setEbs(Integer.parseInt(super.getValueByKey(crossConnectT.additionalInfo, this.INGRESSEBS)));
				qosInfo_forward.setPir(Integer.parseInt(super.getValueByKey(crossConnectT.additionalInfo, this.INGRESSPIR)));
			}
			qosInfoList.add(qosInfo_forward);
			qosInfoList.add(qosInfo_backward);
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "convertQos.");
		}
		return qosInfoList;
	}

	/**
	 * 根据tunnel主键查询tunnel
	 * 
	 * @param tunnelId
	 * @return
	 * @throws ProcessingFailureException
	 */
	public Tunnel getTunnel(int tunnelId,int siteId) throws ProcessingFailureException {
		Tunnel result = null;
		TunnelService_MB tunnelService = null;
		try {
			tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			Tunnel tunnel = new Tunnel();
			tunnel.setTunnelId(tunnelId);
			List<Tunnel> tunnels = tunnelService.select_nojoin(tunnel);
			if (null == tunnels || tunnels.size() != 1) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "name is error");
			}
			result = tunnels.get(0);
			
			//验证是否为单网元
			if(result.getIsSingle()!=1){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "name is error");
			}
			
			//验证tunnel的类型
			if(!result.getTunnelType().equals(UiUtil.getCodeByValue("PROTECTTYPE", "1").getId()+"")){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "name is error");
			}
			
			//验证查询出的tunnel是否与入参site相同
			if(result.getLspParticularList().size()==1){
				if(result.getASiteId()!=siteId && result.getZSiteId()!=siteId){
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "name is error");
				}
			}else if(result.getLspParticularList().size()==2){
				if(result.getLspParticularList().get(0).getZSiteId()!=siteId || result.getLspParticularList().get(1).getASiteId()!=siteId){
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "name is error");
				}
			}
			
			
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "getTunnel is error");
		}finally{
			UiUtil.closeService_MB(tunnelService);
		}
		return result;

	}

	/**
	 * 激活某条tunnel
	 * 
	 * @param tunnelName
	 * @throws ProcessingFailureException
	 */
	public void activateTunnel(int tunnelId,int siteId) throws ProcessingFailureException {
		Tunnel tunnel = null;
		TunnelDispatch tunnelDispatch = null;
		String result=null;
		try {
			tunnel = this.getTunnel(tunnelId,siteId);
			tunnel.setTunnelStatus(EActiveStatus.ACTIVITY.getValue());
			tunnelDispatch = new TunnelDispatch();
			result = tunnelDispatch.excuteUpdate(tunnel);
			if(!result.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, result);
			}
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "deactivateTunnel.");
		}
	}

	/**
	 * 去激活某条tunnel
	 * 
	 * @param tunnelName
	 * @throws Exception
	 */
	public void deactivateTunnel(int tunnelId,int siteId) throws ProcessingFailureException {
		TunnelDispatch tunnelDispatch = null;
		String result=null;
		try {
			Tunnel tunnel = this.getTunnel(tunnelId,siteId);
			tunnel.setTunnelStatus(EActiveStatus.UNACTIVITY.getValue());
			tunnelDispatch = new TunnelDispatch();
			result = tunnelDispatch.excuteUpdate(tunnel);
			if(!result.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, result);
			}
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "deactivateTunnel.");
		}
	}

	/**
	 * 删除某条tunnel
	 * 
	 * @param tunnelName
	 * @throws ProcessingFailureException
	 */
	public void deleteTunnel(int tunnelId,int siteId) throws ProcessingFailureException {
		TunnelDispatch tunnelDispatch = null;
		String result=null;
		try {
			Tunnel tunnel = this.getTunnel(tunnelId,siteId);
			List<Tunnel> tunnels = new ArrayList<Tunnel>();
			tunnels.add(tunnel);
			tunnelDispatch = new TunnelDispatch();
			result = tunnelDispatch.excuteDelete(tunnels);
			if(!result.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, result);
			}
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "deactivateTunnel.");
		}
	}

	/**
	 * function:返回所有的pw信息
	 * 
	 * @param pwInfo
	 *            PW信息
	 * @return CrossConnect_T
	 * @throws ProcessingFailureException
	 */
	public List<CrossConnect_T> pwDATAXToCorba(List<PwInfo> pwInfoList) throws ProcessingFailureException {
		List<CrossConnect_T> crossConnectList = new ArrayList<CrossConnect_T>();
		try {
			for (PwInfo pwInfo : pwInfoList) {
				crossConnectList.add(this.convertCrossConnect(pwInfo));
			}

		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "tunnelDATAXToCorba.");
		}
		return crossConnectList;
	}

	/**
	 * @author guoqc 删除pw
	 * @param objectName
	 *            名称
	 * @throws ProcessingFailureException
	 */
	public void deletePw(int pwId,int siteId) throws ProcessingFailureException {
		String result=null;
		try {
			// 根据pw名称查询需要去激活的pw
			PwInfo pw = this.getPwInfo(pwId,siteId);
			if (pw.getPwId() > 0) {
				// 根据pw名称查询需要删除的pw, 调用适配层的删除功能
				PwDispatch pwDispatch = new PwDispatch();
				List<PwInfo> pwList = new ArrayList<PwInfo>();
				pwList.add(pw);
				result= pwDispatch.excuteDelete(pwList);
			}
			if(!result.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, result);
			}
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "deletePw.");
		}
	}

	/**
	 * @author guoqc 去激活pw
	 * @param objName
	 * @throws ProcessingFailureException
	 */
	public void deactivatePw(int pwId,int siteId) throws ProcessingFailureException {
		String result=null;
		try {
			// 根据pw名称查询需要去激活的pw
			PwInfo pwInfo = this.getPwInfo(pwId,siteId);
			// 调用适配层的更新功能
			if (null != pwInfo) {
				PwDispatch pwDispatch = new PwDispatch();
				pwInfo.setPwStatus(EActiveStatus.UNACTIVITY.getValue());
				result = pwDispatch.excuteUpdate(pwInfo);
			}
			if(!result.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, result);
			}
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "deletePw.");
		}
	}

	/**
	 * @author guoqc 激活pw
	 * @param objName
	 * @throws ProcessingFailureException
	 */
	public void activatePw(int pwid,int siteId) throws ProcessingFailureException {
		String result=null;
		try {
			// 根据pw名称查询需要去激活的pw
			PwInfo pw = this.getPwInfo(pwid,siteId);
			// 调用适配层的更新功能
			PwDispatch pwDispatch = new PwDispatch();
			if (pw.getPwId() > 0) {
				pw.setPwStatus(EActiveStatus.ACTIVITY.getValue());
				result = pwDispatch.excuteUpdate(pw);
			}
			if(!result.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, result);
			}
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "activatePw.");
		}
	}

	/**
	 * 根据主键查询pw对象
	 * 
	 * @param pwId
	 * @return
	 * @throws ProcessingFailureException
	 */
	public PwInfo getPwInfo(int pwId,int siteId) throws ProcessingFailureException {
		PwInfo pwInfo = null;
		PwInfoService_MB pwInfoService = null;
		try {
		    pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			// 根据pw名称查询需要去激活的pw
			pwInfo = new PwInfo();
			pwInfo.setPwId(pwId);
			pwInfo = pwInfoService.queryByPwId(pwInfo);
			if (null == pwInfo) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "name is error");
			}
			
			//验证是否为单网元配置
			if(pwInfo.getIsSingle()==0){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "name is error");
			}
			
			//验证是否为传入的网元
			if(pwInfo.getASiteId()!=siteId && pwInfo.getZSiteId()!=siteId){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "name is error");
			}
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "getPwInfo is error");
		}finally{
			UiUtil.closeService_MB(pwInfoService);
		}
		return pwInfo;
	}

	/**
	 * function:返回所有的ces信息
	 * 
	 * @param cesInfo
	 *            ces信息
	 * @return CrossConnect_T
	 * @throws ProcessingFailureException
	 */
	public CrossConnect_T convertCrossConnect(CesInfo cesInfo) throws ProcessingFailureException {
		CrossConnect_T crossConnectT = null;
		try {
			crossConnectT = this.convertCrossConnect(cesInfo.getActiveStatus());
			// ces分为AZ两端
			if (cesInfo.getaSiteId() > 0) {// 判断A 端网元是否大于 0：有值为返回对象赋值
				crossConnectT.aEndNameList = new NameAndStringValue_T[1][];
				if (cesInfo.getCestype() == ECesType.PDH.getValue() || cesInfo.getCestype() == ECesType.PDHSDH.getValue()) {
					// 判断ces类型：pdh,pdhsdh类型，
					crossConnectT.aEndNameList[0] = convertName(ELayerRate.PORT.getValue(), cesInfo.getaAcId(), cesInfo.getaSiteId());// 网元+ac端口

				} else if (cesInfo.getCestype() == ECesType.SDH.getValue() || cesInfo.getCestype() == ECesType.SDHPDH.getValue()) {// ces类型： sdh,sdhpdh类型
					crossConnectT.aEndNameList[0] = convertName(ELayerRate.CTP.getValue(), cesInfo.getaAcId(), cesInfo.getaSiteId());// 网元+ac端口
				} else {
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "获取信息出错");
				}
			} else {// 为此端网元信息赋予空值
				crossConnectT.aEndNameList = new NameAndStringValue_T[0][];
			}
			if (cesInfo.getzSiteId() > 0) {// 判断Z端网元是否大于 0：有值为返回对象赋值
				crossConnectT.zEndNameList = new NameAndStringValue_T[1][];
				if (cesInfo.getCestype() == ECesType.PDH.getValue() || cesInfo.getCestype() == ECesType.PDHSDH.getValue()) {
					// 判断ces类型：pdh,pdhsdh类型，
					crossConnectT.zEndNameList[0] = convertName(ELayerRate.PORT.getValue(), cesInfo.getzAcId(), cesInfo.getzSiteId());// 网元+ac端口

				} else if (cesInfo.getCestype() == ECesType.SDH.getValue() || cesInfo.getCestype() == ECesType.SDHPDH.getValue()) {// ces类型： sdh,sdhpdh类型
					crossConnectT.zEndNameList[0] = convertName(ELayerRate.CTP.getValue(), cesInfo.getzAcId(), cesInfo.getzSiteId());// 网元+ac端口
				} else {
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "获取信息出错");
				}
			} else {// 为此端网元信息赋予空值
				crossConnectT.zEndNameList = new NameAndStringValue_T[0][];
			}
			// 附加信息
			crossConnectT.additionalInfo = this.getAdditionalInfo(cesInfo);

		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "convertCrossConnect.");
		} finally {

		}
		return crossConnectT;
	}

	/**
	 * 查询，转换，参数有误等时，返回默认值
	 * 
	 * @return
	 * @throws ProcessingFailureException
	 */
	public CrossConnect_T setDefaultCrossConnect_T() throws ProcessingFailureException {
		CrossConnect_T crossConnectT = null;
		try {
			crossConnectT = new CrossConnect_T();
			crossConnectT.active = false;// 是否激活
			crossConnectT.direction = ConnectionDirection_T.CD_BI;// 单向/双向
			crossConnectT.ccType = SNCType_T.ST_ADD_DROP_A;
			// ces分为AZ两端
			crossConnectT.aEndNameList = new NameAndStringValue_T[0][];
			crossConnectT.zEndNameList = new NameAndStringValue_T[0][];
			// 附加信息
			crossConnectT.additionalInfo = new NameAndStringValue_T[0];
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "setDefaultCrossConnect_T.");
		} finally {

		}
		return crossConnectT;
	}

	/**
	 * 创建： 将Corba传人的创建信息 转为Cesinfo
	 * 
	 * @param crossConnect_T
	 * @return
	 * @throws ProcessingFailureException
	 */
	public CesInfo conResourceCes(CrossConnect_T crossConnect_T, ICorbaSession userSession) throws ProcessingFailureException {
		CesInfo cesInfo = null;
		int cesType = 0;
		String lay = "";
		int aAcId = 0;
		int zAcId = 0;
		try {
			lay = super.getValueByKey(crossConnect_T.additionalInfo, this.FDFRRATE);
			if (lay.trim().equals("")) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "输出层速率有误");
			}
			if (Short.parseShort(lay) != ELayerRate.CES.getValue()) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "输出层速率有误");
			}
			cesInfo = new CesInfo();
			cesType = ECesType.valueOf(super.getValueByKey(crossConnect_T.additionalInfo, super.CESTYPE)).getValue();
			cesInfo.setCestype(cesType);// ces类型
			// 判断输入参数的 A 端是否有信息
			if (crossConnect_T.aEndNameList != null && crossConnect_T.aEndNameList.length > 0) {
				cesInfo.setaSiteId(Integer.parseInt(super.getValueByKey(crossConnect_T.aEndNameList[0], this.MANAGEELEMENT)));// A 端网元ID
				// 根据类型，判断A 端的格式信息，取出A 端的 AC ID
				if (cesType == ECesType.PDH.getValue() || cesType == ECesType.PDHSDH.getValue()) {
					aAcId = Integer.parseInt(super.getValueByKey(crossConnect_T.aEndNameList[0], this.PTP,cesInfo.getaSiteId()));
				} else if (cesType == ECesType.SDH.getValue() || cesType == ECesType.SDHPDH.getValue()) {
					aAcId = Integer.parseInt(super.getValueByKey(crossConnect_T.aEndNameList[0], this.CTP));
				} else {
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "没有找到匹配的业务额类型");
				}
			}
			// 判断输入参数的 Z 端是否有信息
			if (crossConnect_T.zEndNameList != null && crossConnect_T.zEndNameList.length > 0) {
				cesInfo.setzSiteId(Integer.parseInt(super.getValueByKey(crossConnect_T.zEndNameList[0], this.MANAGEELEMENT)));// z 端网元ID
				// 根据类型，判断A 端的格式信息，取出A 端的 AC ID
				if (cesType == ECesType.PDH.getValue() || cesType == ECesType.PDHSDH.getValue()) {
					zAcId = Integer.parseInt(super.getValueByKey(crossConnect_T.zEndNameList[0], this.PTP,cesInfo.getzSiteId()));
				} else if (cesType == ECesType.SDH.getValue() || cesType == ECesType.SDHPDH.getValue()) {
					zAcId = Integer.parseInt(super.getValueByKey(crossConnect_T.zEndNameList[0], this.CTP));
				} else {
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "没有找到匹配的业务额类型");
				}
			}
			// 未 ces 的ac 赋值
			cesInfo.setaAcId(aAcId);
			cesInfo.setzAcId(zAcId);
			cesInfo.setActiveStatus(crossConnect_T.active ? EActiveStatus.ACTIVITY.getValue() : EActiveStatus.UNACTIVITY.getValue());// 激活状态
			cesInfo.setIsSingle(1);// 设置单网元标识符
			cesInfo.setPwId(super.converByElaytoId(ELayerRate.PW.getValue(), super.getValueByKey(crossConnect_T.additionalInfo, super.SERVERCONNECTIONS)));// ces关联的pw主键ID
			cesInfo.setCreateUser(userSession.getUserName());// 创建人
			cesInfo.setName(super.getValueByKey(crossConnect_T.additionalInfo, super.USERLABEL));// 名称

		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "获取CrossConnect_T对象失败");
		}
		return cesInfo;
	}

	/**
	 * ces集合转换成CrossConnect_T对象
	 * 
	 * @param cesList
	 *            ces集合
	 * @param sncArray
	 *            snc对象集合
	 * @throws Exception
	 */
	public List<CrossConnect_T> convertCesToCrossConnect(List<CesInfo> cesList) throws ProcessingFailureException {
		List<CrossConnect_T> crossList = new ArrayList<CrossConnect_T>();
		try {
			if (UiUtil.isNull(cesList)) {
				for (int i = 0; i < cesList.size(); i++) {
					crossList.add(this.convertCrossConnect(cesList.get(i)));
				}
			}
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "获取CrossConnect_T对象失败");
		}
		return crossList;
	}

	/**
	 * 获取附加信息
	 * 
	 * @param cesInfo
	 * @return
	 * @throws ProcessingFailureException
	 */
	private NameAndStringValue_T[] getAdditionalInfo(CesInfo cesInfo) throws ProcessingFailureException {
		NameAndStringValue_T[] additionalInfo = new NameAndStringValue_T[8];
		additionalInfo[0] = new NameAndStringValue_T(this.CROSSCONNECTION, ELayerRate.CES.getValue() + "/" + cesInfo.getId());// 标识符
		additionalInfo[1] = new NameAndStringValue_T(super.CREATETIME, DateTimeUtil.converCorbaTime(cesInfo.getCreateTime(), DateUtil.FULLTIME));// 创建时间
		additionalInfo[2] = new NameAndStringValue_T(super.CREATEUSER, cesInfo.getCreateUser()==null?"":cesInfo.getCreateUser());//
		additionalInfo[3] = new NameAndStringValue_T(super.SERVERCONNECTIONS, ELayerRate.PW.getValue() + "/" + cesInfo.getPwId());// ces关联的pw主键ID
		additionalInfo[4] = new NameAndStringValue_T(super.CESTYPE, cesInfo.getCestype() == ECesType.PDH.getValue() ? "e1" : "stm-n");//
		additionalInfo[5] = new NameAndStringValue_T(super.CLIENTNAME, super.queryById(cesInfo.getClientId()));// 客户名称
		additionalInfo[6] = new NameAndStringValue_T(super.USERLABEL, cesInfo.getName());// 友好名称
		additionalInfo[7] = new NameAndStringValue_T(super.NATIVEEMSNAME, cesInfo.getName());// 本地名称
		return additionalInfo;
	}

	/**
	 * 转换topo集合
	 * 
	 * @param segmentList
	 *            段集合
	 * @param topoLink
	 *            corba 拓扑链接集合
	 * @throws Exception 
	 */
	public void corbaTopologicalLinkListConvrtTool(List<Segment> segmentList, TopologicalLink_T[] topoLink) throws Exception {
		for (int i = 0; i < segmentList.size(); i++) {
			topoLink[i] = new TopologicalLink_T();
			this.corbaTopologicalLinkConvrtTool(segmentList.get(i), topoLink[i]);
		}
	}

	/**
	 * 转换topo集合
	 * 
	 * @param segment
	 *            段对象
	 * @param topoLink
	 *            拓扑链接
	 * @throws Exception 
	 */
	public void corbaTopologicalLinkConvrtTool(Segment segment, TopologicalLink_T topoLink) throws ProcessingFailureException {
		try {
			topoLink.name = super.convertName(ELayerRate.TOPOLOGICALLINK.getValue(), segment.getId(), 0);
			topoLink.userLabel = segment.getNAME();
			topoLink.nativeEMSName = segment.getNAME();
			topoLink.owner = CorbaConfig.getInstanse().getCorbaEmsName();
			topoLink.rate = (short) ELayerRate.TOPOLOGICALLINK.getValue();
			topoLink.aEndTP = super.convertName(ELayerRate.PORT.getValue(), segment.getAPORTID(), segment.getASITEID());
			topoLink.zEndTP = super.convertName(ELayerRate.PORT.getValue(), segment.getZPORTID(), segment.getZSITEID());
			topoLink.direction = ConnectionDirection_T.CD_BI;
			topoLink.additionalInfo = new NameAndStringValue_T[0];
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "corbaTopologicalLinkConvrtTool ex.");
		}
	}

	/**
	 * 转换附加信息中的label信息
	 * 
	 * @param createUser
	 *            创建人
	 * @param type
	 *            a或者z
	 * @param aLabel
	 *            a端入标签
	 * @param zLabel
	 *            z端入标签
	 * @return
	 */
	private NameAndStringValue_T[] convertAdditionalInfo_label(String type, int aLabel, int zLabel) throws ProcessingFailureException {
		NameAndStringValue_T[] nameAndStringValue_T = new NameAndStringValue_T[2];

		if ("a".equals(type)) {
			nameAndStringValue_T[0] = new NameAndStringValue_T(super.SRCINLABEL, aLabel + "");
			nameAndStringValue_T[1] = new NameAndStringValue_T(super.SRCOUTLABEL, zLabel + "");
		} else {
			nameAndStringValue_T[0] = new NameAndStringValue_T(super.DESTINLABEL, zLabel + "");
			nameAndStringValue_T[1] = new NameAndStringValue_T(super.DESTOUTLABEL, aLabel + "");
		}
		return nameAndStringValue_T;
	}

	/**
	 * 转换附件信息 包括qos等属性， 转换完成后把新转换的和crossConnect中已有的组合
	 * 
	 * @param crossConnect
	 * @param object
	 *            tunnel\pw
	 * @param type
	 *            a\z
	 */
	private void convertAdditionalInfo(CrossConnect_T crossConnect, Object object, String type) throws ProcessingFailureException {
		NameAndStringValue_T[] nameAndStringValueArray = null;
		if (object instanceof Tunnel) {
			Tunnel tunnel = (Tunnel) object;
			nameAndStringValueArray = new NameAndStringValue_T[15];
			nameAndStringValueArray[0] = new NameAndStringValue_T(super.USERLABEL, tunnel.getTunnelName());
			nameAndStringValueArray[1] = new NameAndStringValue_T(this.CROSSCONNECTION, ELayerRate.TUNNEL.getValue() + "/" + tunnel.getTunnelId() + "");
			nameAndStringValueArray[2] = new NameAndStringValue_T(this.LAYER, ELayerRate.TUNNEL.getValue() + "");
			nameAndStringValueArray[3] = new NameAndStringValue_T(super.CREATEUSER, tunnel.getCreateUser());
			this.convertAdditionalInfo_qos(tunnel.getQosList(), nameAndStringValueArray, 3, type);
		} else if (object instanceof PwInfo) {
			PwInfo pwinfo = (PwInfo) object;
			nameAndStringValueArray = new NameAndStringValue_T[17];
			nameAndStringValueArray[0] = new NameAndStringValue_T(super.USERLABEL, pwinfo.getPwName());
			nameAndStringValueArray[1] = new NameAndStringValue_T(this.CROSSCONNECTION, ELayerRate.PW.getValue() + "/" + pwinfo.getPwId());
			nameAndStringValueArray[2] = new NameAndStringValue_T(super.SERVERCONNECTIONS, ELayerRate.TUNNEL.getValue() + "/" + pwinfo.getTunnelId());
			nameAndStringValueArray[3] = new NameAndStringValue_T(super.PWTYPE, pwinfo.getType().toString());
			nameAndStringValueArray[4] = new NameAndStringValue_T(this.LAYER, ELayerRate.PW.getValue() + "");
			nameAndStringValueArray[5] = new NameAndStringValue_T(super.CREATEUSER, pwinfo.getCreateUser());
			this.convertAdditionalInfo_qos(pwinfo.getQosList(), nameAndStringValueArray, 5, type);
		}
		crossConnect.additionalInfo = super.concatAll(crossConnect.additionalInfo, nameAndStringValueArray);
	}

	/**
	 * 把qos信息转换为附加信息
	 * 
	 * @param qosInfoList
	 *            qos集合
	 * @param nameAndStringValueArray
	 *            已有的附加信息
	 * @param index
	 *            附件信息目前的索引
	 * @param type
	 *            a\z
	 */
	private void convertAdditionalInfo_qos(List<QosInfo> qosInfoList, NameAndStringValue_T[] nameAndStringValueArray, int index, String type) throws ProcessingFailureException {
		nameAndStringValueArray[index++] = new NameAndStringValue_T(super.QOSTYPE, qosInfoList.get(0).getQosType());
		nameAndStringValueArray[index++] = new NameAndStringValue_T(super.QOSCOS, QosCosLevelEnum.from(qosInfoList.get(0).getCos()).toString());

		int ingressIndex = 0;
		int egressIndex = 0;
		// 如果是A端，那么前向(index=0)为出带宽 后向(index=1)为入带宽，Z端和XC反之
		if ("a".equals(type)) {
			ingressIndex = 1;
			egressIndex = 0;
		} else {
			// XC也按照Z端来计算
			ingressIndex = 0;
			egressIndex = 1;
		}

		nameAndStringValueArray[index++] = new NameAndStringValue_T(this.INGRESSCIR, qosInfoList.get(ingressIndex).getCir() + "");
		nameAndStringValueArray[index++] = new NameAndStringValue_T(this.INGRESSCBS, qosInfoList.get(ingressIndex).getCbs() + "");
		nameAndStringValueArray[index++] = new NameAndStringValue_T(this.INGRESSEIR, qosInfoList.get(ingressIndex).getEir() + "");
		nameAndStringValueArray[index++] = new NameAndStringValue_T(this.INGRESSEBS, qosInfoList.get(ingressIndex).getEbs() + "");
		nameAndStringValueArray[index++] = new NameAndStringValue_T(this.INGRESSPIR, qosInfoList.get(ingressIndex).getPir() + "");

		nameAndStringValueArray[index++] = new NameAndStringValue_T(this.EGRESSCIR, qosInfoList.get(egressIndex).getCir() + "");
		nameAndStringValueArray[index++] = new NameAndStringValue_T(this.EGRESSCBS, qosInfoList.get(egressIndex).getCbs() + "");
		nameAndStringValueArray[index++] = new NameAndStringValue_T(this.EGRESSEIR, qosInfoList.get(egressIndex).getEir() + "");
		nameAndStringValueArray[index++] = new NameAndStringValue_T(this.EGRESSEBS, qosInfoList.get(egressIndex).getEbs() + "");
		nameAndStringValueArray[index++] = new NameAndStringValue_T(this.EGRESSPIR, qosInfoList.get(egressIndex).getPir() + "");
	}

	/**
	 * 创建单网元的PW
	 * 
	 * @param siteId
	 *            网元的ID
	 * @param ccList
	 *            创建PW所需要的数据
	 * @param addSuccess
	 *            成功创建的PW
	 * @throws ProcessingFailureException
	 */

	public void convertCrossConnnectionsPW(int siteId, CrossConnect_T crossConnect_T, CrossConnect_THolder connectTHolder, ICorbaSession userSession) throws ProcessingFailureException {
		PwInfo pwInfo = null;
		PwDispatch pwDispatch = null;
		String result = null;
		String type = "";
		PwInfoService_MB pwInfoService = null;
		try {

			// 如果pw的A\Z都等于0 或者都大于0 说明参数错误
			if (crossConnect_T.aEndNameList.length == 0 && crossConnect_T.zEndNameList.length == 0 || crossConnect_T.aEndNameList.length > 0 && crossConnect_T.zEndNameList.length > 0) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "crossConnect_T is error");
			}

			pwInfo = new PwInfo();
			pwInfo.setPwName(super.getValueByKey(crossConnect_T.additionalInfo, super.USERLABEL));

			// 验证tunnel的名称是否重复
			VerifyNameUtil verifyNameUtil = new VerifyNameUtil();
			if (verifyNameUtil.verifyNameBySingle(EServiceType.PW.getValue(), pwInfo.getPwName(), null, siteId)) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "userlabel repeat");
			}

			pwInfo.setTunnelId(super.converByElaytoId(ELayerRate.TUNNEL.getValue(), super.getValueByKey(crossConnect_T.additionalInfo, this.SERVERCONNECTIONS)));

			// 根据主键查询tunnel，测试tunnel是否存在。
			Tunnel tunnel = this.getTunnel(pwInfo.getTunnelId(),siteId);
			if (tunnel.getTunnelStatus() == EActiveStatus.UNACTIVITY.getValue() && crossConnect_T.active) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "lsp active=false");
			}

			pwInfo.setIsSingle(1);
			pwInfo.setCreateTime(DateUtil.getDate(DateUtil.FULLTIME));
			pwInfo.setCreateUser(userSession.getUserName());
			pwInfo.setaOutVlanValue(2);
			pwInfo.setaSourceMac("00-00-00-33-44-55");
			pwInfo.setAtargetMac("00-00-00-AA-BB-CC");
			// PW状态
			pwInfo.setPwStatus(crossConnect_T.active == true ? EActiveStatus.ACTIVITY.getValue() : EActiveStatus.UNACTIVITY.getValue());
			// LSP标签交换标识符
			// pw的类型
			pwInfo.setType(EPwType.valueOf(super.getValueByKey(crossConnect_T.additionalInfo, super.PWTYPE)));

			// eth类型的默认值为494 pdh sdh为496
			if (pwInfo.getType().getValue() == EPwType.ETH.getValue()) {
				pwInfo.setPayload(494);
			} else {
				pwInfo.setPayload(496);
			}

			pwInfo.setAoppositeId("0.0.0.0");
			pwInfo.setZoppositeId("0.0.0.0");

			// A端
			if (crossConnect_T.aEndNameList.length > 0) {
				pwInfo.setInlabelValue(Integer.parseInt(super.getValueByKey(crossConnect_T.additionalInfo, super.SRCINLABEL)));
				pwInfo.setOutlabelValue(Integer.parseInt(super.getValueByKey(crossConnect_T.additionalInfo, super.SRCOUTLABEL)));
				pwInfo.setASiteId(siteId);
				pwInfo.setaOutVlanValue(2);
				pwInfo.setaSourceMac("00-00-00-33-44-55");
				pwInfo.setAtargetMac("00-00-00-AA-BB-CC");
				type = "a";
			} else {
				pwInfo.setInlabelValue(Integer.parseInt(super.getValueByKey(crossConnect_T.additionalInfo, super.DESTOUTLABEL)));
				pwInfo.setOutlabelValue(Integer.parseInt(super.getValueByKey(crossConnect_T.additionalInfo, super.DESTINLABEL)));
				pwInfo.setZSiteId(siteId);
				pwInfo.setzOutVlanValue(2);
				pwInfo.setzSourceMac("00-00-00-33-44-55");
				pwInfo.setZtargetMac("00-00-00-AA-BB-CC");
				type = "z";
			}
			pwInfo.setQosList(this.convertQos(crossConnect_T, type, pwInfo.getType()));

			// 新建时 验证qos是否充足
			pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			if (!pwInfoService.checkingQos(pwInfo, pwInfo.getQosList(), null)) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "Lack of qos");
			}
			pwDispatch = new PwDispatch();
			result = pwDispatch.excuteInsert(pwInfo);
			// 成功
			if (result.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))) {
				connectTHolder.value = crossConnect_T;
			} else {
				connectTHolder.value = this.setDefaultCrossConnect_T();
			}
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "convertCrossConnnectionsPW.");
		} finally {
			pwInfo = null;
			pwDispatch = null;
			UiUtil.closeService_MB(pwInfoService);
		}
	}

	/**
	 * 创建ces
	 * 
	 * @param siteId
	 * @param crossConnect_T
	 * @param connectTHolder
	 * @throws ProcessingFailureException
	 */
	public void convertCreossConnectionsCes(int siteId, CrossConnect_T crossConnect_T, CrossConnect_THolder connectTHolder, ICorbaSession userSession) throws ProcessingFailureException {
		CesInfo cesInfo = null;
		String name;
		VerifyNameUtil verifyNameUtil = null;// 验证名称是否重复
		CesDispatch cesDispatch = null;
		List<CesInfo> cesInfoList = null;
		PwInfo pwInfo = null;
		try {
			verifyNameUtil = new VerifyNameUtil();
			name = super.getValueByKey(crossConnect_T.additionalInfo, super.USERLABEL);
			// 验证名称是否重复
			if (name != null && !verifyNameUtil.verifyNameBySingle(EServiceType.CES.getValue(), name, null, siteId)) {
				cesInfoList = new ArrayList<CesInfo>();
				cesInfo = this.conResourceCes(crossConnect_T, userSession);// 将输入数据转为CES

				// 根据主键查询pw
				pwInfo = this.getPwInfo(cesInfo.getPwId(),siteId);
				// 验证 通过输入条件查找的pw 是否可用
				if (pwInfo.getRelatedServiceId() > 0) {
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "pw By using");
				}
				// 验证pw类型是否与CES类型相同
				if (pwInfo.getType().getValue() != cesInfo.getCestype()) {
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "Pw ces type is not correct");
				}

				// pw为未激活，ces为激活
				if (pwInfo.getPwStatus() == EActiveStatus.UNACTIVITY.getValue() && cesInfo.getActiveStatus() == EActiveStatus.ACTIVITY.getValue()) {
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "pw active=false");
				}

				// 验证通过，下发设备
				cesInfoList.add(cesInfo);
				cesDispatch = new CesDispatch();
				String result=cesDispatch.excuteInsert(cesInfoList);
				// 成功
				if (result.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))) {
					connectTHolder.value = this.convertCrossConnect(cesInfo);
				} else {
					connectTHolder.value = this.setDefaultCrossConnect_T();
				}
			} else {
				// 验证未通过： 名称已经存在
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "userlabel repeat");
			}
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "内部执行出错 赋值错误");
		}
	}
}
