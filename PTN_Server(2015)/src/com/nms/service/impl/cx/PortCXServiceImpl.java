/**   
 * 文件名：PortCXServiceImpl.java   
 * 创建人：kk   
 * 创建时间：2013-5-20 下午02:22:14 
 *   
 */
package com.nms.service.impl.cx;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.equipment.port.PortAttr;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.port.PortNniAttr;
import com.nms.db.bean.equipment.port.PortUniAttr;
import com.nms.db.bean.path.Segment;
import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.qos.QosQueue;
import com.nms.db.enums.EServiceType;
import com.nms.db.enums.QosCosLevelEnum;
import com.nms.drivechenxiao.service.bean.oam.OamObject;
import com.nms.drivechenxiao.service.bean.porteth.EthPortObject;
import com.nms.drivechenxiao.service.bean.porteth.nni.NNIObject;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.path.SegmentService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.CXOperationBase;
import com.nms.service.impl.dispatch.ETHLinkOAMConfigDispath;
import com.nms.service.impl.util.SynchroUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysObj;

/**
 * 
 * 项目名称：WuHanPTN2012 类名称：PortCXServiceImpl 类描述：同步端口用 创建人：kk 创建时间：2013-5-20 下午02:22:14 修改人：kk 修改时间：2013-5-20 下午02:22:14 修改备注：
 * 
 * @version
 * 
 */
public class PortCXServiceImpl extends CXOperationBase implements OperationServiceI {

	@SuppressWarnings("rawtypes")
	@Override
	public String excutionDelete(List objectList) throws Exception {
		return null;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		return null;
	}

	/**
	 * 修改
	 */
	@Override
	public String excutionUpdate(Object object) throws Exception {

		OperationObject operationObject = null;
		PortService_MB portService = null;
		PortInst portInst = null;
		String result = null;
		try {
			portInst = (PortInst) object;
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			operationObject = this.getOperationObject(object);

			super.sendAction(operationObject);
			operationObject = super.verification(operationObject);

			if (operationObject.isSuccess()) {
				if(!portInst.isDataDownLoad()){
					portService.saveOrUpdate(portInst);
				}
				result = operationObject.getCxActionObjectList().get(0).getStatus();
			} else {
				result = super.getErrorMessage(operationObject);
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationObject = null;
			UiUtil.closeService_MB(portService);
			portInst = null;
		}
		return result;

	}

	/**
	 * 获得NNI对象
	 * 
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	public OperationObject getOperationObject(Object object) throws Exception {
		CXActionObject cxActionObject = null;
		PortInst portInst = null;
		OperationObject operationObject = null;
		try {
			operationObject = new OperationObject();

			portInst = (PortInst) object;
			cxActionObject = new CXActionObject();
			cxActionObject.setCxNeObject(super.getCXNEObject(portInst.getSiteId()));
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setType(TypeAndActionUtil.TYPE_PORT);
			cxActionObject.setAction(TypeAndActionUtil.ACTION_UPDATE);
			cxActionObject.setEthPortObject(super.getPortObject(portInst, "SECTION", portInst.getQosQueues(), ""));
			operationObject.getCxActionObjectList().add(cxActionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			portInst = null;
			cxActionObject = null;
		}
		return operationObject;
	}

	/**
	 * 网元
	 */
	@Override
	public Object synchro(int siteId) throws Exception {
		OperationObject operationObject = new OperationObject();
		CXActionObject cxActionObject = new CXActionObject();
		ETHLinkOAMConfigDispath ethLinkOamDispatch = null;
		PortService_MB portService = null;
		try {
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setAction(TypeAndActionUtil.ACTION_SELECT);
			cxActionObject.setType(TypeAndActionUtil.TYPE_PORT);
			cxActionObject.setCxNeObject(super.getCXNEObject(siteId));
			operationObject.getCxActionObjectList().add(cxActionObject);

			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
				portService.updateActiveStatus(siteId, 0, "NONE");
				portService.updateActiveStatus(siteId, 0, "UNI");
				portService.updateActiveStatus(siteId, 0, "NNI");
				this.synchro_db(operationObject.getCxActionObjectList().get(0).getEthPortObjectList(), siteId);
			}
			//同步 以太网链路OAM
			ethLinkOamDispatch=new ETHLinkOAMConfigDispath();
			ethLinkOamDispatch.synchro(siteId);
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(portService);
		}
		return null;
	}

	/**
	 * 与数据同步
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
	public void synchro_db(List<EthPortObject> ethportObjectList, int siteId) throws Exception { 
		if (null == ethportObjectList) {
			throw new Exception("ethportObjectList is null");
		}

		PortInst portInst = null;
		List<QosQueue> qosQueueList = null;
		SynchroUtil synchroUtil = new SynchroUtil();
		for (EthPortObject ethPortObject : ethportObjectList) {
			if(ethPortObject.getName().subSequence(0, 2).equals("lb")){
				continue;
			}
			portInst = new PortInst();		
			portInst.setSiteId(siteId);
			portInst.setJobStatus(ethPortObject.getOper());
			portInst.setPortName(ethPortObject.getName());
			portInst.setIsEnabled_code(Integer.parseInt(ethPortObject.getAdmin()));
			portInst.setMacAddress(ethPortObject.getMac());
			portInst.setPortAttr(new PortAttr());			
			portInst.getPortAttr().setWorkWavelength(ethPortObject.getWavelength()); //工作波长
			portInst.getPortAttr().setMaxFrameSize(ethPortObject.getFramelen());//最大帧长
			portInst.getPortAttr().setEntranceLimit(ethPortObject.getIused());//入口限速
			portInst.getPortAttr().setExitLimit(ethPortObject.getOused());//出口限速 
			portInst.getPortAttr().setSfpExpectType(UiUtil.getCodeByValue("portSfpType", ethPortObject.getSfpexptype()).getId());//SFP期望类型
			if ("false".equals(ethPortObject.getFc())) {
				portInst.getPortAttr().setFluidControl(UiUtil.getCodeByValue("ENABLEDSTATUE", "0").getId());
			} else if ("true".equals(ethPortObject.getFc())) {
				portInst.getPortAttr().setFluidControl(UiUtil.getCodeByValue("ENABLEDSTATUE", "1").getId());
			}//流控
			if ("false".equals(ethPortObject.getXgwan())) {
				portInst.getPortAttr().setTenWan(0);
			} else if ("true".equals(ethPortObject.getXgwan())) {
				portInst.getPortAttr().setTenWan(1);
			}//启动10G WAN
			if ("false".equals(ethPortObject.getPermitpktloop())) {
				portInst.getPortAttr().setMessageLoopback(0);
			} else if ("true".equals(ethPortObject.getPermitpktloop())) {
				portInst.getPortAttr().setMessageLoopback(1);
			}//是否启动报文环回 false
			if ("false".equals(ethPortObject.getSlowproto_tocpu())) {
				portInst.getPortAttr().setSlowAgreement(0);
			} else if ("true".equals(ethPortObject.getSlowproto_tocpu())) {
				portInst.getPortAttr().setSlowAgreement(1);
			}//慢协议
			if(null!=portInst){				
				String portName_short=portInst.getPortName().substring(0, 2);	//端口前两位名称
				//因为fx和fe的速率一致。 code组里是以fe命名。 所以当成fe取值
				if("fx".equals(portName_short)){
					portName_short="fe";
				}
				if("lb".equals(portName_short)){
					portName_short="";
				}
				portInst.getPortAttr().setPortSpeed(UiUtil.getCodeByValue("portSetRate"+portName_short.toUpperCase(), ethPortObject.getSpeed()).getId());////设置端口速率，对应code表主键		
			}
			portInst.getPortAttr().setSfpVender(ethPortObject.getSfpvendor());//厂家信息
			portInst.getPortAttr().setActualSpeed(ethPortObject.getAspeed());
			portInst.getPortAttr().setSfpActual(ethPortObject.getSfptype());
			if ("0".equals(ethPortObject.getRole())) {
				portInst.setPortType("NONE");
			} else if ("1".equals(ethPortObject.getRole())) {
				portInst.setPortType("UNI");
				portInst.getPortAttr().setPortUniAttr(new PortUniAttr());
				portInst.getPortAttr().getPortUniAttr().setVlanId(ethPortObject.getUni().getPvid()); //缺省vlanid
				portInst.getPortAttr().getPortUniAttr().setVlanPri(ethPortObject.getUni().getPvpri());//缺省vlan优先级
				if(ethPortObject.getUni().getBcastlimit().equals("-1")){
					portInst.getPortAttr().getPortUniAttr().setIsBroadcast(0);
				}else{
					portInst.getPortAttr().getPortUniAttr().setIsBroadcast(1);
				    portInst.getPortAttr().getPortUniAttr().setBroadcast(ethPortObject.getUni().getBcastlimit());//广播报文抑制=武汉 广播包抑制
				}
				if(ethPortObject.getUni().getDlflimit().equals("-1")){
					portInst.getPortAttr().getPortUniAttr().setIsUnicast(0);
				}else{
					portInst.getPortAttr().getPortUniAttr().setIsUnicast(1);
					portInst.getPortAttr().getPortUniAttr().setUnicast(ethPortObject.getUni().getDlflimit()); //单播报文抑制=洪泛包抑制
				}
				if(ethPortObject.getUni().getMcastlimit().equals("-1")){
					portInst.getPortAttr().getPortUniAttr().setIsMulticast(0);
				}else{
					portInst.getPortAttr().getPortUniAttr().setIsMulticast(1);
					portInst.getPortAttr().getPortUniAttr().setMulticast(ethPortObject.getUni().getMcastlimit()); //组播报文抑制=组播包抑制
				}
				portInst.getPortAttr().getPortUniAttr().setEthernetPackaging(UiUtil.getCodeByValue("LAGNETWRAP", ethPortObject.getUni().getEncap()).getId());//以太网封装 对应code表主键
				portInst.getPortAttr().getPortUniAttr().setVlanTpId(UiUtil.getCodeByValue("LAGVLANTPID", ethPortObject.getUni().getSdtpid()).getId());//运营商vlantpid 
				portInst.getPortAttr().getPortUniAttr().setOuterVlanTpId(UiUtil.getCodeByValue("LAGOUTERTPID", ethPortObject.getUni().getTpid()).getId());//outer vlan tp id，对应code表主键
				portInst.getPortAttr().getPortUniAttr().setVlanMode(UiUtil.getCodeByValue("LAGNETVLANMODE", ethPortObject.getUni().getVlanmode()).getId()) ;//以太网vlan模式，对应code表主键 
			} else if ("2".equals(ethPortObject.getRole())) {
				portInst.setPortType("NNI");
				portInst.getPortAttr().setPortNniAttr(new PortNniAttr());
				portInst.getPortAttr().getPortNniAttr().setStaticMac(ethPortObject.getNbr().getSmac());// 用户配置的静态MAC地址
				portInst.getPortAttr().getPortNniAttr().setNeighbourId(ethPortObject.getNbr().getNeid());// 邻居网元ID
				portInst.getPortAttr().getPortNniAttr().setOperMac(ethPortObject.getNbr().getMac());// 邻居网元对应端口的MAC地址
				portInst.getPortAttr().getPortNniAttr().setOperId(ethPortObject.getNbr().getIfidx());//// 邻居网元对应端口的接口编号//对端接口ID
				if(ethPortObject.getNbr().getCcn().equals("false")){
					portInst.getPortAttr().getPortNniAttr().setCcnEnable(0) ;
				}else if(ethPortObject.getNbr().getCcn().equals("true")){
					portInst.getPortAttr().getPortNniAttr().setCcnEnable(1);
				}  //ccn承载使能 0=false 1=true
				
				portInst.getPortAttr().getPortNniAttr().setNniVlanid(ethPortObject.getNni().getPvid());
				//邻居发现状态 对应code表主键	默认是灰色的“自动发现中”			
				//缺省vlanid
				//缺省vlan优先级
				//以上3个未知
				if(!"".equals(ethPortObject.getNbr().getStat())){
					portInst.getPortAttr().getPortNniAttr().setStat(Integer.parseInt(ethPortObject.getNbr().getStat()));
				}
			}
			qosQueueList = new ArrayList<QosQueue>();
			qosQueueList.add(this.getQosQue(siteId, QosCosLevelEnum.BE.getValue(), "0", ethPortObject.getBe().getWeight(), "", "", "", ethPortObject.getBe().getYellowwredstart(), ethPortObject.getBe().getYellowwredend(), ethPortObject.getBe().getYellowdroprate(), ethPortObject.getBe().getWreden(), ""));
			qosQueueList.add(this.getQosQue(siteId, QosCosLevelEnum.AF1.getValue(), ethPortObject.getAf1().getCir(), ethPortObject.getAf1().getWeight(), ethPortObject.getAf1().getGreenwredstart(), ethPortObject.getAf1().getGreenwredend(), ethPortObject.getAf1().getGreendroprate(), ethPortObject.getAf1().getYellowwredstart(), ethPortObject.getAf1().getYellowwredend(), ethPortObject.getAf1().getYellowdroprate(), ethPortObject.getAf1().getWreden(), ethPortObject.getAf1().getMaxbw()));
			qosQueueList.add(this.getQosQue(siteId, QosCosLevelEnum.AF2.getValue(), ethPortObject.getAf2().getCir(), ethPortObject.getAf2().getWeight(), ethPortObject.getAf2().getGreenwredstart(), ethPortObject.getAf2().getGreenwredend(), ethPortObject.getAf2().getGreendroprate(), ethPortObject.getAf2().getYellowwredstart(), ethPortObject.getAf2().getYellowwredend(), ethPortObject.getAf2().getYellowdroprate(), ethPortObject.getAf2().getWreden(), ethPortObject.getAf2().getMaxbw()));
			qosQueueList.add(this.getQosQue(siteId, QosCosLevelEnum.AF3.getValue(), ethPortObject.getAf3().getCir(), ethPortObject.getAf3().getWeight(), ethPortObject.getAf3().getGreenwredstart(), ethPortObject.getAf3().getGreenwredend(), ethPortObject.getAf3().getGreendroprate(), ethPortObject.getAf3().getYellowwredstart(), ethPortObject.getAf3().getYellowwredend(), ethPortObject.getAf3().getYellowdroprate(), ethPortObject.getAf3().getWreden(), ethPortObject.getAf3().getMaxbw()));
			qosQueueList.add(this.getQosQue(siteId, QosCosLevelEnum.AF4.getValue(), ethPortObject.getAf4().getCir(), ethPortObject.getAf4().getWeight(), ethPortObject.getAf4().getGreenwredstart(), ethPortObject.getAf4().getGreenwredend(), ethPortObject.getAf4().getGreendroprate(), ethPortObject.getAf4().getYellowwredstart(), ethPortObject.getAf4().getYellowwredend(), ethPortObject.getAf4().getYellowdroprate(), ethPortObject.getAf4().getWreden(), ethPortObject.getAf4().getMaxbw()));
			qosQueueList.add(this.getQosQue(siteId, QosCosLevelEnum.EF.getValue(), ethPortObject.getEf().getCir(), "", ethPortObject.getEf().getGreenwredstart(), ethPortObject.getEf().getGreenwredend(), ethPortObject.getEf().getGreendroprate(), "", "", "", ethPortObject.getEf().getWreden(), ""));
			qosQueueList.add(this.getQosQue(siteId, QosCosLevelEnum.CS6.getValue(), ethPortObject.getCs6().getCir(), "", ethPortObject.getCs6().getGreenwredstart(), ethPortObject.getCs6().getGreenwredend(), ethPortObject.getCs6().getGreendroprate(), "", "", "", ethPortObject.getCs6().getWreden(), ""));
			qosQueueList.add(this.getQosQue(siteId, QosCosLevelEnum.CS7.getValue(), ethPortObject.getCs7().getCir(), "", ethPortObject.getCs7().getGreenwredstart(), ethPortObject.getCs7().getGreenwredend(), ethPortObject.getCs7().getGreendroprate(), "", "", "", ethPortObject.getCs7().getWreden(), ""));

			portInst.setQosQueues(qosQueueList);
			portInst.setOamInfo(getOamInfo(ethPortObject.getOam(), ethPortObject, siteId));
			if(null!=ethPortObject.getNni()){
				NNIObject nNIObject = ethPortObject.getNni();
				if(null!=nNIObject.getLlspexptoclr()&&!"".equals(nNIObject.getLlspexptoclr())){
					portInst.setExpMappingLLspInput(Integer.parseInt(nNIObject.getLlspexptoclr()));
				}
				if(null!=nNIObject.getLlspclrtoexp()&&!"".equals(nNIObject.getLlspclrtoexp())){
					portInst.setExpMappingLLspOutput(Integer.parseInt(nNIObject.getLlspclrtoexp()));
				}
				if(null!=nNIObject.getElspexptocos()&&!"".equals(nNIObject.getElspexptocos())){
					portInst.setExpMappingELspInput(Integer.parseInt(nNIObject.getElspexptocos()));
				}
				if(null!=nNIObject.getElspcostoexp()&&!"".equals(nNIObject.getElspcostoexp())){
					portInst.setExpMappingELspOutput(Integer.parseInt(nNIObject.getElspcostoexp()));
				}
			}
			synchroUtil.updatePort(portInst);
		}
	}

	private OamInfo getOamInfo(OamObject oamObject, EthPortObject ethPortObject, int siteId) throws Exception {
		if ("".equals(oamObject.getMegid()) || null == oamObject.getMegid()) {
			return null;
		  }
		OamInfo oamInfo = null;
		List<Segment> segmentList = null;
		PortInst portInst2 = null;
		PortService_MB portService = null;
		SegmentService_MB segmentService = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			segmentService = (SegmentService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT);
			PortInst portinst = new PortInst();
			portinst.setPortName(ethPortObject.getName());
			portinst.setSiteId(siteId);
			
			oamInfo = convertOamInfo_mep(oamObject).get(0);
			
			List<PortInst> portList = portService.select(portinst);
			if (portList.size() == 1) {
				portInst2 = portList.get(0);
				Segment segment = new Segment();			
				segment.setASITEID(siteId);
				segment.setAPORTID(portInst2.getPortId());
				segmentList = segmentService.selectBySiteIdAndPort(segment);
				if (segmentList.size() == 1) {
					oamInfo.getOamMep().setServiceId(segmentList.get(0).getId());
				} else {
					oamInfo.getOamMep().setServiceId(0);
				}
				
				oamInfo.getOamMep().setObjId(portInst2.getPortId());
				oamInfo.getOamMep().setObjType(EServiceType.SECTION.toString());
				oamInfo.getOamMep().setSiteId(siteId);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(portService);
			UiUtil.closeService_MB(segmentService);
		}
		return oamInfo;
	}

	/**
	 * 获取端口qos对象
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private QosQueue getQosQue(int siteId, int cos, String cir, String weight, String greenLowThresh, String greenHighThresh, String greenProbability, String yellowLowThresh, String yellowHighThresh, String yellowProbability, String wredEnable, String mostBandwidth) {

		QosQueue qosQueue = new QosQueue();
		qosQueue.setSiteId(siteId);
		qosQueue.setObjType(EServiceType.SECTION.toString());
		qosQueue.setQueueType("SP+DWRR");
		qosQueue.setCos(cos);
		if (!"".equals(cir)) {
			qosQueue.setCir(Integer.parseInt(cir));
		}
		if (!"".equals(weight)) {
			qosQueue.setWeight(Integer.parseInt(weight));
		}
		if (!"".equals(greenLowThresh)) {
			qosQueue.setGreenLowThresh(Integer.parseInt(greenLowThresh));
		}
		if (!"".equals(greenHighThresh)) {
			qosQueue.setGreenHighThresh(Integer.parseInt(greenHighThresh));
		}
		if (!"".equals(greenProbability)) {
			qosQueue.setGreenProbability(Integer.parseInt(greenProbability));
		}
		if (!"".equals(yellowLowThresh)) {
			qosQueue.setYellowLowThresh(Integer.parseInt(yellowLowThresh));
		}
		if (!"".equals(yellowHighThresh)) {
			qosQueue.setYellowHighThresh(Integer.parseInt(yellowHighThresh));
		}
		if (!"".equals(yellowProbability)) {
			qosQueue.setYellowProbability(Integer.parseInt(yellowProbability));
		}
		if (!"".equals(wredEnable)) {
			qosQueue.setWredEnable(true);
		}
		if (!"".equals(mostBandwidth)) {
			qosQueue.setMostBandwidth(mostBandwidth);
		} else {
			qosQueue.setMostBandwidth(ResourceUtil.srcStr(StringKeysObj.QOS_UNLIMITED));
		}
		return qosQueue;
	}

}
