package com.nms.service.impl.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.equipment.slot.SlotInst;
import com.nms.db.bean.ptn.ecn.CCN;
import com.nms.db.bean.ptn.ecn.MCN;
import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.oam.OamMepInfo;
import com.nms.db.bean.ptn.oam.OamMipInfo;
import com.nms.db.bean.ptn.path.eth.DualInfo;
import com.nms.db.bean.ptn.path.protect.LoopProtectInfo;
import com.nms.db.bean.ptn.path.protect.MspProtect;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.tunnel.Lsp;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.bean.ptn.port.PortLagInfo;
import com.nms.db.bean.ptn.qos.QosInfo;
import com.nms.db.bean.ptn.qos.QosQueue;
import com.nms.db.enums.EObjectType;
import com.nms.db.enums.EPwType;
import com.nms.db.enums.EQosDirection;
import com.nms.db.enums.EServiceType;
import com.nms.db.enums.OamTypeEnum;
import com.nms.db.enums.QosCosLevelEnum;
import com.nms.db.enums.QosTemplateTypeEnum;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.oam.OamMipObject;
import com.nms.drivechenxiao.service.bean.oam.OamObject;
import com.nms.drivechenxiao.service.bean.porteth.EthPortObject;
import com.nms.drivechenxiao.service.bean.porteth.ac.AcQosObject;
import com.nms.drivechenxiao.service.bean.qos.QosObject;
import com.nms.drivechenxiao.service.bean.qos.elsp.ELSPQosObject;
import com.nms.drivechenxiao.service.bean.qos.llsp.LLSPQosObject;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.equipment.slot.SlotService_MB;
import com.nms.model.ptn.ecn.CCNService_MB;
import com.nms.model.ptn.ecn.MCNService_MB;
import com.nms.model.ptn.path.eth.DualInfoService_MB;
import com.nms.model.ptn.path.protect.MspProtectService_MB;
import com.nms.model.ptn.path.protect.WrappingProtectService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.ptn.path.tunnel.LspInfoService_MB;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.ptn.port.AcPortInfoService_MB;
import com.nms.model.ptn.port.PortLagService_MB;
import com.nms.model.ptn.qos.QosInfoService_MB;
import com.nms.model.ptn.qos.QosQueueService_MB;
import com.nms.model.util.Services;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.ObjectInfo;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.service.translate.PromptInfoTranslate;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class CXOperationBase extends OperationBase {

	/** 执行时间 */
	public Date date;
	private List<QosObject> qosObjectList = new ArrayList<QosObject>();

	/**
	 * 发送
	 * 
	 * @param operationObject
	 * @throws Exception
	 */
	public void sendAction(OperationObject operationObject) throws Exception {
		this.date = new Date();
		for (CXActionObject cxActionObject : operationObject.getCxActionObjectList()) {
			if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_NNI)) { // nni
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updatePortETH(operationObject, cxActionObject);
					ConstantUtil.cxDriveService.updateNNIPortETH(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.updateUNIPortETH(operationObject, cxActionObject);
				}

			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_UNI)) { // uni
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updatePortETH(operationObject, cxActionObject);
					ConstantUtil.cxDriveService.updateUNIPortETH(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.selectUNIPortETH(operationObject, cxActionObject);
				}

			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_AC)) { // ac
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					ConstantUtil.cxDriveService.createACPortETH(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updateACPortETH(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
					ConstantUtil.cxDriveService.deleteACPortETH(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.selectACPortETH(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_TUNNEL)) { // tunnel
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					ConstantUtil.cxDriveService.createTunnelObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updateTunnelObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
					ConstantUtil.cxDriveService.deleteTunnelObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.selectTunnel(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_XC)) { // xc
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					ConstantUtil.cxDriveService.createXC(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updateXC(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
					ConstantUtil.cxDriveService.deleteXC(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.selectXc(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_PWETH)) { // pweth
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					ConstantUtil.cxDriveService.createPwEthObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updatePwEthObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
					ConstantUtil.cxDriveService.deletePwEthObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.selectPwEthObject(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_PWPDH)) { // pwpdh
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					ConstantUtil.cxDriveService.createPwPdhObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updatePwPdhObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
					ConstantUtil.cxDriveService.deletePwPdhObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.selectPwPdhObject(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_PWSDH)) { // pwsdh
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					ConstantUtil.cxDriveService.createPwSdhObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updatePwSdhObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
					ConstantUtil.cxDriveService.deletePwSdhObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.selectPwSdhObject(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_SITE)) { // site
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_LOGIN)) {
					try {
						ConstantUtil.cxDriveService.init(cxActionObject.getCxNeObject());
						ConstantUtil.cxDriveService.login(operationObject, cxActionObject);
					} catch (Exception e) {
						cxActionObject.setStatus(e.getMessage());
					}
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_LOGOUT)) {
					ConstantUtil.cxDriveService.login(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_REGISTER)) {
					ConstantUtil.cxDriveService.configHookNotify(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.seleteNE(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updateNE(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_CLEARSITE)) {
					// 调用初始化方法
					ConstantUtil.cxDriveService.clearNE(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_ELINE)) { // eline
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					ConstantUtil.cxDriveService.createELineObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
					ConstantUtil.cxDriveService.deleteELineObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.selectELineObject(operationObject, cxActionObject);
				}else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updateELineObject(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_ETREE)) { // etree
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					ConstantUtil.cxDriveService.createETreeObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
					ConstantUtil.cxDriveService.deleteETreeObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.selectETreeObject(operationObject, cxActionObject);
				}else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updateEtreeObject(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_ELAN)) { // elan
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					ConstantUtil.cxDriveService.createELanObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
					ConstantUtil.cxDriveService.deleteELanObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.selectELanObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updateElan(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_SEGMENT)) { // segment
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					ConstantUtil.cxDriveService.createSegment(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updateSegment(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
					ConstantUtil.cxDriveService.deleteSegment(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_ALARM)) { // alarm
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.seleteAlarmAll(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_PERFORMANCE)) { // preformance
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.seletePersvrAll(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_PORTSTMTIMESLOT)) { // portstmtimeslot
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					ConstantUtil.cxDriveService.createACPortSDH(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
					ConstantUtil.cxDriveService.deleteACPortSDH(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updateACPortSDH(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.selectPortStmTimesLot(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_CARD)) { // card
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					ConstantUtil.cxDriveService.createCardObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.seleteAllSlotObject(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_PORTSTM)) { // portstm
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updatePortSDH(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.selectPortStm(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_E1)) { // e1
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updatePortPDH(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.selectPortPDH(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_CESPDH)) { // cespdh
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					ConstantUtil.cxDriveService.createPDHCESObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
					ConstantUtil.cxDriveService.deletePDHCESObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.selectPDHCESObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updatePdhCesObject(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_CESSDH)) { // cessdh
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					ConstantUtil.cxDriveService.createSDHCESObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
					ConstantUtil.cxDriveService.deleteSDHCESObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updateSdhCesObject(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_PORTQOS)) { // portqos
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.createSegment(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_OSPF)) {// TXC
				// ospf
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					ConstantUtil.cxDriveService.createOSPFObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
					ConstantUtil.cxDriveService.deleteOSPFObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updateOSPFObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.selectOSPFObject(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_OSPFAREA)) {// TXC
				// ospfarea
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					ConstantUtil.cxDriveService.createOSPFAREAObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
					ConstantUtil.cxDriveService.deleteOSPFAREAObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.selectOspfAREAObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updateOSPFAREAObject(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_QOS)) { // qos
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updateQos(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.selectQos(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_REDISTRIBUTE)) {// 重分发
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					ConstantUtil.cxDriveService.createAndUpdateOSPFRedistributeObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
					ConstantUtil.cxDriveService.deleteOSPFRedistributeObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.createAndUpdateOSPFRedistributeObject(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.selectRedistributeObject(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_MCN)) { // mcn
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updateMcn(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.selectMcn(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_CCN)) { // ccn
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					ConstantUtil.cxDriveService.createCcn(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
					ConstantUtil.cxDriveService.deleteCcn(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updateCcn(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.selectCcn(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_OSPFINTERFACE)) { // ospf接口
				if (!"".equals(cxActionObject.getCcnObject().getName()) && null != cxActionObject.getCcnObject().getName()) {
					if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
						ConstantUtil.cxDriveService.createOspfInterfaceCcn(operationObject, cxActionObject);
					} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
						ConstantUtil.cxDriveService.deleteOspfInterfaceCcn(operationObject, cxActionObject);
					} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
						ConstantUtil.cxDriveService.updateOspfInterfaceCcn(operationObject, cxActionObject);
					}
				} else {
					if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
						ConstantUtil.cxDriveService.createOspfInterfaceMcn(operationObject, cxActionObject);
					} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
						ConstantUtil.cxDriveService.deleteOspfInterfaceMcn(operationObject, cxActionObject);
					} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
						ConstantUtil.cxDriveService.updateOspfInterfaceMcn(operationObject, cxActionObject);
					} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
						ConstantUtil.cxDriveService.selectOspfInterfaceMCN(operationObject, cxActionObject);
						ConstantUtil.cxDriveService.selectOspfInterfaceCCN(operationObject, cxActionObject);
					}
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_OSPFINTERFACECCN)) {
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.selectOspfInterfaceCCN(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_OSPFINTERFACEMCN)) {
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.selectOspfInterfaceMCN(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_PORTLAG)) { // lag
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					ConstantUtil.cxDriveService.createLag(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
					ConstantUtil.cxDriveService.deleteLag(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updateLag(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.selectLag(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_LSP)) { // lsp
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.selectLspByTunnel(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_ACQOS)) { // ACqos
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.selectAcqos(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_PORT)) { // port
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.selectPortETH(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updatePortETH(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_OAM)) { // oam
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					ConstantUtil.cxDriveService.createOam(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updateOam(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
					ConstantUtil.cxDriveService.deleteOam(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_MIPOAM)) { // mipoam
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					ConstantUtil.cxDriveService.createMipOam(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updateMipOam(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
					ConstantUtil.cxDriveService.deleteOam(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_ROTATE)) { // mipoam
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.setApscmd(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_ACN)) { // acn
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.selectACN(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
					ConstantUtil.cxDriveService.deleteAllACN(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_LOOPPROTECT)) { // LOOPPROTECT
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					ConstantUtil.cxDriveService.createRing(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
					ConstantUtil.cxDriveService.deleteRing(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updateRing(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SYNCHRO)) {
					ConstantUtil.cxDriveService.selectRing(operationObject, cxActionObject);
				}
			} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_FREQUENCY_INFO)) { // FrequencyInfoNeClock				
				 if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SAVEANDUPDATE)) {
					ConstantUtil.cxDriveService.updateClock(operationObject, cxActionObject);
				}else if(cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)){
					ConstantUtil.cxDriveService.selectClock(operationObject, cxActionObject);
				}
			}else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_TIMEMANAGER)) { // ptpObject				
				 if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SYNCHRO)) {
					ConstantUtil.cxDriveService.selectPtp(operationObject, cxActionObject);
				}else if(cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SAVEANDUPDATE)){
					ConstantUtil.cxDriveService.updatePtp(operationObject, cxActionObject);
				}else if(cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)){
					ConstantUtil.cxDriveService.selectPtp(operationObject, cxActionObject);
				}
			}else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_EXTERNALCLOCKINTERF)) { // enteralClockInterf
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
			//		ConstantUtil.cxDriveService.createExtclk(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
	//				ConstantUtil.cxDriveService.deleteExtclk(operationObject, cxActionObject);
				}else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updateExtclk(operationObject, cxActionObject);
				}else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SYNCHRO)) {
					ConstantUtil.cxDriveService.selectExtclk(operationObject, cxActionObject);
				}
			}else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_CLOCKSOURCE_CONFIG)) { // ClockPortESObject				
				 if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					ConstantUtil.cxDriveService.createClockPort(operationObject, cxActionObject);
				}else if(cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)){
					ConstantUtil.cxDriveService.updateClockPort(operationObject, cxActionObject);
				}else if(cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SYNCHRO)){
					ConstantUtil.cxDriveService.selectClockPort(operationObject, cxActionObject);
				}else if(cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)){
					ConstantUtil.cxDriveService.deleteClockPort(operationObject, cxActionObject);
				}else if(cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)){
					ConstantUtil.cxDriveService.deleteClockPort(operationObject, cxActionObject);
				}
				 //外时钟接口  -修改
				else if(cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SAVEANDUPDATE)){
					ConstantUtil.cxDriveService.updateExtclkPort(operationObject, cxActionObject);
				}
			}else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_PORTCONFIG)) { // 端口配置
					if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
						ConstantUtil.cxDriveService.createPtpPortOjbect(operationObject, cxActionObject);
					} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
						ConstantUtil.cxDriveService.deleteptpPortObject(operationObject, cxActionObject);
					} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
						ConstantUtil.cxDriveService.updatePtpPortOjbect(operationObject, cxActionObject);
					} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SYNCHRO)) {
						ConstantUtil.cxDriveService.selectPtpPortObject(operationObject, cxActionObject);
					}
			}else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_MSPPROTECT)) { // msp
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					ConstantUtil.cxDriveService.createMsp(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
					ConstantUtil.cxDriveService.deleteMsp(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updateMsp(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SYNCHRO)) {
					ConstantUtil.cxDriveService.selectMsp(operationObject, cxActionObject);
				}
			}else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_ETHLINKOAM)) { //oam以太网链路
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					ConstantUtil.cxDriveService.createEthEfmOam(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
					ConstantUtil.cxDriveService.deleteEthEfmOam(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updateEthEfmOam(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SYNCHRO)) {
					ConstantUtil.cxDriveService.selectEthEfmOam(operationObject, cxActionObject);
				}	
			}else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_DUALPROTECT)) { //双规保护
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					ConstantUtil.cxDriveService.createDual(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
					ConstantUtil.cxDriveService.deleteDual(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updateDual(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SYNCHRO)) {
					ConstantUtil.cxDriveService.selectDual(operationObject, cxActionObject);
				}	
			}else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_EXPMAPPINGLLSPINPUT)) { //EXP映射 LLSP 输入
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					ConstantUtil.cxDriveService.createExptoclr(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
					ConstantUtil.cxDriveService.deleteExptoclr(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updateExptoclr(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SYNCHRO)) {
					//映射的同步（全部）
					ConstantUtil.cxDriveService.selectExptoclr(operationObject, cxActionObject);
					ConstantUtil.cxDriveService.selectClrtoexp(operationObject, cxActionObject);
					ConstantUtil.cxDriveService.selectExptocos(operationObject, cxActionObject);
					ConstantUtil.cxDriveService.selectCostoexp(operationObject, cxActionObject);
					ConstantUtil.cxDriveService.selectVlanpri2cng(operationObject, cxActionObject);
					ConstantUtil.cxDriveService.selectCos2vlanpri(operationObject, cxActionObject);
				}	
			}else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_EXPMAPPINGLLSPOUTPUT)) { //EXP映射 LLSP 输出
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					ConstantUtil.cxDriveService.createClrtoexp(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
					ConstantUtil.cxDriveService.deleteClrtoexp(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updateClrtoexp(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SYNCHRO)) {
				}
			}else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_EXPMAPPINGELSPINPUT)) { //EXP映射 ELSP 输入
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					ConstantUtil.cxDriveService.createExptocos(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
					ConstantUtil.cxDriveService.deleteExptocos(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updateExptocos(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SYNCHRO)) {
				
				}	
			}else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_EXPMAPPINGELSPOUTPUT)) { //EXP映射 ELSP 输出
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					ConstantUtil.cxDriveService.createCostoexp(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
					ConstantUtil.cxDriveService.deleteCostoexp(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updateCostoexp(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SYNCHRO)) {
					
				}
			}else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_MAPPINGVLANPRITOCOLOR)) {// Vlanpri to color 
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					ConstantUtil.cxDriveService.createVlanpri2cng(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
					ConstantUtil.cxDriveService.deleteVlanpri2cng(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updateVlanpri2cng(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SYNCHRO)) {
					
				}
			}else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_MAPPINGCOSTOVLANPRI)) { //Cos2vlanpri
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					ConstantUtil.cxDriveService.createCos2vlanpri(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_DELETE)) {
					ConstantUtil.cxDriveService.deleteCos2vlanpri(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updateCos2vlanpri(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SYNCHRO)) {
					
				}
			}else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_SLOTTEMP)) { //slotTemp
				if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_INSERT)) {
					//ConstantUtil.cxDriveService.createCos2vlanpri(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SELECT)) {
					ConstantUtil.cxDriveService.selectSlotTemp(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)) {
					ConstantUtil.cxDriveService.updateSlotTemp(operationObject, cxActionObject);
				} else if (cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_SYNCHRO)) {
					
				}
			}else if(cxActionObject.getType().equals(TypeAndActionUtil.TYPE_PW_VLAN)){
				if(cxActionObject.getAction().equals(TypeAndActionUtil.ACTION_UPDATE)){
					ConstantUtil.cxDriveService.updatePwVlan(operationObject, cxActionObject);
				}
			}
		}
	}

	/**
	 * 验证是否成功
	 * 
	 * @param operationObject
	 * @throws InterruptedException
	 */
	public OperationObject verification(OperationObject operationObject) throws InterruptedException {
		while (true) {
			this.date = new Date();
			if (!super.isTimeOut(this.date)) {
				boolean flag = true;
				for (CXActionObject cxActionObject : operationObject.getCxActionObjectList()) {
					if (null == cxActionObject.getStatus() || cxActionObject.getStatus().equals("")) {
						flag = false;
					}
				}
				if (flag) {
					operationObject.setSuccess(this.isAllSuccess(operationObject));
					return operationObject;
				}
			} else {
				operationObject.setSuccess(false);
				return operationObject;
			}
			Thread.sleep(1000);
		}
	}

	/**
	 * 验证是否全是成功的
	 * 
	 * @return
	 */
	private boolean isAllSuccess(OperationObject operationObject) {

		boolean flag = true;
		for (CXActionObject cxActionObject : operationObject.getCxActionObjectList()) {
			if (!cxActionObject.getStatus().equals("配置成功") && !"登入成功".equals(cxActionObject.getStatus())) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	/**
	 * 获取NE对象
	 * 
	 * @param siteid
	 *            网元ID
	 * @return ne对象
	 * @throws Exception
	 */
	public CXNEObject getCXNEObject(int siteid) throws Exception {
		CXNEObject cxNeObject = null;
		SiteService_MB siteService = null;
		SiteInst siteInst = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteInst = siteService.select(siteid);

			cxNeObject = new CXNEObject();
			cxNeObject.setNeIp(siteInst.getCellDescribe());
			cxNeObject.setAdmin(siteInst.getUsername());
			cxNeObject.setPassword(siteInst.getUserpwd());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}
		return cxNeObject;
	}

	/**
	 * 获取actionid
	 * 
	 * @param actionObjectList
	 * @return
	 */
	public int getActionId(List<CXActionObject> cxActionObjectList) {

		Random random = new Random();
		int number = Math.abs(random.nextInt()) % 1000;

		if (cxActionObjectList.size() > 0) {
			boolean flag = true;
			for (CXActionObject cxActionObject : cxActionObjectList) {
				if (cxActionObject.getActionId() == number) {
					flag = false;
					break;
				}
			}
			if (flag) {
				return number;
			} else {
				return this.getActionId(cxActionObjectList);
			}

		} else {
			return number;
		}
	}

	/**
	 * 获取失败消息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getErrorMessage(OperationObject operationObject) throws Exception {

		String result = "";
		try {
			for (CXActionObject cxActionObject : operationObject.getCxActionObjectList()) {
				if (cxActionObject.getStatus() == null || cxActionObject.getStatus().equals("")) {
					result = "超时";
					break;
				}
				if (!cxActionObject.getStatus().equals("配置成功")) {
					result = PromptInfoTranslate.PromptInfoTranslation(cxActionObject.getStatus());
					break;
				}
			}
			return result;
		

		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * 获取端口对象
	 * 
	 * @param portInst
	 *            db层端口对象
	 * @param type
	 *            类型 uni或nni
	 * @return 驱动层端口对象
	 * @throws Exception
	 */
	public EthPortObject getEthPortObject(PortInst portInst, String type, String objtype, String action, List<QosQueue> qosQueues) throws Exception {

		EthPortObject ethPortObject = null;
		QosQueueService_MB qosQueueService = null;
		QosQueue qosQueue = null;

		if (portInst == null) {
			throw new Exception("portInst is null");
		}

		try {
			qosQueue = new QosQueue();
			ethPortObject = new EthPortObject();
			ethPortObject.setPortType("eth");
			ethPortObject.setName(portInst.getPortName());
			if (type != null && !type.equals("")) {
				ethPortObject.setRole(type);
			} else {
				ethPortObject.setRole("none");
			}

			ethPortObject.setMac(portInst.getMacAddress());
			ethPortObject.setNeType(this.getSiteType(portInst.getSiteId()));
			ethPortObject.setAdmin(portInst.getIsEnabled_code() == 0 ? "down" : "up");

			ethPortObject.setWavelength(portInst.getPortAttr().getWorkWavelength()); // 工作波长
			ethPortObject.setSfpexptype(UiUtil.getCodeById(portInst.getPortAttr().getSfpExpectType()).getCodeValue()); // SFP期望类型 对应code表主键
			ethPortObject.setFramelen(portInst.getPortAttr().getMaxFrameSize());// 最大帧长=武汉MTU --
			ethPortObject.setFc((UiUtil.getCodeById(portInst.getPortAttr().getFluidControl()).getCodeValue()).equals(0) ? "false" : "true");// 流控=武汉802.3流控
			ethPortObject.setSpeed(UiUtil.getCodeById(portInst.getPortAttr().getPortSpeed()).getCodeValue());// 设置端口速率，对应code表主键
			ethPortObject.setMac(portInst.getMacAddress());
			ethPortObject.setXgwan(portInst.getPortAttr().getTenWan() == 0 ? "false" : "true");// 启动10G WAN
			ethPortObject.setPermitpktloop(portInst.getPortAttr().getMessageLoopback() == 0 ? "false" : "true");// 是否启动报文环回
			ethPortObject.setIused(portInst.getPortAttr().getEntranceLimit());// 入口限速
			ethPortObject.setOused(portInst.getPortAttr().getExitLimit());// 出口限速
			ethPortObject.setSlowproto_tocpu(portInst.getPortAttr().getSlowAgreement() == 0 ? "false" : "true");// slowAgreement

			if ("UNI".equals(portInst.getPortType())) {
				ethPortObject.getUni().setIclrmode("vlanpri2cng");// ETHAC进来的颜色模式(信任CFI/信任VLAN优先级) **
				ethPortObject.getUni().setOclrmode("trustcng");// // ETHAC出去的颜色模式(色盲(blind)/信任)) **
				ethPortObject.getUni().setCos2vlanpri("0");//
				ethPortObject.getUni().setEncap(UiUtil.getCodeById(portInst.getPortAttr().getPortUniAttr().getEthernetPackaging()).getCodeName());// 以太网封装
				ethPortObject.getUni().setVlanmode(UiUtil.getCodeById(portInst.getPortAttr().getPortUniAttr().getVlanMode()).getCodeValue());// 以太网VLAN模式
				ethPortObject.getUni().setTpid(UiUtil.getCodeById(portInst.getPortAttr().getPortUniAttr().getOuterVlanTpId()).getCodeValue());// OUTER VLAN的TPID
				ethPortObject.getUni().setPvid(portInst.getPortAttr().getPortUniAttr().getVlanId());// 缺省VLANID
				ethPortObject.getUni().setPvpri(portInst.getPortAttr().getPortUniAttr().getVlanPri());// 缺省VLAN优先级
				ethPortObject.getUni().setSdtpid(UiUtil.getCodeById(portInst.getPortAttr().getPortUniAttr().getVlanTpId()).getCodeValue());// 运营商VLAN的TPID
				ethPortObject.getUni().setBcastlimit(portInst.getPortAttr().getPortUniAttr().getBroadcast());// 广播报文抑制
				ethPortObject.getUni().setMcastlimit(portInst.getPortAttr().getPortUniAttr().getMulticast());// 组播报文抑制
				ethPortObject.getUni().setDlflimit(portInst.getPortAttr().getPortUniAttr().getUnicast());// 未知单播报文抑制

			} else if ("NNI".equals(portInst.getPortType())) {
				ethPortObject.getNbr().setSmac(portInst.getPortAttr().getPortNniAttr().getStaticMac());// 用户配置的静态MAC地址
				ethPortObject.getNbr().setMac(portInst.getPortAttr().getPortNniAttr().getOperMac());// 邻居网元对应端口的MAC地址
				ethPortObject.getNbr().setIfidx(portInst.getPortAttr().getPortNniAttr().getOperId());// 邻居网元对应端口的接口编号
				ethPortObject.getNbr().setNeid(portInst.getPortAttr().getPortNniAttr().getNeighbourId());// 邻居网元ID
				if (portInst.getPortAttr().getPortNniAttr().getCcnEnable() == 0) {
					ethPortObject.getNbr().setCcn("false");// 是否能够层载CCN
				} else if (portInst.getPortAttr().getPortNniAttr().getCcnEnable() == 1) {
					ethPortObject.getNbr().setCcn("true");// 是否能够层载CCN
				}
			}
			if (qosQueues == null && action.equals(TypeAndActionUtil.ACTION_UPDATE)) {
				qosQueueService = (QosQueueService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosQueue);
				qosQueue.setObjId(portInst.getPortId());
				qosQueue.setObjType(objtype);
				qosQueues = qosQueueService.queryByCondition(qosQueue);
			}

			if (action.equals(TypeAndActionUtil.ACTION_INSERT)) {
				qosQueueService = (QosQueueService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosQueue);
				qosQueue.setObjId(portInst.getPortId());
				qosQueue.setObjType(objtype);
				qosQueues = qosQueueService.queryByCondition(qosQueue);
			}

			if (qosQueues.size() < 1) {
				ethPortObject.getBe().setYellowwredstart("64");
				ethPortObject.getAf1().setCir("0");
				ethPortObject.getAf2().setCir("0");
				ethPortObject.getAf3().setCir("0");
				ethPortObject.getAf4().setCir("0");
				ethPortObject.getEf().setCir("0");
				ethPortObject.getCs6().setCir("0");
				ethPortObject.getCs7().setCir("0");
			} else if (qosQueues.size() > 0) {
				for (QosQueue newqosQueue : qosQueues) {
					if (newqosQueue.getCos() == QosCosLevelEnum.BE.getValue()) {
						ethPortObject.getBe().setYellowwredstart(newqosQueue.getYellowLowThresh() + "");
					} else if (newqosQueue.getCos() == QosCosLevelEnum.AF1.getValue()) {
						ethPortObject.getAf1().setCir(newqosQueue.getCir() + "");
					} else if (newqosQueue.getCos() == QosCosLevelEnum.AF2.getValue()) {
						ethPortObject.getAf2().setCir(newqosQueue.getCir() + "");
					} else if (newqosQueue.getCos() == QosCosLevelEnum.AF3.getValue()) {
						ethPortObject.getAf3().setCir(newqosQueue.getCir() + "");
					} else if (newqosQueue.getCos() == QosCosLevelEnum.AF4.getValue()) {
						ethPortObject.getAf4().setCir(newqosQueue.getCir() + "");
					} else if (newqosQueue.getCos() == QosCosLevelEnum.EF.getValue()) {
						ethPortObject.getEf().setCir(newqosQueue.getCir() + "");
					} else if (newqosQueue.getCos() == QosCosLevelEnum.CS6.getValue()) {
						// if (this.getSiteType(portInst.getSiteId()).equals("cxt20a")) {
						// ethPortObject.getCs3().setCir(newqosQueue.getCir() + "");
						// } else {
						ethPortObject.getCs6().setCir(newqosQueue.getCir() + "");
						// }

					} else if (newqosQueue.getCos() == QosCosLevelEnum.CS7.getValue()) {
						ethPortObject.getCs7().setCir(newqosQueue.getCir() + "");
					}
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(qosQueueService);
		}
		return ethPortObject;
	}

	/**
	 * 获取端口对象
	 * 
	 * @param portInst
	 *            db层端口对象
	 * @param type
	 *            类型 uni或nni
	 * @return 驱动层端口对象
	 * @throws Exception
	 */
	public EthPortObject getPortObject(PortInst portInst, String objtype, List<QosQueue> qosQueues, String type) throws Exception {

		EthPortObject ethPortObject = null;

		if (portInst == null) {
			throw new Exception("portInst is null");
		}
		try {
			ethPortObject = new EthPortObject();
			ethPortObject.setName(portInst.getPortName());
			ethPortObject.setPortType("eth");
			// add by 0823 ----
			ethPortObject.setSiteType(getSiteType(portInst.getSiteId()));// 传入site 类型
			// ethPortObject.setSfptype(portInst.getPortAttr().getSfpActual()); //SFP实际类型
			// ethPortObject.setSfptype(portInst.getPortAttr().getSfpActual()); //SFP实际类型
			// ethPortObject.setSfpvendor(portInst.getPortAttr().getSfpVender()); //sfp厂家信息
			ethPortObject.setWavelength(portInst.getPortAttr().getWorkWavelength()); // 工作波长
			ethPortObject.setSfpexptype(UiUtil.getCodeById(portInst.getPortAttr().getSfpExpectType()).getCodeValue()); // SFP期望类型 对应code表主键
			ethPortObject.setFramelen(portInst.getPortAttr().getMaxFrameSize());// 最大帧长=武汉MTU --
			ethPortObject.setFc((UiUtil.getCodeById(portInst.getPortAttr().getFluidControl()).getCodeValue()).equals(0) ? "false" : "true");// 流控=武汉802.3流控
			ethPortObject.setSpeed(UiUtil.getCodeById(portInst.getPortAttr().getPortSpeed()).getCodeValue());// 设置端口速率，对应code表主键
			ethPortObject.setMac(portInst.getMacAddress());
			ethPortObject.setXgwan(portInst.getPortAttr().getTenWan() == 0 ? "false" : "true");// 启动10G WAN
			ethPortObject.setPermitpktloop(portInst.getPortAttr().getMessageLoopback() == 0 ? "false" : "true");// 是否启动报文环回
			ethPortObject.setIused(portInst.getPortAttr().getEntranceLimit());// 入口限速
			ethPortObject.setOused(portInst.getPortAttr().getExitLimit());// 出口限速
			ethPortObject.setSlowproto_tocpu(portInst.getPortAttr().getSlowAgreement() == 0 ? "false" : "true");// slowAgreement
			// ethPortObject.setAspeed(portInst.getPortAttr().getActualSpeed());//实际速率
			// ethPortObject.setSlowproto_tocpu(portInst.getPortAttr().getSlowAgreement());

			if (type != null && !type.equals("")) {
				ethPortObject.setRole(type);
			} else {
				ethPortObject.setRole("none");
			}

			ethPortObject.setAdmin(portInst.getIsEnabled_code() == 0 ? "down" : "up");
			ethPortObject.setNeType(this.getSiteType(portInst.getSiteId()));

			if ("UNI".equals(portInst.getPortType())) {
				ethPortObject.getUni().setIclrmode("vlanpri2cng");// ETHAC进来的颜色模式(信任CFI/信任VLAN优先级) **
				ethPortObject.getUni().setOclrmode("trustcng");// // ETHAC出去的颜色模式(色盲(blind)/信任)) **
				ethPortObject.getUni().setCos2vlanpri("0");//
				ethPortObject.getUni().setEncap(UiUtil.getCodeById(portInst.getPortAttr().getPortUniAttr().getEthernetPackaging()).getCodeValue());// 以太网封装
				ethPortObject.getUni().setVlanmode(UiUtil.getCodeById(portInst.getPortAttr().getPortUniAttr().getVlanMode()).getCodeValue());// 以太网VLAN模式
				ethPortObject.getUni().setTpid(UiUtil.getCodeById(portInst.getPortAttr().getPortUniAttr().getOuterVlanTpId()).getCodeValue());// OUTER VLAN的TPID
				ethPortObject.getUni().setPvid(portInst.getPortAttr().getPortUniAttr().getVlanId());// 缺省VLANID
				ethPortObject.getUni().setPvpri(portInst.getPortAttr().getPortUniAttr().getVlanPri());// 缺省VLAN优先级
				ethPortObject.getUni().setSdtpid(UiUtil.getCodeById(portInst.getPortAttr().getPortUniAttr().getVlanTpId()).getCodeValue());// 运营商VLAN的TPID
				ethPortObject.getUni().setBcastlimit(portInst.getPortAttr().getPortUniAttr().getBroadcast());// 广播报文抑制
				ethPortObject.getUni().setMcastlimit(portInst.getPortAttr().getPortUniAttr().getMulticast());// 组播报文抑制
				ethPortObject.getUni().setDlflimit(portInst.getPortAttr().getPortUniAttr().getUnicast());// 未知单播报文抑制

			} else if ("NNI".equals(portInst.getPortType())) {
				ethPortObject.getNbr().setSmac(portInst.getPortAttr().getPortNniAttr().getStaticMac());// 用户配置的静态MAC地址
				ethPortObject.getNbr().setMac(portInst.getPortAttr().getPortNniAttr().getOperMac());// 邻居网元对应端口的MAC地址
				ethPortObject.getNbr().setIfidx(portInst.getPortAttr().getPortNniAttr().getOperId());// 邻居网元对应端口的接口编号
				ethPortObject.getNbr().setNeid(portInst.getPortAttr().getPortNniAttr().getNeighbourId());// 邻居网元ID
				if (portInst.getPortAttr().getPortNniAttr().getCcnEnable() == 0) {
					ethPortObject.getNbr().setCcn("false");// 是否能够层载CCN
				} else if (portInst.getPortAttr().getPortNniAttr().getCcnEnable() == 1) {
					ethPortObject.getNbr().setCcn("true");// 是否能够层载CCN
				}

			}

			// System.out.println("cxoperationbase.675 : portinst="+portInst.toString());
			if (qosQueues == null) {
				return ethPortObject;
			}

			if (qosQueues.size() < 1) {
				ethPortObject.getBe().setYellowwredstart("64");
				ethPortObject.getAf1().setCir("0");
				ethPortObject.getAf2().setCir("0");
				ethPortObject.getAf3().setCir("0");
				ethPortObject.getAf4().setCir("0");
				ethPortObject.getEf().setCir("0");
				ethPortObject.getCs6().setCir("0");
				ethPortObject.getCs7().setCir("0");
			} else if (qosQueues.size() > 0) {
				for (QosQueue newqosQueue : qosQueues) {
					if (newqosQueue.getCos() == QosCosLevelEnum.BE.getValue()) {
						ethPortObject.getBe().setYellowwredstart(newqosQueue.getYellowLowThresh() + "");
					} else if (newqosQueue.getCos() == QosCosLevelEnum.AF1.getValue()) {
						ethPortObject.getAf1().setCir(newqosQueue.getCir() + "");
					} else if (newqosQueue.getCos() == QosCosLevelEnum.AF2.getValue()) {
						ethPortObject.getAf2().setCir(newqosQueue.getCir() + "");
					} else if (newqosQueue.getCos() == QosCosLevelEnum.AF3.getValue()) {
						ethPortObject.getAf3().setCir(newqosQueue.getCir() + "");
					} else if (newqosQueue.getCos() == QosCosLevelEnum.AF4.getValue()) {
						ethPortObject.getAf4().setCir(newqosQueue.getCir() + "");
					} else if (newqosQueue.getCos() == QosCosLevelEnum.EF.getValue()) {
						ethPortObject.getEf().setCir(newqosQueue.getCir() + "");
					} else if (newqosQueue.getCos() == QosCosLevelEnum.CS6.getValue()) {
						// if (this.getSiteType(portInst.getSiteId()).equals("cxt20a")) {
						// ethPortObject.getCs3().setCir(newqosQueue.getCir() + "");
						// } else {
						ethPortObject.getCs6().setCir(newqosQueue.getCir() + "");
						// }
					} else if (newqosQueue.getCos() == QosCosLevelEnum.CS7.getValue()) {
						ethPortObject.getCs7().setCir(newqosQueue.getCir() + "");
					}
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return ethPortObject;
	}

	/**
	 * 转换驱动层需要的设备类型
	 * 
	 * @param siteId
	 *            数据库网元主键
	 * @return
	 * @throws Exception
	 */
	public String getSiteType(int siteId) throws Exception {
		SiteService_MB siteService = null;
		SiteInst siteInst = null;
		String siteType = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteInst = siteService.select(siteId);

			if (siteInst != null) {

				if (siteInst.getCellType().equals("700B")) {
					siteType = "cxt100";
				} else if (siteInst.getCellType().equals("700D")) {
					siteType = "cxt20b";
				} else if (siteInst.getCellType().equals("700E")) {
					siteType = "cxt20a";
				} else if(siteInst.getCellType().equals("700A")){
					siteType = "cxt500";
				}else{
					siteType = "";
				}

			} else {
				throw new Exception("查询siteInst失败");
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}
		return siteType;
	}

	/**
	 * 根据网元ID查询网元地址
	 * 
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	public String getSiteAdress(int siteId) throws Exception {
		SiteService_MB siteService = null;
		SiteInst siteInst = null;
		String result = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteInst = siteService.select(siteId);

			if (siteInst != null) {
				result = siteInst.getCellDescribe();
			} else {
				throw new Exception("查询siteInst失败");
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}
		return result;
	}

	/**
	 * 根据端口主键查询端口名称
	 * 
	 * @param portId
	 * @return
	 * @throws Exception
	 */
	public String getPortname(int portId) throws Exception {
		PortService_MB portService = null;
		List<PortInst> portInstList = null;
		String result = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			PortInst portInst = new PortInst();
			portInst.setPortId(portId);
			portInstList = portService.select(portInst);
			if (portInstList != null && portInstList.size() == 1) {
				result = portInstList.get(0).getPortName();
			} else {
				throw new Exception("查询portInst失败");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(portService);
			portInstList = null;
		}
		return result;
	}

	/**
	 * 根据pwID查询pw对象
	 * 
	 * @param pwid
	 * @return
	 * @throws Exception
	 */
	public PwInfo getPwinfo(int pwid) throws Exception {
		PwInfo pwinfo = null;
		PwInfoService_MB pwInfoService = null;
		try {
			pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			pwinfo = new PwInfo();
			pwinfo.setPwId(pwid);
			pwinfo = pwInfoService.selectBypwid_notjoin(pwinfo);

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(pwInfoService);
		}
		return pwinfo;
	}

	/**
	 * 获得QOS对象
	 * 
	 * @param objecttype
	 *            类型
	 * @param name
	 *            名称
	 * @param sitetype
	 *            代表的是A端或者Z端
	 * 
	 * @param objectid
	 *            对应的PW,TUNNEL,XC ID
	 * @return QosObject
	 * @throws Exception
	 */
	public QosObject getqosobject(String objecttype, String sitetype, int objectid, int siteId) throws Exception {
		List<QosInfo> qosinfoList = null;
		QosInfoService_MB qosInfoService = null;
		String qostype = null;
		QosObject qosObject = null;
		try {
			// 从数据库查询qosinfo对象
			qosInfoService = (QosInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosInfo);
			qosinfoList = qosInfoService.getQosByObj(objecttype, objectid, siteId);

			if (qosinfoList.size() == 0) {
				throw new Exception("查询qosinfo出错");
			}
			qostype = qosinfoList.get(0).getQosType();

			// 根据不同类型 转换不同的qos对象
			if (qostype.equals(QosTemplateTypeEnum.ELSP.toString())) {
				qosObject = getelspqosafobject(qosinfoList, sitetype);
			} else if (qostype.equals(QosTemplateTypeEnum.LLSP.toString())) {
				qosObject = getllspqosobject(qosinfoList, sitetype);
			} else {
				// return geteelspqosobject(qosinfoList, objecttype, sitetype);
				qosObject = null;
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(qosInfoService);
		}
		return qosObject;
	}

	/**
	 * 转换成qos对象 修改qos模块中用
	 * 
	 * @param qosInfoList
	 * @param qosName
	 * @return
	 * @throws Exception
	 */
	public QosObject convertQosObject(List<QosInfo> qosInfoList, String qosName, String sitetype) throws Exception {
		String qostype = null;
		QosObject qosObject = null;
		try {

			if (null == qosInfoList || qosInfoList.size() == 0) {
				throw new Exception("qosInfoList");
			}

			qostype = qosInfoList.get(0).getQosType();

			if (qostype.equals(QosTemplateTypeEnum.ELSP.toString())) {
				qosObject = getelspqosafobject(qosInfoList, sitetype);
			} else if (qostype.equals(QosTemplateTypeEnum.LLSP.toString())) {
				qosObject = getllspqosobject(qosInfoList, sitetype);
			} else {
				// return geteelspqosobject(qosinfoList, objecttype, sitetype);
				qosObject = null;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			qostype = null;
		}
		return qosObject;
	}

	/**
	 * 获取驱动层需要的qos对象
	 * 
	 * @param acPortInfo
	 *            数据库端ac对象
	 * @return
	 * @throws Exception
	 */
	public AcQosObject getacobject(AcPortInfo acPortInfo) throws Exception {
		AcQosObject acqosobject = null;
		acqosobject = new AcQosObject();
		List<QosInfo> qosinfoList = null;
		QosInfoService_MB qosInfoService = null;
        try {
        	qosinfoList = new ArrayList<QosInfo>();
        	qosInfoService = (QosInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosInfo);
        	qosinfoList = qosInfoService.getQosByObj(EServiceType.ACPORT.toString(), acPortInfo.getId(),acPortInfo.getSiteId());
        	if (qosinfoList == null || qosinfoList.size() != 1) {
        		throw new Exception("查询acqos出错");
        	}
        	
        	QosInfo newqosInfo = acPortInfo.getSimpleQos();
        	
        	// for (QosInfo newqosInfo : qosinfoList) {
        	if ("0".equals(UiUtil.getCodeById(acPortInfo.getBufType()).getCodeValue())) {
        		acqosobject.setType("ETHAC_L2");
        		// acqosobject.setName("l2" + acPortInfo.getId());
        	} else if ("1".equals(UiUtil.getCodeById(acPortInfo.getBufType()).getCodeValue())) {
        		acqosobject.setType("ETHAC_L3");
        		// acqosobject.setName("l3" + acPortInfo.getId());
        	} else if ("2".equals(UiUtil.getCodeById(acPortInfo.getBufType()).getCodeValue())) {
        		acqosobject.setType("ETHAC_VLANPRI");
        		// acqosobject.setName("vlanpri" + acPortInfo.getId());
        	}
        	acPortInfo.getSimpleQos().setQosname(qosinfoList.get(0).getQosname());
        	acqosobject.setName(qosinfoList.get(0).getQosname());
        	acqosobject.setCbs(newqosInfo.getCos() + "");
        	acqosobject.setCir(newqosInfo.getCir() + "");
        	acqosobject.setSeq(newqosInfo.getSeq() + "");
        	
        	if (newqosInfo.getCos() == QosCosLevelEnum.AF1.getValue()) {
        		acqosobject.setCos("af1");
        	} else if (newqosInfo.getCos() == QosCosLevelEnum.AF2.getValue()) {
        		acqosobject.setCos("af2");
        	} else if (newqosInfo.getCos() == QosCosLevelEnum.AF3.getValue()) {
        		acqosobject.setCos("af3");
        	} else if (newqosInfo.getCos() == QosCosLevelEnum.AF4.getValue()) {
        		acqosobject.setCos("af4");
        	} else if (newqosInfo.getCos() == QosCosLevelEnum.BE.getValue()) {
        		acqosobject.setCos("be");
        	} else if (newqosInfo.getCos() == QosCosLevelEnum.EF.getValue()) {
        		acqosobject.setCos("ef");
        	} else if (newqosInfo.getCos() == QosCosLevelEnum.CS6.getValue()) {
        		acqosobject.setCos("cs6");
        	} else if (newqosInfo.getCos() == QosCosLevelEnum.CS7.getValue()) {
        		acqosobject.setCos("cs7");
        	}
        	
        	acqosobject.setEir(newqosInfo.getEir() + "");
        	acqosobject.setEbs(newqosInfo.getEbs() + "");
        	acqosobject.setColoraware(newqosInfo.getColorSence() + "");
        	acqosobject.setPir(newqosInfo.getPir() + "");
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(qosInfoService);
		}
		return acqosobject;
	}

	private LLSPQosObject getllspqosobject(List<QosInfo> qosInfoList, String siteType) throws Exception {
		LLSPQosObject llspQosObject = new LLSPQosObject(QosTemplateTypeEnum.LLSP.toString());
		try {

			for (QosInfo qosInfo : qosInfoList) {
				// if (objecttype.equals("PW")) {
				// llspQosObject.setName("llsp" + "pw" + qosInfo.getObjId());
				// } else if (objecttype.equals("TUNNEL")) {
				// if (siteType.equals("xc")) {
				// llspQosObject.setName("llsp" + "xc" + qosInfo.getObjId());
				// } else {
				// llspQosObject.setName("llsp" + "tunnel" + qosInfo.getObjId());
				// }
				// } else if (objecttype.equals("XC")) {
				// llspQosObject.setName("llsp" + "xc" + qosInfo.getObjId());
				// }
				llspQosObject.setName(qosInfo.getQosname());

				if (qosInfo.getCos() == QosCosLevelEnum.AF1.getValue()) {
					llspQosObject.setCos(QosCosLevelEnum.AF1.toString().toLowerCase());
				} else if (qosInfo.getCos() == QosCosLevelEnum.AF2.getValue()) {
					llspQosObject.setCos(QosCosLevelEnum.AF2.toString().toLowerCase());
				} else if (qosInfo.getCos() == QosCosLevelEnum.AF3.getValue()) {
					llspQosObject.setCos(QosCosLevelEnum.AF3.toString().toLowerCase());
				} else if (qosInfo.getCos() == QosCosLevelEnum.AF4.getValue()) {
					llspQosObject.setCos(QosCosLevelEnum.AF4.toString().toLowerCase());
				} else if (qosInfo.getCos() == QosCosLevelEnum.BE.getValue()) {
					llspQosObject.setCos(QosCosLevelEnum.BE.toString().toLowerCase());
				} else if (qosInfo.getCos() == QosCosLevelEnum.EF.getValue()) {
					llspQosObject.setCos(QosCosLevelEnum.EF.toString().toLowerCase());
				} else if (qosInfo.getCos() == QosCosLevelEnum.CS6.getValue()) {
					// if (this.getSiteType(siteId).equals("cxt20a")) {
					// llspQosObject.setCos("cs3");
					// } else {
					// llspQosObject.setCos("cs6");
					// }
					llspQosObject.setCos(QosCosLevelEnum.CS6.toString().toLowerCase());
				} else if (qosInfo.getCos() == QosCosLevelEnum.CS7.getValue()) {
					llspQosObject.setCos(QosCosLevelEnum.CS7.toString().toLowerCase());
				}

				if (siteType.equals("a")) {
					if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.FORWARD.getValue()) {
						llspQosObject.setOcir(qosInfo.getCir() + "");
						llspQosObject.setOcbs(qosInfo.getCbs() + "");
						llspQosObject.setOeir(qosInfo.getEir() + "");
						llspQosObject.setOebs(qosInfo.getEbs() + "");
						llspQosObject.setOpir(qosInfo.getPir() + "");
					} else if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.BACKWARD.getValue()) {
						llspQosObject.setIcir(qosInfo.getCir() + "");
						llspQosObject.setIcbs(qosInfo.getCbs() + "");
						llspQosObject.setIeir(qosInfo.getEir() + "");
						llspQosObject.setIebs(qosInfo.getEbs() + "");
						llspQosObject.setIpir(qosInfo.getPir() + "");
					}
				} else if (siteType.equals("z")) {
					if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.FORWARD.getValue()) {
						llspQosObject.setIcir(qosInfo.getCir() + "");
						llspQosObject.setIcbs(qosInfo.getCbs() + "");
						llspQosObject.setIeir(qosInfo.getEir() + "");
						llspQosObject.setIebs(qosInfo.getEbs() + "");
						llspQosObject.setIpir(qosInfo.getPir() + "");
					} else if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.BACKWARD.getValue()) {
						llspQosObject.setOcir(qosInfo.getCir() + "");
						llspQosObject.setOcbs(qosInfo.getCbs() + "");
						llspQosObject.setOeir(qosInfo.getEir() + "");
						llspQosObject.setOebs(qosInfo.getEbs() + "");
						llspQosObject.setOpir(qosInfo.getPir() + "");
					}
				} else {
					if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.FORWARD.getValue()) {
						llspQosObject.setOcir(qosInfo.getCir() + "");
						llspQosObject.setOcbs(qosInfo.getCbs() + "");
						llspQosObject.setOeir(qosInfo.getEir() + "");
						llspQosObject.setOebs(qosInfo.getEbs() + "");
						llspQosObject.setOpir(qosInfo.getPir() + "");
					} else if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.BACKWARD.getValue()) {
						llspQosObject.setIcir(qosInfo.getCir() + "");
						llspQosObject.setIcbs(qosInfo.getCbs() + "");
						llspQosObject.setIeir(qosInfo.getEir() + "");
						llspQosObject.setIebs(qosInfo.getEbs() + "");
						llspQosObject.setIpir(qosInfo.getPir() + "");
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return llspQosObject;
	}

	private ELSPQosObject getelspqosafobject(List<QosInfo> qosInfoList, String siteType) throws Exception {
		ELSPQosObject elspQosObject = new ELSPQosObject(QosTemplateTypeEnum.ELSP.toString());
		for (QosInfo qosInfo : qosInfoList) {
			// if (objecttype.equals("PW")) {
			// elspQosObject.setName("elsp" + "pw" + qosInfo.getObjId());
			// } else if (objecttype.equals("TUNNEL")) {
			// if (siteType.equals("xc")) {
			// elspQosObject.setName("elsp" + "xc" + qosInfo.getObjId());
			// } else {
			// elspQosObject.setName("elsp" + "tunnel" + qosInfo.getObjId());
			// }
			// } else if (objecttype.equals("XC")) {
			// elspQosObject.setName("elsp" + "xc" + qosInfo.getObjId());
			// }
			elspQosObject.setName(qosInfo.getQosname());
			if (siteType.equals("a")) {
				if (qosInfo.getCos() == QosCosLevelEnum.AF1.getValue()) {
					if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.FORWARD.getValue()) {
						elspQosObject.getAf1().setOcir(qosInfo.getCir() + "");
						elspQosObject.getAf1().setOeir(qosInfo.getEir() + "");
					} else if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.BACKWARD.getValue()) {
						elspQosObject.getAf1().setIcir(qosInfo.getCir() + "");
						elspQosObject.getAf1().setIeir(qosInfo.getEir() + "");
					}
				}

				if (qosInfo.getCos() == QosCosLevelEnum.AF2.getValue()) {
					if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.FORWARD.getValue()) {
						elspQosObject.getAf2().setOcir(qosInfo.getCir() + "");
						elspQosObject.getAf2().setOeir(qosInfo.getEir() + "");
					} else if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.BACKWARD.getValue()) {
						elspQosObject.getAf2().setIcir(qosInfo.getCir() + "");
						elspQosObject.getAf2().setIeir(qosInfo.getEir() + "");
					}
				}

				if (qosInfo.getCos() == QosCosLevelEnum.AF3.getValue()) {
					if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.FORWARD.getValue()) {
						elspQosObject.getAf2().setOcir(qosInfo.getCir() + "");
						elspQosObject.getAf2().setOeir(qosInfo.getEir() + "");
					} else if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.BACKWARD.getValue()) {
						elspQosObject.getAf2().setIcir(qosInfo.getCir() + "");
						elspQosObject.getAf2().setIeir(qosInfo.getEir() + "");
					}
				}

				if (qosInfo.getCos() == QosCosLevelEnum.AF4.getValue()) {
					if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.FORWARD.getValue()) {
						elspQosObject.getAf2().setOcir(qosInfo.getCir() + "");
						elspQosObject.getAf2().setOeir(qosInfo.getEir() + "");
					} else if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.BACKWARD.getValue()) {
						elspQosObject.getAf2().setIcir(qosInfo.getCir() + "");
						elspQosObject.getAf2().setIeir(qosInfo.getEir() + "");
					}
				}

				if (qosInfo.getCos() == QosCosLevelEnum.CS6.getValue()) {
					if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.FORWARD.getValue()) {
						elspQosObject.getCs6().setOcir(qosInfo.getCir() + "");
					} else if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.BACKWARD.getValue()) {
						elspQosObject.getCs6().setIcir(qosInfo.getCir() + "");
					}
				}

				if (qosInfo.getCos() == QosCosLevelEnum.CS7.getValue()) {
					if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.FORWARD.getValue()) {
						elspQosObject.getCs6().setOcir(qosInfo.getCir() + "");
					} else if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.BACKWARD.getValue()) {
						elspQosObject.getCs6().setIcir(qosInfo.getCir() + "");
					}
				}

				if (qosInfo.getCos() == QosCosLevelEnum.BE.getValue()) {
					if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.FORWARD.getValue()) {
						elspQosObject.getBe().setOeir(qosInfo.getEir() + "");
						elspQosObject.getBe().setOebs(qosInfo.getEbs() + "");
					} else if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.BACKWARD.getValue()) {
						elspQosObject.getBe().setIeir(qosInfo.getEir() + "");
						elspQosObject.getBe().setIebs(qosInfo.getEbs() + "");
					}
				}

				if (qosInfo.getCos() == QosCosLevelEnum.EF.getValue()) {
					if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.FORWARD.getValue()) {
						elspQosObject.getEf().setOcir(qosInfo.getCir() + "");
					} else if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.BACKWARD.getValue()) {
						elspQosObject.getEf().setIcir(qosInfo.getCir() + "");
					}
				}
			}

			if (siteType.equals("z")) {
				if (qosInfo.getCos() == QosCosLevelEnum.AF1.getValue()) {
					if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.FORWARD.getValue()) {
						elspQosObject.getAf1().setIcir(qosInfo.getCir() + "");
						elspQosObject.getAf1().setIeir(qosInfo.getEir() + "");
					} else if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.FORWARD.getValue()) {
						elspQosObject.getAf1().setOcir(qosInfo.getCir() + "");
						elspQosObject.getAf1().setOeir(qosInfo.getEir() + "");
					}
				}

				if (qosInfo.getCos() == QosCosLevelEnum.AF2.getValue()) {
					if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.FORWARD.getValue()) {
						elspQosObject.getAf2().setIcir(qosInfo.getCir() + "");
						elspQosObject.getAf2().setIeir(qosInfo.getEir() + "");
					} else if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.BACKWARD.getValue()) {
						elspQosObject.getAf2().setOcir(qosInfo.getCir() + "");
						elspQosObject.getAf2().setOeir(qosInfo.getEir() + "");
					}
				}

				if (qosInfo.getCos() == QosCosLevelEnum.AF3.getValue()) {
					if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.FORWARD.getValue()) {
						elspQosObject.getAf2().setIcir(qosInfo.getCir() + "");
						elspQosObject.getAf2().setIeir(qosInfo.getEir() + "");
					} else if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.BACKWARD.getValue()) {
						elspQosObject.getAf2().setOcir(qosInfo.getCir() + "");
						elspQosObject.getAf2().setOeir(qosInfo.getEir() + "");
					}
				}

				if (qosInfo.getCos() == QosCosLevelEnum.AF4.getValue()) {
					if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.FORWARD.getValue()) {
						elspQosObject.getAf2().setIcir(qosInfo.getCir() + "");
						elspQosObject.getAf2().setIeir(qosInfo.getEir() + "");
					} else if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.BACKWARD.getValue()) {
						elspQosObject.getAf2().setOcir(qosInfo.getCir() + "");
						elspQosObject.getAf2().setOeir(qosInfo.getEir() + "");
					}
				}

				if (qosInfo.getCos() == QosCosLevelEnum.CS6.getValue()) {
					if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.FORWARD.getValue()) {
						elspQosObject.getCs6().setIcir(qosInfo.getCir() + "");
					} else if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.BACKWARD.getValue()) {
						elspQosObject.getCs6().setOcir(qosInfo.getCir() + "");
					}
				}

				if (qosInfo.getCos() == QosCosLevelEnum.CS7.getValue()) {
					if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.FORWARD.getValue()) {
						elspQosObject.getCs6().setIcir(qosInfo.getCir() + "");
					} else if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.BACKWARD.getValue()) {
						elspQosObject.getCs6().setOcir(qosInfo.getCir() + "");
					}
				}

				if (qosInfo.getCos() == QosCosLevelEnum.BE.getValue()) {
					if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.FORWARD.getValue()) {
						elspQosObject.getBe().setIeir(qosInfo.getEir() + "");
						elspQosObject.getBe().setIebs(qosInfo.getEbs() + "");
					} else if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.BACKWARD.getValue()) {
						elspQosObject.getBe().setOeir(qosInfo.getEir() + "");
						elspQosObject.getBe().setOeir(qosInfo.getEir() + "");
					}
				}

				if (qosInfo.getCos() == QosCosLevelEnum.EF.getValue()) {
					if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.FORWARD.getValue()) {
						elspQosObject.getEf().setIcir(qosInfo.getCir() + "");
					} else if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.BACKWARD.getValue()) {
						elspQosObject.getEf().setOcir(qosInfo.getCir() + "");
					}
				}
			}
		}

		return elspQosObject;
	}

	/**
	 * 转换驱动层OAM对象
	 * 
	 * @param objType
	 *            类型，TUNNEL、PW等
	 * @param objId
	 *            对应类型的主键，如类型为tunnel objId就是tunnel表主键
	 * @return
	 * @throws Exception
	 */
	public OamObject convertOamObject_mep(String objType, int serviceId, int siteId, List<OamInfo> oamInfoList, String action) throws Exception {

		OamObject oamObject = null;
		/*	OamInfoService oamInfoService = null;
		OamInfo oamInfo = null;
		OamMepInfo oamMepInfo = null;*/
		try {
			/*if (action.equals(TypeAndActionUtil.ACTION_INSERT)) {
				oamInfoService = (OamInfoService) ConstantUtil.serviceFactory.newService(Services.OamInfo);
				oamMepInfo = new OamMepInfo();
				oamMepInfo.setServiceId(serviceId);
				oamMepInfo.setObjType(objType);

				oamInfo = new OamInfo();
				oamInfo.setOamMep(oamMepInfo);

				oamInfoList = oamInfoService.queryByServiceId(oamInfo);
			}*/
			if (oamInfoList != null && oamInfoList.size() > 0) {
				oamObject = new OamObject();
				for (int i = 0; i < oamInfoList.size(); i++) {
					if (oamInfoList.get(i).getOamMep() != null) {
						if (oamInfoList.get(i).getOamMep().getSiteId() == siteId) {
							oamObject = convertOamObject_mep(oamInfoList.get(i));
						}
					}
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			/*oamInfoService = null;
			oamInfo = null;
			oamMepInfo = null;*/
			oamInfoList = null;
		}
		return oamObject;
	}

	/**
	 * DB oam转Analysis oam
	 * 
	 * @param oamInfo
	 * @return
	 * @throws Exception
	 */
	public OamObject convertOamObject_mep(OamInfo oamInfo) throws Exception {

		OamObject oamObject = new OamObject();
		try {
			if(null!=oamInfo.getOamMep()){
				oamObject.setMegid(oamInfo.getOamMep().getMegId()+"");
			}
			oamObject.setMepid(oamInfo.getOamMep().getLocalMepId() + "");
			oamObject.setPeer(oamInfo.getOamMep().getRemoteMepId() + "");
			
			if(oamInfo.getOamMep().getCvCycle()!=0&&oamInfo.getOamMep().isCv()){
				oamObject.setCvintvl((Integer.parseInt(UiUtil.getCodeById(oamInfo.getOamMep().getCvCycle()).getCodeValue()) + 1) + "");
			}else{
				oamObject.setCvintvl("1");
			}


			// if (oamInfo.getOamMep().getCvCycle() == 0) {
			// oamObject.setCvintvl("1");
			// } else {
			// if ("0".equals(UiUtil.getCodeById(oamInfo.getOamMep().getCvCycle()).getCodeValue())) {
			// oamObject.setCvintvl("1");
			// } else if ("1".equals(UiUtil.getCodeById(oamInfo.getOamMep().getCvCycle()).getCodeValue())) {
			// oamObject.setCvintvl("2");
			// } else if ("2".equals(UiUtil.getCodeById(oamInfo.getOamMep().getCvCycle()).getCodeValue())) {
			// oamObject.setCvintvl("3");
			// } else if ("3".equals(UiUtil.getCodeById(oamInfo.getOamMep().getCvCycle()).getCodeValue())) {
			// oamObject.setCvintvl("4");
			// }
			// }

			oamObject.setLvl(oamInfo.getOamMep().getMel() + "");
			oamObject.setEncsf(oamInfo.getOamMep().isCsfEnable() ? "true" : "false");
			oamObject.setIflck(oamInfo.getOamMep().isLck() ? "true" : "false");
			oamObject.setEnlm(oamInfo.getOamMep().isLm() ? "true" : "false");
			oamObject.setEndm(oamInfo.getOamMep().isDm() ? "true" : "false");
			oamObject.setLpbtimeout(oamInfo.getOamMep().getLpbOutTime() + "");
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			oamInfo = null;
		}
		return oamObject;
	}

	public OamMipObject convertOamObject_mip(OamInfo oamInfo) throws Exception {

		OamMipObject oamObject = new OamMipObject();
		try {
			oamObject.setMegid(oamInfo.getOamMip().getMegId() + "");
			oamObject.setMipid(oamInfo.getOamMip().getMipId() + "");
			oamObject.setAMepId(oamInfo.getOamMip().getAMId() + "");
			oamObject.setZMepId(oamInfo.getOamMip().getZMId() + "");
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			oamInfo = null;
		}
		return oamObject;
	}

	public List<OamInfo> convertOamInfo_mep(OamObject oamObject) throws Exception {
		List<OamInfo> list = new ArrayList<OamInfo>();
		OamInfo oamInfo = new OamInfo();
		OamMepInfo oamMepInfo = new OamMepInfo();
		oamInfo.setOamType(OamTypeEnum.MEP);
		oamMepInfo.setMegUmc(oamObject.getMegid());
		oamMepInfo.setLocalMepId(Integer.valueOf(oamObject.getMepid()));
		oamMepInfo.setRemoteMepId(Integer.valueOf(oamObject.getPeer()));
		oamMepInfo.setMel(Integer.valueOf(oamObject.getLvl()));
		oamMepInfo.setLck("true".equals(oamObject.getIflck()) ? true : false);
		oamMepInfo.setLpbOutTime(Integer.valueOf(oamObject.getLpbtimeout()));
		oamMepInfo.setCv(true);
		// oamMepInfo.setSiteId(ConstantUtil.siteId);

		oamMepInfo.setCvCycle(UiUtil.getCodeByValue("OAMCVCYCLE", (Integer.parseInt(oamObject.getCvintvl()) - 1) + "").getId());
		// if ("1".equals(oamObject.getCvintvl())) {
		// oamMepInfo.setCvCycle(0);
		// } else if ("2".equals(oamObject.getCvintvl())) {
		// oamMepInfo.setCvCycle(1);
		// } else if ("3".equals(oamObject.getCvintvl())) {
		// oamMepInfo.setCvCycle(2);
		// } else if ("4".equals(oamObject.getCvintvl())) {
		// oamMepInfo.setCvCycle(3);
		// }

		oamInfo.setOamMep(oamMepInfo);
		list.add(oamInfo);

		return list;
	}

	public OamInfo convertOamInfo_mip(OamMipObject oamMipInfo) throws Exception {

		OamInfo oamInfo = new OamInfo();
		try {
			oamInfo.setOamType(OamTypeEnum.MIP);
			oamInfo.setOamMip(new OamMipInfo());
			oamInfo.getOamMip().setMegId(Integer.valueOf(oamMipInfo.getMegid()));
			oamInfo.getOamMip().setMipId(Integer.valueOf(oamMipInfo.getMipid()));
			oamInfo.getOamMip().setAMId(Integer.valueOf(oamMipInfo.getAMepId()));
			oamInfo.getOamMip().setZMId(Integer.valueOf(oamMipInfo.getZMepId()));
			// oamInfo.getOamMip().setSiteId(ConstantUtil.siteId);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return oamInfo;
	}

	/**
	 * 
	 * @param currentAlarmInfo
	 * @param alarm_objectId
	 * @throws Exception
	 */
	public ObjectInfo converObjectIdAlarm(String alarm_objectId, int siteId) throws Exception {
		if (null == alarm_objectId || alarm_objectId.equals("")) {
			throw new Exception("alarm_objectId为空");
		}
		String[] strArray = null;
		String type = null;
		PwInfoService_MB pwInfoService = null;
		PwInfo pwinfo = null;
		PortService_MB portService = null;
		PortInst portInst = null;
		TunnelService_MB tunnelService = null;
		Tunnel tunnel = null;
		ObjectInfo objectInfo = new ObjectInfo();
		int portId = 0;
		String tunnelSelectType = "";
		LspInfoService_MB lspParticularService = null;
		Lsp lspParticular = null;
		SlotService_MB slotService = null;
		SlotInst slotInst = null;
		String[] str_alarm=null;
		PortLagInfo lagInfo=null;
		PortLagService_MB lagService=null;
		List<PortLagInfo> lagInfoList=null;
		AcPortInfoService_MB acInfoService=null;
		AcPortInfo acInfo=null;
		List<AcPortInfo> acPortInfoList=null;
		MspProtect mspInfo=null;
		MspProtectService_MB mspService=null;
		List<MspProtect> mspPortInfoList=null;
		DualInfoService_MB dualInfoService=null;
		DualInfo dualInfo=null;
		MCNService_MB mcnService=null;
		MCN mcnInfo=null;
		List<MCN> mcnList=null;
		CCNService_MB ccnService = null;
		CCN ccnInfo=null;
		List<CCN> ccnInfoList=null;
		WrappingProtectService_MB wrappingService = null;
		LoopProtectInfo loopProtectInfo=null;
		List<LoopProtectInfo> loopInfoList=null;
		List<SlotInst> slotInstList=null;
		try {
			strArray = alarm_objectId.split("/");
			str_alarm= alarm_objectId.split(":");
			
			if (strArray.length < 2&&str_alarm.length<2) {
				return null;
			}
			type = strArray[0];
			if (strArray.length >= 2&&type.length() >= 2) {
				if (type.contains("pw")) {//pw 主动告警
					pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
					pwinfo = pwInfoService.select(siteId, Integer.parseInt(strArray[1]));
					if (null == pwinfo) {
						return null;
					}
					objectInfo.setObjectType(EObjectType.PW);
					objectInfo.setObjectId(pwinfo.getPwId());
					objectInfo.setObjectName(pwinfo.getPwName());

					tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
					tunnel = new Tunnel();
					tunnel.setTunnelId(pwinfo.getTunnelId());
					List<Tunnel> tunnelList = tunnelService.select(tunnel);
					if (null == tunnelList || tunnelList.size() == 0) {
						return null;
					}
					tunnel = tunnelList.get(0);
					if (pwinfo.getApwServiceId() == Integer.parseInt(strArray[1])) {
						portId = tunnel.getAPortId();
					} else {
						portId = tunnel.getZPortId();
					}
				}else if(type.equals("lag")){//lag 主动告警
					lagService = (PortLagService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTLAG);
					lagInfo = new PortLagInfo();
					lagInfo.setSiteId(siteId);
					lagInfo.setLagID(Integer.parseInt(strArray[1]));
					lagInfoList = lagService.selectByCondition(lagInfo);
					if(lagInfoList!=null&&lagInfoList.size()>0){//查找到记录，添加主动告警
						lagInfo = lagInfoList.get(0);
						objectInfo.setObjectType(EObjectType.LAG);
						objectInfo.setObjectName("lag"+lagInfo.getLagID());
						objectInfo.setObjectId(lagInfo.getId());
						portId = lagInfo.getPortId();					
					}					
				}else if(type.equals("aclist")){//ac           aclist/pdh/1
					if(strArray.length>=3){
						acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
						acInfo=new AcPortInfo();
						acInfo.setSiteId(siteId);
						acInfo.setAcBusinessId(Integer.parseInt(strArray[2]));
						acPortInfoList = acInfoService.queryByAcPortInfo(acInfo);
						if(acPortInfoList!=null&&acPortInfoList.size()>0){
							acInfo=acPortInfoList.get(0);
							objectInfo.setObjectType(EObjectType.AC);
							objectInfo.setObjectName(acInfo.getName());
							objectInfo.setObjectId(acInfo.getId());
							portId = acInfo.getPortId();
						}
					}
//				}
//				else if(type.equals("card")){// card/4/1
//					cardService=(CardService) ConstantUtil.serviceFactory.newService(Services.CARD);
//					cardInst=new CardInst();
//					cardInst.setSiteId(siteId);
//					cardInst.setEquipId(Integer.parseInt(strArray[2]));
//					cardInstList=cardService.select(cardInst);
//					if(cardInstList!=null&&cardInstList.size()>0){
//						cardInst=cardInstList.get(0);
//					//	objectInfo.setObjectType(EObjectType.CARD);
//						objectInfo.setObjectName(cardInst.getCardName());
//						objectInfo.setObjectId(cardInst.getId());
//						objectInfo.setSlotId(Integer.parseInt(strArray[2]));
				//	}
					
				}else if(type.equals("card")){// card/4/4          strArray[2]为设备标识；
					slotService = (SlotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SLOT);
					slotInst = new SlotInst();
					slotInst.setSiteId(siteId);
					slotInst.setNumber(Integer.parseInt(strArray[2]));
					slotInstList = slotService.select(slotInst);
					if(slotInstList!=null&&slotInstList.size()>0){
						slotInst=slotInstList.get(0);
					objectInfo.setObjectType(EObjectType.SLOT);
					objectInfo.setObjectName(Integer.parseInt(strArray[2])+"");
					objectInfo.setObjectId(slotInst.getId());
					objectInfo.setSlotId(slotInst.getId());
					}
				}else if(type.equals("vlan")){
					
				}else if(type.equals("TOD")){//tod； 默认 out 为如8.1/9.1          in 为8.2/9.2  （属性mode  不可修改）
					portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
					portInst = new PortInst();
					portInst.setPortName(strArray[1]);
					portInst.setSiteId(siteId);
					List<PortInst> portInstList = portService.select(portInst);
					if (null == portInstList || portInstList.size() == 0) {
						return null;
					}
					portInst = portInstList.get(0);
					objectInfo.setObjectType(EObjectType.PORT);
					objectInfo.setObjectName((strArray[1]));
					objectInfo.setObjectId(portInst.getPortId());
					portId = portInst.getPortId();
				}else if(type.equals("dual")){//dual 双规保护主动告警
					dualInfoService = (DualInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.DUALINFO);
					dualInfo = dualInfoService.selectBySiteIdAndBusinessId(siteId, Integer.parseInt(strArray[1]));
					if(dualInfo==null){
						return null;
					}
					objectInfo.setObjectType(EObjectType.DUAL);
					objectInfo.setObjectName(dualInfo.getName());
					objectInfo.setObjectId(dualInfo.getId());
					if(dualInfo.getaSiteId()==siteId){
						portId = dualInfo.getAportId();
					}else{
						portId = dualInfo.getZportId();
					}										
				}else if(type.equals("msp")){// msp保护主动告警
					mspService = (MspProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.MSPPROTECT);
					mspInfo = new MspProtect();
					mspInfo.setSiteId(siteId);
					mspInfo.setBusinessId(Integer.parseInt(strArray[1]));
					mspPortInfoList = mspService.select(mspInfo);
					if(mspPortInfoList!=null&&mspPortInfoList.size()>0){
						mspInfo=mspPortInfoList.get(0);
						objectInfo.setObjectType(EObjectType.MSP);
						objectInfo.setObjectName(mspInfo.getName());
						objectInfo.setObjectId(mspInfo.getId());
						portId = mspInfo.getProtectPortId();
					}
					
				}else if(type.equals("ring")){
					wrappingService = (WrappingProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.WRAPPINGPROTECT);
					loopProtectInfo = new LoopProtectInfo();
					loopProtectInfo.setSiteId(siteId);
					loopProtectInfo.setLoopBusinessId(Integer.parseInt(strArray[1]));
					loopInfoList = wrappingService.select(loopProtectInfo);
					if(loopInfoList!=null&&loopInfoList.size()>0){
						loopProtectInfo=loopInfoList.get(0); 
						objectInfo.setObjectType(EObjectType.RING);
						objectInfo.setObjectName(loopProtectInfo.getName());
						objectInfo.setObjectId(loopProtectInfo.getId());
						portId = loopProtectInfo.getWestPort();
					}
				}else if(type.equals("cfp")){
					
				}else if(type.equals("mcn")){
					mcnService = (MCNService_MB) ConstantUtil.serviceFactory.newService_MB(Services.MCN);
					mcnList = mcnService.queryByNeID(siteId+"");
					if(mcnList!=null&&mcnList.size()>0){
						mcnInfo=mcnList.get(0);
						objectInfo.setObjectType(EObjectType.MCN);
						objectInfo.setObjectName("mcn/"+mcnInfo.getId());
						objectInfo.setObjectId(mcnInfo.getId());
					}
				}else if(type.equals("ccn")){
					ccnService = (CCNService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CCN);
					ccnInfoList = ccnService.queryByNeInAndName(strArray[1], siteId);
					if(ccnInfoList!=null&&ccnInfoList.size()>0){
						ccnInfo=ccnInfoList.get(0);
						objectInfo.setObjectType(EObjectType.CCN);
						objectInfo.setObjectName("mcn/"+ccnInfo.getCcnName());
						objectInfo.setObjectId(ccnInfo.getId());
					}
				}
				else if (type.equals("eth") || type.equals("sdh") || type.equals("pdh")) {//端口 主动告警
					portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
					portInst = new PortInst();
					portInst.setPortName(strArray[1]);
					portInst.setSiteId(siteId);
					List<PortInst> portInstList = portService.select(portInst);
					if (null == portInstList || portInstList.size() == 0) {
						return null;
					}
					portInst = portInstList.get(0);
					objectInfo.setObjectType(EObjectType.PORT);
					objectInfo.setObjectName(portInst.getPortName());
					objectInfo.setObjectId(portInst.getPortId());
					portId = portInst.getPortId();						
				}else if (type.equals("tunnel") || type.equals("xc")) {//tunnel主动告警
					tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
					portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
					tunnel = tunnelService.selectBySiteIdAndServiceId(siteId, Integer.parseInt(strArray[1]));
					if (null == tunnel) {
						return null;
					}
					objectInfo.setObjectType(EObjectType.TUNNEL);
					objectInfo.setObjectId(tunnel.getTunnelId());
					if(siteId==tunnel.getASiteId()){
						objectInfo.setObjectName(portService.selectPortybyid(tunnel.getAPortId()).getPortName());
					}else{
						objectInfo.setObjectName(portService.selectPortybyid(tunnel.getZPortId()).getPortName());
					}
					lspParticularService = (LspInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.LSPINFO);
					lspParticular = lspParticularService.select(siteId, Integer.parseInt(strArray[1]), tunnelSelectType);
					if (null == lspParticular) {
						return null;
					}
					if (lspParticular.getAtunnelbusinessid() == Integer.parseInt(strArray[1])) {
						portId = lspParticular.getAPortId();
					} else {
						portId = lspParticular.getZPortId();
					}
				}
			}
			if (portId != 0) {// 赋值 端口名称
				slotService = (SlotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SLOT);
				slotInst = slotService.select(portId);
				if (slotInst != null) {
					//objectInfo.setSlotId(slotInst.getSiteId());
					objectInfo.setSlotId(slotInst.getId());
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(acInfoService);
			UiUtil.closeService_MB(lagService);
			UiUtil.closeService_MB(portService);
			UiUtil.closeService_MB(dualInfoService);
			UiUtil.closeService_MB(mspService);
			UiUtil.closeService_MB(wrappingService);
			UiUtil.closeService_MB(mcnService);
			UiUtil.closeService_MB(ccnService);
			UiUtil.closeService_MB(pwInfoService);
			UiUtil.closeService_MB(slotService);
			UiUtil.closeService_MB(lspParticularService);
			UiUtil.closeService_MB(tunnelService);
		}
		return objectInfo;
	}

	/**
	 * 获取operationObject中成功的cxactionObject 用来做回滚用
	 * 
	 * @param operationObject
	 *            验证完的operationObject
	 * @param action
	 *            回滚动作
	 * @return
	 * @throws Exception
	 */
	public List<CXActionObject> getSuccess(OperationObject operationObject, String action) throws Exception {

		List<CXActionObject> cxActionObjectList = null;

		try {
			cxActionObjectList = new ArrayList<CXActionObject>();
			for (CXActionObject cxActionObject : operationObject.getCxActionObjectList()) {
				if (cxActionObject.getStatus().equals("配置成功")) {
					cxActionObject.setAction(action);
					cxActionObjectList.add(cxActionObject);
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return cxActionObjectList;
	}

	/**
	 * 根据端口名和网元ID查询端口对象
	 * 
	 * @author kk
	 * 
	 * @param siteid
	 *            网元ID
	 * @param portName
	 *            端口名称
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	public PortInst getPortByName(int siteid, String portName) {
		PortService_MB portService = null;
		List<PortInst> portInstList = null;
		PortInst portInst = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);

			portInst = new PortInst();
			portInst.setSiteId(siteid);
			portInst.setPortName(portName);
			portInstList = portService.select(portInst);

			if (portInstList != null && portInstList.size() == 1) {
				portInst = portInstList.get(0);
			} else {
				portInst = null;
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(portService);
			portInstList = null;
		}
		return portInst;
	}

	/**
	 * 根据网元ip获取网元对象
	 * 
	 * @author kk
	 * 
	 * @param siteIp
	 *            网元对象
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	public SiteInst getSiteByIp(String siteIp) throws Exception {
		SiteInst siteInst = null;
		List<SiteInst> siteInstList = null;
		SiteService_MB siteService = null;
		try {
			siteInst = new SiteInst();
			siteInst.setCellDescribe(siteIp);

			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteInstList = siteService.select(siteInst);

			if (siteInstList.size() != 1) {
				siteInst = null;
			} else {
				siteInst = siteInstList.get(0);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(siteService);
		}
		return siteInst;
	}

	/**
	 * 获取当前毫秒数
	 * 
	 * @author kk
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	public String getNowMS() {

		Date date = new Date();
		return date.getTime() + "";

	}

	/**
	 * 查询qos
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	public List<QosInfo> qosInfo(String qosname, int siteId) throws Exception {
		List<QosInfo> qosInfoList = null;
		QosInfoService_MB qosInfoService = null;
		QosInfo qosInfo = null;
		try {
			qosInfoService = (QosInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosInfo);
			qosInfo = new QosInfo();
			qosInfo.setSiteId(siteId);
			qosInfo.setQosname(qosname);
			qosInfoList = qosInfoService.queryByCondition(qosInfo);

		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(qosInfoService);
		}

		return qosInfoList;
	}

	/**
	 * 获取数据库PW对象
	 * 
	 * @author kk
	 * 
	 * @param pwServiceName
	 *            设备返回的pw名称格式为"pweth/1"
	 * @param siteId
	 *            网元id
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	public PwInfo getPwInfo(String pwServiceName, int siteId) throws Exception {
		PwInfo pwInfo = null;
		List<PwInfo> pwInfoList = null;
		PwInfoService_MB pwInfoService = null;
		try {
			
			pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			int pwServiceId = Integer.parseInt(pwServiceName.substring(pwServiceName.lastIndexOf("/") + 1, pwServiceName.length()));
			pwInfoList = pwInfoService.select_synchro(siteId, pwServiceId, EPwType.ETH.getValue());
			if (null == pwInfoList) {
				throw new Exception("同步pw时 查询pw出错");
			}
			if (pwInfoList.size() == 0) {
				pwInfo = null;
			} else {
				pwInfo = pwInfoList.get(0);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(pwInfoService);
			pwInfoList = null;
		}
		return pwInfo;
	}

	/**
	 * 获取数据库AC对象
	 * 
	 * @author kk
	 * 
	 * @param pwServiceName
	 *            设备返回的AC名称格式为"fe.1.1/1"
	 * @param siteId
	 *            网元id
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	public AcPortInfo getAcPortInfo(String acServiceName, int siteId) throws Exception {

		AcPortInfo acPortInfo = null;
		List<AcPortInfo> acList = null;
		AcPortInfoService_MB acInfoService = null;
		PortLagService_MB portLagService = null;
		try {
			acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			String[] port_ac = acServiceName.split("/");
			if (port_ac.length != 2 && port_ac.length != 3) {
				throw new Exception("设备返回的acname格式错误");
			}
			acPortInfo = new AcPortInfo();
			acPortInfo.setAcBusinessId(Integer.parseInt(port_ac[port_ac.length - 1]));
			acPortInfo.setSiteId(siteId);
			if (port_ac.length == 2) {
				acPortInfo.setPortId(this.getPortByName(siteId, port_ac[0]).getPortId());
			} else {
				portLagService = (PortLagService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTLAG);
				PortLagInfo portLagInfo = new PortLagInfo();
				portLagInfo.setSiteId(siteId);
				portLagInfo.setLagID(Integer.parseInt(port_ac[1]));
				List<PortLagInfo> portLagInfoList = portLagService.selectByCondition(portLagInfo);
				if (null == portLagInfoList || portLagInfoList.size() != 1) {
					ExceptionManage.logDebug("同步AC失败，没有找到" + port_ac[1] + "LAG",this.getClass());
					throw new Exception("同步AC失败，没有找到" + port_ac[1] + "LAG");
				}
				acPortInfo.setLagId(portLagInfoList.get(0).getId());
			}
			
			acList = acInfoService.selectByCondition(acPortInfo);
			
			if (null == acList) {
				throw new Exception("同步ac时 查询ac出错");
			}
			
			if (acList.size() == 0) {
				acPortInfo = null;
			} else {
				acPortInfo = acList.get(0);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(portLagService);
			UiUtil.closeService_MB(acInfoService);
		}
		return acPortInfo;
	}

	/**
	 * 根据lagid 查询lag名称
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	public String getLagName(int lagId) throws Exception {
		PortLagService_MB portLagService = null;
		PortLagInfo portLagInfo = null;
		List<PortLagInfo> portlagInfoList = null;
		try {

			portLagService = (PortLagService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTLAG);
			portLagInfo = new PortLagInfo();
			portLagInfo.setId(lagId);
			portlagInfoList = portLagService.selectByCondition(portLagInfo);

			if (null != portlagInfoList && portlagInfoList.size() == 1) {
				return portlagInfoList.get(0).getLagID() + "";
			} else {
				throw new Exception("查询lag出错");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(portLagService);
		}

	}

	/**
	 * 根据类型 主键 网元获取一个qos对象，下发时去qosname和是否创建qos用
	 * 
	 * @param objtype
	 *            tunnel、pw、acport
	 * @param objid
	 *            对应主键
	 * @param siteId
	 *            网元主键
	 * @return
	 * @throws Exception
	 */
	public QosInfo getQosInfo(String objtype, int objid, int siteId) throws Exception {
		List<QosInfo> qosinfoList = null;
		QosInfoService_MB qosInfoService = null;
		QosInfo qosInfo = new QosInfo();
		try {
			// 从数据库查询qosinfo对象
			qosInfoService = (QosInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosInfo);
			qosinfoList = qosInfoService.getQosByObj(objtype, objid, siteId);

			if (null != qosinfoList && qosinfoList.size() > 0) {
				qosInfo = qosinfoList.get(0);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(qosInfoService);
		}
		return qosInfo;
	}

	/**
	 * 下发时，转换等待恢复时间   界面上为分钟。 设备要秒
	 * @param wtrttime 等待恢复时间
	 * @return 转换后的等待恢复时间
	 */
	public int convertWtrtimeSend(int wtrttime){
		return wtrttime*60;
	}
	
	/**
	 * 读取时，转换等待恢复时间   设备为秒。界面要分钟
	 * @param wtrttime 等待恢复时间
	 * @return 转换后的等待恢复时间
	 */
	public int convertWtrtimeRead(int wtrttime){
		return wtrttime/60;
	}
	
	/**
	 * 下发时，转换拖延时间   界面上为ms。 设备为50ms
	 * @param delaytime 拖延时间
	 * @return 转换后的拖延时间
	 */
	public int convertDelaytimeSend(int delaytime){
		return delaytime/50;
	}
	
	/**
	 * 读取时，转换拖延时间   界面上为ms。 设备为50ms
	 * @param delaytime 拖延时间
	 * @return 转换后的拖延时间
	 */
	public int convertDelaytimeRead(int delaytime){
		return delaytime*50;
	}
	
	
	public List<QosObject> getQosObjectList() {
		return qosObjectList;
	}

	public void setQosObjectList(List<QosObject> qosObjectList) {
		this.qosObjectList = qosObjectList;
	}
}
